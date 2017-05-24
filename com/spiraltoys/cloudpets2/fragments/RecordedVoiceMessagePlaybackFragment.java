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
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.BaseActivity;
import com.spiraltoys.cloudpets2.databinding.FragmentRecordedVoiceMessagePlaybackBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;

public class RecordedVoiceMessagePlaybackFragment extends Fragment {
    private static final String ARG_RECIPIENT_PROFILE_ID = "arg_recipient_profile_id";
    private static final String ARG_RECORDING_URI = "arg_recording_uri";
    private FragmentRecordedVoiceMessagePlaybackBinding mBinding;
    private OnRecordedVoiceMessagePlaybackFragmentInteractionListener mListener;
    private Profile mRecipientProfile;
    private Uri mRecordingUri;

    public interface OnRecordedVoiceMessagePlaybackFragmentInteractionListener {
        void onSendToRecipientSelected(Profile profile);
    }

    public static RecordedVoiceMessagePlaybackFragment newInstance(Uri recordingUri, String recipientProfileId) {
        RecordedVoiceMessagePlaybackFragment fragment = new RecordedVoiceMessagePlaybackFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_RECORDING_URI, recordingUri);
        args.putString(ARG_RECIPIENT_PROFILE_ID, recipientProfileId);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mRecordingUri = (Uri) getArguments().getParcelable(ARG_RECORDING_URI);
            if (getRecipientProfileId() != null) {
                try {
                    this.mRecipientProfile = ModelHelper.getProfileFromLocalDatastore(getRecipientProfileId());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentRecordedVoiceMessagePlaybackBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_recorded_voice_message_playback, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        if ((getActivity() instanceof BaseActivity) && ((BaseActivity) getActivity()).getSupportActionBar() != null) {
            ((BaseActivity) getActivity()).getSupportActionBar().setTitle((int) R.string.title_listen);
        }
        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction().replace(R.id.audio_player_container, AudioPlayerFragment.newInstance(this.mRecordingUri, true)).commit();
        }
        if (this.mRecipientProfile != null) {
            this.mBinding.selectRecipientsButton.setText(getString(R.string.btn_send_to_named_recipient, new Object[]{this.mRecipientProfile.getDisplayName()}));
        }
        return this.mBinding.getRoot();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnRecordedVoiceMessagePlaybackFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnRecordedVoiceMessagePlaybackFragmentInteractionListener");
        }
    }

    @OnClick({2131755297})
    public void onCancelClicked() {
        getFragmentManager().popBackStack();
    }

    @OnClick({2131755261})
    public void onSelectClicked() {
        if (this.mRecipientProfile != null) {
            this.mListener.onSendToRecipientSelected(this.mRecipientProfile);
            return;
        }
        getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_container, SelectProfileFragment.newInstance(true, false, true, null)).commit();
    }

    private String getRecipientProfileId() {
        return getArguments().getString(ARG_RECIPIENT_PROFILE_ID);
    }
}
