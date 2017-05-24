package com.google.android.gms.gass.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Creator<GassRequestParcel> {
    static void zza(GassRequestParcel gassRequestParcel, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, gassRequestParcel.versionCode);
        zzb.zza(parcel, 2, gassRequestParcel.packageName, false);
        zzb.zza(parcel, 3, gassRequestParcel.aez, false);
        zzb.zzaj(parcel, zzcr);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmu(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzti(i);
    }

    public GassRequestParcel zzmu(Parcel parcel) {
        String str = null;
        int zzcq = zza.zzcq(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    str2 = zza.zzq(parcel, zzcp);
                    break;
                case 3:
                    str = zza.zzq(parcel, zzcp);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new GassRequestParcel(i, str2, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcq, parcel);
    }

    public GassRequestParcel[] zzti(int i) {
        return new GassRequestParcel[i];
    }
}
