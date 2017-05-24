package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.internal.zzjl.zza;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@zziy
public class zziz {
    @Nullable
    public Location zzawl;
    @Nullable
    public String zzcfw;
    @Nullable
    public Bundle zzcgb;
    @Nullable
    public List<String> zzcgk = new ArrayList();
    @Nullable
    public Bundle zzciu;
    @Nullable
    public zza zzciv;
    @Nullable
    public String zzciw;
    public AdRequestInfoParcel zzcix;
    public zzjh zzciy;
    public JSONObject zzciz = new JSONObject();

    public zziz zza(zzjh com_google_android_gms_internal_zzjh) {
        this.zzciy = com_google_android_gms_internal_zzjh;
        return this;
    }

    public zziz zza(zza com_google_android_gms_internal_zzjl_zza) {
        this.zzciv = com_google_android_gms_internal_zzjl_zza;
        return this;
    }

    public zziz zzc(Location location) {
        this.zzawl = location;
        return this;
    }

    public zziz zzci(String str) {
        this.zzcfw = str;
        return this;
    }

    public zziz zzcj(String str) {
        this.zzciw = str;
        return this;
    }

    public zziz zze(Bundle bundle) {
        this.zzciu = bundle;
        return this;
    }

    public zziz zzf(Bundle bundle) {
        this.zzcgb = bundle;
        return this;
    }

    public zziz zzf(AdRequestInfoParcel adRequestInfoParcel) {
        this.zzcix = adRequestInfoParcel;
        return this;
    }

    public zziz zzj(JSONObject jSONObject) {
        this.zzciz = jSONObject;
        return this;
    }

    public zziz zzk(List<String> list) {
        if (list == null) {
            this.zzcgk.clear();
        }
        this.zzcgk = list;
        return this;
    }
}
