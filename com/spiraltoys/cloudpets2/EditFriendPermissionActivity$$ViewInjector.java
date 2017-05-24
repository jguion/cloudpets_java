package com.spiraltoys.cloudpets2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class EditFriendPermissionActivity$$ViewInjector<T extends EditFriendPermissionActivity> extends BaseActivity$$ViewInjector<T> {
    public void inject(Finder finder, final T target, Object source) {
        super.inject(finder, (BaseActivity) target, source);
        target.mProfilePhoto = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.profile_photo, "field 'mProfilePhoto'"), R.id.profile_photo, "field 'mProfilePhoto'");
        target.mProfileName = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.profile_name, "field 'mProfileName'"), R.id.profile_name, "field 'mProfileName'");
        ((View) finder.findRequiredView(source, R.id.save_permissions_button, "method 'savePermissionsClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.savePermissionsClicked();
            }
        });
    }

    public void reset(T target) {
        super.reset((BaseActivity) target);
        target.mProfilePhoto = null;
        target.mProfileName = null;
    }
}
