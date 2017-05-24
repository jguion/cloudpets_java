package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.afollestad.materialdialogs.AlertDialogWrapper.Builder;
import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.events.ProfileSelectedForEditEvent;
import com.spiraltoys.cloudpets2.events.ProfileUpdatedEvent;
import com.spiraltoys.cloudpets2.fragments.EditAdultProfileFragment;
import com.spiraltoys.cloudpets2.fragments.EditAdultProfileFragment.OnAdultProfileChangedListener;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import icepick.State;
import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class EditParentProfileActivity extends BaseActivity implements OnBackStackChangedListener, OnAdultProfileChangedListener {
    public static final String EXTRA_PROFILE_ID = "extra_profile_id";
    @State
    String mDisplayName;
    @State
    String mEmailAddress;
    @State
    boolean mHasUnsavedChanges;
    @State
    boolean mIsDataPopulated;
    private Profile mProfile;
    @State
    Uri mProfilePhotoUri;
    @InjectView(2131755182)
    Button mSaveProfileButton;

    public static void startWithProfile(Context context, Profile profile) {
        EventBus.getDefault().postSticky(new ProfileSelectedForEditEvent(profile));
        Intent intent = new Intent(context, EditParentProfileActivity.class);
        intent.putExtra(EXTRA_PROFILE_ID, profile.getObjectId());
        context.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProfileSelectedForEditEvent profileSelectedForEditEvent = (ProfileSelectedForEditEvent) EventBus.getDefault().getStickyEvent(ProfileSelectedForEditEvent.class);
        if (profileSelectedForEditEvent != null) {
            this.mProfile = profileSelectedForEditEvent.getSelectedProfile();
        }
        if (this.mProfile == null) {
            try {
                this.mProfile = ModelHelper.getProfileFromLocalDatastore(getProfileId());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        setContentView((int) R.layout.activity_edit_parent_profile);
        ButterKnife.inject((Activity) this);
        getFragmentManager().addOnBackStackChangedListener(this);
        populateProfileData();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, EditAdultProfileFragment.newInstance(this.mDisplayName, this.mEmailAddress, this.mProfilePhotoUri)).commit();
        initToolbar();
        showToolbarTitle();
    }

    public void onBackPressed() {
        backRequested();
    }

    private void backRequested() {
        if (this.mHasUnsavedChanges) {
            new Builder(this).setTitle((int) R.string.title_discard_changes_confirmation).setMessage((int) R.string.message_discard_changes_confirmation).setPositiveButton((int) R.string.btn_discard_changes, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    EditParentProfileActivity.this.goBack();
                }
            }).setNegativeButton((int) R.string.btn_cancel, null).show();
        } else {
            goBack();
        }
    }

    private void goBack() {
        hideSoftKeyboard();
        super.onBackPressed();
    }

    public void onBackStackChanged() {
        updateButtonState();
    }

    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                backRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick({2131755182})
    void onSaveProfileClicked() {
        if (isProfileValid()) {
            updateAndSaveProfile();
        }
    }

    private void populateProfileData() {
        if (this.mIsDataPopulated) {
            updateButtonState();
            return;
        }
        Uri uri;
        this.mDisplayName = this.mProfile.getDisplayName();
        this.mEmailAddress = this.mProfile.getOwner().getEmail();
        if (this.mProfile.getPortrait() == null) {
            uri = null;
        } else {
            uri = Uri.parse(this.mProfile.getPortrait().getFile().getUrl());
        }
        this.mProfilePhotoUri = uri;
        this.mIsDataPopulated = true;
    }

    private void updateButtonState() {
        this.mSaveProfileButton.setEnabled(isProfileValid());
    }

    void updateAndSaveProfile() {
        if (isProfileValid()) {
            showProgress(R.string.title_saving_profile);
            this.mProfile.setDisplayName(this.mDisplayName);
            if (this.mProfilePhotoUri == null || (this.mProfile.getPortrait() != null && this.mProfilePhotoUri.equals(Uri.parse(this.mProfile.getPortrait().getFile().getUrl())))) {
                saveProfile(this.mProfile);
            } else {
                new Thread(new Runnable() {
                    public void run() {
                        Exception e;
                        ByteArrayOutputStream out;
                        byte[] imageData;
                        String fileName;
                        ParseFile parseFile;
                        ProfilePortrait portrait;
                        Runnable saveProfileRunnable = new 1(this);
                        Bitmap bitmap = null;
                        try {
                            bitmap = (Bitmap) Glide.with(EditParentProfileActivity.this).load(EditParentProfileActivity.this.mProfilePhotoUri).asBitmap().centerCrop().into(EditParentProfileActivity.this.getResources().getDimensionPixelSize(R.dimen.profile_photo_width_px), EditParentProfileActivity.this.getResources().getDimensionPixelSize(R.dimen.profile_photo_height_px)).get();
                        } catch (InterruptedException e2) {
                            e = e2;
                            e.printStackTrace();
                            if (bitmap == null) {
                                out = new ByteArrayOutputStream();
                                bitmap.compress(CompressFormat.JPEG, 85, out);
                                imageData = out.toByteArray();
                                fileName = UUID.randomUUID().toString() + ".jpeg";
                                parseFile = new ParseFile(fileName, imageData);
                                portrait = new ProfilePortrait();
                                portrait.setFile(parseFile);
                                portrait.setLocalFilename(fileName);
                                EditParentProfileActivity.this.mProfile.setPortrait(portrait);
                                new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                            }
                            new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                            return;
                        } catch (ExecutionException e3) {
                            e = e3;
                            e.printStackTrace();
                            if (bitmap == null) {
                                new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                                return;
                            }
                            out = new ByteArrayOutputStream();
                            bitmap.compress(CompressFormat.JPEG, 85, out);
                            imageData = out.toByteArray();
                            fileName = UUID.randomUUID().toString() + ".jpeg";
                            parseFile = new ParseFile(fileName, imageData);
                            portrait = new ProfilePortrait();
                            portrait.setFile(parseFile);
                            portrait.setLocalFilename(fileName);
                            EditParentProfileActivity.this.mProfile.setPortrait(portrait);
                            new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                        }
                        if (bitmap == null) {
                            new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                            return;
                        }
                        out = new ByteArrayOutputStream();
                        bitmap.compress(CompressFormat.JPEG, 85, out);
                        imageData = out.toByteArray();
                        fileName = UUID.randomUUID().toString() + ".jpeg";
                        parseFile = new ParseFile(fileName, imageData);
                        portrait = new ProfilePortrait();
                        portrait.setFile(parseFile);
                        portrait.setLocalFilename(fileName);
                        EditParentProfileActivity.this.mProfile.setPortrait(portrait);
                        new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                    }
                }).start();
            }
        }
    }

    private boolean isProfileValid() {
        return isWithinLength(this.mDisplayName, 1, 32);
    }

    private boolean isWithinLength(String text, int minLength, int maxLength) {
        return text != null && text.length() >= minLength && text.length() <= maxLength;
    }

    private void hideSoftKeyboard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private void saveProfile(Profile profile) {
        ModelHelper.saveProfile(profile, new SaveCallback() {
            public void done(ParseException e) {
                EditParentProfileActivity.this.hideProgress();
                if (!EditParentProfileActivity.this.isFinishing() && !EditParentProfileActivity.this.isDestroyed()) {
                    if (e != null) {
                        e.printStackTrace();
                        Utils.showErrorDialog(EditParentProfileActivity.this, ErrorMessages.getStringResourceIdForGenericParseExceiption(EditParentProfileActivity.this));
                        return;
                    }
                    EventBus.getDefault().post(new ProfileUpdatedEvent());
                    EditParentProfileActivity.this.setResult(-1);
                    EditParentProfileActivity.this.finish();
                }
            }
        });
    }

    private String getProfileId() {
        return getIntent().getExtras().getString(EXTRA_PROFILE_ID);
    }

    public void onProfilePhotoUriChanged(Uri photoUri) {
        this.mProfilePhotoUri = photoUri;
        updateButtonState();
    }

    public void onDisplayNameChanged(CharSequence displayName) {
        this.mDisplayName = displayName.toString().trim();
        updateButtonState();
    }

    public void onAdultProfileChanged() {
        this.mHasUnsavedChanges = true;
    }

    public void onPasswordResetRequested() {
        showProgress(R.string.title_resetting_password);
        ParseUser.requestPasswordResetInBackground(this.mProfile.getOwner().getEmail(), new RequestPasswordResetCallback() {
            public void done(ParseException e) {
                EditParentProfileActivity.this.hideProgress();
                if (e != null) {
                    Utils.showErrorDialog(EditParentProfileActivity.this, String.format(EditParentProfileActivity.this.getString(ErrorMessages.getStringResourceIdForErrorResettingPassword(EditParentProfileActivity.this, e)), new Object[]{EditParentProfileActivity.this.mProfile.getOwner().getEmail()}));
                    return;
                }
                new Builder(EditParentProfileActivity.this).setTitle((int) R.string.title_password_reset_sent).setMessage((int) R.string.message_password_reset_sent_instructions).setPositiveButton((int) R.string.btn_okay, null).show();
            }
        });
    }
}
