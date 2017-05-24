package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzau;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzgh;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkm;
import com.google.android.gms.internal.zzkq;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzkt;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.internal.zzln;
import org.json.JSONObject;

@zziy
public class zzb extends zzkm implements com.google.android.gms.ads.internal.request.zzc.zza {
    private final Context mContext;
    private final zzau zzbkp;
    zzgh zzbsv;
    private AdRequestInfoParcel zzbtk;
    AdResponseParcel zzccl;
    private Runnable zzccm;
    private final Object zzccn = new Object();
    private final com.google.android.gms.ads.internal.request.zza.zza zzcfh;
    private final com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza zzcfi;
    zzkt zzcfj;

    @zziy
    static final class zza extends Exception {
        private final int zzcdb;

        public zza(String str, int i) {
            super(str);
            this.zzcdb = i;
        }

        public int getErrorCode() {
            return this.zzcdb;
        }
    }

    public zzb(Context context, com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, zzau com_google_android_gms_internal_zzau, com.google.android.gms.ads.internal.request.zza.zza com_google_android_gms_ads_internal_request_zza_zza) {
        this.zzcfh = com_google_android_gms_ads_internal_request_zza_zza;
        this.mContext = context;
        this.zzcfi = com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza;
        this.zzbkp = com_google_android_gms_internal_zzau;
    }

    private void zzd(int i, String str) {
        if (i == 3 || i == -1) {
            com.google.android.gms.ads.internal.util.client.zzb.zzde(str);
        } else {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf(str);
        }
        if (this.zzccl == null) {
            this.zzccl = new AdResponseParcel(i);
        } else {
            this.zzccl = new AdResponseParcel(i, this.zzccl.zzbsj);
        }
        this.zzcfh.zza(new com.google.android.gms.internal.zzke.zza(this.zzbtk != null ? this.zzbtk : new AdRequestInfoParcel(this.zzcfi, null, -1), this.zzccl, this.zzbsv, null, i, -1, this.zzccl.zzchg, null));
    }

    public void onStop() {
        synchronized (this.zzccn) {
            if (this.zzcfj != null) {
                this.zzcfj.cancel();
            }
        }
    }

    zzkt zza(VersionInfoParcel versionInfoParcel, zzlm<AdRequestInfoParcel> com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel) {
        return zzc.zza(this.mContext, versionInfoParcel, com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, this);
    }

