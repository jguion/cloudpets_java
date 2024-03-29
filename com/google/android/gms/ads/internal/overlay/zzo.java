package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.internal.zziy;

@zziy
public class zzo extends FrameLayout implements OnClickListener {
    private final ImageButton zzbyz;
    private final zzu zzbza;

    public zzo(Context context, int i, zzu com_google_android_gms_ads_internal_overlay_zzu) {
        super(context);
        this.zzbza = com_google_android_gms_ads_internal_overlay_zzu;
        setOnClickListener(this);
        this.zzbyz = new ImageButton(context);
        this.zzbyz.setImageResource(17301527);
        this.zzbyz.setBackgroundColor(0);
        this.zzbyz.setOnClickListener(this);
        this.zzbyz.setPadding(0, 0, 0, 0);
        this.zzbyz.setContentDescription("Interstitial close button");
        int zzb = zzm.zzjr().zzb(context, i);
        addView(this.zzbyz, new LayoutParams(zzb, zzb, 17));
    }

    public void onClick(View view) {
        if (this.zzbza != null) {
            this.zzbza.zzot();
        }
    }

    public void zza(boolean z, boolean z2) {
        if (!z2) {
            this.zzbyz.setVisibility(0);
        } else if (z) {
            this.zzbyz.setVisibility(4);
        } else {
            this.zzbyz.setVisibility(8);
        }
    }
}
