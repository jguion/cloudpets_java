package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.zzu;
import java.util.LinkedList;
import java.util.List;

@zziy
class zzfo {
    private final List<zza> zzamv = new LinkedList();

    interface zza {
        void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException;
    }

    zzfo() {
    }

    void zza(final zzfp com_google_android_gms_internal_zzfp) {
        Handler handler = zzkr.zzcrf;
        for (final zza com_google_android_gms_internal_zzfo_zza : this.zzamv) {
            handler.post(new Runnable(this) {
                final /* synthetic */ zzfo zzbok;

                public void run() {
                    try {
                        com_google_android_gms_internal_zzfo_zza.zzb(com_google_android_gms_internal_zzfp);
                    } catch (Throwable e) {
                        zzb.zzd("Could not propagate interstitial ad event.", e);
                    }
                }
            });
        }
        this.zzamv.clear();
    }

    void zzc(zzl com_google_android_gms_ads_internal_zzl) {
        com_google_android_gms_ads_internal_zzl.zza(new com.google.android.gms.ads.internal.client.zzq.zza(this) {
            final /* synthetic */ zzfo zzbok;

            {
                this.zzbok = r1;
            }

            public void onAdClosed() throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass1 zzbol;

                    {
                        this.zzbol = r1;
                    }

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzamy != null) {
                            com_google_android_gms_internal_zzfp.zzamy.onAdClosed();
                        }
                        zzu.zzgo().zzmm();
                    }
                });
            }

            public void onAdFailedToLoad(final int i) throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass1 zzbol;

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzamy != null) {
                            com_google_android_gms_internal_zzfp.zzamy.onAdFailedToLoad(i);
                        }
                    }
                });
                zzkn.v("Pooled interstitial failed to load.");
            }

            public void onAdLeftApplication() throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass1 zzbol;

                    {
                        this.zzbol = r1;
                    }

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzamy != null) {
                            com_google_android_gms_internal_zzfp.zzamy.onAdLeftApplication();
                        }
                    }
                });
            }

            public void onAdLoaded() throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass1 zzbol;

                    {
                        this.zzbol = r1;
                    }

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzamy != null) {
                            com_google_android_gms_internal_zzfp.zzamy.onAdLoaded();
                        }
                    }
                });
                zzkn.v("Pooled interstitial loaded.");
            }

            public void onAdOpened() throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass1 zzbol;

                    {
                        this.zzbol = r1;
                    }

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzamy != null) {
                            com_google_android_gms_internal_zzfp.zzamy.onAdOpened();
                        }
                    }
                });
            }
        });
        com_google_android_gms_ads_internal_zzl.zza(new com.google.android.gms.ads.internal.client.zzw.zza(this) {
            final /* synthetic */ zzfo zzbok;

            {
                this.zzbok = r1;
            }

            public void onAppEvent(final String str, final String str2) throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass2 zzboo;

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzboy != null) {
                            com_google_android_gms_internal_zzfp.zzboy.onAppEvent(str, str2);
                        }
                    }
                });
            }
        });
        com_google_android_gms_ads_internal_zzl.zza(new com.google.android.gms.internal.zzhx.zza(this) {
            final /* synthetic */ zzfo zzbok;

            {
                this.zzbok = r1;
            }

            public void zza(final zzhw com_google_android_gms_internal_zzhw) throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass3 zzboq;

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzboz != null) {
                            com_google_android_gms_internal_zzfp.zzboz.zza(com_google_android_gms_internal_zzhw);
                        }
                    }
                });
            }
        });
        com_google_android_gms_ads_internal_zzl.zza(new com.google.android.gms.internal.zzdu.zza(this) {
            final /* synthetic */ zzfo zzbok;

            {
                this.zzbok = r1;
            }

            public void zza(final zzdt com_google_android_gms_internal_zzdt) throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass4 zzbos;

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzbpa != null) {
                            com_google_android_gms_internal_zzfp.zzbpa.zza(com_google_android_gms_internal_zzdt);
                        }
                    }
                });
            }
        });
        com_google_android_gms_ads_internal_zzl.zza(new com.google.android.gms.ads.internal.client.zzp.zza(this) {
            final /* synthetic */ zzfo zzbok;

            {
                this.zzbok = r1;
            }

            public void onAdClicked() throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass5 zzbot;

                    {
                        this.zzbot = r1;
                    }

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzbpb != null) {
                            com_google_android_gms_internal_zzfp.zzbpb.onAdClicked();
                        }
                    }
                });
            }
        });
        com_google_android_gms_ads_internal_zzl.zza(new com.google.android.gms.ads.internal.reward.client.zzd.zza(this) {
            final /* synthetic */ zzfo zzbok;

            {
                this.zzbok = r1;
            }

            public void onRewardedVideoAdClosed() throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass6 zzbou;

                    {
                        this.zzbou = r1;
                    }

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzbpc != null) {
                            com_google_android_gms_internal_zzfp.zzbpc.onRewardedVideoAdClosed();
                        }
                    }
                });
            }

            public void onRewardedVideoAdFailedToLoad(final int i) throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass6 zzbou;

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzbpc != null) {
                            com_google_android_gms_internal_zzfp.zzbpc.onRewardedVideoAdFailedToLoad(i);
                        }
                    }
                });
            }

            public void onRewardedVideoAdLeftApplication() throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass6 zzbou;

                    {
                        this.zzbou = r1;
                    }

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzbpc != null) {
                            com_google_android_gms_internal_zzfp.zzbpc.onRewardedVideoAdLeftApplication();
                        }
                    }
                });
            }

            public void onRewardedVideoAdLoaded() throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass6 zzbou;

                    {
                        this.zzbou = r1;
                    }

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzbpc != null) {
                            com_google_android_gms_internal_zzfp.zzbpc.onRewardedVideoAdLoaded();
                        }
                    }
                });
            }

            public void onRewardedVideoAdOpened() throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass6 zzbou;

                    {
                        this.zzbou = r1;
                    }

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzbpc != null) {
                            com_google_android_gms_internal_zzfp.zzbpc.onRewardedVideoAdOpened();
                        }
                    }
                });
            }

            public void onRewardedVideoStarted() throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass6 zzbou;

                    {
                        this.zzbou = r1;
                    }

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzbpc != null) {
                            com_google_android_gms_internal_zzfp.zzbpc.onRewardedVideoStarted();
                        }
                    }
                });
            }

            public void zza(final com.google.android.gms.ads.internal.reward.client.zza com_google_android_gms_ads_internal_reward_client_zza) throws RemoteException {
                this.zzbok.zzamv.add(new zza(this) {
                    final /* synthetic */ AnonymousClass6 zzbou;

                    public void zzb(zzfp com_google_android_gms_internal_zzfp) throws RemoteException {
                        if (com_google_android_gms_internal_zzfp.zzbpc != null) {
                            com_google_android_gms_internal_zzfp.zzbpc.zza(com_google_android_gms_ads_internal_reward_client_zza);
                        }
                    }
                });
            }
        });
    }
}
