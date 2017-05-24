package com.parse;

import android.net.SSLSessionCache;
import bolts.Capture;
import com.parse.http.ParseHttpBody;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpRequest.Method;
import com.parse.http.ParseHttpResponse;
import com.parse.http.ParseNetworkInterceptor;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

class ParseOkHttpClient extends ParseHttpClient<Request, Response> {
    private static final String OKHTTP_DELETE = "DELETE";
    private static final String OKHTTP_GET = "GET";
    private static final String OKHTTP_POST = "POST";
    private static final String OKHTTP_PUT = "PUT";
    private OkHttpClient okHttpClient;

    private static class ParseOkHttpRequestBody extends RequestBody {
        private ParseHttpBody parseBody;

        public ParseOkHttpRequestBody(ParseHttpBody parseBody) {
            this.parseBody = parseBody;
        }

        public long contentLength() throws IOException {
            return this.parseBody.getContentLength();
        }

        public MediaType contentType() {
            return this.parseBody.getContentType() == null ? null : MediaType.parse(this.parseBody.getContentType());
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
            this.parseBody.writeTo(bufferedSink.outputStream());
        }

        public ParseHttpBody getParseHttpBody() {
            return this.parseBody;
        }
    }

    public ParseOkHttpClient(int socketOperationTimeout, SSLSessionCache sslSessionCache) {
        Builder builder = new Builder();
        builder.connectTimeout((long) socketOperationTimeout, TimeUnit.MILLISECONDS);
        builder.readTimeout((long) socketOperationTimeout, TimeUnit.MILLISECONDS);
        builder.followRedirects(false);
        this.okHttpClient = builder.build();
    }

    ParseHttpResponse executeInternal(ParseHttpRequest parseRequest) throws IOException {
        return getResponse(this.okHttpClient.newCall(getRequest(parseRequest)).execute());
    }

    ParseHttpResponse getResponse(Response okHttpResponse) throws IOException {
        int statusCode = okHttpResponse.code();
        InputStream content = okHttpResponse.body().byteStream();
        int totalSize = (int) okHttpResponse.body().contentLength();
        String reasonPhrase = okHttpResponse.message();
        Map<String, String> headers = new HashMap();
        for (String name : okHttpResponse.headers().names()) {
            headers.put(name, okHttpResponse.header(name));
        }
        String contentType = null;
        ResponseBody body = okHttpResponse.body();
        if (!(body == null || body.contentType() == null)) {
            contentType = body.contentType().toString();
        }
        return new ParseHttpResponse.Builder().setStatusCode(statusCode).setContent(content).setTotalSize((long) totalSize).setReasonPhrase(reasonPhrase).setHeaders(headers).setContentType(contentType).build();
    }

    Request getRequest(ParseHttpRequest parseRequest) throws IOException {
        Request.Builder okHttpRequestBuilder = new Request.Builder();
        Method method = parseRequest.getMethod();
        switch (method) {
            case GET:
                okHttpRequestBuilder.get();
                break;
            case DELETE:
                okHttpRequestBuilder.delete();
                break;
            case POST:
            case PUT:
                break;
            default:
                throw new IllegalStateException("Unsupported http method " + method.toString());
        }
        okHttpRequestBuilder.url(parseRequest.getUrl());
        Headers.Builder okHttpHeadersBuilder = new Headers.Builder();
        for (Entry<String, String> entry : parseRequest.getAllHeaders().entrySet()) {
            okHttpHeadersBuilder.add((String) entry.getKey(), (String) entry.getValue());
        }
        okHttpRequestBuilder.headers(okHttpHeadersBuilder.build());
        ParseHttpBody parseBody = parseRequest.getBody();
        ParseOkHttpRequestBody okHttpRequestBody = null;
        if (parseBody instanceof ParseByteArrayHttpBody) {
            okHttpRequestBody = new ParseOkHttpRequestBody(parseBody);
        }
        switch (method) {
            case POST:
                okHttpRequestBuilder.post(okHttpRequestBody);
                break;
            case PUT:
                okHttpRequestBuilder.put(okHttpRequestBody);
                break;
        }
        return okHttpRequestBuilder.build();
    }

