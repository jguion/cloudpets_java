package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.spiraltoys.cloudpets2.databinding.FragmentRequestBlePermissionBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.PlushToy.Character;

public class RequestBLEPermissionFragment extends Fragment {
    private static final String ARG_PLUSH_TOY_CHARACTER_TYPE = "plush_toy_character_type";
    private FragmentRequestBlePermissionBinding mBinding;
    private Character mCharacterType;
    private OnRequestBLEPermissionFragmentInteractionListener mListener;

    public interface OnRequestBLEPermissionFragmentInteractionListener {
        void onRequestBlePermissionClicked();
    }

    public static RequestBLEPermissionFragment newInstance(Character character) {
        RequestBLEPermissionFragment fragment = new RequestBLEPermissionFragment();
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
        this.mBinding = (FragmentRequestBlePermissionBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_request_ble_permission, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        this.mBinding.connectionIndicator.setImageResource(PlushToy.getAvatarResourceForCharacter(this.mCharacterType));
        return this.mBinding.getRoot();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnRequestBLEPermissionFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnRequestBLEPermissionFragmentInteractionListener");
        }
    }

    @OnClick({2131755174})
    void onRequestPermissionClicked() {
        if (this.mListener != null) {
            this.mListener.onRequestBlePermissionClicked();
        }
    }
}
