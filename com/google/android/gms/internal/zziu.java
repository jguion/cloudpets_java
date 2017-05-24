package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzis.zza;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public class zziu implements zza<zzd> {
    private final boolean zzcfd;
    private final boolean zzcfe;

    public zziu(boolean z, boolean z2) {
        this.zzcfd = z;
        this.zzcfe = z2;
    }

    private zzlt zzb(zzlj<zzlt> com_google_android_gms_internal_zzlj_com_google_android_gms_internal_zzlt) {
        Throwable e;
        try {
            return (zzlt) com_google_android_gms_internal_zzlj_com_google_android_gms_internal_zzlt.get((long) ((Integer) zzdi.zzbgb.get()).intValue(), TimeUnit.SECONDS);
        } catch (Throwable e2) {
            zzb.zzd("InterruptedException occurred while waiting for video to load", e2);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e3) {
            e2 = e3;
            zzb.zzd("Exception occurred while waiting for video to load", e2);
        } catch (TimeoutException e4) {
            e2 = e4;
            zzb.zzd("Exception occurred while waiting for video to load", e2);
        } catch (CancellationException e5) {
            e2 = e5;
            zzb.zzd("Exception occurred while waiting for video to load", e2);
        }
        return null;
    }

    public /* synthetic */ zzi.zza zza(zzis com_google_android_gms_internal_zzis, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        return zzb(com_google_android_gms_internal_zzis, jSONObject);
    }

    public zzd zzb(zzis com_google_android_gms_internal_zzis, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        List<zzlj> zza = com_google_android_gms_internal_zzis.zza(jSONObject, "images", true, this.zzcfd, this.zzcfe);
        Future zza2 = com_google_android_gms_internal_zzis.zza(jSONObject, "app_icon", true, this.zzcfd);
        zzlj zzc = com_google_android_gms_internal_zzis.zzc(jSONObject, "video");
        Future zzg = com_google_android_gms_internal_zzis.zzg(jSONObject);
        List arrayList = new ArrayList();
        for (zzlj com_google_android_gms_internal_zzlj : zza) {
            arrayList.add((zzc) com_google_android_gms_internal_zzlj.get());
        }
        zzlt zzb = zzb(zzc);
        return new zzd(jSONObject.getString("headline"), arrayList, jSONObject.getString("body"), (zzdx) zza2.get(), jSONObject.getString("call_to_action"), jSONObject.optDouble("rating", -1.0d), jSONObject.optString("store"), jSONObject.optString(Param.PRICE), (com.google.android.gms.ads.internal.formats.zza) zzg.get(), new Bundle(), zzb != null ? zzb.zzwb() : null, zzb != null ? zzb.getView() : null);
    }
}
