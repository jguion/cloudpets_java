package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzcc;
import com.google.android.gms.internal.zzcd;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzib;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkq;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zziy
public class zzt extends com.google.android.gms.ads.internal.client.zzu.zza {
    private final Context mContext;
    @Nullable
    private zzq zzamy;
    private final VersionInfoParcel zzanh;
    private final AdSizeParcel zzapc;
    private final Future<zzcc> zzapd = zzfp();
    private final zzb zzape;
    @Nullable
    private WebView zzapf = new WebView(this.mContext);
    @Nullable
    private zzcc zzapg;
    private AsyncTask<Void, Void, Void> zzaph;

    private class zza extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ zzt zzapi;

        private zza(zzt com_google_android_gms_ads_internal_zzt) {
            this.zzapi = com_google_android_gms_ads_internal_zzt;
        }

        protected Void doInBackground(Void... voidArr) {
            Throwable e;
            try {
                this.zzapi.zzapg = (zzcc) this.zzapi.zzapd.get(((Long) zzdi.zzbha.get()).longValue(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e2) {
                e = e2;
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to load ad data", e);
            } catch (ExecutionException e3) {
                e = e3;
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to load ad data", e);
            } catch (TimeoutException e4) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Timed out waiting for ad data");
            }
            return null;
        }

        protected void onPostExecute(Void voidR) {
            String zzfn = this.zzapi.zzfn();
            if (this.zzapi.zzapf != null) {
                this.zzapi.zzapf.loadUrl(zzfn);
            }
        }
    }

    private static class zzb {
        private final String zzapj;
        private final Map<String, String> zzapk = new TreeMap();
        private String zzapl;
        private String zzapm;

        public zzb(String str) {
            this.zzapj = str;
        }

        public String getQuery() {
            return this.zzapl;
        }

        public String zzfr() {
            return this.zzapm;
        }

        public String zzfs() {
            return this.zzapj;
        }

        public Map<String, String> zzft() {
            return this.zzapk;
        }

        public void zzi(AdRequestParcel adRequestParcel) {
            this.zzapl = adRequestParcel.zzawk.zzbab;
            Bundle bundle = adRequestParcel.zzawn != null ? adRequestParcel.zzawn.getBundle(AdMobAdapter.class.getName()) : null;
            if (bundle != null) {
                String str = (String) zzdi.zzbgz.get();
                for (String str2 : bundle.keySet()) {
                    if (str.equals(str2)) {
                        this.zzapm = bundle.getString(str2);
                    } else if (str2.startsWith("csa_")) {
                        this.zzapk.put(str2.substring("csa_".length()), bundle.getString(str2));
                    }
                }
            }
        }
    }

    public zzt(Context context, AdSizeParcel adSizeParcel, String str, VersionInfoParcel versionInfoParcel) {
        this.mContext = context;
        this.zzanh = versionInfoParcel;
        this.zzapc = adSizeParcel;
        this.zzape = new zzb(str);
        zzfm();
    }

    private void zzaa(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.mContext.startActivity(intent);
    }

