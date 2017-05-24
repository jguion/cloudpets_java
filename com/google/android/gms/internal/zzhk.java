package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;

@zziy
public class zzhk {
    private final boolean zzbvo;
    private final boolean zzbvp;
    private final boolean zzbvq;
    private final boolean zzbvr;
    private final boolean zzbvs;

    public static final class zza {
        private boolean zzbvo;
        private boolean zzbvp;
        private boolean zzbvq;
        private boolean zzbvr;
        private boolean zzbvs;

        public zzhk zznw() {
            return new zzhk();
        }

        public zza zzu(boolean z) {
            this.zzbvo = z;
            return this;
        }

        public zza zzv(boolean z) {
            this.zzbvp = z;
            return this;
        }

        public zza zzw(boolean z) {
            this.zzbvq = z;
            return this;
        }

        public zza zzx(boolean z) {
            this.zzbvr = z;
            return this;
        }

        public zza zzy(boolean z) {
            this.zzbvs = z;
            return this;
        }
    }

    private zzhk(zza com_google_android_gms_internal_zzhk_zza) {
        this.zzbvo = com_google_android_gms_internal_zzhk_zza.zzbvo;
        this.zzbvp = com_google_android_gms_internal_zzhk_zza.zzbvp;
        this.zzbvq = com_google_android_gms_internal_zzhk_zza.zzbvq;
        this.zzbvr = com_google_android_gms_internal_zzhk_zza.zzbvr;
        this.zzbvs = com_google_android_gms_internal_zzhk_zza.zzbvs;
    }

    public JSONObject toJson() {
        try {
            return new JSONObject().put("sms", this.zzbvo).put("tel", this.zzbvp).put("calendar", this.zzbvq).put("storePicture", this.zzbvr).put("inlineVideo", this.zzbvs);
        } catch (Throwable e) {
            zzb.zzb("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}
