package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.purchase.GInAppPurchaseManagerInfoParcel;
import com.google.android.gms.ads.internal.purchase.zzc;
import com.google.android.gms.ads.internal.purchase.zzd;
import com.google.android.gms.ads.internal.purchase.zzf;
import com.google.android.gms.ads.internal.purchase.zzj;
import com.google.android.gms.ads.internal.purchase.zzk;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza;
import com.google.android.gms.ads.internal.request.CapabilityParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzgi;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zzhu;
import com.google.android.gms.internal.zzhw;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzkg;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzlt;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

@zziy
public abstract class zzb extends zza implements zzg, zzj, zzs, zzex, zzgi {
    private final Messenger mMessenger;
    protected final zzgq zzals;
    protected transient boolean zzalt;

    public zzb(Context context, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        this(new zzv(context, adSizeParcel, str, versionInfoParcel), com_google_android_gms_internal_zzgq, null, com_google_android_gms_ads_internal_zzd);
    }

    protected zzb(zzv com_google_android_gms_ads_internal_zzv, zzgq com_google_android_gms_internal_zzgq, @Nullable zzr com_google_android_gms_ads_internal_zzr, zzd com_google_android_gms_ads_internal_zzd) {
        super(com_google_android_gms_ads_internal_zzv, com_google_android_gms_ads_internal_zzr, com_google_android_gms_ads_internal_zzd);
        this.zzals = com_google_android_gms_internal_zzgq;
        this.mMessenger = new Messenger(new zzhu(this.zzall.zzahn));
        this.zzalt = false;
    }

