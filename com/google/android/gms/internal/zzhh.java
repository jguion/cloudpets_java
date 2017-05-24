package com.google.android.gms.internal;

import android.app.Activity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.zzf;
import java.util.Map;
import java.util.Set;

@zziy
public class zzhh extends zzhm {
    static final Set<String> zzbuv = zzf.zzc("top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center");
    private int zzajw = -1;
    private int zzajx = -1;
    private final Object zzakd = new Object();
    private AdSizeParcel zzapc;
    private final zzlt zzbkr;
    private final Activity zzbul;
    private String zzbuw = "top-right";
    private boolean zzbux = true;
    private int zzbuy = 0;
    private int zzbuz = 0;
    private int zzbva = 0;
    private int zzbvb = 0;
    private ImageView zzbvc;
    private LinearLayout zzbvd;
    private zzhn zzbve;
    private PopupWindow zzbvf;
    private RelativeLayout zzbvg;
    private ViewGroup zzbvh;

    public zzhh(zzlt com_google_android_gms_internal_zzlt, zzhn com_google_android_gms_internal_zzhn) {
        super(com_google_android_gms_internal_zzlt, "resize");
        this.zzbkr = com_google_android_gms_internal_zzlt;
        this.zzbul = com_google_android_gms_internal_zzlt.zzvn();
        this.zzbve = com_google_android_gms_internal_zzhn;
    }

