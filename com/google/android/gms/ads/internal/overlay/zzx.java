package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdm;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkx;
import com.google.android.gms.internal.zzkx.zza;
import com.google.android.gms.internal.zzkx.zzb;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import java.util.concurrent.TimeUnit;

@zziy
public class zzx {
    private final Context mContext;
    private final VersionInfoParcel zzaop;
    private final String zzcaj;
    @Nullable
    private final zzdo zzcak;
    @Nullable
    private final zzdq zzcal;
    private final zzkx zzcam = new zzb().zza("min_1", Double.MIN_VALUE, 1.0d).zza("1_5", 1.0d, 5.0d).zza("5_10", 5.0d, 10.0d).zza("10_20", 10.0d, 20.0d).zza("20_30", 20.0d, 30.0d).zza("30_max", 30.0d, Double.MAX_VALUE).zzuw();
    private final long[] zzcan;
    private final String[] zzcao;
    private boolean zzcap = false;
    private boolean zzcaq = false;
    private boolean zzcar = false;
    private boolean zzcas = false;
    private boolean zzcat;
    private zzi zzcau;
    private boolean zzcav;
    private boolean zzcaw;
    private long zzcax = -1;

    public zzx(Context context, VersionInfoParcel versionInfoParcel, String str, @Nullable zzdq com_google_android_gms_internal_zzdq, @Nullable zzdo com_google_android_gms_internal_zzdo) {
        this.mContext = context;
        this.zzaop = versionInfoParcel;
        this.zzcaj = str;
        this.zzcal = com_google_android_gms_internal_zzdq;
        this.zzcak = com_google_android_gms_internal_zzdo;
        String str2 = (String) zzdi.zzbbm.get();
        if (str2 == null) {
            this.zzcao = new String[0];
            this.zzcan = new long[0];
            return;
        }
        String[] split = TextUtils.split(str2, ",");
        this.zzcao = new String[split.length];
        this.zzcan = new long[split.length];
        for (int i = 0; i < split.length; i++) {
            try {
                this.zzcan[i] = Long.parseLong(split[i]);
            } catch (Throwable e) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Unable to parse frame hash target time number.", e);
                this.zzcan[i] = -1;
            }
        }
    }

    private void zzc(zzi com_google_android_gms_ads_internal_overlay_zzi) {
        long longValue = ((Long) zzdi.zzbbn.get()).longValue();
        long currentPosition = (long) com_google_android_gms_ads_internal_overlay_zzi.getCurrentPosition();
        int i = 0;
        while (i < this.zzcao.length) {
            if (this.zzcao[i] == null && longValue > Math.abs(currentPosition - this.zzcan[i])) {
                this.zzcao[i] = zza((TextureView) com_google_android_gms_ads_internal_overlay_zzi);
                return;
            }
            i++;
        }
    }

    private void zzqe() {
        if (this.zzcar && !this.zzcas) {
            zzdm.zza(this.zzcal, this.zzcak, "vff2");
            this.zzcas = true;
        }
        long nanoTime = zzu.zzgf().nanoTime();
        if (this.zzcat && this.zzcaw && this.zzcax != -1) {
            this.zzcam.zza(((double) TimeUnit.SECONDS.toNanos(1)) / ((double) (nanoTime - this.zzcax)));
        }
        this.zzcaw = this.zzcat;
        this.zzcax = nanoTime;
    }

    public void onStop() {
        if (((Boolean) zzdi.zzbbl.get()).booleanValue() && !this.zzcav) {
            String valueOf;
            String valueOf2;
            Bundle bundle = new Bundle();
            bundle.putString(VoiceMessage.TYPE, "native-player-metrics");
            bundle.putString("request", this.zzcaj);
            bundle.putString("player", this.zzcau.zzog());
            for (zza com_google_android_gms_internal_zzkx_zza : this.zzcam.getBuckets()) {
                valueOf = String.valueOf("fps_c_");
                valueOf2 = String.valueOf(com_google_android_gms_internal_zzkx_zza.name);
                bundle.putString(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), Integer.toString(com_google_android_gms_internal_zzkx_zza.count));
                valueOf = String.valueOf("fps_p_");
                valueOf2 = String.valueOf(com_google_android_gms_internal_zzkx_zza.name);
                bundle.putString(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), Double.toString(com_google_android_gms_internal_zzkx_zza.zzcsg));
            }
            for (int i = 0; i < this.zzcan.length; i++) {
                valueOf2 = this.zzcao[i];
                if (valueOf2 != null) {
                    String valueOf3 = String.valueOf("fh_");
                    valueOf = String.valueOf(Long.valueOf(this.zzcan[i]));
                    bundle.putString(new StringBuilder((String.valueOf(valueOf3).length() + 0) + String.valueOf(valueOf).length()).append(valueOf3).append(valueOf).toString(), valueOf2);
                }
            }
            zzu.zzfz().zza(this.mContext, this.zzaop.zzcs, "gmob-apps", bundle, true);
            this.zzcav = true;
        }
    }

    @TargetApi(14)
    String zza(TextureView textureView) {
        Bitmap bitmap = textureView.getBitmap(8, 8);
        long j = 0;
        long j2 = 63;
        int i = 0;
        while (i < 8) {
            long j3 = j;
            j = j2;
            for (int i2 = 0; i2 < 8; i2++) {
                int pixel = bitmap.getPixel(i2, i);
                j3 |= (Color.green(pixel) + (Color.blue(pixel) + Color.red(pixel)) > 128 ? 1 : 0) << ((int) j);
                j--;
            }
            i++;
            j2 = j;
            j = j3;
        }
        return String.format("%016X", new Object[]{Long.valueOf(j)});
    }

    public void zza(zzi com_google_android_gms_ads_internal_overlay_zzi) {
        zzdm.zza(this.zzcal, this.zzcak, "vpc2");
        this.zzcap = true;
        if (this.zzcal != null) {
            this.zzcal.zzh("vpn", com_google_android_gms_ads_internal_overlay_zzi.zzog());
        }
        this.zzcau = com_google_android_gms_ads_internal_overlay_zzi;
    }

    public void zzb(zzi com_google_android_gms_ads_internal_overlay_zzi) {
        zzqe();
        zzc(com_google_android_gms_ads_internal_overlay_zzi);
    }

    public void zzpj() {
        if (this.zzcap && !this.zzcaq) {
            zzdm.zza(this.zzcal, this.zzcak, "vfr2");
            this.zzcaq = true;
        }
    }

    public void zzqf() {
        this.zzcat = true;
        if (this.zzcaq && !this.zzcar) {
            zzdm.zza(this.zzcal, this.zzcak, "vfp2");
            this.zzcar = true;
        }
    }

    public void zzqg() {
        this.zzcat = false;
    }
}