    private void zzfm() {
        zzj(0);
        this.zzapf.setVerticalScrollBarEnabled(false);
        this.zzapf.getSettings().setJavaScriptEnabled(true);
        this.zzapf.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ zzt zzapi;

            {
                this.zzapi = r1;
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                if (this.zzapi.zzamy != null) {
                    try {
                        this.zzapi.zzamy.onAdFailedToLoad(0);
                    } catch (Throwable e) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call AdListener.onAdFailedToLoad().", e);
                    }
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith(this.zzapi.zzfo())) {
                    return false;
                }
                if (str.startsWith((String) zzdi.zzbgv.get())) {
                    if (this.zzapi.zzamy != null) {
                        try {
                            this.zzapi.zzamy.onAdFailedToLoad(3);
                        } catch (Throwable e) {
                            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call AdListener.onAdFailedToLoad().", e);
                        }
                    }
                    this.zzapi.zzj(0);
                    return true;
                } else if (str.startsWith((String) zzdi.zzbgw.get())) {
                    if (this.zzapi.zzamy != null) {
                        try {
                            this.zzapi.zzamy.onAdFailedToLoad(0);
                        } catch (Throwable e2) {
                            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call AdListener.onAdFailedToLoad().", e2);
                        }
                    }
                    this.zzapi.zzj(0);
                    return true;
                } else if (str.startsWith((String) zzdi.zzbgx.get())) {
                    if (this.zzapi.zzamy != null) {
                        try {
                            this.zzapi.zzamy.onAdLoaded();
                        } catch (Throwable e22) {
                            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call AdListener.onAdLoaded().", e22);
                        }
                    }
                    this.zzapi.zzj(this.zzapi.zzy(str));
                    return true;
                } else if (str.startsWith("gmsg://")) {
                    return true;
                } else {
                    if (this.zzapi.zzamy != null) {
                        try {
                            this.zzapi.zzamy.onAdLeftApplication();
                        } catch (Throwable e222) {
                            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call AdListener.onAdLeftApplication().", e222);
                        }
                    }
                    this.zzapi.zzaa(this.zzapi.zzz(str));
                    return true;
                }
            }
        });
        this.zzapf.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ zzt zzapi;

            {
                this.zzapi = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (this.zzapi.zzapg != null) {
                    try {
                        this.zzapi.zzapg.zza(motionEvent);
                    } catch (Throwable e) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzd("Unable to process ad data", e);
                    }
                }
                return false;
            }
        });
    }

    private Future<zzcc> zzfp() {
        return zzkq.zza(new Callable<zzcc>(this) {
            final /* synthetic */ zzt zzapi;

            {
                this.zzapi = r1;
            }

            public /* synthetic */ Object call() throws Exception {
                return zzfq();
            }

            public zzcc zzfq() throws Exception {
                return new zzcc(this.zzapi.zzanh.zzcs, this.zzapi.mContext, false);
            }
        });
    }

    private String zzz(String str) {
        if (this.zzapg == null) {
            return str;
        }
        Uri parse = Uri.parse(str);
        try {
            parse = this.zzapg.zzd(parse, this.mContext);
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Unable to process ad data", e);
        } catch (Throwable e2) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Unable to parse ad click url", e2);
        }
        return parse.toString();
    }

    public void destroy() throws RemoteException {
        zzac.zzhq("destroy must be called on the main UI thread.");
        this.zzaph.cancel(true);
        this.zzapd.cancel(true);
        this.zzapf.destroy();
        this.zzapf = null;
    }

    @Nullable
    public String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public boolean isLoading() throws RemoteException {
        return false;
    }

    public boolean isReady() throws RemoteException {
        return false;
    }

    public void pause() throws RemoteException {
        zzac.zzhq("pause must be called on the main UI thread.");
    }

    public void resume() throws RemoteException {
        zzac.zzhq("resume must be called on the main UI thread.");
    }

    public void setManualImpressionsEnabled(boolean z) throws RemoteException {
    }

    public void setUserId(String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void stopLoading() throws RemoteException {
    }

    public void zza(AdSizeParcel adSizeParcel) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    public void zza(VideoOptionsParcel videoOptionsParcel) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzp com_google_android_gms_ads_internal_client_zzp) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzq com_google_android_gms_ads_internal_client_zzq) throws RemoteException {
        this.zzamy = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zza(zzw com_google_android_gms_ads_internal_client_zzw) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzy com_google_android_gms_ads_internal_client_zzy) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzd com_google_android_gms_ads_internal_reward_client_zzd) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzdu com_google_android_gms_internal_zzdu) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzhx com_google_android_gms_internal_zzhx) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzib com_google_android_gms_internal_zzib, String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public boolean zzb(AdRequestParcel adRequestParcel) throws RemoteException {
        zzac.zzb(this.zzapf, (Object) "This Search Ad has already been torn down");
        this.zzape.zzi(adRequestParcel);
        this.zzaph = new zza().execute(new Void[0]);
        return true;
    }

    public com.google.android.gms.dynamic.zzd zzds() throws RemoteException {
        zzac.zzhq("getAdFrame must be called on the main UI thread.");
        return zze.zzac(this.zzapf);
    }

    public AdSizeParcel zzdt() throws RemoteException {
        return this.zzapc;
    }

    public void zzdv() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Nullable
    public zzab zzdw() {
        return null;
    }

    String zzfn() {
        String valueOf;
        Uri zzc;
        Throwable e;
        String valueOf2;
        Builder builder = new Builder();
        builder.scheme("https://").appendEncodedPath((String) zzdi.zzbgy.get());
        builder.appendQueryParameter(SearchIntents.EXTRA_QUERY, this.zzape.getQuery());
        builder.appendQueryParameter("pubId", this.zzape.zzfs());
        Map zzft = this.zzape.zzft();
        for (String valueOf3 : zzft.keySet()) {
            builder.appendQueryParameter(valueOf3, (String) zzft.get(valueOf3));
        }
        Uri build = builder.build();
        if (this.zzapg != null) {
            try {
                zzc = this.zzapg.zzc(build, this.mContext);
            } catch (zzcd e2) {
                e = e2;
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Unable to process ad data", e);
                zzc = build;
                valueOf2 = String.valueOf(zzfo());
                valueOf3 = String.valueOf(zzc.getEncodedQuery());
                return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
            } catch (RemoteException e3) {
                e = e3;
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Unable to process ad data", e);
                zzc = build;
                valueOf2 = String.valueOf(zzfo());
                valueOf3 = String.valueOf(zzc.getEncodedQuery());
                return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
            }
            valueOf2 = String.valueOf(zzfo());
            valueOf3 = String.valueOf(zzc.getEncodedQuery());
            return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
        }
        zzc = build;
        valueOf2 = String.valueOf(zzfo());
        valueOf3 = String.valueOf(zzc.getEncodedQuery());
        return new StringBuilder((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf3).length()).append(valueOf2).append("#").append(valueOf3).toString();
    }

    String zzfo() {
        String str;
        CharSequence zzfr = this.zzape.zzfr();
        if (TextUtils.isEmpty(zzfr)) {
            str = "www.google.com";
        } else {
            CharSequence charSequence = zzfr;
        }
        String valueOf = String.valueOf("https://");
        String str2 = (String) zzdi.zzbgy.get();
        return new StringBuilder(((String.valueOf(valueOf).length() + 0) + String.valueOf(str).length()) + String.valueOf(str2).length()).append(valueOf).append(str).append(str2).toString();
    }

    void zzj(int i) {
        if (this.zzapf != null) {
            this.zzapf.setLayoutParams(new LayoutParams(-1, i));
        }
    }

    int zzy(String str) {
        int i = 0;
        Object queryParameter = Uri.parse(str).getQueryParameter("height");
        if (!TextUtils.isEmpty(queryParameter)) {
            try {
                i = zzm.zzjr().zzb(this.mContext, Integer.parseInt(queryParameter));
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }
}
