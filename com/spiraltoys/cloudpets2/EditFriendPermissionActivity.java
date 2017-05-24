package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.afollestad.materialdialogs.AlertDialogWrapper.Builder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.adapters.FriendPermissionsAdapter.ChildFriendRecordGroup;
import com.spiraltoys.cloudpets2.events.FriendRecordEditEvent;
import com.spiraltoys.cloudpets2.events.ProfilesSelectedForSelectProfileEvent;
import com.spiraltoys.cloudpets2.fragments.SelectProfileFragment;
import com.spiraltoys.cloudpets2.fragments.SelectProfileFragment.OnSelectProfileFragmentInteractionListener;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.FriendStatus;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EditFriendPermissionActivity extends BaseActivity implements OnSelectProfileFragmentInteractionListener {
    private ChildFriendRecordGroup mFriendRecordGroup;
    private boolean mHasUnsavedChanges;
    private ArrayList<Profile> mNewlySelectedProfiles;
    private ArrayList<Profile> mPendingProfiles;
    private Profile mProfile;
    @InjectView(2131755177)
    TextView mProfileName;
    @InjectView(2131755168)
    ImageView mProfilePhoto;
    private ArrayList<Profile> mSelectedProfiles;

    public void onCreate(Bundle savedInstanceState) {
        String str;
        super.onCreate(savedInstanceState);
        this.mFriendRecordGroup = ((FriendRecordEditEvent) EventBus.getDefault().getStickyEvent(FriendRecordEditEvent.class)).getFriendRecordGroup();
        this.mProfile = this.mFriendRecordGroup.first().getTargetProfile();
        this.mSelectedProfiles = new ArrayList();
        this.mNewlySelectedProfiles = new ArrayList();
        this.mPendingProfiles = new ArrayList();
        setContentView((int) R.layout.activity_edit_friend_permission);
        ButterKnife.inject((Activity) this);
        initToolbar();
        showToolbarTitle();
        String displayName = this.mProfile.getDisplayName();
        String placeholderText = displayName.isEmpty() ? " " : Character.toString(displayName.charAt(0)).toUpperCase();
        ProfilePortrait profilePortrait = this.mProfile.getPortrait();
        BitmapPool pool = Glide.get(this).getBitmapPool();
        RequestManager with = Glide.with((FragmentActivity) this);
        if (profilePortrait == null) {
            str = null;
        } else {
            str = profilePortrait.getFile().getUrl();
        }
        with.load(str).bitmapTransform(new DoubleBorderCropCircleTransformation(this, pool, ContextCompat.getColor(this, R.color.profile_photo_border_dark), 0)).placeholder(ProfilePlaceholderDrawable.getLargeInstance(this, placeholderText)).crossFade().into(this.mProfilePhoto);
        this.mProfileName.setText(getString(R.string.label_friend_name_with_relation, new Object[]{this.mProfile.getDisplayName(), this.mFriendRecordGroup.first().getRelationship().getLocalizedString(this)}));
        Iterator it = this.mFriendRecordGroup.iterator();
        while (it.hasNext()) {
            FriendRecord record = (FriendRecord) it.next();
            this.mSelectedProfiles.add(record.getSourceProfile());
            this.mNewlySelectedProfiles.add(record.getSourceProfile());
            if (record.getStatus() == FriendStatus.PENDING) {
                this.mPendingProfiles.add(record.getSourceProfile());
            }
        }
        EventBus.getDefault().postSticky(new ProfilesSelectedForSelectProfileEvent(this.mSelectedProfiles, this.mPendingProfiles));
        getFragmentManager().beginTransaction().replace(R.id.select_profile_fragment, SelectProfileFragment.newInstance(true, true, false, this.mFriendRecordGroup.first().getTargetProfile().getObjectId())).commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_friend_permissions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                backRequested();
                return true;
            case R.id.action_delete:
                onDeleteClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void hideSoftKeyboard() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private void goBack() {
        hideSoftKeyboard();
        super.onBackPressed();
    }

    private void backRequested() {
        if (this.mHasUnsavedChanges) {
            new Builder(this).setTitle((int) R.string.title_discard_changes_confirmation).setMessage((int) R.string.message_discard_changes_confirmation).setNegativeButton((int) R.string.btn_discard_changes, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    EditFriendPermissionActivity.this.goBack();
                }
            }).setNeutralButton((int) R.string.btn_cancel, null).show();
        } else {
            goBack();
        }
    }

    public void onBackPressed() {
        backRequested();
    }

    public void onRefreshProfileList(final SwipeRefreshLayout swipeRefreshLayout) {
        ModelHelper.fetchAllFriendsToLocalDatastore(new FindCallback<FriendRecord>() {
            public void done(List<FriendRecord> list, ParseException e) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void onProfilesSelected(Collection<Profile> selectedProfiles) {
        this.mNewlySelectedProfiles = new ArrayList(selectedProfiles);
        if (this.mSelectedProfiles.size() != this.mNewlySelectedProfiles.size()) {
            this.mHasUnsavedChanges = true;
        }
    }

    protected void onDeleteClicked() {
        Builder builder = new Builder(this);
        builder.setTitle((int) R.string.title_delete_friend);
        builder.setMessage((int) R.string.message_remove_friend);
        builder.setPositiveButton((int) R.string.btn_okay, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                EditFriendPermissionActivity.this.showProgress(R.string.message_removing);
                ParseObject.deleteAllInBackground(new ArrayList(EditFriendPermissionActivity.this.mFriendRecordGroup), new 1(this));
            }
        });
        builder.setNegativeButton((int) R.string.btn_cancel, null);
        builder.show();
    }

    @OnClick({2131755179})
    protected void savePermissionsClicked() {
        showProgress(R.string.toast_message_updating_friend_permissions);
        List<Profile> original = new ArrayList(this.mSelectedProfiles);
        List<Profile> newlyChanged = new ArrayList(this.mNewlySelectedProfiles);
        List<Profile> temp = new ArrayList(newlyChanged);
        newlyChanged.removeAll(this.mSelectedProfiles);
        original.removeAll(temp);
        final List<FriendRecord> toBeDeletedFriendRecords = new ArrayList();
        Iterator it = this.mFriendRecordGroup.iterator();
        while (it.hasNext()) {
            FriendRecord friendRecord = (FriendRecord) it.next();
            for (int i = 0; i < original.size(); i++) {
                if (friendRecord.getSourceProfile().equals(original.get(i))) {
                    toBeDeletedFriendRecords.add(friendRecord);
                    break;
                }
            }
        }
        ModelHelper.addFriends(newlyChanged, this.mFriendRecordGroup.first().getTargetEmailAddress(), this.mFriendRecordGroup.first().getName(), this.mFriendRecordGroup.first().getRelationship(), new SaveCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    EditFriendPermissionActivity.this.hideProgress();
                    Utils.showErrorDialog(EditFriendPermissionActivity.this, ErrorMessages.getStringResourceIdForGenericParseExceiption(EditFriendPermissionActivity.this));
                    return;
                }
                ParseObject.deleteAllInBackground(toBeDeletedFriendRecords, new 1(this));
            }
        });
    }
}
