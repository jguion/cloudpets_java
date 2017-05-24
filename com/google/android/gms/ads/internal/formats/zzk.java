package com.google.android.gms.ads.internal.formats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzk implements Creator<NativeAdOptionsParcel> {
    static void zza(NativeAdOptionsParcel nativeAdOptionsParcel, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, nativeAdOptionsParcel.versionCode);
        zzb.zza(parcel, 2, nativeAdOptionsParcel.zzblb);
        zzb.zzc(parcel, 3, nativeAdOptionsParcel.zzblc);
        zzb.zza(parcel, 4, nativeAdOptionsParcel.zzbld);
        zzb.zzc(parcel, 5, nativeAdOptionsParcel.zzble);
        zzb.zza(parcel, 6, nativeAdOptionsParcel.zzblf, i, false);
        zzb.zzaj(parcel, zzcr);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzi(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzy(i);
    }

    public NativeAdOptionsParcel zzi(Parcel parcel) {
        int i = 0;
        int zzcq = zza.zzcq(parcel);
        VideoOptionsParcel videoOptionsParcel = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i3 = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    z2 = zza.zzc(parcel, zzcp);
                    break;
                case 3:
                    i2 = zza.zzg(parcel, zzcp);
                    break;
                case 4:
                    z = zza.zzc(parcel, zzcp);
                    break;
                case 5:
                    i = zza.zzg(parcel, zzcp);
                    break;
                case 6:
                    videoOptionsParcel = (VideoOptionsParcel) zza.zza(parcel, zzcp, VideoOptionsParcel.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new NativeAdOptionsParcel(i3, z2, i2, z, i, videoOptionsParcel);
        }
        throw new zza.zza("Overread allowed size end=" + zzcq, parcel);
    }

    public NativeAdOptionsParcel[] zzy(int i) {
        return new NativeAdOptionsParcel[i];
    }
}
