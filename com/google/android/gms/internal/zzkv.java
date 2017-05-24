package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.internal.zzky.zza;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@zziy
public class zzkv {
    private final Object zzakd = new Object();
    private String zzcrv = "";
    private String zzcrw = "";
    private boolean zzcrx = false;

    private Uri zze(Context context, String str, String str2) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("linkedDeviceId", zzap(context));
        buildUpon.appendQueryParameter("adSlotPath", str2);
        return buildUpon.build();
    }

    private void zzn(Context context, String str) {
        zzu.zzfz().zza(context, zze(context, (String) zzdi.zzbhr.get(), str));
    }

    public void zza(Context context, String str, String str2, String str3) {
        Builder buildUpon = zze(context, (String) zzdi.zzbhu.get(), str3).buildUpon();
        buildUpon.appendQueryParameter("debugData", str2);
        zzu.zzfz().zzc(context, str, buildUpon.build().toString());
    }

    public void zzai(boolean z) {
        synchronized (this.zzakd) {
            this.zzcrx = z;
        }
    }

    public String zzap(Context context) {
        String str;
        synchronized (this.zzakd) {
            if (TextUtils.isEmpty(this.zzcrv)) {
                this.zzcrv = zzu.zzfz().zzh(context, "debug_signals_id.txt");
                if (TextUtils.isEmpty(this.zzcrv)) {
                    this.zzcrv = zzu.zzfz().zzuh();
                    zzu.zzfz().zzd(context, "debug_signals_id.txt", this.zzcrv);
                }
            }
            str = this.zzcrv;
        }
        return str;
    }

    public void zzdb(String str) {
        synchronized (this.zzakd) {
            this.zzcrw = str;
        }
    }

    public void zzi(Context context, String str) {
        if (zzk(context, str)) {
            zzb.zzdd("Device is linked for in app preview.");
        } else {
            zzn(context, str);
        }
    }

    public void zzj(Context context, String str) {
        if (zzl(context, str)) {
            zzb.zzdd("Device is linked for debug signals.");
        } else {
            zzn(context, str);
        }
    }

    boolean zzk(Context context, String str) {
        Object zzm = zzm(context, zze(context, (String) zzdi.zzbhs.get(), str).toString());
        if (TextUtils.isEmpty(zzm)) {
            zzb.zzdd("Not linked for in app preview.");
            return false;
        }
        zzdb(zzm.trim());
        return true;
    }

    boolean zzl(Context context, String str) {
        Object zzm = zzm(context, zze(context, (String) zzdi.zzbht.get(), str).toString());
        if (TextUtils.isEmpty(zzm)) {
            zzb.zzdd("Not linked for debug signals.");
            return false;
        }
        boolean parseBoolean = Boolean.parseBoolean(zzm.trim());
        zzai(parseBoolean);
        return parseBoolean;
    }

    protected String zzm(Context context, final String str) {
        Throwable th;
        String str2;
        String valueOf;
        zzlj zza = new zzky(context).zza(str, new zza<String>(this) {
            final /* synthetic */ zzkv zzcry;

            public /* synthetic */ Object zzh(InputStream inputStream) {
                return zzi(inputStream);
            }

            public String zzi(InputStream inputStream) {
                String str;
                try {
                    str = new String(zzo.zza(inputStream, true), Key.STRING_CHARSET_NAME);
                    String str2 = str;
                    zzb.zzdd(new StringBuilder((String.valueOf(str2).length() + 49) + String.valueOf(str).length()).append("Response received from server. \nURL: ").append(str2).append("\n Response: ").append(str).toString());
                    return str;
                } catch (Throwable e) {
                    Throwable th = e;
                    String str3 = "Error connecting to url: ";
                    str = String.valueOf(str);
                    zzb.zzd(str.length() != 0 ? str3.concat(str) : new String(str3), th);
                    return null;
                }
            }

            public /* synthetic */ Object zzrs() {
                return zzuv();
            }

            public String zzuv() {
                String str = "Error getting a response from: ";
                String valueOf = String.valueOf(str);
                zzb.zzdf(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                return null;
            }
        });
        try {
            return (String) zza.get((long) ((Integer) zzdi.zzbhv.get()).intValue(), TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            th = e;
            str2 = "Timeout while retriving a response from: ";
            valueOf = String.valueOf(str);
            zzb.zzb(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            zza.cancel(true);
        } catch (Throwable e2) {
            th = e2;
            str2 = "Interrupted while retriving a response from: ";
            valueOf = String.valueOf(str);
            zzb.zzb(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            zza.cancel(true);
        } catch (Throwable e22) {
            th = e22;
            String str3 = "Error retriving a response from: ";
            valueOf = String.valueOf(str);
            zzb.zzb(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3), th);
        }
        return null;
    }

    public String zzut() {
        String str;
        synchronized (this.zzakd) {
            str = this.zzcrw;
        }
        return str;
    }

    public boolean zzuu() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcrx;
        }
        return z;
    }
}
