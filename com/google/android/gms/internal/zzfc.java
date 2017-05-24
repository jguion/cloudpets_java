package com.google.android.gms.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zziy
public final class zzfc implements zzev {
    private final zze zzbnl;
    private final zzhh zzbnm;
    private final zzex zzbno;

    public static class zza {
        private final zzlt zzbkr;

        public zza(zzlt com_google_android_gms_internal_zzlt) {
            this.zzbkr = com_google_android_gms_internal_zzlt;
        }

        public Intent zza(Context context, Map<String, String> map) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            String str = (String) map.get("u");
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (this.zzbkr != null) {
                str = zzu.zzfz().zza(this.zzbkr, str);
            }
            Uri parse = Uri.parse(str);
            boolean parseBoolean = Boolean.parseBoolean((String) map.get("use_first_package"));
            boolean parseBoolean2 = Boolean.parseBoolean((String) map.get("use_running_process"));
            Uri build = "http".equalsIgnoreCase(parse.getScheme()) ? parse.buildUpon().scheme("https").build() : "https".equalsIgnoreCase(parse.getScheme()) ? parse.buildUpon().scheme("http").build() : null;
            ArrayList arrayList = new ArrayList();
            Intent zzf = zzf(parse);
            Intent zzf2 = zzf(build);
            ResolveInfo zza = zza(context, zzf, arrayList);
            if (zza != null) {
                return zza(zzf, zza);
            }
            if (zzf2 != null) {
                ResolveInfo zza2 = zza(context, zzf2);
                if (zza2 != null) {
                    Intent zza3 = zza(zzf, zza2);
                    if (zza(context, zza3) != null) {
                        return zza3;
                    }
                }
            }
            if (arrayList.size() == 0) {
                return zzf;
            }
            if (parseBoolean2 && activityManager != null) {
                List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ResolveInfo resolveInfo = (ResolveInfo) it.next();
                        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                            if (runningAppProcessInfo.processName.equals(resolveInfo.activityInfo.packageName)) {
                                return zza(zzf, resolveInfo);
                            }
                        }
                    }
                }
            }
            return parseBoolean ? zza(zzf, (ResolveInfo) arrayList.get(0)) : zzf;
        }

        public Intent zza(Intent intent, ResolveInfo resolveInfo) {
            Intent intent2 = new Intent(intent);
            intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            return intent2;
        }

        public ResolveInfo zza(Context context, Intent intent) {
            return zza(context, intent, new ArrayList());
        }

        public ResolveInfo zza(Context context, Intent intent, ArrayList<ResolveInfo> arrayList) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            ResolveInfo resolveInfo;
            Collection queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (!(queryIntentActivities == null || resolveActivity == null)) {
                for (int i = 0; i < queryIntentActivities.size(); i++) {
                    resolveInfo = (ResolveInfo) queryIntentActivities.get(i);
                    if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo.activityInfo.name)) {
                        resolveInfo = resolveActivity;
                        break;
                    }
                }
            }
            resolveInfo = null;
            arrayList.addAll(queryIntentActivities);
            return resolveInfo;
        }

        public Intent zzf(Uri uri) {
            if (uri == null) {
                return null;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setData(uri);
            intent.setAction("android.intent.action.VIEW");
            return intent;
        }
    }

    public zzfc(zzex com_google_android_gms_internal_zzex, zze com_google_android_gms_ads_internal_zze, zzhh com_google_android_gms_internal_zzhh) {
        this.zzbno = com_google_android_gms_internal_zzex;
        this.zzbnl = com_google_android_gms_ads_internal_zze;
        this.zzbnm = com_google_android_gms_internal_zzhh;
    }

    private static boolean zzc(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int zzd(Map<String, String> map) {
        String str = (String) map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return zzu.zzgb().zzun();
            }
            if ("l".equalsIgnoreCase(str)) {
                return zzu.zzgb().zzum();
            }
            if ("c".equalsIgnoreCase(str)) {
                return zzu.zzgb().zzuo();
            }
        }
        return -1;
    }

    private static void zze(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        Context context = com_google_android_gms_internal_zzlt.getContext();
        if (TextUtils.isEmpty((String) map.get("u"))) {
            zzb.zzdf("Destination url cannot be empty.");
            return;
        }
        try {
            com_google_android_gms_internal_zzlt.zzvr().zza(new AdLauncherIntentInfoParcel(new zza(com_google_android_gms_internal_zzlt).zza(context, (Map) map)));
        } catch (ActivityNotFoundException e) {
            zzb.zzdf(e.getMessage());
        }
    }

    private void zzs(boolean z) {
        if (this.zzbnm != null) {
            this.zzbnm.zzt(z);
        }
    }

    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        String str = (String) map.get("a");
        if (str == null) {
            zzb.zzdf("Action missing from an open GMSG.");
        } else if (this.zzbnl == null || this.zzbnl.zzer()) {
            zzlu zzvr = com_google_android_gms_internal_zzlt.zzvr();
            if ("expand".equalsIgnoreCase(str)) {
                if (com_google_android_gms_internal_zzlt.zzvv()) {
                    zzb.zzdf("Cannot expand WebView that is already expanded.");
                    return;
                }
                zzs(false);
                zzvr.zza(zzc(map), zzd(map));
            } else if ("webapp".equalsIgnoreCase(str)) {
                str = (String) map.get("u");
                zzs(false);
                if (str != null) {
                    zzvr.zza(zzc(map), zzd(map), str);
                } else {
                    zzvr.zza(zzc(map), zzd(map), (String) map.get("html"), (String) map.get("baseurl"));
                }
            } else if ("in_app_purchase".equalsIgnoreCase(str)) {
                str = (String) map.get("product_id");
                String str2 = (String) map.get("report_urls");
                if (this.zzbno == null) {
                    return;
                }
                if (str2 == null || str2.isEmpty()) {
                    this.zzbno.zza(str, new ArrayList());
                } else {
                    this.zzbno.zza(str, new ArrayList(Arrays.asList(str2.split(" "))));
                }
            } else if ("app".equalsIgnoreCase(str) && "true".equalsIgnoreCase((String) map.get("system_browser"))) {
                zzs(true);
                zze(com_google_android_gms_internal_zzlt, map);
            } else {
                zzs(true);
                str = (String) map.get("u");
                zzvr.zza(new AdLauncherIntentInfoParcel((String) map.get("i"), !TextUtils.isEmpty(str) ? zzu.zzfz().zza(com_google_android_gms_internal_zzlt, str) : str, (String) map.get("m"), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
            }
        } else {
            this.zzbnl.zzv((String) map.get("u"));
        }
    }
}
