package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class FragmentAudioPlayerBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final LinearLayout audioPlayerContainer;
    public final TextView currentPlaybackTime;
    private Boolean mAdultRecordingNewMessage;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final LinearLayout noConnectionNotice;
    public final FloatingActionButton pauseButton;
    public final FloatingActionButton playButton;
    public final MaterialProgressBar progressBar;
    public final SeekBar seekBar;
    public final TextView trackLength;

    static {
        sViewsWithIds.put(R.id.audio_player_container, 2);
        sViewsWithIds.put(R.id.progress_bar, 3);
        sViewsWithIds.put(R.id.current_playback_time, 4);
        sViewsWithIds.put(R.id.track_length, 5);
        sViewsWithIds.put(R.id.play_button, 6);
        sViewsWithIds.put(R.id.pause_button, 7);
        sViewsWithIds.put(R.id.no_connection_notice, 8);
    }

    public FragmentAudioPlayerBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 9, sIncludes, sViewsWithIds);
        this.audioPlayerContainer = (LinearLayout) bindings[2];
        this.currentPlaybackTime = (TextView) bindings[4];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.noConnectionNotice = (LinearLayout) bindings[8];
        this.pauseButton = (FloatingActionButton) bindings[7];
        this.playButton = (FloatingActionButton) bindings[6];
        this.progressBar = (MaterialProgressBar) bindings[3];
        this.seekBar = (SeekBar) bindings[1];
        this.seekBar.setTag(null);
        this.trackLength = (TextView) bindings[5];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    private void log(String msg, long i) {
        Log.d("BINDER", msg + ":" + Long.toHexString(i));
    }

    public boolean setVariable(int variableId, Object variable) {
        switch (variableId) {
            case 1:
                setAdultRecordingNewMessage((Boolean) variable);
                return true;
            default:
                return false;
        }
    }

    public void setAdultRecordingNewMessage(Boolean adultRecordingNewMessage) {
        this.mAdultRecordingNewMessage = adultRecordingNewMessage;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        super.requestRebind();
    }

    public Boolean getAdultRecordingNewMessage() {
        return this.mAdultRecordingNewMessage;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        Drawable AdultRecordingNewMessageAndroidDrawableSeekbarThumbRecordingAndroidDrawableSeekbarThumbPlayback = null;
        Drawable AdultRecordingNewMessageAndroidDrawableProgressPlaybackAdultRecordNewMessageAndroidDrawableProgressPlayback = null;
        Boolean adultRecordingNewMessage = this.mAdultRecordingNewMessage;
        if ((dirtyFlags & 3) != 0) {
            if (adultRecordingNewMessage.booleanValue()) {
                dirtyFlags = (dirtyFlags | 8) | 32;
            } else {
                dirtyFlags = (dirtyFlags | 4) | 16;
            }
            if (adultRecordingNewMessage != null) {
                AdultRecordingNewMessageAndroidDrawableSeekbarThumbRecordingAndroidDrawableSeekbarThumbPlayback = adultRecordingNewMessage.booleanValue() ? getRoot().getResources().getDrawable(R.drawable.seekbar_thumb_recording) : getRoot().getResources().getDrawable(R.drawable.seekbar_thumb_playback);
            }
            if (adultRecordingNewMessage != null) {
                AdultRecordingNewMessageAndroidDrawableProgressPlaybackAdultRecordNewMessageAndroidDrawableProgressPlayback = adultRecordingNewMessage.booleanValue() ? getRoot().getResources().getDrawable(R.drawable.progress_playback_adult_record_new_message) : getRoot().getResources().getDrawable(R.drawable.progress_playback);
            }
        }
        if ((dirtyFlags & 3) != 0) {
            this.seekBar.setProgressDrawable(AdultRecordingNewMessageAndroidDrawableProgressPlaybackAdultRecordNewMessageAndroidDrawableProgressPlayback);
        }
        if ((dirtyFlags & 3) != 0) {
            this.seekBar.setThumb(AdultRecordingNewMessageAndroidDrawableSeekbarThumbRecordingAndroidDrawableSeekbarThumbPlayback);
        }
    }

    public static FragmentAudioPlayerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentAudioPlayerBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_audio_player, root, attachToRoot);
    }

    public static FragmentAudioPlayerBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_audio_player, null, false));
    }

    public static FragmentAudioPlayerBinding bind(View view) {
        if ("layout/fragment_audio_player_0".equals(view.getTag())) {
            return new FragmentAudioPlayerBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
