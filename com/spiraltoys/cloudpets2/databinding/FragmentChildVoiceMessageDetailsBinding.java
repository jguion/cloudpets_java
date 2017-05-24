package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.util.VoiceMessageDateFormatter;

public class FragmentChildVoiceMessageDetailsBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final GridLayout buttonPanel;
    public final ImageView childDetailMessageCharacter;
    public final FloatingActionButton deleteVoiceMessageButton;
    public final TextView deleteVoiceMessageButtonLabel;
    private long mDirtyFlags = -1;
    private VoiceMessage mVoiceMessage;
    private VoiceMessageDateFormatter mVoiceMessageDateFormatter;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    public final LinearLayout messageDetailsContainer;
    public final TextView messageTitle;
    public final ImageView profilePhoto;
    public final FloatingActionButton pushToToyButton;
    public final TextView pushToToyButtonLabel;

    static {
        sViewsWithIds.put(R.id.message_details_container, 2);
        sViewsWithIds.put(R.id.profile_photo, 3);
        sViewsWithIds.put(R.id.message_title, 4);
        sViewsWithIds.put(R.id.child_detail_message_character, 5);
        sViewsWithIds.put(R.id.button_panel, 6);
        sViewsWithIds.put(R.id.push_to_toy_button, 7);
        sViewsWithIds.put(R.id.delete_voice_message_button, 8);
        sViewsWithIds.put(R.id.push_to_toy_button_label, 9);
        sViewsWithIds.put(R.id.delete_voice_message_button_label, 10);
    }

    public FragmentChildVoiceMessageDetailsBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 11, sIncludes, sViewsWithIds);
        this.buttonPanel = (GridLayout) bindings[6];
        this.childDetailMessageCharacter = (ImageView) bindings[5];
        this.deleteVoiceMessageButton = (FloatingActionButton) bindings[8];
        this.deleteVoiceMessageButtonLabel = (TextView) bindings[10];
        this.mboundView0 = (LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.messageDetailsContainer = (LinearLayout) bindings[2];
        this.messageTitle = (TextView) bindings[4];
        this.profilePhoto = (ImageView) bindings[3];
        this.pushToToyButton = (FloatingActionButton) bindings[7];
        this.pushToToyButtonLabel = (TextView) bindings[9];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
            case 6:
                setVoiceMessage((VoiceMessage) variable);
                return true;
            case 7:
                setVoiceMessageDateFormatter((VoiceMessageDateFormatter) variable);
                return true;
            default:
                return false;
        }
    }

    public void setVoiceMessage(VoiceMessage voiceMessage) {
        this.mVoiceMessage = voiceMessage;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        super.requestRebind();
    }

    public VoiceMessage getVoiceMessage() {
        return this.mVoiceMessage;
    }

    public void setVoiceMessageDateFormatter(VoiceMessageDateFormatter voiceMessageDateFormatter) {
        this.mVoiceMessageDateFormatter = voiceMessageDateFormatter;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        super.requestRebind();
    }

    public VoiceMessageDateFormatter getVoiceMessageDateFormatter() {
        return this.mVoiceMessageDateFormatter;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        boolean isLessThanAMinuteOldVoiceMessage = false;
        VoiceMessage voiceMessage = this.mVoiceMessage;
        String detailedRelativeCreationDateStringVoiceMessageDateFormatter = null;
        VoiceMessageDateFormatter voiceMessageDateFormatter = this.mVoiceMessageDateFormatter;
        String IsLessThanAMinuteOldVoiceMessageAndroidStringLabelDetailLessThanAMinuteAgoDetailedRelativeCreationDateStringVoiceMessageDateFormatter = null;
        if ((7 & dirtyFlags) != 0) {
            if (voiceMessage != null) {
                isLessThanAMinuteOldVoiceMessage = voiceMessage.isLessThanAMinuteOld();
            }
            if (isLessThanAMinuteOldVoiceMessage) {
                dirtyFlags |= 16;
            } else {
                dirtyFlags |= 8;
            }
        }
        if (!((8 & dirtyFlags) == 0 || voiceMessageDateFormatter == null)) {
            detailedRelativeCreationDateStringVoiceMessageDateFormatter = voiceMessageDateFormatter.getDetailedRelativeCreationDateString();
        }
        if ((7 & dirtyFlags) != 0) {
            IsLessThanAMinuteOldVoiceMessageAndroidStringLabelDetailLessThanAMinuteAgoDetailedRelativeCreationDateStringVoiceMessageDateFormatter = isLessThanAMinuteOldVoiceMessage ? getRoot().getResources().getString(R.string.label_detail_less_than_a_minute_ago) : detailedRelativeCreationDateStringVoiceMessageDateFormatter;
        }
        if ((7 & dirtyFlags) != 0) {
            this.mboundView1.setText(IsLessThanAMinuteOldVoiceMessageAndroidStringLabelDetailLessThanAMinuteAgoDetailedRelativeCreationDateStringVoiceMessageDateFormatter);
        }
    }

    public static FragmentChildVoiceMessageDetailsBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentChildVoiceMessageDetailsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_voice_message_details, root, attachToRoot);
    }

    public static FragmentChildVoiceMessageDetailsBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_child_voice_message_details, null, false));
    }

    public static FragmentChildVoiceMessageDetailsBinding bind(View view) {
        if ("layout/fragment_child_voice_message_details_0".equals(view.getTag())) {
            return new FragmentChildVoiceMessageDetailsBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
