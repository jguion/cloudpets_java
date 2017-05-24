package com.google.android.gms.gass.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd implements Creator<GassResponseParcel> {
    static void zza(GassResponseParcel gassResponseParcel, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, gassResponseParcel.versionCode);
        zzb.zza(parcel, 2, gassResponseParcel.zzbnv(), false);
        zzb.zzaj(parcel, zzcr);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmv(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zztj(i);
    }

    public GassResponseParcel zzmv(Parcel parcel) {
        int zzcq = zza.zzcq(parcel);
        int i = 0;
        byte[] bArr = null;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    bArr = zza.zzt(parcel, zzcp);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new GassResponseParcel(i, bArr);
        }
        throw new zza.zza("Overread allowed size end=" + zzcq, parcel);
    }

    public GassResponseParcel[] zztj(int i) {
        return new GassResponseParcel[i];
    }
}
