package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

@zziy
public class zzdy extends Image {
    private final Drawable mDrawable;
    private final Uri mUri;
    private final double zzbjp;
    private final zzdx zzblp;

    public zzdy(zzdx com_google_android_gms_internal_zzdx) {
        Drawable drawable;
        double d;
        Uri uri = null;
        this.zzblp = com_google_android_gms_internal_zzdx;
        try {
            zzd zzln = this.zzblp.zzln();
            if (zzln != null) {
                drawable = (Drawable) zze.zzae(zzln);
                this.mDrawable = drawable;
                uri = this.zzblp.getUri();
                this.mUri = uri;
                d = 1.0d;
                d = this.zzblp.getScale();
                this.zzbjp = d;
            }
        } catch (Throwable e) {
            zzb.zzb("Failed to get drawable.", e);
        }
        Object obj = uri;
        this.mDrawable = drawable;
        try {
            uri = this.zzblp.getUri();
        } catch (Throwable e2) {
            zzb.zzb("Failed to get uri.", e2);
        }
        this.mUri = uri;
        d = 1.0d;
        try {
            d = this.zzblp.getScale();
        } catch (Throwable e3) {
            zzb.zzb("Failed to get scale.", e3);
        }
        this.zzbjp = d;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public double getScale() {
        return this.zzbjp;
    }

    public Uri getUri() {
        return this.mUri;
    }
}
