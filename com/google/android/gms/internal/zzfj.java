package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.api.Releasable;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@zziy
public abstract class zzfj implements Releasable {
    protected Context mContext;
    protected String zzbnw;
    protected WeakReference<zzlt> zzbnx;

    public zzfj(zzlt com_google_android_gms_internal_zzlt) {
        this.mContext = com_google_android_gms_internal_zzlt.getContext();
        this.zzbnw = zzu.zzfz().zzg(this.mContext, com_google_android_gms_internal_zzlt.zzvu().zzcs);
        this.zzbnx = new WeakReference(com_google_android_gms_internal_zzlt);
    }

    private void zza(String str, Map<String, String> map) {
        zzlt com_google_android_gms_internal_zzlt = (zzlt) this.zzbnx.get();
        if (com_google_android_gms_internal_zzlt != null) {
            com_google_android_gms_internal_zzlt.zza(str, (Map) map);
        }
    }

    private String zzbe(String str) {
        String str2 = "internal";
        Object obj = -1;
        switch (str.hashCode()) {
            case -1396664534:
                if (str.equals("badUrl")) {
                    obj = 6;
                    break;
                }
                break;
            case -1347010958:
                if (str.equals("inProgress")) {
                    obj = 2;
                    break;
                }
                break;
            case -918817863:
                if (str.equals("downloadTimeout")) {
                    obj = 7;
                    break;
                }
                break;
            case -659376217:
                if (str.equals("contentLengthMissing")) {
                    obj = 3;
                    break;
                }
                break;
            case -642208130:
                if (str.equals("playerFailed")) {
                    obj = 1;
                    break;
                }
                break;
            case -354048396:
                if (str.equals("sizeExceeded")) {
                    obj = 8;
                    break;
                }
                break;
            case -32082395:
                if (str.equals("externalAbort")) {
                    obj = 9;
                    break;
                }
                break;
            case 96784904:
                if (str.equals("error")) {
                    obj = null;
                    break;
                }
                break;
            case 580119100:
                if (str.equals("expireFailed")) {
                    obj = 5;
                    break;
                }
                break;
            case 725497484:
                if (str.equals("noCacheDir")) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
            case 1:
            case 2:
            case 3:
                return "internal";
            case 4:
            case 5:
                return "io";
            case 6:
            case 7:
                return "network";
            case 8:
            case 9:
                return "policy";
            default:
                return str2;
        }
    }

    public abstract void abort();

    public void release() {
    }

    protected void zza(final String str, final String str2, final int i) {
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzfj zzbod;

            public void run() {
                Map hashMap = new HashMap();
                hashMap.put("event", "precacheComplete");
                hashMap.put("src", str);
                hashMap.put("cachedSrc", str2);
                hashMap.put("totalBytes", Integer.toString(i));
                this.zzbod.zza("onPrecacheEvent", hashMap);
            }
        });
    }

    protected void zza(String str, String str2, int i, int i2, boolean z) {
        final String str3 = str;
        final String str4 = str2;
        final int i3 = i;
        final int i4 = i2;
        final boolean z2 = z;
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzfj zzbod;

            public void run() {
                Map hashMap = new HashMap();
                hashMap.put("event", "precacheProgress");
                hashMap.put("src", str3);
                hashMap.put("cachedSrc", str4);
                hashMap.put("bytesLoaded", Integer.toString(i3));
                hashMap.put("totalBytes", Integer.toString(i4));
                hashMap.put("cacheReady", z2 ? "1" : "0");
                this.zzbod.zza("onPrecacheEvent", hashMap);
            }
        });
    }

    public void zza(String str, String str2, String str3, @Nullable String str4) {
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final String str8 = str4;
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzfj zzbod;

            public void run() {
                Map hashMap = new HashMap();
                hashMap.put("event", "precacheCanceled");
                hashMap.put("src", str5);
                if (!TextUtils.isEmpty(str6)) {
                    hashMap.put("cachedSrc", str6);
                }
                hashMap.put(VoiceMessage.TYPE, this.zzbod.zzbe(str7));
                hashMap.put("reason", str7);
                if (!TextUtils.isEmpty(str8)) {
                    hashMap.put("message", str8);
                }
                this.zzbod.zza("onPrecacheEvent", hashMap);
            }
        });
    }

    public abstract boolean zzbc(String str);

    protected String zzbd(String str) {
        return zzm.zzjr().zzdc(str);
    }
}
