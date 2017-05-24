package com.spiraltoys.cloudpets2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.model.FriendRelationship;
import java.util.Arrays;
import java.util.List;

public class RelationshipAdapter extends BaseAdapter {
    private List<FriendRelationship> list = Arrays.asList(FriendRelationship.values());
    private Context mContext;

    public RelationshipAdapter(Context context) {
        this.mContext = context;
    }

    public int getCount() {
        return this.list.size();
    }

    public FriendRelationship getItem(int position) {
        return (FriendRelationship) this.list.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(17367048, parent, false);
        }
        ((TextView) convertView.findViewById(16908308)).setText(((FriendRelationship) this.list.get(position)).getLocalizedString(parent.getContext()));
        return convertView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(17367049, parent, false);
        }
        ((TextView) convertView.findViewById(16908308)).setText(((FriendRelationship) this.list.get(position)).getLocalizedString(parent.getContext()));
        return convertView;
    }
}
