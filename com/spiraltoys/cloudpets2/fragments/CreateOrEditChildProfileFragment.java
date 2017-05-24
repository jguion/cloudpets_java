package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import butterknife.Optional;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.spiraltoys.cloudpets2.adapters.DayOfMonthAdapter;
import com.spiraltoys.cloudpets2.adapters.MonthAdapter;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.PlushToy.Character;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.views.CustomTextInputLayout;
import icepick.Icepick;
import icepick.State;
import java.util.regex.Pattern;

public class CreateOrEditChildProfileFragment extends Fragment {
    private static final int ALLOWED_BEAR_OVERLAP_WITH_TITLE_TEXT_DP = 40;
    private static final String ARG_BIRTH_DAY_OF_MONTH = "birth_day_of_month";
    private static final String ARG_BIRTH_MONTH = "birth_month";
    private static final String ARG_DISPLAY_NAME = "display_name";
    private static final String ARG_IS_EDIT_MODE = "is_edit_mode";
    private static final String ARG_PET_CHARACTER = "pet_character";
    private static final String ARG_PET_NAME = "pet_name";
    private static final String ARG_PROFILE_PHOTO_URI = "profile_photo_uri";
    private static final String ARG_USERNAME = "username";
    public static final Pattern INVALID_USERNAME_CHARACTER_SEQUENCES_PATTERN = Pattern.compile("[^a-zA-Z0-9_.]*");
    private static final int PERMISSIONS_REQUEST_ACCESS_EXTERNAL_STORAGE = 42;
    private static final int PICKER_REQUEST_CODE = 42;
    @InjectView(2131755277)
    Spinner mBirthDayOfMonthSpinner;
    @InjectView(2131755276)
    Spinner mBirthMonthSpinner;
    @InjectView(2131755285)
    @Optional
    ImageView mConfiguredPetAvatar;
    @InjectView(2131755286)
    @Optional
    TextView mConfiguredPetName;
    private DayOfMonthAdapter mDayOfMonthAdapter;
    @InjectView(2131755169)
    EditText mDisplayNameTextField;
    @State
    boolean mIsEditMode;
    private OnChildProfileChangedListener mListener;
    private MonthAdapter mMonthAdapter;
    @InjectView(2131755272)
    @Optional
    ImageView mProfileBear;
    @InjectView(2131755168)
    ImageView mProfilePhoto;
    @State
    Uri mProfilePhotoUri;
    @InjectView(2131755203)
    @Optional
    TextView mTitleText;
    @InjectView(2131755274)
    EditText mUsernameTextField;

    public interface OnChildProfileChangedListener {
        void onBirthDayOfMonthSelected(int i);

        void onBirthMonthSelected(int i);

        void onChildNameChanged(CharSequence charSequence);

        void onDeleteProfileClicked();

        void onEditCloudPetClicked();

        void onProfilePhotoUriChanged(Uri uri);

        void onUsernameChanged(CharSequence charSequence);
    }

