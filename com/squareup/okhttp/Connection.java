package com.squareup.okhttp;

import com.google.common.net.HttpHeaders;
import com.squareup.okhttp.internal.ConnectionSpecSelector;
import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.framed.FramedConnection;
import com.squareup.okhttp.internal.framed.FramedConnection.Builder;
import com.squareup.okhttp.internal.http.FramedTransport;
import com.squareup.okhttp.internal.http.HttpConnection;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.HttpTransport;
import com.squareup.okhttp.internal.http.OkHeaders;
import com.squareup.okhttp.internal.http.RouteException;
import com.squareup.okhttp.internal.http.Transport;
import com.squareup.okhttp.internal.tls.OkHostnameVerifier;
import java.io.IOException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.UnknownServiceException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Source;

public final class Connection {
    private boolean connected = false;
    private FramedConnection framedConnection;
    private Handshake handshake;
    private HttpConnection httpConnection;
    private long idleStartTimeNs;
    private Object owner;
    private final ConnectionPool pool;
    private Protocol protocol = Protocol.HTTP_1_1;
    private int recycleCount;
    private final Route route;
    private Socket socket;

    public Connection(ConnectionPool pool, Route route) {
        this.pool = pool;
        this.route = route;
    }

    Object getOwner() {
        Object obj;
        synchronized (this.pool) {
            obj = this.owner;
        }
        return obj;
    }

    void setOwner(Object owner) {
        if (!isFramed()) {
            synchronized (this.pool) {
                if (this.owner != null) {
                    throw new IllegalStateException("Connection already has an owner!");
                }
                this.owner = owner;
            }
        }
    }

