package com.google.android.gms.gass.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface zze extends IInterface {

    public static abstract class zza extends Binder implements zze {

        private static class zza implements zze {
            private IBinder zzajf;

            zza(IBinder iBinder) {
                this.zzajf = iBinder;
            }

            public IBinder asBinder() {
                return this.zzajf;
            }

            public GassResponseParcel zza(GassRequestParcel gassRequestParcel) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.gass.internal.IGassService");
                    if (gassRequestParcel != null) {
                        obtain.writeInt(1);
                        gassRequestParcel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    GassResponseParcel gassResponseParcel = obtain2.readInt() != 0 ? (GassResponseParcel) GassResponseParcel.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return gassResponseParcel;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zze zzgr(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.gass.internal.IGassService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zze)) ? new zza(iBinder) : (zze) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.gass.internal.IGassService");
                    GassResponseParcel zza = zza(parcel.readInt() != 0 ? (GassRequestParcel) GassRequestParcel.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (zza != null) {
                        parcel2.writeInt(1);
                        zza.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.gass.internal.IGassService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    GassResponseParcel zza(GassRequestParcel gassRequestParcel) throws RemoteException;
}
