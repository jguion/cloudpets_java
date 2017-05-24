package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.CalendarView;

@BindingMethods({@BindingMethod(attribute = "android:onSelectedDayChange", method = "setOnDateChangeListener", type = CalendarView.class)})
public class CalendarViewBindingAdapter {
}
