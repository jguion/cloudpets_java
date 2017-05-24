package com.spiraltoys.cloudpets2;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class AdultVoiceMessageActivity$$ViewInjector<T extends AdultVoiceMessageActivity> extends BaseActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (BaseActivity) target, source);
        ((View) finder.findRequiredView(source, R.id.approve_voice_message_button, "method 'onApproveClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onApproveClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.delete_voice_message_button, "method 'onDeleteClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onDeleteClicked();
            }
        });
    }

    public void reset(T target) {
        super.reset((BaseActivity) target);
    }
}
