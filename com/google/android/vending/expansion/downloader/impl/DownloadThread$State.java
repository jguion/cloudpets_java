package com.google.android.vending.expansion.downloader.impl;

import java.io.FileOutputStream;

class DownloadThread$State {
    public boolean mCountRetry = false;
    public String mFilename;
    public boolean mGotData = false;
    public String mNewUri;
    public int mRedirectCount = 0;
    public String mRequestUri;
    public int mRetryAfter = 0;
    public FileOutputStream mStream;

    public DownloadThread$State(DownloadInfo info, DownloaderService service) {
        this.mRedirectCount = info.mRedirectCount;
        this.mRequestUri = info.mUri;
        this.mFilename = service.generateTempSaveFileName(info.mFileName);
    }
}
