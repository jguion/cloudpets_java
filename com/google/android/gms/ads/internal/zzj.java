package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzr.zza;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzei;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.internal.zzek;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkr;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@zziy
public class zzj extends zza {
    private final Context mContext;
    private final Object zzakd = new Object();
    private final zzd zzalo;
    private final zzgq zzals;
    private final zzq zzamy;
    @Nullable
    private final zzeh zzamz;
    @Nullable
    private final zzei zzana;
    private final SimpleArrayMap<String, zzek> zzanb;
    private final SimpleArrayMap<String, zzej> zzanc;
    private final NativeAdOptionsParcel zzand;
    private final List<String> zzane;
    private final zzy zzanf;
    private final String zzang;
    private final VersionInfoParcel zzanh;
    @Nullable
    private WeakReference<zzq> zzani;

    zzj(Context context, String str, zzgq com_google_android_gms_internal_zzgq, VersionInfoParcel versionInfoParcel, zzq com_google_android_gms_ads_internal_client_zzq, zzeh com_google_android_gms_internal_zzeh, zzei com_google_android_gms_internal_zzei, SimpleArrayMap<String, zzek> simpleArrayMap, SimpleArrayMap<String, zzej> simpleArrayMap2, NativeAdOptionsParcel nativeAdOptionsParcel, zzy com_google_android_gms_ads_internal_client_zzy, zzd com_google_android_gms_ads_internal_zzd) {
        this.mContext = context;
        this.zzang = str;
        this.zzals = com_google_android_gms_internal_zzgq;
        this.zzanh = versionInfoParcel;
        this.zzamy = com_google_android_gms_ads_internal_client_zzq;
        this.zzana = com_google_android_gms_internal_zzei;
        this.zzamz = com_google_android_gms_internal_zzeh;
        this.zzanb = simpleArrayMap;
        this.zzanc = simpleArrayMap2;
        this.zzand = nativeAdOptionsParcel;
        this.zzane = zzew();
        this.zzanf = com_google_android_gms_ads_internal_client_zzy;
        this.zzalo = com_google_android_gms_ads_internal_zzd;
    }

    private List<String> zzew() {
        List<String> arrayList = new ArrayList();
        if (this.zzana != null) {
            arrayList.add("1");
        }
        if (this.zzamz != null) {
            arrayList.add("2");
        }
        if (this.zzanb.size() > 0) {
            arrayList.add("3");
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    public java.lang.String getMediationAdapterClassName() {
        /*
        r3 = this;
        r1 = 0;
        r2 = r3.zzakd;
        monitor-enter(r2);
        r0 = r3.zzani;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001a;
    L_0x0008:
        r0 = r3.zzani;	 Catch:{ all -> 0x001d }
        r0 = r0.get();	 Catch:{ all -> 0x001d }
        r0 = (com.google.android.gms.ads.internal.zzq) r0;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r0 = r0.getMediationAdapterClassName();	 Catch:{ all -> 0x001d }
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = r1;
        goto L_0x0016;
    L_0x001a:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        r0 = r1;
        goto L_0x0017;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzj.getMediationAdapterClassName():java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isLoading() {
        /*
        r3 = this;
        r1 = 0;
        r2 = r3.zzakd;
        monitor-enter(r2);
        r0 = r3.zzani;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001a;
    L_0x0008:
        r0 = r3.zzani;	 Catch:{ all -> 0x001d }
        r0 = r0.get();	 Catch:{ all -> 0x001d }
        r0 = (com.google.android.gms.ads.internal.zzq) r0;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r0 = r0.isLoading();	 Catch:{ all -> 0x001d }
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = r1;
        goto L_0x0016;
    L_0x001a:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        r0 = r1;
        goto L_0x0017;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzj.isLoading():boolean");
    }

    protected void runOnUiThread(Runnable runnable) {
        zzkr.zzcrf.post(runnable);
    }

    protected zzq zzex() {
        return new zzq(this.mContext, this.zzalo, AdSizeParcel.zzk(this.mContext), this.zzang, this.zzals, this.zzanh);
    }

    public void zzf(final AdRequestParcel adRequestParcel) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzj zzank;

            public void run() {
                synchronized (this.zzank.zzakd) {
                    zzq zzex = this.zzank.zzex();
                    this.zzank.zzani = new WeakReference(zzex);
                    zzex.zzb(this.zzank.zzamz);
                    zzex.zzb(this.zzank.zzana);
                    zzex.zza(this.zzank.zzanb);
                    zzex.zza(this.zzank.zzamy);
                    zzex.zzb(this.zzank.zzanc);
                    zzex.zzb(this.zzank.zzew());
                    zzex.zzb(this.zzank.zzand);
                    zzex.zza(this.zzank.zzanf);
                    zzex.zzb(adRequestParcel);
                }
            }
        });
    }
}
