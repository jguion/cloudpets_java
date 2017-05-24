package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@zziy
public class zzdk {
    final Context mContext;
    final String zzati;
    String zzbhz;
    BlockingQueue<zzdq> zzbib;
    ExecutorService zzbic;
    LinkedHashMap<String, String> zzbid = new LinkedHashMap();
    Map<String, zzdn> zzbie = new HashMap();
    private AtomicBoolean zzbif;
    private File zzbig;

    public zzdk(Context context, String str, String str2, Map<String, String> map) {
        this.mContext = context;
        this.zzati = str;
        this.zzbhz = str2;
        this.zzbif = new AtomicBoolean(false);
        this.zzbif.set(((Boolean) zzdi.zzbcc.get()).booleanValue());
        if (this.zzbif.get()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                this.zzbig = new File(externalStorageDirectory, "sdk_csi_data.txt");
            }
        }
        for (Entry entry : map.entrySet()) {
            this.zzbid.put((String) entry.getKey(), (String) entry.getValue());
        }
        this.zzbib = new ArrayBlockingQueue(30);
        this.zzbic = Executors.newSingleThreadExecutor();
        this.zzbic.execute(new Runnable(this) {
            final /* synthetic */ zzdk zzbih;

            {
                this.zzbih = r1;
            }

            public void run() {
                this.zzbih.zzkw();
            }
        });
        this.zzbie.put("action", zzdn.zzbij);
        this.zzbie.put("ad_format", zzdn.zzbij);
        this.zzbie.put("e", zzdn.zzbik);
    }

    private void zzc(@Nullable File file, String str) {
        FileOutputStream fileOutputStream;
        Throwable e;
        if (file != null) {
            try {
                fileOutputStream = new FileOutputStream(file, true);
                try {
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.write(10);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            return;
                        } catch (Throwable e2) {
                            zzb.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", e2);
                            return;
                        }
                    }
                    return;
                } catch (IOException e3) {
                    e2 = e3;
                    try {
                        zzb.zzd("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e2);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                return;
                            } catch (Throwable e22) {
                                zzb.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", e22);
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th) {
                        e22 = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable e4) {
                                zzb.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", e4);
                            }
                        }
                        throw e22;
                    }
                }
            } catch (IOException e5) {
                e22 = e5;
                fileOutputStream = null;
                zzb.zzd("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e22);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    return;
                }
                return;
            } catch (Throwable th2) {
                e22 = th2;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw e22;
            }
        }
        zzb.zzdf("CsiReporter: File doesn't exists. Cannot write CSI data to file.");
    }

    private void zzc(Map<String, String> map, String str) {
        String zza = zza(this.zzbhz, map, str);
        if (this.zzbif.get()) {
            zzc(this.zzbig, zza);
        } else {
            zzu.zzfz().zzc(this.mContext, this.zzati, zza);
        }
    }

    private void zzkw() {
        while (true) {
            try {
                zzdq com_google_android_gms_internal_zzdq = (zzdq) this.zzbib.take();
                String zzlc = com_google_android_gms_internal_zzdq.zzlc();
                if (!TextUtils.isEmpty(zzlc)) {
                    zzc(zza(this.zzbid, com_google_android_gms_internal_zzdq.zzm()), zzlc);
                }
            } catch (Throwable e) {
                zzb.zzd("CsiReporter:reporter interrupted", e);
                return;
            }
        }
    }

    String zza(String str, Map<String, String> map, @NonNull String str2) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        for (Entry entry : map.entrySet()) {
            buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        StringBuilder stringBuilder = new StringBuilder(buildUpon.build().toString());
        stringBuilder.append("&").append("it").append("=").append(str2);
        return stringBuilder.toString();
    }

    Map<String, String> zza(Map<String, String> map, @Nullable Map<String, String> map2) {
        Map<String, String> linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Entry entry : map2.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) linkedHashMap.get(str);
            linkedHashMap.put(str, zzat(str).zzg(str2, (String) entry.getValue()));
        }
        return linkedHashMap;
    }

    public boolean zza(zzdq com_google_android_gms_internal_zzdq) {
        return this.zzbib.offer(com_google_android_gms_internal_zzdq);
    }

    public zzdn zzat(String str) {
        zzdn com_google_android_gms_internal_zzdn = (zzdn) this.zzbie.get(str);
        return com_google_android_gms_internal_zzdn != null ? com_google_android_gms_internal_zzdn : zzdn.zzbii;
    }

    public void zzc(@Nullable List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.zzbid.put("e", TextUtils.join(",", list));
        }
    }
}
