package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.formats.zzg;
import com.google.android.gms.ads.internal.formats.zzh;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzei;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.internal.zzek;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.internal.zzgv;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzke.zza;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzlt;
import java.util.List;

@zziy
public class zzq extends zzb {
    private zzlt zzaoq;

    public zzq(Context context, zzd com_google_android_gms_ads_internal_zzd, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, VersionInfoParcel versionInfoParcel) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzgq, versionInfoParcel, com_google_android_gms_ads_internal_zzd);
    }

    private static zzd zza(zzgu com_google_android_gms_internal_zzgu) throws RemoteException {
        return new zzd(com_google_android_gms_internal_zzgu.getHeadline(), com_google_android_gms_internal_zzgu.getImages(), com_google_android_gms_internal_zzgu.getBody(), com_google_android_gms_internal_zzgu.zzlo() != null ? com_google_android_gms_internal_zzgu.zzlo() : null, com_google_android_gms_internal_zzgu.getCallToAction(), com_google_android_gms_internal_zzgu.getStarRating(), com_google_android_gms_internal_zzgu.getStore(), com_google_android_gms_internal_zzgu.getPrice(), null, com_google_android_gms_internal_zzgu.getExtras(), com_google_android_gms_internal_zzgu.zzdw(), null);
    }

    private static zze zza(zzgv com_google_android_gms_internal_zzgv) throws RemoteException {
        return new zze(com_google_android_gms_internal_zzgv.getHeadline(), com_google_android_gms_internal_zzgv.getImages(), com_google_android_gms_internal_zzgv.getBody(), com_google_android_gms_internal_zzgv.zzlt() != null ? com_google_android_gms_internal_zzgv.zzlt() : null, com_google_android_gms_internal_zzgv.getCallToAction(), com_google_android_gms_internal_zzgv.getAdvertiser(), null, com_google_android_gms_internal_zzgv.getExtras());
    }

    private void zza(final zzd com_google_android_gms_ads_internal_formats_zzd) {
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzq zzaor;

            public void run() {
                try {
                    if (this.zzaor.zzall.zzarj != null) {
                        this.zzaor.zzall.zzarj.zza(com_google_android_gms_ads_internal_formats_zzd);
                    }
                } catch (Throwable e) {
                    zzb.zzd("Could not call OnAppInstallAdLoadedListener.onAppInstallAdLoaded().", e);
                }
            }
        });
    }

    private void zza(final zze com_google_android_gms_ads_internal_formats_zze) {
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzq zzaor;

            public void run() {
                try {
                    if (this.zzaor.zzall.zzark != null) {
                        this.zzaor.zzall.zzark.zza(com_google_android_gms_ads_internal_formats_zze);
                    }
                } catch (Throwable e) {
                    zzb.zzd("Could not call OnContentAdLoadedListener.onContentAdLoaded().", e);
                }
            }
        });
    }

    private void zza(final zzke com_google_android_gms_internal_zzke, final String str) {
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzq zzaor;

            public void run() {
                try {
                    ((zzek) this.zzaor.zzall.zzarm.get(str)).zza((zzf) com_google_android_gms_internal_zzke.zzcol);
                } catch (Throwable e) {
                    zzb.zzd("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", e);
                }
            }
        });
    }

    public void pause() {
        throw new IllegalStateException("Native Ad DOES NOT support pause().");
    }

    public void resume() {
        throw new IllegalStateException("Native Ad DOES NOT support resume().");
    }

    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by NativeAdManager.");
    }

    public void zza(SimpleArrayMap<String, zzek> simpleArrayMap) {
        zzac.zzhq("setOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        this.zzall.zzarm = simpleArrayMap;
    }

    public void zza(zzg com_google_android_gms_ads_internal_formats_zzg) {
        if (this.zzaoq != null) {
            this.zzaoq.zzb(com_google_android_gms_ads_internal_formats_zzg);
        }
    }

    public void zza(zzi com_google_android_gms_ads_internal_formats_zzi) {
        if (this.zzall.zzara.zzcod != null) {
            zzu.zzgd().zztx().zza(this.zzall.zzaqz, this.zzall.zzara, com_google_android_gms_ads_internal_formats_zzi);
        }
    }

    public void zza(zzdu com_google_android_gms_internal_zzdu) {
        throw new IllegalStateException("CustomRendering is NOT supported by NativeAdManager.");
    }

    public void zza(zzhx com_google_android_gms_internal_zzhx) {
        throw new IllegalStateException("In App Purchase is NOT supported by NativeAdManager.");
    }

    public void zza(final zza com_google_android_gms_internal_zzke_zza, zzdq com_google_android_gms_internal_zzdq) {
        if (com_google_android_gms_internal_zzke_zza.zzaqz != null) {
            this.zzall.zzaqz = com_google_android_gms_internal_zzke_zza.zzaqz;
        }
        if (com_google_android_gms_internal_zzke_zza.errorCode != -2) {
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzq zzaor;

                public void run() {
                    this.zzaor.zzb(new zzke(com_google_android_gms_internal_zzke_zza, null, null, null, null, null, null, null));
                }
            });
            return;
        }
        this.zzall.zzarv = 0;
        this.zzall.zzaqy = zzu.zzfy().zza(this.zzall.zzahn, this, com_google_android_gms_internal_zzke_zza, this.zzall.zzaqu, null, this.zzals, this, com_google_android_gms_internal_zzdq);
        String str = "AdRenderer: ";
        String valueOf = String.valueOf(this.zzall.zzaqy.getClass().getName());
        zzb.zzdd(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected boolean zza(AdRequestParcel adRequestParcel, zzke com_google_android_gms_internal_zzke, boolean z) {
        return this.zzalk.zzfl();
    }

    protected boolean zza(zzke com_google_android_gms_internal_zzke, zzke com_google_android_gms_internal_zzke2) {
        zzb(null);
        if (this.zzall.zzhc()) {
            if (com_google_android_gms_internal_zzke2.zzchc) {
                try {
                    zzgu zznm = com_google_android_gms_internal_zzke2.zzbtf != null ? com_google_android_gms_internal_zzke2.zzbtf.zznm() : null;
                    zzgv zznn = com_google_android_gms_internal_zzke2.zzbtf != null ? com_google_android_gms_internal_zzke2.zzbtf.zznn() : null;
                    if (zznm == null || this.zzall.zzarj == null) {
                        if (zznn != null) {
                            if (this.zzall.zzark != null) {
                                zze zza = zza(zznn);
                                zza.zzb(new zzh(this.zzall.zzahn, this, this.zzall.zzaqu, zznn, (zzi.zza) zza));
                                zza(zza);
                            }
                        }
                        zzb.zzdf("No matching mapper/listener for retrieved native ad template.");
                        zzh(0);
                        return false;
                    }
                    zzd zza2 = zza(zznm);
                    zza2.zzb(new zzh(this.zzall.zzahn, this, this.zzall.zzaqu, zznm, (zzi.zza) zza2));
                    zza(zza2);
                } catch (Throwable e) {
                    zzb.zzd("Failed to get native ad mapper", e);
                }
            } else {
                zzi.zza com_google_android_gms_ads_internal_formats_zzi_zza = com_google_android_gms_internal_zzke2.zzcol;
                if ((com_google_android_gms_ads_internal_formats_zzi_zza instanceof zze) && this.zzall.zzark != null) {
                    zza((zze) com_google_android_gms_internal_zzke2.zzcol);
                } else if ((com_google_android_gms_ads_internal_formats_zzi_zza instanceof zzd) && this.zzall.zzarj != null) {
                    zza((zzd) com_google_android_gms_internal_zzke2.zzcol);
                } else if (!(com_google_android_gms_ads_internal_formats_zzi_zza instanceof zzf) || this.zzall.zzarm == null || this.zzall.zzarm.get(((zzf) com_google_android_gms_ads_internal_formats_zzi_zza).getCustomTemplateId()) == null) {
                    zzb.zzdf("No matching listener for retrieved native ad template.");
                    zzh(0);
                    return false;
                } else {
                    zza(com_google_android_gms_internal_zzke2, ((zzf) com_google_android_gms_ads_internal_formats_zzi_zza).getCustomTemplateId());
                }
            }
            return super.zza(com_google_android_gms_internal_zzke, com_google_android_gms_internal_zzke2);
        }
        throw new IllegalStateException("Native ad DOES NOT have custom rendering mode.");
    }

    public void zzb(SimpleArrayMap<String, zzej> simpleArrayMap) {
        zzac.zzhq("setOnCustomClickListener must be called on the main UI thread.");
        this.zzall.zzarl = simpleArrayMap;
    }

    public void zzb(NativeAdOptionsParcel nativeAdOptionsParcel) {
        zzac.zzhq("setNativeAdOptions must be called on the main UI thread.");
        this.zzall.zzarn = nativeAdOptionsParcel;
    }

    public void zzb(zzeh com_google_android_gms_internal_zzeh) {
        zzac.zzhq("setOnAppInstallAdLoadedListener must be called on the main UI thread.");
        this.zzall.zzarj = com_google_android_gms_internal_zzeh;
    }

    public void zzb(zzei com_google_android_gms_internal_zzei) {
        zzac.zzhq("setOnContentAdLoadedListener must be called on the main UI thread.");
        this.zzall.zzark = com_google_android_gms_internal_zzei;
    }

    public void zzb(@Nullable List<String> list) {
        zzac.zzhq("setNativeTemplates must be called on the main UI thread.");
        this.zzall.zzarr = list;
    }

    public void zzc(zzlt com_google_android_gms_internal_zzlt) {
        this.zzaoq = com_google_android_gms_internal_zzlt;
    }

    public void zzfh() {
        if (this.zzall.zzara == null || this.zzaoq == null) {
            zzb.zzdf("Request to enable ActiveView before adState is available.");
        } else {
            zzu.zzgd().zztx().zza(this.zzall.zzaqz, this.zzall.zzara, this.zzaoq.getView(), this.zzaoq);
        }
    }

    public SimpleArrayMap<String, zzek> zzfi() {
        zzac.zzhq("getOnCustomTemplateAdLoadedListeners must be called on the main UI thread.");
        return this.zzall.zzarm;
    }

    public void zzfj() {
        if (this.zzaoq != null) {
            this.zzaoq.destroy();
            this.zzaoq = null;
        }
    }

    public void zzfk() {
        if (this.zzaoq != null && this.zzaoq.zzwb() != null && this.zzall.zzarn != null && this.zzall.zzarn.zzblf != null) {
            this.zzaoq.zzwb().zzap(this.zzall.zzarn.zzblf.zzbac);
        }
    }

    @Nullable
    public zzej zzx(String str) {
        zzac.zzhq("getOnCustomClickListener must be called on the main UI thread.");
        return (zzej) this.zzall.zzarl.get(str);
    }
}
