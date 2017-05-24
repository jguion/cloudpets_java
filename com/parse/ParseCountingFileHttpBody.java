package com.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class ParseCountingFileHttpBody extends ParseFileHttpBody {
    private static final int DEFAULT_CHUNK_SIZE = 4096;
    private static final int EOF = -1;
    private final ProgressCallback progressCallback;

    public ParseCountingFileHttpBody(File file, ProgressCallback progressCallback) {
        this(file, null, progressCallback);
    }

    public ParseCountingFileHttpBody(File file, String contentType, ProgressCallback progressCallback) {
        super(file, contentType);
        this.progressCallback = progressCallback;
    }

    public void writeTo(OutputStream output) throws IOException {
        if (output == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream fileInput = new FileInputStream(this.file);
        try {
            byte[] buffer = new byte[4096];
            long totalLength = this.file.length();
            long position = 0;
            while (true) {
                int n = fileInput.read(buffer);
                if (-1 == n) {
                    break;
                }
                output.write(buffer, 0, n);
                position += (long) n;
                if (this.progressCallback != null) {
                    this.progressCallback.done(Integer.valueOf((int) ((100 * position) / totalLength)));
                }
            }
        } finally {
            ParseIOUtils.closeQuietly(fileInput);
        }
    }
}
