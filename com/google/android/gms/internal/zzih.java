package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzlu.zza;
import java.util.concurrent.atomic.AtomicBoolean;

@zziy
public abstract class zzih implements zzkt<Void>, zza {
    protected final Context mContext;
    protected final zzlt zzbkr;
    protected final zzil.zza zzccj;
    protected final zzke.zza zzcck;
    protected AdResponseParcel zzccl;
    private Runnable zzccm;
    protected final Object zzccn = new Object();
    private AtomicBoolean zzcco = new AtomicBoolean(true);

    protected zzih(Context context, zzke.zza com_google_android_gms_internal_zzke_zza, zzlt com_google_android_gms_internal_zzlt, zzil.zza com_google_android_gms_internal_zzil_zza) {
        this.mContext = context;
        this.zzcck = com_google_android_gms_internal_zzke_zza;
        this.zzccl = this.zzcck.zzcop;
        this.zzbkr = com_google_android_gms_internal_zzlt;
        this.zzccj = com_google_android_gms_internal_zzil_zza;
    }

    private zzke zzam(int i) {
        AdRequestInfoParcel adRequestInfoParcel = this.zzcck.zzcix;
        return new zzke(adRequestInfoParcel.zzcfu, this.zzbkr, this.zzccl.zzbsd, i, this.zzccl.zzbse, this.zzccl.zzche, this.zzccl.orientation, this.zzccl.zzbsj, adRequestInfoParcel.zzcfx, this.zzccl.zzchc, null, null, null, null, null, this.zzccl.zzchd, this.zzcck.zzaqz, this.zzccl.zzchb, this.zzcck.zzcoj, this.zzccl.zzchg, this.zzccl.zzchh, this.zzcck.zzcod, null, this.zzccl.zzchr, this.zzccl.zzchs, this.zzccl.zzcht, this.zzccl.zzchu, this.zzccl.zzchv, null, this.zzccl.zzbsg, this.zzccl.zzchy);
    }

    public void cancel() {
        if (this.zzcco.getAndSet(false)) {
            this.zzbkr.stopLoading();
            zzu.zzgb().zzl(this.zzbkr);
            zzal(-1);
            zzkr.zzcrf.removeCallbacks(this.zzccm);
        }
    }

    public void zza(zzlt com_google_android_gms_internal_zzlt, boolean z) {
        int i = 0;
        zzb.zzdd("WebView finished loading.");
        if (this.zzcco.getAndSet(false)) {
            if (z) {
                i = zzqv();
            }
            zzal(i);
            zzkr.zzcrf.removeCallbacks(this.zzccm);
        }
    }

    protected void zzal(int i) {
        if (i != -2) {
            this.zzccl = new AdResponseParcel(i, this.zzccl.zzbsj);
        }
        this.zzbkr.zzvm();
        this.zzccj.zzb(zzam(i));
    }

    public final Void zzqt() {
        zzac.zzhq("Webview render task needs to be called on UI thread.");
        this.zzccm = new Runnable(this) {
            final /* synthetic */ zzih zzccp;

            {
                this.zzccp = r1;
            }

            public void run() {
                if (this.zzccp.zzcco.get()) {
                    zzb.e("Timed out waiting for WebView to finish loading.");
                    this.zzccp.cancel();
                }
            }
        };
        zzkr.zzcrf.postDelayed(this.zzccm, ((Long) zzdi.zzbel.get()).longValue());
        zzqu();
        return null;
    }

    protected abstract void zzqu();

    protected int zzqv() {
        return -2;
    }

    public /* synthetic */ Object zzqw() {
        return zzqt();
    }
}
