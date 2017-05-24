package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;

@zziy
public abstract class zzjj {
    public abstract void zza(Context context, zzjd com_google_android_gms_internal_zzjd, VersionInfoParcel versionInfoParcel);

    protected void zze(zzjd com_google_android_gms_internal_zzjd) {
        com_google_android_gms_internal_zzjd.zzsf();
        if (com_google_android_gms_internal_zzjd.zzsd() != null) {
            com_google_android_gms_internal_zzjd.zzsd().release();
        }
    }
}
