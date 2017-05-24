package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdx.zza;
import java.util.ArrayList;
import java.util.List;

@zziy
public class zzec extends NativeAppInstallAd {
    private VideoController zzayu = new VideoController();
    private final zzeb zzblq;
    private final List<Image> zzblr = new ArrayList();
    private final zzdy zzbls;

    public zzec(zzeb com_google_android_gms_internal_zzeb) {
        zzdy com_google_android_gms_internal_zzdy;
        this.zzblq = com_google_android_gms_internal_zzeb;
        try {
            List<Object> images = this.zzblq.getImages();
            if (images != null) {
                for (Object zze : images) {
                    zzdx zze2 = zze(zze);
                    if (zze2 != null) {
                        this.zzblr.add(new zzdy(zze2));
                    }
                }
            }
        } catch (Throwable e) {
            zzb.zzb("Failed to get image.", e);
        }
        try {
            zzdx zzlo = this.zzblq.zzlo();
            if (zzlo != null) {
                com_google_android_gms_internal_zzdy = new zzdy(zzlo);
                this.zzbls = com_google_android_gms_internal_zzdy;
            }
        } catch (Throwable e2) {
            zzb.zzb("Failed to get icon.", e2);
        }
        com_google_android_gms_internal_zzdy = null;
        this.zzbls = com_google_android_gms_internal_zzdy;
    }

    public void destroy() {
        try {
            this.zzblq.destroy();
        } catch (Throwable e) {
            zzb.zzb("Failed to destroy", e);
        }
    }

    public CharSequence getBody() {
        try {
            return this.zzblq.getBody();
        } catch (Throwable e) {
            zzb.zzb("Failed to get body.", e);
            return null;
        }
    }

    public CharSequence getCallToAction() {
        try {
            return this.zzblq.getCallToAction();
        } catch (Throwable e) {
            zzb.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    public Bundle getExtras() {
        try {
            return this.zzblq.getExtras();
        } catch (Throwable e) {
            zzb.zzb("Failed to get extras", e);
            return null;
        }
    }

    public CharSequence getHeadline() {
        try {
            return this.zzblq.getHeadline();
        } catch (Throwable e) {
            zzb.zzb("Failed to get headline.", e);
            return null;
        }
    }

    public Image getIcon() {
        return this.zzbls;
    }

    public List<Image> getImages() {
        return this.zzblr;
    }

    public CharSequence getPrice() {
        try {
            return this.zzblq.getPrice();
        } catch (Throwable e) {
            zzb.zzb("Failed to get price.", e);
            return null;
        }
    }

    public Double getStarRating() {
        Double d = null;
        try {
            double starRating = this.zzblq.getStarRating();
            if (starRating != -1.0d) {
                d = Double.valueOf(starRating);
            }
        } catch (Throwable e) {
            zzb.zzb("Failed to get star rating.", e);
        }
        return d;
    }

    public CharSequence getStore() {
        try {
            return this.zzblq.getStore();
        } catch (Throwable e) {
            zzb.zzb("Failed to get store", e);
            return null;
        }
    }

    public VideoController getVideoController() {
        try {
            if (this.zzblq.zzdw() != null) {
                this.zzayu.zza(this.zzblq.zzdw());
            }
        } catch (Throwable e) {
            zzb.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzayu;
    }

    protected /* synthetic */ Object zzdl() {
        return zzlp();
    }

    zzdx zze(Object obj) {
        return obj instanceof IBinder ? zza.zzab((IBinder) obj) : null;
    }

    protected zzd zzlp() {
        try {
            return this.zzblq.zzlp();
        } catch (Throwable e) {
            zzb.zzb("Failed to retrieve native ad engine.", e);
            return null;
        }
    }
}
