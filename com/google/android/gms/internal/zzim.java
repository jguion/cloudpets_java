package com.google.android.gms.internal;

import android.content.Context;
import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzlu.zza;

@zziy
public class zzim extends zzih implements zza {
    zzim(Context context, zzke.zza com_google_android_gms_internal_zzke_zza, zzlt com_google_android_gms_internal_zzlt, zzil.zza com_google_android_gms_internal_zzil_zza) {
        super(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzlt, com_google_android_gms_internal_zzil_zza);
    }

    protected void zzqu() {
        if (this.zzccl.errorCode == -2) {
            this.zzbkr.zzvr().zza((zza) this);
            zzrb();
            zzb.zzdd("Loading HTML in WebView.");
            this.zzbkr.loadDataWithBaseURL(zzu.zzfz().zzcv(this.zzccl.zzbyj), this.zzccl.body, "text/html", Key.STRING_CHARSET_NAME, null);
        }
    }

    protected void zzrb() {
    }
}
