package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import java.util.ArrayList;
import java.util.List;

@zziy
public class zzgo implements zzgf {
    private final Context mContext;
    private final Object zzakd = new Object();
    private final zzdq zzalg;
    private final zzgq zzals;
    private final boolean zzatk;
    private final boolean zzazd;
    private final zzgh zzbsv;
    private final AdRequestInfoParcel zzbtk;
    private final long zzbtl;
    private final long zzbtm;
    private boolean zzbto = false;
    private List<zzgl> zzbtq = new ArrayList();
    private zzgk zzbtu;

    public zzgo(Context context, AdRequestInfoParcel adRequestInfoParcel, zzgq com_google_android_gms_internal_zzgq, zzgh com_google_android_gms_internal_zzgh, boolean z, boolean z2, long j, long j2, zzdq com_google_android_gms_internal_zzdq) {
        this.mContext = context;
        this.zzbtk = adRequestInfoParcel;
        this.zzals = com_google_android_gms_internal_zzgq;
        this.zzbsv = com_google_android_gms_internal_zzgh;
        this.zzatk = z;
        this.zzazd = z2;
        this.zzbtl = j;
        this.zzbtm = j2;
        this.zzalg = com_google_android_gms_internal_zzdq;
    }

    public void cancel() {
        synchronized (this.zzakd) {
            this.zzbto = true;
            if (this.zzbtu != null) {
                this.zzbtu.cancel();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzgl zzd(java.util.List<com.google.android.gms.internal.zzgg> r22) {
        /*
        r21 = this;
        r2 = "Starting mediation.";
        com.google.android.gms.ads.internal.util.client.zzb.zzdd(r2);
        r15 = new java.util.ArrayList;
        r15.<init>();
        r0 = r21;
        r2 = r0.zzalg;
        r16 = r2.zzla();
        r17 = r22.iterator();
    L_0x0016:
        r2 = r17.hasNext();
        if (r2 == 0) goto L_0x0133;
    L_0x001c:
        r7 = r17.next();
        r7 = (com.google.android.gms.internal.zzgg) r7;
        r3 = "Trying mediation network: ";
        r2 = r7.zzbrm;
        r2 = java.lang.String.valueOf(r2);
        r4 = r2.length();
        if (r4 == 0) goto L_0x0066;
    L_0x0030:
        r2 = r3.concat(r2);
    L_0x0034:
        com.google.android.gms.ads.internal.util.client.zzb.zzde(r2);
        r2 = r7.zzbrn;
        r18 = r2.iterator();
    L_0x003d:
        r2 = r18.hasNext();
        if (r2 == 0) goto L_0x0016;
    L_0x0043:
        r4 = r18.next();
        r4 = (java.lang.String) r4;
        r0 = r21;
        r2 = r0.zzalg;
        r19 = r2.zzla();
        r0 = r21;
        r0 = r0.zzakd;
        r20 = r0;
        monitor-enter(r20);
        r0 = r21;
        r2 = r0.zzbto;	 Catch:{ all -> 0x010a }
        if (r2 == 0) goto L_0x006c;
    L_0x005e:
        r2 = new com.google.android.gms.internal.zzgl;	 Catch:{ all -> 0x010a }
        r3 = -1;
        r2.<init>(r3);	 Catch:{ all -> 0x010a }
        monitor-exit(r20);	 Catch:{ all -> 0x010a }
    L_0x0065:
        return r2;
    L_0x0066:
        r2 = new java.lang.String;
        r2.<init>(r3);
        goto L_0x0034;
    L_0x006c:
        r2 = new com.google.android.gms.internal.zzgk;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r3 = r0.mContext;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r5 = r0.zzals;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r6 = r0.zzbsv;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r8 = r0.zzbtk;	 Catch:{ all -> 0x010a }
        r8 = r8.zzcfu;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r9 = r0.zzbtk;	 Catch:{ all -> 0x010a }
        r9 = r9.zzaqz;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r10 = r0.zzbtk;	 Catch:{ all -> 0x010a }
        r10 = r10.zzaqv;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r11 = r0.zzatk;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r12 = r0.zzazd;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r13 = r0.zzbtk;	 Catch:{ all -> 0x010a }
        r13 = r13.zzarn;	 Catch:{ all -> 0x010a }
        r0 = r21;
        r14 = r0.zzbtk;	 Catch:{ all -> 0x010a }
        r14 = r14.zzarr;	 Catch:{ all -> 0x010a }
        r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14);	 Catch:{ all -> 0x010a }
        r0 = r21;
        r0.zzbtu = r2;	 Catch:{ all -> 0x010a }
        monitor-exit(r20);	 Catch:{ all -> 0x010a }
        r0 = r21;
        r2 = r0.zzbtu;
        r0 = r21;
        r8 = r0.zzbtl;
        r0 = r21;
        r10 = r0.zzbtm;
        r2 = r2.zza(r8, r10);
        r0 = r21;
        r3 = r0.zzbtq;
        r3.add(r2);
        r3 = r2.zzbtd;
        if (r3 != 0) goto L_0x010d;
    L_0x00c3:
        r3 = "Adapter succeeded.";
        com.google.android.gms.ads.internal.util.client.zzb.zzdd(r3);
        r0 = r21;
        r3 = r0.zzalg;
        r5 = "mediation_network_succeed";
        r3.zzh(r5, r4);
        r3 = r15.isEmpty();
        if (r3 != 0) goto L_0x00e6;
    L_0x00d7:
        r0 = r21;
        r3 = r0.zzalg;
        r4 = "mediation_networks_fail";
        r5 = ",";
        r5 = android.text.TextUtils.join(r5, r15);
        r3.zzh(r4, r5);
    L_0x00e6:
        r0 = r21;
        r3 = r0.zzalg;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "mls";
        r4[r5] = r6;
        r0 = r19;
        r3.zza(r0, r4);
        r0 = r21;
        r3 = r0.zzalg;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "ttm";
        r4[r5] = r6;
        r0 = r16;
        r3.zza(r0, r4);
        goto L_0x0065;
    L_0x010a:
        r2 = move-exception;
        monitor-exit(r20);	 Catch:{ all -> 0x010a }
        throw r2;
    L_0x010d:
        r15.add(r4);
        r0 = r21;
        r3 = r0.zzalg;
        r4 = 1;
        r4 = new java.lang.String[r4];
        r5 = 0;
        r6 = "mlf";
        r4[r5] = r6;
        r0 = r19;
        r3.zza(r0, r4);
        r3 = r2.zzbtf;
        if (r3 == 0) goto L_0x003d;
    L_0x0125:
        r3 = com.google.android.gms.internal.zzkr.zzcrf;
        r4 = new com.google.android.gms.internal.zzgo$1;
        r0 = r21;
        r4.<init>(r0, r2);
        r3.post(r4);
        goto L_0x003d;
    L_0x0133:
        r2 = r15.isEmpty();
        if (r2 != 0) goto L_0x0148;
    L_0x0139:
        r0 = r21;
        r2 = r0.zzalg;
        r3 = "mediation_networks_fail";
        r4 = ",";
        r4 = android.text.TextUtils.join(r4, r15);
        r2.zzh(r3, r4);
    L_0x0148:
        r2 = new com.google.android.gms.internal.zzgl;
        r3 = 1;
        r2.<init>(r3);
        goto L_0x0065;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgo.zzd(java.util.List):com.google.android.gms.internal.zzgl");
    }

    public List<zzgl> zzne() {
        return this.zzbtq;
    }
}
