package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.ThinAdSizeParcel;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzf;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzcm;
import com.google.android.gms.internal.zzcr;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzkj;
import com.google.android.gms.internal.zzkk;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

@zziy
public abstract class zza extends com.google.android.gms.ads.internal.client.zzu.zza implements com.google.android.gms.ads.internal.client.zza, zzp, com.google.android.gms.ads.internal.request.zza.zza, zzer, com.google.android.gms.internal.zzil.zza, zzkj {
    protected zzdq zzalg;
    protected zzdo zzalh;
    protected zzdo zzali;
    protected boolean zzalj = false;
    protected final zzr zzalk;
    protected final zzv zzall;
    @Nullable
    protected transient AdRequestParcel zzalm;
    protected final zzcm zzaln;
    protected final zzd zzalo;

    zza(zzv com_google_android_gms_ads_internal_zzv, @Nullable zzr com_google_android_gms_ads_internal_zzr, zzd com_google_android_gms_ads_internal_zzd) {
        this.zzall = com_google_android_gms_ads_internal_zzv;
        if (com_google_android_gms_ads_internal_zzr == null) {
            com_google_android_gms_ads_internal_zzr = new zzr(this);
        }
        this.zzalk = com_google_android_gms_ads_internal_zzr;
        this.zzalo = com_google_android_gms_ads_internal_zzd;
        zzu.zzfz().zzad(this.zzall.zzahn);
        zzu.zzgd().zzb(this.zzall.zzahn, this.zzall.zzaqv);
        zzu.zzge().initialize(this.zzall.zzahn);
        this.zzaln = zzu.zzgd().zztx();
        zzu.zzgc().initialize(this.zzall.zzahn);
        zzdq();
    }

    private AdRequestParcel zza(AdRequestParcel adRequestParcel) {
        return (!zzi.zzcm(this.zzall.zzahn) || adRequestParcel.zzawl == null) ? adRequestParcel : new zzf(adRequestParcel).zza(null).zzja();
    }

    private TimerTask zza(final Timer timer, final CountDownLatch countDownLatch) {
        return new TimerTask(this) {
            final /* synthetic */ zza zzalr;

            public void run() {
                if (((long) ((Integer) zzdi.zzbgi.get()).intValue()) != countDownLatch.getCount()) {
                    zzb.zzdd("Stopping method tracing");
                    Debug.stopMethodTracing();
                    if (countDownLatch.getCount() == 0) {
                        timer.cancel();
                        return;
                    }
                }
                String concat = String.valueOf(this.zzalr.zzall.zzahn.getPackageName()).concat("_adsTrace_");
                try {
                    zzb.zzdd("Starting method tracing");
                    countDownLatch.countDown();
                    Debug.startMethodTracing(new StringBuilder(String.valueOf(concat).length() + 20).append(concat).append(zzu.zzgf().currentTimeMillis()).toString(), ((Integer) zzdi.zzbgj.get()).intValue());
                } catch (Throwable e) {
                    zzb.zzd("Exception occurred while starting method tracing.", e);
                }
            }
        };
    }

