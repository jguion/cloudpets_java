package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import butterknife.ButterKnife;
import com.google.common.io.Files;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;
import com.spiraltoys.cloudpets2.audio.BackgroundMusicTrack;
import com.spiraltoys.cloudpets2.events.VoiceMessageFailedToSend;
import com.spiraltoys.cloudpets2.fragments.RecordVoiceMessageFragment;
import com.spiraltoys.cloudpets2.fragments.RecordVoiceMessageFragment.OnRecordVoiceMessageFragmentInteractionListener;
import com.spiraltoys.cloudpets2.fragments.RecordedVoiceMessagePlaybackFragment;
import com.spiraltoys.cloudpets2.fragments.RecordedVoiceMessagePlaybackFragment.OnRecordedVoiceMessagePlaybackFragmentInteractionListener;
import com.spiraltoys.cloudpets2.fragments.SelectProfileFragment.OnSelectProfileFragmentInteractionListener;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.ErrorMessages;
import com.spiraltoys.cloudpets2.util.Utils;
import de.greenrobot.event.EventBus;
import icepick.Icepick;
import icepick.State;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class RecordAndSendMessageActivity extends BaseActivity implements OnRecordVoiceMessageFragmentInteractionListener, OnSelectProfileFragmentInteractionListener, OnRecordedVoiceMessagePlaybackFragmentInteractionListener {
    public static final String EXTRA_RECIPIENT_PROFILE_ID = "extra_recipient_profile_id";
    private Profile mAdultProfile;
    private boolean mIsSending;
    @State
    Uri mUri;

    public static void startForResult(BaseActivity context, Profile recipient, int requestCode) {
        Intent intent = new Intent(context, RecordAndSendMessageActivity.class);
        intent.putExtra(EXTRA_RECIPIENT_PROFILE_ID, recipient == null ? null : recipient.getObjectId());
        context.startActivityForResult(intent, requestCode);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState((Object) this, savedInstanceState);
        setContentView((int) R.layout.activity_record_and_send_message);
        ButterKnife.inject((Activity) this);
        if (this.mUri == null) {
            getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.content_container, new RecordVoiceMessageFragment()).commit();
        }
        initToolbar();
        showToolbarTitle();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState((Object) this, outState);
    }

    public void onVoiceMessageRecorded(Uri recordingUri) {
        this.mUri = recordingUri;
        if (!isFinishing() && !isDestroyed()) {
            getFragmentManager().beginTransaction().addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.content_container, RecordedVoiceMessagePlaybackFragment.newInstance(recordingUri, getRecipientProfileId())).commitAllowingStateLoss();
        }
    }

    public void onRefreshProfileList(final SwipeRefreshLayout swipeRefreshLayout) {
        ModelHelper.fetchAllProfilesToLocalDatastore(new FindCallback<Profile>() {
            public void done(List<Profile> list, ParseException e) {
                if (!RecordAndSendMessageActivity.this.isDestroyed() && !RecordAndSendMessageActivity.this.isFinishing()) {
                    ModelHelper.fetchAllFriendsToLocalDatastore(new 1(this));
                }
            }
        });
    }

    public void onSendToRecipientSelected(Profile recipient) {
        ArrayList<Profile> profileList = new ArrayList();
        profileList.add(recipient);
        onProfilesSelected(profileList);
    }

    public synchronized void onProfilesSelected(final Collection<Profile> selectedProfiles) {
        if (!this.mIsSending) {
            this.mIsSending = true;
            showProgress(R.string.title_sending_message_to_recipients);
            try {
                this.mAdultProfile = ModelHelper.getLocalAdultProfile();
                try {
                    byte[] data = Files.toByteArray(new File(this.mUri.getPath()));
                    final ParseFile file = new ParseFile(UUID.randomUUID().toString() + ".wav", data);
                    file.saveInBackground(new SaveCallback() {
                        public void done(ParseException e) {
                            if (e != null) {
                                e.printStackTrace();
                                RecordAndSendMessageActivity.this.mIsSending = false;
                                RecordAndSendMessageActivity.this.hideProgress();
                                EventBus.getDefault().post(new VoiceMessageFailedToSend());
                                Utils.showErrorDialog(RecordAndSendMessageActivity.this, ErrorMessages.getStringResourceIdForErrorSendingVoiceMessage(RecordAndSendMessageActivity.this, e));
                                return;
                            }
                            List<VoiceMessage> messages = new ArrayList();
                            for (Profile profile : selectedProfiles) {
                                VoiceMessage message = new VoiceMessage();
                                message.setFile(file);
                                message.setLocalFilename(file.getName());
                                message.setSender(RecordAndSendMessageActivity.this.mAdultProfile);
                                message.setRecipient(profile);
                                message.setType(RecordAndSendMessageActivity.this.mAdultProfile.getPrivateProfile().getProfileType());
                                if (profile.getOwner().equals(RecordAndSendMessageActivity.this.mAdultProfile.getOwner())) {
                                    message.setApproved(true);
                                }
                                messages.add(message);
                            }
                            ModelHelper.saveVoiceMessages(messages, new 1(this));
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    this.mIsSending = false;
                    hideProgress();
                    Utils.showErrorDialog((Context) this, ErrorMessages.getStringResourceIdForIOException(e));
                }
            } catch (ParseException e2) {
                e2.printStackTrace();
                this.mIsSending = false;
            }
        }
    }

    public boolean containsChildProfile(Collection<Profile> selectedProfiles) {
        for (Profile profile : selectedProfiles) {
            if (profile.getOwner() == this.mAdultProfile.getOwner()) {
                return true;
            }
        }
        return false;
    }

    public BackgroundMusicTrack getBackgroundMusicTrack() {
        return null;
    }

    private String getRecipientProfileId() {
        return getIntent().getStringExtra(EXTRA_RECIPIENT_PROFILE_ID);
    }
}
