package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zziy
public class zzdq {
    private final Object zzakd = new Object();
    boolean zzbhy;
    private final List<zzdo> zzbip = new LinkedList();
    private final Map<String, String> zzbiq = new LinkedHashMap();
    private String zzbir;
    private zzdo zzbis;
    @Nullable
    private zzdq zzbit;

    public zzdq(boolean z, String str, String str2) {
        this.zzbhy = z;
        this.zzbiq.put("action", str);
        this.zzbiq.put("ad_format", str2);
    }

    public boolean zza(zzdo com_google_android_gms_internal_zzdo, long j, String... strArr) {
        synchronized (this.zzakd) {
            for (String com_google_android_gms_internal_zzdo2 : strArr) {
                this.zzbip.add(new zzdo(j, com_google_android_gms_internal_zzdo2, com_google_android_gms_internal_zzdo));
            }
        }
        return true;
    }

    public boolean zza(@Nullable zzdo com_google_android_gms_internal_zzdo, String... strArr) {
        return (!this.zzbhy || com_google_android_gms_internal_zzdo == null) ? false : zza(com_google_android_gms_internal_zzdo, zzu.zzgf().elapsedRealtime(), strArr);
    }

    public void zzav(String str) {
        if (this.zzbhy) {
            synchronized (this.zzakd) {
                this.zzbir = str;
            }
        }
    }

    @Nullable
    public zzdo zzc(long j) {
        return !this.zzbhy ? null : new zzdo(j, null, null);
    }

    public void zzc(@Nullable zzdq com_google_android_gms_internal_zzdq) {
        synchronized (this.zzakd) {
            this.zzbit = com_google_android_gms_internal_zzdq;
        }
    }

    public void zzh(String str, String str2) {
        if (this.zzbhy && !TextUtils.isEmpty(str2)) {
            zzdk zztm = zzu.zzgd().zztm();
            if (zztm != null) {
                synchronized (this.zzakd) {
                    zztm.zzat(str).zza(this.zzbiq, str, str2);
                }
            }
        }
    }

    public zzdo zzla() {
        return zzc(zzu.zzgf().elapsedRealtime());
    }

    public void zzlb() {
        synchronized (this.zzakd) {
            this.zzbis = zzla();
        }
    }

    public String zzlc() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        synchronized (this.zzakd) {
            for (zzdo com_google_android_gms_internal_zzdo : this.zzbip) {
                long time = com_google_android_gms_internal_zzdo.getTime();
                String zzkx = com_google_android_gms_internal_zzdo.zzkx();
                zzdo com_google_android_gms_internal_zzdo2 = com_google_android_gms_internal_zzdo2.zzky();
                if (com_google_android_gms_internal_zzdo2 != null && time > 0) {
                    stringBuilder2.append(zzkx).append('.').append(time - com_google_android_gms_internal_zzdo2.getTime()).append(',');
                }
            }
            this.zzbip.clear();
            if (!TextUtils.isEmpty(this.zzbir)) {
                stringBuilder2.append(this.zzbir);
            } else if (stringBuilder2.length() > 0) {
                stringBuilder2.setLength(stringBuilder2.length() - 1);
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    public zzdo zzld() {
        zzdo com_google_android_gms_internal_zzdo;
        synchronized (this.zzakd) {
            com_google_android_gms_internal_zzdo = this.zzbis;
        }
        return com_google_android_gms_internal_zzdo;
    }

    Map<String, String> zzm() {
        Map<String, String> map;
        synchronized (this.zzakd) {
            zzdk zztm = zzu.zzgd().zztm();
            if (zztm == null || this.zzbit == null) {
                map = this.zzbiq;
            } else {
                map = zztm.zza(this.zzbiq, this.zzbit.zzm());
            }
        }
        return map;
    }
}
