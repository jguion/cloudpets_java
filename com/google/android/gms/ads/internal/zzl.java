package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.safebrowsing.zzc;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzez;
import com.google.android.gms.internal.zzfe;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zzgg;
import com.google.android.gms.internal.zzgh;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzjc;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkm;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzlu;
import java.util.Collections;
import java.util.concurrent.Future;
import org.json.JSONObject;

@zziy
public class zzl extends zzc implements zzez, com.google.android.gms.internal.zzfe.zza {
    protected transient boolean zzanp = false;
    private int zzanq = -1;
    private boolean zzanr;
    private float zzans;

    @zziy
    private class zza extends zzkm {
        private final int zzant;
        final /* synthetic */ zzl zzanu;

        public zza(zzl com_google_android_gms_ads_internal_zzl, int i) {
            this.zzanu = com_google_android_gms_ads_internal_zzl;
            this.zzant = i;
        }

        public void onStop() {
        }

        public void zzfc() {
            InterstitialAdParameterParcel interstitialAdParameterParcel = new InterstitialAdParameterParcel(this.zzanu.zzall.zzanx, this.zzanu.zzez(), this.zzanu.zzanr, this.zzanu.zzans, this.zzanu.zzall.zzanx ? this.zzant : -1);
            int requestedOrientation = this.zzanu.zzall.zzara.zzbyh.getRequestedOrientation();
            final AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(this.zzanu, this.zzanu, this.zzanu, this.zzanu.zzall.zzara.zzbyh, requestedOrientation == -1 ? this.zzanu.zzall.zzara.orientation : requestedOrientation, this.zzanu.zzall.zzaqv, this.zzanu.zzall.zzara.zzchh, interstitialAdParameterParcel);
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zza zzanw;

                public void run() {
                    zzu.zzfx().zza(this.zzanw.zzanu.zzall.zzahn, adOverlayInfoParcel);
                }
            });
        }
    }

    public zzl(Context context, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzgq, versionInfoParcel, com_google_android_gms_ads_internal_zzd);
    }

    private void zzb(Bundle bundle) {
        zzu.zzfz().zzb(this.zzall.zzahn, this.zzall.zzaqv.zzcs, "gmob-apps", bundle, false);
    }

    static com.google.android.gms.internal.zzke.zza zzc(com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza) {
        try {
            String jSONObject = zzjc.zzc(com_google_android_gms_internal_zzke_zza.zzcop).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, com_google_android_gms_internal_zzke_zza.zzcix.zzaqt);
            zzgg com_google_android_gms_internal_zzgg = new zzgg(jSONObject, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList());
            AdResponseParcel adResponseParcel = com_google_android_gms_internal_zzke_zza.zzcop;
            zzgh com_google_android_gms_internal_zzgh = new zzgh(Collections.singletonList(com_google_android_gms_internal_zzgg), -1, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), adResponseParcel.zzbsg, adResponseParcel.zzbsh, "", -1, 0, 1, null, 0, -1, -1, false);
            return new com.google.android.gms.internal.zzke.zza(com_google_android_gms_internal_zzke_zza.zzcix, new AdResponseParcel(com_google_android_gms_internal_zzke_zza.zzcix, adResponseParcel.zzbyj, adResponseParcel.body, Collections.emptyList(), Collections.emptyList(), adResponseParcel.zzchb, true, adResponseParcel.zzchd, Collections.emptyList(), adResponseParcel.zzbsj, adResponseParcel.orientation, adResponseParcel.zzchf, adResponseParcel.zzchg, adResponseParcel.zzchh, adResponseParcel.zzchi, adResponseParcel.zzchj, null, adResponseParcel.zzchl, adResponseParcel.zzaxl, adResponseParcel.zzcgc, adResponseParcel.zzchm, adResponseParcel.zzchn, adResponseParcel.zzchq, adResponseParcel.zzaxm, adResponseParcel.zzaxn, null, Collections.emptyList(), Collections.emptyList(), adResponseParcel.zzchu, adResponseParcel.zzchv, adResponseParcel.zzcgt, adResponseParcel.zzcgu, adResponseParcel.zzbsg, adResponseParcel.zzbsh, adResponseParcel.zzchw, null, adResponseParcel.zzchy), com_google_android_gms_internal_zzgh, com_google_android_gms_internal_zzke_zza.zzaqz, com_google_android_gms_internal_zzke_zza.errorCode, com_google_android_gms_internal_zzke_zza.zzcoj, com_google_android_gms_internal_zzke_zza.zzcok, null);
        } catch (Throwable e) {
            zzb.zzb("Unable to generate ad state for an interstitial ad with pooling.", e);
            return com_google_android_gms_internal_zzke_zza;
        }
    }

    public void showInterstitial() {
        zzac.zzhq("showInterstitial must be called on the main UI thread.");
        if (this.zzall.zzara == null) {
            zzb.zzdf("The interstitial has not loaded.");
            return;
        }
        if (((Boolean) zzdi.zzbdy.get()).booleanValue()) {
            Bundle bundle;
            String packageName = this.zzall.zzahn.getApplicationContext() != null ? this.zzall.zzahn.getApplicationContext().getPackageName() : this.zzall.zzahn.getPackageName();
            if (!this.zzanp) {
                zzb.zzdf("It is not recommended to show an interstitial before onAdLoaded completes.");
                bundle = new Bundle();
                bundle.putString("appid", packageName);
                bundle.putString("action", "show_interstitial_before_load_finish");
                zzb(bundle);
            }
            if (!zzu.zzfz().zzai(this.zzall.zzahn)) {
                zzb.zzdf("It is not recommended to show an interstitial when app is not in foreground.");
                bundle = new Bundle();
                bundle.putString("appid", packageName);
                bundle.putString("action", "show_interstitial_app_not_in_foreground");
                zzb(bundle);
            }
        }
        if (!this.zzall.zzhd()) {
            if (this.zzall.zzara.zzchc && this.zzall.zzara.zzbtf != null) {
                try {
                    this.zzall.zzara.zzbtf.showInterstitial();
                } catch (Throwable e) {
                    zzb.zzd("Could not show interstitial.", e);
                    zzfa();
                }
            } else if (this.zzall.zzara.zzbyh == null) {
                zzb.zzdf("The interstitial failed to load.");
            } else if (this.zzall.zzara.zzbyh.zzvv()) {
                zzb.zzdf("The interstitial is already showing.");
            } else {
                this.zzall.zzara.zzbyh.zzaj(true);
                if (this.zzall.zzara.zzcod != null) {
                    this.zzaln.zza(this.zzall.zzaqz, this.zzall.zzara);
                }
                Bitmap zzaj = this.zzall.zzanx ? zzu.zzfz().zzaj(this.zzall.zzahn) : null;
                this.zzanq = zzu.zzgu().zzb(zzaj);
                if (!((Boolean) zzdi.zzbfn.get()).booleanValue() || zzaj == null) {
                    InterstitialAdParameterParcel interstitialAdParameterParcel = new InterstitialAdParameterParcel(this.zzall.zzanx, zzez(), false, 0.0f, -1);
                    int requestedOrientation = this.zzall.zzara.zzbyh.getRequestedOrientation();
                    if (requestedOrientation == -1) {
                        requestedOrientation = this.zzall.zzara.orientation;
                    }
                    zzu.zzfx().zza(this.zzall.zzahn, new AdOverlayInfoParcel(this, this, this, this.zzall.zzara.zzbyh, requestedOrientation, this.zzall.zzaqv, this.zzall.zzara.zzchh, interstitialAdParameterParcel));
                    return;
                }
                Future future = (Future) new zza(this, this.zzanq).zzqw();
            }
        }
    }

    protected zzlt zza(com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, @Nullable zze com_google_android_gms_ads_internal_zze, @Nullable zzc com_google_android_gms_ads_internal_safebrowsing_zzc) {
        zzlt zza = zzu.zzga().zza(this.zzall.zzahn, this.zzall.zzaqz, false, false, this.zzall.zzaqu, this.zzall.zzaqv, this.zzalg, this, this.zzalo);
        zza.zzvr().zza(this, null, this, this, ((Boolean) zzdi.zzbcp.get()).booleanValue(), this, this, com_google_android_gms_ads_internal_zze, null, com_google_android_gms_ads_internal_safebrowsing_zzc);
        zza((zzfz) zza);
        zza.zzdh(com_google_android_gms_internal_zzke_zza.zzcix.zzcgj);
        zzfe.zza(zza, (com.google.android.gms.internal.zzfe.zza) this);
        return zza;
    }

    public void zza(com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, zzdq com_google_android_gms_internal_zzdq) {
        Object obj = 1;
        if (!((Boolean) zzdi.zzbdi.get()).booleanValue()) {
            super.zza(com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzdq);
        } else if (com_google_android_gms_internal_zzke_zza.errorCode != -2) {
            super.zza(com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzdq);
        } else {
            Bundle bundle = com_google_android_gms_internal_zzke_zza.zzcix.zzcfu.zzawn.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
            Object obj2 = (bundle == null || !bundle.containsKey("gw")) ? 1 : null;
            if (com_google_android_gms_internal_zzke_zza.zzcop.zzchc) {
                obj = null;
            }
            if (!(obj2 == null || r2 == null)) {
                this.zzall.zzarb = zzc(com_google_android_gms_internal_zzke_zza);
            }
            super.zza(this.zzall.zzarb, com_google_android_gms_internal_zzdq);
        }
    }

    public void zza(boolean z, float f) {
        this.zzanr = z;
        this.zzans = f;
    }

    public boolean zza(AdRequestParcel adRequestParcel, zzdq com_google_android_gms_internal_zzdq) {
        if (this.zzall.zzara == null) {
            return super.zza(adRequestParcel, com_google_android_gms_internal_zzdq);
        }
        zzb.zzdf("An interstitial is already loading. Aborting.");
        return false;
    }

    protected boolean zza(AdRequestParcel adRequestParcel, zzke com_google_android_gms_internal_zzke, boolean z) {
        if (this.zzall.zzhc() && com_google_android_gms_internal_zzke.zzbyh != null) {
            zzu.zzgb().zzl(com_google_android_gms_internal_zzke.zzbyh);
        }
        return this.zzalk.zzfl();
    }

    public boolean zza(@Nullable zzke com_google_android_gms_internal_zzke, zzke com_google_android_gms_internal_zzke2) {
        if (!super.zza(com_google_android_gms_internal_zzke, com_google_android_gms_internal_zzke2)) {
            return false;
        }
        if (!(this.zzall.zzhc() || this.zzall.zzaru == null || com_google_android_gms_internal_zzke2.zzcod == null)) {
            this.zzaln.zza(this.zzall.zzaqz, com_google_android_gms_internal_zzke2, this.zzall.zzaru);
        }
        return true;
    }

    public void zzb(RewardItemParcel rewardItemParcel) {
        if (this.zzall.zzara != null) {
            if (this.zzall.zzara.zzcht != null) {
                zzu.zzfz().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara.zzcht);
            }
            if (this.zzall.zzara.zzchr != null) {
                rewardItemParcel = this.zzall.zzara.zzchr;
            }
        }
        zza(rewardItemParcel);
    }

    protected void zzdx() {
        zzfa();
        super.zzdx();
    }

    protected void zzea() {
        super.zzea();
        this.zzanp = true;
    }

    public void zzee() {
        recordImpression();
        super.zzee();
        if (this.zzall.zzara != null && this.zzall.zzara.zzbyh != null) {
            zzlu zzvr = this.zzall.zzara.zzbyh.zzvr();
            if (zzvr != null) {
                zzvr.zzwo();
            }
        }
    }

    protected boolean zzez() {
        if (!(this.zzall.zzahn instanceof Activity)) {
            return false;
        }
        Window window = ((Activity) this.zzall.zzahn).getWindow();
        if (window == null || window.getDecorView() == null) {
            return false;
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        window.getDecorView().getGlobalVisibleRect(rect, null);
        window.getDecorView().getWindowVisibleDisplayFrame(rect2);
        boolean z = (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) ? false : true;
        return z;
    }

    public void zzfa() {
        zzu.zzgu().zzb(Integer.valueOf(this.zzanq));
        if (this.zzall.zzhc()) {
            this.zzall.zzgz();
            this.zzall.zzara = null;
            this.zzall.zzanx = false;
            this.zzanp = false;
        }
    }

    public void zzfb() {
        if (!(this.zzall.zzara == null || this.zzall.zzara.zzcoi == null)) {
            zzu.zzfz().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara.zzcoi);
        }
        zzeb();
    }

    public void zzg(boolean z) {
        this.zzall.zzanx = z;
    }
}
