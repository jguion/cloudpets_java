package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;

public class zzbg extends zzbv {
    private static final Object zzagd = new Object();
    private static volatile zzal zzaip = null;
    private boolean zzaiq = false;

    public zzbg(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2, boolean z) {
        super(com_google_android_gms_internal_zzbb, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
        this.zzaiq = z;
    }

    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        if (zzaip == null) {
            synchronized (zzagd) {
                if (zzaip == null) {
                    zzaip = new zzal((String) this.zzaiz.invoke(null, new Object[]{this.zzafz.getContext(), Boolean.valueOf(this.zzaiq)}));
                }
            }
        }
        synchronized (this.zzair) {
            if (zzaip != null) {
                this.zzair.zzdo = zzaip.zzdo;
                this.zzair.zzdu = Long.valueOf(zzaip.zzye);
                this.zzair.zzdt = zzaip.zzdt;
                if (this.zzaiq) {
                    this.zzair.zzee = zzaip.zzee;
                    this.zzair.zzef = zzaip.zzef;
                }
            }
        }
    }
}
