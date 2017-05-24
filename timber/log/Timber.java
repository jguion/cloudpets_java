package timber.log;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NonNls;

public final class Timber {
    private static final List<Tree> FOREST = new ArrayList();
    private static final Tree[] TREE_ARRAY_EMPTY = new Tree[0];
    private static final Tree TREE_OF_SOULS = new Tree() {
        public void v(String message, Object... args) {
            for (Tree v : Timber.forestAsArray) {
                v.v(message, args);
            }
        }

        public void v(Throwable t, String message, Object... args) {
            for (Tree v : Timber.forestAsArray) {
                v.v(t, message, args);
            }
        }

        public void d(String message, Object... args) {
            for (Tree d : Timber.forestAsArray) {
                d.d(message, args);
            }
        }

        public void d(Throwable t, String message, Object... args) {
            for (Tree d : Timber.forestAsArray) {
                d.d(t, message, args);
            }
        }

        public void i(String message, Object... args) {
            for (Tree i : Timber.forestAsArray) {
                i.i(message, args);
            }
        }

        public void i(Throwable t, String message, Object... args) {
            for (Tree i : Timber.forestAsArray) {
                i.i(t, message, args);
            }
        }

        public void w(String message, Object... args) {
            for (Tree w : Timber.forestAsArray) {
                w.w(message, args);
            }
        }

        public void w(Throwable t, String message, Object... args) {
            for (Tree w : Timber.forestAsArray) {
                w.w(t, message, args);
            }
        }

        public void e(String message, Object... args) {
            for (Tree e : Timber.forestAsArray) {
                e.e(message, args);
            }
        }

        public void e(Throwable t, String message, Object... args) {
            for (Tree e : Timber.forestAsArray) {
                e.e(t, message, args);
            }
        }

        public void wtf(String message, Object... args) {
            for (Tree wtf : Timber.forestAsArray) {
                wtf.wtf(message, args);
            }
        }

        public void wtf(Throwable t, String message, Object... args) {
            for (Tree wtf : Timber.forestAsArray) {
                wtf.wtf(t, message, args);
            }
        }

        public void log(int priority, String message, Object... args) {
            for (Tree log : Timber.forestAsArray) {
                log.log(priority, message, args);
            }
        }

        public void log(int priority, Throwable t, String message, Object... args) {
            for (Tree log : Timber.forestAsArray) {
                log.log(priority, t, message, args);
            }
        }

        protected void log(int priority, String tag, String message, Throwable t) {
            throw new AssertionError("Missing override for log method.");
        }
    };
    private static volatile Tree[] forestAsArray = TREE_ARRAY_EMPTY;

    public static abstract class Tree {
        private final ThreadLocal<String> explicitTag = new ThreadLocal();

        protected abstract void log(int i, String str, String str2, Throwable th);

        String getTag() {
            String tag = (String) this.explicitTag.get();
            if (tag != null) {
                this.explicitTag.remove();
            }
            return tag;
        }

        public void v(String message, Object... args) {
            prepareLog(2, null, message, args);
        }

        public void v(Throwable t, String message, Object... args) {
            prepareLog(2, t, message, args);
        }

        public void d(String message, Object... args) {
            prepareLog(3, null, message, args);
        }

        public void d(Throwable t, String message, Object... args) {
            prepareLog(3, t, message, args);
        }

        public void i(String message, Object... args) {
            prepareLog(4, null, message, args);
        }

        public void i(Throwable t, String message, Object... args) {
            prepareLog(4, t, message, args);
        }

        public void w(String message, Object... args) {
            prepareLog(5, null, message, args);
        }

        public void w(Throwable t, String message, Object... args) {
            prepareLog(5, t, message, args);
        }

        public void e(String message, Object... args) {
            prepareLog(6, null, message, args);
        }

        public void e(Throwable t, String message, Object... args) {
            prepareLog(6, t, message, args);
        }

        public void wtf(String message, Object... args) {
            prepareLog(7, null, message, args);
        }

        public void wtf(Throwable t, String message, Object... args) {
            prepareLog(7, t, message, args);
        }

        public void log(int priority, String message, Object... args) {
            prepareLog(priority, null, message, args);
        }

        public void log(int priority, Throwable t, String message, Object... args) {
            prepareLog(priority, t, message, args);
        }

        protected boolean isLoggable(int priority) {
            return true;
        }

        private void prepareLog(int priority, Throwable t, String message, Object... args) {
            if (isLoggable(priority)) {
                if (message != null && message.length() == 0) {
                    message = null;
                }
                if (message != null) {
                    if (args.length > 0) {
                        message = String.format(message, args);
                    }
                    if (t != null) {
                        message = message + "\n" + getStackTraceString(t);
                    }
                } else if (t != null) {
                    message = getStackTraceString(t);
                } else {
                    return;
                }
                log(priority, getTag(), message, t);
            }
        }

