package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class FriendRequestsFragment$$ViewInjector<T extends FriendRequestsFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        ((View) finder.findRequiredView(source, R.id.invite_friend_button, "method 'onInviteFriendClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onInviteFriendClicked();
            }
        });
    }

    public void reset(T t) {
    }
}
