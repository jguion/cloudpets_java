package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.adapters.RelationshipAdapter;
import com.spiraltoys.cloudpets2.databinding.ActivityInviteFriendBinding;
import com.spiraltoys.cloudpets2.events.FriendRecordAddedEvent;
import com.spiraltoys.cloudpets2.fragments.SelectProfileFragment;
import com.spiraltoys.cloudpets2.fragments.SelectProfileFragment.OnSelectProfileFragmentInteractionListener;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.FriendRelationship;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import java.util.Collection;
import java.util.List;

public class InviteFriendActivity extends BaseActivity implements OnSelectProfileFragmentInteractionListener {
    private ActivityInviteFriendBinding mBinding;
    private Profile mCurrentProfile;
    private Collection<Profile> mSelectedProfiles;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBinding = (ActivityInviteFriendBinding) DataBindingUtil.setContentView(this, R.layout.activity_invite_friend);
        ButterKnife.inject((Activity) this);
        initToolbar();
        showToolbarTitle();
        SpinnerAdapter relationshipAdapter = new RelationshipAdapter(this);
        this.mBinding.relationshipSpinner.setAdapter(relationshipAdapter);
        this.mBinding.relationshipSpinner.setSelection(relationshipAdapter.getCount() - 1);
        getFragmentManager().beginTransaction().replace(R.id.select_profile_fragment, SelectProfileFragment.newInstance(true, true, false, null)).commit();
        ModelHelper.getLocalAdultProfileInBackground(new GetCallback<Profile>() {
            public void done(Profile profile, ParseException e) {
                if (e == null) {
                    InviteFriendActivity.this.mCurrentProfile = profile;
                }
            }
        });
    }

    public void onRefreshProfileList(final SwipeRefreshLayout swipeRefreshLayout) {
        ModelHelper.fetchAllFriendsToLocalDatastore(new FindCallback<FriendRecord>() {
            public void done(List<FriendRecord> list, ParseException e) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void onProfilesSelected(Collection<Profile> selectedProfiles) {
        this.mSelectedProfiles = selectedProfiles;
    }

    @OnClick({2131755200})
    public void onInviteFriend() {
        if (!showValidationErrors()) {
            FriendRelationship relationship = (FriendRelationship) this.mBinding.relationshipSpinner.getSelectedItem();
            String email = this.mBinding.email.getText().toString();
            showProgress(R.string.title_sending_friend_invite);
            ModelHelper.addFriends(this.mSelectedProfiles, email, email, relationship, new SaveCallback() {
                public void done(ParseException e) {
                    InviteFriendActivity.this.hideProgress();
                    if (e != null) {
                        e.printStackTrace();
                        int messageResourceId = ErrorMessages.getStringResourceIdForErrorSendingFriendRequest(InviteFriendActivity.this, e);
                        if (messageResourceId == R.string.error_send_friend_request_already_sent) {
                            Utils.showHintDialog(InviteFriendActivity.this, messageResourceId);
                            return;
                        } else {
                            Utils.showErrorDialog(InviteFriendActivity.this, messageResourceId);
                            return;
                        }
                    }
                    EventBus.getDefault().post(new FriendRecordAddedEvent());
                    Toast.makeText(InviteFriendActivity.this, R.string.toast_message_successfully_sent_friend_request, 1).show();
                    InviteFriendActivity.this.finish();
                }
            });
        }
    }

    private boolean showValidationErrors() {
        if (this.mBinding.email.getText().toString().isEmpty()) {
            Utils.showHintDialog((Context) this, (int) R.string.error_please_enter_email);
            return true;
        } else if (!Utils.isValidEmailAddress(this.mBinding.email.getText().toString())) {
            Utils.showHintDialog((Context) this, (int) R.string.error_please_enter_valid_email);
            return true;
        } else if (this.mSelectedProfiles == null || this.mSelectedProfiles.isEmpty()) {
            Utils.showHintDialog((Context) this, (int) R.string.error_select_child);
            return true;
        } else if (this.mCurrentProfile == null || !this.mBinding.email.getText().toString().toLowerCase().equals(this.mCurrentProfile.getOwner().getUsername())) {
            return false;
        } else {
            Utils.showHintDialog((Context) this, (int) R.string.error_friend_yourself);
            return true;
        }
    }
}
