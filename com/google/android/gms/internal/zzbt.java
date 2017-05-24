package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbt extends zzbv {
    public zzbt(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzbb, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        this.zzair.zzeg = Integer.valueOf(2);
        boolean booleanValue = ((Boolean) this.zzaiz.invoke(null, new Object[]{this.zzafz.getApplicationContext()})).booleanValue();
        synchronized (this.zzair) {
            if (booleanValue) {
                this.zzair.zzeg = Integer.valueOf(1);
            } else {
                this.zzair.zzeg = Integer.valueOf(0);
            }
        }
    }
}
