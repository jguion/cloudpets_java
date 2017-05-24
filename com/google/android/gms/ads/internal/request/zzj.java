package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzj implements Creator<CapabilityParcel> {
    static void zza(CapabilityParcel capabilityParcel, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, capabilityParcel.versionCode);
        zzb.zza(parcel, 2, capabilityParcel.zzcib);
        zzb.zza(parcel, 3, capabilityParcel.zzcic);
        zzb.zza(parcel, 4, capabilityParcel.zzcid);
        zzb.zzaj(parcel, zzcr);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzp(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzat(i);
    }

    public CapabilityParcel[] zzat(int i) {
        return new CapabilityParcel[i];
    }

    public CapabilityParcel zzp(Parcel parcel) {
        boolean z = false;
        int zzcq = zza.zzcq(parcel);
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    z3 = zza.zzc(parcel, zzcp);
                    break;
                case 3:
                    z2 = zza.zzc(parcel, zzcp);
                    break;
                case 4:
                    z = zza.zzc(parcel, zzcp);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new CapabilityParcel(i, z3, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzcq, parcel);
    }
}
