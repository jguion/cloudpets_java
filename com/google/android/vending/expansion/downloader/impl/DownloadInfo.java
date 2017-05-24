package com.google.android.vending.expansion.downloader.impl;

import android.util.Log;
import com.google.android.vending.expansion.downloader.Constants;
import com.google.android.vending.expansion.downloader.Helpers;

public class DownloadInfo {
    public int mControl;
    public long mCurrentBytes;
    public String mETag;
    public final String mFileName;
    public int mFuzz = Helpers.sRandom.nextInt(1001);
    public final int mIndex;
    boolean mInitialized;
    public long mLastMod;
    public int mNumFailed;
    public int mRedirectCount;
    public int mRetryAfter;
    public int mStatus;
    public long mTotalBytes;
    public String mUri;

    public DownloadInfo(int index, String fileName, String pkg) {
        this.mFileName = fileName;
        this.mIndex = index;
    }

    public void resetDownload() {
        this.mCurrentBytes = 0;
        this.mETag = "";
        this.mLastMod = 0;
        this.mStatus = 0;
        this.mControl = 0;
        this.mNumFailed = 0;
        this.mRetryAfter = 0;
        this.mRedirectCount = 0;
    }

    public long restartTime(long now) {
        if (this.mNumFailed == 0) {
            return now;
        }
        if (this.mRetryAfter > 0) {
            return this.mLastMod + ((long) this.mRetryAfter);
        }
        return this.mLastMod + ((long) (((this.mFuzz + Constants.MAX_DOWNLOADS) * 30) * (1 << (this.mNumFailed - 1))));
    }

    public void logVerboseInfo() {
        Log.v(Constants.TAG, "Service adding new entry");
        Log.v(Constants.TAG, "FILENAME: " + this.mFileName);
        Log.v(Constants.TAG, "URI     : " + this.mUri);
        Log.v(Constants.TAG, "FILENAME: " + this.mFileName);
        Log.v(Constants.TAG, "CONTROL : " + this.mControl);
        Log.v(Constants.TAG, "STATUS  : " + this.mStatus);
        Log.v(Constants.TAG, "FAILED_C: " + this.mNumFailed);
        Log.v(Constants.TAG, "RETRY_AF: " + this.mRetryAfter);
        Log.v(Constants.TAG, "REDIRECT: " + this.mRedirectCount);
        Log.v(Constants.TAG, "LAST_MOD: " + this.mLastMod);
        Log.v(Constants.TAG, "TOTAL   : " + this.mTotalBytes);
        Log.v(Constants.TAG, "CURRENT : " + this.mCurrentBytes);
        Log.v(Constants.TAG, "ETAG    : " + this.mETag);
    }
}
