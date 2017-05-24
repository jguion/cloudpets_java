package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.zzs;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
@TargetApi(14)
public class zzcu extends Thread {
    private boolean mStarted = false;
    private final Object zzakd;
    private final int zzatu;
    private final int zzatw;
    private boolean zzauq = false;
    private final zzcs zzaur;
    private final zzix zzaus;
    private final int zzaut;
    private final int zzauu;
    private final int zzauv;
    private boolean zzbl = false;

    @zziy
    class zza {
        final /* synthetic */ zzcu zzaux;
        final int zzavd;
        final int zzave;

        zza(zzcu com_google_android_gms_internal_zzcu, int i, int i2) {
            this.zzaux = com_google_android_gms_internal_zzcu;
            this.zzavd = i;
            this.zzave = i2;
        }
    }

    public zzcu(zzcs com_google_android_gms_internal_zzcs, zzix com_google_android_gms_internal_zzix) {
        this.zzaur = com_google_android_gms_internal_zzcs;
        this.zzaus = com_google_android_gms_internal_zzix;
        this.zzakd = new Object();
        this.zzatu = ((Integer) zzdi.zzbce.get()).intValue();
        this.zzauu = ((Integer) zzdi.zzbcf.get()).intValue();
        this.zzatw = ((Integer) zzdi.zzbcg.get()).intValue();
        this.zzauv = ((Integer) zzdi.zzbch.get()).intValue();
        this.zzaut = ((Integer) zzdi.zzbci.get()).intValue();
        setName("ContentFetchTask");
    }

