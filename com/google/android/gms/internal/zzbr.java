package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbr extends zzbv {
    public zzbr(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzbb, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzair) {
            zzba com_google_android_gms_internal_zzba = new zzba((String) this.zzaiz.invoke(null, new Object[0]));
            this.zzair.zzei = com_google_android_gms_internal_zzba.zzahl;
            this.zzair.zzej = com_google_android_gms_internal_zzba.zzahm;
        }
    }
}
