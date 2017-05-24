package com.google.android.gms.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzap;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zziy;

@zziy
public final class VideoController {
    private final Object zzakd = new Object();
    @Nullable
    private zzab zzake;
    @Nullable
    private VideoLifecycleCallbacks zzakf;

    public static abstract class VideoLifecycleCallbacks {
        public void onVideoEnd() {
        }
    }

    public float getAspectRatio() {
        float f = 0.0f;
        synchronized (this.zzakd) {
            if (this.zzake == null) {
            } else {
                try {
                    f = this.zzake.getAspectRatio();
                } catch (Throwable e) {
                    zzb.zzb("Unable to call getAspectRatio on video controller.", e);
                }
            }
        }
        return f;
    }

    @Nullable
    public VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.zzakd) {
            videoLifecycleCallbacks = this.zzakf;
        }
        return videoLifecycleCallbacks;
    }

    public boolean hasVideoContent() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzake != null;
        }
        return z;
    }

    public void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        zzac.zzb((Object) videoLifecycleCallbacks, (Object) "VideoLifecycleCallbacks may not be null.");
        synchronized (this.zzakd) {
            this.zzakf = videoLifecycleCallbacks;
            if (this.zzake == null) {
                return;
            }
            try {
                this.zzake.zza(new zzap(videoLifecycleCallbacks));
            } catch (Throwable e) {
                zzb.zzb("Unable to call setVideoLifecycleCallbacks on video controller.", e);
            }
        }
    }

    public void zza(zzab com_google_android_gms_ads_internal_client_zzab) {
        synchronized (this.zzakd) {
            this.zzake = com_google_android_gms_ads_internal_client_zzab;
            if (this.zzakf != null) {
                setVideoLifecycleCallbacks(this.zzakf);
            }
        }
    }

    public zzab zzdj() {
        zzab com_google_android_gms_ads_internal_client_zzab;
        synchronized (this.zzakd) {
            com_google_android_gms_ads_internal_client_zzab = this.zzake;
        }
        return com_google_android_gms_ads_internal_client_zzab;
    }
}
