package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzke.zza;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@zziy
public class zzjr extends zzb implements zzjv {
    private static final zzgp zzcmu = new zzgp();
    private final Map<String, zzjz> zzcmv = new HashMap();
    private boolean zzcmw;

    public zzjr(Context context, zzd com_google_android_gms_ads_internal_zzd, AdSizeParcel adSizeParcel, zzgq com_google_android_gms_internal_zzgq, VersionInfoParcel versionInfoParcel) {
        super(context, adSizeParcel, null, com_google_android_gms_internal_zzgq, versionInfoParcel, com_google_android_gms_ads_internal_zzd);
    }

    private zza zzd(zza com_google_android_gms_internal_zzke_zza) {
        zzkn.v("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            String jSONObject = zzjc.zzc(com_google_android_gms_internal_zzke_zza.zzcop).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, com_google_android_gms_internal_zzke_zza.zzcix.zzaqt);
            zzgg com_google_android_gms_internal_zzgg = new zzgg(jSONObject, null, Arrays.asList(new String[]{"com.google.ads.mediation.admob.AdMobAdapter"}), null, null, Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList());
            return new zza(com_google_android_gms_internal_zzke_zza.zzcix, com_google_android_gms_internal_zzke_zza.zzcop, new zzgh(Arrays.asList(new zzgg[]{com_google_android_gms_internal_zzgg}), -1, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1, 0, 1, null, 0, -1, -1, false), com_google_android_gms_internal_zzke_zza.zzaqz, com_google_android_gms_internal_zzke_zza.errorCode, com_google_android_gms_internal_zzke_zza.zzcoj, com_google_android_gms_internal_zzke_zza.zzcok, com_google_android_gms_internal_zzke_zza.zzcod);
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to generate ad state for non-mediated rewarded video.", e);
            return zze(com_google_android_gms_internal_zzke_zza);
        }
    }

    private zza zze(zza com_google_android_gms_internal_zzke_zza) {
        return new zza(com_google_android_gms_internal_zzke_zza.zzcix, com_google_android_gms_internal_zzke_zza.zzcop, null, com_google_android_gms_internal_zzke_zza.zzaqz, 0, com_google_android_gms_internal_zzke_zza.zzcoj, com_google_android_gms_internal_zzke_zza.zzcok, com_google_android_gms_internal_zzke_zza.zzcod);
    }

    public void destroy() {
        String valueOf;
        zzac.zzhq("destroy must be called on the main UI thread.");
        for (String valueOf2 : this.zzcmv.keySet()) {
            try {
                zzjz com_google_android_gms_internal_zzjz = (zzjz) this.zzcmv.get(valueOf2);
                if (!(com_google_android_gms_internal_zzjz == null || com_google_android_gms_internal_zzjz.zzsv() == null)) {
                    com_google_android_gms_internal_zzjz.zzsv().destroy();
                }
            } catch (RemoteException e) {
                String str = "Fail to destroy adapter: ";
                valueOf2 = String.valueOf(valueOf2);
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
            }
        }
    }

    public boolean isLoaded() {
        zzac.zzhq("isLoaded must be called on the main UI thread.");
        return this.zzall.zzaqx == null && this.zzall.zzaqy == null && this.zzall.zzara != null && !this.zzcmw;
    }

