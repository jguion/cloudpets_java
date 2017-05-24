package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.zzu;

@zziy
class zzfp {
    @Nullable
    zzq zzamy;
    @Nullable
    zzw zzboy;
    @Nullable
    zzhx zzboz;
    @Nullable
    zzdu zzbpa;
    @Nullable
    zzp zzbpb;
    @Nullable
    zzd zzbpc;

    private static class zza extends com.google.android.gms.ads.internal.client.zzq.zza {
        private final zzq zzbpd;

        zza(zzq com_google_android_gms_ads_internal_client_zzq) {
            this.zzbpd = com_google_android_gms_ads_internal_client_zzq;
        }

        public void onAdClosed() throws RemoteException {
            this.zzbpd.onAdClosed();
            zzu.zzgo().zzmm();
        }

        public void onAdFailedToLoad(int i) throws RemoteException {
            this.zzbpd.onAdFailedToLoad(i);
        }

        public void onAdLeftApplication() throws RemoteException {
            this.zzbpd.onAdLeftApplication();
        }

        public void onAdLoaded() throws RemoteException {
            this.zzbpd.onAdLoaded();
        }

        public void onAdOpened() throws RemoteException {
            this.zzbpd.onAdOpened();
        }
    }

    zzfp() {
    }

    void zzc(zzl com_google_android_gms_ads_internal_zzl) {
        if (this.zzamy != null) {
            com_google_android_gms_ads_internal_zzl.zza(new zza(this.zzamy));
        }
        if (this.zzboy != null) {
            com_google_android_gms_ads_internal_zzl.zza(this.zzboy);
        }
        if (this.zzboz != null) {
            com_google_android_gms_ads_internal_zzl.zza(this.zzboz);
        }
        if (this.zzbpa != null) {
            com_google_android_gms_ads_internal_zzl.zza(this.zzbpa);
        }
        if (this.zzbpb != null) {
            com_google_android_gms_ads_internal_zzl.zza(this.zzbpb);
        }
        if (this.zzbpc != null) {
            com_google_android_gms_ads_internal_zzl.zza(this.zzbpc);
        }
    }
}
