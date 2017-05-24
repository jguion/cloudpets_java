package com.parse;

import com.parse.http.ParseHttpBody;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class ParseFileHttpBody extends ParseHttpBody {
    final File file;

    public ParseFileHttpBody(File file) {
        this(file, null);
    }

    public ParseFileHttpBody(File file, String contentType) {
        super(contentType, file.length());
        this.file = file;
    }

    public InputStream getContent() throws IOException {
        return new FileInputStream(this.file);
    }

    public void writeTo(OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Output stream can not be null");
        }
        InputStream fileInput = new FileInputStream(this.file);
        try {
            ParseIOUtils.copy(fileInput, out);
        } finally {
            ParseIOUtils.closeQuietly(fileInput);
        }
    }
}
