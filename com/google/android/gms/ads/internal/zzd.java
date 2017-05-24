package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.zzj;
import com.google.android.gms.ads.internal.overlay.zzm;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.safebrowsing.zza;
import com.google.android.gms.internal.zzes;
import com.google.android.gms.internal.zzfl;
import com.google.android.gms.internal.zziy;

@zziy
public class zzd {
    public final zzfl zzamc;
    public final zzj zzamd;
    public final zzm zzame;
    public final com.google.android.gms.ads.internal.safebrowsing.zzd zzamf;

    public zzd(zzfl com_google_android_gms_internal_zzfl, zzj com_google_android_gms_ads_internal_overlay_zzj, zzm com_google_android_gms_ads_internal_overlay_zzm, com.google.android.gms.ads.internal.safebrowsing.zzd com_google_android_gms_ads_internal_safebrowsing_zzd) {
        this.zzamc = com_google_android_gms_internal_zzfl;
        this.zzamd = com_google_android_gms_ads_internal_overlay_zzj;
        this.zzame = com_google_android_gms_ads_internal_overlay_zzm;
        this.zzamf = com_google_android_gms_ads_internal_safebrowsing_zzd;
    }

    public static zzd zzeq() {
        return new zzd(new zzes(), new zzn(), new zzt(), new zza());
    }
}
