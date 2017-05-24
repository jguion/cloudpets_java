package com.spiraltoys.cloudpets2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import butterknife.ButterKnife;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.spiraltoys.cloudpets2.databinding.ActivityManageProfileBinding;
import com.spiraltoys.cloudpets2.fragments.FriendRequestsFragment;
import com.spiraltoys.cloudpets2.fragments.FriendRequestsFragment.OnFriendRequestInteractionListener;
import com.spiraltoys.cloudpets2.fragments.ProfilesFragment;
import com.spiraltoys.cloudpets2.fragments.ProfilesFragment.OnProfileInteractionListener;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import java.util.List;

public class ManageProfilesActivity extends BaseActivity implements OnProfileInteractionListener, OnFriendRequestInteractionListener {
    private static final int FETCH_ALL_DELAY_MS = 250;
    private static final int NUMBER_OF_TABS = 2;
    private static final int[] TAB_TITLES = new int[]{R.string.label_my_profiles, R.string.label_friend_permission};
    ActivityManageProfileBinding binding;

    private class ManageProfileAdapter extends FragmentPagerAdapter {
        public ManageProfileAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ProfilesFragment();
                case 1:
                    return new FriendRequestsFragment();
                default:
                    return null;
            }
        }

        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int position) {
            return ManageProfilesActivity.this.getString(ManageProfilesActivity.TAB_TITLES[position]);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = (ActivityManageProfileBinding) DataBindingUtil.setContentView(this, R.layout.activity_manage_profile);
        ButterKnife.inject((Activity) this);
        this.binding.pager.setAdapter(new ManageProfileAdapter(getFragmentManager()));
        this.binding.tabs.setupWithViewPager(this.binding.pager);
        if (getIntent().getFlags() == 1) {
            this.binding.pager.setCurrentItem(1);
        }
        initToolbar();
        showToolbarTitle();
        if (savedInstanceState == null) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    ModelHelper.fetchAllProfilesToLocalDatastore(null);
                    ModelHelper.fetchAllFriendsToLocalDatastore(null);
                }
            }, 250);
        }
    }

    public void onRefreshProfileList(final SwipeRefreshLayout swipeRefreshLayout) {
        ModelHelper.fetchAllProfilesToLocalDatastore(new FindCallback<Profile>() {
            public void done(List<Profile> list, ParseException e) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void onRefreshFriendRequestList(final SwipeRefreshLayout swipeRefreshLayout) {
        ModelHelper.fetchAllFriendsToLocalDatastore(new FindCallback<FriendRecord>() {
            public void done(List<FriendRecord> list, ParseException e) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void onInviteFriend() {
        startActivity(new Intent(this, InviteFriendActivity.class));
    }
}
