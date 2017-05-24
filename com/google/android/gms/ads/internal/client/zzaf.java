package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.internal.reward.client.zzg;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zzdv;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zzic;
import com.google.android.gms.internal.zzig;
import com.google.android.gms.internal.zziy;

@zziy
public class zzaf {
    private final Context mContext;
    private final zzh zzajr;
    private String zzang;
    private zza zzawb;
    private AdListener zzawc;
    private AppEventListener zzaxo;
    private final zzgp zzays;
    private Correlator zzayw;
    private zzu zzayx;
    private InAppPurchaseListener zzayy;
    private OnCustomRenderedAdLoadedListener zzayz;
    private PlayStorePurchaseListener zzaza;
    private String zzazb;
    private PublisherInterstitialAd zzazf;
    private boolean zzazg;
    private RewardedVideoAdListener zzgf;

    public zzaf(Context context) {
        this(context, zzh.zzjb(), null);
    }

    public zzaf(Context context, PublisherInterstitialAd publisherInterstitialAd) {
        this(context, zzh.zzjb(), publisherInterstitialAd);
    }

    public zzaf(Context context, zzh com_google_android_gms_ads_internal_client_zzh, PublisherInterstitialAd publisherInterstitialAd) {
        this.zzays = new zzgp();
        this.mContext = context;
        this.zzajr = com_google_android_gms_ads_internal_client_zzh;
        this.zzazf = publisherInterstitialAd;
    }

    private void zzap(String str) throws RemoteException {
        if (this.zzang == null) {
            zzaq(str);
        }
        this.zzayx = zzm.zzjs().zzb(this.mContext, this.zzazg ? AdSizeParcel.zzjc() : new AdSizeParcel(), this.zzang, this.zzays);
        if (this.zzawc != null) {
            this.zzayx.zza(new zzc(this.zzawc));
        }
        if (this.zzawb != null) {
            this.zzayx.zza(new zzb(this.zzawb));
        }
        if (this.zzaxo != null) {
            this.zzayx.zza(new zzj(this.zzaxo));
        }
        if (this.zzayy != null) {
            this.zzayx.zza(new zzic(this.zzayy));
        }
        if (this.zzaza != null) {
            this.zzayx.zza(new zzig(this.zzaza), this.zzazb);
        }
        if (this.zzayz != null) {
            this.zzayx.zza(new zzdv(this.zzayz));
        }
        if (this.zzayw != null) {
            this.zzayx.zza(this.zzayw.zzdh());
        }
        if (this.zzgf != null) {
            this.zzayx.zza(new zzg(this.zzgf));
        }
    }

    private void zzaq(String str) {
        if (this.zzayx == null) {
            throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 63).append("The ad unit ID must be set on InterstitialAd before ").append(str).append(" is called.").toString());
        }
    }

    public AdListener getAdListener() {
        return this.zzawc;
    }

    public String getAdUnitId() {
        return this.zzang;
    }

    public AppEventListener getAppEventListener() {
        return this.zzaxo;
    }

    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.zzayy;
    }

    public String getMediationAdapterClassName() {
        try {
            if (this.zzayx != null) {
                return this.zzayx.getMediationAdapterClassName();
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to get the mediation adapter class name.", e);
        }
        return null;
    }

    public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzayz;
    }

    public boolean isLoaded() {
        boolean z = false;
        try {
            if (this.zzayx != null) {
                z = this.zzayx.isReady();
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to check if ad is ready.", e);
        }
        return z;
    }

    public boolean isLoading() {
        boolean z = false;
        try {
            if (this.zzayx != null) {
                z = this.zzayx.isLoading();
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to check if ad is loading.", e);
        }
        return z;
    }

    public void setAdListener(AdListener adListener) {
        try {
            this.zzawc = adListener;
            if (this.zzayx != null) {
                this.zzayx.zza(adListener != null ? new zzc(adListener) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the AdListener.", e);
        }
    }

    public void setAdUnitId(String str) {
        if (this.zzang != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
        }
        this.zzang = str;
    }

    public void setAppEventListener(AppEventListener appEventListener) {
        try {
            this.zzaxo = appEventListener;
            if (this.zzayx != null) {
                this.zzayx.zza(appEventListener != null ? new zzj(appEventListener) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the AppEventListener.", e);
        }
    }

    public void setCorrelator(Correlator correlator) {
        this.zzayw = correlator;
        try {
            if (this.zzayx != null) {
                this.zzayx.zza(this.zzayw == null ? null : this.zzayw.zzdh());
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set correlator.", e);
        }
    }

    public void setInAppPurchaseListener(InAppPurchaseListener inAppPurchaseListener) {
        if (this.zzaza != null) {
            throw new IllegalStateException("Play store purchase parameter has already been set.");
        }
        try {
            this.zzayy = inAppPurchaseListener;
            if (this.zzayx != null) {
                this.zzayx.zza(inAppPurchaseListener != null ? new zzic(inAppPurchaseListener) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the InAppPurchaseListener.", e);
        }
    }

    public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        try {
            this.zzayz = onCustomRenderedAdLoadedListener;
            if (this.zzayx != null) {
                this.zzayx.zza(onCustomRenderedAdLoadedListener != null ? new zzdv(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the OnCustomRenderedAdLoadedListener.", e);
        }
    }

    public void setPlayStorePurchaseParams(PlayStorePurchaseListener playStorePurchaseListener, String str) {
        if (this.zzayy != null) {
            throw new IllegalStateException("In app purchase parameter has already been set.");
        }
        try {
            this.zzaza = playStorePurchaseListener;
            this.zzazb = str;
            if (this.zzayx != null) {
                this.zzayx.zza(playStorePurchaseListener != null ? new zzig(playStorePurchaseListener) : null, str);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the play store purchase parameter.", e);
        }
    }

    public void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        try {
            this.zzgf = rewardedVideoAdListener;
            if (this.zzayx != null) {
                this.zzayx.zza(rewardedVideoAdListener != null ? new zzg(rewardedVideoAdListener) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the AdListener.", e);
        }
    }

    public void show() {
        try {
            zzaq("show");
            this.zzayx.showInterstitial();
        } catch (Throwable e) {
            zzb.zzd("Failed to show interstitial.", e);
        }
    }

    public void zza(zza com_google_android_gms_ads_internal_client_zza) {
        try {
            this.zzawb = com_google_android_gms_ads_internal_client_zza;
            if (this.zzayx != null) {
                this.zzayx.zza(com_google_android_gms_ads_internal_client_zza != null ? new zzb(com_google_android_gms_ads_internal_client_zza) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the AdClickListener.", e);
        }
    }

    public void zza(zzad com_google_android_gms_ads_internal_client_zzad) {
        try {
            if (this.zzayx == null) {
                zzap("loadAd");
            }
            if (this.zzayx.zzb(this.zzajr.zza(this.mContext, com_google_android_gms_ads_internal_client_zzad))) {
                this.zzays.zzh(com_google_android_gms_ads_internal_client_zzad.zzkb());
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to load ad.", e);
        }
    }

    public void zzd(boolean z) {
        this.zzazg = z;
    }
}
