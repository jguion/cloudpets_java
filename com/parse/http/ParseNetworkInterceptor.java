package com.parse.http;

import java.io.IOException;

public interface ParseNetworkInterceptor {

    public interface Chain {
        ParseHttpRequest getRequest();

        ParseHttpResponse proceed(ParseHttpRequest parseHttpRequest) throws IOException;
    }

    ParseHttpResponse intercept(Chain chain) throws IOException;
}
