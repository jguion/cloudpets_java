package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbn extends zzbv {
    private long zzait = -1;

    public zzbn(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzbb, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        this.zzair.zzdd = Long.valueOf(-1);
        if (this.zzait == -1) {
            this.zzait = (long) ((Integer) this.zzaiz.invoke(null, new Object[]{this.zzafz.getContext()})).intValue();
        }
        synchronized (this.zzair) {
            this.zzair.zzdd = Long.valueOf(this.zzait);
        }
    }
}
