package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public class zzfd implements zzev {
    private final Object zzakd = new Object();
    private final Map<String, zza> zzbnp = new HashMap();

    public interface zza {
        void zzbb(String str);

        void zzd(JSONObject jSONObject);
    }

    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        String str = (String) map.get("id");
        String str2 = (String) map.get("fail");
        String str3 = (String) map.get("fail_reason");
        String str4 = (String) map.get("result");
        synchronized (this.zzakd) {
            zza com_google_android_gms_internal_zzfd_zza = (zza) this.zzbnp.remove(str);
            if (com_google_android_gms_internal_zzfd_zza == null) {
                str2 = "Received result for unexpected method invocation: ";
                str = String.valueOf(str);
                zzb.zzdf(str.length() != 0 ? str2.concat(str) : new String(str2));
            } else if (!TextUtils.isEmpty(str2)) {
                com_google_android_gms_internal_zzfd_zza.zzbb(str3);
            } else if (str4 == null) {
                com_google_android_gms_internal_zzfd_zza.zzbb("No result.");
            } else {
                try {
                    com_google_android_gms_internal_zzfd_zza.zzd(new JSONObject(str4));
                } catch (JSONException e) {
                    com_google_android_gms_internal_zzfd_zza.zzbb(e.getMessage());
                }
            }
        }
    }
}
