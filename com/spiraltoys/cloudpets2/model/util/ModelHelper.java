package com.spiraltoys.cloudpets2.model.util;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.vending.expansion.downloader.Constants;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.parse.CountCallback;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.GetCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.model.FriendAcceptanceNotification;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.FriendRelationship;
import com.spiraltoys.cloudpets2.model.FriendStatus;
import com.spiraltoys.cloudpets2.model.PrivateProfile;
import com.spiraltoys.cloudpets2.model.PrivateProfile.ProfileType;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.model.events.LocalDatastoreChangedEvent;
import com.spiraltoys.cloudpets2.util.Analytics;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelHelper {
    private static final int FRIENDS_FETCH_LIMIT = 1000;
    public static final String FRIENDS_TAG = "friends";
    private static final int PROFILES_FETCH_LIMIT = 1000;
    public static final String PROFILES_TAG = "profiles";
    public static final String VOICE_MESSAGES_TAG = "voice_messages";
    private static final int VOICE_MESSAGE_FETCH_LIMIT = 1000;

    private ModelHelper() {
    }

    public static void preFetch(GetCallback<ParseUser> callback) {
        fetchAllProfilesToLocalDatastore(new 1(callback));
    }

    public static void saveVoiceMessage(VoiceMessage voiceMessage, SaveCallback callback) {
        voiceMessage.saveInBackground(new 2(callback, voiceMessage));
    }

    public static void saveVoiceMessages(List<VoiceMessage> voiceMessages, SaveCallback callback) {
        ParseObject.saveAllInBackground(voiceMessages, new 3(callback, voiceMessages));
    }

    public static void saveProfile(Profile profile, SaveCallback callback) {
        profile.saveInBackground(new 4(callback, profile));
    }

    public static void addFriend(Profile profile, String emailAddress, String name, FriendRelationship relationship, SaveCallback callback) {
        String finalEmailAddress = emailAddress.trim();
        String finalName = name.trim();
        if (TextUtils.isEmpty(name)) {
            callback.done(new ParseException(200, null));
        } else if (Utils.isValidEmailAddress(emailAddress)) {
            FriendRecord friend = new FriendRecord();
            friend.setTargetEmailAddress(finalEmailAddress);
            friend.setName(finalName);
            friend.setRelationship(relationship);
            friend.setSourceProfile(profile);
            friend.saveInBackground(new 5(callback, friend));
        } else {
            callback.done(new ParseException(ParseException.INVALID_EMAIL_ADDRESS, null));
        }
    }

    public static void addFriends(Collection<Profile> profiles, String emailAddress, String name, FriendRelationship relationship, SaveCallback callback) {
        String finalEmailAddress = emailAddress.trim();
        String finalName = name.trim();
        if (TextUtils.isEmpty(name)) {
            callback.done(new ParseException(200, null));
        } else if (Utils.isValidEmailAddress(emailAddress)) {
            List<FriendRecord> friendRecords = new ArrayList();
            for (Profile profile : profiles) {
                FriendRecord friend = new FriendRecord();
                friend.setTargetEmailAddress(finalEmailAddress);
                friend.setName(finalName);
                friend.setRelationship(relationship);
                friend.setSourceProfile(profile);
                friendRecords.add(friend);
            }
            ParseObject.saveAllInBackground(friendRecords, new 6(callback));
        } else {
            callback.done(new ParseException(ParseException.INVALID_EMAIL_ADDRESS, null));
        }
    }

    public static ParseQuery<FriendRecord> getFriendsQuery() {
        ParseQuery<FriendRecord> query = ParseQuery.getQuery(FriendRecord.class);
        query.include(FriendRecord.SOURCE_PROFILE);
        query.include("sourceProfile.portrait");
        query.include("sourceProfile.plushToy");
        query.include("sourceProfile.privateProfile");
        query.include(FriendRecord.TARGET_PROFILE);
        query.include("targetProfile.portrait");
        query.include("targetProfile.plushToy");
        query.include("targetProfile.privateProfile");
        return query;
    }

    private static ParseQuery<FriendRecord> getFriendsQuery(Profile profile) {
        ParseQuery<FriendRecord> query = getFriendsQuery();
        if (profile != null) {
            query.whereEqualTo(FriendRecord.SOURCE_PROFILE, profile);
        }
        return query;
    }

    private static ParseQuery<FriendRecord> getFriendsQuery(boolean includeAccepted, boolean includePending, boolean includeRejected) {
        return getFriendsQuery(includeAccepted, includePending, includeRejected, null);
    }

    private static ParseQuery<FriendRecord> getFriendsQuery(boolean includeAccepted, boolean includePending, boolean includeRejected, Profile profile) {
        ArrayList<String> statusIncludes = new ArrayList();
        if (includeAccepted) {
            statusIncludes.add(FriendStatus.ACCEPTED.getModelName());
        }
        if (includePending) {
            statusIncludes.add(FriendStatus.PENDING.getModelName());
        }
        if (includeRejected) {
            statusIncludes.add(FriendStatus.REJECTED.getModelName());
        }
        ParseQuery<FriendRecord> query = getFriendsQuery(profile);
        query.whereContainedIn("status", statusIncludes);
        return query;
    }

    public static ParseQuery<FriendRecord> getFriendsLocalDatastoreQuery(boolean includeAccepted, boolean includePending, boolean includeRejected, Profile profile) {
        return getFriendsQuery(includeAccepted, includePending, includeRejected, profile).fromPin(FRIENDS_TAG);
    }

    public static boolean isAcceptedFriend(Profile ownedProfile, Profile friendProfile) throws ParseException {
        return getFriendsQuery(true, false, false, ownedProfile).fromPin(FRIENDS_TAG).whereEqualTo(FriendRecord.TARGET_PROFILE, friendProfile).fromPin(FRIENDS_TAG).count() > 0;
    }

    @DebugLog
    public static Profile getProfileFromLocalDatastore(String objectId) throws ParseException {
        try {
            return (Profile) getProfilesLocalDatastoreQuery().get(objectId);
        } catch (ParseException e) {
            return getFriendProfileFromLocalDatastore(objectId);
        }
    }

    @DebugLog
    public static Profile getFriendProfileFromLocalDatastore(String objectId) throws ParseException {
        try {
            return (Profile) getFriendProfilesLocalDatastoreQuery().get(objectId);
        } catch (ParseException e) {
            Analytics.logLocalDatastoreException(e, PROFILES_TAG, "objectId: " + objectId);
            throw e;
        }
    }

    public static void countPendingIncomingFriendRequests(Profile recipientProfile, CountCallback callback) {
        if (AccountHelper.isLoggedIn()) {
            getPendingIncomingFriendsQuery(recipientProfile).fromPin(FRIENDS_TAG).countInBackground(callback);
        } else {
            callback.done(0, new ParseException(ParseException.OPERATION_FORBIDDEN, null));
        }
    }

    private static ParseQuery<FriendRecord> getPendingIncomingFriendsQuery(Profile recipientProfile) {
        ParseQuery<FriendRecord> query = getFriendsQuery();
        query.whereEqualTo("status", FriendStatus.PENDING.getModelName());
        query.whereEqualTo("recipient", Boolean.valueOf(true));
        query.whereEqualTo(FriendRecord.SOURCE_PROFILE, recipientProfile);
        return query;
    }

    public static void getPendingFriendRequestAcceptanceNotifications(FindCallback<FriendAcceptanceNotification> callback) {
        if (AccountHelper.isLoggedIn()) {
            ParseQuery<FriendAcceptanceNotification> query = ParseQuery.getQuery(FriendAcceptanceNotification.class);
            query.include(FriendAcceptanceNotification.FRIEND_RECORD);
            query.include("friendRecord.targetProfile");
            query.include("friendRecord.targetProfile.portrait");
            query.include("friendRecord.targetProfile.plushToy");
            query.include("friendRecord.targetProfile.privateProfile");
            query.include("friendRecord.sourceProfile");
            query.include("friendRecord.sourceProfile.portrait");
            query.include("friendRecord.sourceProfile.plushToy");
            query.include("friendRecord.sourceProfile.privateProfile");
            query.findInBackground(callback);
            return;
        }
        callback.done(null, new ParseException(ParseException.OPERATION_FORBIDDEN, null));
    }

    public static void clearPendingFriendRequestAcceptanceNotification(FriendAcceptanceNotification notification, DeleteCallback callback) {
        if (AccountHelper.isLoggedIn()) {
            notification.deleteInBackground(callback);
        } else {
            callback.done(new ParseException(ParseException.OPERATION_FORBIDDEN, null));
        }
    }

    public static void clearAllFriendRequestAcceptanceNotifications(DeleteCallback callback) {
        if (AccountHelper.isLoggedIn()) {
            getPendingFriendRequestAcceptanceNotifications(new 7(callback));
        } else {
            callback.done(new ParseException(ParseException.OPERATION_FORBIDDEN, null));
        }
    }

    public static void resendVerificationEmail(SaveCallback callback) {
        if (AccountHelper.isLoggedIn()) {
            double random1 = Math.floor((Math.random() * 25000.0d) + 1.0d);
            double random2 = Math.floor((Math.random() * 25000.0d) + 1.0d);
            ParseUser currentUser = ParseUser.getCurrentUser();
            String originalEmail = currentUser.getEmail();
            if (currentUser != null && currentUser.isAuthenticated()) {
                currentUser.setEmail(random1 + "@" + random2 + ".com");
                currentUser.saveInBackground(new 8(currentUser, originalEmail, callback));
                return;
            }
            return;
        }
        callback.done(new ParseException(ParseException.OPERATION_FORBIDDEN, null));
    }

    public static void resolvePendingFriendRequest(Context c, FriendRecord record, boolean accept, FunctionCallback<FriendRecord> callback) {
        if (AccountHelper.isLoggedIn()) {
            HashMap<String, Object> params = new HashMap();
            params.put("accepted", Boolean.valueOf(accept));
            params.put("friendRecordId", record.getObjectId());
            ParseCloud.callFunctionInBackground("resolvePendingFriendRequest", params, new 9(callback));
            return;
        }
        callback.done(null, new ParseException(ParseException.OPERATION_FORBIDDEN, null));
    }

    private static ParseQuery<Profile> getProfilesQuery() {
        ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);
        query.whereEqualTo(Profile.OWNER, ParseUser.getCurrentUser());
        query.orderByAscending("createdAt");
        query.include(Profile.PRIVATE_PROFILE);
        query.include("portrait");
        query.include(Profile.PLUSH_TOY);
        return query;
    }

    public static ParseQuery<Profile> getProfilesLocalDatastoreQuery() {
        return getProfilesQuery().fromPin(PROFILES_TAG);
    }

    private static ParseQuery<Profile> getProfilesQuery(Profile profileToExclude) {
        ParseQuery<Profile> query = getProfilesQuery();
        query.whereNotEqualTo(Profile.OBJECT_ID, profileToExclude.getObjectId());
        return query;
    }

    public static ParseQuery<Profile> getProfilesLocalDatastoreQuery(Profile profileToExclude) {
        return getProfilesQuery(profileToExclude).fromPin(PROFILES_TAG);
    }

    public static ParseQuery<Profile> getFriendProfilesLocalDatastoreQuery() {
        return getProfilesQuery().fromPin(FRIENDS_TAG);
    }

    public static ParseQuery<Profile> getOwnedProfilesLocalDatastoreQuery(Profile owner) {
        return getProfilesQuery(owner).whereEqualTo(Profile.OWNER, owner.getOwner()).fromPin(PROFILES_TAG);
    }

    private static ParseQuery<Profile> getChildProfilesQuery() {
        ParseQuery<PrivateProfile> innerQuery = ParseQuery.getQuery(PrivateProfile.class);
        innerQuery.whereEqualTo(PrivateProfile.PROFILE_TYPE, ProfileType.CHILD.toString());
        ParseQuery<Profile> query = getProfilesQuery();
        query.whereMatchesQuery(Profile.PRIVATE_PROFILE, innerQuery);
        query.orderByAscending("createdAt");
        return query;
    }

    public static ParseQuery<Profile> getChildProfilesLocalDatastoreQuery() {
        return getChildProfilesQuery().fromPin(PROFILES_TAG);
    }

    public static void getLocalAdultProfileInBackground(GetCallback<Profile> callback) {
        ParseQuery<PrivateProfile> innerQuery = ParseQuery.getQuery(PrivateProfile.class);
        innerQuery.whereEqualTo(PrivateProfile.PROFILE_TYPE, ProfileType.ADULT.toString());
        ParseQuery<Profile> query = getProfilesQuery();
        query.whereMatchesQuery(Profile.PRIVATE_PROFILE, innerQuery);
        query.fromPin(PROFILES_TAG);
        query.getFirstInBackground(callback);
    }

    @DebugLog
    public static Profile getLocalAdultProfile() throws ParseException {
        ParseQuery<Profile> query = getProfilesLocalDatastoreQuery();
        query.whereDoesNotExist(Profile.PLUSH_TOY);
        try {
            return (Profile) query.getFirst();
        } catch (ParseException e) {
            Analytics.logLocalDatastoreException(e, PROFILES_TAG);
            throw e;
        }
    }

    public static int getLocalChildProfilesCount() throws ParseException {
        if (AccountHelper.isLoggedIn()) {
            ParseQuery<Profile> query = getChildProfilesQuery();
            query.fromPin(PROFILES_TAG);
            return query.count();
        }
        throw new ParseException(ParseException.OPERATION_FORBIDDEN, null);
    }

    public static void getLocalChildProfilesCount(CountCallback callback) {
        if (AccountHelper.isLoggedIn()) {
            ParseQuery<Profile> query = getChildProfilesQuery();
            query.fromPin(PROFILES_TAG);
            query.countInBackground(callback);
            return;
        }
        callback.done(0, new ParseException(ParseException.OPERATION_FORBIDDEN, null));
    }

    public static void getProfileById(String profileId, GetCallback<Profile> callback) {
        getProfilesLocalDatastoreQuery().getInBackground(profileId, new 10(callback));
    }

    public static void fetchAllProfilesToLocalDatastore(FindCallback<Profile> callback) {
        fetchAllToLocalDatastore(PROFILES_TAG, getProfilesQuery().setLimit(Constants.MAX_DOWNLOADS).fromPin(PROFILES_TAG), getProfilesQuery().setLimit(Constants.MAX_DOWNLOADS), new 11(callback));
    }

    public static void fetchAllFriendsToLocalDatastore(FindCallback<FriendRecord> callback) {
        fetchAllToLocalDatastore(FRIENDS_TAG, getFriendsQuery().setLimit(Constants.MAX_DOWNLOADS).fromPin(FRIENDS_TAG), getFriendsQuery().setLimit(Constants.MAX_DOWNLOADS), new 12(callback));
    }

    public static void fetchAllVoiceMessagesToLocalDatastore(FindCallback<VoiceMessage> callback) {
        fetchAllToLocalDatastore(VOICE_MESSAGES_TAG, getVoiceMessagesQuery().setLimit(Constants.MAX_DOWNLOADS).fromPin(VOICE_MESSAGES_TAG), getVoiceMessagesQuery().setLimit(Constants.MAX_DOWNLOADS), callback);
    }

    private static <T extends ParseObject> void fetchAllToLocalDatastore(String pinName, ParseQuery<T> localDatastoreQuery, ParseQuery<T> remoteDatastoreQuery, FindCallback<T> callback) {
        localDatastoreQuery.findInBackground(new 13(pinName, callback, remoteDatastoreQuery));
    }

    @DebugLog
    private static void notifyChanges(HashMap<ParseObject, Date> originalObjectModificationDates, List<? extends ParseObject> results) {
        Handler handler = new Handler();
        Set<ParseObject> insertedItems = new HashSet(results);
        Set<ParseObject> updatedItems = new HashSet();
        Set<ParseObject> deletedItems = new HashSet(originalObjectModificationDates.keySet());
        insertedItems.removeAll(originalObjectModificationDates.keySet());
        deletedItems.removeAll(results);
        for (ParseObject result : results) {
            Date originalUpdateDate = (Date) originalObjectModificationDates.get(result);
            if (!(originalUpdateDate == null || originalUpdateDate.equals(result.getUpdatedAt()))) {
                updatedItems.add(result);
            }
        }
        if (insertedItems.size() > 0 || updatedItems.size() > 0 || deletedItems.size() > 0) {
            EventBus.getDefault().post(new LocalDatastoreChangedEvent(insertedItems, updatedItems, deletedItems));
        }
    }

    public static void countLocalNewMessages(Profile profile, boolean excludeProfile, boolean isParentalControlled, CountCallback callback) {
        ParseQuery<VoiceMessage> query = getVoiceMessagesQuery(profile, excludeProfile);
        if (!isParentalControlled || (profile.getPrivateProfile().isAdult() && !excludeProfile)) {
            query.whereContainedIn("flags", Ints.asList(VoiceMessage.VOICE_MESSAGE_FLAG_VALID_NEW_MESSAGE_PERMUTATIONS));
        } else {
            Set<Integer> approvedFlags = new HashSet();
            Set<Integer> newFlags = new HashSet();
            approvedFlags.addAll(Ints.asList(VoiceMessage.VOICE_MESSAGE_FLAG_APPROVED_MESSAGE_PERMUTATIONS));
            newFlags.addAll(Ints.asList(VoiceMessage.VOICE_MESSAGE_FLAG_VALID_NEW_MESSAGE_PERMUTATIONS));
            query.whereContainedIn("flags", Sets.intersection(approvedFlags, newFlags));
        }
        query.fromPin(VOICE_MESSAGES_TAG);
        query.countInBackground(callback);
    }

    public static void getLocalVoiceMessagesWithServerFallback(Profile profile, boolean excludeProfile, boolean isParentalControlled, FindCallback<VoiceMessage> callback) {
        getVoiceMessagesQuery(profile, excludeProfile, isParentalControlled).fromPin(VOICE_MESSAGES_TAG).findInBackground(new 14(profile, excludeProfile, isParentalControlled, callback));
    }

    public static VoiceMessage getVoiceMessageFromLocalDatastore(String objectId) throws ParseException {
        return (VoiceMessage) getVoiceMessagesQuery().fromPin(VOICE_MESSAGES_TAG).get(objectId);
    }

    private static ParseQuery<VoiceMessage> getVoiceMessagesQuery() {
        ParseQuery<VoiceMessage> query = ParseQuery.getQuery(VoiceMessage.class);
        query.whereExists(VoiceMessage.SENDER);
        query.whereExists("recipient");
        query.include(VoiceMessage.SENDER);
        query.include("sender.portrait");
        query.include("sender.plushToy");
        query.include("sender.privateProfile");
        query.include("recipient");
        query.include("recipient.portrait");
        query.include("recipient.plushToy");
        query.include("recipient.privateProfile");
        query.orderByDescending("createdAt");
        return query;
    }

    private static ParseQuery<VoiceMessage> getVoiceMessagesQuery(Profile profile, boolean excludeProfile) {
        ParseQuery<VoiceMessage> query = getVoiceMessagesQuery();
        if (excludeProfile) {
            ParseQuery<Profile> innerQuery = ParseQuery.getQuery(Profile.class);
            innerQuery.whereEqualTo(Profile.OWNER, ParseUser.getCurrentUser());
            query.whereNotEqualTo("recipient", profile);
            query.whereMatchesQuery("recipient", innerQuery);
        } else {
            query.whereEqualTo("recipient", profile);
        }
        return query;
    }

    public static ParseQuery<VoiceMessage> getVoiceMessagesLocalDatastoreQuery(Profile profile, boolean excludeProfile) {
        return getVoiceMessagesQuery(profile, excludeProfile).fromPin(VOICE_MESSAGES_TAG);
    }

    private static ParseQuery<VoiceMessage> getVoiceMessagesQuery(Profile profile, boolean excludeProfile, boolean isParentalControlled) {
        ParseQuery<VoiceMessage> query = getVoiceMessagesQuery(profile, excludeProfile);
        if (isParentalControlled) {
            query.whereContainedIn("flags", Ints.asList(VoiceMessage.VOICE_MESSAGE_FLAG_APPROVED_MESSAGE_PERMUTATIONS));
        }
        return query;
    }

    public static ParseQuery<VoiceMessage> getVoiceMessagesLocalDatastoreQuery(Profile profile, boolean excludeProfile, boolean isParentalControlled) {
        return getVoiceMessagesQuery(profile, excludeProfile, isParentalControlled).fromPin(VOICE_MESSAGES_TAG);
    }

    private static ParseQuery<VoiceMessage> getVoiceMessagesQuery(Profile profile, boolean excludeProfile, boolean isParentalControlled, boolean isSavedMode) {
        ParseQuery<VoiceMessage> query = getVoiceMessagesQuery(profile, excludeProfile, isParentalControlled);
        if (isSavedMode) {
            query.whereGreaterThanOrEqualTo("flags", Integer.valueOf(8));
        } else {
            query.whereLessThan("flags", Integer.valueOf(8));
        }
        return query;
    }

    public static ParseQuery<VoiceMessage> getVoiceMessagesLocalDatastoreQuery(Profile profile, boolean excludeProfile, boolean isParentalControlled, boolean isSavedMode) {
        return getVoiceMessagesQuery(profile, excludeProfile, isParentalControlled, isSavedMode).fromPin(VOICE_MESSAGES_TAG);
    }
}
