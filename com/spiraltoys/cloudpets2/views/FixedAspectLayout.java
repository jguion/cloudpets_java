package com.spiraltoys.cloudpets2.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.google.common.primitives.Ints;
import com.spiraltoys.cloudpets2.R$styleable;

public class FixedAspectLayout extends FrameLayout {
    private float aspect;

    public FixedAspectLayout(Context context) {
        this(context, null);
    }

    public FixedAspectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.aspect = 1.0f;
        this.aspect = context.obtainStyledAttributes(attrs, R$styleable.FixedAspectLayout).getFloat(0, 1.0f);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(MeasureSpec.makeMeasureSpec(w, Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec((int) (((float) w) * this.aspect), Ints.MAX_POWER_OF_TWO));
    }
}
