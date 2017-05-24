package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.internal.zzpv.zza;

public class zzps extends zzl<zzpv> {
    public zzps(Context context, Looper looper, zzh com_google_android_gms_common_internal_zzh, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 40, com_google_android_gms_common_internal_zzh, connectionCallbacks, onConnectionFailedListener);
    }

    public void zza(zzpu com_google_android_gms_internal_zzpu, LogEventParcelable logEventParcelable) throws RemoteException {
        ((zzpv) zzatx()).zza(com_google_android_gms_internal_zzpu, logEventParcelable);
    }

    protected zzpv zzdm(IBinder iBinder) {
        return zza.zzdo(iBinder);
    }

    protected /* synthetic */ IInterface zzh(IBinder iBinder) {
        return zzdm(iBinder);
    }

    protected String zzix() {
        return "com.google.android.gms.clearcut.service.START";
    }

    protected String zziy() {
        return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
    }
}
