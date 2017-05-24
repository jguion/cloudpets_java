package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class SelectBluetoothToyCompleteConnectionFragment$$ViewInjector<T extends SelectBluetoothToyCompleteConnectionFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        target.mStartedTitleContainer = (View) finder.findRequiredView(source, R.id.title_container_complete_connection_started, "field 'mStartedTitleContainer'");
        target.mFinishedTitleContainer = (View) finder.findRequiredView(source, R.id.title_container_complete_connection_finished, "field 'mFinishedTitleContainer'");
        View view = (View) finder.findRequiredView(source, R.id.complete_connection_button, "field 'mCompleteConnectionButton' and method 'onCompleteConnectionButtonClicked'");
        target.mCompleteConnectionButton = (Button) finder.castView(view, R.id.complete_connection_button, "field 'mCompleteConnectionButton'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onCompleteConnectionButtonClicked(p0);
            }
        });
        target.mConfiguredPetAvatar = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.connection_indicator, "field 'mConfiguredPetAvatar'"), R.id.connection_indicator, "field 'mConfiguredPetAvatar'");
        target.mConnectionText = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.connection_text, "field 'mConnectionText'"), R.id.connection_text, "field 'mConnectionText'");
        target.mProgress2 = (Button) finder.castView((View) finder.findRequiredView(source, R.id.progress2, "field 'mProgress2'"), R.id.progress2, "field 'mProgress2'");
        target.mProgress3 = (Button) finder.castView((View) finder.findRequiredView(source, R.id.progress3, "field 'mProgress3'"), R.id.progress3, "field 'mProgress3'");
        target.mProgress4 = (Button) finder.castView((View) finder.findRequiredView(source, R.id.progress4, "field 'mProgress4'"), R.id.progress4, "field 'mProgress4'");
        target.mProgress5 = (Button) finder.castView((View) finder.findRequiredView(source, R.id.progress5, "field 'mProgress5'"), R.id.progress5, "field 'mProgress5'");
    }

    public void reset(T target) {
        target.mStartedTitleContainer = null;
        target.mFinishedTitleContainer = null;
        target.mCompleteConnectionButton = null;
        target.mConfiguredPetAvatar = null;
        target.mConnectionText = null;
        target.mProgress2 = null;
        target.mProgress3 = null;
        target.mProgress4 = null;
        target.mProgress5 = null;
    }
}
