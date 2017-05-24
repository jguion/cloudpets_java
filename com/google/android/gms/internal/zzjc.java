package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.SearchAdRequestParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.safebrowsing.SafeBrowsingConfigParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzjl.zza;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public final class zzjc {
    private static final SimpleDateFormat zzcjy = new SimpleDateFormat("yyyyMMdd", Locale.US);

    public static AdResponseParcel zza(Context context, AdRequestInfoParcel adRequestInfoParcel, String str) {
        String optString;
        try {
            String str2;
            JSONObject jSONObject = new JSONObject(str);
            String optString2 = jSONObject.optString("ad_base_url", null);
            Object optString3 = jSONObject.optString("ad_url", null);
            String optString4 = jSONObject.optString("ad_size", null);
            String optString5 = jSONObject.optString("ad_slot_size", optString4);
            boolean z = (adRequestInfoParcel == null || adRequestInfoParcel.zzcga == 0) ? false : true;
            CharSequence optString6 = jSONObject.optString("ad_json", null);
            if (optString6 == null) {
                optString6 = jSONObject.optString("ad_html", null);
            }
            if (optString6 == null) {
                optString6 = jSONObject.optString("body", null);
            }
            long j = -1;
            String optString7 = jSONObject.optString("debug_dialog", null);
            String optString8 = jSONObject.optString("debug_signals", null);
            long j2 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1;
            optString = jSONObject.optString("orientation", null);
            int i = -1;
            if ("portrait".equals(optString)) {
                i = zzu.zzgb().zzun();
            } else if ("landscape".equals(optString)) {
                i = zzu.zzgb().zzum();
            }
            AdResponseParcel adResponseParcel = null;
            if (!TextUtils.isEmpty(optString6) || TextUtils.isEmpty(optString3)) {
                CharSequence charSequence = optString6;
            } else {
                adResponseParcel = zzjb.zza(adRequestInfoParcel, context, adRequestInfoParcel.zzaqv.zzcs, optString3, null, null, null, null);
                optString2 = adResponseParcel.zzbyj;
                str2 = adResponseParcel.body;
                j = adResponseParcel.zzchg;
            }
            if (str2 == null) {
                return new AdResponseParcel(0);
            }
            long j3;
            String optString9;
            String str3;
            boolean optBoolean;
            JSONArray optJSONArray = jSONObject.optJSONArray("click_urls");
            List list = adResponseParcel == null ? null : adResponseParcel.zzbsd;
            if (optJSONArray != null) {
                list = zza(optJSONArray, list);
            }
            optJSONArray = jSONObject.optJSONArray("impression_urls");
            List list2 = adResponseParcel == null ? null : adResponseParcel.zzbse;
            if (optJSONArray != null) {
                list2 = zza(optJSONArray, list2);
            }
            optJSONArray = jSONObject.optJSONArray("manual_impression_urls");
            List list3 = adResponseParcel == null ? null : adResponseParcel.zzche;
            if (optJSONArray != null) {
                list3 = zza(optJSONArray, list3);
            }
            if (adResponseParcel != null) {
                if (adResponseParcel.orientation != -1) {
                    i = adResponseParcel.orientation;
                }
                if (adResponseParcel.zzchb > 0) {
                    j3 = adResponseParcel.zzchb;
                    optString9 = jSONObject.optString("active_view");
                    str3 = null;
                    optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
                    if (optBoolean) {
                        str3 = jSONObject.optString("ad_passback_url", null);
                    }
                    return new AdResponseParcel(adRequestInfoParcel, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list3, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString7, optBoolean, str3, optString9, jSONObject.optBoolean("custom_render_allowed", false), z, adRequestInfoParcel.zzcgc, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), RewardItemParcel.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), null), zza(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), AutoClickProtectionConfigurationParcel.zzi(jSONObject.optJSONObject("auto_protection_configuration")), adRequestInfoParcel.zzcgt, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optBoolean("render_in_browser", adRequestInfoParcel.zzbsh), optString5, SafeBrowsingConfigParcel.zzk(jSONObject.optJSONObject("safe_browsing")), optString8);
                }
            }
            j3 = j2;
            optString9 = jSONObject.optString("active_view");
            str3 = null;
            optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
            if (optBoolean) {
                str3 = jSONObject.optString("ad_passback_url", null);
            }
            return new AdResponseParcel(adRequestInfoParcel, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list3, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString7, optBoolean, str3, optString9, jSONObject.optBoolean("custom_render_allowed", false), z, adRequestInfoParcel.zzcgc, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), RewardItemParcel.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), null), zza(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), AutoClickProtectionConfigurationParcel.zzi(jSONObject.optJSONObject("auto_protection_configuration")), adRequestInfoParcel.zzcgt, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optBoolean("render_in_browser", adRequestInfoParcel.zzbsh), optString5, SafeBrowsingConfigParcel.zzk(jSONObject.optJSONObject("safe_browsing")), optString8);
        } catch (JSONException e) {
            String str4 = "Could not parse the inline ad response: ";
            optString = String.valueOf(e.getMessage());
            zzb.zzdf(optString.length() != 0 ? str4.concat(optString) : new String(str4));
            return new AdResponseParcel(0);
        }
    }

    @Nullable
    private static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new LinkedList();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    @Nullable
    public static JSONObject zza(Context context, zziz com_google_android_gms_internal_zziz) {
        Object obj;
        String str;
        String valueOf;
        AdRequestInfoParcel adRequestInfoParcel = com_google_android_gms_internal_zziz.zzcix;
        Location location = com_google_android_gms_internal_zziz.zzawl;
        zzjh com_google_android_gms_internal_zzjh = com_google_android_gms_internal_zziz.zzciy;
        Bundle bundle = com_google_android_gms_internal_zziz.zzcgb;
        JSONObject jSONObject = com_google_android_gms_internal_zziz.zzciz;
        HashMap hashMap = new HashMap();
        hashMap.put("extra_caps", zzdi.zzbfr.get());
        if (com_google_android_gms_internal_zziz.zzcgk.size() > 0) {
            hashMap.put("eid", TextUtils.join(",", com_google_android_gms_internal_zziz.zzcgk));
        }
        if (adRequestInfoParcel.zzcft != null) {
            hashMap.put("ad_pos", adRequestInfoParcel.zzcft);
        }
        zza(hashMap, adRequestInfoParcel.zzcfu);
        if (adRequestInfoParcel.zzaqz.zzaxk != null) {
            obj = null;
            Object obj2 = null;
            for (AdSizeParcel adSizeParcel : adRequestInfoParcel.zzaqz.zzaxk) {
                if (!adSizeParcel.zzaxm && r3 == null) {
                    hashMap.put("format", adSizeParcel.zzaxi);
                    obj2 = 1;
                }
                if (adSizeParcel.zzaxm && r2 == null) {
                    hashMap.put("fluid", "height");
                    obj = 1;
                }
                if (obj2 != null && r2 != null) {
                    break;
                }
            }
        } else {
            hashMap.put("format", adRequestInfoParcel.zzaqz.zzaxi);
            if (adRequestInfoParcel.zzaqz.zzaxm) {
                hashMap.put("fluid", "height");
            }
        }
        if (adRequestInfoParcel.zzaqz.width == -1) {
            hashMap.put("smart_w", "full");
        }
        if (adRequestInfoParcel.zzaqz.height == -2) {
            hashMap.put("smart_h", "auto");
        }
        if (adRequestInfoParcel.zzaqz.zzaxk != null) {
            StringBuilder stringBuilder = new StringBuilder();
            obj = null;
            for (AdSizeParcel adSizeParcel2 : adRequestInfoParcel.zzaqz.zzaxk) {
                if (adSizeParcel2.zzaxm) {
                    obj = 1;
                } else {
                    int i;
                    if (stringBuilder.length() != 0) {
                        stringBuilder.append("|");
                    }
                    if (adSizeParcel2.width == -1) {
                        i = (int) (((float) adSizeParcel2.widthPixels) / com_google_android_gms_internal_zzjh.zzcgg);
                    } else {
                        try {
                            i = adSizeParcel2.width;
                        } catch (JSONException e) {
                            str = "Problem serializing ad request to JSON: ";
                            valueOf = String.valueOf(e.getMessage());
                            zzb.zzdf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                            return null;
                        }
                    }
                    stringBuilder.append(i);
                    stringBuilder.append("x");
                    stringBuilder.append(adSizeParcel2.height == -2 ? (int) (((float) adSizeParcel2.heightPixels) / com_google_android_gms_internal_zzjh.zzcgg) : adSizeParcel2.height);
                }
            }
            if (obj != null) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.insert(0, "|");
                }
                stringBuilder.insert(0, "320x50");
            }
            hashMap.put("sz", stringBuilder);
        }
        if (adRequestInfoParcel.zzcga != 0) {
            hashMap.put("native_version", Integer.valueOf(adRequestInfoParcel.zzcga));
            hashMap.put("native_templates", adRequestInfoParcel.zzarr);
            hashMap.put("native_image_orientation", zzc(adRequestInfoParcel.zzarn));
            if (!adRequestInfoParcel.zzcgl.isEmpty()) {
                hashMap.put("native_custom_templates", adRequestInfoParcel.zzcgl);
            }
        }
        if (adRequestInfoParcel.zzaqz.zzaxn) {
            hashMap.put("ene", Boolean.valueOf(true));
        }
        hashMap.put("slotname", adRequestInfoParcel.zzaqt);
        hashMap.put("pn", adRequestInfoParcel.applicationInfo.packageName);
        if (adRequestInfoParcel.zzcfv != null) {
            hashMap.put("vc", Integer.valueOf(adRequestInfoParcel.zzcfv.versionCode));
        }
        hashMap.put("ms", com_google_android_gms_internal_zziz.zzcfw);
        hashMap.put("seq_num", adRequestInfoParcel.zzcfx);
        hashMap.put("session_id", adRequestInfoParcel.zzcfy);
        hashMap.put("js", adRequestInfoParcel.zzaqv.zzcs);
        zza(hashMap, com_google_android_gms_internal_zzjh, com_google_android_gms_internal_zziz.zzciv, adRequestInfoParcel.zzcgy, com_google_android_gms_internal_zziz.zzciu);
        zza(hashMap, com_google_android_gms_internal_zziz.zzciw);
        hashMap.put("platform", Build.MANUFACTURER);
        hashMap.put("submodel", Build.MODEL);
        if (location != null) {
            zza(hashMap, location);
        } else if (adRequestInfoParcel.zzcfu.versionCode >= 2 && adRequestInfoParcel.zzcfu.zzawl != null) {
            zza(hashMap, adRequestInfoParcel.zzcfu.zzawl);
        }
        if (adRequestInfoParcel.versionCode >= 2) {
            hashMap.put("quality_signals", adRequestInfoParcel.zzcfz);
        }
        if (adRequestInfoParcel.versionCode >= 4 && adRequestInfoParcel.zzcgc) {
            hashMap.put("forceHttps", Boolean.valueOf(adRequestInfoParcel.zzcgc));
        }
        if (bundle != null) {
            hashMap.put("content_info", bundle);
        }
        if (adRequestInfoParcel.versionCode >= 5) {
            hashMap.put("u_sd", Float.valueOf(adRequestInfoParcel.zzcgg));
            hashMap.put("sh", Integer.valueOf(adRequestInfoParcel.zzcgf));
            hashMap.put("sw", Integer.valueOf(adRequestInfoParcel.zzcge));
        } else {
            hashMap.put("u_sd", Float.valueOf(com_google_android_gms_internal_zzjh.zzcgg));
            hashMap.put("sh", Integer.valueOf(com_google_android_gms_internal_zzjh.zzcgf));
            hashMap.put("sw", Integer.valueOf(com_google_android_gms_internal_zzjh.zzcge));
        }
        if (adRequestInfoParcel.versionCode >= 6) {
            if (!TextUtils.isEmpty(adRequestInfoParcel.zzcgh)) {
                try {
                    hashMap.put("view_hierarchy", new JSONObject(adRequestInfoParcel.zzcgh));
                } catch (Throwable e2) {
                    zzb.zzd("Problem serializing view hierarchy to JSON", e2);
                }
            }
            hashMap.put("correlation_id", Long.valueOf(adRequestInfoParcel.zzcgi));
        }
        if (adRequestInfoParcel.versionCode >= 7) {
            hashMap.put("request_id", adRequestInfoParcel.zzcgj);
        }
        if (adRequestInfoParcel.versionCode >= 11 && adRequestInfoParcel.zzcgn != null) {
            hashMap.put("capability", adRequestInfoParcel.zzcgn.toBundle());
        }
        if (adRequestInfoParcel.versionCode >= 12 && !TextUtils.isEmpty(adRequestInfoParcel.zzcgo)) {
            hashMap.put("anchor", adRequestInfoParcel.zzcgo);
        }
        if (adRequestInfoParcel.versionCode >= 13) {
            hashMap.put("android_app_volume", Float.valueOf(adRequestInfoParcel.zzcgp));
        }
        if (adRequestInfoParcel.versionCode >= 18) {
            hashMap.put("android_app_muted", Boolean.valueOf(adRequestInfoParcel.zzcgv));
        }
        if (adRequestInfoParcel.versionCode >= 14 && adRequestInfoParcel.zzcgq > 0) {
            hashMap.put("target_api", Integer.valueOf(adRequestInfoParcel.zzcgq));
        }
        if (adRequestInfoParcel.versionCode >= 15) {
            hashMap.put("scroll_index", Integer.valueOf(adRequestInfoParcel.zzcgr == -1 ? -1 : adRequestInfoParcel.zzcgr));
        }
        if (adRequestInfoParcel.versionCode >= 16) {
            hashMap.put("_activity_context", Boolean.valueOf(adRequestInfoParcel.zzcgs));
        }
        if (adRequestInfoParcel.versionCode >= 18) {
            if (!TextUtils.isEmpty(adRequestInfoParcel.zzcgw)) {
                try {
                    hashMap.put("app_settings", new JSONObject(adRequestInfoParcel.zzcgw));
                } catch (Throwable e22) {
                    zzb.zzd("Problem creating json from app settings", e22);
                }
            }
            hashMap.put("render_in_browser", Boolean.valueOf(adRequestInfoParcel.zzbsh));
        }
        if (adRequestInfoParcel.versionCode >= 18) {
            hashMap.put("android_num_video_cache_tasks", Integer.valueOf(adRequestInfoParcel.zzcgx));
        }
        zza(hashMap);
        hashMap.put("cache_state", jSONObject);
        if (adRequestInfoParcel.versionCode >= 19) {
            hashMap.put("gct", adRequestInfoParcel.zzcgz);
        }
        if (zzb.zzbf(2)) {
            str = "Ad Request JSON: ";
            valueOf = String.valueOf(zzu.zzfz().zzan((Map) hashMap).toString(2));
            zzkn.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        return zzu.zzfz().zzan((Map) hashMap);
    }

    private static void zza(HashMap<String, Object> hashMap) {
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        bundle2.putString("cl", "134102376");
        bundle2.putString("rapid_rc", "dev");
        bundle2.putString("rapid_rollup", "HEAD");
        bundle.putBundle("build_meta", bundle2);
        bundle.putString("mf", Boolean.toString(((Boolean) zzdi.zzbft.get()).booleanValue()));
        hashMap.put("sdk_env", bundle);
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put("lat", valueOf3);
        hashMap2.put("long", valueOf4);
        hashMap2.put("time", valueOf2);
        hashMap.put("uule", hashMap2);
    }

    private static void zza(HashMap<String, Object> hashMap, AdRequestParcel adRequestParcel) {
        String zzub = zzkl.zzub();
        if (zzub != null) {
            hashMap.put("abf", zzub);
        }
        if (adRequestParcel.zzawd != -1) {
            hashMap.put("cust_age", zzcjy.format(new Date(adRequestParcel.zzawd)));
        }
        if (adRequestParcel.extras != null) {
            hashMap.put("extras", adRequestParcel.extras);
        }
        if (adRequestParcel.zzawe != -1) {
            hashMap.put("cust_gender", Integer.valueOf(adRequestParcel.zzawe));
        }
        if (adRequestParcel.zzawf != null) {
            hashMap.put("kw", adRequestParcel.zzawf);
        }
        if (adRequestParcel.zzawh != -1) {
            hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(adRequestParcel.zzawh));
        }
        if (adRequestParcel.zzawg) {
            hashMap.put("adtest", "on");
        }
        if (adRequestParcel.versionCode >= 2) {
            if (adRequestParcel.zzawi) {
                hashMap.put("d_imp_hdr", Integer.valueOf(1));
            }
            if (!TextUtils.isEmpty(adRequestParcel.zzawj)) {
                hashMap.put("ppid", adRequestParcel.zzawj);
            }
            if (adRequestParcel.zzawk != null) {
                zza((HashMap) hashMap, adRequestParcel.zzawk);
            }
        }
        if (adRequestParcel.versionCode >= 3 && adRequestParcel.zzawm != null) {
            hashMap.put("url", adRequestParcel.zzawm);
        }
        if (adRequestParcel.versionCode >= 5) {
            if (adRequestParcel.zzawo != null) {
                hashMap.put("custom_targeting", adRequestParcel.zzawo);
            }
            if (adRequestParcel.zzawp != null) {
                hashMap.put("category_exclusions", adRequestParcel.zzawp);
            }
            if (adRequestParcel.zzawq != null) {
                hashMap.put("request_agent", adRequestParcel.zzawq);
            }
        }
        if (adRequestParcel.versionCode >= 6 && adRequestParcel.zzawr != null) {
            hashMap.put("request_pkg", adRequestParcel.zzawr);
        }
        if (adRequestParcel.versionCode >= 7) {
            hashMap.put("is_designed_for_families", Boolean.valueOf(adRequestParcel.zzaws));
        }
    }

    private static void zza(HashMap<String, Object> hashMap, SearchAdRequestParcel searchAdRequestParcel) {
        Object obj;
        Object obj2 = null;
        if (Color.alpha(searchAdRequestParcel.zzazp) != 0) {
            hashMap.put("acolor", zzaw(searchAdRequestParcel.zzazp));
        }
        if (Color.alpha(searchAdRequestParcel.backgroundColor) != 0) {
            hashMap.put("bgcolor", zzaw(searchAdRequestParcel.backgroundColor));
        }
        if (!(Color.alpha(searchAdRequestParcel.zzazq) == 0 || Color.alpha(searchAdRequestParcel.zzazr) == 0)) {
            hashMap.put("gradientto", zzaw(searchAdRequestParcel.zzazq));
            hashMap.put("gradientfrom", zzaw(searchAdRequestParcel.zzazr));
        }
        if (Color.alpha(searchAdRequestParcel.zzazs) != 0) {
            hashMap.put("bcolor", zzaw(searchAdRequestParcel.zzazs));
        }
        hashMap.put("bthick", Integer.toString(searchAdRequestParcel.zzazt));
        switch (searchAdRequestParcel.zzazu) {
            case 0:
                obj = "none";
                break;
            case 1:
                obj = "dashed";
                break;
            case 2:
                obj = "dotted";
                break;
            case 3:
                obj = "solid";
                break;
            default:
                obj = null;
                break;
        }
        if (obj != null) {
            hashMap.put("btype", obj);
        }
        switch (searchAdRequestParcel.zzazv) {
            case 0:
                obj2 = "light";
                break;
            case 1:
                obj2 = "medium";
                break;
            case 2:
                obj2 = "dark";
                break;
        }
        if (obj2 != null) {
            hashMap.put("callbuttoncolor", obj2);
        }
        if (searchAdRequestParcel.zzazw != null) {
            hashMap.put("channel", searchAdRequestParcel.zzazw);
        }
        if (Color.alpha(searchAdRequestParcel.zzazx) != 0) {
            hashMap.put("dcolor", zzaw(searchAdRequestParcel.zzazx));
        }
        if (searchAdRequestParcel.zzazy != null) {
            hashMap.put("font", searchAdRequestParcel.zzazy);
        }
        if (Color.alpha(searchAdRequestParcel.zzazz) != 0) {
            hashMap.put("hcolor", zzaw(searchAdRequestParcel.zzazz));
        }
        hashMap.put("headersize", Integer.toString(searchAdRequestParcel.zzbaa));
        if (searchAdRequestParcel.zzbab != null) {
            hashMap.put("q", searchAdRequestParcel.zzbab);
        }
    }

    private static void zza(HashMap<String, Object> hashMap, zzjh com_google_android_gms_internal_zzjh, zza com_google_android_gms_internal_zzjl_zza, Bundle bundle, Bundle bundle2) {
        hashMap.put("am", Integer.valueOf(com_google_android_gms_internal_zzjh.zzclr));
        hashMap.put("cog", zzac(com_google_android_gms_internal_zzjh.zzcls));
        hashMap.put("coh", zzac(com_google_android_gms_internal_zzjh.zzclt));
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzjh.zzclu)) {
            hashMap.put("carrier", com_google_android_gms_internal_zzjh.zzclu);
        }
        hashMap.put("gl", com_google_android_gms_internal_zzjh.zzclv);
        if (com_google_android_gms_internal_zzjh.zzclw) {
            hashMap.put("simulator", Integer.valueOf(1));
        }
        if (com_google_android_gms_internal_zzjh.zzclx) {
            hashMap.put("is_sidewinder", Integer.valueOf(1));
        }
        hashMap.put("ma", zzac(com_google_android_gms_internal_zzjh.zzcly));
        hashMap.put("sp", zzac(com_google_android_gms_internal_zzjh.zzclz));
        hashMap.put("hl", com_google_android_gms_internal_zzjh.zzcma);
        if (!TextUtils.isEmpty(com_google_android_gms_internal_zzjh.zzcmb)) {
            hashMap.put("mv", com_google_android_gms_internal_zzjh.zzcmb);
        }
        hashMap.put("muv", Integer.valueOf(com_google_android_gms_internal_zzjh.zzcmc));
        if (com_google_android_gms_internal_zzjh.zzcmd != -2) {
            hashMap.put("cnt", Integer.valueOf(com_google_android_gms_internal_zzjh.zzcmd));
        }
        hashMap.put("gnt", Integer.valueOf(com_google_android_gms_internal_zzjh.zzcme));
        hashMap.put("pt", Integer.valueOf(com_google_android_gms_internal_zzjh.zzcmf));
        hashMap.put("rm", Integer.valueOf(com_google_android_gms_internal_zzjh.zzcmg));
        hashMap.put("riv", Integer.valueOf(com_google_android_gms_internal_zzjh.zzcmh));
        Bundle bundle3 = new Bundle();
        bundle3.putString("build", com_google_android_gms_internal_zzjh.zzcmm);
        Bundle bundle4 = new Bundle();
        bundle4.putBoolean("is_charging", com_google_android_gms_internal_zzjh.zzcmj);
        bundle4.putDouble("battery_level", com_google_android_gms_internal_zzjh.zzcmi);
        bundle3.putBundle("battery", bundle4);
        bundle4 = new Bundle();
        bundle4.putInt("active_network_state", com_google_android_gms_internal_zzjh.zzcml);
        bundle4.putBoolean("active_network_metered", com_google_android_gms_internal_zzjh.zzcmk);
        if (com_google_android_gms_internal_zzjl_zza != null) {
            Bundle bundle5 = new Bundle();
            bundle5.putInt("predicted_latency_micros", 0);
            bundle5.putLong("predicted_down_throughput_bps", 0);
            bundle5.putLong("predicted_up_throughput_bps", 0);
            bundle4.putBundle("predictions", bundle5);
        }
        bundle3.putBundle("network", bundle4);
        bundle4 = new Bundle();
        bundle4.putBoolean("is_browser_custom_tabs_capable", com_google_android_gms_internal_zzjh.zzcmn);
        bundle3.putBundle("browser", bundle4);
        if (bundle != null) {
            bundle3.putBundle("android_mem_info", zzg(bundle));
        }
        bundle4 = new Bundle();
        bundle4.putBundle("parental_controls", bundle2);
        bundle3.putBundle("play_store", bundle4);
        hashMap.put("device", bundle3);
    }

    private static void zza(HashMap<String, Object> hashMap, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("doritos", str);
        hashMap.put("pii", bundle);
    }

    private static Integer zzac(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }

    private static String zzaw(int i) {
        return String.format(Locale.US, "#%06x", new Object[]{Integer.valueOf(ViewCompat.MEASURED_SIZE_MASK & i)});
    }

    private static String zzc(NativeAdOptionsParcel nativeAdOptionsParcel) {
        switch (nativeAdOptionsParcel != null ? nativeAdOptionsParcel.zzblc : 0) {
            case 1:
                return "portrait";
            case 2:
                return "landscape";
            default:
                return "any";
        }
    }

    public static JSONObject zzc(AdResponseParcel adResponseParcel) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (adResponseParcel.zzbyj != null) {
            jSONObject.put("ad_base_url", adResponseParcel.zzbyj);
        }
        if (adResponseParcel.zzchf != null) {
            jSONObject.put("ad_size", adResponseParcel.zzchf);
        }
        jSONObject.put("native", adResponseParcel.zzaxl);
        if (adResponseParcel.zzaxl) {
            jSONObject.put("ad_json", adResponseParcel.body);
        } else {
            jSONObject.put("ad_html", adResponseParcel.body);
        }
        if (adResponseParcel.zzchh != null) {
            jSONObject.put("debug_dialog", adResponseParcel.zzchh);
        }
        if (adResponseParcel.zzchy != null) {
            jSONObject.put("debug_signals", adResponseParcel.zzchy);
        }
        if (adResponseParcel.zzchb != -1) {
            jSONObject.put("interstitial_timeout", ((double) adResponseParcel.zzchb) / 1000.0d);
        }
        if (adResponseParcel.orientation == zzu.zzgb().zzun()) {
            jSONObject.put("orientation", "portrait");
        } else if (adResponseParcel.orientation == zzu.zzgb().zzum()) {
            jSONObject.put("orientation", "landscape");
        }
        if (adResponseParcel.zzbsd != null) {
            jSONObject.put("click_urls", zzl(adResponseParcel.zzbsd));
        }
        if (adResponseParcel.zzbse != null) {
            jSONObject.put("impression_urls", zzl(adResponseParcel.zzbse));
        }
        if (adResponseParcel.zzche != null) {
            jSONObject.put("manual_impression_urls", zzl(adResponseParcel.zzche));
        }
        if (adResponseParcel.zzchk != null) {
            jSONObject.put("active_view", adResponseParcel.zzchk);
        }
        jSONObject.put("ad_is_javascript", adResponseParcel.zzchi);
        if (adResponseParcel.zzchj != null) {
            jSONObject.put("ad_passback_url", adResponseParcel.zzchj);
        }
        jSONObject.put("mediation", adResponseParcel.zzchc);
        jSONObject.put("custom_render_allowed", adResponseParcel.zzchl);
        jSONObject.put("content_url_opted_out", adResponseParcel.zzchm);
        jSONObject.put("prefetch", adResponseParcel.zzchn);
        if (adResponseParcel.zzbsj != -1) {
            jSONObject.put("refresh_interval_milliseconds", adResponseParcel.zzbsj);
        }
        if (adResponseParcel.zzchd != -1) {
            jSONObject.put("mediation_config_cache_time_milliseconds", adResponseParcel.zzchd);
        }
        if (!TextUtils.isEmpty(adResponseParcel.zzchq)) {
            jSONObject.put("gws_query_id", adResponseParcel.zzchq);
        }
        jSONObject.put("fluid", adResponseParcel.zzaxm ? "height" : "");
        jSONObject.put("native_express", adResponseParcel.zzaxn);
        if (adResponseParcel.zzchs != null) {
            jSONObject.put("video_start_urls", zzl(adResponseParcel.zzchs));
        }
        if (adResponseParcel.zzcht != null) {
            jSONObject.put("video_complete_urls", zzl(adResponseParcel.zzcht));
        }
        if (adResponseParcel.zzchr != null) {
            jSONObject.put("rewards", adResponseParcel.zzchr.zzsx());
        }
        jSONObject.put("use_displayed_impression", adResponseParcel.zzchu);
        jSONObject.put("auto_protection_configuration", adResponseParcel.zzchv);
        jSONObject.put("render_in_browser", adResponseParcel.zzbsh);
        return jSONObject;
    }

    private static Bundle zzg(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("runtime_free", Long.toString(bundle.getLong("runtime_free_memory", -1)));
        bundle2.putString("runtime_max", Long.toString(bundle.getLong("runtime_max_memory", -1)));
        bundle2.putString("runtime_total", Long.toString(bundle.getLong("runtime_total_memory", -1)));
        MemoryInfo memoryInfo = (MemoryInfo) bundle.getParcelable("debug_memory_info");
        if (memoryInfo != null) {
            bundle2.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
            bundle2.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
            bundle2.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
            bundle2.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
            bundle2.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
            bundle2.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
            bundle2.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
            bundle2.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
            bundle2.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
        }
        return bundle2;
    }

    @Nullable
    static JSONArray zzl(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }
}
