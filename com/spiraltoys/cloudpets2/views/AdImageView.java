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

public class AdImageView extends ImageView {
    private static final Config BITMAP_CONFIG = Config.ARGB_8888;
    private static final int COLOR_DRAWABLE_DIMENSION = 2;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final boolean DEFAULT_BORDER_OVERLAY = false;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final float RADIUS_WIDTH_MULTIPLIER = 0.65f;
    private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;
    private Bitmap mBitmap;
    private int mBitmapHeight;
    private final Paint mBitmapPaint;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBorderColor;
    private boolean mBorderOverlay;
    private final Paint mBorderPaint;
    private float mBorderRadius;
    private final RectF mBorderRect;
    private int mBorderWidth;
    private ColorFilter mColorFilter;
    private float mDrawableRadius;
    private final RectF mDrawableRect;
    private boolean mReady;
    private boolean mSetupPending;
    private final Matrix mShaderMatrix;

    public AdImageView(Context context) {
        super(context);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mBorderColor = -16777216;
        this.mBorderWidth = 0;
        init();
    }

    public AdImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mBorderColor = -16777216;
        this.mBorderWidth = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, R$styleable.AdImageView, defStyle, 0);
        this.mBorderWidth = a.getDimensionPixelSize(0, 0);
        this.mBorderColor = a.getColor(1, -16777216);
        this.mBorderOverlay = a.getBoolean(2, false);
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

    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            canvas.drawCircle((float) (getWidth() / 2), ((float) getHeight()) - this.mDrawableRadius, this.mDrawableRadius, this.mBitmapPaint);
            if (this.mBorderWidth != 0) {
                canvas.drawCircle((float) (getWidth() / 2), ((float) getHeight()) - this.mBorderRadius, this.mBorderRadius, this.mBorderPaint);
            }
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public void setBorderColor(int borderColor) {
        if (borderColor != this.mBorderColor) {
            this.mBorderColor = borderColor;
            this.mBorderPaint.setColor(this.mBorderColor);
            invalidate();
        }
    }

    public void setBorderColorResource(@ColorRes int borderColorRes) {
        setBorderColor(getContext().getResources().getColor(borderColorRes));
    }

    public int getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        if (borderWidth != this.mBorderWidth) {
            this.mBorderWidth = borderWidth;
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
            this.mBorderPaint.setStyle(Style.STROKE);
            this.mBorderPaint.setAntiAlias(true);
            this.mBorderPaint.setColor(this.mBorderColor);
            this.mBorderPaint.setStrokeWidth((float) this.mBorderWidth);
            this.mBitmapHeight = this.mBitmap.getHeight();
            this.mBitmapWidth = this.mBitmap.getWidth();
            this.mBorderRect.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.mBorderRadius = (this.mBorderRect.width() - ((float) this.mBorderWidth)) * RADIUS_WIDTH_MULTIPLIER;
            this.mDrawableRect.set(this.mBorderRect);
            if (!this.mBorderOverlay) {
                this.mDrawableRect.inset((float) this.mBorderWidth, (float) this.mBorderWidth);
            }
            this.mDrawableRadius = this.mDrawableRect.width() * RADIUS_WIDTH_MULTIPLIER;
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
