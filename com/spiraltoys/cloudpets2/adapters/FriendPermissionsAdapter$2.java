package com.spiraltoys.cloudpets2.adapters;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import java.util.Collections;
import java.util.List;

class FriendPermissionsAdapter$2 implements FindCallback<FriendRecord> {
    final /* synthetic */ FriendPermissionsAdapter this$0;

    FriendPermissionsAdapter$2(FriendPermissionsAdapter this$0) {
        this.this$0 = this$0;
    }

    public void done(List<FriendRecord> results, ParseException e) {
        if (e != null) {
            e.printStackTrace();
            return;
        }
        Collections.sort(results, FriendPermissionsAdapter.access$000());
        this.this$0.mMyFriendRecords = results;
        FriendPermissionsAdapter.access$202(this.this$0, true);
        this.this$0.dataReloadable(FriendPermissionsAdapter.access$200(this.this$0), FriendPermissionsAdapter.access$100(this.this$0));
    }
}
