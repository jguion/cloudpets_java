package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zzac;
import java.util.Map;

@zziy
public class zzfy {
    private final Context mContext;
    private final Object zzakd;
    private final VersionInfoParcel zzanh;
    private final String zzbqi;
    private zzkw<zzfv> zzbqj;
    private zzkw<zzfv> zzbqk;
    @Nullable
    private zzd zzbql;
    private int zzbqm;

    static class zza {
        static int zzbqx = 60000;
        static int zzbqy = 10000;
    }

    public static class zzb<T> implements zzkw<T> {
        public void zzd(T t) {
        }
    }

    public static class zzc extends zzln<zzfz> {
        private final Object zzakd = new Object();
        private final zzd zzbqz;
        private boolean zzbra;

        public zzc(zzd com_google_android_gms_internal_zzfy_zzd) {
            this.zzbqz = com_google_android_gms_internal_zzfy_zzd;
        }

        public void release() {
            synchronized (this.zzakd) {
                if (this.zzbra) {
                    return;
                }
                this.zzbra = true;
                zza(new com.google.android.gms.internal.zzlm.zzc<zzfz>(this) {
                    final /* synthetic */ zzc zzbrb;

                    {
                        this.zzbrb = r1;
                    }

                    public void zzb(zzfz com_google_android_gms_internal_zzfz) {
                        zzkn.v("Ending javascript session.");
                        ((zzga) com_google_android_gms_internal_zzfz).zznd();
                    }

                    public /* synthetic */ void zzd(Object obj) {
                        zzb((zzfz) obj);
                    }
                }, new com.google.android.gms.internal.zzlm.zzb());
                zza(new com.google.android.gms.internal.zzlm.zzc<zzfz>(this) {
                    final /* synthetic */ zzc zzbrb;

                    {
                        this.zzbrb = r1;
                    }

                    public void zzb(zzfz com_google_android_gms_internal_zzfz) {
                        zzkn.v("Releasing engine reference.");
                        this.zzbrb.zzbqz.zzna();
                    }

                    public /* synthetic */ void zzd(Object obj) {
                        zzb((zzfz) obj);
                    }
                }, new com.google.android.gms.internal.zzlm.zza(this) {
                    final /* synthetic */ zzc zzbrb;

                    {
                        this.zzbrb = r1;
                    }

                    public void run() {
                        this.zzbrb.zzbqz.zzna();
                    }
                });
            }
        }
    }

    public static class zzd extends zzln<zzfv> {
        private final Object zzakd = new Object();
        private zzkw<zzfv> zzbqk;
        private boolean zzbrc;
        private int zzbrd;

        public zzd(zzkw<zzfv> com_google_android_gms_internal_zzkw_com_google_android_gms_internal_zzfv) {
            this.zzbqk = com_google_android_gms_internal_zzkw_com_google_android_gms_internal_zzfv;
            this.zzbrc = false;
            this.zzbrd = 0;
        }

        public zzc zzmz() {
            final zzc com_google_android_gms_internal_zzfy_zzc = new zzc(this);
            synchronized (this.zzakd) {
                zza(new com.google.android.gms.internal.zzlm.zzc<zzfv>(this) {
                    final /* synthetic */ zzd zzbrf;

                    public void zza(zzfv com_google_android_gms_internal_zzfv) {
                        zzkn.v("Getting a new session for JS Engine.");
                        com_google_android_gms_internal_zzfy_zzc.zzg(com_google_android_gms_internal_zzfv.zzmw());
                    }

                    public /* synthetic */ void zzd(Object obj) {
                        zza((zzfv) obj);
                    }
                }, new com.google.android.gms.internal.zzlm.zza(this) {
                    final /* synthetic */ zzd zzbrf;

                    public void run() {
                        zzkn.v("Rejecting reference for JS Engine.");
                        com_google_android_gms_internal_zzfy_zzc.reject();
                    }
                });
                zzac.zzbr(this.zzbrd >= 0);
                this.zzbrd++;
            }
            return com_google_android_gms_internal_zzfy_zzc;
        }

