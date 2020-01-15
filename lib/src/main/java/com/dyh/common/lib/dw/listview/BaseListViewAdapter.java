
package com.dyh.common.lib.dw.listview;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseListViewAdapter<T> extends BaseAdapter {

    private final List<T> list = new LinkedList<T>();

    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    public Context mContext;

    private LayoutInflater inflater;

    /**
     * @return the inflater
     */
    public LayoutInflater getInflater() {
        return inflater;
    }

    /**
     * @param inflater the inflater to set
     */
    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public BaseListViewAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (this.list == null) {
            return 0;
        }
        return this.list.size();
    }

    @Override
    public T getItem(int arg0) {
        if (arg0 < 0 || this.list == null || arg0 >= list.size()) {
            return null;
        }
        return this.list.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        if (this.list == null) {
            return 0;
        }
        return position;
    }

    public void addListBottom(List<T> list) {
        if (list != null) {
            this.list.addAll(list);
        }
        this.notifyDataSetChanged();
    }

    public void addListBottom(T item) {
        if (list != null) {
            this.list.add(item);
        }
        this.notifyDataSetChanged();
    }

    public void removeItem(T item) {
        if (list != null) {
            this.list.remove(item);
        }
        this.notifyDataSetChanged();
    }

    public void clearList() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void delete(int position) {
        if ((list != null) && (position >= 0) && (position < list.size())) {
            list.remove(position);
        }
        notifyDataSetChanged();
    }

    public void addListTop(List<T> list) {
        if (list != null) {
            this.list.addAll(0, list);
        }
        notifyDataSetChanged();
    }

    public void addListTop(T item) {
        if (list != null) {
            this.list.add(0, item);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = createView(position, convertView, parent);
        return view;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
    }

    public abstract View createView(int position, View convertView, ViewGroup parent);
}
