package com.google.android.vending.expansion.downloader.impl;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;

public interface DownloadNotification$ICustomNotification {
    void setCurrentBytes(long j);

    void setIcon(int i);

    void setPendingIntent(PendingIntent pendingIntent);

    void setTicker(CharSequence charSequence);

    void setTimeRemaining(long j);

    void setTitle(CharSequence charSequence);

    void setTotalBytes(long j);

    Notification updateNotification(Context context);
}
