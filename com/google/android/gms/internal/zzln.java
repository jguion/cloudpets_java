package com.google.android.gms.internal;

import com.google.android.gms.internal.zzlm.zzc;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@zziy
public class zzln<T> implements zzlm<T> {
    private final Object zzakd = new Object();
    protected int zzbqm = 0;
    protected final BlockingQueue<zza> zzcuk = new LinkedBlockingQueue();
    protected T zzcul;

    class zza {
        public final zzc<T> zzcum;
        public final com.google.android.gms.internal.zzlm.zza zzcun;
        final /* synthetic */ zzln zzcuo;

        public zza(zzln com_google_android_gms_internal_zzln, zzc<T> com_google_android_gms_internal_zzlm_zzc_T, com.google.android.gms.internal.zzlm.zza com_google_android_gms_internal_zzlm_zza) {
            this.zzcuo = com_google_android_gms_internal_zzln;
            this.zzcum = com_google_android_gms_internal_zzlm_zzc_T;
            this.zzcun = com_google_android_gms_internal_zzlm_zza;
        }
    }

    public int getStatus() {
        return this.zzbqm;
    }

    public void reject() {
        synchronized (this.zzakd) {
            if (this.zzbqm != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzbqm = -1;
            for (zza com_google_android_gms_internal_zzln_zza : this.zzcuk) {
                com_google_android_gms_internal_zzln_zza.zzcun.run();
            }
            this.zzcuk.clear();
        }
    }

    public void zza(zzc<T> com_google_android_gms_internal_zzlm_zzc_T, com.google.android.gms.internal.zzlm.zza com_google_android_gms_internal_zzlm_zza) {
        synchronized (this.zzakd) {
            if (this.zzbqm == 1) {
                com_google_android_gms_internal_zzlm_zzc_T.zzd(this.zzcul);
            } else if (this.zzbqm == -1) {
                com_google_android_gms_internal_zzlm_zza.run();
            } else if (this.zzbqm == 0) {
                this.zzcuk.add(new zza(this, com_google_android_gms_internal_zzlm_zzc_T, com_google_android_gms_internal_zzlm_zza));
            }
        }
    }

    public void zzg(T t) {
        synchronized (this.zzakd) {
            if (this.zzbqm != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzcul = t;
            this.zzbqm = 1;
            for (zza com_google_android_gms_internal_zzln_zza : this.zzcuk) {
                com_google_android_gms_internal_zzln_zza.zzcum.zzd(t);
            }
            this.zzcuk.clear();
        }
    }
}
