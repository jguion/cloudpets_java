package com.spiraltoys.cloudpets2.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.spiraltoys.cloudpets2.free.R;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class ProfilePlaceholderDrawable extends Drawable {
    private static final String FONT_LARGE = "fonts/merge_light.otf";
    private static final String FONT_SMALL = "fonts/merge_regular.otf";
    private final Drawable mBackgroundDrawable;
    private final Paint mPaint;
    private final String mText;
    private final Rect mTextBounds = new Rect();

    public static ProfilePlaceholderDrawable getFloatingProfileSwitcherInstance(Context context, String text) {
        return new ProfilePlaceholderDrawable(context.getResources().getDrawable(R.drawable.profile_photo_placeholder_primary), text, TypefaceUtils.load(context.getAssets(), FONT_SMALL), context.getResources().getColor(R.color.primary), context.getResources().getDimensionPixelSize(R.dimen.profile_photo_placeholder_text_size_list_item));
    }

    public static ProfilePlaceholderDrawable getFloatingProfileSwitcherLargeInstance(Context context, String text) {
        return new ProfilePlaceholderDrawable(context.getResources().getDrawable(R.drawable.profile_photo_placeholder_primary), text, TypefaceUtils.load(context.getAssets(), FONT_LARGE), context.getResources().getColor(R.color.primary), context.getResources().getDimensionPixelSize(R.dimen.profile_photo_placeholder_text_size_large));
    }

    public static ProfilePlaceholderDrawable getListItemInstance(Context context, String text) {
        return new ProfilePlaceholderDrawable(context.getResources().getDrawable(R.drawable.profile_photo_placeholder_default), text, TypefaceUtils.load(context.getAssets(), FONT_SMALL), context.getResources().getColor(R.color.profile_photo_border_dark), context.getResources().getDimensionPixelSize(R.dimen.profile_photo_placeholder_text_size_list_item));
    }

    public static ProfilePlaceholderDrawable getListItemInstanceLightBorder(Context context, String text) {
        return new ProfilePlaceholderDrawable(context.getResources().getDrawable(R.drawable.profile_photo_placeholder_light), text, TypefaceUtils.load(context.getAssets(), FONT_SMALL), context.getResources().getColor(R.color.profile_photo_border_dark), context.getResources().getDimensionPixelSize(R.dimen.profile_photo_placeholder_text_size_list_item));
    }

    public static ProfilePlaceholderDrawable getLargeInstanceAccentBorder(Context context, String text) {
        return new ProfilePlaceholderDrawable(context.getResources().getDrawable(R.drawable.profile_photo_placeholder_accent), text, TypefaceUtils.load(context.getAssets(), FONT_LARGE), context.getResources().getColor(R.color.profile_photo_border_dark), context.getResources().getDimensionPixelSize(R.dimen.profile_photo_placeholder_text_size_large));
    }

    public static ProfilePlaceholderDrawable getLargeInstance(Context context, String text) {
        return new ProfilePlaceholderDrawable(context.getResources().getDrawable(R.drawable.profile_photo_placeholder_default), text, TypefaceUtils.load(context.getAssets(), FONT_LARGE), context.getResources().getColor(R.color.profile_photo_border_dark), context.getResources().getDimensionPixelSize(R.dimen.profile_photo_placeholder_text_size_large));
    }

    public ProfilePlaceholderDrawable(Drawable backgroundDrawable, String text, Typeface typeface, int textColor, int textSize) {
        this.mBackgroundDrawable = backgroundDrawable;
        this.mText = text;
        this.mPaint = new Paint();
        this.mPaint.setColor(textColor);
        this.mPaint.setTextSize((float) textSize);
        Paint paint = this.mPaint;
        if (typeface == null) {
            typeface = Typeface.defaultFromStyle(1);
        }
        paint.setTypeface(typeface);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Style.FILL);
        this.mPaint.setTextAlign(Align.CENTER);
        this.mPaint.getTextBounds(text, 0, text.length(), this.mTextBounds);
    }

    public void draw(Canvas canvas) {
        canvas.save();
        if (this.mBackgroundDrawable != null) {
            this.mBackgroundDrawable.draw(canvas);
        }
        canvas.restore();
        canvas.drawText(this.mText, (float) getBounds().centerX(), ((float) getBounds().centerY()) - this.mTextBounds.exactCenterY(), this.mPaint);
    }

    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        this.mBackgroundDrawable.setBounds(left, top, right, bottom);
    }

    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }

    public int getOpacity() {
        return -3;
    }

    public int getIntrinsicHeight() {
        return -1;
    }

    public int getIntrinsicWidth() {
        return -1;
    }
}
