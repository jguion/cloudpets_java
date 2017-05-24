package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import java.util.Map;

@zziy
public final class zzet implements zzev {
    private void zzb(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        String str = (String) map.get("label");
        String str2 = (String) map.get("start_label");
        String str3 = (String) map.get("timestamp");
        if (TextUtils.isEmpty(str)) {
            zzb.zzdf("No label given for CSI tick.");
        } else if (TextUtils.isEmpty(str3)) {
            zzb.zzdf("No timestamp given for CSI tick.");
        } else {
            try {
                long zzd = zzd(Long.parseLong(str3));
                if (TextUtils.isEmpty(str2)) {
                    str2 = "native:view_load";
                }
                com_google_android_gms_internal_zzlt.zzwa().zza(str, str2, zzd);
            } catch (Throwable e) {
                zzb.zzd("Malformed timestamp for CSI tick.", e);
            }
        }
    }

    private void zzc(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        String str = (String) map.get(Param.VALUE);
        if (TextUtils.isEmpty(str)) {
            zzb.zzdf("No value given for CSI experiment.");
            return;
        }
        zzdq zzkz = com_google_android_gms_internal_zzlt.zzwa().zzkz();
        if (zzkz == null) {
            zzb.zzdf("No ticker for WebView, dropping experiment ID.");
        } else {
            zzkz.zzh("e", str);
        }
    }

    private long zzd(long j) {
        return (j - zzu.zzgf().currentTimeMillis()) + zzu.zzgf().elapsedRealtime();
    }

    private void zzd(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        String str = (String) map.get(FriendRecord.NAME);
        String str2 = (String) map.get(Param.VALUE);
        if (TextUtils.isEmpty(str2)) {
            zzb.zzdf("No value given for CSI extra.");
        } else if (TextUtils.isEmpty(str)) {
            zzb.zzdf("No name given for CSI extra.");
        } else {
            zzdq zzkz = com_google_android_gms_internal_zzlt.zzwa().zzkz();
            if (zzkz == null) {
                zzb.zzdf("No ticker for WebView, dropping extra parameter.");
            } else {
                zzkz.zzh(str, str2);
            }
        }
    }

    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        String str = (String) map.get("action");
        if ("tick".equals(str)) {
            zzb(com_google_android_gms_internal_zzlt, map);
        } else if ("experiment".equals(str)) {
            zzc(com_google_android_gms_internal_zzlt, map);
        } else if ("extra".equals(str)) {
            zzd(com_google_android_gms_internal_zzlt, map);
        }
    }
}
