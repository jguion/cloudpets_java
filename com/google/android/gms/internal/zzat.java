package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzae.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class zzat extends zzas {
    private static final String TAG = zzat.class.getSimpleName();

    protected zzat(Context context, String str, boolean z) {
        super(context, str, z);
    }

    public static zzat zza(String str, Context context, boolean z) {
        zzas.zza(context, z);
        return new zzat(context, str, z);
    }

    protected List<Callable<Void>> zzb(zzbb com_google_android_gms_internal_zzbb, zza com_google_android_gms_internal_zzae_zza) {
        if (com_google_android_gms_internal_zzbb.zzch() == null || !this.zzagr) {
            return super.zzb(com_google_android_gms_internal_zzbb, com_google_android_gms_internal_zzae_zza);
        }
        int zzau = com_google_android_gms_internal_zzbb.zzau();
        List<Callable<Void>> arrayList = new ArrayList();
        arrayList.addAll(super.zzb(com_google_android_gms_internal_zzbb, com_google_android_gms_internal_zzae_zza));
        arrayList.add(new zzbk(com_google_android_gms_internal_zzbb, zzax.zzbj(), zzax.zzbk(), com_google_android_gms_internal_zzae_zza, zzau, 24));
        return arrayList;
    }
}
