package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.util.concurrent.Callable;

public class zzbl implements Callable {
    private final zzbb zzafz;
    private final zza zzair;

    public zzbl(zzbb com_google_android_gms_internal_zzbb, zza com_google_android_gms_internal_zzae_zza) {
        this.zzafz = com_google_android_gms_internal_zzbb;
        this.zzair = com_google_android_gms_internal_zzae_zza;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzdb();
    }

    public Void zzdb() throws Exception {
        if (this.zzafz.zzcq() != null) {
            this.zzafz.zzcq().get();
        }
        zzark zzcp = this.zzafz.zzcp();
        if (zzcp != null) {
            try {
                synchronized (this.zzair) {
                    zzark.zza(this.zzair, zzark.zzf(zzcp));
                }
            } catch (zzarj e) {
            }
        }
        return null;
    }
}
