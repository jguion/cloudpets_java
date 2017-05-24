package com.spiraltoys.cloudpets2.events;

import com.spiraltoys.cloudpets2.adapters.FriendPermissionsAdapter.ChildFriendRecordGroup;

public class FriendRecordEditEvent {
    private ChildFriendRecordGroup mFriendRecordGroup;

    public FriendRecordEditEvent(ChildFriendRecordGroup friendRecordGroup) {
        this.mFriendRecordGroup = friendRecordGroup;
    }

    public ChildFriendRecordGroup getFriendRecordGroup() {
        return this.mFriendRecordGroup;
    }
}
