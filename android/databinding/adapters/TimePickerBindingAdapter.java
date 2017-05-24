package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.TimePicker;

@BindingMethods({@BindingMethod(attribute = "android:onTimeChanged", method = "setOnTimeChangedListener", type = TimePicker.class)})
public class TimePickerBindingAdapter {
}
