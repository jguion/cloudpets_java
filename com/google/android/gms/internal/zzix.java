package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@zziy
public class zzix implements UncaughtExceptionHandler {
    private Context mContext;
    private VersionInfoParcel zzaop;
    @Nullable
    private UncaughtExceptionHandler zzcff;
    @Nullable
    private UncaughtExceptionHandler zzcfg;

    public zzix(Context context, VersionInfoParcel versionInfoParcel, @Nullable UncaughtExceptionHandler uncaughtExceptionHandler, @Nullable UncaughtExceptionHandler uncaughtExceptionHandler2) {
        this.zzcff = uncaughtExceptionHandler;
        this.zzcfg = uncaughtExceptionHandler2;
        this.mContext = context;
        this.zzaop = versionInfoParcel;
    }

    public static zzix zza(Context context, Thread thread, VersionInfoParcel versionInfoParcel) {
        if (context == null || thread == null || versionInfoParcel == null) {
            return null;
        }
        if (!zzu(context)) {
            return null;
        }
        UncaughtExceptionHandler uncaughtExceptionHandler = thread.getUncaughtExceptionHandler();
        UncaughtExceptionHandler com_google_android_gms_internal_zzix = new zzix(context, versionInfoParcel, uncaughtExceptionHandler, Thread.getDefaultUncaughtExceptionHandler());
        if (uncaughtExceptionHandler != null && (uncaughtExceptionHandler instanceof zzix)) {
            return (zzix) uncaughtExceptionHandler;
        }
        try {
            thread.setUncaughtExceptionHandler(com_google_android_gms_internal_zzix);
            return com_google_android_gms_internal_zzix;
        } catch (Throwable e) {
            zzb.zzc("Fail to set UncaughtExceptionHandler.", e);
            return null;
        }
    }

    private Throwable zzd(Throwable th) {
        if (((Boolean) zzdi.zzbau.get()).booleanValue()) {
            return th;
        }
        LinkedList linkedList = new LinkedList();
        while (th != null) {
            linkedList.push(th);
            th = th.getCause();
        }
        Throwable th2 = null;
        while (!linkedList.isEmpty()) {
            Throwable th3;
            Throwable th4 = (Throwable) linkedList.pop();
            StackTraceElement[] stackTrace = th4.getStackTrace();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StackTraceElement(th4.getClass().getName(), "<filtered>", "<filtered>", 1));
            int i = 0;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (zzcg(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                    i = 1;
                } else if (zzch(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                } else {
                    arrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                }
            }
            if (i != 0) {
                th3 = th2 == null ? new Throwable(th4.getMessage()) : new Throwable(th4.getMessage(), th2);
                th3.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
            } else {
                th3 = th2;
            }
            th2 = th3;
        }
        return th2;
    }

    private static boolean zzu(Context context) {
        return ((Boolean) zzdi.zzbat.get()).booleanValue();
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (zzb(th)) {
            if (Looper.getMainLooper().getThread() != thread) {
                zza(th, "AdMobExceptionReporter.uncaughtException");
                return;
            }
            zzc(th);
        }
        if (this.zzcff != null) {
            this.zzcff.uncaughtException(thread, th);
        } else if (this.zzcfg != null) {
            this.zzcfg.uncaughtException(thread, th);
        }
    }

    String zza(Class cls, Throwable th, String str) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return new Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", VERSION.RELEASE).appendQueryParameter("api", String.valueOf(VERSION.SDK_INT)).appendQueryParameter("device", zzu.zzfz().zzuj()).appendQueryParameter("js", this.zzaop.zzcs).appendQueryParameter("appid", this.mContext.getApplicationContext().getPackageName()).appendQueryParameter("exceptiontype", cls.getName()).appendQueryParameter("stacktrace", stringWriter.toString()).appendQueryParameter("eids", TextUtils.join(",", zzdi.zzkr())).appendQueryParameter("exceptionkey", str).appendQueryParameter("cl", "134102376").appendQueryParameter("rc", "dev").toString();
    }

    public void zza(Throwable th, String str) {
        if (zzu(this.mContext)) {
            Throwable zzd = zzd(th);
            if (zzd != null) {
                Class cls = th.getClass();
                List arrayList = new ArrayList();
                arrayList.add(zza(cls, zzd, str));
                zzu.zzfz().zza(arrayList, zzu.zzgd().zztp());
            }
        }
    }

    protected boolean zzb(Throwable th) {
        boolean z = true;
        if (th == null) {
            return false;
        }
        boolean z2 = false;
        boolean z3 = false;
        while (th != null) {
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                if (zzcg(stackTraceElement.getClassName())) {
                    z3 = true;
                }
                if (getClass().getName().equals(stackTraceElement.getClassName())) {
                    z2 = true;
                }
            }
            th = th.getCause();
        }
        if (!z3 || r2) {
            z = false;
        }
        return z;
    }

    public void zzc(Throwable th) {
        zza(th, "");
    }

    protected boolean zzcg(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith((String) zzdi.zzbav.get())) {
            return true;
        }
        try {
            return Class.forName(str).isAnnotationPresent(zziy.class);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Fail to check class type for class ";
            String valueOf = String.valueOf(str);
            zzb.zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            return false;
        }
    }

    protected boolean zzch(String str) {
        return TextUtils.isEmpty(str) ? false : str.startsWith("android.") || str.startsWith("java.");
    }
}
