package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.spiraltoys.cloudpets2.BaseActivity;
import com.spiraltoys.cloudpets2.adapters.FriendedProfileAdapter;
import com.spiraltoys.cloudpets2.adapters.ProfileAdapter;
import com.spiraltoys.cloudpets2.adapters.ProfileSelector;
import com.spiraltoys.cloudpets2.adapters.ProfilesForSendingMessagesAdapter;
import com.spiraltoys.cloudpets2.databinding.FragmentSelectProfileBinding;
import com.spiraltoys.cloudpets2.events.ProfileSelectedForToyDashboardEvent;
import com.spiraltoys.cloudpets2.events.ProfileSelectionChangedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessageFailedToSend;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DividerItemDecoration;
import com.spiraltoys.cloudpets2.util.TopSpaceItemDecoration;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import java.util.Collection;
import java.util.Comparator;

public class SelectProfileFragment extends DialogFragment implements OnRefreshListener {
    private static Comparator<Profile> FRIEND_PERMISSION_COMPARATOR = new 2();
    private static final String FRIEND_PROFILE_ID = "FRIEND_PROFILE_ID";
    private static final String INVITE_SELECT = "INVITE_SELECT";
    private static final String MULTI_SELECT = "MULTI_SELECT";
    private static final String SET_TITLE_TO_SEND = "SET_TITLE_TO_SEND";
    public static final String TAG = "SELECT_PROFILE_DIALOG";
    private Adapter mAdapter;
    private FragmentSelectProfileBinding mBinding;
    private String mFriendProfileId;
    private boolean mIsInviteSelect;
    private boolean mIsMultiSelect;
    private OnSelectProfileFragmentInteractionListener mListener;
    private boolean mSetTitleToSend;

    public interface OnSelectProfileFragmentInteractionListener {
        void onProfilesSelected(Collection<Profile> collection);

        void onRefreshProfileList(SwipeRefreshLayout swipeRefreshLayout);
    }

    public static SelectProfileFragment newInstance() {
        SelectProfileFragment fragment = new SelectProfileFragment();
        Bundle args = new Bundle();
        args.putBoolean(MULTI_SELECT, false);
        fragment.setArguments(args);
        return fragment;
    }

    public static SelectProfileFragment newInstance(boolean isMultiSelect, boolean isInviteSelect, boolean setTitleToSend, String friendProfileId) {
        SelectProfileFragment fragment = new SelectProfileFragment();
        Bundle args = new Bundle();
        args.putBoolean(MULTI_SELECT, isMultiSelect);
        args.putBoolean(INVITE_SELECT, isInviteSelect);
        args.putBoolean(SET_TITLE_TO_SEND, setTitleToSend);
        args.putString(FRIEND_PROFILE_ID, friendProfileId);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            this.mIsInviteSelect = getArguments().getBoolean(INVITE_SELECT);
            this.mIsMultiSelect = getArguments().getBoolean(MULTI_SELECT);
            this.mSetTitleToSend = getArguments().getBoolean(SET_TITLE_TO_SEND);
            this.mFriendProfileId = getArguments().getString(FRIEND_PROFILE_ID);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentSelectProfileBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_select_profile, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        if ((getActivity() instanceof BaseActivity) && ((BaseActivity) getActivity()).getSupportActionBar() != null && this.mSetTitleToSend) {
            ((BaseActivity) getActivity()).getSupportActionBar().setTitle((int) R.string.title_send);
        }
        this.mBinding.recyclerView.addItemDecoration(new TopSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.list_single_line_margin_top)));
        this.mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        this.mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        if (!this.mIsMultiSelect || this.mIsInviteSelect) {
            this.mBinding.selectProfileButton.setVisibility(8);
            this.mBinding.title.setVisibility(8);
        }
        try {
            setupAdapter(ModelHelper.getLocalAdultProfile());
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

    private void setupAdapter(Profile adultProfile) {
        ParseQuery<Profile> myChildrenQuery = ModelHelper.getProfilesLocalDatastoreQuery(adultProfile);
        ParseQuery<FriendRecord> myFriendsQuery = ModelHelper.getFriendsLocalDatastoreQuery(true, false, false, adultProfile);
        if (this.mIsInviteSelect) {
            if (this.mFriendProfileId == null) {
                this.mAdapter = new ProfileAdapter(myChildrenQuery, ProfileSelectionChangedEvent.class);
            } else {
                try {
                    this.mAdapter = new FriendedProfileAdapter(myChildrenQuery, ModelHelper.getProfileFromLocalDatastore(this.mFriendProfileId));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } else if (this.mIsMultiSelect) {
            this.mAdapter = new ProfilesForSendingMessagesAdapter(myChildrenQuery, myFriendsQuery);
        } else {
            this.mAdapter = new ProfileAdapter(myChildrenQuery, ProfileSelectedForToyDashboardEvent.class);
        }
        this.mBinding.recyclerView.setAdapter(this.mAdapter);
    }

    public void onRefresh() {
        if (this.mListener != null) {
            this.mListener.onRefreshProfileList(this.mBinding.swipeRefreshLayout);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnSelectProfileFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnSelectProfileFragmentInteractionListener");
        }
    }

    public void onEvent(VoiceMessageFailedToSend event) {
        this.mBinding.selectProfileButton.setText(R.string.btn_send_to_children_retry);
    }

    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onDestroyView() {
        if (this.mAdapter != null) {
            if (this.mAdapter instanceof ProfileAdapter) {
                ((ProfileAdapter) this.mAdapter).release();
            }
            if (this.mAdapter instanceof ProfilesForSendingMessagesAdapter) {
                ((ProfilesForSendingMessagesAdapter) this.mAdapter).release();
            }
        }
        super.onDestroyView();
    }

    @OnClick({2131755317})
    void onSelectProfileClicked() {
        if (this.mAdapter instanceof ProfileSelector) {
            Collection<Profile> items = ((ProfileSelector) this.mAdapter).getSelectedProfiles();
            if (items.isEmpty()) {
                Utils.showHintDialog(getActivity(), (int) R.string.error_select_one_or_more_recipients);
            } else {
                this.mListener.onProfilesSelected(items);
            }
        }
    }

    public void onEvent(ProfileSelectionChangedEvent event) {
        this.mListener.onProfilesSelected(event.getSelectedProfiles());
    }
}
