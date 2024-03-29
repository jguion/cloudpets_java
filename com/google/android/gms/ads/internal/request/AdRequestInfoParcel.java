package com.google.android.gms.ads.internal.request;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Messenger;
import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.zziy;
import java.util.Collections;
import java.util.List;

@zziy
public final class AdRequestInfoParcel extends AbstractSafeParcelable {
    public static final zzf CREATOR = new zzf();
    public final ApplicationInfo applicationInfo;
    public final int versionCode;
    public final String zzaqs;
    public final String zzaqt;
    public final VersionInfoParcel zzaqv;
    public final AdSizeParcel zzaqz;
    public final NativeAdOptionsParcel zzarn;
    public final List<String> zzarr;
    public final boolean zzbsh;
    @Nullable
    public final Bundle zzcft;
    public final AdRequestParcel zzcfu;
    @Nullable
    public final PackageInfo zzcfv;
    public final String zzcfw;
    public final String zzcfx;
    public final String zzcfy;
    public final Bundle zzcfz;
    public final int zzcga;
    public final Bundle zzcgb;
    public final boolean zzcgc;
    public final Messenger zzcgd;
    public final int zzcge;
    public final int zzcgf;
    public final float zzcgg;
    public final String zzcgh;
    public final long zzcgi;
    public final String zzcgj;
    @Nullable
    public final List<String> zzcgk;
    public final List<String> zzcgl;
    public final long zzcgm;
    public final CapabilityParcel zzcgn;
    public final String zzcgo;
    public final float zzcgp;
    public final int zzcgq;
    public final int zzcgr;
    public final boolean zzcgs;
    public final boolean zzcgt;
    public final String zzcgu;
    public final boolean zzcgv;
    public final String zzcgw;
    public final int zzcgx;
    public final Bundle zzcgy;
    public final String zzcgz;

    @zziy
    public static final class zza {
        public final ApplicationInfo applicationInfo;
        public final String zzaqs;
        public final String zzaqt;
        public final VersionInfoParcel zzaqv;
        public final AdSizeParcel zzaqz;
        public final NativeAdOptionsParcel zzarn;
        public final List<String> zzarr;
        public final boolean zzbsh;
        @Nullable
        public final Bundle zzcft;
        public final AdRequestParcel zzcfu;
        @Nullable
        public final PackageInfo zzcfv;
        public final String zzcfx;
        public final String zzcfy;
        public final Bundle zzcfz;
        public final int zzcga;
        public final Bundle zzcgb;
        public final boolean zzcgc;
        public final Messenger zzcgd;
        public final int zzcge;
        public final int zzcgf;
        public final float zzcgg;
        public final String zzcgh;
        public final long zzcgi;
        public final String zzcgj;
        @Nullable
        public final List<String> zzcgk;
        public final List<String> zzcgl;
        public final CapabilityParcel zzcgn;
        public final String zzcgo;
        public final float zzcgp;
        public final int zzcgq;
        public final int zzcgr;
        public final boolean zzcgs;
        public final boolean zzcgt;
        public final String zzcgu;
        public final boolean zzcgv;
        public final String zzcgw;
        public final int zzcgx;
        public final Bundle zzcgy;
        public final String zzcgz;

        public zza(@Nullable Bundle bundle, AdRequestParcel adRequestParcel, AdSizeParcel adSizeParcel, String str, ApplicationInfo applicationInfo, @Nullable PackageInfo packageInfo, String str2, String str3, VersionInfoParcel versionInfoParcel, Bundle bundle2, List<String> list, List<String> list2, Bundle bundle3, boolean z, Messenger messenger, int i, int i2, float f, String str4, long j, String str5, @Nullable List<String> list3, String str6, NativeAdOptionsParcel nativeAdOptionsParcel, CapabilityParcel capabilityParcel, String str7, float f2, boolean z2, int i3, int i4, boolean z3, boolean z4, String str8, String str9, boolean z5, int i5, Bundle bundle4, String str10) {
            this.zzcft = bundle;
            this.zzcfu = adRequestParcel;
            this.zzaqz = adSizeParcel;
            this.zzaqt = str;
            this.applicationInfo = applicationInfo;
            this.zzcfv = packageInfo;
            this.zzcfx = str2;
            this.zzcfy = str3;
            this.zzaqv = versionInfoParcel;
            this.zzcfz = bundle2;
            this.zzcgc = z;
            this.zzcgd = messenger;
            this.zzcge = i;
            this.zzcgf = i2;
            this.zzcgg = f;
            if (list == null || list.size() <= 0) {
                this.zzcga = 0;
                this.zzarr = null;
                this.zzcgl = null;
            } else {
                this.zzcga = 3;
                this.zzarr = list;
                this.zzcgl = list2;
            }
            this.zzcgb = bundle3;
            this.zzcgh = str4;
            this.zzcgi = j;
            this.zzcgj = str5;
            this.zzcgk = list3;
            this.zzaqs = str6;
            this.zzarn = nativeAdOptionsParcel;
            this.zzcgn = capabilityParcel;
            this.zzcgo = str7;
            this.zzcgp = f2;
            this.zzcgv = z2;
            this.zzcgq = i3;
            this.zzcgr = i4;
            this.zzcgs = z3;
            this.zzcgt = z4;
            this.zzcgu = str8;
            this.zzcgw = str9;
            this.zzbsh = z5;
            this.zzcgx = i5;
            this.zzcgy = bundle4;
            this.zzcgz = str10;
        }
    }

