package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;

@zziy
public class zzdl {
    @Nullable
    public zzdk zza(@Nullable zzdj com_google_android_gms_internal_zzdj) {
        if (com_google_android_gms_internal_zzdj == null) {
            throw new IllegalArgumentException("CSI configuration can't be null. ");
        } else if (!com_google_android_gms_internal_zzdj.zzkt()) {
            zzkn.v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
            return null;
        } else if (com_google_android_gms_internal_zzdj.getContext() == null) {
            throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
        } else if (!TextUtils.isEmpty(com_google_android_gms_internal_zzdj.zzhy())) {
            return new zzdk(com_google_android_gms_internal_zzdj.getContext(), com_google_android_gms_internal_zzdj.zzhy(), com_google_android_gms_internal_zzdj.zzku(), com_google_android_gms_internal_zzdj.zzkv());
        } else {
            throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
        }
    }
}
