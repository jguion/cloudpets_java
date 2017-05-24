package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;

@zziy
public class zzco extends zzcj {
    private final zzfz zzato;

    public zzco(Context context, AdSizeParcel adSizeParcel, zzke com_google_android_gms_internal_zzke, VersionInfoParcel versionInfoParcel, zzcq com_google_android_gms_internal_zzcq, zzfz com_google_android_gms_internal_zzfz) {
        super(context, adSizeParcel, com_google_android_gms_internal_zzke, versionInfoParcel, com_google_android_gms_internal_zzcq);
        this.zzato = com_google_android_gms_internal_zzfz;
        zzc(this.zzato);
        zzhj();
        zzk(3);
        String str = "Tracking ad unit: ";
        String valueOf = String.valueOf(this.zzasj.zzia());
        zzb.zzdd(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected void destroy() {
        synchronized (this.zzakd) {
            super.destroy();
            zzd(this.zzato);
        }
    }

    protected void zzb(JSONObject jSONObject) {
        this.zzato.zza("AFMA_updateActiveView", jSONObject);
    }

    public void zzhl() {
        destroy();
    }

    protected boolean zzhr() {
        return true;
    }
}
