package com.parse;

import com.bumptech.glide.load.Key;
import com.parse.http.ParseHttpBody;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

class ParseByteArrayHttpBody extends ParseHttpBody {
    final byte[] content;
    final InputStream contentInputStream;

    public ParseByteArrayHttpBody(String content, String contentType) throws UnsupportedEncodingException {
        this(content.getBytes(Key.STRING_CHARSET_NAME), contentType);
    }

    public ParseByteArrayHttpBody(byte[] content, String contentType) {
        super(contentType, (long) content.length);
        this.content = content;
        this.contentInputStream = new ByteArrayInputStream(content);
    }

    public InputStream getContent() {
        return this.contentInputStream;
    }

    public void writeTo(OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        out.write(this.content);
    }
}
