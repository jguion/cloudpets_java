package com.spiraltoys.cloudpets2.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("FriendAcceptanceNotification")
public class FriendAcceptanceNotification extends ParseObject {
    public static final String FRIEND_RECORD = "friendRecord";

    public FriendRecord getFriendRecord() {
        return (FriendRecord) getParseObject(FRIEND_RECORD);
    }

    public void setFriendRecord(FriendRecord record) {
        put(FRIEND_RECORD, record);
    }
}
