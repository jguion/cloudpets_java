package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.spiraltoys.cloudpets2.databinding.FragmentChildDashboardLullabyDetailsBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Lullaby;
import com.spiraltoys.cloudpets2.util.RoundedRectTransformation;
import icepick.Icepick;
import icepick.State;

public class ChildLullabyDetailsFragment extends Fragment {
    private static final String ARG_LULLABY = "arg_lullaby";
    private static final long[] LULLABY_LENGTHS_MS = new long[]{300000, 480000, 600000, 720000, 900000};
    private static final double[] LULLABY_VOLUMES = new double[]{0.05d, 0.5d, 1.0d};
    private FragmentChildDashboardLullabyDetailsBinding mBinding;
    private String[] mDurationLabels;
    private OnChildDashboardLullabyInteractionListener mListener;
    private Lullaby mLullaby;
    @State
    int mSelectedDurationIndex;
    @State
    int mSelectedVolumeIndex;
    private String[] mVolumeLabels;

    public interface OnChildDashboardLullabyInteractionListener {
        void onSendLullabyToToySelected(Lullaby lullaby, double d, long j);
    }

    public static ChildLullabyDetailsFragment newInstance(Lullaby lullaby) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_LULLABY, lullaby);
        ChildLullabyDetailsFragment fragment = new ChildLullabyDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState((Object) this, savedInstanceState);
        this.mVolumeLabels = getResources().getStringArray(R.array.toy_volumes);
        this.mDurationLabels = getResources().getStringArray(R.array.lullaby_lengths);
        if (getArguments() != null) {
            this.mLullaby = (Lullaby) getArguments().getSerializable(ARG_LULLABY);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentChildDashboardLullabyDetailsBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_child_dashboard_lullaby_details, container, false);
        this.mBinding.setLullaby(this.mLullaby);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        this.mBinding.volumeLabel.setText(this.mVolumeLabels[this.mSelectedVolumeIndex]);
        this.mBinding.durationLabel.setText(this.mDurationLabels[this.mSelectedDurationIndex]);
        updateButtonStates();
        Context context = this.mBinding.getRoot().getContext();
        BitmapPool pool = Glide.get(context).getBitmapPool();
        DrawableTypeRequest load = Glide.with(context).load(Integer.valueOf(this.mLullaby.getImageResourceId()));
        Transformation[] transformationArr = new Transformation[1];
        transformationArr[0] = new RoundedRectTransformation(context, pool, -1, -1, (float) context.getResources().getDimensionPixelSize(R.dimen.default_rounded_corner_radius), false);
        load.bitmapTransform(transformationArr).into(this.mBinding.lullabyImage);
        return this.mBinding.getRoot();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState((Object) this, outState);
    }

    @OnClick({2131755256})
    void onSendToToyClicked() {
        if (this.mListener != null) {
            this.mListener.onSendLullabyToToySelected(this.mLullaby, LULLABY_VOLUMES[this.mSelectedVolumeIndex], LULLABY_LENGTHS_MS[this.mSelectedDurationIndex]);
        }
    }

    @OnClick({2131755252})
    void onIncreaseDurationClicked() {
        if (this.mSelectedDurationIndex < this.mDurationLabels.length - 1) {
            TextView textView = this.mBinding.durationLabel;
            String[] strArr = this.mDurationLabels;
            int i = this.mSelectedDurationIndex + 1;
            this.mSelectedDurationIndex = i;
            textView.setText(strArr[i]);
            updateDurationButtonStates();
        }
    }

    @OnClick({2131755250})
    void onReduceDurationClicked() {
        if (this.mSelectedDurationIndex > 0) {
            TextView textView = this.mBinding.durationLabel;
            String[] strArr = this.mDurationLabels;
            int i = this.mSelectedDurationIndex - 1;
            this.mSelectedDurationIndex = i;
            textView.setText(strArr[i]);
            updateDurationButtonStates();
        }
    }

    @OnClick({2131755255})
    void onIncreaseVolumeClicked() {
        if (this.mSelectedVolumeIndex < this.mVolumeLabels.length - 1) {
            TextView textView = this.mBinding.volumeLabel;
            String[] strArr = this.mVolumeLabels;
            int i = this.mSelectedVolumeIndex + 1;
            this.mSelectedVolumeIndex = i;
            textView.setText(strArr[i]);
            updateVolumeButtonStates();
        }
    }

    @OnClick({2131755253})
    void onReduceVolumeClicked() {
        if (this.mSelectedVolumeIndex > 0) {
            TextView textView = this.mBinding.volumeLabel;
            String[] strArr = this.mVolumeLabels;
            int i = this.mSelectedVolumeIndex - 1;
            this.mSelectedVolumeIndex = i;
            textView.setText(strArr[i]);
            updateVolumeButtonStates();
        }
    }

    private void updateButtonStates() {
        updateDurationButtonStates();
        updateVolumeButtonStates();
    }

    private void updateDurationButtonStates() {
        if (this.mSelectedDurationIndex >= this.mDurationLabels.length - 1) {
            this.mBinding.increaseDurationButton.setEnabled(false);
        } else {
            this.mBinding.increaseDurationButton.setEnabled(true);
        }
        if (this.mSelectedDurationIndex <= 0) {
            this.mBinding.reduceDurationButton.setEnabled(false);
        } else {
            this.mBinding.reduceDurationButton.setEnabled(true);
        }
    }

    private void updateVolumeButtonStates() {
        if (this.mSelectedVolumeIndex >= this.mVolumeLabels.length - 1) {
            this.mBinding.increaseVolumeButton.setEnabled(false);
        } else {
            this.mBinding.increaseVolumeButton.setEnabled(true);
        }
        if (this.mSelectedVolumeIndex <= 0) {
            this.mBinding.reduceVolumeButton.setEnabled(false);
        } else {
            this.mBinding.reduceVolumeButton.setEnabled(true);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnChildDashboardLullabyInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnChildDashboardLullabyInteractionListener");
        }
    }
}
