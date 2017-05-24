package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdx.zza;
import java.util.ArrayList;
import java.util.List;

@zziy
public class zzee extends NativeContentAd {
    private final List<Image> zzblr = new ArrayList();
    private final zzed zzblt;
    private final zzdy zzblu;

    public zzee(zzed com_google_android_gms_internal_zzed) {
        zzdy com_google_android_gms_internal_zzdy;
        this.zzblt = com_google_android_gms_internal_zzed;
        try {
            List<Object> images = this.zzblt.getImages();
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
            zzdx zzlt = this.zzblt.zzlt();
            if (zzlt != null) {
                com_google_android_gms_internal_zzdy = new zzdy(zzlt);
                this.zzblu = com_google_android_gms_internal_zzdy;
            }
        } catch (Throwable e2) {
            zzb.zzb("Failed to get icon.", e2);
        }
        com_google_android_gms_internal_zzdy = null;
        this.zzblu = com_google_android_gms_internal_zzdy;
    }

    public void destroy() {
        try {
            this.zzblt.destroy();
        } catch (Throwable e) {
            zzb.zzb("Failed to destroy", e);
        }
    }

    public CharSequence getAdvertiser() {
        try {
            return this.zzblt.getAdvertiser();
        } catch (Throwable e) {
            zzb.zzb("Failed to get attribution.", e);
            return null;
        }
    }

    public CharSequence getBody() {
        try {
            return this.zzblt.getBody();
        } catch (Throwable e) {
            zzb.zzb("Failed to get body.", e);
            return null;
        }
    }

    public CharSequence getCallToAction() {
        try {
            return this.zzblt.getCallToAction();
        } catch (Throwable e) {
            zzb.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    public Bundle getExtras() {
        try {
            return this.zzblt.getExtras();
        } catch (Throwable e) {
            zzb.zzd("Failed to get extras", e);
            return null;
        }
    }

    public CharSequence getHeadline() {
        try {
            return this.zzblt.getHeadline();
        } catch (Throwable e) {
            zzb.zzb("Failed to get headline.", e);
            return null;
        }
    }

    public List<Image> getImages() {
        return this.zzblr;
    }

    public Image getLogo() {
        return this.zzblu;
    }

    protected /* synthetic */ Object zzdl() {
        return zzlp();
    }

    zzdx zze(Object obj) {
        return obj instanceof IBinder ? zza.zzab((IBinder) obj) : null;
    }

    protected zzd zzlp() {
        try {
            return this.zzblt.zzlp();
        } catch (Throwable e) {
            zzb.zzb("Failed to retrieve native ad engine.", e);
            return null;
        }
    }
}