        protected void zzna() {
            boolean z = true;
            synchronized (this.zzakd) {
                if (this.zzbrd < 1) {
                    z = false;
                }
                zzac.zzbr(z);
                zzkn.v("Releasing 1 reference for JS Engine");
                this.zzbrd--;
                zznc();
            }
        }

        public void zznb() {
            boolean z = true;
            synchronized (this.zzakd) {
                if (this.zzbrd < 0) {
                    z = false;
                }
                zzac.zzbr(z);
                zzkn.v("Releasing root reference. JS Engine will be destroyed once other references are released.");
                this.zzbrc = true;
                zznc();
            }
        }

        protected void zznc() {
            synchronized (this.zzakd) {
                zzac.zzbr(this.zzbrd >= 0);
                if (this.zzbrc && this.zzbrd == 0) {
                    zzkn.v("No reference is left (including root). Cleaning up engine.");
                    zza(new com.google.android.gms.internal.zzlm.zzc<zzfv>(this) {
                        final /* synthetic */ zzd zzbrf;

                        {
                            this.zzbrf = r1;
                        }

                        public void zza(final zzfv com_google_android_gms_internal_zzfv) {
                            zzu.zzfz().runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass3 zzbrh;

                                public void run() {
                                    this.zzbrh.zzbrf.zzbqk.zzd(com_google_android_gms_internal_zzfv);
                                    com_google_android_gms_internal_zzfv.destroy();
                                }
                            });
                        }

                        public /* synthetic */ void zzd(Object obj) {
                            zza((zzfv) obj);
                        }
                    }, new com.google.android.gms.internal.zzlm.zzb());
                } else {
                    zzkn.v("There are still references to the engine. Not destroying.");
                }
            }
        }
    }

    public static class zze extends zzln<zzfz> {
        private zzc zzbri;

        public zze(zzc com_google_android_gms_internal_zzfy_zzc) {
            this.zzbri = com_google_android_gms_internal_zzfy_zzc;
        }

        public void finalize() {
            this.zzbri.release();
            this.zzbri = null;
        }

        public int getStatus() {
            return this.zzbri.getStatus();
        }

        public void reject() {
            this.zzbri.reject();
        }

        public void zza(com.google.android.gms.internal.zzlm.zzc<zzfz> com_google_android_gms_internal_zzlm_zzc_com_google_android_gms_internal_zzfz, com.google.android.gms.internal.zzlm.zza com_google_android_gms_internal_zzlm_zza) {
            this.zzbri.zza(com_google_android_gms_internal_zzlm_zzc_com_google_android_gms_internal_zzfz, com_google_android_gms_internal_zzlm_zza);
        }

        public void zzf(zzfz com_google_android_gms_internal_zzfz) {
            this.zzbri.zzg(com_google_android_gms_internal_zzfz);
        }

        public /* synthetic */ void zzg(Object obj) {
            zzf((zzfz) obj);
        }
    }

    public zzfy(Context context, VersionInfoParcel versionInfoParcel, String str) {
        this.zzakd = new Object();
        this.zzbqm = 1;
        this.zzbqi = str;
        this.mContext = context.getApplicationContext();
        this.zzanh = versionInfoParcel;
        this.zzbqj = new zzb();
        this.zzbqk = new zzb();
    }

    public zzfy(Context context, VersionInfoParcel versionInfoParcel, String str, zzkw<zzfv> com_google_android_gms_internal_zzkw_com_google_android_gms_internal_zzfv, zzkw<zzfv> com_google_android_gms_internal_zzkw_com_google_android_gms_internal_zzfv2) {
        this(context, versionInfoParcel, str);
        this.zzbqj = com_google_android_gms_internal_zzkw_com_google_android_gms_internal_zzfv;
        this.zzbqk = com_google_android_gms_internal_zzkw_com_google_android_gms_internal_zzfv2;
    }

    private zzd zza(@Nullable final zzau com_google_android_gms_internal_zzau) {
        final zzd com_google_android_gms_internal_zzfy_zzd = new zzd(this.zzbqk);
        zzu.zzfz().runOnUiThread(new Runnable(this) {
            final /* synthetic */ zzfy zzbqp;

            public void run() {
                final zzfv zza = this.zzbqp.zza(this.zzbqp.mContext, this.zzbqp.zzanh, com_google_android_gms_internal_zzau);
                zza.zza(new com.google.android.gms.internal.zzfv.zza(this) {
                    final /* synthetic */ AnonymousClass1 zzbqr;

                    public void zzmx() {
                        zzkr.zzcrf.postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 zzbqs;

                            {
                                this.zzbqs = r1;
                            }

                            /* JADX WARNING: inconsistent code. */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public void run() {
                                /*
                                r3 = this;
                                r0 = r3.zzbqs;
                                r0 = r0.zzbqr;
                                r0 = r0.zzbqp;
                                r1 = r0.zzakd;
                                monitor-enter(r1);
                                r0 = r3.zzbqs;	 Catch:{ all -> 0x0043 }
                                r0 = r0.zzbqr;	 Catch:{ all -> 0x0043 }
                                r0 = r0;	 Catch:{ all -> 0x0043 }
                                r0 = r0.getStatus();	 Catch:{ all -> 0x0043 }
                                r2 = -1;
                                if (r0 == r2) goto L_0x0025;
                            L_0x0018:
                                r0 = r3.zzbqs;	 Catch:{ all -> 0x0043 }
                                r0 = r0.zzbqr;	 Catch:{ all -> 0x0043 }
                                r0 = r0;	 Catch:{ all -> 0x0043 }
                                r0 = r0.getStatus();	 Catch:{ all -> 0x0043 }
                                r2 = 1;
                                if (r0 != r2) goto L_0x0027;
                            L_0x0025:
                                monitor-exit(r1);	 Catch:{ all -> 0x0043 }
                            L_0x0026:
                                return;
                            L_0x0027:
                                r0 = r3.zzbqs;	 Catch:{ all -> 0x0043 }
                                r0 = r0.zzbqr;	 Catch:{ all -> 0x0043 }
                                r0 = r0;	 Catch:{ all -> 0x0043 }
                                r0.reject();	 Catch:{ all -> 0x0043 }
                                r0 = com.google.android.gms.ads.internal.zzu.zzfz();	 Catch:{ all -> 0x0043 }
                                r2 = new com.google.android.gms.internal.zzfy$1$1$1$1;	 Catch:{ all -> 0x0043 }
                                r2.<init>(r3);	 Catch:{ all -> 0x0043 }
                                r0.runOnUiThread(r2);	 Catch:{ all -> 0x0043 }
                                r0 = "Could not receive loaded message in a timely manner. Rejecting.";
                                com.google.android.gms.internal.zzkn.v(r0);	 Catch:{ all -> 0x0043 }
                                monitor-exit(r1);	 Catch:{ all -> 0x0043 }
                                goto L_0x0026;
                            L_0x0043:
                                r0 = move-exception;
                                monitor-exit(r1);	 Catch:{ all -> 0x0043 }
                                throw r0;
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfy.1.1.1.run():void");
                            }
                        }, (long) zza.zzbqy);
                    }
                });
                zza.zza("/jsLoaded", new zzev(this) {
                    final /* synthetic */ AnonymousClass1 zzbqr;

                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void zza(com.google.android.gms.internal.zzlt r4, java.util.Map<java.lang.String, java.lang.String> r5) {
                        /*
                        r3 = this;
                        r0 = r3.zzbqr;
                        r0 = r0.zzbqp;
                        r1 = r0.zzakd;
                        monitor-enter(r1);
                        r0 = r3.zzbqr;	 Catch:{ all -> 0x0051 }
                        r0 = r0;	 Catch:{ all -> 0x0051 }
                        r0 = r0.getStatus();	 Catch:{ all -> 0x0051 }
                        r2 = -1;
                        if (r0 == r2) goto L_0x001f;
                    L_0x0014:
                        r0 = r3.zzbqr;	 Catch:{ all -> 0x0051 }
                        r0 = r0;	 Catch:{ all -> 0x0051 }
                        r0 = r0.getStatus();	 Catch:{ all -> 0x0051 }
                        r2 = 1;
                        if (r0 != r2) goto L_0x0021;
                    L_0x001f:
                        monitor-exit(r1);	 Catch:{ all -> 0x0051 }
                    L_0x0020:
                        return;
                    L_0x0021:
                        r0 = r3.zzbqr;	 Catch:{ all -> 0x0051 }
                        r0 = r0.zzbqp;	 Catch:{ all -> 0x0051 }
                        r2 = 0;
                        r0.zzbqm = r2;	 Catch:{ all -> 0x0051 }
                        r0 = r3.zzbqr;	 Catch:{ all -> 0x0051 }
                        r0 = r0.zzbqp;	 Catch:{ all -> 0x0051 }
                        r0 = r0.zzbqj;	 Catch:{ all -> 0x0051 }
                        r2 = r0;	 Catch:{ all -> 0x0051 }
                        r0.zzd(r2);	 Catch:{ all -> 0x0051 }
                        r0 = r3.zzbqr;	 Catch:{ all -> 0x0051 }
                        r0 = r0;	 Catch:{ all -> 0x0051 }
                        r2 = r0;	 Catch:{ all -> 0x0051 }
                        r0.zzg(r2);	 Catch:{ all -> 0x0051 }
                        r0 = r3.zzbqr;	 Catch:{ all -> 0x0051 }
                        r0 = r0.zzbqp;	 Catch:{ all -> 0x0051 }
                        r2 = r3.zzbqr;	 Catch:{ all -> 0x0051 }
                        r2 = r0;	 Catch:{ all -> 0x0051 }
                        r0.zzbql = r2;	 Catch:{ all -> 0x0051 }
                        r0 = "Successfully loaded JS Engine.";
                        com.google.android.gms.internal.zzkn.v(r0);	 Catch:{ all -> 0x0051 }
                        monitor-exit(r1);	 Catch:{ all -> 0x0051 }
                        goto L_0x0020;
                    L_0x0051:
                        r0 = move-exception;
                        monitor-exit(r1);	 Catch:{ all -> 0x0051 }
                        throw r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfy.1.2.zza(com.google.android.gms.internal.zzlt, java.util.Map):void");
                    }
                });
                final zzld com_google_android_gms_internal_zzld = new zzld();
                zzev anonymousClass3 = new zzev(this) {
                    final /* synthetic */ AnonymousClass1 zzbqr;

                    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
                        synchronized (this.zzbqr.zzbqp.zzakd) {
                            com.google.android.gms.ads.internal.util.client.zzb.zzde("JS Engine is requesting an update");
                            if (this.zzbqr.zzbqp.zzbqm == 0) {
                                com.google.android.gms.ads.internal.util.client.zzb.zzde("Starting reload.");
                                this.zzbqr.zzbqp.zzbqm = 2;
                                this.zzbqr.zzbqp.zzb(com_google_android_gms_internal_zzau);
                            }
                            zza.zzb("/requestReload", (zzev) com_google_android_gms_internal_zzld.get());
                        }
                    }
                };
                com_google_android_gms_internal_zzld.set(anonymousClass3);
                zza.zza("/requestReload", anonymousClass3);
                if (this.zzbqp.zzbqi.endsWith(".js")) {
                    zza.zzbk(this.zzbqp.zzbqi);
                } else if (this.zzbqp.zzbqi.startsWith("<html>")) {
                    zza.zzbm(this.zzbqp.zzbqi);
                } else {
                    zza.zzbl(this.zzbqp.zzbqi);
                }
                zzkr.zzcrf.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 zzbqr;

                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                        r3 = this;
                        r0 = r3.zzbqr;
                        r0 = r0.zzbqp;
                        r1 = r0.zzakd;
                        monitor-enter(r1);
                        r0 = r3.zzbqr;	 Catch:{ all -> 0x003b }
                        r0 = r0;	 Catch:{ all -> 0x003b }
                        r0 = r0.getStatus();	 Catch:{ all -> 0x003b }
                        r2 = -1;
                        if (r0 == r2) goto L_0x001f;
                    L_0x0014:
                        r0 = r3.zzbqr;	 Catch:{ all -> 0x003b }
                        r0 = r0;	 Catch:{ all -> 0x003b }
                        r0 = r0.getStatus();	 Catch:{ all -> 0x003b }
                        r2 = 1;
                        if (r0 != r2) goto L_0x0021;
                    L_0x001f:
                        monitor-exit(r1);	 Catch:{ all -> 0x003b }
                    L_0x0020:
                        return;
                    L_0x0021:
                        r0 = r3.zzbqr;	 Catch:{ all -> 0x003b }
                        r0 = r0;	 Catch:{ all -> 0x003b }
                        r0.reject();	 Catch:{ all -> 0x003b }
                        r0 = com.google.android.gms.ads.internal.zzu.zzfz();	 Catch:{ all -> 0x003b }
                        r2 = new com.google.android.gms.internal.zzfy$1$4$1;	 Catch:{ all -> 0x003b }
                        r2.<init>(r3);	 Catch:{ all -> 0x003b }
                        r0.runOnUiThread(r2);	 Catch:{ all -> 0x003b }
                        r0 = "Could not receive loaded message in a timely manner. Rejecting.";
                        com.google.android.gms.internal.zzkn.v(r0);	 Catch:{ all -> 0x003b }
                        monitor-exit(r1);	 Catch:{ all -> 0x003b }
                        goto L_0x0020;
                    L_0x003b:
                        r0 = move-exception;
                        monitor-exit(r1);	 Catch:{ all -> 0x003b }
                        throw r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfy.1.4.run():void");
                    }
                }, (long) zza.zzbqx);
            }
        });
        return com_google_android_gms_internal_zzfy_zzd;
    }

    protected zzfv zza(Context context, VersionInfoParcel versionInfoParcel, @Nullable zzau com_google_android_gms_internal_zzau) {
        return new zzfx(context, versionInfoParcel, com_google_android_gms_internal_zzau, null);
    }

    protected zzd zzb(@Nullable zzau com_google_android_gms_internal_zzau) {
        final zzd zza = zza(com_google_android_gms_internal_zzau);
        zza.zza(new com.google.android.gms.internal.zzlm.zzc<zzfv>(this) {
            final /* synthetic */ zzfy zzbqp;

            public void zza(zzfv com_google_android_gms_internal_zzfv) {
                synchronized (this.zzbqp.zzakd) {
                    this.zzbqp.zzbqm = 0;
                    if (!(this.zzbqp.zzbql == null || zza == this.zzbqp.zzbql)) {
                        zzkn.v("New JS engine is loaded, marking previous one as destroyable.");
                        this.zzbqp.zzbql.zznb();
                    }
                    this.zzbqp.zzbql = zza;
                }
            }

            public /* synthetic */ void zzd(Object obj) {
                zza((zzfv) obj);
            }
        }, new com.google.android.gms.internal.zzlm.zza(this) {
            final /* synthetic */ zzfy zzbqp;

            public void run() {
                synchronized (this.zzbqp.zzakd) {
                    this.zzbqp.zzbqm = 1;
                    zzkn.v("Failed loading new engine. Marking new engine destroyable.");
                    zza.zznb();
                }
            }
        });
        return zza;
    }

    public zzc zzc(@Nullable zzau com_google_android_gms_internal_zzau) {
        zzc zzmz;
        synchronized (this.zzakd) {
            if (this.zzbql == null || this.zzbql.getStatus() == -1) {
                this.zzbqm = 2;
                this.zzbql = zzb(com_google_android_gms_internal_zzau);
                zzmz = this.zzbql.zzmz();
            } else if (this.zzbqm == 0) {
                zzmz = this.zzbql.zzmz();
            } else if (this.zzbqm == 1) {
                this.zzbqm = 2;
                zzb(com_google_android_gms_internal_zzau);
                zzmz = this.zzbql.zzmz();
            } else if (this.zzbqm == 2) {
                zzmz = this.zzbql.zzmz();
            } else {
                zzmz = this.zzbql.zzmz();
            }
        }
        return zzmz;
    }

    public zzc zzmy() {
        return zzc(null);
    }
}
