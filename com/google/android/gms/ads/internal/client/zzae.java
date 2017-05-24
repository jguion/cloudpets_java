package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdv;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zzic;
import com.google.android.gms.internal.zzig;
import com.google.android.gms.internal.zziy;
import java.util.concurrent.atomic.AtomicBoolean;

@zziy
public class zzae {
    private final zzh zzajr;
    private VideoOptions zzakr;
    private boolean zzami;
    private String zzang;
    private zza zzawb;
    private AdListener zzawc;
    private AppEventListener zzaxo;
    private AdSize[] zzaxp;
    private final zzgp zzays;
    private final AtomicBoolean zzayt;
    private final VideoController zzayu;
    final zzo zzayv;
    private Correlator zzayw;
    private zzu zzayx;
    private InAppPurchaseListener zzayy;
    private OnCustomRenderedAdLoadedListener zzayz;
    private PlayStorePurchaseListener zzaza;
    private String zzazb;
    private ViewGroup zzazc;
    private boolean zzazd;

    public zzae(ViewGroup viewGroup) {
        this(viewGroup, null, false, zzh.zzjb(), false);
    }

    public zzae(ViewGroup viewGroup, AttributeSet attributeSet, boolean z) {
        this(viewGroup, attributeSet, z, zzh.zzjb(), false);
    }

    zzae(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzh com_google_android_gms_ads_internal_client_zzh, zzu com_google_android_gms_ads_internal_client_zzu, boolean z2) {
        this.zzays = new zzgp();
        this.zzayu = new VideoController();
        this.zzayv = new zzo(this) {
            final /* synthetic */ zzae zzaze;

            {
                this.zzaze = r1;
            }

            public void onAdFailedToLoad(int i) {
                this.zzaze.zzayu.zza(this.zzaze.zzdj());
                super.onAdFailedToLoad(i);
            }

            public void onAdLoaded() {
                this.zzaze.zzayu.zza(this.zzaze.zzdj());
                super.onAdLoaded();
            }
        };
        this.zzazc = viewGroup;
        this.zzajr = com_google_android_gms_ads_internal_client_zzh;
        this.zzayx = com_google_android_gms_ads_internal_client_zzu;
        this.zzayt = new AtomicBoolean(false);
        this.zzazd = z2;
        if (attributeSet != null) {
            Context context = viewGroup.getContext();
            try {
                zzk com_google_android_gms_ads_internal_client_zzk = new zzk(context, attributeSet);
                this.zzaxp = com_google_android_gms_ads_internal_client_zzk.zzm(z);
                this.zzang = com_google_android_gms_ads_internal_client_zzk.getAdUnitId();
                if (viewGroup.isInEditMode()) {
                    zzm.zzjr().zza(viewGroup, zza(context, this.zzaxp[0], this.zzazd), "Ads by Google");
                }
            } catch (IllegalArgumentException e) {
                zzm.zzjr().zza(viewGroup, new AdSizeParcel(context, AdSize.BANNER), e.getMessage(), e.getMessage());
            }
        }
    }

    zzae(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, zzh com_google_android_gms_ads_internal_client_zzh, boolean z2) {
        this(viewGroup, attributeSet, z, com_google_android_gms_ads_internal_client_zzh, null, z2);
    }

    public zzae(ViewGroup viewGroup, AttributeSet attributeSet, boolean z, boolean z2) {
        this(viewGroup, attributeSet, z, zzh.zzjb(), z2);
    }

    public zzae(ViewGroup viewGroup, boolean z) {
        this(viewGroup, null, false, zzh.zzjb(), z);
    }

    private static AdSizeParcel zza(Context context, AdSize adSize, boolean z) {
        AdSizeParcel adSizeParcel = new AdSizeParcel(context, adSize);
        adSizeParcel.zzl(z);
        return adSizeParcel;
    }

    private static AdSizeParcel zza(Context context, AdSize[] adSizeArr, boolean z) {
        AdSizeParcel adSizeParcel = new AdSizeParcel(context, adSizeArr);
        adSizeParcel.zzl(z);
        return adSizeParcel;
    }

