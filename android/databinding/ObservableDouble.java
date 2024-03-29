package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableDouble extends BaseObservable implements Parcelable, Serializable {
    public static final Creator<ObservableDouble> CREATOR = new Creator<ObservableDouble>() {
        public ObservableDouble createFromParcel(Parcel source) {
            return new ObservableDouble(source.readDouble());
        }

        public ObservableDouble[] newArray(int size) {
            return new ObservableDouble[size];
        }
    };
    static final long serialVersionUID = 1;
    private double mValue;

    public ObservableDouble(double value) {
        this.mValue = value;
    }

    public double get() {
        return this.mValue;
    }

    public void set(double value) {
        if (value != this.mValue) {
            this.mValue = value;
            notifyChange();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.mValue);
    }
}
