package com.parse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

class ParseObjectSubclassingController {
    private final Object mutex = new Object();
    private final Map<String, Constructor<? extends ParseObject>> registeredSubclasses = new HashMap();

    ParseObjectSubclassingController() {
    }

    String getClassName(Class<? extends ParseObject> clazz) {
        ParseClassName info = (ParseClassName) clazz.getAnnotation(ParseClassName.class);
        if (info != null) {
            return info.value();
        }
        throw new IllegalArgumentException("No ParseClassName annotation provided on " + clazz);
    }

    boolean isSubclassValid(String className, Class<? extends ParseObject> clazz) {
        synchronized (this.mutex) {
            Constructor<? extends ParseObject> constructor = (Constructor) this.registeredSubclasses.get(className);
        }
        if (constructor != null) {
            return constructor.getDeclaringClass() == clazz;
        } else {
            if (clazz == ParseObject.class) {
                return true;
            }
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void registerSubclass(java.lang.Class<? extends com.parse.ParseObject> r10) {
        /*
        r9 = this;
        r5 = com.parse.ParseObject.class;
        r5 = r5.isAssignableFrom(r10);
        if (r5 != 0) goto L_0x0010;
    L_0x0008:
        r5 = new java.lang.IllegalArgumentException;
        r6 = "Cannot register a type that is not a subclass of ParseObject";
        r5.<init>(r6);
        throw r5;
    L_0x0010:
        r1 = r9.getClassName(r10);
        r4 = 0;
        r6 = r9.mutex;
        monitor-enter(r6);
        r5 = r9.registeredSubclasses;	 Catch:{ all -> 0x0091 }
        r5 = r5.get(r1);	 Catch:{ all -> 0x0091 }
        r0 = r5;
        r0 = (java.lang.reflect.Constructor) r0;	 Catch:{ all -> 0x0091 }
        r4 = r0;
        if (r4 == 0) goto L_0x0036;
    L_0x0024:
        r3 = r4.getDeclaringClass();	 Catch:{ all -> 0x0091 }
        r5 = r10.isAssignableFrom(r3);	 Catch:{ all -> 0x0091 }
        if (r5 == 0) goto L_0x0030;
    L_0x002e:
        monitor-exit(r6);	 Catch:{ all -> 0x0091 }
    L_0x002f:
        return;
    L_0x0030:
        r5 = r3.isAssignableFrom(r10);	 Catch:{ all -> 0x0091 }
        if (r5 == 0) goto L_0x0056;
    L_0x0036:
        r5 = r9.registeredSubclasses;	 Catch:{ NoSuchMethodException -> 0x0094, IllegalAccessException -> 0x009d }
        r7 = getConstructor(r10);	 Catch:{ NoSuchMethodException -> 0x0094, IllegalAccessException -> 0x009d }
        r5.put(r1, r7);	 Catch:{ NoSuchMethodException -> 0x0094, IllegalAccessException -> 0x009d }
        monitor-exit(r6);	 Catch:{ all -> 0x0091 }
        if (r4 == 0) goto L_0x002f;
    L_0x0042:
        r5 = com.parse.ParseUser.class;
        r5 = r9.getClassName(r5);
        r5 = r1.equals(r5);
        if (r5 == 0) goto L_0x00bd;
    L_0x004e:
        r5 = com.parse.ParseUser.getCurrentUserController();
        r5.clearFromMemory();
        goto L_0x002f;
    L_0x0056:
        r5 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0091 }
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0091 }
        r7.<init>();	 Catch:{ all -> 0x0091 }
        r8 = "Tried to register both ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0091 }
        r8 = r3.getName();	 Catch:{ all -> 0x0091 }
        r7 = r7.append(r8);	 Catch:{ all -> 0x0091 }
        r8 = " and ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0091 }
        r8 = r10.getName();	 Catch:{ all -> 0x0091 }
        r7 = r7.append(r8);	 Catch:{ all -> 0x0091 }
        r8 = " as the ParseObject subclass of ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0091 }
        r7 = r7.append(r1);	 Catch:{ all -> 0x0091 }
        r8 = ". Cannot determine the right class to use because neither inherits from the other.";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0091 }
        r7 = r7.toString();	 Catch:{ all -> 0x0091 }
        r5.<init>(r7);	 Catch:{ all -> 0x0091 }
        throw r5;	 Catch:{ all -> 0x0091 }
    L_0x0091:
        r5 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0091 }
        throw r5;
    L_0x0094:
        r2 = move-exception;
        r5 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0091 }
        r7 = "Cannot register a type that does not implement the default constructor!";
        r5.<init>(r7);	 Catch:{ all -> 0x0091 }
        throw r5;	 Catch:{ all -> 0x0091 }
    L_0x009d:
        r2 = move-exception;
        r5 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0091 }
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0091 }
        r7.<init>();	 Catch:{ all -> 0x0091 }
        r8 = "Default constructor for ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0091 }
        r7 = r7.append(r10);	 Catch:{ all -> 0x0091 }
        r8 = " is not accessible.";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0091 }
        r7 = r7.toString();	 Catch:{ all -> 0x0091 }
        r5.<init>(r7);	 Catch:{ all -> 0x0091 }
        throw r5;	 Catch:{ all -> 0x0091 }
    L_0x00bd:
        r5 = com.parse.ParseInstallation.class;
        r5 = r9.getClassName(r5);
        r5 = r1.equals(r5);
        if (r5 == 0) goto L_0x002f;
    L_0x00c9:
        r5 = com.parse.ParseInstallation.getCurrentInstallationController();
        r5.clearFromMemory();
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.parse.ParseObjectSubclassingController.registerSubclass(java.lang.Class):void");
    }

    void unregisterSubclass(Class<? extends ParseObject> clazz) {
        String className = getClassName(clazz);
        synchronized (this.mutex) {
            this.registeredSubclasses.remove(className);
        }
    }

    ParseObject newInstance(String className) {
        synchronized (this.mutex) {
            Constructor<? extends ParseObject> constructor = (Constructor) this.registeredSubclasses.get(className);
        }
        if (constructor == null) {
            return new ParseObject(className);
        }
        try {
            return (ParseObject) constructor.newInstance(new Object[0]);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException("Failed to create instance of subclass.", e2);
        }
    }

    private static Constructor<? extends ParseObject> getConstructor(Class<? extends ParseObject> clazz) throws NoSuchMethodException, IllegalAccessException {
        Constructor<? extends ParseObject> constructor = clazz.getDeclaredConstructor(new Class[0]);
        if (constructor == null) {
            throw new NoSuchMethodException();
        }
        int modifiers = constructor.getModifiers();
        if (Modifier.isPublic(modifiers) || (clazz.getPackage().getName().equals("com.parse") && !Modifier.isProtected(modifiers) && !Modifier.isPrivate(modifiers))) {
            return constructor;
        }
        throw new IllegalAccessException();
    }
}
