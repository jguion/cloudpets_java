package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.zze;
import java.util.concurrent.Callable;

@zziy
public class zzdh {
    private final Object zzakd = new Object();
    private boolean zzaom = false;
    @Nullable
    private SharedPreferences zzbak = null;

    public void initialize(Context context) {
        synchronized (this.zzakd) {
            if (this.zzaom) {
                return;
            }
            Context remoteContext = zze.getRemoteContext(context);
            if (remoteContext == null) {
                return;
            }
            this.zzbak = zzu.zzgj().zzn(remoteContext);
            this.zzaom = true;
        }
    }

    public <T> T zzd(final zzde<T> com_google_android_gms_internal_zzde_T) {
        synchronized (this.zzakd) {
            if (this.zzaom) {
                return zzle.zzb(new Callable<T>(this) {
                    final /* synthetic */ zzdh zzbam;

                    public T call() {
                        return com_google_android_gms_internal_zzde_T.zza(this.zzbam.zzbak);
                    }
                });
            }
            T zzkq = com_google_android_gms_internal_zzde_T.zzkq();
            return zzkq;
        }
    }
}
