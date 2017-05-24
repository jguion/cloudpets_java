package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.ads.internal.client.zzad;
import com.google.android.gms.ads.internal.client.zzc;
import com.google.android.gms.ads.internal.client.zzh;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.client.zzr;
import com.google.android.gms.ads.internal.client.zzs;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzem;
import com.google.android.gms.internal.zzen;
import com.google.android.gms.internal.zzeo;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzgp;

public class AdLoader {
    private final Context mContext;
    private final zzh zzajr;
    private final zzr zzajs;

    public static class Builder {
        private final Context mContext;
        private final zzs zzajt;

        Builder(Context context, zzs com_google_android_gms_ads_internal_client_zzs) {
            this.mContext = context;
            this.zzajt = com_google_android_gms_ads_internal_client_zzs;
        }

        public Builder(Context context, String str) {
            this((Context) zzac.zzb((Object) context, (Object) "context cannot be null"), zzm.zzjs().zzb(context, str, new zzgp()));
        }

        public AdLoader build() {
            try {
                return new AdLoader(this.mContext, this.zzajt.zzey());
            } catch (Throwable e) {
                zzb.zzb("Failed to build AdLoader.", e);
                return null;
            }
        }

        public Builder forAppInstallAd(OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
            try {
                this.zzajt.zza(new zzem(onAppInstallAdLoadedListener));
            } catch (Throwable e) {
                zzb.zzd("Failed to add app install ad listener", e);
            }
            return this;
        }

        public Builder forContentAd(OnContentAdLoadedListener onContentAdLoadedListener) {
            try {
                this.zzajt.zza(new zzen(onContentAdLoadedListener));
            } catch (Throwable e) {
                zzb.zzd("Failed to add content ad listener", e);
            }
            return this;
        }

        public Builder forCustomTemplateAd(String str, OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener, OnCustomClickListener onCustomClickListener) {
            try {
                this.zzajt.zza(str, new zzep(onCustomTemplateAdLoadedListener), onCustomClickListener == null ? null : new zzeo(onCustomClickListener));
            } catch (Throwable e) {
                zzb.zzd("Failed to add custom template ad listener", e);
            }
            return this;
        }

        public Builder withAdListener(AdListener adListener) {
            try {
                this.zzajt.zzb(new zzc(adListener));
            } catch (Throwable e) {
                zzb.zzd("Failed to set AdListener.", e);
            }
            return this;
        }

        public Builder withCorrelator(@NonNull Correlator correlator) {
            zzac.zzy(correlator);
            try {
                this.zzajt.zzb(correlator.zzdh());
            } catch (Throwable e) {
                zzb.zzd("Failed to set correlator.", e);
            }
            return this;
        }

        public Builder withNativeAdOptions(NativeAdOptions nativeAdOptions) {
            try {
                this.zzajt.zza(new NativeAdOptionsParcel(nativeAdOptions));
            } catch (Throwable e) {
                zzb.zzd("Failed to specify native ad options", e);
            }
            return this;
        }
    }

    AdLoader(Context context, zzr com_google_android_gms_ads_internal_client_zzr) {
        this(context, com_google_android_gms_ads_internal_client_zzr, zzh.zzjb());
    }

    AdLoader(Context context, zzr com_google_android_gms_ads_internal_client_zzr, zzh com_google_android_gms_ads_internal_client_zzh) {
        this.mContext = context;
        this.zzajs = com_google_android_gms_ads_internal_client_zzr;
        this.zzajr = com_google_android_gms_ads_internal_client_zzh;
    }

    private void zza(zzad com_google_android_gms_ads_internal_client_zzad) {
        try {
            this.zzajs.zzf(this.zzajr.zza(this.mContext, com_google_android_gms_ads_internal_client_zzad));
        } catch (Throwable e) {
            zzb.zzb("Failed to load ad.", e);
        }
    }

    public String getMediationAdapterClassName() {
        try {
            return this.zzajs.getMediationAdapterClassName();
        } catch (Throwable e) {
            zzb.zzd("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    public boolean isLoading() {
        try {
            return this.zzajs.isLoading();
        } catch (Throwable e) {
            zzb.zzd("Failed to check if ad is loading.", e);
            return false;
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(AdRequest adRequest) {
        zza(adRequest.zzdg());
    }

    public void loadAd(PublisherAdRequest publisherAdRequest) {
        zza(publisherAdRequest.zzdg());
    }
}