    private void zzi(Map<String, String> map) {
        if (!TextUtils.isEmpty((CharSequence) map.get("width"))) {
            this.zzajw = zzu.zzfz().zzcw((String) map.get("width"));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get("height"))) {
            this.zzajx = zzu.zzfz().zzcw((String) map.get("height"));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get("offsetX"))) {
            this.zzbva = zzu.zzfz().zzcw((String) map.get("offsetX"));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get("offsetY"))) {
            this.zzbvb = zzu.zzfz().zzcw((String) map.get("offsetY"));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get("allowOffscreen"))) {
            this.zzbux = Boolean.parseBoolean((String) map.get("allowOffscreen"));
        }
        String str = (String) map.get("customClosePosition");
        if (!TextUtils.isEmpty(str)) {
            this.zzbuw = str;
        }
    }

    private int[] zznt() {
        if (!zznv()) {
            return null;
        }
        if (this.zzbux) {
            return new int[]{this.zzbuy + this.zzbva, this.zzbuz + this.zzbvb};
        }
        int[] zzi = zzu.zzfz().zzi(this.zzbul);
        int[] zzk = zzu.zzfz().zzk(this.zzbul);
        int i = zzi[0];
        int i2 = this.zzbuy + this.zzbva;
        int i3 = this.zzbuz + this.zzbvb;
        if (i2 < 0) {
            i2 = 0;
        } else if (this.zzajw + i2 > i) {
            i2 = i - this.zzajw;
        }
        if (i3 < zzk[0]) {
            i3 = zzk[0];
        } else if (this.zzajx + i3 > zzk[1]) {
            i3 = zzk[1] - this.zzajx;
        }
        return new int[]{i2, i3};
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(java.util.Map<java.lang.String, java.lang.String> r13) {
        /*
        r12 = this;
        r4 = -1;
        r5 = 1;
        r3 = 0;
        r6 = r12.zzakd;
        monitor-enter(r6);
        r1 = r12.zzbul;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0011;
    L_0x000a:
        r1 = "Not an activity context. Cannot resize.";
        r12.zzbx(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
    L_0x0010:
        return;
    L_0x0011:
        r1 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r1 = r1.zzdt();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0023;
    L_0x0019:
        r1 = "Webview is not yet available, size is not set.";
        r12.zzbx(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0020:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        throw r1;
    L_0x0023:
        r1 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r1 = r1.zzdt();	 Catch:{ all -> 0x0020 }
        r1 = r1.zzaxj;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0034;
    L_0x002d:
        r1 = "Is interstitial. Cannot resize an interstitial.";
        r12.zzbx(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0034:
        r1 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r1 = r1.zzvv();	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0043;
    L_0x003c:
        r1 = "Cannot resize an expanded banner.";
        r12.zzbx(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0043:
        r12.zzi(r13);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzns();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0053;
    L_0x004c:
        r1 = "Invalid width and height options. Cannot resize.";
        r12.zzbx(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0053:
        r1 = r12.zzbul;	 Catch:{ all -> 0x0020 }
        r7 = r1.getWindow();	 Catch:{ all -> 0x0020 }
        if (r7 == 0) goto L_0x0061;
    L_0x005b:
        r1 = r7.getDecorView();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0068;
    L_0x0061:
        r1 = "Activity context is not ready, cannot get window or decor view.";
        r12.zzbx(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0068:
        r8 = r12.zznt();	 Catch:{ all -> 0x0020 }
        if (r8 != 0) goto L_0x0075;
    L_0x006e:
        r1 = "Resize location out of screen or close button is not visible.";
        r12.zzbx(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0075:
        r1 = com.google.android.gms.ads.internal.client.zzm.zzjr();	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbul;	 Catch:{ all -> 0x0020 }
        r9 = r12.zzajw;	 Catch:{ all -> 0x0020 }
        r9 = r1.zzb(r2, r9);	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.client.zzm.zzjr();	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbul;	 Catch:{ all -> 0x0020 }
        r10 = r12.zzajx;	 Catch:{ all -> 0x0020 }
        r10 = r1.zzb(r2, r10);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r1 = r1.getView();	 Catch:{ all -> 0x0020 }
        r2 = r1.getParent();	 Catch:{ all -> 0x0020 }
        if (r2 == 0) goto L_0x01d5;
    L_0x0099:
        r1 = r2 instanceof android.view.ViewGroup;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x01d5;
    L_0x009d:
        r0 = r2;
        r0 = (android.view.ViewGroup) r0;	 Catch:{ all -> 0x0020 }
        r1 = r0;
        r11 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r11 = r11.getView();	 Catch:{ all -> 0x0020 }
        r1.removeView(r11);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvf;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x01ce;
    L_0x00ae:
        r2 = (android.view.ViewGroup) r2;	 Catch:{ all -> 0x0020 }
        r12.zzbvh = r2;	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.zzu.zzfz();	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r2 = r2.getView();	 Catch:{ all -> 0x0020 }
        r1 = r1.zzk(r2);	 Catch:{ all -> 0x0020 }
        r2 = new android.widget.ImageView;	 Catch:{ all -> 0x0020 }
        r11 = r12.zzbul;	 Catch:{ all -> 0x0020 }
        r2.<init>(r11);	 Catch:{ all -> 0x0020 }
        r12.zzbvc = r2;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbvc;	 Catch:{ all -> 0x0020 }
        r2.setImageBitmap(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r1 = r1.zzdt();	 Catch:{ all -> 0x0020 }
        r12.zzapc = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvh;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbvc;	 Catch:{ all -> 0x0020 }
        r1.addView(r2);	 Catch:{ all -> 0x0020 }
    L_0x00dd:
        r1 = new android.widget.RelativeLayout;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbul;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        r12.zzbvg = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvg;	 Catch:{ all -> 0x0020 }
        r2 = 0;
        r1.setBackgroundColor(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvg;	 Catch:{ all -> 0x0020 }
        r2 = new android.view.ViewGroup$LayoutParams;	 Catch:{ all -> 0x0020 }
        r2.<init>(r9, r10);	 Catch:{ all -> 0x0020 }
        r1.setLayoutParams(r2);	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.zzu.zzfz();	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbvg;	 Catch:{ all -> 0x0020 }
        r11 = 0;
        r1 = r1.zza(r2, r9, r10, r11);	 Catch:{ all -> 0x0020 }
        r12.zzbvf = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvf;	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r1.setOutsideTouchable(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvf;	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r1.setTouchable(r2);	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbvf;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbux;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x01dd;
    L_0x0115:
        r1 = r5;
    L_0x0116:
        r2.setClippingEnabled(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvg;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r2 = r2.getView();	 Catch:{ all -> 0x0020 }
        r9 = -1;
        r10 = -1;
        r1.addView(r2, r9, r10);	 Catch:{ all -> 0x0020 }
        r1 = new android.widget.LinearLayout;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbul;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        r12.zzbvd = r1;	 Catch:{ all -> 0x0020 }
        r2 = new android.widget.RelativeLayout$LayoutParams;	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.client.zzm.zzjr();	 Catch:{ all -> 0x0020 }
        r9 = r12.zzbul;	 Catch:{ all -> 0x0020 }
        r10 = 50;
        r1 = r1.zzb(r9, r10);	 Catch:{ all -> 0x0020 }
        r9 = com.google.android.gms.ads.internal.client.zzm.zzjr();	 Catch:{ all -> 0x0020 }
        r10 = r12.zzbul;	 Catch:{ all -> 0x0020 }
        r11 = 50;
        r9 = r9.zzb(r10, r11);	 Catch:{ all -> 0x0020 }
        r2.<init>(r1, r9);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbuw;	 Catch:{ all -> 0x0020 }
        r9 = r1.hashCode();	 Catch:{ all -> 0x0020 }
        switch(r9) {
            case -1364013995: goto L_0x01f6;
            case -1012429441: goto L_0x01e0;
            case -655373719: goto L_0x0201;
            case 1163912186: goto L_0x0217;
            case 1288627767: goto L_0x020c;
            case 1755462605: goto L_0x01eb;
            default: goto L_0x0155;
        };	 Catch:{ all -> 0x0020 }
    L_0x0155:
        r1 = r4;
    L_0x0156:
        switch(r1) {
            case 0: goto L_0x0222;
            case 1: goto L_0x022e;
            case 2: goto L_0x023a;
            case 3: goto L_0x0241;
            case 4: goto L_0x024d;
            case 5: goto L_0x0259;
            default: goto L_0x0159;
        };	 Catch:{ all -> 0x0020 }
    L_0x0159:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 11;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
    L_0x0163:
        r1 = r12.zzbvd;	 Catch:{ all -> 0x0020 }
        r3 = new com.google.android.gms.internal.zzhh$1;	 Catch:{ all -> 0x0020 }
        r3.<init>(r12);	 Catch:{ all -> 0x0020 }
        r1.setOnClickListener(r3);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvd;	 Catch:{ all -> 0x0020 }
        r3 = "Close button";
        r1.setContentDescription(r3);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvg;	 Catch:{ all -> 0x0020 }
        r3 = r12.zzbvd;	 Catch:{ all -> 0x0020 }
        r1.addView(r3, r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvf;	 Catch:{ RuntimeException -> 0x0265 }
        r2 = r7.getDecorView();	 Catch:{ RuntimeException -> 0x0265 }
        r3 = 0;
        r4 = com.google.android.gms.ads.internal.client.zzm.zzjr();	 Catch:{ RuntimeException -> 0x0265 }
        r5 = r12.zzbul;	 Catch:{ RuntimeException -> 0x0265 }
        r7 = 0;
        r7 = r8[r7];	 Catch:{ RuntimeException -> 0x0265 }
        r4 = r4.zzb(r5, r7);	 Catch:{ RuntimeException -> 0x0265 }
        r5 = com.google.android.gms.ads.internal.client.zzm.zzjr();	 Catch:{ RuntimeException -> 0x0265 }
        r7 = r12.zzbul;	 Catch:{ RuntimeException -> 0x0265 }
        r9 = 1;
        r9 = r8[r9];	 Catch:{ RuntimeException -> 0x0265 }
        r5 = r5.zzb(r7, r9);	 Catch:{ RuntimeException -> 0x0265 }
        r1.showAtLocation(r2, r3, r4, r5);	 Catch:{ RuntimeException -> 0x0265 }
        r1 = 0;
        r1 = r8[r1];	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r2 = r8[r2];	 Catch:{ all -> 0x0020 }
        r12.zzb(r1, r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r2 = new com.google.android.gms.ads.internal.client.AdSizeParcel;	 Catch:{ all -> 0x0020 }
        r3 = r12.zzbul;	 Catch:{ all -> 0x0020 }
        r4 = new com.google.android.gms.ads.AdSize;	 Catch:{ all -> 0x0020 }
        r5 = r12.zzajw;	 Catch:{ all -> 0x0020 }
        r7 = r12.zzajx;	 Catch:{ all -> 0x0020 }
        r4.<init>(r5, r7);	 Catch:{ all -> 0x0020 }
        r2.<init>(r3, r4);	 Catch:{ all -> 0x0020 }
        r1.zza(r2);	 Catch:{ all -> 0x0020 }
        r1 = 0;
        r1 = r8[r1];	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r2 = r8[r2];	 Catch:{ all -> 0x0020 }
        r12.zzc(r1, r2);	 Catch:{ all -> 0x0020 }
        r1 = "resized";
        r12.zzbz(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x01ce:
        r1 = r12.zzbvf;	 Catch:{ all -> 0x0020 }
        r1.dismiss();	 Catch:{ all -> 0x0020 }
        goto L_0x00dd;
    L_0x01d5:
        r1 = "Webview is detached, probably in the middle of a resize or expand.";
        r12.zzbx(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x01dd:
        r1 = r3;
        goto L_0x0116;
    L_0x01e0:
        r5 = "top-left";
        r1 = r1.equals(r5);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x01e8:
        r1 = r3;
        goto L_0x0156;
    L_0x01eb:
        r3 = "top-center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x01f3:
        r1 = r5;
        goto L_0x0156;
    L_0x01f6:
        r3 = "center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x01fe:
        r1 = 2;
        goto L_0x0156;
    L_0x0201:
        r3 = "bottom-left";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x0209:
        r1 = 3;
        goto L_0x0156;
    L_0x020c:
        r3 = "bottom-center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x0214:
        r1 = 4;
        goto L_0x0156;
    L_0x0217:
        r3 = "bottom-right";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x021f:
        r1 = 5;
        goto L_0x0156;
    L_0x0222:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 9;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x022e:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 14;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x023a:
        r1 = 13;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x0241:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 9;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x024d:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 14;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x0259:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 11;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x0265:
        r1 = move-exception;
        r2 = "Cannot show popup window: ";
        r1 = r1.getMessage();	 Catch:{ all -> 0x0020 }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x0020 }
        r3 = r1.length();	 Catch:{ all -> 0x0020 }
        if (r3 == 0) goto L_0x02a8;
    L_0x0276:
        r1 = r2.concat(r1);	 Catch:{ all -> 0x0020 }
    L_0x027a:
        r12.zzbx(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvg;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r2 = r2.getView();	 Catch:{ all -> 0x0020 }
        r1.removeView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvh;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x02a5;
    L_0x028c:
        r1 = r12.zzbvh;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbvc;	 Catch:{ all -> 0x0020 }
        r1.removeView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbvh;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r2 = r2.getView();	 Catch:{ all -> 0x0020 }
        r1.addView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbkr;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzapc;	 Catch:{ all -> 0x0020 }
        r1.zza(r2);	 Catch:{ all -> 0x0020 }
    L_0x02a5:
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x02a8:
        r1 = new java.lang.String;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        goto L_0x027a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzhh.execute(java.util.Map):void");
    }

    public void zza(int i, int i2, boolean z) {
        synchronized (this.zzakd) {
            this.zzbuy = i;
            this.zzbuz = i2;
            if (this.zzbvf != null && z) {
                int[] zznt = zznt();
                if (zznt != null) {
                    this.zzbvf.update(zzm.zzjr().zzb(this.zzbul, zznt[0]), zzm.zzjr().zzb(this.zzbul, zznt[1]), this.zzbvf.getWidth(), this.zzbvf.getHeight());
                    zzc(zznt[0], zznt[1]);
                } else {
                    zzt(true);
                }
            }
        }
    }

    void zzb(int i, int i2) {
        if (this.zzbve != null) {
            this.zzbve.zza(i, i2, this.zzajw, this.zzajx);
        }
    }

    void zzc(int i, int i2) {
        zzb(i, i2 - zzu.zzfz().zzk(this.zzbul)[0], this.zzajw, this.zzajx);
    }

    public void zzd(int i, int i2) {
        this.zzbuy = i;
        this.zzbuz = i2;
    }

    boolean zzns() {
        return this.zzajw > -1 && this.zzajx > -1;
    }

    public boolean zznu() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzbvf != null;
        }
        return z;
    }

    boolean zznv() {
        int[] zzi = zzu.zzfz().zzi(this.zzbul);
        int[] zzk = zzu.zzfz().zzk(this.zzbul);
        int i = zzi[0];
        int i2 = zzi[1];
        if (this.zzajw < 50 || this.zzajw > i) {
            zzb.zzdf("Width is too small or too large.");
            return false;
        } else if (this.zzajx < 50 || this.zzajx > i2) {
            zzb.zzdf("Height is too small or too large.");
            return false;
        } else if (this.zzajx == i2 && this.zzajw == i) {
            zzb.zzdf("Cannot resize to a full-screen ad.");
            return false;
        } else {
            if (this.zzbux) {
                int i3;
                String str = this.zzbuw;
                boolean z = true;
                switch (str.hashCode()) {
                    case -1364013995:
                        if (str.equals("center")) {
                            z = true;
                            break;
                        }
                        break;
                    case -1012429441:
                        if (str.equals("top-left")) {
                            z = false;
                            break;
                        }
                        break;
                    case -655373719:
                        if (str.equals("bottom-left")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1163912186:
                        if (str.equals("bottom-right")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1288627767:
                        if (str.equals("bottom-center")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1755462605:
                        if (str.equals("top-center")) {
                            z = true;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        i3 = this.zzbva + this.zzbuy;
                        i2 = this.zzbuz + this.zzbvb;
                        break;
                    case true:
                        i3 = ((this.zzbuy + this.zzbva) + (this.zzajw / 2)) - 25;
                        i2 = this.zzbuz + this.zzbvb;
                        break;
                    case true:
                        i3 = ((this.zzbuy + this.zzbva) + (this.zzajw / 2)) - 25;
                        i2 = ((this.zzbuz + this.zzbvb) + (this.zzajx / 2)) - 25;
                        break;
                    case true:
                        i3 = this.zzbva + this.zzbuy;
                        i2 = ((this.zzbuz + this.zzbvb) + this.zzajx) - 50;
                        break;
                    case true:
                        i3 = ((this.zzbuy + this.zzbva) + (this.zzajw / 2)) - 25;
                        i2 = ((this.zzbuz + this.zzbvb) + this.zzajx) - 50;
                        break;
                    case true:
                        i3 = ((this.zzbuy + this.zzbva) + this.zzajw) - 50;
                        i2 = ((this.zzbuz + this.zzbvb) + this.zzajx) - 50;
                        break;
                    default:
                        i3 = ((this.zzbuy + this.zzbva) + this.zzajw) - 50;
                        i2 = this.zzbuz + this.zzbvb;
                        break;
                }
                if (i3 < 0 || i3 + 50 > i || r2 < zzk[0] || r2 + 50 > zzk[1]) {
                    return false;
                }
            }
            return true;
        }
    }

    public void zzt(boolean z) {
        synchronized (this.zzakd) {
            if (this.zzbvf != null) {
                this.zzbvf.dismiss();
                this.zzbvg.removeView(this.zzbkr.getView());
                if (this.zzbvh != null) {
                    this.zzbvh.removeView(this.zzbvc);
                    this.zzbvh.addView(this.zzbkr.getView());
                    this.zzbkr.zza(this.zzapc);
                }
                if (z) {
                    zzbz("default");
                    if (this.zzbve != null) {
                        this.zzbve.zzep();
                    }
                }
                this.zzbvf = null;
                this.zzbvg = null;
                this.zzbvh = null;
                this.zzbvd = null;
            }
        }
    }
}
