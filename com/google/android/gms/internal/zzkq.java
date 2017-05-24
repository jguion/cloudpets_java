package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@zziy
public final class zzkq {
    private static final ThreadPoolExecutor zzcqx = new ThreadPoolExecutor(10, 10, 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), zzcu("Default"));
    private static final ThreadPoolExecutor zzcqy = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), zzcu("Loader"));

    class AnonymousClass1 implements Callable<Void> {
        final /* synthetic */ Runnable zzcqz;

        AnonymousClass1(Runnable runnable) {
            this.zzcqz = runnable;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzdb();
        }

        public Void zzdb() {
            this.zzcqz.run();
            return null;
        }
    }

    class AnonymousClass2 implements Callable<Void> {
        final /* synthetic */ Runnable zzcqz;

        AnonymousClass2(Runnable runnable) {
            this.zzcqz = runnable;
        }

        public /* synthetic */ Object call() throws Exception {
            return zzdb();
        }

        public Void zzdb() {
            this.zzcqz.run();
            return null;
        }
    }

    class AnonymousClass3 implements Runnable {
        final /* synthetic */ zzlg zzcra;
        final /* synthetic */ Callable zzcrb;

        AnonymousClass3(zzlg com_google_android_gms_internal_zzlg, Callable callable) {
            this.zzcra = com_google_android_gms_internal_zzlg;
            this.zzcrb = callable;
        }

        public void run() {
            try {
                Process.setThreadPriority(10);
                this.zzcra.zzh(this.zzcrb.call());
            } catch (Throwable e) {
                zzu.zzgd().zza(e, "AdThreadPool.submit");
                this.zzcra.zze(e);
            }
        }
    }

    class AnonymousClass4 implements Runnable {
        final /* synthetic */ zzlg zzcra;
        final /* synthetic */ Future zzcrc;

        AnonymousClass4(zzlg com_google_android_gms_internal_zzlg, Future future) {
            this.zzcra = com_google_android_gms_internal_zzlg;
            this.zzcrc = future;
        }

        public void run() {
            if (this.zzcra.isCancelled()) {
                this.zzcrc.cancel(true);
            }
        }
    }

    class AnonymousClass5 implements ThreadFactory {
        private final AtomicInteger zzcrd = new AtomicInteger(1);
        final /* synthetic */ String zzcre;

        AnonymousClass5(String str) {
            this.zzcre = str;
        }

        public Thread newThread(Runnable runnable) {
            String str = this.zzcre;
            return new Thread(runnable, new StringBuilder(String.valueOf(str).length() + 23).append("AdWorker(").append(str).append(") #").append(this.zzcrd.getAndIncrement()).toString());
        }
    }

    static {
        zzcqx.allowCoreThreadTimeOut(true);
        zzcqy.allowCoreThreadTimeOut(true);
    }

    public static zzlj<Void> zza(int i, Runnable runnable) {
        return i == 1 ? zza(zzcqy, new AnonymousClass1(runnable)) : zza(zzcqx, new AnonymousClass2(runnable));
    }

    public static zzlj<Void> zza(Runnable runnable) {
        return zza(0, runnable);
    }

    public static <T> zzlj<T> zza(Callable<T> callable) {
        return zza(zzcqx, (Callable) callable);
    }

    public static <T> zzlj<T> zza(ExecutorService executorService, Callable<T> callable) {
        Object com_google_android_gms_internal_zzlg = new zzlg();
        try {
            com_google_android_gms_internal_zzlg.zzd(new AnonymousClass4(com_google_android_gms_internal_zzlg, executorService.submit(new AnonymousClass3(com_google_android_gms_internal_zzlg, callable))));
        } catch (Throwable e) {
            zzb.zzd("Thread execution is rejected.", e);
            com_google_android_gms_internal_zzlg.cancel(true);
        }
        return com_google_android_gms_internal_zzlg;
    }

    private static ThreadFactory zzcu(String str) {
        return new AnonymousClass5(str);
    }
}
