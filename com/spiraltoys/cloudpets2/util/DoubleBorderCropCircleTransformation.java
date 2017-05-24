package com.spiraltoys.cloudpets2.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Build.VERSION;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.spiraltoys.cloudpets2.free.R;

public class DoubleBorderCropCircleTransformation implements Transformation<Bitmap> {
    private static final Paint CIRCLE_CROP_BITMAP_PAINT = new Paint(7);
    private static final int CIRCLE_CROP_PAINT_FLAGS = 7;
    private static final Paint CIRCLE_CROP_SHAPE_PAINT = new Paint(7);
    private static final int PAINT_FLAGS = 6;
    private BitmapPool mBitmapPool;
    private final int mBorderWidth;
    private int mInnerBorderColor;
    private Paint mInnerBorderPaint = new Paint();
    private int mOuterBorderColor;
    private Paint mOuterBorderPaint;

    static {
        CIRCLE_CROP_BITMAP_PAINT.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
    }

    public DoubleBorderCropCircleTransformation(Context c, BitmapPool pool, int outerBorderColor, int innerBorderColor) {
        this.mOuterBorderColor = outerBorderColor;
        this.mInnerBorderColor = innerBorderColor;
        this.mBitmapPool = pool;
        this.mBorderWidth = c.getResources().getDimensionPixelSize(R.dimen.default_image_border_width);
        this.mInnerBorderPaint.setStyle(Style.STROKE);
        this.mInnerBorderPaint.setAntiAlias(true);
        this.mInnerBorderPaint.setColor(innerBorderColor);
        this.mInnerBorderPaint.setStrokeWidth((float) this.mBorderWidth);
        this.mOuterBorderPaint = new Paint();
        this.mOuterBorderPaint.setStyle(Style.STROKE);
        this.mOuterBorderPaint.setAntiAlias(true);
        this.mOuterBorderPaint.setColor(outerBorderColor);
        this.mOuterBorderPaint.setStrokeWidth((float) this.mBorderWidth);
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
        canvas.drawCircle(radius, radius, radius, CIRCLE_CROP_SHAPE_PAINT);
        canvas.drawBitmap(source, m, CIRCLE_CROP_BITMAP_PAINT);
        canvas.drawCircle(radius, radius, radius - (((float) this.mBorderWidth) * 1.25f), this.mInnerBorderPaint);
        canvas.drawCircle(radius, radius, radius - (((float) this.mBorderWidth) / 2.0f), this.mOuterBorderPaint);
        return BitmapResource.obtain(result, this.mBitmapPool);
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
        return "DoubleBorderCropCircleTransformation(" + this.mOuterBorderColor + ", " + this.mInnerBorderColor + ")";
    }
}
