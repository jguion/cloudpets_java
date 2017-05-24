package com.google.android.gms.ads.internal;

import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkr;
import com.google.android.vending.expansion.downloader.Constants;
import java.lang.ref.WeakReference;

@zziy
public class zzr {
    private final zza zzaov;
    @Nullable
    private AdRequestParcel zzaow;
    private boolean zzaox;
    private boolean zzaoy;
    private long zzaoz;
    private final Runnable zzw;

    public static class zza {
        private final Handler mHandler;

        public zza(Handler handler) {
            this.mHandler = handler;
        }

        public boolean postDelayed(Runnable runnable, long j) {
            return this.mHandler.postDelayed(runnable, j);
        }

        public void removeCallbacks(Runnable runnable) {
            this.mHandler.removeCallbacks(runnable);
        }
    }

    public zzr(zza com_google_android_gms_ads_internal_zza) {
        this(com_google_android_gms_ads_internal_zza, new zza(zzkr.zzcrf));
    }

    zzr(zza com_google_android_gms_ads_internal_zza, zza com_google_android_gms_ads_internal_zzr_zza) {
        this.zzaox = false;
        this.zzaoy = false;
        this.zzaoz = 0;
        this.zzaov = com_google_android_gms_ads_internal_zzr_zza;
        final WeakReference weakReference = new WeakReference(com_google_android_gms_ads_internal_zza);
        this.zzw = new Runnable(this) {
            final /* synthetic */ zzr zzapb;

            public void run() {
                this.zzapb.zzaox = false;
                zza com_google_android_gms_ads_internal_zza = (zza) weakReference.get();
                if (com_google_android_gms_ads_internal_zza != null) {
                    com_google_android_gms_ads_internal_zza.zzd(this.zzapb.zzaow);
                }
            }
        };
    }

    public void cancel() {
        this.zzaox = false;
        this.zzaov.removeCallbacks(this.zzw);
    }

    public void pause() {
        this.zzaoy = true;
        if (this.zzaox) {
            this.zzaov.removeCallbacks(this.zzw);
        }
    }

    public void resume() {
        this.zzaoy = false;
        if (this.zzaox) {
            this.zzaox = false;
            zza(this.zzaow, this.zzaoz);
        }
    }

    public void zza(AdRequestParcel adRequestParcel, long j) {
        if (this.zzaox) {
            zzb.zzdf("An ad refresh is already scheduled.");
            return;
        }
        this.zzaow = adRequestParcel;
        this.zzaox = true;
        this.zzaoz = j;
        if (!this.zzaoy) {
            zzb.zzde("Scheduling ad refresh " + j + " milliseconds from now.");
            this.zzaov.postDelayed(this.zzw, j);
        }
    }

    public boolean zzfl() {
        return this.zzaox;
    }

    public void zzg(AdRequestParcel adRequestParcel) {
        this.zzaow = adRequestParcel;
    }

    public void zzh(AdRequestParcel adRequestParcel) {
        zza(adRequestParcel, (long) Constants.WATCHDOG_WAKE_TIMER);
    }
}
