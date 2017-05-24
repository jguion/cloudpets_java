package com.google.android.gms.internal;

@zziy
public class zzjt {
    public final int errorCode;
    public final String zzbro;
    public final long zzbtj;
    public final String zzcnk;

    public static final class zza {
        private String zzbst;
        private int zzcdb;
        private String zzcnl;
        private long zzcnm;

        public zza zzaz(int i) {
            this.zzcdb = i;
            return this;
        }

        public zza zzcn(String str) {
            this.zzbst = str;
            return this;
        }

        public zza zzco(String str) {
            this.zzcnl = str;
            return this;
        }

        public zza zzl(long j) {
            this.zzcnm = j;
            return this;
        }

        public zzjt zzss() {
            return new zzjt();
        }
    }

    private zzjt(zza com_google_android_gms_internal_zzjt_zza) {
        this.zzcnk = com_google_android_gms_internal_zzjt_zza.zzbst;
        this.zzbro = com_google_android_gms_internal_zzjt_zza.zzcnl;
        this.errorCode = com_google_android_gms_internal_zzjt_zza.zzcdb;
        this.zzbtj = com_google_android_gms_internal_zzjt_zza.zzcnm;
    }
}
