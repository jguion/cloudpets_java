package com.google.android.gms.internal;

import android.util.Log;
import com.google.ads.AdRequest;
import com.google.android.gms.ads.internal.util.client.zzb;

@zziy
public final class zzkn extends zzb {
    public static void v(String str) {
        if (zzue()) {
            Log.v(AdRequest.LOGTAG, str);
        }
    }

    public static boolean zzud() {
        return ((Boolean) zzdi.zzbdt.get()).booleanValue();
    }

    private static boolean zzue() {
        return zzb.zzbf(2) && zzud();
    }
}
