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
import com.spiraltoys.cloudpets2.databinding.FragmentSelectBluetoothToyErrorBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.PlushToy.Character;

public class SelectBluetoothToyErrorFragment extends Fragment {
    public static final String ARG_CUSTOM_MESSAGE = "arg_custom_message";
    private static final String ARG_PLUSH_TOY_CHARACTER_TYPE = "plush_toy_character_type";
    private FragmentSelectBluetoothToyErrorBinding mBinding;
    private Character mCharacterType;
    private String mCustomMessage;
    private OnBluetoothToyErrorFragmentInteractionListener mListener;

    public interface OnBluetoothToyErrorFragmentInteractionListener {
        void onCancelBluetoothScanClicked();

        void onRetryBluetoothScanClicked();
    }

    public static SelectBluetoothToyErrorFragment newInstance(String customMessage, Character character) {
        SelectBluetoothToyErrorFragment fragment = new SelectBluetoothToyErrorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CUSTOM_MESSAGE, customMessage);
        args.putString(ARG_PLUSH_TOY_CHARACTER_TYPE, character.toString());
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mCustomMessage = getArguments().getString(ARG_CUSTOM_MESSAGE);
            this.mCharacterType = Character.getCharacter(getArguments().getString(ARG_PLUSH_TOY_CHARACTER_TYPE));
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentSelectBluetoothToyErrorBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_select_bluetooth_toy_error, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        if (this.mCustomMessage != null) {
            this.mBinding.errorMessage.setText(this.mCustomMessage);
        }
        this.mBinding.connectionIndicator.setImageResource(PlushToy.getAvatarResourceForCharacter(this.mCharacterType));
        return this.mBinding.getRoot();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnBluetoothToyErrorFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnBluetoothToyErrorFragmentInteractionListener");
        }
    }

    @OnClick({2131755309})
    void onRetryClicked() {
        if (this.mListener != null) {
            this.mListener.onRetryBluetoothScanClicked();
        }
    }

    @OnClick({2131755297})
    void onCancelClicked() {
        if (this.mListener != null) {
            this.mListener.onCancelBluetoothScanClicked();
        }
    }
}