        private String getStackTraceString(Throwable t) {
            StringWriter sw = new StringWriter(256);
            PrintWriter pw = new PrintWriter(sw, false);
            t.printStackTrace(pw);
            pw.flush();
            return sw.toString();
        }
    }

    public static class DebugTree extends Tree {
        private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");
        private static final int CALL_STACK_INDEX = 5;
        private static final int MAX_LOG_LENGTH = 4000;

        protected String createStackElementTag(StackTraceElement element) {
            String tag = element.getClassName();
            Matcher m = ANONYMOUS_CLASS.matcher(tag);
            if (m.find()) {
                tag = m.replaceAll("");
            }
            return tag.substring(tag.lastIndexOf(46) + 1);
        }

        final String getTag() {
            String tag = super.getTag();
            if (tag != null) {
                return tag;
            }
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            if (stackTrace.length > 5) {
                return createStackElementTag(stackTrace[5]);
            }
            throw new IllegalStateException("Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }

        protected void log(int priority, String tag, String message, Throwable t) {
            if (message.length() >= MAX_LOG_LENGTH) {
                int i = 0;
                int length = message.length();
                while (i < length) {
                    int newline = message.indexOf(10, i);
                    if (newline == -1) {
                        newline = length;
                    }
                    do {
                        int end = Math.min(newline, i + MAX_LOG_LENGTH);
                        String part = message.substring(i, end);
                        if (priority == 7) {
                            Log.wtf(tag, part);
                        } else {
                            Log.println(priority, tag, part);
                        }
                        i = end;
                    } while (i < newline);
                    i++;
                }
            } else if (priority == 7) {
                Log.wtf(tag, message);
            } else {
                Log.println(priority, tag, message);
            }
        }
    }

    public static void v(@NonNls String message, Object... args) {
        TREE_OF_SOULS.v(message, args);
    }

    public static void v(Throwable t, @NonNls String message, Object... args) {
        TREE_OF_SOULS.v(t, message, args);
    }

    public static void d(@NonNls String message, Object... args) {
        TREE_OF_SOULS.d(message, args);
    }

    public static void d(Throwable t, @NonNls String message, Object... args) {
        TREE_OF_SOULS.d(t, message, args);
    }

    public static void i(@NonNls String message, Object... args) {
        TREE_OF_SOULS.i(message, args);
    }

    public static void i(Throwable t, @NonNls String message, Object... args) {
        TREE_OF_SOULS.i(t, message, args);
    }

    public static void w(@NonNls String message, Object... args) {
        TREE_OF_SOULS.w(message, args);
    }

    public static void w(Throwable t, @NonNls String message, Object... args) {
        TREE_OF_SOULS.w(t, message, args);
    }

    public static void e(@NonNls String message, Object... args) {
        TREE_OF_SOULS.e(message, args);
    }

    public static void e(Throwable t, @NonNls String message, Object... args) {
        TREE_OF_SOULS.e(t, message, args);
    }

    public static void wtf(@NonNls String message, Object... args) {
        TREE_OF_SOULS.wtf(message, args);
    }

    public static void wtf(Throwable t, @NonNls String message, Object... args) {
        TREE_OF_SOULS.wtf(t, message, args);
    }

    public static void log(int priority, @NonNls String message, Object... args) {
        TREE_OF_SOULS.log(priority, message, args);
    }

    public static void log(int priority, Throwable t, @NonNls String message, Object... args) {
        TREE_OF_SOULS.log(priority, t, message, args);
    }

    public static Tree asTree() {
        return TREE_OF_SOULS;
    }

    public static Tree tag(String tag) {
        for (Tree access$000 : forestAsArray) {
            access$000.explicitTag.set(tag);
        }
        return TREE_OF_SOULS;
    }

    public static void plant(Tree tree) {
        if (tree == null) {
            throw new NullPointerException("tree == null");
        } else if (tree == TREE_OF_SOULS) {
            throw new IllegalArgumentException("Cannot plant Timber into itself.");
        } else {
            synchronized (FOREST) {
                FOREST.add(tree);
                forestAsArray = (Tree[]) FOREST.toArray(new Tree[FOREST.size()]);
            }
        }
    }

    public static void uproot(Tree tree) {
        synchronized (FOREST) {
            if (FOREST.remove(tree)) {
                forestAsArray = (Tree[]) FOREST.toArray(new Tree[FOREST.size()]);
            } else {
                throw new IllegalArgumentException("Cannot uproot tree which is not planted: " + tree);
            }
        }
    }

    public static void uprootAll() {
        synchronized (FOREST) {
            FOREST.clear();
            forestAsArray = TREE_ARRAY_EMPTY;
        }
    }

    public static List<Tree> forest() {
        List<Tree> unmodifiableList;
        synchronized (FOREST) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(FOREST));
        }
        return unmodifiableList;
    }

    private Timber() {
        throw new AssertionError("No instances.");
    }
}
