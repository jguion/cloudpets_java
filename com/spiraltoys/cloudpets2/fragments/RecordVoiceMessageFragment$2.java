package com.spiraltoys.cloudpets2.fragments;

import java.util.TimerTask;

class RecordVoiceMessageFragment$2 extends TimerTask {
    final /* synthetic */ RecordVoiceMessageFragment this$0;
    final /* synthetic */ Runnable val$progressBarUpdater;

    RecordVoiceMessageFragment$2(RecordVoiceMessageFragment this$0, Runnable runnable) {
        this.this$0 = this$0;
        this.val$progressBarUpdater = runnable;
    }

    public void run() {
        RecordVoiceMessageFragment.access$200(this.this$0).post(this.val$progressBarUpdater);
    }
}
