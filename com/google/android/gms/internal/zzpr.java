package com.google.android.gms.internal;

import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzh;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class zzpr implements com.google.android.gms.clearcut.zzc {
    private static final Object um = new Object();
    private static ScheduledExecutorService un;
    private static final zze uo = new zze();
    private static final long up = TimeUnit.MILLISECONDS.convert(2, TimeUnit.MINUTES);
    private GoogleApiClient kv;
    private final zza uq;
    private final Object ur;
    private long us;
    private final long ut;
    private ScheduledFuture<?> uu;
    private final Runnable uv;
    private final com.google.android.gms.common.util.zze zzapy;

    public interface zza {
    }

    public static class zzb implements zza {
    }

    static abstract class zzc<R extends Result> extends com.google.android.gms.internal.zzqc.zza<R, zzps> {
        public zzc(GoogleApiClient googleApiClient) {
            super(com.google.android.gms.clearcut.zzb.API, googleApiClient);
        }
    }

    static final class zzd extends zzc<Status> {
        private final LogEventParcelable uA;

        zzd(LogEventParcelable logEventParcelable, GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.uA = logEventParcelable;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzd)) {
                return false;
            }
            return this.uA.equals(((zzd) obj).uA);
        }

        public String toString() {
            String valueOf = String.valueOf(this.uA);
            return new StringBuilder(String.valueOf(valueOf).length() + 12).append("MethodImpl(").append(valueOf).append(")").toString();
        }

        protected void zza(zzps com_google_android_gms_internal_zzps) throws RemoteException {
            zzpu anonymousClass1 = new com.google.android.gms.internal.zzpu.zza(this) {
                final /* synthetic */ zzd uB;

                {
                    this.uB = r1;
                }

                public void zzw(Status status) {
                    this.uB.zzc((Result) status);
                }
            };
            try {
                zzpr.zza(this.uA);
                com_google_android_gms_internal_zzps.zza(anonymousClass1, this.uA);
            } catch (Throwable e) {
                Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", e);
                zzz(new Status(10, "MessageProducer"));
            }
        }

        protected Status zzb(Status status) {
            return status;
        }

        protected /* synthetic */ Result zzc(Status status) {
            return zzb(status);
        }
    }

    private static final class zze {
        private int mSize;

        private zze() {
            this.mSize = 0;
        }

        public synchronized void decrement() {
            if (this.mSize == 0) {
                throw new RuntimeException("too many decrements");
            }
            this.mSize--;
            if (this.mSize == 0) {
                notifyAll();
            }
        }

        public synchronized void increment() {
            this.mSize++;
        }
    }

    public zzpr() {
        this(new zzh(), up, new zzb());
    }

    public zzpr(com.google.android.gms.common.util.zze com_google_android_gms_common_util_zze, long j, zza com_google_android_gms_internal_zzpr_zza) {
        this.ur = new Object();
        this.us = 0;
        this.uu = null;
        this.kv = null;
        this.uv = new Runnable(this) {
            final /* synthetic */ zzpr uw;

            {
                this.uw = r1;
            }

            public void run() {
                synchronized (this.uw.ur) {
                    if (0 <= this.uw.zzapy.elapsedRealtime() && this.uw.kv != null) {
                        Log.i("ClearcutLoggerApiImpl", "disconnect managed GoogleApiClient");
                        this.uw.kv.disconnect();
                        this.uw.kv = null;
                    }
                }
            }
        };
        this.zzapy = com_google_android_gms_common_util_zze;
        this.ut = j;
        this.uq = com_google_android_gms_internal_zzpr_zza;
    }

    private PendingResult<Status> zza(final GoogleApiClient googleApiClient, final zzc<Status> com_google_android_gms_internal_zzpr_zzc_com_google_android_gms_common_api_Status) {
        zzaoz().execute(new Runnable(this) {
            final /* synthetic */ zzpr uw;

            public void run() {
                googleApiClient.zzc(com_google_android_gms_internal_zzpr_zzc_com_google_android_gms_common_api_Status);
            }
        });
        return com_google_android_gms_internal_zzpr_zzc_com_google_android_gms_common_api_Status;
    }

    private static void zza(LogEventParcelable logEventParcelable) {
        if (logEventParcelable.uk != null && logEventParcelable.uj.brh.length == 0) {
            logEventParcelable.uj.brh = logEventParcelable.uk.zzaoy();
        }
        if (logEventParcelable.ul != null && logEventParcelable.uj.bro.length == 0) {
            logEventParcelable.uj.bro = logEventParcelable.ul.zzaoy();
        }
        logEventParcelable.ud = zzark.zzf(logEventParcelable.uj);
    }

    private ScheduledExecutorService zzaoz() {
        synchronized (um) {
            if (un == null) {
                un = Executors.newSingleThreadScheduledExecutor(new ThreadFactory(this) {
                    final /* synthetic */ zzpr uw;

                    {
                        this.uw = r1;
                    }

                    public Thread newThread(final Runnable runnable) {
                        return new Thread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 uy;

                            public void run() {
                                Process.setThreadPriority(10);
                                runnable.run();
                            }
                        }, "ClearcutLoggerApiImpl");
                    }
                });
            }
        }
        return un;
    }

    private zzd zzb(GoogleApiClient googleApiClient, LogEventParcelable logEventParcelable) {
        uo.increment();
        zzd com_google_android_gms_internal_zzpr_zzd = new zzd(logEventParcelable, googleApiClient);
        com_google_android_gms_internal_zzpr_zzd.zza(new com.google.android.gms.common.api.PendingResult.zza(this) {
            final /* synthetic */ zzpr uw;

            {
                this.uw = r1;
            }

            public void zzv(Status status) {
                zzpr.uo.decrement();
            }
        });
        return com_google_android_gms_internal_zzpr_zzd;
    }

    public PendingResult<Status> zza(GoogleApiClient googleApiClient, LogEventParcelable logEventParcelable) {
        return zza(googleApiClient, zzb(googleApiClient, logEventParcelable));
    }
}
