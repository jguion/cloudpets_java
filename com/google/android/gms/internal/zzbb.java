package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.clearcut.zzb;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzae.zza;
import com.google.android.gms.internal.zzae.zzd;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzbb {
    private static final String TAG = zzbb.class.getSimpleName();
    protected static final Object zzaia = new Object();
    private static zzc zzaic = null;
    private volatile boolean zzagr = false;
    protected Context zzahn;
    protected Context zzaho;
    private ExecutorService zzahp;
    private DexClassLoader zzahq;
    private zzaw zzahr;
    private byte[] zzahs;
    private volatile AdvertisingIdClient zzaht = null;
    private Future zzahu = null;
    private volatile zza zzahv = null;
    private Future zzahw = null;
    private zzao zzahx;
    private GoogleApiClient zzahy = null;
    protected boolean zzahz = false;
    protected boolean zzaib = false;
    protected boolean zzaid = false;
    private Map<Pair<String, String>, zzbu> zzaie;

    private zzbb(Context context) {
        this.zzahn = context;
        this.zzaho = context.getApplicationContext();
        this.zzaie = new HashMap();
    }

    public static zzbb zza(Context context, String str, String str2, boolean z) {
        zzbb com_google_android_gms_internal_zzbb = new zzbb(context);
        try {
            if (com_google_android_gms_internal_zzbb.zzc(str, str2, z)) {
                return com_google_android_gms_internal_zzbb;
            }
        } catch (zzay e) {
        }
        return null;
    }

    @NonNull
    private File zza(String str, File file, String str2) throws zzaw.zza, IOException {
        File file2 = new File(String.format("%s/%s.jar", new Object[]{file, str2}));
        if (!file2.exists()) {
            byte[] zzc = this.zzahr.zzc(this.zzahs, str);
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(zzc, 0, zzc.length);
            fileOutputStream.close();
        }
        return file2;
    }

    private void zza(File file) {
        if (file.exists()) {
            file.delete();
            return;
        }
        Log.d(TAG, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
    }

    private void zza(File file, String str) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        File file2 = new File(String.format("%s/%s.tmp", new Object[]{file, str}));
        if (!file2.exists()) {
            File file3 = new File(String.format("%s/%s.dex", new Object[]{file, str}));
            if (file3.exists()) {
                long length = file3.length();
                if (length > 0) {
                    byte[] bArr = new byte[((int) length)];
                    FileInputStream fileInputStream2;
                    try {
                        fileInputStream2 = new FileInputStream(file3);
                        try {
                            if (fileInputStream2.read(bArr) <= 0) {
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException e) {
                                    }
                                }
                                zza(file3);
                                return;
                            }
                            zzark com_google_android_gms_internal_zzae_zzd = new zzd();
                            com_google_android_gms_internal_zzae_zzd.zzft = VERSION.SDK.getBytes();
                            com_google_android_gms_internal_zzae_zzd.zzfs = str.getBytes();
                            bArr = this.zzahr.zzd(this.zzahs, bArr).getBytes();
                            com_google_android_gms_internal_zzae_zzd.data = bArr;
                            com_google_android_gms_internal_zzae_zzd.zzfr = zzam.zzg(bArr);
                            file2.createNewFile();
                            fileOutputStream = new FileOutputStream(file2);
                            try {
                                byte[] zzf = zzark.zzf(com_google_android_gms_internal_zzae_zzd);
                                fileOutputStream.write(zzf, 0, zzf.length);
                                fileOutputStream.close();
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException e2) {
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e3) {
                                    }
                                }
                                zza(file3);
                            } catch (IOException e4) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e5) {
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e6) {
                                    }
                                }
                                zza(file3);
                            } catch (NoSuchAlgorithmException e7) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                zza(file3);
                            } catch (zzaw.zza e8) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                zza(file3);
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                fileOutputStream2 = fileOutputStream;
                                th = th3;
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException e9) {
                                    }
                                }
                                if (fileOutputStream2 != null) {
                                    try {
                                        fileOutputStream2.close();
                                    } catch (IOException e10) {
                                    }
                                }
                                zza(file3);
                                throw th;
                            }
                        } catch (IOException e11) {
                            fileOutputStream = null;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            zza(file3);
                        } catch (NoSuchAlgorithmException e12) {
                            fileOutputStream = null;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            zza(file3);
                        } catch (zzaw.zza e13) {
                            fileOutputStream = null;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            zza(file3);
                        } catch (Throwable th4) {
                            th = th4;
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            zza(file3);
                            throw th;
                        }
                    } catch (IOException e14) {
                        fileOutputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        zza(file3);
                    } catch (NoSuchAlgorithmException e15) {
                        fileOutputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        zza(file3);
                    } catch (zzaw.zza e16) {
                        fileOutputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        zza(file3);
                    } catch (Throwable th5) {
                        th = th5;
                        fileInputStream2 = null;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        zza(file3);
                        throw th;
                    }
                }
            }
        }
    }

    private boolean zzb(File file, String str) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        File file2 = new File(String.format("%s/%s.tmp", new Object[]{file, str}));
        if (!file2.exists()) {
            return false;
        }
        File file3 = new File(String.format("%s/%s.dex", new Object[]{file, str}));
        if (file3.exists()) {
            return false;
        }
        try {
            long length = file2.length();
            if (length <= 0) {
                zza(file2);
                return false;
            }
            byte[] bArr = new byte[((int) length)];
            fileInputStream = new FileInputStream(file2);
            try {
                if (fileInputStream.read(bArr) <= 0) {
                    Log.d(TAG, "Cannot read the cache data.");
                    zza(file2);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                        }
                    }
                    return false;
                }
                zzd zzd = zzd.zzd(bArr);
                if (str.equals(new String(zzd.zzfs)) && Arrays.equals(zzd.zzfr, zzam.zzg(zzd.data)) && Arrays.equals(zzd.zzft, VERSION.SDK.getBytes())) {
                    bArr = this.zzahr.zzc(this.zzahs, new String(zzd.data));
                    file3.createNewFile();
                    FileOutputStream fileOutputStream3 = new FileOutputStream(file3);
                    try {
                        fileOutputStream3.write(bArr, 0, bArr.length);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2) {
                            }
                        }
                        if (fileOutputStream3 == null) {
                            return true;
                        }
                        try {
                            fileOutputStream3.close();
                            return true;
                        } catch (IOException e3) {
                            return true;
                        }
                    } catch (IOException e4) {
                        fileOutputStream = fileOutputStream3;
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e5) {
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e6) {
                            }
                        }
                        return false;
                    } catch (NoSuchAlgorithmException e7) {
                        fileOutputStream = fileOutputStream3;
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return false;
                    } catch (zzaw.zza e8) {
                        fileOutputStream = fileOutputStream3;
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream2 = fileOutputStream3;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e9) {
                            }
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e10) {
                            }
                        }
                        throw th;
                    }
                }
                zza(file2);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e11) {
                    }
                }
                return false;
            } catch (IOException e12) {
                fileOutputStream = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return false;
            } catch (NoSuchAlgorithmException e13) {
                fileOutputStream = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return false;
            } catch (zzaw.zza e14) {
                fileOutputStream = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return false;
            } catch (Throwable th3) {
                th = th3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        } catch (IOException e15) {
            fileOutputStream = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return false;
        } catch (NoSuchAlgorithmException e16) {
            fileOutputStream = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return false;
        } catch (zzaw.zza e17) {
            fileOutputStream = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    private void zzc(boolean z) {
        this.zzagr = z;
        if (z) {
            this.zzahu = this.zzahp.submit(new Runnable(this) {
                final /* synthetic */ zzbb zzaif;

                {
                    this.zzaif = r1;
                }

                public void run() {
                    this.zzaif.zzcr();
                }
            });
        }
    }

    private boolean zzc(String str, String str2, boolean z) throws zzay {
        this.zzahp = Executors.newCachedThreadPool();
        zzc(z);
        zzcu();
        zzcs();
        this.zzahr = new zzaw(null);
        try {
            this.zzahs = this.zzahr.zzn(str);
            boolean zzo = zzo(str2);
            this.zzahx = new zzao(this);
            return zzo;
        } catch (Throwable e) {
            throw new zzay(e);
        }
    }

    private void zzcr() {
        try {
            if (this.zzaht == null && this.zzaho != null) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzaho);
                advertisingIdClient.start();
                this.zzaht = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException e) {
            this.zzaht = null;
        } catch (IOException e2) {
            this.zzaht = null;
        } catch (GooglePlayServicesRepairableException e3) {
            this.zzaht = null;
        }
    }

    private void zzct() {
        if (this.zzaib) {
            try {
                this.zzahv = com.google.android.gms.gass.internal.zza.zzi(this.zzahn, this.zzahn.getPackageName(), Integer.toString(this.zzahn.getPackageManager().getPackageInfo(this.zzahn.getPackageName(), 0).versionCode));
            } catch (NameNotFoundException e) {
            }
        }
    }

    private void zzcu() {
        boolean z = true;
        zzaic = zzc.zzapd();
        this.zzahz = zzaic.zzbo(this.zzahn) > 0;
        if (zzaic.isGooglePlayServicesAvailable(this.zzahn) != 0) {
            z = false;
        }
        this.zzaib = z;
        if (this.zzahn.getApplicationContext() != null) {
            this.zzahy = new Builder(this.zzahn).addApi(zzb.API).build();
        }
        zzdi.initialize(this.zzahn);
    }

    private boolean zzo(String str) throws zzay {
        File file;
        String zzax;
        File zza;
        try {
            File cacheDir = this.zzahn.getCacheDir();
            if (cacheDir == null) {
                cacheDir = this.zzahn.getDir("dex", 0);
                if (cacheDir == null) {
                    throw new zzay();
                }
            }
            file = cacheDir;
            zzax = zzax.zzax();
            zza = zza(str, file, zzax);
            zzb(file, zzax);
            this.zzahq = new DexClassLoader(zza.getAbsolutePath(), file.getAbsolutePath(), null, this.zzahn.getClassLoader());
            zza(zza);
            zza(file, zzax);
            zzp(String.format("%s/%s.dex", new Object[]{file, zzax}));
            return true;
        } catch (Throwable e) {
            throw new zzay(e);
        } catch (Throwable e2) {
            throw new zzay(e2);
        } catch (Throwable e22) {
            throw new zzay(e22);
        } catch (Throwable e222) {
            throw new zzay(e222);
        } catch (Throwable th) {
            zza(zza);
            zza(file, zzax);
            zzp(String.format("%s/%s.dex", new Object[]{file, zzax}));
        }
    }

    private void zzp(String str) {
        zza(new File(str));
    }

    public Context getApplicationContext() {
        return this.zzaho;
    }

    public Context getContext() {
        return this.zzahn;
    }

    public boolean zza(String str, String str2, List<Class> list) {
        if (this.zzaie.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzaie.put(new Pair(str, str2), new zzbu(this, str, str2, list));
        return true;
    }

    public int zzau() {
        zzao zzco = zzco();
        return zzco != null ? zzco.zzau() : Integer.MIN_VALUE;
    }

    public Method zzc(String str, String str2) {
        zzbu com_google_android_gms_internal_zzbu = (zzbu) this.zzaie.get(new Pair(str, str2));
        return com_google_android_gms_internal_zzbu == null ? null : com_google_android_gms_internal_zzbu.zzdd();
    }

    public ExecutorService zzch() {
        return this.zzahp;
    }

    public DexClassLoader zzci() {
        return this.zzahq;
    }

    public zzaw zzcj() {
        return this.zzahr;
    }

    public byte[] zzck() {
        return this.zzahs;
    }

    public GoogleApiClient zzcl() {
        return this.zzahy;
    }

    public boolean zzcm() {
        return this.zzahz;
    }

    public boolean zzcn() {
        return this.zzaid;
    }

    public zzao zzco() {
        return this.zzahx;
    }

    public zza zzcp() {
        return this.zzahv;
    }

    public Future zzcq() {
        return this.zzahw;
    }

    void zzcs() {
        if (((Boolean) zzdi.zzbfh.get()).booleanValue()) {
            this.zzahw = this.zzahp.submit(new Runnable(this) {
                final /* synthetic */ zzbb zzaif;

                {
                    this.zzaif = r1;
                }

                public void run() {
                    this.zzaif.zzct();
                }
            });
        }
    }

    public AdvertisingIdClient zzcv() {
        if (!this.zzagr) {
            return null;
        }
        if (this.zzaht != null) {
            return this.zzaht;
        }
        if (this.zzahu != null) {
            try {
                this.zzahu.get(2000, TimeUnit.MILLISECONDS);
                this.zzahu = null;
            } catch (InterruptedException e) {
            } catch (ExecutionException e2) {
            } catch (TimeoutException e3) {
                this.zzahu.cancel(true);
            }
        }
        return this.zzaht;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzcw() {
        /*
        r2 = this;
        r1 = zzaia;
        monitor-enter(r1);
        r0 = r2.zzaid;	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
    L_0x0008:
        return;
    L_0x0009:
        r0 = r2.zzaib;	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x001e;
    L_0x000d:
        r0 = r2.zzahy;	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x001e;
    L_0x0011:
        r0 = r2.zzahy;	 Catch:{ all -> 0x001b }
        r0.connect();	 Catch:{ all -> 0x001b }
        r0 = 1;
        r2.zzaid = r0;	 Catch:{ all -> 0x001b }
    L_0x0019:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        goto L_0x0008;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        throw r0;
    L_0x001e:
        r0 = 0;
        r2.zzaid = r0;	 Catch:{ all -> 0x001b }
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbb.zzcw():void");
    }

    public void zzcx() {
        synchronized (zzaia) {
            if (this.zzaid && this.zzahy != null) {
                this.zzahy.disconnect();
                this.zzaid = false;
            }
        }
    }
}
