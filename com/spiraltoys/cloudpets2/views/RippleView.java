package com.spiraltoys.cloudpets2.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.spiraltoys.cloudpets2.databinding.ViewRippleBinding;
import com.spiraltoys.cloudpets2.free.R;

public class RippleView extends FrameLayout {
    private static final int ANIMATION_DURATION = 1500;
    private ViewRippleBinding mBinding;
    private boolean mIsAnimating;

    public RippleView(Context context) {
        this(context, null);
    }

    public RippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.mBinding = (ViewRippleBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_ripple, this, true);
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && this.mIsAnimating) {
            restartAnimations();
        }
    }

    public void startRippleAnimation() {
        this.mIsAnimating = true;
        restartAnimations();
    }

    public void stopRippleAnimation() {
        this.mIsAnimating = false;
        if (this.mBinding.ripple1.getAnimation() != null && this.mBinding.ripple1.getAnimation().getStartTime() >= AnimationUtils.currentAnimationTimeMillis()) {
            this.mBinding.ripple1.clearAnimation();
            this.mBinding.ripple1.setVisibility(4);
        }
        if (this.mBinding.ripple2.getAnimation() != null && this.mBinding.ripple2.getAnimation().getStartTime() >= AnimationUtils.currentAnimationTimeMillis()) {
            this.mBinding.ripple2.clearAnimation();
            this.mBinding.ripple2.setVisibility(4);
        }
        if (this.mBinding.ripple3.getAnimation() != null && this.mBinding.ripple3.getAnimation().getStartTime() >= AnimationUtils.currentAnimationTimeMillis()) {
            this.mBinding.ripple3.clearAnimation();
            this.mBinding.ripple3.setVisibility(4);
        }
    }

    private void restartAnimations() {
        setupRippleAnimation(this.mBinding.ripple1, 0);
        setupRippleAnimation(this.mBinding.ripple2, 500);
        setupRippleAnimation(this.mBinding.ripple3, 1000);
        invalidate();
    }

    private void setupRippleAnimation(final View ripple, long startDelayMilliseconds) {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.ripple_out);
        animation.setStartTime(AnimationUtils.currentAnimationTimeMillis() + startDelayMilliseconds);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(1);
        animation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (RippleView.this.mIsAnimating) {
                    animation.reset();
                    animation.start();
                    return;
                }
                ripple.setVisibility(4);
                animation.setRepeatCount(0);
            }

            public void onAnimationRepeat(Animation animation) {
                if (!RippleView.this.mIsAnimating) {
                    animation.cancel();
                }
            }
        });
        ripple.setVisibility(0);
        ripple.setAnimation(animation);
    }
}
