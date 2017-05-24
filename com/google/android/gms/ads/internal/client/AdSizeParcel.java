package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zza;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.zziy;
import com.google.android.vending.expansion.downloader.Constants;

@zziy
public class AdSizeParcel extends AbstractSafeParcelable {
    public static final zzi CREATOR = new zzi();
    public final int height;
    public final int heightPixels;
    public final int versionCode;
    public final int width;
    public final int widthPixels;
    public final String zzaxi;
    public final boolean zzaxj;
    public final AdSizeParcel[] zzaxk;
    public final boolean zzaxl;
    public final boolean zzaxm;
    public boolean zzaxn;

    public AdSizeParcel() {
        this(5, "interstitial_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    AdSizeParcel(int i, String str, int i2, int i3, boolean z, int i4, int i5, AdSizeParcel[] adSizeParcelArr, boolean z2, boolean z3, boolean z4) {
        this.versionCode = i;
        this.zzaxi = str;
        this.height = i2;
        this.heightPixels = i3;
        this.zzaxj = z;
        this.width = i4;
        this.widthPixels = i5;
        this.zzaxk = adSizeParcelArr;
        this.zzaxl = z2;
        this.zzaxm = z3;
        this.zzaxn = z4;
    }

    public AdSizeParcel(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    public AdSizeParcel(Context context, AdSize[] adSizeArr) {
        int i;
        int i2;
        AdSize adSize = adSizeArr[0];
        this.versionCode = 5;
        this.zzaxj = false;
        this.zzaxm = adSize.isFluid();
        if (this.zzaxm) {
            this.width = AdSize.BANNER.getWidth();
            this.height = AdSize.BANNER.getHeight();
        } else {
            this.width = adSize.getWidth();
            this.height = adSize.getHeight();
        }
        boolean z = this.width == -1;
        boolean z2 = this.height == -2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (z) {
            if (zzm.zzjr().zzat(context) && zzm.zzjr().zzau(context)) {
                this.widthPixels = zza(displayMetrics) - zzm.zzjr().zzav(context);
            } else {
                this.widthPixels = zza(displayMetrics);
            }
            double d = (double) (((float) this.widthPixels) / displayMetrics.density);
            i = (int) d;
            if (d - ((double) ((int) d)) >= 0.01d) {
                i++;
            }
            i2 = i;
        } else {
            i = this.width;
            this.widthPixels = zzm.zzjr().zza(displayMetrics, this.width);
            i2 = i;
        }
        i = z2 ? zzc(displayMetrics) : this.height;
        this.heightPixels = zzm.zzjr().zza(displayMetrics, i);
        if (z || z2) {
            this.zzaxi = i2 + "x" + i + "_as";
        } else if (this.zzaxm) {
            this.zzaxi = "320x50_mb";
        } else {
            this.zzaxi = adSize.toString();
        }
        if (adSizeArr.length > 1) {
            this.zzaxk = new AdSizeParcel[adSizeArr.length];
            for (int i3 = 0; i3 < adSizeArr.length; i3++) {
                this.zzaxk[i3] = new AdSizeParcel(context, adSizeArr[i3]);
            }
        } else {
            this.zzaxk = null;
        }
        this.zzaxl = false;
        this.zzaxn = false;
    }

    public AdSizeParcel(AdSizeParcel adSizeParcel, AdSizeParcel[] adSizeParcelArr) {
        this(5, adSizeParcel.zzaxi, adSizeParcel.height, adSizeParcel.heightPixels, adSizeParcel.zzaxj, adSizeParcel.width, adSizeParcel.widthPixels, adSizeParcelArr, adSizeParcel.zzaxl, adSizeParcel.zzaxm, adSizeParcel.zzaxn);
    }

    public static int zza(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int zzb(DisplayMetrics displayMetrics) {
        return (int) (((float) zzc(displayMetrics)) * displayMetrics.density);
    }

    private static int zzc(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        return i <= Constants.STATUS_BAD_REQUEST ? 32 : i <= 720 ? 50 : 90;
    }

    public static AdSizeParcel zzjc() {
        return new AdSizeParcel(5, "reward_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public static AdSizeParcel zzk(Context context) {
        return new AdSizeParcel(5, "320x50_mb", 0, 0, false, 0, 0, null, true, false, false);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }

    public AdSize zzjd() {
        return zza.zza(this.width, this.height, this.zzaxi);
    }

    public void zzl(boolean z) {
        this.zzaxn = z;
    }
}
