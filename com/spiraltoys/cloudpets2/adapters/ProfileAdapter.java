package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
import com.parse.ParseQuery;
import com.spiraltoys.cloudpets2.adapters.ParseQueryAdapter.ParseObjectViewHolder;
import com.spiraltoys.cloudpets2.events.ProfileAddedEvent;
import com.spiraltoys.cloudpets2.events.ProfileDeletedEvent;
import com.spiraltoys.cloudpets2.events.ProfileSelectedToSendMessageEvent;
import com.spiraltoys.cloudpets2.events.ProfileSelectionChangedEvent;
import com.spiraltoys.cloudpets2.events.ProfileUpdatedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ProfileAdapter extends ParseQueryAdapter<ProfileViewHolder, Profile> implements ProfileSelector {
    private Class mEventType;
    private List<Profile> mPendingProfiles;
    private HashMap<String, Profile> mSelectedStates = new HashMap();

    public static class ProfileViewHolder extends ParseObjectViewHolder<Profile> {
        boolean isPending;
        boolean isSelected;
        ImageView mCheckBox;
        TextView mDisplayname;
        Class mEventType;
        Profile mProfile;
        ImageView mProfilePhoto;
        View mView;

        public ProfileViewHolder(View view, Class eventType, boolean isSelected) {
            super(view);
            this.mView = view;
            this.mProfilePhoto = (ImageView) view.findViewById(R.id.profile_photo);
            this.mCheckBox = (ImageView) view.findViewById(R.id.checkbox_image);
            this.mDisplayname = (TextView) view.findViewById(R.id.display_name);
            this.isSelected = isSelected;
            this.mEventType = eventType;
        }

        public void setParseObject(Profile profile) {
            String str;
            this.mProfile = profile;
            Context context = this.mView.getContext();
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
            with.load(str).bitmapTransform(new Transformation[]{new DoubleBorderCropCircleTransformation(context, pool, ContextCompat.getColor(context, R.color.profile_photo_border_dark), 0)}).placeholder(ProfilePlaceholderDrawable.getListItemInstance(context, placeholderText)).crossFade().into(this.mProfilePhoto);
        }
    }

    public ProfileAdapter(ParseQuery<Profile> query, Class eventType) {
        super(query);
        this.mEventType = eventType;
        setHasStableIds(true);
    }

    public ProfileAdapter(ParseQuery<Profile> profileQuery, List<Profile> selectedProfiles, List<Profile> pendingProfiles, Comparator<Profile> comparator, Class eventType) {
        super(profileQuery, comparator);
        this.mEventType = eventType;
        setHasStableIds(true);
        this.mPendingProfiles = purgeEmptyProfiles(pendingProfiles);
        for (Profile profile : selectedProfiles) {
            this.mSelectedStates.put(profile.getObjectId(), profile);
        }
    }

    private void toggleSelection(String objectId, Profile profile, ImageView checkBox) {
        if (this.mSelectedStates.containsKey(objectId)) {
            this.mSelectedStates.remove(objectId);
            checkBox.setImageResource(R.drawable.radio_unselected);
            return;
        }
        this.mSelectedStates.put(objectId, profile);
        checkBox.setImageResource(R.drawable.radio_selected);
    }

    public void onBindViewHolder(final ProfileViewHolder holder, int position) {
        super.onBindViewHolder((ParseObjectViewHolder) holder, position);
        final Profile selectedProfile = holder.mProfile;
        if (this.mSelectedStates.containsKey(holder.mProfile.getObjectId())) {
            holder.mCheckBox.setImageResource(R.drawable.radio_selected);
        } else {
            holder.mCheckBox.setImageResource(R.drawable.radio_unselected);
        }
        if (this.mPendingProfiles != null) {
            holder.isPending = this.mPendingProfiles.contains(holder.mProfile);
        }
        if (holder.isPending) {
            holder.mDisplayname.setText(holder.mView.getResources().getString(R.string.label_friend_name_with_relation, new Object[]{selectedProfile.getDisplayName(), holder.mView.getResources().getString(R.string.friend_status_pending)}));
            holder.mDisplayname.setTextColor(ContextCompat.getColor(holder.mView.getContext(), 17170432));
        } else {
            holder.mDisplayname.setTextColor(ContextCompat.getColor(holder.mView.getContext(), 17170444));
            holder.mDisplayname.setText(selectedProfile.getDisplayName());
        }
        if (this.mEventType.equals(ProfileSelectedToSendMessageEvent.class)) {
            holder.mView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    EventBus.getDefault().postSticky(new ProfileSelectedToSendMessageEvent(selectedProfile));
                    ProfileAdapter.this.toggleSelection(selectedProfile.getObjectId(), selectedProfile, holder.mCheckBox);
                }
            });
        } else if (this.mEventType.equals(ProfileSelectionChangedEvent.class)) {
            holder.mView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    ProfileAdapter.this.toggleSelection(selectedProfile.getObjectId(), selectedProfile, holder.mCheckBox);
                    EventBus.getDefault().post(new ProfileSelectionChangedEvent(ProfileAdapter.this.mSelectedStates.values()));
                }
            });
        }
    }

    public List<Profile> getSelectedProfiles() {
        return new ArrayList(this.mSelectedStates.values());
    }

    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProfileViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_selectable_profile, parent, false), this.mEventType, false);
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

    private List<Profile> purgeEmptyProfiles(List<Profile> profiles) {
        List<Profile> purgedProfileList = new ArrayList();
        for (Profile profile : profiles) {
            if (!(profile == null || profile.getDisplayName() == null)) {
                purgedProfileList.add(profile);
            }
        }
        return purgedProfileList;
    }
}
