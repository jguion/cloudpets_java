package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.NumberPicker;

@BindingMethods({@BindingMethod(attribute = "android:format", method = "setFormatter", type = NumberPicker.class), @BindingMethod(attribute = "android:onScrollStateChange", method = "setOnScrollListener", type = NumberPicker.class), @BindingMethod(attribute = "android:onValueChange", method = "setOnValueChangedListener", type = NumberPicker.class)})
public class NumberPickerBindingAdapter {
}
