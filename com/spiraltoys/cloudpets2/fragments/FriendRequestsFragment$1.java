package com.spiraltoys.cloudpets2.fragments;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.util.Analytics;
import java.util.List;

class FriendRequestsFragment$1 implements FindCallback<Profile> {
    final /* synthetic */ FriendRequestsFragment this$0;

    FriendRequestsFragment$1(FriendRequestsFragment this$0) {
        this.this$0 = this$0;
    }

    public void done(List<Profile> list, ParseException e) {
        if (e != null) {
            Analytics.logLocalDatastoreRecoveryException(e);
        } else if (!list.isEmpty() && ((Profile) list.get(0)).getPrivateProfile().isAdult()) {
            FriendRequestsFragment.access$000(this.this$0, (Profile) list.get(0));
        }
    }
}
