package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.formats.zzg;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.ads.internal.zzu;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
class zzlx extends WebView implements OnGlobalLayoutListener, DownloadListener, zzlt {
    private final Object zzakd = new Object();
    private final zzd zzalo;
    private final VersionInfoParcel zzanh;
    private AdSizeParcel zzapc;
    private zzlf zzasf;
    private final WindowManager zzasl;
    @Nullable
    private final zzau zzbkp;
    private int zzbvw = -1;
    private int zzbvx = -1;
    private int zzbvz = -1;
    private int zzbwa = -1;
    private String zzcaj = "";
    private zzdo zzcak;
    private Boolean zzcpv;
    private final zza zzcvp;
    private final zzs zzcvq;
    private zzlu zzcvr;
    private com.google.android.gms.ads.internal.overlay.zzd zzcvs;
    private boolean zzcvt;
    private boolean zzcvu;
    private boolean zzcvv;
    private boolean zzcvw;
    private int zzcvx;
    private boolean zzcvy = true;
    boolean zzcvz = false;
    private zzly zzcwa;
    private boolean zzcwb;
    private boolean zzcwc;
    private zzg zzcwd;
    private int zzcwe;
    private int zzcwf;
    private zzdo zzcwg;
    private zzdo zzcwh;
    private zzdp zzcwi;
    private WeakReference<OnClickListener> zzcwj;
    private com.google.android.gms.ads.internal.overlay.zzd zzcwk;
    private Map<String, zzfj> zzcwl;

    @zziy
    public static class zza extends MutableContextWrapper {
        private Context zzask;
        private Activity zzctd;
        private Context zzcwn;

        public zza(Context context) {
            super(context);
            setBaseContext(context);
        }

        public Object getSystemService(String str) {
            return this.zzcwn.getSystemService(str);
        }

        public void setBaseContext(Context context) {
            this.zzask = context.getApplicationContext();
            this.zzctd = context instanceof Activity ? (Activity) context : null;
            this.zzcwn = context;
            super.setBaseContext(this.zzask);
        }

        public void startActivity(Intent intent) {
            if (this.zzctd != null) {
                this.zzctd.startActivity(intent);
                return;
            }
            intent.setFlags(268435456);
            this.zzask.startActivity(intent);
        }

        public Activity zzvn() {
            return this.zzctd;
        }

        public Context zzvo() {
            return this.zzcwn;
        }
    }

    protected zzlx(zza com_google_android_gms_internal_zzlx_zza, AdSizeParcel adSizeParcel, boolean z, boolean z2, @Nullable zzau com_google_android_gms_internal_zzau, VersionInfoParcel versionInfoParcel, zzdq com_google_android_gms_internal_zzdq, zzs com_google_android_gms_ads_internal_zzs, zzd com_google_android_gms_ads_internal_zzd) {
        super(com_google_android_gms_internal_zzlx_zza);
        this.zzcvp = com_google_android_gms_internal_zzlx_zza;
        this.zzapc = adSizeParcel;
        this.zzcvv = z;
        this.zzcvx = -1;
        this.zzbkp = com_google_android_gms_internal_zzau;
        this.zzanh = versionInfoParcel;
        this.zzcvq = com_google_android_gms_ads_internal_zzs;
        this.zzalo = com_google_android_gms_ads_internal_zzd;
        this.zzasl = (WindowManager) getContext().getSystemService("window");
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzu.zzfz().zza((Context) com_google_android_gms_internal_zzlx_zza, versionInfoParcel.zzcs, settings);
        zzu.zzgb().zza(getContext(), settings);
        setDownloadListener(this);
        zzxa();
        if (com.google.android.gms.common.util.zzs.zzaxp()) {
            addJavascriptInterface(new zzlz(this), "googleAdsJsInterface");
        }
        if (com.google.android.gms.common.util.zzs.zzaxk()) {
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        }
        this.zzasf = new zzlf(this.zzcvp.zzvn(), this, this, null);
        zzd(com_google_android_gms_internal_zzdq);
    }

    private void zzao(boolean z) {
        Map hashMap = new HashMap();
        hashMap.put("isVisible", z ? "1" : "0");
        zza("onAdVisibilityChanged", hashMap);
    }

    static zzlx zzb(Context context, AdSizeParcel adSizeParcel, boolean z, boolean z2, @Nullable zzau com_google_android_gms_internal_zzau, VersionInfoParcel versionInfoParcel, zzdq com_google_android_gms_internal_zzdq, zzs com_google_android_gms_ads_internal_zzs, zzd com_google_android_gms_ads_internal_zzd) {
        return new zzlx(new zza(context), adSizeParcel, z, z2, com_google_android_gms_internal_zzau, versionInfoParcel, com_google_android_gms_internal_zzdq, com_google_android_gms_ads_internal_zzs, com_google_android_gms_ads_internal_zzd);
    }

