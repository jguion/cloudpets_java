package com.google.android.gms.ads.internal.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.reward.client.zzf;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdz;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zzho;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzhy;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zziy;
import com.google.firebase.analytics.FirebaseAnalytics.Event;

@zziy
public class zzl {
    private final Object zzakd = new Object();
    private zzx zzaxq;
    private final zze zzaxr;
    private final zzd zzaxs;
    private final zzai zzaxt;
    private final zzel zzaxu;
    private final zzf zzaxv;
    private final zzid zzaxw;
    private final zzho zzaxx;

    @VisibleForTesting
    abstract class zza<T> {
        final /* synthetic */ zzl zzaya;

        zza(zzl com_google_android_gms_ads_internal_client_zzl) {
            this.zzaya = com_google_android_gms_ads_internal_client_zzl;
        }

        @Nullable
        protected abstract T zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException;

        @Nullable
        protected abstract T zzjh() throws RemoteException;

        @Nullable
        protected final T zzjo() {
            T t = null;
            zzx zza = this.zzaya.zzjf();
            if (zza == null) {
                zzb.zzdf("ClientApi class cannot be loaded.");
            } else {
                try {
                    t = zzb(zza);
                } catch (Throwable e) {
                    zzb.zzd("Cannot invoke local loader using ClientApi class", e);
                }
            }
            return t;
        }

        @Nullable
        protected final T zzjp() {
            try {
                return zzjh();
            } catch (Throwable e) {
                zzb.zzd("Cannot invoke remote loader", e);
                return null;
            }
        }
    }

    public zzl(zze com_google_android_gms_ads_internal_client_zze, zzd com_google_android_gms_ads_internal_client_zzd, zzai com_google_android_gms_ads_internal_client_zzai, zzel com_google_android_gms_internal_zzel, zzf com_google_android_gms_ads_internal_reward_client_zzf, zzid com_google_android_gms_internal_zzid, zzho com_google_android_gms_internal_zzho) {
        this.zzaxr = com_google_android_gms_ads_internal_client_zze;
        this.zzaxs = com_google_android_gms_ads_internal_client_zzd;
        this.zzaxt = com_google_android_gms_ads_internal_client_zzai;
        this.zzaxu = com_google_android_gms_internal_zzel;
        this.zzaxv = com_google_android_gms_ads_internal_reward_client_zzf;
        this.zzaxw = com_google_android_gms_internal_zzid;
        this.zzaxx = com_google_android_gms_internal_zzho;
    }

    private static boolean zza(Activity activity, String str) {
        Intent intent = activity.getIntent();
        if (intent.hasExtra(str)) {
            return intent.getBooleanExtra(str, false);
        }
        zzb.e("useClientJar flag not found in activity intent extras.");
        return false;
    }

    private void zzc(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString("flow", str);
        zzm.zzjr().zza(context, null, "gmob-apps", bundle, true);
    }

    @Nullable
    private static zzx zzje() {
        try {
            Object newInstance = zzl.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return com.google.android.gms.ads.internal.client.zzx.zza.asInterface((IBinder) newInstance);
            }
            zzb.zzdf("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Throwable e) {
            zzb.zzd("Failed to instantiate ClientApi class.", e);
            return null;
        }
    }

    @Nullable
    private zzx zzjf() {
        zzx com_google_android_gms_ads_internal_client_zzx;
        synchronized (this.zzakd) {
            if (this.zzaxq == null) {
                this.zzaxq = zzje();
            }
            com_google_android_gms_ads_internal_client_zzx = this.zzaxq;
        }
        return com_google_android_gms_ads_internal_client_zzx;
    }

