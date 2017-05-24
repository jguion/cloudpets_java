package com.spiraltoys.cloudpets2.fragments;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import com.spiraltoys.cloudpets2.databinding.FragmentRequestBlePermissionWithOnboardingToolbarBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy.Character;
import com.spiraltoys.cloudpets2.util.Utils;

public class RequestBLEPermissionFragmentWithOnboardingToolbar extends Fragment {
    private static final String ARG_PLUSH_TOY_CHARACTER_TYPE = "plush_toy_character_type";
    private FragmentRequestBlePermissionWithOnboardingToolbarBinding mBinding;
    private Character mCharacterType;

    public static RequestBLEPermissionFragmentWithOnboardingToolbar newInstance(Character character) {
        RequestBLEPermissionFragmentWithOnboardingToolbar fragment = new RequestBLEPermissionFragmentWithOnboardingToolbar();
        Bundle args = new Bundle();
        args.putString(ARG_PLUSH_TOY_CHARACTER_TYPE, character.toString());
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mCharacterType = Character.getCharacter(getArguments().getString(ARG_PLUSH_TOY_CHARACTER_TYPE));
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentRequestBlePermissionWithOnboardingToolbarBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_request_ble_permission_with_onboarding_toolbar, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar((Toolbar) this.mBinding.getRoot().findViewById(R.id.toolbar));
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.mBinding.getRoot().findViewById(R.id.status_bar_spacer).setLayoutParams(new LayoutParams(-1, Utils.getStatusBarHeight(getActivity())));
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_container, RequestBLEPermissionFragment.newInstance(this.mCharacterType)).commit();
        return this.mBinding.getRoot();
    }
}
