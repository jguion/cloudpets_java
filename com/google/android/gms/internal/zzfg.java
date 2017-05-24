package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;

@zziy
class zzfg implements zzev {
    zzfg() {
    }

    private int zzg(Map<String, String> map) throws NullPointerException, NumberFormatException {
        int parseInt = Integer.parseInt((String) map.get("playbackState"));
        return (parseInt < 0 || 3 < parseInt) ? 0 : parseInt;
    }

    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        Throwable e;
        if (((Boolean) zzdi.zzbef.get()).booleanValue()) {
            zzly com_google_android_gms_internal_zzly;
            zzly zzwb = com_google_android_gms_internal_zzlt.zzwb();
            if (zzwb == null) {
                try {
                    zzwb = new zzly(com_google_android_gms_internal_zzlt, Float.parseFloat((String) map.get("duration")));
                    com_google_android_gms_internal_zzlt.zza(zzwb);
                    com_google_android_gms_internal_zzly = zzwb;
                } catch (NullPointerException e2) {
                    e = e2;
                    zzb.zzb("Unable to parse videoMeta message.", e);
                    zzu.zzgd().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                } catch (NumberFormatException e3) {
                    e = e3;
                    zzb.zzb("Unable to parse videoMeta message.", e);
                    zzu.zzgd().zza(e, "VideoMetaGmsgHandler.onGmsg");
                    return;
                }
            }
            com_google_android_gms_internal_zzly = zzwb;
            boolean equals = "1".equals(map.get("muted"));
            float parseFloat = Float.parseFloat((String) map.get("currentTime"));
            int zzg = zzg(map);
            String str = (String) map.get("aspectRatio");
            float parseFloat2 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
            if (zzb.zzbf(3)) {
                zzb.zzdd(new StringBuilder(String.valueOf(str).length() + 79).append("Video Meta GMSG: isMuted : ").append(equals).append(" , playbackState : ").append(zzg).append(" , aspectRatio : ").append(str).toString());
            }
            com_google_android_gms_internal_zzly.zza(parseFloat, zzg, equals, parseFloat2);
        }
    }
}
