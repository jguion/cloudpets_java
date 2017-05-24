package com.spiraltoys.cloudpets2;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.RecordAndSendMessageActivity.1;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import java.util.List;

class RecordAndSendMessageActivity$1$1 implements FindCallback<FriendRecord> {
    final /* synthetic */ 1 this$1;

    RecordAndSendMessageActivity$1$1(1 this$1) {
        this.this$1 = this$1;
    }

    public void done(List<FriendRecord> list, ParseException e) {
        this.this$1.val$swipeRefreshLayout.setRefreshing(false);
    }
}
