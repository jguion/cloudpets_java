package com.google.android.gms.internal;

import java.util.HashMap;

public class zzba extends zzaj<Integer, Long> {
    public Long zzahl;
    public Long zzahm;

    public zzba(String str) {
        zzk(str);
    }

    protected HashMap<Integer, Long> zzar() {
        HashMap<Integer, Long> hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), this.zzahl);
        hashMap.put(Integer.valueOf(1), this.zzahm);
        return hashMap;
    }

    protected void zzk(String str) {
        HashMap zzl = zzaj.zzl(str);
        if (zzl != null) {
            this.zzahl = (Long) zzl.get(Integer.valueOf(0));
            this.zzahm = (Long) zzl.get(Integer.valueOf(1));
        }
    }
}
