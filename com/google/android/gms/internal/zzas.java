package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzae.zza;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class zzas extends zzaq {
    private static final String TAG = zzas.class.getSimpleName();
    private static long startTime = 0;
    protected static volatile zzbb zzafz = null;
    static boolean zzagt = false;
    protected static final Object zzagw = new Object();
    protected boolean zzagr = false;
    protected String zzags;
    protected boolean zzagu = false;
    protected boolean zzagv = false;

    protected zzas(Context context, String str) {
        super(context);
        this.zzags = str;
        this.zzagr = false;
    }

    protected zzas(Context context, String str, boolean z) {
        super(context);
        this.zzags = str;
        this.zzagr = z;
    }

    static zzbc zza(zzbb com_google_android_gms_internal_zzbb, MotionEvent motionEvent, DisplayMetrics displayMetrics) throws zzay {
        Throwable e;
        Method zzc = com_google_android_gms_internal_zzbb.zzc(zzax.zzcd(), zzax.zzce());
        if (zzc == null || motionEvent == null) {
            throw new zzay();
        }
        try {
            return new zzbc((String) zzc.invoke(null, new Object[]{motionEvent, displayMetrics}));
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new zzay(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new zzay(e);
        }
    }

    protected static synchronized void zza(Context context, boolean z) {
        synchronized (zzas.class) {
            if (!zzagt) {
                startTime = Calendar.getInstance().getTime().getTime() / 1000;
                zzafz = zzb(context, z);
                zzagt = true;
            }
        }
    }

    private static void zza(zzbb com_google_android_gms_internal_zzbb) {
        List singletonList = Collections.singletonList(Context.class);
        com_google_android_gms_internal_zzbb.zza(zzax.zzbj(), zzax.zzbk(), singletonList);
        com_google_android_gms_internal_zzbb.zza(zzax.zzbt(), zzax.zzbu(), singletonList);
        com_google_android_gms_internal_zzbb.zza(zzax.zzbr(), zzax.zzbs(), singletonList);
        com_google_android_gms_internal_zzbb.zza(zzax.zzbd(), zzax.zzbe(), singletonList);
        com_google_android_gms_internal_zzbb.zza(zzax.zzbn(), zzax.zzbo(), singletonList);
        com_google_android_gms_internal_zzbb.zza(zzax.zzaz(), zzax.zzba(), singletonList);
        com_google_android_gms_internal_zzbb.zza(zzax.zzcf(), zzax.zzcg(), singletonList);
        singletonList = Arrays.asList(new Class[]{MotionEvent.class, DisplayMetrics.class});
        com_google_android_gms_internal_zzbb.zza(zzax.zzcd(), zzax.zzce(), singletonList);
        com_google_android_gms_internal_zzbb.zza(zzax.zzcb(), zzax.zzcc(), singletonList);
        com_google_android_gms_internal_zzbb.zza(zzax.zzbh(), zzax.zzbi(), Collections.emptyList());
        com_google_android_gms_internal_zzbb.zza(zzax.zzbz(), zzax.zzca(), Collections.emptyList());
        com_google_android_gms_internal_zzbb.zza(zzax.zzbp(), zzax.zzbq(), Collections.emptyList());
        com_google_android_gms_internal_zzbb.zza(zzax.zzbf(), zzax.zzbg(), Collections.emptyList());
        com_google_android_gms_internal_zzbb.zza(zzax.zzbl(), zzax.zzbm(), Collections.emptyList());
        com_google_android_gms_internal_zzbb.zza(zzax.zzbx(), zzax.zzby(), Collections.emptyList());
        com_google_android_gms_internal_zzbb.zza(zzax.zzbb(), zzax.zzbc(), Arrays.asList(new Class[]{Context.class, Boolean.TYPE}));
        com_google_android_gms_internal_zzbb.zza(zzax.zzbv(), zzax.zzbw(), Arrays.asList(new Class[]{StackTraceElement[].class}));
    }

    protected static zzbb zzb(Context context, boolean z) {
        if (zzafz == null) {
            synchronized (zzagw) {
                if (zzafz == null) {
                    zzbb zza = zzbb.zza(context, zzax.getKey(), zzax.zzay(), z);
                    zza(zza);
                    zzafz = zza;
                }
            }
        }
        return zzafz;
    }

    private void zzd(zzbb com_google_android_gms_internal_zzbb, zza com_google_android_gms_internal_zzae_zza) {
        zzbc zza;
        zza.zza com_google_android_gms_internal_zzae_zza_zza;
        int i = 0;
        try {
            zza = zza(com_google_android_gms_internal_zzbb, this.zzage, this.zzagp);
            com_google_android_gms_internal_zzae_zza.zzdf = zza.zzaig;
            com_google_android_gms_internal_zzae_zza.zzdg = zza.zzaih;
            com_google_android_gms_internal_zzae_zza.zzdh = zza.zzaii;
            if (this.zzago) {
                com_google_android_gms_internal_zzae_zza.zzdv = zza.zzfb;
                com_google_android_gms_internal_zzae_zza.zzdw = zza.zzez;
            }
            if (((Boolean) zzdi.zzbex.get()).booleanValue() || ((Boolean) zzdi.zzbes.get()).booleanValue()) {
                com_google_android_gms_internal_zzae_zza_zza = new zza.zza();
                zzbc zzb = zzb(this.zzage);
                com_google_android_gms_internal_zzae_zza_zza.zzdf = zzb.zzaig;
                com_google_android_gms_internal_zzae_zza_zza.zzdg = zzb.zzaih;
                com_google_android_gms_internal_zzae_zza_zza.zzfe = zzb.zzaii;
                if (this.zzago) {
                    com_google_android_gms_internal_zzae_zza_zza.zzez = zzb.zzez;
                    com_google_android_gms_internal_zzae_zza_zza.zzfb = zzb.zzfb;
                    com_google_android_gms_internal_zzae_zza_zza.zzfd = Integer.valueOf(zzb.zzaij.longValue() != 0 ? 1 : 0);
                    if (this.zzagh > 0) {
                        com_google_android_gms_internal_zzae_zza_zza.zzfa = this.zzagp != null ? Long.valueOf(Math.round(((double) this.zzagm) / ((double) this.zzagh))) : null;
                        com_google_android_gms_internal_zzae_zza_zza.zzfc = Long.valueOf(Math.round(((double) this.zzagl) / ((double) this.zzagh)));
                    }
                    com_google_android_gms_internal_zzae_zza_zza.zzfg = zzb.zzfg;
                    com_google_android_gms_internal_zzae_zza_zza.zzff = zzb.zzff;
                    com_google_android_gms_internal_zzae_zza_zza.zzfh = Integer.valueOf(zzb.zzaim.longValue() != 0 ? 1 : 0);
                    if (this.zzagk > 0) {
                        com_google_android_gms_internal_zzae_zza_zza.zzfi = Long.valueOf(this.zzagk);
                    }
                }
                com_google_android_gms_internal_zzae_zza.zzeo = com_google_android_gms_internal_zzae_zza_zza;
            }
        } catch (zzay e) {
        }
        if (this.zzagg > 0) {
            com_google_android_gms_internal_zzae_zza.zzea = Long.valueOf(this.zzagg);
        }
        if (this.zzagh > 0) {
            com_google_android_gms_internal_zzae_zza.zzdz = Long.valueOf(this.zzagh);
        }
        if (this.zzagi > 0) {
            com_google_android_gms_internal_zzae_zza.zzdy = Long.valueOf(this.zzagi);
        }
        if (this.zzagj > 0) {
            com_google_android_gms_internal_zzae_zza.zzeb = Long.valueOf(this.zzagj);
        }
        try {
            int size = this.zzagf.size() - 1;
            if (size > 0) {
                com_google_android_gms_internal_zzae_zza.zzep = new zza.zza[size];
                while (i < size) {
                    zza = zza(com_google_android_gms_internal_zzbb, (MotionEvent) this.zzagf.get(i), this.zzagp);
                    com_google_android_gms_internal_zzae_zza_zza = new zza.zza();
                    com_google_android_gms_internal_zzae_zza_zza.zzdf = zza.zzaig;
                    com_google_android_gms_internal_zzae_zza_zza.zzdg = zza.zzaih;
                    com_google_android_gms_internal_zzae_zza.zzep[i] = com_google_android_gms_internal_zzae_zza_zza;
                    i++;
                }
            }
        } catch (zzay e2) {
            com_google_android_gms_internal_zzae_zza.zzep = null;
        }
    }

    protected long zza(StackTraceElement[] stackTraceElementArr) throws zzay {
        Throwable e;
        Method zzc = zzafz.zzc(zzax.zzbv(), zzax.zzbw());
        if (zzc == null || stackTraceElementArr == null) {
            throw new zzay();
        }
        try {
            return new zzaz((String) zzc.invoke(null, new Object[]{stackTraceElementArr})).zzahi.longValue();
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new zzay(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new zzay(e);
        }
    }

    protected zza zza(Context context, View view) {
        zza com_google_android_gms_internal_zzae_zza = new zza();
        if (!TextUtils.isEmpty(this.zzags)) {
            com_google_android_gms_internal_zzae_zza.zzcs = this.zzags;
        }
        zzbb zzb = zzb(context, this.zzagr);
        zzb.zzcw();
        zza(zzb, com_google_android_gms_internal_zzae_zza, view);
        zzb.zzcx();
        return com_google_android_gms_internal_zzae_zza;
    }

    protected void zza(zzbb com_google_android_gms_internal_zzbb, zza com_google_android_gms_internal_zzae_zza) {
        if (com_google_android_gms_internal_zzbb.zzch() != null) {
            zza(zzb(com_google_android_gms_internal_zzbb, com_google_android_gms_internal_zzae_zza));
        }
    }

    protected void zza(zzbb com_google_android_gms_internal_zzbb, zza com_google_android_gms_internal_zzae_zza, View view) {
        zzd(com_google_android_gms_internal_zzbb, com_google_android_gms_internal_zzae_zza);
        zza(zzc(com_google_android_gms_internal_zzbb, com_google_android_gms_internal_zzae_zza));
    }

    protected void zza(List<Callable<Void>> list) {
        if (zzafz != null) {
            ExecutorService zzch = zzafz.zzch();
            if (zzch != null && !list.isEmpty()) {
                try {
                    zzch.invokeAll(list, ((Long) zzdi.zzben.get()).longValue(), TimeUnit.MILLISECONDS);
                } catch (Throwable e) {
                    Log.d(TAG, String.format("class methods got exception: %s", new Object[]{zzbd.zza(e)}));
                }
            }
        }
    }

    protected zzbc zzb(MotionEvent motionEvent) throws zzay {
        Throwable e;
        Method zzc = zzafz.zzc(zzax.zzcb(), zzax.zzcc());
        if (zzc == null || motionEvent == null) {
            throw new zzay();
        }
        try {
            return new zzbc((String) zzc.invoke(null, new Object[]{motionEvent, this.zzagp}));
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new zzay(e);
        } catch (InvocationTargetException e3) {
            e = e3;
            throw new zzay(e);
        }
    }

    protected List<Callable<Void>> zzb(zzbb com_google_android_gms_internal_zzbb, zza com_google_android_gms_internal_zzae_zza) {
        int zzau = com_google_android_gms_internal_zzbb.zzau();
        List arrayList = new ArrayList();
        String zzbb = zzax.zzbb();
        String zzbc = zzax.zzbc();
        boolean z = ((Boolean) zzdi.zzber.get()).booleanValue() || ((Boolean) zzdi.zzbes.get()).booleanValue();
        arrayList.add(new zzbg(com_google_android_gms_internal_zzbb, zzbb, zzbc, com_google_android_gms_internal_zzae_zza, zzau, 27, z));
        arrayList.add(new zzbj(com_google_android_gms_internal_zzbb, zzax.zzbh(), zzax.zzbi(), com_google_android_gms_internal_zzae_zza, startTime, zzau, 25));
        arrayList.add(new zzbo(com_google_android_gms_internal_zzbb, zzax.zzbp(), zzax.zzbq(), com_google_android_gms_internal_zzae_zza, zzau, 1));
        arrayList.add(new zzbp(com_google_android_gms_internal_zzbb, zzax.zzbr(), zzax.zzbs(), com_google_android_gms_internal_zzae_zza, zzau, 31));
        arrayList.add(new zzbs(com_google_android_gms_internal_zzbb, zzax.zzbz(), zzax.zzca(), com_google_android_gms_internal_zzae_zza, zzau, 33));
        arrayList.add(new zzbf(com_google_android_gms_internal_zzbb, zzax.zzbt(), zzax.zzbu(), com_google_android_gms_internal_zzae_zza, zzau, 29));
        arrayList.add(new zzbh(com_google_android_gms_internal_zzbb, zzax.zzbd(), zzax.zzbe(), com_google_android_gms_internal_zzae_zza, zzau, 5));
        arrayList.add(new zzbn(com_google_android_gms_internal_zzbb, zzax.zzbn(), zzax.zzbo(), com_google_android_gms_internal_zzae_zza, zzau, 12));
        arrayList.add(new zzbe(com_google_android_gms_internal_zzbb, zzax.zzaz(), zzax.zzba(), com_google_android_gms_internal_zzae_zza, zzau, 3));
        arrayList.add(new zzbi(com_google_android_gms_internal_zzbb, zzax.zzbf(), zzax.zzbg(), com_google_android_gms_internal_zzae_zza, zzau, 44));
        arrayList.add(new zzbm(com_google_android_gms_internal_zzbb, zzax.zzbl(), zzax.zzbm(), com_google_android_gms_internal_zzae_zza, zzau, 22));
        if (((Boolean) zzdi.zzbeu.get()).booleanValue() || ((Boolean) zzdi.zzbes.get()).booleanValue()) {
            arrayList.add(new zzbt(com_google_android_gms_internal_zzbb, zzax.zzcf(), zzax.zzcg(), com_google_android_gms_internal_zzae_zza, zzau, 48));
        }
        if (((Boolean) zzdi.zzbez.get()).booleanValue() || ((Boolean) zzdi.zzbes.get()).booleanValue()) {
            arrayList.add(new zzbr(com_google_android_gms_internal_zzbb, zzax.zzbx(), zzax.zzby(), com_google_android_gms_internal_zzae_zza, zzau, 51));
        }
        return arrayList;
    }

    protected zza zzc(Context context) {
        zza com_google_android_gms_internal_zzae_zza = new zza();
        if (!TextUtils.isEmpty(this.zzags)) {
            com_google_android_gms_internal_zzae_zza.zzcs = this.zzags;
        }
        zzbb zzb = zzb(context, this.zzagr);
        zzb.zzcw();
        zza(zzb, com_google_android_gms_internal_zzae_zza);
        zzb.zzcx();
        return com_google_android_gms_internal_zzae_zza;
    }

    protected List<Callable<Void>> zzc(zzbb com_google_android_gms_internal_zzbb, zza com_google_android_gms_internal_zzae_zza) {
        ArrayList arrayList = new ArrayList();
        if (com_google_android_gms_internal_zzbb.zzch() == null) {
            return arrayList;
        }
        int zzau = com_google_android_gms_internal_zzbb.zzau();
        arrayList.add(new zzbl(com_google_android_gms_internal_zzbb, com_google_android_gms_internal_zzae_zza));
        ArrayList arrayList2 = arrayList;
        arrayList2.add(new zzbo(com_google_android_gms_internal_zzbb, zzax.zzbp(), zzax.zzbq(), com_google_android_gms_internal_zzae_zza, zzau, 1));
        arrayList2 = arrayList;
        arrayList2.add(new zzbj(com_google_android_gms_internal_zzbb, zzax.zzbh(), zzax.zzbi(), com_google_android_gms_internal_zzae_zza, startTime, zzau, 25));
        arrayList2 = arrayList;
        arrayList2.add(new zzbi(com_google_android_gms_internal_zzbb, zzax.zzbf(), zzax.zzbg(), com_google_android_gms_internal_zzae_zza, zzau, 44));
        arrayList2 = arrayList;
        arrayList2.add(new zzbe(com_google_android_gms_internal_zzbb, zzax.zzaz(), zzax.zzba(), com_google_android_gms_internal_zzae_zza, zzau, 3));
        arrayList2 = arrayList;
        arrayList2.add(new zzbm(com_google_android_gms_internal_zzbb, zzax.zzbl(), zzax.zzbm(), com_google_android_gms_internal_zzae_zza, zzau, 22));
        if (((Boolean) zzdi.zzbfd.get()).booleanValue() || ((Boolean) zzdi.zzbes.get()).booleanValue()) {
            arrayList2 = arrayList;
            arrayList2.add(new zzbh(com_google_android_gms_internal_zzbb, zzax.zzbd(), zzax.zzbe(), com_google_android_gms_internal_zzae_zza, zzau, 5));
        }
        if (((Boolean) zzdi.zzbew.get()).booleanValue() || ((Boolean) zzdi.zzbes.get()).booleanValue()) {
            arrayList2 = arrayList;
            arrayList2.add(new zzbt(com_google_android_gms_internal_zzbb, zzax.zzcf(), zzax.zzcg(), com_google_android_gms_internal_zzae_zza, zzau, 48));
        }
        if (((Boolean) zzdi.zzbfb.get()).booleanValue() || ((Boolean) zzdi.zzbes.get()).booleanValue()) {
            arrayList2 = arrayList;
            arrayList2.add(new zzbr(com_google_android_gms_internal_zzbb, zzax.zzbx(), zzax.zzby(), com_google_android_gms_internal_zzae_zza, zzau, 51));
        }
        if (((Boolean) zzdi.zzbfg.get()).booleanValue() || ((Boolean) zzdi.zzbes.get()).booleanValue()) {
            arrayList2 = arrayList;
            arrayList2.add(new zzbq(com_google_android_gms_internal_zzbb, zzax.zzbv(), zzax.zzbw(), com_google_android_gms_internal_zzae_zza, zzau, 45, new Throwable().getStackTrace()));
        }
        return arrayList;
    }
}
