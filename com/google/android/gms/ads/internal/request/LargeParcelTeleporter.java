package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.internal.zziy;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@zziy
public final class LargeParcelTeleporter extends AbstractSafeParcelable {
    public static final Creator<LargeParcelTeleporter> CREATOR = new zzm();
    final int mVersionCode;
    ParcelFileDescriptor zzcie;
    private Parcelable zzcif;
    private boolean zzcig;

    LargeParcelTeleporter(int i, ParcelFileDescriptor parcelFileDescriptor) {
        this.mVersionCode = i;
        this.zzcie = parcelFileDescriptor;
        this.zzcif = null;
        this.zzcig = true;
    }

    public LargeParcelTeleporter(SafeParcelable safeParcelable) {
        this.mVersionCode = 1;
        this.zzcie = null;
        this.zzcif = safeParcelable;
        this.zzcig = false;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzcie == null) {
            Parcel obtain = Parcel.obtain();
            try {
                this.zzcif.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                this.zzcie = zzi(marshall);
            } finally {
                obtain.recycle();
            }
        }
        zzm.zza(this, parcel, i);
    }

    public <T extends SafeParcelable> T zza(Creator<T> creator) {
        if (this.zzcig) {
            if (this.zzcie == null) {
                zzb.e("File descriptor is empty, returning null.");
                return null;
            }
            Closeable dataInputStream = new DataInputStream(new AutoCloseInputStream(this.zzcie));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(bArr, 0, bArr.length);
                zzo.zzb(dataInputStream);
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.unmarshall(bArr, 0, bArr.length);
                    obtain.setDataPosition(0);
                    this.zzcif = (SafeParcelable) creator.createFromParcel(obtain);
                    this.zzcig = false;
                } finally {
                    obtain.recycle();
                }
            } catch (Throwable e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th) {
                zzo.zzb(dataInputStream);
            }
        }
        return (SafeParcelable) this.zzcif;
    }

    protected <T> ParcelFileDescriptor zzi(final byte[] bArr) {
        final Closeable autoCloseOutputStream;
        Throwable e;
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new AutoCloseOutputStream(createPipe[1]);
            try {
                new Thread(new Runnable(this) {
                    final /* synthetic */ LargeParcelTeleporter zzcij;

                    public void run() {
                        Closeable dataOutputStream;
                        Throwable e;
                        try {
                            dataOutputStream = new DataOutputStream(autoCloseOutputStream);
                            try {
                                dataOutputStream.writeInt(bArr.length);
                                dataOutputStream.write(bArr);
                                zzo.zzb(dataOutputStream);
                            } catch (IOException e2) {
                                e = e2;
                                try {
                                    zzb.zzb("Error transporting the ad response", e);
                                    zzu.zzgd().zza(e, "LargeParcelTeleporter.pipeData.1");
                                    if (dataOutputStream != null) {
                                        zzo.zzb(autoCloseOutputStream);
                                    } else {
                                        zzo.zzb(dataOutputStream);
                                    }
                                } catch (Throwable th) {
                                    e = th;
                                    if (dataOutputStream != null) {
                                        zzo.zzb(autoCloseOutputStream);
                                    } else {
                                        zzo.zzb(dataOutputStream);
                                    }
                                    throw e;
                                }
                            }
                        } catch (IOException e3) {
                            e = e3;
                            dataOutputStream = null;
                            zzb.zzb("Error transporting the ad response", e);
                            zzu.zzgd().zza(e, "LargeParcelTeleporter.pipeData.1");
                            if (dataOutputStream != null) {
                                zzo.zzb(dataOutputStream);
                            } else {
                                zzo.zzb(autoCloseOutputStream);
                            }
                        } catch (Throwable th2) {
                            e = th2;
                            dataOutputStream = null;
                            if (dataOutputStream != null) {
                                zzo.zzb(dataOutputStream);
                            } else {
                                zzo.zzb(autoCloseOutputStream);
                            }
                            throw e;
                        }
                    }
                }).start();
                return createPipe[0];
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            autoCloseOutputStream = parcelFileDescriptor;
            zzb.zzb("Error transporting the ad response", e);
            zzu.zzgd().zza(e, "LargeParcelTeleporter.pipeData.2");
            zzo.zzb(autoCloseOutputStream);
            return parcelFileDescriptor;
        }
    }
}
