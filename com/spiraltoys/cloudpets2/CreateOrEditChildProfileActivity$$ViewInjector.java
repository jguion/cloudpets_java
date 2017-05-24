package com.spiraltoys.cloudpets2;

import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class CreateOrEditChildProfileActivity$$ViewInjector<T extends CreateOrEditChildProfileActivity> extends BaseActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (BaseActivity) target, source);
        View view = (View) finder.findRequiredView(source, R.id.next_button, "field 'mNextButton' and method 'onNextClicked'");
        target.mNextButton = (Button) finder.castView(view, R.id.next_button, "field 'mNextButton'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onNextClicked();
            }
        });
        view = (View) finder.findRequiredView(source, R.id.create_child_profile_button, "field 'mSaveButton' and method 'onSaveClicked'");
        target.mSaveButton = (Button) finder.castView(view, R.id.create_child_profile_button, "field 'mSaveButton'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onSaveClicked();
            }
        });
        target.mSaveDeleteButtons = (View) finder.findRequiredView(source, R.id.child_profile_buttons, "field 'mSaveDeleteButtons'");
    }

    public void reset(T target) {
        super.reset((BaseActivity) target);
        target.mNextButton = null;
        target.mSaveButton = null;
        target.mSaveDeleteButtons = null;
    }
}