    private ParseHttpRequest getParseHttpRequest(Request okHttpRequest) {
        ParseHttpRequest.Builder parseRequestBuilder = new ParseHttpRequest.Builder();
        String method = okHttpRequest.method();
        int i = -1;
        switch (method.hashCode()) {
            case 70454:
                if (method.equals(OKHTTP_GET)) {
                    i = 0;
                    break;
                }
                break;
            case 79599:
                if (method.equals(OKHTTP_PUT)) {
                    i = 3;
                    break;
                }
                break;
            case 2461856:
                if (method.equals(OKHTTP_POST)) {
                    i = 2;
                    break;
                }
                break;
            case 2012838315:
                if (method.equals(OKHTTP_DELETE)) {
                    i = 1;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                parseRequestBuilder.setMethod(Method.GET);
                break;
            case 1:
                parseRequestBuilder.setMethod(Method.DELETE);
                break;
            case 2:
                parseRequestBuilder.setMethod(Method.POST);
                break;
            case 3:
                parseRequestBuilder.setMethod(Method.PUT);
                break;
            default:
                throw new IllegalArgumentException("Invalid http method " + okHttpRequest.method());
        }
        parseRequestBuilder.setUrl(okHttpRequest.url().toString());
        for (Entry<String, List<String>> entry : okHttpRequest.headers().toMultimap().entrySet()) {
            parseRequestBuilder.addHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0));
        }
        ParseOkHttpRequestBody okHttpBody = (ParseOkHttpRequestBody) okHttpRequest.body();
        if (okHttpBody != null) {
            parseRequestBuilder.setBody(okHttpBody.getParseHttpBody());
        }
        return parseRequestBuilder.build();
    }

    void addExternalInterceptor(final ParseNetworkInterceptor parseNetworkInterceptor) {
        Builder builder = this.okHttpClient.newBuilder();
        builder.networkInterceptors().add(new Interceptor() {
            public Response intercept(final Chain okHttpChain) throws IOException {
                final ParseHttpRequest parseRequest = ParseOkHttpClient.this.getParseHttpRequest(okHttpChain.request());
                final Capture<Response> okHttpResponseCapture = new Capture();
                final ParseHttpResponse parseResponse = parseNetworkInterceptor.intercept(new ParseNetworkInterceptor.Chain() {
                    public ParseHttpRequest getRequest() {
                        return parseRequest;
                    }

                    public ParseHttpResponse proceed(ParseHttpRequest parseRequest) throws IOException {
                        Response okHttpResponse = okHttpChain.proceed(ParseOkHttpClient.this.getRequest(parseRequest));
                        okHttpResponseCapture.set(okHttpResponse);
                        return ParseOkHttpClient.this.getResponse(okHttpResponse);
                    }
                });
                Response.Builder newOkHttpResponseBuilder = ((Response) okHttpResponseCapture.get()).newBuilder();
                newOkHttpResponseBuilder.code(parseResponse.getStatusCode()).message(parseResponse.getReasonPhrase());
                if (parseResponse.getAllHeaders() != null) {
                    for (Entry<String, String> entry : parseResponse.getAllHeaders().entrySet()) {
                        newOkHttpResponseBuilder.header((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                newOkHttpResponseBuilder.body(new ResponseBody() {
                    public MediaType contentType() {
                        if (parseResponse.getContentType() == null) {
                            return null;
                        }
                        return MediaType.parse(parseResponse.getContentType());
                    }

                    public long contentLength() {
                        return parseResponse.getTotalSize();
                    }

                    public BufferedSource source() {
                        if (parseResponse.getContent() == null) {
                            return null;
                        }
                        return Okio.buffer(Okio.source(parseResponse.getContent()));
                    }
                });
                return newOkHttpResponseBuilder.build();
            }
        });
        this.okHttpClient = builder.build();
    }
}
