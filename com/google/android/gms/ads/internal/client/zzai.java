package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.ads.internal.client.zzz.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zziy;

@zziy
public class zzai extends zzg<zzaa> {
    public zzai() {
        super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzy(iBinder);
    }

    public zzz zzm(Context context) {
        try {
            return zza.zzu(((zzaa) zzcu(context)).zza(zze.zzac(context), zzf.BA));
        } catch (Throwable e) {
            zzb.zzd("Could not get remote MobileAdsSettingManager.", e);
            return null;
        } catch (Throwable e2) {
            zzb.zzd("Could not get remote MobileAdsSettingManager.", e2);
            return null;
        }
    }

    protected zzaa zzy(IBinder iBinder) {
        return zzaa.zza.zzv(iBinder);
    }
}
