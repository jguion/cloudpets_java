package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzr;
import com.google.android.gms.ads.internal.client.zzs.zza;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzei;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.internal.zzek;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zziy;

@zziy
public class zzk extends zza {
    private final Context mContext;
    private final zzd zzalo;
    private final zzgq zzals;
    private zzq zzamy;
    private NativeAdOptionsParcel zzand;
    private zzy zzanf;
    private final String zzang;
    private final VersionInfoParcel zzanh;
    private zzeh zzanl;
    private zzei zzanm;
    private SimpleArrayMap<String, zzej> zzann = new SimpleArrayMap();
    private SimpleArrayMap<String, zzek> zzano = new SimpleArrayMap();

    public zzk(Context context, String str, zzgq com_google_android_gms_internal_zzgq, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        this.mContext = context;
        this.zzang = str;
        this.zzals = com_google_android_gms_internal_zzgq;
        this.zzanh = versionInfoParcel;
        this.zzalo = com_google_android_gms_ads_internal_zzd;
    }

    public void zza(NativeAdOptionsParcel nativeAdOptionsParcel) {
        this.zzand = nativeAdOptionsParcel;
    }

    public void zza(zzeh com_google_android_gms_internal_zzeh) {
        this.zzanl = com_google_android_gms_internal_zzeh;
    }

    public void zza(zzei com_google_android_gms_internal_zzei) {
        this.zzanm = com_google_android_gms_internal_zzei;
    }

    public void zza(String str, zzek com_google_android_gms_internal_zzek, zzej com_google_android_gms_internal_zzej) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
        }
        this.zzano.put(str, com_google_android_gms_internal_zzek);
        this.zzann.put(str, com_google_android_gms_internal_zzej);
    }

    public void zzb(zzq com_google_android_gms_ads_internal_client_zzq) {
        this.zzamy = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zzb(zzy com_google_android_gms_ads_internal_client_zzy) {
        this.zzanf = com_google_android_gms_ads_internal_client_zzy;
    }

    public zzr zzey() {
        return new zzj(this.mContext, this.zzang, this.zzals, this.zzanh, this.zzamy, this.zzanl, this.zzanm, this.zzano, this.zzann, this.zzand, this.zzanf, this.zzalo);
    }
}
