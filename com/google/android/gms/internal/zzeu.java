package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.formats.zzg;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.overlay.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public final class zzeu {
    public static final zzev zzbmb = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        }
    };
    public static final zzev zzbmc = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            String str = (String) map.get("urls");
            if (TextUtils.isEmpty(str)) {
                zzb.zzdf("URLs missing in canOpenURLs GMSG.");
                return;
            }
            String[] split = str.split(",");
            Map hashMap = new HashMap();
            PackageManager packageManager = com_google_android_gms_internal_zzlt.getContext().getPackageManager();
            for (String str2 : split) {
                String[] split2 = str2.split(";", 2);
                hashMap.put(str2, Boolean.valueOf(packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), 65536) != null));
            }
            com_google_android_gms_internal_zzlt.zza("openableURLs", hashMap);
        }
    };
    public static final zzev zzbmd = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            PackageManager packageManager = com_google_android_gms_internal_zzlt.getContext().getPackageManager();
            try {
                try {
                    JSONArray jSONArray = new JSONObject((String) map.get("data")).getJSONArray("intents");
                    JSONObject jSONObject = new JSONObject();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            String optString = jSONObject2.optString("id");
                            Object optString2 = jSONObject2.optString("u");
                            Object optString3 = jSONObject2.optString("i");
                            Object optString4 = jSONObject2.optString("m");
                            Object optString5 = jSONObject2.optString("p");
                            Object optString6 = jSONObject2.optString("c");
                            jSONObject2.optString("f");
                            jSONObject2.optString("e");
                            Intent intent = new Intent();
                            if (!TextUtils.isEmpty(optString2)) {
                                intent.setData(Uri.parse(optString2));
                            }
                            if (!TextUtils.isEmpty(optString3)) {
                                intent.setAction(optString3);
                            }
                            if (!TextUtils.isEmpty(optString4)) {
                                intent.setType(optString4);
                            }
                            if (!TextUtils.isEmpty(optString5)) {
                                intent.setPackage(optString5);
                            }
                            if (!TextUtils.isEmpty(optString6)) {
                                String[] split = optString6.split("/", 2);
                                if (split.length == 2) {
                                    intent.setComponent(new ComponentName(split[0], split[1]));
                                }
                            }
                            try {
                                jSONObject.put(optString, packageManager.resolveActivity(intent, 65536) != null);
                            } catch (Throwable e) {
                                zzb.zzb("Error constructing openable urls response.", e);
                            }
                        } catch (Throwable e2) {
                            zzb.zzb("Error parsing the intent data.", e2);
                        }
                    }
                    com_google_android_gms_internal_zzlt.zzb("openableIntents", jSONObject);
                } catch (JSONException e3) {
                    com_google_android_gms_internal_zzlt.zzb("openableIntents", new JSONObject());
                }
            } catch (JSONException e4) {
                com_google_android_gms_internal_zzlt.zzb("openableIntents", new JSONObject());
            }
        }
    };
    public static final zzev zzbme = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            String str = (String) map.get("u");
            if (str == null) {
                zzb.zzdf("URL missing from click GMSG.");
                return;
            }
            Uri zza;
            Future future;
            Uri parse = Uri.parse(str);
            try {
                zzau zzvt = com_google_android_gms_internal_zzlt.zzvt();
                if (zzvt != null && zzvt.zzc(parse)) {
                    zza = zzvt.zza(parse, com_google_android_gms_internal_zzlt.getContext(), com_google_android_gms_internal_zzlt.getView());
                    future = (Future) new zzlb(com_google_android_gms_internal_zzlt.getContext(), com_google_android_gms_internal_zzlt.zzvu().zzcs, zza.toString()).zzqw();
                }
            } catch (zzav e) {
                String str2 = "Unable to append parameter to URL: ";
                str = String.valueOf(str);
                zzb.zzdf(str.length() != 0 ? str2.concat(str) : new String(str2));
            }
            zza = parse;
            future = (Future) new zzlb(com_google_android_gms_internal_zzlt.getContext(), com_google_android_gms_internal_zzlt.zzvu().zzcs, zza.toString()).zzqw();
        }
    };
    public static final zzev zzbmf = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            zzd zzvp = com_google_android_gms_internal_zzlt.zzvp();
            if (zzvp != null) {
                zzvp.close();
                return;
            }
            zzvp = com_google_android_gms_internal_zzlt.zzvq();
            if (zzvp != null) {
                zzvp.close();
            } else {
                zzb.zzdf("A GMSG tried to close something that wasn't an overlay.");
            }
        }
    };
    public static final zzev zzbmg = new zzev() {
        private void zzd(zzlt com_google_android_gms_internal_zzlt) {
            zzb.zzde("Received support message, responding.");
            boolean z = false;
            com.google.android.gms.ads.internal.zzd zzdp = com_google_android_gms_internal_zzlt.zzdp();
            if (zzdp != null) {
                zzm com_google_android_gms_ads_internal_overlay_zzm = zzdp.zzame;
                if (com_google_android_gms_ads_internal_overlay_zzm != null) {
                    z = com_google_android_gms_ads_internal_overlay_zzm.zzr(com_google_android_gms_internal_zzlt.getContext());
                }
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("event", "checkSupport");
                jSONObject.put("supports", z);
                com_google_android_gms_internal_zzlt.zzb("appStreaming", jSONObject);
            } catch (Throwable th) {
            }
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            if ("checkSupport".equals(map.get("action"))) {
                zzd(com_google_android_gms_internal_zzlt);
                return;
            }
            zzd zzvp = com_google_android_gms_internal_zzlt.zzvp();
            if (zzvp != null) {
                zzvp.zzf(com_google_android_gms_internal_zzlt, map);
            }
        }
    };
    public static final zzev zzbmh = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            com_google_android_gms_internal_zzlt.zzak("1".equals(map.get("custom_close")));
        }
    };
    public static final zzev zzbmi = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            String str = (String) map.get("u");
            if (str == null) {
                zzb.zzdf("URL missing from httpTrack GMSG.");
            } else {
                Future future = (Future) new zzlb(com_google_android_gms_internal_zzlt.getContext(), com_google_android_gms_internal_zzlt.zzvu().zzcs, str).zzqw();
            }
        }
    };
    public static final zzev zzbmj = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            String str = "Received log message: ";
            String valueOf = String.valueOf((String) map.get("string"));
            zzb.zzde(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    };
    public static final zzev zzbmk = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            zzg zzwg = com_google_android_gms_internal_zzlt.zzwg();
            if (zzwg != null) {
                zzwg.zzlu();
            }
        }
    };
    public static final zzev zzbml = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            String str = (String) map.get("ty");
            String str2 = (String) map.get("td");
            try {
                int parseInt = Integer.parseInt((String) map.get("tx"));
                int parseInt2 = Integer.parseInt(str);
                int parseInt3 = Integer.parseInt(str2);
                zzau zzvt = com_google_android_gms_internal_zzlt.zzvt();
                if (zzvt != null) {
                    zzvt.zzaw().zza(parseInt, parseInt2, parseInt3);
                }
            } catch (NumberFormatException e) {
                zzb.zzdf("Could not parse touch parameters from gmsg.");
            }
        }
    };
    public static final zzev zzbmm = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            if (((Boolean) zzdi.zzbee.get()).booleanValue()) {
                com_google_android_gms_internal_zzlt.zzal(!Boolean.parseBoolean((String) map.get("disabled")));
            }
        }
    };
    public static final zzev zzbmn = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            String str = (String) map.get("action");
            if ("pause".equals(str)) {
                com_google_android_gms_internal_zzlt.zzel();
            } else if ("resume".equals(str)) {
                com_google_android_gms_internal_zzlt.zzem();
            }
        }
    };
    public static final zzev zzbmo = new zzff();
    public static final zzev zzbmp = new zzfg();
    public static final zzev zzbmq = new zzfk();
    public static final zzev zzbmr = new zzet();
    public static final zzfd zzbms = new zzfd();
    public static final zzev zzbmt = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            if (map.keySet().contains("start")) {
                com_google_android_gms_internal_zzlt.zzvr().zzwp();
            } else if (map.keySet().contains("stop")) {
                com_google_android_gms_internal_zzlt.zzvr().zzwq();
            } else if (map.keySet().contains("cancel")) {
                com_google_android_gms_internal_zzlt.zzvr().zzwr();
            }
        }
    };
    public static final zzev zzbmu = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            if (map.keySet().contains("start")) {
                com_google_android_gms_internal_zzlt.zzam(true);
            }
            if (map.keySet().contains("stop")) {
                com_google_android_gms_internal_zzlt.zzam(false);
            }
        }
    };
    public static final zzev zzbmv = new zzev() {
        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            com_google_android_gms_internal_zzlt.zza("locationReady", zzu.zzfz().zza((View) com_google_android_gms_internal_zzlt, (WindowManager) com_google_android_gms_internal_zzlt.getContext().getSystemService("window")));
            zzb.zzdf("GET LOCATION COMPILED");
        }
    };
}
