package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.zzk.zza;
import com.google.android.gms.ads.internal.request.zzl;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzfy.zzc;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public final class zzjb extends zza {
    private static final Object zzaok = new Object();
    private static zzjb zzcjk;
    private final Context mContext;
    private final zzja zzcjl;
    private final zzdb zzcjm;
    private final zzfy zzcjn;

    class AnonymousClass1 implements Callable<Void> {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ zzja zzcjo;
        final /* synthetic */ AdRequestInfoParcel zzcjp;
        final /* synthetic */ Bundle zzcjq;

        AnonymousClass1(zzja com_google_android_gms_internal_zzja, Context context, AdRequestInfoParcel adRequestInfoParcel, Bundle bundle) {
            this.zzcjo = com_google_android_gms_internal_zzja;
            this.zzamt = context;
            this.zzcjp = adRequestInfoParcel;
            this.zzcjq = bundle;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzdb();
        }

        public Void zzdb() throws Exception {
            this.zzcjo.zzcja.zza(this.zzamt, this.zzcjp.zzcfv.packageName, this.zzcjq);
            return null;
        }
    }

    class AnonymousClass2 implements Runnable {
        final /* synthetic */ zzdq zzalz;
        final /* synthetic */ zzfy zzamp;
        final /* synthetic */ zzjd zzcjr;
        final /* synthetic */ zzdo zzcjs;
        final /* synthetic */ String zzcjt;

        AnonymousClass2(zzfy com_google_android_gms_internal_zzfy, zzjd com_google_android_gms_internal_zzjd, zzdq com_google_android_gms_internal_zzdq, zzdo com_google_android_gms_internal_zzdo, String str) {
            this.zzamp = com_google_android_gms_internal_zzfy;
            this.zzcjr = com_google_android_gms_internal_zzjd;
            this.zzalz = com_google_android_gms_internal_zzdq;
            this.zzcjs = com_google_android_gms_internal_zzdo;
            this.zzcjt = str;
        }

        public void run() {
            zzc zzmy = this.zzamp.zzmy();
            this.zzcjr.zzb(zzmy);
            this.zzalz.zza(this.zzcjs, "rwc");
            final zzdo zzla = this.zzalz.zzla();
            zzmy.zza(new zzlm.zzc<zzfz>(this) {
                final /* synthetic */ AnonymousClass2 zzcjv;

                public void zzb(zzfz com_google_android_gms_internal_zzfz) {
                    this.zzcjv.zzalz.zza(zzla, "jsf");
                    this.zzcjv.zzalz.zzlb();
                    com_google_android_gms_internal_zzfz.zza("/invalidRequest", this.zzcjv.zzcjr.zzckc);
                    com_google_android_gms_internal_zzfz.zza("/loadAdURL", this.zzcjv.zzcjr.zzckd);
                    com_google_android_gms_internal_zzfz.zza("/loadAd", this.zzcjv.zzcjr.zzcke);
                    try {
                        com_google_android_gms_internal_zzfz.zzj("AFMA_getAd", this.zzcjv.zzcjt);
                    } catch (Throwable e) {
                        zzb.zzb("Error requesting an ad url", e);
                    }
                }

                public /* synthetic */ void zzd(Object obj) {
                    zzb((zzfz) obj);
                }
            }, new zzlm.zza(this) {
                final /* synthetic */ AnonymousClass2 zzcjv;

                {
                    this.zzcjv = r1;
                }

                public void run() {
                }
            });
        }
    }

    class AnonymousClass3 implements Runnable {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ zzja zzcjo;
        final /* synthetic */ AdRequestInfoParcel zzcjp;
        final /* synthetic */ zzjd zzcjr;

        AnonymousClass3(zzja com_google_android_gms_internal_zzja, Context context, zzjd com_google_android_gms_internal_zzjd, AdRequestInfoParcel adRequestInfoParcel) {
            this.zzcjo = com_google_android_gms_internal_zzja;
            this.zzamt = context;
            this.zzcjr = com_google_android_gms_internal_zzjd;
            this.zzcjp = adRequestInfoParcel;
        }

        public void run() {
            this.zzcjo.zzcje.zza(this.zzamt, this.zzcjr, this.zzcjp.zzaqv);
        }
    }

    zzjb(Context context, zzdb com_google_android_gms_internal_zzdb, zzja com_google_android_gms_internal_zzja) {
        this.mContext = context;
        this.zzcjl = com_google_android_gms_internal_zzja;
        this.zzcjm = com_google_android_gms_internal_zzdb;
        this.zzcjn = new zzfy(context.getApplicationContext() != null ? context.getApplicationContext() : context, VersionInfoParcel.zzvg(), com_google_android_gms_internal_zzdb.zzkp(), new zzkw<zzfv>(this) {
            final /* synthetic */ zzjb zzcjw;

            {
                this.zzcjw = r1;
            }

            public void zza(zzfv com_google_android_gms_internal_zzfv) {
                com_google_android_gms_internal_zzfv.zza("/log", zzeu.zzbmj);
            }

            public /* synthetic */ void zzd(Object obj) {
                zza((zzfv) obj);
            }
        }, new zzfy.zzb());
    }

    private static AdResponseParcel zza(Context context, zzfy com_google_android_gms_internal_zzfy, zzdb com_google_android_gms_internal_zzdb, zzja com_google_android_gms_internal_zzja, AdRequestInfoParcel adRequestInfoParcel) {
        Bundle bundle;
        Future future;
        Throwable e;
        zzb.zzdd("Starting ad request from service using: AFMA_getAd");
        zzdi.initialize(context);
        zzlj zzqi = com_google_android_gms_internal_zzja.zzcji.zzqi();
        zzdq com_google_android_gms_internal_zzdq = new zzdq(((Boolean) zzdi.zzbca.get()).booleanValue(), "load_ad", adRequestInfoParcel.zzaqz.zzaxi);
        if (adRequestInfoParcel.versionCode > 10 && adRequestInfoParcel.zzcgm != -1) {
            com_google_android_gms_internal_zzdq.zza(com_google_android_gms_internal_zzdq.zzc(adRequestInfoParcel.zzcgm), "cts");
        }
        zzdo zzla = com_google_android_gms_internal_zzdq.zzla();
        Bundle bundle2 = (adRequestInfoParcel.versionCode < 4 || adRequestInfoParcel.zzcgb == null) ? null : adRequestInfoParcel.zzcgb;
        if (!((Boolean) zzdi.zzbcj.get()).booleanValue() || com_google_android_gms_internal_zzja.zzcja == null) {
            bundle = bundle2;
            future = null;
        } else {
            if (bundle2 == null && ((Boolean) zzdi.zzbck.get()).booleanValue()) {
                zzkn.v("contentInfo is not present, but we'll still launch the app index task");
                bundle2 = new Bundle();
            }
            if (bundle2 != null) {
                bundle = bundle2;
                future = zzkq.zza(new AnonymousClass1(com_google_android_gms_internal_zzja, context, adRequestInfoParcel, bundle2));
            } else {
                bundle = bundle2;
                future = null;
            }
        }
        zzlh com_google_android_gms_internal_zzlh = new zzlh(null);
        Bundle bundle3 = adRequestInfoParcel.zzcfu.extras;
        Object obj = (bundle3 == null || bundle3.getString("_ad") == null) ? null : 1;
        if (adRequestInfoParcel.zzcgt && obj == null) {
            zzlj zza = com_google_android_gms_internal_zzja.zzcjf.zza(adRequestInfoParcel.applicationInfo);
        } else {
            Object obj2 = com_google_android_gms_internal_zzlh;
        }
        zzjh zzy = zzu.zzgi().zzy(context);
        if (zzy.zzcmd == -1) {
            zzb.zzdd("Device is offline.");
            return new AdResponseParcel(2);
        }
        String uuid = adRequestInfoParcel.versionCode >= 7 ? adRequestInfoParcel.zzcgj : UUID.randomUUID().toString();
        zzjd com_google_android_gms_internal_zzjd = new zzjd(uuid, adRequestInfoParcel.applicationInfo.packageName);
        if (adRequestInfoParcel.zzcfu.extras != null) {
            String string = adRequestInfoParcel.zzcfu.extras.getString("_ad");
            if (string != null) {
                return zzjc.zza(context, adRequestInfoParcel, string);
            }
        }
        List zza2 = com_google_android_gms_internal_zzja.zzcjd.zza(adRequestInfoParcel);
        String zzg = com_google_android_gms_internal_zzja.zzcjj.zzg(adRequestInfoParcel);
        zzjl.zza zzz = com_google_android_gms_internal_zzja.zzcjh.zzz(context);
        if (future != null) {
            try {
                zzkn.v("Waiting for app index fetching task.");
                future.get(((Long) zzdi.zzbcl.get()).longValue(), TimeUnit.MILLISECONDS);
                zzkn.v("App index fetching task completed.");
            } catch (ExecutionException e2) {
                e = e2;
                zzb.zzd("Failed to fetch app index signal", e);
            } catch (InterruptedException e3) {
                e = e3;
                zzb.zzd("Failed to fetch app index signal", e);
            } catch (TimeoutException e4) {
                zzb.zzdd("Timed out waiting for app index fetching task");
            }
        }
        String zzcr = com_google_android_gms_internal_zzja.zzcjc.zzcr(adRequestInfoParcel.zzcfv.packageName);
        zzd(zzqi);
        JSONObject zza3 = zzjc.zza(context, new zziz().zzf(adRequestInfoParcel).zza(zzy).zza(zzz).zzc(zzc(zza)).zze(zzd(zzqi)).zzci(zzg).zzk(zza2).zzf(bundle).zzcj(zzcr).zzj(com_google_android_gms_internal_zzja.zzcjb.zzj(context)));
        if (zza3 == null) {
            return new AdResponseParcel(0);
        }
        if (adRequestInfoParcel.versionCode < 7) {
            try {
                zza3.put("request_id", uuid);
            } catch (JSONException e5) {
            }
        }
        try {
            zza3.put("prefetch_mode", "url");
        } catch (Throwable e6) {
            zzb.zzd("Failed putting prefetch parameters to ad request.", e6);
        }
        String jSONObject = zza3.toString();
        com_google_android_gms_internal_zzdq.zza(zzla, "arc");
        zzkr.zzcrf.post(new AnonymousClass2(com_google_android_gms_internal_zzfy, com_google_android_gms_internal_zzjd, com_google_android_gms_internal_zzdq, com_google_android_gms_internal_zzdq.zzla(), jSONObject));
        AdResponseParcel adResponseParcel;
        try {
            zzjg com_google_android_gms_internal_zzjg = (zzjg) com_google_android_gms_internal_zzjd.zzse().get(10, TimeUnit.SECONDS);
            if (com_google_android_gms_internal_zzjg == null) {
                adResponseParcel = new AdResponseParcel(0);
                return adResponseParcel;
            } else if (com_google_android_gms_internal_zzjg.getErrorCode() != -2) {
                adResponseParcel = new AdResponseParcel(com_google_android_gms_internal_zzjg.getErrorCode());
                zzkr.zzcrf.post(new AnonymousClass3(com_google_android_gms_internal_zzja, context, com_google_android_gms_internal_zzjd, adRequestInfoParcel));
                return adResponseParcel;
            } else {
                if (com_google_android_gms_internal_zzdq.zzld() != null) {
                    com_google_android_gms_internal_zzdq.zza(com_google_android_gms_internal_zzdq.zzld(), "rur");
                }
                adResponseParcel = null;
                if (!TextUtils.isEmpty(com_google_android_gms_internal_zzjg.zzsj())) {
                    adResponseParcel = zzjc.zza(context, adRequestInfoParcel, com_google_android_gms_internal_zzjg.zzsj());
                }
                if (adResponseParcel == null && !TextUtils.isEmpty(com_google_android_gms_internal_zzjg.getUrl())) {
                    adResponseParcel = zza(adRequestInfoParcel, context, adRequestInfoParcel.zzaqv.zzcs, com_google_android_gms_internal_zzjg.getUrl(), zzcr, com_google_android_gms_internal_zzjg, com_google_android_gms_internal_zzdq, com_google_android_gms_internal_zzja);
                }
                if (adResponseParcel == null) {
                    adResponseParcel = new AdResponseParcel(0);
                }
                com_google_android_gms_internal_zzdq.zza(zzla, "tts");
                adResponseParcel.zzchp = com_google_android_gms_internal_zzdq.zzlc();
                zzkr.zzcrf.post(new AnonymousClass3(com_google_android_gms_internal_zzja, context, com_google_android_gms_internal_zzjd, adRequestInfoParcel));
                return adResponseParcel;
            }
        } catch (Exception e7) {
            adResponseParcel = new AdResponseParcel(0);
            return adResponseParcel;
        } finally {
            zzkr.zzcrf.post(new AnonymousClass3(com_google_android_gms_internal_zzja, context, com_google_android_gms_internal_zzjd, adRequestInfoParcel));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.ads.internal.request.AdResponseParcel zza(com.google.android.gms.ads.internal.request.AdRequestInfoParcel r13, android.content.Context r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, com.google.android.gms.internal.zzjg r18, com.google.android.gms.internal.zzdq r19, com.google.android.gms.internal.zzja r20) {
        /*
        if (r19 == 0) goto L_0x00e9;
    L_0x0002:
        r2 = r19.zzla();
        r3 = r2;
    L_0x0007:
        r8 = new com.google.android.gms.internal.zzje;	 Catch:{ IOException -> 0x00f4 }
        r8.<init>(r13);	 Catch:{ IOException -> 0x00f4 }
        r4 = "AdRequestServiceImpl: Sending request: ";
        r2 = java.lang.String.valueOf(r16);	 Catch:{ IOException -> 0x00f4 }
        r5 = r2.length();	 Catch:{ IOException -> 0x00f4 }
        if (r5 == 0) goto L_0x00ed;
    L_0x0018:
        r2 = r4.concat(r2);	 Catch:{ IOException -> 0x00f4 }
    L_0x001c:
        com.google.android.gms.ads.internal.util.client.zzb.zzdd(r2);	 Catch:{ IOException -> 0x00f4 }
        r4 = new java.net.URL;	 Catch:{ IOException -> 0x00f4 }
        r0 = r16;
        r4.<init>(r0);	 Catch:{ IOException -> 0x00f4 }
        r2 = 0;
        r5 = com.google.android.gms.ads.internal.zzu.zzgf();	 Catch:{ IOException -> 0x00f4 }
        r10 = r5.elapsedRealtime();	 Catch:{ IOException -> 0x00f4 }
        r6 = r2;
        r7 = r4;
    L_0x0031:
        if (r20 == 0) goto L_0x003a;
    L_0x0033:
        r0 = r20;
        r2 = r0.zzcjg;	 Catch:{ IOException -> 0x00f4 }
        r2.zzsl();	 Catch:{ IOException -> 0x00f4 }
    L_0x003a:
        r2 = r7.openConnection();	 Catch:{ IOException -> 0x00f4 }
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x00f4 }
        r4 = com.google.android.gms.ads.internal.zzu.zzfz();	 Catch:{ all -> 0x0119 }
        r5 = 0;
        r4.zza(r14, r15, r5, r2);	 Catch:{ all -> 0x0119 }
        r4 = android.text.TextUtils.isEmpty(r17);	 Catch:{ all -> 0x0119 }
        if (r4 != 0) goto L_0x005b;
    L_0x004e:
        r4 = r18.zzsi();	 Catch:{ all -> 0x0119 }
        if (r4 == 0) goto L_0x005b;
    L_0x0054:
        r4 = "x-afma-drt-cookie";
        r0 = r17;
        r2.addRequestProperty(r4, r0);	 Catch:{ all -> 0x0119 }
    L_0x005b:
        r4 = r13.zzcgu;	 Catch:{ all -> 0x0119 }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0119 }
        if (r5 != 0) goto L_0x006d;
    L_0x0063:
        r5 = "Sending webview cookie in ad request header.";
        com.google.android.gms.ads.internal.util.client.zzb.zzdd(r5);	 Catch:{ all -> 0x0119 }
        r5 = "Cookie";
        r2.addRequestProperty(r5, r4);	 Catch:{ all -> 0x0119 }
    L_0x006d:
        if (r18 == 0) goto L_0x0099;
    L_0x006f:
        r4 = r18.zzsh();	 Catch:{ all -> 0x0119 }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0119 }
        if (r4 != 0) goto L_0x0099;
    L_0x0079:
        r4 = 1;
        r2.setDoOutput(r4);	 Catch:{ all -> 0x0119 }
        r4 = r18.zzsh();	 Catch:{ all -> 0x0119 }
        r9 = r4.getBytes();	 Catch:{ all -> 0x0119 }
        r4 = r9.length;	 Catch:{ all -> 0x0119 }
        r2.setFixedLengthStreamingMode(r4);	 Catch:{ all -> 0x0119 }
        r5 = 0;
        r4 = new java.io.BufferedOutputStream;	 Catch:{ all -> 0x0113 }
        r12 = r2.getOutputStream();	 Catch:{ all -> 0x0113 }
        r4.<init>(r12);	 Catch:{ all -> 0x0113 }
        r4.write(r9);	 Catch:{ all -> 0x01d0 }
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x0119 }
    L_0x0099:
        r9 = r2.getResponseCode();	 Catch:{ all -> 0x0119 }
        r12 = r2.getHeaderFields();	 Catch:{ all -> 0x0119 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r9 < r4) goto L_0x012d;
    L_0x00a5:
        r4 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 >= r4) goto L_0x012d;
    L_0x00a9:
        r6 = r7.toString();	 Catch:{ all -> 0x0119 }
        r5 = 0;
        r4 = new java.io.InputStreamReader;	 Catch:{ all -> 0x0127 }
        r7 = r2.getInputStream();	 Catch:{ all -> 0x0127 }
        r4.<init>(r7);	 Catch:{ all -> 0x0127 }
        r5 = com.google.android.gms.ads.internal.zzu.zzfz();	 Catch:{ all -> 0x01cd }
        r5 = r5.zza(r4);	 Catch:{ all -> 0x01cd }
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x0119 }
        zza(r6, r12, r5, r9);	 Catch:{ all -> 0x0119 }
        r8.zzb(r6, r12, r5);	 Catch:{ all -> 0x0119 }
        if (r19 == 0) goto L_0x00d7;
    L_0x00ca:
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ all -> 0x0119 }
        r5 = 0;
        r6 = "ufe";
        r4[r5] = r6;	 Catch:{ all -> 0x0119 }
        r0 = r19;
        r0.zza(r3, r4);	 Catch:{ all -> 0x0119 }
    L_0x00d7:
        r3 = r8.zzj(r10);	 Catch:{ all -> 0x0119 }
        r2.disconnect();	 Catch:{ IOException -> 0x00f4 }
        if (r20 == 0) goto L_0x00e7;
    L_0x00e0:
        r0 = r20;
        r2 = r0.zzcjg;	 Catch:{ IOException -> 0x00f4 }
        r2.zzsm();	 Catch:{ IOException -> 0x00f4 }
    L_0x00e7:
        r2 = r3;
    L_0x00e8:
        return r2;
    L_0x00e9:
        r2 = 0;
        r3 = r2;
        goto L_0x0007;
    L_0x00ed:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x00f4 }
        r2.<init>(r4);	 Catch:{ IOException -> 0x00f4 }
        goto L_0x001c;
    L_0x00f4:
        r2 = move-exception;
        r3 = "Error while connecting to ad server: ";
        r2 = r2.getMessage();
        r2 = java.lang.String.valueOf(r2);
        r4 = r2.length();
        if (r4 == 0) goto L_0x01c6;
    L_0x0105:
        r2 = r3.concat(r2);
    L_0x0109:
        com.google.android.gms.ads.internal.util.client.zzb.zzdf(r2);
        r2 = new com.google.android.gms.ads.internal.request.AdResponseParcel;
        r3 = 2;
        r2.<init>(r3);
        goto L_0x00e8;
    L_0x0113:
        r3 = move-exception;
        r4 = r5;
    L_0x0115:
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x0119 }
        throw r3;	 Catch:{ all -> 0x0119 }
    L_0x0119:
        r3 = move-exception;
        r2.disconnect();	 Catch:{ IOException -> 0x00f4 }
        if (r20 == 0) goto L_0x0126;
    L_0x011f:
        r0 = r20;
        r2 = r0.zzcjg;	 Catch:{ IOException -> 0x00f4 }
        r2.zzsm();	 Catch:{ IOException -> 0x00f4 }
    L_0x0126:
        throw r3;	 Catch:{ IOException -> 0x00f4 }
    L_0x0127:
        r3 = move-exception;
        r4 = r5;
    L_0x0129:
        com.google.android.gms.common.util.zzo.zzb(r4);	 Catch:{ all -> 0x0119 }
        throw r3;	 Catch:{ all -> 0x0119 }
    L_0x012d:
        r4 = r7.toString();	 Catch:{ all -> 0x0119 }
        r5 = 0;
        zza(r4, r12, r5, r9);	 Catch:{ all -> 0x0119 }
        r4 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r9 < r4) goto L_0x0186;
    L_0x0139:
        r4 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r9 >= r4) goto L_0x0186;
    L_0x013d:
        r4 = "Location";
        r4 = r2.getHeaderField(r4);	 Catch:{ all -> 0x0119 }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x0119 }
        if (r5 == 0) goto L_0x0162;
    L_0x0149:
        r3 = "No location header to follow redirect.";
        com.google.android.gms.ads.internal.util.client.zzb.zzdf(r3);	 Catch:{ all -> 0x0119 }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x0119 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0119 }
        r2.disconnect();	 Catch:{ IOException -> 0x00f4 }
        if (r20 == 0) goto L_0x0160;
    L_0x0159:
        r0 = r20;
        r2 = r0.zzcjg;	 Catch:{ IOException -> 0x00f4 }
        r2.zzsm();	 Catch:{ IOException -> 0x00f4 }
    L_0x0160:
        r2 = r3;
        goto L_0x00e8;
    L_0x0162:
        r5 = new java.net.URL;	 Catch:{ all -> 0x0119 }
        r5.<init>(r4);	 Catch:{ all -> 0x0119 }
        r4 = r6 + 1;
        r6 = 5;
        if (r4 <= r6) goto L_0x01b3;
    L_0x016c:
        r3 = "Too many redirects.";
        com.google.android.gms.ads.internal.util.client.zzb.zzdf(r3);	 Catch:{ all -> 0x0119 }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x0119 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0119 }
        r2.disconnect();	 Catch:{ IOException -> 0x00f4 }
        if (r20 == 0) goto L_0x0183;
    L_0x017c:
        r0 = r20;
        r2 = r0.zzcjg;	 Catch:{ IOException -> 0x00f4 }
        r2.zzsm();	 Catch:{ IOException -> 0x00f4 }
    L_0x0183:
        r2 = r3;
        goto L_0x00e8;
    L_0x0186:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0119 }
        r4 = 46;
        r3.<init>(r4);	 Catch:{ all -> 0x0119 }
        r4 = "Received error HTTP response code: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0119 }
        r3 = r3.append(r9);	 Catch:{ all -> 0x0119 }
        r3 = r3.toString();	 Catch:{ all -> 0x0119 }
        com.google.android.gms.ads.internal.util.client.zzb.zzdf(r3);	 Catch:{ all -> 0x0119 }
        r3 = new com.google.android.gms.ads.internal.request.AdResponseParcel;	 Catch:{ all -> 0x0119 }
        r4 = 0;
        r3.<init>(r4);	 Catch:{ all -> 0x0119 }
        r2.disconnect();	 Catch:{ IOException -> 0x00f4 }
        if (r20 == 0) goto L_0x01b0;
    L_0x01a9:
        r0 = r20;
        r2 = r0.zzcjg;	 Catch:{ IOException -> 0x00f4 }
        r2.zzsm();	 Catch:{ IOException -> 0x00f4 }
    L_0x01b0:
        r2 = r3;
        goto L_0x00e8;
    L_0x01b3:
        r8.zzj(r12);	 Catch:{ all -> 0x0119 }
        r2.disconnect();	 Catch:{ IOException -> 0x00f4 }
        if (r20 == 0) goto L_0x01c2;
    L_0x01bb:
        r0 = r20;
        r2 = r0.zzcjg;	 Catch:{ IOException -> 0x00f4 }
        r2.zzsm();	 Catch:{ IOException -> 0x00f4 }
    L_0x01c2:
        r6 = r4;
        r7 = r5;
        goto L_0x0031;
    L_0x01c6:
        r2 = new java.lang.String;
        r2.<init>(r3);
        goto L_0x0109;
    L_0x01cd:
        r3 = move-exception;
        goto L_0x0129;
    L_0x01d0:
        r3 = move-exception;
        goto L_0x0115;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzjb.zza(com.google.android.gms.ads.internal.request.AdRequestInfoParcel, android.content.Context, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.zzjg, com.google.android.gms.internal.zzdq, com.google.android.gms.internal.zzja):com.google.android.gms.ads.internal.request.AdResponseParcel");
    }

    public static zzjb zza(Context context, zzdb com_google_android_gms_internal_zzdb, zzja com_google_android_gms_internal_zzja) {
        zzjb com_google_android_gms_internal_zzjb;
        synchronized (zzaok) {
            if (zzcjk == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                zzcjk = new zzjb(context, com_google_android_gms_internal_zzdb, com_google_android_gms_internal_zzja);
            }
            com_google_android_gms_internal_zzjb = zzcjk;
        }
        return com_google_android_gms_internal_zzjb;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (zzb.zzbf(2)) {
            zzkn.v(new StringBuilder(String.valueOf(str).length() + 39).append("Http Response: {\n  URL:\n    ").append(str).append("\n  Headers:").toString());
            if (map != null) {
                for (String str3 : map.keySet()) {
                    String str32;
                    zzkn.v(new StringBuilder(String.valueOf(str32).length() + 5).append("    ").append(str32).append(":").toString());
                    for (String str322 : (List) map.get(str322)) {
                        String str4 = "      ";
                        str322 = String.valueOf(str322);
                        zzkn.v(str322.length() != 0 ? str4.concat(str322) : new String(str4));
                    }
                }
            }
            zzkn.v("  Body:");
            if (str2 != null) {
                for (int i2 = 0; i2 < Math.min(str2.length(), 100000); i2 += Constants.MAX_DOWNLOADS) {
                    zzkn.v(str2.substring(i2, Math.min(str2.length(), i2 + Constants.MAX_DOWNLOADS)));
                }
            } else {
                zzkn.v("    null");
            }
            zzkn.v("  Response Code:\n    " + i + "\n}");
        }
    }

    private static Location zzc(zzlj<Location> com_google_android_gms_internal_zzlj_android_location_Location) {
        try {
            return (Location) com_google_android_gms_internal_zzlj_android_location_Location.get(((Long) zzdi.zzbgm.get()).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            zzb.zzd("Exception caught while getting location", e);
            return null;
        }
    }

    private static Bundle zzd(zzlj<Bundle> com_google_android_gms_internal_zzlj_android_os_Bundle) {
        Bundle bundle = new Bundle();
        try {
            return (Bundle) com_google_android_gms_internal_zzlj_android_os_Bundle.get(((Long) zzdi.zzbhd.get()).longValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            zzb.zzd("Exception caught while getting parental controls.", e);
            return bundle;
        }
    }

    public void zza(final AdRequestInfoParcel adRequestInfoParcel, final zzl com_google_android_gms_ads_internal_request_zzl) {
        zzu.zzgd().zzb(this.mContext, adRequestInfoParcel.zzaqv);
        zzkq.zza(new Runnable(this) {
            final /* synthetic */ zzjb zzcjw;

            public void run() {
                AdResponseParcel zzd;
                try {
                    zzd = this.zzcjw.zzd(adRequestInfoParcel);
                } catch (Throwable e) {
                    zzu.zzgd().zza(e, "AdRequestServiceImpl.loadAdAsync");
                    zzb.zzd("Could not fetch ad response due to an Exception.", e);
                    zzd = null;
                }
                if (zzd == null) {
                    zzd = new AdResponseParcel(0);
                }
                try {
                    com_google_android_gms_ads_internal_request_zzl.zzb(zzd);
                } catch (Throwable e2) {
                    zzb.zzd("Fail to forward ad response.", e2);
                }
            }
        });
    }

    public AdResponseParcel zzd(AdRequestInfoParcel adRequestInfoParcel) {
        return zza(this.mContext, this.zzcjn, this.zzcjm, this.zzcjl, adRequestInfoParcel);
    }
}
