package com.spiraltoys.cloudpets2.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.spiraltoys.cloudpets2.databinding.FragmentPremiumDialogBinding;
import com.spiraltoys.cloudpets2.free.R;
import java.lang.reflect.Field;

public class PremiumDialogFragment extends DialogFragment {
    private FragmentPremiumDialogBinding mBinding;

    public static PremiumDialogFragment newInstance() {
        PremiumDialogFragment fragment = new PremiumDialogFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(2, R.style.AppTheme.AppCompatDialogStyle);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mBinding = (FragmentPremiumDialogBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_premium_dialog, container, false);
        this.mBinding.bgGetPremium.setOnClickListener(new 1(this));
        this.mBinding.getPremiumButton.setOnClickListener(new 2(this));
        return this.mBinding.getRoot();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AppCompatDialog dialog = new AppCompatDialog(getActivity(), getTheme());
        dialog.supportRequestWindowFeature(1);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return dialog;
    }

    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        try {
            Field mDialog = DialogFragment.class.getDeclaredField("mDialog");
            mDialog.setAccessible(true);
            mDialog.set(this, onCreateDialog(savedInstanceState));
            return (LayoutInflater) ((Dialog) mDialog.get(this)).getContext().getSystemService("layout_inflater");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
