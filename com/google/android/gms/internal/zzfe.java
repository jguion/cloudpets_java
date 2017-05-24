package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import java.util.Map;

@zziy
public class zzfe implements zzev {
    private final zza zzbnq;

    public interface zza {
        void zzb(RewardItemParcel rewardItemParcel);

        void zzfb();
    }

    public zzfe(zza com_google_android_gms_internal_zzfe_zza) {
        this.zzbnq = com_google_android_gms_internal_zzfe_zza;
    }

    public static void zza(zzlt com_google_android_gms_internal_zzlt, zza com_google_android_gms_internal_zzfe_zza) {
        com_google_android_gms_internal_zzlt.zzvr().zza("/reward", new zzfe(com_google_android_gms_internal_zzfe_zza));
    }

    private void zze(Map<String, String> map) {
        RewardItemParcel rewardItemParcel;
        try {
            int parseInt = Integer.parseInt((String) map.get("amount"));
            String str = (String) map.get(VoiceMessage.TYPE);
            if (!TextUtils.isEmpty(str)) {
                rewardItemParcel = new RewardItemParcel(str, parseInt);
                this.zzbnq.zzb(rewardItemParcel);
            }
        } catch (Throwable e) {
            zzb.zzd("Unable to parse reward amount.", e);
        }
        rewardItemParcel = null;
        this.zzbnq.zzb(rewardItemParcel);
    }

    private void zzf(Map<String, String> map) {
        this.zzbnq.zzfb();
    }

    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        String str = (String) map.get("action");
        if ("grant".equals(str)) {
            zze(map);
        } else if ("video_start".equals(str)) {
            zzf(map);
        }
    }
}
