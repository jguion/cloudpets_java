package com.unity3d.player;

import android.os.Build.VERSION;

public final class o {
    static final boolean a = (VERSION.SDK_INT >= 11);
    static final boolean b = (VERSION.SDK_INT >= 12);
    static final boolean c = (VERSION.SDK_INT >= 14);
    static final boolean d = (VERSION.SDK_INT >= 16);
    static final boolean e = (VERSION.SDK_INT >= 17);
    static final boolean f = (VERSION.SDK_INT >= 19);
    static final boolean g;
    static final f h = (a ? new d() : null);
    static final e i = (b ? new c() : null);
    static final h j = (d ? new k() : null);
    static final g k;

    static {
        g gVar = null;
        boolean z = true;
        if (VERSION.SDK_INT < 21) {
            z = false;
        }
        g = z;
        if (e) {
            gVar = new j();
        }
        k = gVar;
    }
}
