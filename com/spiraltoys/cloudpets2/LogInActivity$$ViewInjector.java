package com.spiraltoys.cloudpets2;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class LogInActivity$$ViewInjector<T extends LogInActivity> extends BaseActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (BaseActivity) target, source);
        ((View) finder.findRequiredView(source, R.id.button_log_in, "method 'onLogInClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onLogInClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.reset_password_button, "method 'onPasswordResetClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onPasswordResetClicked();
            }
        });
    }

    public void reset(T target) {
        super.reset((BaseActivity) target);
    }
}
