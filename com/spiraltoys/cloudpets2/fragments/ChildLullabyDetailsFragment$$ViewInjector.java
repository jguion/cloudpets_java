package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class ChildLullabyDetailsFragment$$ViewInjector<T extends ChildLullabyDetailsFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        ((View) finder.findRequiredView(source, R.id.send_button, "method 'onSendToToyClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onSendToToyClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.increase_duration_button, "method 'onIncreaseDurationClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onIncreaseDurationClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.reduce_duration_button, "method 'onReduceDurationClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onReduceDurationClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.increase_volume_button, "method 'onIncreaseVolumeClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onIncreaseVolumeClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.reduce_volume_button, "method 'onReduceVolumeClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onReduceVolumeClicked();
            }
        });
    }

    public void reset(T t) {
    }
}
