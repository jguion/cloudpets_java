package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonthAdapter extends BaseAdapter {
    private Context mContext;
    private boolean mHasNoSelectionValue;
    private List<String> mMonths;
    private boolean mUseNoSelectionValueAsPlaceholder;

    public MonthAdapter(Context c, boolean hasNoSelectionValue, boolean useNoSelectionValueAsPlaceholder) {
        this.mContext = c;
        this.mHasNoSelectionValue = hasNoSelectionValue;
        this.mUseNoSelectionValueAsPlaceholder = useNoSelectionValueAsPlaceholder;
        this.mMonths = new ArrayList(Arrays.asList(c.getResources().getStringArray(R.array.months)));
        if (this.mHasNoSelectionValue) {
            this.mMonths.add(0, this.mContext.getString(useNoSelectionValueAsPlaceholder ? R.string.spinner_placeholder_month : R.string.empty_spinner_item));
        }
    }

    public int getCount() {
        return this.mMonths.size();
    }

    public String getItem(int position) {
        return (String) this.mMonths.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        int i;
        if (convertView == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i | (convertView instanceof TextView)) != 0) {
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
        if (this.mHasNoSelectionValue && this.mUseNoSelectionValueAsPlaceholder && position == 0) {
            text1.setTextColor(text1.getHintTextColors());
        }
        return convertView;
    }
}
