package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class ProfileSwitcherDialogFragment$$ViewInjector<T extends ProfileSwitcherDialogFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        ((View) finder.findRequiredView(source, R.id.continue_button, "method 'onContinueToProfileClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onContinueToProfileClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.log_out_button, "method 'onLogOutClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onLogOutClicked();
            }
        });
    }

    public void reset(T t) {
    }
}