    private void zzd(zzdq com_google_android_gms_internal_zzdq) {
        zzxe();
        this.zzcwi = new zzdp(new zzdq(true, "make_wv", this.zzapc.zzaxi));
        this.zzcwi.zzkz().zzc(com_google_android_gms_internal_zzdq);
        this.zzcak = zzdm.zzb(this.zzcwi.zzkz());
        this.zzcwi.zza("native:view_create", this.zzcak);
        this.zzcwh = null;
        this.zzcwg = null;
    }

    private void zzww() {
        synchronized (this.zzakd) {
            this.zzcpv = zzu.zzgd().zztr();
            if (this.zzcpv == null) {
                try {
                    evaluateJavascript("(function(){})()", null);
                    zzb(Boolean.valueOf(true));
                } catch (IllegalStateException e) {
                    zzb(Boolean.valueOf(false));
                }
            }
        }
    }

    private void zzwx() {
        zzdm.zza(this.zzcwi.zzkz(), this.zzcak, "aeh2");
    }

    private void zzwy() {
        zzdm.zza(this.zzcwi.zzkz(), this.zzcak, "aebb2");
    }

    private void zzxa() {
        synchronized (this.zzakd) {
            if (this.zzcvv || this.zzapc.zzaxj) {
                if (VERSION.SDK_INT < 14) {
                    zzb.zzdd("Disabling hardware acceleration on an overlay.");
                    zzxb();
                } else {
                    zzb.zzdd("Enabling hardware acceleration on an overlay.");
                    zzxc();
                }
            } else if (VERSION.SDK_INT < 18) {
                zzb.zzdd("Disabling hardware acceleration on an AdView.");
                zzxb();
            } else {
                zzb.zzdd("Enabling hardware acceleration on an AdView.");
                zzxc();
            }
        }
    }

    private void zzxb() {
        synchronized (this.zzakd) {
            if (!this.zzcvw) {
                zzu.zzgb().zzp(this);
            }
            this.zzcvw = true;
        }
    }

    private void zzxc() {
        synchronized (this.zzakd) {
            if (this.zzcvw) {
                zzu.zzgb().zzo(this);
            }
            this.zzcvw = false;
        }
    }

    private void zzxd() {
        synchronized (this.zzakd) {
            this.zzcwl = null;
        }
    }

    private void zzxe() {
        if (this.zzcwi != null) {
            zzdq zzkz = this.zzcwi.zzkz();
            if (zzkz != null && zzu.zzgd().zztm() != null) {
                zzu.zzgd().zztm().zza(zzkz);
            }
        }
    }

