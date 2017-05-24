package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

@zziy
public class zzky {
    private static zzl zzcsk;
    private static final Object zzcsl = new Object();
    public static final zza<Void> zzcsm = new zza<Void>() {
        public /* synthetic */ Object zzh(InputStream inputStream) {
            return zzj(inputStream);
        }

        public Void zzj(InputStream inputStream) {
            return null;
        }

        public /* synthetic */ Object zzrs() {
            return zzux();
        }

        public Void zzux() {
            return null;
        }
    };

    public interface zza<T> {
        T zzh(InputStream inputStream);

        T zzrs();
    }

    private static class zzb<T> extends zzk<InputStream> {
        private final com.google.android.gms.internal.zzm.zzb<T> zzcg;
        private final zza<T> zzcsr;

        class AnonymousClass1 implements com.google.android.gms.internal.zzm.zza {
            final /* synthetic */ com.google.android.gms.internal.zzm.zzb zzcss;
            final /* synthetic */ zza zzcst;

            AnonymousClass1(com.google.android.gms.internal.zzm.zzb com_google_android_gms_internal_zzm_zzb, zza com_google_android_gms_internal_zzky_zza) {
                this.zzcss = com_google_android_gms_internal_zzm_zzb;
                this.zzcst = com_google_android_gms_internal_zzky_zza;
            }

            public void zze(zzr com_google_android_gms_internal_zzr) {
                this.zzcss.zzb(this.zzcst.zzrs());
            }
        }

        public zzb(String str, zza<T> com_google_android_gms_internal_zzky_zza_T, com.google.android.gms.internal.zzm.zzb<T> com_google_android_gms_internal_zzm_zzb_T) {
            super(0, str, new AnonymousClass1(com_google_android_gms_internal_zzm_zzb_T, com_google_android_gms_internal_zzky_zza_T));
            this.zzcsr = com_google_android_gms_internal_zzky_zza_T;
            this.zzcg = com_google_android_gms_internal_zzm_zzb_T;
        }

        protected zzm<InputStream> zza(zzi com_google_android_gms_internal_zzi) {
            return zzm.zza(new ByteArrayInputStream(com_google_android_gms_internal_zzi.data), zzx.zzb(com_google_android_gms_internal_zzi));
        }

        protected /* synthetic */ void zza(Object obj) {
            zzk((InputStream) obj);
        }

        protected void zzk(InputStream inputStream) {
            this.zzcg.zzb(this.zzcsr.zzh(inputStream));
        }
    }

    private class zzc<T> extends zzlg<T> implements com.google.android.gms.internal.zzm.zzb<T> {
        final /* synthetic */ zzky zzcso;

        private zzc(zzky com_google_android_gms_internal_zzky) {
            this.zzcso = com_google_android_gms_internal_zzky;
        }

        public void zzb(@Nullable T t) {
            super.zzh(t);
        }
    }

    public zzky(Context context) {
        zzaq(context);
    }

    private static zzl zzaq(Context context) {
        zzl com_google_android_gms_internal_zzl;
        synchronized (zzcsl) {
            if (zzcsk == null) {
                zzcsk = zzac.zza(context.getApplicationContext());
            }
            com_google_android_gms_internal_zzl = zzcsk;
        }
        return com_google_android_gms_internal_zzl;
    }

    public zzlj<String> zza(int i, final String str, @Nullable Map<String, String> map, @Nullable byte[] bArr) {
        final Object com_google_android_gms_internal_zzky_zzc = new zzc();
        final byte[] bArr2 = bArr;
        final Map<String, String> map2 = map;
        zzcsk.zze(new zzab(this, i, str, com_google_android_gms_internal_zzky_zzc, new com.google.android.gms.internal.zzm.zza(this) {
            final /* synthetic */ zzky zzcso;

            public void zze(zzr com_google_android_gms_internal_zzr) {
                String str = str;
                String valueOf = String.valueOf(com_google_android_gms_internal_zzr.toString());
                com.google.android.gms.ads.internal.util.client.zzb.zzdf(new StringBuilder((String.valueOf(str).length() + 21) + String.valueOf(valueOf).length()).append("Failed to load URL: ").append(str).append("\n").append(valueOf).toString());
                com_google_android_gms_internal_zzky_zzc.zzb(null);
            }
        }) {
            final /* synthetic */ zzky zzcso;

            public Map<String, String> getHeaders() throws zza {
                return map2 == null ? super.getHeaders() : map2;
            }

            public byte[] zzp() throws zza {
                return bArr2 == null ? super.zzp() : bArr2;
            }
        });
        return com_google_android_gms_internal_zzky_zzc;
    }

    public <T> zzlj<T> zza(String str, zza<T> com_google_android_gms_internal_zzky_zza_T) {
        Object com_google_android_gms_internal_zzky_zzc = new zzc();
        zzcsk.zze(new zzb(str, com_google_android_gms_internal_zzky_zza_T, com_google_android_gms_internal_zzky_zzc));
        return com_google_android_gms_internal_zzky_zzc;
    }

    public zzlj<String> zzd(String str, Map<String, String> map) {
        return zza(0, str, map, null);
    }
}
