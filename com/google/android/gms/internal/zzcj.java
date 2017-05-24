package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public abstract class zzcj implements OnGlobalLayoutListener, OnScrollChangedListener {
    protected final Object zzakd = new Object();
    private boolean zzaoy = false;
    private zzlc zzasa;
    private final WeakReference<zzke> zzasg;
    private WeakReference<ViewTreeObserver> zzash;
    private final zzcq zzasi;
    protected final zzcl zzasj;
    private final Context zzask;
    private final WindowManager zzasl;
    private final PowerManager zzasm;
    private final KeyguardManager zzasn;
    @Nullable
    private zzcn zzaso;
    private boolean zzasp;
    private boolean zzasq = false;
    private boolean zzasr;
    private boolean zzass;
    private boolean zzast;
    @Nullable
    BroadcastReceiver zzasu;
    private final HashSet<zzck> zzasv = new HashSet();
    private final zzev zzasw = new zzev(this) {
        final /* synthetic */ zzcj zzasz;

        {
            this.zzasz = r1;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            if (this.zzasz.zzb((Map) map)) {
                this.zzasz.zza(com_google_android_gms_internal_zzlt.getView(), (Map) map);
            }
        }
    };
    private final zzev zzasx = new zzev(this) {
        final /* synthetic */ zzcj zzasz;

        {
            this.zzasz = r1;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            if (this.zzasz.zzb((Map) map)) {
                String str = "Received request to untrack: ";
                String valueOf = String.valueOf(this.zzasz.zzasj.zzia());
                com.google.android.gms.ads.internal.util.client.zzb.zzdd(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                this.zzasz.destroy();
            }
        }
    };
    private final zzev zzasy = new zzev(this) {
        final /* synthetic */ zzcj zzasz;

        {
            this.zzasz = r1;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            if (this.zzasz.zzb((Map) map) && map.containsKey("isVisible")) {
                boolean z = "1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible"));
                this.zzasz.zzj(Boolean.valueOf(z).booleanValue());
            }
        }
    };

    public static class zza implements zzcq {
        private WeakReference<zzi> zzata;

        public zza(zzi com_google_android_gms_ads_internal_formats_zzi) {
            this.zzata = new WeakReference(com_google_android_gms_ads_internal_formats_zzi);
        }

        @Nullable
        public View zzhu() {
            zzi com_google_android_gms_ads_internal_formats_zzi = (zzi) this.zzata.get();
            return com_google_android_gms_ads_internal_formats_zzi != null ? com_google_android_gms_ads_internal_formats_zzi.zzly() : null;
        }

        public boolean zzhv() {
            return this.zzata.get() == null;
        }

        public zzcq zzhw() {
            return new zzb((zzi) this.zzata.get());
        }
    }

    public static class zzb implements zzcq {
        private zzi zzatb;

        public zzb(zzi com_google_android_gms_ads_internal_formats_zzi) {
            this.zzatb = com_google_android_gms_ads_internal_formats_zzi;
        }

        public View zzhu() {
            return this.zzatb != null ? this.zzatb.zzly() : null;
        }

        public boolean zzhv() {
            return this.zzatb == null;
        }

        public zzcq zzhw() {
            return this;
        }
    }

    public static class zzc implements zzcq {
        @Nullable
        private final View mView;
        @Nullable
        private final zzke zzatc;

        public zzc(View view, zzke com_google_android_gms_internal_zzke) {
            this.mView = view;
            this.zzatc = com_google_android_gms_internal_zzke;
        }

        public View zzhu() {
            return this.mView;
        }

        public boolean zzhv() {
            return this.zzatc == null || this.mView == null;
        }

        public zzcq zzhw() {
            return this;
        }
    }

    public static class zzd implements zzcq {
        private final WeakReference<View> zzatd;
        private final WeakReference<zzke> zzate;

        public zzd(View view, zzke com_google_android_gms_internal_zzke) {
            this.zzatd = new WeakReference(view);
            this.zzate = new WeakReference(com_google_android_gms_internal_zzke);
        }

        public View zzhu() {
            return (View) this.zzatd.get();
        }

        public boolean zzhv() {
            return this.zzatd.get() == null || this.zzate.get() == null;
        }

        public zzcq zzhw() {
            return new zzc((View) this.zzatd.get(), (zzke) this.zzate.get());
        }
    }

    public zzcj(Context context, AdSizeParcel adSizeParcel, zzke com_google_android_gms_internal_zzke, VersionInfoParcel versionInfoParcel, zzcq com_google_android_gms_internal_zzcq) {
        this.zzasg = new WeakReference(com_google_android_gms_internal_zzke);
        this.zzasi = com_google_android_gms_internal_zzcq;
        this.zzash = new WeakReference(null);
        this.zzasr = true;
        this.zzast = false;
        this.zzasa = new zzlc(200);
        this.zzasj = new zzcl(UUID.randomUUID().toString(), versionInfoParcel, adSizeParcel.zzaxi, com_google_android_gms_internal_zzke.zzcod, com_google_android_gms_internal_zzke.zzib(), adSizeParcel.zzaxl);
        this.zzasl = (WindowManager) context.getSystemService("window");
        this.zzasm = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzasn = (KeyguardManager) context.getSystemService("keyguard");
        this.zzask = context;
    }

    protected void destroy() {
        synchronized (this.zzakd) {
            zzhp();
            zzhk();
            this.zzasr = false;
            zzhm();
        }
    }

    boolean isScreenOn() {
        return this.zzasm.isScreenOn();
    }

    public void onGlobalLayout() {
        zzk(2);
    }

    public void onScrollChanged() {
        zzk(1);
    }

    public void pause() {
        synchronized (this.zzakd) {
            this.zzaoy = true;
            zzk(3);
        }
    }

    public void resume() {
        synchronized (this.zzakd) {
            this.zzaoy = false;
            zzk(3);
        }
    }

    public void stop() {
        synchronized (this.zzakd) {
            this.zzasq = true;
            zzk(3);
        }
    }

    protected int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    protected void zza(View view, Map<String, String> map) {
        zzk(3);
    }

    public void zza(zzck com_google_android_gms_internal_zzck) {
        this.zzasv.add(com_google_android_gms_internal_zzck);
    }

    public void zza(zzcn com_google_android_gms_internal_zzcn) {
        synchronized (this.zzakd) {
            this.zzaso = com_google_android_gms_internal_zzcn;
        }
    }

    protected void zza(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONArray.put(jSONObject);
            jSONObject2.put("units", jSONArray);
            zzb(jSONObject2);
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Skipping active view message.", th);
        }
    }

    protected abstract void zzb(JSONObject jSONObject);

    protected boolean zzb(@Nullable Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = (String) map.get("hashCode");
        boolean z = !TextUtils.isEmpty(str) && str.equals(this.zzasj.zzia());
        return z;
    }

    protected void zzc(zzfz com_google_android_gms_internal_zzfz) {
        com_google_android_gms_internal_zzfz.zza("/updateActiveView", this.zzasw);
        com_google_android_gms_internal_zzfz.zza("/untrackActiveViewUnit", this.zzasx);
        com_google_android_gms_internal_zzfz.zza("/visibilityChanged", this.zzasy);
    }

    protected JSONObject zzd(@Nullable View view) throws JSONException {
        if (view == null) {
            return zzhs();
        }
        boolean isAttachedToWindow = zzu.zzgb().isAttachedToWindow(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Failure getting view location.", e);
        }
        DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        rect2.right = this.zzasl.getDefaultDisplay().getWidth();
        rect2.bottom = this.zzasl.getDefaultDisplay().getHeight();
        Rect rect3 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect3, null);
        Rect rect4 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect4);
        Rect rect5 = new Rect();
        view.getHitRect(rect5);
        JSONObject zzhq = zzhq();
        zzhq.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", isAttachedToWindow).put("viewBox", new JSONObject().put("top", zza(rect2.top, displayMetrics)).put("bottom", zza(rect2.bottom, displayMetrics)).put("left", zza(rect2.left, displayMetrics)).put("right", zza(rect2.right, displayMetrics))).put("adBox", new JSONObject().put("top", zza(rect.top, displayMetrics)).put("bottom", zza(rect.bottom, displayMetrics)).put("left", zza(rect.left, displayMetrics)).put("right", zza(rect.right, displayMetrics))).put("globalVisibleBox", new JSONObject().put("top", zza(rect3.top, displayMetrics)).put("bottom", zza(rect3.bottom, displayMetrics)).put("left", zza(rect3.left, displayMetrics)).put("right", zza(rect3.right, displayMetrics))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect4.top, displayMetrics)).put("bottom", zza(rect4.bottom, displayMetrics)).put("left", zza(rect4.left, displayMetrics)).put("right", zza(rect4.right, displayMetrics))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect5.top, displayMetrics)).put("bottom", zza(rect5.bottom, displayMetrics)).put("left", zza(rect5.left, displayMetrics)).put("right", zza(rect5.right, displayMetrics))).put("screenDensity", (double) displayMetrics.density).put("isVisible", zzu.zzfz().zza(view, this.zzasm, this.zzasn));
        return zzhq;
    }

    protected void zzd(zzfz com_google_android_gms_internal_zzfz) {
        com_google_android_gms_internal_zzfz.zzb("/visibilityChanged", this.zzasy);
        com_google_android_gms_internal_zzfz.zzb("/untrackActiveViewUnit", this.zzasx);
        com_google_android_gms_internal_zzfz.zzb("/updateActiveView", this.zzasw);
    }

    protected void zzhj() {
        synchronized (this.zzakd) {
            if (this.zzasu != null) {
                return;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.zzasu = new BroadcastReceiver(this) {
                final /* synthetic */ zzcj zzasz;

                {
                    this.zzasz = r1;
                }

                public void onReceive(Context context, Intent intent) {
                    this.zzasz.zzk(3);
                }
            };
            this.zzask.registerReceiver(this.zzasu, intentFilter);
        }
    }

    protected void zzhk() {
        synchronized (this.zzakd) {
            if (this.zzasu != null) {
                try {
                    this.zzask.unregisterReceiver(this.zzasu);
                } catch (Throwable e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Failed trying to unregister the receiver", e);
                } catch (Throwable e2) {
                    zzu.zzgd().zza(e2, "AbstractActiveViewUnit.stopScreenStatusMonitoring");
                }
                this.zzasu = null;
            }
        }
    }

    public void zzhl() {
        synchronized (this.zzakd) {
            if (this.zzasr) {
                this.zzass = true;
                try {
                    zza(zzht());
                } catch (Throwable e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzb("JSON failure while processing active view data.", e);
                } catch (Throwable e2) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Failure while processing active view data.", e2);
                }
                String str = "Untracking ad unit: ";
                String valueOf = String.valueOf(this.zzasj.zzia());
                com.google.android.gms.ads.internal.util.client.zzb.zzdd(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
    }

    protected void zzhm() {
        if (this.zzaso != null) {
            this.zzaso.zza(this);
        }
    }

    public boolean zzhn() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzasr;
        }
        return z;
    }

    protected void zzho() {
        View zzhu = this.zzasi.zzhw().zzhu();
        if (zzhu != null) {
            ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzash.get();
            ViewTreeObserver viewTreeObserver2 = zzhu.getViewTreeObserver();
            if (viewTreeObserver2 != viewTreeObserver) {
                zzhp();
                if (!this.zzasp || (viewTreeObserver != null && viewTreeObserver.isAlive())) {
                    this.zzasp = true;
                    viewTreeObserver2.addOnScrollChangedListener(this);
                    viewTreeObserver2.addOnGlobalLayoutListener(this);
                }
                this.zzash = new WeakReference(viewTreeObserver2);
            }
        }
    }

    protected void zzhp() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzash.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    protected JSONObject zzhq() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zzasj.zzhy()).put("activeViewJSON", this.zzasj.zzhz()).put("timestamp", zzu.zzgf().elapsedRealtime()).put("adFormat", this.zzasj.zzhx()).put("hashCode", this.zzasj.zzia()).put("isMraid", this.zzasj.zzib()).put("isStopped", this.zzasq).put("isPaused", this.zzaoy).put("isScreenOn", isScreenOn()).put("isNative", this.zzasj.zzic()).put("appMuted", zzu.zzfz().zzfg()).put("appVolume", (double) zzu.zzfz().zzfe()).put("deviceVolume", (double) zzu.zzfz().zzal(this.zzask));
        return jSONObject;
    }

    protected abstract boolean zzhr();

    protected JSONObject zzhs() throws JSONException {
        return zzhq().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
    }

    protected JSONObject zzht() throws JSONException {
        JSONObject zzhq = zzhq();
        zzhq.put("doneReasonCode", "u");
        return zzhq;
    }

    protected void zzj(boolean z) {
        Iterator it = this.zzasv.iterator();
        while (it.hasNext()) {
            ((zzck) it.next()).zza(this, z);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void zzk(int r8) {
        /*
        r7 = this;
        r0 = 0;
        r1 = 1;
        r3 = r7.zzakd;
        monitor-enter(r3);
        r2 = r7.zzhr();	 Catch:{ all -> 0x0041 }
        if (r2 == 0) goto L_0x000f;
    L_0x000b:
        r2 = r7.zzasr;	 Catch:{ all -> 0x0041 }
        if (r2 != 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r3);	 Catch:{ all -> 0x0041 }
    L_0x0010:
        return;
    L_0x0011:
        r2 = r7.zzasi;	 Catch:{ all -> 0x0041 }
        r4 = r2.zzhu();	 Catch:{ all -> 0x0041 }
        if (r4 == 0) goto L_0x0044;
    L_0x0019:
        r2 = com.google.android.gms.ads.internal.zzu.zzfz();	 Catch:{ all -> 0x0041 }
        r5 = r7.zzasm;	 Catch:{ all -> 0x0041 }
        r6 = r7.zzasn;	 Catch:{ all -> 0x0041 }
        r2 = r2.zza(r4, r5, r6);	 Catch:{ all -> 0x0041 }
        if (r2 == 0) goto L_0x0044;
    L_0x0027:
        r2 = new android.graphics.Rect;	 Catch:{ all -> 0x0041 }
        r2.<init>();	 Catch:{ all -> 0x0041 }
        r5 = 0;
        r2 = r4.getGlobalVisibleRect(r2, r5);	 Catch:{ all -> 0x0041 }
        if (r2 == 0) goto L_0x0044;
    L_0x0033:
        r2 = r1;
    L_0x0034:
        r5 = r7.zzasi;	 Catch:{ all -> 0x0041 }
        r5 = r5.zzhv();	 Catch:{ all -> 0x0041 }
        if (r5 == 0) goto L_0x0046;
    L_0x003c:
        r7.zzhl();	 Catch:{ all -> 0x0041 }
        monitor-exit(r3);	 Catch:{ all -> 0x0041 }
        goto L_0x0010;
    L_0x0041:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0041 }
        throw r0;
    L_0x0044:
        r2 = r0;
        goto L_0x0034;
    L_0x0046:
        if (r8 != r1) goto L_0x0049;
    L_0x0048:
        r0 = r1;
    L_0x0049:
        if (r0 == 0) goto L_0x0059;
    L_0x004b:
        r0 = r7.zzasa;	 Catch:{ all -> 0x0041 }
        r0 = r0.tryAcquire();	 Catch:{ all -> 0x0041 }
        if (r0 != 0) goto L_0x0059;
    L_0x0053:
        r0 = r7.zzast;	 Catch:{ all -> 0x0041 }
        if (r2 != r0) goto L_0x0059;
    L_0x0057:
        monitor-exit(r3);	 Catch:{ all -> 0x0041 }
        goto L_0x0010;
    L_0x0059:
        if (r2 != 0) goto L_0x0063;
    L_0x005b:
        r0 = r7.zzast;	 Catch:{ all -> 0x0041 }
        if (r0 != 0) goto L_0x0063;
    L_0x005f:
        if (r8 != r1) goto L_0x0063;
    L_0x0061:
        monitor-exit(r3);	 Catch:{ all -> 0x0041 }
        goto L_0x0010;
    L_0x0063:
        r0 = r7.zzd(r4);	 Catch:{ JSONException -> 0x007b, RuntimeException -> 0x0074 }
        r7.zza(r0);	 Catch:{ JSONException -> 0x007b, RuntimeException -> 0x0074 }
        r7.zzast = r2;	 Catch:{ JSONException -> 0x007b, RuntimeException -> 0x0074 }
    L_0x006c:
        r7.zzho();	 Catch:{ all -> 0x0041 }
        r7.zzhm();	 Catch:{ all -> 0x0041 }
        monitor-exit(r3);	 Catch:{ all -> 0x0041 }
        goto L_0x0010;
    L_0x0074:
        r0 = move-exception;
    L_0x0075:
        r1 = "Active view update failed.";
        com.google.android.gms.ads.internal.util.client.zzb.zza(r1, r0);	 Catch:{ all -> 0x0041 }
        goto L_0x006c;
    L_0x007b:
        r0 = move-exception;
        goto L_0x0075;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcj.zzk(int):void");
    }
}
