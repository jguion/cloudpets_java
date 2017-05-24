package com.google.android.gms.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.internal.zzae.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class zzbk extends zzbv {
    public zzbk(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        super(com_google_android_gms_internal_zzbb, str, str2, com_google_android_gms_internal_zzae_zza, i, i2);
    }

    private void zzcz() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzair) {
            this.zzair.zzes = (String) this.zzaiz.invoke(null, new Object[]{this.zzafz.getApplicationContext()});
        }
    }

    private void zzda() {
        AdvertisingIdClient zzcv = this.zzafz.zzcv();
        if (zzcv == null) {
            zzr("E1");
            return;
        }
        try {
            Info info = zzcv.getInfo();
            String zzq = zzbd.zzq(info.getId());
            if (zzq != null) {
                synchronized (this.zzair) {
                    this.zzair.zzes = zzq;
                    this.zzair.zzeu = Boolean.valueOf(info.isLimitAdTrackingEnabled());
                    this.zzair.zzet = Integer.valueOf(5);
                }
                return;
            }
            zzr("E");
        } catch (IOException e) {
            zzr("E");
        }
    }

    private void zzr(String str) {
    }

    protected void zzcy() throws IllegalAccessException, InvocationTargetException {
        if (this.zzafz.zzcm()) {
            zzda();
        } else {
            zzcz();
        }
    }
}
