package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzac;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class zzpw implements com.google.android.gms.clearcut.zzb.zzb {
    private static final Charset UTF_8 = Charset.forName(Key.STRING_CHARSET_NAME);
    static Boolean uC = null;
    final zza uD;

    static class zza {
        final ContentResolver mContentResolver;

        zza(Context context) {
            if (context == null || !zzbn(context)) {
                this.mContentResolver = null;
                return;
            }
            this.mContentResolver = context.getContentResolver();
            zzafz.zzb(this.mContentResolver, "gms:playlog:service:sampling_");
        }

        private static boolean zzbn(Context context) {
            if (zzpw.uC == null) {
                zzpw.uC = Boolean.valueOf(context.checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
            }
            return zzpw.uC.booleanValue();
        }

        long zzapb() {
            return this.mContentResolver == null ? 0 : zzafz.getLong(this.mContentResolver, "android_id", 0);
        }

        String zzhc(String str) {
            if (this.mContentResolver == null) {
                return null;
            }
            ContentResolver contentResolver = this.mContentResolver;
            String valueOf = String.valueOf("gms:playlog:service:sampling_");
            String valueOf2 = String.valueOf(str);
            return zzafz.zza(contentResolver, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), null);
        }
    }

    static class zzb {
        public final String uE;
        public final long uF;
        public final long uG;

        public zzb(String str, long j, long j2) {
            this.uE = str;
            this.uF = j;
            this.uG = j2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb com_google_android_gms_internal_zzpw_zzb = (zzb) obj;
            return zzab.equal(this.uE, com_google_android_gms_internal_zzpw_zzb.uE) && zzab.equal(Long.valueOf(this.uF), Long.valueOf(com_google_android_gms_internal_zzpw_zzb.uF)) && zzab.equal(Long.valueOf(this.uG), Long.valueOf(com_google_android_gms_internal_zzpw_zzb.uG));
        }

        public int hashCode() {
            return zzab.hashCode(this.uE, Long.valueOf(this.uF), Long.valueOf(this.uG));
        }
    }

    public zzpw() {
        this(new zza(null));
    }

    public zzpw(Context context) {
        this(new zza(context));
    }

    zzpw(zza com_google_android_gms_internal_zzpw_zza) {
        this.uD = (zza) zzac.zzy(com_google_android_gms_internal_zzpw_zza);
    }

    static boolean zza(long j, long j2, long j3) {
        if (j2 >= 0 && j3 >= 0) {
            return j3 > 0 && zzpx.zzd(j, j3) < j2;
        } else {
            throw new IllegalArgumentException("negative values not supported: " + j2 + "/" + j3);
        }
    }

    static long zzai(long j) {
        return zzpt.zzm(ByteBuffer.allocate(8).putLong(j).array());
    }

    static long zzd(String str, long j) {
        if (str == null || str.isEmpty()) {
            return zzai(j);
        }
        byte[] bytes = str.getBytes(UTF_8);
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
        allocate.put(bytes);
        allocate.putLong(j);
        return zzpt.zzm(allocate.array());
    }

    static zzb zzhb(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        String str2 = "";
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            i = indexOf + 1;
        }
        int indexOf2 = str.indexOf(47, i);
        if (indexOf2 <= 0) {
            str2 = "LogSamplerImpl";
            String str3 = "Failed to parse the rule: ";
            String valueOf = String.valueOf(str);
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i, indexOf2));
            long parseLong2 = Long.parseLong(str.substring(indexOf2 + 1));
            if (parseLong >= 0 && parseLong2 >= 0) {
                return new zzb(str2, parseLong, parseLong2);
            }
            Log.e("LogSamplerImpl", "negative values not supported: " + parseLong + "/" + parseLong2);
            return null;
        } catch (Throwable e) {
            Throwable th = e;
            str3 = "LogSamplerImpl";
            String str4 = "parseLong() failed while parsing: ";
            valueOf = String.valueOf(str);
            Log.e(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4), th);
            return null;
        }
    }

    public boolean zzh(String str, int i) {
        if (str == null || str.isEmpty()) {
            str = i >= 0 ? String.valueOf(i) : null;
        }
        if (str == null) {
            return true;
        }
        long zzapb = this.uD.zzapb();
        zzb zzhb = zzhb(this.uD.zzhc(str));
        return zzhb != null ? zza(zzd(zzhb.uE, zzapb), zzhb.uF, zzhb.uG) : true;
    }
}
