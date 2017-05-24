package android.databinding;

import android.databinding.Observable.OnPropertyChangedCallback;

public class BaseObservable implements Observable {
    private transient PropertyChangeRegistry mCallbacks;

    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback listener) {
        if (this.mCallbacks == null) {
            this.mCallbacks = new PropertyChangeRegistry();
        }
        this.mCallbacks.add(listener);
    }

    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback listener) {
        if (this.mCallbacks != null) {
            this.mCallbacks.remove(listener);
        }
    }

    public synchronized void notifyChange() {
        if (this.mCallbacks != null) {
            this.mCallbacks.notifyCallbacks(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int fieldId) {
        if (this.mCallbacks != null) {
            this.mCallbacks.notifyCallbacks(this, fieldId, null);
        }
    }
}
