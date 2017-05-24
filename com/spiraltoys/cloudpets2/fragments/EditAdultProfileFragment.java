package com.spiraltoys.cloudpets2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.views.CustomTextInputLayout;
import icepick.Icepick;
import icepick.State;

public class EditAdultProfileFragment extends Fragment {
    private static final String ARG_EMAIL_ADDRESS = "email_address";
    private static final String ARG_PROFILE_DISPLAY_NAME = "display_name";
    private static final String ARG_PROFILE_PHOTO_URI = "profile_photo_uri";
    private static final int PERMISSIONS_REQUEST_ACCESS_EXTERNAL_STORAGE = 99;
    private static final int PICKER_REQUEST_CODE = 42;
    @InjectView(2131755169)
    EditText mDisplayNameTextField;
    @InjectView(2131755170)
    EditText mEmailTextField;
    OnAdultProfileChangedListener mListener;
    @InjectView(2131755281)
    CardView mPasswordResetContainerKitKat;
    @InjectView(2131755283)
    Button mPasswordResetLollipop;
    @InjectView(2131755168)
    ImageView mProfilePhoto;
    @State
    Uri mProfilePhotoUri;

    public interface OnAdultProfileChangedListener {
        void onAdultProfileChanged();

        void onDisplayNameChanged(CharSequence charSequence);

        void onPasswordResetRequested();

        void onProfilePhotoUriChanged(Uri uri);
    }

    public static EditAdultProfileFragment newInstance(String displayName, String emailAddress, Uri profilePhotoUri) {
        EditAdultProfileFragment fragment = new EditAdultProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PROFILE_DISPLAY_NAME, displayName);
        args.putString(ARG_EMAIL_ADDRESS, emailAddress);
        args.putParcelable(ARG_PROFILE_PHOTO_URI, profilePhotoUri);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState((Object) this, savedInstanceState);
        if (getArguments() != null && savedInstanceState == null) {
            this.mProfilePhotoUri = (Uri) getArguments().getParcelable(ARG_PROFILE_PHOTO_URI);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int i;
        int i2 = 0;
        View view = inflater.inflate(R.layout.fragment_edit_adult_profile, container, false);
        ButterKnife.inject((Object) this, view);
        CardView cardView = this.mPasswordResetContainerKitKat;
        if (VERSION.SDK_INT < 21) {
            i = 0;
        } else {
            i = 8;
        }
        cardView.setVisibility(i);
        Button button = this.mPasswordResetLollipop;
        if (VERSION.SDK_INT < 21) {
            i2 = 8;
        }
        button.setVisibility(i2);
        if (getArguments() != null && savedInstanceState == null) {
            ((CustomTextInputLayout) this.mDisplayNameTextField.getParent()).setTextWithoutAnimation(getArguments().getString(ARG_PROFILE_DISPLAY_NAME));
            ((CustomTextInputLayout) this.mEmailTextField.getParent()).setTextWithoutAnimation(getArguments().getString(ARG_EMAIL_ADDRESS));
        }
        this.mDisplayNameTextField.addTextChangedListener(new 1(this));
        setupProfilePhoto();
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState((Object) this, outState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnAdultProfileChangedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnAdultProfileChangedListener");
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
                this.mListener.onAdultProfileChanged();
            }
            getArguments().putParcelable(ARG_PROFILE_PHOTO_URI, this.mProfilePhotoUri);
            setupProfilePhoto();
        }
    }

    private void setupProfilePhoto() {
        BitmapPool pool = Glide.get(getActivity()).getBitmapPool();
        Glide.with(getActivity()).load(this.mProfilePhotoUri).bitmapTransform(new DoubleBorderCropCircleTransformation(getActivity(), pool, getResources().getColor(R.color.button_secondary_dark), -1)).placeholder((int) R.drawable.no_picture).crossFade().into(this.mProfilePhoto);
    }

    @OnTextChanged({2131755169})
    void onDisplayNameChanged(CharSequence newDisplayName) {
        if (this.mListener != null) {
            this.mListener.onDisplayNameChanged(newDisplayName);
        }
    }

    @OnClick({2131755168})
    void onSetPhotoClicked() {
        if (VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(getActivity(), "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            startPhotoPickerForResult();
            return;
        }
        requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 99);
    }

    private void startPhotoPickerForResult() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(intent, 42);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 99:
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

    @OnClick({2131755282})
    void onPasswordRestClickedKitKat() {
        if (this.mListener != null) {
            this.mListener.onPasswordResetRequested();
        }
    }

    @OnClick({2131755283})
    void onPasswordRestClickedLollipop() {
        if (this.mListener != null) {
            this.mListener.onPasswordResetRequested();
        }
    }
}
