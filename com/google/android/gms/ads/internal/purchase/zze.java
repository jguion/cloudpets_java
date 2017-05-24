package com.google.android.gms.ads.internal.purchase;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzhw;
import com.google.android.gms.internal.zzhy.zza;
import com.google.android.gms.internal.zziy;

@zziy
public class zze extends zza implements ServiceConnection {
    private final Activity mActivity;
    private zzb zzcbl;
    zzh zzcbm;
    private zzk zzcbo;
    private Context zzcbt;
    private zzhw zzcbu;
    private zzf zzcbv;
    private zzj zzcbw;
    private String zzcbx = null;

    public zze(Activity activity) {
        this.mActivity = activity;
        this.zzcbm = zzh.zzs(this.mActivity.getApplicationContext());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1001) {
            boolean z = false;
            try {
                int zzd = zzu.zzgn().zzd(intent);
                if (i2 == -1) {
                    zzu.zzgn();
                    if (zzd == 0) {
                        if (this.zzcbo.zza(this.zzcbx, i2, intent)) {
                            z = true;
                        }
                        this.zzcbu.recordPlayBillingResolution(zzd);
                        this.mActivity.finish();
                        zza(this.zzcbu.getProductId(), z, i2, intent);
                    }
                }
                this.zzcbm.zza(this.zzcbv);
                this.zzcbu.recordPlayBillingResolution(zzd);
                this.mActivity.finish();
                zza(this.zzcbu.getProductId(), z, i2, intent);
            } catch (RemoteException e) {
                zzb.zzdf("Fail to process purchase result.");
                this.mActivity.finish();
            } finally {
                this.zzcbx = null;
            }
        }
    }

    public void onCreate() {
        GInAppPurchaseManagerInfoParcel zzc = GInAppPurchaseManagerInfoParcel.zzc(this.mActivity.getIntent());
        this.zzcbw = zzc.zzcbh;
        this.zzcbo = zzc.zzars;
        this.zzcbu = zzc.zzcbf;
        this.zzcbl = new zzb(this.mActivity.getApplicationContext());
        this.zzcbt = zzc.zzcbg;
        if (this.mActivity.getResources().getConfiguration().orientation == 2) {
            this.mActivity.setRequestedOrientation(zzu.zzgb().zzum());
        } else {
            this.mActivity.setRequestedOrientation(zzu.zzgb().zzun());
        }
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        this.mActivity.bindService(intent, this, 1);
    }

    public void onDestroy() {
        this.mActivity.unbindService(this);
        this.zzcbl.destroy();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Throwable e;
        this.zzcbl.zzav(iBinder);
        try {
            this.zzcbx = this.zzcbo.zzqs();
            Bundle zzb = this.zzcbl.zzb(this.mActivity.getPackageName(), this.zzcbu.getProductId(), this.zzcbx);
            PendingIntent pendingIntent = (PendingIntent) zzb.getParcelable("BUY_INTENT");
            if (pendingIntent == null) {
                int zzd = zzu.zzgn().zzd(zzb);
                this.zzcbu.recordPlayBillingResolution(zzd);
                zza(this.zzcbu.getProductId(), false, zzd, null);
                this.mActivity.finish();
                return;
            }
            this.zzcbv = new zzf(this.zzcbu.getProductId(), this.zzcbx);
            this.zzcbm.zzb(this.zzcbv);
            this.mActivity.startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
        } catch (RemoteException e2) {
            e = e2;
            zzb.zzd("Error when connecting in-app billing service", e);
            this.mActivity.finish();
        } catch (SendIntentException e3) {
            e = e3;
            zzb.zzd("Error when connecting in-app billing service", e);
            this.mActivity.finish();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzb.zzde("In-app billing service disconnected.");
        this.zzcbl.destroy();
    }

    protected void zza(String str, boolean z, int i, Intent intent) {
        if (this.zzcbw != null) {
            this.zzcbw.zza(str, z, i, intent, this.zzcbv);
        }
    }
}
