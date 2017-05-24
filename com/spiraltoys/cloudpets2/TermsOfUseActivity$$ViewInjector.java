package com.spiraltoys.cloudpets2;

import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class TermsOfUseActivity$$ViewInjector<T extends TermsOfUseActivity> extends BaseActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (BaseActivity) target, source);
        target.mButtonBar = (ViewGroup) finder.castView((View) finder.findRequiredView(source, R.id.button_bar, "field 'mButtonBar'"), R.id.button_bar, "field 'mButtonBar'");
        ((View) finder.findRequiredView(source, R.id.agree_button, "method 'agreeClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.agreeClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.disagree_button, "method 'disagreeClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.disagreeClicked();
            }
        });
    }

    public void reset(T target) {
        super.reset((BaseActivity) target);
        target.mButtonBar = null;
    }
}
