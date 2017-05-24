package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;

@zziy
public class zzhi {
    private final zzlt zzbkr;
    private final boolean zzbvj;
    private final String zzbvk;

    public zzhi(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        this.zzbkr = com_google_android_gms_internal_zzlt;
        this.zzbvk = (String) map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzbvj = Boolean.parseBoolean((String) map.get("allowOrientationChange"));
        } else {
            this.zzbvj = true;
        }
    }

    public void execute() {
        if (this.zzbkr == null) {
            zzb.zzdf("AdWebView is null");
            return;
        }
        int zzun = "portrait".equalsIgnoreCase(this.zzbvk) ? zzu.zzgb().zzun() : "landscape".equalsIgnoreCase(this.zzbvk) ? zzu.zzgb().zzum() : this.zzbvj ? -1 : zzu.zzgb().zzuo();
        this.zzbkr.setRequestedOrientation(zzun);
    }
}
