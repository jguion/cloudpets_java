package com.google.android.vending.expansion.downloader;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Messenger;
import com.google.android.vending.expansion.downloader.impl.DownloaderService;

public class DownloaderClientMarshaller {
    public static final int DOWNLOAD_REQUIRED = 2;
    public static final int LVL_CHECK_REQUIRED = 1;
    public static final int MSG_ONDOWNLOADPROGRESS = 11;
    public static final int MSG_ONDOWNLOADSTATE_CHANGED = 10;
    public static final int MSG_ONSERVICECONNECTED = 12;
    public static final int NO_DOWNLOAD_REQUIRED = 0;
    public static final String PARAM_MESSENGER = "EMH";
    public static final String PARAM_NEW_STATE = "newState";
    public static final String PARAM_PROGRESS = "progress";

    public static IDownloaderClient CreateProxy(Messenger msg) {
        return new Proxy(msg);
    }

    public static IStub CreateStub(IDownloaderClient itf, Class<?> downloaderService) {
        return new Stub(itf, downloaderService);
    }

    public static int startDownloadServiceIfRequired(Context context, PendingIntent notificationClient, Class<?> serviceClass) throws NameNotFoundException {
        return DownloaderService.startDownloadServiceIfRequired(context, notificationClient, (Class) serviceClass);
    }

    public static int startDownloadServiceIfRequired(Context context, Intent notificationClient, Class<?> serviceClass) throws NameNotFoundException {
        return DownloaderService.startDownloadServiceIfRequired(context, notificationClient, (Class) serviceClass);
    }
}
