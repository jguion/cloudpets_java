package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.views.RippleView;

public class FragmentRecordVoiceMessageBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final TextView currentPlaybackTime;
    private long mDirtyFlags = -1;
    private final FrameLayout mboundView0;
    public final ProgressBar progressBar;
    public final FloatingActionButton recordMessageButton;
    public final RippleView recordRippleEffect;
    public final TextView trackLength;

    static {
        sViewsWithIds.put(R.id.progress_bar, 1);
        sViewsWithIds.put(R.id.current_playback_time, 2);
        sViewsWithIds.put(R.id.track_length, 3);
        sViewsWithIds.put(R.id.record_ripple_effect, 4);
        sViewsWithIds.put(R.id.record_message_button, 5);
    }

    public FragmentRecordVoiceMessageBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 6, sIncludes, sViewsWithIds);
        this.currentPlaybackTime = (TextView) bindings[2];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressBar = (ProgressBar) bindings[1];
        this.recordMessageButton = (FloatingActionButton) bindings[5];
        this.recordRippleEffect = (RippleView) bindings[4];
        this.trackLength = (TextView) bindings[3];
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

    public static FragmentRecordVoiceMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentRecordVoiceMessageBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_record_voice_message, root, attachToRoot);
    }

    public static FragmentRecordVoiceMessageBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_record_voice_message, null, false));
    }

    public static FragmentRecordVoiceMessageBinding bind(View view) {
        if ("layout/fragment_record_voice_message_0".equals(view.getTag())) {
            return new FragmentRecordVoiceMessageBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
