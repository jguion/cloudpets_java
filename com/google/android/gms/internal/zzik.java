package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;

@zziy
public abstract class zzik extends zzkm {
    protected final Context mContext;
    protected final Object zzakd = new Object();
    protected final com.google.android.gms.internal.zzil.zza zzccj;
    protected final com.google.android.gms.internal.zzke.zza zzcck;
    protected AdResponseParcel zzccl;
    protected final Object zzccn = new Object();

    protected static final class zza extends Exception {
        private final int zzcdb;

        public zza(String str, int i) {
            super(str);
            this.zzcdb = i;
        }

        public int getErrorCode() {
            return this.zzcdb;
        }
    }

    protected zzik(Context context, com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, com.google.android.gms.internal.zzil.zza com_google_android_gms_internal_zzil_zza) {
        super(true);
        this.mContext = context;
        this.zzcck = com_google_android_gms_internal_zzke_zza;
        this.zzccl = com_google_android_gms_internal_zzke_zza.zzcop;
        this.zzccj = com_google_android_gms_internal_zzil_zza;
    }

    public void onStop() {
    }

    protected abstract zzke zzam(int i);

    public void zzfc() {
        int errorCode;
        synchronized (this.zzakd) {
            zzb.zzdd("AdRendererBackgroundTask started.");
            int i = this.zzcck.errorCode;
            try {
                zzh(SystemClock.elapsedRealtime());
            } catch (zza e) {
                errorCode = e.getErrorCode();
                if (errorCode == 3 || errorCode == -1) {
                    zzb.zzde(e.getMessage());
                } else {
                    zzb.zzdf(e.getMessage());
                }
                if (this.zzccl == null) {
                    this.zzccl = new AdResponseParcel(errorCode);
                } else {
                    this.zzccl = new AdResponseParcel(errorCode, this.zzccl.zzbsj);
                }
                zzkr.zzcrf.post(new Runnable(this) {
                    final /* synthetic */ zzik zzcda;

                    {
                        this.zzcda = r1;
                    }

                    public void run() {
                        this.zzcda.onStop();
                    }
                });
                i = errorCode;
            }
            final zzke zzam = zzam(i);
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzik zzcda;

                public void run() {
                    synchronized (this.zzcda.zzakd) {
                        this.zzcda.zzn(zzam);
                    }
                }
            });
        }
    }

    protected abstract void zzh(long j) throws zza;

    protected void zzn(zzke com_google_android_gms_internal_zzke) {
        this.zzccj.zzb(com_google_android_gms_internal_zzke);
    }
}
