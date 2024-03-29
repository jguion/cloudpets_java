package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzae.zza;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zzaq implements zzap {
    protected MotionEvent zzage;
    protected LinkedList<MotionEvent> zzagf = new LinkedList();
    protected long zzagg = 0;
    protected long zzagh = 0;
    protected long zzagi = 0;
    protected long zzagj = 0;
    protected long zzagk = 0;
    protected long zzagl = 0;
    protected long zzagm = 0;
    private boolean zzagn = false;
    protected boolean zzago = false;
    protected DisplayMetrics zzagp;

    protected zzaq(Context context) {
        try {
            zzam.zzas();
            this.zzagp = context.getResources().getDisplayMetrics();
        } catch (Throwable th) {
        }
    }

    private String zza(Context context, String str, boolean z, View view) {
        boolean z2 = true;
        if (z) {
            try {
                zza zza = zza(context, view);
                this.zzagn = true;
            } catch (NoSuchAlgorithmException e) {
                return Integer.toString(7);
            } catch (UnsupportedEncodingException e2) {
                return Integer.toString(7);
            } catch (Throwable th) {
                return Integer.toString(3);
            }
        }
        zza = zzc(context);
        if (zza == null || zza.db() == 0) {
            return Integer.toString(5);
        }
        if (zzb(z)) {
            z2 = false;
        }
        return zzam.zza(zza, str, z2);
    }

    private static boolean zza(zzbc com_google_android_gms_internal_zzbc) {
        return (com_google_android_gms_internal_zzbc == null || com_google_android_gms_internal_zzbc.zzfb == null || com_google_android_gms_internal_zzbc.zzaik == null) ? false : true;
    }

    private boolean zzb(zzbc com_google_android_gms_internal_zzbc) {
        return (this.zzagp == null || com_google_android_gms_internal_zzbc == null || com_google_android_gms_internal_zzbc.zzez == null || com_google_android_gms_internal_zzbc.zzail == null) ? false : true;
    }

    private static boolean zzb(boolean z) {
        return !((Boolean) zzdi.zzbep.get()).booleanValue() ? true : ((Boolean) zzdi.zzbfh.get()).booleanValue() && z;
    }

    protected abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzay;

    protected abstract zza zza(Context context, View view);

    public String zza(Context context, String str, View view) {
        return zza(context, str, true, view);
    }

    public void zza(int i, int i2, int i3) {
        if (this.zzage != null) {
            this.zzage.recycle();
        }
        if (this.zzagp != null) {
            this.zzage = MotionEvent.obtain(0, (long) i3, 1, ((float) i) * this.zzagp.density, ((float) i2) * this.zzagp.density, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            this.zzage = null;
        }
        this.zzago = false;
    }

    public void zza(MotionEvent motionEvent) {
        if (this.zzagn) {
            this.zzagj = 0;
            this.zzagi = 0;
            this.zzagh = 0;
            this.zzagg = 0;
            this.zzagk = 0;
            this.zzagm = 0;
            this.zzagl = 0;
            Iterator it = this.zzagf.iterator();
            while (it.hasNext()) {
                ((MotionEvent) it.next()).recycle();
            }
            this.zzagf.clear();
            this.zzage = null;
            this.zzagn = false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.zzagg++;
                break;
            case 1:
                this.zzage = MotionEvent.obtain(motionEvent);
                this.zzagf.add(this.zzage);
                if (this.zzagf.size() > 6) {
                    ((MotionEvent) this.zzagf.remove()).recycle();
                }
                this.zzagi++;
                try {
                    this.zzagk = zza(new Throwable().getStackTrace());
                    break;
                } catch (zzay e) {
                    break;
                }
            case 2:
                this.zzagh += (long) (motionEvent.getHistorySize() + 1);
                if (((Boolean) zzdi.zzbex.get()).booleanValue() || ((Boolean) zzdi.zzbes.get()).booleanValue()) {
                    try {
                        zzbc zzb = zzb(motionEvent);
                        if (zza(zzb)) {
                            this.zzagl += zzb.zzfb.longValue() + zzb.zzaik.longValue();
                        }
                        if (zzb(zzb)) {
                            this.zzagm = (zzb.zzail.longValue() + zzb.zzez.longValue()) + this.zzagm;
                            break;
                        }
                    } catch (zzay e2) {
                        break;
                    }
                }
                break;
            case 3:
                this.zzagj++;
                break;
        }
        this.zzago = true;
    }

    protected abstract zzbc zzb(MotionEvent motionEvent) throws zzay;

    public String zzb(Context context) {
        return zza(context, null, false, null);
    }

    public String zzb(Context context, String str) {
        return zza(context, str, null);
    }

    protected abstract zza zzc(Context context);
}
