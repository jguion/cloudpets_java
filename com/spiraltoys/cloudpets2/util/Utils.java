package com.spiraltoys.cloudpets2.util;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.afollestad.materialdialogs.AlertDialogWrapper.Builder;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.free.R;
import hugo.weaving.DebugLog;
import java.util.concurrent.TimeUnit;

public abstract class Utils {
    private static final int COUNT_BADGE_ANIMATION_DURATION_MS = 500;
    private static final float MIN_TOY_VOLUME = 0.025f;
    private static final float TOY_VOLUME_EXPONENT_SCALE = 2.1f;

    public static boolean isValidEmailAddress(CharSequence text) {
        return !TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches();
    }

    public static int getStyledResourceIdForAttribute(Context context, int attribute) {
        TypedArray styledAttributes = context.obtainStyledAttributes(new int[]{attribute});
        int resourceId = styledAttributes.getResourceId(0, 0);
        styledAttributes.recycle();
        return resourceId;
    }

    public static String getStyledStringForAttribute(Context context, int attribute) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attribute, typedValue, true);
        return typedValue.string.toString();
    }

    public static int dpToPx(Context context, float dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }

    public static void crossFadeImageView(ImageView imageView, int destImageResourceId, int durationMilliseconds) {
        Drawable[] drawables = new Drawable[]{imageView.getDrawable(), imageView.getContext().getResources().getDrawable(destImageResourceId)};
        if (drawables[1] instanceof AnimationDrawable) {
            drawables[1].start();
        }
        crossFadeImageView(imageView, drawables, durationMilliseconds);
    }

    public static void crossFadeImageView(ImageView imageView, Bitmap bitmap, int durationMilliseconds) {
        crossFadeImageView(imageView, new Drawable[]{imageView.getDrawable(), new BitmapDrawable(imageView.getResources(), bitmap)}, durationMilliseconds);
    }

    private static void crossFadeImageView(ImageView imageView, Drawable[] drawables, int durationMilliseconds) {
        TransitionDrawable crossfader = new TransitionDrawable(drawables);
        crossfader.setCrossFadeEnabled(true);
        imageView.setImageDrawable(crossfader);
        crossfader.startTransition(durationMilliseconds);
    }

    public static void crossFadeButtonBackground(Button button, int destImageResourceId, int durationMilliseconds) {
        TransitionDrawable crossfader = new TransitionDrawable(new Drawable[]{button.getBackground(), button.getContext().getResources().getDrawable(destImageResourceId)});
        crossfader.setCrossFadeEnabled(true);
        button.setBackground(crossfader);
        crossfader.startTransition(durationMilliseconds);
    }

    public static String getLocalizedErrorMessage(Context context, ParseException ex) {
        return (ex.getLocalizedMessage() == null || ex.getLocalizedMessage().trim().isEmpty()) ? context.getString(R.string.error_parse_generic) : ex.getLocalizedMessage();
    }

    public static Dialog showHintDialog(Context context, String message) {
        return new Builder(context).setMessage((CharSequence) message).setPositiveButton((int) R.string.btn_okay, null).show();
    }

    public static void showHintDialog(Context context, int messageResourceId) {
        showHintDialog(context, context.getString(messageResourceId));
    }

    public static Dialog showErrorDialog(Context context, String message) {
        return new Builder(context).setTitle((int) R.string.title_error).setMessage((CharSequence) message).setPositiveButton((int) R.string.btn_continue, null).show();
    }

    public static void showErrorDialog(Context context, int messageResourceId) {
        showErrorDialog(context, context.getString(messageResourceId));
    }

    public static void showErrorDialogWithTitle(Context context, int messageResourceId, int titleResourceId) {
        showErrorDialogWithTitle(context, context.getString(messageResourceId), context.getString(titleResourceId));
    }

    private static Dialog showErrorDialogWithTitle(Context context, String message, String title) {
        return new Builder(context).setTitle((CharSequence) title).setMessage((CharSequence) message).setPositiveButton((int) R.string.btn_continue, null).show();
    }

    public static void highlightCloudPetsText(TextView textView) {
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(textView.getResources().getColor(R.color.primary));
        String text = textView.getText().toString();
        String textToHighlight = textView.getResources().getString(R.string.brand_cloud_pets);
        int highlightTextIndex = text.indexOf(textToHighlight);
        if (highlightTextIndex >= 0) {
            SpannableStringBuilder sBuilder = new SpannableStringBuilder();
            sBuilder.append(text);
            sBuilder.setSpan(colorSpan, highlightTextIndex, textToHighlight.length() + highlightTextIndex, 33);
            textView.setText(sBuilder, BufferType.SPANNABLE);
        }
    }

    public static void animateBadgeWithCount(TextView badge, int count) {
        if (badge.getVisibility() != 0) {
            badge.setAlpha(0.0f);
        }
        badge.setVisibility(count > 0 ? 0 : 8);
        badge.setText(String.format("%d", new Object[]{Integer.valueOf(count)}));
        badge.animate().alpha(1.0f).setDuration(500).start();
    }

    public static void showLogoutConfirmationDialog(Context context) {
        new Builder(context).setTitle((int) R.string.title_log_out).setMessage((int) R.string.message_log_out_confirmation).setPositiveButton((int) R.string.btn_okay, new 1(context)).setNegativeButton((int) R.string.btn_cancel, null).show();
    }

    public static boolean isBluetoothEnabled() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return mBluetoothAdapter != null && mBluetoothAdapter.isEnabled();
    }

    @DebugLog
    public static boolean isNetworkConnectionAvailable(Context context) {
        NetworkInfo activeNetwork = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static String formatAudioTime(long audioTime) {
        return String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(audioTime)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(audioTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(audioTime)))});
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int scale) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = 1 / Math.max(1, scale);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static boolean isColliding(View v1, View v2) {
        return isColliding(v1, v2, 0, 0);
    }

    public static boolean isColliding(View v1, View v2, int allowedOverlapX, int allowedOverlapY) {
        Rect r1 = new Rect();
        Rect r2 = new Rect();
        v1.getHitRect(r1);
        v2.getHitRect(r2);
        return r1.intersects(r2.left + allowedOverlapX, r2.top + allowedOverlapY, r2.right - allowedOverlapX, r2.bottom - allowedOverlapY);
    }

    public static void deleteAllCachedToyRecordingsAsync(Context context) {
        new Thread(new 2(context)).run();
    }

    public static double getRelativeToyVolume(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        double expVolume = Math.pow(((double) audioManager.getStreamVolume(3)) / ((double) audioManager.getStreamMaxVolume(3)), 2.0999999046325684d);
        return (expVolume + 0.02500000037252903d) - (expVolume * 0.02500000037252903d);
    }
}
