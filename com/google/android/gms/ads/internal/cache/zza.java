package com.google.android.gms.ads.internal.cache;

import android.content.Context;
import android.os.Binder;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.internal.zze.zzc;
import com.google.android.gms.internal.zzct;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkr;

@zziy
public class zza {
    @Nullable
    private Context mContext;
    private final Object zzakd = new Object();
    private final Runnable zzavq = new Runnable(this) {
        final /* synthetic */ zza zzavt;

        {
            this.zzavt = r1;
        }

        public void run() {
            this.zzavt.disconnect();
        }
    };
    @Nullable
    private zzc zzavr;
    @Nullable
    private zzf zzavs;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void connect() {
        /*
        r3 = this;
        r1 = r3.zzakd;
        monitor-enter(r1);
        r0 = r3.mContext;	 Catch:{ all -> 0x0024 }
        if (r0 == 0) goto L_0x000b;
    L_0x0007:
        r0 = r3.zzavr;	 Catch:{ all -> 0x0024 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x0024 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = new com.google.android.gms.ads.internal.cache.zza$3;	 Catch:{ all -> 0x0024 }
        r0.<init>(r3);	 Catch:{ all -> 0x0024 }
        r2 = new com.google.android.gms.ads.internal.cache.zza$4;	 Catch:{ all -> 0x0024 }
        r2.<init>(r3);	 Catch:{ all -> 0x0024 }
        r0 = r3.zza(r0, r2);	 Catch:{ all -> 0x0024 }
        r3.zzavr = r0;	 Catch:{ all -> 0x0024 }
        r0 = r3.zzavr;	 Catch:{ all -> 0x0024 }
        r0.zzatu();	 Catch:{ all -> 0x0024 }
        monitor-exit(r1);	 Catch:{ all -> 0x0024 }
        goto L_0x000c;
    L_0x0024:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0024 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.cache.zza.connect():void");
    }

    private void disconnect() {
        synchronized (this.zzakd) {
            if (this.zzavr == null) {
                return;
            }
            if (this.zzavr.isConnected() || this.zzavr.isConnecting()) {
                this.zzavr.disconnect();
            }
            this.zzavr = null;
            this.zzavs = null;
            Binder.flushPendingCommands();
            zzu.zzgp().zzuz();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initialize(android.content.Context r3) {
        /*
        r2 = this;
        if (r3 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r1 = r2.zzakd;
        monitor-enter(r1);
        r0 = r2.mContext;	 Catch:{ all -> 0x000c }
        if (r0 == 0) goto L_0x000f;
    L_0x000a:
        monitor-exit(r1);	 Catch:{ all -> 0x000c }
        goto L_0x0002;
    L_0x000c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x000c }
        throw r0;
    L_0x000f:
        r0 = r3.getApplicationContext();	 Catch:{ all -> 0x000c }
        r2.mContext = r0;	 Catch:{ all -> 0x000c }
        r0 = com.google.android.gms.internal.zzdi.zzbhl;	 Catch:{ all -> 0x000c }
        r0 = r0.get();	 Catch:{ all -> 0x000c }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x000c }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x000c }
        if (r0 == 0) goto L_0x0028;
    L_0x0023:
        r2.connect();	 Catch:{ all -> 0x000c }
    L_0x0026:
        monitor-exit(r1);	 Catch:{ all -> 0x000c }
        goto L_0x0002;
    L_0x0028:
        r0 = com.google.android.gms.internal.zzdi.zzbhk;	 Catch:{ all -> 0x000c }
        r0 = r0.get();	 Catch:{ all -> 0x000c }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x000c }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x000c }
        if (r0 == 0) goto L_0x0026;
    L_0x0036:
        r0 = new com.google.android.gms.ads.internal.cache.zza$2;	 Catch:{ all -> 0x000c }
        r0.<init>(r2);	 Catch:{ all -> 0x000c }
        r2.zza(r0);	 Catch:{ all -> 0x000c }
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.cache.zza.initialize(android.content.Context):void");
    }

    public CacheEntryParcel zza(CacheOffering cacheOffering) {
        CacheEntryParcel cacheEntryParcel;
        synchronized (this.zzakd) {
            if (this.zzavs == null) {
                cacheEntryParcel = new CacheEntryParcel();
            } else {
                try {
                    cacheEntryParcel = this.zzavs.zza(cacheOffering);
                } catch (Throwable e) {
                    zzb.zzb("Unable to call into cache service.", e);
                    cacheEntryParcel = new CacheEntryParcel();
                }
            }
        }
        return cacheEntryParcel;
    }

    protected zzc zza(zze.zzb com_google_android_gms_common_internal_zze_zzb, zzc com_google_android_gms_common_internal_zze_zzc) {
        return new zzc(this.mContext, zzu.zzgp().zzuy(), com_google_android_gms_common_internal_zze_zzb, com_google_android_gms_common_internal_zze_zzc);
    }

    protected void zza(zzct.zzb com_google_android_gms_internal_zzct_zzb) {
        zzu.zzgc().zza(com_google_android_gms_internal_zzct_zzb);
    }

    public void zzit() {
        if (((Boolean) zzdi.zzbhm.get()).booleanValue()) {
            synchronized (this.zzakd) {
                connect();
                zzu.zzfz();
                zzkr.zzcrf.removeCallbacks(this.zzavq);
                zzu.zzfz();
                zzkr.zzcrf.postDelayed(this.zzavq, ((Long) zzdi.zzbhn.get()).longValue());
            }
        }
    }
}
