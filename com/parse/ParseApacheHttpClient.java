package com.parse;

import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.net.http.AndroidHttpClient;
import com.google.common.net.HttpHeaders;
import com.parse.http.ParseHttpBody;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpRequest.Method;
import com.parse.http.ParseHttpResponse;
import com.parse.http.ParseHttpResponse.Builder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

class ParseApacheHttpClient extends ParseHttpClient<HttpUriRequest, HttpResponse> {
    private static final String CONTENT_ENCODING_HEADER = "Content-Encoding";
    private DefaultHttpClient apacheClient;

    private static class ParseApacheHttpEntity extends InputStreamEntity {
        private ParseHttpBody parseBody;

        public ParseApacheHttpEntity(ParseHttpBody parseBody) throws IOException {
            super(parseBody.getContent(), parseBody.getContentLength());
            super.setContentType(parseBody.getContentType());
            this.parseBody = parseBody;
        }

        public void writeTo(OutputStream out) throws IOException {
            this.parseBody.writeTo(out);
        }
    }

    public ParseApacheHttpClient(int socketOperationTimeout, SSLSessionCache sslSessionCache) {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(params, false);
        HttpConnectionParams.setConnectionTimeout(params, socketOperationTimeout);
        HttpConnectionParams.setSoTimeout(params, socketOperationTimeout);
        HttpConnectionParams.setSocketBufferSize(params, 8192);
        HttpClientParams.setRedirecting(params, false);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLCertificateSocketFactory.getHttpSocketFactory(socketOperationTimeout, sslSessionCache), 443));
        String maxConnectionsStr = System.getProperty("http.maxConnections");
        if (maxConnectionsStr != null) {
            int maxConnections = Integer.parseInt(maxConnectionsStr);
            ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRouteBean(maxConnections));
            ConnManagerParams.setMaxTotalConnections(params, maxConnections);
        }
        String host = System.getProperty("http.proxyHost");
        String portString = System.getProperty("http.proxyPort");
        if (!(host == null || host.length() == 0 || portString == null || portString.length() == 0)) {
            params.setParameter("http.route.default-proxy", new HttpHost(host, Integer.parseInt(portString), "http"));
        }
        this.apacheClient = new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
        this.apacheClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
    }

    ParseHttpResponse executeInternal(ParseHttpRequest parseRequest) throws IOException {
        return getResponse(this.apacheClient.execute(getRequest(parseRequest)));
    }

    ParseHttpResponse getResponse(HttpResponse apacheResponse) throws IOException {
        int i = 0;
        if (apacheResponse == null) {
            throw new IllegalArgumentException("HttpResponse passed to getResponse should not be null.");
        }
        InputStream content;
        int statusCode = apacheResponse.getStatusLine().getStatusCode();
        if (disableHttpLibraryAutoDecompress()) {
            content = apacheResponse.getEntity().getContent();
        } else {
            content = AndroidHttpClient.getUngzippedContent(apacheResponse.getEntity());
        }
        int totalSize = -1;
        Header[] contentLengthHeader = apacheResponse.getHeaders(HttpHeaders.CONTENT_LENGTH);
        if (contentLengthHeader.length > 0) {
            totalSize = Integer.parseInt(contentLengthHeader[0].getValue());
        }
        String reasonPhrase = apacheResponse.getStatusLine().getReasonPhrase();
        Map<String, String> headers = new HashMap();
        Header[] allHeaders = apacheResponse.getAllHeaders();
        int length = allHeaders.length;
        while (i < length) {
            Header header = allHeaders[i];
            headers.put(header.getName(), header.getValue());
            i++;
        }
        if (!disableHttpLibraryAutoDecompress()) {
            headers.remove("Content-Encoding");
        }
        String contentType = null;
        HttpEntity entity = apacheResponse.getEntity();
        if (!(entity == null || entity.getContentType() == null)) {
            contentType = entity.getContentType().getValue();
        }
        return new Builder().setStatusCode(statusCode).setContent(content).setTotalSize((long) totalSize).setReasonPhrase(reasonPhrase).setHeaders(headers).setContentType(contentType).build();
    }

    HttpUriRequest getRequest(ParseHttpRequest parseRequest) throws IOException {
        if (parseRequest == null) {
            throw new IllegalArgumentException("ParseHttpRequest passed to getApacheRequest should not be null.");
        }
        HttpUriRequest apacheRequest;
        Method method = parseRequest.getMethod();
        String url = parseRequest.getUrl();
        switch (method) {
            case GET:
                apacheRequest = new HttpGet(url);
                break;
            case DELETE:
                apacheRequest = new HttpDelete(url);
                break;
            case POST:
                apacheRequest = new HttpPost(url);
                break;
            case PUT:
                apacheRequest = new HttpPut(url);
                break;
            default:
                throw new IllegalStateException("Unsupported http method " + method.toString());
        }
        for (Entry<String, String> entry : parseRequest.getAllHeaders().entrySet()) {
            apacheRequest.setHeader((String) entry.getKey(), (String) entry.getValue());
        }
        AndroidHttpClient.modifyRequestToAcceptGzipResponse(apacheRequest);
        ParseHttpBody body = parseRequest.getBody();
        switch (method) {
            case POST:
                ((HttpPost) apacheRequest).setEntity(new ParseApacheHttpEntity(body));
                break;
            case PUT:
                ((HttpPut) apacheRequest).setEntity(new ParseApacheHttpEntity(body));
                break;
        }
        return apacheRequest;
    }
}
