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
import com.spiraltoys.cloudpets2.databinding.ListItemLullabyBinding;
import com.spiraltoys.cloudpets2.events.LullabyClickedEvent;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.model.Lullaby;
import com.spiraltoys.cloudpets2.util.RoundedRectTransformation;
import de.greenrobot.event.EventBus;

public class LullabyAdapter extends Adapter<LullabyListItemViewHolder> {
    private Lullaby[] mLullabies = new Lullaby[]{new Lullaby(R.string.lullaby_title_baa_baa_black_sheep, R.drawable.lullaby_ba_ba_black_sheep, R.raw.lullaby_ba_ba_black_sheep), new Lullaby(R.string.lullaby_title_this_old_man, R.drawable.lullaby_this_old_man, R.raw.lullaby_this_old_man), new Lullaby(R.string.lullaby_title_twinkle_twinkle_little_star, R.drawable.lullaby_twinkle_twinkle_little_star, R.raw.lullaby_twinkle_twinkle_little_star), new Lullaby(R.string.lullaby_title_welsh_lullaby, R.drawable.lullaby_welsh, R.raw.lullaby_welsh)};

    public class LullabyListItemViewHolder extends ViewHolder implements OnClickListener {
        ListItemLullabyBinding mBinding;
        Lullaby mLullaby;

        public LullabyListItemViewHolder(ListItemLullabyBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void setLullaby(Lullaby lullaby) {
            this.mLullaby = lullaby;
            this.mBinding.setLullaby(this.mLullaby);
            this.mBinding.executePendingBindings();
            Context context = this.mBinding.getRoot().getContext();
            BitmapPool pool = Glide.get(context).getBitmapPool();
            DrawableTypeRequest load = Glide.with(context).load(Integer.valueOf(this.mLullaby.getImageResourceId()));
            Transformation[] transformationArr = new Transformation[1];
            transformationArr[0] = new RoundedRectTransformation(context, pool, -1, -1, (float) context.getResources().getDimensionPixelSize(R.dimen.default_rounded_corner_radius), true);
            load.bitmapTransform(transformationArr).into(this.mBinding.lullabyImage);
            this.mBinding.tile.setOnClickListener(this);
        }

        public void onClick(View v) {
            EventBus.getDefault().post(new LullabyClickedEvent(this.mLullaby));
        }
    }

    public LullabyListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LullabyListItemViewHolder((ListItemLullabyBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_lullaby, parent, false));
    }

    public void onBindViewHolder(LullabyListItemViewHolder holder, int position) {
        holder.setLullaby(this.mLullabies[position]);
    }

    public int getItemCount() {
        return this.mLullabies.length;
    }
}
