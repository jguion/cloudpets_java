package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.AlertDialogWrapper.Builder;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.audio.BackgroundMusicTrack;
import com.spiraltoys.cloudpets2.databinding.ActivityAdultVoiceMessageBinding;
import com.spiraltoys.cloudpets2.events.VoiceMessageApprovedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessageDeletedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessageMarkedAsViewedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessageSelectedForEditEvent;
import com.spiraltoys.cloudpets2.fragments.AudioPlayerFragment.OnAudioPlayerInteractionListener;
import com.spiraltoys.cloudpets2.fragments.VoiceMessagePlayerFragment;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.SettingsManager;
import de.greenrobot.event.EventBus;

public class AdultVoiceMessageActivity extends BaseActivity implements OnAudioPlayerInteractionListener {
    private static final int REPLY_REQUEST = 42;
    private ActivityAdultVoiceMessageBinding mBinding;
    private VoiceMessage mVoiceMessage;

    public static void start(Context context, VoiceMessage voiceMessage) {
        EventBus.getDefault().postSticky(new VoiceMessageSelectedForEditEvent(voiceMessage));
        context.startActivity(new Intent(context, AdultVoiceMessageActivity.class));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBinding = (ActivityAdultVoiceMessageBinding) DataBindingUtil.setContentView(this, R.layout.activity_adult_voice_message);
        ButterKnife.inject((Activity) this);
        this.mVoiceMessage = ((VoiceMessageSelectedForEditEvent) EventBus.getDefault().getStickyEvent(VoiceMessageSelectedForEditEvent.class)).getVoiceMessage();
        if (this.mVoiceMessage.getRecipient().getPrivateProfile().isAdult() || !SettingsManager.isParentalControlEnabled(this)) {
            this.mBinding.approveVoiceMessageButton.setVisibility(8);
        }
        if (this.mVoiceMessage.isApproved()) {
            this.mBinding.approveVoiceMessageButton.setText(R.string.btn_unapprove_message);
        }
        getFragmentManager().beginTransaction().replace(R.id.voice_message_player_container, VoiceMessagePlayerFragment.newInstance(this.mVoiceMessage)).commit();
        initToolbar();
        if (this.mVoiceMessage.getRecipient().getPrivateProfile().isAdult()) {
            showToolbarTitle(getString(R.string.title_activity_voice_message_for_me));
            return;
        }
        showToolbarTitle(getString(R.string.title_activity_voice_message_for, new Object[]{this.mVoiceMessage.getRecipient().getDisplayName()}));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            if (this.mVoiceMessage.getRecipient().getPrivateProfile().isAdult() && (this.mVoiceMessage.getSender().getOwner().equals(this.mVoiceMessage.getRecipient().getOwner()) || isAcceptedFriend())) {
                getMenuInflater().inflate(R.menu.adult_voice_message, menu);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return super.onCreateOptionsMenu(menu);
    }

    private boolean isAcceptedFriend() throws ParseException {
        return ModelHelper.isAcceptedFriend(this.mVoiceMessage.getRecipient(), this.mVoiceMessage.getSender());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reply:
                onReplyClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 42 && resultCode == -1) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void onReplyClicked() {
        RecordAndSendMessageActivity.startForResult(this, this.mVoiceMessage.getSender(), 42);
    }

    @OnClick({2131755145})
    void onApproveClicked() {
        boolean approvedState = this.mVoiceMessage.isApproved();
        this.mVoiceMessage.setApproved(!approvedState);
        this.mVoiceMessage.setRejected(false);
        this.mVoiceMessage.saveEventually();
        if (approvedState) {
            Toast.makeText(this, getString(R.string.message_child_message_unapproved, new Object[]{this.mVoiceMessage.getRecipient().getDisplayName().trim()}), 1).show();
        } else {
            Toast.makeText(this, getString(R.string.message_child_message_approved, new Object[]{this.mVoiceMessage.getRecipient().getDisplayName().trim()}), 1).show();
        }
        finish();
        EventBus.getDefault().post(new VoiceMessageApprovedEvent());
    }

    @OnClick({2131755146})
    void onDeleteClicked() {
        new Builder(this).setTitle((int) R.string.title_dialog_delete_message).setMessage((int) R.string.message_delete_voice_message_confirm).setPositiveButton((int) R.string.btn_delete, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AdultVoiceMessageActivity.this.mVoiceMessage.deleteEventually();
                EventBus.getDefault().post(new VoiceMessageDeletedEvent());
                AdultVoiceMessageActivity.this.finish();
            }
        }).setNegativeButton((int) R.string.btn_cancel, null).show();
    }

    public void onPlaybackStarted() {
        markVoiceMessageAsViewed();
    }

    public void onPlaybackPaused() {
    }

    public void onPlaybackCompleted() {
    }

    public BackgroundMusicTrack getBackgroundMusicTrack() {
        return null;
    }

    private void markVoiceMessageAsViewed() {
        if (!this.mVoiceMessage.isParentViewed()) {
            if (this.mVoiceMessage.getRecipient().getPrivateProfile().isAdult()) {
                this.mVoiceMessage.setViewed(true);
            }
            this.mVoiceMessage.setParentViewed(true);
            this.mVoiceMessage.saveEventually();
            EventBus.getDefault().post(new VoiceMessageMarkedAsViewedEvent());
        }
    }
}
