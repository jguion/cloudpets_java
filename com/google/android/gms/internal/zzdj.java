package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build.VERSION;
import com.google.android.gms.ads.internal.zzu;
import java.util.LinkedHashMap;
import java.util.Map;

@zziy
public class zzdj {
    private Context mContext = null;
    private String zzati = null;
    private boolean zzbhy;
    private String zzbhz;
    private Map<String, String> zzbia;

    public zzdj(Context context, String str) {
        this.mContext = context;
        this.zzati = str;
        this.zzbhy = ((Boolean) zzdi.zzbca.get()).booleanValue();
        this.zzbhz = (String) zzdi.zzbcb.get();
        this.zzbia = new LinkedHashMap();
        this.zzbia.put("s", "gmob_sdk");
        this.zzbia.put("v", "3");
        this.zzbia.put("os", VERSION.RELEASE);
        this.zzbia.put("sdk", VERSION.SDK);
        this.zzbia.put("device", zzu.zzfz().zzuj());
        this.zzbia.put("app", context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        this.zzbia.put("is_lite_sdk", zzu.zzfz().zzan(context) ? "1" : "0");
        zzjh zzy = zzu.zzgi().zzy(this.mContext);
        this.zzbia.put("network_coarse", Integer.toString(zzy.zzcmd));
        this.zzbia.put("network_fine", Integer.toString(zzy.zzcme));
    }

    Context getContext() {
        return this.mContext;
    }

    String zzhy() {
        return this.zzati;
    }

    boolean zzkt() {
        return this.zzbhy;
    }

    String zzku() {
        return this.zzbhz;
    }

    Map<String, String> zzkv() {
        return this.zzbia;
    }
}
