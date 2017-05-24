package com.google.android.gms.ads.internal.util.client;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.zziy;

@zziy
public final class VersionInfoParcel extends AbstractSafeParcelable {
    public static final zzd CREATOR = new zzd();
    public final int versionCode;
    public String zzcs;
    public int zzcts;
    public int zzctt;
    public boolean zzctu;

    public VersionInfoParcel(int i, int i2, boolean z) {
        this(i, i2, z, false);
    }

    public VersionInfoParcel(int i, int i2, boolean z, boolean z2) {
        String valueOf = String.valueOf("afma-sdk-a-v");
        String str = z ? "0" : z2 ? "2" : "1";
        this(1, new StringBuilder((String.valueOf(valueOf).length() + 24) + String.valueOf(str).length()).append(valueOf).append(i).append(".").append(i2).append(".").append(str).toString(), i, i2, z);
    }

    VersionInfoParcel(int i, String str, int i2, int i3, boolean z) {
        this.versionCode = i;
        this.zzcs = str;
        this.zzcts = i2;
        this.zzctt = i3;
        this.zzctu = z;
    }

    public static VersionInfoParcel zzvg() {
        return new VersionInfoParcel(9683208, 9683208, true);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}
