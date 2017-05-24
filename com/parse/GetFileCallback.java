package com.parse;

import java.io.File;

public interface GetFileCallback extends ParseCallback2<File, ParseException> {
    void done(File file, ParseException parseException);
}
