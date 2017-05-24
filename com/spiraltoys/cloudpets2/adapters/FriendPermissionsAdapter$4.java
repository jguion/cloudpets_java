package com.spiraltoys.cloudpets2.adapters;

import com.parse.CountCallback;
import com.parse.ParseException;

class FriendPermissionsAdapter$4 implements CountCallback {
    final /* synthetic */ FriendPermissionsAdapter this$0;

    FriendPermissionsAdapter$4(FriendPermissionsAdapter this$0) {
        this.this$0 = this$0;
    }

    public void done(int i, ParseException e) {
        FriendPermissionsAdapter.access$302(this.this$0, i);
        this.this$0.notifyDataSetChanged();
    }
}
