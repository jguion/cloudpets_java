package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zziy
public class zzcs {
    private final Object zzakd = new Object();
    private int zzaug;
    private List<zzcr> zzauh = new LinkedList();

    public boolean zza(zzcr com_google_android_gms_internal_zzcr) {
        boolean z;
        synchronized (this.zzakd) {
            if (this.zzauh.contains(com_google_android_gms_internal_zzcr)) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public boolean zzb(zzcr com_google_android_gms_internal_zzcr) {
        boolean z;
        synchronized (this.zzakd) {
            Iterator it = this.zzauh.iterator();
            while (it.hasNext()) {
                zzcr com_google_android_gms_internal_zzcr2 = (zzcr) it.next();
                if (com_google_android_gms_internal_zzcr != com_google_android_gms_internal_zzcr2 && com_google_android_gms_internal_zzcr2.zzie().equals(com_google_android_gms_internal_zzcr.zzie())) {
                    it.remove();
                    z = true;
                    break;
                }
            }
            z = false;
        }
        return z;
    }

    public void zzc(zzcr com_google_android_gms_internal_zzcr) {
        synchronized (this.zzakd) {
            if (this.zzauh.size() >= 10) {
                zzb.zzdd("Queue is full, current size = " + this.zzauh.size());
                this.zzauh.remove(0);
            }
            int i = this.zzaug;
            this.zzaug = i + 1;
            com_google_android_gms_internal_zzcr.zzl(i);
            this.zzauh.add(com_google_android_gms_internal_zzcr);
        }
    }

    @Nullable
    public zzcr zzil() {
        zzcr com_google_android_gms_internal_zzcr = null;
        synchronized (this.zzakd) {
            if (this.zzauh.size() == 0) {
                zzb.zzdd("Queue empty");
                return null;
            } else if (this.zzauh.size() >= 2) {
                int i = Integer.MIN_VALUE;
                for (zzcr com_google_android_gms_internal_zzcr2 : this.zzauh) {
                    zzcr com_google_android_gms_internal_zzcr3;
                    int i2;
                    int score = com_google_android_gms_internal_zzcr2.getScore();
                    if (score > i) {
                        int i3 = score;
                        com_google_android_gms_internal_zzcr3 = com_google_android_gms_internal_zzcr2;
                        i2 = i3;
                    } else {
                        i2 = i;
                        com_google_android_gms_internal_zzcr3 = com_google_android_gms_internal_zzcr;
                    }
                    i = i2;
                    com_google_android_gms_internal_zzcr = com_google_android_gms_internal_zzcr3;
                }
                this.zzauh.remove(com_google_android_gms_internal_zzcr);
                return com_google_android_gms_internal_zzcr;
            } else {
                com_google_android_gms_internal_zzcr2 = (zzcr) this.zzauh.get(0);
                com_google_android_gms_internal_zzcr2.zzig();
                return com_google_android_gms_internal_zzcr2;
            }
        }
    }
}