    protected AdSizeParcel zzb(AdRequestInfoParcel adRequestInfoParcel) throws zza {
        int i;
        if (this.zzccl.zzaxm) {
            for (AdSizeParcel adSizeParcel : adRequestInfoParcel.zzaqz.zzaxk) {
                if (adSizeParcel.zzaxm) {
                    return new AdSizeParcel(adSizeParcel, adRequestInfoParcel.zzaqz.zzaxk);
                }
            }
        }
        if (this.zzccl.zzchf == null) {
            throw new zza("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.zzccl.zzchf.split("x");
        if (split.length != 2) {
            String str = "Invalid ad size format from the ad response: ";
            String valueOf = String.valueOf(this.zzccl.zzchf);
            throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            for (AdSizeParcel adSizeParcel2 : adRequestInfoParcel.zzaqz.zzaxk) {
                float f = this.mContext.getResources().getDisplayMetrics().density;
                i = adSizeParcel2.width == -1 ? (int) (((float) adSizeParcel2.widthPixels) / f) : adSizeParcel2.width;
                int i2 = adSizeParcel2.height == -2 ? (int) (((float) adSizeParcel2.heightPixels) / f) : adSizeParcel2.height;
                if (parseInt == i && parseInt2 == i2 && !adSizeParcel2.zzaxm) {
                    return new AdSizeParcel(adSizeParcel2, adRequestInfoParcel.zzaqz.zzaxk);
                }
            }
            str = "The ad size from the ad response was not one of the requested sizes: ";
            valueOf = String.valueOf(this.zzccl.zzchf);
            throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        } catch (NumberFormatException e) {
            str = "Invalid ad size number from the ad response: ";
            valueOf = String.valueOf(this.zzccl.zzchf);
            throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
    }

    public void zzb(@NonNull AdResponseParcel adResponseParcel) {
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Received ad response.");
        this.zzccl = adResponseParcel;
        long elapsedRealtime = zzu.zzgf().elapsedRealtime();
        synchronized (this.zzccn) {
            this.zzcfj = null;
        }
        zzu.zzgd().zzd(this.mContext, this.zzccl.zzcgt);
        try {
            if (this.zzccl.errorCode == -2 || this.zzccl.errorCode == -3) {
                JSONObject jSONObject;
                zzru();
                AdSizeParcel zzb = this.zzbtk.zzaqz.zzaxk != null ? zzb(this.zzbtk) : null;
                zzu.zzgd().zzaf(this.zzccl.zzchm);
                if (!TextUtils.isEmpty(this.zzccl.zzchk)) {
                    try {
                        jSONObject = new JSONObject(this.zzccl.zzchk);
                    } catch (Throwable e) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzb("Error parsing the JSON for Active View.", e);
                    }
                    this.zzcfh.zza(new com.google.android.gms.internal.zzke.zza(this.zzbtk, this.zzccl, this.zzbsv, zzb, -2, elapsedRealtime, this.zzccl.zzchg, jSONObject));
                    zzkr.zzcrf.removeCallbacks(this.zzccm);
                    return;
                }
                jSONObject = null;
                this.zzcfh.zza(new com.google.android.gms.internal.zzke.zza(this.zzbtk, this.zzccl, this.zzbsv, zzb, -2, elapsedRealtime, this.zzccl.zzchg, jSONObject));
                zzkr.zzcrf.removeCallbacks(this.zzccm);
                return;
            }
            throw new zza("There was a problem getting an ad response. ErrorCode: " + this.zzccl.errorCode, this.zzccl.errorCode);
        } catch (zza e2) {
            zzd(e2.getErrorCode(), e2.getMessage());
            zzkr.zzcrf.removeCallbacks(this.zzccm);
        }
    }

    public void zzfc() {
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("AdLoaderBackgroundTask started.");
        this.zzccm = new Runnable(this) {
            final /* synthetic */ zzb zzcfk;

            {
                this.zzcfk = r1;
            }

            public void run() {
                synchronized (this.zzcfk.zzccn) {
                    if (this.zzcfk.zzcfj == null) {
                        return;
                    }
                    this.zzcfk.onStop();
                    this.zzcfk.zzd(2, "Timed out waiting for ad response.");
                }
            }
        };
        zzkr.zzcrf.postDelayed(this.zzccm, ((Long) zzdi.zzbek.get()).longValue());
        final zzlm com_google_android_gms_internal_zzln = new zzln();
        long elapsedRealtime = zzu.zzgf().elapsedRealtime();
        zzkq.zza(new Runnable(this) {
            final /* synthetic */ zzb zzcfk;

            public void run() {
                synchronized (this.zzcfk.zzccn) {
                    this.zzcfk.zzcfj = this.zzcfk.zza(this.zzcfk.zzcfi.zzaqv, com_google_android_gms_internal_zzln);
                    if (this.zzcfk.zzcfj == null) {
                        this.zzcfk.zzd(0, "Could not start the ad request service.");
                        zzkr.zzcrf.removeCallbacks(this.zzcfk.zzccm);
                    }
                }
            }
        });
        this.zzbtk = new AdRequestInfoParcel(this.zzcfi, this.zzbkp.zzaw().zzb(this.mContext), elapsedRealtime);
        com_google_android_gms_internal_zzln.zzg(this.zzbtk);
    }

    protected void zzru() throws zza {
        if (this.zzccl.errorCode != -3) {
            if (TextUtils.isEmpty(this.zzccl.body)) {
                throw new zza("No fill from ad server.", 3);
            }
            zzu.zzgd().zzc(this.mContext, this.zzccl.zzcgc);
            if (this.zzccl.zzchc) {
                try {
                    this.zzbsv = new zzgh(this.zzccl.body);
                    zzu.zzgd().zzag(this.zzbsv.zzbsh);
                } catch (Throwable e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Could not parse mediation config.", e);
                    String str = "Could not parse mediation config: ";
                    String valueOf = String.valueOf(this.zzccl.body);
                    throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
                }
            }
            zzu.zzgd().zzag(this.zzccl.zzbsh);
            if (!TextUtils.isEmpty(this.zzccl.zzcgu) && ((Boolean) zzdi.zzbhh.get()).booleanValue()) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdd("Received cookie from server. Setting webview cookie in CookieManager.");
                CookieManager zzao = zzu.zzgb().zzao(this.mContext);
                if (zzao != null) {
                    zzao.setCookie("googleads.g.doubleclick.net", this.zzccl.zzcgu);
                }
            }
        }
    }
}
