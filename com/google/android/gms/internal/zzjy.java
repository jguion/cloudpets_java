package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzke.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Future;
import org.json.JSONObject;

@zziy
public class zzjy extends zzkm implements zzjx {
    private final Context mContext;
    private final Object zzakd;
    private final zza zzcck;
    private final long zzcne;
    private final ArrayList<Future> zzcnp;
    private final ArrayList<String> zzcnq;
    private final HashMap<String, zzjs> zzcnr;
    private final List<zzjt> zzcns;
    private final HashSet<String> zzcnt;
    private final zzjr zzcnu;

    public zzjy(Context context, zza com_google_android_gms_internal_zzke_zza, zzjr com_google_android_gms_internal_zzjr) {
        this(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzjr, ((Long) zzdi.zzbda.get()).longValue());
    }

    zzjy(Context context, zza com_google_android_gms_internal_zzke_zza, zzjr com_google_android_gms_internal_zzjr, long j) {
        this.zzcnp = new ArrayList();
        this.zzcnq = new ArrayList();
        this.zzcnr = new HashMap();
        this.zzcns = new ArrayList();
        this.zzcnt = new HashSet();
        this.zzakd = new Object();
        this.mContext = context;
        this.zzcck = com_google_android_gms_internal_zzke_zza;
        this.zzcnu = com_google_android_gms_internal_zzjr;
        this.zzcne = j;
    }

    private zzke zza(int i, @Nullable String str, @Nullable zzgg com_google_android_gms_internal_zzgg) {
        return new zzke(this.zzcck.zzcix.zzcfu, null, this.zzcck.zzcop.zzbsd, i, this.zzcck.zzcop.zzbse, this.zzcck.zzcop.zzche, this.zzcck.zzcop.orientation, this.zzcck.zzcop.zzbsj, this.zzcck.zzcix.zzcfx, this.zzcck.zzcop.zzchc, com_google_android_gms_internal_zzgg, null, str, this.zzcck.zzcof, null, this.zzcck.zzcop.zzchd, this.zzcck.zzaqz, this.zzcck.zzcop.zzchb, this.zzcck.zzcoj, this.zzcck.zzcop.zzchg, this.zzcck.zzcop.zzchh, this.zzcck.zzcod, null, this.zzcck.zzcop.zzchr, this.zzcck.zzcop.zzchs, this.zzcck.zzcop.zzcht, this.zzcck.zzcop.zzchu, this.zzcck.zzcop.zzchv, zzsu(), this.zzcck.zzcop.zzbsg, this.zzcck.zzcop.zzchy);
    }

    private zzke zza(String str, zzgg com_google_android_gms_internal_zzgg) {
        return zza(-2, str, com_google_android_gms_internal_zzgg);
    }

    private static String zza(zzjt com_google_android_gms_internal_zzjt) {
        String str = com_google_android_gms_internal_zzjt.zzbro;
        int zzao = zzao(com_google_android_gms_internal_zzjt.errorCode);
        return new StringBuilder(String.valueOf(str).length() + 33).append(str).append(".").append(zzao).append(".").append(com_google_android_gms_internal_zzjt.zzbtj).toString();
    }

    private void zza(String str, String str2, zzgg com_google_android_gms_internal_zzgg) {
        synchronized (this.zzakd) {
            zzjz zzcl = this.zzcnu.zzcl(str);
            if (zzcl == null || zzcl.zzsw() == null || zzcl.zzsv() == null) {
                this.zzcns.add(new zzjt.zza().zzco(com_google_android_gms_internal_zzgg.zzbro).zzcn(str).zzl(0).zzaz(7).zzss());
                return;
            }
            zzkm zza = zza(str, str2, com_google_android_gms_internal_zzgg, zzcl);
            this.zzcnp.add((Future) zza.zzqw());
            this.zzcnq.add(str);
            this.zzcnr.put(str, zza);
        }
    }

    private static int zzao(int i) {
        switch (i) {
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 4;
            case 6:
                return 0;
            case 7:
                return 3;
            default:
                return 6;
        }
    }

    private zzke zzst() {
        return zza(3, null, null);
    }

    private String zzsu() {
        StringBuilder stringBuilder = new StringBuilder("");
        if (this.zzcns == null) {
            return stringBuilder.toString();
        }
        for (zzjt com_google_android_gms_internal_zzjt : this.zzcns) {
            if (!(com_google_android_gms_internal_zzjt == null || TextUtils.isEmpty(com_google_android_gms_internal_zzjt.zzbro))) {
                stringBuilder.append(String.valueOf(zza(com_google_android_gms_internal_zzjt)).concat("_"));
            }
        }
        return stringBuilder.substring(0, Math.max(0, stringBuilder.length() - 1));
    }

