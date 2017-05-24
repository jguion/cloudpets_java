package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdq;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzlt;
import java.util.HashMap;
import java.util.Map;

@zziy
public class zzk extends FrameLayout implements zzh {
    private final zzlt zzbkr;
    private String zzbnt;
    private final FrameLayout zzbyr;
    private final zzy zzbys;
    @Nullable
    private zzi zzbyt;
    private boolean zzbyu;
    private boolean zzbyv;
    private long zzbyw;
    private long zzbyx;

    public zzk(Context context, zzlt com_google_android_gms_internal_zzlt, int i, boolean z, zzdq com_google_android_gms_internal_zzdq) {
        super(context);
        this.zzbkr = com_google_android_gms_internal_zzlt;
        this.zzbyr = new FrameLayout(context);
        addView(this.zzbyr, new LayoutParams(-1, -1));
        zzc.zzu(com_google_android_gms_internal_zzlt.zzdp());
        this.zzbyt = com_google_android_gms_internal_zzlt.zzdp().zzamd.zza(context, com_google_android_gms_internal_zzlt, i, z, com_google_android_gms_internal_zzdq);
        if (this.zzbyt != null) {
            this.zzbyr.addView(this.zzbyt, new LayoutParams(-1, -1, 17));
            if (((Boolean) zzdi.zzbbo.get()).booleanValue()) {
                zzpn();
            }
        }
        this.zzbys = new zzy(this);
        this.zzbys.zzqh();
        if (this.zzbyt != null) {
            this.zzbyt.zza((zzh) this);
        }
        if (this.zzbyt == null) {
            zzl("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    private void zza(String str, String... strArr) {
        Map hashMap = new HashMap();
        hashMap.put("event", str);
        int length = strArr.length;
        int i = 0;
        Object obj = null;
        while (i < length) {
            Object obj2 = strArr[i];
            if (obj != null) {
                hashMap.put(obj, obj2);
                obj2 = null;
            }
            i++;
            obj = obj2;
        }
        this.zzbkr.zza("onVideoEvent", hashMap);
    }

    public static void zzi(zzlt com_google_android_gms_internal_zzlt) {
        Map hashMap = new HashMap();
        hashMap.put("event", "no_video_view");
        com_google_android_gms_internal_zzlt.zza("onVideoEvent", hashMap);
    }

    private void zzpp() {
        if (this.zzbkr.zzvn() != null && !this.zzbyu) {
            this.zzbyv = (this.zzbkr.zzvn().getWindow().getAttributes().flags & 128) != 0;
            if (!this.zzbyv) {
                this.zzbkr.zzvn().getWindow().addFlags(128);
                this.zzbyu = true;
            }
        }
    }

    private void zzpq() {
        if (this.zzbkr.zzvn() != null && this.zzbyu && !this.zzbyv) {
            this.zzbkr.zzvn().getWindow().clearFlags(128);
            this.zzbyu = false;
        }
    }

    public void destroy() {
        this.zzbys.cancel();
        if (this.zzbyt != null) {
            this.zzbyt.stop();
        }
        zzpq();
    }

    public void onPaused() {
        zza("pause", new String[0]);
        zzpq();
    }

    public void pause() {
        if (this.zzbyt != null) {
            this.zzbyt.pause();
        }
    }

    public void play() {
        if (this.zzbyt != null) {
            this.zzbyt.play();
        }
    }

    public void seekTo(int i) {
        if (this.zzbyt != null) {
            this.zzbyt.seekTo(i);
        }
    }

    public void zza(float f) {
        if (this.zzbyt != null) {
            this.zzbyt.zza(f);
        }
    }

    public void zza(float f, float f2) {
        if (this.zzbyt != null) {
            this.zzbyt.zza(f, f2);
        }
    }

    public void zzca(String str) {
        this.zzbnt = str;
    }

    public void zzd(int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(i3, i4);
            layoutParams.setMargins(i, i2, 0, 0);
            this.zzbyr.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    @TargetApi(14)
    public void zzf(MotionEvent motionEvent) {
        if (this.zzbyt != null) {
            this.zzbyt.dispatchTouchEvent(motionEvent);
        }
    }

    public void zzl(String str, @Nullable String str2) {
        zza("error", "what", str, "extra", str2);
    }

    public void zzmt() {
        if (this.zzbyt != null) {
            if (TextUtils.isEmpty(this.zzbnt)) {
                zza("no_src", new String[0]);
            } else {
                this.zzbyt.setVideoPath(this.zzbnt);
            }
        }
    }

    public void zzom() {
        if (this.zzbyt != null) {
            this.zzbyt.zzom();
        }
    }

    public void zzon() {
        if (this.zzbyt != null) {
            this.zzbyt.zzon();
        }
    }

    public void zzpi() {
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzk zzbyy;

            {
                this.zzbyy = r1;
            }

            public void run() {
                this.zzbyy.zza("surfaceCreated", new String[0]);
            }
        });
    }

    public void zzpj() {
        if (this.zzbyt != null && this.zzbyx == 0) {
            float duration = ((float) this.zzbyt.getDuration()) / 1000.0f;
            int videoWidth = this.zzbyt.getVideoWidth();
            int videoHeight = this.zzbyt.getVideoHeight();
            zza("canplaythrough", "duration", String.valueOf(duration), "videoWidth", String.valueOf(videoWidth), "videoHeight", String.valueOf(videoHeight));
        }
    }

    public void zzpk() {
        zzpp();
    }

    public void zzpl() {
        zza("ended", new String[0]);
        zzpq();
    }

    public void zzpm() {
        this.zzbyx = this.zzbyw;
        zzkr.zzcrf.post(new Runnable(this) {
            final /* synthetic */ zzk zzbyy;

            {
                this.zzbyy = r1;
            }

            public void run() {
                this.zzbyy.zza("surfaceDestroyed", new String[0]);
            }
        });
    }

    @TargetApi(14)
    public void zzpn() {
        if (this.zzbyt != null) {
            View textView = new TextView(this.zzbyt.getContext());
            String str = "AdMob - ";
            String valueOf = String.valueOf(this.zzbyt.zzog());
            textView.setText(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            this.zzbyr.addView(textView, new LayoutParams(-2, -2, 17));
            this.zzbyr.bringChildToFront(textView);
        }
    }

    void zzpo() {
        if (this.zzbyt != null) {
            long currentPosition = (long) this.zzbyt.getCurrentPosition();
            if (this.zzbyw != currentPosition && currentPosition > 0) {
                float f = ((float) currentPosition) / 1000.0f;
                zza("timeupdate", "time", String.valueOf(f));
                this.zzbyw = currentPosition;
            }
        }
    }
}
