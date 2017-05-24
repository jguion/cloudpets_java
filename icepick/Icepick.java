package icepick;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import icepick.Injector.Object;
import java.util.LinkedHashMap;
import java.util.Map;

public class Icepick {
    public static final String ANDROID_PREFIX = "android.";
    private static final Map<Class<?>, Injector> INJECTORS = new LinkedHashMap();
    public static final String JAVA_PREFIX = "java.";
    public static final String SUFFIX = "$$Icepick";
    private static final String TAG = "Icepick";
    private static boolean debug = false;

    public static void setDebug(boolean z) {
        debug = z;
    }

    private static Injector getInjector(Class<?> cls) throws IllegalAccessException, InstantiationException {
        Injector injector = (Injector) INJECTORS.get(cls);
        if (injector == null) {
            String name = cls.getName();
            if (name.startsWith("android.") || name.startsWith("java.")) {
                if (debug) {
                    Log.d(TAG, "MISS: Reached framework class. Abandoning search.");
                }
                return null;
            }
            try {
                injector = (Injector) Class.forName(name + SUFFIX).newInstance();
                if (debug) {
                    Log.d(TAG, "HIT: Class loaded injection class.");
                }
            } catch (ClassNotFoundException e) {
                if (debug) {
                    Log.d(TAG, "Not found. Trying superclass " + cls.getSuperclass().getName());
                }
                injector = getInjector(cls.getSuperclass());
            }
            INJECTORS.put(cls, injector);
            return injector;
        } else if (!debug) {
            return injector;
        } else {
            Log.d(TAG, "HIT: Cached in injector map.");
            return injector;
        }
    }

    private static <T extends Injector> T safeGet(Object obj, Injector injector) {
        try {
            Injector injector2 = getInjector(obj.getClass());
            if (injector2 == null) {
                return injector;
            }
            return injector2;
        } catch (Throwable e) {
            throw new RuntimeException("Unable to inject state for " + obj, e);
        }
    }

    public static <T> void saveInstanceState(T t, Bundle bundle) {
        ((Object) safeGet(t, new Object())).save(t, bundle);
    }

    public static <T> void restoreInstanceState(T t, Bundle bundle) {
        ((Object) safeGet(t, new Object())).restore(t, bundle);
    }

    public static <T extends View> Parcelable saveInstanceState(T t, Parcelable parcelable) {
        return ((Injector.View) safeGet(t, new Injector.View())).save(t, parcelable);
    }

    public static <T extends View> Parcelable restoreInstanceState(T t, Parcelable parcelable) {
        return ((Injector.View) safeGet(t, new Injector.View())).restore(t, parcelable);
    }
}
