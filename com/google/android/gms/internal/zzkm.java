package com.google.android.gms.internal;

import java.util.concurrent.Future;

@zziy
public abstract class zzkm implements zzkt<Future> {
    private volatile Thread zzcql;
    private boolean zzcqm;
    private final Runnable zzw;

    public zzkm() {
        this.zzw = new Runnable(this) {
            final /* synthetic */ zzkm zzcqn;

            {
                this.zzcqn = r1;
            }

            public final void run() {
                this.zzcqn.zzcql = Thread.currentThread();
                this.zzcqn.zzfc();
            }
        };
        this.zzcqm = false;
    }

    public zzkm(boolean z) {
        this.zzw = /* anonymous class already generated */;
        this.zzcqm = z;
    }

    public final void cancel() {
        onStop();
        if (this.zzcql != null) {
            this.zzcql.interrupt();
        }
    }

    public abstract void onStop();

    public abstract void zzfc();

    public /* synthetic */ Object zzqw() {
        return zzuc();
    }

    public final Future zzuc() {
        return this.zzcqm ? zzkq.zza(1, this.zzw) : zzkq.zza(this.zzw);
    }
}
