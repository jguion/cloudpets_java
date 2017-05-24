package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.internal.zzae.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class zzar extends zzas {
    private static final String TAG = zzar.class.getSimpleName();
    private Info zzagq;

    protected zzar(Context context) {
        super(context, "");
    }

    public static zzar zzd(Context context) {
        zzas.zza(context, true);
        return new zzar(context);
    }

    protected zza zza(Context context, View view) {
        return null;
    }

    public String zza(String str, String str2) {
        return zzam.zza(str, str2, true);
    }

    public void zza(Info info) {
        this.zzagq = info;
    }

    protected void zza(zzbb com_google_android_gms_internal_zzbb, zza com_google_android_gms_internal_zzae_zza) {
        if (!com_google_android_gms_internal_zzbb.zzcm()) {
            zza(zzb(com_google_android_gms_internal_zzbb, com_google_android_gms_internal_zzae_zza));
        } else if (this.zzagq != null) {
            Object id = this.zzagq.getId();
            if (!TextUtils.isEmpty(id)) {
                com_google_android_gms_internal_zzae_zza.zzes = zzbd.zzq(id);
                com_google_android_gms_internal_zzae_zza.zzet = Integer.valueOf(5);
                com_google_android_gms_internal_zzae_zza.zzeu = Boolean.valueOf(this.zzagq.isLimitAdTrackingEnabled());
            }
            this.zzagq = null;
        }
    }

    protected List<Callable<Void>> zzb(zzbb com_google_android_gms_internal_zzbb, zza com_google_android_gms_internal_zzae_zza) {
        List<Callable<Void>> arrayList = new ArrayList();
        if (com_google_android_gms_internal_zzbb.zzch() == null) {
            return arrayList;
        }
        zzbb com_google_android_gms_internal_zzbb2 = com_google_android_gms_internal_zzbb;
        arrayList.add(new zzbk(com_google_android_gms_internal_zzbb2, zzax.zzbj(), zzax.zzbk(), com_google_android_gms_internal_zzae_zza, com_google_android_gms_internal_zzbb.zzau(), 24));
        return arrayList;
    }
}
