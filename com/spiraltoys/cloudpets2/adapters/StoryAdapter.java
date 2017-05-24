package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.spiraltoys.cloudpets2.databinding.ListItemStoryBinding;
import com.spiraltoys.cloudpets2.events.StoryClickedEvent;
import com.spiraltoys.cloudpets2.expansion.glide.MainExpansionFile;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Story;
import com.spiraltoys.cloudpets2.model.StoryLoader;
import com.spiraltoys.cloudpets2.util.RoundedRectTransformation;
import de.greenrobot.event.EventBus;
import java.util.List;

public class StoryAdapter extends Adapter<StoryListItemViewHolder> {
    private List<Story> mStories;

    public class StoryListItemViewHolder extends ViewHolder implements OnClickListener {
        ListItemStoryBinding mBinding;
        Story mStory;

        public StoryListItemViewHolder(ListItemStoryBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void setStory(Story story) {
            Context context = this.mBinding.getRoot().getContext();
            this.mStory = story;
            this.mBinding.setStory(story);
            this.mBinding.executePendingBindings();
            BitmapPool pool = Glide.get(context).getBitmapPool();
            DrawableTypeRequest load = Glide.with(context).load(new MainExpansionFile(this.mStory.getPreviewImagePath()));
            Transformation[] transformationArr = new Transformation[1];
            transformationArr[0] = new RoundedRectTransformation(context, pool, -1, -1, (float) context.getResources().getDimensionPixelSize(R.dimen.default_rounded_corner_radius), true);
            load.bitmapTransform(transformationArr).into(this.mBinding.storyImage);
            this.mBinding.tile.setOnClickListener(this);
        }

        public void onClick(View v) {
            EventBus.getDefault().post(new StoryClickedEvent(this.mStory));
        }
    }

    public StoryAdapter(Context context) {
        this.mStories = StoryLoader.getInstance().loadStories(context);
    }

    public StoryListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StoryListItemViewHolder((ListItemStoryBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_story, parent, false));
    }

    public void onBindViewHolder(StoryListItemViewHolder holder, int position) {
        holder.setStory((Story) this.mStories.get(position));
    }

    public int getItemCount() {
        return this.mStories.size();
    }
}
