package com.google.android.gms.common.data;

import com.google.android.gms.common.data.DataBufferObserver.Observable;
import java.util.HashSet;
import java.util.Iterator;

public final class DataBufferObserverSet implements DataBufferObserver, Observable {
    private HashSet<DataBufferObserver> zJ = new HashSet();

    public void addObserver(DataBufferObserver dataBufferObserver) {
        this.zJ.add(dataBufferObserver);
    }

    public void clear() {
        this.zJ.clear();
    }

    public boolean hasObservers() {
        return !this.zJ.isEmpty();
    }

    public void onDataChanged() {
        Iterator it = this.zJ.iterator();
        while (it.hasNext()) {
            ((DataBufferObserver) it.next()).onDataChanged();
        }
    }

    public void onDataRangeChanged(int i, int i2) {
        Iterator it = this.zJ.iterator();
        while (it.hasNext()) {
            ((DataBufferObserver) it.next()).onDataRangeChanged(i, i2);
        }
    }

    public void onDataRangeInserted(int i, int i2) {
        Iterator it = this.zJ.iterator();
        while (it.hasNext()) {
            ((DataBufferObserver) it.next()).onDataRangeInserted(i, i2);
        }
    }

    public void onDataRangeMoved(int i, int i2, int i3) {
        Iterator it = this.zJ.iterator();
        while (it.hasNext()) {
            ((DataBufferObserver) it.next()).onDataRangeMoved(i, i2, i3);
        }
    }

    public void onDataRangeRemoved(int i, int i2) {
        Iterator it = this.zJ.iterator();
        while (it.hasNext()) {
            ((DataBufferObserver) it.next()).onDataRangeRemoved(i, i2);
        }
    }

    public void removeObserver(DataBufferObserver dataBufferObserver) {
        this.zJ.remove(dataBufferObserver);
    }
}
