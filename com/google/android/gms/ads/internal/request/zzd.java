package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zze.zzc;
import com.google.android.gms.internal.zzdb;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.internal.zzkt;
import com.google.android.gms.internal.zzlm;

@zziy
public abstract class zzd implements com.google.android.gms.ads.internal.request.zzc.zza, zzkt<Void> {
    private final Object zzakd = new Object();
    private final zzlm<AdRequestInfoParcel> zzcfm;
    private final com.google.android.gms.ads.internal.request.zzc.zza zzcfn;

    @zziy
    public static final class zza extends zzd {
        private final Context mContext;

        public zza(Context context, zzlm<AdRequestInfoParcel> com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com.google.android.gms.ads.internal.request.zzc.zza com_google_android_gms_ads_internal_request_zzc_zza) {
            super(com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
            this.mContext = context;
        }

        public /* synthetic */ Object zzqw() {
            return super.zzqt();
        }

        public void zzrv() {
        }

        public zzk zzrw() {
            return zzjb.zza(this.mContext, new zzdb((String) zzdi.zzbao.get()), zzja.zzsc());
        }
    }

    @zziy
    public static class zzb extends zzd implements com.google.android.gms.common.internal.zze.zzb, zzc {
        private Context mContext;
        private final Object zzakd = new Object();
        private VersionInfoParcel zzanh;
        private zzlm<AdRequestInfoParcel> zzcfm;
        private final com.google.android.gms.ads.internal.request.zzc.zza zzcfn;
        protected zze zzcfq;
        private boolean zzcfr;

        public zzb(Context context, VersionInfoParcel versionInfoParcel, zzlm<AdRequestInfoParcel> com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com.google.android.gms.ads.internal.request.zzc.zza com_google_android_gms_ads_internal_request_zzc_zza) {
            Looper zzuy;
            super(com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
            this.mContext = context;
            this.zzanh = versionInfoParcel;
            this.zzcfm = com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel;
            this.zzcfn = com_google_android_gms_ads_internal_request_zzc_zza;
            if (((Boolean) zzdi.zzbbu.get()).booleanValue()) {
                this.zzcfr = true;
                zzuy = zzu.zzgp().zzuy();
            } else {
                zzuy = context.getMainLooper();
            }
            this.zzcfq = new zze(context, zzuy, this, this, this.zzanh.zzctt);
            connect();
        }

        protected void connect() {
            this.zzcfq.zzatu();
        }

        public void onConnected(Bundle bundle) {
            Void voidR = (Void) zzqw();
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Cannot connect to remote service, fallback to local instance.");
            zzrx().zzqw();
            Bundle bundle = new Bundle();
            bundle.putString("action", "gms_connection_failed_fallback_to_local");
            zzu.zzfz().zzb(this.mContext, this.zzanh.zzcs, "gmob-apps", bundle, true);
        }

        public void onConnectionSuspended(int i) {
            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Disconnected from remote ad request service.");
        }

        public /* synthetic */ Object zzqw() {
            return super.zzqt();
        }

        public void zzrv() {
            synchronized (this.zzakd) {
                if (this.zzcfq.isConnected() || this.zzcfq.isConnecting()) {
                    this.zzcfq.disconnect();
                }
                Binder.flushPendingCommands();
                if (this.zzcfr) {
                    zzu.zzgp().zzuz();
                    this.zzcfr = false;
                }
            }
        }

        public zzk zzrw() {
            zzk zzry;
            synchronized (this.zzakd) {
                try {
                    zzry = this.zzcfq.zzry();
                } catch (IllegalStateException e) {
                    zzry = null;
                    return zzry;
                } catch (DeadObjectException e2) {
                    zzry = null;
                    return zzry;
                }
            }
            return zzry;
        }

        zzkt zzrx() {
            return new zza(this.mContext, this.zzcfm, this.zzcfn);
        }
    }

    public zzd(zzlm<AdRequestInfoParcel> com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com.google.android.gms.ads.internal.request.zzc.zza com_google_android_gms_ads_internal_request_zzc_zza) {
        this.zzcfm = com_google_android_gms_internal_zzlm_com_google_android_gms_ads_internal_request_AdRequestInfoParcel;
        this.zzcfn = com_google_android_gms_ads_internal_request_zzc_zza;
    }

    public void cancel() {
        zzrv();
    }

    boolean zza(zzk com_google_android_gms_ads_internal_request_zzk, AdRequestInfoParcel adRequestInfoParcel) {
        try {
            com_google_android_gms_ads_internal_request_zzk.zza(adRequestInfoParcel, new zzg(this));
            return true;
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service.", e);
            zzu.zzgd().zza(e, "AdRequestClientTask.getAdResponseFromService");
            this.zzcfn.zzb(new AdResponseParcel(0));
            return false;
        } catch (Throwable e2) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", e2);
            zzu.zzgd().zza(e2, "AdRequestClientTask.getAdResponseFromService");
            this.zzcfn.zzb(new AdResponseParcel(0));
            return false;
        } catch (Throwable e22) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", e22);
            zzu.zzgd().zza(e22, "AdRequestClientTask.getAdResponseFromService");
            this.zzcfn.zzb(new AdResponseParcel(0));
            return false;
        } catch (Throwable e222) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", e222);
            zzu.zzgd().zza(e222, "AdRequestClientTask.getAdResponseFromService");
            this.zzcfn.zzb(new AdResponseParcel(0));
            return false;
        }
    }

    public void zzb(AdResponseParcel adResponseParcel) {
        synchronized (this.zzakd) {
            this.zzcfn.zzb(adResponseParcel);
            zzrv();
        }
    }

    public Void zzqt() {
        final zzk zzrw = zzrw();
        if (zzrw == null) {
            this.zzcfn.zzb(new AdResponseParcel(0));
            zzrv();
        } else {
            this.zzcfm.zza(new zzlm.zzc<AdRequestInfoParcel>(this) {
                final /* synthetic */ zzd zzcfp;

                public void zzc(AdRequestInfoParcel adRequestInfoParcel) {
                    if (!this.zzcfp.zza(zzrw, adRequestInfoParcel)) {
                        this.zzcfp.zzrv();
                    }
                }

                public /* synthetic */ void zzd(Object obj) {
                    zzc((AdRequestInfoParcel) obj);
                }
            }, new com.google.android.gms.internal.zzlm.zza(this) {
                final /* synthetic */ zzd zzcfp;

                {
                    this.zzcfp = r1;
                }

                public void run() {
                    this.zzcfp.zzrv();
                }
            });
        }
        return null;
    }

    public /* synthetic */ Object zzqw() {
        return zzqt();
    }

    public abstract void zzrv();

    public abstract zzk zzrw();
}
