package com.google.android.gms.ads.internal.reward.mediation.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Creator<RewardItemParcel> {
    static void zza(RewardItemParcel rewardItemParcel, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, rewardItemParcel.versionCode);
        zzb.zza(parcel, 2, rewardItemParcel.type, false);
        zzb.zzc(parcel, 3, rewardItemParcel.zzcny);
        zzb.zzaj(parcel, zzcr);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzt(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzba(i);
    }

    public RewardItemParcel[] zzba(int i) {
        return new RewardItemParcel[i];
    }

    public RewardItemParcel zzt(Parcel parcel) {
        int i = 0;
        int zzcq = zza.zzcq(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i2 = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzcp);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzcp);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new RewardItemParcel(i2, str, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcq, parcel);
    }
}
