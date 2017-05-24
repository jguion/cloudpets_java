package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.spiraltoys.cloudpets2.adapters.StoryAdapter;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardStoriesBinding;
import com.spiraltoys.cloudpets2.events.StoryClickedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Story;
import com.spiraltoys.cloudpets2.util.TopSpaceItemDecoration;
import de.greenrobot.event.EventBus;

public class ChildStoriesFragment extends Fragment {
    private StoryAdapter mAdapter;
    private FragmentChildDashboardStoriesBinding mBinding;
    private OnChildDashboardStoriesInteractionListener mListener;

    public interface OnChildDashboardStoriesInteractionListener {
        void onStorySelected(Story story);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentChildDashboardStoriesBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_stories, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        this.mAdapter = new StoryAdapter(getActivity());
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
            this.mListener = (OnChildDashboardStoriesInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnChildDashboardStoriesInteractionListener");
        }
    }

    public void onEvent(StoryClickedEvent event) {
        if (this.mListener != null) {
            this.mListener.onStorySelected(event.getStory());
        }
    }
}
