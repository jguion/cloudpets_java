package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.view.View;
import android.webkit.WebChromeClient.CustomViewCallback;

@zziy
@TargetApi(14)
public final class zzmc extends zzma {
    public zzmc(zzlt com_google_android_gms_internal_zzlt) {
        super(com_google_android_gms_internal_zzlt);
    }

    public void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
        zza(view, i, customViewCallback);
    }
}
