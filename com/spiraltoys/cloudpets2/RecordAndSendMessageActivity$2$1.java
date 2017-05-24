package com.spiraltoys.cloudpets2;

import android.widget.Toast;
import com.parse.ParseException;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.RecordAndSendMessageActivity.2;
import com.spiraltoys.cloudpets2.events.VoiceMessageFailedToSend;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;

class RecordAndSendMessageActivity$2$1 implements SaveCallback {
    final /* synthetic */ 2 this$1;

    RecordAndSendMessageActivity$2$1(2 this$1) {
        this.this$1 = this$1;
    }

    public void done(ParseException e) {
        RecordAndSendMessageActivity.access$002(this.this$1.this$0, false);
        this.this$1.this$0.hideProgress();
        if (e != null) {
            e.printStackTrace();
            EventBus.getDefault().post(new VoiceMessageFailedToSend());
            Utils.showErrorDialog(this.this$1.this$0, ErrorMessages.getStringResourceIdForErrorSendingVoiceMessage(this.this$1.this$0, e));
            return;
        }
        if (this.this$1.this$0.containsChildProfile(this.this$1.val$selectedProfiles)) {
            Toast.makeText(this.this$1.this$0, R.string.message_sent_to_child, 1).show();
        } else {
            Toast.makeText(this.this$1.this$0, R.string.message_sent, 1).show();
        }
        this.this$1.this$0.setResult(-1);
        this.this$1.this$0.finish();
    }
}
