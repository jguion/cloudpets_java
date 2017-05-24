package com.google.android.gms.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public class zzkg {
    private final long zzcpb;
    private final List<String> zzcpc = new ArrayList();
    private final Map<String, zzb> zzcpd = new HashMap();
    private String zzcpe;
    private String zzcpf;
    private boolean zzcpg = false;

    class zza {
        private final List<String> zzcph;
        private final Bundle zzcpi;
        final /* synthetic */ zzkg zzcpj;

        public zza(zzkg com_google_android_gms_internal_zzkg, List<String> list, Bundle bundle) {
            this.zzcpj = com_google_android_gms_internal_zzkg;
            this.zzcph = list;
            this.zzcpi = bundle;
        }
    }

    class zzb {
        final /* synthetic */ zzkg zzcpj;
        final List<zza> zzcpk = new ArrayList();

        zzb(zzkg com_google_android_gms_internal_zzkg) {
            this.zzcpj = com_google_android_gms_internal_zzkg;
        }

        public void zza(zza com_google_android_gms_internal_zzkg_zza) {
            this.zzcpk.add(com_google_android_gms_internal_zzkg_zza);
        }
    }

    public zzkg(String str, long j) {
        this.zzcpf = str;
        this.zzcpb = j;
        zzcs(str);
    }

    private void zzcs(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt("status", -1) != 1) {
                    this.zzcpg = false;
                    com.google.android.gms.ads.internal.util.client.zzb.zzdf("App settings could not be fetched successfully.");
                    return;
                }
                this.zzcpg = true;
                this.zzcpe = jSONObject.optString("app_id");
                JSONArray optJSONArray = jSONObject.optJSONArray("ad_unit_id_settings");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        zzl(optJSONArray.getJSONObject(i));
                    }
                }
            } catch (Throwable e) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Exception occurred while processing app setting json", e);
                zzu.zzgd().zza(e, "AppSettings.parseAppSettingsJson");
            }
        }
    }

    private void zzl(JSONObject jSONObject) throws JSONException {
        String optString = jSONObject.optString("format");
        CharSequence optString2 = jSONObject.optString("ad_unit_id");
        if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
            if ("interstitial".equalsIgnoreCase(optString)) {
                this.zzcpc.add(optString2);
            } else if ("rewarded".equalsIgnoreCase(optString)) {
                JSONObject optJSONObject = jSONObject.optJSONObject("mediation_config");
                if (optJSONObject != null) {
                    JSONArray optJSONArray = optJSONObject.optJSONArray("ad_networks");
                    if (optJSONArray != null) {
                        int i = 0;
                        while (i < optJSONArray.length()) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            JSONArray optJSONArray2 = jSONObject2.optJSONArray("adapters");
                            if (optJSONArray2 != null) {
                                List arrayList = new ArrayList();
                                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    arrayList.add(optJSONArray2.getString(i2));
                                }
                                jSONObject2 = jSONObject2.optJSONObject("data");
                                if (jSONObject2 != null) {
                                    Bundle bundle = new Bundle();
                                    Iterator keys = jSONObject2.keys();
                                    while (keys.hasNext()) {
                                        optString = (String) keys.next();
                                        bundle.putString(optString, jSONObject2.getString(optString));
                                    }
                                    zza com_google_android_gms_internal_zzkg_zza = new zza(this, arrayList, bundle);
                                    zzb com_google_android_gms_internal_zzkg_zzb = this.zzcpd.containsKey(optString2) ? (zzb) this.zzcpd.get(optString2) : new zzb(this);
                                    com_google_android_gms_internal_zzkg_zzb.zza(com_google_android_gms_internal_zzkg_zza);
                                    this.zzcpd.put(optString2, com_google_android_gms_internal_zzkg_zzb);
                                    i++;
                                } else {
                                    return;
                                }
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    public long zztf() {
        return this.zzcpb;
    }

    public boolean zztg() {
        return this.zzcpg;
    }

    public String zzth() {
        return this.zzcpf;
    }

    public String zzti() {
        return this.zzcpe;
    }
}
