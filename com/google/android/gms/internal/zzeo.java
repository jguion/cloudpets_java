package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.internal.zzej.zza;

@zziy
public class zzeo extends zza {
    private final OnCustomClickListener zzbly;

    public zzeo(OnCustomClickListener onCustomClickListener) {
        this.zzbly = onCustomClickListener;
    }

    public void zza(zzef com_google_android_gms_internal_zzef, String str) {
        this.zzbly.onCustomClick(new zzeg(com_google_android_gms_internal_zzef), str);
    }
}
