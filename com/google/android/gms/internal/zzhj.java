package com.google.android.gms.internal;

import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.zzu;
import com.google.common.net.HttpHeaders;
import java.util.Map;

@zziy
public class zzhj extends zzhm {
    private final Context mContext;
    private final Map<String, String> zzbiq;

    public zzhj(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
        super(com_google_android_gms_internal_zzlt, "storePicture");
        this.zzbiq = map;
        this.mContext = com_google_android_gms_internal_zzlt.zzvn();
    }

    public void execute() {
        if (this.mContext == null) {
            zzbx("Activity context is not available");
        } else if (zzu.zzfz().zzag(this.mContext).zzkl()) {
            final String str = (String) this.zzbiq.get("iurl");
            if (TextUtils.isEmpty(str)) {
                zzbx("Image url cannot be empty.");
            } else if (URLUtil.isValidUrl(str)) {
                final String zzbw = zzbw(str);
                if (zzu.zzfz().zzcx(zzbw)) {
                    Resources resources = zzu.zzgd().getResources();
                    Builder zzaf = zzu.zzfz().zzaf(this.mContext);
                    zzaf.setTitle(resources != null ? resources.getString(R.string.store_picture_title) : "Save image");
                    zzaf.setMessage(resources != null ? resources.getString(R.string.store_picture_message) : "Allow Ad to store image in Picture gallery?");
                    zzaf.setPositiveButton(resources != null ? resources.getString(R.string.accept) : HttpHeaders.ACCEPT, new OnClickListener(this) {
                        final /* synthetic */ zzhj zzbvn;

                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                ((DownloadManager) this.zzbvn.mContext.getSystemService("download")).enqueue(this.zzbvn.zzk(str, zzbw));
                            } catch (IllegalStateException e) {
                                this.zzbvn.zzbx("Could not store picture.");
                            }
                        }
                    });
                    zzaf.setNegativeButton(resources != null ? resources.getString(R.string.decline) : "Decline", new OnClickListener(this) {
                        final /* synthetic */ zzhj zzbvn;

                        {
                            this.zzbvn = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.zzbvn.zzbx("User canceled the download.");
                        }
                    });
                    zzaf.create().show();
                    return;
                }
                r1 = "Image type not recognized: ";
                str = String.valueOf(zzbw);
                zzbx(str.length() != 0 ? r1.concat(str) : new String(r1));
            } else {
                r1 = "Invalid image url: ";
                str = String.valueOf(str);
                zzbx(str.length() != 0 ? r1.concat(str) : new String(r1));
            }
        } else {
            zzbx("Feature is not supported by the device.");
        }
    }

    String zzbw(String str) {
        return Uri.parse(str).getLastPathSegment();
    }

    Request zzk(String str, String str2) {
        Request request = new Request(Uri.parse(str));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
        zzu.zzgb().zza(request);
        return request;
    }
}
