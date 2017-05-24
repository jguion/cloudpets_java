package com.parse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class ParseMulticastDelegate<T> {
    private final List<ParseCallback2<T, ParseException>> callbacks = new LinkedList();

    public void subscribe(ParseCallback2<T, ParseException> callback) {
        this.callbacks.add(callback);
    }

    public void unsubscribe(ParseCallback2<T, ParseException> callback) {
        this.callbacks.remove(callback);
    }

    public void invoke(T result, ParseException exception) {
        Iterator it = new ArrayList(this.callbacks).iterator();
        while (it.hasNext()) {
            ((ParseCallback2) it.next()).done(result, exception);
        }
    }

    public void clear() {
        this.callbacks.clear();
    }
}