    public void run() {
        while (true) {
            try {
                if (zzin()) {
                    Activity activity = zzu.zzgc().getActivity();
                    if (activity == null) {
                        zzb.zzdd("ContentFetchThread: no activity. Sleeping.");
                        zzip();
                    } else {
                        zza(activity);
                    }
                } else {
                    zzb.zzdd("ContentFetchTask: sleeping");
                    zzip();
                }
                Thread.sleep((long) (this.zzaut * Constants.MAX_DOWNLOADS));
            } catch (Throwable th) {
                zzb.zzb("Error in ContentFetchTask", th);
                this.zzaus.zza(th, "ContentFetchTask.run");
            }
            synchronized (this.zzakd) {
                while (this.zzauq) {
                    try {
                        zzb.zzdd("ContentFetchTask: waiting");
                        this.zzakd.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    public void wakeup() {
        synchronized (this.zzakd) {
            this.zzauq = false;
            this.zzakd.notifyAll();
            zzb.zzdd("ContentFetchThread: wakeup");
        }
    }

    zza zza(@Nullable View view, zzcr com_google_android_gms_internal_zzcr) {
        int i = 0;
        if (view == null) {
            return new zza(this, 0, 0);
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zza(this, 0, 0);
            }
            com_google_android_gms_internal_zzcr.zze(text.toString(), globalVisibleRect);
            return new zza(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzlt)) {
            com_google_android_gms_internal_zzcr.zzii();
            return zza((WebView) view, com_google_android_gms_internal_zzcr, globalVisibleRect) ? new zza(this, 0, 1) : new zza(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zza(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i2 = 0;
            int i3 = 0;
            while (i < viewGroup.getChildCount()) {
                zza zza = zza(viewGroup.getChildAt(i), com_google_android_gms_internal_zzcr);
                i3 += zza.zzavd;
                i2 += zza.zzave;
                i++;
            }
            return new zza(this, i3, i2);
        }
    }

    void zza(@Nullable Activity activity) {
        if (activity != null) {
            View view = null;
            try {
                if (!(activity.getWindow() == null || activity.getWindow().getDecorView() == null)) {
                    view = activity.getWindow().getDecorView().findViewById(16908290);
                }
            } catch (Throwable th) {
                zzb.zzdd("Failed getting root view of activity. Content not extracted.");
            }
            if (view != null) {
                zze(view);
            }
        }
    }

    void zza(zzcr com_google_android_gms_internal_zzcr, WebView webView, String str, boolean z) {
        com_google_android_gms_internal_zzcr.zzih();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (TextUtils.isEmpty(webView.getTitle())) {
                    com_google_android_gms_internal_zzcr.zzd(optString, z);
                } else {
                    String valueOf = String.valueOf(webView.getTitle());
                    com_google_android_gms_internal_zzcr.zzd(new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(optString).length()).append(valueOf).append("\n").append(optString).toString(), z);
                }
            }
            if (com_google_android_gms_internal_zzcr.zzid()) {
                this.zzaur.zzb(com_google_android_gms_internal_zzcr);
            }
        } catch (JSONException e) {
            zzb.zzdd("Json string may be malformed.");
        } catch (Throwable th) {
            zzb.zza("Failed to get webview content.", th);
            this.zzaus.zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    boolean zza(RunningAppProcessInfo runningAppProcessInfo) {
        return runningAppProcessInfo.importance == 100;
    }

    @TargetApi(19)
    boolean zza(final WebView webView, final zzcr com_google_android_gms_internal_zzcr, final boolean z) {
        if (!zzs.zzaxr()) {
            return false;
        }
        com_google_android_gms_internal_zzcr.zzii();
        webView.post(new Runnable(this) {
            final /* synthetic */ zzcu zzaux;
            ValueCallback<String> zzauy = new ValueCallback<String>(this) {
                final /* synthetic */ AnonymousClass2 zzavc;

                {
                    this.zzavc = r1;
                }

                public /* synthetic */ void onReceiveValue(Object obj) {
                    zzab((String) obj);
                }

                public void zzab(String str) {
                    this.zzavc.zzaux.zza(com_google_android_gms_internal_zzcr, webView, str, z);
                }
            };

            public void run() {
                if (webView.getSettings().getJavaScriptEnabled()) {
                    try {
                        webView.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzauy);
                    } catch (Throwable th) {
                        this.zzauy.onReceiveValue("");
                    }
                }
            }
        });
        return true;
    }

    boolean zze(@Nullable final View view) {
        if (view == null) {
            return false;
        }
        view.post(new Runnable(this) {
            final /* synthetic */ zzcu zzaux;

            public void run() {
                this.zzaux.zzf(view);
            }
        });
        return true;
    }

    void zzf(View view) {
        try {
            zzcr com_google_android_gms_internal_zzcr = new zzcr(this.zzatu, this.zzauu, this.zzatw, this.zzauv);
            zza zza = zza(view, com_google_android_gms_internal_zzcr);
            com_google_android_gms_internal_zzcr.zzij();
            if (zza.zzavd != 0 || zza.zzave != 0) {
                if (zza.zzave != 0 || com_google_android_gms_internal_zzcr.zzik() != 0) {
                    if (zza.zzave != 0 || !this.zzaur.zza(com_google_android_gms_internal_zzcr)) {
                        this.zzaur.zzc(com_google_android_gms_internal_zzcr);
                    }
                }
            }
        } catch (Throwable e) {
            zzb.zzb("Exception in fetchContentOnUIThread", e);
            this.zzaus.zza(e, "ContentFetchTask.fetchContent");
        }
    }

    boolean zzi(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager == null ? false : powerManager.isScreenOn();
    }

    public void zzim() {
        synchronized (this.zzakd) {
            if (this.mStarted) {
                zzb.zzdd("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    boolean zzin() {
        try {
            Context context = zzu.zzgc().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (zza(runningAppProcessInfo) && !keyguardManager.inKeyguardRestrictedInputMode() && zzi(context)) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public zzcr zzio() {
        return this.zzaur.zzil();
    }

    public void zzip() {
        synchronized (this.zzakd) {
            this.zzauq = true;
            zzb.zzdd("ContentFetchThread: paused, mPause = " + this.zzauq);
        }
    }

    public boolean zziq() {
        return this.zzauq;
    }
}
