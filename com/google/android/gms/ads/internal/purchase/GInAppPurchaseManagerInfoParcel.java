package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.dynamic.zzd.zza;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzhw;
import com.google.android.gms.internal.zziy;

@zziy
public final class GInAppPurchaseManagerInfoParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final zza CREATOR = new zza();
    public final int versionCode;
    public final zzk zzars;
    public final zzhw zzcbf;
    public final Context zzcbg;
    public final zzj zzcbh;

    GInAppPurchaseManagerInfoParcel(int i, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4) {
        this.versionCode = i;
        this.zzars = (zzk) zze.zzae(zza.zzfe(iBinder));
        this.zzcbf = (zzhw) zze.zzae(zza.zzfe(iBinder2));
        this.zzcbg = (Context) zze.zzae(zza.zzfe(iBinder3));
        this.zzcbh = (zzj) zze.zzae(zza.zzfe(iBinder4));
    }

    public GInAppPurchaseManagerInfoParcel(Context context, zzk com_google_android_gms_ads_internal_purchase_zzk, zzhw com_google_android_gms_internal_zzhw, zzj com_google_android_gms_ads_internal_purchase_zzj) {
        this.versionCode = 2;
        this.zzcbg = context;
        this.zzars = com_google_android_gms_ads_internal_purchase_zzk;
        this.zzcbf = com_google_android_gms_internal_zzhw;
        this.zzcbh = com_google_android_gms_ads_internal_purchase_zzj;
    }

    public static void zza(Intent intent, GInAppPurchaseManagerInfoParcel gInAppPurchaseManagerInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", gInAppPurchaseManagerInfoParcel);
        intent.putExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo", bundle);
    }

    public static GInAppPurchaseManagerInfoParcel zzc(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
            bundleExtra.setClassLoader(GInAppPurchaseManagerInfoParcel.class.getClassLoader());
            return (GInAppPurchaseManagerInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.internal.purchase.InAppPurchaseManagerInfo");
        } catch (Exception e) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    IBinder zzqk() {
        return zze.zzac(this.zzcbh).asBinder();
    }

    IBinder zzql() {
        return zze.zzac(this.zzars).asBinder();
    }

    IBinder zzqm() {
        return zze.zzac(this.zzcbf).asBinder();
    }

    IBinder zzqn() {
        return zze.zzac(this.zzcbg).asBinder();
    }
}
