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
import com.spiraltoys.cloudpets2.adapters.VoiceMessageAdapter;
import com.spiraltoys.cloudpets2.databinding.FragmentMessageListBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PrivateProfile.ProfileType;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DividerItemDecoration;
import com.spiraltoys.cloudpets2.util.TopSpaceItemDecoration;

public class VoiceMessagesFragment extends Fragment implements OnRefreshListener {
    private static final String ARG_PROFILE_TYPE = "arg_profile_type";
    private FragmentMessageListBinding mBinding;
    private LinearLayoutManager mLayoutManager;
    private OnMessageFragmentInteractionListener mListener;
    private ProfileType mProfileType;
    private VoiceMessageAdapter mVoiceMessageAdapter;

    public interface OnMessageFragmentInteractionListener {
        void onRefresh(SwipeRefreshLayout swipeRefreshLayout);
    }

    public static VoiceMessagesFragment newInstance(ProfileType profileType) {
        VoiceMessagesFragment fragment = new VoiceMessagesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROFILE_TYPE, profileType);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mProfileType = (ProfileType) getArguments().getSerializable(ARG_PROFILE_TYPE);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mLayoutManager = new LinearLayoutManager(getActivity());
        this.mBinding = (FragmentMessageListBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_message_list, container, false);
        this.mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        this.mBinding.swipeRefreshLayout.post(new 1(this));
        RecyclerView recyclerView = this.mBinding.recyclerView;
        recyclerView.setLayoutManager(this.mLayoutManager);
        recyclerView.addItemDecoration(new TopSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.abc_dialog_list_padding_vertical_material)));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        ModelHelper.getLocalAdultProfileInBackground(new 2(this, recyclerView));
        ModelHelper.getLocalChildProfilesCount(new 3(this));
        return this.mBinding.getRoot();
    }

    public void onDestroyView() {
        if (this.mVoiceMessageAdapter != null) {
            this.mVoiceMessageAdapter.release();
        }
        super.onDestroyView();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnMessageFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onRefresh() {
        if (this.mListener != null) {
            this.mListener.onRefresh(this.mBinding.swipeRefreshLayout);
        }
    }

    private void updateEmptyView() {
        if (this.mBinding.recyclerView.getAdapter().getItemCount() == 0) {
            this.mBinding.emptyView.setVisibility(0);
        } else {
            this.mBinding.emptyView.setVisibility(8);
        }
    }
}
