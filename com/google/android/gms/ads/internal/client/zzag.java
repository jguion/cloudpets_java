package com.google.android.gms.ads.internal.client;

import android.content.Context;
import com.google.android.gms.ads.internal.reward.client.zzi;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zziy;

@zziy
public class zzag {
    private static final Object zzaok = new Object();
    private static zzag zzazh;
    private zzz zzazi;
    private RewardedVideoAd zzazj;

    private zzag() {
    }

    public static zzag zzki() {
        zzag com_google_android_gms_ads_internal_client_zzag;
        synchronized (zzaok) {
            if (zzazh == null) {
                zzazh = new zzag();
            }
            com_google_android_gms_ads_internal_client_zzag = zzazh;
        }
        return com_google_android_gms_ads_internal_client_zzag;
    }

    public RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        RewardedVideoAd rewardedVideoAd;
        synchronized (zzaok) {
            if (this.zzazj != null) {
                rewardedVideoAd = this.zzazj;
            } else {
                this.zzazj = new zzi(context, zzm.zzjs().zza(context, new zzgp()));
                rewardedVideoAd = this.zzazj;
            }
        }
        return rewardedVideoAd;
    }

    public void openDebugMenu(Context context, String str) {
        zzac.zza(this.zzazi != null, (Object) "MobileAds.initialize() must be called prior to opening debug menu.");
        try {
            this.zzazi.zzb(zze.zzac(context), str);
        } catch (Throwable e) {
            zzb.zzb("Unable to open debug menu.", e);
        }
    }

    public void setAppMuted(boolean z) {
        zzac.zza(this.zzazi != null, (Object) "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzazi.setAppMuted(z);
        } catch (Throwable e) {
            zzb.zzb("Unable to set app mute state.", e);
        }
    }

    public void setAppVolume(float f) {
        boolean z = true;
        boolean z2 = 0.0f <= f && f <= 1.0f;
        zzac.zzb(z2, (Object) "The app volume must be a value between 0 and 1 inclusive.");
        if (this.zzazi == null) {
            z = false;
        }
        zzac.zza(z, (Object) "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzazi.setAppVolume(f);
        } catch (Throwable e) {
            zzb.zzb("Unable to set app volume.", e);
        }
    }

    public void zza(Context context, String str, zzah com_google_android_gms_ads_internal_client_zzah) {
        synchronized (zzaok) {
            if (this.zzazi != null) {
            } else if (context == null) {
                throw new IllegalArgumentException("Context cannot be null.");
            } else {
                try {
                    this.zzazi = zzm.zzjs().zzl(context);
                    this.zzazi.initialize();
                    if (str != null) {
                        this.zzazi.zzw(str);
                    }
                } catch (Throwable e) {
                    zzb.zzd("Fail to initialize or set applicationCode on mobile ads setting manager", e);
                }
            }
        }
    }
}
