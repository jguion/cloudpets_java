package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzu.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zziy;

@zziy
public class zze extends zzg<zzv> {
    public zze() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public zzu zza(Context context, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, int i) {
        Throwable e;
        try {
            return zza.zzq(((zzv) zzcu(context)).zza(com.google.android.gms.dynamic.zze.zzac(context), adSizeParcel, str, com_google_android_gms_internal_zzgq, zzf.BA, i));
        } catch (RemoteException e2) {
            e = e2;
            zzb.zza("Could not create remote AdManager.", e);
            return null;
        } catch (zzg.zza e3) {
            e = e3;
            zzb.zza("Could not create remote AdManager.", e);
            return null;
        }
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzk(iBinder);
    }

    protected zzv zzk(IBinder iBinder) {
        return zzv.zza.zzr(iBinder);
    }
}
