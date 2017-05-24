package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzdx;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzgl;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.internal.zzgv;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzlu.zza;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public class zzn {

    class AnonymousClass1 implements zza {
        final /* synthetic */ zzd zzaod;
        final /* synthetic */ String zzaoe;
        final /* synthetic */ zzlt zzaof;

        AnonymousClass1(zzd com_google_android_gms_ads_internal_formats_zzd, String str, zzlt com_google_android_gms_internal_zzlt) {
            this.zzaod = com_google_android_gms_ads_internal_formats_zzd;
            this.zzaoe = str;
            this.zzaof = com_google_android_gms_internal_zzlt;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, boolean z) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("headline", this.zzaod.getHeadline());
                jSONObject.put("body", this.zzaod.getBody());
                jSONObject.put("call_to_action", this.zzaod.getCallToAction());
                jSONObject.put(Param.PRICE, this.zzaod.getPrice());
                jSONObject.put("star_rating", String.valueOf(this.zzaod.getStarRating()));
                jSONObject.put("store", this.zzaod.getStore());
                jSONObject.put("icon", zzn.zza(this.zzaod.zzlo()));
                JSONArray jSONArray = new JSONArray();
                List<Object> images = this.zzaod.getImages();
                if (images != null) {
                    for (Object zzf : images) {
                        jSONArray.put(zzn.zza(zzn.zze(zzf)));
                    }
                }
                jSONObject.put("images", jSONArray);
                jSONObject.put("extras", zzn.zza(this.zzaod.getExtras(), this.zzaoe));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("assets", jSONObject);
                jSONObject2.put("template_id", "2");
                this.zzaof.zza("google.afma.nativeExpressAds.loadAssets", jSONObject2);
            } catch (Throwable e) {
                zzb.zzd("Exception occurred when loading assets", e);
            }
        }
    }

    class AnonymousClass2 implements zza {
        final /* synthetic */ String zzaoe;
        final /* synthetic */ zzlt zzaof;
        final /* synthetic */ zze zzaog;

        AnonymousClass2(zze com_google_android_gms_ads_internal_formats_zze, String str, zzlt com_google_android_gms_internal_zzlt) {
            this.zzaog = com_google_android_gms_ads_internal_formats_zze;
            this.zzaoe = str;
            this.zzaof = com_google_android_gms_internal_zzlt;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, boolean z) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("headline", this.zzaog.getHeadline());
                jSONObject.put("body", this.zzaog.getBody());
                jSONObject.put("call_to_action", this.zzaog.getCallToAction());
                jSONObject.put("advertiser", this.zzaog.getAdvertiser());
                jSONObject.put("logo", zzn.zza(this.zzaog.zzlt()));
                JSONArray jSONArray = new JSONArray();
                List<Object> images = this.zzaog.getImages();
                if (images != null) {
                    for (Object zzf : images) {
                        jSONArray.put(zzn.zza(zzn.zze(zzf)));
                    }
                }
                jSONObject.put("images", jSONArray);
                jSONObject.put("extras", zzn.zza(this.zzaog.getExtras(), this.zzaoe));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("assets", jSONObject);
                jSONObject2.put("template_id", "1");
                this.zzaof.zza("google.afma.nativeExpressAds.loadAssets", jSONObject2);
            } catch (Throwable e) {
                zzb.zzd("Exception occurred when loading assets", e);
            }
        }
    }

    class AnonymousClass3 implements zzev {
        final /* synthetic */ CountDownLatch zzalp;

        AnonymousClass3(CountDownLatch countDownLatch) {
            this.zzalp = countDownLatch;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            this.zzalp.countDown();
            com_google_android_gms_internal_zzlt.getView().setVisibility(0);
        }
    }

    class AnonymousClass4 implements zzev {
        final /* synthetic */ CountDownLatch zzalp;

        AnonymousClass4(CountDownLatch countDownLatch) {
            this.zzalp = countDownLatch;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            zzb.zzdf("Adapter returned an ad, but assets substitution failed");
            this.zzalp.countDown();
            com_google_android_gms_internal_zzlt.destroy();
        }
    }

    class AnonymousClass5 implements zzev {
        final /* synthetic */ zzgu zzaoh;
        final /* synthetic */ zzf.zza zzaoi;
        final /* synthetic */ zzgv zzaoj;

        AnonymousClass5(zzgu com_google_android_gms_internal_zzgu, zzf.zza com_google_android_gms_ads_internal_zzf_zza, zzgv com_google_android_gms_internal_zzgv) {
            this.zzaoh = com_google_android_gms_internal_zzgu;
            this.zzaoi = com_google_android_gms_ads_internal_zzf_zza;
            this.zzaoj = com_google_android_gms_internal_zzgv;
        }

        public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
            View view = com_google_android_gms_internal_zzlt.getView();
            if (view != null) {
                try {
                    if (this.zzaoh != null) {
                        if (this.zzaoh.getOverrideClickHandling()) {
                            zzn.zza(com_google_android_gms_internal_zzlt);
                            return;
                        }
                        this.zzaoh.zzk(com.google.android.gms.dynamic.zze.zzac(view));
                        this.zzaoi.onClick();
                    } else if (this.zzaoj == null) {
                    } else {
                        if (this.zzaoj.getOverrideClickHandling()) {
                            zzn.zza(com_google_android_gms_internal_zzlt);
                            return;
                        }
                        this.zzaoj.zzk(com.google.android.gms.dynamic.zze.zzac(view));
                        this.zzaoi.onClick();
                    }
                } catch (Throwable e) {
                    zzb.zzd("Unable to call handleClick on mapper", e);
                }
            }
        }
    }

    private static zzd zza(zzgu com_google_android_gms_internal_zzgu) throws RemoteException {
        return new zzd(com_google_android_gms_internal_zzgu.getHeadline(), com_google_android_gms_internal_zzgu.getImages(), com_google_android_gms_internal_zzgu.getBody(), com_google_android_gms_internal_zzgu.zzlo(), com_google_android_gms_internal_zzgu.getCallToAction(), com_google_android_gms_internal_zzgu.getStarRating(), com_google_android_gms_internal_zzgu.getStore(), com_google_android_gms_internal_zzgu.getPrice(), null, com_google_android_gms_internal_zzgu.getExtras(), null, null);
    }

    private static zze zza(zzgv com_google_android_gms_internal_zzgv) throws RemoteException {
        return new zze(com_google_android_gms_internal_zzgv.getHeadline(), com_google_android_gms_internal_zzgv.getImages(), com_google_android_gms_internal_zzgv.getBody(), com_google_android_gms_internal_zzgv.zzlt(), com_google_android_gms_internal_zzgv.getCallToAction(), com_google_android_gms_internal_zzgv.getAdvertiser(), null, com_google_android_gms_internal_zzgv.getExtras());
    }

    static zzev zza(@Nullable zzgu com_google_android_gms_internal_zzgu, @Nullable zzgv com_google_android_gms_internal_zzgv, zzf.zza com_google_android_gms_ads_internal_zzf_zza) {
        return new AnonymousClass5(com_google_android_gms_internal_zzgu, com_google_android_gms_ads_internal_zzf_zza, com_google_android_gms_internal_zzgv);
    }

    static zzev zza(CountDownLatch countDownLatch) {
        return new AnonymousClass3(countDownLatch);
    }

    private static String zza(@Nullable Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzb.zzdf("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        encodeToString = String.valueOf(encodeToString);
        return encodeToString.length() != 0 ? valueOf.concat(encodeToString) : new String(valueOf);
    }

    static String zza(@Nullable zzdx com_google_android_gms_internal_zzdx) {
        if (com_google_android_gms_internal_zzdx == null) {
            zzb.zzdf("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = com_google_android_gms_internal_zzdx.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException e) {
            zzb.zzdf("Unable to get image uri. Trying data uri next");
        }
        return zzb(com_google_android_gms_internal_zzdx);
    }

    private static JSONObject zza(@Nullable Bundle bundle, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if (bundle.containsKey(str2)) {
                if ("image".equals(jSONObject2.getString(str2))) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(str2, zza((Bitmap) obj));
                    } else {
                        zzb.zzdf("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(str2) instanceof Bitmap) {
                    zzb.zzdf("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(str2, String.valueOf(bundle.get(str2)));
                }
            }
        }
        return jSONObject;
    }

    public static void zza(@Nullable zzke com_google_android_gms_internal_zzke, zzf.zza com_google_android_gms_ads_internal_zzf_zza) {
        zzgv com_google_android_gms_internal_zzgv = null;
        if (com_google_android_gms_internal_zzke != null && zzh(com_google_android_gms_internal_zzke)) {
            zzlt com_google_android_gms_internal_zzlt = com_google_android_gms_internal_zzke.zzbyh;
            Object view = com_google_android_gms_internal_zzlt != null ? com_google_android_gms_internal_zzlt.getView() : null;
            if (view == null) {
                zzb.zzdf("AdWebView is null");
                return;
            }
            try {
                List list = com_google_android_gms_internal_zzke.zzbte != null ? com_google_android_gms_internal_zzke.zzbte.zzbrz : null;
                if (list == null || list.isEmpty()) {
                    zzb.zzdf("No template ids present in mediation response");
                    return;
                }
                zzgu zznm = com_google_android_gms_internal_zzke.zzbtf != null ? com_google_android_gms_internal_zzke.zzbtf.zznm() : null;
                if (com_google_android_gms_internal_zzke.zzbtf != null) {
                    com_google_android_gms_internal_zzgv = com_google_android_gms_internal_zzke.zzbtf.zznn();
                }
                if (list.contains("2") && zznm != null) {
                    zznm.zzl(com.google.android.gms.dynamic.zze.zzac(view));
                    if (!zznm.getOverrideImpressionRecording()) {
                        zznm.recordImpression();
                    }
                    com_google_android_gms_internal_zzlt.zzvr().zza("/nativeExpressViewClicked", zza(zznm, null, com_google_android_gms_ads_internal_zzf_zza));
                } else if (!list.contains("1") || com_google_android_gms_internal_zzgv == null) {
                    zzb.zzdf("No matching template id and mapper");
                } else {
                    com_google_android_gms_internal_zzgv.zzl(com.google.android.gms.dynamic.zze.zzac(view));
                    if (!com_google_android_gms_internal_zzgv.getOverrideImpressionRecording()) {
                        com_google_android_gms_internal_zzgv.recordImpression();
                    }
                    com_google_android_gms_internal_zzlt.zzvr().zza("/nativeExpressViewClicked", zza(null, com_google_android_gms_internal_zzgv, com_google_android_gms_ads_internal_zzf_zza));
                }
            } catch (Throwable e) {
                zzb.zzd("Error occurred while recording impression and registering for clicks", e);
            }
        }
    }

    private static void zza(zzlt com_google_android_gms_internal_zzlt) {
        OnClickListener zzwf = com_google_android_gms_internal_zzlt.zzwf();
        if (zzwf != null) {
            zzwf.onClick(com_google_android_gms_internal_zzlt.getView());
        }
    }

    private static void zza(zzlt com_google_android_gms_internal_zzlt, zzd com_google_android_gms_ads_internal_formats_zzd, String str) {
        com_google_android_gms_internal_zzlt.zzvr().zza(new AnonymousClass1(com_google_android_gms_ads_internal_formats_zzd, str, com_google_android_gms_internal_zzlt));
    }

    private static void zza(zzlt com_google_android_gms_internal_zzlt, zze com_google_android_gms_ads_internal_formats_zze, String str) {
        com_google_android_gms_internal_zzlt.zzvr().zza(new AnonymousClass2(com_google_android_gms_ads_internal_formats_zze, str, com_google_android_gms_internal_zzlt));
    }

    private static void zza(zzlt com_google_android_gms_internal_zzlt, CountDownLatch countDownLatch) {
        com_google_android_gms_internal_zzlt.zzvr().zza("/nativeExpressAssetsLoaded", zza(countDownLatch));
        com_google_android_gms_internal_zzlt.zzvr().zza("/nativeExpressAssetsLoadingFailed", zzb(countDownLatch));
    }

    public static boolean zza(zzlt com_google_android_gms_internal_zzlt, zzgl com_google_android_gms_internal_zzgl, CountDownLatch countDownLatch) {
        boolean z = false;
        try {
            z = zzb(com_google_android_gms_internal_zzlt, com_google_android_gms_internal_zzgl, countDownLatch);
        } catch (Throwable e) {
            zzb.zzd("Unable to invoke load assets", e);
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    static zzev zzb(CountDownLatch countDownLatch) {
        return new AnonymousClass4(countDownLatch);
    }

    private static String zzb(zzdx com_google_android_gms_internal_zzdx) {
        try {
            com.google.android.gms.dynamic.zzd zzln = com_google_android_gms_internal_zzdx.zzln();
            if (zzln == null) {
                zzb.zzdf("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) com.google.android.gms.dynamic.zze.zzae(zzln);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            zzb.zzdf("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return "";
        } catch (RemoteException e) {
            zzb.zzdf("Unable to get drawable. Returning empty string");
            return "";
        }
    }

    private static boolean zzb(zzlt com_google_android_gms_internal_zzlt, zzgl com_google_android_gms_internal_zzgl, CountDownLatch countDownLatch) throws RemoteException {
        View view = com_google_android_gms_internal_zzlt.getView();
        if (view == null) {
            zzb.zzdf("AdWebView is null");
            return false;
        }
        view.setVisibility(4);
        List list = com_google_android_gms_internal_zzgl.zzbte.zzbrz;
        if (list == null || list.isEmpty()) {
            zzb.zzdf("No template ids present in mediation response");
            return false;
        }
        zza(com_google_android_gms_internal_zzlt, countDownLatch);
        zzgu zznm = com_google_android_gms_internal_zzgl.zzbtf.zznm();
        zzgv zznn = com_google_android_gms_internal_zzgl.zzbtf.zznn();
        if (list.contains("2") && zznm != null) {
            zza(com_google_android_gms_internal_zzlt, zza(zznm), com_google_android_gms_internal_zzgl.zzbte.zzbry);
        } else if (!list.contains("1") || zznn == null) {
            zzb.zzdf("No matching template id and mapper");
            return false;
        } else {
            zza(com_google_android_gms_internal_zzlt, zza(zznn), com_google_android_gms_internal_zzgl.zzbte.zzbry);
        }
        String str = com_google_android_gms_internal_zzgl.zzbte.zzbrw;
        String str2 = com_google_android_gms_internal_zzgl.zzbte.zzbrx;
        if (str2 != null) {
            com_google_android_gms_internal_zzlt.loadDataWithBaseURL(str2, str, "text/html", Key.STRING_CHARSET_NAME, null);
        } else {
            com_google_android_gms_internal_zzlt.loadData(str, "text/html", Key.STRING_CHARSET_NAME);
        }
        return true;
    }

    @Nullable
    private static zzdx zze(Object obj) {
        return obj instanceof IBinder ? zzdx.zza.zzab((IBinder) obj) : null;
    }

    @Nullable
    public static View zzg(@Nullable zzke com_google_android_gms_internal_zzke) {
        if (com_google_android_gms_internal_zzke == null) {
            zzb.e("AdState is null");
            return null;
        } else if (zzh(com_google_android_gms_internal_zzke) && com_google_android_gms_internal_zzke.zzbyh != null) {
            return com_google_android_gms_internal_zzke.zzbyh.getView();
        } else {
            try {
                com.google.android.gms.dynamic.zzd view = com_google_android_gms_internal_zzke.zzbtf != null ? com_google_android_gms_internal_zzke.zzbtf.getView() : null;
                if (view != null) {
                    return (View) com.google.android.gms.dynamic.zze.zzae(view);
                }
                zzb.zzdf("View in mediation adapter is null.");
                return null;
            } catch (Throwable e) {
                zzb.zzd("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zzh(@Nullable zzke com_google_android_gms_internal_zzke) {
        return (com_google_android_gms_internal_zzke == null || !com_google_android_gms_internal_zzke.zzchc || com_google_android_gms_internal_zzke.zzbte == null || com_google_android_gms_internal_zzke.zzbte.zzbrw == null) ? false : true;
    }
}
