package com.google.android.gms.ads.internal.reward.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Creator<RewardedVideoAdRequestParcel> {
    static void zza(RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, rewardedVideoAdRequestParcel.versionCode);
        zzb.zza(parcel, 2, rewardedVideoAdRequestParcel.zzcfu, i, false);
        zzb.zza(parcel, 3, rewardedVideoAdRequestParcel.zzaqt, false);
        zzb.zzaj(parcel, zzcr);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzs(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzax(i);
    }

    public RewardedVideoAdRequestParcel[] zzax(int i) {
        return new RewardedVideoAdRequestParcel[i];
    }

    public RewardedVideoAdRequestParcel zzs(Parcel parcel) {
        String str = null;
        int zzcq = zza.zzcq(parcel);
        int i = 0;
        AdRequestParcel adRequestParcel = null;
        while (parcel.dataPosition() < zzcq) {
            AdRequestParcel adRequestParcel2;
            int zzg;
            String str2;
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    String str3 = str;
                    adRequestParcel2 = adRequestParcel;
                    zzg = zza.zzg(parcel, zzcp);
                    str2 = str3;
                    break;
                case 2:
                    zzg = i;
                    AdRequestParcel adRequestParcel3 = (AdRequestParcel) zza.zza(parcel, zzcp, AdRequestParcel.CREATOR);
                    str2 = str;
                    adRequestParcel2 = adRequestParcel3;
                    break;
                case 3:
                    str2 = zza.zzq(parcel, zzcp);
                    adRequestParcel2 = adRequestParcel;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    str2 = str;
                    adRequestParcel2 = adRequestParcel;
                    zzg = i;
                    break;
            }
            i = zzg;
            adRequestParcel = adRequestParcel2;
            str = str2;
        }
        if (parcel.dataPosition() == zzcq) {
            return new RewardedVideoAdRequestParcel(i, adRequestParcel, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcq, parcel);
    }
}
