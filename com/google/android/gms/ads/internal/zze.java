package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzke.zza;

@zziy
public class zze {
    private final Context mContext;
    private final AutoClickProtectionConfigurationParcel zzamg;
    private boolean zzamh;

    public zze(Context context) {
        this(context, false);
    }

    public zze(Context context, @Nullable zza com_google_android_gms_internal_zzke_zza) {
        this.mContext = context;
        if (com_google_android_gms_internal_zzke_zza == null || com_google_android_gms_internal_zzke_zza.zzcop.zzchv == null) {
            this.zzamg = new AutoClickProtectionConfigurationParcel();
        } else {
            this.zzamg = com_google_android_gms_internal_zzke_zza.zzcop.zzchv;
        }
    }

    public zze(Context context, boolean z) {
        this.mContext = context;
        this.zzamg = new AutoClickProtectionConfigurationParcel(z);
    }

    public void recordClick() {
        this.zzamh = true;
    }

    public boolean zzer() {
        return !this.zzamg.zzchz || this.zzamh;
    }

    public void zzv(@Nullable String str) {
        if (str == null) {
            str = "";
        }
        zzb.zzde("Action was blocked because no touch was detected.");
        if (this.zzamg.zzchz && this.zzamg.zzcia != null) {
            for (String str2 : this.zzamg.zzcia) {
                if (!TextUtils.isEmpty(str2)) {
                    zzu.zzfz().zzc(this.mContext, "", str2.replace("{NAVIGATION_URL}", Uri.encode(str)));
                }
            }
        }
    }
}
