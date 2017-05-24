package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.parse.ParseQuery;
import com.spiraltoys.cloudpets2.adapters.ParseQueryAdapter.ParseObjectViewHolder;
import com.spiraltoys.cloudpets2.databinding.ListItemProfileSwitcherBinding;
import com.spiraltoys.cloudpets2.events.ProfilePickerItemClickedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Profile;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.util.LastAccessedProfileComparator;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;

public class ProfileSwitcherAdapter extends ParseQueryAdapter<ProfileSwitcherViewHolder, Profile> {
    private Profile mSelectedProfile;
    private ProfileSwitcherViewHolder mSelectedProfileViewHolder;

    public class ProfileSwitcherViewHolder extends ParseObjectViewHolder<Profile> implements OnClickListener {
        ListItemProfileSwitcherBinding mBinding;
        Profile mProfile;

        public ProfileSwitcherViewHolder(ListItemProfileSwitcherBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mBinding.getRoot().setOnClickListener(this);
        }

        public void setParseObject(Profile profile) {
            this.mProfile = profile;
            this.mBinding.setProfile(profile);
            this.mBinding.executePendingBindings();
            if (isSelected()) {
                ProfileSwitcherAdapter.this.mSelectedProfileViewHolder = this;
            } else if (ProfileSwitcherAdapter.this.mSelectedProfileViewHolder == this) {
                ProfileSwitcherAdapter.this.mSelectedProfileViewHolder = null;
            }
            setupProfilePhoto();
            updateSelectionIndicator();
        }

        public boolean isSelected() {
            return this.mProfile == ProfileSwitcherAdapter.this.mSelectedProfile;
        }

        private void setupProfilePhoto() {
            String str;
            Context context = this.mBinding.getRoot().getContext();
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
            with.load(str).bitmapTransform(new Transformation[]{new DoubleBorderCropCircleTransformation(context, pool, context.getResources().getColor(R.color.profile_photo_border), 0)}).placeholder(ProfilePlaceholderDrawable.getListItemInstanceLightBorder(context, placeholderText)).crossFade().into(this.mBinding.profilePhoto);
        }

        private void updateSelectionIndicator() {
            this.mBinding.selectionIndicator.setVisibility(isSelected() ? 0 : 4);
        }

        public void onClick(View v) {
            ProfileSwitcherAdapter.this.mSelectedProfile = this.mProfile;
            ProfileSwitcherViewHolder lastSelectedViewHolder = ProfileSwitcherAdapter.this.mSelectedProfileViewHolder;
            ProfileSwitcherAdapter.this.mSelectedProfileViewHolder = this;
            if (lastSelectedViewHolder != null) {
                lastSelectedViewHolder.updateSelectionIndicator();
            }
            updateSelectionIndicator();
            EventBus.getDefault().post(new ProfilePickerItemClickedEvent(this.mProfile));
        }
    }

    public ProfileSwitcherAdapter(ParseQuery<Profile> query, Profile selectedProfile, Context context) {
        super(query, new LastAccessedProfileComparator(context));
        this.mSelectedProfile = selectedProfile;
    }

    public ProfileSwitcherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProfileSwitcherViewHolder((ListItemProfileSwitcherBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_profile_switcher, parent, false));
    }
}
