package com.parse;

import bolts.Task;
import com.parse.http.ParseHttpRequest.Method;
import com.parse.http.ParseHttpResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.Callable;

class ParseAWSRequest extends ParseRequest<Void> {
    private final File tempFile;

    public ParseAWSRequest(Method method, String url, File tempFile) {
        super(method, url);
        this.tempFile = tempFile;
    }

    protected Task<Void> onResponseAsync(final ParseHttpResponse response, final ProgressCallback downloadProgressCallback) {
        int statusCode = response.getStatusCode();
        if ((statusCode < 200 || statusCode >= 300) && statusCode != 304) {
            String action = this.method == Method.GET ? "Download from" : "Upload to";
            return Task.forError(new ParseException(100, String.format("%s S3 failed. %s", new Object[]{action, response.getReasonPhrase()})));
        } else if (this.method != Method.GET) {
            return null;
        } else {
            return Task.call(new Callable<Void>() {
                public Void call() throws Exception {
                    long totalSize = response.getTotalSize();
                    long downloadedSize = 0;
                    InputStream responseStream = null;
                    try {
                        responseStream = response.getContent();
                        FileOutputStream tempFileStream = ParseFileUtils.openOutputStream(ParseAWSRequest.this.tempFile);
                        byte[] data = new byte[32768];
                        while (true) {
                            int nRead = responseStream.read(data, 0, data.length);
                            if (nRead == -1) {
                                break;
                            }
                            tempFileStream.write(data, 0, nRead);
                            downloadedSize += (long) nRead;
                            if (!(downloadProgressCallback == null || totalSize == -1)) {
                                downloadProgressCallback.done(Integer.valueOf(Math.round((((float) downloadedSize) / ((float) totalSize)) * 100.0f)));
                            }
                        }
                        return null;
                    } finally {
                        ParseIOUtils.closeQuietly(responseStream);
                    }
                }
            }, ParseExecutors.io());
        }
    }
}
