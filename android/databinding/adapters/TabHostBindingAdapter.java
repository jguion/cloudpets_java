package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.TabHost;

@BindingMethods({@BindingMethod(attribute = "android:onTabChanged", method = "setOnTabChangedListener", type = TabHost.class)})
public class TabHostBindingAdapter {
}
