package com.google.android.gms.internal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;

@zziy
public class zzku {
    private final Context mContext;
    private int mState;
    private String zzang;
    private final float zzbvv;
    private String zzcro;
    private float zzcrp;
    private float zzcrq;
    private float zzcrr;

    public zzku(Context context) {
        this.mState = 0;
        this.mContext = context;
        this.zzbvv = context.getResources().getDisplayMetrics().density;
    }

    public zzku(Context context, String str) {
        this(context);
        this.zzcro = str;
    }

    static String zzda(String str) {
        if (TextUtils.isEmpty(str)) {
            return "No debug information";
        }
        Uri build = new Builder().encodedQuery(str.replaceAll("\\+", "%20")).build();
        StringBuilder stringBuilder = new StringBuilder();
        Map zzg = zzu.zzfz().zzg(build);
        for (String str2 : zzg.keySet()) {
            stringBuilder.append(str2).append(" = ").append((String) zzg.get(str2)).append("\n\n");
        }
        Object trim = stringBuilder.toString().trim();
        return TextUtils.isEmpty(trim) ? "No debug information" : trim;
    }

    private void zzur() {
        if (this.mContext instanceof Activity) {
            CharSequence string;
            CharSequence string2;
            CharSequence string3;
            Resources resources = zzu.zzgd().getResources();
            if (resources != null) {
                string = resources.getString(R.string.debug_menu_title);
            } else {
                Object obj = "Select a Debug Mode";
            }
            if (resources != null) {
                string2 = resources.getString(R.string.debug_menu_ad_information);
            } else {
                Object obj2 = "Ad Information";
            }
            if (resources != null) {
                string3 = resources.getString(R.string.debug_menu_creative_preview);
            } else {
                Object obj3 = "Creative Preview";
            }
            String string4 = resources != null ? resources.getString(R.string.debug_menu_troubleshooting) : "Troubleshooting";
            new AlertDialog.Builder(this.mContext).setTitle(string).setItems(new String[]{string2, string3, string4}, new OnClickListener(this) {
                final /* synthetic */ zzku zzcrs;

                {
                    this.zzcrs = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i) {
                        case 0:
                            this.zzcrs.zzus();
                            return;
                        case 1:
                            zzb.zzdd("Debug mode [Creative Preview] selected.");
                            zzkq.zza(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 zzcrt;

                                {
                                    this.zzcrt = r1;
                                }

                                public void run() {
                                    zzu.zzgh().zzi(this.zzcrt.zzcrs.mContext, this.zzcrt.zzcrs.zzang);
                                }
                            });
                            return;
                        case 2:
                            zzb.zzdd("Debug mode [Troubleshooting] selected.");
                            zzkq.zza(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 zzcrt;

                                {
                                    this.zzcrt = r1;
                                }

                                public void run() {
                                    zzu.zzgh().zzj(this.zzcrt.zzcrs.mContext, this.zzcrt.zzcrs.zzang);
                                }
                            });
                            return;
                        default:
                            return;
                    }
                }
            }).create().show();
            return;
        }
        zzb.zzde("Can not create dialog without Activity Context");
    }

    private void zzus() {
        if (this.mContext instanceof Activity) {
            final Object zzda = zzda(this.zzcro);
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            builder.setMessage(zzda);
            builder.setTitle("Ad Information");
            builder.setPositiveButton("Share", new OnClickListener(this) {
                final /* synthetic */ zzku zzcrs;

                public void onClick(DialogInterface dialogInterface, int i) {
                    zzu.zzfz().zzb(this.zzcrs.mContext, Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", zzda), "Share via"));
                }
            });
            builder.setNegativeButton("Close", new OnClickListener(this) {
                final /* synthetic */ zzku zzcrs;

                {
                    this.zzcrs = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();
            return;
        }
        zzb.zzde("Can not create dialog without Activity Context");
    }

    public void setAdUnitId(String str) {
        this.zzang = str;
    }

    public void showDialog() {
        if (((Boolean) zzdi.zzbhq.get()).booleanValue()) {
            zzur();
        } else {
            zzus();
        }
    }

    void zza(int i, float f, float f2) {
        if (i == 0) {
            this.mState = 0;
            this.zzcrp = f;
            this.zzcrq = f2;
            this.zzcrr = f2;
        } else if (this.mState == -1) {
        } else {
            if (i == 2) {
                if (f2 > this.zzcrq) {
                    this.zzcrq = f2;
                } else if (f2 < this.zzcrr) {
                    this.zzcrr = f2;
                }
                if (this.zzcrq - this.zzcrr > 30.0f * this.zzbvv) {
                    this.mState = -1;
                    return;
                }
                if (this.mState == 0 || this.mState == 2) {
                    if (f - this.zzcrp >= 50.0f * this.zzbvv) {
                        this.zzcrp = f;
                        this.mState++;
                    }
                } else if ((this.mState == 1 || this.mState == 3) && f - this.zzcrp <= -50.0f * this.zzbvv) {
                    this.zzcrp = f;
                    this.mState++;
                }
                if (this.mState == 1 || this.mState == 3) {
                    if (f > this.zzcrp) {
                        this.zzcrp = f;
                    }
                } else if (this.mState == 2 && f < this.zzcrp) {
                    this.zzcrp = f;
                }
            } else if (i == 1 && this.mState == 4) {
                showDialog();
            }
        }
    }

    public void zzcz(String str) {
        this.zzcro = str;
    }

    public void zzg(MotionEvent motionEvent) {
        int historySize = motionEvent.getHistorySize();
        for (int i = 0; i < historySize; i++) {
            zza(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i));
        }
        zza(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }
}
