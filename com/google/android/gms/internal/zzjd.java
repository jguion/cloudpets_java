package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzfy.zzc;
import java.util.Map;
import java.util.concurrent.Future;

@zziy
public final class zzjd {
    private final Object zzakd = new Object();
    private String zzcaj;
    private String zzcjz;
    private zzlg<zzjg> zzcka = new zzlg();
    zzc zzckb;
    public final zzev zzckc = new zzev(this) {
        final /* synthetic */ zzjd zzckf;

        {
            this.zzckf = r1;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            synchronized (this.zzckf.zzakd) {
                if (this.zzckf.zzcka.isDone()) {
                } else if (this.zzckf.zzcaj.equals(map.get("request_id"))) {
                    zzjg com_google_android_gms_internal_zzjg = new zzjg(1, map);
                    String valueOf = String.valueOf(com_google_android_gms_internal_zzjg.getType());
                    String valueOf2 = String.valueOf(com_google_android_gms_internal_zzjg.zzsg());
                    zzb.zzdf(new StringBuilder((String.valueOf(valueOf).length() + 24) + String.valueOf(valueOf2).length()).append("Invalid ").append(valueOf).append(" request error: ").append(valueOf2).toString());
                    this.zzckf.zzcka.zzh(com_google_android_gms_internal_zzjg);
                }
            }
        }
    };
    public final zzev zzckd = new zzev(this) {
        final /* synthetic */ zzjd zzckf;

        {
            this.zzckf = r1;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            synchronized (this.zzckf.zzakd) {
                if (this.zzckf.zzcka.isDone()) {
                    return;
                }
                zzjg com_google_android_gms_internal_zzjg = new zzjg(-2, map);
                if (this.zzckf.zzcaj.equals(com_google_android_gms_internal_zzjg.getRequestId())) {
                    String url = com_google_android_gms_internal_zzjg.getUrl();
                    if (url == null) {
                        zzb.zzdf("URL missing in loadAdUrl GMSG.");
                        return;
                    }
                    if (url.contains("%40mediation_adapters%40")) {
                        String replaceAll = url.replaceAll("%40mediation_adapters%40", zzkl.zza(com_google_android_gms_internal_zzlt.getContext(), (String) map.get("check_adapters"), this.zzckf.zzcjz));
                        com_google_android_gms_internal_zzjg.setUrl(replaceAll);
                        url = "Ad request URL modified to ";
                        replaceAll = String.valueOf(replaceAll);
                        zzkn.v(replaceAll.length() != 0 ? url.concat(replaceAll) : new String(url));
                    }
                    this.zzckf.zzcka.zzh(com_google_android_gms_internal_zzjg);
                    return;
                }
            }
        }
    };
    public final zzev zzcke = new zzev(this) {
        final /* synthetic */ zzjd zzckf;

        {
            this.zzckf = r1;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            synchronized (this.zzckf.zzakd) {
                if (this.zzckf.zzcka.isDone()) {
                    return;
                }
                zzjg com_google_android_gms_internal_zzjg = new zzjg(-2, map);
                if (this.zzckf.zzcaj.equals(com_google_android_gms_internal_zzjg.getRequestId())) {
                    com_google_android_gms_internal_zzjg.zzsj();
                    this.zzckf.zzcka.zzh(com_google_android_gms_internal_zzjg);
                    return;
                }
            }
        }
    };

    public zzjd(String str, String str2) {
        this.zzcjz = str2;
        this.zzcaj = str;
    }

    public void zzb(zzc com_google_android_gms_internal_zzfy_zzc) {
        this.zzckb = com_google_android_gms_internal_zzfy_zzc;
    }

    public zzc zzsd() {
        return this.zzckb;
    }

    public Future<zzjg> zzse() {
        return this.zzcka;
    }

    public void zzsf() {
    }
}