    private void zzkf() {
        try {
            zzd zzds = this.zzayx.zzds();
            if (zzds != null) {
                this.zzazc.addView((View) zze.zzae(zzds));
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to get an ad frame.", e);
        }
    }

    public void destroy() {
        try {
            if (this.zzayx != null) {
                this.zzayx.destroy();
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to destroy AdView.", e);
        }
    }

    public AdListener getAdListener() {
        return this.zzawc;
    }

    public AdSize getAdSize() {
        try {
            if (this.zzayx != null) {
                AdSizeParcel zzdt = this.zzayx.zzdt();
                if (zzdt != null) {
                    return zzdt.zzjd();
                }
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to get the current AdSize.", e);
        }
        return this.zzaxp != null ? this.zzaxp[0] : null;
    }

    public AdSize[] getAdSizes() {
        return this.zzaxp;
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

    public VideoController getVideoController() {
        return this.zzayu;
    }

    public VideoOptions getVideoOptions() {
        return this.zzakr;
    }

    public boolean isLoading() {
        try {
            if (this.zzayx != null) {
                return this.zzayx.isLoading();
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to check if ad is loading.", e);
        }
        return false;
    }

    public void pause() {
        try {
            if (this.zzayx != null) {
                this.zzayx.pause();
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to call pause.", e);
        }
    }

    public void recordManualImpression() {
        if (!this.zzayt.getAndSet(true)) {
            try {
                if (this.zzayx != null) {
                    this.zzayx.zzdv();
                }
            } catch (Throwable e) {
                zzb.zzd("Failed to record impression.", e);
            }
        }
    }

    public void resume() {
        try {
            if (this.zzayx != null) {
                this.zzayx.resume();
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to call resume.", e);
        }
    }

    public void setAdListener(AdListener adListener) {
        this.zzawc = adListener;
        this.zzayv.zza(adListener);
    }

    public void setAdSizes(AdSize... adSizeArr) {
        if (this.zzaxp != null) {
            throw new IllegalStateException("The ad size can only be set once on AdView.");
        }
        zza(adSizeArr);
    }

    public void setAdUnitId(String str) {
        if (this.zzang != null) {
            throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
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

    public void setManualImpressionsEnabled(boolean z) {
        this.zzami = z;
        try {
            if (this.zzayx != null) {
                this.zzayx.setManualImpressionsEnabled(this.zzami);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set manual impressions.", e);
        }
    }

    public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzayz = onCustomRenderedAdLoadedListener;
        try {
            if (this.zzayx != null) {
                this.zzayx.zza(onCustomRenderedAdLoadedListener != null ? new zzdv(onCustomRenderedAdLoadedListener) : null);
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the onCustomRenderedAdLoadedListener.", e);
        }
    }

    public void setPlayStorePurchaseParams(PlayStorePurchaseListener playStorePurchaseListener, String str) {
        if (this.zzayy != null) {
            throw new IllegalStateException("InAppPurchaseListener has already been set.");
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

    public void setVideoOptions(VideoOptions videoOptions) {
        this.zzakr = videoOptions;
        try {
            if (this.zzayx != null) {
                this.zzayx.zza(videoOptions == null ? null : new VideoOptionsParcel(videoOptions));
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set video options.", e);
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
                zzkg();
            }
            if (this.zzayx.zzb(this.zzajr.zza(this.zzazc.getContext(), com_google_android_gms_ads_internal_client_zzad))) {
                this.zzays.zzh(com_google_android_gms_ads_internal_client_zzad.zzkb());
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to load ad.", e);
        }
    }

    public void zza(AdSize... adSizeArr) {
        this.zzaxp = adSizeArr;
        try {
            if (this.zzayx != null) {
                this.zzayx.zza(zza(this.zzazc.getContext(), this.zzaxp, this.zzazd));
            }
        } catch (Throwable e) {
            zzb.zzd("Failed to set the ad size.", e);
        }
        this.zzazc.requestLayout();
    }

    public boolean zzb(AdSizeParcel adSizeParcel) {
        return "search_v2".equals(adSizeParcel.zzaxi);
    }

    public zzab zzdj() {
        zzab com_google_android_gms_ads_internal_client_zzab = null;
        if (this.zzayx != null) {
            try {
                com_google_android_gms_ads_internal_client_zzab = this.zzayx.zzdw();
            } catch (Throwable e) {
                zzb.zzd("Failed to retrieve VideoController.", e);
            }
        }
        return com_google_android_gms_ads_internal_client_zzab;
    }

    void zzkg() throws RemoteException {
        if ((this.zzaxp == null || this.zzang == null) && this.zzayx == null) {
            throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
        }
        this.zzayx = zzkh();
        this.zzayx.zza(new zzc(this.zzayv));
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
        if (this.zzakr != null) {
            this.zzayx.zza(new VideoOptionsParcel(this.zzakr));
        }
        this.zzayx.setManualImpressionsEnabled(this.zzami);
        zzkf();
    }

    protected zzu zzkh() throws RemoteException {
        Context context = this.zzazc.getContext();
        AdSizeParcel zza = zza(context, this.zzaxp, this.zzazd);
        return zzb(zza) ? zzm.zzjs().zza(context, zza, this.zzang) : zzm.zzjs().zza(context, zza, this.zzang, this.zzays);
    }
}
