package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.client.zzb.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.dynamic.zze;

@zziy
public class zzjq extends zza {
    private final Context mContext;
    private final Object zzakd;
    private final VersionInfoParcel zzanh;
    private final zzjr zzcmt;

    zzjq(Context context, VersionInfoParcel versionInfoParcel, zzjr com_google_android_gms_internal_zzjr) {
        this.zzakd = new Object();
        this.mContext = context;
        this.zzanh = versionInfoParcel;
        this.zzcmt = com_google_android_gms_internal_zzjr;
    }

    public zzjq(Context context, zzd com_google_android_gms_ads_internal_zzd, zzgq com_google_android_gms_internal_zzgq, VersionInfoParcel versionInfoParcel) {
        this(context, versionInfoParcel, new zzjr(context, com_google_android_gms_ads_internal_zzd, AdSizeParcel.zzjc(), com_google_android_gms_internal_zzgq, versionInfoParcel));
    }

    public void destroy() {
        zzh(null);
    }

    public boolean isLoaded() {
        boolean isLoaded;
        synchronized (this.zzakd) {
            isLoaded = this.zzcmt.isLoaded();
        }
        return isLoaded;
    }

    public void pause() {
        zzf(null);
    }

    public void resume() {
        zzg(null);
    }

    public void setUserId(String str) {
        zzb.zzdf("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }

    public void show() {
        synchronized (this.zzakd) {
            this.zzcmt.zzsn();
        }
    }

    public void zza(RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel) {
        synchronized (this.zzakd) {
            this.zzcmt.zza(rewardedVideoAdRequestParcel);
        }
    }

    public void zza(com.google.android.gms.ads.internal.reward.client.zzd com_google_android_gms_ads_internal_reward_client_zzd) {
        synchronized (this.zzakd) {
            this.zzcmt.zza(com_google_android_gms_ads_internal_reward_client_zzd);
        }
    }

    public void zzf(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzakd) {
            this.zzcmt.pause();
        }
    }

    public void zzg(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzakd) {
            Context context = com_google_android_gms_dynamic_zzd == null ? null : (Context) zze.zzae(com_google_android_gms_dynamic_zzd);
            if (context != null) {
                try {
                    this.zzcmt.onContextChanged(context);
                } catch (Throwable e) {
                    zzb.zzd("Unable to extract updated context.", e);
                }
            }
            this.zzcmt.resume();
        }
    }

    public void zzh(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzakd) {
            this.zzcmt.destroy();
        }
    }
}
