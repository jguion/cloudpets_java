package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzfy;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkg;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.internal.zzlm.zzc;
import com.google.android.gms.internal.zzlt;
import java.util.Map;
import org.json.JSONObject;

@zziy
public class zzg {
    private Context mContext;
    private final Object zzakd = new Object();
    public final zzev zzamn = new zzev(this) {
        final /* synthetic */ zzg zzamo;

        {
            this.zzamo = r1;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            com_google_android_gms_internal_zzlt.zzb("/appSettingsFetched", (zzev) this);
            synchronized (this.zzamo.zzakd) {
                if (map != null) {
                    if ("true".equalsIgnoreCase((String) map.get("isSuccessful"))) {
                        zzu.zzgd().zzd(this.zzamo.mContext, (String) map.get("appSettingsJson"));
                    }
                }
            }
        }
    };

    private static boolean zza(@Nullable zzkg com_google_android_gms_internal_zzkg) {
        if (com_google_android_gms_internal_zzkg == null) {
            return true;
        }
        boolean z = (((zzu.zzgf().currentTimeMillis() - com_google_android_gms_internal_zzkg.zztf()) > ((Long) zzdi.zzbgs.get()).longValue() ? 1 : ((zzu.zzgf().currentTimeMillis() - com_google_android_gms_internal_zzkg.zztf()) == ((Long) zzdi.zzbgs.get()).longValue() ? 0 : -1)) > 0) || !com_google_android_gms_internal_zzkg.zztg();
        return z;
    }

    public void zza(Context context, VersionInfoParcel versionInfoParcel, boolean z, @Nullable zzkg com_google_android_gms_internal_zzkg, String str, @Nullable String str2) {
        if (!zza(com_google_android_gms_internal_zzkg)) {
            return;
        }
        if (context == null) {
            zzb.zzdf("Context not provided to fetch application settings");
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            zzb.zzdf("App settings could not be fetched. Required parameters missing");
        } else {
            this.mContext = context;
            final zzfy zzc = zzu.zzfz().zzc(context, versionInfoParcel);
            final String str3 = str;
            final String str4 = str2;
            final boolean z2 = z;
            final Context context2 = context;
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzg zzamo;

                public void run() {
                    zzc.zzmy().zza(new zzc<zzfz>(this) {
                        final /* synthetic */ AnonymousClass2 zzamu;

                        {
                            this.zzamu = r1;
                        }

                        public void zzb(zzfz com_google_android_gms_internal_zzfz) {
                            com_google_android_gms_internal_zzfz.zza("/appSettingsFetched", this.zzamu.zzamo.zzamn);
                            try {
                                JSONObject jSONObject = new JSONObject();
                                if (!TextUtils.isEmpty(str3)) {
                                    jSONObject.put("app_id", str3);
                                } else if (!TextUtils.isEmpty(str4)) {
                                    jSONObject.put("ad_unit_id", str4);
                                }
                                jSONObject.put("is_init", z2);
                                jSONObject.put("pn", context2.getPackageName());
                                com_google_android_gms_internal_zzfz.zza("AFMA_fetchAppSettings", jSONObject);
                            } catch (Throwable e) {
                                com_google_android_gms_internal_zzfz.zzb("/appSettingsFetched", this.zzamu.zzamo.zzamn);
                                zzb.zzb("Error requesting application settings", e);
                            }
                        }

                        public /* synthetic */ void zzd(Object obj) {
                            zzb((zzfz) obj);
                        }
                    }, new zzlm.zzb());
                }
            });
        }
    }
}
