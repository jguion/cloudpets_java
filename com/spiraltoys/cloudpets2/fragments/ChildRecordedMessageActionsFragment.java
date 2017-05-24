package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.spiraltoys.cloudpets2.databinding.FragmentChildRecordedMessageActionsBinding;
import com.spiraltoys.cloudpets2.events.VoiceMessagePlaybackCompletedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessagePlaybackPausedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessagePlaybackStartedEvent;
import com.spiraltoys.cloudpets2.free.R;
import de.greenrobot.event.EventBus;

public class ChildRecordedMessageActionsFragment extends Fragment {
    private static final String ARG_VOICE_MESSAGE_URI = "arg_voice_message_URI";
    private FragmentChildRecordedMessageActionsBinding mBinding;
    private OnChildRecordedMessageActionsFragmentInteractionListener mListener;
    private Uri mVoiceMessageUri;

    public interface OnChildRecordedMessageActionsFragmentInteractionListener {
        void onDeleteRecordedMessageClicked(Uri uri);

        void onSelectRecipientsClicked(Uri uri);
    }

    public static ChildRecordedMessageActionsFragment newInstance(Uri recordingUri) {
        ChildRecordedMessageActionsFragment fragment = new ChildRecordedMessageActionsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_VOICE_MESSAGE_URI, recordingUri);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mVoiceMessageUri = (Uri) getArguments().getParcelable(ARG_VOICE_MESSAGE_URI);
        }
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentChildRecordedMessageActionsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_recorded_message_actions, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        getFragmentManager().beginTransaction().replace(R.id.message_details_container, AudioPlayerFragment.newInstance(this.mVoiceMessageUri, false)).commit();
        return this.mBinding.getRoot();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnChildRecordedMessageActionsFragmentInteractionListener) {
            this.mListener = (OnChildRecordedMessageActionsFragmentInteractionListener) activity;
            return;
        }
        throw new ClassCastException("activity must implement OnChildRecordedMessageActionsFragmentInteractionListener");
    }

    @OnClick({2131755261})
    void onSelectRecipientsClicked() {
        if (this.mListener != null) {
            this.mListener.onSelectRecipientsClicked(this.mVoiceMessageUri);
        }
    }

    @OnClick({2131755262})
    void onDeleteClicked() {
        if (this.mListener != null) {
            this.mListener.onDeleteRecordedMessageClicked(this.mVoiceMessageUri);
        }
    }
}
