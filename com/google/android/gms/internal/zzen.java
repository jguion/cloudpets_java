package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.internal.zzei.zza;

@zziy
public class zzen extends zza {
    private final OnContentAdLoadedListener zzblx;

    public zzen(OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zzblx = onContentAdLoadedListener;
    }

    public void zza(zzed com_google_android_gms_internal_zzed) {
        this.zzblx.onContentAdLoaded(zzb(com_google_android_gms_internal_zzed));
    }

    zzee zzb(zzed com_google_android_gms_internal_zzed) {
        return new zzee(com_google_android_gms_internal_zzed);
    }
}
