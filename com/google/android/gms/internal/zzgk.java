package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgl.zza;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public class zzgk implements zza {
    private final Context mContext;
    private final Object zzakd = new Object();
    private final zzgq zzals;
    private final NativeAdOptionsParcel zzand;
    private final List<String> zzane;
    private final VersionInfoParcel zzanh;
    private AdRequestParcel zzaow;
    private final AdSizeParcel zzapc;
    private final boolean zzatk;
    private final boolean zzazd;
    private final String zzbst;
    private final long zzbsu;
    private final zzgh zzbsv;
    private final zzgg zzbsw;
    private zzgr zzbsx;
    private int zzbsy = -2;
    private zzgt zzbsz;

    class AnonymousClass2 extends zzgt.zza {
        final /* synthetic */ int zzbtc;

        AnonymousClass2(int i) {
            this.zzbtc = i;
        }

        public int zznk() throws RemoteException {
            return this.zzbtc;
        }
    }

    public zzgk(Context context, String str, zzgq com_google_android_gms_internal_zzgq, zzgh com_google_android_gms_internal_zzgh, zzgg com_google_android_gms_internal_zzgg, AdRequestParcel adRequestParcel, AdSizeParcel adSizeParcel, VersionInfoParcel versionInfoParcel, boolean z, boolean z2, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list) {
        this.mContext = context;
        this.zzals = com_google_android_gms_internal_zzgq;
        this.zzbsw = com_google_android_gms_internal_zzgg;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            this.zzbst = zznf();
        } else {
            this.zzbst = str;
        }
        this.zzbsv = com_google_android_gms_internal_zzgh;
        this.zzbsu = com_google_android_gms_internal_zzgh.zzbsc != -1 ? com_google_android_gms_internal_zzgh.zzbsc : 10000;
        this.zzaow = adRequestParcel;
        this.zzapc = adSizeParcel;
        this.zzanh = versionInfoParcel;
        this.zzatk = z;
        this.zzazd = z2;
        this.zzand = nativeAdOptionsParcel;
        this.zzane = list;
    }

    private long zza(long j, long j2, long j3, long j4) {
        while (this.zzbsy == -2) {
            zzb(j, j2, j3, j4);
        }
        return zzu.zzgf().elapsedRealtime() - j;
    }

    private void zza(zzgj com_google_android_gms_internal_zzgj) {
        String zzbn = zzbn(this.zzbsw.zzbrt);
        try {
            if (this.zzanh.zzctt < 4100000) {
                if (this.zzapc.zzaxj) {
                    this.zzbsx.zza(zze.zzac(this.mContext), this.zzaow, zzbn, com_google_android_gms_internal_zzgj);
                } else {
                    this.zzbsx.zza(zze.zzac(this.mContext), this.zzapc, this.zzaow, zzbn, (zzgs) com_google_android_gms_internal_zzgj);
                }
            } else if (this.zzatk) {
                this.zzbsx.zza(zze.zzac(this.mContext), this.zzaow, zzbn, this.zzbsw.zzbrl, com_google_android_gms_internal_zzgj, this.zzand, this.zzane);
            } else if (this.zzapc.zzaxj) {
                this.zzbsx.zza(zze.zzac(this.mContext), this.zzaow, zzbn, this.zzbsw.zzbrl, (zzgs) com_google_android_gms_internal_zzgj);
            } else if (!this.zzazd) {
                this.zzbsx.zza(zze.zzac(this.mContext), this.zzapc, this.zzaow, zzbn, this.zzbsw.zzbrl, com_google_android_gms_internal_zzgj);
            } else if (this.zzbsw.zzbrw != null) {
                this.zzbsx.zza(zze.zzac(this.mContext), this.zzaow, zzbn, this.zzbsw.zzbrl, com_google_android_gms_internal_zzgj, new NativeAdOptionsParcel(zzbo(this.zzbsw.zzbsa)), this.zzbsw.zzbrz);
            } else {
                this.zzbsx.zza(zze.zzac(this.mContext), this.zzapc, this.zzaow, zzbn, this.zzbsw.zzbrl, com_google_android_gms_internal_zzgj);
            }
        } catch (Throwable e) {
            zzb.zzd("Could not request ad from mediation adapter.", e);
            zzaa(5);
        }
    }

    private boolean zzab(int i) {
        try {
            Bundle zznp = this.zzatk ? this.zzbsx.zznp() : this.zzapc.zzaxj ? this.zzbsx.getInterstitialAdapterInfo() : this.zzbsx.zzno();
            if (zznp == null) {
                return false;
            }
            return (zznp.getInt("capabilities", 0) & i) == i;
        } catch (RemoteException e) {
            zzb.zzdf("Could not get adapter info. Returning false");
            return false;
        }
    }

    private static zzgt zzac(int i) {
        return new AnonymousClass2(i);
    }

    private void zzb(long j, long j2, long j3, long j4) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j5 = j2 - (elapsedRealtime - j);
        elapsedRealtime = j4 - (elapsedRealtime - j3);
        if (j5 <= 0 || elapsedRealtime <= 0) {
            zzb.zzde("Timed out waiting for adapter.");
            this.zzbsy = 3;
            return;
        }
        try {
            this.zzakd.wait(Math.min(j5, elapsedRealtime));
        } catch (InterruptedException e) {
            this.zzbsy = -1;
        }
    }

    private String zzbn(String str) {
        if (!(str == null || !zzni() || zzab(2))) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.remove("cpm_floor_cents");
                str = jSONObject.toString();
            } catch (JSONException e) {
                zzb.zzdf("Could not remove field. Returning the original value");
            }
        }
        return str;
    }

    private static NativeAdOptions zzbo(String str) {
        Builder builder = new Builder();
        if (str == null) {
            return builder.build();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            builder.setRequestMultipleImages(jSONObject.optBoolean("multiple_images", false));
            builder.setReturnUrlsForImageAssets(jSONObject.optBoolean("only_urls", false));
            builder.setImageOrientation(zzbp(jSONObject.optString("native_image_orientation", "any")));
        } catch (Throwable e) {
            zzb.zzd("Exception occurred when creating native ad options", e);
        }
        return builder.build();
    }

    private static int zzbp(String str) {
        return "landscape".equals(str) ? 2 : "portrait".equals(str) ? 1 : 0;
    }

    private String zznf() {
        try {
            if (!TextUtils.isEmpty(this.zzbsw.zzbrp)) {
                return this.zzals.zzbr(this.zzbsw.zzbrp) ? "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter" : "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        } catch (RemoteException e) {
            zzb.zzdf("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    private zzgt zzng() {
        if (this.zzbsy != 0 || !zzni()) {
            return null;
        }
        try {
            if (!(!zzab(4) || this.zzbsz == null || this.zzbsz.zznk() == 0)) {
                return this.zzbsz;
            }
        } catch (RemoteException e) {
            zzb.zzdf("Could not get cpm value from MediationResponseMetadata");
        }
        return zzac(zznj());
    }

    private zzgr zznh() {
        String str = "Instantiating mediation adapter: ";
        String valueOf = String.valueOf(this.zzbst);
        zzb.zzde(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        if (!this.zzatk) {
            if (((Boolean) zzdi.zzbei.get()).booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbst)) {
                return zza(new AdMobAdapter());
            }
            if (((Boolean) zzdi.zzbej.get()).booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.zzbst)) {
                return zza(new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzbst)) {
                return new zzgx(new zzhf());
            }
        }
        try {
            return this.zzals.zzbq(this.zzbst);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Could not instantiate mediation adapter: ";
            valueOf = String.valueOf(this.zzbst);
            zzb.zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            return null;
        }
    }

    private boolean zzni() {
        return this.zzbsv.zzbsm != -1;
    }

    private int zznj() {
        if (this.zzbsw.zzbrt == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.zzbsw.zzbrt);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzbst)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = zzab(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            return optInt == 0 ? jSONObject.optInt("penalized_average_cpm_cents", 0) : optInt;
        } catch (JSONException e) {
            zzb.zzdf("Could not convert to json. Returning 0");
            return 0;
        }
    }

    public void cancel() {
        synchronized (this.zzakd) {
            try {
                if (this.zzbsx != null) {
                    this.zzbsx.destroy();
                }
            } catch (Throwable e) {
                zzb.zzd("Could not destroy mediation adapter.", e);
            }
            this.zzbsy = -1;
            this.zzakd.notify();
        }
    }

    public zzgl zza(long j, long j2) {
        zzgl com_google_android_gms_internal_zzgl;
        synchronized (this.zzakd) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            final zzgj com_google_android_gms_internal_zzgj = new zzgj();
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzgk zzbtb;

                public void run() {
                    synchronized (this.zzbtb.zzakd) {
                        if (this.zzbtb.zzbsy != -2) {
                            return;
                        }
                        this.zzbtb.zzbsx = this.zzbtb.zznh();
                        if (this.zzbtb.zzbsx == null) {
                            this.zzbtb.zzaa(4);
                        } else if (!this.zzbtb.zzni() || this.zzbtb.zzab(1)) {
                            com_google_android_gms_internal_zzgj.zza(this.zzbtb);
                            this.zzbtb.zza(com_google_android_gms_internal_zzgj);
                        } else {
                            String zzf = this.zzbtb.zzbst;
                            zzb.zzdf(new StringBuilder(String.valueOf(zzf).length() + 56).append("Ignoring adapter ").append(zzf).append(" as delayed impression is not supported").toString());
                            this.zzbtb.zzaa(2);
                        }
                    }
                }
            });
            zzgj com_google_android_gms_internal_zzgj2 = com_google_android_gms_internal_zzgj;
            com_google_android_gms_internal_zzgl = new zzgl(this.zzbsw, this.zzbsx, this.zzbst, com_google_android_gms_internal_zzgj2, this.zzbsy, zzng(), zza(elapsedRealtime, this.zzbsu, j, j2));
        }
        return com_google_android_gms_internal_zzgl;
    }

    protected zzgr zza(MediationAdapter mediationAdapter) {
        return new zzgx(mediationAdapter);
    }

    public void zza(int i, zzgt com_google_android_gms_internal_zzgt) {
        synchronized (this.zzakd) {
            this.zzbsy = i;
            this.zzbsz = com_google_android_gms_internal_zzgt;
            this.zzakd.notify();
        }
    }

    public void zzaa(int i) {
        synchronized (this.zzakd) {
            this.zzbsy = i;
            this.zzakd.notify();
        }
    }
}
