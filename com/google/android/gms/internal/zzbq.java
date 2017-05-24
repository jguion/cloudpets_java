package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbq extends zzbv {
    private final StackTraceElement[] zzaiv;

    public zzbq(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2, StackTraceElement[] stackTraceElementArr) {
        super(com_google_android_gms_internal_zzbb, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
        this.zzaiv = stackTraceElementArr;
    }

    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        if (this.zzaiv != null) {
            zzaz com_google_android_gms_internal_zzaz = new zzaz((String) this.zzaiz.invoke(null, new Object[]{this.zzaiv}));
            synchronized (this.zzair) {
                this.zzair.zzed = com_google_android_gms_internal_zzaz.zzahi;
                if (com_google_android_gms_internal_zzaz.zzahj.booleanValue()) {
                    this.zzair.zzen = Integer.valueOf(com_google_android_gms_internal_zzaz.zzahk.booleanValue() ? 0 : 1);
                }
            }
        }
    }
}
