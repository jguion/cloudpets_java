package android.databinding.adapters;

import android.annotation.TargetApi;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.View.OnLongClickListener;
import com.android.databinding.library.baseAdapters.R;

@BindingMethods({@BindingMethod(attribute = "android:backgroundTint", method = "setBackgroundTintList", type = View.class), @BindingMethod(attribute = "android:fadeScrollbars", method = "setScrollbarFadingEnabled", type = View.class), @BindingMethod(attribute = "android:getOutline", method = "setOutlineProvider", type = View.class), @BindingMethod(attribute = "android:nextFocusForward", method = "setNextFocusForwardId", type = View.class), @BindingMethod(attribute = "android:nextFocusLeft", method = "setNextFocusLeftId", type = View.class), @BindingMethod(attribute = "android:nextFocusRight", method = "setNextFocusRightId", type = View.class), @BindingMethod(attribute = "android:nextFocusUp", method = "setNextFocusUpId", type = View.class), @BindingMethod(attribute = "android:nextFocusDown", method = "setNextFocusDownId", type = View.class), @BindingMethod(attribute = "android:requiresFadingEdge", method = "setVerticalFadingEdgeEnabled", type = View.class), @BindingMethod(attribute = "android:scrollbarDefaultDelayBeforeFade", method = "setScrollBarDefaultDelayBeforeFade", type = View.class), @BindingMethod(attribute = "android:scrollbarFadeDuration", method = "setScrollBarFadeDuration", type = View.class), @BindingMethod(attribute = "android:scrollbarSize", method = "setScrollBarSize", type = View.class), @BindingMethod(attribute = "android:scrollbarStyle", method = "setScrollBarStyle", type = View.class), @BindingMethod(attribute = "android:transformPivotX", method = "setPivotX", type = View.class), @BindingMethod(attribute = "android:transformPivotY", method = "setPivotY", type = View.class), @BindingMethod(attribute = "android:onDrag", method = "setOnDragListener", type = View.class), @BindingMethod(attribute = "android:onClick", method = "setOnClickListener", type = View.class), @BindingMethod(attribute = "android:onApplyWindowInsets", method = "setOnApplyWindowInsetsListener", type = View.class), @BindingMethod(attribute = "android:onCreateContextMenu", method = "setOnCreateContextMenuListener", type = View.class), @BindingMethod(attribute = "android:onFocusChange", method = "setOnFocusChangeListener", type = View.class), @BindingMethod(attribute = "android:onGenericMotion", method = "setOnGenericMotionListener", type = View.class), @BindingMethod(attribute = "android:onHover", method = "setOnHoverListener", type = View.class), @BindingMethod(attribute = "android:onKey", method = "setOnKeyListener", type = View.class), @BindingMethod(attribute = "android:onLongClick", method = "setOnLongClickListener", type = View.class), @BindingMethod(attribute = "android:onSystemUiVisibilityChange", method = "setOnSystemUiVisibilityChangeListener", type = View.class), @BindingMethod(attribute = "android:onTouch", method = "setOnTouchListener", type = View.class)})
public class ViewBindingAdapter {
    public static int FADING_EDGE_HORIZONTAL = 1;
    public static int FADING_EDGE_NONE = 0;
    public static int FADING_EDGE_VERTICAL = 2;

    @TargetApi(12)
    public interface OnViewAttachedToWindow {
        void onViewAttachedToWindow(View view);
    }

    @TargetApi(12)
    public interface OnViewDetachedFromWindow {
        void onViewDetachedFromWindow(View view);
    }

    @BindingAdapter({"android:padding"})
    public static void setPadding(View view, int padding) {
        view.setPadding(padding, padding, padding, padding);
    }

