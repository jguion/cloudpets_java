package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.parse.ParseException;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.databinding.ActivityCreateAccountBinding;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.util.AccountHelper;
import com.spiraltoys.cloudpets2.model.util.AccountHelper.SignupCredentials;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;
import icepick.State;

public class CreateAccountActivity extends CloudActionBarActivity {
    private static final int PERMISSIONS_REQUEST_ACCESS_EXTERNAL_STORAGE = 99;
    private static final int PICKER_REQUEST_CODE = 42;
    private ActivityCreateAccountBinding mBinding;
    @State
    Uri mProfilePhotoUri;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBinding = (ActivityCreateAccountBinding) DataBindingUtil.setContentView(this, R.layout.activity_create_account);
        ButterKnife.inject((Activity) this);
        Utils.highlightCloudPetsText(this.mBinding.subHeadingText);
        initToolbar();
        setupProfilePhoto();
    }

    @OnClick({2131755173})
    void onLogInClicked() {
        SignupCredentials credentials = new SignupCredentials();
        credentials.setDisplayName(this.mBinding.displayName.getText().toString());
        credentials.setEmail(this.mBinding.email.getText().toString());
        credentials.setPassword(this.mBinding.password.getText().toString());
        credentials.setPasswordRepeat(this.mBinding.passwordRepeat.getText().toString());
        credentials.setProfilePhotoUri(this.mProfilePhotoUri);
        if (!showValidationErrors()) {
            showProgress(R.string.title_creating_account);
            AccountHelper.logOut(this);
            AccountHelper.signUp(this, credentials, new SaveCallback() {
                public void done(ParseException e) {
                    CreateAccountActivity.this.hideProgress();
                    if (e != null) {
                        Utils.showErrorDialog(CreateAccountActivity.this, ErrorMessages.getStringResourceIdForSignupException(CreateAccountActivity.this, e));
                    } else {
                        CreateAccountActivity.this.showVerifyEmailDialog();
                    }
                }
            });
        }
    }

    @OnClick({2131755168})
    void onSetPhotoClicked() {
        if (VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
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
                    Snackbar.make(this.mBinding.getRoot(), (int) R.string.hint_photos_permission_denied, 0).show();
                    return;
                }
            default:
                return;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 42 && resultCode == -1 && data != null) {
            this.mProfilePhotoUri = data.getData();
            setupProfilePhoto();
        }
    }

    private boolean showValidationErrors() {
        if (this.mBinding.displayName.getText().toString().trim().isEmpty()) {
            Utils.showHintDialog((Context) this, (int) R.string.error_please_enter_display_name);
            return true;
        } else if (!isWithinRange(this.mBinding.displayName.getText().toString().trim(), 1, 32)) {
            Utils.showHintDialog((Context) this, (int) R.string.error_please_enter_valid_display_name);
            return true;
        } else if (this.mBinding.email.getText().toString().isEmpty()) {
            Utils.showHintDialog((Context) this, (int) R.string.error_please_enter_email);
            return true;
        } else if (!Utils.isValidEmailAddress(this.mBinding.email.getText().toString())) {
            Utils.showHintDialog((Context) this, (int) R.string.error_please_enter_valid_email);
            return true;
        } else if (this.mBinding.password.getText().toString().isEmpty()) {
            Utils.showHintDialog((Context) this, (int) R.string.error_please_enter_password);
            return true;
        } else if (!isWithinRange(this.mBinding.password.getText().toString(), 1, 32)) {
            Utils.showHintDialog((Context) this, (int) R.string.error_please_enter_valid_password);
            return true;
        } else if (this.mBinding.password.getText().toString().equals(this.mBinding.passwordRepeat.getText().toString())) {
            return false;
        } else {
            Utils.showHintDialog((Context) this, (int) R.string.error_passwords_do_not_match);
            return true;
        }
    }

    private void setupProfilePhoto() {
        BitmapPool pool = Glide.get(this).getBitmapPool();
        Glide.with((FragmentActivity) this).load(this.mProfilePhotoUri).bitmapTransform(new DoubleBorderCropCircleTransformation(this, pool, getResources().getColor(R.color.profile_photo_border_dark), -1)).placeholder((int) R.drawable.no_picture).crossFade().into(this.mBinding.profilePhoto);
    }

    private boolean isWithinRange(String text, int minLength, int maxLength) {
        return text != null && text.length() >= minLength && text.length() <= maxLength;
    }
}
