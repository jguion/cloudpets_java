package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzg implements Creator<AdRequestParcel> {
    static void zza(AdRequestParcel adRequestParcel, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, adRequestParcel.versionCode);
        zzb.zza(parcel, 2, adRequestParcel.zzawd);
        zzb.zza(parcel, 3, adRequestParcel.extras, false);
        zzb.zzc(parcel, 4, adRequestParcel.zzawe);
        zzb.zzb(parcel, 5, adRequestParcel.zzawf, false);
        zzb.zza(parcel, 6, adRequestParcel.zzawg);
        zzb.zzc(parcel, 7, adRequestParcel.zzawh);
        zzb.zza(parcel, 8, adRequestParcel.zzawi);
        zzb.zza(parcel, 9, adRequestParcel.zzawj, false);
        zzb.zza(parcel, 10, adRequestParcel.zzawk, i, false);
        zzb.zza(parcel, 11, adRequestParcel.zzawl, i, false);
        zzb.zza(parcel, 12, adRequestParcel.zzawm, false);
        zzb.zza(parcel, 13, adRequestParcel.zzawn, false);
        zzb.zza(parcel, 14, adRequestParcel.zzawo, false);
        zzb.zzb(parcel, 15, adRequestParcel.zzawp, false);
        zzb.zza(parcel, 16, adRequestParcel.zzawq, false);
        zzb.zza(parcel, 17, adRequestParcel.zzawr, false);
        zzb.zza(parcel, 18, adRequestParcel.zzaws);
        zzb.zzaj(parcel, zzcr);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zze(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzt(i);
    }

    public AdRequestParcel zze(Parcel parcel) {
        int zzcq = zza.zzcq(parcel);
        int i = 0;
        long j = 0;
        Bundle bundle = null;
        int i2 = 0;
        List list = null;
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        String str = null;
        SearchAdRequestParcel searchAdRequestParcel = null;
        Location location = null;
        String str2 = null;
        Bundle bundle2 = null;
        Bundle bundle3 = null;
        List list2 = null;
        String str3 = null;
        String str4 = null;
        boolean z3 = false;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    j = zza.zzi(parcel, zzcp);
                    break;
                case 3:
                    bundle = zza.zzs(parcel, zzcp);
                    break;
                case 4:
                    i2 = zza.zzg(parcel, zzcp);
                    break;
                case 5:
                    list = zza.zzae(parcel, zzcp);
                    break;
                case 6:
                    z = zza.zzc(parcel, zzcp);
                    break;
                case 7:
                    i3 = zza.zzg(parcel, zzcp);
                    break;
                case 8:
                    z2 = zza.zzc(parcel, zzcp);
                    break;
                case 9:
                    str = zza.zzq(parcel, zzcp);
                    break;
                case 10:
                    searchAdRequestParcel = (SearchAdRequestParcel) zza.zza(parcel, zzcp, SearchAdRequestParcel.CREATOR);
                    break;
                case 11:
                    location = (Location) zza.zza(parcel, zzcp, Location.CREATOR);
                    break;
                case 12:
                    str2 = zza.zzq(parcel, zzcp);
                    break;
                case 13:
                    bundle2 = zza.zzs(parcel, zzcp);
                    break;
                case 14:
                    bundle3 = zza.zzs(parcel, zzcp);
                    break;
                case 15:
                    list2 = zza.zzae(parcel, zzcp);
                    break;
                case 16:
                    str3 = zza.zzq(parcel, zzcp);
                    break;
                case 17:
                    str4 = zza.zzq(parcel, zzcp);
                    break;
                case 18:
                    z3 = zza.zzc(parcel, zzcp);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new AdRequestParcel(i, j, bundle, i2, list, z, i3, z2, str, searchAdRequestParcel, location, str2, bundle2, bundle3, list2, str3, str4, z3);
        }
        throw new zza.zza("Overread allowed size end=" + zzcq, parcel);
    }

    public AdRequestParcel[] zzt(int i) {
        return new AdRequestParcel[i];
    }
}
