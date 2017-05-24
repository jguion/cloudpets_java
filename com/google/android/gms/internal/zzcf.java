package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.zzc;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

public final class zzcf extends zzg<zzch> {
    private static final zzcf zzakm = new zzcf();

    private zzcf() {
        super("com.google.android.gms.ads.adshield.AdShieldCreatorImpl");
    }

    public static zzcg zzb(String str, Context context, boolean z) {
        if (zzc.zzapd().isGooglePlayServicesAvailable(context) == 0) {
            zzcg zzc = zzakm.zzc(str, context, z);
            if (zzc != null) {
                return zzc;
            }
        }
        return new zzce(str, context, z);
    }

    private zzcg zzc(String str, Context context, boolean z) {
        IBinder zza;
        zzd zzac = zze.zzac(context);
        if (z) {
            try {
                zza = ((zzch) zzcu(context)).zza(str, zzac);
            } catch (RemoteException e) {
                return null;
            } catch (zza e2) {
                return null;
            }
        }
        zza = ((zzch) zzcu(context)).zzb(str, zzac);
        return zzcg.zza.zzd(zza);
    }

    protected zzch zzb(IBinder iBinder) {
        return zzch.zza.zze(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzb(iBinder);
    }
}
