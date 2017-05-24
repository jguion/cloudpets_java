package com.spiraltoys.cloudpets2;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.ButterKnife.Finder;
import com.spiraltoys.cloudpets2.free.R;
import com.spiraltoys.cloudpets2.views.PetVideoSurfaceView;

public class InteractiveToyActivity$$ViewInjector<T extends InteractiveToyActivity> extends BaseActivity$$ViewInjector<T> {
    public void inject(Finder finder, T target, Object source) {
        super.inject(finder, (BaseActivity) target, source);
        target.mContainer = (FrameLayout) finder.castView((View) finder.findRequiredView(source, R.id.container, "field 'mContainer'"), R.id.container, "field 'mContainer'");
        target.mPetVideoSurfaceView = (PetVideoSurfaceView) finder.castView((View) finder.findRequiredView(source, R.id.pet_video, "field 'mPetVideoSurfaceView'"), R.id.pet_video, "field 'mPetVideoSurfaceView'");
        target.mVideoPlaceholder = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.video_placeholder, "field 'mVideoPlaceholder'"), R.id.video_placeholder, "field 'mVideoPlaceholder'");
    }

    public void reset(T target) {
        super.reset((BaseActivity) target);
        target.mContainer = null;
        target.mPetVideoSurfaceView = null;
        target.mVideoPlaceholder = null;
    }
}
