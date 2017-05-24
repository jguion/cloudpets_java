package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class ProfilesFragment$$ViewInjector<T extends ProfilesFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        ((View) finder.findRequiredView(source, R.id.add_profile_button, "method 'onAddProfileClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onAddProfileClicked();
            }
        });
    }

    public void reset(T t) {
    }
}
