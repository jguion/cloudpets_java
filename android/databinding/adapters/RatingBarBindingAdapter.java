package android.databinding.adapters;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.RatingBar;

@BindingMethods({@BindingMethod(attribute = "android:onRatingChanged", method = "setOnRatingBarChangeListener", type = RatingBar.class)})
public class RatingBarBindingAdapter {
}
