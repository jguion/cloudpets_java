package com.spiraltoys.cloudpets2;

import android.widget.Toast;
import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.EditFriendPermissionActivity.4;
import com.spiraltoys.cloudpets2.events.FriendRecordDeletedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;

class EditFriendPermissionActivity$4$1 implements DeleteCallback {
    final /* synthetic */ 4 this$1;

    EditFriendPermissionActivity$4$1(4 this$1) {
        this.this$1 = this$1;
    }

    public void done(ParseException e) {
        if (e != null) {
            this.this$1.this$0.hideProgress();
            Utils.showErrorDialog(this.this$1.this$0, ErrorMessages.getStringResourceIdForGenericParseExceiption(this.this$1.this$0));
            return;
        }
        this.this$1.this$0.hideProgress();
        Toast.makeText(this.this$1.this$0, R.string.toast_message_successful_friend_permission_update, 1).show();
        EventBus.getDefault().post(new FriendRecordDeletedEvent());
        this.this$1.this$0.finish();
    }
}
