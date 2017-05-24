package com.google.android.gms.ads.internal.request;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkt;
import com.google.android.gms.internal.zzlm;

@zziy
public final class zzc {

    public interface zza {
        void zzb(AdResponseParcel adResponseParcel);
    }

    interface zzb {
        boolean zza(VersionInfoParcel versionInfoParcel);
    }

    class AnonymousClass1 implements zzb {
        final /* synthetic */ Context zzamt;

        AnonymousClass1(Context context) {
            this.zzamt = context;
        }

        public boolean zza(VersionInfoParcel versionInfoParcel) {
            return versionInfoParcel.zzctu || (zzi.zzcm(this.zzamt) && !((Boolean) zzdi.zzbbv.get()).booleanValue());
        }
    }

    public static zzkt zza(Context context, VersionInfoParcel versionInfoParcel, zzlm<AdRequestInfoParcel> com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, zza com_google_android_gms_ads_internal_request_zzc_zza) {
        return zza(context, versionInfoParcel, com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza, new AnonymousClass1(context));
    }

    static zzkt zza(Context context, VersionInfoParcel versionInfoParcel, zzlm<AdRequestInfoParcel> com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, zza com_google_android_gms_ads_internal_request_zzc_zza, zzb com_google_android_gms_ads_internal_request_zzc_zzb) {
        return com_google_android_gms_ads_internal_request_zzc_zzb.zza(versionInfoParcel) ? zza(context, com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza) : zzb(context, versionInfoParcel, com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
    }

    private static zzkt zza(Context context, zzlm<AdRequestInfoParcel> com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, zza com_google_android_gms_ads_internal_request_zzc_zza) {
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Fetching ad response from local ad request service.");
        zzkt com_google_android_gms_ads_internal_request_zzd_zza = new com.google.android.gms.ads.internal.request.zzd.zza(context, com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
        Void voidR = (Void) com_google_android_gms_ads_internal_request_zzd_zza.zzqw();
        return com_google_android_gms_ads_internal_request_zzd_zza;
    }

    private static zzkt zzb(Context context, VersionInfoParcel versionInfoParcel, zzlm<AdRequestInfoParcel> com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, zza com_google_android_gms_ads_internal_request_zzc_zza) {
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Fetching ad response from remote ad request service.");
        if (zzm.zzjr().zzas(context)) {
            return new com.google.android.gms.ads.internal.request.zzd.zzb(context, versionInfoParcel, com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
        }
        com.google.android.gms.ads.internal.util.client.zzb.zzdf("Failed to connect to remote ad request service.");
        return null;
    }
}
