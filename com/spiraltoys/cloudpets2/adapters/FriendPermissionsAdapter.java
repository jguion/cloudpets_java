package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.google.common.hash.HashCode;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.spiraltoys.cloudpets2.events.FriendRecordAddedEvent;
import com.spiraltoys.cloudpets2.events.FriendRecordDeletedEvent;
import com.spiraltoys.cloudpets2.events.FriendRecordSavedToLocalDatastoreEvent;
import com.spiraltoys.cloudpets2.events.ProfileAddedEvent;
import com.spiraltoys.cloudpets2.events.ProfileDeletedEvent;
import com.spiraltoys.cloudpets2.events.ProfileSavedToLocalDatastoreEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.FriendStatus;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

public class FriendPermissionsAdapter extends Adapter {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM_CHILD_FRIEND = 2;
    private static final int TYPE_ITEM_MY_FRIEND = 3;
    private static final int TYPE_MESSAGE = 1;
    private static Comparator<FriendRecord> myFriendRecordComparator = new 3();
    private boolean childFriendsLoaded;
    List<ChildFriendRecordGroup> mChildFriendRecords = new ArrayList();
    ParseQuery<FriendRecord> mChildFriendsQuery;
    List<FriendRecord> mMyFriendRecords = new ArrayList();
    ParseQuery<FriendRecord> mMyFriendsQuery;
    private int mNumberOfChildren;
    private boolean myFriendsLoaded;

    public class ChildFriendRecordGroup extends LinkedHashSet<FriendRecord> implements Comparable<ChildFriendRecordGroup> {
        public FriendRecord first() {
            return (FriendRecord) iterator().next();
        }

        public int compareTo(ChildFriendRecordGroup another) {
            FriendRecord lhs = first();
            FriendRecord rhs = another.first();
            String leftName = lhs.getTargetProfile() == null ? lhs.getName().toLowerCase() : lhs.getTargetProfile().getDisplayName().toLowerCase();
            String rightName = rhs.getTargetProfile() == null ? rhs.getName().toLowerCase() : rhs.getTargetProfile().getDisplayName().toLowerCase();
            if (lhs.getStatus().equals(FriendStatus.PENDING)) {
                if (!rhs.getStatus().equals(FriendStatus.PENDING)) {
                    return 1;
                }
                if (leftName.equals(rightName)) {
                    return rhs.getCreatedAt().compareTo(lhs.getCreatedAt());
                }
                return leftName.compareTo(rightName);
            } else if (rhs.getStatus().equals(FriendStatus.PENDING)) {
                return -1;
            } else {
                if (leftName.equals(rightName)) {
                    return rhs.getCreatedAt().compareTo(lhs.getCreatedAt());
                }
                return leftName.compareTo(rightName);
            }
        }
    }

