package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.internal.zzhx.zza;

@zziy
public final class zzic extends zza {
    private final InAppPurchaseListener zzayy;

    public zzic(InAppPurchaseListener inAppPurchaseListener) {
        this.zzayy = inAppPurchaseListener;
    }

    public void zza(zzhw com_google_android_gms_internal_zzhw) {
        this.zzayy.onInAppPurchaseRequested(new zzif(com_google_android_gms_internal_zzhw));
    }
}