    boolean clearOwner() {
        boolean z;
        synchronized (this.pool) {
            if (this.owner == null) {
                z = false;
            } else {
                this.owner = null;
                z = true;
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void closeIfOwnedBy(java.lang.Object r3) throws java.io.IOException {
        /*
        r2 = this;
        r0 = r2.isFramed();
        if (r0 == 0) goto L_0x000c;
    L_0x0006:
        r0 = new java.lang.IllegalStateException;
        r0.<init>();
        throw r0;
    L_0x000c:
        r1 = r2.pool;
        monitor-enter(r1);
        r0 = r2.owner;	 Catch:{ all -> 0x0023 }
        if (r0 == r3) goto L_0x0015;
    L_0x0013:
        monitor-exit(r1);	 Catch:{ all -> 0x0023 }
    L_0x0014:
        return;
    L_0x0015:
        r0 = 0;
        r2.owner = r0;	 Catch:{ all -> 0x0023 }
        monitor-exit(r1);	 Catch:{ all -> 0x0023 }
        r0 = r2.socket;
        if (r0 == 0) goto L_0x0014;
    L_0x001d:
        r0 = r2.socket;
        r0.close();
        goto L_0x0014;
    L_0x0023:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0023 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.Connection.closeIfOwnedBy(java.lang.Object):void");
    }

    void connect(int connectTimeout, int readTimeout, int writeTimeout, Request request, List<ConnectionSpec> connectionSpecs, boolean connectionRetryEnabled) throws RouteException {
        if (this.connected) {
            throw new IllegalStateException("already connected");
        }
        RouteException routeException = null;
        ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(connectionSpecs);
        Proxy proxy = this.route.getProxy();
        Address address = this.route.getAddress();
        if (this.route.address.getSslSocketFactory() != null || connectionSpecs.contains(ConnectionSpec.CLEARTEXT)) {
            while (!this.connected) {
                try {
                    Socket createSocket = (proxy.type() == Type.DIRECT || proxy.type() == Type.HTTP) ? address.getSocketFactory().createSocket() : new Socket(proxy);
                    this.socket = createSocket;
                    connectSocket(connectTimeout, readTimeout, writeTimeout, request, connectionSpecSelector);
                    this.connected = true;
                } catch (IOException e) {
                    Util.closeQuietly(this.socket);
                    this.socket = null;
                    if (routeException == null) {
                        routeException = new RouteException(e);
                    } else {
                        routeException.addConnectException(e);
                    }
                    if (!connectionRetryEnabled || !connectionSpecSelector.connectionFailed(e)) {
                        throw routeException;
                    }
                }
            }
            return;
        }
        throw new RouteException(new UnknownServiceException("CLEARTEXT communication not supported: " + connectionSpecs));
    }

    private void connectSocket(int connectTimeout, int readTimeout, int writeTimeout, Request request, ConnectionSpecSelector connectionSpecSelector) throws IOException {
        this.socket.setSoTimeout(readTimeout);
        Platform.get().connectSocket(this.socket, this.route.getSocketAddress(), connectTimeout);
        if (this.route.address.getSslSocketFactory() != null) {
            connectTls(readTimeout, writeTimeout, request, connectionSpecSelector);
        }
        if (this.protocol == Protocol.SPDY_3 || this.protocol == Protocol.HTTP_2) {
            this.socket.setSoTimeout(0);
            this.framedConnection = new Builder(this.route.address.uriHost, true, this.socket).protocol(this.protocol).build();
            this.framedConnection.sendConnectionPreface();
            return;
        }
        this.httpConnection = new HttpConnection(this.pool, this, this.socket);
    }

    private void connectTls(int readTimeout, int writeTimeout, Request request, ConnectionSpecSelector connectionSpecSelector) throws IOException {
        if (this.route.requiresTunnel()) {
            createTunnel(readTimeout, writeTimeout, request);
        }
        Address address = this.route.getAddress();
        Socket sslSocket = null;
        try {
            sslSocket = (SSLSocket) address.getSslSocketFactory().createSocket(this.socket, address.getUriHost(), address.getUriPort(), true);
            ConnectionSpec connectionSpec = connectionSpecSelector.configureSecureSocket(sslSocket);
            if (connectionSpec.supportsTlsExtensions()) {
                Platform.get().configureTlsExtensions(sslSocket, address.getUriHost(), address.getProtocols());
            }
            sslSocket.startHandshake();
            Handshake unverifiedHandshake = Handshake.get(sslSocket.getSession());
            if (address.getHostnameVerifier().verify(address.getUriHost(), sslSocket.getSession())) {
                address.getCertificatePinner().check(address.getUriHost(), unverifiedHandshake.peerCertificates());
                String maybeProtocol = connectionSpec.supportsTlsExtensions() ? Platform.get().getSelectedProtocol(sslSocket) : null;
                this.protocol = maybeProtocol != null ? Protocol.get(maybeProtocol) : Protocol.HTTP_1_1;
                this.handshake = unverifiedHandshake;
                this.socket = sslSocket;
                if (sslSocket != null) {
                    Platform.get().afterHandshake(sslSocket);
                }
                if (!true) {
                    Util.closeQuietly(sslSocket);
                    return;
                }
                return;
            }
            X509Certificate cert = (X509Certificate) unverifiedHandshake.peerCertificates().get(0);
            throw new SSLPeerUnverifiedException("Hostname " + address.getUriHost() + " not verified:" + "\n    certificate: " + CertificatePinner.pin(cert) + "\n    DN: " + cert.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.allSubjectAltNames(cert));
        } catch (AssertionError e) {
            if (Util.isAndroidGetsocknameError(e)) {
                throw new IOException(e);
            }
            throw e;
        } catch (Throwable th) {
            if (sslSocket != null) {
                Platform.get().afterHandshake(sslSocket);
            }
            if (!false) {
                Util.closeQuietly(sslSocket);
            }
        }
    }

    private void createTunnel(int readTimeout, int writeTimeout, Request request) throws IOException {
        Request tunnelRequest = createTunnelRequest(request);
        HttpConnection tunnelConnection = new HttpConnection(this.pool, this, this.socket);
        tunnelConnection.setTimeouts(readTimeout, writeTimeout);
        HttpUrl url = tunnelRequest.httpUrl();
        String requestLine = "CONNECT " + url.host() + ":" + url.port() + " HTTP/1.1";
        do {
            tunnelConnection.writeRequest(tunnelRequest.headers(), requestLine);
            tunnelConnection.flush();
            Response response = tunnelConnection.readResponse().request(tunnelRequest).build();
            long contentLength = OkHeaders.contentLength(response);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source body = tunnelConnection.newFixedLengthSource(contentLength);
            Util.skipAll(body, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            body.close();
            switch (response.code()) {
                case 200:
                    if (tunnelConnection.bufferSize() > 0) {
                        throw new IOException("TLS tunnel buffered too many bytes!");
                    }
                    return;
                case 407:
                    tunnelRequest = OkHeaders.processAuthHeader(this.route.getAddress().getAuthenticator(), response, this.route.getProxy());
                    break;
                default:
                    throw new IOException("Unexpected response code for CONNECT: " + response.code());
            }
        } while (tunnelRequest != null);
        throw new IOException("Failed to authenticate with proxy");
    }

    private Request createTunnelRequest(Request request) throws IOException {
        HttpUrl tunnelUrl = new HttpUrl.Builder().scheme("https").host(request.httpUrl().host()).port(request.httpUrl().port()).build();
        Request.Builder result = new Request.Builder().url(tunnelUrl).header(HttpHeaders.HOST, Util.hostHeader(tunnelUrl)).header("Proxy-Connection", "Keep-Alive");
        String userAgent = request.header(HttpHeaders.USER_AGENT);
        if (userAgent != null) {
            result.header(HttpHeaders.USER_AGENT, userAgent);
        }
        String proxyAuthorization = request.header(HttpHeaders.PROXY_AUTHORIZATION);
        if (proxyAuthorization != null) {
            result.header(HttpHeaders.PROXY_AUTHORIZATION, proxyAuthorization);
        }
        return result.build();
    }

    void connectAndSetOwner(OkHttpClient client, Object owner, Request request) throws RouteException {
        setOwner(owner);
        if (!isConnected()) {
            Request request2 = request;
            connect(client.getConnectTimeout(), client.getReadTimeout(), client.getWriteTimeout(), request2, this.route.address.getConnectionSpecs(), client.getRetryOnConnectionFailure());
            if (isFramed()) {
                client.getConnectionPool().share(this);
            }
            client.routeDatabase().connected(getRoute());
        }
        setTimeouts(client.getReadTimeout(), client.getWriteTimeout());
    }

    boolean isConnected() {
        return this.connected;
    }

    public Route getRoute() {
        return this.route;
    }

    public Socket getSocket() {
        return this.socket;
    }

    BufferedSource rawSource() {
        if (this.httpConnection != null) {
            return this.httpConnection.rawSource();
        }
        throw new UnsupportedOperationException();
    }

    BufferedSink rawSink() {
        if (this.httpConnection != null) {
            return this.httpConnection.rawSink();
        }
        throw new UnsupportedOperationException();
    }

    boolean isAlive() {
        return (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) ? false : true;
    }

    boolean isReadable() {
        if (this.httpConnection != null) {
            return this.httpConnection.isReadable();
        }
        return true;
    }

    void resetIdleStartTime() {
        if (this.framedConnection != null) {
            throw new IllegalStateException("framedConnection != null");
        }
        this.idleStartTimeNs = System.nanoTime();
    }

    boolean isIdle() {
        return this.framedConnection == null || this.framedConnection.isIdle();
    }

    long getIdleStartTimeNs() {
        return this.framedConnection == null ? this.idleStartTimeNs : this.framedConnection.getIdleStartTimeNs();
    }

    public Handshake getHandshake() {
        return this.handshake;
    }

    Transport newTransport(HttpEngine httpEngine) throws IOException {
        return this.framedConnection != null ? new FramedTransport(httpEngine, this.framedConnection) : new HttpTransport(httpEngine, this.httpConnection);
    }

    boolean isFramed() {
        return this.framedConnection != null;
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    void setProtocol(Protocol protocol) {
        if (protocol == null) {
            throw new IllegalArgumentException("protocol == null");
        }
        this.protocol = protocol;
    }

    void setTimeouts(int readTimeoutMillis, int writeTimeoutMillis) throws RouteException {
        if (!this.connected) {
            throw new IllegalStateException("setTimeouts - not connected");
        } else if (this.httpConnection != null) {
            try {
                this.socket.setSoTimeout(readTimeoutMillis);
                this.httpConnection.setTimeouts(readTimeoutMillis, writeTimeoutMillis);
            } catch (IOException e) {
                throw new RouteException(e);
            }
        }
    }

    void incrementRecycleCount() {
        this.recycleCount++;
    }

    int recycleCount() {
        return this.recycleCount;
    }

    public String toString() {
        return "Connection{" + this.route.address.uriHost + ":" + this.route.address.uriPort + ", proxy=" + this.route.proxy + " hostAddress=" + this.route.inetSocketAddress.getAddress().getHostAddress() + " cipherSuite=" + (this.handshake != null ? this.handshake.cipherSuite() : "none") + " protocol=" + this.protocol + '}';
    }
}
