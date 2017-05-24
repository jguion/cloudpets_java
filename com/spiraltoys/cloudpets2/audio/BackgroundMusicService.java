package com.spiraltoys.cloudpets2.audio;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import com.spiraltoys.cloudpets2.util.FadingMediaPlayer;
import com.spiraltoys.cloudpets2.util.FadingMediaPlayer.FadeTransitionListener;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import hugo.weaving.DebugLog;
import java.io.IOException;

public class BackgroundMusicService extends Service implements OnSharedPreferenceChangeListener {
    public static final String INTENT_PAUSE_MUSIC = "intent-pause-music";
    public static final String INTENT_RESUME_MUSIC = "intent-resume-music";
    public static final String KEY_MUSIC = "key-music";
    public static final String KEY_START_PAUSED = "key-start-paused";
    private static FadingMediaPlayer mMediaPlayer;
    private BackgroundMusicTrack mCurrentTrack;
    private BroadcastReceiver mPauseMusicReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (BackgroundMusicService.this.isBackgroundMusicEnabled()) {
                BackgroundMusicService.mMediaPlayer.mute();
            }
        }
    };
    private BroadcastReceiver mResumeMusicReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (BackgroundMusicService.this.isBackgroundMusicEnabled()) {
                BackgroundMusicService.this.mStartPaused = false;
                BackgroundMusicService.mMediaPlayer.unmute();
            }
        }
    };
    private boolean mStartPaused = false;

    @DebugLog
    public void onCreate() {
        super.onCreate();
        if (mMediaPlayer == null) {
            mMediaPlayer = new FadingMediaPlayer();
        }
        registerReceiver(this.mPauseMusicReceiver, new IntentFilter(INTENT_PAUSE_MUSIC));
        registerReceiver(this.mResumeMusicReceiver, new IntentFilter(INTENT_RESUME_MUSIC));
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).registerOnSharedPreferenceChangeListener(this);
    }

    @DebugLog
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.mStartPaused = intent.getBooleanExtra(KEY_START_PAUSED, false);
        final BackgroundMusicTrack track = (BackgroundMusicTrack) intent.getSerializableExtra(KEY_MUSIC);
        if (!(mMediaPlayer.isPlaying() && this.mCurrentTrack == track)) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.mute(new FadeTransitionListener() {
                    public void onFadeComplete() {
                        BackgroundMusicService.this.playTrack(track);
                    }
                });
            } else {
                playTrack(track);
            }
        }
        return 2;
    }

    private void playTrack(BackgroundMusicTrack track) {
        Exception e;
        this.mCurrentTrack = track;
        if (mMediaPlayer != null && isBackgroundMusicEnabled()) {
            try {
                mMediaPlayer.reset();
            } catch (IllegalStateException e2) {
                mMediaPlayer.release();
                mMediaPlayer = new FadingMediaPlayer();
            }
            try {
                AssetFileDescriptor afd = getAssets().openFd(track.getFilename());
                mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mMediaPlayer.setLooping(true);
                mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
                    public void onPrepared(MediaPlayer mp) {
                        BackgroundMusicService.mMediaPlayer.setVolume(1.0f, 1.0f);
                        if (!BackgroundMusicService.this.mStartPaused) {
                            mp.start();
                        }
                    }
                });
                mMediaPlayer.prepareAsync();
            } catch (IllegalStateException e3) {
                e = e3;
                Log.e(BackgroundMusicService.class.getName(), "Error playing background music track", e);
            } catch (IOException e4) {
                e = e4;
                Log.e(BackgroundMusicService.class.getName(), "Error playing background music track", e);
            }
        }
    }

    @DebugLog
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        unregisterReceiver(this.mPauseMusicReceiver);
        unregisterReceiver(this.mResumeMusicReceiver);
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).unregisterOnSharedPreferenceChangeListener(this);
    }

    public static void startMe(Context c, BackgroundMusicTrack track) {
        startMe(c, track, false);
    }

    @DebugLog
    public static void startMe(Context c, BackgroundMusicTrack track, boolean startPaused) {
        Intent intent = new Intent(c, BackgroundMusicService.class);
        intent.putExtra(KEY_MUSIC, track);
        intent.putExtra(KEY_START_PAUSED, startPaused);
        c.startService(intent);
    }

    public static void stopMe(Context c) {
        c.stopService(new Intent(c, BackgroundMusicService.class));
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (!key.equals(SettingsManager.KEY_ENABLE_ADULT_DASHBOARD_BACKGROUND_MUSIC)) {
            return;
        }
        if (isBackgroundMusicEnabled()) {
            playTrack(this.mCurrentTrack);
        } else if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    private boolean isBackgroundMusicEnabled() {
        return this.mCurrentTrack == BackgroundMusicTrack.FRONT_END ? SettingsManager.isAdultDashboardMusicEnabled(getApplicationContext()) : SettingsManager.isChildDashboardMusicEnabled(getApplicationContext());
    }
}
