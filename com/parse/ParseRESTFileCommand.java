package com.parse;

import com.parse.http.ParseHttpBody;
import com.parse.http.ParseHttpRequest.Method;
import java.io.File;

class ParseRESTFileCommand extends ParseRESTCommand {
    private final String contentType;
    private final byte[] data;
    private final File file;

    public static class Builder extends Init<Builder> {
        private String contentType = null;
        private byte[] data = null;
        private File file;

        public Builder() {
            method(Method.POST);
        }

        public Builder fileName(String fileName) {
            return (Builder) httpPath(String.format("files/%s", new Object[]{fileName}));
        }

        public Builder data(byte[] data) {
            this.data = data;
            return this;
        }

        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder file(File file) {
            this.file = file;
            return this;
        }

        Builder self() {
            return this;
        }

        public ParseRESTFileCommand build() {
            return new ParseRESTFileCommand(this);
        }
    }

    public ParseRESTFileCommand(Builder builder) {
        super(builder);
        if (builder.file == null || builder.data == null) {
            this.data = builder.data;
            this.contentType = builder.contentType;
            this.file = builder.file;
            return;
        }
        throw new IllegalArgumentException("File and data can not be set at the same time");
    }

    protected ParseHttpBody newBody(ProgressCallback progressCallback) {
        return progressCallback == null ? this.data != null ? new ParseByteArrayHttpBody(this.data, this.contentType) : new ParseFileHttpBody(this.file, this.contentType) : this.data != null ? new ParseCountingByteArrayHttpBody(this.data, this.contentType, progressCallback) : new ParseCountingFileHttpBody(this.file, this.contentType, progressCallback);
    }
}
