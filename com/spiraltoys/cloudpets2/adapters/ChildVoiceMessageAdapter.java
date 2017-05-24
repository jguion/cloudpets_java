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
import com.spiraltoys.cloudpets2.databinding.ListItemChildVoiceMessageBinding;
import com.spiraltoys.cloudpets2.events.VoiceMessageApprovedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessageClickedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessageDeletedEvent;
import com.spiraltoys.cloudpets2.events.VoiceMessageMarkedAsViewedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.ProfilePortrait;
import com.spiraltoys.cloudpets2.model.VoiceMessage;
import com.spiraltoys.cloudpets2.util.DoubleBorderCropCircleTransformation;
import com.spiraltoys.cloudpets2.views.ProfilePlaceholderDrawable;
import de.greenrobot.event.EventBus;

public class ChildVoiceMessageAdapter extends ParseQueryAdapter<ChildVoiceMessageViewHolder, VoiceMessage> {

    public class ChildVoiceMessageViewHolder extends ParseObjectViewHolder<VoiceMessage> implements OnClickListener {
        ListItemChildVoiceMessageBinding mBinding;
        VoiceMessage mVoiceMessage;

        public ChildVoiceMessageViewHolder(ListItemChildVoiceMessageBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mBinding.getRoot().setOnClickListener(this);
        }

        public void setParseObject(VoiceMessage voiceMessage) {
            String str;
            this.mVoiceMessage = voiceMessage;
            this.mBinding.setVoiceMessage(voiceMessage);
            this.mBinding.executePendingBindings();
            Context context = this.mBinding.getRoot().getContext();
            String displayName = this.mVoiceMessage.getSender().getDisplayName();
            String placeholderText = displayName.isEmpty() ? " " : Character.toString(displayName.charAt(0)).toUpperCase();
            ProfilePortrait profilePortrait = voiceMessage.getSender().getPortrait();
            BitmapPool pool = Glide.get(context).getBitmapPool();
            RequestManager with = Glide.with(context);
            if (profilePortrait == null) {
                str = null;
            } else {
                str = profilePortrait.getFile().getUrl();
            }
            with.load(str).bitmapTransform(new Transformation[]{new DoubleBorderCropCircleTransformation(context, pool, context.getResources().getColor(R.color.profile_photo_border_dark), 0)}).placeholder(ProfilePlaceholderDrawable.getListItemInstance(context, placeholderText)).crossFade().into(this.mBinding.profilePhoto);
        }

        public void onClick(View v) {
            EventBus.getDefault().post(new VoiceMessageClickedEvent(this.mVoiceMessage));
        }
    }

    public ChildVoiceMessageAdapter(ParseQuery<VoiceMessage> query) {
        super(query);
    }

    public ChildVoiceMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChildVoiceMessageViewHolder((ListItemChildVoiceMessageBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_child_voice_message, parent, false));
    }

    public void onEvent(VoiceMessageMarkedAsViewedEvent event) {
        notifyDataSetChanged();
    }

    public void onEvent(VoiceMessageApprovedEvent event) {
        notifyDataSetChanged();
    }

    public void onEvent(VoiceMessageDeletedEvent event) {
        reloadData();
    }
}
