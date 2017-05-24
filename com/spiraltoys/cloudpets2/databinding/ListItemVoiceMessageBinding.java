package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import com.parse.ParseFileUtils;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PrivateProfile;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.VoiceMessage;

public class ListItemVoiceMessageBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags = -1;
    private boolean mIsParentalControlEnabled;
    private VoiceMessage mVoiceMessage;
    private final GridLayout mboundView0;
    private final Space mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final Space mboundView12;
    private final Space mboundView13;
    private final FrameLayout mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final Space mboundView6;
    private final Space mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;
    public final ImageView profilePhoto;

    static {
        sViewsWithIds.put(R.id.profile_photo, 14);
    }

    public ListItemVoiceMessageBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 15, sIncludes, sViewsWithIds);
        this.mboundView0 = (GridLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (Space) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView10 = (TextView) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (TextView) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView12 = (Space) bindings[12];
        this.mboundView12.setTag(null);
        this.mboundView13 = (Space) bindings[13];
        this.mboundView13.setTag(null);
        this.mboundView2 = (FrameLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (Space) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (Space) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (TextView) bindings[8];
        this.mboundView8.setTag(null);
        this.mboundView9 = (TextView) bindings[9];
        this.mboundView9.setTag(null);
        this.profilePhoto = (ImageView) bindings[14];
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
            case 2:
                setIsParentalControlEnabled(((Boolean) variable).booleanValue());
                return true;
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

    public void setIsParentalControlEnabled(boolean isParentalControlEnabled) {
        this.mIsParentalControlEnabled = isParentalControlEnabled;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        super.requestRebind();
    }

    public boolean getIsParentalControlEnabled() {
        return this.mIsParentalControlEnabled;
    }

    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    protected void executeBindings() {
        synchronized (this) {
            long dirtyFlags = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        int IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageVISIBLEViewGONEView = 0;
        int IsAdultPrivateProfileRecipientVoiceMessageVISIBLEViewGONEView = 0;
        int IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageBooleantrueIsParentalControlEnabledLogicalNotIsAdultPrivateProfileRecipientVoiceMessageBooleanfalseGONEViewVISIBLEView = 0;
        boolean isApprovedVoiceMessage = false;
        int IsAdultPrivateProfileRecipientVoiceMessageGONEViewVISIBLEView = 0;
        String IsLessThanAMinuteOldVoiceMessageAndroidStringLabelLessThanAMinuteAgoRelativeCreationDateStringVoiceMessage = null;
        String IsAdultPrivateProfileRecipientVoiceMessageAndroidStringMessageForMeAndroidStringMessageForDisplayNameRecipientVoiceMessage = null;
        boolean isLessThanAMinuteOldVoiceMessage = false;
        VoiceMessage voiceMessage = this.mVoiceMessage;
        boolean IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalse = false;
        boolean isParentViewedVoiceMessage = false;
        boolean logicalNotIsAdultPrivateProfileRecipientVoiceMessage = false;
        int IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageGONEViewVISIBLEView = 0;
        String relativeCreationDateStringVoiceMessage = null;
        boolean IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessage = false;
        int IsApprovedVoiceMessageVISIBLEViewGONEView = 0;
        Profile senderVoiceMessage = null;
        int IsApprovedVoiceMessageGONEViewVISIBLEView = 0;
        boolean isParentalControlEnabled = this.mIsParentalControlEnabled;
        String AndroidStringMessageForDisplayNameRecipientVoiceMessage = null;
        Profile recipientVoiceMessage = null;
        String displayNameRecipientVoiceMessage = null;
        boolean isAdultPrivateProfileRecipientVoiceMessage = false;
        PrivateProfile privateProfileRecipientVoiceMessage = null;
        String displayNameSenderVoiceMessage = null;
        boolean isViewedVoiceMessage = false;
        int IsAdultPrivateProfileRecipientVoiceMessageLogicalNotIsParentalControlEnabledGONEViewVISIBLEView = 0;
        boolean IsParentalControlEnabledLogicalNotIsAdultPrivateProfileRecipientVoiceMessageBooleanfalse = false;
        if ((7 & dirtyFlags) != 0) {
            if ((5 & dirtyFlags) != 0) {
                if (voiceMessage != null) {
                    isApprovedVoiceMessage = voiceMessage.isApproved();
                }
                if (isApprovedVoiceMessage) {
                    dirtyFlags = (dirtyFlags | 4194304) | 16777216;
                } else {
                    dirtyFlags = (dirtyFlags | 2097152) | 8388608;
                }
                IsApprovedVoiceMessageVISIBLEViewGONEView = isApprovedVoiceMessage ? 0 : 8;
                IsApprovedVoiceMessageGONEViewVISIBLEView = isApprovedVoiceMessage ? 8 : 0;
            }
            if ((5 & dirtyFlags) != 0) {
                if (voiceMessage != null) {
                    isLessThanAMinuteOldVoiceMessage = voiceMessage.isLessThanAMinuteOld();
                }
                if (isLessThanAMinuteOldVoiceMessage) {
                    dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                } else {
                    dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                }
            }
            if ((5 & dirtyFlags) != 0) {
                if (voiceMessage != null) {
                    senderVoiceMessage = voiceMessage.getSender();
                }
                if (senderVoiceMessage != null) {
                    displayNameSenderVoiceMessage = senderVoiceMessage.getDisplayName();
                }
            }
            if (voiceMessage != null) {
                recipientVoiceMessage = voiceMessage.getRecipient();
            }
            if (recipientVoiceMessage != null) {
                privateProfileRecipientVoiceMessage = recipientVoiceMessage.getPrivateProfile();
            }
            if (privateProfileRecipientVoiceMessage != null) {
                isAdultPrivateProfileRecipientVoiceMessage = privateProfileRecipientVoiceMessage.isAdult();
            }
            if (isAdultPrivateProfileRecipientVoiceMessage) {
                dirtyFlags = (((dirtyFlags | 64) | 1024) | 16384) | 65536;
            } else {
                dirtyFlags = (((dirtyFlags | 32) | 512) | PlaybackStateCompat.ACTION_PLAY_FROM_URI) | 32768;
            }
            if ((5 & dirtyFlags) != 0) {
                IsAdultPrivateProfileRecipientVoiceMessageVISIBLEViewGONEView = isAdultPrivateProfileRecipientVoiceMessage ? 0 : 8;
            }
            if ((5 & dirtyFlags) != 0) {
                IsAdultPrivateProfileRecipientVoiceMessageGONEViewVISIBLEView = isAdultPrivateProfileRecipientVoiceMessage ? 8 : 0;
            }
        }
        if ((7 & dirtyFlags) != 0) {
            if (isParentalControlEnabled) {
                dirtyFlags |= 1073741824;
            } else {
                dirtyFlags |= 536870912;
            }
            boolean isAdultPrivateProfileRecipientVoiceMessageLogicalNotIsParentalControlEnabled = isAdultPrivateProfileRecipientVoiceMessage | (!isParentalControlEnabled);
            if (isAdultPrivateProfileRecipientVoiceMessageLogicalNotIsParentalControlEnabled) {
                dirtyFlags |= 268435456;
            } else {
                dirtyFlags |= 134217728;
            }
            IsAdultPrivateProfileRecipientVoiceMessageLogicalNotIsParentalControlEnabledGONEViewVISIBLEView = isAdultPrivateProfileRecipientVoiceMessageLogicalNotIsParentalControlEnabled ? 8 : 0;
        }
        if ((1073741824 & dirtyFlags) != 0) {
            logicalNotIsAdultPrivateProfileRecipientVoiceMessage = !isAdultPrivateProfileRecipientVoiceMessage;
        }
        if (!((PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH & dirtyFlags) == 0 || voiceMessage == null)) {
            relativeCreationDateStringVoiceMessage = voiceMessage.getRelativeCreationDateString();
        }
        if ((PlaybackStateCompat.ACTION_PLAY_FROM_URI & dirtyFlags) != 0) {
            if (recipientVoiceMessage != null) {
                displayNameRecipientVoiceMessage = recipientVoiceMessage.getDisplayName();
            }
            AndroidStringMessageForDisplayNameRecipientVoiceMessage = getRoot().getResources().getString(R.string.message_for, new Object[]{displayNameRecipientVoiceMessage});
        }
        if (!((65536 & dirtyFlags) == 0 || voiceMessage == null)) {
            isViewedVoiceMessage = voiceMessage.isViewed();
        }
        if ((5 & dirtyFlags) != 0) {
            IsLessThanAMinuteOldVoiceMessageAndroidStringLabelLessThanAMinuteAgoRelativeCreationDateStringVoiceMessage = isLessThanAMinuteOldVoiceMessage ? getRoot().getResources().getString(R.string.label_less_than_a_minute_ago) : relativeCreationDateStringVoiceMessage;
        }
        if ((5 & dirtyFlags) != 0) {
            if (isAdultPrivateProfileRecipientVoiceMessage) {
                IsAdultPrivateProfileRecipientVoiceMessageAndroidStringMessageForMeAndroidStringMessageForDisplayNameRecipientVoiceMessage = getRoot().getResources().getString(R.string.message_for_me);
            } else {
                IsAdultPrivateProfileRecipientVoiceMessageAndroidStringMessageForMeAndroidStringMessageForDisplayNameRecipientVoiceMessage = AndroidStringMessageForDisplayNameRecipientVoiceMessage;
            }
        }
        if ((7 & dirtyFlags) != 0) {
            IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalse = isAdultPrivateProfileRecipientVoiceMessage ? isViewedVoiceMessage : false;
            if (IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalse) {
                dirtyFlags |= ParseFileUtils.ONE_MB;
            } else {
                dirtyFlags |= 524288;
            }
        }
        if (!((524288 & dirtyFlags) == 0 || voiceMessage == null)) {
            isParentViewedVoiceMessage = voiceMessage.isParentViewed();
        }
        if ((7 & dirtyFlags) != 0) {
            IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessage = IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalse ? true : isParentViewedVoiceMessage;
            if (IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessage) {
                dirtyFlags = ((dirtyFlags | 16) | 262144) | 67108864;
            } else {
                dirtyFlags = ((dirtyFlags | 8) | 131072) | 33554432;
            }
            if ((5 & dirtyFlags) != 0) {
                IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageVISIBLEViewGONEView = IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessage ? 0 : 8;
            }
            if ((5 & dirtyFlags) != 0) {
                IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageGONEViewVISIBLEView = IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessage ? 8 : 0;
            }
        }
        if ((33554432 & dirtyFlags) != 0) {
            if (isParentalControlEnabled) {
                dirtyFlags |= 1073741824;
            } else {
                dirtyFlags |= 536870912;
            }
            IsParentalControlEnabledLogicalNotIsAdultPrivateProfileRecipientVoiceMessageBooleanfalse = isParentalControlEnabled ? logicalNotIsAdultPrivateProfileRecipientVoiceMessage : false;
        }
        if ((7 & dirtyFlags) != 0) {
            boolean IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageBooleantrueIsParentalControlEnabledLogicalNotIsAdultPrivateProfileRecipientVoiceMessageBooleanfalse;
            if (IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessage) {
                IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageBooleantrueIsParentalControlEnabledLogicalNotIsAdultPrivateProfileRecipientVoiceMessageBooleanfalse = true;
            } else {
                IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageBooleantrueIsParentalControlEnabledLogicalNotIsAdultPrivateProfileRecipientVoiceMessageBooleanfalse = IsParentalControlEnabledLogicalNotIsAdultPrivateProfileRecipientVoiceMessageBooleanfalse;
            }
            if (IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageBooleantrueIsParentalControlEnabledLogicalNotIsAdultPrivateProfileRecipientVoiceMessageBooleanfalse) {
                dirtyFlags |= 256;
            } else {
                dirtyFlags |= 128;
            }
            IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageBooleantrueIsParentalControlEnabledLogicalNotIsAdultPrivateProfileRecipientVoiceMessageBooleanfalseGONEViewVISIBLEView = IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageBooleantrueIsParentalControlEnabledLogicalNotIsAdultPrivateProfileRecipientVoiceMessageBooleanfalse ? 8 : 0;
        }
        if ((5 & dirtyFlags) != 0) {
            this.mboundView1.setVisibility(IsAdultPrivateProfileRecipientVoiceMessageGONEViewVISIBLEView);
            this.mboundView10.setVisibility(IsAdultPrivateProfileRecipientVoiceMessageGONEViewVISIBLEView);
            this.mboundView13.setVisibility(IsAdultPrivateProfileRecipientVoiceMessageGONEViewVISIBLEView);
            this.mboundView7.setVisibility(IsAdultPrivateProfileRecipientVoiceMessageGONEViewVISIBLEView);
        }
        if ((5 & dirtyFlags) != 0) {
            this.mboundView10.setText(IsAdultPrivateProfileRecipientVoiceMessageAndroidStringMessageForMeAndroidStringMessageForDisplayNameRecipientVoiceMessage);
        }
        if ((5 & dirtyFlags) != 0) {
            this.mboundView11.setText(IsLessThanAMinuteOldVoiceMessageAndroidStringLabelLessThanAMinuteAgoRelativeCreationDateStringVoiceMessage);
        }
        if ((5 & dirtyFlags) != 0) {
            this.mboundView12.setVisibility(IsAdultPrivateProfileRecipientVoiceMessageVISIBLEViewGONEView);
            this.mboundView6.setVisibility(IsAdultPrivateProfileRecipientVoiceMessageVISIBLEViewGONEView);
        }
        if ((7 & dirtyFlags) != 0) {
            this.mboundView2.setVisibility(IsAdultPrivateProfileRecipientVoiceMessageLogicalNotIsParentalControlEnabledGONEViewVISIBLEView);
        }
        if ((5 & dirtyFlags) != 0) {
            this.mboundView3.setVisibility(IsApprovedVoiceMessageGONEViewVISIBLEView);
        }
        if ((5 & dirtyFlags) != 0) {
            this.mboundView4.setVisibility(IsApprovedVoiceMessageVISIBLEViewGONEView);
        }
        if ((7 & dirtyFlags) != 0) {
            this.mboundView5.setVisibility(IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageBooleantrueIsParentalControlEnabledLogicalNotIsAdultPrivateProfileRecipientVoiceMessageBooleanfalseGONEViewVISIBLEView);
        }
        if ((5 & dirtyFlags) != 0) {
            this.mboundView8.setText(displayNameSenderVoiceMessage);
            this.mboundView9.setText(displayNameSenderVoiceMessage);
        }
        if ((5 & dirtyFlags) != 0) {
            this.mboundView8.setVisibility(IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageVISIBLEViewGONEView);
        }
        if ((5 & dirtyFlags) != 0) {
            this.mboundView9.setVisibility(IsAdultPrivateProfileRecipientVoiceMessageIsViewedVoiceMessageBooleanfalseBooleantrueIsParentViewedVoiceMessageGONEViewVISIBLEView);
        }
    }

    public static ListItemVoiceMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (ListItemVoiceMessageBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_voice_message, root, attachToRoot);
    }

    public static ListItemVoiceMessageBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.list_item_voice_message, null, false));
    }

    public static ListItemVoiceMessageBinding bind(View view) {
        if ("layout/list_item_voice_message_0".equals(view.getTag())) {
            return new ListItemVoiceMessageBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
