package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import butterknife.ButterKnife;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.databinding.ActivityMessageCenterBinding;
import com.spiraltoys.cloudpets2.events.VoiceMessageClickedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessagePushNotificationReceivedEvent;
import com.spiraltoys.cloudpets2.fragments.VoiceMessagesFragment;
import com.spiraltoys.cloudpets2.fragments.VoiceMessagesFragment.OnMessageFragmentInteractionListener;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.PrivateProfile.ProfileType;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import de.greenrobot.event.EventBus;
import java.util.List;

public class MessageCenterActivity extends BaseActivity implements OnMessageFragmentInteractionListener {
    private static final int FETCH_ALL_DELAY_MS = 250;
    private static final int[] TAB_LABELS = new int[]{R.string.label_my_inbox, R.string.label_child_messages};
    private static final ProfileType[] TAB_PROFILE_TYPES = new ProfileType[]{ProfileType.ADULT, ProfileType.CHILD};
    ActivityMessageCenterBinding mBinding;

    private class MessagesPagerAdapter extends FragmentPagerAdapter {
        public MessagesPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public int getCount() {
            return MessageCenterActivity.TAB_LABELS.length;
        }

        public Fragment getItem(int position) {
            return VoiceMessagesFragment.newInstance(MessageCenterActivity.TAB_PROFILE_TYPES[position]);
        }

        public CharSequence getPageTitle(int position) {
            return MessageCenterActivity.this.getString(MessageCenterActivity.TAB_LABELS[position]);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBinding = (ActivityMessageCenterBinding) DataBindingUtil.setContentView(this, R.layout.activity_message_center);
        ButterKnife.inject((Activity) this);
        this.mBinding.pager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(this.mBinding.tabs));
        this.mBinding.pager.setAdapter(new MessagesPagerAdapter(getFragmentManager()));
        this.mBinding.tabs.setupWithViewPager(this.mBinding.pager);
        initToolbar();
        showToolbarTitle();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ModelHelper.fetchAllVoiceMessagesToLocalDatastore(null);
            }
        }, 250);
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onRefresh(final SwipeRefreshLayout swipeRefreshLayout) {
        ModelHelper.fetchAllVoiceMessagesToLocalDatastore(new FindCallback<VoiceMessage>() {
            public void done(List<VoiceMessage> list, ParseException e) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void onEvent(VoiceMessageClickedEvent event) {
        AdultVoiceMessageActivity.start(this, event.getVoiceMessage());
    }

    public void onEvent(VoiceMessagePushNotificationReceivedEvent event) {
        ModelHelper.fetchAllVoiceMessagesToLocalDatastore(null);
    }
}
