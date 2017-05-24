package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.zzn;
import com.google.android.gms.internal.zzke.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@zziy
public class zzip extends zzik {
    private final zzdq zzalg;
    private zzgq zzals;
    private final zzlt zzbkr;
    private zzgh zzbsv;
    zzgf zzcdh;
    protected zzgl zzcdi;
    private boolean zzcdj;

    zzip(Context context, zza com_google_android_gms_internal_zzke_zza, zzgq com_google_android_gms_internal_zzgq, zzil.zza com_google_android_gms_internal_zzil_zza, zzdq com_google_android_gms_internal_zzdq, zzlt com_google_android_gms_internal_zzlt) {
        super(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzil_zza);
        this.zzals = com_google_android_gms_internal_zzgq;
        this.zzbsv = com_google_android_gms_internal_zzke_zza.zzcof;
        this.zzalg = com_google_android_gms_internal_zzdq;
        this.zzbkr = com_google_android_gms_internal_zzlt;
    }

    private static String zza(zzgl com_google_android_gms_internal_zzgl) {
        String str = com_google_android_gms_internal_zzgl.zzbte.zzbro;
        int zzao = zzao(com_google_android_gms_internal_zzgl.zzbtd);
        return new StringBuilder(String.valueOf(str).length() + 33).append(str).append(".").append(zzao).append(".").append(com_google_android_gms_internal_zzgl.zzbtj).toString();
    }

    private static int zzao(int i) {
        switch (i) {
            case -1:
                return 4;
            case 0:
                return 0;
            case 1:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 5;
            default:
                return 6;
        }
    }

    private static String zzg(List<zzgl> list) {
        String str = "";
        if (list == null) {
            return str.toString();
        }
        String str2 = str;
        for (zzgl com_google_android_gms_internal_zzgl : list) {
            if (!(com_google_android_gms_internal_zzgl == null || com_google_android_gms_internal_zzgl.zzbte == null || TextUtils.isEmpty(com_google_android_gms_internal_zzgl.zzbte.zzbro))) {
                str2 = String.valueOf(str2);
                str = String.valueOf(zza(com_google_android_gms_internal_zzgl));
                str2 = new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(str).length()).append(str2).append(str).append("_").toString();
            }
        }
        return str2.substring(0, Math.max(0, str2.length() - 1));
    }

    private void zzrd() throws zza {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzip zzcdk;

            public void run() {
                synchronized (this.zzcdk.zzccn) {
                    this.zzcdk.zzcdj = zzn.zza(this.zzcdk.zzbkr, this.zzcdk.zzcdi, countDownLatch);
                }
            }
        });
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
            synchronized (this.zzccn) {
                if (!this.zzcdj) {
                    throw new zza("View could not be prepared", 0);
                } else if (this.zzbkr.isDestroyed()) {
                    throw new zza("Assets not loaded, web view is destroyed", 0);
                }
            }
        } catch (InterruptedException e) {
            String valueOf = String.valueOf(e);
            throw new zza(new StringBuilder(String.valueOf(valueOf).length() + 38).append("Interrupted while waiting for latch : ").append(valueOf).toString(), 0);
        }
    }

    public void onStop() {
        synchronized (this.zzccn) {
            super.onStop();
            if (this.zzcdh != null) {
                this.zzcdh.cancel();
            }
        }
    }

    protected zzke zzam(int i) {
        AdRequestInfoParcel adRequestInfoParcel = this.zzcck.zzcix;
        return new zzke(adRequestInfoParcel.zzcfu, this.zzbkr, this.zzccl.zzbsd, i, this.zzccl.zzbse, this.zzccl.zzche, this.zzccl.orientation, this.zzccl.zzbsj, adRequestInfoParcel.zzcfx, this.zzccl.zzchc, this.zzcdi != null ? this.zzcdi.zzbte : null, this.zzcdi != null ? this.zzcdi.zzbtf : null, this.zzcdi != null ? this.zzcdi.zzbtg : AdMobAdapter.class.getName(), this.zzbsv, this.zzcdi != null ? this.zzcdi.zzbth : null, this.zzccl.zzchd, this.zzcck.zzaqz, this.zzccl.zzchb, this.zzcck.zzcoj, this.zzccl.zzchg, this.zzccl.zzchh, this.zzcck.zzcod, null, this.zzccl.zzchr, this.zzccl.zzchs, this.zzccl.zzcht, this.zzbsv != null ? this.zzbsv.zzbso : false, this.zzccl.zzchv, this.zzcdh != null ? zzg(this.zzcdh.zzne()) : null, this.zzccl.zzbsg, this.zzccl.zzchy);
    }

    protected void zzh(long j) throws zza {
        boolean z;
        ListIterator listIterator;
        synchronized (this.zzccn) {
            this.zzcdh = zzi(j);
        }
        List arrayList = new ArrayList(this.zzbsv.zzbsb);
        Bundle bundle = this.zzcck.zzcix.zzcfu.zzawn;
        String str = "com.google.ads.mediation.admob.AdMobAdapter";
        if (bundle != null) {
            bundle = bundle.getBundle(str);
            if (bundle != null) {
                z = bundle.getBoolean("_skipMediation");
                if (z) {
                    listIterator = arrayList.listIterator();
                    while (listIterator.hasNext()) {
                        if (!((zzgg) listIterator.next()).zzbrn.contains(str)) {
                            listIterator.remove();
                        }
                    }
                }
                this.zzcdi = this.zzcdh.zzd(arrayList);
                switch (this.zzcdi.zzbtd) {
                    case 0:
                        if (this.zzcdi.zzbte != null && this.zzcdi.zzbte.zzbrw != null) {
                            zzrd();
                            return;
                        }
                        return;
                    case 1:
                        throw new zza("No fill from any mediation ad networks.", 3);
                    default:
                        throw new zza("Unexpected mediation result: " + this.zzcdi.zzbtd, 0);
                }
            }
        }
        z = false;
        if (z) {
            listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                if (!((zzgg) listIterator.next()).zzbrn.contains(str)) {
                    listIterator.remove();
                }
            }
        }
        this.zzcdi = this.zzcdh.zzd(arrayList);
        switch (this.zzcdi.zzbtd) {
            case 0:
                if (this.zzcdi.zzbte != null) {
                    return;
                }
                return;
            case 1:
                throw new zza("No fill from any mediation ad networks.", 3);
            default:
                throw new zza("Unexpected mediation result: " + this.zzcdi.zzbtd, 0);
        }
    }

    zzgf zzi(long j) {
        if (this.zzbsv.zzbsm != -1) {
            return new zzgn(this.mContext, this.zzcck.zzcix, this.zzals, this.zzbsv, this.zzccl.zzaxl, this.zzccl.zzaxn, j, ((Long) zzdi.zzbel.get()).longValue(), 2);
        }
        return new zzgo(this.mContext, this.zzcck.zzcix, this.zzals, this.zzbsv, this.zzccl.zzaxl, this.zzccl.zzaxn, j, ((Long) zzdi.zzbel.get()).longValue(), this.zzalg);
    }
}
