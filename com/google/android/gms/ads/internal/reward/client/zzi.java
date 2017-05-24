package com.google.android.gms.ads.internal.reward.client;

import android.content.Context;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.internal.client.zzh;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zziy;

@zziy
public class zzi implements RewardedVideoAd {
    private final Context mContext;
    private final Object zzakd = new Object();
    private final zzb zzcmz;
    private RewardedVideoAdListener zzgf;

    public zzi(Context context, zzb com_google_android_gms_ads_internal_reward_client_zzb) {
        this.zzcmz = com_google_android_gms_ads_internal_reward_client_zzb;
        this.mContext = context;
    }

    public void destroy() {
        destroy(null);
    }

    public void destroy(Context context) {
        synchronized (this.zzakd) {
            if (this.zzcmz == null) {
                return;
            }
            try {
                this.zzcmz.zzh(zze.zzac(context));
            } catch (Throwable e) {
                zzb.zzd("Could not forward destroy to RewardedVideoAd", e);
            }
        }
    }

    public RewardedVideoAdListener getRewardedVideoAdListener() {
        RewardedVideoAdListener rewardedVideoAdListener;
        synchronized (this.zzakd) {
            rewardedVideoAdListener = this.zzgf;
        }
        return rewardedVideoAdListener;
    }

    public String getUserId() {
        zzb.zzdf("RewardedVideoAd.getUserId() is deprecated. Please do not call this method.");
        return null;
    }

    public boolean isLoaded() {
        boolean z = false;
        synchronized (this.zzakd) {
            if (this.zzcmz == null) {
            } else {
                try {
                    z = this.zzcmz.isLoaded();
                } catch (Throwable e) {
                    zzb.zzd("Could not forward isLoaded to RewardedVideoAd", e);
                }
            }
        }
        return z;
    }

    public void loadAd(String str, AdRequest adRequest) {
        synchronized (this.zzakd) {
            if (this.zzcmz == null) {
                return;
            }
            try {
                this.zzcmz.zza(zzh.zzjb().zza(this.mContext, adRequest.zzdg(), str));
            } catch (Throwable e) {
                zzb.zzd("Could not forward loadAd to RewardedVideoAd", e);
            }
        }
    }

    public void pause() {
        pause(null);
    }

    public void pause(Context context) {
        synchronized (this.zzakd) {
            if (this.zzcmz == null) {
                return;
            }
            try {
                this.zzcmz.zzf(zze.zzac(context));
            } catch (Throwable e) {
                zzb.zzd("Could not forward pause to RewardedVideoAd", e);
            }
        }
    }

    public void resume() {
        resume(null);
    }

    public void resume(Context context) {
        synchronized (this.zzakd) {
            if (this.zzcmz == null) {
                return;
            }
            try {
                this.zzcmz.zzg(zze.zzac(context));
            } catch (Throwable e) {
                zzb.zzd("Could not forward resume to RewardedVideoAd", e);
            }
        }
    }

    public void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        synchronized (this.zzakd) {
            this.zzgf = rewardedVideoAdListener;
            if (this.zzcmz != null) {
                try {
                    this.zzcmz.zza(new zzg(rewardedVideoAdListener));
                } catch (Throwable e) {
                    zzb.zzd("Could not forward setRewardedVideoAdListener to RewardedVideoAd", e);
                }
            }
        }
    }

    public void setUserId(String str) {
        zzb.zzdf("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }

    public void show() {
        synchronized (this.zzakd) {
            if (this.zzcmz == null) {
                return;
            }
            try {
                this.zzcmz.show();
            } catch (Throwable e) {
                zzb.zzd("Could not forward show to RewardedVideoAd", e);
            }
        }
    }
}
