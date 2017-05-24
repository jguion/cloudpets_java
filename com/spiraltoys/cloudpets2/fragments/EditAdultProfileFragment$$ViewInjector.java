package com.spiraltoys.cloudpets2.fragments;

import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class EditAdultProfileFragment$$ViewInjector<T extends EditAdultProfileFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.profile_photo, "field 'mProfilePhoto' and method 'onSetPhotoClicked'");
        target.mProfilePhoto = (ImageView) finder.castView(view, R.id.profile_photo, "field 'mProfilePhoto'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onSetPhotoClicked();
            }
        });
        view = (View) finder.findRequiredView(source, R.id.display_name, "field 'mDisplayNameTextField' and method 'onDisplayNameChanged'");
        target.mDisplayNameTextField = (EditText) finder.castView(view, R.id.display_name, "field 'mDisplayNameTextField'");
        ((TextView) view).addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                target.onDisplayNameChanged(p0);
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
            }
        });
        target.mEmailTextField = (EditText) finder.castView((View) finder.findRequiredView(source, R.id.email, "field 'mEmailTextField'"), R.id.email, "field 'mEmailTextField'");
        target.mPasswordResetContainerKitKat = (CardView) finder.castView((View) finder.findRequiredView(source, R.id.password_reset_kit_kat_container, "field 'mPasswordResetContainerKitKat'"), R.id.password_reset_kit_kat_container, "field 'mPasswordResetContainerKitKat'");
        view = (View) finder.findRequiredView(source, R.id.password_reset_lollipop, "field 'mPasswordResetLollipop' and method 'onPasswordRestClickedLollipop'");
        target.mPasswordResetLollipop = (Button) finder.castView(view, R.id.password_reset_lollipop, "field 'mPasswordResetLollipop'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onPasswordRestClickedLollipop();
            }
        });
        ((View) finder.findRequiredView(source, R.id.password_reset_kit_kat, "method 'onPasswordRestClickedKitKat'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onPasswordRestClickedKitKat();
            }
        });
    }

    public void reset(T target) {
        target.mProfilePhoto = null;
        target.mDisplayNameTextField = null;
        target.mEmailTextField = null;
        target.mPasswordResetContainerKitKat = null;
        target.mPasswordResetLollipop = null;
    }
}