    public FriendPermissionsAdapter(ParseQuery<FriendRecord> childFriendsQuery, ParseQuery<FriendRecord> myFriendsQuery, int numberOfChildren) {
        this.mMyFriendsQuery = myFriendsQuery;
        this.mChildFriendsQuery = childFriendsQuery;
        this.mNumberOfChildren = numberOfChildren;
        reloadData();
        setHasStableIds(true);
        EventBus.getDefault().register(this);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_header, parent, false));
        }
        if (viewType == 1) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_generic, parent, false));
        }
        if (viewType == 2) {
            return new ChildFriendsViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_friend, parent, false));
        }
        return new MyFriendsViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_friend, parent, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        int childFriendAdjuster = this.mChildFriendRecords.isEmpty() ? 1 : 0;
        Context context = viewHolder.itemView.getContext();
        if (viewHolder instanceof HeaderViewHolder) {
            String headerTitle;
            if (position == 0) {
                int i;
                if (this.mNumberOfChildren > 1) {
                    i = R.string.header_children_friends;
                } else {
                    i = R.string.header_child_friends;
                }
                headerTitle = context.getString(i);
            } else if (position == 1) {
                if (this.mNumberOfChildren == 0) {
                    headerTitle = context.getString(R.string.message_no_children_short);
                } else {
                    headerTitle = context.getString(this.mNumberOfChildren > 1 ? R.string.message_no_children_friends : R.string.message_no_child_friends);
                }
            } else if (position == (this.mChildFriendRecords.size() + childFriendAdjuster) + 1) {
                headerTitle = context.getString(R.string.header_my_friends);
            } else {
                headerTitle = context.getString(R.string.message_adult_no_friends);
            }
            ((HeaderViewHolder) viewHolder).setParseObject(headerTitle);
        } else if (viewHolder instanceof ChildFriendsViewHolder) {
            ((ChildFriendsViewHolder) viewHolder).setParseObject((ChildFriendRecordGroup) this.mChildFriendRecords.get(position - 1));
        } else {
            int myFriendListPosition = ((position - this.mChildFriendRecords.size()) - 2) - childFriendAdjuster;
            if (myFriendListPosition >= 0) {
                ((MyFriendsViewHolder) viewHolder).setParseObject((FriendRecord) this.mMyFriendRecords.get(myFriendListPosition));
            }
        }
    }

    public int getItemViewType(int position) {
        int childFriendAdjuster;
        if (this.mChildFriendRecords.isEmpty()) {
            childFriendAdjuster = 1;
        } else {
            childFriendAdjuster = 0;
        }
        if (position == 0) {
            return 0;
        }
        if (this.mChildFriendRecords.isEmpty() && position == 1) {
            return 1;
        }
        if (position == (this.mChildFriendRecords.size() + childFriendAdjuster) + 1) {
            return 0;
        }
        if (this.mMyFriendRecords.isEmpty() && position == (this.mChildFriendRecords.size() + 2) + childFriendAdjuster) {
            return 1;
        }
        if (position < this.mChildFriendRecords.size() + 1) {
            return 2;
        }
        return 3;
    }

    public void reloadData() {
        this.myFriendsLoaded = false;
        this.childFriendsLoaded = false;
        if (this.mChildFriendsQuery != null) {
            this.mChildFriendsQuery.findInBackground(new 1(this));
        }
        if (this.mMyFriendsQuery != null) {
            this.mMyFriendsQuery.findInBackground(new 2(this));
        }
    }

    public void dataReloadable(boolean friends, boolean profiles) {
        if (friends && profiles) {
            notifyDataSetChanged();
        }
    }

    public int getItemCount() {
        int size;
        if (this.mChildFriendRecords.isEmpty()) {
            size = 2 + 1;
        } else {
            size = 2 + this.mChildFriendRecords.size();
        }
        if (this.mMyFriendRecords.isEmpty()) {
            return size + 1;
        }
        return size + this.mMyFriendRecords.size();
    }

    public long getItemId(int position) {
        int childFriendAdjuster = this.mChildFriendRecords.isEmpty() ? 1 : 0;
        if (position == 0) {
            return 0;
        }
        if (this.mChildFriendRecords.isEmpty() && position == 1) {
            return 1;
        }
        if (position - 1 < this.mChildFriendRecords.size()) {
            return (long) ((ChildFriendRecordGroup) this.mChildFriendRecords.get(position - 1)).hashCode();
        }
        if (position - 1 == this.mChildFriendRecords.size() + childFriendAdjuster) {
            return 2;
        }
        if (this.mMyFriendRecords.isEmpty()) {
            return 3;
        }
        return getHashForParseObject((ParseObject) this.mMyFriendRecords.get(((position - this.mChildFriendRecords.size()) - childFriendAdjuster) - 2));
    }

    private long getHashForParseObject(ParseObject parseObject) {
        return HashCode.fromBytes(parseObject.getObjectId().getBytes()).asLong();
    }

    public void release() {
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(FriendRecordAddedEvent event) {
        ModelHelper.fetchAllFriendsToLocalDatastore(null);
    }

    public void onEvent(FriendRecordDeletedEvent event) {
        reloadData();
    }

    public void onEvent(FriendRecordSavedToLocalDatastoreEvent event) {
        reloadData();
    }

    public void onEvent(ProfileSavedToLocalDatastoreEvent event) {
        ModelHelper.getLocalChildProfilesCount(new 4(this));
    }

    public void onEvent(ProfileAddedEvent event) {
        this.mNumberOfChildren++;
        notifyDataSetChanged();
    }

    public void onEvent(ProfileDeletedEvent event) {
        this.mNumberOfChildren--;
        notifyDataSetChanged();
    }
}
