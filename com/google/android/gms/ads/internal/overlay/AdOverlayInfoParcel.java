package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.ads.internal.InterstitialAdParameterParcel;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzlt;

@zziy
public final class AdOverlayInfoParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final zzf CREATOR = new zzf();
    public final int orientation;
    public final String url;
    public final int versionCode;
    public final VersionInfoParcel zzaqv;
    public final AdLauncherIntentInfoParcel zzbye;
    public final zza zzbyf;
    public final zzg zzbyg;
    public final zzlt zzbyh;
    public final zzer zzbyi;
    public final String zzbyj;
    public final boolean zzbyk;
    public final String zzbyl;
    public final zzp zzbym;
    public final int zzbyn;
    public final zzex zzbyo;
    public final String zzbyp;
    public final InterstitialAdParameterParcel zzbyq;

    AdOverlayInfoParcel(int i, AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i2, int i3, String str3, VersionInfoParcel versionInfoParcel, IBinder iBinder6, String str4, InterstitialAdParameterParcel interstitialAdParameterParcel) {
        this.versionCode = i;
        this.zzbye = adLauncherIntentInfoParcel;
        this.zzbyf = (zza) zze.zzae(zzd.zza.zzfe(iBinder));
        this.zzbyg = (zzg) zze.zzae(zzd.zza.zzfe(iBinder2));
        this.zzbyh = (zzlt) zze.zzae(zzd.zza.zzfe(iBinder3));
        this.zzbyi = (zzer) zze.zzae(zzd.zza.zzfe(iBinder4));
        this.zzbyj = str;
        this.zzbyk = z;
        this.zzbyl = str2;
        this.zzbym = (zzp) zze.zzae(zzd.zza.zzfe(iBinder5));
        this.orientation = i2;
        this.zzbyn = i3;
        this.url = str3;
        this.zzaqv = versionInfoParcel;
        this.zzbyo = (zzex) zze.zzae(zzd.zza.zzfe(iBinder6));
        this.zzbyp = str4;
        this.zzbyq = interstitialAdParameterParcel;
    }

    public AdOverlayInfoParcel(zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzp com_google_android_gms_ads_internal_overlay_zzp, zzlt com_google_android_gms_internal_zzlt, int i, VersionInfoParcel versionInfoParcel, String str, InterstitialAdParameterParcel interstitialAdParameterParcel) {
        this.versionCode = 4;
        this.zzbye = null;
        this.zzbyf = com_google_android_gms_ads_internal_client_zza;
        this.zzbyg = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbyh = com_google_android_gms_internal_zzlt;
        this.zzbyi = null;
        this.zzbyj = null;
        this.zzbyk = false;
        this.zzbyl = null;
        this.zzbym = com_google_android_gms_ads_internal_overlay_zzp;
        this.orientation = i;
        this.zzbyn = 1;
        this.url = null;
        this.zzaqv = versionInfoParcel;
        this.zzbyo = null;
        this.zzbyp = str;
        this.zzbyq = interstitialAdParameterParcel;
    }

    public AdOverlayInfoParcel(zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzp com_google_android_gms_ads_internal_overlay_zzp, zzlt com_google_android_gms_internal_zzlt, boolean z, int i, VersionInfoParcel versionInfoParcel) {
        this.versionCode = 4;
        this.zzbye = null;
        this.zzbyf = com_google_android_gms_ads_internal_client_zza;
        this.zzbyg = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbyh = com_google_android_gms_internal_zzlt;
        this.zzbyi = null;
        this.zzbyj = null;
        this.zzbyk = z;
        this.zzbyl = null;
        this.zzbym = com_google_android_gms_ads_internal_overlay_zzp;
        this.orientation = i;
        this.zzbyn = 2;
        this.url = null;
        this.zzaqv = versionInfoParcel;
        this.zzbyo = null;
        this.zzbyp = null;
        this.zzbyq = null;
    }

    public AdOverlayInfoParcel(zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzer com_google_android_gms_internal_zzer, zzp com_google_android_gms_ads_internal_overlay_zzp, zzlt com_google_android_gms_internal_zzlt, boolean z, int i, String str, VersionInfoParcel versionInfoParcel, zzex com_google_android_gms_internal_zzex) {
        this.versionCode = 4;
        this.zzbye = null;
        this.zzbyf = com_google_android_gms_ads_internal_client_zza;
        this.zzbyg = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbyh = com_google_android_gms_internal_zzlt;
        this.zzbyi = com_google_android_gms_internal_zzer;
        this.zzbyj = null;
        this.zzbyk = z;
        this.zzbyl = null;
        this.zzbym = com_google_android_gms_ads_internal_overlay_zzp;
        this.orientation = i;
        this.zzbyn = 3;
        this.url = str;
        this.zzaqv = versionInfoParcel;
        this.zzbyo = com_google_android_gms_internal_zzex;
        this.zzbyp = null;
        this.zzbyq = null;
    }

    public AdOverlayInfoParcel(zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzer com_google_android_gms_internal_zzer, zzp com_google_android_gms_ads_internal_overlay_zzp, zzlt com_google_android_gms_internal_zzlt, boolean z, int i, String str, String str2, VersionInfoParcel versionInfoParcel, zzex com_google_android_gms_internal_zzex) {
        this.versionCode = 4;
        this.zzbye = null;
        this.zzbyf = com_google_android_gms_ads_internal_client_zza;
        this.zzbyg = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbyh = com_google_android_gms_internal_zzlt;
        this.zzbyi = com_google_android_gms_internal_zzer;
        this.zzbyj = str2;
        this.zzbyk = z;
        this.zzbyl = str;
        this.zzbym = com_google_android_gms_ads_internal_overlay_zzp;
        this.orientation = i;
        this.zzbyn = 3;
        this.url = null;
        this.zzaqv = versionInfoParcel;
        this.zzbyo = com_google_android_gms_internal_zzex;
        this.zzbyp = null;
        this.zzbyq = null;
    }

    public AdOverlayInfoParcel(AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzp com_google_android_gms_ads_internal_overlay_zzp, VersionInfoParcel versionInfoParcel) {
        this.versionCode = 4;
        this.zzbye = adLauncherIntentInfoParcel;
        this.zzbyf = com_google_android_gms_ads_internal_client_zza;
        this.zzbyg = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbyh = null;
        this.zzbyi = null;
        this.zzbyj = null;
        this.zzbyk = false;
        this.zzbyl = null;
        this.zzbym = com_google_android_gms_ads_internal_overlay_zzp;
        this.orientation = -1;
        this.zzbyn = 4;
        this.url = null;
        this.zzaqv = versionInfoParcel;
        this.zzbyo = null;
        this.zzbyp = null;
        this.zzbyq = null;
    }

    public static void zza(Intent intent, AdOverlayInfoParcel adOverlayInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public static AdOverlayInfoParcel zzb(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    IBinder zzpc() {
        return zze.zzac(this.zzbyf).asBinder();
    }

    IBinder zzpd() {
        return zze.zzac(this.zzbyg).asBinder();
    }

    IBinder zzpe() {
        return zze.zzac(this.zzbyh).asBinder();
    }

    IBinder zzpf() {
        return zze.zzac(this.zzbyi).asBinder();
    }

    IBinder zzpg() {
        return zze.zzac(this.zzbyo).asBinder();
    }

    IBinder zzph() {
        return zze.zzac(this.zzbym).asBinder();
    }
}
