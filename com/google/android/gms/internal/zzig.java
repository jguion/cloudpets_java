package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.internal.zzib.zza;

@zziy
public final class zzig extends zza {
    private final PlayStorePurchaseListener zzaza;

    public zzig(PlayStorePurchaseListener playStorePurchaseListener) {
        this.zzaza = playStorePurchaseListener;
    }

    public boolean isValidPurchase(String str) {
        return this.zzaza.isValidPurchase(str);
    }

    public void zza(zzia com_google_android_gms_internal_zzia) {
        this.zzaza.onInAppPurchaseFinished(new zzie(com_google_android_gms_internal_zzia));
    }
}
