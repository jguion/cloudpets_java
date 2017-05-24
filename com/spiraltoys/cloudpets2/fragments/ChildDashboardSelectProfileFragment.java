package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.adapters.ProfilesForSendingMessagesAdapter;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardSelectProfileBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DividerItemDecoration;
import com.spiraltoys.cloudpets2.util.Utils;
import java.util.List;

public class ChildDashboardSelectProfileFragment extends Fragment implements OnRefreshListener {
    private static final String ARG_SENDER_ID = "arg_sender_id";
    private ProfilesForSendingMessagesAdapter mAdapter;
    private FragmentChildDashboardSelectProfileBinding mBinding;
    private OnSelectProfileFragmentInteractionListener mListener;
    private Profile mSendingProfile;

    public interface OnSelectProfileFragmentInteractionListener {
        void onProfilesSelected(List<Profile> list);

        void onRefreshProfileList(SwipeRefreshLayout swipeRefreshLayout);
    }

    public static ChildDashboardSelectProfileFragment newInstance(Profile sendingProfile) {
        Bundle args = new Bundle();
        args.putString(ARG_SENDER_ID, sendingProfile.getObjectId());
        ChildDashboardSelectProfileFragment fragment = new ChildDashboardSelectProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                this.mSendingProfile = ModelHelper.getProfileFromLocalDatastore(getArguments().getString(ARG_SENDER_ID));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentChildDashboardSelectProfileBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_select_profile, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        this.mAdapter = new ProfilesForSendingMessagesAdapter(ModelHelper.getProfilesLocalDatastoreQuery(this.mSendingProfile), ModelHelper.getFriendsLocalDatastoreQuery(true, false, false, this.mSendingProfile));
        RecyclerView recyclerView = this.mBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        recyclerView.setAdapter(this.mAdapter);
        this.mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        return this.mBinding.getRoot();
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

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mAdapter != null) {
            this.mAdapter.release();
        }
    }

    @OnClick({2131755256})
    void onSelectProfileClicked() {
        List<Profile> items = this.mAdapter.getSelectedProfiles();
        if (items.isEmpty()) {
            Utils.showHintDialog(getActivity(), (int) R.string.error_select_one_or_more_recipients);
        } else if (this.mListener != null) {
            this.mListener.onProfilesSelected(items);
        }
    }
}