    AdRequestInfoParcel(int i, Bundle bundle, AdRequestParcel adRequestParcel, AdSizeParcel adSizeParcel, String str, ApplicationInfo applicationInfo, PackageInfo packageInfo, String str2, String str3, String str4, VersionInfoParcel versionInfoParcel, Bundle bundle2, int i2, List<String> list, Bundle bundle3, boolean z, Messenger messenger, int i3, int i4, float f, String str5, long j, String str6, List<String> list2, String str7, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list3, long j2, CapabilityParcel capabilityParcel, String str8, float f2, boolean z2, int i5, int i6, boolean z3, boolean z4, String str9, String str10, boolean z5, int i7, Bundle bundle4, String str11) {
        this.versionCode = i;
        this.zzcft = bundle;
        this.zzcfu = adRequestParcel;
        this.zzaqz = adSizeParcel;
        this.zzaqt = str;
        this.applicationInfo = applicationInfo;
        this.zzcfv = packageInfo;
        this.zzcfw = str2;
        this.zzcfx = str3;
        this.zzcfy = str4;
        this.zzaqv = versionInfoParcel;
        this.zzcfz = bundle2;
        this.zzcga = i2;
        this.zzarr = list;
        this.zzcgl = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.zzcgb = bundle3;
        this.zzcgc = z;
        this.zzcgd = messenger;
        this.zzcge = i3;
        this.zzcgf = i4;
        this.zzcgg = f;
        this.zzcgh = str5;
        this.zzcgi = j;
        this.zzcgj = str6;
        this.zzcgk = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzaqs = str7;
        this.zzarn = nativeAdOptionsParcel;
        this.zzcgm = j2;
        this.zzcgn = capabilityParcel;
        this.zzcgo = str8;
        this.zzcgp = f2;
        this.zzcgv = z2;
        this.zzcgq = i5;
        this.zzcgr = i6;
        this.zzcgs = z3;
        this.zzcgt = z4;
        this.zzcgu = str9;
        this.zzcgw = str10;
        this.zzbsh = z5;
        this.zzcgx = i7;
        this.zzcgy = bundle4;
        this.zzcgz = str11;
    }

    public AdRequestInfoParcel(@Nullable Bundle bundle, AdRequestParcel adRequestParcel, AdSizeParcel adSizeParcel, String str, ApplicationInfo applicationInfo, @Nullable PackageInfo packageInfo, String str2, String str3, String str4, VersionInfoParcel versionInfoParcel, Bundle bundle2, int i, List<String> list, List<String> list2, Bundle bundle3, boolean z, Messenger messenger, int i2, int i3, float f, String str5, long j, String str6, @Nullable List<String> list3, String str7, NativeAdOptionsParcel nativeAdOptionsParcel, long j2, CapabilityParcel capabilityParcel, String str8, float f2, boolean z2, int i4, int i5, boolean z3, boolean z4, String str9, String str10, boolean z5, int i6, Bundle bundle4, String str11) {
        this(19, bundle, adRequestParcel, adSizeParcel, str, applicationInfo, packageInfo, str2, str3, str4, versionInfoParcel, bundle2, i, list, bundle3, z, messenger, i2, i3, f, str5, j, str6, list3, str7, nativeAdOptionsParcel, list2, j2, capabilityParcel, str8, f2, z2, i4, i5, z3, z4, str9, str10, z5, i6, bundle4, str11);
    }

    public AdRequestInfoParcel(zza com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, String str, long j) {
        this(com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcft, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcfu, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzaqz, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzaqt, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.applicationInfo, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcfv, str, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcfx, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcfy, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzaqv, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcfz, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcga, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzarr, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgl, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgb, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgc, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgd, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcge, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgf, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgg, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgh, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgi, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgj, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgk, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzaqs, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzarn, j, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgn, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgo, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgp, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgv, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgq, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgr, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgs, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgt, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgu, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgw, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzbsh, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgx, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgy, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcgz);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }
}
