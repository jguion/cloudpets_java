package com.spiraltoys.cloudpets2;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class AdultDashboardActivity$$ViewInjector<T extends AdultDashboardActivity> extends BaseActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (BaseActivity) target, source);
        ((View) finder.findRequiredView(source, R.id.record_message_button, "method 'onRecordMessageClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onRecordMessageClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.show_messages_button, "method 'onShowMessagesClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onShowMessagesClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.manage_profiles_button, "method 'onManageProfilesClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onManageProfilesClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.manage_settings_button, "method 'onManageSettingsClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onManageSettingsClicked();
            }
        });
    }

    public void reset(T target) {
        super.reset((BaseActivity) target);
    }
}
