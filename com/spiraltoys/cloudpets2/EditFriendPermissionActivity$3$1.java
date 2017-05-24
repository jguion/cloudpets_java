package com.spiraltoys.cloudpets2;

import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.EditFriendPermissionActivity.3;
import com.spiraltoys.cloudpets2.events.FriendRecordDeletedEvent;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;

class EditFriendPermissionActivity$3$1 implements DeleteCallback {
    final /* synthetic */ 3 this$1;

    EditFriendPermissionActivity$3$1(3 this$1) {
        this.this$1 = this$1;
    }

    public void done(ParseException e) {
        this.this$1.this$0.hideProgress();
        if (e == null) {
            EventBus.getDefault().post(new FriendRecordDeletedEvent());
            this.this$1.this$0.finish();
            return;
        }
        Utils.showErrorDialog(this.this$1.this$0, ErrorMessages.getStringResourceIdForGenericParseExceiption(this.this$1.this$0));
    }
}
