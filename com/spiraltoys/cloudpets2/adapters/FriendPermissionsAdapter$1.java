package com.spiraltoys.cloudpets2.adapters;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.adapters.FriendPermissionsAdapter.ChildFriendRecordGroup;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class FriendPermissionsAdapter$1 implements FindCallback<FriendRecord> {
    final /* synthetic */ FriendPermissionsAdapter this$0;

    FriendPermissionsAdapter$1(FriendPermissionsAdapter this$0) {
        this.this$0 = this$0;
    }

    public void done(List<FriendRecord> results, ParseException e) {
        if (e != null) {
            e.printStackTrace();
            return;
        }
        HashMap<String, ChildFriendRecordGroup> friendRecordGroups = new HashMap();
        Collections.sort(results, FriendPermissionsAdapter.access$000());
        for (FriendRecord record : results) {
            String key;
            if (record.getTargetProfile() == null) {
                key = record.getTargetEmailAddress();
            } else {
                key = record.getTargetProfile().getObjectId();
            }
            ChildFriendRecordGroup group = (ChildFriendRecordGroup) friendRecordGroups.get(key);
            if (group == null) {
                group = new ChildFriendRecordGroup(this.this$0);
                friendRecordGroups.put(key, group);
            }
            group.add(record);
        }
        this.this$0.mChildFriendRecords = new ArrayList(friendRecordGroups.values());
        Collections.sort(this.this$0.mChildFriendRecords);
        FriendPermissionsAdapter.access$102(this.this$0, true);
        this.this$0.dataReloadable(FriendPermissionsAdapter.access$200(this.this$0), FriendPermissionsAdapter.access$100(this.this$0));
    }
}
