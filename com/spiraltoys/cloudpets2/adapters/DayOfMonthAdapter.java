package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import java.util.GregorianCalendar;

public class DayOfMonthAdapter extends BaseAdapter {
    private Context mContext;
    private boolean mHasNoSelectionValue;
    private int mMonth = 0;
    private boolean mUseNoSelectionValueAsPlaceholder;

    public DayOfMonthAdapter(Context c, boolean hasNoSelectionValue, boolean useNoSelectionValueAsPlaceholder) {
        this.mContext = c;
        this.mHasNoSelectionValue = hasNoSelectionValue;
        this.mUseNoSelectionValueAsPlaceholder = useNoSelectionValueAsPlaceholder;
    }

    public void setMonth(int month) {
        this.mMonth = month - (this.mHasNoSelectionValue ? 1 : 0);
        notifyDataSetChanged();
    }

    public int getCount() {
        int i = 1;
        int actualMaximum = new GregorianCalendar(2000, this.mMonth, 1).getActualMaximum(5);
        if (!this.mHasNoSelectionValue) {
            i = 0;
        }
        return i + actualMaximum;
    }

    public String getItem(int position) {
        if (this.mHasNoSelectionValue && position == 0) {
            return this.mContext.getString(this.mUseNoSelectionValueAsPlaceholder ? R.string.spinner_placeholder_day : R.string.empty_spinner_item);
        }
        return String.valueOf((this.mHasNoSelectionValue ? 0 : 1) + position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || (convertView instanceof TextView)) {
            convertView = LayoutInflater.from(this.mContext).inflate(17367049, parent, false);
        }
        TextView text1 = (TextView) convertView.findViewById(16908308);
        if (this.mHasNoSelectionValue && position == 0) {
            text1.setText(R.string.empty_spinner_item);
        } else {
            text1.setText(getItem(position));
        }
        return convertView;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(17367048, parent, false);
        }
        TextView text1 = (TextView) convertView.findViewById(16908308);
        text1.setText(getItem(position));
        if (this.mHasNoSelectionValue && this.mUseNoSelectionValueAsPlaceholder && position == 0 && parent.isEnabled()) {
            text1.setTextColor(text1.getHintTextColors());
        }
        return convertView;
    }
}
