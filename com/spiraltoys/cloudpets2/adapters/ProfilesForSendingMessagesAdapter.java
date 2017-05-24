package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.google.common.collect.Sets;
import com.google.common.hash.HashCode;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.spiraltoys.cloudpets2.adapters.ParseQueryAdapter.ParseObjectViewHolder;
import com.spiraltoys.cloudpets2.databinding.ListItemHeaderBinding;
import com.spiraltoys.cloudpets2.events.ProfileSelectedToSendMessageEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.FriendRecord;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.events.LocalDatastoreChangedEvent;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ProfilesForSendingMessagesAdapter extends Adapter<ViewHolder> implements ProfileSelector {
    private static final Comparator<Profile> DISPLAY_NAME_COMPARATOR = new Comparator<Profile>() {
        public int compare(Profile lhs, Profile rhs) {
            return lhs.getDisplayName().toLowerCase().compareTo(rhs.getDisplayName().toLowerCase());
        }
    };
    public static final int PROFILE_VIEW_TYPE = 1;
    public static final int SECTION_HEADING_VIEW_TYPE = 0;
    private boolean mAreChildrenLoaded;
    private boolean mAreFriendsLoaded;
    private List<Profile> mChildrenProfiles = new ArrayList();
    private List<Profile> mFriendProfiles = new ArrayList();
    private ParseQuery<Profile> mMyChildrenQuery;
    private ParseQuery<FriendRecord> mMyFriendsQuery;
    private HashSet<Profile> mSelectedProfiles = new HashSet();

    public class ProfileViewHolder extends ParseObjectViewHolder<Profile> {
        ImageView mCheckBox;
        TextView mDisplayName;
        Profile mProfile;
        ImageView mProfilePhoto;
        View mView;

        public ProfileViewHolder(View view) {
            super(view);
            this.mView = view;
            this.mProfilePhoto = (ImageView) view.findViewById(R.id.profile_photo);
            this.mCheckBox = (ImageView) view.findViewById(R.id.checkbox_image);
            this.mDisplayName = (TextView) view.findViewById(R.id.display_name);
        }

        public void setParseObject(Profile profile) {
            String str;
            this.mProfile = profile;
            Context context = this.mView.getContext();
            this.mCheckBox.setImageResource(ProfilesForSendingMessagesAdapter.this.mSelectedProfiles.contains(this.mProfile) ? R.drawable.radio_selected : R.drawable.radio_unselected);
            this.mView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    EventBus.getDefault().postSticky(new ProfileSelectedToSendMessageEvent(ProfileViewHolder.this.mProfile));
                    ProfileViewHolder.this.toggleSelection();
                }
            });
            String displayName = this.mProfile.getDisplayName();
            String placeholderText = displayName.isEmpty() ? " " : Character.toString(displayName.charAt(0)).toUpperCase();
            this.mDisplayName.setText(displayName);
            ProfilePortrait profilePortrait = this.mProfile.getPortrait();
            BitmapPool pool = Glide.get(context).getBitmapPool();
            RequestManager with = Glide.with(context);
            if (profilePortrait == null) {
                str = null;
            } else {
                str = profilePortrait.getFile().getUrl();
            }
            with.load(str).bitmapTransform(new Transformation[]{new DoubleBorderCropCircleTransformation(context, pool, context.getResources().getColor(R.color.profile_photo_border_dark), 0)}).placeholder(ProfilePlaceholderDrawable.getListItemInstance(context, placeholderText)).crossFade().into(this.mProfilePhoto);
        }

        private void toggleSelection() {
            if (ProfilesForSendingMessagesAdapter.this.mSelectedProfiles.remove(this.mProfile)) {
                this.mCheckBox.setImageResource(R.drawable.radio_unselected);
                return;
            }
            ProfilesForSendingMessagesAdapter.this.mSelectedProfiles.add(this.mProfile);
            this.mCheckBox.setImageResource(R.drawable.radio_selected);
        }
    }

    public static class SectionHeadingViewHolder extends ViewHolder {
        ListItemHeaderBinding mBinding;

        public SectionHeadingViewHolder(ListItemHeaderBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void setTitle(int titleResourceId) {
            this.mBinding.text.setText(titleResourceId);
        }
    }

    public ProfilesForSendingMessagesAdapter(ParseQuery<Profile> children, ParseQuery<FriendRecord> friends) {
        this.mMyChildrenQuery = children;
        this.mMyFriendsQuery = friends;
        reloadData();
        setHasStableIds(true);
        EventBus.getDefault().register(this);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                return new SectionHeadingViewHolder((ListItemHeaderBinding) DataBindingUtil.inflate(inflater, R.layout.list_item_header, parent, false));
            case 1:
                return new ProfileViewHolder(inflater.inflate(R.layout.list_item_selectable_profile, parent, false));
            default:
                return null;
        }
    }

    public int getItemViewType(int position) {
        if (hasChildrenSection() && position == 0) {
            return 0;
        }
        if (hasFriendSection() && position == (getNumberOfSectionHeadings() + this.mChildrenProfiles.size()) - 1) {
            return 0;
        }
        return 1;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                onBindSectionHeadingViewHolder((SectionHeadingViewHolder) holder, position);
                return;
            case 1:
                onBindProfileViewHolder((ProfileViewHolder) holder, position);
                return;
            default:
                return;
        }
    }

    private void onBindSectionHeadingViewHolder(SectionHeadingViewHolder holder, int position) {
        holder.setTitle(position == 0 ? R.string.section_heading_family : R.string.section_heading_friends);
    }

    private Profile getProfileAtPosition(int position) {
        if (position <= this.mChildrenProfiles.size()) {
            return (Profile) this.mChildrenProfiles.get(position - 1);
        }
        return (Profile) this.mFriendProfiles.get((position - this.mChildrenProfiles.size()) - (hasChildrenSection() ? 2 : 1));
    }

    private void onBindProfileViewHolder(ProfileViewHolder holder, int position) {
        holder.setParseObject(getProfileAtPosition(position));
    }

    public void reloadData() {
        this.mAreChildrenLoaded = false;
        this.mAreFriendsLoaded = false;
        if (this.mMyChildrenQuery != null) {
            this.mMyChildrenQuery.findInBackground(new FindCallback<Profile>() {
                public void done(List<Profile> list, ParseException e) {
                    if (e != null) {
                        e.printStackTrace();
                        return;
                    }
                    ProfilesForSendingMessagesAdapter.this.mChildrenProfiles = list;
                    ProfilesForSendingMessagesAdapter.this.mAreChildrenLoaded = true;
                    ProfilesForSendingMessagesAdapter.this.dataReloadable(ProfilesForSendingMessagesAdapter.this.mAreChildrenLoaded, ProfilesForSendingMessagesAdapter.this.mAreFriendsLoaded);
                }
            });
        }
        if (this.mMyFriendsQuery != null) {
            this.mMyFriendsQuery.findInBackground(new FindCallback<FriendRecord>() {
                public void done(List<FriendRecord> list, ParseException e) {
                    if (e != null) {
                        e.printStackTrace();
                        return;
                    }
                    List<Profile> tempProfiles = new ArrayList();
                    for (FriendRecord record : list) {
                        tempProfiles.add(record.getTargetProfile());
                    }
                    ProfilesForSendingMessagesAdapter.this.mFriendProfiles = tempProfiles;
                    ProfilesForSendingMessagesAdapter.this.mAreFriendsLoaded = true;
                    ProfilesForSendingMessagesAdapter.this.dataReloadable(ProfilesForSendingMessagesAdapter.this.mAreChildrenLoaded, ProfilesForSendingMessagesAdapter.this.mAreFriendsLoaded);
                }
            });
        }
    }

    public List<Profile> getSelectedProfiles() {
        return new ArrayList(this.mSelectedProfiles);
    }

    public void dataReloadable(boolean children, boolean friends) {
        if (children && friends) {
            Collections.sort(this.mChildrenProfiles, DISPLAY_NAME_COMPARATOR);
            Collections.sort(this.mFriendProfiles, DISPLAY_NAME_COMPARATOR);
            HashSet<Profile> combinedProfilesSet = new HashSet(this.mChildrenProfiles);
            combinedProfilesSet.addAll(this.mFriendProfiles);
            this.mSelectedProfiles = new HashSet(Sets.intersection(combinedProfilesSet, this.mSelectedProfiles));
            notifyDataSetChanged();
        }
    }

    private boolean hasChildrenSection() {
        return !this.mChildrenProfiles.isEmpty();
    }

    private boolean hasFriendSection() {
        return !this.mFriendProfiles.isEmpty();
    }

    private int getNumberOfSectionHeadings() {
        int i = 1;
        int i2 = hasChildrenSection() ? 1 : 0;
        if (!hasFriendSection()) {
            i = 0;
        }
        return i2 + i;
    }

    public int getItemCount() {
        return (this.mChildrenProfiles.size() + this.mFriendProfiles.size()) + getNumberOfSectionHeadings();
    }

    public long getItemId(int position) {
        int viewType = getItemViewType(position);
        return viewType == 1 ? HashCode.fromBytes(getProfileAtPosition(position).getObjectId().getBytes()).asLong() : (long) viewType;
    }

    public void release() {
        EventBus.getDefault().unregister(this);
    }

    @DebugLog
    public void onEvent(LocalDatastoreChangedEvent event) {
        reloadData();
    }
}
