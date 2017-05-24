package com.google.android.gms.internal;

import java.util.HashMap;

public class zzal extends zzaj<Integer, Object> {
    public String zzdo;
    public String zzdt;
    public String zzee;
    public String zzef;
    public long zzye;

    public zzal() {
        this.zzdo = "E";
        this.zzye = -1;
        this.zzdt = "E";
        this.zzee = "E";
        this.zzef = "E";
    }

    public zzal(String str) {
        this();
        zzk(str);
    }

    protected HashMap<Integer, Object> zzar() {
        HashMap<Integer, Object> hashMap = new HashMap();
        hashMap.put(Integer.valueOf(0), this.zzdo);
        hashMap.put(Integer.valueOf(4), this.zzef);
        hashMap.put(Integer.valueOf(3), this.zzee);
        hashMap.put(Integer.valueOf(2), this.zzdt);
        hashMap.put(Integer.valueOf(1), Long.valueOf(this.zzye));
        return hashMap;
    }

    protected void zzk(String str) {
        HashMap zzl = zzaj.zzl(str);
        if (zzl != null) {
            this.zzdo = zzl.get(Integer.valueOf(0)) == null ? "E" : (String) zzl.get(Integer.valueOf(0));
            this.zzye = zzl.get(Integer.valueOf(1)) == null ? -1 : ((Long) zzl.get(Integer.valueOf(1))).longValue();
            this.zzdt = zzl.get(Integer.valueOf(2)) == null ? "E" : (String) zzl.get(Integer.valueOf(2));
            this.zzee = zzl.get(Integer.valueOf(3)) == null ? "E" : (String) zzl.get(Integer.valueOf(3));
            this.zzef = zzl.get(Integer.valueOf(4)) == null ? "E" : (String) zzl.get(Integer.valueOf(4));
        }
    }
}
