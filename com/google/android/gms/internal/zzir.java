package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.internal.zzfy.zzb;
import com.google.android.gms.internal.zzfy.zze;
import com.google.android.gms.internal.zzlm.zzc;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zziy
public class zzir {
    private static final Object zzaok = new Object();
    private static final long zzcdo = TimeUnit.SECONDS.toMillis(60);
    private static boolean zzcdp = false;
    private static zzfy zzcdq = null;
    private final Context mContext;
    private final zzq zzbkj;
    private final zzau zzbkp;
    private final com.google.android.gms.internal.zzke.zza zzcck;
    private zzfw zzcdr;
    private zze zzcds;
    private zzfv zzcdt;
    private boolean zzcdu = false;

    public static abstract class zza {
        public abstract void zze(zzfz com_google_android_gms_internal_zzfz);

        public void zzro() {
        }
    }

    public zzir(Context context, com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, zzq com_google_android_gms_ads_internal_zzq, zzau com_google_android_gms_internal_zzau) {
        this.mContext = context;
        this.zzcck = com_google_android_gms_internal_zzke_zza;
        this.zzbkj = com_google_android_gms_ads_internal_zzq;
        this.zzbkp = com_google_android_gms_internal_zzau;
        this.zzcdu = ((Boolean) zzdi.zzbfx.get()).booleanValue();
    }

    public static String zza(com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, String str) {
        String valueOf = String.valueOf(com_google_android_gms_internal_zzke_zza.zzcop.zzbyj.indexOf("https") == 0 ? "https:" : "http:");
        String valueOf2 = String.valueOf(str);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    private void zzrg() {
        synchronized (zzaok) {
            if (!zzcdp) {
                zzcdq = new zzfy(this.mContext.getApplicationContext() != null ? this.mContext.getApplicationContext() : this.mContext, this.zzcck.zzcix.zzaqv, zza(this.zzcck, (String) zzdi.zzbfv.get()), new zzkw<zzfv>(this) {
                    final /* synthetic */ zzir zzcdw;

                    {
                        this.zzcdw = r1;
                    }

                    public void zza(zzfv com_google_android_gms_internal_zzfv) {
                        com_google_android_gms_internal_zzfv.zza(this.zzcdw.zzbkj, this.zzcdw.zzbkj, this.zzcdw.zzbkj, this.zzcdw.zzbkj, false, null, null, null, null);
                    }

                    public /* synthetic */ void zzd(Object obj) {
                        zza((zzfv) obj);
                    }
                }, new zzb());
                zzcdp = true;
            }
        }
    }

    private void zzrh() {
        this.zzcds = new zze(zzrm().zzc(this.zzbkp));
    }

    private void zzri() {
        this.zzcdr = new zzfw();
    }

    private void zzrj() throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        this.zzcdt = (zzfv) zzrk().zza(this.mContext, this.zzcck.zzcix.zzaqv, zza(this.zzcck, (String) zzdi.zzbfv.get()), this.zzbkp, this.zzbkj.zzdp()).get(zzcdo, TimeUnit.MILLISECONDS);
        this.zzcdt.zza(this.zzbkj, this.zzbkj, this.zzbkj, this.zzbkj, false, null, null, null, null);
    }

    public void zza(final zza com_google_android_gms_internal_zzir_zza) {
        if (this.zzcdu) {
            zze zzrn = zzrn();
            if (zzrn == null) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("SharedJavascriptEngine not initialized");
                return;
            } else {
                zzrn.zza(new zzc<zzfz>(this) {
                    final /* synthetic */ zzir zzcdw;

                    public void zzb(zzfz com_google_android_gms_internal_zzfz) {
                        com_google_android_gms_internal_zzir_zza.zze(com_google_android_gms_internal_zzfz);
                    }

                    public /* synthetic */ void zzd(Object obj) {
                        zzb((zzfz) obj);
                    }
                }, new com.google.android.gms.internal.zzlm.zza(this) {
                    final /* synthetic */ zzir zzcdw;

                    public void run() {
                        com_google_android_gms_internal_zzir_zza.zzro();
                    }
                });
                return;
            }
        }
        zzfz zzrl = zzrl();
        if (zzrl == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdf("JavascriptEngine not initialized");
        } else {
            com_google_android_gms_internal_zzir_zza.zze(zzrl);
        }
    }

    public void zzre() {
        if (this.zzcdu) {
            zzrg();
        } else {
            zzri();
        }
    }

    public void zzrf() throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        if (this.zzcdu) {
            zzrh();
        } else {
            zzrj();
        }
    }

    protected zzfw zzrk() {
        return this.zzcdr;
    }

    protected zzfv zzrl() {
        return this.zzcdt;
    }

    protected zzfy zzrm() {
        return zzcdq;
    }

    protected zze zzrn() {
        return this.zzcds;
    }
}
