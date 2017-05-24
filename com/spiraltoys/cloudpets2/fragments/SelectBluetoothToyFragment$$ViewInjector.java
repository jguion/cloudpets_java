package com.spiraltoys.cloudpets2.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class SelectBluetoothToyFragment$$ViewInjector<T extends SelectBluetoothToyFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.next_toy_button, "field 'mNextToyButton' and method 'onNextToyButtonClicked'");
        target.mNextToyButton = (Button) finder.castView(view, R.id.next_toy_button, "field 'mNextToyButton'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onNextToyButtonClicked(p0);
            }
        });
        view = (View) finder.findRequiredView(source, R.id.use_toy_button, "field 'mUseToyButton' and method 'onUseAsSharedToyClicked'");
        target.mUseToyButton = (Button) finder.castView(view, R.id.use_toy_button, "field 'mUseToyButton'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onUseAsSharedToyClicked(p0);
            }
        });
        target.mConfiguredPetAvatar = (ImageView) finder.castView((View) finder.findRequiredView(source, R.id.connection_indicator, "field 'mConfiguredPetAvatar'"), R.id.connection_indicator, "field 'mConfiguredPetAvatar'");
        target.mConnectionText = (TextView) finder.castView((View) finder.findRequiredView(source, R.id.connection_text, "field 'mConnectionText'"), R.id.connection_text, "field 'mConnectionText'");
        target.mScanningTitleContainer = (View) finder.findRequiredView(source, R.id.title_container_scanning, "field 'mScanningTitleContainer'");
        target.mConnectedTitleContainer = (View) finder.findRequiredView(source, R.id.title_container_connected, "field 'mConnectedTitleContainer'");
        target.mProgress2 = (View) finder.findRequiredView(source, R.id.progress2, "field 'mProgress2'");
        target.mProgress3 = (View) finder.findRequiredView(source, R.id.progress3, "field 'mProgress3'");
    }

    public void reset(T target) {
        target.mNextToyButton = null;
        target.mUseToyButton = null;
        target.mConfiguredPetAvatar = null;
        target.mConnectionText = null;
        target.mScanningTitleContainer = null;
        target.mConnectedTitleContainer = null;
        target.mProgress2 = null;
        target.mProgress3 = null;
    }
}
