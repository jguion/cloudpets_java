package com.google.android.gms.ads.internal.cache;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.ads.internal.cache.zzf.zza;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.internal.zze.zzb;
import com.google.android.gms.internal.zziy;
import com.parse.ParseException;

@zziy
public class zzc extends zze<zzf> {
    zzc(Context context, Looper looper, zzb com_google_android_gms_common_internal_zze_zzb, com.google.android.gms.common.internal.zze.zzc com_google_android_gms_common_internal_zze_zzc) {
        super(context, looper, ParseException.INVALID_ACL, com_google_android_gms_common_internal_zze_zzb, com_google_android_gms_common_internal_zze_zzc, null);
    }

    protected zzf zzg(IBinder iBinder) {
        return zza.zzi(iBinder);
    }

    protected /* synthetic */ IInterface zzh(IBinder iBinder) {
        return zzg(iBinder);
    }

    protected String zzix() {
        return "com.google.android.gms.ads.service.CACHE";
    }

    protected String zziy() {
        return "com.google.android.gms.ads.internal.cache.ICacheService";
    }

    public zzf zziz() throws DeadObjectException {
        return (zzf) super.zzatx();
    }
}
