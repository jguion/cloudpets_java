package android.databinding.adapters;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.os.Build.VERSION;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView.OnSuggestionListener;

@BindingMethods({@BindingMethod(attribute = "android:onQueryTextFocusChange", method = "setOnQueryTextFocusChangeListener", type = SearchView.class), @BindingMethod(attribute = "android:onSearchClick", method = "setOnSearchClickListener", type = SearchView.class), @BindingMethod(attribute = "android:onClose", method = "setOnCloseListener", type = SearchView.class)})
public class SearchViewBindingAdapter {

    @TargetApi(11)
    public interface OnQueryTextChange {
        boolean onQueryTextChange(String str);
    }

    @TargetApi(11)
    public interface OnQueryTextSubmit {
        boolean onQueryTextSubmit(String str);
    }

    @TargetApi(11)
    public interface OnSuggestionClick {
        boolean onSuggestionClick(int i);
    }

    @TargetApi(11)
    public interface OnSuggestionSelect {
        boolean onSuggestionSelect(int i);
    }

    @BindingAdapter({"android:onQueryTextChange"})
    public static void setListener(SearchView view, OnQueryTextChange listener) {
        setListener(view, null, listener);
    }

    @BindingAdapter({"android:onQueryTextSubmit"})
    public static void setListener(SearchView view, OnQueryTextSubmit listener) {
        setListener(view, listener, null);
    }

    @BindingAdapter({"android:onQueryTextSubmit", "android:onQueryTextChange"})
    public static void setListener(SearchView view, final OnQueryTextSubmit submit, final OnQueryTextChange change) {
        if (VERSION.SDK_INT < 11) {
            return;
        }
        if (submit == null && change == null) {
            view.setOnQueryTextListener(null);
        } else {
            view.setOnQueryTextListener(new OnQueryTextListener() {
                public boolean onQueryTextSubmit(String query) {
                    if (submit != null) {
                        return submit.onQueryTextSubmit(query);
                    }
                    return false;
                }

                public boolean onQueryTextChange(String newText) {
                    if (change != null) {
                        return change.onQueryTextChange(newText);
                    }
                    return false;
                }
            });
        }
    }

    @BindingAdapter({"android:onSuggestionClick"})
    public static void setListener(SearchView view, OnSuggestionClick listener) {
        setListener(view, null, listener);
    }

    @BindingAdapter({"android:onSuggestionSelect"})
    public static void setListener(SearchView view, OnSuggestionSelect listener) {
        setListener(view, listener, null);
    }

    @BindingAdapter({"android:onSuggestionSelect", "android:onSuggestionClick"})
    public static void setListener(SearchView view, final OnSuggestionSelect submit, final OnSuggestionClick change) {
        if (VERSION.SDK_INT < 11) {
            return;
        }
        if (submit == null && change == null) {
            view.setOnSuggestionListener(null);
        } else {
            view.setOnSuggestionListener(new OnSuggestionListener() {
                public boolean onSuggestionSelect(int position) {
                    if (submit != null) {
                        return submit.onSuggestionSelect(position);
                    }
                    return false;
                }

                public boolean onSuggestionClick(int position) {
                    if (change != null) {
                        return change.onSuggestionClick(position);
                    }
                    return false;
                }
            });
        }
    }
}
