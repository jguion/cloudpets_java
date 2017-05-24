package com.spiraltoys.cloudpets2.views;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.Uri;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import com.google.android.vending.expansion.downloader.Constants;
import com.spiraltoys.cloudpets2.events.PetVideoPlaybackStartedEvent;
import com.spiraltoys.cloudpets2.expansion.ExpansionUtils;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.FadingMediaPlayer;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import icepick.Icepick;
import icepick.State;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PetVideoSurfaceView extends SurfaceView implements OnTouchListener, Callback, OnCompletionListener {
    private static final RectF ABOVE_HEAD_BOUNDS = new RectF(0.0f, 0.0f, 1.0f, 0.45f);
    public static final String ANIMATION_CLAPS = "claps";
    public static final String ANIMATION_FALLING_ASLEEP = "fallingasleep";
    public static final String ANIMATION_FUN_DANCE = "fundance";
    public static final String ANIMATION_HICCUP = "hiccup";
    public static final String ANIMATION_IDLE = "idle";
    public static final String ANIMATION_SLEEP_CYCLE = "sleepcycle";
    public static final String ANIMATION_SMILE = "smile";
    public static final String ANIMATION_SNEEZE = "sneeze";
    public static final String ANIMATION_SWATS = "swats";
    public static final String ANIMATION_TICKLE = "tickle";
    private static final String ANIMATION_VIDEO_DIRECTORY_PATH = "expansion_media_files";
    private static final String ANIMATION_VIDEO_EXTENSION = ".mp4";
    public static final String ANIMATION_WAKE_UP = "wakeup";
    public static final String ANIMATION_WAVE = "wave";
    public static final String ANIMATION_WINK = "wink";
    private static final RectF BELLY_BOUNDS = new RectF(0.4f, BELLY_MIN_Y, 0.6f, BELLY_MAX_Y);
    private static final float BELLY_MAX_X = 0.6f;
    private static final float BELLY_MAX_Y = 0.84f;
    private static final float BELLY_MIN_X = 0.4f;
    private static final float BELLY_MIN_Y = 0.65f;
    private static final int FALL_ASLEEP_RETRY_TIME_MILLISECONDS = 1000;
    private static final RectF HEAD_BOUNDS = new RectF(HEAD_MIN_X, 0.45f, HEAD_MAX_X, HEAD_MAX_Y);
    private static final float HEAD_MAX_X = 0.71f;
    private static final float HEAD_MAX_Y = 0.64f;
    private static final float HEAD_MIN_X = 0.35f;
    private static final float HEAD_MIN_Y = 0.45f;
    private static final RectF LEFT_FOOT_BOUNDS = new RectF(LEFT_FOOT_MIN_X, 0.7f, 0.45f, 0.86f);
    private static final float LEFT_FOOT_MAX_X = 0.45f;
    private static final float LEFT_FOOT_MAX_Y = 0.86f;
    private static final float LEFT_FOOT_MIN_X = 0.2f;
    private static final float LEFT_FOOT_MIN_Y = 0.7f;
    private static final int MICROSECONDS_PER_MILLISECOND = 1000;
    private static final RectF RIGHT_FOOT_BOUNDS = new RectF(0.6f, 0.7f, 0.85f, 0.86f);
    private static final float RIGHT_FOOT_MAX_X = 0.85f;
    private static final float RIGHT_FOOT_MAX_Y = 0.86f;
    private static final float RIGHT_FOOT_MIN_X = 0.6f;
    private static final float RIGHT_FOOT_MIN_Y = 0.7f;
    private static final RectF SLEEPING_HEAD_BOUNDS = new RectF(0.4f, SLEEPING_HEAD_MIN_Y, 0.85f, 0.7f);
    private static final float SLEEPING_HEAD_MAX_X = 0.85f;
    private static final float SLEEPING_HEAD_MAX_Y = 0.7f;
    private static final float SLEEPING_HEAD_MIN_X = 0.4f;
    private static final float SLEEPING_HEAD_MIN_Y = 0.5f;
    private static final String TAG = PetVideoSurfaceView.class.getSimpleName();
    private static final int TIME_TO_FALL_ASLEEP_MILLISECONDS = 14000;
    private static final float VIDEO_HEIGHT = 1024.0f;
    private static final float VIDEO_WIDTH = 768.0f;
    private FadingMediaPlayer mCurrentMediaPlayer;
    @State
    String mCurrentlyPlayingAnimation;
    @State
    String mCurrentlyQueuedAnimation;
    private Timer mFallAsleepTimer;
    private TimerTask mFallAsleepTimerTask;
    private boolean mIsTouchQueued;
    private FadingMediaPlayer mNextMediaPlayer;
    private boolean mNextMediaPlayerPrepared;
    private OnPreparedListener mOnCurrentVideoPreparedListener;
    private OnErrorListener mOnErrorListener;
    private OnPreparedListener mOnImmediatePlayNextVideoPreparedListener;
    private OnInfoListener mOnInfoListener;
    private OnPreparedListener mOnNextVideoPreparedListener;
    private OnSeekCompleteListener mOnSeekCompleteListener;
    @State
    int mSavedPosition;
    @State
    float mVolume;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Animation {
    }

    private class FallAsleepTimerTask extends TimerTask {
        private FallAsleepTimerTask() {
        }

        public void run() {
            PetVideoSurfaceView.this.goToSleep();
        }
    }

    public PetVideoSurfaceView(Context context) {
        this(context, null);
    }

    public PetVideoSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PetVideoSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mOnInfoListener = new OnInfoListener() {
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == 3) {
                    EventBus.getDefault().post(new PetVideoPlaybackStartedEvent());
                }
                return false;
            }
        };
        this.mOnSeekCompleteListener = new OnSeekCompleteListener() {
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
            }
        };
        this.mOnCurrentVideoPreparedListener = new OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                synchronized (PetVideoSurfaceView.this) {
                    mediaPlayer.seekTo(PetVideoSurfaceView.this.mSavedPosition);
                    PetVideoSurfaceView.this.mSavedPosition = 0;
                }
            }
        };
        this.mOnImmediatePlayNextVideoPreparedListener = new OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                synchronized (PetVideoSurfaceView.this) {
                    if (!(PetVideoSurfaceView.this.mCurrentMediaPlayer == null || PetVideoSurfaceView.this.mNextMediaPlayer != mediaPlayer || PetVideoSurfaceView.this.mCurrentlyPlayingAnimation == PetVideoSurfaceView.this.mCurrentlyQueuedAnimation)) {
                        PetVideoSurfaceView.this.mNextMediaPlayerPrepared = true;
                        PetVideoSurfaceView.this.mCurrentMediaPlayer.setLooping(false);
                        if (!PetVideoSurfaceView.this.mCurrentMediaPlayer.isPlaying()) {
                            PetVideoSurfaceView.this.mCurrentMediaPlayer.start();
                        }
                        if (PetVideoSurfaceView.this.mCurrentlyPlayingAnimation == PetVideoSurfaceView.ANIMATION_IDLE || PetVideoSurfaceView.this.mCurrentlyPlayingAnimation == PetVideoSurfaceView.ANIMATION_SLEEP_CYCLE) {
                            PetVideoSurfaceView.this.onCompletion(PetVideoSurfaceView.this.mCurrentMediaPlayer);
                        }
                    }
                }
            }
        };
        this.mOnNextVideoPreparedListener = new OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                synchronized (PetVideoSurfaceView.this) {
                    if (!(PetVideoSurfaceView.this.mCurrentMediaPlayer == null || PetVideoSurfaceView.this.mNextMediaPlayer != mediaPlayer || PetVideoSurfaceView.this.mCurrentlyPlayingAnimation == PetVideoSurfaceView.this.mCurrentlyQueuedAnimation)) {
                        PetVideoSurfaceView.this.mNextMediaPlayerPrepared = true;
                        PetVideoSurfaceView.this.mCurrentMediaPlayer.setLooping(false);
                        if (!PetVideoSurfaceView.this.mCurrentMediaPlayer.isPlaying()) {
                            PetVideoSurfaceView.this.mCurrentMediaPlayer.start();
                        }
                    }
                }
            }
        };
        this.mOnErrorListener = new OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                switch (what) {
                    case 1:
                    case 100:
                        mediaPlayer.release();
                        if (mediaPlayer == PetVideoSurfaceView.this.mCurrentMediaPlayer) {
                            PetVideoSurfaceView.this.release();
                            PetVideoSurfaceView.this.prepareCurrentMediaPlayer();
                            PetVideoSurfaceView.this.queueNextVideo();
                        }
                        if (mediaPlayer == PetVideoSurfaceView.this.mNextMediaPlayer) {
                            PetVideoSurfaceView.this.mNextMediaPlayer = null;
                        }
                        PetVideoSurfaceView.this.mIsTouchQueued = false;
                        return true;
                    default:
                        return false;
                }
            }
        };
        init(context);
    }

    private void init(Context context) {
        this.mCurrentlyPlayingAnimation = ANIMATION_IDLE;
        this.mVolume = SettingsManager.isChildDashboardSoundEnabled(context) ? 1.0f : 0.0f;
        getHolder().addCallback(this);
        setOnTouchListener(this);
        resetFallAsleepTimer();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float viewportWidth = (float) MeasureSpec.getSize(widthMeasureSpec);
        float viewportHeight = (float) MeasureSpec.getSize(heightMeasureSpec);
        if (0.75f > viewportWidth / viewportHeight) {
            setMeasuredDimension((int) (viewportHeight * 0.75f), (int) viewportHeight);
        } else {
            setMeasuredDimension((int) viewportWidth, (int) (viewportWidth / 0.75f));
        }
    }

    public synchronized void pausePlayback() {
        if (this.mCurrentMediaPlayer != null && this.mCurrentMediaPlayer.isPlaying()) {
            try {
                this.mCurrentMediaPlayer.pause();
            } catch (IllegalStateException ex) {
                Log.d(TAG, "Attempted to pause pause playback but player was not initialized.");
                ex.printStackTrace();
            }
        }
        if (this.mNextMediaPlayer != null) {
            this.mNextMediaPlayer.release();
            this.mNextMediaPlayer = null;
        }
    }

    public void resumePlayback() {
        if (this.mCurrentMediaPlayer == null || this.mCurrentMediaPlayer.isPlaying()) {
            setVisibility(8);
            setVisibility(0);
        } else {
            try {
                this.mCurrentMediaPlayer.start();
                if (this.mNextMediaPlayer == null) {
                    queueNextVideo();
                }
            } catch (IllegalStateException ex) {
                ex.printStackTrace();
                setVisibility(8);
                setVisibility(0);
            }
        }
        this.mIsTouchQueued = false;
    }

    public void mute() {
        if (SettingsManager.isChildDashboardSoundEnabled(getContext())) {
            setVolume(0.0f);
        }
    }

    public void unmute() {
        if (SettingsManager.isChildDashboardSoundEnabled(getContext())) {
            setVolume(1.0f);
        }
    }

    public void setVolume(float volume) {
        this.mVolume = volume;
        try {
            if (this.mCurrentMediaPlayer != null) {
                this.mCurrentMediaPlayer.fadeTo(volume);
            }
            if (this.mNextMediaPlayer != null) {
                this.mNextMediaPlayer.setVolume(volume, volume);
            }
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }

    public Bitmap getLastPausedFrame() {
        RuntimeException ex;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            AssetFileDescriptor assetFileDescriptor = getAnimationAssetFileDescriptor(this.mCurrentlyPlayingAnimation);
            retriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            Bitmap frameAtTime = retriever.getFrameAtTime((long) (this.mSavedPosition * Constants.MAX_DOWNLOADS));
            retriever.release();
            return frameAtTime;
        } catch (RuntimeException e) {
            ex = e;
            try {
                ex.printStackTrace();
                return null;
            } finally {
                retriever.release();
            }
        } catch (RuntimeException e2) {
            ex = e2;
            ex.printStackTrace();
            return null;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        resetFallAsleepTimer();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelFallAsleepTimer();
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (getHolder() == null) {
            return false;
        }
        if (event.getAction() == 0) {
            float x = event.getX();
            float y = event.getY();
            resetFallAsleepTimer();
            if (this.mCurrentlyPlayingAnimation == ANIMATION_IDLE || this.mCurrentlyPlayingAnimation == ANIMATION_SLEEP_CYCLE) {
                detectTouchZone(x, y);
            }
        }
        return true;
    }

    private void resetFallAsleepTimer() {
        resetFallAsleepTimer(14000);
    }

    private synchronized void resetFallAsleepTimer(long timeUntilSleep) {
        cancelFallAsleepTimer();
        this.mFallAsleepTimerTask = new FallAsleepTimerTask();
        this.mFallAsleepTimer = new Timer();
        this.mFallAsleepTimer.schedule(this.mFallAsleepTimerTask, timeUntilSleep);
    }

    private void cancelFallAsleepTimer() {
        if (this.mFallAsleepTimer != null) {
            this.mFallAsleepTimer.cancel();
            this.mFallAsleepTimer.purge();
        }
    }

    private void goToSleep() {
        if (this.mCurrentlyPlayingAnimation == ANIMATION_IDLE) {
            queueVideo(ANIMATION_FALLING_ASLEEP);
        } else {
            resetFallAsleepTimer(1000);
        }
    }

    public void wakeUp() {
        if (this.mCurrentlyPlayingAnimation == ANIMATION_SLEEP_CYCLE) {
            playVideo(ANIMATION_WAKE_UP);
        }
    }

    protected Parcelable onSaveInstanceState() {
        savePlaybackState();
        return Icepick.saveInstanceState((View) this, super.onSaveInstanceState());
    }

    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Icepick.restoreInstanceState((View) this, state));
    }

    private void detectTouchZone(float x, float y) {
        if (!this.mIsTouchQueued) {
            float xf = x / ((float) getWidth());
            float yf = y / ((float) getHeight());
            Log.d(TAG, "x:" + xf + "% y:" + yf + "%");
            if (LEFT_FOOT_BOUNDS.contains(xf, yf)) {
                onLeftFootTouch();
            } else if (RIGHT_FOOT_BOUNDS.contains(xf, yf)) {
                onRightFootTouch();
            } else if ((this.mCurrentlyPlayingAnimation == ANIMATION_IDLE && HEAD_BOUNDS.contains(xf, yf)) || (this.mCurrentlyPlayingAnimation == ANIMATION_SLEEP_CYCLE && SLEEPING_HEAD_BOUNDS.contains(xf, yf))) {
                onFaceTouch();
            } else if (BELLY_BOUNDS.contains(xf, yf)) {
                onBellyTouch();
            } else if (ABOVE_HEAD_BOUNDS.contains(xf, yf)) {
                onAboveHeadTouch();
            }
        }
    }

    private void onLeftFootTouch() {
        this.mIsTouchQueued = true;
        if (this.mCurrentlyPlayingAnimation == ANIMATION_IDLE) {
            playVideo(getRandomFootTouchAnimation());
        } else if (this.mCurrentlyPlayingAnimation == ANIMATION_SLEEP_CYCLE) {
            wakeUp();
        } else {
            this.mIsTouchQueued = false;
        }
    }

    private void onRightFootTouch() {
        this.mIsTouchQueued = true;
        if (this.mCurrentlyPlayingAnimation == ANIMATION_IDLE) {
            playVideo(getRandomFootTouchAnimation());
        } else if (this.mCurrentlyPlayingAnimation == ANIMATION_SLEEP_CYCLE) {
            wakeUp();
        } else {
            this.mIsTouchQueued = false;
        }
    }

    private void onFaceTouch() {
        this.mIsTouchQueued = true;
        if (this.mCurrentlyPlayingAnimation == ANIMATION_IDLE) {
            playVideo(getRandomFaceTouchAnimation());
        } else if (this.mCurrentlyPlayingAnimation == ANIMATION_SLEEP_CYCLE) {
            wakeUp();
        } else {
            this.mIsTouchQueued = false;
        }
    }

    private void onBellyTouch() {
        this.mIsTouchQueued = true;
        if (this.mCurrentlyPlayingAnimation == ANIMATION_IDLE) {
            playVideo(ANIMATION_TICKLE);
        } else if (this.mCurrentlyPlayingAnimation == ANIMATION_SLEEP_CYCLE) {
            wakeUp();
        } else {
            this.mIsTouchQueued = false;
        }
    }

    private void onAboveHeadTouch() {
        this.mIsTouchQueued = true;
        if (this.mCurrentlyPlayingAnimation == ANIMATION_IDLE) {
            playVideo(ANIMATION_SWATS);
        } else if (this.mCurrentlyPlayingAnimation == ANIMATION_SLEEP_CYCLE) {
            wakeUp();
        } else {
            this.mIsTouchQueued = false;
        }
    }

    private String getRandomFaceTouchAnimation() {
        switch (new Random().nextInt(5)) {
            case 0:
                return ANIMATION_HICCUP;
            case 1:
                return ANIMATION_SMILE;
            case 2:
                return ANIMATION_SNEEZE;
            case 3:
                return ANIMATION_WAVE;
            case 4:
                return ANIMATION_WINK;
            default:
                throw new IllegalStateException("Random face touch animation selection is broken");
        }
    }

    private String getRandomFootTouchAnimation() {
        switch (new Random().nextInt(3)) {
            case 0:
                return ANIMATION_CLAPS;
            case 1:
                return ANIMATION_SMILE;
            case 2:
                return ANIMATION_FUN_DANCE;
            default:
                throw new IllegalStateException("Random foot touch animation selection is broken");
        }
    }

    private String getRandomIdleAnimation() {
        switch (new Random().nextInt(4)) {
            case 0:
            case 1:
                return ANIMATION_IDLE;
            case 2:
                return ANIMATION_SMILE;
            case 3:
                return ANIMATION_WAVE;
            default:
                throw new IllegalStateException("Random idle animation selection is broken");
        }
    }

    private void playVideo(String animation) {
        queueVideo(animation, true);
    }

    private void queueVideo(String animation) {
        queueVideo(animation, false);
    }

    private synchronized void queueVideo(String animation, boolean forceImmediatePlay) {
        if (this.mNextMediaPlayer != null) {
            this.mNextMediaPlayer.release();
        }
        this.mCurrentlyQueuedAnimation = animation;
        this.mNextMediaPlayerPrepared = false;
        try {
            AssetFileDescriptor assetFileDescriptor = getAnimationAssetFileDescriptor(this.mCurrentlyQueuedAnimation);
            this.mNextMediaPlayer = new FadingMediaPlayer();
            this.mNextMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            this.mNextMediaPlayer.setOnPreparedListener(forceImmediatePlay ? this.mOnImmediatePlayNextVideoPreparedListener : this.mOnNextVideoPreparedListener);
            this.mNextMediaPlayer.setOnCompletionListener(this);
            this.mNextMediaPlayer.setOnInfoListener(this.mOnInfoListener);
            this.mNextMediaPlayer.setOnErrorListener(this.mOnErrorListener);
            this.mNextMediaPlayer.setVolume(this.mVolume, this.mVolume);
            this.mNextMediaPlayer.setLooping(true);
            this.mNextMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void prepareCurrentMediaPlayer() {
        if (this.mCurrentlyPlayingAnimation == null) {
            this.mCurrentlyPlayingAnimation = ANIMATION_IDLE;
        }
        if (this.mCurrentMediaPlayer != null) {
            this.mCurrentMediaPlayer.setDisplay(getHolder());
        } else {
            try {
                AssetFileDescriptor assetFileDescriptor = getAnimationAssetFileDescriptor(this.mCurrentlyPlayingAnimation);
                this.mCurrentMediaPlayer = new FadingMediaPlayer();
                this.mCurrentMediaPlayer.setDisplay(getHolder());
                this.mCurrentMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                this.mCurrentMediaPlayer.setOnPreparedListener(this.mOnCurrentVideoPreparedListener);
                this.mCurrentMediaPlayer.setOnSeekCompleteListener(this.mOnSeekCompleteListener);
                this.mCurrentMediaPlayer.setOnCompletionListener(this);
                this.mCurrentMediaPlayer.setOnInfoListener(this.mOnInfoListener);
                this.mCurrentMediaPlayer.setOnErrorListener(this.mOnErrorListener);
                this.mCurrentMediaPlayer.setVolume(this.mVolume, this.mVolume);
                this.mCurrentMediaPlayer.setLooping(true);
                this.mCurrentMediaPlayer.prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        switch (visibility) {
            case 0:
                resetFallAsleepTimer();
                return;
            default:
                cancelFallAsleepTimer();
                return;
        }
    }

    public synchronized void surfaceCreated(SurfaceHolder holder) {
        resetFallAsleepTimer();
        prepareCurrentMediaPlayer();
        queueNextVideo();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        savePlaybackState();
        release();
    }

    private void savePlaybackState() {
        pausePlayback();
        if (this.mCurrentMediaPlayer != null) {
            this.mSavedPosition = this.mCurrentMediaPlayer.getCurrentPosition();
        }
    }

    public synchronized void onCompletion(MediaPlayer mediaPlayer) {
        if (this.mCurrentMediaPlayer != null) {
            if (this.mNextMediaPlayer == null || !this.mNextMediaPlayerPrepared) {
                this.mCurrentMediaPlayer.setLooping(true);
                this.mCurrentMediaPlayer.start();
            } else {
                this.mCurrentMediaPlayer.setDisplay(null);
                this.mNextMediaPlayer.setDisplay(getHolder());
                this.mNextMediaPlayer.start();
                this.mCurrentMediaPlayer.release();
                this.mCurrentMediaPlayer = this.mNextMediaPlayer;
                this.mNextMediaPlayer = null;
                this.mCurrentlyPlayingAnimation = this.mCurrentlyQueuedAnimation;
                this.mCurrentlyQueuedAnimation = null;
            }
            this.mIsTouchQueued = false;
            queueNextVideo();
        }
    }

    private void queueNextVideo() {
        if (this.mCurrentlyPlayingAnimation == ANIMATION_SLEEP_CYCLE || this.mCurrentlyPlayingAnimation == ANIMATION_FALLING_ASLEEP) {
            queueVideo(ANIMATION_SLEEP_CYCLE);
        } else if (this.mCurrentlyPlayingAnimation == ANIMATION_IDLE) {
            queueVideo(getRandomIdleAnimation());
        } else {
            queueVideo(ANIMATION_IDLE);
        }
    }

    public void release() {
        if (this.mCurrentMediaPlayer != null) {
            this.mCurrentMediaPlayer.release();
            this.mCurrentMediaPlayer = null;
        }
        if (this.mNextMediaPlayer != null) {
            this.mNextMediaPlayer.release();
            this.mNextMediaPlayer = null;
        }
    }

    private Uri getResourceUri(int resource) {
        return Uri.parse("android.resource://" + getContext().getPackageName() + "/" + resource);
    }

    @DebugLog
    private AssetFileDescriptor getAnimationAssetFileDescriptor(String animation) {
        try {
            return ExpansionUtils.getMainExpansionDescriptor().getZipResourceFile(getContext()).getAssetFileDescriptor(ANIMATION_VIDEO_DIRECTORY_PATH + File.separator + Utils.getStyledStringForAttribute(getContext(), R.attr.character_animation_prefix) + animation + ANIMATION_VIDEO_EXTENSION);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
