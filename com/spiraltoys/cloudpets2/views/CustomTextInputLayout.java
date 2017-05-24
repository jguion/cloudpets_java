package com.spiraltoys.cloudpets2.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import java.lang.reflect.Field;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class CustomTextInputLayout extends TextInputLayout {
    private CharSequence mHint;
    private boolean mIsHintSet;

    public CustomTextInputLayout(Context context) {
        this(context, null);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addView(View child, int index, LayoutParams params) {
        ReflectiveOperationException e;
        if (child instanceof EditText) {
            this.mHint = ((EditText) child).getHint();
        }
        super.addView(child, index, params);
        Typeface typeface = TypefaceUtils.load(getContext().getAssets(), "fonts/merge_light.otf");
        try {
            getEditText().setTypeface(typeface);
            Field collapsingTextHelperField = getClass().getSuperclass().getDeclaredField("mCollapsingTextHelper");
            collapsingTextHelperField.setAccessible(true);
            Object collapsingTextHelper = collapsingTextHelperField.get(this);
            Field textPaintField = collapsingTextHelper.getClass().getDeclaredField("mTextPaint");
            textPaintField.setAccessible(true);
            ((TextPaint) textPaintField.get(collapsingTextHelper)).setTypeface(typeface);
            return;
        } catch (NoSuchFieldException e2) {
            e = e2;
        } catch (IllegalAccessException e3) {
            e = e3;
        }
        e.printStackTrace();
    }

    public void removeView(View view) {
        ReflectiveOperationException e;
        super.removeView(view);
        try {
            if (view instanceof EditText) {
                Field editTextField = getClass().getSuperclass().getDeclaredField("mEditText");
                editTextField.setAccessible(true);
                editTextField.set(this, null);
            }
        } catch (NoSuchFieldException e2) {
            e = e2;
            e.printStackTrace();
        } catch (IllegalAccessException e3) {
            e = e3;
            e.printStackTrace();
        }
    }

    public CharSequence getHint() {
        ReflectiveOperationException e;
        try {
            Field hintField = getClass().getSuperclass().getDeclaredField("mHint");
            hintField.setAccessible(true);
            return (CharSequence) hintField.get(this);
        } catch (NoSuchFieldException e2) {
            e = e2;
        } catch (IllegalAccessException e3) {
            e = e3;
        }
        e.printStackTrace();
        return null;
    }

    public void setTextWithoutAnimation(String textToSet) {
        CharSequence hint = getHint();
        EditText input = getEditText();
        removeView(input);
        setHint(null);
        input.setText(textToSet);
        input.setHint(hint);
        addView(input);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mIsHintSet && ViewCompat.isLaidOut(this)) {
            setHint(null);
            CharSequence currentEditTextHint = getEditText().getHint();
            if (currentEditTextHint != null && currentEditTextHint.length() > 0) {
                this.mHint = currentEditTextHint;
            }
            setHint(this.mHint);
            this.mIsHintSet = true;
        }
    }
}
