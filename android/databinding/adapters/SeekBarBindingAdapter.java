package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SeekBarBindingAdapter {

    public interface OnProgressChanged {
        void onProgressChanged(SeekBar seekBar, int i, boolean z);
    }

    public interface OnStartTrackingTouch {
        void onStartTrackingTouch(SeekBar seekBar);
    }

    public interface OnStopTrackingTouch {
        void onStopTrackingTouch(SeekBar seekBar);
    }

    @BindingAdapter({"android:onProgressChanged"})
    public static void setListener(SeekBar view, OnProgressChanged listener) {
        setListener(view, null, null, listener);
    }

    @BindingAdapter({"android:onStartTrackingTouch"})
    public static void setListener(SeekBar view, OnStartTrackingTouch listener) {
        setListener(view, listener, null, null);
    }

    @BindingAdapter({"android:onStopTrackingTouch"})
    public static void setListener(SeekBar view, OnStopTrackingTouch listener) {
        setListener(view, null, listener, null);
    }

    @BindingAdapter({"android:onStartTrackingTouch", "android:onStopTrackingTouch"})
    public static void setListener(SeekBar view, OnStartTrackingTouch start, OnStopTrackingTouch stop) {
        setListener(view, start, stop, null);
    }

    @BindingAdapter({"android:onStartTrackingTouch", "android:onProgressChanged"})
    public static void setListener(SeekBar view, OnStartTrackingTouch start, OnProgressChanged progressChanged) {
        setListener(view, start, null, progressChanged);
    }

    @BindingAdapter({"android:onStopTrackingTouch", "android:onProgressChanged"})
    public static void setListener(SeekBar view, OnStopTrackingTouch stop, OnProgressChanged progressChanged) {
        setListener(view, null, stop, progressChanged);
    }

    @BindingAdapter({"android:onStartTrackingTouch", "android:onStopTrackingTouch", "android:onProgressChanged"})
    public static void setListener(SeekBar view, final OnStartTrackingTouch start, final OnStopTrackingTouch stop, final OnProgressChanged progressChanged) {
        if (start == null && stop == null && progressChanged == null) {
            view.setOnSeekBarChangeListener(null);
        } else {
            view.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (progressChanged != null) {
                        progressChanged.onProgressChanged(seekBar, progress, fromUser);
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    if (start != null) {
                        start.onStartTrackingTouch(seekBar);
                    }
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (stop != null) {
                        stop.onStopTrackingTouch(seekBar);
                    }
                }
            });
        }
    }
}
