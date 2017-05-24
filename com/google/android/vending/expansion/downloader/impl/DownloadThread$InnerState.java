package com.google.android.vending.expansion.downloader.impl;

class DownloadThread$InnerState {
    public int mBytesNotified;
    public int mBytesSoFar;
    public int mBytesThisSession;
    public boolean mContinuingDownload;
    public String mHeaderContentDisposition;
    public String mHeaderContentLength;
    public String mHeaderContentLocation;
    public String mHeaderETag;
    public long mTimeLastNotification;

    private DownloadThread$InnerState() {
        this.mBytesSoFar = 0;
        this.mBytesThisSession = 0;
        this.mContinuingDownload = false;
        this.mBytesNotified = 0;
        this.mTimeLastNotification = 0;
    }
}
