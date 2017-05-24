package com.google.android.gms.internal;

import com.google.android.gms.clearcut.zzb;
import com.google.android.gms.internal.zzad.zza;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class zzao {
    protected static volatile zzb zzaga = null;
    private static volatile Random zzagc = null;
    private static final Object zzagd = new Object();
    private zzbb zzafz;
    protected boolean zzagb = false;

    public zzao(zzbb com_google_android_gms_internal_zzbb) {
        this.zzafz = com_google_android_gms_internal_zzbb;
        zzdi.initialize(com_google_android_gms_internal_zzbb.getContext());
        this.zzagb = ((Boolean) zzdi.zzbem.get()).booleanValue();
        if (this.zzagb && zzaga == null) {
            synchronized (zzagd) {
                if (zzaga == null) {
                    zzaga = new zzb(com_google_android_gms_internal_zzbb.getContext(), "ADSHIELD", null);
                }
            }
        }
    }

    private static Random zzav() {
        if (zzagc == null) {
            synchronized (zzagd) {
                if (zzagc == null) {
                    zzagc = new Random();
                }
            }
        }
        return zzagc;
    }

    public void zza(int i, int i2, long j) throws IOException {
        try {
            if (this.zzagb && zzaga != null && this.zzafz.zzcn()) {
                zzark com_google_android_gms_internal_zzad_zza = new zza();
                com_google_android_gms_internal_zzad_zza.zzck = this.zzafz.getContext().getPackageName();
                com_google_android_gms_internal_zzad_zza.zzcl = Long.valueOf(j);
                zzb.zza zzl = zzaga.zzl(zzark.zzf(com_google_android_gms_internal_zzad_zza));
                zzl.zzfi(i2);
                zzl.zzfh(i);
                zzl.zze(this.zzafz.zzcl());
            }
        } catch (Exception e) {
        }
    }

    public int zzau() {
        try {
            return ThreadLocalRandom.current().nextInt();
        } catch (NoClassDefFoundError e) {
            return zzav().nextInt();
        } catch (RuntimeException e2) {
            return zzav().nextInt();
        }
    }
}
