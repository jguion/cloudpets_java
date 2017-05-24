package com.google.android.gms.internal;

import com.bumptech.glide.load.Key;
import com.google.android.gms.internal.zzaw.zza;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzbu {
    protected static final String TAG = zzbu.class.getSimpleName();
    private final String className;
    private final zzbb zzafz;
    private final String zzaix;
    private final int zzaiy = 2;
    private volatile Method zzaiz = null;
    private List<Class> zzaja;
    private CountDownLatch zzajb = new CountDownLatch(1);

    public zzbu(zzbb com_google_android_gms_internal_zzbb, String str, String str2, List<Class> list) {
        this.zzafz = com_google_android_gms_internal_zzbb;
        this.className = str;
        this.zzaix = str2;
        this.zzaja = new ArrayList(list);
        this.zzafz.zzch().submit(new Runnable(this) {
            final /* synthetic */ zzbu zzajc;

            {
                this.zzajc = r1;
            }

            public void run() {
                this.zzajc.zzdc();
            }
        });
    }

    private String zzd(byte[] bArr, String str) throws zza, UnsupportedEncodingException {
        return new String(this.zzafz.zzcj().zzc(bArr, str), Key.STRING_CHARSET_NAME);
    }

    private void zzdc() {
        try {
            Class loadClass = this.zzafz.zzci().loadClass(zzd(this.zzafz.zzck(), this.className));
            if (loadClass != null) {
                this.zzaiz = loadClass.getMethod(zzd(this.zzafz.zzck(), this.zzaix), (Class[]) this.zzaja.toArray(new Class[this.zzaja.size()]));
                if (this.zzaiz == null) {
                    this.zzajb.countDown();
                } else {
                    this.zzajb.countDown();
                }
            }
        } catch (zza e) {
        } catch (UnsupportedEncodingException e2) {
        } catch (ClassNotFoundException e3) {
        } catch (NoSuchMethodException e4) {
        } catch (NullPointerException e5) {
        } finally {
            this.zzajb.countDown();
        }
    }

    public Method zzdd() {
        if (this.zzaiz != null) {
            return this.zzaiz;
        }
        try {
            return this.zzajb.await(2, TimeUnit.SECONDS) ? this.zzaiz : null;
        } catch (InterruptedException e) {
            return null;
        }
    }
}
