package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.internal.zzek.zza;

@zziy
public class zzep extends zza {
    private final OnCustomTemplateAdLoadedListener zzblz;

    public zzep(OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener) {
        this.zzblz = onCustomTemplateAdLoadedListener;
    }

    public void zza(zzef com_google_android_gms_internal_zzef) {
        this.zzblz.onCustomTemplateAdLoaded(new zzeg(com_google_android_gms_internal_zzef));
    }
}
