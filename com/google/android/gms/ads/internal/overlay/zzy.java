package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkr;

@zziy
class zzy implements Runnable {
    private boolean mCancelled = false;
    private zzk zzcay;

    zzy(zzk com_google_android_gms_ads_internal_overlay_zzk) {
        this.zzcay = com_google_android_gms_ads_internal_overlay_zzk;
    }

    public void cancel() {
        this.mCancelled = true;
        zzkr.zzcrf.removeCallbacks(this);
    }

    public void run() {
        if (!this.mCancelled) {
            this.zzcay.zzpo();
            zzqh();
        }
    }

    public void zzqh() {
        zzkr.zzcrf.postDelayed(this, 250);
    }
}
