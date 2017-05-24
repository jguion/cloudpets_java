package com.google.android.gms.ads.internal.formats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdx;
import com.google.android.gms.internal.zzeb.zza;
import com.google.android.gms.internal.zziy;
import java.util.List;

@zziy
public class zzd extends zza implements zzi.zza {
    private Bundle mExtras;
    private Object zzakd = new Object();
    private String zzbjq;
    private List<zzc> zzbjr;
    private String zzbjs;
    private zzdx zzbjt;
    private String zzbju;
    private double zzbjv;
    private String zzbjw;
    private String zzbjx;
    @Nullable
    private zza zzbjy;
    @Nullable
    private zzab zzbjz;
    @Nullable
    private View zzbka;
    private zzi zzbkb;

    public zzd(String str, List list, String str2, zzdx com_google_android_gms_internal_zzdx, String str3, double d, String str4, String str5, @Nullable zza com_google_android_gms_ads_internal_formats_zza, Bundle bundle, zzab com_google_android_gms_ads_internal_client_zzab, View view) {
        this.zzbjq = str;
        this.zzbjr = list;
        this.zzbjs = str2;
        this.zzbjt = com_google_android_gms_internal_zzdx;
        this.zzbju = str3;
        this.zzbjv = d;
        this.zzbjw = str4;
        this.zzbjx = str5;
        this.zzbjy = com_google_android_gms_ads_internal_formats_zza;
        this.mExtras = bundle;
        this.zzbjz = com_google_android_gms_ads_internal_client_zzab;
        this.zzbka = view;
    }

    public void destroy() {
        this.zzbjq = null;
        this.zzbjr = null;
        this.zzbjs = null;
        this.zzbjt = null;
        this.zzbju = null;
        this.zzbjv = 0.0d;
        this.zzbjw = null;
        this.zzbjx = null;
        this.zzbjy = null;
        this.mExtras = null;
        this.zzakd = null;
        this.zzbkb = null;
        this.zzbjz = null;
        this.zzbka = null;
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

    public String getPrice() {
        return this.zzbjx;
    }

    public double getStarRating() {
        return this.zzbjv;
    }

    public String getStore() {
        return this.zzbjw;
    }

    public void zzb(zzi com_google_android_gms_ads_internal_formats_zzi) {
        synchronized (this.zzakd) {
            this.zzbkb = com_google_android_gms_ads_internal_formats_zzi;
        }
    }

    public zzab zzdw() {
        return this.zzbjz;
    }

    public zzdx zzlo() {
        return this.zzbjt;
    }

    public com.google.android.gms.dynamic.zzd zzlp() {
        return zze.zzac(this.zzbkb);
    }

    public String zzlq() {
        return "2";
    }

    public zza zzlr() {
        return this.zzbjy;
    }

    public View zzls() {
        return this.zzbka;
    }
}
