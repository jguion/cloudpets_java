package com.google.android.gms.internal;

import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzu;

@zziy
public abstract class zzde<T> {
    private final int zzbae;
    private final String zzbaf;
    private final T zzbag;

    class AnonymousClass1 extends zzde<Boolean> {
        AnonymousClass1(int i, String str, Boolean bool) {
            super(i, str, bool);
        }

        public /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
            return zzb(sharedPreferences);
        }

        public Boolean zzb(SharedPreferences sharedPreferences) {
            return Boolean.valueOf(sharedPreferences.getBoolean(getKey(), ((Boolean) zzkq()).booleanValue()));
        }
    }

    class AnonymousClass2 extends zzde<Integer> {
        AnonymousClass2(int i, String str, Integer num) {
            super(i, str, num);
        }

        public /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
            return zzc(sharedPreferences);
        }

        public Integer zzc(SharedPreferences sharedPreferences) {
            return Integer.valueOf(sharedPreferences.getInt(getKey(), ((Integer) zzkq()).intValue()));
        }
    }

    class AnonymousClass3 extends zzde<Long> {
        AnonymousClass3(int i, String str, Long l) {
            super(i, str, l);
        }

        public /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
            return zzd(sharedPreferences);
        }

        public Long zzd(SharedPreferences sharedPreferences) {
            return Long.valueOf(sharedPreferences.getLong(getKey(), ((Long) zzkq()).longValue()));
        }
    }

    class AnonymousClass4 extends zzde<String> {
        AnonymousClass4(int i, String str, String str2) {
            super(i, str, str2);
        }

        public /* synthetic */ Object zza(SharedPreferences sharedPreferences) {
            return zze(sharedPreferences);
        }

        public String zze(SharedPreferences sharedPreferences) {
            return sharedPreferences.getString(getKey(), (String) zzkq());
        }
    }

    private zzde(int i, String str, T t) {
        this.zzbae = i;
        this.zzbaf = str;
        this.zzbag = t;
        zzu.zzgk().zza(this);
    }

    public static zzde<String> zza(int i, String str) {
        zzde<String> zza = zza(i, str, null);
        zzu.zzgk().zzb(zza);
        return zza;
    }

    public static zzde<Integer> zza(int i, String str, int i2) {
        return new AnonymousClass2(i, str, Integer.valueOf(i2));
    }

    public static zzde<Long> zza(int i, String str, long j) {
        return new AnonymousClass3(i, str, Long.valueOf(j));
    }

    public static zzde<Boolean> zza(int i, String str, Boolean bool) {
        return new AnonymousClass1(i, str, bool);
    }

    public static zzde<String> zza(int i, String str, String str2) {
        return new AnonymousClass4(i, str, str2);
    }

    public static zzde<String> zzb(int i, String str) {
        zzde<String> zza = zza(i, str, null);
        zzu.zzgk().zzc(zza);
        return zza;
    }

    public T get() {
        return zzu.zzgl().zzd(this);
    }

    public String getKey() {
        return this.zzbaf;
    }

    protected abstract T zza(SharedPreferences sharedPreferences);

    public T zzkq() {
        return this.zzbag;
    }
}
