package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

@BindingMethods({@BindingMethod(attribute = "android:onItemClick", method = "setOnItemClickListener", type = AdapterView.class), @BindingMethod(attribute = "android:onItemLongClick", method = "setOnItemLongClickListener", type = AdapterView.class)})
public class AdapterViewBindingAdapter {

    public interface OnItemSelected {
        void onItemSelected(AdapterView<?> adapterView, View view, int i, long j);
    }

    public static class OnItemSelectedComponentListener implements OnItemSelectedListener {
        private final OnNothingSelected mNothingSelected;
        private final OnItemSelected mSelected;

        public OnItemSelectedComponentListener(OnItemSelected selected, OnNothingSelected nothingSelected) {
            this.mSelected = selected;
            this.mNothingSelected = nothingSelected;
        }

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (this.mSelected != null) {
                this.mSelected.onItemSelected(parent, view, position, id);
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            if (this.mNothingSelected != null) {
                this.mNothingSelected.onNothingSelected(parent);
            }
        }
    }

    public interface OnNothingSelected {
        void onNothingSelected(AdapterView<?> adapterView);
    }

    @BindingAdapter({"android:onItemSelected"})
    public static void setListener(AdapterView view, OnItemSelected listener) {
        setListener(view, listener, null);
    }

    @BindingAdapter({"android:onNothingSelected"})
    public static void setListener(AdapterView view, OnNothingSelected listener) {
        setListener(view, null, listener);
    }

    @BindingAdapter({"android:onItemSelected", "android:onNothingSelected"})
    public static void setListener(AdapterView view, OnItemSelected selected, OnNothingSelected nothingSelected) {
        if (selected == null && nothingSelected == null) {
            view.setOnItemSelectedListener(null);
        } else {
            view.setOnItemSelectedListener(new OnItemSelectedComponentListener(selected, nothingSelected));
        }
    }
}
