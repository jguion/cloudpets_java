package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

@BindingMethods({@BindingMethod(attribute = "android:listSelector", method = "setSelector", type = AbsListView.class), @BindingMethod(attribute = "android:scrollingCache", method = "setScrollingCacheEnabled", type = AbsListView.class), @BindingMethod(attribute = "android:smoothScrollbar", method = "setSmoothScrollbarEnabled", type = AbsListView.class), @BindingMethod(attribute = "android:onMovedToScrapHeap", method = "setRecyclerListener", type = AbsListView.class)})
public class AbsListViewBindingAdapter {

    public interface OnScroll {
        void onScroll(AbsListView absListView, int i, int i2, int i3);
    }

    public interface OnScrollStateChanged {
        void onScrollStateChanged(AbsListView absListView, int i);
    }

    @BindingAdapter({"android:onScroll"})
    public static void setOnScroll(AbsListView view, OnScroll listener) {
        setOnScroll(view, listener, null);
    }

    @BindingAdapter({"android:onScrollStateChanged"})
    public static void setOnScroll(AbsListView view, OnScrollStateChanged listener) {
        setOnScroll(view, null, listener);
    }

    @BindingAdapter({"android:onScroll", "android:onScrollStateChanged"})
    public static void setOnScroll(AbsListView view, final OnScroll scrollListener, final OnScrollStateChanged scrollStateListener) {
        view.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollStateListener != null) {
                    scrollStateListener.onScrollStateChanged(view, scrollState);
                }
            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (scrollListener != null) {
                    scrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }
            }
        });
    }
}
