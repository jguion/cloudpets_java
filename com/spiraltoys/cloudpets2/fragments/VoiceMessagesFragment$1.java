package com.spiraltoys.cloudpets2.fragments;

class VoiceMessagesFragment$1 implements Runnable {
    final /* synthetic */ VoiceMessagesFragment this$0;

    VoiceMessagesFragment$1(VoiceMessagesFragment this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        VoiceMessagesFragment.access$000(this.this$0).swipeRefreshLayout.setRefreshing(true);
    }
}
