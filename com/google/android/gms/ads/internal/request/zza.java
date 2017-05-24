package com.google.android.gms.ads.internal.request;

import android.content.Context;
import com.google.android.gms.internal.zzau;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkm;
import java.util.concurrent.Future;

@zziy
public class zza {

    public interface zza {
        void zza(com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza);
    }

    public zzkm zza(Context context, com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, zzau com_google_android_gms_internal_zzau, zza com_google_android_gms_ads_internal_request_zza_zza) {
        zzkm com_google_android_gms_ads_internal_request_zzn = com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcfu.extras.getBundle("sdk_less_server_data") != null ? new zzn(context, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, com_google_android_gms_ads_internal_request_zza_zza) : new zzb(context, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, com_google_android_gms_internal_zzau, com_google_android_gms_ads_internal_request_zza_zza);
        Future future = (Future) com_google_android_gms_ads_internal_request_zzn.zzqw();
        return com_google_android_gms_ads_internal_request_zzn;
    }
}
