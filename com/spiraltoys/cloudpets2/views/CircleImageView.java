package com.spiraltoys.cloudpets2.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.spiraltoys.cloudpets2.R$styleable;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.Utils;

public class CircleImageView extends ImageView {
    private static final Config BITMAP_CONFIG = Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 2;
    private static final int DEFAULT_BACKGROUND_COLOR = 0;
    private static final boolean DEFAULT_BORDER_OVERLAY = false;
    private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;
    private static final float SHADOW_OFFSET_Y_DP = 0.0f;
    private static final float SHADOW_RADIUS_DP = 2.0f;
    private int mBackgroundColor;
    private final Paint mBackgroundPaint;
    private Bitmap mBitmap;
    private int mBitmapHeight;
    private final Paint mBitmapPaint;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private boolean mBorderOverlay;
    private final RectF mBorderRect;
    private ColorFilter mColorFilter;
    private float mDrawableRadius;
    private final RectF mDrawableRect;
    private int mInnerBorderColor;
    private final Paint mInnerBorderPaint;
    private float mInnerBorderRadius;
    private int mInnerBorderWidth;
    private int mOuterBorderColor;
    private final Paint mOuterBorderPaint;
    private float mOuterBorderRadius;
    private int mOuterBorderWidth;
    private boolean mReady;
    private boolean mSetupPending;
    private final Matrix mShaderMatrix;

    public CircleImageView(Context context) {
        super(context);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mOuterBorderPaint = new Paint();
        this.mInnerBorderPaint = new Paint();
        this.mBackgroundPaint = new Paint();
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mOuterBorderPaint = new Paint();
        this.mInnerBorderPaint = new Paint();
        this.mBackgroundPaint = new Paint();
        TypedArray a = context.obtainStyledAttributes(attrs, R$styleable.CircleImageView, defStyle, 0);
        this.mOuterBorderWidth = a.getDimensionPixelSize(0, getResources().getDimensionPixelSize(R.dimen.profile_photo_outer_border_width));
        this.mOuterBorderColor = a.getColor(1, getResources().getColor(R.color.profile_photo_border_dark));
        this.mInnerBorderWidth = a.getDimensionPixelSize(2, getResources().getDimensionPixelSize(R.dimen.profile_photo_inner_border_width));
        this.mInnerBorderColor = a.getColor(3, -1);
        this.mBackgroundColor = a.getColor(5, 0);
        this.mBorderOverlay = a.getBoolean(4, false);
        a.recycle();
        init();
    }

