package com.spiraltoys.cloudpets2.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class AudioPlayer {
    private static final int AUDIO_PROGRESS_UPDATE_TIME = 17;
    private static final String ERROR_PLAYTIME_CURRENT_NEGATIVE = "Current playback time cannot be negative";
    private static final String ERROR_PLAYTIME_TOTAL_NEGATIVE = "Total playback time cannot be negative";
    private static final String ERROR_PLAYVIEW_NULL = "Play view cannot be null";
    private static final int MEDIA_PLAYER_QUERY_INTERVAL = 1000;
    private static final int PROGRESS_ERROR_TOLERANCE_MS = 1250;
    private static final int ROUND_UP_MILLISECOND_TO_SECOND_MODIFIER = 950;
    private static final String TAG = AudioPlayer.class.getSimpleName();
    private static AudioPlayer mAudioPlayer;
    private static Uri mUri;
    private ArrayList<OnCompletionListener> mCompletionListeners = new ArrayList();
    private int mLastMediaPlayerPosition;
    private long mLastMediaPlayerPositionQueryTime;
    private MediaPlayer mMediaPlayer;
    private OnCompletionListener mOnCompletion = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mp) {
            if (AudioPlayer.this.mSeekBar != null) {
                AudioPlayer.this.mSeekBar.setProgress(0);
            }
            AudioPlayer.this.mLastMediaPlayerPosition = 0;
            AudioPlayer.this.mLastMediaPlayerPositionQueryTime = 0;
            AudioPlayer.this.updateRuntime(0);
            AudioPlayer.this.setPlayable();
            AudioPlayer.this.fireCustomCompletionListeners(mp);
        }
    };
    private OnPreparedListener mOnPrepared = new OnPreparedListener() {
        public void onPrepared(MediaPlayer mp) {
            Iterator it = AudioPlayer.this.mPreparedListeners.iterator();
            while (it.hasNext()) {
                ((OnPreparedListener) it.next()).onPrepared(mp);
            }
        }
    };
    private View mPauseButton;
    private ArrayList<OnClickListener> mPauseListeners = new ArrayList();
    private View mPlayButton;
    private ArrayList<OnClickListener> mPlayListeners = new ArrayList();
    private ArrayList<OnPreparedListener> mPreparedListeners = new ArrayList();
    private Handler mProgressUpdateHandler;
    private TextView mRunTime;
    private SeekBar mSeekBar;
    private TextView mTotalTime;
    private Runnable mUpdateProgress = new Runnable() {
        public void run() {
            if (AudioPlayer.this.mSeekBar != null && AudioPlayer.this.mProgressUpdateHandler != null && AudioPlayer.this.mMediaPlayer.isPlaying()) {
                if (SystemClock.uptimeMillis() > AudioPlayer.this.mLastMediaPlayerPositionQueryTime + 1000) {
                    int currentTimeEstimate = (int) (((long) AudioPlayer.this.mLastMediaPlayerPosition) + (SystemClock.uptimeMillis() - AudioPlayer.this.mLastMediaPlayerPositionQueryTime));
                    boolean isPositionCorrectionRequired = Math.abs(currentTimeEstimate - AudioPlayer.this.mMediaPlayer.getCurrentPosition()) > AudioPlayer.PROGRESS_ERROR_TOLERANCE_MS;
                    AudioPlayer audioPlayer = AudioPlayer.this;
                    if (isPositionCorrectionRequired) {
                        currentTimeEstimate = AudioPlayer.this.mMediaPlayer.getCurrentPosition();
                    }
                    audioPlayer.mLastMediaPlayerPosition = currentTimeEstimate;
                    AudioPlayer.this.mLastMediaPlayerPositionQueryTime = SystemClock.uptimeMillis();
                }
                int currentTime = (int) (((long) AudioPlayer.this.mLastMediaPlayerPosition) + (SystemClock.uptimeMillis() - AudioPlayer.this.mLastMediaPlayerPositionQueryTime));
                AudioPlayer.this.mSeekBar.setProgress(currentTime);
                AudioPlayer.this.updateRuntime(currentTime);
                AudioPlayer.this.mProgressUpdateHandler.postDelayed(this, 17);
            }
        }
    };

    public void play() {
        if (this.mPlayButton == null) {
            throw new IllegalStateException(ERROR_PLAYVIEW_NULL);
        } else if (mUri == null) {
            throw new IllegalStateException("Uri cannot be null. Call init() before calling this method");
        } else if (this.mMediaPlayer == null) {
            throw new IllegalStateException("Call init() before calling this method");
        } else if (!this.mMediaPlayer.isPlaying()) {
            this.mProgressUpdateHandler.postDelayed(this.mUpdateProgress, 17);
            setViewsVisibility();
            this.mMediaPlayer.start();
            setPausable();
        }
    }

    private void setViewsVisibility() {
        if (this.mSeekBar != null) {
            this.mSeekBar.setVisibility(0);
        }
        if (this.mRunTime != null) {
            this.mRunTime.setVisibility(0);
        }
        if (this.mTotalTime != null) {
            this.mTotalTime.setVisibility(0);
        }
        if (this.mPlayButton != null) {
            this.mPlayButton.setVisibility(0);
        }
        if (this.mPauseButton != null) {
            this.mPauseButton.setVisibility(0);
        }
    }

    public void pause() {
        if (this.mMediaPlayer != null) {
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.pause();
                setPlayable();
            }
            this.mLastMediaPlayerPositionQueryTime = 0;
        }
    }

    private void updateRuntime(int currentTime) {
        if (this.mRunTime != null) {
            if (currentTime < 0) {
                throw new IllegalArgumentException(ERROR_PLAYTIME_CURRENT_NEGATIVE);
            }
            this.mRunTime.setText(String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes((long) currentTime)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) currentTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) currentTime)))}));
        }
    }

    private void setTotalTime() {
        if (this.mTotalTime != null) {
            StringBuilder playbackStr = new StringBuilder();
            long totalDuration = 0;
            if (this.mMediaPlayer != null) {
                try {
                    totalDuration = (long) this.mMediaPlayer.getDuration();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            if (totalDuration < 0) {
                throw new IllegalArgumentException(ERROR_PLAYTIME_TOTAL_NEGATIVE);
            }
            if (totalDuration != 0) {
                playbackStr.append(String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(totalDuration)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(950 + totalDuration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalDuration)))}));
            }
            this.mTotalTime.setText(playbackStr);
        }
    }

    private void setPlayable() {
        if (this.mPlayButton != null) {
            this.mPlayButton.setVisibility(0);
        }
        if (this.mPauseButton != null) {
            this.mPauseButton.setVisibility(8);
        }
    }

    private void setPausable() {
        if (this.mPlayButton != null) {
            this.mPlayButton.setVisibility(8);
        }
        if (this.mPauseButton != null) {
            this.mPauseButton.setVisibility(0);
        }
    }

    public AudioPlayer init(Context ctx, Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("Uri cannot be null");
        }
        if (mAudioPlayer == null) {
            mAudioPlayer = new AudioPlayer();
        }
        mUri = uri;
        this.mProgressUpdateHandler = new Handler(Looper.getMainLooper());
        initPlayer(ctx);
        return this;
    }

    public AudioPlayer setPlayView(View play) {
        if (play == null) {
            throw new NullPointerException("PlayView cannot be null");
        }
        this.mPlayButton = play;
        initOnPlayClick();
        return this;
    }

    private void initOnPlayClick() {
        if (this.mPlayButton == null) {
            throw new NullPointerException(ERROR_PLAYVIEW_NULL);
        }
        this.mPlayListeners.add(0, new OnClickListener() {
            public void onClick(View v) {
                AudioPlayer.this.play();
            }
        });
        this.mPlayButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Iterator it = AudioPlayer.this.mPlayListeners.iterator();
                while (it.hasNext()) {
                    ((OnClickListener) it.next()).onClick(v);
                }
            }
        });
    }

    public AudioPlayer setPauseView(View pause) {
        if (pause == null) {
            throw new NullPointerException("PauseView cannot be null");
        }
        this.mPauseButton = pause;
        initOnPauseClick();
        return this;
    }

    private void initOnPauseClick() {
        if (this.mPauseButton == null) {
            throw new NullPointerException("Pause view cannot be null");
        }
        this.mPauseListeners.add(0, new OnClickListener() {
            public void onClick(View v) {
                AudioPlayer.this.pause();
            }
        });
        this.mPauseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Iterator it = AudioPlayer.this.mPauseListeners.iterator();
                while (it.hasNext()) {
                    ((OnClickListener) it.next()).onClick(v);
                }
            }
        });
    }

    public AudioPlayer setRuntimeView(TextView currentTime) {
        this.mRunTime = currentTime;
        updateRuntime(0);
        return this;
    }

    public AudioPlayer setTotalTimeView(TextView totalTime) {
        this.mTotalTime = totalTime;
        setTotalTime();
        return this;
    }

    public AudioPlayer setSeekBar(SeekBar seekbar) {
        this.mSeekBar = seekbar;
        initMediaSeekBar();
        return this;
    }

    public AudioPlayer addOnCompletionListener(OnCompletionListener listener) {
        this.mCompletionListeners.add(0, listener);
        return this;
    }

    public AudioPlayer addOnPlayClickListener(OnClickListener listener) {
        this.mPlayListeners.add(listener);
        return this;
    }

    public AudioPlayer addOnPauseClickListener(OnClickListener listener) {
        this.mPauseListeners.add(listener);
        return this;
    }

    public AudioPlayer addOnPreparedListener(OnPreparedListener listener) {
        this.mPreparedListeners.add(0, listener);
        return this;
    }

    private synchronized void initPlayer(Context ctx) {
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setAudioStreamType(3);
        try {
            this.mMediaPlayer.setDataSource(ctx, mUri);
            this.mMediaPlayer.setOnCompletionListener(this.mOnCompletion);
            this.mMediaPlayer.setOnPreparedListener(this.mOnPrepared);
            this.mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
            release();
        }
    }

    private void initMediaSeekBar() {
        if (this.mMediaPlayer != null && this.mSeekBar != null) {
            long finalTime = (long) this.mMediaPlayer.getDuration();
            this.mLastMediaPlayerPosition = 0;
            this.mLastMediaPlayerPositionQueryTime = 0;
            this.mSeekBar.setMax((int) finalTime);
            this.mSeekBar.setProgress(0);
            this.mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                public void onStopTrackingTouch(SeekBar seekBar) {
                    AudioPlayer.this.mMediaPlayer.seekTo(seekBar.getProgress());
                    AudioPlayer.this.mLastMediaPlayerPositionQueryTime = 0;
                    AudioPlayer.this.updateRuntime(seekBar.getProgress());
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                }
            });
        }
    }

    private void fireCustomCompletionListeners(MediaPlayer mp) {
        Iterator it = this.mCompletionListeners.iterator();
        while (it.hasNext()) {
            ((OnCompletionListener) it.next()).onCompletion(mp);
        }
    }

    public synchronized void release() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mProgressUpdateHandler = null;
        }
    }
}
