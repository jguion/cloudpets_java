package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzlt;

@zziy
public class zzn extends zzj {
    @Nullable
    public zzi zza(Context context, zzlt com_google_android_gms_internal_zzlt, int i, boolean z, zzdq com_google_android_gms_internal_zzdq) {
        if (!zzq(context)) {
            return null;
        }
        return new zzc(context, z, zzh(com_google_android_gms_internal_zzlt), new zzx(context, com_google_android_gms_internal_zzlt.zzvu(), com_google_android_gms_internal_zzlt.getRequestId(), com_google_android_gms_internal_zzdq, com_google_android_gms_internal_zzlt.zzvz()));
    }
}
