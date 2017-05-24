package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzke.zza;

@zziy
public class zzij extends zzih {
    private zzii zzccz;

    zzij(Context context, zza com_google_android_gms_internal_zzke_zza, zzlt com_google_android_gms_internal_zzlt, zzil.zza com_google_android_gms_internal_zzil_zza) {
        super(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzlt, com_google_android_gms_internal_zzil_zza);
    }

    protected void zzqu() {
        int i;
        int i2;
        AdSizeParcel zzdt = this.zzbkr.zzdt();
        if (zzdt.zzaxj) {
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
        } else {
            i = zzdt.widthPixels;
            i2 = zzdt.heightPixels;
        }
        this.zzccz = new zzii(this, this.zzbkr, i, i2);
        this.zzbkr.zzvr().zza((zzlu.zza) this);
        this.zzccz.zza(this.zzccl);
    }

    protected int zzqv() {
        if (!this.zzccz.zzqz()) {
            return !this.zzccz.zzra() ? 2 : -2;
        } else {
            zzb.zzdd("Ad-Network indicated no fill with passback URL.");
            return 3;
        }
    }
}
