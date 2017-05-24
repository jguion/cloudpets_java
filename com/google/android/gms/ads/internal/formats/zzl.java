package com.google.android.gms.ads.internal.formats;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdz.zza;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzlt;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@zziy
public class zzl extends zza implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener {
    private final Object zzakd = new Object();
    @Nullable
    private FrameLayout zzaks;
    @Nullable
    private zzi zzbkb;
    private final FrameLayout zzblg;
    private Map<String, WeakReference<View>> zzblh = new HashMap();
    @Nullable
    private zzb zzbli;
    boolean zzblj = false;
    int zzblk;
    int zzbll;

    public zzl(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzblg = frameLayout;
        this.zzaks = frameLayout2;
        zzu.zzgx().zza(this.zzblg, (OnGlobalLayoutListener) this);
        zzu.zzgx().zza(this.zzblg, (OnScrollChangedListener) this);
        this.zzblg.setOnTouchListener(this);
        this.zzblg.setOnClickListener(this);
    }

    private void zzd(zzj com_google_android_gms_ads_internal_formats_zzj) {
        if (this.zzblh.containsKey("2011")) {
            final View view = (View) ((WeakReference) this.zzblh.get("2011")).get();
            if (view instanceof FrameLayout) {
                com_google_android_gms_ads_internal_formats_zzj.zza(view, new zzg(this) {
                    final /* synthetic */ zzl zzbln;

                    public void zzc(MotionEvent motionEvent) {
                        this.zzbln.onTouch(null, motionEvent);
                    }

                    public void zzlu() {
                        this.zzbln.onClick(view);
                    }
                });
                return;
            } else {
                com_google_android_gms_ads_internal_formats_zzj.zzlz();
                return;
            }
        }
        com_google_android_gms_ads_internal_formats_zzj.zzlz();
    }

    public void destroy() {
        synchronized (this.zzakd) {
            if (this.zzaks != null) {
                this.zzaks.removeAllViews();
            }
            this.zzaks = null;
            this.zzblh = null;
            this.zzbli = null;
            this.zzbkb = null;
        }
    }

    int getMeasuredHeight() {
        return this.zzblg.getMeasuredHeight();
    }

