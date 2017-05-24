package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzap;
import com.google.android.gms.internal.zzat;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkq;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zziy
class zzi implements zzap, Runnable {
    private zzv zzall;
    private final List<Object[]> zzamv = new Vector();
    private final AtomicReference<zzap> zzamw = new AtomicReference();
    CountDownLatch zzamx = new CountDownLatch(1);

    public zzi(zzv com_google_android_gms_ads_internal_zzv) {
        this.zzall = com_google_android_gms_ads_internal_zzv;
        if (zzm.zzjr().zzvf()) {
            zzkq.zza((Runnable) this);
        } else {
            run();
        }
    }

    private void zzev() {
        if (!this.zzamv.isEmpty()) {
            for (Object[] objArr : this.zzamv) {
                if (objArr.length == 1) {
                    ((zzap) this.zzamw.get()).zza((MotionEvent) objArr[0]);
                } else if (objArr.length == 3) {
                    ((zzap) this.zzamw.get()).zza(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
                }
            }
            this.zzamv.clear();
        }
    }

    private Context zzh(Context context) {
        if (!((Boolean) zzdi.zzbba.get()).booleanValue()) {
            return context;
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    public void run() {
        try {
            boolean z = !((Boolean) zzdi.zzbbs.get()).booleanValue() || this.zzall.zzaqv.zzctu;
            zza(zzd(this.zzall.zzaqv.zzcs, zzh(this.zzall.zzahn), z));
        } finally {
            this.zzamx.countDown();
            this.zzall = null;
        }
    }

    public String zza(Context context, String str, View view) {
        if (zzeu()) {
            zzap com_google_android_gms_internal_zzap = (zzap) this.zzamw.get();
            if (com_google_android_gms_internal_zzap != null) {
                zzev();
                return com_google_android_gms_internal_zzap.zza(zzh(context), str, view);
            }
        }
        return "";
    }

    public void zza(int i, int i2, int i3) {
        zzap com_google_android_gms_internal_zzap = (zzap) this.zzamw.get();
        if (com_google_android_gms_internal_zzap != null) {
            zzev();
            com_google_android_gms_internal_zzap.zza(i, i2, i3);
            return;
        }
        this.zzamv.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public void zza(MotionEvent motionEvent) {
        zzap com_google_android_gms_internal_zzap = (zzap) this.zzamw.get();
        if (com_google_android_gms_internal_zzap != null) {
            zzev();
            com_google_android_gms_internal_zzap.zza(motionEvent);
            return;
        }
        this.zzamv.add(new Object[]{motionEvent});
    }

    protected void zza(zzap com_google_android_gms_internal_zzap) {
        this.zzamw.set(com_google_android_gms_internal_zzap);
    }

    public String zzb(Context context) {
        if (zzeu()) {
            zzap com_google_android_gms_internal_zzap = (zzap) this.zzamw.get();
            if (com_google_android_gms_internal_zzap != null) {
                zzev();
                return com_google_android_gms_internal_zzap.zzb(zzh(context));
            }
        }
        return "";
    }

    protected zzap zzd(String str, Context context, boolean z) {
        return zzat.zza(str, context, z);
    }

    protected boolean zzeu() {
        try {
            this.zzamx.await();
            return true;
        } catch (Throwable e) {
            zzb.zzd("Interrupted during GADSignals creation.", e);
            return false;
        }
    }
}
