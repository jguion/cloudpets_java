package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbf extends zzbv {
    private static final Object zzagd = new Object();
    private static volatile String zzaio = null;

    public zzbf(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzbb, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        this.zzair.zzdp = "E";
        if (zzaio == null) {
            synchronized (zzagd) {
                if (zzaio == null) {
                    zzaio = (String) this.zzaiz.invoke(null, new Object[]{this.zzafz.getContext()});
                }
            }
        }
        synchronized (this.zzair) {
            this.zzair.zzdp = zzak.zza(zzaio.getBytes(), true);
        }
    }
}
