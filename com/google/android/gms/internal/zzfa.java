package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONObject;

@zziy
public class zzfa implements zzev {
    final HashMap<String, zzlg<JSONObject>> zzbnk = new HashMap();

    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        zzi((String) map.get("request_id"), (String) map.get("fetched_ad"));
    }

    public Future<JSONObject> zzaz(String str) {
        Future com_google_android_gms_internal_zzlg = new zzlg();
        this.zzbnk.put(str, com_google_android_gms_internal_zzlg);
        return com_google_android_gms_internal_zzlg;
    }

    public void zzba(String str) {
        zzlg com_google_android_gms_internal_zzlg = (zzlg) this.zzbnk.get(str);
        if (com_google_android_gms_internal_zzlg == null) {
            zzb.e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        if (!com_google_android_gms_internal_zzlg.isDone()) {
            com_google_android_gms_internal_zzlg.cancel(true);
        }
        this.zzbnk.remove(str);
    }

    public void zzi(String str, String str2) {
        zzb.zzdd("Received ad from the cache.");
        zzlg com_google_android_gms_internal_zzlg = (zzlg) this.zzbnk.get(str);
        if (com_google_android_gms_internal_zzlg == null) {
            zzb.e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        try {
            com_google_android_gms_internal_zzlg.zzh(new JSONObject(str2));
        } catch (Throwable e) {
            zzb.zzb("Failed constructing JSON object from value passed from javascript", e);
            com_google_android_gms_internal_zzlg.zzh(null);
        } finally {
            this.zzbnk.remove(str);
        }
    }
}
