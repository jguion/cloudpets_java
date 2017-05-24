package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzd;
import java.util.concurrent.Future;

@zziy
public class zzfw {

    private static class zza<JavascriptEngine> extends zzlg<JavascriptEngine> {
        JavascriptEngine zzbqb;

        private zza() {
        }
    }

    private zzfv zza(Context context, VersionInfoParcel versionInfoParcel, final zza<zzfv> com_google_android_gms_internal_zzfw_zza_com_google_android_gms_internal_zzfv, zzau com_google_android_gms_internal_zzau, zzd com_google_android_gms_ads_internal_zzd) {
        zzfv com_google_android_gms_internal_zzfx = new zzfx(context, versionInfoParcel, com_google_android_gms_internal_zzau, com_google_android_gms_ads_internal_zzd);
        com_google_android_gms_internal_zzfw_zza_com_google_android_gms_internal_zzfv.zzbqb = com_google_android_gms_internal_zzfx;
        com_google_android_gms_internal_zzfx.zza(new com.google.android.gms.internal.zzfv.zza(this) {
            final /* synthetic */ zzfw zzbqa;

            public void zzmx() {
                com_google_android_gms_internal_zzfw_zza_com_google_android_gms_internal_zzfv.zzh((zzfv) com_google_android_gms_internal_zzfw_zza_com_google_android_gms_internal_zzfv.zzbqb);
            }
        });
        return com_google_android_gms_internal_zzfx;
    }

    public Future<zzfv> zza(Context context, VersionInfoParcel versionInfoParcel, String str, zzau com_google_android_gms_internal_zzau, zzd com_google_android_gms_ads_internal_zzd) {
        final Future com_google_android_gms_internal_zzfw_zza = new zza();
        final Context context2 = context;
        final VersionInfoParcel versionInfoParcel2 = versionInfoParcel;
        final zzau com_google_android_gms_internal_zzau2 = com_google_android_gms_internal_zzau;
        final zzd com_google_android_gms_ads_internal_zzd2 = com_google_android_gms_ads_internal_zzd;
        final String str2 = str;
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzfw zzbqa;

            public void run() {
                this.zzbqa.zza(context2, versionInfoParcel2, com_google_android_gms_internal_zzfw_zza, com_google_android_gms_internal_zzau2, com_google_android_gms_ads_internal_zzd2).zzbl(str2);
            }
        });
        return com_google_android_gms_internal_zzfw_zza;
    }
}
