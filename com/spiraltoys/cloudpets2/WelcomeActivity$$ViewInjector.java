package com.spiraltoys.cloudpets2;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class WelcomeActivity$$ViewInjector<T extends WelcomeActivity> extends InteractiveToyActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (InteractiveToyActivity) target, source);
        ((View) finder.findRequiredView(source, R.id.button_log_in, "method 'onLogInClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onLogInClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.button_create_account, "method 'onCreateAccountClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onCreateAccountClicked();
            }
        });
    }

    public void reset(T target) {
        super.reset((InteractiveToyActivity) target);
    }
}
