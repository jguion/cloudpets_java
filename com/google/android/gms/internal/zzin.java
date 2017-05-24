package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.internal.zzil.zza;

@zziy
public class zzin extends zzkm {
    private final zza zzccj;
    private final zzke.zza zzcck;
    private final AdResponseParcel zzccl = this.zzcck.zzcop;

    public zzin(zzke.zza com_google_android_gms_internal_zzke_zza, zza com_google_android_gms_internal_zzil_zza) {
        this.zzcck = com_google_android_gms_internal_zzke_zza;
        this.zzccj = com_google_android_gms_internal_zzil_zza;
    }

    private zzke zzan(int i) {
        return new zzke(this.zzcck.zzcix.zzcfu, null, null, i, null, null, this.zzccl.orientation, this.zzccl.zzbsj, this.zzcck.zzcix.zzcfx, false, null, null, null, null, null, this.zzccl.zzchd, this.zzcck.zzaqz, this.zzccl.zzchb, this.zzcck.zzcoj, this.zzccl.zzchg, this.zzccl.zzchh, this.zzcck.zzcod, null, null, null, null, this.zzcck.zzcop.zzchu, this.zzcck.zzcop.zzchv, null, null, null);
    }

    public void onStop() {
    }

    public void zzfc() {
        final zzke zzan = zzan(0);
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzin zzcdd;

            public void run() {
                this.zzcdd.zzccj.zzb(zzan);
            }
        });
    }
}
