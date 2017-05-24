package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import java.util.Map;

@zziy
public final class zzeq implements zzev {
    private final zzer zzbma;

    public zzeq(zzer com_google_android_gms_internal_zzer) {
        this.zzbma = com_google_android_gms_internal_zzer;
    }

    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        String str = (String) map.get(FriendRecord.NAME);
        if (str == null) {
            zzb.zzdf("App event with no name parameter.");
        } else {
            this.zzbma.onAppEvent(str, (String) map.get("info"));
        }
    }
}
