package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.client.zzae;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;

class BaseAdView extends ViewGroup {
    protected final zzae zzajz;

    public BaseAdView(Context context, int i) {
        super(context);
        this.zzajz = new zzae(this, zzg(i));
    }

    public BaseAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.zzajz = new zzae(this, attributeSet, false, zzg(i));
    }

    public BaseAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.zzajz = new zzae(this, attributeSet, false, zzg(i2));
    }

    private static boolean zzg(int i) {
        return i == 2;
    }

    public void destroy() {
        this.zzajz.destroy();
    }

    public AdListener getAdListener() {
        return this.zzajz.getAdListener();
    }

    public AdSize getAdSize() {
        return this.zzajz.getAdSize();
    }

    public String getAdUnitId() {
        return this.zzajz.getAdUnitId();
    }

    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.zzajz.getInAppPurchaseListener();
    }

    public String getMediationAdapterClassName() {
        return this.zzajz.getMediationAdapterClassName();
    }

    public boolean isLoading() {
        return this.zzajz.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(AdRequest adRequest) {
        this.zzajz.zza(adRequest.zzdg());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = ((i3 - i) - measuredWidth) / 2;
            int i6 = ((i4 - i2) - measuredHeight) / 2;
            childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
        }
    }

    protected void onMeasure(int i, int i2) {
        int widthInPixels;
        int i3 = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            AdSize adSize;
            AdSize adSize2 = null;
            try {
                adSize = getAdSize();
            } catch (Throwable e) {
                zzb.zzb("Unable to retrieve ad size.", e);
                adSize = adSize2;
            }
            if (adSize != null) {
                Context context = getContext();
                widthInPixels = adSize.getWidthInPixels(context);
                i3 = adSize.getHeightInPixels(context);
            } else {
                widthInPixels = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            widthInPixels = childAt.getMeasuredWidth();
            i3 = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(widthInPixels, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
    }

    public void pause() {
        this.zzajz.pause();
    }

    public void resume() {
        this.zzajz.resume();
    }

    public void setAdListener(AdListener adListener) {
        this.zzajz.setAdListener(adListener);
        if (adListener != null && (adListener instanceof zza)) {
            this.zzajz.zza((zza) adListener);
        } else if (adListener == null) {
            this.zzajz.zza(null);
        }
    }

    public void setAdSize(AdSize adSize) {
        this.zzajz.setAdSizes(adSize);
    }

    public void setAdUnitId(String str) {
        this.zzajz.setAdUnitId(str);
    }

    public void setInAppPurchaseListener(InAppPurchaseListener inAppPurchaseListener) {
        this.zzajz.setInAppPurchaseListener(inAppPurchaseListener);
    }

    public void setPlayStorePurchaseParams(PlayStorePurchaseListener playStorePurchaseListener, String str) {
        this.zzajz.setPlayStorePurchaseParams(playStorePurchaseListener, str);
    }
}
