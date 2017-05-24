package com.google.android.gms.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.zzac;

@zziy
public class zzkz {
    private Handler mHandler = null;
    private final Object zzakd = new Object();
    private HandlerThread zzcsu = null;
    private int zzcsv = 0;

    public Looper zzuy() {
        Looper looper;
        synchronized (this.zzakd) {
            if (this.zzcsv != 0) {
                zzac.zzb(this.zzcsu, (Object) "Invalid state: mHandlerThread should already been initialized.");
            } else if (this.zzcsu == null) {
                zzkn.v("Starting the looper thread.");
                this.zzcsu = new HandlerThread("LooperProvider");
                this.zzcsu.start();
                this.mHandler = new Handler(this.zzcsu.getLooper());
                zzkn.v("Looper thread started.");
            } else {
                zzkn.v("Resuming the looper thread");
                this.zzakd.notifyAll();
            }
            this.zzcsv++;
            looper = this.zzcsu.getLooper();
        }
        return looper;
    }

    public void zzuz() {
        synchronized (this.zzakd) {
            zzac.zzb(this.zzcsv > 0, (Object) "Invalid state: release() called more times than expected.");
            int i = this.zzcsv - 1;
            this.zzcsv = i;
            if (i == 0) {
                this.mHandler.post(new Runnable(this) {
                    final /* synthetic */ zzkz zzcsw;

                    {
                        this.zzcsw = r1;
                    }

                    public void run() {
                        synchronized (this.zzcsw.zzakd) {
                            zzkn.v("Suspending the looper thread");
                            while (this.zzcsw.zzcsv == 0) {
                                try {
                                    this.zzcsw.zzakd.wait();
                                    zzkn.v("Looper thread resumed");
                                } catch (InterruptedException e) {
                                    zzkn.v("Looper thread interrupted.");
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
