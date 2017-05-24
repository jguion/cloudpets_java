package com.google.android.gms.ads.internal.reward.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.zzb.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zziy;

@zziy
public class zzf extends zzg<zzc> {
    public zzf() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }

    public zzb zzb(Context context, zzgq com_google_android_gms_internal_zzgq) {
        Throwable e;
        try {
            return zza.zzbh(((zzc) zzcu(context)).zza(zze.zzac(context), com_google_android_gms_internal_zzgq, com.google.android.gms.common.internal.zzf.BA));
        } catch (RemoteException e2) {
            e = e2;
            zzb.zzd("Could not get remote RewardedVideoAd.", e);
            return null;
        } catch (zzg.zza e3) {
            e = e3;
            zzb.zzd("Could not get remote RewardedVideoAd.", e);
            return null;
        }
    }

    protected zzc zzbk(IBinder iBinder) {
        return zzc.zza.zzbi(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzbk(iBinder);
    }
}
