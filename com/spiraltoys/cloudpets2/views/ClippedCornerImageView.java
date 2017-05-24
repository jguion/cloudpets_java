package com.spiraltoys.cloudpets2.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ClippedCornerImageView extends ImageView {
    public ClippedCornerImageView(Context context) {
        super(context);
    }

    public ClippedCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClippedCornerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(getRoundedCornerBitmap(getContext(), ((BitmapDrawable) getDrawable()).getBitmap().copy(Config.ARGB_8888, true), 8, getWidth(), getHeight(), false, false, false, false), 0.0f, 0.0f, null);
    }

    public static Bitmap getRoundedCornerBitmap(Context context, Bitmap input, int pixels, int w, int h, boolean squareTL, boolean squareTR, boolean squareBL, boolean squareBR) {
        Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        float densityMultiplier = context.getResources().getDisplayMetrics().density;
        Paint paint = new Paint();
        RectF rectF = new RectF(new Rect(0, 0, w, h));
        float roundPx = ((float) pixels) * densityMultiplier;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        if (squareTL) {
            canvas.drawRect(0.0f, 0.0f, (float) (w / 2), (float) (h / 2), paint);
        }
        if (squareTR) {
            canvas.drawRect((float) (w / 2), 0.0f, (float) w, (float) (h / 2), paint);
        }
        if (squareBL) {
            canvas.drawRect(0.0f, (float) (h / 2), (float) (w / 2), (float) h, paint);
        }
        if (squareBR) {
            canvas.drawRect((float) (w / 2), (float) (h / 2), (float) w, (float) h, paint);
        }
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(input, 0.0f, 0.0f, paint);
        return output;
    }
}
