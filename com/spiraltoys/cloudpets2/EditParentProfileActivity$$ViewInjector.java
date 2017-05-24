package com.spiraltoys.cloudpets2;

import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class EditParentProfileActivity$$ViewInjector<T extends EditParentProfileActivity> extends BaseActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (BaseActivity) target, source);
        View view = (View) finder.findRequiredView(source, R.id.save_adult_profile, "field 'mSaveProfileButton' and method 'onSaveProfileClicked'");
        target.mSaveProfileButton = (Button) finder.castView(view, R.id.save_adult_profile, "field 'mSaveProfileButton'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onSaveProfileClicked();
            }
        });
    }

    public void reset(T target) {
        super.reset((BaseActivity) target);
        target.mSaveProfileButton = null;
    }
}
