package com.spiraltoys.cloudpets2.adapters;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;
import com.spiraltoys.cloudpets2.free.R;

public class FriendPermissionsAdapter$HeaderViewHolder extends ViewHolder {
    View mView;

    public FriendPermissionsAdapter$HeaderViewHolder(View view) {
        super(view);
        this.mView = view;
    }

    public void setParseObject(String text) {
        ((TextView) this.mView.findViewById(R.id.text)).setText(text);
    }
}
