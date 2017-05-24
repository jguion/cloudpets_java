package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzi implements Creator<AutoClickProtectionConfigurationParcel> {
    static void zza(AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, autoClickProtectionConfigurationParcel.versionCode);
        zzb.zza(parcel, 2, autoClickProtectionConfigurationParcel.zzchz);
        zzb.zzb(parcel, 3, autoClickProtectionConfigurationParcel.zzcia, false);
        zzb.zzaj(parcel, zzcr);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzo(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzas(i);
    }

    public AutoClickProtectionConfigurationParcel[] zzas(int i) {
        return new AutoClickProtectionConfigurationParcel[i];
    }

    public AutoClickProtectionConfigurationParcel zzo(Parcel parcel) {
        boolean z = false;
        int zzcq = zza.zzcq(parcel);
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    z = zza.zzc(parcel, zzcp);
                    break;
                case 3:
                    list = zza.zzae(parcel, zzcp);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new AutoClickProtectionConfigurationParcel(i, z, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzcq, parcel);
    }
}
