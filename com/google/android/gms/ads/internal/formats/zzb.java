package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zziy;
import java.util.List;

@zziy
class zzb extends RelativeLayout {
    private static final float[] zzbjl = new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
    private final RelativeLayout zzbjm;
    @Nullable
    private AnimationDrawable zzbjn;

    public zzb(Context context, zza com_google_android_gms_ads_internal_formats_zza) {
        super(context);
        zzac.zzy(com_google_android_gms_ads_internal_formats_zza);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        switch (com_google_android_gms_ads_internal_formats_zza.zzll()) {
            case 0:
                layoutParams.addRule(10);
                layoutParams.addRule(9);
                break;
            case 2:
                layoutParams.addRule(12);
                layoutParams.addRule(11);
                break;
            case 3:
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                break;
            default:
                layoutParams.addRule(10);
                layoutParams.addRule(11);
                break;
        }
        Drawable shapeDrawable = new ShapeDrawable(new RoundRectShape(zzbjl, null, null));
        shapeDrawable.getPaint().setColor(com_google_android_gms_ads_internal_formats_zza.getBackgroundColor());
        this.zzbjm = new RelativeLayout(context);
        this.zzbjm.setLayoutParams(layoutParams);
        zzu.zzgb().zza(this.zzbjm, shapeDrawable);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (!TextUtils.isEmpty(com_google_android_gms_ads_internal_formats_zza.getText())) {
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            View textView = new TextView(context);
            textView.setLayoutParams(layoutParams2);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(com_google_android_gms_ads_internal_formats_zza.getText());
            textView.setTextColor(com_google_android_gms_ads_internal_formats_zza.getTextColor());
            textView.setTextSize((float) com_google_android_gms_ads_internal_formats_zza.getTextSize());
            textView.setPadding(zzm.zzjr().zzb(context, 4), 0, zzm.zzjr().zzb(context, 4), 0);
            this.zzbjm.addView(textView);
            layoutParams.addRule(1, textView.getId());
        }
        View imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        imageView.setId(1195835394);
        List<Drawable> zzlj = com_google_android_gms_ads_internal_formats_zza.zzlj();
        if (zzlj.size() > 1) {
            this.zzbjn = new AnimationDrawable();
            for (Drawable addFrame : zzlj) {
                this.zzbjn.addFrame(addFrame, com_google_android_gms_ads_internal_formats_zza.zzlk());
            }
            zzu.zzgb().zza(imageView, this.zzbjn);
        } else if (zzlj.size() == 1) {
            imageView.setImageDrawable((Drawable) zzlj.get(0));
        }
        this.zzbjm.addView(imageView);
        addView(this.zzbjm);
    }

    public void onAttachedToWindow() {
        if (this.zzbjn != null) {
            this.zzbjn.start();
        }
        super.onAttachedToWindow();
    }

    public ViewGroup zzlm() {
        return this.zzbjm;
    }
}
