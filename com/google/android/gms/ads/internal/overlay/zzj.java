package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzlt;

@zziy
public abstract class zzj {
    @Nullable
    public abstract zzi zza(Context context, zzlt com_google_android_gms_internal_zzlt, int i, boolean z, zzdq com_google_android_gms_internal_zzdq);

    protected boolean zzh(zzlt com_google_android_gms_internal_zzlt) {
        return com_google_android_gms_internal_zzlt.zzdt().zzaxj;
    }

    protected boolean zzq(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return zzs.zzaxn() && (applicationInfo == null || applicationInfo.targetSdkVersion >= 11);
    }
}
