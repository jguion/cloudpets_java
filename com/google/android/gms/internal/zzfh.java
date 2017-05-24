package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;

@zziy
public class zzfh extends zzkm {
    final zzlt zzbkr;
    final zzfj zzbns;
    private final String zzbnt;

    zzfh(zzlt com_google_android_gms_internal_zzlt, zzfj com_google_android_gms_internal_zzfj, String str) {
        this.zzbkr = com_google_android_gms_internal_zzlt;
        this.zzbns = com_google_android_gms_internal_zzfj;
        this.zzbnt = str;
        zzu.zzgw().zza(this);
    }

    public void onStop() {
        this.zzbns.abort();
    }

    public void zzfc() {
        try {
            this.zzbns.zzbc(this.zzbnt);
        } finally {
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzfh zzbnu;

                {
                    this.zzbnu = r1;
                }

                public void run() {
                    zzu.zzgw().zzb(this.zzbnu);
                }
            });
        }
    }
}