    public void onStop() {
    }

    protected zzjs zza(String str, String str2, zzgg com_google_android_gms_internal_zzgg, zzjz com_google_android_gms_internal_zzjz) {
        return new zzjs(this.mContext, str, str2, com_google_android_gms_internal_zzgg, this.zzcck, com_google_android_gms_internal_zzjz, this, this.zzcne);
    }

    public void zza(String str, int i) {
    }

    public void zzcm(String str) {
        synchronized (this.zzakd) {
            this.zzcnt.add(str);
        }
    }

    public void zzfc() {
        zzjs com_google_android_gms_internal_zzjs;
        final zzke zza;
        for (zzgg com_google_android_gms_internal_zzgg : this.zzcck.zzcof.zzbsb) {
            String str = com_google_android_gms_internal_zzgg.zzbrt;
            for (String str2 : com_google_android_gms_internal_zzgg.zzbrn) {
                String str22;
                if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str22) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str22)) {
                    try {
                        str22 = new JSONObject(str).getString("class_name");
                    } catch (Throwable e) {
                        zzb.zzb("Unable to determine custom event class name, skipping...", e);
                    }
                }
                zza(str22, str, com_google_android_gms_internal_zzgg);
            }
        }
        int i = 0;
        while (i < this.zzcnp.size()) {
            String str3;
            try {
                ((Future) this.zzcnp.get(i)).get();
                synchronized (this.zzakd) {
                    str3 = (String) this.zzcnq.get(i);
                    if (!TextUtils.isEmpty(str3)) {
                        com_google_android_gms_internal_zzjs = (zzjs) this.zzcnr.get(str3);
                        if (com_google_android_gms_internal_zzjs != null) {
                            this.zzcns.add(com_google_android_gms_internal_zzjs.zzsp());
                        }
                    }
                }
                synchronized (this.zzakd) {
                    if (this.zzcnt.contains(this.zzcnq.get(i))) {
                        str3 = (String) this.zzcnq.get(i);
                        zza = zza(str3, this.zzcnr.get(str3) != null ? ((zzjs) this.zzcnr.get(str3)).zzsq() : null);
                        com.google.android.gms.ads.internal.util.client.zza.zzctj.post(new Runnable(this) {
                            final /* synthetic */ zzjy zzcnv;

                            public void run() {
                                this.zzcnv.zzcnu.zzb(zza);
                            }
                        });
                        return;
                    }
                }
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                synchronized (this.zzakd) {
                    str3 = (String) this.zzcnq.get(i);
                    if (!TextUtils.isEmpty(str3)) {
                        com_google_android_gms_internal_zzjs = (zzjs) this.zzcnr.get(str3);
                        if (com_google_android_gms_internal_zzjs != null) {
                            this.zzcns.add(com_google_android_gms_internal_zzjs.zzsp());
                        }
                    }
                }
            } catch (Throwable e3) {
                zzb.zzd("Unable to resolve rewarded adapter.", e3);
                synchronized (this.zzakd) {
                    str3 = (String) this.zzcnq.get(i);
                    if (!TextUtils.isEmpty(str3)) {
                        com_google_android_gms_internal_zzjs = (zzjs) this.zzcnr.get(str3);
                        if (com_google_android_gms_internal_zzjs != null) {
                            this.zzcns.add(com_google_android_gms_internal_zzjs.zzsp());
                        }
                    }
                }
            } catch (Throwable e32) {
                Throwable th = e32;
                synchronized (this.zzakd) {
                    str3 = (String) this.zzcnq.get(i);
                    if (!TextUtils.isEmpty(str3)) {
                        com_google_android_gms_internal_zzjs = (zzjs) this.zzcnr.get(str3);
                        if (com_google_android_gms_internal_zzjs != null) {
                            this.zzcns.add(com_google_android_gms_internal_zzjs.zzsp());
                        }
                    }
                }
            }
        }
        zza = zzst();
        com.google.android.gms.ads.internal.util.client.zza.zzctj.post(new Runnable(this) {
            final /* synthetic */ zzjy zzcnv;

            public void run() {
                this.zzcnv.zzcnu.zzb(zza);
            }
        });
        return;
        i++;
    }
}
