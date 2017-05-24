package com.google.android.gms.gass.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zze.zzb;
import com.google.android.gms.common.internal.zze.zzc;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class zza {

    static class zza implements zzb, zzc {
        protected zzb aev;
        private final String aew;
        private final LinkedBlockingQueue<com.google.android.gms.internal.zzae.zza> aex;
        private final HandlerThread aey = new HandlerThread("GassClient");
        private final String packageName;

        public zza(Context context, String str, String str2) {
            this.packageName = str;
            this.aew = str2;
            this.aey.start();
            this.aev = new zzb(context, this.aey.getLooper(), this, this);
            this.aex = new LinkedBlockingQueue();
            connect();
        }

        protected void connect() {
            this.aev.zzatu();
        }

        public void onConnected(Bundle bundle) {
            zze zzbnt = zzbnt();
            if (zzbnt != null) {
                try {
                    this.aex.put(zzbnt.zza(new GassRequestParcel(this.packageName, this.aew)).zzbnw());
                } catch (Throwable th) {
                } finally {
                    zzrv();
                    this.aey.quit();
                }
            }
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            try {
                this.aex.put(new com.google.android.gms.internal.zzae.zza());
            } catch (InterruptedException e) {
            }
        }

        public void onConnectionSuspended(int i) {
            try {
                this.aex.put(new com.google.android.gms.internal.zzae.zza());
            } catch (InterruptedException e) {
            }
        }

        protected zze zzbnt() {
            try {
                return this.aev.zzbnu();
            } catch (IllegalStateException e) {
                return null;
            } catch (DeadObjectException e2) {
                return null;
            }
        }

        public com.google.android.gms.internal.zzae.zza zzcp() {
            return zzth(2000);
        }

        public void zzrv() {
            if (this.aev == null) {
                return;
            }
            if (this.aev.isConnected() || this.aev.isConnecting()) {
                this.aev.disconnect();
            }
        }

        public com.google.android.gms.internal.zzae.zza zzth(int i) {
            com.google.android.gms.internal.zzae.zza com_google_android_gms_internal_zzae_zza;
            try {
                com_google_android_gms_internal_zzae_zza = (com.google.android.gms.internal.zzae.zza) this.aex.poll((long) i, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                com_google_android_gms_internal_zzae_zza = null;
            }
            return com_google_android_gms_internal_zzae_zza == null ? new com.google.android.gms.internal.zzae.zza() : com_google_android_gms_internal_zzae_zza;
        }
    }

    public static com.google.android.gms.internal.zzae.zza zzi(Context context, String str, String str2) {
        return new zza(context, str, str2).zzcp();
    }
}
