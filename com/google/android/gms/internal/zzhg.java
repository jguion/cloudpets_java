package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract.Events;
import android.text.TextUtils;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.zzu;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Map;

@zziy
public class zzhg extends zzhm {
    private final Context mContext;
    private final Map<String, String> zzbiq;
    private String zzbup;
    private long zzbuq;
    private long zzbur;
    private String zzbus;
    private String zzbut;

    public zzhg(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        super(com_google_android_gms_internal_zzlt, "createCalendarEvent");
        this.zzbiq = map;
        this.mContext = com_google_android_gms_internal_zzlt.zzvn();
        zznr();
    }

    private String zzbu(String str) {
        return TextUtils.isEmpty((CharSequence) this.zzbiq.get(str)) ? "" : (String) this.zzbiq.get(str);
    }

    private long zzbv(String str) {
        String str2 = (String) this.zzbiq.get(str);
        if (str2 == null) {
            return -1;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void zznr() {
        this.zzbup = zzbu("description");
        this.zzbus = zzbu("summary");
        this.zzbuq = zzbv("start_ticks");
        this.zzbur = zzbv("end_ticks");
        this.zzbut = zzbu(Param.LOCATION);
    }

    @TargetApi(14)
    Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(Events.CONTENT_URI);
        data.putExtra("title", this.zzbup);
        data.putExtra("eventLocation", this.zzbut);
        data.putExtra("description", this.zzbus);
        if (this.zzbuq > -1) {
            data.putExtra("beginTime", this.zzbuq);
        }
        if (this.zzbur > -1) {
            data.putExtra("endTime", this.zzbur);
        }
        data.setFlags(268435456);
        return data;
    }

    public void execute() {
        if (this.mContext == null) {
            zzbx("Activity context is not available.");
        } else if (zzu.zzfz().zzag(this.mContext).zzko()) {
            Builder zzaf = zzu.zzfz().zzaf(this.mContext);
            Resources resources = zzu.zzgd().getResources();
            zzaf.setTitle(resources != null ? resources.getString(R.string.create_calendar_title) : "Create calendar event");
            zzaf.setMessage(resources != null ? resources.getString(R.string.create_calendar_message) : "Allow Ad to create a calendar event?");
            zzaf.setPositiveButton(resources != null ? resources.getString(R.string.accept) : HttpHeaders.ACCEPT, new OnClickListener(this) {
                final /* synthetic */ zzhg zzbuu;

                {
                    this.zzbuu = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    zzu.zzfz().zzb(this.zzbuu.mContext, this.zzbuu.createIntent());
                }
            });
            zzaf.setNegativeButton(resources != null ? resources.getString(R.string.decline) : "Decline", new OnClickListener(this) {
                final /* synthetic */ zzhg zzbuu;

                {
                    this.zzbuu = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.zzbuu.zzbx("Operation denied by user.");
                }
            });
            zzaf.create().show();
        } else {
            zzbx("This feature is not available on the device.");
        }
    }
}
