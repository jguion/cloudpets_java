package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.spiraltoys.cloudpets2.adapters.LullabyAdapter;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardLullabiesBinding;
import com.spiraltoys.cloudpets2.events.LullabyClickedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Lullaby;
import com.spiraltoys.cloudpets2.util.TopSpaceItemDecoration;
import de.greenrobot.event.EventBus;

public class ChildLullabiesFragment extends Fragment {
    private LullabyAdapter mAdapter;
    private FragmentChildDashboardLullabiesBinding mBinding;
    private OnChildDashboardLullabiesInteractionListener mListener;

    public interface OnChildDashboardLullabiesInteractionListener {
        void onLullabySelected(Lullaby lullaby);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentChildDashboardLullabiesBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_lullabies, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        this.mAdapter = new LullabyAdapter();
        RecyclerView recyclerView = this.mBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new TopSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.abc_dialog_list_padding_vertical_material)));
        recyclerView.setAdapter(this.mAdapter);
        return this.mBinding.getRoot();
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnChildDashboardLullabiesInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnChildDashboardLullabiesInteractionListener");
        }
    }

    public void onEvent(LullabyClickedEvent event) {
        if (this.mListener != null) {
            this.mListener.onLullabySelected(event.getLullaby());
        }
    }
}
