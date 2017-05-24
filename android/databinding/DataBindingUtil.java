package android.databinding;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class DataBindingUtil {
    private static DataBinderMapper sMapper = new DataBinderMapper();

    public static <T extends ViewDataBinding> T inflate(LayoutInflater inflater, int layoutId, @Nullable ViewGroup parent, boolean attachToParent) {
        boolean useChildren;
        int startChildren = 0;
        if (parent == null || !attachToParent) {
            useChildren = false;
        } else {
            useChildren = true;
        }
        if (useChildren) {
            startChildren = parent.getChildCount();
        }
        View view = inflater.inflate(layoutId, parent, attachToParent);
        if (!useChildren) {
            return bind(view, layoutId);
        }
        int endChildren = parent.getChildCount();
        int childrenAdded = endChildren - startChildren;
        if (childrenAdded == 1) {
            return bind(parent.getChildAt(endChildren - 1), layoutId);
        }
        View[] children = new View[childrenAdded];
        for (int i = 0; i < childrenAdded; i++) {
            children[i] = parent.getChildAt(i + startChildren);
        }
        return bind(children, layoutId);
    }

    public static <T extends ViewDataBinding> T bind(View root) {
        T binding = getBinding(root);
        if (binding != null) {
            return binding;
        }
        String tagObj = root.getTag();
        if (tagObj instanceof String) {
            int layoutId = sMapper.getLayoutId(tagObj);
            if (layoutId != 0) {
                return sMapper.getDataBinder(root, layoutId);
            }
            throw new IllegalArgumentException("View is not a binding layout");
        }
        throw new IllegalArgumentException("View is not a binding layout");
    }

    static <T extends ViewDataBinding> T bind(View[] roots, int layoutId) {
        return sMapper.getDataBinder(roots, layoutId);
    }

    static <T extends ViewDataBinding> T bind(View root, int layoutId) {
        return sMapper.getDataBinder(root, layoutId);
    }

    public static <T extends ViewDataBinding> T findBinding(View view) {
        while (view != null) {
            ViewDataBinding binding = ViewDataBinding.getBinding(view);
            if (binding != null) {
                return binding;
            }
            String tag = view.getTag();
            if (tag instanceof String) {
                String tagString = tag;
                if (tagString.startsWith("layout") && tagString.endsWith("_0")) {
                    char nextChar = tagString.charAt(6);
                    int slashIndex = tagString.indexOf(47, 7);
                    boolean isUnboundRoot = false;
                    if (nextChar == '/') {
                        isUnboundRoot = slashIndex == -1;
                    } else if (nextChar == '-' && slashIndex != -1) {
                        isUnboundRoot = tagString.indexOf(47, slashIndex + 1) == -1;
                    }
                    if (isUnboundRoot) {
                        return null;
                    }
                }
            }
            ViewParent viewParent = view.getParent();
            if (viewParent instanceof View) {
                view = (View) viewParent;
            } else {
                view = null;
            }
        }
        return null;
    }

    public static <T extends ViewDataBinding> T getBinding(View view) {
        return ViewDataBinding.getBinding(view);
    }

    public static <T extends ViewDataBinding> T setContentView(Activity activity, int layoutId) {
        T binding = inflate(activity.getLayoutInflater(), layoutId, (ViewGroup) activity.getWindow().getDecorView().findViewById(16908290), false);
        activity.setContentView(binding.getRoot(), binding.getRoot().getLayoutParams());
        return binding;
    }

    public static String convertBrIdToString(int id) {
        return sMapper.convertBrIdToString(id);
    }
}
