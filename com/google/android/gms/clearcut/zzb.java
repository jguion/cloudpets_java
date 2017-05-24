package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.internal.zzpr;
import com.google.android.gms.internal.zzps;
import com.google.android.gms.internal.zzpw;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimeZone;

public final class zzb {
    public static final Api<NoOptions> API = new Api("ClearcutLogger.API", fb, fa);
    public static final zzf<zzps> fa = new zzf();
    public static final com.google.android.gms.common.api.Api.zza<zzps, NoOptions> fb = new com.google.android.gms.common.api.Api.zza<zzps, NoOptions>() {
        public /* synthetic */ zze zza(Context context, Looper looper, zzh com_google_android_gms_common_internal_zzh, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zze(context, looper, com_google_android_gms_common_internal_zzh, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzps zze(Context context, Looper looper, zzh com_google_android_gms_common_internal_zzh, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzps(context, looper, com_google_android_gms_common_internal_zzh, connectionCallbacks, onConnectionFailedListener);
        }
    };
    public static final zzc tH = new zzpr();
    private final String ed;
    private final Context mContext;
    private final int tI;
    private String tJ;
    private int tK;
    private String tL;
    private String tM;
    private final boolean tN;
    private int tO;
    private final zzc tP;
    private final zza tQ;
    private zzd tR;
    private final zzb tS;
    private final com.google.android.gms.common.util.zze zzapy;

    public class zza {
        private String tJ;
        private int tK;
        private String tL;
        private String tM;
        private int tO;
        private final zzc tT;
        private ArrayList<Integer> tU;
        private ArrayList<String> tV;
        private ArrayList<Integer> tW;
        private ArrayList<byte[]> tX;
        private boolean tY;
        private final com.google.android.gms.internal.zzarp.zzd tZ;
        private boolean ua;
        final /* synthetic */ zzb ub;

        private zza(zzb com_google_android_gms_clearcut_zzb, byte[] bArr) {
            this(com_google_android_gms_clearcut_zzb, bArr, null);
        }

        private zza(zzb com_google_android_gms_clearcut_zzb, byte[] bArr, zzc com_google_android_gms_clearcut_zzb_zzc) {
            this.ub = com_google_android_gms_clearcut_zzb;
            this.tK = this.ub.tK;
            this.tJ = this.ub.tJ;
            this.tL = this.ub.tL;
            this.tM = this.ub.tM;
            this.tO = 0;
            this.tU = null;
            this.tV = null;
            this.tW = null;
            this.tX = null;
            this.tY = true;
            this.tZ = new com.google.android.gms.internal.zzarp.zzd();
            this.ua = false;
            this.tL = com_google_android_gms_clearcut_zzb.tL;
            this.tM = com_google_android_gms_clearcut_zzb.tM;
            this.tZ.bra = com_google_android_gms_clearcut_zzb.zzapy.currentTimeMillis();
            this.tZ.brb = com_google_android_gms_clearcut_zzb.zzapy.elapsedRealtime();
            this.tZ.brs = (long) com_google_android_gms_clearcut_zzb.tQ.zzbl(com_google_android_gms_clearcut_zzb.mContext);
            this.tZ.brm = com_google_android_gms_clearcut_zzb.tR.zzag(this.tZ.bra);
            if (bArr != null) {
                this.tZ.brh = bArr;
            }
            this.tT = com_google_android_gms_clearcut_zzb_zzc;
        }

        public LogEventParcelable zzaox() {
            return new LogEventParcelable(new PlayLoggerContext(this.ub.ed, this.ub.tI, this.tK, this.tJ, this.tL, this.tM, this.ub.tN, this.tO), this.tZ, this.tT, null, zzb.zzb(null), zzb.zzc(null), zzb.zzb(null), zzb.zzd(null), this.tY);
        }

        public PendingResult<Status> zze(GoogleApiClient googleApiClient) {
            if (this.ua) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.ua = true;
            PlayLoggerContext playLoggerContext = zzaox().uc;
            return this.ub.tS.zzh(playLoggerContext.axz, playLoggerContext.axv) ? this.ub.tP.zza(googleApiClient, zzaox()) : PendingResults.immediatePendingResult(Status.vY);
        }

        public zza zzfh(int i) {
            this.tZ.brd = i;
            return this;
        }

        public zza zzfi(int i) {
            this.tZ.zzajd = i;
            return this;
        }
    }

    public interface zzb {
        boolean zzh(String str, int i);
    }

    public interface zzc {
        byte[] zzaoy();
    }

    public static class zzd {
        public long zzag(long j) {
            return (long) (TimeZone.getDefault().getOffset(j) / Constants.MAX_DOWNLOADS);
        }
    }

    public zzb(Context context, int i, String str, String str2, String str3, boolean z, zzc com_google_android_gms_clearcut_zzc, com.google.android.gms.common.util.zze com_google_android_gms_common_util_zze, zzd com_google_android_gms_clearcut_zzb_zzd, zza com_google_android_gms_clearcut_zza, zzb com_google_android_gms_clearcut_zzb_zzb) {
        this.tK = -1;
        this.tO = 0;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            applicationContext = context;
        }
        this.mContext = applicationContext;
        this.ed = context.getPackageName();
        this.tI = zzbm(context);
        this.tK = i;
        this.tJ = str;
        this.tL = str2;
        this.tM = str3;
        this.tN = z;
        this.tP = com_google_android_gms_clearcut_zzc;
        this.zzapy = com_google_android_gms_common_util_zze;
        if (com_google_android_gms_clearcut_zzb_zzd == null) {
            com_google_android_gms_clearcut_zzb_zzd = new zzd();
        }
        this.tR = com_google_android_gms_clearcut_zzb_zzd;
        this.tQ = com_google_android_gms_clearcut_zza;
        this.tO = 0;
        this.tS = com_google_android_gms_clearcut_zzb_zzb;
        if (this.tN) {
            zzac.zzb(this.tL == null, (Object) "can't be anonymous with an upload account");
        }
    }

    public zzb(Context context, String str, String str2) {
        this(context, -1, str, str2, null, false, tH, com.google.android.gms.common.util.zzh.zzaxj(), null, zza.tG, new zzpw(context));
    }

    private static int[] zzb(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            iArr[i] = ((Integer) it.next()).intValue();
            i = i2;
        }
        return iArr;
    }

    private int zzbm(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.wtf("ClearcutLogger", "This can't happen.");
            return i;
        }
    }

    private static String[] zzc(ArrayList<String> arrayList) {
        return arrayList == null ? null : (String[]) arrayList.toArray(new String[0]);
    }

    private static byte[][] zzd(ArrayList<byte[]> arrayList) {
        return arrayList == null ? null : (byte[][]) arrayList.toArray(new byte[0][]);
    }

    public zza zzl(byte[] bArr) {
        return new zza(bArr);
    }
}
