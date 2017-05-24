package com.spiraltoys.cloudpets2;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class PlayStoryActivity$$ViewInjector<T extends PlayStoryActivity> extends BaseActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (BaseActivity) target, source);
        ((View) finder.findRequiredView(source, R.id.previous_page_button, "method 'onPreviousPageClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onPreviousPageClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.next_page_button, "method 'onNextPageClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onNextPageClicked();
            }
        });
    }

    public void reset(T target) {
        super.reset((BaseActivity) target);
    }
}
