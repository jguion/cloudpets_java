package com.spiraltoys.cloudpets2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
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
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.events.ProfileAddedEvent;
import com.spiraltoys.cloudpets2.events.ProfileDeletedEvent;
import com.spiraltoys.cloudpets2.events.ProfileUpdatedEvent;
import com.spiraltoys.cloudpets2.fragments.CreateOrEditChildProfileFragment;
import com.spiraltoys.cloudpets2.fragments.CreateOrEditChildProfileFragment.OnChildProfileChangedListener;
import com.spiraltoys.cloudpets2.fragments.RequestBLEPermissionFragment;
import com.spiraltoys.cloudpets2.fragments.RequestBLEPermissionFragment.OnRequestBLEPermissionFragmentInteractionListener;
import com.spiraltoys.cloudpets2.fragments.SelectBluetoothToyCompleteConnectionFragment;
import com.spiraltoys.cloudpets2.fragments.SelectBluetoothToyCompleteConnectionFragment.OnConnectionCompletedListener;
import com.spiraltoys.cloudpets2.fragments.SelectBluetoothToyErrorFragment;
import com.spiraltoys.cloudpets2.fragments.SelectBluetoothToyErrorFragment.OnBluetoothToyErrorFragmentInteractionListener;
import com.spiraltoys.cloudpets2.fragments.SelectBluetoothToyFragment;
import com.spiraltoys.cloudpets2.fragments.SelectBluetoothToyFragment.OnToySelectedListener;
import com.spiraltoys.cloudpets2.fragments.SelectPetAvatarFragment;
import com.spiraltoys.cloudpets2.fragments.SelectPetAvatarFragment.OnPetAvatarChangeListener;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PlushToy;
import com.spiraltoys.cloudpets2.model.PlushToy.Character;
import com.spiraltoys.cloudpets2.model.PrivateProfile;
import com.spiraltoys.cloudpets2.model.PrivateProfile.ProfileType;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.toy.ToyManager;
import com.spiraltoys.cloudpets2.toy.event.ToyEventRequiresUpdate;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import icepick.State;
import java.io.ByteArrayOutputStream;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class CreateOrEditChildProfileActivity extends BaseActivity implements OnBackStackChangedListener, OnChildProfileChangedListener, OnPetAvatarChangeListener, OnToySelectedListener, OnConnectionCompletedListener, OnBluetoothToyErrorFragmentInteractionListener, OnRequestBLEPermissionFragmentInteractionListener {
    private static final int PERMISSIONS_REQUEST_LOCATION_ACCESS = 42;
    public static final String PROFILE_OBJECT_ID = "profile_object_id";
    private boolean isNewChild;
    @State
    int mBirthDayOfMonth;
    @State
    int mBirthMonth;
    @State
    String mDisplayName;
    @State
    boolean mHasUnsavedChanges;
    @State
    boolean mIsDataPopulated;
    @InjectView(2131755174)
    Button mNextButton;
    @State
    String mPetNickname;
    private Profile mProfile;
    @State
    Uri mProfilePhotoUri;
    private FragmentTransaction mQueuedFragmentTransaction;
    @InjectView(2131755176)
    Button mSaveButton;
    @InjectView(2131755175)
    View mSaveDeleteButtons;
    @State
    Character mSelectedCharacter;
    @State
    String mToyIdentifier;
    private ToyEventRequiresUpdate mUpdateRequiredEvent;
    @State
    String mUsername;

    enum Step {
        SELECT_CHARACTER,
        CONFIGURE_TOY,
        CONFIGURE_TOY_ERROR,
        REQUEST_BLE_PERMISSION,
        CONFIGURE_TOY_COMPLETE_CONNECTION,
        CONFIGURE_PROFILE
    }

    public static void startWithProfile(Activity context, Profile profile) {
        Intent intent = new Intent(context, CreateOrEditChildProfileActivity.class);
        intent.putExtra(PROFILE_OBJECT_ID, profile.getObjectId());
        context.startActivity(intent);
    }

    public static void startWithProfileForResult(Activity context, Profile profile, int requestCode) {
        Intent intent = new Intent(context, CreateOrEditChildProfileActivity.class);
        intent.putExtra(PROFILE_OBJECT_ID, profile.getObjectId());
        context.startActivityForResult(intent, requestCode);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (getIntent().getExtras().getString(PROFILE_OBJECT_ID) != null) {
            try {
                this.mProfile = ModelHelper.getProfileFromLocalDatastore(getIntent().getExtras().getString(PROFILE_OBJECT_ID));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        setContentView(isEditMode() ? R.layout.activity_edit_child_profile : R.layout.activity_create_child_profile);
        ButterKnife.inject((Activity) this);
        if (savedInstanceState == null) {
            this.mBirthMonth = 0;
            this.mBirthDayOfMonth = 0;
        } else {
            setCharacter(this.mSelectedCharacter);
            updateCloudPetDetails();
        }
        getFragmentManager().addOnBackStackChangedListener(this);
        initProfileIfEmpty();
        populateProfileData();
        initToolbar();
        if (isEditMode() && getSupportActionBar() != null) {
            getSupportActionBar().setTitle((int) R.string.title_activity_edit_child_profile);
            showToolbarTitle();
        }
        if (!isEditMode()) {
            this.isNewChild = true;
        }
    }

    protected void onPause() {
        super.onPause();
        ToyManager.disconnectFromToyEventually();
    }

    protected void onResume() {
        super.onResume();
        if (this.mQueuedFragmentTransaction != null) {
            this.mQueuedFragmentTransaction.commit();
            this.mQueuedFragmentTransaction = null;
        }
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

    private Step getCurrentStep() {
        return getStepForFragment(getFragmentManager().findFragmentById(R.id.fragment_container));
    }

    private Step getStepForFragment(Fragment fragment) {
        if (fragment == null) {
            throw new IllegalArgumentException();
        } else if (fragment instanceof SelectPetAvatarFragment) {
            return Step.SELECT_CHARACTER;
        } else {
            if (fragment instanceof SelectBluetoothToyErrorFragment) {
                return Step.CONFIGURE_TOY_ERROR;
            }
            if (fragment instanceof RequestBLEPermissionFragment) {
                return Step.REQUEST_BLE_PERMISSION;
            }
            if (fragment instanceof SelectBluetoothToyCompleteConnectionFragment) {
                return Step.CONFIGURE_TOY_COMPLETE_CONNECTION;
            }
            if (fragment instanceof SelectBluetoothToyFragment) {
                return Step.CONFIGURE_TOY;
            }
            if (fragment instanceof CreateOrEditChildProfileFragment) {
                return Step.CONFIGURE_PROFILE;
            }
            throw new IllegalArgumentException();
        }
    }

    private void initProfileIfEmpty() {
        if (this.mProfile == null) {
            this.mProfile = new Profile();
        }
        if (this.mProfile.getPrivateProfile() == null) {
            this.mProfile.setPrivateProfile(new PrivateProfile());
            this.mProfile.getPrivateProfile().setProfileType(ProfileType.CHILD);
        }
        if (this.mProfile.getPrivateProfile().getProfileType() == ProfileType.ADULT) {
            throw new IllegalArgumentException("Invalid ProfileType. ProfileType must be of type CHILD");
        } else if (this.mProfile.getPlushToy() == null) {
            PlushToy plushToy = new PlushToy();
            plushToy.setCharacter(Character.STARBURST_THE_UNICORN);
            this.mProfile.setPlushToy(plushToy);
        }
    }

    private void populateProfileData() {
        if (this.mIsDataPopulated) {
            updateButtonStates();
            return;
        }
        Uri uri;
        this.mBirthMonth = Math.max(0, this.mProfile.getPrivateProfile().getBirthMonth());
        this.mBirthDayOfMonth = Math.max(0, this.mProfile.getPrivateProfile().getBirthDayOfMonth());
        if (this.mProfile.getPortrait() == null) {
            uri = null;
        } else {
            uri = Uri.parse(this.mProfile.getPortrait().getFile().getUrl());
        }
        this.mProfilePhotoUri = uri;
        this.mDisplayName = this.mProfile.getDisplayName();
        this.mUsername = this.mProfile.getUsername();
        this.mPetNickname = this.mProfile.getPlushToy().getNickName();
        this.mToyIdentifier = this.mProfile.getPlushToy().getConfigID();
        this.mSelectedCharacter = this.mProfile.getPlushToy().getCharacter();
        this.mIsDataPopulated = true;
        setCharacter(this.mSelectedCharacter);
        updateCloudPetDetails();
        if (this.mToyIdentifier == null && getFragmentManager().getBackStackEntryCount() == 0) {
            onCreateProfile();
        } else {
            onEditProfile();
        }
    }

    private void backRequested() {
        if (isEditMode() && this.mHasUnsavedChanges) {
            switch (getCurrentStep()) {
                case CONFIGURE_PROFILE:
                    new Builder(this).setTitle((int) R.string.title_discard_changes_confirmation).setMessage((int) R.string.message_discard_changes_confirmation).setPositiveButton((int) R.string.btn_discard_changes, new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            CreateOrEditChildProfileActivity.this.goBack();
                        }
                    }).setNegativeButton((int) R.string.btn_cancel, null).show();
                    return;
                case SELECT_CHARACTER:
                    if (this.mPetNickname == null || this.mPetNickname.isEmpty()) {
                        onPetNicknameChanged(this.mProfile.getPlushToy().getNickName());
                        goBack();
                        return;
                    }
            }
            goBack();
            return;
        }
        goBack();
    }

    private void goBack() {
        hideSoftKeyboard();
        switch (getCurrentStep()) {
            case CONFIGURE_PROFILE:
            case CONFIGURE_TOY_COMPLETE_CONNECTION:
            case CONFIGURE_TOY_ERROR:
            case REQUEST_BLE_PERMISSION:
                if (!getFragmentManager().popBackStackImmediate(Step.CONFIGURE_TOY.toString(), 0)) {
                    super.onBackPressed();
                    return;
                }
                return;
            default:
                super.onBackPressed();
                return;
        }
    }

    public void onBackPressed() {
        backRequested();
    }

    public void onEvent(ToyEventRequiresUpdate eventRequiresUpdate) {
        this.mUpdateRequiredEvent = eventRequiresUpdate;
    }

    @OnClick({2131755174})
    void onNextClicked() {
        hideSoftKeyboard();
        switch (getCurrentStep()) {
            case SELECT_CHARACTER:
                onContinueToSelectBluetoothToyClicked();
                return;
            case CONFIGURE_TOY_COMPLETE_CONNECTION:
                onContinueToSetupChildProfileClicked();
                return;
            case CONFIGURE_TOY:
                onContinueToCompleteTheConnectionClicked();
                return;
            default:
                return;
        }
    }

    @OnClick({2131755176})
    void onSaveClicked() {
        if (isProfileValid() && isPlushToyValid()) {
            createAndSaveChildProfile();
        }
    }

    private void hideSoftKeyboard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public void onRetryBluetoothScanClicked() {
        getFragmentManager().popBackStack(Step.CONFIGURE_TOY.toString(), 0);
    }

    public void onCancelBluetoothScanClicked() {
        getFragmentManager().popBackStack(Step.SELECT_CHARACTER.toString(), 0);
    }

    public void onProfilePhotoUriChanged(Uri photoUri) {
        this.mProfilePhotoUri = photoUri;
    }

    public void onChildNameChanged(CharSequence displayName) {
        if (!displayName.toString().equals(this.mDisplayName)) {
            this.mHasUnsavedChanges = true;
        }
        this.mDisplayName = displayName.toString().trim();
        updateButtonStates();
    }

    public void onUsernameChanged(CharSequence username) {
        if (!username.toString().equals(this.mUsername)) {
            this.mHasUnsavedChanges = true;
        }
        this.mUsername = username.toString();
        updateButtonStates();
    }

    public void onBirthMonthSelected(int month) {
        if (month != this.mBirthMonth) {
            this.mHasUnsavedChanges = true;
        }
        this.mBirthMonth = month;
        updateButtonStates();
    }

    public void onBirthDayOfMonthSelected(int dayOfMonth) {
        if (dayOfMonth != this.mBirthDayOfMonth) {
            this.mHasUnsavedChanges = true;
        }
        this.mBirthDayOfMonth = dayOfMonth;
        updateButtonStates();
    }

    public void onPetCharacterChanged(Character newCharacter) {
        setCharacter(newCharacter);
        updateCloudPetDetails();
    }

    public void onPetNicknameChanged(CharSequence petNickname) {
        this.mPetNickname = petNickname.toString().trim();
        updateCloudPetDetails();
        updateButtonStates();
    }

    public void onPetChanged() {
        this.mHasUnsavedChanges = true;
    }

    public void onEditCloudPetClicked() {
        hideSoftKeyboard();
        SelectPetAvatarFragment newFragment = SelectPetAvatarFragment.newInstance(this.mSelectedCharacter, this.mPetNickname);
        Step newStep = getStepForFragment(newFragment);
        getFragmentManager().beginTransaction().setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(newStep.toString()).replace(R.id.fragment_container, newFragment, newStep.toString()).commit();
    }

    public void onDeleteProfileClicked() {
        new Builder(this).setTitle((int) R.string.title_delete_child_profile_confirm).setMessage(getString(R.string.message_delete_child_profile_confirm, new Object[]{this.mProfile.getDisplayName()})).setPositiveButton((int) R.string.btn_delete, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                CreateOrEditChildProfileActivity.this.mProfile.deleteEventually();
                EventBus.getDefault().post(new ProfileDeletedEvent());
                CreateOrEditChildProfileActivity.this.finish();
            }
        }).setNegativeButton((int) R.string.btn_cancel, null).show();
    }

    public void onToySelected(String toyIdentifier) {
        this.mToyIdentifier = toyIdentifier;
        updateCloudPetDetails();
        if (isPlushToyValid()) {
            onNextClicked();
        }
    }

    public void onToyScanTimeout(int numberOfToysFound) {
        showToyError(numberOfToysFound > 0 ? getString(R.string.message_scanning_error_out_of_toys) : getString(R.string.message_scanning_details));
    }

    public void onToyConnectionTimeout() {
        showToyError(getString(R.string.message_connecting_error));
    }

    public void onToySelectionConfirmed() {
        onNextClicked();
    }

    private void showToyError(String errorMessage) {
        FragmentTransaction displayErrorTransaction = getFragmentManager().beginTransaction().setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).replace(R.id.fragment_container, SelectBluetoothToyErrorFragment.newInstance(errorMessage, this.mSelectedCharacter));
        if (hasWindowFocus()) {
            displayErrorTransaction.commit();
        } else {
            this.mQueuedFragmentTransaction = displayErrorTransaction;
        }
    }

    public void onBackStackChanged() {
        updateButtonStates();
    }

    private void onContinueToCompleteTheConnectionClicked() {
        if (this.mUpdateRequiredEvent == null || !this.mUpdateRequiredEvent.getToyIdentifier().equals(this.mToyIdentifier)) {
            SelectBluetoothToyCompleteConnectionFragment newFragment = SelectBluetoothToyCompleteConnectionFragment.newInstance(this.mSelectedCharacter, this.mToyIdentifier);
            Step newStep = getStepForFragment(newFragment);
            getFragmentManager().beginTransaction().setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(newStep.toString()).replace(R.id.fragment_container, newFragment, newStep.toString()).commit();
            return;
        }
        super.onEvent(this.mUpdateRequiredEvent);
        this.mUpdateRequiredEvent = null;
    }

    private void onContinueToSetupChildProfileClicked() {
        Step newStep = Step.CONFIGURE_PROFILE;
        if (!getFragmentManager().popBackStackImmediate(newStep.toString(), 0)) {
            getFragmentManager().beginTransaction().setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(newStep.toString()).replace(R.id.fragment_container, CreateOrEditChildProfileFragment.newInstance(this.mDisplayName, this.mUsername, this.mBirthMonth, this.mBirthDayOfMonth, this.mProfilePhotoUri, false, this.mPetNickname, this.mSelectedCharacter), newStep.toString()).commit();
        }
    }

    private void onContinueToSelectBluetoothToyClicked() {
        if (VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
            continueToSelectBluetoothToy();
            return;
        }
        Fragment newFragment = RequestBLEPermissionFragment.newInstance(this.mSelectedCharacter);
        getFragmentManager().beginTransaction().setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).replace(R.id.fragment_container, newFragment, getStepForFragment(newFragment).toString()).commit();
    }

    @TargetApi(23)
    public void onRequestBlePermissionClicked() {
        requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION"}, 42);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 42:
                if (grantResults[0] == 0) {
                    getFragmentManager().popBackStack();
                    continueToSelectBluetoothToy();
                    return;
                }
                Snackbar.make(this.mSaveDeleteButtons, (int) R.string.hint_location_permission_denied, 0).show();
                return;
            default:
                return;
        }
    }

    private void continueToSelectBluetoothToy() {
        SelectBluetoothToyFragment newFragment = SelectBluetoothToyFragment.newInstance(this.mSelectedCharacter);
        Step newStep = getStepForFragment(newFragment);
        getFragmentManager().beginTransaction().setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(newStep.toString()).replace(R.id.fragment_container, newFragment, newStep.toString()).commit();
    }

    private void onEditProfile() {
        CreateOrEditChildProfileFragment newFragment = CreateOrEditChildProfileFragment.newInstance(this.mDisplayName, this.mUsername, this.mBirthMonth, this.mBirthDayOfMonth, this.mProfilePhotoUri, true, this.mPetNickname, this.mSelectedCharacter);
        Step newStep = getStepForFragment(newFragment);
        getFragmentManager().beginTransaction().addToBackStack(newStep.toString()).replace(R.id.fragment_container, newFragment, newStep.toString()).commit();
    }

    private void onCreateProfile() {
        SelectPetAvatarFragment newFragment = SelectPetAvatarFragment.newInstance(this.mSelectedCharacter, this.mPetNickname);
        Step newStep = getStepForFragment(newFragment);
        getFragmentManager().beginTransaction().addToBackStack(newStep.toString()).replace(R.id.fragment_container, newFragment, newStep.toString()).commit();
    }

    private boolean isAvatarValid() {
        return isWithinLength(this.mPetNickname, 1, 32);
    }

    private boolean isProfileValid() {
        if (isWithinLength(this.mDisplayName, 1, 32) && isWithinLength(this.mUsername, 1, 32) && isWithinLength(this.mPetNickname, 1, 32)) {
            return true;
        }
        return false;
    }

    private boolean isPlushToyValid() {
        return this.mToyIdentifier != null;
    }

    private boolean isWithinLength(String text, int minLength, int maxLength) {
        return text != null && text.length() >= minLength && text.length() <= maxLength;
    }

    void createAndSaveChildProfile() {
        if (!isProfileValid() || !isPlushToyValid()) {
            return;
        }
        if (this.mBirthMonth == 0 || this.mBirthDayOfMonth != 0) {
            showProgress(R.string.title_saving_profile);
            PrivateProfile privateProfile = this.mProfile.getPrivateProfile();
            privateProfile.setProfileType(ProfileType.CHILD);
            if (this.mBirthMonth == 0) {
                privateProfile.remove(PrivateProfile.BIRTH_MONTH);
                privateProfile.remove(PrivateProfile.BIRTH_DAY_OF_MONTH);
            } else {
                privateProfile.setBirthMonth(this.mBirthMonth);
                privateProfile.setBirthDayOfMonth(this.mBirthDayOfMonth);
            }
            PlushToy plushToy = this.mProfile.getPlushToy();
            plushToy.setNickName(this.mPetNickname);
            plushToy.setCharacter(this.mSelectedCharacter);
            plushToy.setConfigID(this.mToyIdentifier);
            this.mProfile.setDisplayName(this.mDisplayName);
            this.mProfile.setUsername(this.mUsername);
            this.mProfile.setPlushToy(plushToy);
            this.mProfile.setOwner(ParseUser.getCurrentUser());
            this.mProfile.setPrivateProfile(privateProfile);
            this.mProfile.getACL().setPublicReadAccess(true);
            if (this.mProfilePhotoUri == null || (this.mProfile.getPortrait() != null && this.mProfilePhotoUri.equals(Uri.parse(this.mProfile.getPortrait().getFile().getUrl())))) {
                saveProfile(this.mProfile);
                return;
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
                            bitmap = (Bitmap) Glide.with(CreateOrEditChildProfileActivity.this).load(CreateOrEditChildProfileActivity.this.mProfilePhotoUri).asBitmap().centerCrop().into(CreateOrEditChildProfileActivity.this.getResources().getDimensionPixelSize(R.dimen.profile_photo_width_px), CreateOrEditChildProfileActivity.this.getResources().getDimensionPixelSize(R.dimen.profile_photo_height_px)).get();
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
                                CreateOrEditChildProfileActivity.this.mProfile.setPortrait(portrait);
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
                            CreateOrEditChildProfileActivity.this.mProfile.setPortrait(portrait);
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
                        CreateOrEditChildProfileActivity.this.mProfile.setPortrait(portrait);
                        new Handler(Looper.getMainLooper()).post(saveProfileRunnable);
                    }
                }).start();
                return;
            }
        }
        new Builder(this).setTitle((int) R.string.title_dialog_incomplete_birthday).setMessage((int) R.string.message_enter_child_birthday).setNegativeButton((int) R.string.btn_okay, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    private void saveProfile(Profile profile) {
        ModelHelper.saveProfile(profile, new SaveCallback() {
            public void done(ParseException e) {
                CreateOrEditChildProfileActivity.this.hideProgress();
                if (!CreateOrEditChildProfileActivity.this.isFinishing() && !CreateOrEditChildProfileActivity.this.isDestroyed()) {
                    if (e != null) {
                        e.printStackTrace();
                        Utils.showErrorDialog(CreateOrEditChildProfileActivity.this, ErrorMessages.getStringResourceIdForCreateProfileException(CreateOrEditChildProfileActivity.this, e));
                        return;
                    }
                    if (CreateOrEditChildProfileActivity.this.isNewChild) {
                        EventBus.getDefault().post(new ProfileAddedEvent());
                    } else {
                        EventBus.getDefault().post(new ProfileUpdatedEvent());
                    }
                    CreateOrEditChildProfileActivity.this.setResult(-1);
                    CreateOrEditChildProfileActivity.this.finish();
                }
            }
        });
    }

    private void updateCloudPetDetails() {
        CreateOrEditChildProfileFragment createOrEditChildProfileFragment = (CreateOrEditChildProfileFragment) getFragmentManager().findFragmentByTag(Step.CONFIGURE_PROFILE.toString());
        if (createOrEditChildProfileFragment != null) {
            createOrEditChildProfileFragment.setPlushToyCharacter(this.mSelectedCharacter);
            createOrEditChildProfileFragment.setPlushToyCharacterName(this.mPetNickname);
        }
    }

    private void updateButtonStates() {
        switch (getCurrentStep()) {
            case CONFIGURE_PROFILE:
                this.mNextButton.setVisibility(8);
                this.mSaveButton.setEnabled(isProfileValid());
                this.mSaveDeleteButtons.setVisibility(0);
                return;
            case SELECT_CHARACTER:
                this.mNextButton.setEnabled(isAvatarValid());
                this.mNextButton.setVisibility(0);
                this.mSaveDeleteButtons.setVisibility(8);
                return;
            case CONFIGURE_TOY_COMPLETE_CONNECTION:
            case CONFIGURE_TOY_ERROR:
            case REQUEST_BLE_PERMISSION:
            case CONFIGURE_TOY:
                this.mNextButton.setVisibility(8);
                this.mSaveDeleteButtons.setVisibility(8);
                return;
            default:
                return;
        }
    }

    private boolean isEditMode() {
        return (this.mProfile == null || this.mProfile.getObjectId() == null) ? false : true;
    }

    private void setCharacter(Character character) {
        this.mSelectedCharacter = character;
        setTheme(PlushToy.getAppThemeResourceForCharacter(this.mSelectedCharacter));
    }
}
