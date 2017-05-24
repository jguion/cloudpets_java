package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class SelectBluetoothToyErrorFragment$$ViewInjector<T extends SelectBluetoothToyErrorFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        ((View) finder.findRequiredView(source, R.id.retry_button, "method 'onRetryClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onRetryClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.cancel_button, "method 'onCancelClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onCancelClicked();
            }
        });
    }

    public void reset(T t) {
    }
}
