package com.google.android.gms.internal;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzab;
import java.net.URI;
import java.net.URISyntaxException;

@zziy
public class zzme extends WebViewClient {
    private final zzlt zzbkr;
    private final zzii zzccz;
    private final String zzcxd;
    private boolean zzcxe = false;

    public zzme(zzii com_google_android_gms_internal_zzii, zzlt com_google_android_gms_internal_zzlt, String str) {
        this.zzcxd = zzdn(str);
        this.zzbkr = com_google_android_gms_internal_zzlt;
        this.zzccz = com_google_android_gms_internal_zzii;
    }

    private String zzdn(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (str.endsWith("/")) {
                    str = str.substring(0, str.length() - 1);
                }
            } catch (IndexOutOfBoundsException e) {
                zzb.e(e.getMessage());
            }
        }
        return str;
    }

    public void onLoadResource(WebView webView, String str) {
        String str2 = "JavascriptAdWebViewClient::onLoadResource: ";
        String valueOf = String.valueOf(str);
        zzb.zzdd(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        if (!zzdm(str)) {
            this.zzbkr.zzvr().onLoadResource(this.zzbkr.getWebView(), str);
        }
    }

    public void onPageFinished(WebView webView, String str) {
        String str2 = "JavascriptAdWebViewClient::onPageFinished: ";
        String valueOf = String.valueOf(str);
        zzb.zzdd(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        if (!this.zzcxe) {
            this.zzccz.zzqx();
            this.zzcxe = true;
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = "JavascriptAdWebViewClient::shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(str);
        zzb.zzdd(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        if (!zzdm(str)) {
            return this.zzbkr.zzvr().shouldOverrideUrlLoading(this.zzbkr.getWebView(), str);
        }
        zzb.zzdd("shouldOverrideUrlLoading: received passback url");
        return true;
    }

    protected boolean zzdm(String str) {
        Object zzdn = zzdn(str);
        if (TextUtils.isEmpty(zzdn)) {
            return false;
        }
        try {
            URI uri = new URI(zzdn);
            if ("passback".equals(uri.getScheme())) {
                zzb.zzdd("Passback received");
                this.zzccz.zzqy();
                return true;
            } else if (TextUtils.isEmpty(this.zzcxd)) {
                return false;
            } else {
                URI uri2 = new URI(this.zzcxd);
                String host = uri2.getHost();
                String host2 = uri.getHost();
                String path = uri2.getPath();
                String path2 = uri.getPath();
                if (!zzab.equal(host, host2) || !zzab.equal(path, path2)) {
                    return false;
                }
                zzb.zzdd("Passback received");
                this.zzccz.zzqy();
                return true;
            }
        } catch (URISyntaxException e) {
            zzb.e(e.getMessage());
            return false;
        }
    }
}
