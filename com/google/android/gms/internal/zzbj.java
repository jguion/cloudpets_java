package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbj extends zzbv {
    private long startTime;

    public zzbj(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, long j, int i, int i2) {
        super(com_google_android_gms_internal_zzbb, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
        this.startTime = j;
    }

    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zzaiz.invoke(null, new Object[0])).longValue();
        synchronized (this.zzair) {
            this.zzair.zzew = Long.valueOf(longValue);
            if (this.startTime != 0) {
                this.zzair.zzdi = Long.valueOf(longValue - this.startTime);
                this.zzair.zzdn = Long.valueOf(this.startTime);
            }
        }
    }
}