    public zzu zza(final Context context, final AdSizeParcel adSizeParcel, final String str) {
        return (zzu) zza(context, false, new zza<zzu>(this) {
            final /* synthetic */ zzl zzaya;

            public zzu zza(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return com_google_android_gms_ads_internal_client_zzx.createSearchAdManager(zze.zzac(context), adSizeParcel, str, com.google.android.gms.common.internal.zzf.BA);
            }

            public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return zza(com_google_android_gms_ads_internal_client_zzx);
            }

            public zzu zzjg() {
                zzu zza = this.zzaya.zzaxr.zza(context, adSizeParcel, str, null, 3);
                if (zza != null) {
                    return zza;
                }
                this.zzaya.zzc(context, Event.SEARCH);
                return new zzak();
            }

            public /* synthetic */ Object zzjh() throws RemoteException {
                return zzjg();
            }
        });
    }

    public zzu zza(Context context, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq) {
        final Context context2 = context;
        final AdSizeParcel adSizeParcel2 = adSizeParcel;
        final String str2 = str;
        final zzgq com_google_android_gms_internal_zzgq2 = com_google_android_gms_internal_zzgq;
        return (zzu) zza(context, false, new zza<zzu>(this) {
            final /* synthetic */ zzl zzaya;

            public zzu zza(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return com_google_android_gms_ads_internal_client_zzx.createBannerAdManager(zze.zzac(context2), adSizeParcel2, str2, com_google_android_gms_internal_zzgq2, com.google.android.gms.common.internal.zzf.BA);
            }

            public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return zza(com_google_android_gms_ads_internal_client_zzx);
            }

            public zzu zzjg() {
                zzu zza = this.zzaya.zzaxr.zza(context2, adSizeParcel2, str2, com_google_android_gms_internal_zzgq2, 1);
                if (zza != null) {
                    return zza;
                }
                this.zzaya.zzc(context2, "banner");
                return new zzak();
            }

            public /* synthetic */ Object zzjh() throws RemoteException {
                return zzjg();
            }
        });
    }

    public com.google.android.gms.ads.internal.reward.client.zzb zza(final Context context, final zzgq com_google_android_gms_internal_zzgq) {
        return (com.google.android.gms.ads.internal.reward.client.zzb) zza(context, false, new zza<com.google.android.gms.ads.internal.reward.client.zzb>(this) {
            final /* synthetic */ zzl zzaya;

            public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return zzf(com_google_android_gms_ads_internal_client_zzx);
            }

            public com.google.android.gms.ads.internal.reward.client.zzb zzf(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return com_google_android_gms_ads_internal_client_zzx.createRewardedVideoAd(zze.zzac(context), com_google_android_gms_internal_zzgq, com.google.android.gms.common.internal.zzf.BA);
            }

            public /* synthetic */ Object zzjh() throws RemoteException {
                return zzjl();
            }

            public com.google.android.gms.ads.internal.reward.client.zzb zzjl() {
                com.google.android.gms.ads.internal.reward.client.zzb zzb = this.zzaya.zzaxv.zzb(context, com_google_android_gms_internal_zzgq);
                if (zzb != null) {
                    return zzb;
                }
                this.zzaya.zzc(context, "rewarded_video");
                return new zzan();
            }
        });
    }

    public zzdz zza(final Context context, final FrameLayout frameLayout, final FrameLayout frameLayout2) {
        return (zzdz) zza(context, false, new zza<zzdz>(this) {
            final /* synthetic */ zzl zzaya;

            public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return zze(com_google_android_gms_ads_internal_client_zzx);
            }

            public zzdz zze(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return com_google_android_gms_ads_internal_client_zzx.createNativeAdViewDelegate(zze.zzac(frameLayout), zze.zzac(frameLayout2));
            }

            public /* synthetic */ Object zzjh() throws RemoteException {
                return zzjk();
            }

            public zzdz zzjk() {
                zzdz zzb = this.zzaya.zzaxu.zzb(context, frameLayout, frameLayout2);
                if (zzb != null) {
                    return zzb;
                }
                this.zzaya.zzc(context, "native_ad_view_delegate");
                return new zzam();
            }
        });
    }

    @VisibleForTesting
    <T> T zza(Context context, boolean z, zza<T> com_google_android_gms_ads_internal_client_zzl_zza_T) {
        if (!(z || zzm.zzjr().zzas(context))) {
            zzb.zzdd("Google Play Services is not available");
            z = true;
        }
        T zzjo;
        if (z) {
            zzjo = com_google_android_gms_ads_internal_client_zzl_zza_T.zzjo();
            return zzjo == null ? com_google_android_gms_ads_internal_client_zzl_zza_T.zzjp() : zzjo;
        } else {
            zzjo = com_google_android_gms_ads_internal_client_zzl_zza_T.zzjp();
            return zzjo == null ? com_google_android_gms_ads_internal_client_zzl_zza_T.zzjo() : zzjo;
        }
    }

    public zzs zzb(final Context context, final String str, final zzgq com_google_android_gms_internal_zzgq) {
        return (zzs) zza(context, false, new zza<zzs>(this) {
            final /* synthetic */ zzl zzaya;

            public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return zzc(com_google_android_gms_ads_internal_client_zzx);
            }

            public zzs zzc(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return com_google_android_gms_ads_internal_client_zzx.createAdLoaderBuilder(zze.zzac(context), str, com_google_android_gms_internal_zzgq, com.google.android.gms.common.internal.zzf.BA);
            }

            public /* synthetic */ Object zzjh() throws RemoteException {
                return zzji();
            }

            public zzs zzji() {
                zzs zza = this.zzaya.zzaxs.zza(context, str, com_google_android_gms_internal_zzgq);
                if (zza != null) {
                    return zza;
                }
                this.zzaya.zzc(context, "native_ad");
                return new zzaj();
            }
        });
    }

    public zzu zzb(Context context, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq) {
        final Context context2 = context;
        final AdSizeParcel adSizeParcel2 = adSizeParcel;
        final String str2 = str;
        final zzgq com_google_android_gms_internal_zzgq2 = com_google_android_gms_internal_zzgq;
        return (zzu) zza(context, false, new zza<zzu>(this) {
            final /* synthetic */ zzl zzaya;

            public zzu zza(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return com_google_android_gms_ads_internal_client_zzx.createInterstitialAdManager(zze.zzac(context2), adSizeParcel2, str2, com_google_android_gms_internal_zzgq2, com.google.android.gms.common.internal.zzf.BA);
            }

            public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return zza(com_google_android_gms_ads_internal_client_zzx);
            }

            public zzu zzjg() {
                zzu zza = this.zzaya.zzaxr.zza(context2, adSizeParcel2, str2, com_google_android_gms_internal_zzgq2, 2);
                if (zza != null) {
                    return zza;
                }
                this.zzaya.zzc(context2, "interstitial");
                return new zzak();
            }

            public /* synthetic */ Object zzjh() throws RemoteException {
                return zzjg();
            }
        });
    }

    @Nullable
    public zzhy zzb(final Activity activity) {
        return (zzhy) zza((Context) activity, zza(activity, "com.google.android.gms.ads.internal.purchase.useClientJar"), new zza<zzhy>(this) {
            final /* synthetic */ zzl zzaya;

            public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return zzg(com_google_android_gms_ads_internal_client_zzx);
            }

            public zzhy zzg(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return com_google_android_gms_ads_internal_client_zzx.createInAppPurchaseManager(zze.zzac(activity));
            }

            public /* synthetic */ Object zzjh() throws RemoteException {
                return zzjm();
            }

            public zzhy zzjm() {
                zzhy zzg = this.zzaya.zzaxw.zzg(activity);
                if (zzg != null) {
                    return zzg;
                }
                this.zzaya.zzc(activity, "iap");
                return null;
            }
        });
    }

    @Nullable
    public zzhp zzc(final Activity activity) {
        return (zzhp) zza((Context) activity, zza(activity, "com.google.android.gms.ads.internal.overlay.useClientJar"), new zza<zzhp>(this) {
            final /* synthetic */ zzl zzaya;

            public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return zzh(com_google_android_gms_ads_internal_client_zzx);
            }

            public zzhp zzh(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return com_google_android_gms_ads_internal_client_zzx.createAdOverlay(zze.zzac(activity));
            }

            public /* synthetic */ Object zzjh() throws RemoteException {
                return zzjn();
            }

            public zzhp zzjn() {
                zzhp zzf = this.zzaya.zzaxx.zzf(activity);
                if (zzf != null) {
                    return zzf;
                }
                this.zzaya.zzc(activity, "ad_overlay");
                return null;
            }
        });
    }

    public zzz zzl(final Context context) {
        return (zzz) zza(context, false, new zza<zzz>(this) {
            final /* synthetic */ zzl zzaya;

            public /* synthetic */ Object zzb(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return zzd(com_google_android_gms_ads_internal_client_zzx);
            }

            public zzz zzd(zzx com_google_android_gms_ads_internal_client_zzx) throws RemoteException {
                return com_google_android_gms_ads_internal_client_zzx.getMobileAdsSettingsManagerWithClientJarVersion(zze.zzac(context), com.google.android.gms.common.internal.zzf.BA);
            }

            public /* synthetic */ Object zzjh() throws RemoteException {
                return zzjj();
            }

            public zzz zzjj() {
                zzz zzm = this.zzaya.zzaxt.zzm(context);
                if (zzm != null) {
                    return zzm;
                }
                this.zzaya.zzc(context, "mobile_ads_settings");
                return new zzal();
            }
        });
    }
}
