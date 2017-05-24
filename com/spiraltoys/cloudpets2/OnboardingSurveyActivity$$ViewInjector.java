package com.spiraltoys.cloudpets2;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class OnboardingSurveyActivity$$ViewInjector<T extends OnboardingSurveyActivity> extends BaseActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (BaseActivity) target, source);
        ((View) finder.findRequiredView(source, R.id.have_cloudpet_button, "method 'onHaveCloudPetClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onHaveCloudPetClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.dont_have_cloudpet_button, "method 'onDontHaveCloudPetClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onDontHaveCloudPetClicked();
            }
        });
    }

    public void reset(T target) {
        super.reset((BaseActivity) target);
    }
}
