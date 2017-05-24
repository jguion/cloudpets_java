package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zziy
public class zzey implements zzev {
    private final zzez zzbnj;

    public zzey(zzez com_google_android_gms_internal_zzez) {
        this.zzbnj = com_google_android_gms_internal_zzez;
    }

    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        float parseFloat;
        boolean equals = "1".equals(map.get("transparentBackground"));
        boolean equals2 = "1".equals(map.get("blur"));
        try {
            if (map.get("blurRadius") != null) {
                parseFloat = Float.parseFloat((String) map.get("blurRadius"));
                this.zzbnj.zzg(equals);
                this.zzbnj.zza(equals2, parseFloat);
            }
        } catch (Throwable e) {
            zzb.zzb("Fail to parse float", e);
        }
        parseFloat = 0.0f;
        this.zzbnj.zzg(equals);
        this.zzbnj.zza(equals2, parseFloat);
    }
}
