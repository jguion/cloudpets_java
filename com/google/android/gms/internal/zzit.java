package com.google.android.gms.internal;

import android.content.Context;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzke.zza;
import com.google.android.gms.internal.zzlu.zzb;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

@zziy
public class zzit {
    private final Context mContext;
    private final Object zzakd = new Object();
    private final zzdq zzalg;
    private int zzary = -1;
    private int zzarz = -1;
    private zzlc zzasa;
    private final zzq zzbkj;
    private final zzau zzbkp;
    private final zza zzcck;
    private OnGlobalLayoutListener zzcew;
    private OnScrollChangedListener zzcex;

    public zzit(Context context, zzau com_google_android_gms_internal_zzau, zza com_google_android_gms_internal_zzke_zza, zzdq com_google_android_gms_internal_zzdq, zzq com_google_android_gms_ads_internal_zzq) {
        this.mContext = context;
        this.zzbkp = com_google_android_gms_internal_zzau;
        this.zzcck = com_google_android_gms_internal_zzke_zza;
        this.zzalg = com_google_android_gms_internal_zzdq;
        this.zzbkj = com_google_android_gms_ads_internal_zzq;
        this.zzasa = new zzlc(200);
    }

    private OnGlobalLayoutListener zza(final WeakReference<zzlt> weakReference) {
        if (this.zzcew == null) {
            this.zzcew = new OnGlobalLayoutListener(this) {
                final /* synthetic */ zzit zzcfa;

                public void onGlobalLayout() {
                    this.zzcfa.zza(weakReference, false);
                }
            };
        }
        return this.zzcew;
    }

    private void zza(WeakReference<zzlt> weakReference, boolean z) {
        if (weakReference != null) {
            zzlt com_google_android_gms_internal_zzlt = (zzlt) weakReference.get();
            if (com_google_android_gms_internal_zzlt != null && com_google_android_gms_internal_zzlt.getView() != null) {
                if (!z || this.zzasa.tryAcquire()) {
                    int[] iArr = new int[2];
                    com_google_android_gms_internal_zzlt.getView().getLocationOnScreen(iArr);
                    int zzc = zzm.zzjr().zzc(this.mContext, iArr[0]);
                    int zzc2 = zzm.zzjr().zzc(this.mContext, iArr[1]);
                    synchronized (this.zzakd) {
                        if (!(this.zzary == zzc && this.zzarz == zzc2)) {
                            this.zzary = zzc;
                            this.zzarz = zzc2;
                            com_google_android_gms_internal_zzlt.zzvr().zza(this.zzary, this.zzarz, !z);
                        }
                    }
                }
            }
        }
    }

    private OnScrollChangedListener zzb(final WeakReference<zzlt> weakReference) {
        if (this.zzcex == null) {
            this.zzcex = new OnScrollChangedListener(this) {
                final /* synthetic */ zzit zzcfa;

                public void onScrollChanged() {
                    this.zzcfa.zza(weakReference, true);
                }
            };
        }
        return this.zzcex;
    }

    private void zzj(zzlt com_google_android_gms_internal_zzlt) {
        zzlu zzvr = com_google_android_gms_internal_zzlt.zzvr();
        zzvr.zza("/video", zzeu.zzbmo);
        zzvr.zza("/videoMeta", zzeu.zzbmp);
        zzvr.zza("/precache", zzeu.zzbmq);
        zzvr.zza("/delayPageLoaded", zzeu.zzbmt);
        zzvr.zza("/instrument", zzeu.zzbmr);
        zzvr.zza("/log", zzeu.zzbmj);
        zzvr.zza("/videoClicked", zzeu.zzbmk);
        zzvr.zza("/trackActiveViewUnit", new zzev(this) {
            final /* synthetic */ zzit zzcfa;

            {
                this.zzcfa = r1;
            }

            public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
                this.zzcfa.zzbkj.zzfh();
            }
        });
    }

    public zzlj<zzlt> zzh(final JSONObject jSONObject) {
        final zzlj com_google_android_gms_internal_zzlg = new zzlg();
        zzu.zzfz().runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzit zzcfa;

            public void run() {
                try {
                    final zzlt zzrt = this.zzcfa.zzrt();
                    this.zzcfa.zzbkj.zzc(zzrt);
                    WeakReference weakReference = new WeakReference(zzrt);
                    zzrt.zzvr().zza(this.zzcfa.zza(weakReference), this.zzcfa.zzb(weakReference));
                    this.zzcfa.zzj(zzrt);
                    zzrt.zzvr().zza(new zzb(this) {
                        final /* synthetic */ AnonymousClass1 zzcfb;

                        public void zzk(zzlt com_google_android_gms_internal_zzlt) {
                            zzrt.zza("google.afma.nativeAds.renderVideo", jSONObject);
                        }
                    });
                    zzrt.zzvr().zza(new zzlu.zza(this) {
                        final /* synthetic */ AnonymousClass1 zzcfb;

                        {
                            this.zzcfb = r1;
                        }

                        public void zza(zzlt com_google_android_gms_internal_zzlt, boolean z) {
                            this.zzcfb.zzcfa.zzbkj.zzfk();
                            com_google_android_gms_internal_zzlg.zzh(com_google_android_gms_internal_zzlt);
                        }
                    });
                    zzrt.loadUrl(zzir.zza(this.zzcfa.zzcck, (String) zzdi.zzbfw.get()));
                } catch (Throwable e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Exception occurred while getting video view", e);
                    com_google_android_gms_internal_zzlg.zzh(null);
                }
            }
        });
        return com_google_android_gms_internal_zzlg;
    }

    zzlt zzrt() {
        return zzu.zzga().zza(this.mContext, AdSizeParcel.zzk(this.mContext), false, false, this.zzbkp, this.zzcck.zzcix.zzaqv, this.zzalg, null, this.zzbkj.zzdp());
    }
}
