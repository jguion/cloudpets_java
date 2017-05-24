package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzu;
import org.json.JSONObject;

@zziy
public class zzfx implements zzfv {
    private final zzlt zzbkr;

    public zzfx(Context context, VersionInfoParcel versionInfoParcel, @Nullable zzau com_google_android_gms_internal_zzau, zzd com_google_android_gms_ads_internal_zzd) {
        this.zzbkr = zzu.zzga().zza(context, new AdSizeParcel(), false, false, com_google_android_gms_internal_zzau, versionInfoParcel, null, null, com_google_android_gms_ads_internal_zzd);
        this.zzbkr.getWebView().setWillNotDraw(true);
    }

    private void runOnUiThread(Runnable runnable) {
        if (zzm.zzjr().zzvf()) {
            runnable.run();
        } else {
            zzkr.zzcrf.post(runnable);
        }
    }

    public void destroy() {
        this.zzbkr.destroy();
    }

    public void zza(zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzer com_google_android_gms_internal_zzer, zzp com_google_android_gms_ads_internal_overlay_zzp, boolean z, zzex com_google_android_gms_internal_zzex, zzez com_google_android_gms_internal_zzez, zze com_google_android_gms_ads_internal_zze, zzhn com_google_android_gms_internal_zzhn) {
        this.zzbkr.zzvr().zza(com_google_android_gms_ads_internal_client_zza, com_google_android_gms_ads_internal_overlay_zzg, com_google_android_gms_internal_zzer, com_google_android_gms_ads_internal_overlay_zzp, z, com_google_android_gms_internal_zzex, com_google_android_gms_internal_zzez, new zze(this.zzbkr.getContext(), false), com_google_android_gms_internal_zzhn, null);
    }

    public void zza(final zzfv.zza com_google_android_gms_internal_zzfv_zza) {
        this.zzbkr.zzvr().zza(new zzlu.zza(this) {
            final /* synthetic */ zzfx zzbqe;

            public void zza(zzlt com_google_android_gms_internal_zzlt, boolean z) {
                com_google_android_gms_internal_zzfv_zza.zzmx();
            }
        });
    }

    public void zza(String str, zzev com_google_android_gms_internal_zzev) {
        this.zzbkr.zzvr().zza(str, com_google_android_gms_internal_zzev);
    }

    public void zza(final String str, final JSONObject jSONObject) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzfx zzbqe;

            public void run() {
                this.zzbqe.zzbkr.zza(str, jSONObject);
            }
        });
    }

    public void zzb(String str, zzev com_google_android_gms_internal_zzev) {
        this.zzbkr.zzvr().zzb(str, com_google_android_gms_internal_zzev);
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzbkr.zzb(str, jSONObject);
    }

    public void zzbk(String str) {
        final String format = String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", new Object[]{str});
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzfx zzbqe;

            public void run() {
                this.zzbqe.zzbkr.loadData(format, "text/html", Key.STRING_CHARSET_NAME);
            }
        });
    }

    public void zzbl(final String str) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzfx zzbqe;

            public void run() {
                this.zzbqe.zzbkr.loadUrl(str);
            }
        });
    }

    public void zzbm(final String str) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzfx zzbqe;

            public void run() {
                this.zzbqe.zzbkr.loadData(str, "text/html", Key.STRING_CHARSET_NAME);
            }
        });
    }

    public void zzj(final String str, final String str2) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzfx zzbqe;

            public void run() {
                this.zzbqe.zzbkr.zzj(str, str2);
            }
        });
    }

    public zzga zzmw() {
        return new zzgb(this);
    }
}
