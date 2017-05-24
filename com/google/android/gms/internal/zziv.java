package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.internal.zzis.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public class zziv implements zza<zze> {
    private final boolean zzcfd;
    private final boolean zzcfe;

    public zziv(boolean z, boolean z2) {
        this.zzcfd = z;
        this.zzcfe = z2;
    }

    public /* synthetic */ zzi.zza zza(zzis com_google_android_gms_internal_zzis, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        return zzc(com_google_android_gms_internal_zzis, jSONObject);
    }

    public zze zzc(zzis com_google_android_gms_internal_zzis, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        List<zzlj> zza = com_google_android_gms_internal_zzis.zza(jSONObject, "images", true, this.zzcfd, this.zzcfe);
        Future zza2 = com_google_android_gms_internal_zzis.zza(jSONObject, "secondary_image", false, this.zzcfd);
        Future zzg = com_google_android_gms_internal_zzis.zzg(jSONObject);
        List arrayList = new ArrayList();
        for (zzlj com_google_android_gms_internal_zzlj : zza) {
            arrayList.add((zzc) com_google_android_gms_internal_zzlj.get());
        }
        return new zze(jSONObject.getString("headline"), arrayList, jSONObject.getString("body"), (zzdx) zza2.get(), jSONObject.getString("call_to_action"), jSONObject.getString("advertiser"), (com.google.android.gms.ads.internal.formats.zza) zzg.get(), new Bundle());
    }
}
