package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.v7.widget.SwitchCompat;

@BindingMethods({@BindingMethod(attribute = "android:thumb", method = "setThumbDrawable", type = SwitchCompat.class), @BindingMethod(attribute = "android:track", method = "setTrackDrawable", type = SwitchCompat.class)})
public class SwitchCompatBindingAdapter {
    @BindingAdapter({"android:switchTextAppearance"})
    public static void setSwitchTextAppearance(SwitchCompat view, int value) {
        view.setSwitchTextAppearance(null, value);
    }
}