package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import com.spiraltoys.cloudpets2.free.R;

public class FragmentRecordedVoiceMessagePlaybackBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final FrameLayout audioPlayerContainer;
    public final Button cancelButton;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final Button selectRecipientsButton;

    static {
        sViewsWithIds.put(R.id.audio_player_container, 1);
        sViewsWithIds.put(R.id.cancel_button, 2);
        sViewsWithIds.put(R.id.select_recipients_button, 3);
    }

    public FragmentRecordedVoiceMessagePlaybackBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 4, sIncludes, sViewsWithIds);
        this.audioPlayerContainer = (FrameLayout) bindings[1];
        this.cancelButton = (Button) bindings[2];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.selectRecipientsButton = (Button) bindings[3];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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
        return false;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
    }

    public static FragmentRecordedVoiceMessagePlaybackBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentRecordedVoiceMessagePlaybackBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_recorded_voice_message_playback, root, attachToRoot);
    }

    public static FragmentRecordedVoiceMessagePlaybackBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_recorded_voice_message_playback, null, false));
    }

    public static FragmentRecordedVoiceMessagePlaybackBinding bind(View view) {
        if ("layout/fragment_recorded_voice_message_playback_0".equals(view.getTag())) {
            return new FragmentRecordedVoiceMessagePlaybackBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
