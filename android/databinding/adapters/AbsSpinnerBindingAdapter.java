package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.widget.AbsSpinner;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

public class AbsSpinnerBindingAdapter {
    @BindingAdapter({"android:entries"})
    public static void setEntries(AbsSpinner view, CharSequence[] entries) {
        if (entries != null) {
            SpinnerAdapter oldAdapter = view.getAdapter();
            boolean changed = true;
            if (oldAdapter != null && oldAdapter.getCount() == entries.length) {
                changed = false;
                for (int i = 0; i < entries.length; i++) {
                    if (!entries[i].equals(oldAdapter.getItem(i))) {
                        changed = true;
                        break;
                    }
                }
            }
            if (changed) {
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter(view.getContext(), 17367048, entries);
                adapter.setDropDownViewResource(17367049);
                view.setAdapter(adapter);
                return;
            }
            return;
        }
        view.setAdapter(null);
    }
}
