package com.spiraltoys.cloudpets2.fragments;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import com.spiraltoys.cloudpets2.free.R;

public class SelectPetAvatarFragment$$ViewInjector<T extends SelectPetAvatarFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.pet_nickname, "field 'mNickNameText' and method 'onPetNicknameChanged'");
        target.mNickNameText = (EditText) finder.castView(view, R.id.pet_nickname, "field 'mNickNameText'");
        ((TextView) view).addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                target.onPetNicknameChanged(p0);
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
            }
        });
        view = (View) finder.findRequiredView(source, R.id.radio_bear, "field 'mBearButton' and method 'onBearChecked'");
        target.mBearButton = (RadioButton) finder.castView(view, R.id.radio_bear, "field 'mBearButton'");
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onBearChecked(p1);
            }
        });
        view = (View) finder.findRequiredView(source, R.id.radio_cat, "field 'mCatButton' and method 'onCatChecked'");
        target.mCatButton = (RadioButton) finder.castView(view, R.id.radio_cat, "field 'mCatButton'");
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onCatChecked(p1);
            }
        });
        view = (View) finder.findRequiredView(source, R.id.radio_dog, "field 'mDogButton' and method 'onDogChecked'");
        target.mDogButton = (RadioButton) finder.castView(view, R.id.radio_dog, "field 'mDogButton'");
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onDogChecked(p1);
            }
        });
        view = (View) finder.findRequiredView(source, R.id.radio_bunny, "field 'mBunnyButton' and method 'onBunnyChecked'");
        target.mBunnyButton = (RadioButton) finder.castView(view, R.id.radio_bunny, "field 'mBunnyButton'");
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onBunnyChecked(p1);
            }
        });
        view = (View) finder.findRequiredView(source, R.id.radio_unicorn, "field 'mUnicornButton' and method 'onUnicornChecked'");
        target.mUnicornButton = (RadioButton) finder.castView(view, R.id.radio_unicorn, "field 'mUnicornButton'");
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target.onUnicornChecked(p1);
            }
        });
    }

    public void reset(T target) {
        target.mNickNameText = null;
        target.mBearButton = null;
        target.mCatButton = null;
        target.mDogButton = null;
        target.mBunnyButton = null;
        target.mUnicornButton = null;
    }
}
