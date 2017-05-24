package com.spiraltoys.cloudpets2.util;

import android.media.MediaPlayer;
import android.util.Log;
import java.util.Timer;

public class FadingMediaPlayer extends MediaPlayer {
    private static final float STEP_SIZE = 0.033333335f;
    private static final int STEP_TIME_MS = 16;
    private static final String TAG = FadingMediaPlayer.class.getSimpleName();
    private float mCurrentVolume = 1.0f;
    private FadeTransitionListener mListener;
    private float mTargetVolume = 1.0f;
    private Timer mTimer;

    public interface FadeTransitionListener {
        void onFadeComplete();
    }

    public void mute(FadeTransitionListener listener) {
        this.mListener = listener;
        mute();
    }

    public void unmute(FadeTransitionListener listener) {
        this.mListener = listener;
        unmute();
    }

    public void mute() {
        fadeTo(0.0f);
    }

    public void unmute() {
        fadeTo(1.0f);
    }

    public void fadeTo(float targetVolume) {
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer.purge();
        }
        this.mTargetVolume = targetVolume;
        this.mTimer = new Timer(true);
        this.mTimer.schedule(new FadeTimerTask(this, null), 0, 16);
        if (this.mTargetVolume > 0.0f) {
            start();
        }
    }

    private boolean step() {
        if (this.mCurrentVolume == this.mTargetVolume) {
            return true;
        }
        float delta = this.mTargetVolume - this.mCurrentVolume;
        float nextVolume = this.mCurrentVolume;
        if (delta > 0.0f) {
            nextVolume += Math.min(STEP_SIZE, Math.abs(delta));
        } else {
            nextVolume -= Math.min(STEP_SIZE, Math.abs(delta));
        }
        try {
            setVolume(nextVolume, nextVolume);
        } catch (IllegalStateException e) {
            Log.d(TAG, "Ignoring IllegalStateException on setVolume()");
        }
        this.mCurrentVolume = nextVolume;
        if (this.mCurrentVolume != this.mTargetVolume) {
            return false;
        }
        return true;
    }
}
