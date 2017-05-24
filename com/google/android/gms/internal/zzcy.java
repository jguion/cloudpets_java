package com.google.android.gms.internal;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import java.security.MessageDigest;

@zziy
public class zzcy extends zzcv {
    private MessageDigest zzavn;

    byte[] zza(String[] strArr) {
        int i = 0;
        if (strArr.length == 1) {
            return zzcx.zzn(zzcx.zzae(strArr[0]));
        }
        if (strArr.length < 5) {
            byte[] bArr = new byte[(strArr.length * 2)];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                byte[] zzq = zzq(zzcx.zzae(strArr[i2]));
                bArr[i2 * 2] = zzq[0];
                bArr[(i2 * 2) + 1] = zzq[1];
            }
            return bArr;
        }
        byte[] bArr2 = new byte[strArr.length];
        while (i < strArr.length) {
            bArr2[i] = zzp(zzcx.zzae(strArr[i]));
            i++;
        }
        return bArr2;
    }

    public byte[] zzac(String str) {
        byte[] bArr;
        int i = 4;
        byte[] zza = zza(str.split(" "));
        this.zzavn = zzir();
        synchronized (this.zzakd) {
            if (this.zzavn == null) {
                bArr = new byte[0];
            } else {
                this.zzavn.reset();
                this.zzavn.update(zza);
                Object digest = this.zzavn.digest();
                if (digest.length <= 4) {
                    i = digest.length;
                }
                bArr = new byte[i];
                System.arraycopy(digest, 0, bArr, 0, bArr.length);
            }
        }
        return bArr;
    }

    byte zzp(int i) {
        return (byte) ((((i & 255) ^ ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8)) ^ ((16711680 & i) >> 16)) ^ ((ViewCompat.MEASURED_STATE_MASK & i) >> 24));
    }

    byte[] zzq(int i) {
        int i2 = (65535 & i) ^ ((SupportMenu.CATEGORY_MASK & i) >> 16);
        return new byte[]{(byte) i2, (byte) (i2 >> 8)};
    }
}
