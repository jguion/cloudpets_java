package com.google.android.gms.ads.internal.cache;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.zziy;
import java.io.InputStream;

@zziy
public class CacheEntryParcel extends AbstractSafeParcelable {
    public static final zzb CREATOR = new zzb();
    public final int version;
    @Nullable
    private ParcelFileDescriptor zzavu;

    public CacheEntryParcel() {
        this(1, null);
    }

    CacheEntryParcel(int i, @Nullable ParcelFileDescriptor parcelFileDescriptor) {
        this.version = i;
        this.zzavu = parcelFileDescriptor;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public synchronized boolean zziu() {
        return this.zzavu != null;
    }

    @Nullable
    public synchronized InputStream zziv() {
        InputStream inputStream = null;
        synchronized (this) {
            if (this.zzavu != null) {
                inputStream = new AutoCloseInputStream(this.zzavu);
                this.zzavu = null;
            }
        }
        return inputStream;
    }

    synchronized ParcelFileDescriptor zziw() {
        return this.zzavu;
    }
}
