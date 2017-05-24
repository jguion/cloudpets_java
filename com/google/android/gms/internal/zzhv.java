package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zziy
public class zzhv implements zzht {
    private final Context mContext;
    final Set<WebView> zzcba = Collections.synchronizedSet(new HashSet());

    public zzhv(Context context) {
        this.mContext = context;
    }

    public void zza(String str, final String str2, final String str3) {
        zzb.zzdd("Fetching assets for the given html");
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzhv zzcbd;

            public void run() {
                final WebView zzqj = this.zzcbd.zzqj();
                zzqj.setWebViewClient(new WebViewClient(this) {
                    final /* synthetic */ AnonymousClass1 zzcbe;

                    public void onPageFinished(WebView webView, String str) {
                        zzb.zzdd("Loading assets have finished");
                        this.zzcbe.zzcbd.zzcba.remove(zzqj);
                    }

                    public void onReceivedError(WebView webView, int i, String str, String str2) {
                        zzb.zzdf("Loading assets have failed.");
                        this.zzcbe.zzcbd.zzcba.remove(zzqj);
                    }
                });
                this.zzcbd.zzcba.add(zzqj);
                zzqj.loadDataWithBaseURL(str2, str3, "text/html", Key.STRING_CHARSET_NAME, null);
                zzb.zzdd("Fetching assets finished.");
            }
        });
    }

    public WebView zzqj() {
        WebView webView = new WebView(this.mContext);
        webView.getSettings().setJavaScriptEnabled(true);
        return webView;
    }
}
