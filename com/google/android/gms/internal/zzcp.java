package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzfy.zzc;
import com.google.android.gms.internal.zzlm.zza;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public class zzcp extends zzcj {
    private zzc zzatp;
    private boolean zzatq;

    public zzcp(Context context, AdSizeParcel adSizeParcel, zzke com_google_android_gms_internal_zzke, VersionInfoParcel versionInfoParcel, zzcq com_google_android_gms_internal_zzcq, zzfy com_google_android_gms_internal_zzfy) {
        super(context, adSizeParcel, com_google_android_gms_internal_zzke, versionInfoParcel, com_google_android_gms_internal_zzcq);
        this.zzatp = com_google_android_gms_internal_zzfy.zzmy();
        try {
            final JSONObject zzd = zzd(com_google_android_gms_internal_zzcq.zzhw().zzhu());
            this.zzatp.zza(new zzlm.zzc<zzfz>(this) {
                final /* synthetic */ zzcp zzats;

                public void zzb(zzfz com_google_android_gms_internal_zzfz) {
                    this.zzats.zza(zzd);
                }

                public /* synthetic */ void zzd(Object obj) {
                    zzb((zzfz) obj);
                }
            }, new zza(this) {
                final /* synthetic */ zzcp zzats;

                {
                    this.zzats = r1;
                }

                public void run() {
                }
            });
        } catch (JSONException e) {
        } catch (Throwable e2) {
            zzb.zzb("Failure while processing active view data.", e2);
        }
        this.zzatp.zza(new zzlm.zzc<zzfz>(this) {
            final /* synthetic */ zzcp zzats;

            {
                this.zzats = r1;
            }

            public void zzb(zzfz com_google_android_gms_internal_zzfz) {
                this.zzats.zzatq = true;
                this.zzats.zzc(com_google_android_gms_internal_zzfz);
                this.zzats.zzhj();
                this.zzats.zzk(3);
            }

            public /* synthetic */ void zzd(Object obj) {
                zzb((zzfz) obj);
            }
        }, new zza(this) {
            final /* synthetic */ zzcp zzats;

            {
                this.zzats = r1;
            }

            public void run() {
                this.zzats.destroy();
            }
        });
        String str = "Tracking ad unit: ";
        String valueOf = String.valueOf(this.zzasj.zzia());
        zzb.zzdd(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected void destroy() {
        synchronized (this.zzakd) {
            super.destroy();
            this.zzatp.zza(new zzlm.zzc<zzfz>(this) {
                final /* synthetic */ zzcp zzats;

                {
                    this.zzats = r1;
                }

                public void zzb(zzfz com_google_android_gms_internal_zzfz) {
                    this.zzats.zzd(com_google_android_gms_internal_zzfz);
                }

                public /* synthetic */ void zzd(Object obj) {
                    zzb((zzfz) obj);
                }
            }, new zzlm.zzb());
            this.zzatp.release();
        }
    }

    protected void zzb(final JSONObject jSONObject) {
        this.zzatp.zza(new zzlm.zzc<zzfz>(this) {
            final /* synthetic */ zzcp zzats;

            public void zzb(zzfz com_google_android_gms_internal_zzfz) {
                com_google_android_gms_internal_zzfz.zza("AFMA_updateActiveView", jSONObject);
            }

            public /* synthetic */ void zzd(Object obj) {
                zzb((zzfz) obj);
            }
        }, new zzlm.zzb());
    }

    protected boolean zzhr() {
        return this.zzatq;
    }
}
