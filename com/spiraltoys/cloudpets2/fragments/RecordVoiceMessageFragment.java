package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import com.android.Convert;
import com.google.android.vending.expansion.downloader.Constants;
import com.google.common.io.ByteStreams;
import com.spiraltoys.cloudpets2.BaseActivity;
import com.spiraltoys.cloudpets2.audio.WavAudio;
import com.spiraltoys.cloudpets2.audio.WavRecorder;
import com.spiraltoys.cloudpets2.audio.WavUtil;
import com.spiraltoys.cloudpets2.databinding.FragmentRecordVoiceMessageBinding;
import com.spiraltoys.cloudpets2.events.RecordingFinishedEvent;
import com.spiraltoys.cloudpets2.events.RecordingStartedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.UUID;

public class RecordVoiceMessageFragment extends Fragment {
    private static final int MAX_RECORDING_LENGTH_MS = 10000;
    private static final int MIN_RECORDING_LENGTH_MS = 100;
    private static final int MS_PER_SECOND = 1000;
    private static final int PROGRESS_START_OFFSET = 50;
    private static final String RECORDINGS_DIR = "recordings";
    private static final String RECORDING_FILE_NAME = "recording.wav";
    private static final int RECORD_PROGRESS_UPDATE_INTERVAL = 16;
    private static final int TOTAL_PROGRESS_STEPS = 1000;
    private FragmentRecordVoiceMessageBinding mBinding;
    private OnRecordVoiceMessageFragmentInteractionListener mListener;
    private Timer mProgressTimer;
    private Handler mProgressUpdateHandler;
    private Handler mRecordTimeoutHandler;
    private Runnable mStopRecordingRunnable = new 4(this);
    private WavRecorder mWavRecorder;

    public interface OnRecordVoiceMessageFragmentInteractionListener {
        void onVoiceMessageRecorded(Uri uri);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentRecordVoiceMessageBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_record_voice_message, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        this.mRecordTimeoutHandler = new Handler();
        this.mProgressUpdateHandler = new Handler(Looper.getMainLooper());
        this.mBinding.progressBar.setMax(Constants.MAX_DOWNLOADS);
        this.mBinding.currentPlaybackTime.setText(Utils.formatAudioTime(0));
        this.mBinding.trackLength.setText(Utils.formatAudioTime(10000));
        if ((getActivity() instanceof BaseActivity) && ((BaseActivity) getActivity()).getSupportActionBar() != null) {
            ((BaseActivity) getActivity()).getSupportActionBar().setTitle((int) R.string.title_activity_record_and_send_message);
        }
        return this.mBinding.getRoot();
    }

    @OnTouch({2131755133})
    boolean onRecordTouch(View v, MotionEvent event) {
        if (event.getAction() == 0) {
            if (VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(getActivity(), "android.permission.RECORD_AUDIO") == 0) {
                this.mBinding.progressBar.setProgress(50);
                startRecording();
            } else {
                requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 0);
            }
        } else if (event.getAction() == 1 || event.getAction() == 3) {
            stopRecording();
        }
        return false;
    }

    @OnClick({2131755133})
    void onRecordClicked() {
    }

    private synchronized void startRecording() {
        if (this.mWavRecorder == null) {
            EventBus.getDefault().post(new RecordingStartedEvent());
            this.mBinding.recordRippleEffect.startRippleAnimation();
            try {
                this.mWavRecorder = new WavRecorder(File.createTempFile(UUID.randomUUID().toString(), null, getActivity().getCacheDir()));
                this.mWavRecorder.startRecording();
                this.mRecordTimeoutHandler.removeCallbacks(this.mStopRecordingRunnable);
                this.mRecordTimeoutHandler.postDelayed(this.mStopRecordingRunnable, 10000);
                long startTime = System.currentTimeMillis();
                this.mProgressTimer = new Timer();
                this.mProgressTimer.scheduleAtFixedRate(new 2(this, new 1(this, startTime)), 0, 16);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void stopRecording() {
        if (this.mWavRecorder != null) {
            this.mBinding.recordRippleEffect.stopRippleAnimation();
            this.mRecordTimeoutHandler.removeCallbacks(this.mStopRecordingRunnable);
            this.mWavRecorder.stopRecording();
            resetProgress();
            try {
                WavAudio wavAudio = new WavAudio(new Convert().Bytes2Shorts(ByteStreams.toByteArray(new FileInputStream(this.mWavRecorder.getOutputFile()))), WavRecorder.SAMPLE_RATE, 4, 2);
                if (((float) wavAudio.getFramesNum()) / ((float) wavAudio.getSampleRate()) >= 0.1f) {
                    File imagePath = new File(getActivity().getFilesDir(), RECORDINGS_DIR);
                    if (imagePath.exists() || imagePath.mkdir()) {
                        File newFile = new File(imagePath, RECORDING_FILE_NAME);
                        if (this.mListener != null && WavUtil.saveSync(wavAudio, newFile)) {
                            this.mListener.onVoiceMessageRecorded(Uri.fromFile(newFile));
                        }
                    }
                    EventBus.getDefault().post(new RecordingFinishedEvent(wavAudio));
                    this.mWavRecorder.getOutputFile().delete();
                    this.mWavRecorder = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.mWavRecorder.getOutputFile().delete();
                this.mWavRecorder = null;
            }
        }
    }

    public void onStop() {
        super.onStop();
        this.mRecordTimeoutHandler.removeCallbacks(this.mStopRecordingRunnable);
        if (this.mWavRecorder != null) {
            EventBus.getDefault().post(new RecordingFinishedEvent(null));
            this.mWavRecorder.stopRecording();
        }
    }

    private void resetProgress() {
        this.mProgressTimer.cancel();
        new Handler().postDelayed(new 3(this), 16);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnRecordVoiceMessageFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}
