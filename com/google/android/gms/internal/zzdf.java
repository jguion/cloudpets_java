package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@zziy
public class zzdf {
    private final Collection<zzde> zzbah = new ArrayList();
    private final Collection<zzde<String>> zzbai = new ArrayList();
    private final Collection<zzde<String>> zzbaj = new ArrayList();

    public void zza(zzde com_google_android_gms_internal_zzde) {
        this.zzbah.add(com_google_android_gms_internal_zzde);
    }

    public void zzb(zzde<String> com_google_android_gms_internal_zzde_java_lang_String) {
        this.zzbai.add(com_google_android_gms_internal_zzde_java_lang_String);
    }

    public void zzc(zzde<String> com_google_android_gms_internal_zzde_java_lang_String) {
        this.zzbaj.add(com_google_android_gms_internal_zzde_java_lang_String);
    }

    public List<String> zzkr() {
        List<String> arrayList = new ArrayList();
        for (zzde com_google_android_gms_internal_zzde : this.zzbai) {
            String str = (String) com_google_android_gms_internal_zzde.get();
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public List<String> zzks() {
        List<String> zzkr = zzkr();
        for (zzde com_google_android_gms_internal_zzde : this.zzbaj) {
            String str = (String) com_google_android_gms_internal_zzde.get();
            if (str != null) {
                zzkr.add(str);
            }
        }
        return zzkr;
    }
}
