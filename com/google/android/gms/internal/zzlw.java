package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.zzg;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.Map;
import org.json.JSONObject;

@zziy
class zzlw extends FrameLayout implements zzlt {
    private static final int zzbxe = Color.argb(0, 0, 0, 0);
    private final zzlt zzcvn;
    private final zzls zzcvo;

    public zzlw(zzlt com_google_android_gms_internal_zzlt) {
        super(com_google_android_gms_internal_zzlt.getContext());
        this.zzcvn = com_google_android_gms_internal_zzlt;
        this.zzcvo = new zzls(com_google_android_gms_internal_zzlt.zzvo(), this, this);
        zzlu zzvr = this.zzcvn.zzvr();
        if (zzvr != null) {
            zzvr.zzo(this);
        }
        addView(this.zzcvn.getView());
    }

    public void destroy() {
        this.zzcvn.destroy();
    }

    public String getRequestId() {
        return this.zzcvn.getRequestId();
    }

    public int getRequestedOrientation() {
        return this.zzcvn.getRequestedOrientation();
    }

    public View getView() {
        return this;
    }

    public WebView getWebView() {
        return this.zzcvn.getWebView();
    }

    public boolean isDestroyed() {
        return this.zzcvn.isDestroyed();
    }

    public void loadData(String str, String str2, String str3) {
        this.zzcvn.loadData(str, str2, str3);
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.zzcvn.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public void loadUrl(String str) {
        this.zzcvn.loadUrl(str);
    }

    public void onPause() {
        this.zzcvo.onPause();
        this.zzcvn.onPause();
    }

    public void onResume() {
        this.zzcvn.onResume();
    }

    public void setContext(Context context) {
        this.zzcvn.setContext(context);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.zzcvn.setOnClickListener(onClickListener);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.zzcvn.setOnTouchListener(onTouchListener);
    }

    public void setRequestedOrientation(int i) {
        this.zzcvn.setRequestedOrientation(i);
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        this.zzcvn.setWebChromeClient(webChromeClient);
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        this.zzcvn.setWebViewClient(webViewClient);
    }

    public void stopLoading() {
        this.zzcvn.stopLoading();
    }

    public void zza(Context context, AdSizeParcel adSizeParcel, zzdq com_google_android_gms_internal_zzdq) {
        this.zzcvo.onDestroy();
        this.zzcvn.zza(context, adSizeParcel, com_google_android_gms_internal_zzdq);
    }

    public void zza(AdSizeParcel adSizeParcel) {
        this.zzcvn.zza(adSizeParcel);
    }

    public void zza(zzcj com_google_android_gms_internal_zzcj, boolean z) {
        this.zzcvn.zza(com_google_android_gms_internal_zzcj, z);
    }

    public void zza(zzly com_google_android_gms_internal_zzly) {
        this.zzcvn.zza(com_google_android_gms_internal_zzly);
    }

    public void zza(String str, zzev com_google_android_gms_internal_zzev) {
        this.zzcvn.zza(str, com_google_android_gms_internal_zzev);
    }

    public void zza(String str, Map<String, ?> map) {
        this.zzcvn.zza(str, (Map) map);
    }

    public void zza(String str, JSONObject jSONObject) {
        this.zzcvn.zza(str, jSONObject);
    }

    public void zzah(int i) {
        this.zzcvn.zzah(i);
    }

    public void zzaj(boolean z) {
        this.zzcvn.zzaj(z);
    }

    public void zzak(boolean z) {
        this.zzcvn.zzak(z);
    }

    public void zzal(boolean z) {
        this.zzcvn.zzal(z);
    }

    public void zzam(boolean z) {
        this.zzcvn.zzam(z);
    }

    public void zzb(@Nullable zzg com_google_android_gms_ads_internal_formats_zzg) {
        this.zzcvn.zzb(com_google_android_gms_ads_internal_formats_zzg);
    }

    public void zzb(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        this.zzcvn.zzb(com_google_android_gms_ads_internal_overlay_zzd);
    }

    public void zzb(String str, zzev com_google_android_gms_internal_zzev) {
        this.zzcvn.zzb(str, com_google_android_gms_internal_zzev);
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzcvn.zzb(str, jSONObject);
    }

    public void zzc(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        this.zzcvn.zzc(com_google_android_gms_ads_internal_overlay_zzd);
    }

    public void zzdg(String str) {
        this.zzcvn.zzdg(str);
    }

    public void zzdh(String str) {
        this.zzcvn.zzdh(str);
    }

    public com.google.android.gms.ads.internal.zzd zzdp() {
        return this.zzcvn.zzdp();
    }

    public AdSizeParcel zzdt() {
        return this.zzcvn.zzdt();
    }

    public void zzel() {
        this.zzcvn.zzel();
    }

    public void zzem() {
        this.zzcvn.zzem();
    }

    public void zzj(String str, String str2) {
        this.zzcvn.zzj(str, str2);
    }

    public void zzoz() {
        this.zzcvn.zzoz();
    }

    public boolean zzpr() {
        return this.zzcvn.zzpr();
    }

    public void zzvl() {
        this.zzcvn.zzvl();
    }

    public void zzvm() {
        this.zzcvn.zzvm();
    }

    public Activity zzvn() {
        return this.zzcvn.zzvn();
    }

    public Context zzvo() {
        return this.zzcvn.zzvo();
    }

    public zzd zzvp() {
        return this.zzcvn.zzvp();
    }

    public zzd zzvq() {
        return this.zzcvn.zzvq();
    }

    public zzlu zzvr() {
        return this.zzcvn.zzvr();
    }

    public boolean zzvs() {
        return this.zzcvn.zzvs();
    }

    public zzau zzvt() {
        return this.zzcvn.zzvt();
    }

    public VersionInfoParcel zzvu() {
        return this.zzcvn.zzvu();
    }

    public boolean zzvv() {
        return this.zzcvn.zzvv();
    }

    public void zzvw() {
        this.zzcvo.onDestroy();
        this.zzcvn.zzvw();
    }

    public boolean zzvx() {
        return this.zzcvn.zzvx();
    }

    public zzls zzvy() {
        return this.zzcvo;
    }

    public zzdo zzvz() {
        return this.zzcvn.zzvz();
    }

    public zzdp zzwa() {
        return this.zzcvn.zzwa();
    }

    public zzly zzwb() {
        return this.zzcvn.zzwb();
    }

    public boolean zzwc() {
        return this.zzcvn.zzwc();
    }

    public void zzwd() {
        this.zzcvn.zzwd();
    }

    public void zzwe() {
        this.zzcvn.zzwe();
    }

    public OnClickListener zzwf() {
        return this.zzcvn.zzwf();
    }

    @Nullable
    public zzg zzwg() {
        return this.zzcvn.zzwg();
    }

    public void zzwh() {
        setBackgroundColor(zzbxe);
        this.zzcvn.setBackgroundColor(zzbxe);
    }
}
