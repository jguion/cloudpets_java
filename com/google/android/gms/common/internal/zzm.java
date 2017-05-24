package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzm implements Callback {
    private final zza Cs;
    private final ArrayList<ConnectionCallbacks> Ct = new ArrayList();
    final ArrayList<ConnectionCallbacks> Cu = new ArrayList();
    private final ArrayList<OnConnectionFailedListener> Cv = new ArrayList();
    private volatile boolean Cw = false;
    private final AtomicInteger Cx = new AtomicInteger(0);
    private boolean Cy = false;
    private final Handler mHandler;
    private final Object zzakd = new Object();

    public interface zza {
        boolean isConnected();

        Bundle zzaoe();
    }

    public zzm(Looper looper, zza com_google_android_gms_common_internal_zzm_zza) {
        this.Cs = com_google_android_gms_common_internal_zzm_zza;
        this.mHandler = new Handler(looper, this);
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) message.obj;
            synchronized (this.zzakd) {
                if (this.Cw && this.Cs.isConnected() && this.Ct.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.Cs.zzaoe());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + message.what, new Exception());
        return false;
    }

    public boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        zzac.zzy(connectionCallbacks);
        synchronized (this.zzakd) {
            contains = this.Ct.contains(connectionCallbacks);
        }
        return contains;
    }

    public boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        zzac.zzy(onConnectionFailedListener);
        synchronized (this.zzakd) {
            contains = this.Cv.contains(onConnectionFailedListener);
        }
        return contains;
    }

    public void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        zzac.zzy(connectionCallbacks);
        synchronized (this.zzakd) {
            if (this.Ct.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 62).append("registerConnectionCallbacks(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.Ct.add(connectionCallbacks);
            }
        }
        if (this.Cs.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks));
        }
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzy(onConnectionFailedListener);
        synchronized (this.zzakd) {
            if (this.Cv.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 67).append("registerConnectionFailedListener(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.Cv.add(onConnectionFailedListener);
            }
        }
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        zzac.zzy(connectionCallbacks);
        synchronized (this.zzakd) {
            if (!this.Ct.remove(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 52).append("unregisterConnectionCallbacks(): listener ").append(valueOf).append(" not found").toString());
            } else if (this.Cy) {
                this.Cu.add(connectionCallbacks);
            }
        }
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzy(onConnectionFailedListener);
        synchronized (this.zzakd) {
            if (!this.Cv.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 57).append("unregisterConnectionFailedListener(): listener ").append(valueOf).append(" not found").toString());
            }
        }
    }

    public void zzaut() {
        this.Cw = false;
        this.Cx.incrementAndGet();
    }

    public void zzauu() {
        this.Cw = true;
    }

    public void zzgo(int i) {
        boolean z = false;
        if (Looper.myLooper() == this.mHandler.getLooper()) {
            z = true;
        }
        zzac.zza(z, (Object) "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzakd) {
            this.Cy = true;
            ArrayList arrayList = new ArrayList(this.Ct);
            int i2 = this.Cx.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) it.next();
                if (!this.Cw || this.Cx.get() != i2) {
                    break;
                } else if (this.Ct.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.Cu.clear();
            this.Cy = false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzn(com.google.android.gms.common.ConnectionResult r6) {
        /*
        r5 = this;
        r1 = 1;
        r0 = android.os.Looper.myLooper();
        r2 = r5.mHandler;
        r2 = r2.getLooper();
        if (r0 != r2) goto L_0x0046;
    L_0x000d:
        r0 = r1;
    L_0x000e:
        r2 = "onConnectionFailure must only be called on the Handler thread";
        com.google.android.gms.common.internal.zzac.zza(r0, r2);
        r0 = r5.mHandler;
        r0.removeMessages(r1);
        r1 = r5.zzakd;
        monitor-enter(r1);
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0054 }
        r2 = r5.Cv;	 Catch:{ all -> 0x0054 }
        r0.<init>(r2);	 Catch:{ all -> 0x0054 }
        r2 = r5.Cx;	 Catch:{ all -> 0x0054 }
        r2 = r2.get();	 Catch:{ all -> 0x0054 }
        r3 = r0.iterator();	 Catch:{ all -> 0x0054 }
    L_0x002c:
        r0 = r3.hasNext();	 Catch:{ all -> 0x0054 }
        if (r0 == 0) goto L_0x0057;
    L_0x0032:
        r0 = r3.next();	 Catch:{ all -> 0x0054 }
        r0 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r0;	 Catch:{ all -> 0x0054 }
        r4 = r5.Cw;	 Catch:{ all -> 0x0054 }
        if (r4 == 0) goto L_0x0044;
    L_0x003c:
        r4 = r5.Cx;	 Catch:{ all -> 0x0054 }
        r4 = r4.get();	 Catch:{ all -> 0x0054 }
        if (r4 == r2) goto L_0x0048;
    L_0x0044:
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
    L_0x0045:
        return;
    L_0x0046:
        r0 = 0;
        goto L_0x000e;
    L_0x0048:
        r4 = r5.Cv;	 Catch:{ all -> 0x0054 }
        r4 = r4.contains(r0);	 Catch:{ all -> 0x0054 }
        if (r4 == 0) goto L_0x002c;
    L_0x0050:
        r0.onConnectionFailed(r6);	 Catch:{ all -> 0x0054 }
        goto L_0x002c;
    L_0x0054:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
        throw r0;
    L_0x0057:
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzm.zzn(com.google.android.gms.common.ConnectionResult):void");
    }

    public void zzp(Bundle bundle) {
        boolean z = true;
        zzac.zza(Looper.myLooper() == this.mHandler.getLooper(), (Object) "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.zzakd) {
            zzac.zzbr(!this.Cy);
            this.mHandler.removeMessages(1);
            this.Cy = true;
            if (this.Cu.size() != 0) {
                z = false;
            }
            zzac.zzbr(z);
            ArrayList arrayList = new ArrayList(this.Ct);
            int i = this.Cx.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) it.next();
                if (!this.Cw || !this.Cs.isConnected() || this.Cx.get() != i) {
                    break;
                } else if (!this.Cu.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.Cu.clear();
            this.Cy = false;
        }
    }
}
