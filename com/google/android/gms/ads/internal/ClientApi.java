package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzs;
import com.google.android.gms.ads.internal.client.zzu;
import com.google.android.gms.ads.internal.client.zzx.zza;
import com.google.android.gms.ads.internal.client.zzz;
import com.google.android.gms.ads.internal.formats.zzl;
import com.google.android.gms.ads.internal.reward.client.zzb;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdz;
import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzhy;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzjq;

@Keep
@zziy
@DynamiteApi
public class ClientApi extends zza {
    public zzs createAdLoaderBuilder(zzd com_google_android_gms_dynamic_zzd, String str, zzgq com_google_android_gms_internal_zzgq, int i) {
        return new zzk((Context) zze.zzae(com_google_android_gms_dynamic_zzd), str, com_google_android_gms_internal_zzgq, new VersionInfoParcel(zzf.BA, i, true), zzd.zzeq());
    }

    public zzhp createAdOverlay(zzd com_google_android_gms_dynamic_zzd) {
        return new com.google.android.gms.ads.internal.overlay.zzd((Activity) zze.zzae(com_google_android_gms_dynamic_zzd));
    }

    public zzu createBannerAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, int i) throws RemoteException {
        return new zzf((Context) zze.zzae(com_google_android_gms_dynamic_zzd), adSizeParcel, str, com_google_android_gms_internal_zzgq, new VersionInfoParcel(zzf.BA, i, true), zzd.zzeq());
    }

    public zzhy createInAppPurchaseManager(zzd com_google_android_gms_dynamic_zzd) {
        return new com.google.android.gms.ads.internal.purchase.zze((Activity) zze.zzae(com_google_android_gms_dynamic_zzd));
    }

    public zzu createInterstitialAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, int i) throws RemoteException {
        Context context = (Context) zze.zzae(com_google_android_gms_dynamic_zzd);
        zzdi.initialize(context);
        VersionInfoParcel versionInfoParcel = new VersionInfoParcel(zzf.BA, i, true);
        boolean equals = "reward_mb".equals(adSizeParcel.zzaxi);
        Object obj = ((equals || !((Boolean) zzdi.zzbdi.get()).booleanValue()) && !(equals && ((Boolean) zzdi.zzbdj.get()).booleanValue())) ? null : 1;
        if (obj != null) {
            return new zzft(context, str, com_google_android_gms_internal_zzgq, versionInfoParcel, zzd.zzeq());
        }
        return new zzl(context, adSizeParcel, str, com_google_android_gms_internal_zzgq, versionInfoParcel, zzd.zzeq());
    }

    public zzdz createNativeAdViewDelegate(zzd com_google_android_gms_dynamic_zzd, zzd com_google_android_gms_dynamic_zzd2) {
        return new zzl((FrameLayout) zze.zzae(com_google_android_gms_dynamic_zzd), (FrameLayout) zze.zzae(com_google_android_gms_dynamic_zzd2));
    }

    public zzb createRewardedVideoAd(zzd com_google_android_gms_dynamic_zzd, zzgq com_google_android_gms_internal_zzgq, int i) {
        return new zzjq((Context) zze.zzae(com_google_android_gms_dynamic_zzd), zzd.zzeq(), com_google_android_gms_internal_zzgq, new VersionInfoParcel(zzf.BA, i, true));
    }

    public zzu createSearchAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, int i) throws RemoteException {
        return new zzt((Context) zze.zzae(com_google_android_gms_dynamic_zzd), adSizeParcel, str, new VersionInfoParcel(zzf.BA, i, true));
    }

    @Nullable
    public zzz getMobileAdsSettingsManager(zzd com_google_android_gms_dynamic_zzd) {
        return null;
    }

    public zzz getMobileAdsSettingsManagerWithClientJarVersion(zzd com_google_android_gms_dynamic_zzd, int i) {
        return zzo.zza((Context) zze.zzae(com_google_android_gms_dynamic_zzd), new VersionInfoParcel(zzf.BA, i, true));
    }
}
