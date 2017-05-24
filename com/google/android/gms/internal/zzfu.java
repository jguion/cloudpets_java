package com.google.android.gms.internal;

import android.os.Parcel;
import android.util.Base64;
import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@zziy
class zzfu {
    final String zzang;
    final AdRequestParcel zzaow;
    final int zzbpj;

    zzfu(AdRequestParcel adRequestParcel, String str, int i) {
        this.zzaow = adRequestParcel;
        this.zzang = str;
        this.zzbpj = i;
    }

    zzfu(zzfs com_google_android_gms_internal_zzfs) {
        this(com_google_android_gms_internal_zzfs.zzmo(), com_google_android_gms_internal_zzfs.getAdUnitId(), com_google_android_gms_internal_zzfs.getNetworkType());
    }

    static zzfu zzbj(String str) throws IOException {
        String[] split = str.split("\u0000");
        if (split.length != 3) {
            throw new IOException("Incorrect field count for QueueSeed.");
        }
        Parcel obtain = Parcel.obtain();
        try {
            String str2 = new String(Base64.decode(split[0], 0), Key.STRING_CHARSET_NAME);
            int parseInt = Integer.parseInt(split[1]);
            byte[] decode = Base64.decode(split[2], 0);
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            zzfu com_google_android_gms_internal_zzfu = new zzfu((AdRequestParcel) AdRequestParcel.CREATOR.createFromParcel(obtain), str2, parseInt);
            obtain.recycle();
            return com_google_android_gms_internal_zzfu;
        } catch (Throwable th) {
            obtain.recycle();
        }
    }

    String zzmv() {
        Parcel obtain = Parcel.obtain();
        String encodeToString;
        try {
            encodeToString = Base64.encodeToString(this.zzang.getBytes(Key.STRING_CHARSET_NAME), 0);
            String num = Integer.toString(this.zzbpj);
            this.zzaow.writeToParcel(obtain, 0);
            String encodeToString2 = Base64.encodeToString(obtain.marshall(), 0);
            encodeToString = new StringBuilder(((String.valueOf(encodeToString).length() + 2) + String.valueOf(num).length()) + String.valueOf(encodeToString2).length()).append(encodeToString).append("\u0000").append(num).append("\u0000").append(encodeToString2).toString();
            return encodeToString;
        } catch (UnsupportedEncodingException e) {
            encodeToString = "QueueSeed encode failed because UTF-8 is not available.";
            zzb.e(encodeToString);
            return "";
        } finally {
            obtain.recycle();
        }
    }
}