    public void onContextChanged(@NonNull Context context) {
        for (zzjz zzsv : this.zzcmv.values()) {
            try {
                zzsv.zzsv().zzj(zze.zzac(context));
            } catch (Throwable e) {
                com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public void onRewardedVideoAdClosed() {
        zzdx();
    }

    public void onRewardedVideoAdLeftApplication() {
        zzdy();
    }

    public void onRewardedVideoAdOpened() {
        zza(this.zzall.zzara, false);
        zzdz();
    }

    public void onRewardedVideoStarted() {
        if (!(this.zzall.zzara == null || this.zzall.zzara.zzbte == null)) {
            zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara, this.zzall.zzaqt, false, this.zzall.zzara.zzbte.zzbru);
        }
        zzeb();
    }

    public void pause() {
        zzac.zzhq("pause must be called on the main UI thread.");
        for (String str : this.zzcmv.keySet()) {
            String str2;
            try {
                zzjz com_google_android_gms_internal_zzjz = (zzjz) this.zzcmv.get(str2);
                if (!(com_google_android_gms_internal_zzjz == null || com_google_android_gms_internal_zzjz.zzsv() == null)) {
                    com_google_android_gms_internal_zzjz.zzsv().pause();
                }
            } catch (RemoteException e) {
                String str3 = "Fail to pause adapter: ";
                str2 = String.valueOf(str2);
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(str2.length() != 0 ? str3.concat(str2) : new String(str3));
            }
        }
    }

    public void resume() {
        zzac.zzhq("resume must be called on the main UI thread.");
        for (String str : this.zzcmv.keySet()) {
            String str2;
            try {
                zzjz com_google_android_gms_internal_zzjz = (zzjz) this.zzcmv.get(str2);
                if (!(com_google_android_gms_internal_zzjz == null || com_google_android_gms_internal_zzjz.zzsv() == null)) {
                    com_google_android_gms_internal_zzjz.zzsv().resume();
                }
            } catch (RemoteException e) {
                String str3 = "Fail to resume adapter: ";
                str2 = String.valueOf(str2);
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(str2.length() != 0 ? str3.concat(str2) : new String(str3));
            }
        }
    }

    public void zza(RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel) {
        zzac.zzhq("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty(rewardedVideoAdRequestParcel.zzaqt)) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Invalid ad unit id. Aborting.");
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzjr zzcmx;

                {
                    this.zzcmx = r1;
                }

                public void run() {
                    this.zzcmx.zzh(1);
                }
            });
            return;
        }
        this.zzcmw = false;
        this.zzall.zzaqt = rewardedVideoAdRequestParcel.zzaqt;
        super.zzb(rewardedVideoAdRequestParcel.zzcfu);
    }

    public void zza(final zza com_google_android_gms_internal_zzke_zza, zzdq com_google_android_gms_internal_zzdq) {
        if (com_google_android_gms_internal_zzke_zza.errorCode != -2) {
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzjr zzcmx;

                public void run() {
                    this.zzcmx.zzb(new zzke(com_google_android_gms_internal_zzke_zza, null, null, null, null, null, null, null));
                }
            });
            return;
        }
        this.zzall.zzarb = com_google_android_gms_internal_zzke_zza;
        if (com_google_android_gms_internal_zzke_zza.zzcof == null) {
            this.zzall.zzarb = zzd(com_google_android_gms_internal_zzke_zza);
        }
        this.zzall.zzarv = 0;
        this.zzall.zzaqy = zzu.zzfy().zza(this.zzall.zzahn, this.zzall.zzarb, this);
    }

    protected boolean zza(AdRequestParcel adRequestParcel, zzke com_google_android_gms_internal_zzke, boolean z) {
        return false;
    }

    public boolean zza(zzke com_google_android_gms_internal_zzke, zzke com_google_android_gms_internal_zzke2) {
        return true;
    }

    public void zzc(@Nullable RewardItemParcel rewardItemParcel) {
        if (!(this.zzall.zzara == null || this.zzall.zzara.zzbte == null)) {
            zzu.zzgs().zza(this.zzall.zzahn, this.zzall.zzaqv.zzcs, this.zzall.zzara, this.zzall.zzaqt, false, this.zzall.zzara.zzbte.zzbrv);
        }
        if (!(this.zzall.zzara == null || this.zzall.zzara.zzcof == null || TextUtils.isEmpty(this.zzall.zzara.zzcof.zzbsk))) {
            rewardItemParcel = new RewardItemParcel(this.zzall.zzara.zzcof.zzbsk, this.zzall.zzara.zzcof.zzbsl);
        }
        zza(rewardItemParcel);
    }

    @Nullable
    public zzjz zzcl(String str) {
        zzjz com_google_android_gms_internal_zzjz;
        Throwable th;
        String str2;
        String valueOf;
        zzjz com_google_android_gms_internal_zzjz2 = (zzjz) this.zzcmv.get(str);
        if (com_google_android_gms_internal_zzjz2 != null) {
            return com_google_android_gms_internal_zzjz2;
        }
        try {
            com_google_android_gms_internal_zzjz = new zzjz(("com.google.ads.mediation.admob.AdMobAdapter".equals(str) ? zzcmu : this.zzals).zzbq(str), this);
            try {
                this.zzcmv.put(str, com_google_android_gms_internal_zzjz);
                return com_google_android_gms_internal_zzjz;
            } catch (Throwable e) {
                th = e;
                str2 = "Fail to instantiate adapter ";
                valueOf = String.valueOf(str);
                com.google.android.gms.ads.internal.util.client.zzb.zzd(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
                return com_google_android_gms_internal_zzjz;
            }
        } catch (Throwable e2) {
            th = e2;
            com_google_android_gms_internal_zzjz = com_google_android_gms_internal_zzjz2;
            str2 = "Fail to instantiate adapter ";
            valueOf = String.valueOf(str);
            if (valueOf.length() == 0) {
            }
            com.google.android.gms.ads.internal.util.client.zzb.zzd(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
            return com_google_android_gms_internal_zzjz;
        }
    }

    public void zzsn() {
        zzac.zzhq("showAd must be called on the main UI thread.");
        if (isLoaded()) {
            this.zzcmw = true;
            zzjz zzcl = zzcl(this.zzall.zzara.zzbtg);
            if (zzcl != null && zzcl.zzsv() != null) {
                try {
                    zzcl.zzsv().showVideo();
                    return;
                } catch (Throwable e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call showVideo.", e);
                    return;
                }
            }
            return;
        }
        com.google.android.gms.ads.internal.util.client.zzb.zzdf("The reward video has not loaded.");
    }

    public void zzso() {
        onAdClicked();
    }
}
