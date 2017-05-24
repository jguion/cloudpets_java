package com.google.android.gms.internal;

import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzis.zza;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public class zziw implements zza<zzf> {
    private final boolean zzcfd;

    public zziw(boolean z) {
        this.zzcfd = z;
    }

    private void zza(zzis com_google_android_gms_internal_zzis, JSONObject jSONObject, SimpleArrayMap<String, Future<zzc>> simpleArrayMap) throws JSONException {
        simpleArrayMap.put(jSONObject.getString(FriendRecord.NAME), com_google_android_gms_internal_zzis.zza(jSONObject, "image_value", this.zzcfd));
    }

    private void zza(JSONObject jSONObject, SimpleArrayMap<String, String> simpleArrayMap) throws JSONException {
        simpleArrayMap.put(jSONObject.getString(FriendRecord.NAME), jSONObject.getString("string_value"));
    }

    private <K, V> SimpleArrayMap<K, V> zzc(SimpleArrayMap<K, Future<V>> simpleArrayMap) throws InterruptedException, ExecutionException {
        SimpleArrayMap<K, V> simpleArrayMap2 = new SimpleArrayMap();
        for (int i = 0; i < simpleArrayMap.size(); i++) {
            simpleArrayMap2.put(simpleArrayMap.keyAt(i), ((Future) simpleArrayMap.valueAt(i)).get());
        }
        return simpleArrayMap2;
    }

    public /* synthetic */ zzi.zza zza(zzis com_google_android_gms_internal_zzis, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        return zzd(com_google_android_gms_internal_zzis, jSONObject);
    }

    public zzf zzd(zzis com_google_android_gms_internal_zzis, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        SimpleArrayMap simpleArrayMap2 = new SimpleArrayMap();
        Future zzg = com_google_android_gms_internal_zzis.zzg(jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("custom_assets");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            String string = jSONObject2.getString(VoiceMessage.TYPE);
            if ("string".equals(string)) {
                zza(jSONObject2, simpleArrayMap2);
            } else if ("image".equals(string)) {
                zza(com_google_android_gms_internal_zzis, jSONObject2, simpleArrayMap);
            } else {
                String str = "Unknown custom asset type: ";
                String valueOf = String.valueOf(string);
                zzb.zzdf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
        return new zzf(jSONObject.getString("custom_template_id"), zzc(simpleArrayMap), simpleArrayMap2, (com.google.android.gms.ads.internal.formats.zza) zzg.get());
    }
}
