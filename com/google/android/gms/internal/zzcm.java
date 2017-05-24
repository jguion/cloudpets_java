package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzcj.zza;
import com.google.android.gms.internal.zzcj.zzd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@zziy
public class zzcm implements zzcn {
    private final Object zzakd = new Object();
    private final VersionInfoParcel zzanh;
    private final Context zzask;
    private final WeakHashMap<zzke, zzcj> zzatl = new WeakHashMap();
    private final ArrayList<zzcj> zzatm = new ArrayList();
    private final zzfy zzatn;

    public zzcm(Context context, VersionInfoParcel versionInfoParcel, zzfy com_google_android_gms_internal_zzfy) {
        this.zzask = context.getApplicationContext();
        this.zzanh = versionInfoParcel;
        this.zzatn = com_google_android_gms_internal_zzfy;
    }

    public zzcj zza(AdSizeParcel adSizeParcel, zzke com_google_android_gms_internal_zzke) {
        return zza(adSizeParcel, com_google_android_gms_internal_zzke, com_google_android_gms_internal_zzke.zzbyh.getView());
    }

    public zzcj zza(AdSizeParcel adSizeParcel, zzke com_google_android_gms_internal_zzke, View view) {
        return zza(adSizeParcel, com_google_android_gms_internal_zzke, new zzd(view, com_google_android_gms_internal_zzke), null);
    }

    public zzcj zza(AdSizeParcel adSizeParcel, zzke com_google_android_gms_internal_zzke, View view, zzfz com_google_android_gms_internal_zzfz) {
        return zza(adSizeParcel, com_google_android_gms_internal_zzke, new zzd(view, com_google_android_gms_internal_zzke), com_google_android_gms_internal_zzfz);
    }

    public zzcj zza(AdSizeParcel adSizeParcel, zzke com_google_android_gms_internal_zzke, zzi com_google_android_gms_ads_internal_formats_zzi) {
        return zza(adSizeParcel, com_google_android_gms_internal_zzke, new zza(com_google_android_gms_ads_internal_formats_zzi), null);
    }

    public zzcj zza(AdSizeParcel adSizeParcel, zzke com_google_android_gms_internal_zzke, zzcq com_google_android_gms_internal_zzcq, @Nullable zzfz com_google_android_gms_internal_zzfz) {
        zzcj com_google_android_gms_internal_zzcj;
        synchronized (this.zzakd) {
            if (zzi(com_google_android_gms_internal_zzke)) {
                com_google_android_gms_internal_zzcj = (zzcj) this.zzatl.get(com_google_android_gms_internal_zzke);
            } else {
                if (com_google_android_gms_internal_zzfz != null) {
                    com_google_android_gms_internal_zzcj = new zzco(this.zzask, adSizeParcel, com_google_android_gms_internal_zzke, this.zzanh, com_google_android_gms_internal_zzcq, com_google_android_gms_internal_zzfz);
                } else {
                    com_google_android_gms_internal_zzcj = new zzcp(this.zzask, adSizeParcel, com_google_android_gms_internal_zzke, this.zzanh, com_google_android_gms_internal_zzcq, this.zzatn);
                }
                com_google_android_gms_internal_zzcj.zza((zzcn) this);
                this.zzatl.put(com_google_android_gms_internal_zzke, com_google_android_gms_internal_zzcj);
                this.zzatm.add(com_google_android_gms_internal_zzcj);
            }
        }
        return com_google_android_gms_internal_zzcj;
    }

    public void zza(zzcj com_google_android_gms_internal_zzcj) {
        synchronized (this.zzakd) {
            if (!com_google_android_gms_internal_zzcj.zzhn()) {
                this.zzatm.remove(com_google_android_gms_internal_zzcj);
                Iterator it = this.zzatl.entrySet().iterator();
                while (it.hasNext()) {
                    if (((Entry) it.next()).getValue() == com_google_android_gms_internal_zzcj) {
                        it.remove();
                    }
                }
            }
        }
    }

    public boolean zzi(zzke com_google_android_gms_internal_zzke) {
        boolean z;
        synchronized (this.zzakd) {
            zzcj com_google_android_gms_internal_zzcj = (zzcj) this.zzatl.get(com_google_android_gms_internal_zzke);
            z = com_google_android_gms_internal_zzcj != null && com_google_android_gms_internal_zzcj.zzhn();
        }
        return z;
    }

    public void zzj(zzke com_google_android_gms_internal_zzke) {
        synchronized (this.zzakd) {
            zzcj com_google_android_gms_internal_zzcj = (zzcj) this.zzatl.get(com_google_android_gms_internal_zzke);
            if (com_google_android_gms_internal_zzcj != null) {
                com_google_android_gms_internal_zzcj.zzhl();
            }
        }
    }

    public void zzk(zzke com_google_android_gms_internal_zzke) {
        synchronized (this.zzakd) {
            zzcj com_google_android_gms_internal_zzcj = (zzcj) this.zzatl.get(com_google_android_gms_internal_zzke);
            if (com_google_android_gms_internal_zzcj != null) {
                com_google_android_gms_internal_zzcj.stop();
            }
        }
    }

    public void zzl(zzke com_google_android_gms_internal_zzke) {
        synchronized (this.zzakd) {
            zzcj com_google_android_gms_internal_zzcj = (zzcj) this.zzatl.get(com_google_android_gms_internal_zzke);
            if (com_google_android_gms_internal_zzcj != null) {
                com_google_android_gms_internal_zzcj.pause();
            }
        }
    }

    public void zzm(zzke com_google_android_gms_internal_zzke) {
        synchronized (this.zzakd) {
            zzcj com_google_android_gms_internal_zzcj = (zzcj) this.zzatl.get(com_google_android_gms_internal_zzke);
            if (com_google_android_gms_internal_zzcj != null) {
                com_google_android_gms_internal_zzcj.resume();
            }
        }
    }
}
