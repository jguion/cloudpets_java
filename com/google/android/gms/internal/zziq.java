package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.internal.zzil.zza;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zziy
public class zziq extends zzkm {
    private final Object zzakd;
    private final zza zzccj;
    private final zzke.zza zzcck;
    private final AdResponseParcel zzccl;
    private final zzis zzcdl;
    private Future<zzke> zzcdm;

    public zziq(Context context, zzq com_google_android_gms_ads_internal_zzq, zzke.zza com_google_android_gms_internal_zzke_zza, zzau com_google_android_gms_internal_zzau, zza com_google_android_gms_internal_zzil_zza, zzdq com_google_android_gms_internal_zzdq) {
        this(com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzil_zza, new zzis(context, com_google_android_gms_ads_internal_zzq, new zzky(context), com_google_android_gms_internal_zzau, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzdq));
    }

    zziq(zzke.zza com_google_android_gms_internal_zzke_zza, zza com_google_android_gms_internal_zzil_zza, zzis com_google_android_gms_internal_zzis) {
        this.zzakd = new Object();
        this.zzcck = com_google_android_gms_internal_zzke_zza;
        this.zzccl = com_google_android_gms_internal_zzke_zza.zzcop;
        this.zzccj = com_google_android_gms_internal_zzil_zza;
        this.zzcdl = com_google_android_gms_internal_zzis;
    }

    private zzke zzan(int i) {
        return new zzke(this.zzcck.zzcix.zzcfu, null, null, i, null, null, this.zzccl.orientation, this.zzccl.zzbsj, this.zzcck.zzcix.zzcfx, false, null, null, null, null, null, this.zzccl.zzchd, this.zzcck.zzaqz, this.zzccl.zzchb, this.zzcck.zzcoj, this.zzccl.zzchg, this.zzccl.zzchh, this.zzcck.zzcod, null, null, null, null, this.zzcck.zzcop.zzchu, this.zzcck.zzcop.zzchv, null, null, this.zzccl.zzchy);
    }

    public void onStop() {
        synchronized (this.zzakd) {
            if (this.zzcdm != null) {
                this.zzcdm.cancel(true);
            }
        }
    }

    public void zzfc() {
        zzke com_google_android_gms_internal_zzke;
        int i;
        try {
            synchronized (this.zzakd) {
                this.zzcdm = zzkq.zza(this.zzcdl);
            }
            com_google_android_gms_internal_zzke = (zzke) this.zzcdm.get(Constants.WATCHDOG_WAKE_TIMER, TimeUnit.MILLISECONDS);
            i = -2;
        } catch (TimeoutException e) {
            zzb.zzdf("Timed out waiting for native ad.");
            this.zzcdm.cancel(true);
            i = 2;
            com_google_android_gms_internal_zzke = null;
        } catch (ExecutionException e2) {
            com_google_android_gms_internal_zzke = null;
            i = 0;
        } catch (InterruptedException e3) {
            com_google_android_gms_internal_zzke = null;
            i = 0;
        } catch (CancellationException e4) {
            com_google_android_gms_internal_zzke = null;
            i = 0;
        }
        if (com_google_android_gms_internal_zzke == null) {
            com_google_android_gms_internal_zzke = zzan(i);
        }
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zziq zzcdn;

            public void run() {
                this.zzcdn.zzccj.zzb(com_google_android_gms_internal_zzke);
            }
        });
    }
}
