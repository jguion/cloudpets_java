package com.spiraltoys.cloudpets2;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class ChildDashboardActivity$$ViewInjector<T extends ChildDashboardActivity> extends InteractiveToyActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (InteractiveToyActivity) target, source);
        ((View) finder.findRequiredView(source, R.id.show_messages_button, "method 'onShowMessagesClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onShowMessagesClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.show_lullabies_button, "method 'onShowLullabiesClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onShowLullabiesClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.show_games_button, "method 'onShowGamesClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onShowGamesClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.show_stories_button, "method 'onShowStoriesClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onShowStoriesClicked();
            }
        });
        ((View) finder.findRequiredView(source, R.id.child_dashboard_help, "method 'onHelpClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onHelpClicked();
            }
        });
    }

    public void reset(T target) {
        super.reset((InteractiveToyActivity) target);
    }
}