    int getMeasuredWidth() {
        return this.zzblg.getMeasuredWidth();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r10) {
        /*
        r9 = this;
        r6 = r9.zzakd;
        monitor-enter(r6);
        r0 = r9.zzbkb;	 Catch:{ all -> 0x0090 }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r6);	 Catch:{ all -> 0x0090 }
    L_0x0008:
        return;
    L_0x0009:
        r3 = new org.json.JSONObject;	 Catch:{ all -> 0x0090 }
        r3.<init>();	 Catch:{ all -> 0x0090 }
        r0 = r9.zzblh;	 Catch:{ all -> 0x0090 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0090 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0090 }
    L_0x0018:
        r0 = r2.hasNext();	 Catch:{ all -> 0x0090 }
        if (r0 == 0) goto L_0x0099;
    L_0x001e:
        r0 = r2.next();	 Catch:{ all -> 0x0090 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0090 }
        r1 = r0.getValue();	 Catch:{ all -> 0x0090 }
        r1 = (java.lang.ref.WeakReference) r1;	 Catch:{ all -> 0x0090 }
        r1 = r1.get();	 Catch:{ all -> 0x0090 }
        r1 = (android.view.View) r1;	 Catch:{ all -> 0x0090 }
        if (r1 == 0) goto L_0x0018;
    L_0x0032:
        r4 = r9.zzi(r1);	 Catch:{ all -> 0x0090 }
        r5 = new org.json.JSONObject;	 Catch:{ all -> 0x0090 }
        r5.<init>();	 Catch:{ all -> 0x0090 }
        r7 = "width";
        r8 = r1.getWidth();	 Catch:{ JSONException -> 0x0075 }
        r8 = r9.zzz(r8);	 Catch:{ JSONException -> 0x0075 }
        r5.put(r7, r8);	 Catch:{ JSONException -> 0x0075 }
        r7 = "height";
        r1 = r1.getHeight();	 Catch:{ JSONException -> 0x0075 }
        r1 = r9.zzz(r1);	 Catch:{ JSONException -> 0x0075 }
        r5.put(r7, r1);	 Catch:{ JSONException -> 0x0075 }
        r1 = "x";
        r7 = r4.x;	 Catch:{ JSONException -> 0x0075 }
        r7 = r9.zzz(r7);	 Catch:{ JSONException -> 0x0075 }
        r5.put(r1, r7);	 Catch:{ JSONException -> 0x0075 }
        r1 = "y";
        r4 = r4.y;	 Catch:{ JSONException -> 0x0075 }
        r4 = r9.zzz(r4);	 Catch:{ JSONException -> 0x0075 }
        r5.put(r1, r4);	 Catch:{ JSONException -> 0x0075 }
        r1 = r0.getKey();	 Catch:{ JSONException -> 0x0075 }
        r1 = (java.lang.String) r1;	 Catch:{ JSONException -> 0x0075 }
        r3.put(r1, r5);	 Catch:{ JSONException -> 0x0075 }
        goto L_0x0018;
    L_0x0075:
        r1 = move-exception;
        r1 = "Unable to get view rectangle for view ";
        r0 = r0.getKey();	 Catch:{ all -> 0x0090 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0090 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x0090 }
        r4 = r0.length();	 Catch:{ all -> 0x0090 }
        if (r4 == 0) goto L_0x0093;
    L_0x0088:
        r0 = r1.concat(r0);	 Catch:{ all -> 0x0090 }
    L_0x008c:
        com.google.android.gms.ads.internal.util.client.zzb.zzdf(r0);	 Catch:{ all -> 0x0090 }
        goto L_0x0018;
    L_0x0090:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0090 }
        throw r0;
    L_0x0093:
        r0 = new java.lang.String;	 Catch:{ all -> 0x0090 }
        r0.<init>(r1);	 Catch:{ all -> 0x0090 }
        goto L_0x008c;
    L_0x0099:
        r4 = new org.json.JSONObject;	 Catch:{ all -> 0x0090 }
        r4.<init>();	 Catch:{ all -> 0x0090 }
        r0 = "x";
        r1 = r9.zzblk;	 Catch:{ JSONException -> 0x0104 }
        r1 = r9.zzz(r1);	 Catch:{ JSONException -> 0x0104 }
        r4.put(r0, r1);	 Catch:{ JSONException -> 0x0104 }
        r0 = "y";
        r1 = r9.zzbll;	 Catch:{ JSONException -> 0x0104 }
        r1 = r9.zzz(r1);	 Catch:{ JSONException -> 0x0104 }
        r4.put(r0, r1);	 Catch:{ JSONException -> 0x0104 }
    L_0x00b4:
        r5 = new org.json.JSONObject;	 Catch:{ all -> 0x0090 }
        r5.<init>();	 Catch:{ all -> 0x0090 }
        r0 = "width";
        r1 = r9.getMeasuredWidth();	 Catch:{ JSONException -> 0x010b }
        r1 = r9.zzz(r1);	 Catch:{ JSONException -> 0x010b }
        r5.put(r0, r1);	 Catch:{ JSONException -> 0x010b }
        r0 = "height";
        r1 = r9.getMeasuredHeight();	 Catch:{ JSONException -> 0x010b }
        r1 = r9.zzz(r1);	 Catch:{ JSONException -> 0x010b }
        r5.put(r0, r1);	 Catch:{ JSONException -> 0x010b }
    L_0x00d3:
        r0 = r9.zzbli;	 Catch:{ all -> 0x0090 }
        if (r0 == 0) goto L_0x011b;
    L_0x00d7:
        r0 = r9.zzbli;	 Catch:{ all -> 0x0090 }
        r0 = r0.zzlm();	 Catch:{ all -> 0x0090 }
        r0 = r0.equals(r10);	 Catch:{ all -> 0x0090 }
        if (r0 == 0) goto L_0x011b;
    L_0x00e3:
        r0 = r9.zzbkb;	 Catch:{ all -> 0x0090 }
        r0 = r0 instanceof com.google.android.gms.ads.internal.formats.zzh;	 Catch:{ all -> 0x0090 }
        if (r0 == 0) goto L_0x0112;
    L_0x00e9:
        r0 = r9.zzbkb;	 Catch:{ all -> 0x0090 }
        r0 = (com.google.android.gms.ads.internal.formats.zzh) r0;	 Catch:{ all -> 0x0090 }
        r0 = r0.zzlw();	 Catch:{ all -> 0x0090 }
        if (r0 == 0) goto L_0x0112;
    L_0x00f3:
        r0 = r9.zzbkb;	 Catch:{ all -> 0x0090 }
        r0 = (com.google.android.gms.ads.internal.formats.zzh) r0;	 Catch:{ all -> 0x0090 }
        r0 = r0.zzlw();	 Catch:{ all -> 0x0090 }
        r2 = "1007";
        r1 = r10;
        r0.zza(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0090 }
    L_0x0101:
        monitor-exit(r6);	 Catch:{ all -> 0x0090 }
        goto L_0x0008;
    L_0x0104:
        r0 = move-exception;
        r0 = "Unable to get click location";
        com.google.android.gms.ads.internal.util.client.zzb.zzdf(r0);	 Catch:{ all -> 0x0090 }
        goto L_0x00b4;
    L_0x010b:
        r0 = move-exception;
        r0 = "Unable to get native ad view bounding box";
        com.google.android.gms.ads.internal.util.client.zzb.zzdf(r0);	 Catch:{ all -> 0x0090 }
        goto L_0x00d3;
    L_0x0112:
        r0 = r9.zzbkb;	 Catch:{ all -> 0x0090 }
        r2 = "1007";
        r1 = r10;
        r0.zza(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0090 }
        goto L_0x0101;
    L_0x011b:
        r0 = r9.zzbkb;	 Catch:{ all -> 0x0090 }
        r2 = r9.zzblh;	 Catch:{ all -> 0x0090 }
        r1 = r10;
        r0.zza(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0090 }
        goto L_0x0101;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.formats.zzl.onClick(android.view.View):void");
    }

    public void onGlobalLayout() {
        synchronized (this.zzakd) {
            if (this.zzblj) {
                int measuredWidth = getMeasuredWidth();
                int measuredHeight = getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || this.zzaks == null)) {
                    this.zzaks.setLayoutParams(new LayoutParams(measuredWidth, measuredHeight));
                    this.zzblj = false;
                }
            }
            if (this.zzbkb != null) {
                this.zzbkb.zzg(this.zzblg);
            }
        }
    }

    public void onScrollChanged() {
        synchronized (this.zzakd) {
            if (this.zzbkb != null) {
                this.zzbkb.zzg(this.zzblg);
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.zzakd) {
            if (this.zzbkb == null) {
            } else {
                Point zze = zze(motionEvent);
                this.zzblk = zze.x;
                this.zzbll = zze.y;
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setLocation((float) zze.x, (float) zze.y);
                this.zzbkb.zzd(obtain);
                obtain.recycle();
            }
        }
        return false;
    }

    public zzd zzas(String str) {
        zzd zzac;
        synchronized (this.zzakd) {
            Object obj;
            WeakReference weakReference = (WeakReference) this.zzblh.get(str);
            if (weakReference == null) {
                obj = null;
            } else {
                View view = (View) weakReference.get();
            }
            zzac = zze.zzac(obj);
        }
        return zzac;
    }

    @Nullable
    zzb zzc(zzj com_google_android_gms_ads_internal_formats_zzj) {
        return com_google_android_gms_ads_internal_formats_zzj.zza((OnClickListener) this);
    }

    public void zzc(String str, zzd com_google_android_gms_dynamic_zzd) {
        View view = (View) zze.zzae(com_google_android_gms_dynamic_zzd);
        synchronized (this.zzakd) {
            if (view == null) {
                this.zzblh.remove(str);
            } else {
                this.zzblh.put(str, new WeakReference(view));
                view.setOnTouchListener(this);
                view.setClickable(true);
                view.setOnClickListener(this);
            }
        }
    }

    Point zze(MotionEvent motionEvent) {
        int[] iArr = new int[2];
        this.zzblg.getLocationOnScreen(iArr);
        return new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
    }

    public void zze(zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzakd) {
            zzh(null);
            Object zzae = zze.zzae(com_google_android_gms_dynamic_zzd);
            if (zzae instanceof zzj) {
                if (this.zzaks != null) {
                    this.zzaks.setLayoutParams(new LayoutParams(0, 0));
                    this.zzblg.requestLayout();
                }
                this.zzblj = true;
                final zzj com_google_android_gms_ads_internal_formats_zzj = (zzj) zzae;
                if (this.zzbkb != null && ((Boolean) zzdi.zzbfz.get()).booleanValue()) {
                    this.zzbkb.zzb(this.zzblg, this.zzblh);
                }
                if ((this.zzbkb instanceof zzh) && ((zzh) this.zzbkb).zzlv()) {
                    ((zzh) this.zzbkb).zzc(com_google_android_gms_ads_internal_formats_zzj);
                } else {
                    this.zzbkb = com_google_android_gms_ads_internal_formats_zzj;
                    if (com_google_android_gms_ads_internal_formats_zzj instanceof zzh) {
                        ((zzh) com_google_android_gms_ads_internal_formats_zzj).zzc(null);
                    }
                }
                if (((Boolean) zzdi.zzbfz.get()).booleanValue()) {
                    this.zzaks.setClickable(false);
                }
                this.zzaks.removeAllViews();
                this.zzbli = zzc(com_google_android_gms_ads_internal_formats_zzj);
                if (this.zzbli != null) {
                    this.zzblh.put("1007", new WeakReference(this.zzbli.zzlm()));
                    this.zzaks.addView(this.zzbli);
                }
                com_google_android_gms_ads_internal_formats_zzj.zza(this.zzblg, this.zzblh, this, this);
                zzkr.zzcrf.post(new Runnable(this) {
                    final /* synthetic */ zzl zzbln;

                    public void run() {
                        zzlt zzlx = com_google_android_gms_ads_internal_formats_zzj.zzlx();
                        if (!(zzlx == null || this.zzbln.zzaks == null)) {
                            this.zzbln.zzaks.addView(zzlx.getView());
                        }
                        if (!(com_google_android_gms_ads_internal_formats_zzj instanceof zzh)) {
                            this.zzbln.zzd(com_google_android_gms_ads_internal_formats_zzj);
                        }
                    }
                });
                zzh(this.zzblg);
                return;
            }
            zzb.zzdf("Not an instance of native engine. This is most likely a transient error");
        }
    }

    void zzh(@Nullable View view) {
        if (this.zzbkb != null) {
            zzi zzlw = this.zzbkb instanceof zzh ? ((zzh) this.zzbkb).zzlw() : this.zzbkb;
            if (zzlw != null) {
                zzlw.zzh(view);
            }
        }
    }

    Point zzi(View view) {
        if (this.zzbli == null || !this.zzbli.zzlm().equals(view)) {
            Point point = new Point();
            view.getGlobalVisibleRect(new Rect(), point);
            return point;
        }
        Point point2 = new Point();
        this.zzblg.getGlobalVisibleRect(new Rect(), point2);
        Point point3 = new Point();
        view.getGlobalVisibleRect(new Rect(), point3);
        return new Point(point3.x - point2.x, point3.y - point2.y);
    }

    int zzz(int i) {
        return zzm.zzjr().zzc(this.zzbkb.getContext(), i);
    }
}
