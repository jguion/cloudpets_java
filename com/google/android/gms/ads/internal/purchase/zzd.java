package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzhw.zza;
import com.google.android.gms.internal.zziy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zziy
public class zzd extends zza {
    private Context mContext;
    private String zzati;
    private String zzcbr;
    private ArrayList<String> zzcbs;

    public zzd(String str, ArrayList<String> arrayList, Context context, String str2) {
        this.zzcbr = str;
        this.zzcbs = arrayList;
        this.zzati = str2;
        this.mContext = context;
    }

    public String getProductId() {
        return this.zzcbr;
    }

    public void recordPlayBillingResolution(int i) {
        if (i == 0) {
            zzqq();
        }
        Map zzqp = zzqp();
        zzqp.put("google_play_status", String.valueOf(i));
        zzqp.put("sku", this.zzcbr);
        zzqp.put("status", String.valueOf(zzak(i)));
        List linkedList = new LinkedList();
        Iterator it = this.zzcbs.iterator();
        while (it.hasNext()) {
            linkedList.add(zzu.zzfz().zzc((String) it.next(), zzqp));
        }
        zzu.zzfz().zza(this.mContext, this.zzati, linkedList);
    }

    public void recordResolution(int i) {
        if (i == 1) {
            zzqq();
        }
        Map zzqp = zzqp();
        zzqp.put("status", String.valueOf(i));
        zzqp.put("sku", this.zzcbr);
        List linkedList = new LinkedList();
        Iterator it = this.zzcbs.iterator();
        while (it.hasNext()) {
            linkedList.add(zzu.zzfz().zzc((String) it.next(), zzqp));
        }
        zzu.zzfz().zza(this.mContext, this.zzati, linkedList);
    }

    protected int zzak(int i) {
        return i == 0 ? 1 : i == 1 ? 2 : i == 4 ? 3 : 0;
    }

    Map<String, String> zzqp() {
        String packageName = this.mContext.getPackageName();
        Object obj = "";
        try {
            obj = this.mContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (Throwable e) {
            zzb.zzd("Error to retrieve app version", e);
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - zzu.zzgd().zztl().zzua();
        Map<String, String> hashMap = new HashMap();
        hashMap.put("sessionid", zzu.zzgd().getSessionId());
        hashMap.put("appid", packageName);
        hashMap.put("osversion", String.valueOf(VERSION.SDK_INT));
        hashMap.put("sdkversion", this.zzati);
        hashMap.put("appversion", obj);
        hashMap.put("timestamp", String.valueOf(elapsedRealtime));
        return hashMap;
    }

    void zzqq() {
        try {
            this.mContext.getClassLoader().loadClass("com.google.ads.conversiontracking.IAPConversionReporter").getDeclaredMethod("reportWithProductId", new Class[]{Context.class, String.class, String.class, Boolean.TYPE}).invoke(null, new Object[]{this.mContext, this.zzcbr, "", Boolean.valueOf(true)});
        } catch (ClassNotFoundException e) {
            zzb.zzdf("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (NoSuchMethodException e2) {
            zzb.zzdf("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (Throwable e3) {
            zzb.zzd("Fail to report a conversion.", e3);
        }
    }
}