    private void init() {
        super.setScaleType(SCALE_TYPE);
        this.mReady = true;
        if (this.mSetupPending) {
            setup();
            this.mSetupPending = false;
        }
    }

    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", new Object[]{scaleType}));
        }
    }

    public void setAdjustViewBounds(boolean adjustViewBounds) {
        if (adjustViewBounds) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            if (this.mBackgroundColor != 0) {
                canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.mDrawableRadius, this.mBackgroundPaint);
            }
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.mDrawableRadius, this.mBitmapPaint);
            if (this.mInnerBorderWidth != 0) {
                canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.mInnerBorderRadius, this.mInnerBorderPaint);
            }
            if (this.mOuterBorderWidth != 0) {
                canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.mOuterBorderRadius, this.mOuterBorderPaint);
            }
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    public int getBorderColor() {
        return this.mOuterBorderColor;
    }

    public void setBorderColor(int borderColor) {
        if (borderColor != this.mOuterBorderColor) {
            this.mOuterBorderColor = borderColor;
            this.mOuterBorderPaint.setColor(this.mOuterBorderColor);
            this.mInnerBorderPaint.setColor(this.mInnerBorderColor);
            invalidate();
        }
    }

    public void setBorderColorResource(@ColorRes int borderColorRes) {
        setBorderColor(getContext().getResources().getColor(borderColorRes));
    }

    public int getBorderWidth() {
        return this.mOuterBorderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        if (borderWidth != this.mOuterBorderWidth) {
            this.mOuterBorderWidth = borderWidth;
            setup();
        }
    }

    public boolean isBorderOverlay() {
        return this.mBorderOverlay;
    }

    public void setBorderOverlay(boolean borderOverlay) {
        if (borderOverlay != this.mBorderOverlay) {
            this.mBorderOverlay = borderOverlay;
            setup();
        }
    }

    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        this.mBitmap = bm;
        setup();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        this.mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    public void setColorFilter(ColorFilter cf) {
        if (cf != this.mColorFilter) {
            this.mColorFilter = cf;
            this.mBitmapPaint.setColorFilter(this.mColorFilter);
            invalidate();
        }
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmap;
            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(2, 2, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void setup() {
        if (!this.mReady) {
            this.mSetupPending = true;
        } else if (this.mBitmap != null) {
            this.mBitmapShader = new BitmapShader(this.mBitmap, TileMode.CLAMP, TileMode.CLAMP);
            this.mBitmapPaint.setAntiAlias(true);
            this.mBitmapPaint.setShader(this.mBitmapShader);
            this.mBackgroundPaint.setStyle(Style.FILL);
            this.mBackgroundPaint.setAntiAlias(true);
            this.mBackgroundPaint.setColor(this.mBackgroundColor);
            this.mOuterBorderPaint.setStyle(Style.STROKE);
            this.mOuterBorderPaint.setAntiAlias(true);
            this.mOuterBorderPaint.setColor(this.mOuterBorderColor);
            this.mOuterBorderPaint.setStrokeWidth((float) this.mOuterBorderWidth);
            this.mInnerBorderPaint.setStyle(Style.STROKE);
            this.mInnerBorderPaint.setAntiAlias(true);
            this.mInnerBorderPaint.setColor(this.mInnerBorderColor);
            this.mInnerBorderPaint.setStrokeWidth((float) this.mInnerBorderWidth);
            this.mInnerBorderPaint.setShadowLayer((float) Utils.dpToPx(getContext(), SHADOW_RADIUS_DP), 0.0f, (float) Utils.dpToPx(getContext(), 0.0f), getResources().getColor(R.color.profile_photo_shadow));
            setLayerType(1, this.mInnerBorderPaint);
            this.mBitmapHeight = this.mBitmap.getHeight();
            this.mBitmapWidth = this.mBitmap.getWidth();
            this.mBorderRect.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.mOuterBorderRadius = Math.min((this.mBorderRect.height() - ((float) this.mOuterBorderWidth)) / SHADOW_RADIUS_DP, (this.mBorderRect.width() - ((float) this.mOuterBorderWidth)) / SHADOW_RADIUS_DP);
            this.mInnerBorderRadius = this.mOuterBorderRadius - ((float) this.mInnerBorderWidth);
            this.mDrawableRect.set(this.mBorderRect);
            if (!this.mBorderOverlay) {
                this.mDrawableRect.inset((float) (this.mOuterBorderWidth + this.mInnerBorderWidth), (float) (this.mOuterBorderWidth + this.mInnerBorderWidth));
            }
            this.mDrawableRadius = Math.min(this.mDrawableRect.height() / SHADOW_RADIUS_DP, this.mDrawableRect.width() / SHADOW_RADIUS_DP);
            updateShaderMatrix();
            invalidate();
        }
    }

    private void updateShaderMatrix() {
        float scale;
        float dx = 0.0f;
        float dy = 0.0f;
        this.mShaderMatrix.set(null);
        if (((float) this.mBitmapWidth) * this.mDrawableRect.height() > this.mDrawableRect.width() * ((float) this.mBitmapHeight)) {
            scale = this.mDrawableRect.height() / ((float) this.mBitmapHeight);
            dx = (this.mDrawableRect.width() - (((float) this.mBitmapWidth) * scale)) * 0.5f;
        } else {
            scale = this.mDrawableRect.width() / ((float) this.mBitmapWidth);
            dy = (this.mDrawableRect.height() - (((float) this.mBitmapHeight) * scale)) * 0.5f;
        }
        this.mShaderMatrix.setScale(scale, scale);
        this.mShaderMatrix.postTranslate(((float) ((int) (dx + 0.5f))) + this.mDrawableRect.left, ((float) ((int) (dy + 0.5f))) + this.mDrawableRect.top);
        this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
    }
}
