package com.spiraltoys.cloudpets2;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.ChildDashboardActivity.8;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import java.util.List;

class ChildDashboardActivity$8$1 implements FindCallback<FriendRecord> {
    final /* synthetic */ 8 this$1;

    ChildDashboardActivity$8$1(8 this$1) {
        this.this$1 = this$1;
    }

    public void done(List<FriendRecord> list, ParseException e) {
        this.this$1.val$swipeRefreshLayout.setRefreshing(false);
    }
}
