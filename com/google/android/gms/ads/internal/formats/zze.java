package com.google.android.gms.ads.internal.formats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdx;
import com.google.android.gms.internal.zzed.zza;
import com.google.android.gms.internal.zziy;
import java.util.List;

@zziy
public class zze extends zza implements zzi.zza {
    private Bundle mExtras;
    private Object zzakd = new Object();
    private String zzbjq;
    private List<zzc> zzbjr;
    private String zzbjs;
    private String zzbju;
    @Nullable
    private zza zzbjy;
    private zzi zzbkb;
    private zzdx zzbkc;
    private String zzbkd;

    public zze(String str, List list, String str2, zzdx com_google_android_gms_internal_zzdx, String str3, String str4, @Nullable zza com_google_android_gms_ads_internal_formats_zza, Bundle bundle) {
        this.zzbjq = str;
        this.zzbjr = list;
        this.zzbjs = str2;
        this.zzbkc = com_google_android_gms_internal_zzdx;
        this.zzbju = str3;
        this.zzbkd = str4;
        this.zzbjy = com_google_android_gms_ads_internal_formats_zza;
        this.mExtras = bundle;
    }

    public void destroy() {
        this.zzbjq = null;
        this.zzbjr = null;
        this.zzbjs = null;
        this.zzbkc = null;
        this.zzbju = null;
        this.zzbkd = null;
        this.zzbjy = null;
        this.mExtras = null;
        this.zzakd = null;
        this.zzbkb = null;
    }

    public String getAdvertiser() {
        return this.zzbkd;
    }

    public String getBody() {
        return this.zzbjs;
    }

    public String getCallToAction() {
        return this.zzbju;
    }

    public String getCustomTemplateId() {
        return "";
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public String getHeadline() {
        return this.zzbjq;
    }

    public List getImages() {
        return this.zzbjr;
    }

    public void zzb(zzi com_google_android_gms_ads_internal_formats_zzi) {
        synchronized (this.zzakd) {
            this.zzbkb = com_google_android_gms_ads_internal_formats_zzi;
        }
    }

    public zzd zzlp() {
        return com.google.android.gms.dynamic.zze.zzac(this.zzbkb);
    }

    public String zzlq() {
        return "1";
    }

    public zza zzlr() {
        return this.zzbjy;
    }

    public zzdx zzlt() {
        return this.zzbkc;
    }
}
