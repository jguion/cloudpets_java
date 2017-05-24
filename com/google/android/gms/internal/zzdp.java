package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@zziy
public class zzdp {
    @Nullable
    private final zzdq zzalg;
    private final Map<String, zzdo> zzbio = new HashMap();

    public zzdp(@Nullable zzdq com_google_android_gms_internal_zzdq) {
        this.zzalg = com_google_android_gms_internal_zzdq;
    }

    public void zza(String str, zzdo com_google_android_gms_internal_zzdo) {
        this.zzbio.put(str, com_google_android_gms_internal_zzdo);
    }

    public void zza(String str, String str2, long j) {
        zzdm.zza(this.zzalg, (zzdo) this.zzbio.get(str2), j, str);
        this.zzbio.put(str, zzdm.zza(this.zzalg, j));
    }

    @Nullable
    public zzdq zzkz() {
        return this.zzalg;
    }
}
