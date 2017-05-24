package com.parse;

import java.io.InputStream;

public interface GetDataStreamCallback extends ParseCallback2<InputStream, ParseException> {
    void done(InputStream inputStream, ParseException parseException);
}
