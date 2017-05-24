package com.google.android.vending.expansion.downloader;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class DownloadProgressInfo$1 implements Creator<DownloadProgressInfo> {
    DownloadProgressInfo$1() {
    }

    public DownloadProgressInfo createFromParcel(Parcel parcel) {
        return new DownloadProgressInfo(parcel);
    }

    public DownloadProgressInfo[] newArray(int i) {
        return new DownloadProgressInfo[i];
    }
}
