package com.google.android.gms.internal;

import android.content.Intent;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;

@zziy
public class zzie implements InAppPurchaseResult {
    private final zzia zzcci;

    public zzie(zzia com_google_android_gms_internal_zzia) {
        this.zzcci = com_google_android_gms_internal_zzia;
    }

    public void finishPurchase() {
        try {
            this.zzcci.finishPurchase();
        } catch (Throwable e) {
            zzb.zzd("Could not forward finishPurchase to InAppPurchaseResult", e);
        }
    }

    public String getProductId() {
        try {
            return this.zzcci.getProductId();
        } catch (Throwable e) {
            zzb.zzd("Could not forward getProductId to InAppPurchaseResult", e);
            return null;
        }
    }

    public Intent getPurchaseData() {
        try {
            return this.zzcci.getPurchaseData();
        } catch (Throwable e) {
            zzb.zzd("Could not forward getPurchaseData to InAppPurchaseResult", e);
            return null;
        }
    }

    public int getResultCode() {
        try {
            return this.zzcci.getResultCode();
        } catch (Throwable e) {
            zzb.zzd("Could not forward getPurchaseData to InAppPurchaseResult", e);
            return 0;
        }
    }

    public boolean isVerified() {
        try {
            return this.zzcci.isVerified();
        } catch (Throwable e) {
            zzb.zzd("Could not forward isVerified to InAppPurchaseResult", e);
            return false;
        }
    }
}