    private zza zza(AdRequestParcel adRequestParcel, Bundle bundle, zzkg com_google_android_gms_internal_zzkg) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo = this.zzall.zzahn.getApplicationInfo();
        try {
            packageInfo = this.zzall.zzahn.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        DisplayMetrics displayMetrics = this.zzall.zzahn.getResources().getDisplayMetrics();
        Bundle bundle2 = null;
        if (!(this.zzall.zzaqw == null || this.zzall.zzaqw.getParent() == null)) {
            int[] iArr = new int[2];
            this.zzall.zzaqw.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            int width = this.zzall.zzaqw.getWidth();
            int height = this.zzall.zzaqw.getHeight();
            int i3 = 0;
            if (this.zzall.zzaqw.isShown() && i + width > 0 && i2 + height > 0 && i <= displayMetrics.widthPixels && i2 <= displayMetrics.heightPixels) {
                i3 = 1;
            }
            bundle2 = new Bundle(5);
            bundle2.putInt("x", i);
            bundle2.putInt("y", i2);
            bundle2.putInt("width", width);
            bundle2.putInt("height", height);
            bundle2.putInt("visible", i3);
        }
        String zztk = zzu.zzgd().zztk();
        this.zzall.zzarc = new zzkf(zztk, this.zzall.zzaqt);
        this.zzall.zzarc.zzt(adRequestParcel);
        String zza = zzu.zzfz().zza(this.zzall.zzahn, this.zzall.zzaqw, this.zzall.zzaqz);
        long j = 0;
        if (this.zzall.zzarg != null) {
            try {
                j = this.zzall.zzarg.getValue();
            } catch (RemoteException e2) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Cannot get correlation id, default to 0.");
            }
        }
        String uuid = UUID.randomUUID().toString();
        Bundle zza2 = zzu.zzgd().zza(this.zzall.zzahn, this, zztk);
        List arrayList = new ArrayList();
        for (i = 0; i < this.zzall.zzarm.size(); i++) {
            arrayList.add((String) this.zzall.zzarm.keyAt(i));
        }
        boolean z = this.zzall.zzarh != null;
        boolean z2 = this.zzall.zzari != null && zzu.zzgd().zzty();
        boolean zzr = this.zzalo.zzame.zzr(this.zzall.zzahn);
        String str = "";
        if (((Boolean) zzdi.zzbhh.get()).booleanValue()) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Getting webview cookie from CookieManager.");
            CookieManager zzao = zzu.zzgb().zzao(this.zzall.zzahn);
            if (zzao != null) {
                str = zzao.getCookie("googleads.g.doubleclick.net");
            }
        }
        String str2 = null;
        if (com_google_android_gms_internal_zzkg != null) {
            str2 = com_google_android_gms_internal_zzkg.zzth();
        }
        return new zza(bundle2, adRequestParcel, this.zzall.zzaqz, this.zzall.zzaqt, applicationInfo, packageInfo, zztk, zzu.zzgd().getSessionId(), this.zzall.zzaqv, zza2, this.zzall.zzarr, arrayList, bundle, zzu.zzgd().zzto(), this.mMessenger, displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.density, zza, j, uuid, zzdi.zzkr(), this.zzall.zzaqs, this.zzall.zzarn, new CapabilityParcel(z, z2, zzr), this.zzall.zzhg(), zzu.zzfz().zzfe(), zzu.zzfz().zzfg(), zzu.zzfz().zzam(this.zzall.zzahn), zzu.zzfz().zzn(this.zzall.zzaqw), this.zzall.zzahn instanceof Activity, zzu.zzgd().zzts(), str, str2, zzu.zzgd().zztv(), zzu.zzgw().zzmi(), zzu.zzfz().zzul(), zzu.zzgh().zzut());
    }

    public String getMediationAdapterClassName() {
        return this.zzall.zzara == null ? null : this.zzall.zzara.zzbtg;
    }

    public void onAdClicked() {
        if (this.zzall.zzara == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (!(this.zzall.zzara.zzcof == null || this.zzall.zzara.zzcof.zzbsd == null)) {
            zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara, this.zzall.zzaqt, false, this.zzall.zzara.zzcof.zzbsd);
        }
        if (!(this.zzall.zzara.zzbte == null || this.zzall.zzara.zzbte.zzbrq == null)) {
            zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara, this.zzall.zzaqt, false, this.zzall.zzara.zzbte.zzbrq);
        }
        super.onAdClicked();
    }

    public void onPause() {
        this.zzaln.zzl(this.zzall.zzara);
    }

    public void onResume() {
        this.zzaln.zzm(this.zzall.zzara);
    }

    public void pause() {
        zzac.zzhq("pause must be called on the main UI thread.");
        if (!(this.zzall.zzara == null || this.zzall.zzara.zzbyh == null || !this.zzall.zzhc())) {
            zzu.zzgb().zzl(this.zzall.zzara.zzbyh);
        }
        if (!(this.zzall.zzara == null || this.zzall.zzara.zzbtf == null)) {
            try {
                this.zzall.zzara.zzbtf.pause();
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Could not pause mediation adapter.");
            }
        }
        this.zzaln.zzl(this.zzall.zzara);
        this.zzalk.pause();
    }

    public void recordImpression() {
        zza(this.zzall.zzara, false);
    }

    public void resume() {
        zzac.zzhq("resume must be called on the main UI thread.");
        zzlt com_google_android_gms_internal_zzlt = null;
        if (!(this.zzall.zzara == null || this.zzall.zzara.zzbyh == null)) {
            com_google_android_gms_internal_zzlt = this.zzall.zzara.zzbyh;
        }
        if (com_google_android_gms_internal_zzlt != null && this.zzall.zzhc()) {
            zzu.zzgb().zzm(this.zzall.zzara.zzbyh);
        }
        if (!(this.zzall.zzara == null || this.zzall.zzara.zzbtf == null)) {
            try {
                this.zzall.zzara.zzbtf.resume();
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Could not resume mediation adapter.");
            }
        }
        if (com_google_android_gms_internal_zzlt == null || !com_google_android_gms_internal_zzlt.zzvx()) {
            this.zzalk.resume();
        }
        this.zzaln.zzm(this.zzall.zzara);
    }

    public void showInterstitial() {
        throw new IllegalStateException("showInterstitial is not supported for current ad type");
    }

    public void zza(zzhx com_google_android_gms_internal_zzhx) {
        zzac.zzhq("setInAppPurchaseListener must be called on the main UI thread.");
        this.zzall.zzarh = com_google_android_gms_internal_zzhx;
    }

    public void zza(zzib com_google_android_gms_internal_zzib, @Nullable String str) {
        zzac.zzhq("setPlayStorePurchaseParams must be called on the main UI thread.");
        this.zzall.zzars = new zzk(str);
        this.zzall.zzari = com_google_android_gms_internal_zzib;
        if (!zzu.zzgd().zztn() && com_google_android_gms_internal_zzib != null) {
            Future future = (Future) new zzc(this.zzall.zzahn, this.zzall.zzari, this.zzall.zzars).zzqw();
        }
    }

    protected void zza(@Nullable zzke com_google_android_gms_internal_zzke, boolean z) {
        if (com_google_android_gms_internal_zzke == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Ad state was null when trying to ping impression URLs.");
            return;
        }
        super.zzc(com_google_android_gms_internal_zzke);
        if (!(com_google_android_gms_internal_zzke.zzcof == null || com_google_android_gms_internal_zzke.zzcof.zzbse == null)) {
            zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, com_google_android_gms_internal_zzke, this.zzall.zzaqt, z, com_google_android_gms_internal_zzke.zzcof.zzbse);
        }
        if (com_google_android_gms_internal_zzke.zzbte != null && com_google_android_gms_internal_zzke.zzbte.zzbrr != null) {
            zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, com_google_android_gms_internal_zzke, this.zzall.zzaqt, z, com_google_android_gms_internal_zzke.zzbte.zzbrr);
        }
    }

    public void zza(String str, ArrayList<String> arrayList) {
        zzhw com_google_android_gms_ads_internal_purchase_zzd = new zzd(str, arrayList, this.zzall.zzahn, this.zzall.zzaqv.zzcs);
        if (this.zzall.zzarh == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("InAppPurchaseListener is not set. Try to launch default purchase flow.");
            if (!zzm.zzjr().zzas(this.zzall.zzahn)) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Google Play Service unavailable, cannot launch default purchase flow.");
                return;
            } else if (this.zzall.zzari == null) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("PlayStorePurchaseListener is not set.");
                return;
            } else if (this.zzall.zzars == null) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("PlayStorePurchaseVerifier is not initialized.");
                return;
            } else if (this.zzall.zzarw) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("An in-app purchase request is already in progress, abort");
                return;
            } else {
                this.zzall.zzarw = true;
                try {
                    if (this.zzall.zzari.isValidPurchase(str)) {
                        zzu.zzgn().zza(this.zzall.zzahn, this.zzall.zzaqv.zzctu, new GInAppPurchaseManagerInfoParcel(this.zzall.zzahn, this.zzall.zzars, com_google_android_gms_ads_internal_purchase_zzd, this));
                        return;
                    } else {
                        this.zzall.zzarw = false;
                        return;
                    }
                } catch (RemoteException e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzdf("Could not start In-App purchase.");
                    this.zzall.zzarw = false;
                    return;
                }
            }
        }
        try {
            this.zzall.zzarh.zza(com_google_android_gms_ads_internal_purchase_zzd);
        } catch (RemoteException e2) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Could not start In-App purchase.");
        }
    }

    public void zza(String str, boolean z, int i, final Intent intent, zzf com_google_android_gms_ads_internal_purchase_zzf) {
        try {
            if (this.zzall.zzari != null) {
                this.zzall.zzari.zza(new com.google.android.gms.ads.internal.purchase.zzg(this.zzall.zzahn, str, z, i, intent, com_google_android_gms_ads_internal_purchase_zzf));
            }
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Fail to invoke PlayStorePurchaseListener.");
        }
        zzkr.zzcrf.postDelayed(new Runnable(this) {
            final /* synthetic */ zzb zzalv;

            public void run() {
                int zzd = zzu.zzgn().zzd(intent);
                zzu.zzgn();
                if (!(zzd != 0 || this.zzalv.zzall.zzara == null || this.zzalv.zzall.zzara.zzbyh == null || this.zzalv.zzall.zzara.zzbyh.zzvp() == null)) {
                    this.zzalv.zzall.zzara.zzbyh.zzvp().close();
                }
                this.zzalv.zzall.zzarw = false;
            }
        }, 500);
    }

    public boolean zza(AdRequestParcel adRequestParcel, zzdq com_google_android_gms_internal_zzdq) {
        String str = null;
        if (!zzec()) {
            return false;
        }
        zzkg zztw;
        Bundle zza = zza(zzu.zzgd().zzaa(this.zzall.zzahn));
        this.zzalk.cancel();
        this.zzall.zzarv = 0;
        if (((Boolean) zzdi.zzbgq.get()).booleanValue()) {
            zztw = zzu.zzgd().zztw();
            zzg zzgv = zzu.zzgv();
            Context context = this.zzall.zzahn;
            VersionInfoParcel versionInfoParcel = this.zzall.zzaqv;
            if (zztw != null) {
                str = zztw.zzti();
            }
            zzgv.zza(context, versionInfoParcel, false, zztw, str, this.zzall.zzaqt);
        } else {
            zztw = null;
        }
        zza zza2 = zza(adRequestParcel, zza, zztw);
        com_google_android_gms_internal_zzdq.zzh("seq_num", zza2.zzcfx);
        com_google_android_gms_internal_zzdq.zzh("request_id", zza2.zzcgj);
        com_google_android_gms_internal_zzdq.zzh("session_id", zza2.zzcfy);
        if (zza2.zzcfv != null) {
            com_google_android_gms_internal_zzdq.zzh("app_version", String.valueOf(zza2.zzcfv.versionCode));
        }
        this.zzall.zzaqx = zzu.zzfv().zza(this.zzall.zzahn, zza2, this.zzall.zzaqu, this);
        return true;
    }

    protected boolean zza(AdRequestParcel adRequestParcel, zzke com_google_android_gms_internal_zzke, boolean z) {
        if (!z && this.zzall.zzhc()) {
            if (com_google_android_gms_internal_zzke.zzbsj > 0) {
                this.zzalk.zza(adRequestParcel, com_google_android_gms_internal_zzke.zzbsj);
            } else if (com_google_android_gms_internal_zzke.zzcof != null && com_google_android_gms_internal_zzke.zzcof.zzbsj > 0) {
                this.zzalk.zza(adRequestParcel, com_google_android_gms_internal_zzke.zzcof.zzbsj);
            } else if (!com_google_android_gms_internal_zzke.zzchc && com_google_android_gms_internal_zzke.errorCode == 2) {
                this.zzalk.zzh(adRequestParcel);
            }
        }
        return this.zzalk.zzfl();
    }

    boolean zza(zzke com_google_android_gms_internal_zzke) {
        AdRequestParcel adRequestParcel;
        boolean z = false;
        if (this.zzalm != null) {
            adRequestParcel = this.zzalm;
            this.zzalm = null;
        } else {
            adRequestParcel = com_google_android_gms_internal_zzke.zzcfu;
            if (adRequestParcel.extras != null) {
                z = adRequestParcel.extras.getBoolean("_noRefresh", false);
            }
        }
        return zza(adRequestParcel, com_google_android_gms_internal_zzke, z);
    }

    protected boolean zza(@Nullable zzke com_google_android_gms_internal_zzke, zzke com_google_android_gms_internal_zzke2) {
        int i;
        int i2 = 0;
        if (!(com_google_android_gms_internal_zzke == null || com_google_android_gms_internal_zzke.zzbth == null)) {
            com_google_android_gms_internal_zzke.zzbth.zza(null);
        }
        if (com_google_android_gms_internal_zzke2.zzbth != null) {
            com_google_android_gms_internal_zzke2.zzbth.zza((zzgi) this);
        }
        if (com_google_android_gms_internal_zzke2.zzcof != null) {
            i = com_google_android_gms_internal_zzke2.zzcof.zzbsp;
            i2 = com_google_android_gms_internal_zzke2.zzcof.zzbsq;
        } else {
            i = 0;
        }
        this.zzall.zzart.zzh(i, i2);
        return true;
    }

    public void zzb(zzke com_google_android_gms_internal_zzke) {
        super.zzb(com_google_android_gms_internal_zzke);
        if (com_google_android_gms_internal_zzke.zzbte != null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Pinging network fill URLs.");
            zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, com_google_android_gms_internal_zzke, this.zzall.zzaqt, false, com_google_android_gms_internal_zzke.zzbte.zzbrs);
            if (!(com_google_android_gms_internal_zzke.zzcof == null || com_google_android_gms_internal_zzke.zzcof.zzbsg == null || com_google_android_gms_internal_zzke.zzcof.zzbsg.size() <= 0)) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdd("Pinging urls remotely");
                zzu.zzfz().zza(this.zzall.zzahn, com_google_android_gms_internal_zzke.zzcof.zzbsg);
            }
        }
        if (com_google_android_gms_internal_zzke.errorCode == 3 && com_google_android_gms_internal_zzke.zzcof != null && com_google_android_gms_internal_zzke.zzcof.zzbsf != null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Pinging no fill URLs.");
            zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, com_google_android_gms_internal_zzke, this.zzall.zzaqt, false, com_google_android_gms_internal_zzke.zzcof.zzbsf);
        }
    }

    protected boolean zzc(AdRequestParcel adRequestParcel) {
        return super.zzc(adRequestParcel) && !this.zzalt;
    }

    protected boolean zzec() {
        return zzu.zzfz().zza(this.zzall.zzahn.getPackageManager(), this.zzall.zzahn.getPackageName(), "android.permission.INTERNET") && zzu.zzfz().zzac(this.zzall.zzahn);
    }

    public void zzed() {
        this.zzaln.zzj(this.zzall.zzara);
        this.zzalt = false;
        zzdx();
        this.zzall.zzarc.zztb();
    }

    public void zzee() {
        this.zzalt = true;
        zzdz();
    }

    public void zzef() {
        onAdClicked();
    }

    public void zzeg() {
        zzed();
    }

    public void zzeh() {
        zzdu();
    }

    public void zzei() {
        zzee();
    }

    public void zzej() {
        if (this.zzall.zzara != null) {
            String str = this.zzall.zzara.zzbtg;
            com.google.android.gms.ads.internal.util.client.zzb.zzdf(new StringBuilder(String.valueOf(str).length() + 74).append("Mediation adapter ").append(str).append(" refreshed, but mediation adapters should never refresh.").toString());
        }
        zza(this.zzall.zzara, true);
        zzea();
    }

    public void zzek() {
        recordImpression();
    }

    public void zzel() {
        zzu.zzfz().runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzb zzalv;

            {
                this.zzalv = r1;
            }

            public void run() {
                this.zzalv.zzalk.pause();
            }
        });
    }

    public void zzem() {
        zzu.zzfz().runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzb zzalv;

            {
                this.zzalv = r1;
            }

            public void run() {
                this.zzalv.zzalk.resume();
            }
        });
    }
}
