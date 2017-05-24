package com.spiraltoys.cloudpets2;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.InjectView;
import com.spiraltoys.cloudpets2.events.PetVideoPlaybackStartedEvent;
import com.spiraltoys.cloudpets2.views.PetVideoSurfaceView;
import de.greenrobot.event.EventBus;

public abstract class InteractiveToyActivity extends BaseActivity {
    private static final int MAX_TIME_TO_RESUME_VIDEO_MS = 750;
    private static final int OVERLAY_TRANSITION_TIME_MS = 200;
    private static final int TIME_TO_OVERLAY_VIDEO_MS = 500;
    @InjectView(2131755155)
    FrameLayout mContainer;
    private Runnable mDelayedVideoOverlayRunnable = new Runnable() {
        public void run() {
            InteractiveToyActivity.this.mVideoPlaceholder.setVisibility(0);
            InteractiveToyActivity.this.mVideoPlaceholder.setAlpha(1.0f);
        }
    };
    private boolean mEnterAnimationCompleteDone;
    private Handler mHandler;
    private Bitmap mLastSavedFrame;
    private Runnable mOnEnterAnimationCompleteFallbackRunnable = new Runnable() {
        public void run() {
            InteractiveToyActivity.this.onEnterAnimationComplete();
        }
    };
    @InjectView(2131755156)
    PetVideoSurfaceView mPetVideoSurfaceView;
    @InjectView(2131755157)
    ImageView mVideoPlaceholder;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mHandler = new Handler();
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
        if (VERSION.SDK_INT >= 21) {
            this.mContainer.removeView(this.mPetVideoSurfaceView);
        }
    }

    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    protected void onPause() {
        super.onPause();
        this.mPetVideoSurfaceView.pausePlayback();
        this.mHandler.postDelayed(this.mDelayedVideoOverlayRunnable, 500);
        new Thread(new Runnable() {
            public void run() {
                InteractiveToyActivity.this.mLastSavedFrame = InteractiveToyActivity.this.mPetVideoSurfaceView.getLastPausedFrame();
                if (InteractiveToyActivity.this.mLastSavedFrame != null) {
                    InteractiveToyActivity.this.mHandler.post(new 1(this));
                }
            }
        }).start();
    }

    protected void onResume() {
        super.onResume();
        this.mHandler.removeCallbacks(this.mDelayedVideoOverlayRunnable);
        this.mVideoPlaceholder.setAlpha(1.0f);
        this.mVideoPlaceholder.setVisibility(0);
        if (VERSION.SDK_INT >= 21) {
            this.mEnterAnimationCompleteDone = false;
            this.mHandler.postDelayed(this.mOnEnterAnimationCompleteFallbackRunnable, 750);
            return;
        }
        this.mPetVideoSurfaceView.resumePlayback();
        fadeOutVideoPlaceholder();
    }

    public void onEnterAnimationComplete() {
        this.mHandler.removeCallbacks(this.mOnEnterAnimationCompleteFallbackRunnable);
        if (!this.mEnterAnimationCompleteDone) {
            this.mEnterAnimationCompleteDone = true;
            if (VERSION.SDK_INT >= 21 && this.mContainer != null) {
                this.mContainer.removeView(this.mPetVideoSurfaceView);
                this.mContainer.addView(this.mPetVideoSurfaceView, 0);
            }
        }
    }

    public void onEvent(PetVideoPlaybackStartedEvent event) {
        if (this.mVideoPlaceholder.getVisibility() == 0) {
            fadeOutVideoPlaceholder();
        }
    }

    private void fadeOutVideoPlaceholder() {
        this.mVideoPlaceholder.animate().alpha(0.0f).setDuration(200).setListener(new AnimatorListener() {
            public void onAnimationStart(Animator animation) {
            }

            public void onAnimationEnd(Animator animation) {
                InteractiveToyActivity.this.mVideoPlaceholder.setVisibility(8);
                InteractiveToyActivity.this.mPetVideoSurfaceView.wakeUp();
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
}
