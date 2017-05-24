package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzs;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzqe<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> wF = new ThreadLocal<Boolean>() {
        protected /* synthetic */ Object initialValue() {
            return zzaqv();
        }

        protected Boolean zzaqv() {
            return Boolean.valueOf(false);
        }
    };
    private R vU;
    private final Object wG;
    protected final zza<R> wH;
    protected final WeakReference<GoogleApiClient> wI;
    private final ArrayList<com.google.android.gms.common.api.PendingResult.zza> wJ;
    private ResultCallback<? super R> wK;
    private final AtomicReference<zzb> wL;
    private zzb wM;
    private volatile boolean wN;
    private boolean wO;
    private zzs wP;
    private volatile zzrp<R> wQ;
    private boolean wR;
    private boolean zzak;
    private final CountDownLatch zzamx;

    public static class zza<R extends Result> extends Handler {
        public zza() {
            this(Looper.getMainLooper());
        }

        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    zzb((ResultCallback) pair.first, (Result) pair.second);
                    return;
                case 2:
                    ((zzqe) message.obj).zzaa(Status.wb);
                    return;
                default:
                    Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, new Exception());
                    return;
            }
        }

        public void zza(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        public void zza(zzqe<R> com_google_android_gms_internal_zzqe_R, long j) {
            sendMessageDelayed(obtainMessage(2, com_google_android_gms_internal_zzqe_R), j);
        }

        public void zzaqw() {
            removeMessages(2);
        }

        protected void zzb(ResultCallback<? super R> resultCallback, R r) {
            try {
                resultCallback.onResult(r);
            } catch (RuntimeException e) {
                zzqe.zze(r);
                throw e;
            }
        }
    }

    private final class zzb {
        final /* synthetic */ zzqe wS;

        private zzb(zzqe com_google_android_gms_internal_zzqe) {
            this.wS = com_google_android_gms_internal_zzqe;
        }

        protected void finalize() throws Throwable {
            zzqe.zze(this.wS.vU);
            super.finalize();
        }
    }

    @Deprecated
    zzqe() {
        this.wG = new Object();
        this.zzamx = new CountDownLatch(1);
        this.wJ = new ArrayList();
        this.wL = new AtomicReference();
        this.wR = false;
        this.wH = new zza(Looper.getMainLooper());
        this.wI = new WeakReference(null);
    }

    @Deprecated
    protected zzqe(Looper looper) {
        this.wG = new Object();
        this.zzamx = new CountDownLatch(1);
        this.wJ = new ArrayList();
        this.wL = new AtomicReference();
        this.wR = false;
        this.wH = new zza(looper);
        this.wI = new WeakReference(null);
    }

    protected zzqe(GoogleApiClient googleApiClient) {
        this.wG = new Object();
        this.zzamx = new CountDownLatch(1);
        this.wJ = new ArrayList();
        this.wL = new AtomicReference();
        this.wR = false;
        this.wH = new zza(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.wI = new WeakReference(googleApiClient);
    }

    private R get() {
        R r;
        boolean z = true;
        synchronized (this.wG) {
            if (this.wN) {
                z = false;
            }
            zzac.zza(z, (Object) "Result has already been consumed.");
            zzac.zza(isReady(), (Object) "Result is not ready.");
            r = this.vU;
            this.vU = null;
            this.wK = null;
            this.wN = true;
        }
        zzaqr();
        return r;
    }

    private void zzaqr() {
        zzb com_google_android_gms_internal_zzrq_zzb = (zzb) this.wL.getAndSet(null);
        if (com_google_android_gms_internal_zzrq_zzb != null) {
            com_google_android_gms_internal_zzrq_zzb.zzc(this);
        }
    }

    private void zzd(R r) {
        this.vU = r;
        this.wP = null;
        this.zzamx.countDown();
        Status status = this.vU.getStatus();
        if (this.zzak) {
            this.wK = null;
        } else if (this.wK != null) {
            this.wH.zzaqw();
            this.wH.zza(this.wK, get());
        } else if (this.vU instanceof Releasable) {
            this.wM = new zzb();
        }
        Iterator it = this.wJ.iterator();
        while (it.hasNext()) {
            ((com.google.android.gms.common.api.PendingResult.zza) it.next()).zzv(status);
        }
        this.wJ.clear();
    }

    public static void zze(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    public final R await() {
        boolean z = true;
        zzac.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread");
        zzac.zza(!this.wN, (Object) "Result has already been consumed");
        if (this.wQ != null) {
            z = false;
        }
        zzac.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            this.zzamx.await();
        } catch (InterruptedException e) {
            zzaa(Status.vZ);
        }
        zzac.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        boolean z2 = j <= 0 || Looper.myLooper() != Looper.getMainLooper();
        zzac.zza(z2, (Object) "await must not be called on the UI thread when time is greater than zero.");
        zzac.zza(!this.wN, (Object) "Result has already been consumed.");
        if (this.wQ != null) {
            z = false;
        }
        zzac.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            if (!this.zzamx.await(j, timeUnit)) {
                zzaa(Status.wb);
            }
        } catch (InterruptedException e) {
            zzaa(Status.vZ);
        }
        zzac.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
        r2 = this;
        r1 = r2.wG;
        monitor-enter(r1);
        r0 = r2.zzak;	 Catch:{ all -> 0x0029 }
        if (r0 != 0) goto L_0x000b;
    L_0x0007:
        r0 = r2.wN;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = r2.wP;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x0016;
    L_0x0011:
        r0 = r2.wP;	 Catch:{ RemoteException -> 0x002c }
        r0.cancel();	 Catch:{ RemoteException -> 0x002c }
    L_0x0016:
        r0 = r2.vU;	 Catch:{ all -> 0x0029 }
        zze(r0);	 Catch:{ all -> 0x0029 }
        r0 = 1;
        r2.zzak = r0;	 Catch:{ all -> 0x0029 }
        r0 = com.google.android.gms.common.api.Status.wc;	 Catch:{ all -> 0x0029 }
        r0 = r2.zzc(r0);	 Catch:{ all -> 0x0029 }
        r2.zzd(r0);	 Catch:{ all -> 0x0029 }
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        goto L_0x000c;
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        throw r0;
    L_0x002c:
        r0 = move-exception;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqe.cancel():void");
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.wG) {
            z = this.zzak;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zzamx.getCount() == 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r6) {
        /*
        r5 = this;
        r0 = 1;
        r1 = 0;
        r3 = r5.wG;
        monitor-enter(r3);
        if (r6 != 0) goto L_0x000c;
    L_0x0007:
        r0 = 0;
        r5.wK = r0;	 Catch:{ all -> 0x0027 }
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
    L_0x000b:
        return;
    L_0x000c:
        r2 = r5.wN;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002a;
    L_0x0010:
        r2 = r0;
    L_0x0011:
        r4 = "Result has already been consumed.";
        com.google.android.gms.common.internal.zzac.zza(r2, r4);	 Catch:{ all -> 0x0027 }
        r2 = r5.wQ;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002c;
    L_0x001a:
        r1 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.zzac.zza(r0, r1);	 Catch:{ all -> 0x0027 }
        r0 = r5.isCanceled();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x002e;
    L_0x0025:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r2 = r1;
        goto L_0x0011;
    L_0x002c:
        r0 = r1;
        goto L_0x001a;
    L_0x002e:
        r0 = r5.isReady();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x003f;
    L_0x0034:
        r0 = r5.wH;	 Catch:{ all -> 0x0027 }
        r1 = r5.get();	 Catch:{ all -> 0x0027 }
        r0.zza(r6, r1);	 Catch:{ all -> 0x0027 }
    L_0x003d:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x003f:
        r5.wK = r6;	 Catch:{ all -> 0x0027 }
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqe.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        r3 = r6.wG;
        monitor-enter(r3);
        if (r7 != 0) goto L_0x000c;
    L_0x0007:
        r0 = 0;
        r6.wK = r0;	 Catch:{ all -> 0x0027 }
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
    L_0x000b:
        return;
    L_0x000c:
        r2 = r6.wN;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002a;
    L_0x0010:
        r2 = r0;
    L_0x0011:
        r4 = "Result has already been consumed.";
        com.google.android.gms.common.internal.zzac.zza(r2, r4);	 Catch:{ all -> 0x0027 }
        r2 = r6.wQ;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002c;
    L_0x001a:
        r1 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.zzac.zza(r0, r1);	 Catch:{ all -> 0x0027 }
        r0 = r6.isCanceled();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x002e;
    L_0x0025:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r2 = r1;
        goto L_0x0011;
    L_0x002c:
        r0 = r1;
        goto L_0x001a;
    L_0x002e:
        r0 = r6.isReady();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x003f;
    L_0x0034:
        r0 = r6.wH;	 Catch:{ all -> 0x0027 }
        r1 = r6.get();	 Catch:{ all -> 0x0027 }
        r0.zza(r7, r1);	 Catch:{ all -> 0x0027 }
    L_0x003d:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x003f:
        r6.wK = r7;	 Catch:{ all -> 0x0027 }
        r0 = r6.wH;	 Catch:{ all -> 0x0027 }
        r4 = r10.toMillis(r8);	 Catch:{ all -> 0x0027 }
        r0.zza(r6, r4);	 Catch:{ all -> 0x0027 }
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqe.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        boolean z = true;
        zzac.zza(!this.wN, (Object) "Result has already been consumed.");
        synchronized (this.wG) {
            zzac.zza(this.wQ == null, (Object) "Cannot call then() twice.");
            if (this.wK != null) {
                z = false;
            }
            zzac.zza(z, (Object) "Cannot call then() if callbacks are set.");
            this.wR = true;
            this.wQ = new zzrp(this.wI);
            then = this.wQ.then(resultTransform);
            if (isReady()) {
                this.wH.zza(this.wQ, get());
            } else {
                this.wK = this.wQ;
            }
        }
        return then;
    }

    public final void zza(com.google.android.gms.common.api.PendingResult.zza com_google_android_gms_common_api_PendingResult_zza) {
        boolean z = true;
        zzac.zza(!this.wN, (Object) "Result has already been consumed.");
        if (com_google_android_gms_common_api_PendingResult_zza == null) {
            z = false;
        }
        zzac.zzb(z, (Object) "Callback cannot be null.");
        synchronized (this.wG) {
            if (isReady()) {
                com_google_android_gms_common_api_PendingResult_zza.zzv(this.vU.getStatus());
            } else {
                this.wJ.add(com_google_android_gms_common_api_PendingResult_zza);
            }
        }
    }

    protected final void zza(zzs com_google_android_gms_common_internal_zzs) {
        synchronized (this.wG) {
            this.wP = com_google_android_gms_common_internal_zzs;
        }
    }

    public void zza(zzb com_google_android_gms_internal_zzrq_zzb) {
        this.wL.set(com_google_android_gms_internal_zzrq_zzb);
    }

    public final void zzaa(Status status) {
        synchronized (this.wG) {
            if (!isReady()) {
                zzc(zzc(status));
                this.wO = true;
            }
        }
    }

    public Integer zzaqf() {
        return null;
    }

    public boolean zzaqq() {
        boolean isCanceled;
        synchronized (this.wG) {
            if (((GoogleApiClient) this.wI.get()) == null || !this.wR) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public void zzaqs() {
        setResultCallback(null);
    }

    public void zzaqt() {
        boolean z = this.wR || ((Boolean) wF.get()).booleanValue();
        this.wR = z;
    }

    boolean zzaqu() {
        return false;
    }

    protected abstract R zzc(Status status);

    public final void zzc(R r) {
        boolean z = true;
        synchronized (this.wG) {
            if (this.wO || this.zzak || (isReady() && zzaqu())) {
                zze(r);
                return;
            }
            zzac.zza(!isReady(), (Object) "Results have already been set");
            if (this.wN) {
                z = false;
            }
            zzac.zza(z, (Object) "Result has already been consumed");
            zzd(r);
        }
    }
}
