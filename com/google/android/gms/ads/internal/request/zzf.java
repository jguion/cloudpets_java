package com.google.android.gms.ads.internal.request;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzf implements Creator<AdRequestInfoParcel> {
    static void zza(AdRequestInfoParcel adRequestInfoParcel, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, adRequestInfoParcel.versionCode);
        zzb.zza(parcel, 2, adRequestInfoParcel.zzcft, false);
        zzb.zza(parcel, 3, adRequestInfoParcel.zzcfu, i, false);
        zzb.zza(parcel, 4, adRequestInfoParcel.zzaqz, i, false);
        zzb.zza(parcel, 5, adRequestInfoParcel.zzaqt, false);
        zzb.zza(parcel, 6, adRequestInfoParcel.applicationInfo, i, false);
        zzb.zza(parcel, 7, adRequestInfoParcel.zzcfv, i, false);
        zzb.zza(parcel, 8, adRequestInfoParcel.zzcfw, false);
        zzb.zza(parcel, 9, adRequestInfoParcel.zzcfx, false);
        zzb.zza(parcel, 10, adRequestInfoParcel.zzcfy, false);
        zzb.zza(parcel, 11, adRequestInfoParcel.zzaqv, i, false);
        zzb.zza(parcel, 12, adRequestInfoParcel.zzcfz, false);
        zzb.zzc(parcel, 13, adRequestInfoParcel.zzcga);
        zzb.zzb(parcel, 14, adRequestInfoParcel.zzarr, false);
        zzb.zza(parcel, 15, adRequestInfoParcel.zzcgb, false);
        zzb.zza(parcel, 16, adRequestInfoParcel.zzcgc);
        zzb.zza(parcel, 17, adRequestInfoParcel.zzcgd, i, false);
        zzb.zzc(parcel, 18, adRequestInfoParcel.zzcge);
        zzb.zzc(parcel, 19, adRequestInfoParcel.zzcgf);
        zzb.zza(parcel, 20, adRequestInfoParcel.zzcgg);
        zzb.zza(parcel, 21, adRequestInfoParcel.zzcgh, false);
        zzb.zza(parcel, 25, adRequestInfoParcel.zzcgi);
        zzb.zza(parcel, 26, adRequestInfoParcel.zzcgj, false);
        zzb.zzb(parcel, 27, adRequestInfoParcel.zzcgk, false);
        zzb.zza(parcel, 28, adRequestInfoParcel.zzaqs, false);
        zzb.zza(parcel, 29, adRequestInfoParcel.zzarn, i, false);
        zzb.zzb(parcel, 30, adRequestInfoParcel.zzcgl, false);
        zzb.zza(parcel, 31, adRequestInfoParcel.zzcgm);
        zzb.zza(parcel, 32, adRequestInfoParcel.zzcgn, i, false);
        zzb.zza(parcel, 33, adRequestInfoParcel.zzcgo, false);
        zzb.zza(parcel, 34, adRequestInfoParcel.zzcgp);
        zzb.zzc(parcel, 35, adRequestInfoParcel.zzcgq);
        zzb.zzc(parcel, 36, adRequestInfoParcel.zzcgr);
        zzb.zza(parcel, 37, adRequestInfoParcel.zzcgs);
        zzb.zza(parcel, 38, adRequestInfoParcel.zzcgt);
        zzb.zza(parcel, 39, adRequestInfoParcel.zzcgu, false);
        zzb.zza(parcel, 40, adRequestInfoParcel.zzcgv);
        zzb.zza(parcel, 41, adRequestInfoParcel.zzcgw, false);
        zzb.zza(parcel, 42, adRequestInfoParcel.zzbsh);
        zzb.zzc(parcel, 43, adRequestInfoParcel.zzcgx);
        zzb.zza(parcel, 44, adRequestInfoParcel.zzcgy, false);
        zzb.zza(parcel, 45, adRequestInfoParcel.zzcgz, false);
        zzb.zzaj(parcel, zzcr);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzm(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzaq(i);
    }

    public AdRequestInfoParcel[] zzaq(int i) {
        return new AdRequestInfoParcel[i];
    }

    public AdRequestInfoParcel zzm(Parcel parcel) {
        int zzcq = zza.zzcq(parcel);
        int i = 0;
        Bundle bundle = null;
        AdRequestParcel adRequestParcel = null;
        AdSizeParcel adSizeParcel = null;
        String str = null;
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        VersionInfoParcel versionInfoParcel = null;
        Bundle bundle2 = null;
        int i2 = 0;
        List list = null;
        Bundle bundle3 = null;
        boolean z = false;
        Messenger messenger = null;
        int i3 = 0;
        int i4 = 0;
        float f = 0.0f;
        String str5 = null;
        long j = 0;
        String str6 = null;
        List list2 = null;
        String str7 = null;
        NativeAdOptionsParcel nativeAdOptionsParcel = null;
        List list3 = null;
        long j2 = 0;
        CapabilityParcel capabilityParcel = null;
        String str8 = null;
        float f2 = 0.0f;
        boolean z2 = false;
        int i5 = 0;
        int i6 = 0;
        boolean z3 = false;
        boolean z4 = false;
        String str9 = null;
        String str10 = null;
        boolean z5 = false;
        int i7 = 0;
        Bundle bundle4 = null;
        String str11 = null;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    bundle = zza.zzs(parcel, zzcp);
                    break;
                case 3:
                    adRequestParcel = (AdRequestParcel) zza.zza(parcel, zzcp, AdRequestParcel.CREATOR);
                    break;
                case 4:
                    adSizeParcel = (AdSizeParcel) zza.zza(parcel, zzcp, AdSizeParcel.CREATOR);
                    break;
                case 5:
                    str = zza.zzq(parcel, zzcp);
                    break;
                case 6:
                    applicationInfo = (ApplicationInfo) zza.zza(parcel, zzcp, ApplicationInfo.CREATOR);
                    break;
                case 7:
                    packageInfo = (PackageInfo) zza.zza(parcel, zzcp, PackageInfo.CREATOR);
                    break;
                case 8:
                    str2 = zza.zzq(parcel, zzcp);
                    break;
                case 9:
                    str3 = zza.zzq(parcel, zzcp);
                    break;
                case 10:
                    str4 = zza.zzq(parcel, zzcp);
                    break;
                case 11:
                    versionInfoParcel = (VersionInfoParcel) zza.zza(parcel, zzcp, VersionInfoParcel.CREATOR);
                    break;
                case 12:
                    bundle2 = zza.zzs(parcel, zzcp);
                    break;
                case 13:
                    i2 = zza.zzg(parcel, zzcp);
                    break;
                case 14:
                    list = zza.zzae(parcel, zzcp);
                    break;
                case 15:
                    bundle3 = zza.zzs(parcel, zzcp);
                    break;
                case 16:
                    z = zza.zzc(parcel, zzcp);
                    break;
                case 17:
                    messenger = (Messenger) zza.zza(parcel, zzcp, Messenger.CREATOR);
                    break;
                case 18:
                    i3 = zza.zzg(parcel, zzcp);
                    break;
                case 19:
                    i4 = zza.zzg(parcel, zzcp);
                    break;
                case 20:
                    f = zza.zzl(parcel, zzcp);
                    break;
                case 21:
                    str5 = zza.zzq(parcel, zzcp);
                    break;
                case 25:
                    j = zza.zzi(parcel, zzcp);
                    break;
                case 26:
                    str6 = zza.zzq(parcel, zzcp);
                    break;
                case 27:
                    list2 = zza.zzae(parcel, zzcp);
                    break;
                case 28:
                    str7 = zza.zzq(parcel, zzcp);
                    break;
                case 29:
                    nativeAdOptionsParcel = (NativeAdOptionsParcel) zza.zza(parcel, zzcp, (Creator) NativeAdOptionsParcel.CREATOR);
                    break;
                case 30:
                    list3 = zza.zzae(parcel, zzcp);
                    break;
                case 31:
                    j2 = zza.zzi(parcel, zzcp);
                    break;
                case 32:
                    capabilityParcel = (CapabilityParcel) zza.zza(parcel, zzcp, CapabilityParcel.CREATOR);
                    break;
                case 33:
                    str8 = zza.zzq(parcel, zzcp);
                    break;
                case 34:
                    f2 = zza.zzl(parcel, zzcp);
                    break;
                case 35:
                    i5 = zza.zzg(parcel, zzcp);
                    break;
                case 36:
                    i6 = zza.zzg(parcel, zzcp);
                    break;
                case 37:
                    z3 = zza.zzc(parcel, zzcp);
                    break;
                case 38:
                    z4 = zza.zzc(parcel, zzcp);
                    break;
                case 39:
                    str9 = zza.zzq(parcel, zzcp);
                    break;
                case 40:
                    z2 = zza.zzc(parcel, zzcp);
                    break;
                case 41:
                    str10 = zza.zzq(parcel, zzcp);
                    break;
                case 42:
                    z5 = zza.zzc(parcel, zzcp);
                    break;
                case 43:
                    i7 = zza.zzg(parcel, zzcp);
                    break;
                case 44:
                    bundle4 = zza.zzs(parcel, zzcp);
                    break;
                case 45:
                    str11 = zza.zzq(parcel, zzcp);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new AdRequestInfoParcel(i, bundle, adRequestParcel, adSizeParcel, str, applicationInfo, packageInfo, str2, str3, str4, versionInfoParcel, bundle2, i2, list, bundle3, z, messenger, i3, i4, f, str5, j, str6, list2, str7, nativeAdOptionsParcel, list3, j2, capabilityParcel, str8, f2, z2, i5, i6, z3, z4, str9, str10, z5, i7, bundle4, str11);
        }
        throw new zza.zza("Overread allowed size end=" + zzcq, parcel);
    }
}
