package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzke.zza;

@zziy
@TargetApi(19)
public class zzio extends zzim {
    private Object zzcde = new Object();
    private PopupWindow zzcdf;
    private boolean zzcdg = false;

    zzio(Context context, zza com_google_android_gms_internal_zzke_zza, zzlt com_google_android_gms_internal_zzlt, zzil.zza com_google_android_gms_internal_zzil_zza) {
        super(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzlt, com_google_android_gms_internal_zzil_zza);
    }

    private void zzrc() {
        synchronized (this.zzcde) {
            this.zzcdg = true;
            if ((this.mContext instanceof Activity) && ((Activity) this.mContext).isDestroyed()) {
                this.zzcdf = null;
            }
            if (this.zzcdf != null) {
                if (this.zzcdf.isShowing()) {
                    this.zzcdf.dismiss();
                }
                this.zzcdf = null;
            }
        }
    }

    public void cancel() {
        zzrc();
        super.cancel();
    }

    protected void zzal(int i) {
        zzrc();
        super.zzal(i);
    }

    protected void zzrb() {
        Window window = this.mContext instanceof Activity ? ((Activity) this.mContext).getWindow() : null;
        if (window != null && window.getDecorView() != null && !((Activity) this.mContext).isDestroyed()) {
            View frameLayout = new FrameLayout(this.mContext);
            frameLayout.setLayoutParams(new LayoutParams(-1, -1));
            frameLayout.addView(this.zzbkr.getView(), -1, -1);
            synchronized (this.zzcde) {
                if (this.zzcdg) {
                    return;
                }
                this.zzcdf = new PopupWindow(frameLayout, 1, 1, false);
                this.zzcdf.setOutsideTouchable(true);
                this.zzcdf.setClippingEnabled(false);
                zzb.zzdd("Displaying the 1x1 popup off the screen.");
                try {
                    this.zzcdf.showAtLocation(window.getDecorView(), 0, -1, -1);
                } catch (Exception e) {
                    this.zzcdf = null;
                }
            }
        }
    }
}
