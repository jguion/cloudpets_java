package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zziy
public class zzew implements zzev {
    private final Context mContext;
    private final VersionInfoParcel zzanh;

    @zziy
    static class zza {
        private final String mValue;
        private final String zzbaf;

        public zza(String str, String str2) {
            this.zzbaf = str;
            this.mValue = str2;
        }

        public String getKey() {
            return this.zzbaf;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    @zziy
    static class zzb {
        private final String zzbna;
        private final URL zzbnb;
        private final ArrayList<zza> zzbnc;
        private final String zzbnd;

        public zzb(String str, URL url, ArrayList<zza> arrayList, String str2) {
            this.zzbna = str;
            this.zzbnb = url;
            if (arrayList == null) {
                this.zzbnc = new ArrayList();
            } else {
                this.zzbnc = arrayList;
            }
            this.zzbnd = str2;
        }

        public String zzmc() {
            return this.zzbna;
        }

        public URL zzmd() {
            return this.zzbnb;
        }

        public ArrayList<zza> zzme() {
            return this.zzbnc;
        }

        public String zzmf() {
            return this.zzbnd;
        }
    }

    @zziy
    class zzc {
        final /* synthetic */ zzew zzbmx;
        private final zzd zzbne;
        private final boolean zzbnf;
        private final String zzbng;

        public zzc(zzew com_google_android_gms_internal_zzew, boolean z, zzd com_google_android_gms_internal_zzew_zzd, String str) {
            this.zzbmx = com_google_android_gms_internal_zzew;
            this.zzbnf = z;
            this.zzbne = com_google_android_gms_internal_zzew_zzd;
            this.zzbng = str;
        }

        public String getReason() {
            return this.zzbng;
        }

        public boolean isSuccess() {
            return this.zzbnf;
        }

        public zzd zzmg() {
            return this.zzbne;
        }
    }

    @zziy
    static class zzd {
        private final String zzbjs;
        private final String zzbna;
        private final int zzbnh;
        private final List<zza> zzbni;

        public zzd(String str, int i, List<zza> list, String str2) {
            this.zzbna = str;
            this.zzbnh = i;
            if (list == null) {
                this.zzbni = new ArrayList();
            } else {
                this.zzbni = list;
            }
            this.zzbjs = str2;
        }

        public String getBody() {
            return this.zzbjs;
        }

        public int getResponseCode() {
            return this.zzbnh;
        }

        public String zzmc() {
            return this.zzbna;
        }

        public Iterable<zza> zzmh() {
            return this.zzbni;
        }
    }

    public zzew(Context context, VersionInfoParcel versionInfoParcel) {
        this.mContext = context;
        this.zzanh = versionInfoParcel;
    }

    protected zzc zza(zzb com_google_android_gms_internal_zzew_zzb) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) com_google_android_gms_internal_zzew_zzb.zzmd().openConnection();
            zzu.zzfz().zza(this.mContext, this.zzanh.zzcs, false, httpURLConnection);
            Iterator it = com_google_android_gms_internal_zzew_zzb.zzme().iterator();
            while (it.hasNext()) {
                zza com_google_android_gms_internal_zzew_zza = (zza) it.next();
                httpURLConnection.addRequestProperty(com_google_android_gms_internal_zzew_zza.getKey(), com_google_android_gms_internal_zzew_zza.getValue());
            }
            if (!TextUtils.isEmpty(com_google_android_gms_internal_zzew_zzb.zzmf())) {
                httpURLConnection.setDoOutput(true);
                byte[] bytes = com_google_android_gms_internal_zzew_zzb.zzmf().getBytes();
                httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();
            }
            List arrayList = new ArrayList();
            if (httpURLConnection.getHeaderFields() != null) {
                for (Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
                    for (String com_google_android_gms_internal_zzew_zza2 : (List) entry.getValue()) {
                        arrayList.add(new zza((String) entry.getKey(), com_google_android_gms_internal_zzew_zza2));
                    }
                }
            }
            return new zzc(this, true, new zzd(com_google_android_gms_internal_zzew_zzb.zzmc(), httpURLConnection.getResponseCode(), arrayList, zzu.zzfz().zza(new InputStreamReader(httpURLConnection.getInputStream()))), null);
        } catch (Exception e) {
            return new zzc(this, false, null, e.toString());
        }
    }

    protected JSONObject zza(zzd com_google_android_gms_internal_zzew_zzd) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("http_request_id", com_google_android_gms_internal_zzew_zzd.zzmc());
            if (com_google_android_gms_internal_zzew_zzd.getBody() != null) {
                jSONObject.put("body", com_google_android_gms_internal_zzew_zzd.getBody());
            }
            JSONArray jSONArray = new JSONArray();
            for (zza com_google_android_gms_internal_zzew_zza : com_google_android_gms_internal_zzew_zzd.zzmh()) {
                jSONArray.put(new JSONObject().put("key", com_google_android_gms_internal_zzew_zza.getKey()).put(Param.VALUE, com_google_android_gms_internal_zzew_zza.getValue()));
            }
            jSONObject.put("headers", jSONArray);
            jSONObject.put("response_code", com_google_android_gms_internal_zzew_zzd.getResponseCode());
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Error constructing JSON for http response.", e);
        }
        return jSONObject;
    }

    public void zza(final zzlt com_google_android_gms_internal_zzlt, final Map<String, String> map) {
        zzkq.zza(new Runnable(this) {
            final /* synthetic */ zzew zzbmx;

            public void run() {
                com.google.android.gms.ads.internal.util.client.zzb.zzdd("Received Http request.");
                final JSONObject zzay = this.zzbmx.zzay((String) map.get("http_request"));
                if (zzay == null) {
                    com.google.android.gms.ads.internal.util.client.zzb.e("Response should not be null.");
                } else {
                    zzkr.zzcrf.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 zzbmz;

                        public void run() {
                            com_google_android_gms_internal_zzlt.zzb("fetchHttpRequestCompleted", zzay);
                            com.google.android.gms.ads.internal.util.client.zzb.zzdd("Dispatched http response.");
                        }
                    });
                }
            }
        });
    }

    public JSONObject zzay(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject();
            Object obj = "";
            try {
                obj = jSONObject.optString("http_request_id");
                zzc zza = zza(zzc(jSONObject));
                if (zza.isSuccess()) {
                    jSONObject2.put("response", zza(zza.zzmg()));
                    jSONObject2.put("success", true);
                    return jSONObject2;
                }
                jSONObject2.put("response", new JSONObject().put("http_request_id", obj));
                jSONObject2.put("success", false);
                jSONObject2.put("reason", zza.getReason());
                return jSONObject2;
            } catch (Exception e) {
                try {
                    jSONObject2.put("response", new JSONObject().put("http_request_id", obj));
                    jSONObject2.put("success", false);
                    jSONObject2.put("reason", e.toString());
                    return jSONObject2;
                } catch (JSONException e2) {
                    return jSONObject2;
                }
            }
        } catch (JSONException e3) {
            com.google.android.gms.ads.internal.util.client.zzb.e("The request is not a valid JSON.");
            try {
                return new JSONObject().put("success", false);
            } catch (JSONException e4) {
                return new JSONObject();
            }
        }
    }

    protected zzb zzc(JSONObject jSONObject) {
        URL url;
        String optString = jSONObject.optString("http_request_id");
        String optString2 = jSONObject.optString("url");
        String optString3 = jSONObject.optString("post_body", null);
        try {
            url = new URL(optString2);
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Error constructing http request.", e);
            url = null;
        }
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("headers");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                arrayList.add(new zza(optJSONObject.optString("key"), optJSONObject.optString(Param.VALUE)));
            }
        }
        return new zzb(optString, url, arrayList, optString3);
    }
}
