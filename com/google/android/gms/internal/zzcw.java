package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

@zziy
public class zzcw {
    private final int zzavg;
    private final int zzavh;
    private final int zzavi;
    private final zzcv zzavj = new zzcy();

    static class zza {
        ByteArrayOutputStream zzavl = new ByteArrayOutputStream(4096);
        Base64OutputStream zzavm = new Base64OutputStream(this.zzavl, 10);

        public String toString() {
            String byteArrayOutputStream;
            try {
                this.zzavm.close();
            } catch (Throwable e) {
                zzb.zzb("HashManager: Unable to convert to Base64.", e);
            }
            try {
                this.zzavl.close();
                byteArrayOutputStream = this.zzavl.toString();
            } catch (Throwable e2) {
                zzb.zzb("HashManager: Unable to convert to Base64.", e2);
                byteArrayOutputStream = "";
            } finally {
                this.zzavl = null;
                this.zzavm = null;
            }
            return byteArrayOutputStream;
        }

        public void write(byte[] bArr) throws IOException {
            this.zzavm.write(bArr);
        }
    }

    public zzcw(int i) {
        this.zzavh = i;
        this.zzavg = 6;
        this.zzavi = 0;
    }

    public String zza(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(((String) it.next()).toLowerCase(Locale.US));
            stringBuffer.append('\n');
        }
        return zzad(stringBuffer.toString());
    }

    String zzad(String str) {
        String[] split = str.split("\n");
        if (split.length == 0) {
            return "";
        }
        zza zzis = zzis();
        PriorityQueue priorityQueue = new PriorityQueue(this.zzavh, new Comparator<com.google.android.gms.internal.zzcz.zza>(this) {
            final /* synthetic */ zzcw zzavk;

            {
                this.zzavk = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return zza((com.google.android.gms.internal.zzcz.zza) obj, (com.google.android.gms.internal.zzcz.zza) obj2);
            }

            public int zza(com.google.android.gms.internal.zzcz.zza com_google_android_gms_internal_zzcz_zza, com.google.android.gms.internal.zzcz.zza com_google_android_gms_internal_zzcz_zza2) {
                int i = com_google_android_gms_internal_zzcz_zza.zzavp - com_google_android_gms_internal_zzcz_zza2.zzavp;
                return i != 0 ? i : (int) (com_google_android_gms_internal_zzcz_zza.value - com_google_android_gms_internal_zzcz_zza2.value);
            }
        });
        for (String zzaf : split) {
            String[] zzaf2 = zzcx.zzaf(zzaf);
            if (zzaf2.length != 0) {
                zzcz.zza(zzaf2, this.zzavh, this.zzavg, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                zzis.write(this.zzavj.zzac(((com.google.android.gms.internal.zzcz.zza) it.next()).zzavo));
            } catch (Throwable e) {
                zzb.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zzis.toString();
    }

    zza zzis() {
        return new zza();
    }
}
