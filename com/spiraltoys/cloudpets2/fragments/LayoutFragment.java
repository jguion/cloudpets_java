package com.spiraltoys.cloudpets2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutFragment extends Fragment {
    public static final String ARG_LAYOUT_RESOURCE_ID = "arg_layout_resource_id";

    public static LayoutFragment newInstance(int layoutResourceId) {
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RESOURCE_ID, layoutResourceId);
        LayoutFragment fragment = new LayoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getArguments().getInt(ARG_LAYOUT_RESOURCE_ID), container, false);
    }
}
