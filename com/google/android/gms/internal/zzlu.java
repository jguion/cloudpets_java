package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.TransportMediator;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.cache.CacheEntryParcel;
import com.google.android.gms.ads.internal.cache.CacheOffering;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.zzu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@zziy
public class zzlu extends WebViewClient {
    private static final String[] zzcus = new String[]{"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzcut = new String[]{"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    private final Object zzakd;
    private boolean zzatj;
    private com.google.android.gms.ads.internal.client.zza zzawb;
    protected zzlt zzbkr;
    private zzer zzbma;
    private zzez zzbnj;
    private com.google.android.gms.ads.internal.zze zzbnl;
    private zzhh zzbnm;
    private zzex zzbno;
    private zzhn zzbve;
    private zza zzcct;
    private final HashMap<String, List<zzev>> zzcuu;
    private zzg zzcuv;
    private zzb zzcuw;
    private zzc zzcux;
    private boolean zzcuy;
    private boolean zzcuz;
    private OnGlobalLayoutListener zzcva;
    private OnScrollChangedListener zzcvb;
    private boolean zzcvc;
    private zzp zzcvd;
    private final zzhl zzcve;
    private zze zzcvf;
    @Nullable
    protected com.google.android.gms.ads.internal.safebrowsing.zzc zzcvg;
    private boolean zzcvh;
    private boolean zzcvi;
    private boolean zzcvj;
    private int zzcvk;

    public interface zza {
        void zza(zzlt com_google_android_gms_internal_zzlt, boolean z);
    }

    public interface zze {
        void zzes();
    }

    public interface zzc {
        void zzet();
    }

    public interface zzb {
        void zzk(zzlt com_google_android_gms_internal_zzlt);
    }

    private static class zzd implements zzg {
        private zzg zzcuv;
        private zzlt zzcvm;

        public zzd(zzlt com_google_android_gms_internal_zzlt, zzg com_google_android_gms_ads_internal_overlay_zzg) {
            this.zzcvm = com_google_android_gms_internal_zzlt;
            this.zzcuv = com_google_android_gms_ads_internal_overlay_zzg;
        }

        public void onPause() {
        }

        public void onResume() {
        }

        public void zzed() {
            this.zzcuv.zzed();
            this.zzcvm.zzvl();
        }

        public void zzee() {
            this.zzcuv.zzee();
            this.zzcvm.zzoz();
        }
    }

    public zzlu(zzlt com_google_android_gms_internal_zzlt, boolean z) {
        this(com_google_android_gms_internal_zzlt, z, new zzhl(com_google_android_gms_internal_zzlt, com_google_android_gms_internal_zzlt.zzvo(), new zzda(com_google_android_gms_internal_zzlt.getContext())), null);
    }

    zzlu(zzlt com_google_android_gms_internal_zzlt, boolean z, zzhl com_google_android_gms_internal_zzhl, zzhh com_google_android_gms_internal_zzhh) {
        this.zzcuu = new HashMap();
        this.zzakd = new Object();
        this.zzcuy = false;
        this.zzbkr = com_google_android_gms_internal_zzlt;
        this.zzatj = z;
        this.zzcve = com_google_android_gms_internal_zzhl;
        this.zzbnm = com_google_android_gms_internal_zzhh;
    }

    private void zzb(Context context, String str, String str2, String str3) {
        if (((Boolean) zzdi.zzbdz.get()).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString("err", str);
            bundle.putString("code", str2);
            bundle.putString("host", zzdi(str3));
            zzu.zzfz().zza(context, this.zzbkr.zzvu().zzcs, "gmob-apps", bundle, true);
        }
    }

    private String zzdi(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Uri parse = Uri.parse(str);
        return parse.getHost() != null ? parse.getHost() : "";
    }

    private static boolean zzi(Uri uri) {
        String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }

    private void zzws() {
        if (this.zzcuw != null) {
            this.zzcuw.zzk(this.zzbkr);
            this.zzcuw = null;
        }
    }

    public final void onLoadResource(WebView webView, String str) {
        String str2 = "Loading resource: ";
        String valueOf = String.valueOf(str);
        zzkn.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzj(parse);
        }
    }

