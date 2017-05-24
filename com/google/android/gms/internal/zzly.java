package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzab.zza;
import com.google.android.gms.ads.internal.client.zzac;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.zzf;
import java.util.HashMap;
import java.util.Map;

@zziy
public class zzly extends zza {
    private final Object zzakd = new Object();
    private boolean zzakg = true;
    private final zzlt zzbkr;
    private final float zzcwo;
    private int zzcwp;
    private zzac zzcwq;
    private boolean zzcwr;
    private boolean zzcws;
    private float zzcwt;
    private float zzcwu;

    public zzly(zzlt com_google_android_gms_internal_zzlt, float f) {
        this.zzbkr = com_google_android_gms_internal_zzlt;
        this.zzcwo = f;
    }

    private void zzdl(String str) {
        zze(str, null);
    }

    private void zze(String str, @Nullable Map<String, String> map) {
        final Map hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put("action", str);
        zzu.zzfz().runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzly zzcww;

            public void run() {
                this.zzcww.zzbkr.zza("pubVideoCmd", hashMap);
            }
        });
    }

    private void zzi(final int i, final int i2) {
        zzu.zzfz().runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzly zzcww;

            public void run() {
                boolean z = false;
                synchronized (this.zzcww.zzakd) {
                    boolean z2 = i != i2;
                    boolean z3 = !this.zzcww.zzcwr && i2 == 1;
                    boolean z4 = z2 && i2 == 1;
                    boolean z5 = z2 && i2 == 2;
                    z2 = z2 && i2 == 3;
                    zzly com_google_android_gms_internal_zzly = this.zzcww;
                    if (this.zzcww.zzcwr || z3) {
                        z = true;
                    }
                    com_google_android_gms_internal_zzly.zzcwr = z;
                    if (this.zzcww.zzcwq == null) {
                        return;
                    }
                    if (z3) {
                        try {
                            this.zzcww.zzcwq.zzjw();
                        } catch (Throwable e) {
                            zzb.zzd("Unable to call onVideoStart()", e);
                        }
                    }
                    if (z4) {
                        try {
                            this.zzcww.zzcwq.zzjx();
                        } catch (Throwable e2) {
                            zzb.zzd("Unable to call onVideoPlay()", e2);
                        }
                    }
                    if (z5) {
                        try {
                            this.zzcww.zzcwq.zzjy();
                        } catch (Throwable e22) {
                            zzb.zzd("Unable to call onVideoPause()", e22);
                        }
                    }
                    if (z2) {
                        try {
                            this.zzcww.zzcwq.onVideoEnd();
                        } catch (Throwable e222) {
                            zzb.zzd("Unable to call onVideoEnd()", e222);
                        }
                    }
                }
            }
        });
    }

    public float getAspectRatio() {
        float f;
        synchronized (this.zzakd) {
            f = this.zzcwu;
        }
        return f;
    }

    public int getPlaybackState() {
        int i;
        synchronized (this.zzakd) {
            i = this.zzcwp;
        }
        return i;
    }

    public boolean isMuted() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcws;
        }
        return z;
    }

    public void pause() {
        zzdl("pause");
    }

    public void play() {
        zzdl("play");
    }

    public void zza(float f, int i, boolean z, float f2) {
        int i2;
        synchronized (this.zzakd) {
            this.zzcwt = f;
            this.zzcws = z;
            i2 = this.zzcwp;
            this.zzcwp = i;
            this.zzcwu = f2;
        }
        zzi(i2, i);
    }

    public void zza(zzac com_google_android_gms_ads_internal_client_zzac) {
        synchronized (this.zzakd) {
            this.zzcwq = com_google_android_gms_ads_internal_client_zzac;
        }
    }

    public void zzap(boolean z) {
        synchronized (this.zzakd) {
            this.zzakg = z;
        }
        zze("initialState", zzf.zze("muteStart", z ? "1" : "0"));
    }

    public float zzju() {
        return this.zzcwo;
    }

    public float zzjv() {
        float f;
        synchronized (this.zzakd) {
            f = this.zzcwt;
        }
        return f;
    }

    public void zzn(boolean z) {
        zzdl(z ? "mute" : "unmute");
    }
}
