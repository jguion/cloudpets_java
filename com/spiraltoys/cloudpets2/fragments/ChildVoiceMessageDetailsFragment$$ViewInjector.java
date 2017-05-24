package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class ChildVoiceMessageDetailsFragment$$ViewInjector<T extends ChildVoiceMessageDetailsFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        ((View) finder.findRequiredView(source, R.id.push_to_toy_button, "method 'onPushToToyClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onPushToToyClicked(p0);
            }
        });
        ((View) finder.findRequiredView(source, R.id.delete_voice_message_button, "method 'onDeleteClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onDeleteClicked(p0);
            }
        });
    }

    public void reset(T t) {
    }
}
