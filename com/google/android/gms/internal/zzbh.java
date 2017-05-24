package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbh extends zzbv {
    public zzbh(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzbb, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        this.zzair.zzcw = Long.valueOf(-1);
        this.zzair.zzcx = Long.valueOf(-1);
        int[] iArr = (int[]) this.zzaiz.invoke(null, new Object[]{this.zzafz.getContext()});
        synchronized (this.zzair) {
            this.zzair.zzcw = Long.valueOf((long) iArr[0]);
            this.zzair.zzcx = Long.valueOf((long) iArr[1]);
        }
    }
}
