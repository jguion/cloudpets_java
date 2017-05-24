package com.google.android.gms.internal;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public class zzarr extends CustomTabsServiceConnection {
    private WeakReference<zzars> brx;

    public zzarr(zzars com_google_android_gms_internal_zzars) {
        this.brx = new WeakReference(com_google_android_gms_internal_zzars);
    }

    public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzars com_google_android_gms_internal_zzars = (zzars) this.brx.get();
        if (com_google_android_gms_internal_zzars != null) {
            com_google_android_gms_internal_zzars.zza(customTabsClient);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzars com_google_android_gms_internal_zzars = (zzars) this.brx.get();
        if (com_google_android_gms_internal_zzars != null) {
            com_google_android_gms_internal_zzars.zzlg();
        }
    }
}
