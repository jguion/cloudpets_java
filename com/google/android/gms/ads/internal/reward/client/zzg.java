package com.google.android.gms.ads.internal.reward.client;

import com.google.android.gms.ads.internal.reward.client.zzd.zza;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zziy;

@zziy
public class zzg extends zza {
    private final RewardedVideoAdListener zzgf;

    public zzg(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzgf = rewardedVideoAdListener;
    }

    public void onRewardedVideoAdClosed() {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoAdClosed();
        }
    }

    public void onRewardedVideoAdFailedToLoad(int i) {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoAdFailedToLoad(i);
        }
    }

    public void onRewardedVideoAdLeftApplication() {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoAdLeftApplication();
        }
    }

    public void onRewardedVideoAdLoaded() {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoAdLoaded();
        }
    }

    public void onRewardedVideoAdOpened() {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoAdOpened();
        }
    }

    public void onRewardedVideoStarted() {
        if (this.zzgf != null) {
            this.zzgf.onRewardedVideoStarted();
        }
    }

    public void zza(zza com_google_android_gms_ads_internal_reward_client_zza) {
        if (this.zzgf != null) {
            this.zzgf.onRewarded(new zze(com_google_android_gms_ads_internal_reward_client_zza));
        }
    }
}
