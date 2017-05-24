package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.parse.ParseException;

public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final int FULL_WIDTH = -1;
    public static final AdSize LARGE_BANNER = new AdSize(320, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(ParseException.INVALID_EVENT_NAME, 600, "160x600_as");
    private final int zzajw;
    private final int zzajx;
    private final String zzajy;

    public AdSize(int i, int i2) {
        String valueOf = i == -1 ? "FULL" : String.valueOf(i);
        String valueOf2 = i2 == -2 ? "AUTO" : String.valueOf(i2);
        String valueOf3 = String.valueOf("_as");
        this(i, i2, new StringBuilder(((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()).append(valueOf).append("x").append(valueOf2).append(valueOf3).toString());
    }

    AdSize(int i, int i2, String str) {
        if (i < 0 && i != -1 && i != -3) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + i);
        } else if (i2 >= 0 || i2 == -2 || i2 == -4) {
            this.zzajw = i;
            this.zzajx = i2;
            this.zzajy = str;
        } else {
            throw new IllegalArgumentException("Invalid height for AdSize: " + i2);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.zzajw == adSize.zzajw && this.zzajx == adSize.zzajx && this.zzajy.equals(adSize.zzajy);
    }

    public int getHeight() {
        return this.zzajx;
    }

    public int getHeightInPixels(Context context) {
        switch (this.zzajx) {
            case -4:
            case -3:
                return -1;
            case -2:
                return AdSizeParcel.zzb(context.getResources().getDisplayMetrics());
            default:
                return zzm.zzjr().zzb(context, this.zzajx);
        }
    }

    public int getWidth() {
        return this.zzajw;
    }

    public int getWidthInPixels(Context context) {
        switch (this.zzajw) {
            case -4:
            case -3:
                return -1;
            case -1:
                return AdSizeParcel.zza(context.getResources().getDisplayMetrics());
            default:
                return zzm.zzjr().zzb(context, this.zzajw);
        }
    }

    public int hashCode() {
        return this.zzajy.hashCode();
    }

    public boolean isAutoHeight() {
        return this.zzajx == -2;
    }

    public boolean isFluid() {
        return this.zzajw == -3 && this.zzajx == -4;
    }

    public boolean isFullWidth() {
        return this.zzajw == -1;
    }

    public String toString() {
        return this.zzajy;
    }
}
