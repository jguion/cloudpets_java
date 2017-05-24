package com.spiraltoys.cloudpets2.fragments;

import com.parse.CountCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PrivateProfile.ProfileType;

class VoiceMessagesFragment$3 implements CountCallback {
    final /* synthetic */ VoiceMessagesFragment this$0;

    VoiceMessagesFragment$3(VoiceMessagesFragment this$0) {
        this.this$0 = this$0;
    }

    public void done(int i, ParseException e) {
        if (this.this$0.isAdded() && !this.this$0.isRemoving()) {
            if (VoiceMessagesFragment.access$200(this.this$0) == ProfileType.ADULT) {
                VoiceMessagesFragment.access$000(this.this$0).emptyViewTitle.setText(R.string.message_no_adult_messages);
            } else {
                VoiceMessagesFragment.access$000(this.this$0).emptyViewTitle.setText(i == 0 ? this.this$0.getString(R.string.message_no_children) : this.this$0.getResources().getQuantityString(R.plurals.message_no_child_messages, i));
            }
        }
    }
}
