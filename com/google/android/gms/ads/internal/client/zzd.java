package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.ads.internal.client.zzs.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zziy;

@zziy
public final class zzd extends zzg<zzt> {
    public zzd() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    public zzs zza(Context context, String str, zzgq com_google_android_gms_internal_zzgq) {
        try {
            return zza.zzo(((zzt) zzcu(context)).zza(zze.zzac(context), str, com_google_android_gms_internal_zzgq, zzf.BA));
        } catch (Throwable e) {
            zzb.zzd("Could not create remote builder for AdLoader.", e);
            return null;
        } catch (Throwable e2) {
            zzb.zzd("Could not create remote builder for AdLoader.", e2);
            return null;
        }
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzj(iBinder);
    }

    protected zzt zzj(IBinder iBinder) {
        return zzt.zza.zzp(iBinder);
    }
}
