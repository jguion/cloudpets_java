package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.spiraltoys.cloudpets2.adapters.ParseQueryAdapter.ParseObjectViewHolder;
import com.spiraltoys.cloudpets2.databinding.ListItemSelectableProfileBinding;
import com.spiraltoys.cloudpets2.events.FriendRecordAddedEvent;
import com.spiraltoys.cloudpets2.events.FriendRecordDeletedEvent;
import com.spiraltoys.cloudpets2.events.FriendRecordSavedToLocalDatastoreEvent;
import com.spiraltoys.cloudpets2.events.ProfileAddedEvent;
import com.spiraltoys.cloudpets2.events.ProfileDeletedEvent;
import com.spiraltoys.cloudpets2.events.ProfileSelectionChangedEvent;
import com.spiraltoys.cloudpets2.events.ProfileUpdatedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.FriendStatus;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.util.ModelHelper;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class FriendedProfileAdapter extends ParseQueryAdapter<ProfileViewHolder, Profile> implements ProfileSelector {
    private ParseQuery<FriendRecord> mFriendRecordQuery;
    private HashSet<Profile> mPendingProfiles = new HashSet();
    private HashSet<Profile> mSelectedProfiles = new HashSet();

    public class ProfileViewHolder extends ParseObjectViewHolder<Profile> {
        private ListItemSelectableProfileBinding mBinding;
        private Profile mProfile;

        public ProfileViewHolder(ListItemSelectableProfileBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void setParseObject(Profile profile) {
            String str;
            this.mProfile = profile;
            Context context = this.itemView.getContext();
            boolean isPending = FriendedProfileAdapter.this.mPendingProfiles.contains(this.mProfile);
            this.mBinding.checkboxImage.setImageResource(FriendedProfileAdapter.this.mSelectedProfiles.contains(this.mProfile) ? R.drawable.radio_selected : R.drawable.radio_unselected);
            this.mBinding.displayName.setText(isPending ? this.itemView.getResources().getString(R.string.label_friend_name_with_relation, new Object[]{this.mProfile.getDisplayName(), this.itemView.getResources().getString(R.string.friend_status_pending)}) : this.mProfile.getDisplayName());
            this.mBinding.displayName.setTextColor(ContextCompat.getColor(context, isPending ? 17170432 : 17170444));
            this.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    ProfileViewHolder.this.toggleSelection();
                }
            });
            String displayName = this.mProfile.getDisplayName();
            String placeholderText = displayName.isEmpty() ? " " : Character.toString(displayName.charAt(0)).toUpperCase();
            ProfilePortrait profilePortrait = this.mProfile.getPortrait();
            BitmapPool pool = Glide.get(context).getBitmapPool();
            RequestManager with = Glide.with(context);
            if (profilePortrait == null) {
                str = null;
            } else {
                str = profilePortrait.getFile().getUrl();
            }
            with.load(str).bitmapTransform(new Transformation[]{new DoubleBorderCropCircleTransformation(context, pool, ContextCompat.getColor(context, R.color.profile_photo_border_dark), 0)}).placeholder(ProfilePlaceholderDrawable.getListItemInstance(context, placeholderText)).crossFade().into(this.mBinding.profilePhoto);
        }

        private void toggleSelection() {
            if (FriendedProfileAdapter.this.mSelectedProfiles.remove(this.mProfile)) {
                this.mBinding.checkboxImage.setImageResource(R.drawable.radio_unselected);
            } else {
                FriendedProfileAdapter.this.mSelectedProfiles.add(this.mProfile);
                this.mBinding.checkboxImage.setImageResource(R.drawable.radio_selected);
            }
            EventBus.getDefault().post(new ProfileSelectionChangedEvent(FriendedProfileAdapter.this.mSelectedProfiles));
        }
    }

    public FriendedProfileAdapter(ParseQuery<Profile> profileQuery, Profile targetProfile) {
        super(profileQuery, new Comparator<Profile>() {
            public int compare(Profile lhs, Profile rhs) {
                return lhs.getDisplayName().toLowerCase().compareTo(rhs.getDisplayName().toLowerCase());
            }
        });
        ParseQuery<FriendRecord> childFriendsQuery = ModelHelper.getFriendsLocalDatastoreQuery(true, true, false, null).whereEqualTo("recipient", Boolean.valueOf(false));
        ParseQuery<Profile> innerQuery = ParseQuery.getQuery(Profile.class);
        innerQuery.whereEqualTo(Profile.OWNER, ParseUser.getCurrentUser());
        childFriendsQuery.whereMatchesQuery(FriendRecord.SOURCE_PROFILE, innerQuery);
        childFriendsQuery.whereEqualTo(FriendRecord.TARGET_PROFILE, targetProfile);
        this.mFriendRecordQuery = childFriendsQuery;
        reloadData();
    }

    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProfileViewHolder((ListItemSelectableProfileBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_selectable_profile, parent, false));
    }

    public void reloadData() {
        if (this.mFriendRecordQuery != null) {
            this.mFriendRecordQuery.findInBackground(new FindCallback<FriendRecord>() {
                public void done(List<FriendRecord> friendRecords, ParseException e) {
                    if (e != null) {
                        e.printStackTrace();
                    }
                    FriendedProfileAdapter.this.mSelectedProfiles.clear();
                    FriendedProfileAdapter.this.mPendingProfiles.clear();
                    for (FriendRecord friendRecord : friendRecords) {
                        FriendedProfileAdapter.this.mSelectedProfiles.add(friendRecord.getSourceProfile());
                        if (friendRecord.getStatus() == FriendStatus.PENDING) {
                            FriendedProfileAdapter.this.mPendingProfiles.add(friendRecord.getSourceProfile());
                        }
                    }
                    FriendedProfileAdapter.this.notifyDataSetChanged();
                    EventBus.getDefault().post(new ProfileSelectionChangedEvent(FriendedProfileAdapter.this.mSelectedProfiles));
                    super.reloadData();
                }
            });
        }
    }

    public HashSet<Profile> getSelectedProfiles() {
        return new HashSet(this.mSelectedProfiles);
    }

    public void onEvent(FriendRecordAddedEvent event) {
        reloadData();
    }

    public void onEvent(FriendRecordDeletedEvent event) {
        reloadData();
    }

    public void onEvent(FriendRecordSavedToLocalDatastoreEvent event) {
        reloadData();
    }

    public void onEvent(ProfileAddedEvent event) {
        reloadData();
    }

    public void onEvent(ProfileUpdatedEvent event) {
        notifyDataSetChanged();
    }

    public void onEvent(ProfileDeletedEvent event) {
        reloadData();
    }
}
