package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkm;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzku;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzlu;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Future;

@zziy
public class zzd extends com.google.android.gms.internal.zzhp.zza implements zzu {
    static final int zzbxe = Color.argb(0, 0, 0, 0);
    private final Activity mActivity;
    zzlt zzbkr;
    AdOverlayInfoParcel zzbxf;
    zzc zzbxg;
    zzo zzbxh;
    boolean zzbxi = false;
    FrameLayout zzbxj;
    CustomViewCallback zzbxk;
    boolean zzbxl = false;
    boolean zzbxm = false;
    zzb zzbxn;
    boolean zzbxo = false;
    int zzbxp = 0;
    zzl zzbxq;
    private final Object zzbxr = new Object();
    private Runnable zzbxs;
    private boolean zzbxt;
    private boolean zzbxu;
    private boolean zzbxv = false;
    private boolean zzbxw = false;
    private boolean zzbxx = true;

    @zziy
    private static final class zza extends Exception {
        public zza(String str) {
            super(str);
        }
    }

    @zziy
    static class zzb extends RelativeLayout {
        zzku zzase;
        boolean zzbxz;

        public zzb(Context context, String str) {
            super(context);
            this.zzase = new zzku(context, str);
        }

        void disable() {
            this.zzbxz = true;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (!this.zzbxz) {
                this.zzase.zzg(motionEvent);
            }
            return false;
        }
    }

    @zziy
    public static class zzc {
        public final int index;
        public final Context zzahn;
        public final LayoutParams zzbya;
        public final ViewGroup zzbyb;

        public zzc(zzlt com_google_android_gms_internal_zzlt) throws zza {
            this.zzbya = com_google_android_gms_internal_zzlt.getLayoutParams();
            ViewParent parent = com_google_android_gms_internal_zzlt.getParent();
            this.zzahn = com_google_android_gms_internal_zzlt.zzvo();
            if (parent == null || !(parent instanceof ViewGroup)) {
                throw new zza("Could not get the parent of the WebView for an overlay.");
            }
            this.zzbyb = (ViewGroup) parent;
            this.index = this.zzbyb.indexOfChild(com_google_android_gms_internal_zzlt.getView());
            this.zzbyb.removeView(com_google_android_gms_internal_zzlt.getView());
            com_google_android_gms_internal_zzlt.zzaj(true);
        }
    }

    @zziy
    private class zzd extends zzkm {
        final /* synthetic */ zzd zzbxy;

        private zzd(zzd com_google_android_gms_ads_internal_overlay_zzd) {
            this.zzbxy = com_google_android_gms_ads_internal_overlay_zzd;
        }

        public void onStop() {
        }

        public void zzfc() {
            Bitmap zza = zzu.zzgu().zza(Integer.valueOf(this.zzbxy.zzbxf.zzbyq.zzaoc));
            if (zza != null) {
                final Drawable zza2 = zzu.zzgb().zza(this.zzbxy.mActivity, zza, this.zzbxy.zzbxf.zzbyq.zzaoa, this.zzbxy.zzbxf.zzbyq.zzaob);
                zzkr.zzcrf.post(new Runnable(this) {
                    final /* synthetic */ zzd zzbyd;

                    public void run() {
                        this.zzbyd.zzbxy.mActivity.getWindow().setBackgroundDrawable(zza2);
                    }
                });
            }
        }
    }

    public zzd(Activity activity) {
        this.mActivity = activity;
        this.zzbxq = new zzs();
    }

