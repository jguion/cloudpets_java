package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class FloatingProfileSwitcherFragment$$ViewInjector<T extends FloatingProfileSwitcherFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        ((View) finder.findRequiredView(source, R.id.profile_switcher, "method 'onSwitchProfileClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onSwitchProfileClicked();
            }
        });
    }

    public void reset(T t) {
    }
}
