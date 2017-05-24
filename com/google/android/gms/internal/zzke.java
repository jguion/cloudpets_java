package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@zziy
public class zzke {
    public final int errorCode;
    public final int orientation;
    public final List<String> zzbsd;
    public final List<String> zzbse;
    @Nullable
    public final List<String> zzbsg;
    public final long zzbsj;
    @Nullable
    public final zzgg zzbte;
    @Nullable
    public final zzgr zzbtf;
    @Nullable
    public final String zzbtg;
    @Nullable
    public final zzgj zzbth;
    @Nullable
    public final zzlt zzbyh;
    public final AdRequestParcel zzcfu;
    public final String zzcfx;
    public final long zzchb;
    public final boolean zzchc;
    public final long zzchd;
    public final List<String> zzche;
    public final String zzchh;
    @Nullable
    public final RewardItemParcel zzchr;
    @Nullable
    public final List<String> zzcht;
    public final boolean zzchu;
    public final AutoClickProtectionConfigurationParcel zzchv;
    public final String zzchy;
    public final JSONObject zzcod;
    public boolean zzcoe;
    public final zzgh zzcof;
    @Nullable
    public final String zzcog;
    public final AdSizeParcel zzcoh;
    @Nullable
    public final List<String> zzcoi;
    public final long zzcoj;
    public final long zzcok;
    @Nullable
    public final com.google.android.gms.ads.internal.formats.zzi.zza zzcol;
    public boolean zzcom;
    public boolean zzcon;
    public boolean zzcoo;

    @zziy
    public static final class zza {
        public final int errorCode;
        @Nullable
        public final AdSizeParcel zzaqz;
        public final AdRequestInfoParcel zzcix;
        @Nullable
        public final JSONObject zzcod;
        public final zzgh zzcof;
        public final long zzcoj;
        public final long zzcok;
        public final AdResponseParcel zzcop;

        public zza(AdRequestInfoParcel adRequestInfoParcel, AdResponseParcel adResponseParcel, zzgh com_google_android_gms_internal_zzgh, AdSizeParcel adSizeParcel, int i, long j, long j2, JSONObject jSONObject) {
            this.zzcix = adRequestInfoParcel;
            this.zzcop = adResponseParcel;
            this.zzcof = com_google_android_gms_internal_zzgh;
            this.zzaqz = adSizeParcel;
            this.errorCode = i;
            this.zzcoj = j;
            this.zzcok = j2;
            this.zzcod = jSONObject;
        }
    }

    public zzke(AdRequestParcel adRequestParcel, @Nullable zzlt com_google_android_gms_internal_zzlt, List<String> list, int i, List<String> list2, List<String> list3, int i2, long j, String str, boolean z, @Nullable zzgg com_google_android_gms_internal_zzgg, @Nullable zzgr com_google_android_gms_internal_zzgr, @Nullable String str2, zzgh com_google_android_gms_internal_zzgh, @Nullable zzgj com_google_android_gms_internal_zzgj, long j2, AdSizeParcel adSizeParcel, long j3, long j4, long j5, String str3, JSONObject jSONObject, @Nullable com.google.android.gms.ads.internal.formats.zzi.zza com_google_android_gms_ads_internal_formats_zzi_zza, RewardItemParcel rewardItemParcel, List<String> list4, List<String> list5, boolean z2, AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel, @Nullable String str4, List<String> list6, String str5) {
        this.zzcom = false;
        this.zzcon = false;
        this.zzcoo = false;
        this.zzcfu = adRequestParcel;
        this.zzbyh = com_google_android_gms_internal_zzlt;
        this.zzbsd = zzm(list);
        this.errorCode = i;
        this.zzbse = zzm(list2);
        this.zzche = zzm(list3);
        this.orientation = i2;
        this.zzbsj = j;
        this.zzcfx = str;
        this.zzchc = z;
        this.zzbte = com_google_android_gms_internal_zzgg;
        this.zzbtf = com_google_android_gms_internal_zzgr;
        this.zzbtg = str2;
        this.zzcof = com_google_android_gms_internal_zzgh;
        this.zzbth = com_google_android_gms_internal_zzgj;
        this.zzchd = j2;
        this.zzcoh = adSizeParcel;
        this.zzchb = j3;
        this.zzcoj = j4;
        this.zzcok = j5;
        this.zzchh = str3;
        this.zzcod = jSONObject;
        this.zzcol = com_google_android_gms_ads_internal_formats_zzi_zza;
        this.zzchr = rewardItemParcel;
        this.zzcoi = zzm(list4);
        this.zzcht = zzm(list5);
        this.zzchu = z2;
        this.zzchv = autoClickProtectionConfigurationParcel;
        this.zzcog = str4;
        this.zzbsg = zzm(list6);
        this.zzchy = str5;
    }

    public zzke(zza com_google_android_gms_internal_zzke_zza, @Nullable zzlt com_google_android_gms_internal_zzlt, @Nullable zzgg com_google_android_gms_internal_zzgg, @Nullable zzgr com_google_android_gms_internal_zzgr, @Nullable String str, @Nullable zzgj com_google_android_gms_internal_zzgj, @Nullable com.google.android.gms.ads.internal.formats.zzi.zza com_google_android_gms_ads_internal_formats_zzi_zza, @Nullable String str2) {
        this(com_google_android_gms_internal_zzke_zza.zzcix.zzcfu, com_google_android_gms_internal_zzlt, com_google_android_gms_internal_zzke_zza.zzcop.zzbsd, com_google_android_gms_internal_zzke_zza.errorCode, com_google_android_gms_internal_zzke_zza.zzcop.zzbse, com_google_android_gms_internal_zzke_zza.zzcop.zzche, com_google_android_gms_internal_zzke_zza.zzcop.orientation, com_google_android_gms_internal_zzke_zza.zzcop.zzbsj, com_google_android_gms_internal_zzke_zza.zzcix.zzcfx, com_google_android_gms_internal_zzke_zza.zzcop.zzchc, com_google_android_gms_internal_zzgg, com_google_android_gms_internal_zzgr, str, com_google_android_gms_internal_zzke_zza.zzcof, com_google_android_gms_internal_zzgj, com_google_android_gms_internal_zzke_zza.zzcop.zzchd, com_google_android_gms_internal_zzke_zza.zzaqz, com_google_android_gms_internal_zzke_zza.zzcop.zzchb, com_google_android_gms_internal_zzke_zza.zzcoj, com_google_android_gms_internal_zzke_zza.zzcok, com_google_android_gms_internal_zzke_zza.zzcop.zzchh, com_google_android_gms_internal_zzke_zza.zzcod, com_google_android_gms_ads_internal_formats_zzi_zza, com_google_android_gms_internal_zzke_zza.zzcop.zzchr, com_google_android_gms_internal_zzke_zza.zzcop.zzchs, com_google_android_gms_internal_zzke_zza.zzcop.zzchs, com_google_android_gms_internal_zzke_zza.zzcop.zzchu, com_google_android_gms_internal_zzke_zza.zzcop.zzchv, str2, com_google_android_gms_internal_zzke_zza.zzcop.zzbsg, com_google_android_gms_internal_zzke_zza.zzcop.zzchy);
    }

    @Nullable
    private static <T> List<T> zzm(@Nullable List<T> list) {
        return list == null ? null : Collections.unmodifiableList(list);
    }

    public boolean zzib() {
        return (this.zzbyh == null || this.zzbyh.zzvr() == null) ? false : this.zzbyh.zzvr().zzib();
    }
}