    public void close() {
        this.zzbxp = 2;
        this.mActivity.finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onBackPressed() {
        this.zzbxp = 0;
    }

    public void onCreate(Bundle bundle) {
        boolean z = false;
        this.mActivity.requestWindowFeature(1);
        if (bundle != null) {
            z = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.zzbxl = z;
        try {
            this.zzbxf = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
            if (this.zzbxf == null) {
                throw new zza("Could not get info for ad overlay.");
            }
            if (this.zzbxf.zzaqv.zzctt > 7500000) {
                this.zzbxp = 3;
            }
            if (this.mActivity.getIntent() != null) {
                this.zzbxx = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }
            if (this.zzbxf.zzbyq != null) {
                this.zzbxm = this.zzbxf.zzbyq.zzanx;
            } else {
                this.zzbxm = false;
            }
            if (((Boolean) zzdi.zzbfn.get()).booleanValue() && this.zzbxm && this.zzbxf.zzbyq.zzaoc != -1) {
                Future future = (Future) new zzd().zzqw();
            }
            if (bundle == null) {
                if (this.zzbxf.zzbyg != null && this.zzbxx) {
                    this.zzbxf.zzbyg.zzee();
                }
                if (!(this.zzbxf.zzbyn == 1 || this.zzbxf.zzbyf == null)) {
                    this.zzbxf.zzbyf.onAdClicked();
                }
            }
            this.zzbxn = new zzb(this.mActivity, this.zzbxf.zzbyp);
            this.zzbxn.setId(Constants.MAX_DOWNLOADS);
            switch (this.zzbxf.zzbyn) {
                case 1:
                    zzab(false);
                    return;
                case 2:
                    this.zzbxg = new zzc(this.zzbxf.zzbyh);
                    zzab(false);
                    return;
                case 3:
                    zzab(true);
                    return;
                case 4:
                    if (this.zzbxl) {
                        this.zzbxp = 3;
                        this.mActivity.finish();
                        return;
                    } else if (!zzu.zzfw().zza(this.mActivity, this.zzbxf.zzbye, this.zzbxf.zzbym)) {
                        this.zzbxp = 3;
                        this.mActivity.finish();
                        return;
                    } else {
                        return;
                    }
                default:
                    throw new zza("Could not determine ad overlay type.");
            }
        } catch (zza e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf(e.getMessage());
            this.zzbxp = 3;
            this.mActivity.finish();
        }
    }

    public void onDestroy() {
        if (this.zzbkr != null) {
            this.zzbxn.removeView(this.zzbkr.getView());
        }
        zzow();
    }

    public void onPause() {
        this.zzbxq.pause();
        zzos();
        if (this.zzbxf.zzbyg != null) {
            this.zzbxf.zzbyg.onPause();
        }
        if (this.zzbkr != null && (!this.mActivity.isFinishing() || this.zzbxg == null)) {
            zzu.zzgb().zzl(this.zzbkr);
        }
        zzow();
    }

    public void onRestart() {
    }

    public void onResume() {
        if (this.zzbxf != null && this.zzbxf.zzbyn == 4) {
            if (this.zzbxl) {
                this.zzbxp = 3;
                this.mActivity.finish();
            } else {
                this.zzbxl = true;
            }
        }
        if (this.zzbxf.zzbyg != null) {
            this.zzbxf.zzbyg.onResume();
        }
        if (this.zzbkr == null || this.zzbkr.isDestroyed()) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("The webview does not exit. Ignoring action.");
        } else {
            zzu.zzgb().zzm(this.zzbkr);
        }
        this.zzbxq.resume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzbxl);
    }

    public void onStart() {
    }

    public void onStop() {
        zzow();
    }

    public void setRequestedOrientation(int i) {
        this.mActivity.setRequestedOrientation(i);
    }

