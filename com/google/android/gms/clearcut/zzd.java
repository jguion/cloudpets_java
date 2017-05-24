package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.playlog.internal.PlayLoggerContext;

public class zzd implements Creator<LogEventParcelable> {
    static void zza(LogEventParcelable logEventParcelable, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, logEventParcelable.versionCode);
        zzb.zza(parcel, 2, logEventParcelable.uc, i, false);
        zzb.zza(parcel, 3, logEventParcelable.ud, false);
        zzb.zza(parcel, 4, logEventParcelable.ue, false);
        zzb.zza(parcel, 5, logEventParcelable.uf, false);
        zzb.zza(parcel, 6, logEventParcelable.ug, false);
        zzb.zza(parcel, 7, logEventParcelable.uh, false);
        zzb.zza(parcel, 8, logEventParcelable.ui);
        zzb.zzaj(parcel, zzcr);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzcb(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzfj(i);
    }

    public LogEventParcelable zzcb(Parcel parcel) {
        byte[][] bArr = null;
        int zzcq = zza.zzcq(parcel);
        int i = 0;
        boolean z = true;
        int[] iArr = null;
        String[] strArr = null;
        int[] iArr2 = null;
        byte[] bArr2 = null;
        PlayLoggerContext playLoggerContext = null;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    playLoggerContext = (PlayLoggerContext) zza.zza(parcel, zzcp, PlayLoggerContext.CREATOR);
                    break;
                case 3:
                    bArr2 = zza.zzt(parcel, zzcp);
                    break;
                case 4:
                    iArr2 = zza.zzw(parcel, zzcp);
                    break;
                case 5:
                    strArr = zza.zzac(parcel, zzcp);
                    break;
                case 6:
                    iArr = zza.zzw(parcel, zzcp);
                    break;
                case 7:
                    bArr = zza.zzu(parcel, zzcp);
                    break;
                case 8:
                    z = zza.zzc(parcel, zzcp);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new LogEventParcelable(i, playLoggerContext, bArr2, iArr2, strArr, iArr, bArr, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzcq, parcel);
    }

    public LogEventParcelable[] zzfj(int i) {
        return new LogEventParcelable[i];
    }
}