    public void destroy() {
        synchronized (this.zzakd) {
            zzxe();
            this.zzasf.zzvb();
            if (this.zzcvs != null) {
                this.zzcvs.close();
                this.zzcvs.onDestroy();
                this.zzcvs = null;
            }
            this.zzcvr.reset();
            if (this.zzcvu) {
                return;
            }
            zzu.zzgw().zze(this);
            zzxd();
            this.zzcvu = true;
            zzkn.v("Initiating WebView self destruct sequence in 3...");
            this.zzcvr.zzwn();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.TargetApi(19)
    public void evaluateJavascript(java.lang.String r3, android.webkit.ValueCallback<java.lang.String> r4) {
        /*
        r2 = this;
        r1 = r2.zzakd;
        monitor-enter(r1);
        r0 = r2.isDestroyed();	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x0016;
    L_0x0009:
        r0 = "The webview is destroyed. Ignoring action.";
        com.google.android.gms.ads.internal.util.client.zzb.zzdf(r0);	 Catch:{ all -> 0x001b }
        if (r4 == 0) goto L_0x0014;
    L_0x0010:
        r0 = 0;
        r4.onReceiveValue(r0);	 Catch:{ all -> 0x001b }
    L_0x0014:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
    L_0x0015:
        return;
    L_0x0016:
        super.evaluateJavascript(r3, r4);	 Catch:{ all -> 0x001b }
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        goto L_0x0015;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlx.evaluateJavascript(java.lang.String, android.webkit.ValueCallback):void");
    }

    protected void finalize() throws Throwable {
        synchronized (this.zzakd) {
            if (!this.zzcvu) {
                this.zzcvr.reset();
                zzu.zzgw().zze(this);
                zzxd();
            }
        }
        super.finalize();
    }

    public String getRequestId() {
        String str;
        synchronized (this.zzakd) {
            str = this.zzcaj;
        }
        return str;
    }

    public int getRequestedOrientation() {
        int i;
        synchronized (this.zzakd) {
            i = this.zzcvx;
        }
        return i;
    }

    public View getView() {
        return this;
    }

    public WebView getWebView() {
        return this;
    }

    public boolean isDestroyed() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcvu;
        }
        return z;
    }

    public void loadData(String str, String str2, String str3) {
        synchronized (this.zzakd) {
            if (isDestroyed()) {
                zzb.zzdf("The webview is destroyed. Ignoring action.");
            } else {
                super.loadData(str, str2, str3);
            }
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        synchronized (this.zzakd) {
            if (isDestroyed()) {
                zzb.zzdf("The webview is destroyed. Ignoring action.");
            } else {
                super.loadDataWithBaseURL(str, str2, str3, str4, str5);
            }
        }
    }

    public void loadUrl(String str) {
        synchronized (this.zzakd) {
            if (isDestroyed()) {
                zzb.zzdf("The webview is destroyed. Ignoring action.");
            } else {
                try {
                    super.loadUrl(str);
                } catch (Throwable th) {
                    String valueOf = String.valueOf(th);
                    zzb.zzdf(new StringBuilder(String.valueOf(valueOf).length() + 24).append("Could not call loadUrl. ").append(valueOf).toString());
                }
            }
        }
    }

    protected void onAttachedToWindow() {
        boolean z = true;
        synchronized (this.zzakd) {
            super.onAttachedToWindow();
            if (!isDestroyed()) {
                this.zzasf.onAttachedToWindow();
            }
            boolean z2 = this.zzcwb;
            if (zzvr() == null || !zzvr().zzwj()) {
                z = z2;
            } else if (!this.zzcwc) {
                OnGlobalLayoutListener zzwk = zzvr().zzwk();
                if (zzwk != null) {
                    zzu.zzgx().zza(getView(), zzwk);
                }
                OnScrollChangedListener zzwl = zzvr().zzwl();
                if (zzwl != null) {
                    zzu.zzgx().zza(getView(), zzwl);
                }
                this.zzcwc = true;
            }
            zzao(z);
        }
    }

    protected void onDetachedFromWindow() {
        synchronized (this.zzakd) {
            if (!isDestroyed()) {
                this.zzasf.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzcwc && zzvr() != null && zzvr().zzwj() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                OnGlobalLayoutListener zzwk = zzvr().zzwk();
                if (zzwk != null) {
                    zzu.zzgb().zza(getViewTreeObserver(), zzwk);
                }
                OnScrollChangedListener zzwl = zzvr().zzwl();
                if (zzwl != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(zzwl);
                }
                this.zzcwc = false;
            }
        }
        zzao(false);
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzu.zzfz().zzb(getContext(), intent);
        } catch (ActivityNotFoundException e) {
            zzb.zzdd(new StringBuilder((String.valueOf(str).length() + 51) + String.valueOf(str4).length()).append("Couldn't find an Activity to view url/mimetype: ").append(str).append(" / ").append(str4).toString());
        }
    }

    @TargetApi(21)
    protected void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            if (VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
                if (zzvr() != null && zzvr().zzwu() != null) {
                    zzvr().zzwu().zzes();
                }
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (((Boolean) zzdi.zzbcx.get()).booleanValue()) {
            float axisValue = motionEvent.getAxisValue(9);
            float axisValue2 = motionEvent.getAxisValue(10);
            if ((motionEvent.getActionMasked() == 8 ? 1 : 0) != 0 && ((axisValue > 0.0f && !canScrollVertically(-1)) || ((axisValue < 0.0f && !canScrollVertically(1)) || ((axisValue2 > 0.0f && !canScrollHorizontally(-1)) || (axisValue2 < 0.0f && !canScrollHorizontally(1)))))) {
                return false;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public void onGlobalLayout() {
        boolean zzwv = zzwv();
        com.google.android.gms.ads.internal.overlay.zzd zzvp = zzvp();
        if (zzvp != null && zzwv) {
            zzvp.zzoy();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"DrawAllocation"})
    protected void onMeasure(int r10, int r11) {
        /*
        r9 = this;
        r0 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = 8;
        r6 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r4 = r9.zzakd;
        monitor-enter(r4);
        r1 = r9.isDestroyed();	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x0019;
    L_0x0012:
        r0 = 0;
        r1 = 0;
        r9.setMeasuredDimension(r0, r1);	 Catch:{ all -> 0x002e }
        monitor-exit(r4);	 Catch:{ all -> 0x002e }
    L_0x0018:
        return;
    L_0x0019:
        r1 = r9.isInEditMode();	 Catch:{ all -> 0x002e }
        if (r1 != 0) goto L_0x0029;
    L_0x001f:
        r1 = r9.zzcvv;	 Catch:{ all -> 0x002e }
        if (r1 != 0) goto L_0x0029;
    L_0x0023:
        r1 = r9.zzapc;	 Catch:{ all -> 0x002e }
        r1 = r1.zzaxl;	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x0031;
    L_0x0029:
        super.onMeasure(r10, r11);	 Catch:{ all -> 0x002e }
        monitor-exit(r4);	 Catch:{ all -> 0x002e }
        goto L_0x0018;
    L_0x002e:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x002e }
        throw r0;
    L_0x0031:
        r1 = r9.zzapc;	 Catch:{ all -> 0x002e }
        r1 = r1.zzaxm;	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x0082;
    L_0x0037:
        r0 = com.google.android.gms.internal.zzdi.zzbgf;	 Catch:{ all -> 0x002e }
        r0 = r0.get();	 Catch:{ all -> 0x002e }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x002e }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x002e }
        if (r0 != 0) goto L_0x004b;
    L_0x0045:
        r0 = com.google.android.gms.common.util.zzs.zzaxp();	 Catch:{ all -> 0x002e }
        if (r0 != 0) goto L_0x0050;
    L_0x004b:
        super.onMeasure(r10, r11);	 Catch:{ all -> 0x002e }
        monitor-exit(r4);	 Catch:{ all -> 0x002e }
        goto L_0x0018;
    L_0x0050:
        r0 = "/contentHeight";
        r1 = r9.zzwz();	 Catch:{ all -> 0x002e }
        r9.zza(r0, r1);	 Catch:{ all -> 0x002e }
        r0 = "(function() {  var height = -1;  if (document.body) { height = document.body.offsetHeight;}  else if (document.documentElement) {      height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  window.googleAdsJsInterface.notify(url);  })();";
        r9.zzdk(r0);	 Catch:{ all -> 0x002e }
        r0 = r9.zzcvp;	 Catch:{ all -> 0x002e }
        r0 = r0.getResources();	 Catch:{ all -> 0x002e }
        r0 = r0.getDisplayMetrics();	 Catch:{ all -> 0x002e }
        r0 = r0.density;	 Catch:{ all -> 0x002e }
        r1 = android.view.View.MeasureSpec.getSize(r10);	 Catch:{ all -> 0x002e }
        r2 = r9.zzcwf;	 Catch:{ all -> 0x002e }
        switch(r2) {
            case -1: goto L_0x007d;
            default: goto L_0x0073;
        };	 Catch:{ all -> 0x002e }
    L_0x0073:
        r2 = r9.zzcwf;	 Catch:{ all -> 0x002e }
        r2 = (float) r2;	 Catch:{ all -> 0x002e }
        r0 = r0 * r2;
        r0 = (int) r0;	 Catch:{ all -> 0x002e }
    L_0x0078:
        r9.setMeasuredDimension(r1, r0);	 Catch:{ all -> 0x002e }
        monitor-exit(r4);	 Catch:{ all -> 0x002e }
        goto L_0x0018;
    L_0x007d:
        r0 = android.view.View.MeasureSpec.getSize(r11);	 Catch:{ all -> 0x002e }
        goto L_0x0078;
    L_0x0082:
        r1 = r9.zzapc;	 Catch:{ all -> 0x002e }
        r1 = r1.zzaxj;	 Catch:{ all -> 0x002e }
        if (r1 == 0) goto L_0x00a0;
    L_0x0088:
        r0 = new android.util.DisplayMetrics;	 Catch:{ all -> 0x002e }
        r0.<init>();	 Catch:{ all -> 0x002e }
        r1 = r9.zzasl;	 Catch:{ all -> 0x002e }
        r1 = r1.getDefaultDisplay();	 Catch:{ all -> 0x002e }
        r1.getMetrics(r0);	 Catch:{ all -> 0x002e }
        r1 = r0.widthPixels;	 Catch:{ all -> 0x002e }
        r0 = r0.heightPixels;	 Catch:{ all -> 0x002e }
        r9.setMeasuredDimension(r1, r0);	 Catch:{ all -> 0x002e }
        monitor-exit(r4);	 Catch:{ all -> 0x002e }
        goto L_0x0018;
    L_0x00a0:
        r2 = android.view.View.MeasureSpec.getMode(r10);	 Catch:{ all -> 0x002e }
        r3 = android.view.View.MeasureSpec.getSize(r10);	 Catch:{ all -> 0x002e }
        r5 = android.view.View.MeasureSpec.getMode(r11);	 Catch:{ all -> 0x002e }
        r1 = android.view.View.MeasureSpec.getSize(r11);	 Catch:{ all -> 0x002e }
        if (r2 == r6) goto L_0x00b4;
    L_0x00b2:
        if (r2 != r8) goto L_0x014b;
    L_0x00b4:
        r2 = r3;
    L_0x00b5:
        if (r5 == r6) goto L_0x00b9;
    L_0x00b7:
        if (r5 != r8) goto L_0x00ba;
    L_0x00b9:
        r0 = r1;
    L_0x00ba:
        r5 = r9.zzapc;	 Catch:{ all -> 0x002e }
        r5 = r5.widthPixels;	 Catch:{ all -> 0x002e }
        if (r5 > r2) goto L_0x00c6;
    L_0x00c0:
        r2 = r9.zzapc;	 Catch:{ all -> 0x002e }
        r2 = r2.heightPixels;	 Catch:{ all -> 0x002e }
        if (r2 <= r0) goto L_0x0135;
    L_0x00c6:
        r0 = r9.zzcvp;	 Catch:{ all -> 0x002e }
        r0 = r0.getResources();	 Catch:{ all -> 0x002e }
        r0 = r0.getDisplayMetrics();	 Catch:{ all -> 0x002e }
        r0 = r0.density;	 Catch:{ all -> 0x002e }
        r2 = r9.zzapc;	 Catch:{ all -> 0x002e }
        r2 = r2.widthPixels;	 Catch:{ all -> 0x002e }
        r2 = (float) r2;	 Catch:{ all -> 0x002e }
        r2 = r2 / r0;
        r2 = (int) r2;	 Catch:{ all -> 0x002e }
        r5 = r9.zzapc;	 Catch:{ all -> 0x002e }
        r5 = r5.heightPixels;	 Catch:{ all -> 0x002e }
        r5 = (float) r5;	 Catch:{ all -> 0x002e }
        r5 = r5 / r0;
        r5 = (int) r5;	 Catch:{ all -> 0x002e }
        r3 = (float) r3;	 Catch:{ all -> 0x002e }
        r3 = r3 / r0;
        r3 = (int) r3;	 Catch:{ all -> 0x002e }
        r1 = (float) r1;	 Catch:{ all -> 0x002e }
        r0 = r1 / r0;
        r0 = (int) r0;	 Catch:{ all -> 0x002e }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x002e }
        r6 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r1.<init>(r6);	 Catch:{ all -> 0x002e }
        r6 = "Not enough space to show ad. Needs ";
        r1 = r1.append(r6);	 Catch:{ all -> 0x002e }
        r1 = r1.append(r2);	 Catch:{ all -> 0x002e }
        r2 = "x";
        r1 = r1.append(r2);	 Catch:{ all -> 0x002e }
        r1 = r1.append(r5);	 Catch:{ all -> 0x002e }
        r2 = " dp, but only has ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x002e }
        r1 = r1.append(r3);	 Catch:{ all -> 0x002e }
        r2 = "x";
        r1 = r1.append(r2);	 Catch:{ all -> 0x002e }
        r0 = r1.append(r0);	 Catch:{ all -> 0x002e }
        r1 = " dp.";
        r0 = r0.append(r1);	 Catch:{ all -> 0x002e }
        r0 = r0.toString();	 Catch:{ all -> 0x002e }
        com.google.android.gms.ads.internal.util.client.zzb.zzdf(r0);	 Catch:{ all -> 0x002e }
        r0 = r9.getVisibility();	 Catch:{ all -> 0x002e }
        if (r0 == r7) goto L_0x012d;
    L_0x0129:
        r0 = 4;
        r9.setVisibility(r0);	 Catch:{ all -> 0x002e }
    L_0x012d:
        r0 = 0;
        r1 = 0;
        r9.setMeasuredDimension(r0, r1);	 Catch:{ all -> 0x002e }
    L_0x0132:
        monitor-exit(r4);	 Catch:{ all -> 0x002e }
        goto L_0x0018;
    L_0x0135:
        r0 = r9.getVisibility();	 Catch:{ all -> 0x002e }
        if (r0 == r7) goto L_0x013f;
    L_0x013b:
        r0 = 0;
        r9.setVisibility(r0);	 Catch:{ all -> 0x002e }
    L_0x013f:
        r0 = r9.zzapc;	 Catch:{ all -> 0x002e }
        r0 = r0.widthPixels;	 Catch:{ all -> 0x002e }
        r1 = r9.zzapc;	 Catch:{ all -> 0x002e }
        r1 = r1.heightPixels;	 Catch:{ all -> 0x002e }
        r9.setMeasuredDimension(r0, r1);	 Catch:{ all -> 0x002e }
        goto L_0x0132;
    L_0x014b:
        r2 = r0;
        goto L_0x00b5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlx.onMeasure(int, int):void");
    }

    public void onPause() {
        if (!isDestroyed()) {
            try {
                if (com.google.android.gms.common.util.zzs.zzaxk()) {
                    super.onPause();
                }
            } catch (Throwable e) {
                zzb.zzb("Could not pause webview.", e);
            }
        }
    }

    public void onResume() {
        if (!isDestroyed()) {
            try {
                if (com.google.android.gms.common.util.zzs.zzaxk()) {
                    super.onResume();
                }
            } catch (Throwable e) {
                zzb.zzb("Could not resume webview.", e);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (zzvr().zzwj()) {
            synchronized (this.zzakd) {
                if (this.zzcwd != null) {
                    this.zzcwd.zzc(motionEvent);
                }
            }
        } else if (this.zzbkp != null) {
            this.zzbkp.zza(motionEvent);
        }
        return isDestroyed() ? false : super.onTouchEvent(motionEvent);
    }

    public void setContext(Context context) {
        this.zzcvp.setBaseContext(context);
        this.zzasf.zzl(this.zzcvp.zzvn());
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.zzcwj = new WeakReference(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public void setRequestedOrientation(int i) {
        synchronized (this.zzakd) {
            this.zzcvx = i;
            if (this.zzcvs != null) {
                this.zzcvs.setRequestedOrientation(this.zzcvx);
            }
        }
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzlu) {
            this.zzcvr = (zzlu) webViewClient;
        }
    }

    public void stopLoading() {
        if (!isDestroyed()) {
            try {
                super.stopLoading();
            } catch (Throwable e) {
                zzb.zzb("Could not stop loading webview.", e);
            }
        }
    }

    public void zza(Context context, AdSizeParcel adSizeParcel, zzdq com_google_android_gms_internal_zzdq) {
        synchronized (this.zzakd) {
            this.zzasf.zzvb();
            setContext(context);
            this.zzcvs = null;
            this.zzapc = adSizeParcel;
            this.zzcvv = false;
            this.zzcvt = false;
            this.zzcaj = "";
            this.zzcvx = -1;
            zzu.zzgb().zzm(this);
            loadUrl("about:blank");
            this.zzcvr.reset();
            setOnTouchListener(null);
            setOnClickListener(null);
            this.zzcvy = true;
            this.zzcvz = false;
            this.zzcwa = null;
            zzd(com_google_android_gms_internal_zzdq);
            this.zzcwb = false;
            this.zzcwe = 0;
            zzu.zzgw().zze(this);
            zzxd();
        }
    }

    public void zza(AdSizeParcel adSizeParcel) {
        synchronized (this.zzakd) {
            this.zzapc = adSizeParcel;
            requestLayout();
        }
    }

    public void zza(zzcj com_google_android_gms_internal_zzcj, boolean z) {
        synchronized (this.zzakd) {
            this.zzcwb = z;
        }
        zzao(z);
    }

    public void zza(zzly com_google_android_gms_internal_zzly) {
        synchronized (this.zzakd) {
            if (this.zzcwa != null) {
                zzb.e("Attempt to create multiple AdWebViewVideoControllers.");
                return;
            }
            this.zzcwa = com_google_android_gms_internal_zzly;
        }
    }

    @TargetApi(19)
    protected void zza(String str, ValueCallback<String> valueCallback) {
        synchronized (this.zzakd) {
            if (isDestroyed()) {
                zzb.zzdf("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(null);
                }
            } else {
                evaluateJavascript(str, valueCallback);
            }
        }
    }

    public void zza(String str, zzev com_google_android_gms_internal_zzev) {
        if (this.zzcvr != null) {
            this.zzcvr.zza(str, com_google_android_gms_internal_zzev);
        }
    }

    public void zza(String str, Map<String, ?> map) {
        try {
            zzb(str, zzu.zzfz().zzan((Map) map));
        } catch (JSONException e) {
            zzb.zzdf("Could not convert parameters to JSON.");
        }
    }

    public void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        zzj(str, jSONObject.toString());
    }

    public void zzah(int i) {
        if (i == 0) {
            zzwy();
        }
        zzwx();
        if (this.zzcwi.zzkz() != null) {
            this.zzcwi.zzkz().zzh("close_type", String.valueOf(i));
        }
        Map hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.zzanh.zzcs);
        zza("onhide", hashMap);
    }

    public void zzaj(boolean z) {
        synchronized (this.zzakd) {
            this.zzcvv = z;
            zzxa();
        }
    }

    public void zzak(boolean z) {
        synchronized (this.zzakd) {
            if (this.zzcvs != null) {
                this.zzcvs.zza(this.zzcvr.zzib(), z);
            } else {
                this.zzcvt = z;
            }
        }
    }

    public void zzal(boolean z) {
        synchronized (this.zzakd) {
            this.zzcvy = z;
        }
    }

    public void zzam(boolean z) {
        synchronized (this.zzakd) {
            this.zzcwe = (z ? 1 : -1) + this.zzcwe;
            if (this.zzcwe <= 0 && this.zzcvs != null) {
                this.zzcvs.zzpb();
            }
        }
    }

    public void zzb(zzg com_google_android_gms_ads_internal_formats_zzg) {
        synchronized (this.zzakd) {
            this.zzcwd = com_google_android_gms_ads_internal_formats_zzg;
        }
    }

    public void zzb(com.google.android.gms.ads.internal.overlay.zzd com_google_android_gms_ads_internal_overlay_zzd) {
        synchronized (this.zzakd) {
            this.zzcvs = com_google_android_gms_ads_internal_overlay_zzd;
        }
    }

    void zzb(Boolean bool) {
        synchronized (this.zzakd) {
            this.zzcpv = bool;
        }
        zzu.zzgd().zzb(bool);
    }

    public void zzb(String str, zzev com_google_android_gms_internal_zzev) {
        if (this.zzcvr != null) {
            this.zzcvr.zzb(str, com_google_android_gms_internal_zzev);
        }
    }

    public void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(window.AFMA_ReceiveMessage || function() {})('");
        stringBuilder.append(str);
        stringBuilder.append("'");
        stringBuilder.append(",");
        stringBuilder.append(jSONObject2);
        stringBuilder.append(");");
        String str2 = "Dispatching AFMA event: ";
        jSONObject2 = String.valueOf(stringBuilder.toString());
        zzb.zzdd(jSONObject2.length() != 0 ? str2.concat(jSONObject2) : new String(str2));
        zzdk(stringBuilder.toString());
    }

    public void zzc(com.google.android.gms.ads.internal.overlay.zzd com_google_android_gms_ads_internal_overlay_zzd) {
        synchronized (this.zzakd) {
            this.zzcwk = com_google_android_gms_ads_internal_overlay_zzd;
        }
    }

    public void zzdg(String str) {
        synchronized (this.zzakd) {
            try {
                super.loadUrl(str);
            } catch (Throwable th) {
                String valueOf = String.valueOf(th);
                zzb.zzdf(new StringBuilder(String.valueOf(valueOf).length() + 24).append("Could not call loadUrl. ").append(valueOf).toString());
            }
        }
    }

    public void zzdh(String str) {
        synchronized (this.zzakd) {
            if (str == null) {
                str = "";
            }
            this.zzcaj = str;
        }
    }

    protected void zzdj(String str) {
        synchronized (this.zzakd) {
            if (isDestroyed()) {
                zzb.zzdf("The webview is destroyed. Ignoring action.");
            } else {
                loadUrl(str);
            }
        }
    }

    protected void zzdk(String str) {
        if (com.google.android.gms.common.util.zzs.zzaxr()) {
            if (zztr() == null) {
                zzww();
            }
            if (zztr().booleanValue()) {
                zza(str, null);
                return;
            }
            String str2 = "javascript:";
            String valueOf = String.valueOf(str);
            zzdj(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return;
        }
        str2 = "javascript:";
        valueOf = String.valueOf(str);
        zzdj(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    public zzd zzdp() {
        return this.zzalo;
    }

    public AdSizeParcel zzdt() {
        AdSizeParcel adSizeParcel;
        synchronized (this.zzakd) {
            adSizeParcel = this.zzapc;
        }
        return adSizeParcel;
    }

    public void zzel() {
        synchronized (this.zzakd) {
            this.zzcvz = true;
            if (this.zzcvq != null) {
                this.zzcvq.zzel();
            }
        }
    }

    public void zzem() {
        synchronized (this.zzakd) {
            this.zzcvz = false;
            if (this.zzcvq != null) {
                this.zzcvq.zzem();
            }
        }
    }

    public void zzj(String str, String str2) {
        zzdk(new StringBuilder((String.valueOf(str).length() + 3) + String.valueOf(str2).length()).append(str).append("(").append(str2).append(");").toString());
    }

    public void zzoz() {
        if (this.zzcwg == null) {
            zzdm.zza(this.zzcwi.zzkz(), this.zzcak, "aes2");
            this.zzcwg = zzdm.zzb(this.zzcwi.zzkz());
            this.zzcwi.zza("native:view_show", this.zzcwg);
        }
        Map hashMap = new HashMap(1);
        hashMap.put("version", this.zzanh.zzcs);
        zza("onshow", hashMap);
    }

    public boolean zzpr() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcvy;
        }
        return z;
    }

    Boolean zztr() {
        Boolean bool;
        synchronized (this.zzakd) {
            bool = this.zzcpv;
        }
        return bool;
    }

    public void zzvl() {
        zzwx();
        Map hashMap = new HashMap(1);
        hashMap.put("version", this.zzanh.zzcs);
        zza("onhide", hashMap);
    }

    public void zzvm() {
        Map hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzu.zzfz().zzfg()));
        hashMap.put("app_volume", String.valueOf(zzu.zzfz().zzfe()));
        hashMap.put("device_volume", String.valueOf(zzu.zzfz().zzal(getContext())));
        zza("volume", hashMap);
    }

    public Activity zzvn() {
        return this.zzcvp.zzvn();
    }

    public Context zzvo() {
        return this.zzcvp.zzvo();
    }

    public com.google.android.gms.ads.internal.overlay.zzd zzvp() {
        com.google.android.gms.ads.internal.overlay.zzd com_google_android_gms_ads_internal_overlay_zzd;
        synchronized (this.zzakd) {
            com_google_android_gms_ads_internal_overlay_zzd = this.zzcvs;
        }
        return com_google_android_gms_ads_internal_overlay_zzd;
    }

    public com.google.android.gms.ads.internal.overlay.zzd zzvq() {
        com.google.android.gms.ads.internal.overlay.zzd com_google_android_gms_ads_internal_overlay_zzd;
        synchronized (this.zzakd) {
            com_google_android_gms_ads_internal_overlay_zzd = this.zzcwk;
        }
        return com_google_android_gms_ads_internal_overlay_zzd;
    }

    public zzlu zzvr() {
        return this.zzcvr;
    }

    public boolean zzvs() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcvt;
        }
        return z;
    }

    public zzau zzvt() {
        return this.zzbkp;
    }

    public VersionInfoParcel zzvu() {
        return this.zzanh;
    }

    public boolean zzvv() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcvv;
        }
        return z;
    }

    public void zzvw() {
        synchronized (this.zzakd) {
            zzkn.v("Destroying WebView!");
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzlx zzcwm;

                {
                    this.zzcwm = r1;
                }

                public void run() {
                    super.destroy();
                }
            });
        }
    }

    public boolean zzvx() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcvz;
        }
        return z;
    }

    public zzls zzvy() {
        return null;
    }

    public zzdo zzvz() {
        return this.zzcak;
    }

    public zzdp zzwa() {
        return this.zzcwi;
    }

    public zzly zzwb() {
        zzly com_google_android_gms_internal_zzly;
        synchronized (this.zzakd) {
            com_google_android_gms_internal_zzly = this.zzcwa;
        }
        return com_google_android_gms_internal_zzly;
    }

    public boolean zzwc() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcwe > 0;
        }
        return z;
    }

    public void zzwd() {
        this.zzasf.zzva();
    }

    public void zzwe() {
        if (this.zzcwh == null) {
            this.zzcwh = zzdm.zzb(this.zzcwi.zzkz());
            this.zzcwi.zza("native:view_load", this.zzcwh);
        }
    }

    public OnClickListener zzwf() {
        return (OnClickListener) this.zzcwj.get();
    }

    public zzg zzwg() {
        zzg com_google_android_gms_ads_internal_formats_zzg;
        synchronized (this.zzakd) {
            com_google_android_gms_ads_internal_formats_zzg = this.zzcwd;
        }
        return com_google_android_gms_ads_internal_formats_zzg;
    }

    public void zzwh() {
        setBackgroundColor(0);
    }

    public boolean zzwv() {
        if (!zzvr().zzib() && !zzvr().zzwj()) {
            return false;
        }
        int i;
        int i2;
        DisplayMetrics zza = zzu.zzfz().zza(this.zzasl);
        int zzb = zzm.zzjr().zzb(zza, zza.widthPixels);
        int zzb2 = zzm.zzjr().zzb(zza, zza.heightPixels);
        Activity zzvn = zzvn();
        if (zzvn == null || zzvn.getWindow() == null) {
            i = zzb2;
            i2 = zzb;
        } else {
            int[] zzh = zzu.zzfz().zzh(zzvn);
            i2 = zzm.zzjr().zzb(zza, zzh[0]);
            i = zzm.zzjr().zzb(zza, zzh[1]);
        }
        if (this.zzbvw == zzb && this.zzbvx == zzb2 && this.zzbvz == i2 && this.zzbwa == i) {
            return false;
        }
        boolean z = (this.zzbvw == zzb && this.zzbvx == zzb2) ? false : true;
        this.zzbvw = zzb;
        this.zzbvx = zzb2;
        this.zzbvz = i2;
        this.zzbwa = i;
        new zzhm(this).zza(zzb, zzb2, i2, i, zza.density, this.zzasl.getDefaultDisplay().getRotation());
        return z;
    }

    zzev zzwz() {
        return new zzev(this) {
            final /* synthetic */ zzlx zzcwm;

            {
                this.zzcwm = r1;
            }

            public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
                if (map != null) {
                    String str = (String) map.get("height");
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            int parseInt = Integer.parseInt(str);
                            synchronized (this.zzcwm.zzakd) {
                                if (this.zzcwm.zzcwf != parseInt) {
                                    this.zzcwm.zzcwf = parseInt;
                                    this.zzcwm.requestLayout();
                                }
                            }
                        } catch (Throwable e) {
                            zzb.zzd("Exception occurred while getting webview content height", e);
                        }
                    }
                }
            }
        };
    }
}
