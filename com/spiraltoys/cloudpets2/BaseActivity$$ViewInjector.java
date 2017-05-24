package com.spiraltoys.cloudpets2;

import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class BaseActivity$$ViewInjector<T extends BaseActivity> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        target.mStatusBarSpace = (View) finder.findOptionalView(source, R.id.status_bar_spacer, null);
        target.mToolbar = (Toolbar) finder.castView((View) finder.findOptionalView(source, R.id.toolbar, null), R.id.toolbar, "field 'mToolbar'");
        View view = (View) finder.findOptionalView(source, R.id.buy_a_cloudpet_button, null);
        if (view != null) {
            view.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View p0) {
                    target.onBuyNowClicked();
                }
            });
        }
        view = (View) finder.findOptionalView(source, R.id.tutorial_button, null);
        if (view != null) {
            view.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View p0) {
                    target.onTutorialClicked();
                }
            });
        }
    }

    public void reset(T target) {
        target.mStatusBarSpace = null;
        target.mToolbar = null;
    }
}
