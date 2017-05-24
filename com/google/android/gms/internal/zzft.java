package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzu.zza;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.zzu;

@zziy
public class zzft extends zza {
    private final String zzang;
    private final zzfn zzbpg;
    @Nullable
    private zzl zzbpl;
    private final zzfp zzbps;
    @Nullable
    private zzib zzbpt;
    private String zzbpu;

    public zzft(Context context, String str, zzgq com_google_android_gms_internal_zzgq, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        this(str, new zzfn(context, com_google_android_gms_internal_zzgq, versionInfoParcel, com_google_android_gms_ads_internal_zzd));
    }

    zzft(String str, zzfn com_google_android_gms_internal_zzfn) {
        this.zzang = str;
        this.zzbpg = com_google_android_gms_internal_zzfn;
        this.zzbps = new zzfp();
        zzu.zzgo().zza(com_google_android_gms_internal_zzfn);
    }

    private void zzmu() {
        if (this.zzbpl != null && this.zzbpt != null) {
            this.zzbpl.zza(this.zzbpt, this.zzbpu);
        }
    }

    static boolean zzq(AdRequestParcel adRequestParcel) {
        Bundle zzk = zzfq.zzk(adRequestParcel);
        return zzk != null && zzk.containsKey("gw");
    }

    static boolean zzr(AdRequestParcel adRequestParcel) {
        Bundle zzk = zzfq.zzk(adRequestParcel);
        return zzk != null && zzk.containsKey("_ad");
    }

    void abort() {
        if (this.zzbpl == null) {
            this.zzbpl = this.zzbpg.zzbf(this.zzang);
            this.zzbps.zzc(this.zzbpl);
            zzmu();
        }
    }

    public void destroy() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.destroy();
        }
    }

    @Nullable
    public String getMediationAdapterClassName() throws RemoteException {
        return this.zzbpl != null ? this.zzbpl.getMediationAdapterClassName() : null;
    }

    public boolean isLoading() throws RemoteException {
        return this.zzbpl != null && this.zzbpl.isLoading();
    }

    public boolean isReady() throws RemoteException {
        return this.zzbpl != null && this.zzbpl.isReady();
    }

    public void pause() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.pause();
        }
    }

    public void resume() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.resume();
        }
    }

    public void setManualImpressionsEnabled(boolean z) throws RemoteException {
        abort();
        if (this.zzbpl != null) {
            this.zzbpl.setManualImpressionsEnabled(z);
        }
    }

    public void setUserId(String str) {
    }

    public void showInterstitial() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.showInterstitial();
        } else {
            zzb.zzdf("Interstitial ad must be loaded before showInterstitial().");
        }
    }

    public void stopLoading() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.stopLoading();
        }
    }

    public void zza(AdSizeParcel adSizeParcel) throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.zza(adSizeParcel);
        }
    }

    public void zza(VideoOptionsParcel videoOptionsParcel) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public void zza(zzp com_google_android_gms_ads_internal_client_zzp) throws RemoteException {
        this.zzbps.zzbpb = com_google_android_gms_ads_internal_client_zzp;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }

    public void zza(zzq com_google_android_gms_ads_internal_client_zzq) throws RemoteException {
        this.zzbps.zzamy = com_google_android_gms_ads_internal_client_zzq;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }

    public void zza(zzw com_google_android_gms_ads_internal_client_zzw) throws RemoteException {
        this.zzbps.zzboy = com_google_android_gms_ads_internal_client_zzw;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }

    public void zza(zzy com_google_android_gms_ads_internal_client_zzy) throws RemoteException {
        abort();
        if (this.zzbpl != null) {
            this.zzbpl.zza(com_google_android_gms_ads_internal_client_zzy);
        }
    }

    public void zza(com.google.android.gms.ads.internal.reward.client.zzd com_google_android_gms_ads_internal_reward_client_zzd) {
        this.zzbps.zzbpc = com_google_android_gms_ads_internal_reward_client_zzd;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }

    public void zza(zzdu com_google_android_gms_internal_zzdu) throws RemoteException {
        this.zzbps.zzbpa = com_google_android_gms_internal_zzdu;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }

    public void zza(zzhx com_google_android_gms_internal_zzhx) throws RemoteException {
        this.zzbps.zzboz = com_google_android_gms_internal_zzhx;
        if (this.zzbpl != null) {
            this.zzbps.zzc(this.zzbpl);
        }
    }

    public void zza(zzib com_google_android_gms_internal_zzib, String str) throws RemoteException {
        this.zzbpt = com_google_android_gms_internal_zzib;
        this.zzbpu = str;
        zzmu();
    }

    public boolean zzb(AdRequestParcel adRequestParcel) throws RemoteException {
        if (((Boolean) zzdi.zzbdg.get()).booleanValue()) {
            AdRequestParcel.zzj(adRequestParcel);
        }
        if (!zzq(adRequestParcel)) {
            abort();
        }
        if (zzfq.zzm(adRequestParcel)) {
            abort();
        }
        if (adRequestParcel.zzawk != null) {
            abort();
        }
        if (this.zzbpl != null) {
            return this.zzbpl.zzb(adRequestParcel);
        }
        zzfq zzgo = zzu.zzgo();
        if (zzr(adRequestParcel)) {
            zzgo.zzb(adRequestParcel, this.zzang);
        }
        zza zza = zzgo.zza(adRequestParcel, this.zzang);
        if (zza != null) {
            if (!zza.zzbpp) {
                zza.zzmt();
            }
            this.zzbpl = zza.zzbpl;
            zza.zzbpn.zza(this.zzbps);
            this.zzbps.zzc(this.zzbpl);
            zzmu();
            return zza.zzbpq;
        }
        abort();
        return this.zzbpl.zzb(adRequestParcel);
    }

    @Nullable
    public com.google.android.gms.dynamic.zzd zzds() throws RemoteException {
        return this.zzbpl != null ? this.zzbpl.zzds() : null;
    }

    @Nullable
    public AdSizeParcel zzdt() throws RemoteException {
        return this.zzbpl != null ? this.zzbpl.zzdt() : null;
    }

    public void zzdv() throws RemoteException {
        if (this.zzbpl != null) {
            this.zzbpl.zzdv();
        } else {
            zzb.zzdf("Interstitial ad must be loaded before pingManualTrackingUrl().");
        }
    }

    public zzab zzdw() {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }
}
