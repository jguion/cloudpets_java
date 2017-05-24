package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzu;
import java.util.WeakHashMap;

@zziy
public final class zzji {
    private WeakHashMap<Context, zza> zzcmo = new WeakHashMap();

    private class zza {
        public final long zzcmp = zzu.zzgf().currentTimeMillis();
        public final zzjh zzcmq;
        final /* synthetic */ zzji zzcmr;

        public zza(zzji com_google_android_gms_internal_zzji, zzjh com_google_android_gms_internal_zzjh) {
            this.zzcmr = com_google_android_gms_internal_zzji;
            this.zzcmq = com_google_android_gms_internal_zzjh;
        }

        public boolean hasExpired() {
            return ((Long) zzdi.zzbdx.get()).longValue() + this.zzcmp < zzu.zzgf().currentTimeMillis();
        }
    }

    public zzjh zzy(Context context) {
        zza com_google_android_gms_internal_zzji_zza = (zza) this.zzcmo.get(context);
        zzjh zzsk = (com_google_android_gms_internal_zzji_zza == null || com_google_android_gms_internal_zzji_zza.hasExpired() || !((Boolean) zzdi.zzbdw.get()).booleanValue()) ? new com.google.android.gms.internal.zzjh.zza(context).zzsk() : new com.google.android.gms.internal.zzjh.zza(context, com_google_android_gms_internal_zzji_zza.zzcmq).zzsk();
        this.zzcmo.put(context, new zza(this, zzsk));
        return zzsk;
    }
}
