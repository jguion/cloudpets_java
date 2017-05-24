package com.google.android.gms.internal;

import java.util.HashMap;

public class zzbc extends zzaj<Integer, Long> {
    public Long zzaig;
    public Long zzaih;
    public Long zzaii;
    public Long zzaij;
    public Long zzaik;
    public Long zzail;
    public Long zzaim;
    public Long zzez;
    public Long zzfb;
    public Long zzff;
    public Long zzfg;

    public zzbc(String str) {
        zzk(str);
    }

    protected HashMap<Integer, Long> zzar() {
        HashMap<Integer, Long> hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), this.zzaig);
        hashMap.put(Integer.valueOf(1), this.zzaih);
        hashMap.put(Integer.valueOf(2), this.zzaii);
        hashMap.put(Integer.valueOf(3), this.zzfb);
        hashMap.put(Integer.valueOf(4), this.zzez);
        hashMap.put(Integer.valueOf(5), this.zzaij);
        hashMap.put(Integer.valueOf(6), this.zzaik);
        hashMap.put(Integer.valueOf(7), this.zzail);
        hashMap.put(Integer.valueOf(8), this.zzfg);
        hashMap.put(Integer.valueOf(9), this.zzff);
        hashMap.put(Integer.valueOf(10), this.zzaim);
        return hashMap;
    }

    protected void zzk(String str) {
        HashMap zzl = zzaj.zzl(str);
        if (zzl != null) {
            this.zzaig = (Long) zzl.get(Integer.valueOf(0));
            this.zzaih = (Long) zzl.get(Integer.valueOf(1));
            this.zzaii = (Long) zzl.get(Integer.valueOf(2));
            this.zzfb = (Long) zzl.get(Integer.valueOf(3));
            this.zzez = (Long) zzl.get(Integer.valueOf(4));
            this.zzaij = (Long) zzl.get(Integer.valueOf(5));
            this.zzaik = (Long) zzl.get(Integer.valueOf(6));
            this.zzail = (Long) zzl.get(Integer.valueOf(7));
            this.zzfg = (Long) zzl.get(Integer.valueOf(8));
            this.zzff = (Long) zzl.get(Integer.valueOf(9));
            this.zzaim = (Long) zzl.get(Integer.valueOf(10));
        }
    }
}
