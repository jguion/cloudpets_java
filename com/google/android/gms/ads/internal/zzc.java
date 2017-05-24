package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zzdr;
import com.google.android.gms.internal.zzdt;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zzhn;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzke.zza;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzlt;
import java.util.Map;

@zziy
public abstract class zzc extends zzb implements zzh, zzhn {
    public zzc(Context context, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzgq, versionInfoParcel, com_google_android_gms_ads_internal_zzd);
    }

    protected zzlt zza(zza com_google_android_gms_internal_zzke_zza, @Nullable zze com_google_android_gms_ads_internal_zze, @Nullable com.google.android.gms.ads.internal.safebrowsing.zzc com_google_android_gms_ads_internal_safebrowsing_zzc) {
        zzlt com_google_android_gms_internal_zzlt = null;
        View nextView = this.zzall.zzaqw.getNextView();
        if (nextView instanceof zzlt) {
            com_google_android_gms_internal_zzlt = (zzlt) nextView;
            if (((Boolean) zzdi.zzbcv.get()).booleanValue()) {
                zzb.zzdd("Reusing webview...");
                com_google_android_gms_internal_zzlt.zza(this.zzall.zzahn, this.zzall.zzaqz, this.zzalg);
            } else {
                com_google_android_gms_internal_zzlt.destroy();
                com_google_android_gms_internal_zzlt = null;
            }
        }
        if (com_google_android_gms_internal_zzlt == null) {
            if (nextView != null) {
                this.zzall.zzaqw.removeView(nextView);
            }
            com_google_android_gms_internal_zzlt = zzu.zzga().zza(this.zzall.zzahn, this.zzall.zzaqz, false, false, this.zzall.zzaqu, this.zzall.zzaqv, this.zzalg, this, this.zzalo);
            if (this.zzall.zzaqz.zzaxk == null) {
                zzb(com_google_android_gms_internal_zzlt.getView());
            }
        }
        zzfz com_google_android_gms_internal_zzfz = com_google_android_gms_internal_zzlt;
        com_google_android_gms_internal_zzfz.zzvr().zza(this, this, this, this, false, this, null, com_google_android_gms_ads_internal_zze, this, com_google_android_gms_ads_internal_safebrowsing_zzc);
        zza(com_google_android_gms_internal_zzfz);
        com_google_android_gms_internal_zzfz.zzdh(com_google_android_gms_internal_zzke_zza.zzcix.zzcgj);
        return com_google_android_gms_internal_zzfz;
    }

    public void zza(int i, int i2, int i3, int i4) {
        zzdz();
    }

    public void zza(zzdu com_google_android_gms_internal_zzdu) {
        zzac.zzhq("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
        this.zzall.zzarp = com_google_android_gms_internal_zzdu;
    }

    protected void zza(zzfz com_google_android_gms_internal_zzfz) {
        com_google_android_gms_internal_zzfz.zza("/trackActiveViewUnit", new zzev(this) {
            final /* synthetic */ zzc zzalw;

            {
                this.zzalw = r1;
            }

            public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
                if (this.zzalw.zzall.zzara != null) {
                    this.zzalw.zzaln.zza(this.zzalw.zzall.zzaqz, this.zzalw.zzall.zzara, com_google_android_gms_internal_zzlt.getView(), (zzfz) com_google_android_gms_internal_zzlt);
                } else {
                    zzb.zzdf("Request to enable ActiveView before adState is available.");
                }
            }
        });
    }

    protected void zza(final zza com_google_android_gms_internal_zzke_zza, final zzdq com_google_android_gms_internal_zzdq) {
        if (com_google_android_gms_internal_zzke_zza.errorCode != -2) {
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzc zzalw;

                public void run() {
                    this.zzalw.zzb(new zzke(com_google_android_gms_internal_zzke_zza, null, null, null, null, null, null, null));
                }
            });
            return;
        }
        if (com_google_android_gms_internal_zzke_zza.zzaqz != null) {
            this.zzall.zzaqz = com_google_android_gms_internal_zzke_zza.zzaqz;
        }
        if (!com_google_android_gms_internal_zzke_zza.zzcop.zzchc || com_google_android_gms_internal_zzke_zza.zzcop.zzaxn) {
            final com.google.android.gms.ads.internal.safebrowsing.zzc zza = this.zzalo.zzamf.zza(this.zzall.zzahn, com_google_android_gms_internal_zzke_zza.zzcop);
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzc zzalw;

                public void run() {
                    if (com_google_android_gms_internal_zzke_zza.zzcop.zzchl && this.zzalw.zzall.zzarp != null) {
                        String str = null;
                        if (com_google_android_gms_internal_zzke_zza.zzcop.zzbyj != null) {
                            str = zzu.zzfz().zzcv(com_google_android_gms_internal_zzke_zza.zzcop.zzbyj);
                        }
                        zzdt com_google_android_gms_internal_zzdr = new zzdr(this.zzalw, str, com_google_android_gms_internal_zzke_zza.zzcop.body);
                        this.zzalw.zzall.zzarv = 1;
                        try {
                            this.zzalw.zzalj = false;
                            this.zzalw.zzall.zzarp.zza(com_google_android_gms_internal_zzdr);
                            return;
                        } catch (Throwable e) {
                            zzb.zzd("Could not call the onCustomRenderedAdLoadedListener.", e);
                            this.zzalw.zzalj = true;
                        }
                    }
                    final zze com_google_android_gms_ads_internal_zze = new zze(this.zzalw.zzall.zzahn, com_google_android_gms_internal_zzke_zza);
                    zzlt zza = this.zzalw.zza(com_google_android_gms_internal_zzke_zza, com_google_android_gms_ads_internal_zze, zza);
                    zza.setOnTouchListener(new OnTouchListener(this) {
                        final /* synthetic */ AnonymousClass3 zzamb;

                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            com_google_android_gms_ads_internal_zze.recordClick();
                            return false;
                        }
                    });
                    zza.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass3 zzamb;

                        public void onClick(View view) {
                            com_google_android_gms_ads_internal_zze.recordClick();
                        }
                    });
                    this.zzalw.zzall.zzarv = 0;
                    this.zzalw.zzall.zzaqy = zzu.zzfy().zza(this.zzalw.zzall.zzahn, this.zzalw, com_google_android_gms_internal_zzke_zza, this.zzalw.zzall.zzaqu, zza, this.zzalw.zzals, this.zzalw, com_google_android_gms_internal_zzdq);
                }
            });
            return;
        }
        this.zzall.zzarv = 0;
        this.zzall.zzaqy = zzu.zzfy().zza(this.zzall.zzahn, this, com_google_android_gms_internal_zzke_zza, this.zzall.zzaqu, null, this.zzals, this, com_google_android_gms_internal_zzdq);
    }

    protected boolean zza(@Nullable zzke com_google_android_gms_internal_zzke, zzke com_google_android_gms_internal_zzke2) {
        if (this.zzall.zzhc() && this.zzall.zzaqw != null) {
            this.zzall.zzaqw.zzhi().zzcz(com_google_android_gms_internal_zzke2.zzchh);
        }
        return super.zza(com_google_android_gms_internal_zzke, com_google_android_gms_internal_zzke2);
    }

    public void zzc(View view) {
        this.zzall.zzaru = view;
        zzb(new zzke(this.zzall.zzarb, null, null, null, null, null, null, null));
    }

    public void zzen() {
        onAdClicked();
    }

    public void zzeo() {
        recordImpression();
        zzdv();
    }

    public void zzep() {
        zzdx();
    }
}
