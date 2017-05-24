package com.spiraltoys.cloudpets2.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.ViewDataBinding.IncludedLayouts;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PrivateProfile;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.util.VoiceMessageDateFormatter;
import com.spiraltoys.cloudpets2.views.RippleView;

public class FragmentVoiceMessagePlayerBinding extends ViewDataBinding {
    private static final IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    public final FrameLayout audioPlayerContainer;
    private long mDirtyFlags = -1;
    private boolean mIsParentalControlEnabled;
    private VoiceMessage mVoiceMessage;
    private VoiceMessageDateFormatter mVoiceMessageDateFormatter;
    private final FrameLayout mboundView0;
    private final TextView mboundView1;
    private final FrameLayout mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    public final ImageView messageForMeCharacterImage;
    public final TextView messageTitle;
    public final RippleView playbackRippleEffect;
    public final ImageView profilePhoto;

    static {
        sViewsWithIds.put(R.id.messageForMeCharacterImage, 5);
        sViewsWithIds.put(R.id.playback_ripple_effect, 6);
        sViewsWithIds.put(R.id.profile_photo, 7);
        sViewsWithIds.put(R.id.message_title, 8);
        sViewsWithIds.put(R.id.audio_player_container, 9);
    }

    public FragmentVoiceMessagePlayerBinding(View root) {
        super(root, 0);
        Object[] bindings = mapBindings(root, 10, sIncludes, sViewsWithIds);
        this.audioPlayerContainer = (FrameLayout) bindings[9];
        this.mboundView0 = (FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (FrameLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.messageForMeCharacterImage = (ImageView) bindings[5];
        this.messageTitle = (TextView) bindings[8];
        this.playbackRippleEffect = (RippleView) bindings[6];
        this.profilePhoto = (ImageView) bindings[7];
        setRootTag(root);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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

    public void setIsParentalControlEnabled(boolean isParentalControlEnabled) {
        this.mIsParentalControlEnabled = isParentalControlEnabled;
        synchronized (this) {
            this.mDirtyFlags |= 4;
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
        int LogicalNotIsParentalControlEnabledBooleantrueIsAdultPrivateProfileRecipientVoiceMessageGONEViewVISIBLEView = 0;
        boolean isApprovedVoiceMessage = false;
        boolean isLessThanAMinuteOldVoiceMessage = false;
        VoiceMessage voiceMessage = this.mVoiceMessage;
        int IsApprovedVoiceMessageVISIBLEViewGONEView = 0;
        String detailedRelativeCreationDateStringVoiceMessageDateFormatter = null;
        VoiceMessageDateFormatter voiceMessageDateFormatter = this.mVoiceMessageDateFormatter;
        int IsApprovedVoiceMessageGONEViewVISIBLEView = 0;
        boolean isParentalControlEnabled = this.mIsParentalControlEnabled;
        String IsLessThanAMinuteOldVoiceMessageAndroidStringLabelDetailLessThanAMinuteAgoDetailedRelativeCreationDateStringVoiceMessageDateFormatter = null;
        Profile recipientVoiceMessage = null;
        boolean logicalNotIsParentalControlEnabled = false;
        boolean isAdultPrivateProfileRecipientVoiceMessage = false;
        PrivateProfile privateProfileRecipientVoiceMessage = null;
        if ((11 & dirtyFlags) != 0) {
            if ((9 & dirtyFlags) != 0) {
                if (voiceMessage != null) {
                    isApprovedVoiceMessage = voiceMessage.isApproved();
                }
                if (isApprovedVoiceMessage) {
                    dirtyFlags = (dirtyFlags | 128) | PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                } else {
                    dirtyFlags = (dirtyFlags | 64) | 1024;
                }
                IsApprovedVoiceMessageVISIBLEViewGONEView = isApprovedVoiceMessage ? 0 : 8;
                IsApprovedVoiceMessageGONEViewVISIBLEView = isApprovedVoiceMessage ? 8 : 0;
            }
            if (voiceMessage != null) {
                isLessThanAMinuteOldVoiceMessage = voiceMessage.isLessThanAMinuteOld();
            }
            if (isLessThanAMinuteOldVoiceMessage) {
                dirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            } else {
                dirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
            }
        }
        if ((13 & dirtyFlags) != 0) {
            logicalNotIsParentalControlEnabled = !isParentalControlEnabled;
            if (logicalNotIsParentalControlEnabled) {
                dirtyFlags |= 512;
            } else {
                dirtyFlags |= 256;
            }
        }
        if ((256 & dirtyFlags) != 0) {
            if (voiceMessage != null) {
                recipientVoiceMessage = voiceMessage.getRecipient();
            }
            if (recipientVoiceMessage != null) {
                privateProfileRecipientVoiceMessage = recipientVoiceMessage.getPrivateProfile();
            }
            if (privateProfileRecipientVoiceMessage != null) {
                isAdultPrivateProfileRecipientVoiceMessage = privateProfileRecipientVoiceMessage.isAdult();
            }
        }
        if (!((PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM & dirtyFlags) == 0 || voiceMessageDateFormatter == null)) {
            detailedRelativeCreationDateStringVoiceMessageDateFormatter = voiceMessageDateFormatter.getDetailedRelativeCreationDateString();
        }
        if ((13 & dirtyFlags) != 0) {
            boolean LogicalNotIsParentalControlEnabledBooleantrueIsAdultPrivateProfileRecipientVoiceMessage = logicalNotIsParentalControlEnabled ? true : isAdultPrivateProfileRecipientVoiceMessage;
            if (LogicalNotIsParentalControlEnabledBooleantrueIsAdultPrivateProfileRecipientVoiceMessage) {
                dirtyFlags |= 32;
            } else {
                dirtyFlags |= 16;
            }
            LogicalNotIsParentalControlEnabledBooleantrueIsAdultPrivateProfileRecipientVoiceMessageGONEViewVISIBLEView = LogicalNotIsParentalControlEnabledBooleantrueIsAdultPrivateProfileRecipientVoiceMessage ? 8 : 0;
        }
        if ((11 & dirtyFlags) != 0) {
            if (isLessThanAMinuteOldVoiceMessage) {
                IsLessThanAMinuteOldVoiceMessageAndroidStringLabelDetailLessThanAMinuteAgoDetailedRelativeCreationDateStringVoiceMessageDateFormatter = getRoot().getResources().getString(R.string.label_detail_less_than_a_minute_ago);
            } else {
                IsLessThanAMinuteOldVoiceMessageAndroidStringLabelDetailLessThanAMinuteAgoDetailedRelativeCreationDateStringVoiceMessageDateFormatter = detailedRelativeCreationDateStringVoiceMessageDateFormatter;
            }
        }
        if ((11 & dirtyFlags) != 0) {
            this.mboundView1.setText(IsLessThanAMinuteOldVoiceMessageAndroidStringLabelDetailLessThanAMinuteAgoDetailedRelativeCreationDateStringVoiceMessageDateFormatter);
        }
        if ((13 & dirtyFlags) != 0) {
            this.mboundView2.setVisibility(LogicalNotIsParentalControlEnabledBooleantrueIsAdultPrivateProfileRecipientVoiceMessageGONEViewVISIBLEView);
        }
        if ((9 & dirtyFlags) != 0) {
            this.mboundView3.setVisibility(IsApprovedVoiceMessageGONEViewVISIBLEView);
        }
        if ((9 & dirtyFlags) != 0) {
            this.mboundView4.setVisibility(IsApprovedVoiceMessageVISIBLEViewGONEView);
        }
    }

    public static FragmentVoiceMessagePlayerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return (FragmentVoiceMessagePlayerBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_voice_message_player, root, attachToRoot);
    }

    public static FragmentVoiceMessagePlayerBinding inflate(LayoutInflater inflater) {
        return bind(inflater.inflate(R.layout.fragment_voice_message_player, null, false));
    }

    public static FragmentVoiceMessagePlayerBinding bind(View view) {
        if ("layout/fragment_voice_message_player_0".equals(view.getTag())) {
            return new FragmentVoiceMessagePlayerBinding(view);
        }
        throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
    }
}
