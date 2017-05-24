package com.google.android.gms.ads.internal.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdz;
import com.google.android.gms.internal.zzgq;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzhy;

public interface zzx extends IInterface {

    public static abstract class zza extends Binder implements zzx {

        private static class zza implements zzx {
            private IBinder zzajf;

            zza(IBinder iBinder) {
                this.zzajf = iBinder;
            }

            public IBinder asBinder() {
                return this.zzajf;
            }

            public zzs createAdLoaderBuilder(zzd com_google_android_gms_dynamic_zzd, String str, zzgq com_google_android_gms_internal_zzgq, int i) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    obtain.writeString(str);
                    if (com_google_android_gms_internal_zzgq != null) {
                        iBinder = com_google_android_gms_internal_zzgq.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    this.zzajf.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    zzs zzo = com.google.android.gms.ads.internal.client.zzs.zza.zzo(obtain2.readStrongBinder());
                    return zzo;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzhp createAdOverlay(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.zzajf.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    zzhp zzat = com.google.android.gms.internal.zzhp.zza.zzat(obtain2.readStrongBinder());
                    return zzat;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzu createBannerAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, int i) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (com_google_android_gms_internal_zzgq != null) {
                        iBinder = com_google_android_gms_internal_zzgq.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    this.zzajf.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    zzu zzq = com.google.android.gms.ads.internal.client.zzu.zza.zzq(obtain2.readStrongBinder());
                    return zzq;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzhy createInAppPurchaseManager(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.zzajf.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    zzhy zzay = com.google.android.gms.internal.zzhy.zza.zzay(obtain2.readStrongBinder());
                    return zzay;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzu createInterstitialAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, int i) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (com_google_android_gms_internal_zzgq != null) {
                        iBinder = com_google_android_gms_internal_zzgq.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    this.zzajf.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    zzu zzq = com.google.android.gms.ads.internal.client.zzu.zza.zzq(obtain2.readStrongBinder());
                    return zzq;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzdz createNativeAdViewDelegate(zzd com_google_android_gms_dynamic_zzd, zzd com_google_android_gms_dynamic_zzd2) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (com_google_android_gms_dynamic_zzd2 != null) {
                        iBinder = com_google_android_gms_dynamic_zzd2.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzajf.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    zzdz zzac = com.google.android.gms.internal.zzdz.zza.zzac(obtain2.readStrongBinder());
                    return zzac;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzb createRewardedVideoAd(zzd com_google_android_gms_dynamic_zzd, zzgq com_google_android_gms_internal_zzgq, int i) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (com_google_android_gms_internal_zzgq != null) {
                        iBinder = com_google_android_gms_internal_zzgq.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    this.zzajf.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    zzb zzbh = com.google.android.gms.ads.internal.reward.client.zzb.zza.zzbh(obtain2.readStrongBinder());
                    return zzbh;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzu createSearchAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.zzajf.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    zzu zzq = com.google.android.gms.ads.internal.client.zzu.zza.zzq(obtain2.readStrongBinder());
                    return zzq;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzz getMobileAdsSettingsManager(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.zzajf.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    zzz zzu = com.google.android.gms.ads.internal.client.zzz.zza.zzu(obtain2.readStrongBinder());
                    return zzu;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzz getMobileAdsSettingsManagerWithClientJarVersion(zzd com_google_android_gms_dynamic_zzd, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    obtain.writeInt(i);
                    this.zzajf.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    zzz zzu = com.google.android.gms.ads.internal.client.zzz.zza.zzu(obtain2.readStrongBinder());
                    return zzu;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.client.IClientApi");
        }

        public static zzx asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzx)) ? new zza(iBinder) : (zzx) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IBinder iBinder = null;
            zzu createBannerAdManager;
            zzz mobileAdsSettingsManager;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    createBannerAdManager = createBannerAdManager(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readInt() != 0 ? (AdSizeParcel) AdSizeParcel.CREATOR.createFromParcel(parcel) : null, parcel.readString(), com.google.android.gms.internal.zzgq.zza.zzam(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createBannerAdManager != null) {
                        iBinder = createBannerAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    createBannerAdManager = createInterstitialAdManager(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readInt() != 0 ? (AdSizeParcel) AdSizeParcel.CREATOR.createFromParcel(parcel) : null, parcel.readString(), com.google.android.gms.internal.zzgq.zza.zzam(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createBannerAdManager != null) {
                        iBinder = createBannerAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzs createAdLoaderBuilder = createAdLoaderBuilder(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readString(), com.google.android.gms.internal.zzgq.zza.zzam(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createAdLoaderBuilder != null) {
                        iBinder = createAdLoaderBuilder.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 4:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    mobileAdsSettingsManager = getMobileAdsSettingsManager(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (mobileAdsSettingsManager != null) {
                        iBinder = mobileAdsSettingsManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 5:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzdz createNativeAdViewDelegate = createNativeAdViewDelegate(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()), com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (createNativeAdViewDelegate != null) {
                        iBinder = createNativeAdViewDelegate.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 6:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzb createRewardedVideoAd = createRewardedVideoAd(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()), com.google.android.gms.internal.zzgq.zza.zzam(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createRewardedVideoAd != null) {
                        iBinder = createRewardedVideoAd.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 7:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzhy createInAppPurchaseManager = createInAppPurchaseManager(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (createInAppPurchaseManager != null) {
                        iBinder = createInAppPurchaseManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 8:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzhp createAdOverlay = createAdOverlay(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (createAdOverlay != null) {
                        iBinder = createAdOverlay.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 9:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    mobileAdsSettingsManager = getMobileAdsSettingsManagerWithClientJarVersion(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (mobileAdsSettingsManager != null) {
                        iBinder = mobileAdsSettingsManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 10:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    createBannerAdManager = createSearchAdManager(com.google.android.gms.dynamic.zzd.zza.zzfe(parcel.readStrongBinder()), parcel.readInt() != 0 ? (AdSizeParcel) AdSizeParcel.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (createBannerAdManager != null) {
                        iBinder = createBannerAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IClientApi");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    zzs createAdLoaderBuilder(zzd com_google_android_gms_dynamic_zzd, String str, zzgq com_google_android_gms_internal_zzgq, int i) throws RemoteException;

    zzhp createAdOverlay(zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    zzu createBannerAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, int i) throws RemoteException;

    zzhy createInAppPurchaseManager(zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    zzu createInterstitialAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgq com_google_android_gms_internal_zzgq, int i) throws RemoteException;

    zzdz createNativeAdViewDelegate(zzd com_google_android_gms_dynamic_zzd, zzd com_google_android_gms_dynamic_zzd2) throws RemoteException;

    zzb createRewardedVideoAd(zzd com_google_android_gms_dynamic_zzd, zzgq com_google_android_gms_internal_zzgq, int i) throws RemoteException;

    zzu createSearchAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, int i) throws RemoteException;

    zzz getMobileAdsSettingsManager(zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    zzz getMobileAdsSettingsManagerWithClientJarVersion(zzd com_google_android_gms_dynamic_zzd, int i) throws RemoteException;
}
