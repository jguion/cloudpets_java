package android.databinding;

import android.annotation.TargetApi;
import android.databinding.CallbackRegistry.NotifierCallback;
import android.databinding.Observable.OnPropertyChangedCallback;
import android.databinding.ObservableList.OnListChangedCallback;
import android.databinding.ObservableMap.OnMapChangedCallback;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import com.android.databinding.library.R;
import java.lang.ref.WeakReference;

public abstract class ViewDataBinding {
    private static final int BINDING_NUMBER_START = BINDING_TAG_PREFIX.length();
    public static final String BINDING_TAG_PREFIX = "binding_";
    private static final CreateWeakListener CREATE_LIST_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakListListener(viewDataBinding, localFieldId).getListener();
        }
    };
    private static final CreateWeakListener CREATE_MAP_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakMapListener(viewDataBinding, localFieldId).getListener();
        }
    };
    private static final CreateWeakListener CREATE_PROPERTY_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakPropertyListener(viewDataBinding, localFieldId).getListener();
        }
    };
    private static final int HALTED = 2;
    private static final int REBIND = 1;
    private static final NotifierCallback<OnRebindCallback, ViewDataBinding, Void> REBIND_NOTIFIER = new NotifierCallback<OnRebindCallback, ViewDataBinding, Void>() {
        public void onNotifyCallback(OnRebindCallback callback, ViewDataBinding sender, int mode, Void arg2) {
            switch (mode) {
                case 1:
                    if (!callback.onPreBind(sender)) {
                        sender.mRebindHalted = true;
                        return;
                    }
                    return;
                case 2:
                    callback.onCanceled(sender);
                    return;
                case 3:
                    callback.onBound(sender);
                    return;
                default:
                    return;
            }
        }
    };
    private static final int REBOUND = 3;
    private static final OnAttachStateChangeListener ROOT_REATTACHED_LISTENER;
    static int SDK_INT = VERSION.SDK_INT;
    private static final boolean USE_CHOREOGRAPHER;
    private static final boolean USE_TAG_ID = (DataBinderMapper.TARGET_MIN_SDK >= 14);
    private Choreographer mChoreographer;
    private final FrameCallback mFrameCallback;
    private boolean mIsExecutingPendingBindings;
    private WeakListener[] mLocalFieldObservers;
    private boolean mPendingRebind = false;
    private CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> mRebindCallbacks;
    private boolean mRebindHalted = false;
    private final Runnable mRebindRunnable = new Runnable() {
        public void run() {
            synchronized (this) {
                ViewDataBinding.this.mPendingRebind = false;
            }
            if (VERSION.SDK_INT < 19 || ViewDataBinding.this.mRoot.isAttachedToWindow()) {
                ViewDataBinding.this.executePendingBindings();
                return;
            }
            ViewDataBinding.this.mRoot.removeOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
            ViewDataBinding.this.mRoot.addOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
        }
    };
    private final View mRoot;
    private Handler mUIThreadHandler;

    private interface CreateWeakListener {
        WeakListener create(ViewDataBinding viewDataBinding, int i);
    }

    protected static class IncludedLayouts {
        public final int[][] indexes;
        public final int[][] layoutIds;
        public final String[][] layouts;

        public IncludedLayouts(int bindingCount) {
            this.layouts = new String[bindingCount][];
            this.indexes = new int[bindingCount][];
            this.layoutIds = new int[bindingCount][];
        }

        public void setIncludes(int index, String[] layouts, int[] indexes, int[] layoutIds) {
            this.layouts[index] = layouts;
            this.indexes[index] = indexes;
            this.layoutIds[index] = layoutIds;
        }
    }

    private interface ObservableReference<T> {
        void addListener(T t);

        WeakListener<T> getListener();

        void removeListener(T t);
    }

    private static class WeakListListener extends OnListChangedCallback implements ObservableReference<ObservableList> {
        final WeakListener<ObservableList> mListener;

        public WeakListListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener(binder, localFieldId, this);
        }

        public WeakListener<ObservableList> getListener() {
            return this.mListener;
        }

        public void addListener(ObservableList target) {
            target.addOnListChangedCallback(this);
        }

        public void removeListener(ObservableList target) {
            target.removeOnListChangedCallback(this);
        }

        public void onChanged(ObservableList sender) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null) {
                ObservableList target = (ObservableList) this.mListener.getTarget();
                if (target == sender) {
                    binder.handleFieldChange(this.mListener.mLocalFieldId, target, 0);
                }
            }
        }

        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
            onChanged(sender);
        }

        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }
    }

    private static class WeakListener<T> extends WeakReference<ViewDataBinding> {
        protected final int mLocalFieldId;
        private final ObservableReference<T> mObservable;
        private T mTarget;

        public WeakListener(ViewDataBinding binder, int localFieldId, ObservableReference<T> observable) {
            super(binder);
            this.mLocalFieldId = localFieldId;
            this.mObservable = observable;
        }

        public void setTarget(T object) {
            unregister();
            this.mTarget = object;
            if (this.mTarget != null) {
                this.mObservable.addListener(this.mTarget);
            }
        }

        public boolean unregister() {
            boolean unregistered = false;
            if (this.mTarget != null) {
                this.mObservable.removeListener(this.mTarget);
                unregistered = true;
            }
            this.mTarget = null;
            return unregistered;
        }

        public T getTarget() {
            return this.mTarget;
        }

        protected ViewDataBinding getBinder() {
            ViewDataBinding binder = (ViewDataBinding) get();
            if (binder == null) {
                unregister();
            }
            return binder;
        }
    }

    private static class WeakMapListener extends OnMapChangedCallback implements ObservableReference<ObservableMap> {
        final WeakListener<ObservableMap> mListener;

        public WeakMapListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener(binder, localFieldId, this);
        }

        public WeakListener<ObservableMap> getListener() {
            return this.mListener;
        }

        public void addListener(ObservableMap target) {
            target.addOnMapChangedCallback(this);
        }

        public void removeListener(ObservableMap target) {
            target.removeOnMapChangedCallback(this);
        }

        public void onMapChanged(ObservableMap sender, Object key) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null && sender == this.mListener.getTarget()) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, sender, 0);
            }
        }
    }

    private static class WeakPropertyListener extends OnPropertyChangedCallback implements ObservableReference<Observable> {
        final WeakListener<Observable> mListener;

        public WeakPropertyListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener(binder, localFieldId, this);
        }

        public WeakListener<Observable> getListener() {
            return this.mListener;
        }

        public void addListener(Observable target) {
            target.addOnPropertyChangedCallback(this);
        }

        public void removeListener(Observable target) {
            target.removeOnPropertyChangedCallback(this);
        }

        public void onPropertyChanged(Observable sender, int propertyId) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null && ((Observable) this.mListener.getTarget()) == sender) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, sender, propertyId);
            }
        }
    }

    protected abstract void executeBindings();

    public abstract boolean hasPendingBindings();

    public abstract void invalidateAll();

    protected abstract boolean onFieldChange(int i, Object obj, int i2);

    public abstract boolean setVariable(int i, Object obj);

    static {
        boolean z = true;
        if (SDK_INT < 16) {
            z = false;
        }
        USE_CHOREOGRAPHER = z;
        if (VERSION.SDK_INT < 19) {
            ROOT_REATTACHED_LISTENER = null;
        } else {
            ROOT_REATTACHED_LISTENER = new OnAttachStateChangeListener() {
                @TargetApi(19)
                public void onViewAttachedToWindow(View v) {
                    ViewDataBinding.getBinding(v).mRebindRunnable.run();
                    v.removeOnAttachStateChangeListener(this);
                }

                public void onViewDetachedFromWindow(View v) {
                }
            };
        }
    }

    protected ViewDataBinding(View root, int localFieldCount) {
        this.mLocalFieldObservers = new WeakListener[localFieldCount];
        this.mRoot = root;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        } else if (USE_CHOREOGRAPHER) {
            this.mChoreographer = Choreographer.getInstance();
            this.mFrameCallback = new FrameCallback() {
                public void doFrame(long frameTimeNanos) {
                    ViewDataBinding.this.mRebindRunnable.run();
                }
            };
        } else {
            this.mFrameCallback = null;
            this.mUIThreadHandler = new Handler(Looper.myLooper());
        }
    }

    protected void setRootTag(View view) {
        if (USE_TAG_ID) {
            view.setTag(R.id.dataBinding, this);
        } else {
            view.setTag(this);
        }
    }

    protected void setRootTag(View[] views) {
        if (USE_TAG_ID) {
            for (View view : views) {
                view.setTag(R.id.dataBinding, this);
            }
            return;
        }
        for (View view2 : views) {
            view2.setTag(this);
        }
    }

    public static int getBuildSdkInt() {
        return SDK_INT;
    }

    public void addOnRebindCallback(OnRebindCallback listener) {
        if (this.mRebindCallbacks == null) {
            this.mRebindCallbacks = new CallbackRegistry(REBIND_NOTIFIER);
        }
        this.mRebindCallbacks.add(listener);
    }

    public void removeOnRebindCallback(OnRebindCallback listener) {
        if (this.mRebindCallbacks != null) {
            this.mRebindCallbacks.remove(listener);
        }
    }

    public void executePendingBindings() {
        if (this.mIsExecutingPendingBindings) {
            requestRebind();
        } else if (hasPendingBindings()) {
            this.mIsExecutingPendingBindings = true;
            this.mRebindHalted = false;
            if (this.mRebindCallbacks != null) {
                this.mRebindCallbacks.notifyCallbacks(this, 1, null);
                if (this.mRebindHalted) {
                    this.mRebindCallbacks.notifyCallbacks(this, 2, null);
                }
            }
            if (!this.mRebindHalted) {
                executeBindings();
                if (this.mRebindCallbacks != null) {
                    this.mRebindCallbacks.notifyCallbacks(this, 3, null);
                }
            }
            this.mIsExecutingPendingBindings = false;
        }
    }

    void forceExecuteBindings() {
        executeBindings();
    }

    public void unbind() {
        for (WeakListener weakListener : this.mLocalFieldObservers) {
            if (weakListener != null) {
                weakListener.unregister();
            }
        }
    }

    protected void finalize() throws Throwable {
        unbind();
    }

    static ViewDataBinding getBinding(View v) {
        if (v != null) {
            if (USE_TAG_ID) {
                return (ViewDataBinding) v.getTag(R.id.dataBinding);
            }
            Object tag = v.getTag();
            if (tag instanceof ViewDataBinding) {
                return (ViewDataBinding) tag;
            }
        }
        return null;
    }

    public View getRoot() {
        return this.mRoot;
    }

    private void handleFieldChange(int mLocalFieldId, Object object, int fieldId) {
        if (onFieldChange(mLocalFieldId, object, fieldId)) {
            requestRebind();
        }
    }

    protected boolean unregisterFrom(int localFieldId) {
        WeakListener listener = this.mLocalFieldObservers[localFieldId];
        if (listener != null) {
            return listener.unregister();
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void requestRebind() {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.mPendingRebind;	 Catch:{ all -> 0x0017 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r2);	 Catch:{ all -> 0x0017 }
    L_0x0006:
        return;
    L_0x0007:
        r0 = 1;
        r2.mPendingRebind = r0;	 Catch:{ all -> 0x0017 }
        monitor-exit(r2);	 Catch:{ all -> 0x0017 }
        r0 = USE_CHOREOGRAPHER;
        if (r0 == 0) goto L_0x001a;
    L_0x000f:
        r0 = r2.mChoreographer;
        r1 = r2.mFrameCallback;
        r0.postFrameCallback(r1);
        goto L_0x0006;
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0017 }
        throw r0;
    L_0x001a:
        r0 = r2.mUIThreadHandler;
        r1 = r2.mRebindRunnable;
        r0.post(r1);
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.databinding.ViewDataBinding.requestRebind():void");
    }

    protected Object getObservedField(int localFieldId) {
        WeakListener listener = this.mLocalFieldObservers[localFieldId];
        if (listener == null) {
            return null;
        }
        return listener.getTarget();
    }

    private boolean updateRegistration(int localFieldId, Object observable, CreateWeakListener listenerCreator) {
        if (observable == null) {
            return unregisterFrom(localFieldId);
        }
        WeakListener listener = this.mLocalFieldObservers[localFieldId];
        if (listener == null) {
            registerTo(localFieldId, observable, listenerCreator);
            return true;
        } else if (listener.getTarget() == observable) {
            return false;
        } else {
            unregisterFrom(localFieldId);
            registerTo(localFieldId, observable, listenerCreator);
            return true;
        }
    }

    protected boolean updateRegistration(int localFieldId, Observable observable) {
        return updateRegistration(localFieldId, observable, CREATE_PROPERTY_LISTENER);
    }

    protected boolean updateRegistration(int localFieldId, ObservableList observable) {
        return updateRegistration(localFieldId, observable, CREATE_LIST_LISTENER);
    }

    protected boolean updateRegistration(int localFieldId, ObservableMap observable) {
        return updateRegistration(localFieldId, observable, CREATE_MAP_LISTENER);
    }

    protected void registerTo(int localFieldId, Object observable, CreateWeakListener listenerCreator) {
        if (observable != null) {
            WeakListener listener = this.mLocalFieldObservers[localFieldId];
            if (listener == null) {
                listener = listenerCreator.create(this, localFieldId);
                this.mLocalFieldObservers[localFieldId] = listener;
            }
            listener.setTarget(observable);
        }
    }

    protected static ViewDataBinding bind(View view, int layoutId) {
        return DataBindingUtil.bind(view, layoutId);
    }

    protected static Object[] mapBindings(View root, int numBindings, IncludedLayouts includes, SparseIntArray viewsWithIds) {
        Object[] bindings = new Object[numBindings];
        mapBindings(root, bindings, includes, viewsWithIds, true);
        return bindings;
    }

    protected static Object[] mapBindings(View[] roots, int numBindings, IncludedLayouts includes, SparseIntArray viewsWithIds) {
        Object[] bindings = new Object[numBindings];
        for (View mapBindings : roots) {
            mapBindings(mapBindings, bindings, includes, viewsWithIds, true);
        }
        return bindings;
    }

    private static void mapBindings(View view, Object[] bindings, IncludedLayouts includes, SparseIntArray viewsWithIds, boolean isRoot) {
        if (getBinding(view) == null) {
            int indexInIncludes;
            int index;
            String tag = (String) view.getTag();
            boolean isBound = false;
            if (isRoot && tag != null && tag.startsWith("layout")) {
                int underscoreIndex = tag.lastIndexOf(95);
                if (underscoreIndex <= 0 || !isNumeric(tag, underscoreIndex + 1)) {
                    indexInIncludes = -1;
                } else {
                    index = parseTagInt(tag, underscoreIndex + 1);
                    if (bindings[index] == null) {
                        bindings[index] = view;
                    }
                    if (includes == null) {
                        indexInIncludes = -1;
                    } else {
                        indexInIncludes = index;
                    }
                    isBound = true;
                }
            } else if (tag == null || !tag.startsWith(BINDING_TAG_PREFIX)) {
                indexInIncludes = -1;
            } else {
                int tagIndex = parseTagInt(tag, BINDING_NUMBER_START);
                if (bindings[tagIndex] == null) {
                    bindings[tagIndex] = view;
                }
                isBound = true;
                if (includes == null) {
                    indexInIncludes = -1;
                } else {
                    indexInIncludes = tagIndex;
                }
            }
            if (!isBound) {
                int id = view.getId();
                if (id > 0 && viewsWithIds != null) {
                    index = viewsWithIds.get(id, -1);
                    if (index >= 0 && bindings[index] == null) {
                        bindings[index] = view;
                    }
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int count = viewGroup.getChildCount();
                int minInclude = 0;
                int i = 0;
                while (i < count) {
                    View child = viewGroup.getChildAt(i);
                    boolean isInclude = false;
                    if (indexInIncludes >= 0) {
                        String childTag = (String) child.getTag();
                        if (childTag != null && childTag.endsWith("_0") && childTag.startsWith("layout") && childTag.indexOf(47) > 0) {
                            int includeIndex = findIncludeIndex(childTag, minInclude, includes, indexInIncludes);
                            if (includeIndex >= 0) {
                                isInclude = true;
                                minInclude = includeIndex + 1;
                                index = includes.indexes[indexInIncludes][includeIndex];
                                int layoutId = includes.layoutIds[indexInIncludes][includeIndex];
                                int lastMatchingIndex = findLastMatching(viewGroup, i);
                                if (lastMatchingIndex == i) {
                                    bindings[index] = DataBindingUtil.bind(child, layoutId);
                                } else {
                                    int includeCount = (lastMatchingIndex - i) + 1;
                                    View[] included = new View[includeCount];
                                    for (int j = 0; j < includeCount; j++) {
                                        included[j] = viewGroup.getChildAt(i + j);
                                    }
                                    bindings[index] = DataBindingUtil.bind(included, layoutId);
                                    i += includeCount - 1;
                                }
                            }
                        }
                    }
                    if (!isInclude) {
                        mapBindings(child, bindings, includes, viewsWithIds, false);
                    }
                    i++;
                }
            }
        }
    }

    private static int findIncludeIndex(String tag, int minInclude, IncludedLayouts included, int includedIndex) {
        CharSequence layoutName = tag.subSequence(tag.indexOf(47) + 1, tag.length() - 2);
        String[] layouts = included.layouts[includedIndex];
        int length = layouts.length;
        for (int i = minInclude; i < length; i++) {
            if (TextUtils.equals(layoutName, layouts[i])) {
                return i;
            }
        }
        return -1;
    }

    private static int findLastMatching(ViewGroup viewGroup, int firstIncludedIndex) {
        String firstViewTag = (String) viewGroup.getChildAt(firstIncludedIndex).getTag();
        String tagBase = firstViewTag.substring(0, firstViewTag.length() - 1);
        int tagSequenceIndex = tagBase.length();
        int count = viewGroup.getChildCount();
        int max = firstIncludedIndex;
        for (int i = firstIncludedIndex + 1; i < count; i++) {
            String tag = (String) viewGroup.getChildAt(i).getTag();
            if (tag != null && tag.startsWith(tagBase)) {
                if (tag.length() == firstViewTag.length() && tag.charAt(tag.length() - 1) == '0') {
                    break;
                } else if (isNumeric(tag, tagSequenceIndex)) {
                    max = i;
                }
            }
        }
        return max;
    }

    private static boolean isNumeric(String tag, int startIndex) {
        int length = tag.length();
        if (length == startIndex) {
            return false;
        }
        for (int i = startIndex; i < length; i++) {
            if (!Character.isDigit(tag.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int parseTagInt(String str, int startIndex) {
        int val = 0;
        for (int i = startIndex; i < str.length(); i++) {
            val = (val * 10) + (str.charAt(i) - 48);
        }
        return val;
    }
}
