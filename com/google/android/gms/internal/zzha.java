package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzgv.zza;
import java.util.ArrayList;
import java.util.List;

@zziy
public class zzha extends zza {
    private final NativeContentAdMapper zzbue;

    public zzha(NativeContentAdMapper nativeContentAdMapper) {
        this.zzbue = nativeContentAdMapper;
    }

    public String getAdvertiser() {
        return this.zzbue.getAdvertiser();
    }

    public String getBody() {
        return this.zzbue.getBody();
    }

    public String getCallToAction() {
        return this.zzbue.getCallToAction();
    }

    public Bundle getExtras() {
        return this.zzbue.getExtras();
    }

    public String getHeadline() {
        return this.zzbue.getHeadline();
    }

    public List getImages() {
        List<Image> images = this.zzbue.getImages();
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
        return this.zzbue.getOverrideClickHandling();
    }

    public boolean getOverrideImpressionRecording() {
        return this.zzbue.getOverrideImpressionRecording();
    }

    public void recordImpression() {
        this.zzbue.recordImpression();
    }

    public void zzk(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbue.handleClick((View) zze.zzae(com_google_android_gms_dynamic_zzd));
    }

    public void zzl(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbue.trackView((View) zze.zzae(com_google_android_gms_dynamic_zzd));
    }

    public zzdx zzlt() {
        Image logo = this.zzbue.getLogo();
        return logo != null ? new zzc(logo.getDrawable(), logo.getUri(), logo.getScale()) : null;
    }

    public void zzm(zzd com_google_android_gms_dynamic_zzd) {
        this.zzbue.untrackView((View) zze.zzae(com_google_android_gms_dynamic_zzd));
    }
}
