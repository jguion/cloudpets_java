package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.spiraltoys.cloudpets2.adapters.FriendPermissionsAdapter;
import com.spiraltoys.cloudpets2.databinding.FragmentFriendPermissionsBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DividerItemDecoration;
import com.spiraltoys.cloudpets2.util.Utils;

public class FriendRequestsFragment extends Fragment implements OnRefreshListener {
    private FriendPermissionsAdapter mAdapter;
    private FragmentFriendPermissionsBinding mBinding;
    private OnFriendRequestInteractionListener mListener;

    public interface OnFriendRequestInteractionListener {
        void onInviteFriend();

        void onRefreshFriendRequestList(SwipeRefreshLayout swipeRefreshLayout);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentFriendPermissionsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_friend_permissions, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        this.mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        this.mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        try {
            setupAdapters(ModelHelper.getLocalAdultProfile());
            return this.mBinding.getRoot();
        } catch (ParseException e) {
            e.printStackTrace();
            reloadProfilesFromServerAndAttemptRecovery();
            return this.mBinding.getRoot();
        }
    }

    private void reloadProfilesFromServerAndAttemptRecovery() {
        ModelHelper.fetchAllProfilesToLocalDatastore(new 1(this));
    }

    private void setupAdapters(Profile adultProfile) {
        ParseQuery<FriendRecord> childFriendsQuery = ModelHelper.getFriendsLocalDatastoreQuery(true, true, false, null).whereEqualTo("recipient", Boolean.valueOf(false));
        ParseQuery<Profile> innerQuery = ParseQuery.getQuery(Profile.class);
        innerQuery.whereEqualTo(Profile.OWNER, ParseUser.getCurrentUser());
        childFriendsQuery.whereMatchesQuery(FriendRecord.SOURCE_PROFILE, innerQuery);
        try {
            this.mAdapter = new FriendPermissionsAdapter(childFriendsQuery, ModelHelper.getFriendsLocalDatastoreQuery(true, true, false, adultProfile), ModelHelper.getLocalChildProfilesCount());
            this.mBinding.recyclerView.setAdapter(this.mAdapter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        checkForChildProfiles();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnFriendRequestInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFriendRequestInteractionListener");
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mAdapter != null) {
            this.mAdapter.release();
        }
        this.mListener = null;
    }

    public void onRefresh() {
        if (this.mListener != null) {
            this.mListener.onRefreshFriendRequestList(this.mBinding.swipeRefreshLayout);
        }
    }

    @OnClick({2131755200})
    public void onInviteFriendClicked() {
        try {
            if (ModelHelper.getLocalChildProfilesCount() > 0) {
                this.mListener.onInviteFriend();
            } else {
                Utils.showHintDialog(getActivity(), (int) R.string.message_no_friends);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void checkForChildProfiles() {
    }
}
