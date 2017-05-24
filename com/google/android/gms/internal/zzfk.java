package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zzc;
import java.util.Map;
import java.util.concurrent.Future;

@zziy
public class zzfk implements zzev {
    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        zzfi zzgw = zzu.zzgw();
        if (!map.containsKey("abort")) {
            String str = (String) map.get("src");
            if (str == null) {
                zzb.zzdf("Precache video action is missing the src parameter.");
                return;
            }
            int parseInt;
            try {
                parseInt = Integer.parseInt((String) map.get("player"));
            } catch (NumberFormatException e) {
                parseInt = 0;
            }
            String str2 = map.containsKey("mimetype") ? (String) map.get("mimetype") : "";
            if (zzgw.zzf(com_google_android_gms_internal_zzlt)) {
                zzb.zzdf("Precache task already running.");
                return;
            }
            zzc.zzu(com_google_android_gms_internal_zzlt.zzdp());
            Future future = (Future) new zzfh(com_google_android_gms_internal_zzlt, com_google_android_gms_internal_zzlt.zzdp().zzamc.zza(com_google_android_gms_internal_zzlt, parseInt, str2), str).zzqw();
        } else if (!zzgw.zze(com_google_android_gms_internal_zzlt)) {
            zzb.zzdf("Precache abort but no preload task running.");
        }
    }
}
