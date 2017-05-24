package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.internal.zzlu.zzc;
import com.google.android.gms.internal.zzlu.zze;
import com.google.android.gms.internal.zzly;
import java.util.List;

@zziy
public class zzf extends zzc implements OnGlobalLayoutListener, OnScrollChangedListener {
    private boolean zzami;

    public class zza {
        final /* synthetic */ zzf zzamj;

        public zza(zzf com_google_android_gms_ads_internal_zzf) {
            this.zzamj = com_google_android_gms_ads_internal_zzf;
        }

        public void onClick() {
            this.zzamj.onAdClicked();
        }
    }

    public zzf(Context context, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzgq, versionInfoParcel, com_google_android_gms_ads_internal_zzd);
    }

    private AdSizeParcel zzb(com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza) {
        if (com_google_android_gms_internal_zzke_zza.zzcop.zzaxm) {
            return this.zzall.zzaqz;
        }
        AdSize adSize;
        String str = com_google_android_gms_internal_zzke_zza.zzcop.zzchf;
        if (str != null) {
            String[] split = str.split("[xX]");
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            adSize = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        } else {
            adSize = this.zzall.zzaqz.zzjd();
        }
        return new AdSizeParcel(this.zzall.zzahn, adSize);
    }

    private boolean zzb(@Nullable zzke com_google_android_gms_internal_zzke, zzke com_google_android_gms_internal_zzke2) {
        if (com_google_android_gms_internal_zzke2.zzchc) {
            View zzg = zzn.zzg(com_google_android_gms_internal_zzke2);
            if (zzg == null) {
                zzb.zzdf("Could not get mediation view");
                return false;
            }
            View nextView = this.zzall.zzaqw.getNextView();
            if (nextView != null) {
                if (nextView instanceof zzlt) {
                    ((zzlt) nextView).destroy();
                }
                this.zzall.zzaqw.removeView(nextView);
            }
            if (!zzn.zzh(com_google_android_gms_internal_zzke2)) {
                try {
                    zzb(zzg);
                } catch (Throwable th) {
                    zzb.zzd("Could not add mediation view to view hierarchy.", th);
                    return false;
                }
            }
        } else if (!(com_google_android_gms_internal_zzke2.zzcoh == null || com_google_android_gms_internal_zzke2.zzbyh == null)) {
            com_google_android_gms_internal_zzke2.zzbyh.zza(com_google_android_gms_internal_zzke2.zzcoh);
            this.zzall.zzaqw.removeAllViews();
            this.zzall.zzaqw.setMinimumWidth(com_google_android_gms_internal_zzke2.zzcoh.widthPixels);
            this.zzall.zzaqw.setMinimumHeight(com_google_android_gms_internal_zzke2.zzcoh.heightPixels);
            zzb(com_google_android_gms_internal_zzke2.zzbyh.getView());
        }
        if (this.zzall.zzaqw.getChildCount() > 1) {
            this.zzall.zzaqw.showNext();
        }
        if (com_google_android_gms_internal_zzke != null) {
            View nextView2 = this.zzall.zzaqw.getNextView();
            if (nextView2 instanceof zzlt) {
                ((zzlt) nextView2).zza(this.zzall.zzahn, this.zzall.zzaqz, this.zzalg);
            } else if (nextView2 != null) {
                this.zzall.zzaqw.removeView(nextView2);
            }
            this.zzall.zzhb();
        }
        this.zzall.zzaqw.setVisibility(0);
        return true;
    }

    private void zze(final zzke com_google_android_gms_internal_zzke) {
        if (this.zzall.zzhc()) {
            if (com_google_android_gms_internal_zzke.zzbyh != null) {
                if (com_google_android_gms_internal_zzke.zzcod != null) {
                    this.zzaln.zza(this.zzall.zzaqz, com_google_android_gms_internal_zzke);
                }
                if (com_google_android_gms_internal_zzke.zzib()) {
                    this.zzaln.zza(this.zzall.zzaqz, com_google_android_gms_internal_zzke).zza(com_google_android_gms_internal_zzke.zzbyh);
                } else {
                    com_google_android_gms_internal_zzke.zzbyh.zzvr().zza(new zzc(this) {
                        final /* synthetic */ zzf zzamj;

                        public void zzet() {
                            this.zzamj.zzaln.zza(this.zzamj.zzall.zzaqz, com_google_android_gms_internal_zzke).zza(com_google_android_gms_internal_zzke.zzbyh);
                        }
                    });
                }
            }
        } else if (this.zzall.zzaru != null && com_google_android_gms_internal_zzke.zzcod != null) {
            this.zzaln.zza(this.zzall.zzaqz, com_google_android_gms_internal_zzke, this.zzall.zzaru);
        }
    }

    public void onGlobalLayout() {
        zzf(this.zzall.zzara);
    }

    public void onScrollChanged() {
        zzf(this.zzall.zzara);
    }

    public void setManualImpressionsEnabled(boolean z) {
        zzac.zzhq("setManualImpressionsEnabled must be called from the main thread.");
        this.zzami = z;
    }

    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    protected zzlt zza(com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, @Nullable zze com_google_android_gms_ads_internal_zze, @Nullable com.google.android.gms.ads.internal.safebrowsing.zzc com_google_android_gms_ads_internal_safebrowsing_zzc) {
        if (this.zzall.zzaqz.zzaxk == null && this.zzall.zzaqz.zzaxm) {
            this.zzall.zzaqz = zzb(com_google_android_gms_internal_zzke_zza);
        }
        return super.zza(com_google_android_gms_internal_zzke_zza, com_google_android_gms_ads_internal_zze, com_google_android_gms_ads_internal_safebrowsing_zzc);
    }

    protected void zza(@Nullable zzke com_google_android_gms_internal_zzke, boolean z) {
        super.zza(com_google_android_gms_internal_zzke, z);
        if (zzn.zzh(com_google_android_gms_internal_zzke)) {
            zzn.zza(com_google_android_gms_internal_zzke, new zza(this));
        }
    }

    public boolean zza(@Nullable zzke com_google_android_gms_internal_zzke, final zzke com_google_android_gms_internal_zzke2) {
        if (!super.zza(com_google_android_gms_internal_zzke, com_google_android_gms_internal_zzke2)) {
            return false;
        }
        if (!this.zzall.zzhc() || zzb(com_google_android_gms_internal_zzke, com_google_android_gms_internal_zzke2)) {
            zzly zzwb;
            if (com_google_android_gms_internal_zzke2.zzchu) {
                zzf(com_google_android_gms_internal_zzke2);
                zzu.zzgx().zza(this.zzall.zzaqw, (OnGlobalLayoutListener) this);
                zzu.zzgx().zza(this.zzall.zzaqw, (OnScrollChangedListener) this);
                if (!com_google_android_gms_internal_zzke2.zzcoe) {
                    final Runnable anonymousClass1 = new Runnable(this) {
                        final /* synthetic */ zzf zzamj;

                        {
                            this.zzamj = r1;
                        }

                        public void run() {
                            this.zzamj.zzf(this.zzamj.zzall.zzara);
                        }
                    };
                    zzlu zzvr = com_google_android_gms_internal_zzke2.zzbyh != null ? com_google_android_gms_internal_zzke2.zzbyh.zzvr() : null;
                    if (zzvr != null) {
                        zzvr.zza(new zze(this) {
                            final /* synthetic */ zzf zzamj;

                            public void zzes() {
                                if (!com_google_android_gms_internal_zzke2.zzcoe) {
                                    zzu.zzfz();
                                    zzkr.zzb(anonymousClass1);
                                }
                            }
                        });
                    }
                }
            } else if (!this.zzall.zzhd() || ((Boolean) zzdi.zzbfu.get()).booleanValue()) {
                zza(com_google_android_gms_internal_zzke2, false);
            }
            if (com_google_android_gms_internal_zzke2.zzbyh != null) {
                zzwb = com_google_android_gms_internal_zzke2.zzbyh.zzwb();
                zzlu zzvr2 = com_google_android_gms_internal_zzke2.zzbyh.zzvr();
                if (zzvr2 != null) {
                    zzvr2.zzwo();
                }
            } else {
                zzwb = null;
            }
            if (!(this.zzall.zzaro == null || zzwb == null)) {
                zzwb.zzap(this.zzall.zzaro.zzbac);
            }
            zze(com_google_android_gms_internal_zzke2);
            return true;
        }
        zzh(0);
        return false;
    }

    public boolean zzb(AdRequestParcel adRequestParcel) {
        return super.zzb(zze(adRequestParcel));
    }

    @Nullable
    public zzab zzdw() {
        zzac.zzhq("getVideoController must be called from the main thread.");
        return (this.zzall.zzara == null || this.zzall.zzara.zzbyh == null) ? null : this.zzall.zzara.zzbyh.zzwb();
    }

    AdRequestParcel zze(AdRequestParcel adRequestParcel) {
        if (adRequestParcel.zzawi == this.zzami) {
            return adRequestParcel;
        }
        int i = adRequestParcel.versionCode;
        long j = adRequestParcel.zzawd;
        Bundle bundle = adRequestParcel.extras;
        int i2 = adRequestParcel.zzawe;
        List list = adRequestParcel.zzawf;
        boolean z = adRequestParcel.zzawg;
        int i3 = adRequestParcel.zzawh;
        boolean z2 = adRequestParcel.zzawi || this.zzami;
        return new AdRequestParcel(i, j, bundle, i2, list, z, i3, z2, adRequestParcel.zzawj, adRequestParcel.zzawk, adRequestParcel.zzawl, adRequestParcel.zzawm, adRequestParcel.zzawn, adRequestParcel.zzawo, adRequestParcel.zzawp, adRequestParcel.zzawq, adRequestParcel.zzawr, adRequestParcel.zzaws);
    }

    protected boolean zzec() {
        boolean z = true;
        if (!zzu.zzfz().zza(this.zzall.zzahn.getPackageManager(), this.zzall.zzahn.getPackageName(), "android.permission.INTERNET")) {
            zzm.zzjr().zza(this.zzall.zzaqw, this.zzall.zzaqz, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        }
        if (!zzu.zzfz().zzac(this.zzall.zzahn)) {
            zzm.zzjr().zza(this.zzall.zzaqw, this.zzall.zzaqz, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!(z || this.zzall.zzaqw == null)) {
            this.zzall.zzaqw.setVisibility(0);
        }
        return z;
    }

    void zzf(@Nullable zzke com_google_android_gms_internal_zzke) {
        if (com_google_android_gms_internal_zzke != null && !com_google_android_gms_internal_zzke.zzcoe && this.zzall.zzaqw != null && zzu.zzfz().zza(this.zzall.zzaqw, this.zzall.zzahn) && this.zzall.zzaqw.getGlobalVisibleRect(new Rect(), null)) {
            if (!(com_google_android_gms_internal_zzke == null || com_google_android_gms_internal_zzke.zzbyh == null || com_google_android_gms_internal_zzke.zzbyh.zzvr() == null)) {
                com_google_android_gms_internal_zzke.zzbyh.zzvr().zza(null);
            }
            zza(com_google_android_gms_internal_zzke, false);
            com_google_android_gms_internal_zzke.zzcoe = true;
        }
    }
}
