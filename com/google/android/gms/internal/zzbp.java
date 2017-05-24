package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class zzbp extends zzbv {
    private List<Long> zzaiu = null;

    public zzbp(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzbb, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        this.zzair.zzdq = Long.valueOf(-1);
        this.zzair.zzdr = Long.valueOf(-1);
        if (this.zzaiu == null) {
            this.zzaiu = (List) this.zzaiz.invoke(null, new Object[]{this.zzafz.getContext()});
        }
        if (this.zzaiu != null && this.zzaiu.size() == 2) {
            synchronized (this.zzair) {
                this.zzair.zzdq = Long.valueOf(((Long) this.zzaiu.get(0)).longValue());
                this.zzair.zzdr = Long.valueOf(((Long) this.zzaiu.get(1)).longValue());
            }
        }
    }
}
