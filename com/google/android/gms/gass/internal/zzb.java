package com.google.android.gms.gass.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.internal.zze.zzc;
import com.google.android.gms.gass.internal.zze.zza;
import com.parse.ParseException;

public class zzb extends zze<zze> {
    public zzb(Context context, Looper looper, com.google.android.gms.common.internal.zze.zzb com_google_android_gms_common_internal_zze_zzb, zzc com_google_android_gms_common_internal_zze_zzc) {
        super(context, looper, ParseException.OBJECT_TOO_LARGE, com_google_android_gms_common_internal_zze_zzb, com_google_android_gms_common_internal_zze_zzc, null);
    }

    public zze zzbnu() throws DeadObjectException {
        return (zze) super.zzatx();
    }

    protected zze zzgq(IBinder iBinder) {
        return zza.zzgr(iBinder);
    }

    protected /* synthetic */ IInterface zzh(IBinder iBinder) {
        return zzgq(iBinder);
    }

    protected String zzix() {
        return "com.google.android.gms.gass.START";
    }

    protected String zziy() {
        return "com.google.android.gms.gass.internal.IGassService";
    }
}
