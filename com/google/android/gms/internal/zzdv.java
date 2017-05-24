package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.internal.zzdu.zza;

@zziy
public final class zzdv extends zza {
    private final OnCustomRenderedAdLoadedListener zzayz;

    public zzdv(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzayz = onCustomRenderedAdLoadedListener;
    }

    public void zza(zzdt com_google_android_gms_internal_zzdt) {
        this.zzayz.onCustomRenderedAdLoaded(new zzds(com_google_android_gms_internal_zzdt));
    }
}
