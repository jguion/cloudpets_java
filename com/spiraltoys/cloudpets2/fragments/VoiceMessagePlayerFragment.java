package com.spiraltoys.cloudpets2.fragments;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView.BufferType;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.spiraltoys.cloudpets2.databinding.FragmentVoiceMessagePlayerBinding;
import com.spiraltoys.cloudpets2.events.VoiceMessagePlaybackCompletedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessagePlaybackPausedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessagePlaybackStartedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessageSelectedForPlaybackEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import com.spiraltoys.cloudpets2.util.VoiceMessageDateFormatter;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class VoiceMessagePlayerFragment extends Fragment {
    private FragmentVoiceMessagePlayerBinding mBinding;
    private VoiceMessage mVoiceMessage;

    public static VoiceMessagePlayerFragment newInstance(VoiceMessage voiceMessage) {
        EventBus.getDefault().postSticky(new VoiceMessageSelectedForPlaybackEvent(voiceMessage));
        return new VoiceMessagePlayerFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String str;
        this.mBinding = (FragmentVoiceMessagePlayerBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_voice_message_player, container, false);
        this.mVoiceMessage = ((VoiceMessageSelectedForPlaybackEvent) EventBus.getDefault().getStickyEvent(VoiceMessageSelectedForPlaybackEvent.class)).getVoiceMessage();
        this.mBinding.setVoiceMessage(this.mVoiceMessage);
        this.mBinding.setVoiceMessageDateFormatter(new VoiceMessageDateFormatter(inflater.getContext(), this.mVoiceMessage));
        this.mBinding.setIsParentalControlEnabled(SettingsManager.isParentalControlEnabled(getActivity()));
        if (this.mVoiceMessage.getRecipient().getPrivateProfile().isAdult()) {
            setAdultVoiceMessageTitle();
            if (this.mVoiceMessage.getSender().getPlushToy() != null) {
                this.mBinding.messageForMeCharacterImage.setImageResource(PlushToy.getMessageForMeAvatarResourceForCharacter(this.mVoiceMessage.getSender().getPlushToy().getCharacter()));
            }
        } else {
            this.mBinding.messageForMeCharacterImage.setVisibility(8);
            setChildVoiceMessageTitle();
        }
        String displayName = this.mVoiceMessage.getSender().getDisplayName();
        String placeholderText = displayName.isEmpty() ? " " : Character.toString(displayName.charAt(0)).toUpperCase();
        ProfilePortrait profilePortrait = this.mVoiceMessage.getSender().getPortrait();
        BitmapPool pool = Glide.get(getActivity()).getBitmapPool();
        RequestManager with = Glide.with(getActivity());
        if (profilePortrait == null) {
            str = null;
        } else {
            str = profilePortrait.getFile().getUrl();
        }
        with.load(str).bitmapTransform(new DoubleBorderCropCircleTransformation(getActivity(), pool, getResources().getColor(R.color.profile_photo_border_dark), -1)).placeholder(ProfilePlaceholderDrawable.getLargeInstance(getActivity(), placeholderText)).into(this.mBinding.profilePhoto);
        getChildFragmentManager().beginTransaction().replace(R.id.audio_player_container, AudioPlayerFragment.newInstance(Uri.parse(this.mVoiceMessage.getFile().getUrl()), false)).commit();
        return this.mBinding.getRoot();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEvent(VoiceMessagePlaybackStartedEvent event) {
        this.mBinding.playbackRippleEffect.startRippleAnimation();
    }

    public void onEvent(VoiceMessagePlaybackPausedEvent event) {
        this.mBinding.playbackRippleEffect.stopRippleAnimation();
    }

    public void onEvent(VoiceMessagePlaybackCompletedEvent event) {
        this.mBinding.playbackRippleEffect.stopRippleAnimation();
    }

    private void setAdultVoiceMessageTitle() {
        this.mBinding.messageTitle.setText(getString(R.string.message_from, new Object[]{this.mVoiceMessage.getSender().getDisplayName()}));
    }

    private void setChildVoiceMessageTitle() {
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(TypefaceUtils.load(getActivity().getAssets(), "fonts/merge_bold.otf"));
        String prefixText = getString(R.string.message_for, new Object[]{""});
        String recipient = this.mVoiceMessage.getRecipient().getDisplayName();
        SpannableStringBuilder sBuilder = new SpannableStringBuilder();
        sBuilder.append(prefixText).append(recipient).append(" " + getString(R.string.child_message_from, new Object[]{this.mVoiceMessage.getSender().getDisplayName()}));
        sBuilder.setSpan(typefaceSpan, prefixText.length(), prefixText.length() + recipient.length(), 33);
        this.mBinding.messageTitle.setText(sBuilder, BufferType.SPANNABLE);
    }
}
