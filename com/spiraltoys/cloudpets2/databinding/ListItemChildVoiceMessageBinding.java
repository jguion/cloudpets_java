package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.VoiceMessage;

public class ListItemChildVoiceMessageBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private VoiceMessage mVoiceMessage;
    private final GridLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    public final ImageView profilePhoto;

    static {
        sViewsWithIds.put(R.id.profile_photo, 5);
    }

    public ListItemChildVoiceMessageBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 6, sIncludes, sViewsWithIds);
        this.mboundView0 = (GridLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.profilePhoto = (ImageView) bindings[5];
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
            case 6:
                setVoiceMessage((VoiceMessage) variable);
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

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int IsViewedVoiceMessageGONEViewVISIBLEView = 0;
        String IsLessThanAMinuteOldVoiceMessageAndroidStringLabelLessThanAMinuteAgoRelativeCreationDateStringVoiceMessage = null;
        boolean isLessThanAMinuteOldVoiceMessage = false;
        VoiceMessage voiceMessage = this.mVoiceMessage;
        String relativeCreationDateStringVoiceMessage = null;
        Profile senderVoiceMessage = null;
        int IsViewedVoiceMessageVISIBLEViewGONEView = 0;
        String displayNameSenderVoiceMessage = null;
        boolean isViewedVoiceMessage = false;
        if ((3 & dirtyFlags) != 0) {
            if (voiceMessage != null) {
                isLessThanAMinuteOldVoiceMessage = voiceMessage.isLessThanAMinuteOld();
            }
            if (isLessThanAMinuteOldVoiceMessage) {
                dirtyFlags |= 32;
            } else {
                dirtyFlags |= 16;
            }
            if (voiceMessage != null) {
                senderVoiceMessage = voiceMessage.getSender();
            }
            if (senderVoiceMessage != null) {
                displayNameSenderVoiceMessage = senderVoiceMessage.getDisplayName();
            }
            if (voiceMessage != null) {
                isViewedVoiceMessage = voiceMessage.isViewed();
            }
            if (isViewedVoiceMessage) {
                dirtyFlags = (dirtyFlags | 8) | 128;
            } else {
                dirtyFlags = (dirtyFlags | 4) | 64;
            }
            IsViewedVoiceMessageGONEViewVISIBLEView = isViewedVoiceMessage ? 8 : 0;
            IsViewedVoiceMessageVISIBLEViewGONEView = isViewedVoiceMessage ? 0 : 8;
        }
        if (!((16 & dirtyFlags) == 0 || voiceMessage == null)) {
            relativeCreationDateStringVoiceMessage = voiceMessage.getRelativeCreationDateString();
        }
        if ((3 & dirtyFlags) != 0) {
            IsLessThanAMinuteOldVoiceMessageAndroidStringLabelLessThanAMinuteAgoRelativeCreationDateStringVoiceMessage = isLessThanAMinuteOldVoiceMessage ? getRoot().getResources().getString(R.string.label_less_than_a_minute_ago) : relativeCreationDateStringVoiceMessage;
        }
        if ((3 & dirtyFlags) != 0) {
            this.mboundView1.setVisibility(IsViewedVoiceMessageGONEViewVISIBLEView);
            this.mboundView3.setVisibility(IsViewedVoiceMessageGONEViewVISIBLEView);
        }
        if ((3 & dirtyFlags) != 0) {
            this.mboundView2.setText(displayNameSenderVoiceMessage);
            this.mboundView3.setText(displayNameSenderVoiceMessage);
        }
        if ((3 & dirtyFlags) != 0) {
            this.mboundView2.setVisibility(IsViewedVoiceMessageVISIBLEViewGONEView);
        }
        if ((3 & dirtyFlags) != 0) {
            this.mboundView4.setText(IsLessThanAMinuteOldVoiceMessageAndroidStringLabelLessThanAMinuteAgoRelativeCreationDateStringVoiceMessage);
        }
    }

    public static ListItemChildVoiceMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ListItemChildVoiceMessageBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_child_voice_message, root, attachToRoot);
    }

    public static ListItemChildVoiceMessageBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.list_item_child_voice_message, null, false));
    }

    public static ListItemChildVoiceMessageBinding bind(View view) {
        if ("layout/list_item_child_voice_message_0".equals(view.getTag())) {
            return new ListItemChildVoiceMessageBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
