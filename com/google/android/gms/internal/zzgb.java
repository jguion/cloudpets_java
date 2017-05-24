package com.google.android.gms.internal;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

@zziy
public class zzgb implements zzga {
    private final zzfz zzbrj;
    private final HashSet<SimpleEntry<String, zzev>> zzbrk = new HashSet();

    public zzgb(zzfz com_google_android_gms_internal_zzfz) {
        this.zzbrj = com_google_android_gms_internal_zzfz;
    }

    public void zza(String str, zzev com_google_android_gms_internal_zzev) {
        this.zzbrj.zza(str, com_google_android_gms_internal_zzev);
        this.zzbrk.add(new SimpleEntry(str, com_google_android_gms_internal_zzev));
    }

    public void zza(String str, JSONObject jSONObject) {
        this.zzbrj.zza(str, jSONObject);
    }

    public void zzb(String str, zzev com_google_android_gms_internal_zzev) {
        this.zzbrj.zzb(str, com_google_android_gms_internal_zzev);
        this.zzbrk.remove(new SimpleEntry(str, com_google_android_gms_internal_zzev));
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzbrj.zzb(str, jSONObject);
    }

    public void zzj(String str, String str2) {
        this.zzbrj.zzj(str, str2);
    }

    public void zznd() {
        Iterator it = this.zzbrk.iterator();
        while (it.hasNext()) {
            SimpleEntry simpleEntry = (SimpleEntry) it.next();
            String str = "Unregistering eventhandler: ";
            String valueOf = String.valueOf(((zzev) simpleEntry.getValue()).toString());
            zzkn.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzbrj.zzb((String) simpleEntry.getKey(), (zzev) simpleEntry.getValue());
        }
        this.zzbrk.clear();
    }
}
