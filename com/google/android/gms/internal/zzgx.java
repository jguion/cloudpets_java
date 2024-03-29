package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.mediation.OnContextChangedListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgr.zza;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zziy
public final class zzgx extends zza {
    private final MediationAdapter zzbtz;
    private zzgy zzbua;

    public zzgx(MediationAdapter mediationAdapter) {
        this.zzbtz = mediationAdapter;
    }

    private Bundle zza(String str, int i, String str2) throws RemoteException {
        String str3 = "Server parameters: ";
        String valueOf = String.valueOf(str);
        zzb.zzdf(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    valueOf = (String) keys.next();
                    bundle2.putString(valueOf, jSONObject.getString(valueOf));
                }
                bundle = bundle2;
            }
            if (this.zzbtz instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                bundle.putInt("tagForChildDirectedTreatment", i);
            }
            return bundle;
        } catch (Throwable th) {
            zzb.zzd("Could not get Server Parameters Bundle.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public void destroy() throws RemoteException {
        try {
            this.zzbtz.onDestroy();
        } catch (Throwable th) {
            zzb.zzd("Could not destroy adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public Bundle getInterstitialAdapterInfo() {
        if (this.zzbtz instanceof zzmh) {
            return ((zzmh) this.zzbtz).getInterstitialAdapterInfo();
        }
        String str = "MediationAdapter is not a v2 MediationInterstitialAdapter: ";
        String valueOf = String.valueOf(this.zzbtz.getClass().getCanonicalName());
        zzb.zzdf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public zzd getView() throws RemoteException {
        if (this.zzbtz instanceof MediationBannerAdapter) {
            try {
                return zze.zzac(((MediationBannerAdapter) this.zzbtz).getBannerView());
            } catch (Throwable th) {
                zzb.zzd("Could not get banner view from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            zzb.zzdf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public boolean isInitialized() throws RemoteException {
        if (this.zzbtz instanceof MediationRewardedVideoAdAdapter) {
            zzb.zzdd("Check if adapter is initialized.");
            try {
                return ((MediationRewardedVideoAdAdapter) this.zzbtz).isInitialized();
            } catch (Throwable th) {
                zzb.zzd("Could not check if adapter is initialized.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            zzb.zzdf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public void pause() throws RemoteException {
        try {
            this.zzbtz.onPause();
        } catch (Throwable th) {
            zzb.zzd("Could not pause adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public void resume() throws RemoteException {
        try {
            this.zzbtz.onResume();
        } catch (Throwable th) {
            zzb.zzd("Could not resume adapter.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public void showInterstitial() throws RemoteException {
        if (this.zzbtz instanceof MediationInterstitialAdapter) {
            zzb.zzdd("Showing interstitial from adapter.");
            try {
                ((MediationInterstitialAdapter) this.zzbtz).showInterstitial();
            } catch (Throwable th) {
                zzb.zzd("Could not show interstitial from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            zzb.zzdf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public void showVideo() throws RemoteException {
        if (this.zzbtz instanceof MediationRewardedVideoAdAdapter) {
            zzb.zzdd("Show rewarded video ad from adapter.");
            try {
                ((MediationRewardedVideoAdAdapter) this.zzbtz).showVideo();
            } catch (Throwable th) {
                zzb.zzd("Could not show rewarded video ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            zzb.zzdf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
    }

    public void zza(AdRequestParcel adRequestParcel, String str, String str2) throws RemoteException {
        if (this.zzbtz instanceof MediationRewardedVideoAdAdapter) {
            zzb.zzdd("Requesting rewarded video ad from adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzbtz;
                mediationRewardedVideoAdAdapter.loadAd(new zzgw(adRequestParcel.zzawd == -1 ? null : new Date(adRequestParcel.zzawd), adRequestParcel.zzawe, adRequestParcel.zzawf != null ? new HashSet(adRequestParcel.zzawf) : null, adRequestParcel.zzawl, adRequestParcel.zzawg, adRequestParcel.zzawh, adRequestParcel.zzaws), zza(str, adRequestParcel.zzawh, str2), adRequestParcel.zzawn != null ? adRequestParcel.zzawn.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzb.zzd("Could not load rewarded video ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            zzb.zzdf(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, com.google.android.gms.ads.internal.reward.mediation.client.zza com_google_android_gms_ads_internal_reward_mediation_client_zza, String str2) throws RemoteException {
        if (this.zzbtz instanceof MediationRewardedVideoAdAdapter) {
            zzb.zzdd("Initialize rewarded video adapter.");
            try {
                MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzbtz;
                mediationRewardedVideoAdAdapter.initialize((Context) zze.zzae(com_google_android_gms_dynamic_zzd), new zzgw(adRequestParcel.zzawd == -1 ? null : new Date(adRequestParcel.zzawd), adRequestParcel.zzawe, adRequestParcel.zzawf != null ? new HashSet(adRequestParcel.zzawf) : null, adRequestParcel.zzawl, adRequestParcel.zzawg, adRequestParcel.zzawh, adRequestParcel.zzaws), str, new com.google.android.gms.ads.internal.reward.mediation.client.zzb(com_google_android_gms_ads_internal_reward_mediation_client_zza), zza(str2, adRequestParcel.zzawh, null), adRequestParcel.zzawn != null ? adRequestParcel.zzawn.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzb.zzd("Could not initialize rewarded video adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            zzb.zzdf(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, zzgs com_google_android_gms_internal_zzgs) throws RemoteException {
        zza(com_google_android_gms_dynamic_zzd, adRequestParcel, str, null, com_google_android_gms_internal_zzgs);
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, String str2, zzgs com_google_android_gms_internal_zzgs) throws RemoteException {
        if (this.zzbtz instanceof MediationInterstitialAdapter) {
            zzb.zzdd("Requesting interstitial ad from adapter.");
            try {
                MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzbtz;
                mediationInterstitialAdapter.requestInterstitialAd((Context) zze.zzae(com_google_android_gms_dynamic_zzd), new zzgy(com_google_android_gms_internal_zzgs), zza(str, adRequestParcel.zzawh, str2), new zzgw(adRequestParcel.zzawd == -1 ? null : new Date(adRequestParcel.zzawd), adRequestParcel.zzawe, adRequestParcel.zzawf != null ? new HashSet(adRequestParcel.zzawf) : null, adRequestParcel.zzawl, adRequestParcel.zzawg, adRequestParcel.zzawh, adRequestParcel.zzaws), adRequestParcel.zzawn != null ? adRequestParcel.zzawn.getBundle(mediationInterstitialAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzb.zzd("Could not request interstitial ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            zzb.zzdf(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdRequestParcel adRequestParcel, String str, String str2, zzgs com_google_android_gms_internal_zzgs, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list) throws RemoteException {
        if (this.zzbtz instanceof MediationNativeAdapter) {
            try {
                MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) this.zzbtz;
                zzhb com_google_android_gms_internal_zzhb = new zzhb(adRequestParcel.zzawd == -1 ? null : new Date(adRequestParcel.zzawd), adRequestParcel.zzawe, adRequestParcel.zzawf != null ? new HashSet(adRequestParcel.zzawf) : null, adRequestParcel.zzawl, adRequestParcel.zzawg, adRequestParcel.zzawh, nativeAdOptionsParcel, list, adRequestParcel.zzaws);
                Bundle bundle = adRequestParcel.zzawn != null ? adRequestParcel.zzawn.getBundle(mediationNativeAdapter.getClass().getName()) : null;
                this.zzbua = new zzgy(com_google_android_gms_internal_zzgs);
                mediationNativeAdapter.requestNativeAd((Context) zze.zzae(com_google_android_gms_dynamic_zzd), this.zzbua, zza(str, adRequestParcel.zzawh, str2), com_google_android_gms_internal_zzhb, bundle);
            } catch (Throwable th) {
                zzb.zzd("Could not request native ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationNativeAdapter: ";
            String valueOf = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            zzb.zzdf(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, AdRequestParcel adRequestParcel, String str, zzgs com_google_android_gms_internal_zzgs) throws RemoteException {
        zza(com_google_android_gms_dynamic_zzd, adSizeParcel, adRequestParcel, str, null, com_google_android_gms_internal_zzgs);
    }

    public void zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, AdRequestParcel adRequestParcel, String str, String str2, zzgs com_google_android_gms_internal_zzgs) throws RemoteException {
        if (this.zzbtz instanceof MediationBannerAdapter) {
            zzb.zzdd("Requesting banner ad from adapter.");
            try {
                MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzbtz;
                mediationBannerAdapter.requestBannerAd((Context) zze.zzae(com_google_android_gms_dynamic_zzd), new zzgy(com_google_android_gms_internal_zzgs), zza(str, adRequestParcel.zzawh, str2), com.google.android.gms.ads.zza.zza(adSizeParcel.width, adSizeParcel.height, adSizeParcel.zzaxi), new zzgw(adRequestParcel.zzawd == -1 ? null : new Date(adRequestParcel.zzawd), adRequestParcel.zzawe, adRequestParcel.zzawf != null ? new HashSet(adRequestParcel.zzawf) : null, adRequestParcel.zzawl, adRequestParcel.zzawg, adRequestParcel.zzawh, adRequestParcel.zzaws), adRequestParcel.zzawn != null ? adRequestParcel.zzawn.getBundle(mediationBannerAdapter.getClass().getName()) : null);
            } catch (Throwable th) {
                zzb.zzd("Could not request banner ad from adapter.", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            String str3 = "MediationAdapter is not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbtz.getClass().getCanonicalName());
            zzb.zzdf(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
    }

    public void zzc(AdRequestParcel adRequestParcel, String str) throws RemoteException {
        zza(adRequestParcel, str, null);
    }

    public void zzj(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
        try {
            ((OnContextChangedListener) this.zzbtz).onContextChanged((Context) zze.zzae(com_google_android_gms_dynamic_zzd));
        } catch (Throwable th) {
            zzb.zza("Could not inform adapter of changed context", th);
        }
    }

    public zzgu zznm() {
        NativeAdMapper zznq = this.zzbua.zznq();
        return zznq instanceof NativeAppInstallAdMapper ? new zzgz((NativeAppInstallAdMapper) zznq) : null;
    }

    public zzgv zznn() {
        NativeAdMapper zznq = this.zzbua.zznq();
        return zznq instanceof NativeContentAdMapper ? new zzha((NativeContentAdMapper) zznq) : null;
    }

    public Bundle zzno() {
        if (this.zzbtz instanceof zzmg) {
            return ((zzmg) this.zzbtz).zzno();
        }
        String str = "MediationAdapter is not a v2 MediationBannerAdapter: ";
        String valueOf = String.valueOf(this.zzbtz.getClass().getCanonicalName());
        zzb.zzdf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public Bundle zznp() {
        return new Bundle();
    }
}
