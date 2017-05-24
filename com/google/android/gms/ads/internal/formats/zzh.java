package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.ads.internal.formats.zzi.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzau;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.internal.zzgv;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzlt;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

@zziy
public class zzh extends zzj {
    private Object zzakd;
    @Nullable
    private zzgu zzbkh;
    @Nullable
    private zzgv zzbki;
    private final zzq zzbkj;
    @Nullable
    private zzi zzbkk;
    private boolean zzbkl;

    private zzh(Context context, zzq com_google_android_gms_ads_internal_zzq, zzau com_google_android_gms_internal_zzau, zza com_google_android_gms_ads_internal_formats_zzi_zza) {
        super(context, com_google_android_gms_ads_internal_zzq, null, com_google_android_gms_internal_zzau, null, com_google_android_gms_ads_internal_formats_zzi_zza, null, null);
        this.zzbkl = false;
        this.zzakd = new Object();
        this.zzbkj = com_google_android_gms_ads_internal_zzq;
    }

    public zzh(Context context, zzq com_google_android_gms_ads_internal_zzq, zzau com_google_android_gms_internal_zzau, zzgu com_google_android_gms_internal_zzgu, zza com_google_android_gms_ads_internal_formats_zzi_zza) {
        this(context, com_google_android_gms_ads_internal_zzq, com_google_android_gms_internal_zzau, com_google_android_gms_ads_internal_formats_zzi_zza);
        this.zzbkh = com_google_android_gms_internal_zzgu;
    }

    public zzh(Context context, zzq com_google_android_gms_ads_internal_zzq, zzau com_google_android_gms_internal_zzau, zzgv com_google_android_gms_internal_zzgv, zza com_google_android_gms_ads_internal_formats_zzi_zza) {
        this(context, com_google_android_gms_ads_internal_zzq, com_google_android_gms_internal_zzau, com_google_android_gms_ads_internal_formats_zzi_zza);
        this.zzbki = com_google_android_gms_internal_zzgv;
    }

    public void recordImpression() {
        zzac.zzhq("recordImpression must be called on the main UI thread.");
        synchronized (this.zzakd) {
            zzr(true);
            if (this.zzbkk != null) {
                this.zzbkk.recordImpression();
                this.zzbkj.recordImpression();
            } else {
                try {
                    if (this.zzbkh != null && !this.zzbkh.getOverrideImpressionRecording()) {
                        this.zzbkh.recordImpression();
                        this.zzbkj.recordImpression();
                    } else if (!(this.zzbki == null || this.zzbki.getOverrideImpressionRecording())) {
                        this.zzbki.recordImpression();
                        this.zzbkj.recordImpression();
                    }
                } catch (Throwable e) {
                    zzb.zzd("Failed to call recordImpression", e);
                }
            }
        }
    }

    @Nullable
    public zzb zza(OnClickListener onClickListener) {
        return null;
    }

    public void zza(View view, Map<String, WeakReference<View>> map, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        synchronized (this.zzakd) {
            this.zzbkl = true;
            try {
                if (this.zzbkh != null) {
                    this.zzbkh.zzl(zze.zzac(view));
                } else if (this.zzbki != null) {
                    this.zzbki.zzl(zze.zzac(view));
                }
            } catch (Throwable e) {
                zzb.zzd("Failed to call prepareAd", e);
            }
            this.zzbkl = false;
        }
    }

    public void zza(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        zzac.zzhq("performClick must be called on the main UI thread.");
        synchronized (this.zzakd) {
            if (this.zzbkk != null) {
                this.zzbkk.zza(view, (Map) map, jSONObject, jSONObject2, jSONObject3);
                this.zzbkj.onAdClicked();
            } else {
                try {
                    if (!(this.zzbkh == null || this.zzbkh.getOverrideClickHandling())) {
                        this.zzbkh.zzk(zze.zzac(view));
                        this.zzbkj.onAdClicked();
                    }
                    if (!(this.zzbki == null || this.zzbki.getOverrideClickHandling())) {
                        this.zzbki.zzk(zze.zzac(view));
                        this.zzbkj.onAdClicked();
                    }
                } catch (Throwable e) {
                    zzb.zzd("Failed to call performClick", e);
                }
            }
        }
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.zzakd) {
            try {
                if (this.zzbkh != null) {
                    this.zzbkh.zzm(zze.zzac(view));
                } else if (this.zzbki != null) {
                    this.zzbki.zzm(zze.zzac(view));
                }
            } catch (Throwable e) {
                zzb.zzd("Failed to call untrackView", e);
            }
        }
    }

    public void zzc(@Nullable zzi com_google_android_gms_ads_internal_formats_zzi) {
        synchronized (this.zzakd) {
            this.zzbkk = com_google_android_gms_ads_internal_formats_zzi;
        }
    }

    public boolean zzlv() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzbkl;
        }
        return z;
    }

    public zzi zzlw() {
        zzi com_google_android_gms_ads_internal_formats_zzi;
        synchronized (this.zzakd) {
            com_google_android_gms_ads_internal_formats_zzi = this.zzbkk;
        }
        return com_google_android_gms_ads_internal_formats_zzi;
    }

    @Nullable
    public zzlt zzlx() {
        return null;
    }
}
