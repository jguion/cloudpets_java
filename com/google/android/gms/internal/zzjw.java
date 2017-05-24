package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.zza.zza;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

@zziy
public class zzjw extends zza {
    private volatile zzjx zzcnb;
    private volatile zzju zzcnn;
    private volatile zzjv zzcno;

    public zzjw(zzjv com_google_android_gms_internal_zzjv) {
        this.zzcno = com_google_android_gms_internal_zzjv;
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, RewardItemParcel rewardItemParcel) {
        if (this.zzcno != null) {
            this.zzcno.zzc(rewardItemParcel);
        }
    }

    public void zza(zzju com_google_android_gms_internal_zzju) {
        this.zzcnn = com_google_android_gms_internal_zzju;
    }

    public void zza(zzjx com_google_android_gms_internal_zzjx) {
        this.zzcnb = com_google_android_gms_internal_zzjx;
    }

    public void zzb(zzd com_google_android_gms_dynamic_zzd, int i) {
        if (this.zzcnn != null) {
            this.zzcnn.zzay(i);
        }
    }

    public void zzc(zzd com_google_android_gms_dynamic_zzd, int i) {
        if (this.zzcnb != null) {
            this.zzcnb.zza(zze.zzae(com_google_android_gms_dynamic_zzd).getClass().getName(), i);
        }
    }

    public void zzq(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzcnn != null) {
            this.zzcnn.zzsr();
        }
    }

    public void zzr(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzcnb != null) {
            this.zzcnb.zzcm(zze.zzae(com_google_android_gms_dynamic_zzd).getClass().getName());
        }
    }

    public void zzs(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzcno != null) {
            this.zzcno.onRewardedVideoAdOpened();
        }
    }

    public void zzt(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzcno != null) {
            this.zzcno.onRewardedVideoStarted();
        }
    }

    public void zzu(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzcno != null) {
            this.zzcno.onRewardedVideoAdClosed();
        }
    }

    public void zzv(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzcno != null) {
            this.zzcno.zzso();
        }
    }

    public void zzw(zzd com_google_android_gms_dynamic_zzd) {
        if (this.zzcno != null) {
            this.zzcno.onRewardedVideoAdLeftApplication();
        }
    }
}
