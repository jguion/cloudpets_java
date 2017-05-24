package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardStoryDetailsBinding;
import com.spiraltoys.cloudpets2.expansion.glide.MainExpansionFile;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Story;
import com.spiraltoys.cloudpets2.util.RoundedRectTransformation;
import icepick.Icepick;

public class ChildStoryDetailsFragment extends Fragment {
    private static final String ARG_STORY = "ARG_STORY";
    private FragmentChildDashboardStoryDetailsBinding mBinding;
    private OnChildDashboardStoryInteractionListener mListener;
    private Story mStory;

    public interface OnChildDashboardStoryInteractionListener {
        void onPlayStory(Story story);
    }

    public static ChildStoryDetailsFragment newInstance(Story story) {
        ChildStoryDetailsFragment fragment = new ChildStoryDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_STORY, story);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState((Object) this, savedInstanceState);
        if (getArguments() != null) {
            this.mStory = (Story) getArguments().getSerializable(ARG_STORY);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentChildDashboardStoryDetailsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_story_details, container, false);
        this.mBinding.setStory(this.mStory);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        Context context = this.mBinding.getRoot().getContext();
        BitmapPool pool = Glide.get(context).getBitmapPool();
        DrawableTypeRequest load = Glide.with(context).load(new MainExpansionFile(this.mStory.getPreviewImagePath()));
        Transformation[] transformationArr = new Transformation[1];
        transformationArr[0] = new RoundedRectTransformation(context, pool, -1, -1, (float) context.getResources().getDimensionPixelSize(R.dimen.default_rounded_corner_radius), false);
        load.bitmapTransform(transformationArr).into(this.mBinding.storyImage);
        return this.mBinding.getRoot();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnChildDashboardStoryInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    @OnClick({2131755240})
    public void onPlayStoryClicked() {
        this.mListener.onPlayStory(this.mStory);
    }
}
