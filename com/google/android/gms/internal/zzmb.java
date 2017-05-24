package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

@zziy
@TargetApi(11)
public class zzmb extends zzmd {
    public zzmb(zzlt com_google_android_gms_internal_zzlt, boolean z) {
        super(com_google_android_gms_internal_zzlt, z);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zza(webView, str, null);
    }
}
