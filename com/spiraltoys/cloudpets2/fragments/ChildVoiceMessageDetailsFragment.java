package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView.BufferType;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.databinding.FragmentChildVoiceMessageDetailsBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.util.VoiceMessageDateFormatter;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class ChildVoiceMessageDetailsFragment extends Fragment {
    private static final String ARG_VOICE_MESSAGE_ID = "arg_voice_message_id";
    private FragmentChildVoiceMessageDetailsBinding mBinding;
    private OnChildVoiceMessageDetailsFragmentInteractionListener mListener;
    private VoiceMessage mVoiceMessage;

    public interface OnChildVoiceMessageDetailsFragmentInteractionListener {
        void onDeleteVoiceMessageClicked(VoiceMessage voiceMessage);

        void onPushToToyClicked(VoiceMessage voiceMessage);
    }

    public static ChildVoiceMessageDetailsFragment newInstance(VoiceMessage voiceMessage) {
        ChildVoiceMessageDetailsFragment fragment = new ChildVoiceMessageDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_VOICE_MESSAGE_ID, voiceMessage.getObjectId());
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                this.mVoiceMessage = ModelHelper.getVoiceMessageFromLocalDatastore(getArguments().getString(ARG_VOICE_MESSAGE_ID));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String str;
        this.mBinding = (FragmentChildVoiceMessageDetailsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_voice_message_details, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        this.mBinding.setVoiceMessage(this.mVoiceMessage);
        this.mBinding.setVoiceMessageDateFormatter(new VoiceMessageDateFormatter(inflater.getContext(), this.mVoiceMessage));
        this.mBinding.pushToToyButtonLabel.setSelected(true);
        this.mBinding.deleteVoiceMessageButtonLabel.setSelected(true);
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
        with.load(str).bitmapTransform(new DoubleBorderCropCircleTransformation(getActivity(), pool, getActivity().getResources().getColor(R.color.profile_photo_border_dark), -1)).placeholder(ProfilePlaceholderDrawable.getLargeInstance(getActivity(), placeholderText)).into(this.mBinding.profilePhoto);
        setVoiceMessageTitle();
        return this.mBinding.getRoot();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnChildVoiceMessageDetailsFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnChildVoiceMessageDetailsFragmentInteractionListener");
        }
    }

    @OnClick({2131755265})
    void onPushToToyClicked(View view) {
        if (this.mListener != null) {
            this.mListener.onPushToToyClicked(this.mVoiceMessage);
        }
    }

    @OnClick({2131755146})
    void onDeleteClicked(View view) {
        if (this.mListener != null) {
            this.mListener.onDeleteVoiceMessageClicked(this.mVoiceMessage);
        }
    }

    private void setVoiceMessageTitle() {
        CalligraphyTypefaceSpan typefaceSpan = new CalligraphyTypefaceSpan(TypefaceUtils.load(getActivity().getAssets(), "fonts/merge_bold.otf"));
        String prefixText = getString(R.string.message_from, new Object[]{""});
        String sender = this.mVoiceMessage.getSender().getDisplayName();
        SpannableStringBuilder sBuilder = new SpannableStringBuilder();
        sBuilder.append(prefixText).append(sender);
        sBuilder.setSpan(typefaceSpan, prefixText.length(), prefixText.length() + sender.length(), 33);
        this.mBinding.messageTitle.setText(sBuilder, BufferType.SPANNABLE);
    }
}
