package com.google.android.gms.clearcut;

import android.content.Context;

public class zza {
    private static int tF = -1;
    public static final zza tG = new zza();

    protected zza() {
    }

    public int zzbl(Context context) {
        if (tF < 0) {
            tF = context.getSharedPreferences("bootCount", 0).getInt("bootCount", 1);
        }
        return tF;
    }
}
