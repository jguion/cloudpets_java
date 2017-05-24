package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.RadioGroup;

@BindingMethods({@BindingMethod(attribute = "android:checkedButton", method = "check", type = RadioGroup.class), @BindingMethod(attribute = "android:onCheckedChanged", method = "setOnCheckedChangeListener", type = RadioGroup.class)})
public class RadioGroupBindingAdapter {
}
