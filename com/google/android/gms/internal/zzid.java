package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzhz.zza;

@zziy
public final class zzid extends zzg<zzhz> {
    public zzid() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }

    protected zzhz zzbc(IBinder iBinder) {
        return zza.zzaz(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzbc(iBinder);
    }

    public zzhy zzg(Activity activity) {
        try {
            return zzhy.zza.zzay(((zzhz) zzcu(activity)).zzp(zze.zzac(activity)));
        } catch (Throwable e) {
            zzb.zzd("Could not create remote InAppPurchaseManager.", e);
            return null;
        } catch (Throwable e2) {
            zzb.zzd("Could not create remote InAppPurchaseManager.", e2);
            return null;
        }
    }
}
