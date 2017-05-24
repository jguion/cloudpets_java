package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.zzu;
import java.lang.ref.WeakReference;

@zziy
class zzlp extends zzlr implements OnGlobalLayoutListener {
    private final WeakReference<OnGlobalLayoutListener> zzcup;

    public zzlp(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        super(view);
        this.zzcup = new WeakReference(onGlobalLayoutListener);
    }

    public void onGlobalLayout() {
        OnGlobalLayoutListener onGlobalLayoutListener = (OnGlobalLayoutListener) this.zzcup.get();
        if (onGlobalLayoutListener != null) {
            onGlobalLayoutListener.onGlobalLayout();
        } else {
            detach();
        }
    }

    protected void zza(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnGlobalLayoutListener(this);
    }

    protected void zzb(ViewTreeObserver viewTreeObserver) {
        zzu.zzgb().zza(viewTreeObserver, (OnGlobalLayoutListener) this);
    }
}
