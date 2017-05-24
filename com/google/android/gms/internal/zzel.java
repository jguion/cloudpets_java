package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.internal.zzea.zza;

@zziy
public class zzel extends zzg<zzea> {
    public zzel() {
        super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
    }

    protected zzea zzal(IBinder iBinder) {
        return zza.zzad(iBinder);
    }

    public zzdz zzb(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        Throwable e;
        try {
            return zzdz.zza.zzac(((zzea) zzcu(context)).zza(zze.zzac(context), zze.zzac(frameLayout), zze.zzac(frameLayout2), zzf.BA));
        } catch (RemoteException e2) {
            e = e2;
            zzb.zzd("Could not create remote NativeAdViewDelegate.", e);
            return null;
        } catch (zzg.zza e3) {
            e = e3;
            zzb.zzd("Could not create remote NativeAdViewDelegate.", e);
            return null;
        }
    }

    protected /* synthetic */ Object zzc(IBinder iBinder) {
        return zzal(iBinder);
    }
}
