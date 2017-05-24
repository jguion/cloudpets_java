package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.spiraltoys.cloudpets2.databinding.FragmentAudioPlayerBinding;
import com.spiraltoys.cloudpets2.events.NetworkStateChangedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.AudioPlayer;
import com.spiraltoys.cloudpets2.util.CacheUtil;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import java.io.File;

public class AudioPlayerFragment extends Fragment {
    private static final String ARG_AUDIO_URI = "arg_audio_uri";
    private static final String ARG_NEWLY_RECORDED_ADULT_MESSAGE = "arg_newly_recorded_adult_message";
    private static final float SEEK_BAR_THUMB_OFFSET_DP = 4.8f;
    private AudioPlayer mAudioPlayer;
    private Uri mAudioUri;
    private FragmentAudioPlayerBinding mBinding;
    private boolean mIsAudioPlayerInitialized;
    private OnAudioPlayerInteractionListener mListener;

    public interface OnAudioPlayerInteractionListener {
        void onPlaybackCompleted();

        void onPlaybackPaused();

        void onPlaybackStarted();
    }

    public static AudioPlayerFragment newInstance(Uri audioUri, boolean autoplayMessage) {
        AudioPlayerFragment fragment = new AudioPlayerFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_AUDIO_URI, audioUri);
        args.putBoolean(ARG_NEWLY_RECORDED_ADULT_MESSAGE, autoplayMessage);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mAudioUri = (Uri) getArguments().getParcelable(ARG_AUDIO_URI);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentAudioPlayerBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_audio_player, container, false);
        this.mBinding.setAdultRecordingNewMessage(Boolean.valueOf(getArguments().getBoolean(ARG_NEWLY_RECORDED_ADULT_MESSAGE)));
        loadAudio();
        return this.mBinding.getRoot();
    }

    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnAudioPlayerInteractionListener) {
            this.mListener = (OnAudioPlayerInteractionListener) activity;
        }
    }

    @DebugLog
    public void onDestroyView() {
        if (this.mAudioPlayer != null) {
            this.mAudioPlayer.release();
            this.mAudioPlayer = null;
        }
        if (this.mBinding.progressBar != null) {
            this.mBinding.progressBar.setVisibility(4);
            this.mBinding.progressBar.destroyDrawingCache();
            this.mBinding.progressBar.clearAnimation();
        }
        super.onDestroyView();
    }

    private void loadAudio() {
        boolean isCached;
        boolean isLocalFile = new File(this.mAudioUri.getPath()).exists();
        if (isLocalFile || !CacheUtil.isAvailableInOfflineCache(getActivity(), this.mAudioUri)) {
            isCached = false;
        } else {
            isCached = true;
        }
        if (isLocalFile || isCached || Utils.isNetworkConnectionAvailable(getActivity())) {
            if (this.mAudioPlayer != null) {
                this.mAudioPlayer.release();
            }
            this.mAudioPlayer = new AudioPlayer();
            this.mBinding.noConnectionNotice.setVisibility(8);
            this.mBinding.audioPlayerContainer.setVisibility(0);
            this.mAudioPlayer.addOnPreparedListener(new 1(this));
            if (isLocalFile) {
                showProgressImmediately();
                this.mIsAudioPlayerInitialized = true;
                this.mAudioPlayer.init(getActivity(), this.mAudioUri);
                return;
            }
            if (isCached) {
                showProgressImmediately();
            } else {
                this.mBinding.seekBar.setAlpha(0.0f);
            }
            CacheUtil.fetchToTemporaryFileAndCache(getActivity(), this.mAudioUri, new 2(this));
            return;
        }
        this.mBinding.audioPlayerContainer.setVisibility(4);
        this.mBinding.noConnectionNotice.setVisibility(0);
    }

    private void showProgressImmediately() {
        this.mBinding.seekBar.setAlpha(1.0f);
        this.mBinding.seekBar.setVisibility(0);
        this.mBinding.progressBar.setVisibility(4);
    }

    public void onEventMainThread(NetworkStateChangedEvent event) {
        if (!this.mIsAudioPlayerInitialized) {
            loadAudio();
        }
    }
}
