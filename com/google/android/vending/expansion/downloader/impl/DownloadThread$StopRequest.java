package com.google.android.vending.expansion.downloader.impl;

class DownloadThread$StopRequest extends Throwable {
    private static final long serialVersionUID = 6338592678988347973L;
    public int mFinalStatus;
    final /* synthetic */ DownloadThread this$0;

    public DownloadThread$StopRequest(DownloadThread downloadThread, int finalStatus, String message) {
        this.this$0 = downloadThread;
        super(message);
        this.mFinalStatus = finalStatus;
    }

    public DownloadThread$StopRequest(DownloadThread downloadThread, int finalStatus, String message, Throwable throwable) {
        this.this$0 = downloadThread;
        super(message, throwable);
        this.mFinalStatus = finalStatus;
    }
}
