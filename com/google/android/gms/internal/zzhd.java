package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;

@zziy
public final class zzhd<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    private final zzgs zzbub;

    public zzhd(zzgs com_google_android_gms_internal_zzgs) {
        this.zzbub = com_google_android_gms_internal_zzgs;
    }

    public void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzdd("Adapter called onClick.");
        if (zzm.zzjr().zzvf()) {
            try {
                this.zzbub.onAdClicked();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClicked.", e);
                return;
            }
        }
        zzb.zzdf("onClick must be called on the main UI thread.");
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzhd zzbuh;

            {
                this.zzbuh = r1;
            }

            public void run() {
                try {
                    this.zzbuh.zzbub.onAdClicked();
                } catch (Throwable e) {
                    zzb.zzd("Could not call onAdClicked.", e);
                }
            }
        });
    }

    public void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzdd("Adapter called onDismissScreen.");
        if (zzm.zzjr().zzvf()) {
            try {
                this.zzbub.onAdClosed();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClosed.", e);
                return;
            }
        }
        zzb.zzdf("onDismissScreen must be called on the main UI thread.");
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzhd zzbuh;

            {
                this.zzbuh = r1;
            }

            public void run() {
                try {
                    this.zzbuh.zzbub.onAdClosed();
                } catch (Throwable e) {
                    zzb.zzd("Could not call onAdClosed.", e);
                }
            }
        });
    }

    public void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzdd("Adapter called onDismissScreen.");
        if (zzm.zzjr().zzvf()) {
            try {
                this.zzbub.onAdClosed();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClosed.", e);
                return;
            }
        }
        zzb.zzdf("onDismissScreen must be called on the main UI thread.");
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzhd zzbuh;

            {
                this.zzbuh = r1;
            }

            public void run() {
                try {
                    this.zzbuh.zzbub.onAdClosed();
                } catch (Throwable e) {
                    zzb.zzd("Could not call onAdClosed.", e);
                }
            }
        });
    }

    public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, final ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzb.zzdd(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error. ").append(valueOf).toString());
        if (zzm.zzjr().zzvf()) {
            try {
                this.zzbub.onAdFailedToLoad(zzhe.zza(errorCode));
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdFailedToLoad.", e);
                return;
            }
        }
        zzb.zzdf("onFailedToReceiveAd must be called on the main UI thread.");
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzhd zzbuh;

            public void run() {
                try {
                    this.zzbuh.zzbub.onAdFailedToLoad(zzhe.zza(errorCode));
                } catch (Throwable e) {
                    zzb.zzd("Could not call onAdFailedToLoad.", e);
                }
            }
        });
    }

    public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, final ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzb.zzdd(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error ").append(valueOf).append(".").toString());
        if (zzm.zzjr().zzvf()) {
            try {
                this.zzbub.onAdFailedToLoad(zzhe.zza(errorCode));
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdFailedToLoad.", e);
                return;
            }
        }
        zzb.zzdf("onFailedToReceiveAd must be called on the main UI thread.");
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzhd zzbuh;

            public void run() {
                try {
                    this.zzbuh.zzbub.onAdFailedToLoad(zzhe.zza(errorCode));
                } catch (Throwable e) {
                    zzb.zzd("Could not call onAdFailedToLoad.", e);
                }
            }
        });
    }

    public void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzdd("Adapter called onLeaveApplication.");
        if (zzm.zzjr().zzvf()) {
            try {
                this.zzbub.onAdLeftApplication();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLeftApplication.", e);
                return;
            }
        }
        zzb.zzdf("onLeaveApplication must be called on the main UI thread.");
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzhd zzbuh;

            {
                this.zzbuh = r1;
            }

            public void run() {
                try {
                    this.zzbuh.zzbub.onAdLeftApplication();
                } catch (Throwable e) {
                    zzb.zzd("Could not call onAdLeftApplication.", e);
                }
            }
        });
    }

    public void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzdd("Adapter called onLeaveApplication.");
        if (zzm.zzjr().zzvf()) {
            try {
                this.zzbub.onAdLeftApplication();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLeftApplication.", e);
                return;
            }
        }
        zzb.zzdf("onLeaveApplication must be called on the main UI thread.");
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzhd zzbuh;

            {
                this.zzbuh = r1;
            }

            public void run() {
                try {
                    this.zzbuh.zzbub.onAdLeftApplication();
                } catch (Throwable e) {
                    zzb.zzd("Could not call onAdLeftApplication.", e);
                }
            }
        });
    }

    public void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzdd("Adapter called onPresentScreen.");
        if (zzm.zzjr().zzvf()) {
            try {
                this.zzbub.onAdOpened();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdOpened.", e);
                return;
            }
        }
        zzb.zzdf("onPresentScreen must be called on the main UI thread.");
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzhd zzbuh;

            {
                this.zzbuh = r1;
            }

            public void run() {
                try {
                    this.zzbuh.zzbub.onAdOpened();
                } catch (Throwable e) {
                    zzb.zzd("Could not call onAdOpened.", e);
                }
            }
        });
    }

    public void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzdd("Adapter called onPresentScreen.");
        if (zzm.zzjr().zzvf()) {
            try {
                this.zzbub.onAdOpened();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdOpened.", e);
                return;
            }
        }
        zzb.zzdf("onPresentScreen must be called on the main UI thread.");
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzhd zzbuh;

            {
                this.zzbuh = r1;
            }

            public void run() {
                try {
                    this.zzbuh.zzbub.onAdOpened();
                } catch (Throwable e) {
                    zzb.zzd("Could not call onAdOpened.", e);
                }
            }
        });
    }

    public void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzdd("Adapter called onReceivedAd.");
        if (zzm.zzjr().zzvf()) {
            try {
                this.zzbub.onAdLoaded();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLoaded.", e);
                return;
            }
        }
        zzb.zzdf("onReceivedAd must be called on the main UI thread.");
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzhd zzbuh;

            {
                this.zzbuh = r1;
            }

            public void run() {
                try {
                    this.zzbuh.zzbub.onAdLoaded();
                } catch (Throwable e) {
                    zzb.zzd("Could not call onAdLoaded.", e);
                }
            }
        });
    }

    public void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzdd("Adapter called onReceivedAd.");
        if (zzm.zzjr().zzvf()) {
            try {
                this.zzbub.onAdLoaded();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLoaded.", e);
                return;
            }
        }
        zzb.zzdf("onReceivedAd must be called on the main UI thread.");
        zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzhd zzbuh;

            {
                this.zzbuh = r1;
            }

            public void run() {
                try {
                    this.zzbuh.zzbub.onAdLoaded();
                } catch (Throwable e) {
                    zzb.zzd("Could not call onAdLoaded.", e);
                }
            }
        });
    }
}
