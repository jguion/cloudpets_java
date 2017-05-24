package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkm;
import com.google.android.gms.internal.zzkn;
import com.google.android.gms.internal.zzkr;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zziy
public class zzc extends zzkm implements ServiceConnection {
    private Context mContext;
    private final Object zzakd;
    private zzib zzbpt;
    private boolean zzcbk;
    private zzb zzcbl;
    private zzh zzcbm;
    private List<zzf> zzcbn;
    private zzk zzcbo;

    public zzc(Context context, zzib com_google_android_gms_internal_zzib, zzk com_google_android_gms_ads_internal_purchase_zzk) {
        this(context, com_google_android_gms_internal_zzib, com_google_android_gms_ads_internal_purchase_zzk, new zzb(context), zzh.zzs(context.getApplicationContext()));
    }

    zzc(Context context, zzib com_google_android_gms_internal_zzib, zzk com_google_android_gms_ads_internal_purchase_zzk, zzb com_google_android_gms_ads_internal_purchase_zzb, zzh com_google_android_gms_ads_internal_purchase_zzh) {
        this.zzakd = new Object();
        this.zzcbk = false;
        this.zzcbn = null;
        this.mContext = context;
        this.zzbpt = com_google_android_gms_internal_zzib;
        this.zzcbo = com_google_android_gms_ads_internal_purchase_zzk;
        this.zzcbl = com_google_android_gms_ads_internal_purchase_zzb;
        this.zzcbm = com_google_android_gms_ads_internal_purchase_zzh;
        this.zzcbn = this.zzcbm.zzg(10);
    }

    private void zze(long j) {
        do {
            if (!zzf(j)) {
                zzkn.v("Timeout waiting for pending transaction to be processed.");
            }
        } while (!this.zzcbk);
    }

    private boolean zzf(long j) {
        long elapsedRealtime = Constants.WATCHDOG_WAKE_TIMER - (SystemClock.elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.zzakd.wait(elapsedRealtime);
        } catch (InterruptedException e) {
            zzb.zzdf("waitWithTimeout_lock interrupted");
        }
        return true;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.zzakd) {
            this.zzcbl.zzav(iBinder);
            zzqo();
            this.zzcbk = true;
            this.zzakd.notify();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzb.zzde("In-app billing service disconnected.");
        this.zzcbl.destroy();
    }

    public void onStop() {
        synchronized (this.zzakd) {
            com.google.android.gms.common.stats.zzb.zzawu().zza(this.mContext, (ServiceConnection) this);
            this.zzcbl.destroy();
        }
    }

    protected void zza(final zzf com_google_android_gms_ads_internal_purchase_zzf, String str, String str2) {
        final Intent intent = new Intent();
        zzu.zzgn();
        intent.putExtra("RESPONSE_CODE", 0);
        zzu.zzgn();
        intent.putExtra("INAPP_PURCHASE_DATA", str);
        zzu.zzgn();
        intent.putExtra("INAPP_DATA_SIGNATURE", str2);
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzc zzcbq;

            public void run() {
                try {
                    if (this.zzcbq.zzcbo.zza(com_google_android_gms_ads_internal_purchase_zzf.zzcbz, -1, intent)) {
                        this.zzcbq.zzbpt.zza(new zzg(this.zzcbq.mContext, com_google_android_gms_ads_internal_purchase_zzf.zzcca, true, -1, intent, com_google_android_gms_ads_internal_purchase_zzf));
                    } else {
                        this.zzcbq.zzbpt.zza(new zzg(this.zzcbq.mContext, com_google_android_gms_ads_internal_purchase_zzf.zzcca, false, -1, intent, com_google_android_gms_ads_internal_purchase_zzf));
                    }
                } catch (RemoteException e) {
                    zzb.zzdf("Fail to verify and dispatch pending transaction");
                }
            }
        });
    }

    public void zzfc() {
        synchronized (this.zzakd) {
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            com.google.android.gms.common.stats.zzb.zzawu().zza(this.mContext, intent, (ServiceConnection) this, 1);
            zze(SystemClock.elapsedRealtime());
            com.google.android.gms.common.stats.zzb.zzawu().zza(this.mContext, (ServiceConnection) this);
            this.zzcbl.destroy();
        }
    }

    protected void zzqo() {
        if (!this.zzcbn.isEmpty()) {
            HashMap hashMap = new HashMap();
            for (zzf com_google_android_gms_ads_internal_purchase_zzf : this.zzcbn) {
                hashMap.put(com_google_android_gms_ads_internal_purchase_zzf.zzcca, com_google_android_gms_ads_internal_purchase_zzf);
            }
            String str = null;
            while (true) {
                Bundle zzn = this.zzcbl.zzn(this.mContext.getPackageName(), str);
                if (zzn == null || zzu.zzgn().zzd(zzn) != 0) {
                    break;
                }
                ArrayList stringArrayList = zzn.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList stringArrayList2 = zzn.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList stringArrayList3 = zzn.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                String string = zzn.getString("INAPP_CONTINUATION_TOKEN");
                for (int i = 0; i < stringArrayList.size(); i++) {
                    if (hashMap.containsKey(stringArrayList.get(i))) {
                        str = (String) stringArrayList.get(i);
                        String str2 = (String) stringArrayList2.get(i);
                        String str3 = (String) stringArrayList3.get(i);
                        zzf com_google_android_gms_ads_internal_purchase_zzf2 = (zzf) hashMap.get(str);
                        if (com_google_android_gms_ads_internal_purchase_zzf2.zzcbz.equals(zzu.zzgn().zzcc(str2))) {
                            zza(com_google_android_gms_ads_internal_purchase_zzf2, str2, str3);
                            hashMap.remove(str);
                        }
                    }
                }
                if (string == null || hashMap.isEmpty()) {
                    break;
                }
                str = string;
            }
            for (String str4 : hashMap.keySet()) {
                this.zzcbm.zza((zzf) hashMap.get(str4));
            }
        }
    }
}
