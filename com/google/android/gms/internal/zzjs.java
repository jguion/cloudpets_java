package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzke.zza;

@zziy
public class zzjs extends zzkm implements zzju, zzjx {
    private final Context mContext;
    private final Object zzakd;
    private final String zzbst;
    private final zza zzcck;
    private int zzcdb = 3;
    private final zzjz zzcna;
    private final zzjx zzcnb;
    private final String zzcnc;
    private final zzgg zzcnd;
    private final long zzcne;
    private int zzcnf = 0;
    private zzjt zzcng;

    public zzjs(Context context, String str, String str2, zzgg com_google_android_gms_internal_zzgg, zza com_google_android_gms_internal_zzke_zza, zzjz com_google_android_gms_internal_zzjz, zzjx com_google_android_gms_internal_zzjx, long j) {
        this.mContext = context;
        this.zzbst = str;
        this.zzcnc = str2;
        this.zzcnd = com_google_android_gms_internal_zzgg;
        this.zzcck = com_google_android_gms_internal_zzke_zza;
        this.zzcna = com_google_android_gms_internal_zzjz;
        this.zzakd = new Object();
        this.zzcnb = com_google_android_gms_internal_zzjx;
        this.zzcne = j;
    }

    private void zza(AdRequestParcel adRequestParcel, zzgr com_google_android_gms_internal_zzgr) {
        this.zzcna.zzsw().zza((zzjx) this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbst)) {
                com_google_android_gms_internal_zzgr.zza(adRequestParcel, this.zzcnc, this.zzcnd.zzbrl);
            } else {
                com_google_android_gms_internal_zzgr.zzc(adRequestParcel, this.zzcnc);
            }
        } catch (Throwable e) {
            zzb.zzd("Fail to load ad from adapter.", e);
            zza(this.zzbst, 0);
        }
    }

    private void zzk(long j) {
        while (true) {
            synchronized (this.zzakd) {
                if (this.zzcnf != 0) {
                    break;
                } else if (!zzf(j)) {
                    this.zzcng = new zzjt.zza().zzaz(this.zzcdb).zzl(zzu.zzgf().elapsedRealtime() - j).zzcn(this.zzbst).zzco(this.zzcnd.zzbro).zzss();
                    return;
                }
            }
        }
        this.zzcng = new zzjt.zza().zzl(zzu.zzgf().elapsedRealtime() - j).zzaz(1 == this.zzcnf ? 6 : this.zzcdb).zzcn(this.zzbst).zzco(this.zzcnd.zzbro).zzss();
    }

    public void onStop() {
    }

    public void zza(String str, int i) {
        synchronized (this.zzakd) {
            this.zzcnf = 2;
            this.zzcdb = i;
            this.zzakd.notify();
        }
    }

    public void zzay(int i) {
        zza(this.zzbst, 0);
    }

    public void zzcm(String str) {
        synchronized (this.zzakd) {
            this.zzcnf = 1;
            this.zzakd.notify();
        }
    }

    protected boolean zzf(long j) {
        long elapsedRealtime = this.zzcne - (zzu.zzgf().elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            this.zzcdb = 4;
            return false;
        }
        try {
            this.zzakd.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.zzcdb = 5;
            return false;
        }
    }

    public void zzfc() {
        if (this.zzcna != null && this.zzcna.zzsw() != null && this.zzcna.zzsv() != null) {
            final zzjw zzsw = this.zzcna.zzsw();
            zzsw.zza(null);
            zzsw.zza((zzju) this);
            final AdRequestParcel adRequestParcel = this.zzcck.zzcix.zzcfu;
            final zzgr zzsv = this.zzcna.zzsv();
            try {
                if (zzsv.isInitialized()) {
                    com.google.android.gms.ads.internal.util.client.zza.zzctj.post(new Runnable(this) {
                        final /* synthetic */ zzjs zzcni;

                        public void run() {
                            this.zzcni.zza(adRequestParcel, zzsv);
                        }
                    });
                } else {
                    com.google.android.gms.ads.internal.util.client.zza.zzctj.post(new Runnable(this) {
                        final /* synthetic */ zzjs zzcni;

                        public void run() {
                            try {
                                zzsv.zza(zze.zzac(this.zzcni.mContext), adRequestParcel, null, zzsw, this.zzcni.zzcnc);
                            } catch (Throwable e) {
                                Throwable th = e;
                                String str = "Fail to initialize adapter ";
                                String valueOf = String.valueOf(this.zzcni.zzbst);
                                zzb.zzd(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), th);
                                this.zzcni.zza(this.zzcni.zzbst, 0);
                            }
                        }
                    });
                }
            } catch (Throwable e) {
                zzb.zzd("Fail to check if adapter is initialized.", e);
                zza(this.zzbst, 0);
            }
            zzk(zzu.zzgf().elapsedRealtime());
            zzsw.zza(null);
            zzsw.zza(null);
            if (this.zzcnf == 1) {
                this.zzcnb.zzcm(this.zzbst);
            } else {
                this.zzcnb.zza(this.zzbst, this.zzcdb);
            }
        }
    }

    public zzjt zzsp() {
        zzjt com_google_android_gms_internal_zzjt;
        synchronized (this.zzakd) {
            com_google_android_gms_internal_zzjt = this.zzcng;
        }
        return com_google_android_gms_internal_zzjt;
    }

    public zzgg zzsq() {
        return this.zzcnd;
    }

    public void zzsr() {
        zza(this.zzcck.zzcix.zzcfu, this.zzcna.zzsv());
    }
}
