package com.spiraltoys.cloudpets2.expansion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.vending.expansion.downloader.DownloaderClientMarshaller;

public class ExpansionAlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            DownloaderClientMarshaller.startDownloadServiceIfRequired(context, intent, ExpansionDownloaderService.class);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
