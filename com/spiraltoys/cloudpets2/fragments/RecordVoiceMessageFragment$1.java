package com.spiraltoys.cloudpets2.fragments;

import com.spiraltoys.cloudpets2.util.Utils;

class RecordVoiceMessageFragment$1 implements Runnable {
    final /* synthetic */ RecordVoiceMessageFragment this$0;
    final /* synthetic */ long val$startTime;

    RecordVoiceMessageFragment$1(RecordVoiceMessageFragment this$0, long j) {
        this.this$0 = this$0;
        this.val$startTime = j;
    }

    public void run() {
        float progress = Math.min(1.0f, ((float) (System.currentTimeMillis() - this.val$startTime)) / 10000.0f);
        RecordVoiceMessageFragment.access$000(this.this$0).progressBar.setProgress(((int) (1000.0f * progress)) + 50);
        if (progress >= 1.0f) {
            RecordVoiceMessageFragment.access$100(this.this$0).cancel();
        }
        RecordVoiceMessageFragment.access$000(this.this$0).currentPlaybackTime.setText(Utils.formatAudioTime((long) (10000.0f * progress)));
    }
}
