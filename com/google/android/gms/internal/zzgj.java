package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzgs.zza;

@zziy
public final class zzgj extends zza {
    private final Object zzakd = new Object();
    private zzgl.zza zzbsr;
    private zzgi zzbss;

    public void onAdClicked() {
        synchronized (this.zzakd) {
            if (this.zzbss != null) {
                this.zzbss.zzef();
            }
        }
    }

    public void onAdClosed() {
        synchronized (this.zzakd) {
            if (this.zzbss != null) {
                this.zzbss.zzeg();
            }
        }
    }

    public void onAdFailedToLoad(int i) {
        synchronized (this.zzakd) {
            if (this.zzbsr != null) {
                this.zzbsr.zzaa(i == 3 ? 1 : 2);
                this.zzbsr = null;
            }
        }
    }

    public void onAdImpression() {
        synchronized (this.zzakd) {
            if (this.zzbss != null) {
                this.zzbss.zzek();
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.zzakd) {
            if (this.zzbss != null) {
                this.zzbss.zzeh();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAdLoaded() {
        /*
        r3 = this;
        r1 = r3.zzakd;
        monitor-enter(r1);
        r0 = r3.zzbsr;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0012;
    L_0x0007:
        r0 = r3.zzbsr;	 Catch:{ all -> 0x001d }
        r2 = 0;
        r0.zzaa(r2);	 Catch:{ all -> 0x001d }
        r0 = 0;
        r3.zzbsr = r0;	 Catch:{ all -> 0x001d }
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
    L_0x0011:
        return;
    L_0x0012:
        r0 = r3.zzbss;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001b;
    L_0x0016:
        r0 = r3.zzbss;	 Catch:{ all -> 0x001d }
        r0.zzej();	 Catch:{ all -> 0x001d }
    L_0x001b:
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        goto L_0x0011;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgj.onAdLoaded():void");
    }

    public void onAdOpened() {
        synchronized (this.zzakd) {
            if (this.zzbss != null) {
                this.zzbss.zzei();
            }
        }
    }

    public void zza(@Nullable zzgi com_google_android_gms_internal_zzgi) {
        synchronized (this.zzakd) {
            this.zzbss = com_google_android_gms_internal_zzgi;
        }
    }

    public void zza(zzgl.zza com_google_android_gms_internal_zzgl_zza) {
        synchronized (this.zzakd) {
            this.zzbsr = com_google_android_gms_internal_zzgl_zza;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(com.google.android.gms.internal.zzgt r4) {
        /*
        r3 = this;
        r1 = r3.zzakd;
        monitor-enter(r1);
        r0 = r3.zzbsr;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0012;
    L_0x0007:
        r0 = r3.zzbsr;	 Catch:{ all -> 0x001d }
        r2 = 0;
        r0.zza(r2, r4);	 Catch:{ all -> 0x001d }
        r0 = 0;
        r3.zzbsr = r0;	 Catch:{ all -> 0x001d }
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
    L_0x0011:
        return;
    L_0x0012:
        r0 = r3.zzbss;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001b;
    L_0x0016:
        r0 = r3.zzbss;	 Catch:{ all -> 0x001d }
        r0.zzej();	 Catch:{ all -> 0x001d }
    L_0x001b:
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        goto L_0x0011;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgj.zza(com.google.android.gms.internal.zzgt):void");
    }
}