    public final void onPageFinished(WebView webView, String str) {
        synchronized (this.zzakd) {
            if (this.zzcvh) {
                zzkn.v("Blank page loaded, 1...");
                this.zzbkr.zzvw();
                return;
            }
            this.zzcvi = true;
            zzws();
            zzwt();
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        String valueOf = (i >= 0 || (-i) - 1 >= zzcus.length) ? String.valueOf(i) : zzcus[(-i) - 1];
        zzb(this.zzbkr.getContext(), "http_err", valueOf, str2);
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            String valueOf = (primaryError < 0 || primaryError >= zzcut.length) ? String.valueOf(primaryError) : zzcut[primaryError];
            zzb(this.zzbkr.getContext(), "ssl_err", valueOf, zzu.zzgb().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public final void reset() {
        if (this.zzcvg != null) {
            this.zzcvg.zzsy();
            this.zzcvg = null;
        }
        synchronized (this.zzakd) {
            this.zzcuu.clear();
            this.zzawb = null;
            this.zzcuv = null;
            this.zzcct = null;
            this.zzcuw = null;
            this.zzbma = null;
            this.zzcuy = false;
            this.zzatj = false;
            this.zzcuz = false;
            this.zzcvc = false;
            this.zzbno = null;
            this.zzcvd = null;
            this.zzcux = null;
            if (this.zzbnm != null) {
                this.zzbnm.zzt(true);
                this.zzbnm = null;
            }
        }
    }

    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        try {
            CacheOffering zzag = CacheOffering.zzag(str);
            if (zzag == null) {
                return null;
            }
            CacheEntryParcel zza = zzu.zzge().zza(zzag);
            return (zza == null || !zza.zziu()) ? null : new WebResourceResponse("", "", zza.zziv());
        } catch (Throwable th) {
            return null;
        }
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case TransportMediator.KEYCODE_MEDIA_PLAY /*126*/:
            case TransportMediator.KEYCODE_MEDIA_PAUSE /*127*/:
            case 128:
            case 129:
            case TransportMediator.KEYCODE_MEDIA_RECORD /*130*/:
            case 222:
                return true;
            default:
                return false;
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = "AdWebView shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(str);
        zzkn.v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzj(parse);
        } else if (this.zzcuy && webView == this.zzbkr.getWebView() && zzi(parse)) {
            if (this.zzawb != null && ((Boolean) zzdi.zzbcq.get()).booleanValue()) {
                this.zzawb.onAdClicked();
                if (this.zzcvg != null) {
                    this.zzcvg.zzcq(str);
                }
                this.zzawb = null;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        } else if (this.zzbkr.getWebView().willNotDraw()) {
            str2 = "AdWebView unable to handle URL: ";
            valueOf = String.valueOf(str);
            com.google.android.gms.ads.internal.util.client.zzb.zzdf(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else {
            Uri uri;
            try {
                zzau zzvt = this.zzbkr.zzvt();
                if (zzvt != null && zzvt.zzc(parse)) {
                    parse = zzvt.zza(parse, this.zzbkr.getContext(), this.zzbkr.getView());
                }
                uri = parse;
            } catch (zzav e) {
                String str3 = "Unable to append parameter to URL: ";
                str2 = String.valueOf(str);
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                uri = parse;
            }
            if (this.zzbnl == null || this.zzbnl.zzer()) {
                zza(new AdLauncherIntentInfoParcel("android.intent.action.VIEW", uri.toString(), null, null, null, null, null));
            } else {
                this.zzbnl.zzv(str);
            }
        }
        return true;
    }

    public void zza(int i, int i2, boolean z) {
        this.zzcve.zze(i, i2);
        if (this.zzbnm != null) {
            this.zzbnm.zza(i, i2, z);
        }
    }

    public final void zza(OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        synchronized (this.zzakd) {
            this.zzcuz = true;
            this.zzbkr.zzwd();
            this.zzcva = onGlobalLayoutListener;
            this.zzcvb = onScrollChangedListener;
        }
    }

    public void zza(com.google.android.gms.ads.internal.client.zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzer com_google_android_gms_internal_zzer, zzp com_google_android_gms_ads_internal_overlay_zzp, boolean z, zzex com_google_android_gms_internal_zzex, @Nullable zzez com_google_android_gms_internal_zzez, com.google.android.gms.ads.internal.zze com_google_android_gms_ads_internal_zze, zzhn com_google_android_gms_internal_zzhn, @Nullable com.google.android.gms.ads.internal.safebrowsing.zzc com_google_android_gms_ads_internal_safebrowsing_zzc) {
        if (com_google_android_gms_ads_internal_zze == null) {
            com_google_android_gms_ads_internal_zze = new com.google.android.gms.ads.internal.zze(this.zzbkr.getContext());
        }
        this.zzbnm = new zzhh(this.zzbkr, com_google_android_gms_internal_zzhn);
        this.zzcvg = com_google_android_gms_ads_internal_safebrowsing_zzc;
        zza("/appEvent", new zzeq(com_google_android_gms_internal_zzer));
        zza("/backButton", zzeu.zzbmm);
        zza("/refresh", zzeu.zzbmn);
        zza("/canOpenURLs", zzeu.zzbmc);
        zza("/canOpenIntents", zzeu.zzbmd);
        zza("/click", zzeu.zzbme);
        zza("/close", zzeu.zzbmf);
        zza("/customClose", zzeu.zzbmh);
        zza("/instrument", zzeu.zzbmr);
        zza("/delayPageLoaded", zzeu.zzbmt);
        zza("/delayPageClosed", zzeu.zzbmu);
        zza("/getLocationInfo", zzeu.zzbmv);
        zza("/httpTrack", zzeu.zzbmi);
        zza("/log", zzeu.zzbmj);
        zza("/mraid", new zzfb(com_google_android_gms_ads_internal_zze, this.zzbnm));
        zza("/mraidLoaded", this.zzcve);
        zza("/open", new zzfc(com_google_android_gms_internal_zzex, com_google_android_gms_ads_internal_zze, this.zzbnm));
        zza("/precache", zzeu.zzbmq);
        zza("/touch", zzeu.zzbml);
        zza("/video", zzeu.zzbmo);
        zza("/videoMeta", zzeu.zzbmp);
        zza("/appStreaming", zzeu.zzbmg);
        if (com_google_android_gms_internal_zzez != null) {
            zza("/setInterstitialProperties", new zzey(com_google_android_gms_internal_zzez));
        }
        this.zzawb = com_google_android_gms_ads_internal_client_zza;
        this.zzcuv = com_google_android_gms_ads_internal_overlay_zzg;
        this.zzbma = com_google_android_gms_internal_zzer;
        this.zzbno = com_google_android_gms_internal_zzex;
        this.zzcvd = com_google_android_gms_ads_internal_overlay_zzp;
        this.zzbnl = com_google_android_gms_ads_internal_zze;
        this.zzbve = com_google_android_gms_internal_zzhn;
        this.zzbnj = com_google_android_gms_internal_zzez;
        zzan(z);
    }

    public final void zza(AdLauncherIntentInfoParcel adLauncherIntentInfoParcel) {
        zzg com_google_android_gms_ads_internal_overlay_zzg = null;
        boolean zzvv = this.zzbkr.zzvv();
        com.google.android.gms.ads.internal.client.zza com_google_android_gms_ads_internal_client_zza = (!zzvv || this.zzbkr.zzdt().zzaxj) ? this.zzawb : null;
        if (!zzvv) {
            com_google_android_gms_ads_internal_overlay_zzg = this.zzcuv;
        }
        zza(new AdOverlayInfoParcel(adLauncherIntentInfoParcel, com_google_android_gms_ads_internal_client_zza, com_google_android_gms_ads_internal_overlay_zzg, this.zzcvd, this.zzbkr.zzvu()));
    }

    public void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean z = false;
        boolean zznu = this.zzbnm != null ? this.zzbnm.zznu() : false;
        com.google.android.gms.ads.internal.overlay.zze zzfx = zzu.zzfx();
        Context context = this.zzbkr.getContext();
        if (!zznu) {
            z = true;
        }
        zzfx.zza(context, adOverlayInfoParcel, z);
        if (this.zzcvg != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzbye != null) {
                str = adOverlayInfoParcel.zzbye.url;
            }
            this.zzcvg.zzcq(str);
        }
    }

    public void zza(zza com_google_android_gms_internal_zzlu_zza) {
        this.zzcct = com_google_android_gms_internal_zzlu_zza;
    }

    public void zza(zzb com_google_android_gms_internal_zzlu_zzb) {
        this.zzcuw = com_google_android_gms_internal_zzlu_zzb;
    }

    public void zza(zzc com_google_android_gms_internal_zzlu_zzc) {
        this.zzcux = com_google_android_gms_internal_zzlu_zzc;
    }

    public void zza(zze com_google_android_gms_internal_zzlu_zze) {
        this.zzcvf = com_google_android_gms_internal_zzlu_zze;
    }

    public void zza(String str, zzev com_google_android_gms_internal_zzev) {
        synchronized (this.zzakd) {
            List list = (List) this.zzcuu.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zzcuu.put(str, list);
            }
            list.add(com_google_android_gms_internal_zzev);
        }
    }

    public final void zza(boolean z, int i) {
        com.google.android.gms.ads.internal.client.zza com_google_android_gms_ads_internal_client_zza = (!this.zzbkr.zzvv() || this.zzbkr.zzdt().zzaxj) ? this.zzawb : null;
        zza(new AdOverlayInfoParcel(com_google_android_gms_ads_internal_client_zza, this.zzcuv, this.zzcvd, this.zzbkr, z, i, this.zzbkr.zzvu()));
    }

    public final void zza(boolean z, int i, String str) {
        zzg com_google_android_gms_ads_internal_overlay_zzg = null;
        boolean zzvv = this.zzbkr.zzvv();
        com.google.android.gms.ads.internal.client.zza com_google_android_gms_ads_internal_client_zza = (!zzvv || this.zzbkr.zzdt().zzaxj) ? this.zzawb : null;
        if (!zzvv) {
            com_google_android_gms_ads_internal_overlay_zzg = new zzd(this.zzbkr, this.zzcuv);
        }
        zza(new AdOverlayInfoParcel(com_google_android_gms_ads_internal_client_zza, com_google_android_gms_ads_internal_overlay_zzg, this.zzbma, this.zzcvd, this.zzbkr, z, i, str, this.zzbkr.zzvu(), this.zzbno));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zzvv = this.zzbkr.zzvv();
        com.google.android.gms.ads.internal.client.zza com_google_android_gms_ads_internal_client_zza = (!zzvv || this.zzbkr.zzdt().zzaxj) ? this.zzawb : null;
        zza(new AdOverlayInfoParcel(com_google_android_gms_ads_internal_client_zza, zzvv ? null : new zzd(this.zzbkr, this.zzcuv), this.zzbma, this.zzcvd, this.zzbkr, z, i, str, str2, this.zzbkr.zzvu(), this.zzbno));
    }

    public void zzan(boolean z) {
        this.zzcuy = z;
    }

    public void zzb(String str, zzev com_google_android_gms_internal_zzev) {
        synchronized (this.zzakd) {
            List list = (List) this.zzcuu.get(str);
            if (list == null) {
                return;
            }
            list.remove(com_google_android_gms_internal_zzev);
        }
    }

    public void zzd(int i, int i2) {
        if (this.zzbnm != null) {
            this.zzbnm.zzd(i, i2);
        }
    }

    public boolean zzib() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzatj;
        }
        return z;
    }

    public void zzj(Uri uri) {
        String path = uri.getPath();
        List<zzev> list = (List) this.zzcuu.get(path);
        if (list != null) {
            Map zzg = zzu.zzfz().zzg(uri);
            if (com.google.android.gms.ads.internal.util.client.zzb.zzbf(2)) {
                String str = "Received GMSG: ";
                path = String.valueOf(path);
                zzkn.v(path.length() != 0 ? str.concat(path) : new String(str));
                for (String path2 : zzg.keySet()) {
                    str = (String) zzg.get(path2);
                    zzkn.v(new StringBuilder((String.valueOf(path2).length() + 4) + String.valueOf(str).length()).append("  ").append(path2).append(": ").append(str).toString());
                }
            }
            for (zzev zza : list) {
                zza.zza(this.zzbkr, zzg);
            }
            return;
        }
        String valueOf = String.valueOf(uri);
        zzkn.v(new StringBuilder(String.valueOf(valueOf).length() + 32).append("No GMSG handler found for GMSG: ").append(valueOf).toString());
    }

    public void zzo(zzlt com_google_android_gms_internal_zzlt) {
        this.zzbkr = com_google_android_gms_internal_zzlt;
    }

    public final void zzov() {
        synchronized (this.zzakd) {
            this.zzcuy = false;
            this.zzatj = true;
            zzu.zzfz().runOnUiThread(new Runnable(this) {
                final /* synthetic */ zzlu zzcvl;

                {
                    this.zzcvl = r1;
                }

                public void run() {
                    this.zzcvl.zzbkr.zzwd();
                    com.google.android.gms.ads.internal.overlay.zzd zzvp = this.zzcvl.zzbkr.zzvp();
                    if (zzvp != null) {
                        zzvp.zzov();
                    }
                    if (this.zzcvl.zzcux != null) {
                        this.zzcvl.zzcux.zzet();
                        this.zzcvl.zzcux = null;
                    }
                }
            });
        }
    }

    public com.google.android.gms.ads.internal.zze zzwi() {
        return this.zzbnl;
    }

    public boolean zzwj() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcuz;
        }
        return z;
    }

    public OnGlobalLayoutListener zzwk() {
        OnGlobalLayoutListener onGlobalLayoutListener;
        synchronized (this.zzakd) {
            onGlobalLayoutListener = this.zzcva;
        }
        return onGlobalLayoutListener;
    }

    public OnScrollChangedListener zzwl() {
        OnScrollChangedListener onScrollChangedListener;
        synchronized (this.zzakd) {
            onScrollChangedListener = this.zzcvb;
        }
        return onScrollChangedListener;
    }

    public boolean zzwm() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcvc;
        }
        return z;
    }

    public void zzwn() {
        synchronized (this.zzakd) {
            zzkn.v("Loading blank page in WebView, 2...");
            this.zzcvh = true;
            this.zzbkr.zzdg("about:blank");
        }
    }

    public void zzwo() {
        if (this.zzcvg != null) {
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzlu zzcvl;

                {
                    this.zzcvl = r1;
                }

                public void run() {
                    if (this.zzcvl.zzcvg != null) {
                        this.zzcvl.zzcvg.zzj(this.zzcvl.zzbkr.getView());
                    }
                }
            });
        }
    }

    public void zzwp() {
        synchronized (this.zzakd) {
            this.zzcvc = true;
        }
        this.zzcvk++;
        zzwt();
    }

    public void zzwq() {
        this.zzcvk--;
        zzwt();
    }

    public void zzwr() {
        this.zzcvj = true;
        zzwt();
    }

    public final void zzwt() {
        if (this.zzcct != null && ((this.zzcvi && this.zzcvk <= 0) || this.zzcvj)) {
            this.zzcct.zza(this.zzbkr, !this.zzcvj);
            this.zzcct = null;
        }
        this.zzbkr.zzwe();
    }

    public zze zzwu() {
        return this.zzcvf;
    }
}
