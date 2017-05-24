package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.formats.zzj;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.dynamic.zze;
import com.google.android.vending.expansion.downloader.Constants;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public class zzis implements Callable<zzke> {
    private static final long zzcdo = TimeUnit.SECONDS.toMillis(60);
    private final Context mContext;
    private final Object zzakd = new Object();
    private final zzdq zzalg;
    private final zzir zzbkn;
    private final zzau zzbkp;
    private final com.google.android.gms.internal.zzke.zza zzcck;
    private int zzcdb;
    private final zzky zzcdx;
    private final zzq zzcdy;
    private boolean zzcdz;
    private List<String> zzcea;
    private JSONObject zzceb;

    public interface zza<T extends com.google.android.gms.ads.internal.formats.zzi.zza> {
        T zza(zzis com_google_android_gms_internal_zzis, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException;
    }

    class zzb {
        final /* synthetic */ zzis zzcef;
        public zzev zzcev;

        zzb(zzis com_google_android_gms_internal_zzis) {
            this.zzcef = com_google_android_gms_internal_zzis;
        }
    }

    public zzis(Context context, zzq com_google_android_gms_ads_internal_zzq, zzky com_google_android_gms_internal_zzky, zzau com_google_android_gms_internal_zzau, com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, zzdq com_google_android_gms_internal_zzdq) {
        this.mContext = context;
        this.zzcdy = com_google_android_gms_ads_internal_zzq;
        this.zzcdx = com_google_android_gms_internal_zzky;
        this.zzcck = com_google_android_gms_internal_zzke_zza;
        this.zzbkp = com_google_android_gms_internal_zzau;
        this.zzalg = com_google_android_gms_internal_zzdq;
        this.zzbkn = zza(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_ads_internal_zzq, com_google_android_gms_internal_zzau);
        this.zzbkn.zzre();
        this.zzcdz = false;
        this.zzcdb = -2;
        this.zzcea = null;
    }

    private com.google.android.gms.ads.internal.formats.zzi.zza zza(zza com_google_android_gms_internal_zzis_zza, JSONObject jSONObject, String str) throws ExecutionException, InterruptedException, JSONException {
        if (zzrq() || com_google_android_gms_internal_zzis_zza == null || jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("tracking_urls_and_actions");
        String[] zzd = zzd(jSONObject2, "impression_tracking_urls");
        this.zzcea = zzd == null ? null : Arrays.asList(zzd);
        this.zzceb = jSONObject2.optJSONObject("active_view");
        com.google.android.gms.ads.internal.formats.zzi.zza zza = com_google_android_gms_internal_zzis_zza.zza(this, jSONObject);
        if (zza == null) {
            com.google.android.gms.ads.internal.util.client.zzb.e("Failed to retrieve ad assets.");
            return null;
        }
        zza.zzb(new zzj(this.mContext, this.zzcdy, this.zzbkn, this.zzbkp, jSONObject, zza, this.zzcck.zzcix.zzaqv, str));
        return zza;
    }

    private zzlj<zzc> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        final String string = z ? jSONObject.getString("url") : jSONObject.optString("url");
        final double optDouble = jSONObject.optDouble("scale", 1.0d);
        final boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (TextUtils.isEmpty(string)) {
            zza(0, z);
            return new zzlh(null);
        } else if (z2) {
            return new zzlh(new zzc(null, Uri.parse(string), optDouble));
        } else {
            final boolean z3 = z;
            return this.zzcdx.zza(string, new com.google.android.gms.internal.zzky.zza<zzc>(this) {
                final /* synthetic */ zzis zzcef;

                @TargetApi(19)
                public zzc zzg(InputStream inputStream) {
                    Bitmap decodeStream;
                    Options options = new Options();
                    options.inDensity = (int) (160.0d * optDouble);
                    if (!optBoolean) {
                        options.inPreferredConfig = Config.RGB_565;
                    }
                    try {
                        decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                    } catch (Throwable e) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzb("Error grabbing image.", e);
                        decodeStream = null;
                    }
                    if (decodeStream == null) {
                        this.zzcef.zza(2, z3);
                        return null;
                    }
                    if (zzs.zzaxr()) {
                        int width = decodeStream.getWidth();
                        int height = decodeStream.getHeight();
                        zzkn.v("Decoded image w: " + width + " h:" + height + " bytes: " + decodeStream.getAllocationByteCount());
                    }
                    return new zzc(new BitmapDrawable(Resources.getSystem(), decodeStream), Uri.parse(string), optDouble);
                }

                @TargetApi(19)
                public /* synthetic */ Object zzh(InputStream inputStream) {
                    return zzg(inputStream);
                }

                public zzc zzrr() {
                    this.zzcef.zza(2, z3);
                    return null;
                }

                public /* synthetic */ Object zzrs() {
                    return zzrr();
                }
            });
        }
    }

    private void zza(com.google.android.gms.ads.internal.formats.zzi.zza com_google_android_gms_ads_internal_formats_zzi_zza) {
        if (com_google_android_gms_ads_internal_formats_zzi_zza instanceof zzf) {
            final zzf com_google_android_gms_ads_internal_formats_zzf = (zzf) com_google_android_gms_ads_internal_formats_zzi_zza;
            zzb com_google_android_gms_internal_zzis_zzb = new zzb(this);
            final zzev anonymousClass3 = new zzev(this) {
                final /* synthetic */ zzis zzcef;

                public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
                    this.zzcef.zzb(com_google_android_gms_ads_internal_formats_zzf, (String) map.get("asset"));
                }
            };
            com_google_android_gms_internal_zzis_zzb.zzcev = anonymousClass3;
            this.zzbkn.zza(new com.google.android.gms.internal.zzir.zza(this) {
                final /* synthetic */ zzis zzcef;

                public void zze(zzfz com_google_android_gms_internal_zzfz) {
                    com_google_android_gms_internal_zzfz.zza("/nativeAdCustomClick", anonymousClass3);
                }
            });
        }
    }

    private zzke zzb(com.google.android.gms.ads.internal.formats.zzi.zza com_google_android_gms_ads_internal_formats_zzi_zza) {
        int i;
        synchronized (this.zzakd) {
            i = this.zzcdb;
            if (com_google_android_gms_ads_internal_formats_zzi_zza == null && this.zzcdb == -2) {
                i = 0;
            }
        }
        return new zzke(this.zzcck.zzcix.zzcfu, null, this.zzcck.zzcop.zzbsd, i, this.zzcck.zzcop.zzbse, this.zzcea, this.zzcck.zzcop.orientation, this.zzcck.zzcop.zzbsj, this.zzcck.zzcix.zzcfx, false, null, null, null, null, null, 0, this.zzcck.zzaqz, this.zzcck.zzcop.zzchb, this.zzcck.zzcoj, this.zzcck.zzcok, this.zzcck.zzcop.zzchh, this.zzceb, i != -2 ? null : com_google_android_gms_ads_internal_formats_zzi_zza, null, null, null, this.zzcck.zzcop.zzchu, this.zzcck.zzcop.zzchv, null, this.zzcck.zzcop.zzbsg, this.zzcck.zzcop.zzchy);
    }

    private Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException e) {
            return null;
        }
    }

    private void zzb(zzef com_google_android_gms_internal_zzef, String str) {
        try {
            zzej zzx = this.zzcdy.zzx(com_google_android_gms_internal_zzef.getCustomTemplateId());
            if (zzx != null) {
                zzx.zza(com_google_android_gms_internal_zzef, str);
            }
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd(new StringBuilder(String.valueOf(str).length() + 40).append("Failed to call onCustomClick for asset ").append(str).append(".").toString(), e);
        }
    }

    private JSONObject zzcf(final String str) throws ExecutionException, InterruptedException, TimeoutException, JSONException {
        if (zzrq()) {
            return null;
        }
        final zzlg com_google_android_gms_internal_zzlg = new zzlg();
        final zzb com_google_android_gms_internal_zzis_zzb = new zzb(this);
        this.zzbkn.zza(new com.google.android.gms.internal.zzir.zza(this) {
            final /* synthetic */ zzis zzcef;

            public void zze(final zzfz com_google_android_gms_internal_zzfz) {
                zzev anonymousClass1 = new zzev(this) {
                    final /* synthetic */ AnonymousClass1 zzceg;

                    public void zza(zzlt com_google_android_gms_internal_zzlt, Map<String, String> map) {
                        com_google_android_gms_internal_zzfz.zzb("/nativeAdPreProcess", com_google_android_gms_internal_zzis_zzb.zzcev);
                        try {
                            String str = (String) map.get("success");
                            if (!TextUtils.isEmpty(str)) {
                                com_google_android_gms_internal_zzlg.zzh(new JSONObject(str).getJSONArray("ads").getJSONObject(0));
                                return;
                            }
                        } catch (Throwable e) {
                            com.google.android.gms.ads.internal.util.client.zzb.zzb("Malformed native JSON response.", e);
                        }
                        this.zzceg.zzcef.zzap(0);
                        zzac.zza(this.zzceg.zzcef.zzrq(), (Object) "Unable to set the ad state error!");
                        com_google_android_gms_internal_zzlg.zzh(null);
                    }
                };
                com_google_android_gms_internal_zzis_zzb.zzcev = anonymousClass1;
                com_google_android_gms_internal_zzfz.zza("/nativeAdPreProcess", anonymousClass1);
                try {
                    JSONObject jSONObject = new JSONObject(this.zzcef.zzcck.zzcop.body);
                    jSONObject.put("ads_id", str);
                    com_google_android_gms_internal_zzfz.zza("google.afma.nativeAds.preProcessJsonGmsg", jSONObject);
                } catch (Throwable e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Exception occurred while invoking javascript", e);
                    com_google_android_gms_internal_zzlg.zzh(null);
                }
            }

            public void zzro() {
                com_google_android_gms_internal_zzlg.zzh(null);
            }
        });
        return (JSONObject) com_google_android_gms_internal_zzlg.get(zzcdo, TimeUnit.MILLISECONDS);
    }

    private String[] zzd(JSONObject jSONObject, String str) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        String[] strArr = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            strArr[i] = optJSONArray.getString(i);
        }
        return strArr;
    }

    private static List<Drawable> zzh(List<zzc> list) throws RemoteException {
        List<Drawable> arrayList = new ArrayList();
        for (zzc zzln : list) {
            arrayList.add((Drawable) zze.zzae(zzln.zzln()));
        }
        return arrayList;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzrp();
    }

    zzir zza(Context context, com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, zzq com_google_android_gms_ads_internal_zzq, zzau com_google_android_gms_internal_zzau) {
        return new zzir(context, com_google_android_gms_internal_zzke_zza, com_google_android_gms_ads_internal_zzq, com_google_android_gms_internal_zzau);
    }

    zzit zza(Context context, zzau com_google_android_gms_internal_zzau, com.google.android.gms.internal.zzke.zza com_google_android_gms_internal_zzke_zza, zzdq com_google_android_gms_internal_zzdq, zzq com_google_android_gms_ads_internal_zzq) {
        return new zzit(context, com_google_android_gms_internal_zzau, com_google_android_gms_internal_zzke_zza, com_google_android_gms_internal_zzdq, com_google_android_gms_ads_internal_zzq);
    }

    public zzlj<zzc> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public List<zzlj<zzc>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray jSONArray = z ? jSONObject.getJSONArray(str) : jSONObject.optJSONArray(str);
        List<zzlj<zzc>> arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            zza(0, z);
            return arrayList;
        }
        int length = z3 ? jSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, z, z2));
        }
        return arrayList;
    }

    public Future<zzc> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    public void zza(int i, boolean z) {
        if (z) {
            zzap(i);
        }
    }

    public void zzap(int i) {
        synchronized (this.zzakd) {
            this.zzcdz = true;
            this.zzcdb = i;
        }
    }

    public zzlj<zzlt> zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return new zzlh(null);
        }
        if (!TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            return zza(this.mContext, this.zzbkp, this.zzcck, this.zzalg, this.zzcdy).zzh(optJSONObject);
        }
        com.google.android.gms.ads.internal.util.client.zzb.zzdf("Required field 'vast_xml' is missing");
        return new zzlh(null);
    }

    protected zza zzf(JSONObject jSONObject) throws ExecutionException, InterruptedException, JSONException, TimeoutException {
        if (zzrq() || jSONObject == null) {
            return null;
        }
        String string = jSONObject.getString("template_id");
        boolean z = this.zzcck.zzcix.zzarn != null ? this.zzcck.zzcix.zzarn.zzblb : false;
        boolean z2 = this.zzcck.zzcix.zzarn != null ? this.zzcck.zzcix.zzarn.zzbld : false;
        if ("2".equals(string)) {
            return new zziu(z, z2);
        }
        if ("1".equals(string)) {
            return new zziv(z, z2);
        }
        if ("3".equals(string)) {
            final String string2 = jSONObject.getString("custom_template_id");
            final zzlg com_google_android_gms_internal_zzlg = new zzlg();
            zzkr.zzcrf.post(new Runnable(this) {
                final /* synthetic */ zzis zzcef;

                public void run() {
                    com_google_android_gms_internal_zzlg.zzh((zzek) this.zzcef.zzcdy.zzfi().get(string2));
                }
            });
            if (com_google_android_gms_internal_zzlg.get(zzcdo, TimeUnit.MILLISECONDS) != null) {
                return new zziw(z);
            }
            string2 = "No handler for custom template: ";
            String valueOf = String.valueOf(jSONObject.getString("custom_template_id"));
            com.google.android.gms.ads.internal.util.client.zzb.e(valueOf.length() != 0 ? string2.concat(valueOf) : new String(string2));
        } else {
            zzap(0);
        }
        return null;
    }

    public zzlj<com.google.android.gms.ads.internal.formats.zza> zzg(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return new zzlh(null);
        }
        String optString = optJSONObject.optString("text");
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb = zzb(optJSONObject, "text_color");
        Integer zzb2 = zzb(optJSONObject, "bg_color");
        final int optInt2 = optJSONObject.optInt("animation_ms", Constants.MAX_DOWNLOADS);
        final int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        final int i = (this.zzcck.zzcix.zzarn == null || this.zzcck.zzcix.zzarn.versionCode < 2) ? 1 : this.zzcck.zzcix.zzarn.zzble;
        List arrayList = new ArrayList();
        if (optJSONObject.optJSONArray("images") != null) {
            arrayList = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, "image", false, false));
        }
        final String str = optString;
        final Integer num = zzb2;
        final Integer num2 = zzb;
        final int i2 = optInt;
        return zzli.zza(zzli.zzo(arrayList), new com.google.android.gms.internal.zzli.zza<List<zzc>, com.google.android.gms.ads.internal.formats.zza>(this) {
            final /* synthetic */ zzis zzcef;

            public /* synthetic */ Object apply(Object obj) {
                return zzj((List) obj);
            }

            public com.google.android.gms.ads.internal.formats.zza zzj(List<zzc> list) {
                com.google.android.gms.ads.internal.formats.zza com_google_android_gms_ads_internal_formats_zza;
                if (list != null) {
                    try {
                        if (!list.isEmpty()) {
                            com_google_android_gms_ads_internal_formats_zza = new com.google.android.gms.ads.internal.formats.zza(str, zzis.zzh(list), num, num2, i2 > 0 ? Integer.valueOf(i2) : null, optInt3 + optInt2, i);
                            return com_google_android_gms_ads_internal_formats_zza;
                        }
                    } catch (Throwable e) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzb("Could not get attribution icon", e);
                        return null;
                    }
                }
                com_google_android_gms_ads_internal_formats_zza = null;
                return com_google_android_gms_ads_internal_formats_zza;
            }
        });
    }

    public zzke zzrp() {
        try {
            this.zzbkn.zzrf();
            String uuid = UUID.randomUUID().toString();
            JSONObject zzcf = zzcf(uuid);
            com.google.android.gms.ads.internal.formats.zzi.zza zza = zza(zzf(zzcf), zzcf, uuid);
            zza(zza);
            return zzb(zza);
        } catch (CancellationException e) {
            if (!this.zzcdz) {
                zzap(0);
            }
            return zzb(null);
        } catch (ExecutionException e2) {
            if (this.zzcdz) {
                zzap(0);
            }
            return zzb(null);
        } catch (InterruptedException e3) {
            if (this.zzcdz) {
                zzap(0);
            }
            return zzb(null);
        } catch (Throwable e4) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Malformed native JSON response.", e4);
            if (this.zzcdz) {
                zzap(0);
            }
            return zzb(null);
        } catch (Throwable e42) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Timeout when loading native ad.", e42);
            if (this.zzcdz) {
                zzap(0);
            }
            return zzb(null);
        }
    }

    public boolean zzrq() {
        boolean z;
        synchronized (this.zzakd) {
            z = this.zzcdz;
        }
        return z;
    }
}
