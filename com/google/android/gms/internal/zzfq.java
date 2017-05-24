package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzjh.zza;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

@zziy
public class zzfq {
    private final Map<zzfr, zzfs> zzbpe = new HashMap();
    private final LinkedList<zzfr> zzbpf = new LinkedList();
    @Nullable
    private zzfn zzbpg;

    private static void zza(String str, zzfr com_google_android_gms_internal_zzfr) {
        if (zzb.zzbf(2)) {
            zzkn.v(String.format(str, new Object[]{com_google_android_gms_internal_zzfr}));
        }
    }

    private String[] zzbh(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), Key.STRING_CHARSET_NAME);
            }
            return split;
        } catch (UnsupportedEncodingException e) {
            return new String[0];
        }
    }

    private boolean zzbi(String str) {
        try {
            return Pattern.matches((String) zzdi.zzbdp.get(), str);
        } catch (Throwable e) {
            zzu.zzgd().zza(e, "InterstitialAdPool.isExcludedAdUnit");
            return false;
        }
    }

    private static void zzc(Bundle bundle, String str) {
        String[] split = str.split("/", 2);
        if (split.length != 0) {
            String str2 = split[0];
            if (split.length == 1) {
                bundle.remove(str2);
                return;
            }
            Bundle bundle2 = bundle.getBundle(str2);
            if (bundle2 != null) {
                zzc(bundle2, split[1]);
            }
        }
    }

    @Nullable
    static Bundle zzk(AdRequestParcel adRequestParcel) {
        Bundle bundle = adRequestParcel.zzawn;
        return bundle == null ? null : bundle.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
    }

    static AdRequestParcel zzl(AdRequestParcel adRequestParcel) {
        AdRequestParcel zzo = zzo(adRequestParcel);
        Bundle zzk = zzk(zzo);
        if (zzk == null) {
            zzk = new Bundle();
            zzo.zzawn.putBundle("com.google.ads.mediation.admob.AdMobAdapter", zzk);
        }
        zzk.putBoolean("_skipMediation", true);
        return zzo;
    }

    static boolean zzm(AdRequestParcel adRequestParcel) {
        Bundle bundle = adRequestParcel.zzawn;
        if (bundle == null) {
            return false;
        }
        bundle = bundle.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        return bundle != null && bundle.containsKey("_skipMediation");
    }

    private String zzmn() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = this.zzbpf.iterator();
            while (it.hasNext()) {
                stringBuilder.append(Base64.encodeToString(((zzfr) it.next()).toString().getBytes(Key.STRING_CHARSET_NAME), 0));
                if (it.hasNext()) {
                    stringBuilder.append("\u0000");
                }
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    private static AdRequestParcel zzn(AdRequestParcel adRequestParcel) {
        AdRequestParcel zzo = zzo(adRequestParcel);
        for (String zzc : ((String) zzdi.zzbdl.get()).split(",")) {
            zzc(zzo.zzawn, zzc);
        }
        return zzo;
    }

    static AdRequestParcel zzo(AdRequestParcel adRequestParcel) {
        Parcel obtain = Parcel.obtain();
        adRequestParcel.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        AdRequestParcel adRequestParcel2 = (AdRequestParcel) AdRequestParcel.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        AdRequestParcel.zzj(adRequestParcel2);
        return adRequestParcel2;
    }

    void flush() {
        while (this.zzbpf.size() > 0) {
            zzfr com_google_android_gms_internal_zzfr = (zzfr) this.zzbpf.remove();
            zzfs com_google_android_gms_internal_zzfs = (zzfs) this.zzbpe.get(com_google_android_gms_internal_zzfr);
            zza("Flushing interstitial queue for %s.", com_google_android_gms_internal_zzfr);
            while (com_google_android_gms_internal_zzfs.size() > 0) {
                com_google_android_gms_internal_zzfs.zzp(null).zzbpl.zzfa();
            }
            this.zzbpe.remove(com_google_android_gms_internal_zzfr);
        }
    }

    void restore() {
        if (this.zzbpg != null) {
            SharedPreferences sharedPreferences = this.zzbpg.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
            flush();
            try {
                Map hashMap = new HashMap();
                for (Entry entry : sharedPreferences.getAll().entrySet()) {
                    if (!((String) entry.getKey()).equals("PoolKeys")) {
                        zzfu zzbj = zzfu.zzbj((String) entry.getValue());
                        zzfr com_google_android_gms_internal_zzfr = new zzfr(zzbj.zzaow, zzbj.zzang, zzbj.zzbpj);
                        if (!this.zzbpe.containsKey(com_google_android_gms_internal_zzfr)) {
                            this.zzbpe.put(com_google_android_gms_internal_zzfr, new zzfs(zzbj.zzaow, zzbj.zzang, zzbj.zzbpj));
                            hashMap.put(com_google_android_gms_internal_zzfr.toString(), com_google_android_gms_internal_zzfr);
                            zza("Restored interstitial queue for %s.", com_google_android_gms_internal_zzfr);
                        }
                    }
                }
                for (Object obj : zzbh(sharedPreferences.getString("PoolKeys", ""))) {
                    zzfr com_google_android_gms_internal_zzfr2 = (zzfr) hashMap.get(obj);
                    if (this.zzbpe.containsKey(com_google_android_gms_internal_zzfr2)) {
                        this.zzbpf.add(com_google_android_gms_internal_zzfr2);
                    }
                }
            } catch (Throwable th) {
                zzu.zzgd().zza(th, "InterstitialAdPool.restore");
                zzb.zzd("Malformed preferences value for InterstitialAdPool.", th);
                this.zzbpe.clear();
                this.zzbpf.clear();
            }
        }
    }

    void save() {
        if (this.zzbpg != null) {
            Editor edit = this.zzbpg.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
            edit.clear();
            for (Entry entry : this.zzbpe.entrySet()) {
                zzfr com_google_android_gms_internal_zzfr = (zzfr) entry.getKey();
                zzfs com_google_android_gms_internal_zzfs = (zzfs) entry.getValue();
                if (com_google_android_gms_internal_zzfs.zzms()) {
                    edit.putString(com_google_android_gms_internal_zzfr.toString(), new zzfu(com_google_android_gms_internal_zzfs).zzmv());
                    zza("Saved interstitial queue for %s.", com_google_android_gms_internal_zzfr);
                }
            }
            edit.putString("PoolKeys", zzmn());
            edit.apply();
        }
    }

    @Nullable
    zza zza(AdRequestParcel adRequestParcel, String str) {
        if (zzbi(str)) {
            return null;
        }
        zzfs com_google_android_gms_internal_zzfs;
        int i = new zza(this.zzbpg.getApplicationContext()).zzsk().zzcmd;
        AdRequestParcel zzn = zzn(adRequestParcel);
        zzfr com_google_android_gms_internal_zzfr = new zzfr(zzn, str, i);
        zzfs com_google_android_gms_internal_zzfs2 = (zzfs) this.zzbpe.get(com_google_android_gms_internal_zzfr);
        if (com_google_android_gms_internal_zzfs2 == null) {
            zza("Interstitial pool created at %s.", com_google_android_gms_internal_zzfr);
            com_google_android_gms_internal_zzfs2 = new zzfs(zzn, str, i);
            this.zzbpe.put(com_google_android_gms_internal_zzfr, com_google_android_gms_internal_zzfs2);
            com_google_android_gms_internal_zzfs = com_google_android_gms_internal_zzfs2;
        } else {
            com_google_android_gms_internal_zzfs = com_google_android_gms_internal_zzfs2;
        }
        this.zzbpf.remove(com_google_android_gms_internal_zzfr);
        this.zzbpf.add(com_google_android_gms_internal_zzfr);
        com_google_android_gms_internal_zzfs.zzmr();
        while (this.zzbpf.size() > ((Integer) zzdi.zzbdm.get()).intValue()) {
            zzfr com_google_android_gms_internal_zzfr2 = (zzfr) this.zzbpf.remove();
            zzfs com_google_android_gms_internal_zzfs3 = (zzfs) this.zzbpe.get(com_google_android_gms_internal_zzfr2);
            zza("Evicting interstitial queue for %s.", com_google_android_gms_internal_zzfr2);
            while (com_google_android_gms_internal_zzfs3.size() > 0) {
                com_google_android_gms_internal_zzfs3.zzp(null).zzbpl.zzfa();
            }
            this.zzbpe.remove(com_google_android_gms_internal_zzfr2);
        }
        while (com_google_android_gms_internal_zzfs.size() > 0) {
            zza zzp = com_google_android_gms_internal_zzfs.zzp(zzn);
            if (!zzp.zzbpp || zzu.zzgf().currentTimeMillis() - zzp.zzbpo <= 1000 * ((long) ((Integer) zzdi.zzbdo.get()).intValue())) {
                String str2 = zzp.zzbpm != null ? " (inline) " : " ";
                zza(new StringBuilder(String.valueOf(str2).length() + 34).append("Pooled interstitial").append(str2).append("returned at %s.").toString(), com_google_android_gms_internal_zzfr);
                return zzp;
            }
            zza("Expired interstitial at %s.", com_google_android_gms_internal_zzfr);
        }
        return null;
    }

    void zza(zzfn com_google_android_gms_internal_zzfn) {
        if (this.zzbpg == null) {
            this.zzbpg = com_google_android_gms_internal_zzfn.zzml();
            restore();
        }
    }

    void zzb(AdRequestParcel adRequestParcel, String str) {
        if (this.zzbpg != null) {
            int i = new zza(this.zzbpg.getApplicationContext()).zzsk().zzcmd;
            AdRequestParcel zzn = zzn(adRequestParcel);
            zzfr com_google_android_gms_internal_zzfr = new zzfr(zzn, str, i);
            zzfs com_google_android_gms_internal_zzfs = (zzfs) this.zzbpe.get(com_google_android_gms_internal_zzfr);
            if (com_google_android_gms_internal_zzfs == null) {
                zza("Interstitial pool created at %s.", com_google_android_gms_internal_zzfr);
                com_google_android_gms_internal_zzfs = new zzfs(zzn, str, i);
                this.zzbpe.put(com_google_android_gms_internal_zzfr, com_google_android_gms_internal_zzfs);
            }
            com_google_android_gms_internal_zzfs.zza(this.zzbpg, adRequestParcel);
            com_google_android_gms_internal_zzfs.zzmr();
            zza("Inline entry added to the queue at %s.", com_google_android_gms_internal_zzfr);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void zzmm() {
        /*
        r9 = this;
        r8 = 2;
        r0 = r9.zzbpg;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r0 = r9.zzbpe;
        r0 = r0.entrySet();
        r3 = r0.iterator();
    L_0x0010:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x0076;
    L_0x0016:
        r0 = r3.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (com.google.android.gms.internal.zzfr) r1;
        r0 = r0.getValue();
        r0 = (com.google.android.gms.internal.zzfs) r0;
        r2 = com.google.android.gms.ads.internal.util.client.zzb.zzbf(r8);
        if (r2 == 0) goto L_0x0056;
    L_0x002e:
        r2 = r0.size();
        r4 = r0.zzmp();
        if (r4 >= r2) goto L_0x0056;
    L_0x0038:
        r5 = "Loading %s/%s pooled interstitials for %s.";
        r6 = 3;
        r6 = new java.lang.Object[r6];
        r7 = 0;
        r4 = r2 - r4;
        r4 = java.lang.Integer.valueOf(r4);
        r6[r7] = r4;
        r4 = 1;
        r2 = java.lang.Integer.valueOf(r2);
        r6[r4] = r2;
        r6[r8] = r1;
        r2 = java.lang.String.format(r5, r6);
        com.google.android.gms.internal.zzkn.v(r2);
    L_0x0056:
        r0.zzmq();
    L_0x0059:
        r4 = r0.size();
        r2 = com.google.android.gms.internal.zzdi.zzbdn;
        r2 = r2.get();
        r2 = (java.lang.Integer) r2;
        r2 = r2.intValue();
        if (r4 >= r2) goto L_0x0010;
    L_0x006b:
        r2 = "Pooling and loading one new interstitial for %s.";
        zza(r2, r1);
        r2 = r9.zzbpg;
        r0.zzb(r2);
        goto L_0x0059;
    L_0x0076:
        r9.save();
        goto L_0x0005;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfq.zzmm():void");
    }
}
