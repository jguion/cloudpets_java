package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.reward.client.zza.zza;
import com.google.android.gms.common.internal.zzab;

@zziy
public class zzjp extends zza {
    private final String zzcln;
    private final int zzcms;

    public zzjp(String str, int i) {
        this.zzcln = str;
        this.zzcms = i;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzjp)) {
            return false;
        }
        zzjp com_google_android_gms_internal_zzjp = (zzjp) obj;
        return zzab.equal(getType(), com_google_android_gms_internal_zzjp.getType()) && zzab.equal(Integer.valueOf(getAmount()), Integer.valueOf(com_google_android_gms_internal_zzjp.getAmount()));
    }

    public int getAmount() {
        return this.zzcms;
    }

    public String getType() {
        return this.zzcln;
    }
}
