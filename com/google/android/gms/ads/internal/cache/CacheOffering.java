package com.google.android.gms.ads.internal.cache;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.zziy;
import java.util.List;

@zziy
public class CacheOffering extends AbstractSafeParcelable {
    public static final zzd CREATOR = new zzd();
    @Nullable
    public final String url;
    public final int version;
    public final long zzavv;
    public final String zzavw;
    public final String zzavx;
    public final String zzavy;
    public final Bundle zzavz;
    public final boolean zzawa;

    CacheOffering(int i, @Nullable String str, long j, String str2, String str3, String str4, Bundle bundle, boolean z) {
        this.version = i;
        this.url = str;
        this.zzavv = j;
        if (str2 == null) {
            str2 = "";
        }
        this.zzavw = str2;
        if (str3 == null) {
            str3 = "";
        }
        this.zzavx = str3;
        if (str4 == null) {
            str4 = "";
        }
        this.zzavy = str4;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzavz = bundle;
        this.zzawa = z;
    }

    @Nullable
    public static CacheOffering zzag(String str) {
        return zze(Uri.parse(str));
    }

    @Nullable
    public static CacheOffering zze(Uri uri) {
        Throwable e;
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                zzb.zzdf("Expected 2 path parts for namespace and id, found :" + pathSegments.size());
                return null;
            }
            String str = (String) pathSegments.get(0);
            String str2 = (String) pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter("url");
            boolean equals = "1".equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            long parseLong = queryParameter2 == null ? 0 : Long.parseLong(queryParameter2);
            Bundle bundle = new Bundle();
            for (String queryParameter22 : zzu.zzgb().zzh(uri)) {
                if (queryParameter22.startsWith("tag.")) {
                    bundle.putString(queryParameter22.substring("tag.".length()), uri.getQueryParameter(queryParameter22));
                }
            }
            return new CacheOffering(1, queryParameter, parseLong, host, str, str2, bundle, equals);
        } catch (NullPointerException e2) {
            e = e2;
            zzb.zzd("Unable to parse Uri into cache offering.", e);
            return null;
        } catch (NumberFormatException e3) {
            e = e3;
            zzb.zzd("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}
