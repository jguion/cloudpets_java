package com.parse.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ParseHttpRequest {
    private final ParseHttpBody body;
    private final Map<String, String> headers;
    private final Method method;
    private final String url;

    public static final class Builder {
        private ParseHttpBody body;
        private Map<String, String> headers;
        private Method method;
        private String url;

        public Builder() {
            this.headers = new HashMap();
        }

        public Builder(ParseHttpRequest request) {
            this.url = request.url;
            this.method = request.method;
            this.headers = new HashMap(request.headers);
            this.body = request.body;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setMethod(Method method) {
            this.method = method;
            return this;
        }

        public Builder setBody(ParseHttpBody body) {
            this.body = body;
            return this;
        }

        public Builder addHeader(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        public Builder addHeaders(Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        public Builder setHeaders(Map<String, String> headers) {
            this.headers = new HashMap(headers);
            return this;
        }

        public ParseHttpRequest build() {
            return new ParseHttpRequest();
        }
    }

    public enum Method {
        GET,
        POST,
        PUT,
        DELETE;

        public static Method fromString(String string) {
            Object obj = -1;
            switch (string.hashCode()) {
                case 70454:
                    if (string.equals("GET")) {
                        obj = null;
                        break;
                    }
                    break;
                case 79599:
                    if (string.equals("PUT")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 2461856:
                    if (string.equals("POST")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 2012838315:
                    if (string.equals("DELETE")) {
                        obj = 3;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return GET;
                case 1:
                    return POST;
                case 2:
                    return PUT;
                case 3:
                    return DELETE;
                default:
                    throw new IllegalArgumentException("Invalid http method: <" + string + ">");
            }
        }

        public String toString() {
            switch (this) {
                case GET:
                    return "GET";
                case POST:
                    return "POST";
                case PUT:
                    return "PUT";
                case DELETE:
                    return "DELETE";
                default:
                    throw new IllegalArgumentException("Invalid http method: <" + this + ">");
            }
        }
    }

    private ParseHttpRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = Collections.unmodifiableMap(new HashMap(builder.headers));
        this.body = builder.body;
    }

    public String getUrl() {
        return this.url;
    }

    public Method getMethod() {
        return this.method;
    }

    public Map<String, String> getAllHeaders() {
        return this.headers;
    }

    public String getHeader(String name) {
        return (String) this.headers.get(name);
    }

    public ParseHttpBody getBody() {
        return this.body;
    }
}
