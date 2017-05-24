package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class ChildRecordedMessageActionsFragment$$ViewInjector<T extends ChildRecordedMessageActionsFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        ((View) finder.findRequiredView(source, R.id.select_recipients_button, "method 'onSelectRecipientsClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onSelectRecipientsClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.delete_button, "method 'onDeleteClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onDeleteClicked();
            }
        });
    }

    public void reset(T t) {
    }
}
