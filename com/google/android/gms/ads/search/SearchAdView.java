package com.google.android.gms.ads.search;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.client.zzae;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zziy;

@zziy
public final class SearchAdView extends ViewGroup {
    private final zzae zzajz;

    public SearchAdView(Context context) {
        super(context);
        this.zzajz = new zzae(this);
    }

    public SearchAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzajz = new zzae(this, attributeSet, false);
    }

    public SearchAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzajz = new zzae(this, attributeSet, false);
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

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(DynamicHeightSearchAdRequest dynamicHeightSearchAdRequest) {
        if (AdSize.SEARCH.equals(getAdSize())) {
            this.zzajz.zza(dynamicHeightSearchAdRequest.zzdg());
            return;
        }
        throw new IllegalStateException("You must use AdSize.SEARCH for a DynamicHeightSearchAdRequest");
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(SearchAdRequest searchAdRequest) {
        this.zzajz.zza(searchAdRequest.zzdg());
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
    }

    public void setAdSize(AdSize adSize) {
        this.zzajz.setAdSizes(adSize);
    }

    public void setAdUnitId(String str) {
        this.zzajz.setAdUnitId(str);
    }
}
