package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;
import java.util.LinkedList;

@zziy
class zzfs {
    private final String zzang;
    private final LinkedList<zza> zzbph = new LinkedList();
    private AdRequestParcel zzbpi;
    private final int zzbpj;
    private boolean zzbpk;

    class zza {
        zzl zzbpl;
        @Nullable
        AdRequestParcel zzbpm;
        zzfo zzbpn;
        long zzbpo;
        boolean zzbpp;
        boolean zzbpq;
        final /* synthetic */ zzfs zzbpr;

        zza(zzfs com_google_android_gms_internal_zzfs, zzfn com_google_android_gms_internal_zzfn) {
            this.zzbpr = com_google_android_gms_internal_zzfs;
            this.zzbpl = com_google_android_gms_internal_zzfn.zzbg(com_google_android_gms_internal_zzfs.zzang);
            this.zzbpn = new zzfo();
            this.zzbpn.zzc(this.zzbpl);
        }

        zza(zzfs com_google_android_gms_internal_zzfs, zzfn com_google_android_gms_internal_zzfn, AdRequestParcel adRequestParcel) {
            this(com_google_android_gms_internal_zzfs, com_google_android_gms_internal_zzfn);
            this.zzbpm = adRequestParcel;
        }

        void zzmt() {
            if (!this.zzbpp) {
                this.zzbpq = this.zzbpl.zzb(zzfq.zzl(this.zzbpm != null ? this.zzbpm : this.zzbpr.zzbpi));
                this.zzbpp = true;
                this.zzbpo = zzu.zzgf().currentTimeMillis();
            }
        }
    }

    zzfs(AdRequestParcel adRequestParcel, String str, int i) {
        zzac.zzy(adRequestParcel);
        zzac.zzy(str);
        this.zzbpi = adRequestParcel;
        this.zzang = str;
        this.zzbpj = i;
    }

    String getAdUnitId() {
        return this.zzang;
    }

    int getNetworkType() {
        return this.zzbpj;
    }

    int size() {
        return this.zzbph.size();
    }

    void zza(zzfn com_google_android_gms_internal_zzfn, AdRequestParcel adRequestParcel) {
        this.zzbph.add(new zza(this, com_google_android_gms_internal_zzfn, adRequestParcel));
    }

    void zzb(zzfn com_google_android_gms_internal_zzfn) {
        zza com_google_android_gms_internal_zzfs_zza = new zza(this, com_google_android_gms_internal_zzfn);
        this.zzbph.add(com_google_android_gms_internal_zzfs_zza);
        com_google_android_gms_internal_zzfs_zza.zzmt();
    }

    AdRequestParcel zzmo() {
        return this.zzbpi;
    }

    int zzmp() {
        Iterator it = this.zzbph.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((zza) it.next()).zzbpp ? i + 1 : i;
        }
        return i;
    }

    void zzmq() {
        Iterator it = this.zzbph.iterator();
        while (it.hasNext()) {
            ((zza) it.next()).zzmt();
        }
    }

    void zzmr() {
        this.zzbpk = true;
    }

    boolean zzms() {
        return this.zzbpk;
    }

    zza zzp(@Nullable AdRequestParcel adRequestParcel) {
        if (adRequestParcel != null) {
            this.zzbpi = adRequestParcel;
        }
        return (zza) this.zzbph.remove();
    }
}
