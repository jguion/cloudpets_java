package com.google.android.gms.ads.internal.client;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzei;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.internal.zzek;

public class zzaj extends com.google.android.gms.ads.internal.client.zzs.zza {
    private zzq zzamy;

    private class zza extends com.google.android.gms.ads.internal.client.zzr.zza {
        final /* synthetic */ zzaj zzazk;

        private zza(zzaj com_google_android_gms_ads_internal_client_zzaj) {
            this.zzazk = com_google_android_gms_ads_internal_client_zzaj;
        }

        public String getMediationAdapterClassName() throws RemoteException {
            return null;
        }

        public boolean isLoading() throws RemoteException {
            return false;
        }

        public void zzf(AdRequestParcel adRequestParcel) throws RemoteException {
            zzb.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
            com.google.android.gms.ads.internal.util.client.zza.zzctj.post(new Runnable(this) {
                final /* synthetic */ zza zzazl;

                {
                    this.zzazl = r1;
                }

                public void run() {
                    if (this.zzazl.zzazk.zzamy != null) {
                        try {
                            this.zzazl.zzazk.zzamy.onAdFailedToLoad(1);
                        } catch (Throwable e) {
                            zzb.zzd("Could not notify onAdFailedToLoad event.", e);
                        }
                    }
                }
            });
        }
    }

    public void zza(NativeAdOptionsParcel nativeAdOptionsParcel) throws RemoteException {
    }

    public void zza(zzeh com_google_android_gms_internal_zzeh) throws RemoteException {
    }

    public void zza(zzei com_google_android_gms_internal_zzei) throws RemoteException {
    }

    public void zza(String str, zzek com_google_android_gms_internal_zzek, zzej com_google_android_gms_internal_zzej) throws RemoteException {
    }

    public void zzb(zzq com_google_android_gms_ads_internal_client_zzq) throws RemoteException {
        this.zzamy = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zzb(zzy com_google_android_gms_ads_internal_client_zzy) throws RemoteException {
    }

    public zzr zzey() throws RemoteException {
        return new zza();
    }
}
