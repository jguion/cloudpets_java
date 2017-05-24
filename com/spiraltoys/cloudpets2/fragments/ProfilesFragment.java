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
import com.spiraltoys.cloudpets2.CreateOrEditChildProfileActivity;
import com.spiraltoys.cloudpets2.EditParentProfileActivity;
import com.spiraltoys.cloudpets2.OnboardingCreateChildProfileActivity;
import com.spiraltoys.cloudpets2.adapters.SingleSelectProfileAdapter;
import com.spiraltoys.cloudpets2.databinding.FragmentProfilesListBinding;
import com.spiraltoys.cloudpets2.events.ProfileSelectedEvent;
import com.spiraltoys.cloudpets2.events.ProfileSelectedForEditEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PrivateProfile.ProfileType;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DividerItemDecoration;
import com.spiraltoys.cloudpets2.util.TopSpaceItemDecoration;
import de.greenrobot.event.EventBus;

public class ProfilesFragment extends Fragment implements OnRefreshListener {
    private FragmentProfilesListBinding mBinding;
    private OnProfileInteractionListener mListener;
    private SingleSelectProfileAdapter mProfileAdapter;

    public interface OnProfileInteractionListener {
        void onRefreshProfileList(SwipeRefreshLayout swipeRefreshLayout);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentProfilesListBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_profiles_list, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        this.mProfileAdapter = new SingleSelectProfileAdapter(ModelHelper.getProfilesLocalDatastoreQuery(), ProfileSelectedForEditEvent.class);
        this.mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        this.mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mBinding.recyclerView.addItemDecoration(new TopSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.abc_dialog_list_padding_vertical_material)));
        this.mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        this.mBinding.recyclerView.setAdapter(this.mProfileAdapter);
        return this.mBinding.getRoot();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnProfileInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnProfileInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onStart() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDestroyView() {
        if (this.mProfileAdapter != null) {
            this.mProfileAdapter.release();
        }
        super.onDestroyView();
    }

    public void onRefresh() {
        if (this.mListener != null) {
            this.mListener.onRefreshProfileList(this.mBinding.swipeRefreshLayout);
        }
    }

    @OnClick({2131755295})
    void onAddProfileClicked() {
        OnboardingCreateChildProfileActivity.startWithProfile(getActivity(), new Profile());
    }

    public void onEvent(ProfileSelectedEvent event) {
        if (event.getSelectedProfile().getPrivateProfile().getProfileType() == ProfileType.ADULT) {
            EditParentProfileActivity.startWithProfile(getActivity(), event.getSelectedProfile());
        } else {
            CreateOrEditChildProfileActivity.startWithProfile(getActivity(), event.getSelectedProfile());
        }
    }
}
