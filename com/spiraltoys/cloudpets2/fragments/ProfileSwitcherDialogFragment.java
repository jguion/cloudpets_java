package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.BaseActivity;
import com.spiraltoys.cloudpets2.adapters.ProfileSwitcherAdapter;
import com.spiraltoys.cloudpets2.databinding.FragmentDialogLockScreenBinding;
import com.spiraltoys.cloudpets2.events.ProfilePickerItemClickedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.util.AccountHelper;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import com.spiraltoys.cloudpets2.util.TopSpaceItemDecoration;
import com.spiraltoys.cloudpets2.util.Utils;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;
import java.lang.reflect.Field;

public class ProfileSwitcherDialogFragment extends DialogFragment {
    private static final String ARG_IS_LOCKED = "arg_is_locked";
    private static final String ARG_PROFILE_ID = "arg_profile_id";
    private ProfileSwitcherAdapter mAdapter;
    private FragmentDialogLockScreenBinding mBinding;
    private boolean mIsPasswordProtectAdultDashboardEnabled;
    private boolean mIsSourceLocked;
    private OnProfilePickerInteractionListener mListener;
    private Profile mSelectedProfile;
    private Profile mSourceProfile;

    public interface OnProfilePickerInteractionListener {
        void onLogOutSelected();

        void onProfileSelected(Profile profile);
    }

    public static ProfileSwitcherDialogFragment newInstance(Profile sourceProfile, boolean isLocked) {
        Bundle args = new Bundle();
        args.putString(ARG_PROFILE_ID, sourceProfile.getObjectId());
        args.putBoolean(ARG_IS_LOCKED, isLocked);
        ProfileSwitcherDialogFragment fragment = new ProfileSwitcherDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(2, R.style.AppTheme.AppCompatDialogStyleMinWidth);
        this.mIsPasswordProtectAdultDashboardEnabled = SettingsManager.isUseAdminPasswordEnabled(getActivity());
        if (getArguments() != null) {
            try {
                this.mIsSourceLocked = getArguments().getBoolean(ARG_IS_LOCKED);
                this.mSourceProfile = ModelHelper.getProfileFromLocalDatastore(getArguments().getString(ARG_PROFILE_ID));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        this.mBinding = (FragmentDialogLockScreenBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_lock_screen, container, false);
        ButterKnife.inject((Object) this, this.mBinding.getRoot());
        setSelectedProfile(this.mSourceProfile);
        if (this.mIsSourceLocked && this.mIsPasswordProtectAdultDashboardEnabled) {
            z = false;
        } else {
            z = true;
        }
        setCancelable(z);
        setPasswordFieldFontSize();
        this.mAdapter = new ProfileSwitcherAdapter(ModelHelper.getProfilesLocalDatastoreQuery(), this.mSourceProfile, getActivity());
        RecyclerView recyclerView = this.mBinding.profileRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new TopSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.abc_dialog_list_padding_vertical_material)));
        recyclerView.setAdapter(this.mAdapter);
        return this.mBinding.getRoot();
    }

    public void onEvent(ProfilePickerItemClickedEvent event) {
        setSelectedProfile(event.getClickedProfile());
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDestroy() {
        if (this.mAdapter != null) {
            this.mAdapter.release();
        }
        super.onDestroy();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnProfilePickerInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnProfilePickerInteractionListener");
        }
    }

    @OnClick({2131755211})
    void onContinueToProfileClicked() {
        if (isPasswordRequired()) {
            if (getActivity() instanceof BaseActivity) {
                ((BaseActivity) getActivity()).showProgress(R.string.title_checking_password);
            }
            AccountHelper.checkPassword(getActivity(), this.mBinding.password.getText().toString(), new 1(this));
            return;
        }
        if (this.mListener != null) {
            this.mListener.onProfileSelected(this.mSelectedProfile);
        }
        dismiss();
    }

    @OnClick({2131755280})
    void onLogOutClicked() {
        if (this.mListener != null) {
            this.mListener.onLogOutSelected();
        }
    }

    private void showIncorrectPasswordError() {
        Utils.showErrorDialog(getActivity(), (int) R.string.error_wrong_password);
    }

    private void setSelectedProfile(Profile profile) {
        String str;
        this.mSelectedProfile = profile;
        String displayName = profile.getDisplayName();
        String placeholderText = displayName.isEmpty() ? " " : Character.toString(displayName.charAt(0)).toUpperCase();
        ProfilePortrait profilePortrait = profile.getPortrait();
        BitmapPool pool = Glide.get(getActivity()).getBitmapPool();
        RequestManager with = Glide.with(getActivity());
        if (profilePortrait == null) {
            str = null;
        } else {
            str = profilePortrait.getFile().getUrl();
        }
        with.load(str).bitmapTransform(new DoubleBorderCropCircleTransformation(getActivity(), pool, getResources().getColor(R.color.accent), -1)).placeholder(ProfilePlaceholderDrawable.getLargeInstanceAccentBorder(getActivity(), placeholderText)).crossFade().into(this.mBinding.profilePhoto);
        TransitionManager.beginDelayedTransition((ViewGroup) this.mBinding.getRoot());
        this.mBinding.displayName.setText(profile.getDisplayName());
        this.mBinding.passwordContainer.setVisibility(isPasswordRequired() ? 0 : 8);
    }

    private void setPasswordFieldFontSize() {
        int maxPasswordFontSize = getResources().getDimensionPixelSize(R.dimen.lock_screen_password_max_text_size);
        if (this.mBinding.password.getTextSize() > ((float) maxPasswordFontSize)) {
            this.mBinding.password.setTextSize(0, (float) maxPasswordFontSize);
        }
    }

    private boolean isPasswordRequired() {
        return this.mSelectedProfile.getPrivateProfile().isAdult() && this.mIsPasswordProtectAdultDashboardEnabled && (this.mIsSourceLocked || this.mSelectedProfile != this.mSourceProfile);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AppCompatDialog dialog = new AppCompatDialog(getActivity(), getTheme());
        dialog.supportRequestWindowFeature(1);
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
