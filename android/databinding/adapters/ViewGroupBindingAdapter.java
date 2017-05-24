package android.databinding.adapters;

import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

@BindingMethods({@BindingMethod(attribute = "android:alwaysDrawnWithCache", method = "setAlwaysDrawnWithCacheEnabled", type = ViewGroup.class), @BindingMethod(attribute = "android:animationCache", method = "setAnimationCacheEnabled", type = ViewGroup.class), @BindingMethod(attribute = "android:splitMotionEvents", method = "setMotionEventSplittingEnabled", type = ViewGroup.class)})
public class ViewGroupBindingAdapter {

    public interface OnAnimationEnd {
        void onAnimationEnd(Animation animation);
    }

    public interface OnAnimationRepeat {
        void onAnimationRepeat(Animation animation);
    }

    public interface OnAnimationStart {
        void onAnimationStart(Animation animation);
    }

    public interface OnChildViewAdded {
        void onChildViewAdded(View view, View view2);
    }

    public interface OnChildViewRemoved {
        void onChildViewRemoved(View view, View view2);
    }

    @BindingAdapter({"android:animateLayoutChanges"})
    @TargetApi(11)
    public static void setAnimateLayoutChanges(ViewGroup view, boolean animate) {
        if (animate) {
            view.setLayoutTransition(new LayoutTransition());
        } else {
            view.setLayoutTransition(null);
        }
    }

    @BindingAdapter({"android:onChildViewAdded"})
    public static void setListener(ViewGroup view, OnChildViewAdded listener) {
        setListener(view, listener, null);
    }

    @BindingAdapter({"android:onChildViewRemoved"})
    public static void setListener(ViewGroup view, OnChildViewRemoved listener) {
        setListener(view, null, listener);
    }

    @BindingAdapter({"android:onChildViewAdded", "android:onChildViewRemoved"})
    public static void setListener(ViewGroup view, final OnChildViewAdded added, final OnChildViewRemoved removed) {
        if (added == null && removed == null) {
            view.setOnHierarchyChangeListener(null);
        } else {
            view.setOnHierarchyChangeListener(new OnHierarchyChangeListener() {
                public void onChildViewAdded(View parent, View child) {
                    if (added != null) {
                        added.onChildViewAdded(parent, child);
                    }
                }

                public void onChildViewRemoved(View parent, View child) {
                    if (removed != null) {
                        removed.onChildViewRemoved(parent, child);
                    }
                }
            });
        }
    }

    @BindingAdapter({"android:onAnimationStart", "android:onAnimationEnd", "android:onAnimationRepeat"})
    public static void setListener(ViewGroup view, final OnAnimationStart start, final OnAnimationEnd end, final OnAnimationRepeat repeat) {
        if (start == null && end == null && repeat == null) {
            view.setLayoutAnimationListener(null);
        } else {
            view.setLayoutAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    if (start != null) {
                        start.onAnimationStart(animation);
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    if (end != null) {
                        end.onAnimationEnd(animation);
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                    if (repeat != null) {
                        repeat.onAnimationRepeat(animation);
                    }
                }
            });
        }
    }

    @BindingAdapter({"android:onAnimationStart", "android:onAnimationEnd"})
    public static void setListener(ViewGroup view, OnAnimationStart start, OnAnimationEnd end) {
        setListener(view, start, end, null);
    }

    @BindingAdapter({"android:onAnimationEnd", "android:onAnimationRepeat"})
    public static void setListener(ViewGroup view, OnAnimationEnd end, OnAnimationRepeat repeat) {
        setListener(view, null, end, repeat);
    }

    @BindingAdapter({"android:onAnimationStart", "android:onAnimationRepeat"})
    public static void setListener(ViewGroup view, OnAnimationStart start, OnAnimationRepeat repeat) {
        setListener(view, start, null, repeat);
    }

    @BindingAdapter({"android:onAnimationStart"})
    public static void setListener(ViewGroup view, OnAnimationStart start) {
        setListener(view, start, null, null);
    }

    @BindingAdapter({"android:onAnimationEnd"})
    public static void setListener(ViewGroup view, OnAnimationEnd end) {
        setListener(view, null, end, null);
    }

    @BindingAdapter({"android:onAnimationRepeat"})
    public static void setListener(ViewGroup view, OnAnimationRepeat repeat) {
        setListener(view, null, null, repeat);
    }
}
