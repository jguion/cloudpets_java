package com.spiraltoys.cloudpets2.views;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

public class CustomAppCompatSwitch extends SwitchCompat {
    public CustomAppCompatSwitch(Context context) {
        super(context);
    }

    public CustomAppCompatSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAppCompatSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isShown() {
        View current = this;
        while (current.getVisibility() == 0) {
            ViewParent parent = current.getParent();
            if (!(parent instanceof View)) {
                return true;
            }
            current = (View) parent;
            if (current == null) {
                return false;
            }
        }
        return false;
    }
}