    public void zza(View view, CustomViewCallback customViewCallback) {
        this.zzbxj = new FrameLayout(this.mActivity);
        this.zzbxj.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.zzbxj.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzbxj);
        zzdf();
        this.zzbxk = customViewCallback;
        this.zzbxi = true;
    }

    public void zza(boolean z, boolean z2) {
        if (this.zzbxh != null) {
            this.zzbxh.zza(z, z2);
        }
    }

    public void zzaa(boolean z) {
        this.zzbxh = new zzo(this.mActivity, z ? 50 : 32, this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        this.zzbxh.zza(z, this.zzbxf.zzbyk);
        this.zzbxn.addView(this.zzbxh, layoutParams);
    }

    protected void zzab(boolean z) throws zza {
        if (!this.zzbxu) {
            this.mActivity.requestWindowFeature(1);
        }
        Window window = this.mActivity.getWindow();
        if (window == null) {
            throw new zza("Invalid activity, no window available.");
        }
        boolean zza = (zzs.zzaxw() && ((Boolean) zzdi.zzbhx.get()).booleanValue()) ? zzu.zzfz().zza(this.mActivity, this.mActivity.getResources().getConfiguration()) : true;
        Object obj = (this.zzbxf.zzbyq == null || !this.zzbxf.zzbyq.zzany) ? null : 1;
        if (!(this.zzbxm && obj == null) && zza) {
            window.setFlags(1024, 1024);
        }
        zzlu zzvr = this.zzbxf.zzbyh.zzvr();
        boolean zzib = zzvr != null ? zzvr.zzib() : false;
        this.zzbxo = false;
        if (zzib) {
            if (this.zzbxf.orientation == zzu.zzgb().zzum()) {
                this.zzbxo = this.mActivity.getResources().getConfiguration().orientation == 1;
            } else if (this.zzbxf.orientation == zzu.zzgb().zzun()) {
                this.zzbxo = this.mActivity.getResources().getConfiguration().orientation == 2;
            }
        }
        com.google.android.gms.ads.internal.util.client.zzb.zzdd("Delay onShow to next orientation change: " + this.zzbxo);
        setRequestedOrientation(this.zzbxf.orientation);
        if (zzu.zzgb().zza(window)) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Hardware acceleration on the AdActivity window enabled.");
        }
        if (this.zzbxm) {
            this.zzbxn.setBackgroundColor(zzbxe);
        } else {
            this.zzbxn.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        this.mActivity.setContentView(this.zzbxn);
        zzdf();
        if (z) {
            this.zzbkr = zzu.zzga().zza(this.mActivity, this.zzbxf.zzbyh.zzdt(), true, zzib, null, this.zzbxf.zzaqv, null, null, this.zzbxf.zzbyh.zzdp());
            this.zzbkr.zzvr().zza(null, null, this.zzbxf.zzbyi, this.zzbxf.zzbym, true, this.zzbxf.zzbyo, null, this.zzbxf.zzbyh.zzvr().zzwi(), null, null);
            this.zzbkr.zzvr().zza(new com.google.android.gms.internal.zzlu.zza(this) {
                final /* synthetic */ zzd zzbxy;

                {
                    this.zzbxy = r1;
                }

                public void zza(zzlt com_google_android_gms_internal_zzlt, boolean z) {
                    com_google_android_gms_internal_zzlt.zzoz();
                }
            });
            if (this.zzbxf.url != null) {
                this.zzbkr.loadUrl(this.zzbxf.url);
            } else if (this.zzbxf.zzbyl != null) {
                this.zzbkr.loadDataWithBaseURL(this.zzbxf.zzbyj, this.zzbxf.zzbyl, "text/html", Key.STRING_CHARSET_NAME, null);
            } else {
                throw new zza("No URL or HTML to display in ad overlay.");
            }
            if (this.zzbxf.zzbyh != null) {
                this.zzbxf.zzbyh.zzc(this);
            }
        } else {
            this.zzbkr = this.zzbxf.zzbyh;
            this.zzbkr.setContext(this.mActivity);
        }
        this.zzbkr.zzb(this);
        ViewParent parent = this.zzbkr.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.zzbkr.getView());
        }
        if (this.zzbxm) {
            this.zzbkr.zzwh();
        }
        this.zzbxn.addView(this.zzbkr.getView(), -1, -1);
        if (!(z || this.zzbxo)) {
            zzoz();
        }
        zzaa(zzib);
        if (this.zzbkr.zzvs()) {
            zza(zzib, true);
        }
        com.google.android.gms.ads.internal.zzd zzdp = this.zzbkr.zzdp();
        zzm com_google_android_gms_ads_internal_overlay_zzm = zzdp != null ? zzdp.zzame : null;
        if (com_google_android_gms_ads_internal_overlay_zzm != null) {
            this.zzbxq = com_google_android_gms_ads_internal_overlay_zzm.zza(this.mActivity, this.zzbkr, this.zzbxn);
        } else {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("Appstreaming controller is null.");
        }
    }

    protected void zzah(int i) {
        this.zzbkr.zzah(i);
    }

    public void zzdf() {
        this.zzbxu = true;
    }

    public void zzf(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        this.zzbxq.zzf(com_google_android_gms_internal_zzlt, map);
    }

    public void zzn(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) {
        if (((Boolean) zzdi.zzbhx.get()).booleanValue() && zzs.zzaxw()) {
            if (zzu.zzfz().zza(this.mActivity, (Configuration) zze.zzae(com_google_android_gms_dynamic_zzd))) {
                this.mActivity.getWindow().addFlags(1024);
                this.mActivity.getWindow().clearFlags(2048);
                return;
            }
            this.mActivity.getWindow().addFlags(2048);
            this.mActivity.getWindow().clearFlags(1024);
        }
    }

    public void zzos() {
        if (this.zzbxf != null && this.zzbxi) {
            setRequestedOrientation(this.zzbxf.orientation);
        }
        if (this.zzbxj != null) {
            this.mActivity.setContentView(this.zzbxn);
            zzdf();
            this.zzbxj.removeAllViews();
            this.zzbxj = null;
        }
        if (this.zzbxk != null) {
            this.zzbxk.onCustomViewHidden();
            this.zzbxk = null;
        }
        this.zzbxi = false;
    }

    public void zzot() {
        this.zzbxp = 1;
        this.mActivity.finish();
    }

    public boolean zzou() {
        boolean z = true;
        this.zzbxp = 0;
        if (this.zzbkr != null) {
            if (!(this.zzbkr.zzpr() && this.zzbxq.zzpr())) {
                z = false;
            }
            if (!z) {
                this.zzbkr.zza("onbackblocked", Collections.emptyMap());
            }
        }
        return z;
    }

    public void zzov() {
        this.zzbxn.removeView(this.zzbxh);
        zzaa(true);
    }

    protected void zzow() {
        if (this.mActivity.isFinishing() && !this.zzbxv) {
            this.zzbxv = true;
            if (this.zzbkr != null) {
                zzah(this.zzbxp);
                synchronized (this.zzbxr) {
                    if (!this.zzbxt && this.zzbkr.zzwc()) {
                        this.zzbxs = new Runnable(this) {
                            final /* synthetic */ zzd zzbxy;

                            {
                                this.zzbxy = r1;
                            }

                            public void run() {
                                this.zzbxy.zzox();
                            }
                        };
                        zzkr.zzcrf.postDelayed(this.zzbxs, ((Long) zzdi.zzbdh.get()).longValue());
                        return;
                    }
                }
            }
            zzox();
        }
    }

    void zzox() {
        if (!this.zzbxw) {
            this.zzbxw = true;
            if (this.zzbkr != null) {
                this.zzbxn.removeView(this.zzbkr.getView());
                if (this.zzbxg != null) {
                    this.zzbkr.setContext(this.zzbxg.zzahn);
                    this.zzbkr.zzaj(false);
                    this.zzbxg.zzbyb.addView(this.zzbkr.getView(), this.zzbxg.index, this.zzbxg.zzbya);
                    this.zzbxg = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzbkr.setContext(this.mActivity.getApplicationContext());
                }
                this.zzbkr = null;
            }
            if (!(this.zzbxf == null || this.zzbxf.zzbyg == null)) {
                this.zzbxf.zzbyg.zzed();
            }
            this.zzbxq.destroy();
        }
    }

    public void zzoy() {
        if (this.zzbxo) {
            this.zzbxo = false;
            zzoz();
        }
    }

    protected void zzoz() {
        this.zzbkr.zzoz();
    }

    public void zzpa() {
        this.zzbxn.disable();
    }

    public void zzpb() {
        synchronized (this.zzbxr) {
            this.zzbxt = true;
            if (this.zzbxs != null) {
                zzkr.zzcrf.removeCallbacks(this.zzbxs);
                zzkr.zzcrf.post(this.zzbxs);
            }
        }
    }
}
