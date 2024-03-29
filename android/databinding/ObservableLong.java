package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableLong extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableLong> CREATOR = new Creator<ObservableLong>() {
        public ObservableLong createFromParcel(Parcel source) {
            return new ObservableLong(source.readLong());
        }

        public ObservableLong[] newArray(int size) {
            return new ObservableLong[size];
        }
    };
    static final long serialVersionUID = 1;
    private long mValue;

    public ObservableLong(long value) {
        this.mValue = value;
    }

    public long get() {
        return this.mValue;
    }

    public void set(long value) {
        if (value != this.mValue) {
            this.mValue = value;
            notifyChange();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mValue);
    }
}
