package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import java.util.concurrent.Future;

@zziy
public final class zzkp {

    public interface zzb {
        void zzh(Bundle bundle);
    }

    private static abstract class zza extends zzkm {
        private zza() {
        }

        public void onStop() {
        }
    }

    class AnonymousClass10 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ boolean zzcqu;

        AnonymousClass10(Context context, boolean z) {
            this.zzamt = context;
            this.zzcqu = z;
            super();
        }

        public void zzfc() {
            Editor edit = zzkp.zzn(this.zzamt).edit();
            edit.putBoolean("content_url_opted_out", this.zzcqu);
            edit.apply();
        }
    }

    class AnonymousClass11 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ zzb zzcqp;

        AnonymousClass11(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
            this.zzamt = context;
            this.zzcqp = com_google_android_gms_internal_zzkp_zzb;
            super();
        }

        public void zzfc() {
            SharedPreferences zzn = zzkp.zzn(this.zzamt);
            Bundle bundle = new Bundle();
            bundle.putBoolean("content_url_opted_out", zzn.getBoolean("content_url_opted_out", true));
            if (this.zzcqp != null) {
                this.zzcqp.zzh(bundle);
            }
        }
    }

    class AnonymousClass12 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ String zzcqv;

        AnonymousClass12(Context context, String str) {
            this.zzamt = context;
            this.zzcqv = str;
            super();
        }

        public void zzfc() {
            Editor edit = zzkp.zzn(this.zzamt).edit();
            edit.putString("content_url_hashes", this.zzcqv);
            edit.apply();
        }
    }

    class AnonymousClass13 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ zzb zzcqp;

        AnonymousClass13(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
            this.zzamt = context;
            this.zzcqp = com_google_android_gms_internal_zzkp_zzb;
            super();
        }

        public void zzfc() {
            SharedPreferences zzn = zzkp.zzn(this.zzamt);
            Bundle bundle = new Bundle();
            bundle.putString("content_url_hashes", zzn.getString("content_url_hashes", ""));
            if (this.zzcqp != null) {
                this.zzcqp.zzh(bundle);
            }
        }
    }

    class AnonymousClass14 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ boolean zzcqw;

        AnonymousClass14(Context context, boolean z) {
            this.zzamt = context;
            this.zzcqw = z;
            super();
        }

        public void zzfc() {
            Editor edit = zzkp.zzn(this.zzamt).edit();
            edit.putBoolean("auto_collect_location", this.zzcqw);
            edit.apply();
        }
    }

    class AnonymousClass1 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ boolean zzcqo;

        AnonymousClass1(Context context, boolean z) {
            this.zzamt = context;
            this.zzcqo = z;
            super();
        }

        public void zzfc() {
            Editor edit = zzkp.zzn(this.zzamt).edit();
            edit.putBoolean("use_https", this.zzcqo);
            edit.apply();
        }
    }

    class AnonymousClass2 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ zzb zzcqp;

        AnonymousClass2(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
            this.zzamt = context;
            this.zzcqp = com_google_android_gms_internal_zzkp_zzb;
            super();
        }

        public void zzfc() {
            SharedPreferences zzn = zzkp.zzn(this.zzamt);
            Bundle bundle = new Bundle();
            bundle.putBoolean("auto_collect_location", zzn.getBoolean("auto_collect_location", false));
            if (this.zzcqp != null) {
                this.zzcqp.zzh(bundle);
            }
        }
    }

    class AnonymousClass3 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ String zzcqq;
        final /* synthetic */ long zzcqr;

        AnonymousClass3(Context context, String str, long j) {
            this.zzamt = context;
            this.zzcqq = str;
            this.zzcqr = j;
            super();
        }

        public void zzfc() {
            Editor edit = zzkp.zzn(this.zzamt).edit();
            edit.putString("app_settings_json", this.zzcqq);
            edit.putLong("app_settings_last_update_ms", this.zzcqr);
            edit.apply();
        }
    }

    class AnonymousClass4 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ zzb zzcqp;

        AnonymousClass4(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
            this.zzamt = context;
            this.zzcqp = com_google_android_gms_internal_zzkp_zzb;
            super();
        }

        public void zzfc() {
            SharedPreferences zzn = zzkp.zzn(this.zzamt);
            Bundle bundle = new Bundle();
            bundle.putString("app_settings_json", zzn.getString("app_settings_json", ""));
            bundle.putLong("app_settings_last_update_ms", zzn.getLong("app_settings_last_update_ms", 0));
            if (this.zzcqp != null) {
                this.zzcqp.zzh(bundle);
            }
        }
    }

    class AnonymousClass5 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ long zzcqs;

        AnonymousClass5(Context context, long j) {
            this.zzamt = context;
            this.zzcqs = j;
            super();
        }

        public void zzfc() {
            Editor edit = zzkp.zzn(this.zzamt).edit();
            edit.putLong("app_last_background_time_ms", this.zzcqs);
            edit.apply();
        }
    }

    class AnonymousClass6 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ zzb zzcqp;

        AnonymousClass6(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
            this.zzamt = context;
            this.zzcqp = com_google_android_gms_internal_zzkp_zzb;
            super();
        }

        public void zzfc() {
            SharedPreferences zzn = zzkp.zzn(this.zzamt);
            Bundle bundle = new Bundle();
            bundle.putLong("app_last_background_time_ms", zzn.getLong("app_last_background_time_ms", 0));
            if (this.zzcqp != null) {
                this.zzcqp.zzh(bundle);
            }
        }
    }

    class AnonymousClass7 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ int zzcqt;

        AnonymousClass7(Context context, int i) {
            this.zzamt = context;
            this.zzcqt = i;
            super();
        }

        public void zzfc() {
            Editor edit = zzkp.zzn(this.zzamt).edit();
            edit.putInt("request_in_session_count", this.zzcqt);
            edit.apply();
        }
    }

    class AnonymousClass8 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ zzb zzcqp;

        AnonymousClass8(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
            this.zzamt = context;
            this.zzcqp = com_google_android_gms_internal_zzkp_zzb;
            super();
        }

        public void zzfc() {
            SharedPreferences zzn = zzkp.zzn(this.zzamt);
            Bundle bundle = new Bundle();
            bundle.putBoolean("use_https", zzn.getBoolean("use_https", true));
            if (this.zzcqp != null) {
                this.zzcqp.zzh(bundle);
            }
        }
    }

    class AnonymousClass9 extends zza {
        final /* synthetic */ Context zzamt;
        final /* synthetic */ zzb zzcqp;

        AnonymousClass9(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
            this.zzamt = context;
            this.zzcqp = com_google_android_gms_internal_zzkp_zzb;
            super();
        }

        public void zzfc() {
            SharedPreferences zzn = zzkp.zzn(this.zzamt);
            Bundle bundle = new Bundle();
            bundle.putInt("webview_cache_version", zzn.getInt("webview_cache_version", 0));
            if (this.zzcqp != null) {
                this.zzcqp.zzh(bundle);
            }
        }
    }

    public static Future zza(Context context, int i) {
        return (Future) new AnonymousClass7(context, i).zzqw();
    }

    public static Future zza(Context context, long j) {
        return (Future) new AnonymousClass5(context, j).zzqw();
    }

    public static Future zza(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
        return (Future) new AnonymousClass8(context, com_google_android_gms_internal_zzkp_zzb).zzqw();
    }

    public static Future zza(Context context, String str, long j) {
        return (Future) new AnonymousClass3(context, str, j).zzqw();
    }

    public static Future zzb(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
        return (Future) new AnonymousClass9(context, com_google_android_gms_internal_zzkp_zzb).zzqw();
    }

    public static Future zzc(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
        return (Future) new AnonymousClass11(context, com_google_android_gms_internal_zzkp_zzb).zzqw();
    }

    public static Future zzc(Context context, boolean z) {
        return (Future) new AnonymousClass1(context, z).zzqw();
    }

    public static Future zzd(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
        return (Future) new AnonymousClass13(context, com_google_android_gms_internal_zzkp_zzb).zzqw();
    }

    public static Future zze(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
        return (Future) new AnonymousClass2(context, com_google_android_gms_internal_zzkp_zzb).zzqw();
    }

    public static Future zze(Context context, boolean z) {
        return (Future) new AnonymousClass10(context, z).zzqw();
    }

    public static Future zzf(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
        return (Future) new AnonymousClass4(context, com_google_android_gms_internal_zzkp_zzb).zzqw();
    }

    public static Future zzf(Context context, String str) {
        return (Future) new AnonymousClass12(context, str).zzqw();
    }

    public static Future zzf(Context context, boolean z) {
        return (Future) new AnonymousClass14(context, z).zzqw();
    }

    public static Future zzg(Context context, zzb com_google_android_gms_internal_zzkp_zzb) {
        return (Future) new AnonymousClass6(context, com_google_android_gms_internal_zzkp_zzb).zzqw();
    }

    public static SharedPreferences zzn(Context context) {
        return context.getSharedPreferences("admob", 0);
    }
}
