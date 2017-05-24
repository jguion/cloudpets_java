package com.google.android.gms.internal;

import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzbv implements Callable {
    protected final String TAG = getClass().getSimpleName();
    protected final String className;
    protected final zzbb zzafz;
    protected final zza zzair;
    protected final String zzaix;
    protected Method zzaiz;
    protected final int zzajd;
    protected final int zzaje;

    public zzbv(zzbb com_google_android_gms_internal_zzbb, String str, String str2, zza com_google_android_gms_internal_zzae_zza, int i, int i2) {
        this.zzafz = com_google_android_gms_internal_zzbb;
        this.className = str;
        this.zzaix = str2;
        this.zzair = com_google_android_gms_internal_zzae_zza;
        this.zzajd = i;
        this.zzaje = i2;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzdb();
    }

    protected abstract void zzcy() throws IllegalAccessException, InvocationTargetException;

    public Void zzdb() throws Exception {
        try {
            long nanoTime = System.nanoTime();
            this.zzaiz = this.zzafz.zzc(this.className, this.zzaix);
            if (this.zzaiz != null) {
                zzcy();
                zzao zzco = this.zzafz.zzco();
                if (!(zzco == null || this.zzajd == Integer.MIN_VALUE)) {
                    zzco.zza(this.zzaje, this.zzajd, (System.nanoTime() - nanoTime) / 1000);
                }
            }
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e2) {
        }
        return null;
    }
}
