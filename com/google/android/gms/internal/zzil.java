package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.zzs;

@zziy
public class zzil {

    public interface zza {
        void zzb(zzke com_google_android_gms_internal_zzke);
    }

    public zzkt zza(Context context, com.google.android.gms.ads.internal.zza com_google_android_gms_ads_internal_zza, com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, zzau com_google_android_gms_internal_zzau, @Nullable zzlt com_google_android_gms_internal_zzlt, zzgq com_google_android_gms_internal_zzgq, zza com_google_android_gms_internal_zzil_zza, zzdq com_google_android_gms_internal_zzdq) {
        AdResponseParcel adResponseParcel = com_google_android_gms_internal_zzke_zza.zzcop;
        zzkt com_google_android_gms_internal_zzip = adResponseParcel.zzchc ? new zzip(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzgq, com_google_android_gms_internal_zzil_zza, com_google_android_gms_internal_zzdq, com_google_android_gms_internal_zzlt) : (adResponseParcel.zzaxl || (com_google_android_gms_ads_internal_zza instanceof zzq)) ? (adResponseParcel.zzaxl && (com_google_android_gms_ads_internal_zza instanceof zzq)) ? new zziq(context, (zzq) com_google_android_gms_ads_internal_zza, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzau, com_google_android_gms_internal_zzil_zza, com_google_android_gms_internal_zzdq) : new zzin(com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzil_zza) : adResponseParcel.zzchi ? new zzij(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzlt, com_google_android_gms_internal_zzil_zza) : (((Boolean) zzdi.zzbco.get()).booleanValue() && zzs.zzaxr() && !zzs.zzaxt() && com_google_android_gms_internal_zzlt != null && com_google_android_gms_internal_zzlt.zzdt().zzaxj) ? new zzio(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzlt, com_google_android_gms_internal_zzil_zza) : new zzim(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzlt, com_google_android_gms_internal_zzil_zza);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(com_google_android_gms_internal_zzip.getClass().getName());
        zzb.zzdd(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        com_google_android_gms_internal_zzip.zzqw();
        return com_google_android_gms_internal_zzip;
    }

    public zzkt zza(Context context, com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, zzjr com_google_android_gms_internal_zzjr) {
        zzkt com_google_android_gms_internal_zzjy = new zzjy(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzjr);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(com_google_android_gms_internal_zzjy.getClass().getName());
        zzb.zzdd(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        com_google_android_gms_internal_zzjy.zzqw();
        return com_google_android_gms_internal_zzjy;
    }
}
