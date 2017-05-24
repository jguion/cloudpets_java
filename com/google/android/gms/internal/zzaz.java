package com.google.android.gms.internal;

import java.util.HashMap;

public class zzaz extends zzaj<Integer, Object> {
    public Long zzahi;
    public Boolean zzahj;
    public Boolean zzahk;

    public zzaz(String str) {
        zzk(str);
    }

    protected HashMap<Integer, Object> zzar() {
        HashMap<Integer, Object> hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), this.zzahi);
        hashMap.put(Integer.valueOf(1), this.zzahj);
        hashMap.put(Integer.valueOf(2), this.zzahk);
        return hashMap;
    }

    protected void zzk(String str) {
        HashMap zzl = zzaj.zzl(str);
        if (zzl != null) {
            this.zzahi = (Long) zzl.get(Integer.valueOf(0));
            this.zzahj = (Boolean) zzl.get(Integer.valueOf(1));
            this.zzahk = (Boolean) zzl.get(Integer.valueOf(2));
        }
    }
}
