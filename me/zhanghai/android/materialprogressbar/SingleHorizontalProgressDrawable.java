package me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import me.zhanghai.android.materialprogressbar.internal.ThemeUtils;

class SingleHorizontalProgressDrawable extends ProgressDrawableBase {
    private static final int LEVEL_MAX = 10000;
    private static final float PADDED_INTRINSIC_HEIGHT_DP = 16.0f;
    private static final float PROGRESS_INTRINSIC_HEIGHT_DP = 3.2f;
    private static final RectF RECT_BOUND = new RectF(-180.0f, -1.0f, 180.0f, 1.0f);
    private static final RectF RECT_PADDED_BOUND = new RectF(-180.0f, -5.0f, 180.0f, 5.0f);
    private int mPaddedIntrinsicHeight;
    private int mProgressIntrinsicHeight;
    private boolean mShowTrack = true;
    private float mTrackAlpha;

    public SingleHorizontalProgressDrawable(Context context) {
        super(context);
        float density = context.getResources().getDisplayMetrics().density;
        this.mProgressIntrinsicHeight = Math.round(PROGRESS_INTRINSIC_HEIGHT_DP * density);
        this.mPaddedIntrinsicHeight = Math.round(PADDED_INTRINSIC_HEIGHT_DP * density);
        this.mTrackAlpha = ThemeUtils.getFloatFromAttrRes(16842803, context);
    }

    public boolean getShowTrack() {
        return this.mShowTrack;
    }

    public void setShowTrack(boolean showTrack) {
        if (this.mShowTrack != showTrack) {
            this.mShowTrack = showTrack;
            invalidateSelf();
        }
    }

    public int getIntrinsicHeight() {
        return this.mUseIntrinsicPadding ? this.mPaddedIntrinsicHeight : this.mProgressIntrinsicHeight;
    }

    public int getOpacity() {
        if (this.mAlpha == 0) {
            return -2;
        }
        if (this.mAlpha != 255 || (this.mShowTrack && this.mTrackAlpha != 1.0f)) {
            return -3;
        }
        return -1;
    }

    protected boolean onLevelChange(int level) {
        invalidateSelf();
        return true;
    }

    protected void onPreparePaint(Paint paint) {
        paint.setStyle(Style.FILL);
    }

    protected void onDraw(Canvas canvas, int width, int height, Paint paint) {
        if (this.mUseIntrinsicPadding) {
            canvas.scale(((float) width) / RECT_PADDED_BOUND.width(), ((float) height) / RECT_PADDED_BOUND.height());
            canvas.translate(RECT_PADDED_BOUND.width() / 2.0f, RECT_PADDED_BOUND.height() / 2.0f);
        } else {
            canvas.scale(((float) width) / RECT_BOUND.width(), ((float) height) / RECT_BOUND.height());
            canvas.translate(RECT_BOUND.width() / 2.0f, RECT_BOUND.height() / 2.0f);
        }
        if (this.mShowTrack) {
            paint.setAlpha(Math.round(((float) this.mAlpha) * this.mTrackAlpha));
            drawTrackRect(canvas, paint);
            paint.setAlpha(this.mAlpha);
        }
        drawProgressRect(canvas, paint);
    }

    private static void drawTrackRect(Canvas canvas, Paint paint) {
        canvas.drawRect(RECT_BOUND, paint);
    }

    private void drawProgressRect(Canvas canvas, Paint paint) {
        int level = getLevel();
        if (level != 0) {
            int saveCount = canvas.save();
            canvas.scale(((float) level) / 10000.0f, 1.0f, RECT_BOUND.left, 0.0f);
            canvas.drawRect(RECT_BOUND, paint);
            canvas.restoreToCount(saveCount);
        }
    }
}
