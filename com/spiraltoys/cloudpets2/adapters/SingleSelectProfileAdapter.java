package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
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
import com.spiraltoys.cloudpets2.events.ProfileSelectedEvent;
import com.spiraltoys.cloudpets2.events.ProfileUpdatedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;

public class SingleSelectProfileAdapter extends ParseQueryAdapter<ProfileViewHolder, Profile> {
    private Class mEventType;

    public static class ProfileViewHolder extends ParseObjectViewHolder<Profile> {
        Class mEventType;
        Profile mProfile;
        TextView mProfileAccountType = ((TextView) this.mView.findViewById(R.id.profile_type));
        TextView mProfileDisplayName = ((TextView) this.mView.findViewById(R.id.profile_display_name));
        ImageView mProfilePhoto = ((ImageView) this.mView.findViewById(R.id.profile_photo));
        View mView;

        public ProfileViewHolder(View view, Class eventType) {
            super(view);
            this.mView = view;
            this.mEventType = eventType;
        }

        public void setParseObject(Profile profile) {
            String str;
            this.mProfile = profile;
            Context context = this.mView.getContext();
            this.mProfileDisplayName.setText(profile.getDisplayName());
            if (profile.getPrivateProfile().isAdult()) {
                this.mProfileAccountType.setText(R.string.account_type_parent);
            } else {
                this.mProfileAccountType.setText(R.string.account_type_child);
            }
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
            with.load(str).bitmapTransform(new Transformation[]{new DoubleBorderCropCircleTransformation(context, pool, context.getResources().getColor(R.color.profile_photo_border_dark), 0)}).placeholder(ProfilePlaceholderDrawable.getListItemInstance(context, placeholderText)).crossFade().into(this.mProfilePhoto);
            this.mView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    EventBus.getDefault().post(new ProfileSelectedEvent(ProfileViewHolder.this.mProfile));
                }
            });
        }
    }

    public SingleSelectProfileAdapter(ParseQuery<Profile> query, Class eventType) {
        super(query);
        this.mEventType = eventType;
        setHasStableIds(true);
    }

    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProfileViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_profile, parent, false), this.mEventType);
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
