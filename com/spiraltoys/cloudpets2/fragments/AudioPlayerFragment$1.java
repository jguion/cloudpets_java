package com.spiraltoys.cloudpets2.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import com.spiraltoys.cloudpets2.events.VoiceMessagePlaybackCompletedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessagePlaybackPausedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessagePlaybackStartedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;

class AudioPlayerFragment$1 implements OnPreparedListener {
    final /* synthetic */ AudioPlayerFragment this$0;

    AudioPlayerFragment$1(AudioPlayerFragment this$0) {
        this.this$0 = this$0;
    }

    public void onPrepared(MediaPlayer mp) {
        if (AudioPlayerFragment.access$000(this.this$0) != null) {
            AudioPlayerFragment.access$000(this.this$0).setSeekBar(AudioPlayerFragment.access$200(this.this$0).seekBar).setRuntimeView(AudioPlayerFragment.access$200(this.this$0).currentPlaybackTime).setTotalTimeView(AudioPlayerFragment.access$200(this.this$0).trackLength).setPlayView(AudioPlayerFragment.access$200(this.this$0).playButton).setPauseView(AudioPlayerFragment.access$200(this.this$0).pauseButton).addOnPlayClickListener(new OnClickListener() {
                public void onClick(View v) {
                    EventBus.getDefault().post(new VoiceMessagePlaybackStartedEvent());
                    if (AudioPlayerFragment.access$100(AudioPlayerFragment$1.this.this$0) != null) {
                        AudioPlayerFragment.access$100(AudioPlayerFragment$1.this.this$0).onPlaybackStarted();
                    }
                }
            }).addOnPauseClickListener(new OnClickListener() {
                public void onClick(View v) {
                    EventBus.getDefault().post(new VoiceMessagePlaybackPausedEvent());
                    if (AudioPlayerFragment.access$100(AudioPlayerFragment$1.this.this$0) != null) {
                        AudioPlayerFragment.access$100(AudioPlayerFragment$1.this.this$0).onPlaybackPaused();
                    }
                }
            }).addOnCompletionListener(new OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    EventBus.getDefault().post(new VoiceMessagePlaybackCompletedEvent());
                    if (AudioPlayerFragment.access$100(AudioPlayerFragment$1.this.this$0) != null) {
                        AudioPlayerFragment.access$100(AudioPlayerFragment$1.this.this$0).onPlaybackCompleted();
                    }
                }
            });
            AudioPlayerFragment.access$200(this.this$0).progressBar.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    AudioPlayerFragment.access$200(AudioPlayerFragment$1.this.this$0).progressBar.setVisibility(4);
                }
            }).start();
            AudioPlayerFragment.access$200(this.this$0).seekBar.setVisibility(0);
            AudioPlayerFragment.access$200(this.this$0).seekBar.setThumbOffset(Utils.dpToPx(this.this$0.getActivity(), 4.8f));
            AudioPlayerFragment.access$200(this.this$0).seekBar.animate().alpha(1.0f).start();
            AudioPlayerFragment.access$200(this.this$0).playButton.setEnabled(true);
            if (this.this$0.getArguments().getBoolean("arg_newly_recorded_adult_message")) {
                AudioPlayerFragment.access$000(this.this$0).play();
                AudioPlayerFragment.access$200(this.this$0).playButton.setBackgroundTintList(ContextCompat.getColorStateList(this.this$0.getActivity(), R.color.button_bg_magenta_play_pause));
                AudioPlayerFragment.access$200(this.this$0).pauseButton.setBackgroundTintList(ContextCompat.getColorStateList(this.this$0.getActivity(), R.color.button_bg_magenta_play_pause));
            }
        }
    }
}