    @BindingAdapter({"android:paddingBottom"})
    public static void setPaddingBottom(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), padding);
    }

    @BindingAdapter({"android:paddingEnd"})
    public static void setPaddingEnd(View view, int padding) {
        if (VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), padding, view.getPaddingBottom());
        } else {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), padding, view.getPaddingBottom());
        }
    }

    @BindingAdapter({"android:paddingLeft"})
    public static void setPaddingLeft(View view, int padding) {
        view.setPadding(padding, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    @BindingAdapter({"android:paddingRight"})
    public static void setPaddingRight(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), padding, view.getPaddingBottom());
    }

    @BindingAdapter({"android:paddingStart"})
    public static void setPaddingStart(View view, int padding) {
        if (VERSION.SDK_INT >= 17) {
            view.setPaddingRelative(padding, view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        } else {
            view.setPadding(padding, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    @BindingAdapter({"android:paddingTop"})
    public static void setPaddingTop(View view, int padding) {
        view.setPadding(view.getPaddingLeft(), padding, view.getPaddingRight(), view.getPaddingBottom());
    }

    @BindingAdapter({"android:requiresFadingEdge"})
    public static void setRequiresFadingEdge(View view, int value) {
        boolean vertical;
        boolean horizontal;
        if ((FADING_EDGE_VERTICAL & value) != 0) {
            vertical = true;
        } else {
            vertical = false;
        }
        if ((FADING_EDGE_HORIZONTAL & value) != 0) {
            horizontal = true;
        } else {
            horizontal = false;
        }
        view.setVerticalFadingEdgeEnabled(vertical);
        view.setHorizontalFadingEdgeEnabled(horizontal);
    }

    @BindingAdapter({"android:onClickListener", "android:clickable"})
    public static void setClickListener(View view, OnClickListener clickListener, boolean clickable) {
        view.setOnClickListener(clickListener);
        view.setClickable(clickable);
    }

    @BindingAdapter({"android:onClick", "android:clickable"})
    public static void setOnClick(View view, OnClickListener clickListener, boolean clickable) {
        view.setOnClickListener(clickListener);
        view.setClickable(clickable);
    }

    @BindingAdapter({"android:onLongClickListener", "android:longClickable"})
    public static void setListener(View view, OnLongClickListener clickListener, boolean clickable) {
        view.setOnLongClickListener(clickListener);
        view.setLongClickable(clickable);
    }

    @BindingAdapter({"android:onLongClick", "android:longClickable"})
    public static void setOnLongClick(View view, OnLongClickListener clickListener, boolean clickable) {
        view.setOnLongClickListener(clickListener);
        view.setLongClickable(clickable);
    }

    @BindingAdapter({"android:onViewAttachedToWindow"})
    public static void setListener(View view, OnViewAttachedToWindow attached) {
        setListener(view, null, attached);
    }

    @BindingAdapter({"android:onViewDetachedFromWindow"})
    public static void setListener(View view, OnViewDetachedFromWindow detached) {
        setListener(view, detached, null);
    }

    @BindingAdapter({"android:onViewDetachedFromWindow", "android:onViewAttachedToWindow"})
    public static void setListener(View view, final OnViewDetachedFromWindow detach, final OnViewAttachedToWindow attach) {
        if (VERSION.SDK_INT >= 12) {
            OnAttachStateChangeListener newListener;
            if (detach == null && attach == null) {
                newListener = null;
            } else {
                newListener = new OnAttachStateChangeListener() {
                    public void onViewAttachedToWindow(View v) {
                        if (attach != null) {
                            attach.onViewAttachedToWindow(v);
                        }
                    }

                    public void onViewDetachedFromWindow(View v) {
                        if (detach != null) {
                            detach.onViewDetachedFromWindow(v);
                        }
                    }
                };
            }
            OnAttachStateChangeListener oldListener = (OnAttachStateChangeListener) ListenerUtil.trackListener(view, newListener, R.id.onAttachStateChangeListener);
            if (oldListener != null) {
                view.removeOnAttachStateChangeListener(oldListener);
            }
            if (newListener != null) {
                view.addOnAttachStateChangeListener(newListener);
            }
        }
    }

    @BindingAdapter({"android:onLayoutChange"})
    public static void setOnLayoutChangeListener(View view, OnLayoutChangeListener oldValue, OnLayoutChangeListener newValue) {
        if (VERSION.SDK_INT >= 11) {
            if (oldValue != null) {
                view.removeOnLayoutChangeListener(oldValue);
            }
            if (newValue != null) {
                view.addOnLayoutChangeListener(newValue);
            }
        }
    }
}
