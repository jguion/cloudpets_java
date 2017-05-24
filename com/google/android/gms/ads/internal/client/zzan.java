package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.client.zzb.zza;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.zzb;

public class zzan extends zza {
    private zzd zzazn;

    public void destroy() throws RemoteException {
    }

    public boolean isLoaded() throws RemoteException {
        return false;
    }

    public void pause() throws RemoteException {
    }

    public void resume() throws RemoteException {
    }

    public void setUserId(String str) throws RemoteException {
    }

    public void show() throws RemoteException {
    }

    public void zza(RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel) throws RemoteException {
        zzb.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        com.google.android.gms.ads.internal.util.client.zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzan zzazo;

            {
                this.zzazo = r1;
            }

            public void run() {
                if (this.zzazo.zzazn != null) {
                    try {
                        this.zzazo.zzazn.onRewardedVideoAdFailedToLoad(1);
                    } catch (Throwable e) {
                        zzb.zzd("Could not notify onRewardedVideoAdFailedToLoad event.", e);
                    }
                }
            }
        });
    }

    public void zza(zzd com_google_android_gms_ads_internal_reward_client_zzd) throws RemoteException {
        this.zzazn = com_google_android_gms_ads_internal_reward_client_zzd;
    }

    public void zzf(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
    }

    public void zzg(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
    }

    public void zzh(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
    }
}
