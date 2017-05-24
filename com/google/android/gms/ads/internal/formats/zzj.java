package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.zzi.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzau;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdx;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzlu;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

@zziy
public class zzj implements zzi {
    private final Context mContext;
    private final Object zzakd = new Object();
    @Nullable
    private final VersionInfoParcel zzanh;
    private final zzq zzbkj;
    @Nullable
    private final JSONObject zzbkm;
    @Nullable
    private final zzir zzbkn;
    @Nullable
    private final zza zzbko;
    private final zzau zzbkp;
    private boolean zzbkq;
    private zzlt zzbkr;
    private String zzbks;
    @Nullable
    private String zzbkt;
    private WeakReference<View> zzbku = null;

    public zzj(Context context, zzq com_google_android_gms_ads_internal_zzq, @Nullable zzir com_google_android_gms_internal_zzir, zzau com_google_android_gms_internal_zzau, @Nullable JSONObject jSONObject, @Nullable zza com_google_android_gms_ads_internal_formats_zzi_zza, @Nullable VersionInfoParcel versionInfoParcel, @Nullable String str) {
        this.mContext = context;
        this.zzbkj = com_google_android_gms_ads_internal_zzq;
        this.zzbkn = com_google_android_gms_internal_zzir;
        this.zzbkp = com_google_android_gms_internal_zzau;
        this.zzbkm = jSONObject;
        this.zzbko = com_google_android_gms_ads_internal_formats_zzi_zza;
        this.zzanh = versionInfoParcel;
        this.zzbkt = str;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void recordImpression() {
        zzac.zzhq("recordImpression must be called on the main UI thread.");
        zzr(true);
        try {
            final JSONObject jSONObject = new JSONObject();
            jSONObject.put("ad", this.zzbkm);
            jSONObject.put("ads_id", this.zzbkt);
            this.zzbkn.zza(new zzir.zza(this) {
                final /* synthetic */ zzj zzbkw;

                public void zze(zzfz com_google_android_gms_internal_zzfz) {
                    com_google_android_gms_internal_zzfz.zza("google.afma.nativeAds.handleImpressionPing", jSONObject);
                }
            });
        } catch (Throwable e) {
            zzb.zzb("Unable to create impression JSON.", e);
        }
        this.zzbkj.zza((zzi) this);
    }

    public zzb zza(OnClickListener onClickListener) {
        zza zzlr = this.zzbko.zzlr();
        if (zzlr == null) {
            return null;
        }
        zzb com_google_android_gms_ads_internal_formats_zzb = new zzb(this.mContext, zzlr);
        com_google_android_gms_ads_internal_formats_zzb.setLayoutParams(new LayoutParams(-1, -1));
        com_google_android_gms_ads_internal_formats_zzb.zzlm().setOnClickListener(onClickListener);
        com_google_android_gms_ads_internal_formats_zzb.zzlm().setContentDescription((CharSequence) zzdi.zzbgd.get());
        return com_google_android_gms_ads_internal_formats_zzb;
    }

    public void zza(View view, zzg com_google_android_gms_ads_internal_formats_zzg) {
        if (this.zzbko instanceof zzd) {
            zzd com_google_android_gms_ads_internal_formats_zzd = (zzd) this.zzbko;
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
            if (com_google_android_gms_ads_internal_formats_zzd.zzls() != null) {
                ((FrameLayout) view).addView(com_google_android_gms_ads_internal_formats_zzd.zzls(), layoutParams);
                this.zzbkj.zza(com_google_android_gms_ads_internal_formats_zzg);
            } else if (com_google_android_gms_ads_internal_formats_zzd.getImages() != null && com_google_android_gms_ads_internal_formats_zzd.getImages().size() > 0) {
                zzdx zze = zze(com_google_android_gms_ads_internal_formats_zzd.getImages().get(0));
                if (zze != null) {
                    try {
                        zzd zzln = zze.zzln();
                        if (zzln != null) {
                            Drawable drawable = (Drawable) zze.zzae(zzln);
                            View zzmb = zzmb();
                            zzmb.setImageDrawable(drawable);
                            zzmb.setScaleType(ScaleType.CENTER_INSIDE);
                            ((FrameLayout) view).addView(zzmb, layoutParams);
                        }
                    } catch (RemoteException e) {
                        zzb.zzdf("Could not get drawable from image");
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(android.view.View r7, java.lang.String r8, @android.support.annotation.Nullable org.json.JSONObject r9, @android.support.annotation.Nullable org.json.JSONObject r10, @android.support.annotation.Nullable org.json.JSONObject r11) {
        /*
        r6 = this;
        r0 = "performClick must be called on the main UI thread.";
        com.google.android.gms.common.internal.zzac.zzhq(r0);
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0095 }
        r1.<init>();	 Catch:{ JSONException -> 0x0095 }
        r0 = "asset";
        r1.put(r0, r8);	 Catch:{ JSONException -> 0x0095 }
        r0 = "template";
        r2 = r6.zzbko;	 Catch:{ JSONException -> 0x0095 }
        r2 = r2.zzlq();	 Catch:{ JSONException -> 0x0095 }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x0095 }
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0095 }
        r2.<init>();	 Catch:{ JSONException -> 0x0095 }
        r0 = "ad";
        r3 = r6.zzbkm;	 Catch:{ JSONException -> 0x0095 }
        r2.put(r0, r3);	 Catch:{ JSONException -> 0x0095 }
        r0 = "click";
        r2.put(r0, r1);	 Catch:{ JSONException -> 0x0095 }
        r3 = "has_custom_click_handler";
        r0 = r6.zzbkj;	 Catch:{ JSONException -> 0x0095 }
        r4 = r6.zzbko;	 Catch:{ JSONException -> 0x0095 }
        r4 = r4.getCustomTemplateId();	 Catch:{ JSONException -> 0x0095 }
        r0 = r0.zzx(r4);	 Catch:{ JSONException -> 0x0095 }
        if (r0 == 0) goto L_0x008c;
    L_0x003b:
        r0 = 1;
    L_0x003c:
        r2.put(r3, r0);	 Catch:{ JSONException -> 0x0095 }
        if (r9 == 0) goto L_0x0046;
    L_0x0041:
        r0 = "view_rectangles";
        r2.put(r0, r9);	 Catch:{ JSONException -> 0x0095 }
    L_0x0046:
        if (r10 == 0) goto L_0x004d;
    L_0x0048:
        r0 = "click_point";
        r2.put(r0, r10);	 Catch:{ JSONException -> 0x0095 }
    L_0x004d:
        if (r11 == 0) goto L_0x0054;
    L_0x004f:
        r0 = "native_view_rectangle";
        r2.put(r0, r11);	 Catch:{ JSONException -> 0x0095 }
    L_0x0054:
        r0 = r6.zzbkm;	 Catch:{ Exception -> 0x008e }
        r3 = "tracking_urls_and_actions";
        r0 = r0.optJSONObject(r3);	 Catch:{ Exception -> 0x008e }
        if (r0 != 0) goto L_0x0063;
    L_0x005e:
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x008e }
        r0.<init>();	 Catch:{ Exception -> 0x008e }
    L_0x0063:
        r3 = "click_string";
        r0 = r0.optString(r3);	 Catch:{ Exception -> 0x008e }
        r3 = "click_signals";
        r4 = r6.zzbkp;	 Catch:{ Exception -> 0x008e }
        r4 = r4.zzaw();	 Catch:{ Exception -> 0x008e }
        r5 = r6.mContext;	 Catch:{ Exception -> 0x008e }
        r0 = r4.zza(r5, r0, r7);	 Catch:{ Exception -> 0x008e }
        r1.put(r3, r0);	 Catch:{ Exception -> 0x008e }
    L_0x007a:
        r0 = "ads_id";
        r1 = r6.zzbkt;	 Catch:{ JSONException -> 0x0095 }
        r2.put(r0, r1);	 Catch:{ JSONException -> 0x0095 }
        r0 = r6.zzbkn;	 Catch:{ JSONException -> 0x0095 }
        r1 = new com.google.android.gms.ads.internal.formats.zzj$1;	 Catch:{ JSONException -> 0x0095 }
        r1.<init>(r6, r2);	 Catch:{ JSONException -> 0x0095 }
        r0.zza(r1);	 Catch:{ JSONException -> 0x0095 }
    L_0x008b:
        return;
    L_0x008c:
        r0 = 0;
        goto L_0x003c;
    L_0x008e:
        r0 = move-exception;
        r1 = "Exception obtaining click signals";
        com.google.android.gms.ads.internal.util.client.zzb.zzb(r1, r0);	 Catch:{ JSONException -> 0x0095 }
        goto L_0x007a;
    L_0x0095:
        r0 = move-exception;
        r1 = "Unable to create click JSON.";
        com.google.android.gms.ads.internal.util.client.zzb.zzb(r1, r0);
        goto L_0x008b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.formats.zzj.zza(android.view.View, java.lang.String, org.json.JSONObject, org.json.JSONObject, org.json.JSONObject):void");
    }

    public void zza(View view, Map<String, WeakReference<View>> map, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        if (((Boolean) zzdi.zzbga.get()).booleanValue()) {
            view.setOnTouchListener(onTouchListener);
            view.setClickable(true);
            view.setOnClickListener(onClickListener);
            for (Entry value : map.entrySet()) {
                View view2 = (View) ((WeakReference) value.getValue()).get();
                if (view2 != null) {
                    view2.setOnTouchListener(onTouchListener);
                    view2.setClickable(true);
                    view2.setOnClickListener(onClickListener);
                }
            }
        }
    }

    public void zza(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        zzac.zzhq("performClick must be called on the main UI thread.");
        for (Entry entry : map.entrySet()) {
            if (view.equals((View) ((WeakReference) entry.getValue()).get())) {
                zza(view, (String) entry.getKey(), jSONObject, jSONObject2, jSONObject3);
                return;
            }
        }
        if ("2".equals(this.zzbko.zzlq())) {
            zza(view, "2099", jSONObject, jSONObject2, jSONObject3);
        } else if ("1".equals(this.zzbko.zzlq())) {
            zza(view, "1099", jSONObject, jSONObject2, jSONObject3);
        }
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
        view.setOnTouchListener(null);
        view.setClickable(false);
        view.setOnClickListener(null);
        for (Entry value : map.entrySet()) {
            View view2 = (View) ((WeakReference) value.getValue()).get();
            if (view2 != null) {
                view2.setOnTouchListener(null);
                view2.setClickable(false);
                view2.setOnClickListener(null);
            }
        }
    }

    public void zzd(MotionEvent motionEvent) {
        this.zzbkp.zza(motionEvent);
    }

    @Nullable
    zzdx zze(Object obj) {
        return obj instanceof IBinder ? zzdx.zza.zzab((IBinder) obj) : null;
    }

    public void zzg(View view) {
        synchronized (this.zzakd) {
            if (this.zzbkq) {
            } else if (!view.isShown()) {
            } else if (view.getGlobalVisibleRect(new Rect(), null)) {
                recordImpression();
            }
        }
    }

    public void zzh(View view) {
        this.zzbku = new WeakReference(view);
    }

    public zzlt zzlx() {
        this.zzbkr = zzma();
        this.zzbkr.getView().setVisibility(8);
        this.zzbkn.zza(new zzir.zza(this) {
            final /* synthetic */ zzj zzbkw;

            {
                this.zzbkw = r1;
            }

            public void zze(final zzfz com_google_android_gms_internal_zzfz) {
                com_google_android_gms_internal_zzfz.zza("/loadHtml", new zzev(this) {
                    final /* synthetic */ AnonymousClass3 zzbky;

                    public void zza(zzlt com_google_android_gms_internal_zzlt, final Map<String, String> map) {
                        this.zzbky.zzbkw.zzbkr.zzvr().zza(new zzlu.zza(this) {
                            final /* synthetic */ AnonymousClass1 zzbla;

                            public void zza(zzlt com_google_android_gms_internal_zzlt, boolean z) {
                                this.zzbla.zzbky.zzbkw.zzbks = (String) map.get("id");
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put("messageType", "htmlLoaded");
                                    jSONObject.put("id", this.zzbla.zzbky.zzbkw.zzbks);
                                    com_google_android_gms_internal_zzfz.zzb("sendMessageToNativeJs", jSONObject);
                                } catch (Throwable e) {
                                    zzb.zzb("Unable to dispatch sendMessageToNativeJs event", e);
                                }
                            }
                        });
                        String str = (String) map.get("overlayHtml");
                        String str2 = (String) map.get("baseUrl");
                        if (TextUtils.isEmpty(str2)) {
                            this.zzbky.zzbkw.zzbkr.loadData(str, "text/html", Key.STRING_CHARSET_NAME);
                        } else {
                            this.zzbky.zzbkw.zzbkr.loadDataWithBaseURL(str2, str, "text/html", Key.STRING_CHARSET_NAME, null);
                        }
                    }
                });
                com_google_android_gms_internal_zzfz.zza("/showOverlay", new zzev(this) {
                    final /* synthetic */ AnonymousClass3 zzbky;

                    {
                        this.zzbky = r1;
                    }

                    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
                        this.zzbky.zzbkw.zzbkr.getView().setVisibility(0);
                    }
                });
                com_google_android_gms_internal_zzfz.zza("/hideOverlay", new zzev(this) {
                    final /* synthetic */ AnonymousClass3 zzbky;

                    {
                        this.zzbky = r1;
                    }

                    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
                        this.zzbky.zzbkw.zzbkr.getView().setVisibility(8);
                    }
                });
                this.zzbkw.zzbkr.zzvr().zza("/hideOverlay", new zzev(this) {
                    final /* synthetic */ AnonymousClass3 zzbky;

                    {
                        this.zzbky = r1;
                    }

                    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
                        this.zzbky.zzbkw.zzbkr.getView().setVisibility(8);
                    }
                });
                this.zzbkw.zzbkr.zzvr().zza("/sendMessageToSdk", new zzev(this) {
                    final /* synthetic */ AnonymousClass3 zzbky;

                    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            for (String str : map.keySet()) {
                                jSONObject.put(str, map.get(str));
                            }
                            jSONObject.put("id", this.zzbky.zzbkw.zzbks);
                            com_google_android_gms_internal_zzfz.zzb("sendMessageToNativeJs", jSONObject);
                        } catch (Throwable e) {
                            zzb.zzb("Unable to dispatch sendMessageToNativeJs event", e);
                        }
                    }
                });
            }
        });
        return this.zzbkr;
    }

    public View zzly() {
        return this.zzbku != null ? (View) this.zzbku.get() : null;
    }

    public void zzlz() {
        if (this.zzbko instanceof zzd) {
            this.zzbkj.zzfj();
        }
    }

    zzlt zzma() {
        return zzu.zzga().zza(this.mContext, AdSizeParcel.zzk(this.mContext), false, false, this.zzbkp, this.zzanh);
    }

    ImageView zzmb() {
        return new ImageView(this.mContext);
    }

    protected void zzr(boolean z) {
        this.zzbkq = z;
    }
}
