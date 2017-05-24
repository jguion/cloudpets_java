package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzhq.zza;

@zziy
public final class zzho extends zzg<zzhq> {
    public zzho() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    protected zzhq zzas(IBinder iBinder) {
        return zza.zzau(iBinder);
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzas(iBinder);
    }

    public zzhp zzf(Activity activity) {
        try {
            return zzhp.zza.zzat(((zzhq) zzcu(activity)).zzo(zze.zzac(activity)));
        } catch (Throwable e) {
            zzb.zzd("Could not create remote AdOverlay.", e);
            return null;
        } catch (Throwable e2) {
            zzb.zzd("Could not create remote AdOverlay.", e2);
            return null;
        }
    }
}
