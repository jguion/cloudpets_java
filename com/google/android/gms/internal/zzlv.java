package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.ads.internal.zzu;

@zziy
public class zzlv {
    public zzlt zza(Context context, AdSizeParcel adSizeParcel, boolean z, boolean z2, @Nullable zzau com_google_android_gms_internal_zzau, VersionInfoParcel versionInfoParcel) {
        return zza(context, adSizeParcel, z, z2, com_google_android_gms_internal_zzau, versionInfoParcel, null, null, null);
    }

    public zzlt zza(Context context, AdSizeParcel adSizeParcel, boolean z, boolean z2, @Nullable zzau com_google_android_gms_internal_zzau, VersionInfoParcel versionInfoParcel, zzdq com_google_android_gms_internal_zzdq, zzs com_google_android_gms_ads_internal_zzs, zzd com_google_android_gms_ads_internal_zzd) {
        zzlt com_google_android_gms_internal_zzlw = new zzlw(zzlx.zzb(context, adSizeParcel, z, z2, com_google_android_gms_internal_zzau, versionInfoParcel, com_google_android_gms_internal_zzdq, com_google_android_gms_ads_internal_zzs, com_google_android_gms_ads_internal_zzd));
        com_google_android_gms_internal_zzlw.setWebViewClient(zzu.zzgb().zzb(com_google_android_gms_internal_zzlw, z2));
        com_google_android_gms_internal_zzlw.setWebChromeClient(zzu.zzgb().zzn(com_google_android_gms_internal_zzlw));
        return com_google_android_gms_internal_zzlw;
    }
}
