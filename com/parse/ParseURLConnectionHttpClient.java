package com.parse;

import android.net.SSLSessionCache;
import com.google.android.vending.expansion.downloader.Constants;
import com.parse.http.ParseHttpBody;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpResponse;
import com.parse.http.ParseHttpResponse.Builder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class ParseURLConnectionHttpClient extends ParseHttpClient<HttpURLConnection, HttpURLConnection> {
    private static final String ACCEPT_ENCODING_HEADER = "Accept-encoding";
    private static final String CONTENT_LENGTH_HEADER = "Content-Length";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String GZIP_ENCODING = "gzip";
    private int socketOperationTimeout;

    public ParseURLConnectionHttpClient(int socketOperationTimeout, SSLSessionCache sslSessionCache) {
        this.socketOperationTimeout = socketOperationTimeout;
    }

    ParseHttpResponse executeInternal(ParseHttpRequest parseRequest) throws IOException {
        HttpURLConnection connection = getRequest(parseRequest);
        ParseHttpBody body = parseRequest.getBody();
        if (body != null) {
            OutputStream outputStream = connection.getOutputStream();
            body.writeTo(outputStream);
            outputStream.flush();
            outputStream.close();
        }
        return getResponse(connection);
    }

    HttpURLConnection getRequest(ParseHttpRequest parseRequest) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(parseRequest.getUrl()).openConnection();
        connection.setRequestMethod(parseRequest.getMethod().toString());
        connection.setConnectTimeout(this.socketOperationTimeout);
        connection.setReadTimeout(this.socketOperationTimeout);
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        for (Entry<String, String> entry : parseRequest.getAllHeaders().entrySet()) {
            connection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        if (disableHttpLibraryAutoDecompress()) {
            connection.setRequestProperty(ACCEPT_ENCODING_HEADER, GZIP_ENCODING);
        }
        ParseHttpBody body = parseRequest.getBody();
        if (body != null) {
            connection.setRequestProperty("Content-Length", String.valueOf(body.getContentLength()));
            connection.setRequestProperty("Content-Type", body.getContentType());
            connection.setFixedLengthStreamingMode(body.getContentLength());
            connection.setDoOutput(true);
        }
        return connection;
    }

    ParseHttpResponse getResponse(HttpURLConnection connection) throws IOException {
        InputStream content;
        int statusCode = connection.getResponseCode();
        if (statusCode < Constants.STATUS_BAD_REQUEST) {
            content = connection.getInputStream();
        } else {
            content = connection.getErrorStream();
        }
        int totalSize = connection.getContentLength();
        String reasonPhrase = connection.getResponseMessage();
        Map<String, String> headers = new HashMap();
        for (Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null && ((List) entry.getValue()).size() > 0) {
                Object obj;
                Object key = entry.getKey();
                if (entry.getValue() == null) {
                    obj = "";
                } else {
                    String str = (String) ((List) entry.getValue()).get(0);
                }
                headers.put(key, obj);
            }
        }
        return new Builder().setStatusCode(statusCode).setContent(content).setTotalSize((long) totalSize).setReasonPhrase(reasonPhrase).setHeaders(headers).setContentType(connection.getContentType()).build();
    }
}
