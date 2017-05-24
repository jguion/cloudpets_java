package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.common.util.zzf;
import java.util.Map;

@zziy
public class zzfb implements zzev {
    static final Map<String, Integer> zzbnn = zzf.zza("resize", Integer.valueOf(1), "playVideo", Integer.valueOf(2), "storePicture", Integer.valueOf(3), "createCalendarEvent", Integer.valueOf(4), "setOrientationProperties", Integer.valueOf(5), "closeResizedAd", Integer.valueOf(6));
    private final zze zzbnl;
    private final zzhh zzbnm;

    public zzfb(zze com_google_android_gms_ads_internal_zze, zzhh com_google_android_gms_internal_zzhh) {
        this.zzbnl = com_google_android_gms_ads_internal_zze;
        this.zzbnm = com_google_android_gms_internal_zzhh;
    }

    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        int intValue = ((Integer) zzbnn.get((String) map.get("a"))).intValue();
        if (intValue == 5 || this.zzbnl == null || this.zzbnl.zzer()) {
            switch (intValue) {
                case 1:
                    this.zzbnm.execute(map);
                    return;
                case 3:
                    new zzhj(com_google_android_gms_internal_zzlt, map).execute();
                    return;
                case 4:
                    new zzhg(com_google_android_gms_internal_zzlt, map).execute();
                    return;
                case 5:
                    new zzhi(com_google_android_gms_internal_zzlt, map).execute();
                    return;
                case 6:
                    this.zzbnm.zzt(true);
                    return;
                default:
                    zzb.zzde("Unknown MRAID command called.");
                    return;
            }
        }
        this.zzbnl.zzv(null);
    }
}
