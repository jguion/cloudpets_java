package com.spiraltoys.cloudpets2.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Build.VERSION;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.spiraltoys.cloudpets2.free.R;

public class RoundedRectTransformation implements Transformation<Bitmap> {
    private static final Paint CIRCLE_CROP_BITMAP_PAINT = new Paint(7);
    private static final int CIRCLE_CROP_PAINT_FLAGS = 7;
    private static final Paint CIRCLE_CROP_SHAPE_PAINT = new Paint(7);
    private static final int NUMBER_OF_SCRIM_GRADIENT_STOPS = 10;
    private static final int PAINT_FLAGS = 6;
    private static final int SCRIM_BASE_COLOR = 1140850688;
    private BitmapPool mBitmapPool;
    private final float mBorderWidth;
    private final float mCornerRadius;
    private int mInnerBorderColor;
    private Paint mInnerBorderPaint = new Paint();
    private int mOuterBorderColor;
    private Paint mOuterBorderPaint;
    private final boolean mUseScrim;

    static {
        CIRCLE_CROP_BITMAP_PAINT.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
    }

    public RoundedRectTransformation(Context c, BitmapPool pool, int outerBorderColor, int innerBorderColor, float cornerRadius, boolean useScrim) {
        this.mOuterBorderColor = outerBorderColor;
        this.mInnerBorderColor = innerBorderColor;
        this.mCornerRadius = cornerRadius;
        this.mBitmapPool = pool;
        this.mBorderWidth = c.getResources().getDimension(R.dimen.default_image_border_width);
        this.mInnerBorderPaint.setStyle(Style.STROKE);
        this.mInnerBorderPaint.setAntiAlias(true);
        this.mInnerBorderPaint.setColor(innerBorderColor);
        this.mInnerBorderPaint.setStrokeWidth(this.mBorderWidth * 2.0f);
        this.mOuterBorderPaint = new Paint();
        this.mOuterBorderPaint.setStyle(Style.STROKE);
        this.mOuterBorderPaint.setAntiAlias(true);
        this.mOuterBorderPaint.setColor(outerBorderColor);
        this.mOuterBorderPaint.setStrokeWidth(this.mBorderWidth);
        this.mUseScrim = useScrim;
    }

    public Resource<Bitmap> transform(Resource<Bitmap> resource, int destWidth, int destHeight) {
        float scale;
        Bitmap source = (Bitmap) resource.get();
        float radius = ((float) Math.min(destWidth, destHeight)) / 2.0f;
        float dx = 0.0f;
        float dy = 0.0f;
        Matrix m = new Matrix();
        if (source.getWidth() * destHeight > source.getHeight() * destWidth) {
            scale = ((float) destHeight) / ((float) source.getHeight());
            dx = (((float) destWidth) - (((float) source.getWidth()) * scale)) * 0.5f;
        } else {
            scale = ((float) destWidth) / ((float) source.getWidth());
            dy = (((float) destHeight) - (((float) source.getHeight()) * scale)) * 0.5f;
        }
        m.setScale(scale, scale);
        m.postTranslate((float) ((int) (0.5f + dx)), (float) ((int) (0.5f + dy)));
        Bitmap result = this.mBitmapPool.get(destWidth, destHeight, getSafeConfig(source));
        if (result == null) {
            result = Bitmap.createBitmap(destWidth, destHeight, Config.ARGB_8888);
        }
        setAlphaIfAvailable(result, true);
        Canvas canvas = new Canvas(result);
        canvas.drawRoundRect(new RectF(this.mBorderWidth, this.mBorderWidth, ((float) destWidth) - this.mBorderWidth, ((float) destHeight) - this.mBorderWidth), this.mCornerRadius, this.mCornerRadius, CIRCLE_CROP_SHAPE_PAINT);
        canvas.drawBitmap(source, m, CIRCLE_CROP_BITMAP_PAINT);
        if (this.mUseScrim) {
            canvas.drawRoundRect(new RectF(this.mBorderWidth, this.mBorderWidth, ((float) destWidth) - this.mBorderWidth, ((float) destHeight) - this.mBorderWidth), this.mCornerRadius, this.mCornerRadius, getScrimPaint(destHeight));
        }
        float halfOuterBorderWidth = this.mOuterBorderPaint.getStrokeWidth() / 2.0f;
        float halfInnerBorderWidth = this.mInnerBorderPaint.getStrokeWidth() / 2.0f;
        canvas.drawRoundRect(new RectF(halfInnerBorderWidth, halfInnerBorderWidth, ((float) destWidth) - halfInnerBorderWidth, ((float) destHeight) - halfInnerBorderWidth), this.mCornerRadius, this.mCornerRadius, this.mInnerBorderPaint);
        canvas.drawRoundRect(new RectF(halfOuterBorderWidth, halfOuterBorderWidth, ((float) destWidth) - halfOuterBorderWidth, ((float) destHeight) - halfOuterBorderWidth), this.mCornerRadius, this.mCornerRadius, this.mOuterBorderPaint);
        return BitmapResource.obtain(result, this.mBitmapPool);
    }

    private Paint getScrimPaint(int height) {
        int[] stopColors = new int[10];
        int red = Color.red(SCRIM_BASE_COLOR);
        int green = Color.green(SCRIM_BASE_COLOR);
        int blue = Color.blue(SCRIM_BASE_COLOR);
        int alpha = Color.alpha(SCRIM_BASE_COLOR);
        for (int i = 0; i < 10; i++) {
            stopColors[i] = Color.argb((int) (((float) alpha) * ((float) Math.min(1.0d, Math.max(0.0d, Math.pow((double) ((((float) i) * 1.0f) / 9.0f), 3.0d))))), red, green, blue);
        }
        Paint paint = new Paint(7);
        paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, (float) height, stopColors, null, TileMode.CLAMP));
        return paint;
    }

    private static void setAlphaIfAvailable(Bitmap bitmap, boolean hasAlpha) {
        if (VERSION.SDK_INT >= 12 && bitmap != null) {
            bitmap.setHasAlpha(hasAlpha);
        }
    }

    private static Config getSafeConfig(Bitmap bitmap) {
        return Config.ARGB_8888;
    }

    public String getId() {
        return "DoubleBorderCropCircleTransformation(" + this.mOuterBorderColor + ", " + this.mInnerBorderColor + ", " + this.mUseScrim + ")";
    }
}
