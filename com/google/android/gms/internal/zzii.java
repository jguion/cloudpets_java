package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import android.webkit.WebView;
import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;

@zziy
public class zzii implements Runnable {
    private final int zzajw;
    private final int zzajx;
    protected final zzlt zzbkr;
    private final Handler zzccq;
    private final long zzccr;
    private long zzccs;
    private com.google.android.gms.internal.zzlu.zza zzcct;
    protected boolean zzccu;
    protected boolean zzccv;

    protected final class zza extends AsyncTask<Void, Void, Boolean> {
        private final WebView zzccw;
        private Bitmap zzccx;
        final /* synthetic */ zzii zzccy;

        public zza(zzii com_google_android_gms_internal_zzii, WebView webView) {
            this.zzccy = com_google_android_gms_internal_zzii;
            this.zzccw = webView;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return zza((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            zza((Boolean) obj);
        }

        protected synchronized void onPreExecute() {
            this.zzccx = Bitmap.createBitmap(this.zzccy.zzajw, this.zzccy.zzajx, Config.ARGB_8888);
            this.zzccw.setVisibility(0);
            this.zzccw.measure(MeasureSpec.makeMeasureSpec(this.zzccy.zzajw, 0), MeasureSpec.makeMeasureSpec(this.zzccy.zzajx, 0));
            this.zzccw.layout(0, 0, this.zzccy.zzajw, this.zzccy.zzajx);
            this.zzccw.draw(new Canvas(this.zzccx));
            this.zzccw.invalidate();
        }

        protected synchronized Boolean zza(Void... voidArr) {
            Boolean valueOf;
            int width = this.zzccx.getWidth();
            int height = this.zzccx.getHeight();
            if (width == 0 || height == 0) {
                valueOf = Boolean.valueOf(false);
            } else {
                int i = 0;
                for (int i2 = 0; i2 < width; i2 += 10) {
                    for (int i3 = 0; i3 < height; i3 += 10) {
                        if (this.zzccx.getPixel(i2, i3) != 0) {
                            i++;
                        }
                    }
                }
                valueOf = Boolean.valueOf(((double) i) / (((double) (width * height)) / 100.0d) > 0.1d);
            }
            return valueOf;
        }

        protected void zza(Boolean bool) {
            zzii.zzc(this.zzccy);
            if (bool.booleanValue() || this.zzccy.zzqz() || this.zzccy.zzccs <= 0) {
                this.zzccy.zzccv = bool.booleanValue();
                this.zzccy.zzcct.zza(this.zzccy.zzbkr, true);
            } else if (this.zzccy.zzccs > 0) {
                if (zzb.zzbf(2)) {
                    zzb.zzdd("Ad not detected, scheduling another run.");
                }
                this.zzccy.zzccq.postDelayed(this.zzccy, this.zzccy.zzccr);
            }
        }
    }

    public zzii(com.google.android.gms.internal.zzlu.zza com_google_android_gms_internal_zzlu_zza, zzlt com_google_android_gms_internal_zzlt, int i, int i2) {
        this(com_google_android_gms_internal_zzlu_zza, com_google_android_gms_internal_zzlt, i, i2, 200, 50);
    }

    public zzii(com.google.android.gms.internal.zzlu.zza com_google_android_gms_internal_zzlu_zza, zzlt com_google_android_gms_internal_zzlt, int i, int i2, long j, long j2) {
        this.zzccr = j;
        this.zzccs = j2;
        this.zzccq = new Handler(Looper.getMainLooper());
        this.zzbkr = com_google_android_gms_internal_zzlt;
        this.zzcct = com_google_android_gms_internal_zzlu_zza;
        this.zzccu = false;
        this.zzccv = false;
        this.zzajx = i2;
        this.zzajw = i;
    }

    static /* synthetic */ long zzc(zzii com_google_android_gms_internal_zzii) {
        long j = com_google_android_gms_internal_zzii.zzccs - 1;
        com_google_android_gms_internal_zzii.zzccs = j;
        return j;
    }

    public void run() {
        if (this.zzbkr == null || zzqz()) {
            this.zzcct.zza(this.zzbkr, true);
        } else {
            new zza(this, this.zzbkr.getWebView()).execute(new Void[0]);
        }
    }

    public void zza(AdResponseParcel adResponseParcel) {
        zza(adResponseParcel, new zzme(this, this.zzbkr, adResponseParcel.zzchj));
    }

    public void zza(AdResponseParcel adResponseParcel, zzme com_google_android_gms_internal_zzme) {
        this.zzbkr.setWebViewClient(com_google_android_gms_internal_zzme);
        this.zzbkr.loadDataWithBaseURL(TextUtils.isEmpty(adResponseParcel.zzbyj) ? null : zzu.zzfz().zzcv(adResponseParcel.zzbyj), adResponseParcel.body, "text/html", Key.STRING_CHARSET_NAME, null);
    }

    public void zzqx() {
        this.zzccq.postDelayed(this, this.zzccr);
    }

    public synchronized void zzqy() {
        this.zzccu = true;
    }

    public synchronized boolean zzqz() {
        return this.zzccu;
    }

    public boolean zzra() {
        return this.zzccv;
    }
}
