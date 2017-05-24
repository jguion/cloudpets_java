package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgu.zza;
import java.util.ArrayList;
import java.util.List;

@zziy
public class zzgz extends zza {
    private final NativeAppInstallAdMapper zzbud;

    public zzgz(NativeAppInstallAdMapper nativeAppInstallAdMapper) {
        this.zzbud = nativeAppInstallAdMapper;
    }

    public String getBody() {
        return this.zzbud.getBody();
    }

    public String getCallToAction() {
        return this.zzbud.getCallToAction();
    }

    public Bundle getExtras() {
        return this.zzbud.getExtras();
    }

    public String getHeadline() {
        return this.zzbud.getHeadline();
    }

    public List getImages() {
        List<Image> images = this.zzbud.getImages();
        if (images == null) {
            return null;
        }
        List arrayList = new ArrayList();
        for (Image image : images) {
            arrayList.add(new zzc(image.getDrawable(), image.getUri(), image.getScale()));
        }
        return arrayList;
    }

    public boolean getOverrideClickHandling() {
        return this.zzbud.getOverrideClickHandling();
    }

    public boolean getOverrideImpressionRecording() {
        return this.zzbud.getOverrideImpressionRecording();
    }

    public String getPrice() {
        return this.zzbud.getPrice();
    }

    public double getStarRating() {
        return this.zzbud.getStarRating();
    }

    public String getStore() {
        return this.zzbud.getStore();
    }

    public void recordImpression() {
        this.zzbud.recordImpression();
    }

    public zzab zzdw() {
        return this.zzbud.getVideoController() != null ? this.zzbud.getVideoController().zzdj() : null;
    }

    public void zzk(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbud.handleClick((View) zze.zzae(com_google_android_gms_dynamic_zzd));
    }

    public void zzl(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbud.trackView((View) zze.zzae(com_google_android_gms_dynamic_zzd));
    }

    public zzdx zzlo() {
        Image icon = this.zzbud.getIcon();
        return icon != null ? new zzc(icon.getDrawable(), icon.getUri(), icon.getScale()) : null;
    }

    public void zzm(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbud.untrackView((View) zze.zzae(com_google_android_gms_dynamic_zzd));
    }
}
