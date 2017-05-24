package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.VoiceMessage;

public class ActivityAdultVoiceMessageBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final Button approveVoiceMessageButton;
    public final LinearLayout buttonPanel;
    public final Button deleteVoiceMessageButton;
    private long mDirtyFlags = -1;
    private final LinearLayout mboundView0;
    public final ScrollView voiceMessagePlayerContainer;

    static {
        sViewsWithIds.put(R.id.voice_message_player_container, 1);
        sViewsWithIds.put(R.id.button_panel, 2);
        sViewsWithIds.put(R.id.approve_voice_message_button, 3);
        sViewsWithIds.put(R.id.delete_voice_message_button, 4);
    }

    public ActivityAdultVoiceMessageBinding(View root) {
        super(root, 0);
        Object[] bindings = ViewDataBinding.mapBindings(root, 5, sIncludes, sViewsWithIds);
        this.approveVoiceMessageButton = (Button) bindings[3];
        this.buttonPanel = (LinearLayout) bindings[2];
        this.deleteVoiceMessageButton = (Button) bindings[4];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.voiceMessagePlayerContainer = (ScrollView) bindings[1];
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
        return false;
    }

    public void setVoiceMessage(VoiceMessage voiceMessage) {
    }

    public VoiceMessage getVoiceMessage() {
        return null;
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

    public static ActivityAdultVoiceMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ActivityAdultVoiceMessageBinding) DataBindingUtil.inflate(inflater, R.layout.activity_adult_voice_message, root, attachToRoot);
    }

    public static ActivityAdultVoiceMessageBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.activity_adult_voice_message, null, false));
    }

    public static ActivityAdultVoiceMessageBinding bind(View view) {
        if ("layout/activity_adult_voice_message_0".equals(view.getTag())) {
            return new ActivityAdultVoiceMessageBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
