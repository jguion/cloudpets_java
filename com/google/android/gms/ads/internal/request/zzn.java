package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzfa;
import com.google.android.gms.internal.zzfv;
import com.google.android.gms.internal.zzfy;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zziz;
import com.google.android.gms.internal.zzjc;
import com.google.android.gms.internal.zzkm;
import com.google.android.gms.internal.zzkw;
import com.google.android.gms.internal.zzlt;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public class zzn extends zzkm {
    private static final Object zzaok = new Object();
    private static zzfy zzcdq = null;
    static final long zzcik = TimeUnit.SECONDS.toMillis(10);
    static boolean zzcil = false;
    private static zzew zzcim = null;
    private static zzfa zzcin = null;
    private static zzev zzcio = null;
    private final Context mContext;
    private final Object zzccn = new Object();
    private final com.google.android.gms.ads.internal.request.zza.zza zzcfh;
    private final com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza zzcfi;
    private com.google.android.gms.internal.zzfy.zzc zzcip;

    public static class zza implements zzkw<zzfv> {
        public void zza(zzfv com_google_android_gms_internal_zzfv) {
            zzn.zzc(com_google_android_gms_internal_zzfv);
        }

        public /* synthetic */ void zzd(Object obj) {
            zza((zzfv) obj);
        }
    }

    public static class zzb implements zzkw<zzfv> {
        public void zza(zzfv com_google_android_gms_internal_zzfv) {
            zzn.zzb(com_google_android_gms_internal_zzfv);
        }

        public /* synthetic */ void zzd(Object obj) {
            zza((zzfv) obj);
        }
    }

    public static class zzc implements zzev {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            String str = (String) map.get("request_id");
            String str2 = "Invalid request: ";
            String valueOf = String.valueOf((String) map.get("errors"));
            com.google.android.gms.ads.internal.util.client.zzb.zzdf(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            zzn.zzcin.zzba(str);
        }
    }

    public zzn(Context context, com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, com.google.android.gms.ads.internal.request.zza.zza com_google_android_gms_ads_internal_request_zza_zza) {
        super(true);
        this.zzcfh = com_google_android_gms_ads_internal_request_zza_zza;
        this.mContext = context;
        this.zzcfi = com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza;
        synchronized (zzaok) {
            if (!zzcil) {
                zzcin = new zzfa();
                zzcim = new zzew(context.getApplicationContext(), com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzaqv);
                zzcio = new zzc();
                zzcdq = new zzfy(this.mContext.getApplicationContext(), this.zzcfi.zzaqv, (String) zzdi.zzbao.get(), new zzb(), new zza());
                zzcil = true;
            }
        }
    }

    private JSONObject zza(AdRequestInfoParcel adRequestInfoParcel, String str) {
        Throwable e;
        Object obj;
        Map hashMap;
        JSONObject jSONObject = null;
        Bundle bundle = adRequestInfoParcel.zzcfu.extras.getBundle("sdk_less_server_data");
        if (bundle != null) {
            JSONObject zza = zzjc.zza(this.mContext, new zziz().zzf(adRequestInfoParcel).zza(zzu.zzgi().zzy(this.mContext)));
            if (zza != null) {
                Info advertisingIdInfo;
                try {
                    advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
                } catch (IOException e2) {
                    e = e2;
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzu.zzfz().zzan(hashMap);
                    return jSONObject;
                } catch (IllegalStateException e3) {
                    e = e3;
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzu.zzfz().zzan(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesNotAvailableException e4) {
                    e = e4;
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzu.zzfz().zzan(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesRepairableException e5) {
                    e = e5;
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("request_param", zza);
                    hashMap.put("data", bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzu.zzfz().zzan(hashMap);
                    return jSONObject;
                }
                hashMap = new HashMap();
                hashMap.put("request_id", str);
                hashMap.put("request_param", zza);
                hashMap.put("data", bundle);
                if (advertisingIdInfo != null) {
                    hashMap.put("adid", advertisingIdInfo.getId());
                    if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                    }
                    hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                }
                try {
                    jSONObject = zzu.zzfz().zzan(hashMap);
                } catch (JSONException e6) {
                }
            }
        }
        return jSONObject;
    }

    protected static void zzb(zzfv com_google_android_gms_internal_zzfv) {
        com_google_android_gms_internal_zzfv.zza("/loadAd", zzcin);
        com_google_android_gms_internal_zzfv.zza("/fetchHttpRequest", zzcim);
        com_google_android_gms_internal_zzfv.zza("/invalidRequest", zzcio);
    }

    protected static void zzc(zzfv com_google_android_gms_internal_zzfv) {
        com_google_android_gms_internal_zzfv.zzb("/loadAd", zzcin);
        com_google_android_gms_internal_zzfv.zzb("/fetchHttpRequest", zzcim);
        com_google_android_gms_internal_zzfv.zzb("/invalidRequest", zzcio);
    }

    private AdResponseParcel zze(AdRequestInfoParcel adRequestInfoParcel) {
        final String zzuh = zzu.zzfz().zzuh();
        final JSONObject zza = zza(adRequestInfoParcel, zzuh);
        if (zza == null) {
            return new AdResponseParcel(0);
        }
        long elapsedRealtime = zzu.zzgf().elapsedRealtime();
        Future zzaz = zzcin.zzaz(zzuh);
        com.google.android.gms.ads.internal.util.client.zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzn zzciq;

            public void run() {
                this.zzciq.zzcip = zzn.zzcdq.zzmy();
                this.zzciq.zzcip.zza(new com.google.android.gms.internal.zzlm.zzc<zzfz>(this) {
                    final /* synthetic */ AnonymousClass2 zzcit;

                    {
                        this.zzcit = r1;
                    }

                    public void zzb(zzfz com_google_android_gms_internal_zzfz) {
                        try {
                            com_google_android_gms_internal_zzfz.zza("AFMA_getAdapterLessMediationAd", zza);
                        } catch (Throwable e) {
                            com.google.android.gms.ads.internal.util.client.zzb.zzb("Error requesting an ad url", e);
                            zzn.zzcin.zzba(zzuh);
                        }
                    }

                    public /* synthetic */ void zzd(Object obj) {
                        zzb((zzfz) obj);
                    }
                }, new com.google.android.gms.internal.zzlm.zza(this) {
                    final /* synthetic */ AnonymousClass2 zzcit;

                    {
                        this.zzcit = r1;
                    }

                    public void run() {
                        zzn.zzcin.zzba(zzuh);
                    }
                });
            }
        });
        try {
            JSONObject jSONObject = (JSONObject) zzaz.get(zzcik - (zzu.zzgf().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new AdResponseParcel(-1);
            }
            AdResponseParcel zza2 = zzjc.zza(this.mContext, adRequestInfoParcel, jSONObject.toString());
            return (zza2.errorCode == -3 || !TextUtils.isEmpty(zza2.body)) ? zza2 : new AdResponseParcel(3);
        } catch (CancellationException e) {
            return new AdResponseParcel(-1);
        } catch (InterruptedException e2) {
            return new AdResponseParcel(-1);
        } catch (TimeoutException e3) {
            return new AdResponseParcel(2);
        } catch (ExecutionException e4) {
            return new AdResponseParcel(0);
        }
    }

    public void onStop() {
        synchronized (this.zzccn) {
            com.google.android.gms.ads.internal.util.client.zza.zzctj.post(new Runnable(this) {
                final /* synthetic */ zzn zzciq;

                {
                    this.zzciq = r1;
                }

                public void run() {
                    if (this.zzciq.zzcip != null) {
                        this.zzciq.zzcip.release();
                        this.zzciq.zzcip = null;
                    }
                }
            });
        }
    }

    public void zzfc() {
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("SdkLessAdLoaderBackgroundTask started.");
        AdRequestInfoParcel adRequestInfoParcel = new AdRequestInfoParcel(this.zzcfi, null, -1);
        AdResponseParcel zze = zze(adRequestInfoParcel);
        AdSizeParcel adSizeParcel = null;
        final com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza = new com.google.android.gms.internal.zzke.zza(adRequestInfoParcel, zze, null, adSizeParcel, zze.errorCode, zzu.zzgf().elapsedRealtime(), zze.zzchg, null);
        com.google.android.gms.ads.internal.util.client.zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzn zzciq;

            public void run() {
                this.zzciq.zzcfh.zza(com_google_android_gms_internal_zzke_zza);
                if (this.zzciq.zzcip != null) {
                    this.zzciq.zzcip.release();
                    this.zzciq.zzcip = null;
                }
            }
        });
    }
}
