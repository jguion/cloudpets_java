package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbm extends zzbv {
    private static final Object zzagd = new Object();
    private static volatile Long zzais = null;

    public zzbm(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzbb, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        if (zzais == null) {
            synchronized (zzagd) {
                if (zzais == null) {
                    zzais = (Long) this.zzaiz.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.zzair) {
            this.zzair.zzdm = zzais;
        }
    }
}
