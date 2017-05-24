package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@zziy
public class zzgn implements zzgf {
    private final Context mContext;
    private final Object zzakd = new Object();
    private final zzgq zzals;
    private final boolean zzatk;
    private final boolean zzazd;
    private final zzgh zzbsv;
    private final AdRequestInfoParcel zzbtk;
    private final long zzbtl;
    private final long zzbtm;
    private final int zzbtn;
    private boolean zzbto = false;
    private final Map<zzlj<zzgl>, zzgk> zzbtp = new HashMap();
    private List<zzgl> zzbtq = new ArrayList();

    public zzgn(Context context, AdRequestInfoParcel adRequestInfoParcel, zzgq com_google_android_gms_internal_zzgq, zzgh com_google_android_gms_internal_zzgh, boolean z, boolean z2, long j, long j2, int i) {
        this.mContext = context;
        this.zzbtk = adRequestInfoParcel;
        this.zzals = com_google_android_gms_internal_zzgq;
        this.zzbsv = com_google_android_gms_internal_zzgh;
        this.zzatk = z;
        this.zzazd = z2;
        this.zzbtl = j;
        this.zzbtm = j2;
        this.zzbtn = i;
    }

    private void zza(final zzlj<zzgl> com_google_android_gms_internal_zzlj_com_google_android_gms_internal_zzgl) {
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzgn zzbts;

            public void run() {
                for (zzlj com_google_android_gms_internal_zzlj : this.zzbts.zzbtp.keySet()) {
                    if (com_google_android_gms_internal_zzlj != com_google_android_gms_internal_zzlj_com_google_android_gms_internal_zzgl) {
                        ((zzgk) this.zzbts.zzbtp.get(com_google_android_gms_internal_zzlj)).cancel();
                    }
                }
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.gms.internal.zzgl zze(java.util.List<com.google.android.gms.internal.zzlj<com.google.android.gms.internal.zzgl>> r5) {
        /*
        r4 = this;
        r2 = r4.zzakd;
        monitor-enter(r2);
        r0 = r4.zzbto;	 Catch:{ all -> 0x003c }
        if (r0 == 0) goto L_0x000f;
    L_0x0007:
        r1 = new com.google.android.gms.internal.zzgl;	 Catch:{ all -> 0x003c }
        r0 = -1;
        r1.<init>(r0);	 Catch:{ all -> 0x003c }
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
    L_0x000e:
        return r1;
    L_0x000f:
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
        r2 = r5.iterator();
    L_0x0014:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x003f;
    L_0x001a:
        r0 = r2.next();
        r0 = (com.google.android.gms.internal.zzlj) r0;
        r1 = r0.get();	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        r1 = (com.google.android.gms.internal.zzgl) r1;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        r3 = r4.zzbtq;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        r3.add(r1);	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        if (r1 == 0) goto L_0x0014;
    L_0x002d:
        r3 = r1.zzbtd;	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        if (r3 != 0) goto L_0x0014;
    L_0x0031:
        r4.zza(r0);	 Catch:{ InterruptedException -> 0x0035, ExecutionException -> 0x004a }
        goto L_0x000e;
    L_0x0035:
        r0 = move-exception;
    L_0x0036:
        r1 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.ads.internal.util.client.zzb.zzd(r1, r0);
        goto L_0x0014;
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
        throw r0;
    L_0x003f:
        r0 = 0;
        r4.zza(r0);
        r1 = new com.google.android.gms.internal.zzgl;
        r0 = 1;
        r1.<init>(r0);
        goto L_0x000e;
    L_0x004a:
        r0 = move-exception;
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgn.zze(java.util.List):com.google.android.gms.internal.zzgl");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.gms.internal.zzgl zzf(java.util.List<com.google.android.gms.internal.zzlj<com.google.android.gms.internal.zzgl>> r16) {
        /*
        r15 = this;
        r1 = r15.zzakd;
        monitor-enter(r1);
        r0 = r15.zzbto;	 Catch:{ all -> 0x0080 }
        if (r0 == 0) goto L_0x000f;
    L_0x0007:
        r2 = new com.google.android.gms.internal.zzgl;	 Catch:{ all -> 0x0080 }
        r0 = -1;
        r2.<init>(r0);	 Catch:{ all -> 0x0080 }
        monitor-exit(r1);	 Catch:{ all -> 0x0080 }
    L_0x000e:
        return r2;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x0080 }
        r4 = -1;
        r3 = 0;
        r2 = 0;
        r0 = r15.zzbsv;
        r0 = r0.zzbsn;
        r6 = -1;
        r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r0 == 0) goto L_0x0083;
    L_0x001d:
        r0 = r15.zzbsv;
        r0 = r0.zzbsn;
    L_0x0021:
        r8 = r16.iterator();
        r6 = r0;
    L_0x0026:
        r0 = r8.hasNext();
        if (r0 == 0) goto L_0x00b9;
    L_0x002c:
        r0 = r8.next();
        r0 = (com.google.android.gms.internal.zzlj) r0;
        r1 = com.google.android.gms.ads.internal.zzu.zzgf();
        r10 = r1.currentTimeMillis();
        r12 = 0;
        r1 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r1 != 0) goto L_0x0086;
    L_0x0040:
        r1 = r0.isDone();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        if (r1 == 0) goto L_0x0086;
    L_0x0046:
        r1 = r0.get();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        r1 = (com.google.android.gms.internal.zzgl) r1;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
    L_0x004c:
        r5 = r15.zzbtq;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        r5.add(r1);	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        if (r1 == 0) goto L_0x00cc;
    L_0x0053:
        r5 = r1.zzbtd;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        if (r5 != 0) goto L_0x00cc;
    L_0x0057:
        r5 = r1.zzbti;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        if (r5 == 0) goto L_0x00cc;
    L_0x005b:
        r9 = r5.zznk();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        if (r9 <= r4) goto L_0x00cc;
    L_0x0061:
        r2 = r5.zznk();	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        r14 = r1;
        r1 = r0;
        r0 = r14;
    L_0x0068:
        r3 = com.google.android.gms.ads.internal.zzu.zzgf();
        r4 = r3.currentTimeMillis();
        r4 = r4 - r10;
        r4 = r6 - r4;
        r6 = 0;
        r4 = java.lang.Math.max(r4, r6);
        r3 = r1;
        r14 = r0;
        r0 = r4;
        r4 = r2;
        r2 = r14;
    L_0x007e:
        r6 = r0;
        goto L_0x0026;
    L_0x0080:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0080 }
        throw r0;
    L_0x0083:
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        goto L_0x0021;
    L_0x0086:
        r1 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        r1 = r0.get(r6, r1);	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        r1 = (com.google.android.gms.internal.zzgl) r1;	 Catch:{ InterruptedException -> 0x00c6, ExecutionException -> 0x00c8, RemoteException -> 0x008f, TimeoutException -> 0x00ca }
        goto L_0x004c;
    L_0x008f:
        r0 = move-exception;
    L_0x0090:
        r1 = "Exception while processing an adapter; continuing with other adapters";
        com.google.android.gms.ads.internal.util.client.zzb.zzd(r1, r0);	 Catch:{ all -> 0x00a7 }
        r0 = com.google.android.gms.ads.internal.zzu.zzgf();
        r0 = r0.currentTimeMillis();
        r0 = r0 - r10;
        r0 = r6 - r0;
        r6 = 0;
        r0 = java.lang.Math.max(r0, r6);
        goto L_0x007e;
    L_0x00a7:
        r0 = move-exception;
        r1 = com.google.android.gms.ads.internal.zzu.zzgf();
        r2 = r1.currentTimeMillis();
        r2 = r2 - r10;
        r2 = r6 - r2;
        r4 = 0;
        java.lang.Math.max(r2, r4);
        throw r0;
    L_0x00b9:
        r15.zza(r3);
        if (r2 != 0) goto L_0x000e;
    L_0x00be:
        r2 = new com.google.android.gms.internal.zzgl;
        r0 = 1;
        r2.<init>(r0);
        goto L_0x000e;
    L_0x00c6:
        r0 = move-exception;
        goto L_0x0090;
    L_0x00c8:
        r0 = move-exception;
        goto L_0x0090;
    L_0x00ca:
        r0 = move-exception;
        goto L_0x0090;
    L_0x00cc:
        r0 = r2;
        r1 = r3;
        r2 = r4;
        goto L_0x0068;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgn.zzf(java.util.List):com.google.android.gms.internal.zzgl");
    }

    public void cancel() {
        synchronized (this.zzakd) {
            this.zzbto = true;
            for (zzgk cancel : this.zzbtp.values()) {
                cancel.cancel();
            }
        }
    }

    public zzgl zzd(List<zzgg> list) {
        zzb.zzdd("Starting mediation.");
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        List arrayList = new ArrayList();
        for (zzgg com_google_android_gms_internal_zzgg : list) {
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(com_google_android_gms_internal_zzgg.zzbrm);
            zzb.zzde(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            for (String com_google_android_gms_internal_zzgk : com_google_android_gms_internal_zzgg.zzbrn) {
                final zzgk com_google_android_gms_internal_zzgk2 = new zzgk(this.mContext, com_google_android_gms_internal_zzgk, this.zzals, this.zzbsv, com_google_android_gms_internal_zzgg, this.zzbtk.zzcfu, this.zzbtk.zzaqz, this.zzbtk.zzaqv, this.zzatk, this.zzazd, this.zzbtk.zzarn, this.zzbtk.zzarr);
                zzlj zza = zzkq.zza(newCachedThreadPool, new Callable<zzgl>(this) {
                    final /* synthetic */ zzgn zzbts;

                    public /* synthetic */ Object call() throws Exception {
                        return zznl();
                    }

                    public zzgl zznl() throws Exception {
                        synchronized (this.zzbts.zzakd) {
                            if (this.zzbts.zzbto) {
                                return null;
                            }
                            return com_google_android_gms_internal_zzgk2.zza(this.zzbts.zzbtl, this.zzbts.zzbtm);
                        }
                    }
                });
                this.zzbtp.put(zza, com_google_android_gms_internal_zzgk2);
                arrayList.add(zza);
            }
        }
        switch (this.zzbtn) {
            case 2:
                return zzf(arrayList);
            default:
                return zze(arrayList);
        }
    }

    public List<zzgl> zzne() {
        return this.zzbtq;
    }
}
