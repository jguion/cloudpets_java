package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@zziy
public class zzli {

    public interface zza<D, R> {
        R apply(D d);
    }

    class AnonymousClass1 implements Runnable {
        final /* synthetic */ zzlg zzctz;
        final /* synthetic */ zza zzcua;
        final /* synthetic */ zzlj zzcub;

        AnonymousClass1(zzlg com_google_android_gms_internal_zzlg, zza com_google_android_gms_internal_zzli_zza, zzlj com_google_android_gms_internal_zzlj) {
            this.zzctz = com_google_android_gms_internal_zzlg;
            this.zzcua = com_google_android_gms_internal_zzli_zza;
            this.zzcub = com_google_android_gms_internal_zzlj;
        }

        public void run() {
            try {
                this.zzctz.zzh(this.zzcua.apply(this.zzcub.get()));
                return;
            } catch (CancellationException e) {
            } catch (InterruptedException e2) {
            } catch (ExecutionException e3) {
            }
            this.zzctz.cancel(true);
        }
    }

    class AnonymousClass2 implements Runnable {
        final /* synthetic */ AtomicInteger zzcuc;
        final /* synthetic */ int zzcud;
        final /* synthetic */ zzlg zzcue;
        final /* synthetic */ List zzcuf;

        AnonymousClass2(AtomicInteger atomicInteger, int i, zzlg com_google_android_gms_internal_zzlg, List list) {
            this.zzcuc = atomicInteger;
            this.zzcud = i;
            this.zzcue = com_google_android_gms_internal_zzlg;
            this.zzcuf = list;
        }

        public void run() {
            Throwable e;
            if (this.zzcuc.incrementAndGet() >= this.zzcud) {
                try {
                    this.zzcue.zzh(zzli.zzp(this.zzcuf));
                    return;
                } catch (ExecutionException e2) {
                    e = e2;
                } catch (InterruptedException e3) {
                    e = e3;
                }
            } else {
                return;
            }
            zzb.zzd("Unable to convert list of futures to a future of list", e);
        }
    }

    public static <A, B> zzlj<B> zza(zzlj<A> com_google_android_gms_internal_zzlj_A, zza<A, B> com_google_android_gms_internal_zzli_zza_A__B) {
        zzlj com_google_android_gms_internal_zzlg = new zzlg();
        com_google_android_gms_internal_zzlj_A.zzc(new AnonymousClass1(com_google_android_gms_internal_zzlg, com_google_android_gms_internal_zzli_zza_A__B, com_google_android_gms_internal_zzlj_A));
        return com_google_android_gms_internal_zzlg;
    }

    public static <V> zzlj<List<V>> zzo(List<zzlj<V>> list) {
        zzlj com_google_android_gms_internal_zzlg = new zzlg();
        int size = list.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (zzlj zzc : list) {
            zzc.zzc(new AnonymousClass2(atomicInteger, size, com_google_android_gms_internal_zzlg, list));
        }
        return com_google_android_gms_internal_zzlg;
    }

    private static <V> List<V> zzp(List<zzlj<V>> list) throws ExecutionException, InterruptedException {
        List<V> arrayList = new ArrayList();
        for (zzlj com_google_android_gms_internal_zzlj : list) {
            Object obj = com_google_android_gms_internal_zzlj.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
