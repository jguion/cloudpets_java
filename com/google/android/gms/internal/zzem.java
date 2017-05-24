package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.internal.zzeh.zza;

@zziy
public class zzem extends zza {
    private final OnAppInstallAdLoadedListener zzblw;

    public zzem(OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
        this.zzblw = onAppInstallAdLoadedListener;
    }

    public void zza(zzeb com_google_android_gms_internal_zzeb) {
        this.zzblw.onAppInstallAdLoaded(zzb(com_google_android_gms_internal_zzeb));
    }

    zzec zzb(zzeb com_google_android_gms_internal_zzeb) {
        return new zzec(com_google_android_gms_internal_zzeb);
    }
}
