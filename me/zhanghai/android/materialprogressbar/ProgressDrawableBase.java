package me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import me.zhanghai.android.materialprogressbar.internal.ThemeUtils;

abstract class ProgressDrawableBase extends Drawable implements IntrinsicPaddingDrawable, TintableDrawable {
    protected int mAlpha = 255;
    protected ColorFilter mColorFilter;
    private Paint mPaint;
    protected PorterDuffColorFilter mTintFilter;
    protected ColorStateList mTintList;
    protected Mode mTintMode = Mode.SRC_IN;
    protected boolean mUseIntrinsicPadding = true;

    protected abstract void onDraw(Canvas canvas, int i, int i2, Paint paint);

    protected abstract void onPreparePaint(Paint paint);

    public ProgressDrawableBase(Context context) {
        setTint(ThemeUtils.getColorFromAttrRes(R.attr.colorControlActivated, context));
    }

    public boolean getUseIntrinsicPadding() {
        return this.mUseIntrinsicPadding;
    }

    public void setUseIntrinsicPadding(boolean useIntrinsicPadding) {
        if (this.mUseIntrinsicPadding != useIntrinsicPadding) {
            this.mUseIntrinsicPadding = useIntrinsicPadding;
            invalidateSelf();
        }
    }

    public void setAlpha(int alpha) {
        if (this.mAlpha != alpha) {
            this.mAlpha = alpha;
            invalidateSelf();
        }
    }

    public ColorFilter getColorFilter() {
        return this.mColorFilter;
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mColorFilter = colorFilter;
        invalidateSelf();
    }

    public void setTint(@ColorInt int tintColor) {
        setTintList(ColorStateList.valueOf(tintColor));
    }

    public void setTintList(@Nullable ColorStateList tint) {
        this.mTintList = tint;
        this.mTintFilter = makeTintFilter(this.mTintList, this.mTintMode);
        invalidateSelf();
    }

    public void setTintMode(@NonNull Mode tintMode) {
        this.mTintMode = tintMode;
        this.mTintFilter = makeTintFilter(this.mTintList, this.mTintMode);
        invalidateSelf();
    }

    private PorterDuffColorFilter makeTintFilter(ColorStateList tint, Mode tintMode) {
        if (tint == null || tintMode == null) {
            return null;
        }
        return new PorterDuffColorFilter(tint.getColorForState(getState(), 0), tintMode);
    }

    private boolean needMirroring() {
        if (DrawableCompat.isAutoMirrored(this) && DrawableCompat.getLayoutDirection(this) == 1) {
            return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.width() != 0 && bounds.height() != 0) {
            if (this.mPaint == null) {
                this.mPaint = new Paint();
                this.mPaint.setAntiAlias(true);
                this.mPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
                onPreparePaint(this.mPaint);
            }
            this.mPaint.setAlpha(this.mAlpha);
            this.mPaint.setColorFilter(this.mColorFilter != null ? this.mColorFilter : this.mTintFilter);
            int saveCount = canvas.save();
            canvas.translate((float) bounds.left, (float) bounds.top);
            if (needMirroring()) {
                canvas.translate((float) bounds.width(), 0.0f);
                canvas.scale(-1.0f, 1.0f);
            }
            onDraw(canvas, bounds.width(), bounds.height(), this.mPaint);
            canvas.restoreToCount(saveCount);
        }
    }
}
