package com.spiraltoys.cloudpets2.fragments;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class CreateOrEditChildProfileFragment$$ViewInjector<T extends CreateOrEditChildProfileFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.profile_photo, "field 'mProfilePhoto' and method 'onSetPhotoClicked'");
        target.mProfilePhoto = (ImageView) finder.castView(view, R.id.profile_photo, "field 'mProfilePhoto'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onSetPhotoClicked();
            }
        });
        view = (View) finder.findRequiredView(source, R.id.birthday_month_spinner, "field 'mBirthMonthSpinner' and method 'onMonthSelected'");
        target.mBirthMonthSpinner = (Spinner) finder.castView(view, R.id.birthday_month_spinner, "field 'mBirthMonthSpinner'");
        ((AdapterView) view).setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View p1, int p2, long p3) {
                target.onMonthSelected(p2);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        view = (View) finder.findRequiredView(source, R.id.birthday_day_spinner, "field 'mBirthDayOfMonthSpinner' and method 'onDayOfMonthSelected'");
        target.mBirthDayOfMonthSpinner = (Spinner) finder.castView(view, R.id.birthday_day_spinner, "field 'mBirthDayOfMonthSpinner'");
        ((AdapterView) view).setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View p1, int p2, long p3) {
                target.onDayOfMonthSelected(p2);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        view = (View) finder.findRequiredView(source, R.id.display_name, "field 'mDisplayNameTextField' and method 'onChildNameChanged'");
        target.mDisplayNameTextField = (EditText) finder.castView(view, R.id.display_name, "field 'mDisplayNameTextField'");
        ((TextView) view).addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                target.onChildNameChanged(p0);
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
            }
        });
        view = (View) finder.findRequiredView(source, R.id.username, "field 'mUsernameTextField' and method 'onUsernameChanged'");
        target.mUsernameTextField = (EditText) finder.castView(view, R.id.username, "field 'mUsernameTextField'");
        ((TextView) view).addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                target.onUsernameChanged(p0);
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
            }
        });
        target.mConfiguredPetName = (TextView) finder.castView((View) finder.findOptionalView(source, R.id.configured_cloud_pet_name, null), R.id.configured_cloud_pet_name, "field 'mConfiguredPetName'");
        target.mConfiguredPetAvatar = (ImageView) finder.castView((View) finder.findOptionalView(source, R.id.configured_cloud_pet_avatar, null), R.id.configured_cloud_pet_avatar, "field 'mConfiguredPetAvatar'");
        target.mProfileBear = (ImageView) finder.castView((View) finder.findOptionalView(source, R.id.profile_bear, null), R.id.profile_bear, "field 'mProfileBear'");
        target.mTitleText = (TextView) finder.castView((View) finder.findOptionalView(source, R.id.title_text, null), R.id.title_text, "field 'mTitleText'");
        ((View) finder.findRequiredView(source, R.id.username_help, "method 'onUsernameHelpButtonClicked'")).setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onUsernameHelpButtonClicked();
            }
        });
        view = (View) finder.findOptionalView(source, R.id.edit_cloudpet_button, null);
        if (view != null) {
            view.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View p0) {
                    target.onEditCloudPetClicked();
                }
            });
        }
    }

    public void reset(T target) {
        target.mProfilePhoto = null;
        target.mBirthMonthSpinner = null;
        target.mBirthDayOfMonthSpinner = null;
        target.mDisplayNameTextField = null;
        target.mUsernameTextField = null;
        target.mConfiguredPetName = null;
        target.mConfiguredPetAvatar = null;
        target.mProfileBear = null;
        target.mTitleText = null;
    }
}