    public static CreateOrEditChildProfileFragment newInstance(String displayName, String username, int birthMonth, int birthDayOfMonth, Uri profilePhotoUri, boolean isEditMode, String petName, Character character) {
        CreateOrEditChildProfileFragment fragment = new CreateOrEditChildProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DISPLAY_NAME, displayName);
        args.putString("username", username);
        args.putInt(ARG_BIRTH_MONTH, birthMonth);
        args.putInt(ARG_BIRTH_DAY_OF_MONTH, birthDayOfMonth);
        args.putParcelable(ARG_PROFILE_PHOTO_URI, profilePhotoUri);
        args.putBoolean(ARG_IS_EDIT_MODE, isEditMode);
        args.putString(ARG_PET_NAME, petName);
        args.putSerializable(ARG_PET_CHARACTER, character);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState((Object) this, savedInstanceState);
        if (getArguments() != null && savedInstanceState == null) {
            this.mProfilePhotoUri = (Uri) getArguments().getParcelable(ARG_PROFILE_PHOTO_URI);
            this.mIsEditMode = getArguments().getBoolean(ARG_IS_EDIT_MODE, false);
        }
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (this.mIsEditMode) {
            inflater.inflate(R.menu.edit_child_profile, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                if (this.mListener != null) {
                    this.mListener.onDeleteProfileClicked();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        View view = inflater.inflate(this.mIsEditMode ? R.layout.fragment_edit_child_profile : R.layout.fragment_create_child_profile, container, false);
        ButterKnife.inject((Object) this, view);
        setupUsernameInputFilter();
        Context activity = getActivity();
        if (this.mIsEditMode) {
            z = false;
        } else {
            z = true;
        }
        this.mMonthAdapter = new MonthAdapter(activity, true, z);
        this.mBirthMonthSpinner.setAdapter(this.mMonthAdapter);
        activity = getActivity();
        if (this.mIsEditMode) {
            z = false;
        } else {
            z = true;
        }
        this.mDayOfMonthAdapter = new DayOfMonthAdapter(activity, true, z);
        this.mBirthDayOfMonthSpinner.setAdapter(this.mDayOfMonthAdapter);
        if (getArguments() != null && savedInstanceState == null) {
            ((CustomTextInputLayout) this.mDisplayNameTextField.getParent()).setTextWithoutAnimation(getArguments().getString(ARG_DISPLAY_NAME));
            ((CustomTextInputLayout) this.mUsernameTextField.getParent()).setTextWithoutAnimation(getArguments().getString("username"));
            this.mBirthMonthSpinner.setSelection(getArguments().getInt(ARG_BIRTH_MONTH));
            this.mBirthDayOfMonthSpinner.setSelection(getArguments().getInt(ARG_BIRTH_DAY_OF_MONTH));
        }
        if (this.mConfiguredPetName != null) {
            this.mConfiguredPetName.setText(getArguments().getString(ARG_PET_NAME));
        }
        if (this.mConfiguredPetAvatar != null) {
            this.mConfiguredPetAvatar.setImageResource(PlushToy.getAvatarResourceForCharacter((Character) getArguments().getSerializable(ARG_PET_CHARACTER)));
        }
        if (this.mIsEditMode) {
            this.mUsernameTextField.setEnabled(false);
            this.mConfiguredPetName.setOnTouchListener(new 1(this));
        }
        setupProfilePhoto();
        hideProfileBearIfOverlappingText();
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState((Object) this, outState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnChildProfileChangedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnChildProfileChangedListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 42 && resultCode == -1 && data != null) {
            this.mProfilePhotoUri = data.getData();
            if (this.mListener != null) {
                this.mListener.onProfilePhotoUriChanged(this.mProfilePhotoUri);
            }
            getArguments().putParcelable(ARG_PROFILE_PHOTO_URI, this.mProfilePhotoUri);
            setupProfilePhoto();
        }
    }

    public void setPlushToyCharacter(Character character) {
        getArguments().putSerializable(ARG_PET_CHARACTER, character);
        if (isAdded() && !isRemoving() && this.mConfiguredPetAvatar != null) {
            this.mConfiguredPetAvatar.setImageResource(PlushToy.getAvatarResourceForCharacter((Character) getArguments().getSerializable(ARG_PET_CHARACTER)));
        }
    }

    public void setPlushToyCharacterName(String name) {
        getArguments().putSerializable(ARG_PET_NAME, name);
        if (isAdded() && !isRemoving() && this.mConfiguredPetName != null) {
            this.mConfiguredPetName.setText(getArguments().getString(ARG_PET_NAME));
        }
    }

    @OnTextChanged({2131755169})
    void onChildNameChanged(CharSequence newNickname) {
        if (this.mListener != null) {
            this.mListener.onChildNameChanged(newNickname);
        }
    }

    @OnTextChanged({2131755274})
    void onUsernameChanged(CharSequence newUsername) {
        if (this.mListener != null) {
            this.mListener.onUsernameChanged(newUsername);
        }
    }

    @OnItemSelected({2131755276})
    void onMonthSelected(int position) {
        this.mDayOfMonthAdapter.setMonth(position);
        if (position == 0) {
            this.mBirthDayOfMonthSpinner.setSelection(0);
            this.mBirthDayOfMonthSpinner.setEnabled(false);
            this.mBirthDayOfMonthSpinner.clearFocus();
        } else {
            this.mBirthDayOfMonthSpinner.setEnabled(true);
        }
        if (this.mListener != null) {
            this.mListener.onBirthMonthSelected(position);
        }
    }

    @OnItemSelected({2131755277})
    void onDayOfMonthSelected(int position) {
        if (this.mListener != null) {
            this.mListener.onBirthDayOfMonthSelected(position);
        }
    }

    @OnClick({2131755275})
    void onUsernameHelpButtonClicked() {
        showHelpDialog();
    }

    @OnClick({2131755168})
    void onSetPhotoClicked() {
        if (VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(getActivity(), "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            startPhotoPickerForResult();
            return;
        }
        requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 42);
    }

    private void startPhotoPickerForResult() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(intent, 42);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 42:
                if (grantResults[0] == 0) {
                    startPhotoPickerForResult();
                    return;
                } else {
                    Snackbar.make(this.mProfilePhoto, (int) R.string.hint_photos_permission_denied, 0).show();
                    return;
                }
            default:
                return;
        }
    }

    @OnClick({2131755284})
    @Optional
    void onEditCloudPetClicked() {
        if (this.mListener != null) {
            this.mListener.onEditCloudPetClicked();
        }
    }

    private void setupProfilePhoto() {
        BitmapPool pool = Glide.get(getActivity()).getBitmapPool();
        Glide.with(getActivity()).load(this.mProfilePhotoUri).bitmapTransform(new DoubleBorderCropCircleTransformation(getActivity(), pool, ContextCompat.getColor(getActivity(), R.color.profile_photo_border_dark), -1)).placeholder((int) R.drawable.no_picture).crossFade().into(this.mProfilePhoto);
    }

    private void setupUsernameInputFilter() {
        InputFilter characterFilter = new 2(this);
        InputFilter lengthFilter = new LengthFilter(32);
        this.mUsernameTextField.setFilters(new InputFilter[]{characterFilter, lengthFilter});
    }

    private void showHelpDialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_generic_help);
        ((TextView) dialog.findViewById(R.id.help_title)).setText(getString(R.string.title_unique_username));
        ((TextView) dialog.findViewById(R.id.help_text)).setText(getString(R.string.message_unique_username));
        ((Button) dialog.findViewById(R.id.button_ok)).setOnClickListener(new 3(this, dialog));
        dialog.show();
    }

    private void hideProfileBearIfOverlappingText() {
        if (this.mProfileBear != null) {
            this.mProfileBear.getViewTreeObserver().addOnGlobalLayoutListener(new 4(this));
        }
    }
}
