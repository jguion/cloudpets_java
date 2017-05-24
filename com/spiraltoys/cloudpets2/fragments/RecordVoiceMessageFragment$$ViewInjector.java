package com.spiraltoys.cloudpets2.fragments;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import com.spiraltoys.cloudpets2.free.R;

public class RecordVoiceMessageFragment$$ViewInjector<T extends RecordVoiceMessageFragment> implements Injector<T> {
    public void inject(Finder finder, final T target, Object source) {
        View view = (View) finder.findRequiredView(source, R.id.record_message_button, "method 'onRecordClicked' and method 'onRecordTouch'");
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onRecordClicked();
            }
        });
        view.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View p0, MotionEvent p1) {
                return target.onRecordTouch(p0, p1);
            }
        });
    }

    public void reset(T t) {
    }
}
