package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zziy
public class zzfi implements Iterable<zzfh> {
    private final List<zzfh> zzbnv = new LinkedList();

    private zzfh zzg(zzlt com_google_android_gms_internal_zzlt) {
        Iterator it = zzu.zzgw().iterator();
        while (it.hasNext()) {
            zzfh com_google_android_gms_internal_zzfh = (zzfh) it.next();
            if (com_google_android_gms_internal_zzfh.zzbkr == com_google_android_gms_internal_zzlt) {
                return com_google_android_gms_internal_zzfh;
            }
        }
        return null;
    }

    public Iterator<zzfh> iterator() {
        return this.zzbnv.iterator();
    }

    public void zza(zzfh com_google_android_gms_internal_zzfh) {
        this.zzbnv.add(com_google_android_gms_internal_zzfh);
    }

    public void zzb(zzfh com_google_android_gms_internal_zzfh) {
        this.zzbnv.remove(com_google_android_gms_internal_zzfh);
    }

    public boolean zze(zzlt com_google_android_gms_internal_zzlt) {
        zzfh zzg = zzg(com_google_android_gms_internal_zzlt);
        if (zzg == null) {
            return false;
        }
        zzg.zzbns.abort();
        return true;
    }

    public boolean zzf(zzlt com_google_android_gms_internal_zzlt) {
        return zzg(com_google_android_gms_internal_zzlt) != null;
    }

    public int zzmi() {
        return this.zzbnv.size();
    }
}