    private void zzd(zzke com_google_android_gms_internal_zzke) {
        if (zzu.zzgh().zzuu() && !com_google_android_gms_internal_zzke.zzcoo && !TextUtils.isEmpty(com_google_android_gms_internal_zzke.zzchy)) {
            zzb.zzdd("Sending troubleshooting signals to the server.");
            zzu.zzgh().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, com_google_android_gms_internal_zzke.zzchy, this.zzall.zzaqt);
            com_google_android_gms_internal_zzke.zzcoo = true;
        }
    }

    private void zzdq() {
        if (((Boolean) zzdi.zzbgg.get()).booleanValue()) {
            Timer timer = new Timer();
            timer.schedule(zza(timer, new CountDownLatch(((Integer) zzdi.zzbgi.get()).intValue())), 0, ((Long) zzdi.zzbgh.get()).longValue());
        }
    }

    public void destroy() {
        zzac.zzhq("destroy must be called on the main UI thread.");
        this.zzalk.cancel();
        this.zzaln.zzk(this.zzall.zzara);
        this.zzall.destroy();
    }

    public boolean isLoading() {
        return this.zzalj;
    }

    public boolean isReady() {
        zzac.zzhq("isLoaded must be called on the main UI thread.");
        return this.zzall.zzaqx == null && this.zzall.zzaqy == null && this.zzall.zzara != null;
    }

    public void onAdClicked() {
        if (this.zzall.zzara == null) {
            zzb.zzdf("Ad state was null when trying to ping click URLs.");
            return;
        }
        zzb.zzdd("Pinging click URLs.");
        if (this.zzall.zzarc != null) {
            this.zzall.zzarc.zzta();
        }
        if (this.zzall.zzara.zzbsd != null) {
            zzu.zzfz().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara.zzbsd);
        }
        if (this.zzall.zzard != null) {
            try {
                this.zzall.zzard.onAdClicked();
            } catch (Throwable e) {
                zzb.zzd("Could not notify onAdClicked event.", e);
            }
        }
    }

    public void onAppEvent(String str, @Nullable String str2) {
        if (this.zzall.zzarf != null) {
            try {
                this.zzall.zzarf.onAppEvent(str, str2);
            } catch (Throwable e) {
                zzb.zzd("Could not call the AppEventListener.", e);
            }
        }
    }

    public void pause() {
        zzac.zzhq("pause must be called on the main UI thread.");
    }

    public void resume() {
        zzac.zzhq("resume must be called on the main UI thread.");
    }

    public void setManualImpressionsEnabled(boolean z) {
        throw new UnsupportedOperationException("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }

    public void setUserId(String str) {
        zzb.zzdf("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }

    public void stopLoading() {
        zzac.zzhq("stopLoading must be called on the main UI thread.");
        this.zzalj = false;
        this.zzall.zzi(true);
    }

    Bundle zza(@Nullable zzcu com_google_android_gms_internal_zzcu) {
        if (com_google_android_gms_internal_zzcu == null) {
            return null;
        }
        String zzie;
        String zzif;
        if (com_google_android_gms_internal_zzcu.zziq()) {
            com_google_android_gms_internal_zzcu.wakeup();
        }
        zzcr zzio = com_google_android_gms_internal_zzcu.zzio();
        if (zzio != null) {
            zzie = zzio.zzie();
            zzif = zzio.zzif();
            String str = "In AdManager: loadAd, ";
            String valueOf = String.valueOf(zzio.toString());
            zzb.zzdd(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            if (zzie != null) {
                zzu.zzgd().zzct(zzie);
            }
        } else {
            zzif = null;
            zzie = zzu.zzgd().zztq();
        }
        if (zzie == null) {
            return null;
        }
        Bundle bundle = new Bundle(1);
        bundle.putString("fingerprint", zzie);
        if (zzie.equals(zzif)) {
            return bundle;
        }
        bundle.putString("v_fp", zzif);
        return bundle;
    }

    public void zza(AdSizeParcel adSizeParcel) {
        zzac.zzhq("setAdSize must be called on the main UI thread.");
        this.zzall.zzaqz = adSizeParcel;
        if (!(this.zzall.zzara == null || this.zzall.zzara.zzbyh == null || this.zzall.zzarv != 0)) {
            this.zzall.zzara.zzbyh.zza(adSizeParcel);
        }
        if (this.zzall.zzaqw != null) {
            if (this.zzall.zzaqw.getChildCount() > 1) {
                this.zzall.zzaqw.removeView(this.zzall.zzaqw.getNextView());
            }
            this.zzall.zzaqw.setMinimumWidth(adSizeParcel.widthPixels);
            this.zzall.zzaqw.setMinimumHeight(adSizeParcel.heightPixels);
            this.zzall.zzaqw.requestLayout();
        }
    }

    public void zza(@Nullable VideoOptionsParcel videoOptionsParcel) {
        zzac.zzhq("setVideoOptions must be called on the main UI thread.");
        this.zzall.zzaro = videoOptionsParcel;
    }

    public void zza(com.google.android.gms.ads.internal.client.zzp com_google_android_gms_ads_internal_client_zzp) {
        zzac.zzhq("setAdListener must be called on the main UI thread.");
        this.zzall.zzard = com_google_android_gms_ads_internal_client_zzp;
    }

    public void zza(zzq com_google_android_gms_ads_internal_client_zzq) {
        zzac.zzhq("setAdListener must be called on the main UI thread.");
        this.zzall.zzare = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zza(zzw com_google_android_gms_ads_internal_client_zzw) {
        zzac.zzhq("setAppEventListener must be called on the main UI thread.");
        this.zzall.zzarf = com_google_android_gms_ads_internal_client_zzw;
    }

    public void zza(zzy com_google_android_gms_ads_internal_client_zzy) {
        zzac.zzhq("setCorrelationIdProvider must be called on the main UI thread");
        this.zzall.zzarg = com_google_android_gms_ads_internal_client_zzy;
    }

    public void zza(zzd com_google_android_gms_ads_internal_reward_client_zzd) {
        zzac.zzhq("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zzall.zzarq = com_google_android_gms_ads_internal_reward_client_zzd;
    }

    protected void zza(@Nullable RewardItemParcel rewardItemParcel) {
        if (this.zzall.zzarq != null) {
            try {
                String str = "";
                int i = 0;
                if (rewardItemParcel != null) {
                    str = rewardItemParcel.type;
                    i = rewardItemParcel.zzcny;
                }
                this.zzall.zzarq.zza(new zzjp(str, i));
            } catch (Throwable e) {
                zzb.zzd("Could not call RewardedVideoAdListener.onRewarded().", e);
            }
        }
    }

    public void zza(zzdu com_google_android_gms_internal_zzdu) {
        throw new IllegalStateException("setOnCustomRenderedAdLoadedListener is not supported for current ad type");
    }

    public void zza(zzhx com_google_android_gms_internal_zzhx) {
        throw new IllegalStateException("setInAppPurchaseListener is not supported for current ad type");
    }

    public void zza(zzib com_google_android_gms_internal_zzib, String str) {
        throw new IllegalStateException("setPlayStorePurchaseParams is not supported for current ad type");
    }

    public void zza(com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza) {
        if (!(com_google_android_gms_internal_zzke_zza.zzcop.zzchg == -1 || TextUtils.isEmpty(com_google_android_gms_internal_zzke_zza.zzcop.zzchp))) {
            long zzu = zzu(com_google_android_gms_internal_zzke_zza.zzcop.zzchp);
            if (zzu != -1) {
                zzdo zzc = this.zzalg.zzc(zzu + com_google_android_gms_internal_zzke_zza.zzcop.zzchg);
                this.zzalg.zza(zzc, "stc");
            }
        }
        this.zzalg.zzav(com_google_android_gms_internal_zzke_zza.zzcop.zzchp);
        this.zzalg.zza(this.zzalh, "arf");
        this.zzali = this.zzalg.zzla();
        this.zzalg.zzh("gqi", com_google_android_gms_internal_zzke_zza.zzcop.zzchq);
        this.zzall.zzaqx = null;
        this.zzall.zzarb = com_google_android_gms_internal_zzke_zza;
        zza(com_google_android_gms_internal_zzke_zza, this.zzalg);
    }

    protected abstract void zza(com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, zzdq com_google_android_gms_internal_zzdq);

    public void zza(HashSet<zzkf> hashSet) {
        this.zzall.zza(hashSet);
    }

    protected abstract boolean zza(AdRequestParcel adRequestParcel, zzdq com_google_android_gms_internal_zzdq);

    boolean zza(zzke com_google_android_gms_internal_zzke) {
        return false;
    }

    protected abstract boolean zza(@Nullable zzke com_google_android_gms_internal_zzke, zzke com_google_android_gms_internal_zzke2);

    protected void zzb(View view) {
        com.google.android.gms.ads.internal.zzv.zza com_google_android_gms_ads_internal_zzv_zza = this.zzall.zzaqw;
        if (com_google_android_gms_ads_internal_zzv_zza != null) {
            com_google_android_gms_ads_internal_zzv_zza.addView(view, zzu.zzgb().zzup());
        }
    }

    public void zzb(zzke com_google_android_gms_internal_zzke) {
        this.zzalg.zza(this.zzali, "awr");
        this.zzall.zzaqy = null;
        if (!(com_google_android_gms_internal_zzke.errorCode == -2 || com_google_android_gms_internal_zzke.errorCode == 3)) {
            zzu.zzgd().zzb(this.zzall.zzgy());
        }
        if (com_google_android_gms_internal_zzke.errorCode == -1) {
            this.zzalj = false;
            return;
        }
        if (zza(com_google_android_gms_internal_zzke)) {
            zzb.zzdd("Ad refresh scheduled.");
        }
        if (com_google_android_gms_internal_zzke.errorCode != -2) {
            zzh(com_google_android_gms_internal_zzke.errorCode);
            return;
        }
        if (this.zzall.zzart == null) {
            this.zzall.zzart = new zzkk(this.zzall.zzaqt);
        }
        this.zzaln.zzj(this.zzall.zzara);
        if (zza(this.zzall.zzara, com_google_android_gms_internal_zzke)) {
            this.zzall.zzara = com_google_android_gms_internal_zzke;
            this.zzall.zzhh();
            this.zzalg.zzh("is_mraid", this.zzall.zzara.zzib() ? "1" : "0");
            this.zzalg.zzh("is_mediation", this.zzall.zzara.zzchc ? "1" : "0");
            if (!(this.zzall.zzara.zzbyh == null || this.zzall.zzara.zzbyh.zzvr() == null)) {
                this.zzalg.zzh("is_delay_pl", this.zzall.zzara.zzbyh.zzvr().zzwm() ? "1" : "0");
            }
            this.zzalg.zza(this.zzalh, "ttc");
            if (zzu.zzgd().zztm() != null) {
                zzu.zzgd().zztm().zza(this.zzalg);
            }
            if (this.zzall.zzhc()) {
                zzea();
            }
        }
        if (com_google_android_gms_internal_zzke.zzbsg != null) {
            zzu.zzfz().zza(this.zzall.zzahn, com_google_android_gms_internal_zzke.zzbsg);
        }
    }

    public boolean zzb(AdRequestParcel adRequestParcel) {
        zzac.zzhq("loadAd must be called on the main UI thread.");
        zzu.zzge().zzit();
        if (((Boolean) zzdi.zzbdg.get()).booleanValue()) {
            AdRequestParcel.zzj(adRequestParcel);
        }
        AdRequestParcel zza = zza(adRequestParcel);
        if (this.zzall.zzaqx == null && this.zzall.zzaqy == null) {
            zzb.zzde("Starting ad request.");
            zzdr();
            this.zzalh = this.zzalg.zzla();
            if (!zza.zzawg) {
                String valueOf = String.valueOf(zzm.zzjr().zzar(this.zzall.zzahn));
                zzb.zzde(new StringBuilder(String.valueOf(valueOf).length() + 71).append("Use AdRequest.Builder.addTestDevice(\"").append(valueOf).append("\") to get test ads on this device.").toString());
            }
            this.zzalk.zzg(zza);
            this.zzalj = zza(zza, this.zzalg);
            return this.zzalj;
        }
        if (this.zzalm != null) {
            zzb.zzdf("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
        } else {
            zzb.zzdf("Loading already in progress, saving this object for future refreshes.");
        }
        this.zzalm = zza;
        return false;
    }

    protected void zzc(@Nullable zzke com_google_android_gms_internal_zzke) {
        if (com_google_android_gms_internal_zzke == null) {
            zzb.zzdf("Ad state was null when trying to ping impression URLs.");
            return;
        }
        zzb.zzdd("Pinging Impression URLs.");
        if (this.zzall.zzarc != null) {
            this.zzall.zzarc.zzsz();
        }
        if (com_google_android_gms_internal_zzke.zzbse != null && !com_google_android_gms_internal_zzke.zzcom) {
            zzu.zzfz().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, com_google_android_gms_internal_zzke.zzbse);
            com_google_android_gms_internal_zzke.zzcom = true;
            zzd(com_google_android_gms_internal_zzke);
        }
    }

    protected boolean zzc(AdRequestParcel adRequestParcel) {
        if (this.zzall.zzaqw == null) {
            return false;
        }
        ViewParent parent = this.zzall.zzaqw.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        View view = (View) parent;
        return zzu.zzfz().zza(view, view.getContext());
    }

    public void zzd(AdRequestParcel adRequestParcel) {
        if (zzc(adRequestParcel)) {
            zzb(adRequestParcel);
            return;
        }
        zzb.zzde("Ad is not visible. Not refreshing ad.");
        this.zzalk.zzh(adRequestParcel);
    }

    public zzd zzdp() {
        return this.zzalo;
    }

    public void zzdr() {
        this.zzalg = new zzdq(((Boolean) zzdi.zzbca.get()).booleanValue(), "load_ad", this.zzall.zzaqz.zzaxi);
        this.zzalh = new zzdo(-1, null, null);
        this.zzali = new zzdo(-1, null, null);
    }

    public com.google.android.gms.dynamic.zzd zzds() {
        zzac.zzhq("getAdFrame must be called on the main UI thread.");
        return zze.zzac(this.zzall.zzaqw);
    }

    @Nullable
    public AdSizeParcel zzdt() {
        zzac.zzhq("getAdSize must be called on the main UI thread.");
        return this.zzall.zzaqz == null ? null : new ThinAdSizeParcel(this.zzall.zzaqz);
    }

    public void zzdu() {
        zzdy();
    }

    public void zzdv() {
        zzac.zzhq("recordManualImpression must be called on the main UI thread.");
        if (this.zzall.zzara == null) {
            zzb.zzdf("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        zzb.zzdd("Pinging manual tracking URLs.");
        if (this.zzall.zzara.zzche != null && !this.zzall.zzara.zzcon) {
            zzu.zzfz().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara.zzche);
            this.zzall.zzara.zzcon = true;
            zzd(this.zzall.zzara);
        }
    }

    public zzab zzdw() {
        return null;
    }

    protected void zzdx() {
        zzb.zzde("Ad closing.");
        if (this.zzall.zzare != null) {
            try {
                this.zzall.zzare.onAdClosed();
            } catch (Throwable e) {
                zzb.zzd("Could not call AdListener.onAdClosed().", e);
            }
        }
        if (this.zzall.zzarq != null) {
            try {
                this.zzall.zzarq.onRewardedVideoAdClosed();
            } catch (Throwable e2) {
                zzb.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdClosed().", e2);
            }
        }
    }

    protected void zzdy() {
        zzb.zzde("Ad leaving application.");
        if (this.zzall.zzare != null) {
            try {
                this.zzall.zzare.onAdLeftApplication();
            } catch (Throwable e) {
                zzb.zzd("Could not call AdListener.onAdLeftApplication().", e);
            }
        }
        if (this.zzall.zzarq != null) {
            try {
                this.zzall.zzarq.onRewardedVideoAdLeftApplication();
            } catch (Throwable e2) {
                zzb.zzd("Could not call  RewardedVideoAdListener.onRewardedVideoAdLeftApplication().", e2);
            }
        }
    }

    protected void zzdz() {
        zzb.zzde("Ad opening.");
        if (this.zzall.zzare != null) {
            try {
                this.zzall.zzare.onAdOpened();
            } catch (Throwable e) {
                zzb.zzd("Could not call AdListener.onAdOpened().", e);
            }
        }
        if (this.zzall.zzarq != null) {
            try {
                this.zzall.zzarq.onRewardedVideoAdOpened();
            } catch (Throwable e2) {
                zzb.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdOpened().", e2);
            }
        }
    }

    protected void zzea() {
        zzb.zzde("Ad finished loading.");
        this.zzalj = false;
        if (this.zzall.zzare != null) {
            try {
                this.zzall.zzare.onAdLoaded();
            } catch (Throwable e) {
                zzb.zzd("Could not call AdListener.onAdLoaded().", e);
            }
        }
        if (this.zzall.zzarq != null) {
            try {
                this.zzall.zzarq.onRewardedVideoAdLoaded();
            } catch (Throwable e2) {
                zzb.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdLoaded().", e2);
            }
        }
    }

    protected void zzeb() {
        if (this.zzall.zzarq != null) {
            try {
                this.zzall.zzarq.onRewardedVideoStarted();
            } catch (Throwable e) {
                zzb.zzd("Could not call RewardedVideoAdListener.onVideoStarted().", e);
            }
        }
    }

    protected void zzh(int i) {
        zzb.zzdf("Failed to load ad: " + i);
        this.zzalj = false;
        if (this.zzall.zzare != null) {
            try {
                this.zzall.zzare.onAdFailedToLoad(i);
            } catch (Throwable e) {
                zzb.zzd("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
        if (this.zzall.zzarq != null) {
            try {
                this.zzall.zzarq.onRewardedVideoAdFailedToLoad(i);
            } catch (Throwable e2) {
                zzb.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdFailedToLoad().", e2);
            }
        }
    }

    long zzu(String str) {
        int indexOf = str.indexOf("ufe");
        int indexOf2 = str.indexOf(44, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        try {
            return Long.parseLong(str.substring(indexOf + 4, indexOf2));
        } catch (IndexOutOfBoundsException e) {
            zzb.zzdf("Invalid index for Url fetch time in CSI latency info.");
            return -1;
        } catch (NumberFormatException e2) {
            zzb.zzdf("Cannot find valid format of Url fetch time in CSI latency info.");
            return -1;
        }
    }
}
