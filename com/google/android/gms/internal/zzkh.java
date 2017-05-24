package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.zzct.zzb;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Future;

@zziy
public class zzkh implements zzb, zzkp.zzb {
    private Context mContext;
    private final Object zzakd = new Object();
    private zzcm zzaln;
    private VersionInfoParcel zzanh;
    private boolean zzaom = false;
    private zzcs zzaur = null;
    private String zzbnw;
    private boolean zzckt = true;
    private boolean zzcku = true;
    private boolean zzclc = false;
    private final String zzcpl;
    private final zzki zzcpm;
    private BigInteger zzcpn = BigInteger.ONE;
    private final HashSet<zzkf> zzcpo = new HashSet();
    private final HashMap<String, zzkk> zzcpp = new HashMap();
    private boolean zzcpq = false;
    private int zzcpr = 0;
    private zzdk zzcps = null;
    private zzcu zzcpt = null;
    private String zzcpu;
    private Boolean zzcpv = null;
    private boolean zzcpw = false;
    private boolean zzcpx = false;
    private boolean zzcpy = false;
    private String zzcpz = "";
    private long zzcqa = 0;
    private long zzcqb = 0;
    private int zzcqc = -1;

    public zzkh(zzkr com_google_android_gms_internal_zzkr) {
        this.zzcpl = com_google_android_gms_internal_zzkr.zzui();
        this.zzcpm = new zzki(this.zzcpl);
    }

