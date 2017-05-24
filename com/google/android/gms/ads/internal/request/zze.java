package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.ads.internal.request.zzk.zza;
import com.google.android.gms.common.internal.zze.zzb;
import com.google.android.gms.common.internal.zze.zzc;
import com.google.android.gms.internal.zziy;

@zziy
public class zze extends com.google.android.gms.common.internal.zze<zzk> {
    final int zzcfs;

    public zze(Context context, Looper looper, zzb com_google_android_gms_common_internal_zze_zzb, zzc com_google_android_gms_common_internal_zze_zzc, int i) {
        super(context, looper, 8, com_google_android_gms_common_internal_zze_zzb, com_google_android_gms_common_internal_zze_zzc, null);
        this.zzcfs = i;
    }

    protected zzk zzbd(IBinder iBinder) {
        return zza.zzbe(iBinder);
    }

    protected /* synthetic */ IInterface zzh(IBinder iBinder) {
        return zzbd(iBinder);
    }

    protected String zzix() {
        return "com.google.android.gms.ads.service.START";
    }

    protected String zziy() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }

    public zzk zzry() throws DeadObjectException {
        return (zzk) super.zzatx();
    }
}
