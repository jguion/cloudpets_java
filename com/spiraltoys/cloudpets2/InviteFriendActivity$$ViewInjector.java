package com.spiraltoys.cloudpets2;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class InviteFriendActivity$$ViewInjector<T extends InviteFriendActivity> extends BaseActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (BaseActivity) target, source);
        ((View) finder.findRequiredView(source, R.id.invite_friend_button, "method 'onInviteFriend'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onInviteFriend();
            }
        });
    }

    public void reset(T target) {
        super.reset((BaseActivity) target);
    }
}
