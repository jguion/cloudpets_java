package com.spiraltoys.cloudpets2.adapters;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import com.google.common.hash.HashCode;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.spiraltoys.cloudpets2.model.events.LocalDatastoreChangedEvent;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class ParseQueryAdapter<VH extends ParseObjectViewHolder<PO>, PO extends ParseObject> extends Adapter<VH> {
    private Comparator<PO> mComparator;
    private List<PO> mParseObjects = new ArrayList();
    private ParseQuery<PO> mQuery;

    public static abstract class ParseObjectViewHolder<PO> extends ViewHolder {
        public abstract void setParseObject(PO po);

        public ParseObjectViewHolder(View itemView) {
            super(itemView);
        }
    }

    public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

    public ParseQueryAdapter(ParseQuery<PO> query) {
        this.mQuery = query;
        setHasStableIds(true);
        reloadData();
        EventBus.getDefault().register(this);
    }

    public ParseQueryAdapter(ParseQuery<PO> query, Comparator<PO> comparator) {
        this.mQuery = query;
        this.mComparator = comparator;
        setHasStableIds(true);
        reloadData();
        EventBus.getDefault().register(this);
    }

    public void onBindViewHolder(VH holder, int position) {
        holder.setParseObject(this.mParseObjects.get(position));
    }

    public int getItemCount() {
        return this.mParseObjects.size();
    }

    public void reloadData() {
        try {
            this.mQuery.findInBackground(new FindCallback<PO>() {
                public void done(List<PO> results, ParseException e) {
                    if (e != null) {
                        e.printStackTrace();
                        return;
                    }
                    ParseQueryAdapter.this.mParseObjects = results;
                    if (ParseQueryAdapter.this.mComparator != null) {
                        Collections.sort(ParseQueryAdapter.this.mParseObjects, ParseQueryAdapter.this.mComparator);
                    }
                    ParseQueryAdapter.this.notifyDataSetChanged();
                }
            });
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    public void release() {
        EventBus.getDefault().unregister(this);
    }

    public long getItemId(int position) {
        return HashCode.fromBytes(((ParseObject) this.mParseObjects.get(position)).getObjectId().getBytes()).asLong();
    }

    @DebugLog
    public void onEvent(LocalDatastoreChangedEvent event) {
        reloadData();
    }
}