    public Resources getResources() {
        if (this.zzanh.zzctu) {
            return this.mContext.getResources();
        }
        try {
            zzsu zza = zzsu.zza(this.mContext, zzsu.Oy, ModuleDescriptor.MODULE_ID);
            return zza != null ? zza.zzbdy().getResources() : null;
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public String getSessionId() {
        return this.zzcpl;
    }

    public Bundle zza(Context context, zzkj com_google_android_gms_internal_zzkj, String str) {
        Bundle bundle;
        synchronized (this.zzakd) {
            bundle = new Bundle();
            bundle.putBundle("app", this.zzcpm.zze(context, str));
            Bundle bundle2 = new Bundle();
            for (String str2 : this.zzcpp.keySet()) {
                bundle2.putBundle(str2, ((zzkk) this.zzcpp.get(str2)).toBundle());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList arrayList = new ArrayList();
            Iterator it = this.zzcpo.iterator();
            while (it.hasNext()) {
                arrayList.add(((zzkf) it.next()).toBundle());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            com_google_android_gms_internal_zzkj.zza(this.zzcpo);
            this.zzcpo.clear();
        }
        return bundle;
    }

    public void zza(zzkf com_google_android_gms_internal_zzkf) {
        synchronized (this.zzakd) {
            this.zzcpo.add(com_google_android_gms_internal_zzkf);
        }
    }

    public void zza(String str, zzkk com_google_android_gms_internal_zzkk) {
        synchronized (this.zzakd) {
            this.zzcpp.put(str, com_google_android_gms_internal_zzkk);
        }
    }

    public void zza(Thread thread) {
        zzix.zza(this.mContext, thread, this.zzanh);
    }

    public void zza(Throwable th, String str) {
        new zzix(this.mContext, this.zzanh, null, null).zza(th, str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzcu zzaa(android.content.Context r9) {
        /*
        r8 = this;
        r1 = 0;
        r0 = com.google.android.gms.internal.zzdi.zzbcd;
        r0 = r0.get();
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x001b;
    L_0x000f:
        r0 = com.google.android.gms.common.util.zzs.zzaxn();
        if (r0 == 0) goto L_0x001b;
    L_0x0015:
        r0 = r8.zztj();
        if (r0 == 0) goto L_0x001d;
    L_0x001b:
        r0 = r1;
    L_0x001c:
        return r0;
    L_0x001d:
        r2 = r8.zzakd;
        monitor-enter(r2);
        r0 = android.os.Looper.getMainLooper();	 Catch:{ all -> 0x0057 }
        if (r0 == 0) goto L_0x0028;
    L_0x0026:
        if (r9 != 0) goto L_0x002b;
    L_0x0028:
        monitor-exit(r2);	 Catch:{ all -> 0x0057 }
        r0 = r1;
        goto L_0x001c;
    L_0x002b:
        r0 = r8.zzaur;	 Catch:{ all -> 0x0057 }
        if (r0 != 0) goto L_0x0036;
    L_0x002f:
        r0 = new com.google.android.gms.internal.zzcs;	 Catch:{ all -> 0x0057 }
        r0.<init>();	 Catch:{ all -> 0x0057 }
        r8.zzaur = r0;	 Catch:{ all -> 0x0057 }
    L_0x0036:
        r0 = r8.zzcpt;	 Catch:{ all -> 0x0057 }
        if (r0 != 0) goto L_0x004e;
    L_0x003a:
        r0 = new com.google.android.gms.internal.zzcu;	 Catch:{ all -> 0x0057 }
        r1 = r8.zzaur;	 Catch:{ all -> 0x0057 }
        r3 = new com.google.android.gms.internal.zzix;	 Catch:{ all -> 0x0057 }
        r4 = r8.mContext;	 Catch:{ all -> 0x0057 }
        r5 = r8.zzanh;	 Catch:{ all -> 0x0057 }
        r6 = 0;
        r7 = 0;
        r3.<init>(r4, r5, r6, r7);	 Catch:{ all -> 0x0057 }
        r0.<init>(r1, r3);	 Catch:{ all -> 0x0057 }
        r8.zzcpt = r0;	 Catch:{ all -> 0x0057 }
    L_0x004e:
        r0 = r8.zzcpt;	 Catch:{ all -> 0x0057 }
        r0.zzim();	 Catch:{ all -> 0x0057 }
        r0 = r8.zzcpt;	 Catch:{ all -> 0x0057 }
        monitor-exit(r2);	 Catch:{ all -> 0x0057 }
        goto L_0x001c;
    L_0x0057:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0057 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzkh.zzaa(android.content.Context):com.google.android.gms.internal.zzcu");
    }

    public void zzaf(boolean z) {
        synchronized (this.zzakd) {
            if (this.zzcku != z) {
                zzkp.zze(this.mContext, z);
            }
            this.zzcku = z;
            zzcu zzaa = zzaa(this.mContext);
            if (!(zzaa == null || zzaa.isAlive())) {
                com.google.android.gms.ads.internal.util.client.zzb.zzde("start fetching content...");
                zzaa.zzim();
            }
        }
    }

    public void zzag(boolean z) {
        this.zzcpy = z;
    }

    public void zzah(boolean z) {
        synchronized (this.zzakd) {
            this.zzcpw = z;
        }
    }

    @TargetApi(23)
    public void zzb(Context context, VersionInfoParcel versionInfoParcel) {
        synchronized (this.zzakd) {
            if (!this.zzaom) {
                this.mContext = context.getApplicationContext();
                this.zzanh = versionInfoParcel;
                zzu.zzgc().zza(this);
                zzkp.zza(context, (zzkp.zzb) this);
                zzkp.zzb(context, this);
                zzkp.zzc(context, (zzkp.zzb) this);
                zzkp.zzd(context, this);
                zzkp.zze(context, (zzkp.zzb) this);
                zzkp.zzf(context, (zzkp.zzb) this);
                zzkp.zzg(context, this);
                zza(Thread.currentThread());
                this.zzbnw = zzu.zzfz().zzg(context, versionInfoParcel.zzcs);
                if (zzs.zzaxv() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
                    this.zzcpx = true;
                }
                this.zzaln = new zzcm(context.getApplicationContext(), this.zzanh, zzu.zzfz().zzc(context, versionInfoParcel));
                zztz();
                zzu.zzgn().zzt(this.mContext);
                this.zzaom = true;
            }
        }
    }

    public void zzb(Boolean bool) {
        synchronized (this.zzakd) {
            this.zzcpv = bool;
        }
    }

    public void zzb(HashSet<zzkf> hashSet) {
        synchronized (this.zzakd) {
            this.zzcpo.addAll(hashSet);
        }
    }

    public Future zzbc(int i) {
        Future zza;
        synchronized (this.zzakd) {
            this.zzcqc = i;
            zza = zzkp.zza(this.mContext, i);
        }
        return zza;
    }

    public Future zzc(Context context, boolean z) {
        Future zzc;
        synchronized (this.zzakd) {
            if (z != this.zzckt) {
                this.zzckt = z;
                zzc = zzkp.zzc(context, z);
            } else {
                zzc = null;
            }
        }
        return zzc;
    }

    public Future zzct(String str) {
        Future zzf;
        synchronized (this.zzakd) {
            if (str != null) {
                if (!str.equals(this.zzcpu)) {
                    this.zzcpu = str;
                    zzf = zzkp.zzf(this.mContext, str);
                }
            }
            zzf = null;
        }
        return zzf;
    }

    public Future zzd(Context context, String str) {
        Future zza;
        this.zzcqa = zzu.zzgf().currentTimeMillis();
        synchronized (this.zzakd) {
            if (str != null) {
                if (!str.equals(this.zzcpz)) {
                    this.zzcpz = str;
                    zza = zzkp.zza(context, str, this.zzcqa);
                }
            }
            zza = null;
        }
        return zza;
    }

    public Future zzd(Context context, boolean z) {
        Future zzf;
        synchronized (this.zzakd) {
            if (z != this.zzclc) {
                this.zzclc = z;
                zzf = zzkp.zzf(context, z);
            } else {
                zzf = null;
            }
        }
        return zzf;
    }

    public void zzh(Bundle bundle) {
        synchronized (this.zzakd) {
            this.zzckt = bundle.getBoolean("use_https", this.zzckt);
            this.zzcpr = bundle.getInt("webview_cache_version", this.zzcpr);
            if (bundle.containsKey("content_url_opted_out")) {
                zzaf(bundle.getBoolean("content_url_opted_out"));
            }
            if (bundle.containsKey("content_url_hashes")) {
                this.zzcpu = bundle.getString("content_url_hashes");
            }
            this.zzclc = bundle.getBoolean("auto_collect_location", this.zzclc);
            this.zzcpz = bundle.containsKey("app_settings_json") ? bundle.getString("app_settings_json") : this.zzcpz;
            this.zzcqa = bundle.getLong("app_settings_last_update_ms", this.zzcqa);
            this.zzcqb = bundle.getLong("app_last_background_time_ms", this.zzcqb);
            this.zzcqc = bundle.getInt("request_in_session_count", this.zzcqc);
        }
    }

    public void zzk(boolean z) {
        if (!z) {
            zzo(zzu.zzgf().currentTimeMillis());
            zzbc(this.zzcpm.zztu());
        } else if (zzu.zzgf().currentTimeMillis() - this.zzcqb > ((Long) zzdi.zzbdd.get()).longValue()) {
            this.zzcpm.zzbd(-1);
        } else {
            this.zzcpm.zzbd(this.zzcqc);
        }
    }

    public Future zzo(long j) {
        Future zza;
        synchronized (this.zzakd) {
            if (this.zzcqb < j) {
                this.zzcqb = j;
                zza = zzkp.zza(this.mContext, j);
            } else {
                zza = null;
            }
        }
        return zza;
    }

    public boolean zztj() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcku;
        }
        return z;
    }

    public String zztk() {
        String bigInteger;
        synchronized (this.zzakd) {
            bigInteger = this.zzcpn.toString();
            this.zzcpn = this.zzcpn.add(BigInteger.ONE);
        }
        return bigInteger;
    }

    public zzki zztl() {
        zzki com_google_android_gms_internal_zzki;
        synchronized (this.zzakd) {
            com_google_android_gms_internal_zzki = this.zzcpm;
        }
        return com_google_android_gms_internal_zzki;
    }

    public zzdk zztm() {
        zzdk com_google_android_gms_internal_zzdk;
        synchronized (this.zzakd) {
            com_google_android_gms_internal_zzdk = this.zzcps;
        }
        return com_google_android_gms_internal_zzdk;
    }

    public boolean zztn() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcpq;
            this.zzcpq = true;
        }
        return z;
    }

    public boolean zzto() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzckt || this.zzcpx;
        }
        return z;
    }

    public String zztp() {
        String str;
        synchronized (this.zzakd) {
            str = this.zzbnw;
        }
        return str;
    }

    public String zztq() {
        String str;
        synchronized (this.zzakd) {
            str = this.zzcpu;
        }
        return str;
    }

    public Boolean zztr() {
        Boolean bool;
        synchronized (this.zzakd) {
            bool = this.zzcpv;
        }
        return bool;
    }

    public boolean zzts() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzclc;
        }
        return z;
    }

    public long zztt() {
        long j;
        synchronized (this.zzakd) {
            j = this.zzcqb;
        }
        return j;
    }

    public int zztu() {
        int i;
        synchronized (this.zzakd) {
            i = this.zzcqc;
        }
        return i;
    }

    public boolean zztv() {
        return this.zzcpy;
    }

    public zzkg zztw() {
        zzkg com_google_android_gms_internal_zzkg;
        synchronized (this.zzakd) {
            com_google_android_gms_internal_zzkg = new zzkg(this.zzcpz, this.zzcqa);
        }
        return com_google_android_gms_internal_zzkg;
    }

    public zzcm zztx() {
        return this.zzaln;
    }

    public boolean zzty() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcpw;
        }
        return z;
    }

    void zztz() {
        try {
            this.zzcps = zzu.zzgg().zza(new zzdj(this.mContext, this.zzanh.zzcs));
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot initialize CSI reporter.", e);
        }
    }
}
