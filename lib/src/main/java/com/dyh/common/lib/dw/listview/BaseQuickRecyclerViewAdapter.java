package com.dyh.common.lib.dw.listview;

import android.support.annotation.Nullable;

import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.List;

/**
 * RecyclerView适配器基类
 *
 * @param <T>
 */
public abstract class BaseQuickRecyclerViewAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    public static final int PAGE_SIZE = 20;

    public BaseQuickRecyclerViewAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void setNewData( List<T> data) {
        super.setNewData(data);
        refreshLoadMoreStatus(getItemCount());
    }

    private void refreshLoadMoreStatus(int count) {
        if (isLoadMoreEnable()) {
            if (count >= PAGE_SIZE) {
                loadMoreComplete();
            } else {
                loadMoreEnd();
            }
        }
    }

    public void addListBottom(List<T> list) {
        if (list == null) {
            return;
        }
        addData(list);
        refreshLoadMoreStatus(list.size());
        super.notifyDataSetChanged();
    }

    public void addListBottom(T item) {
        if (item == null) {
            return;
        }
        addData(item);
        refreshLoadMoreStatus(1);
        super.notifyDataSetChanged();
    }

    public void removeItem(T item) {
        super.mData.remove(item);
        super.notifyDataSetChanged();
    }

    public void clearList() {
        super.mData.clear();
        super.notifyDataSetChanged();
    }

    public void delete(int position) {
        if (position >= 0 && position < super.mData.size()) {
            super.mData.remove(position);
            super.notifyDataSetChanged();
        }
    }

    public void addListTop(List<T> list) {
        if (list == null) {
            return;
        }
        super.mData.addAll(0, list);
        refreshLoadMoreStatus(list.size());
        super.notifyDataSetChanged();
    }

    public void addListTop(T item) {
        if (item == null) {
            return;
        }
        super.mData.add(0, item);
        refreshLoadMoreStatus(1);
        super.notifyDataSetChanged();
    }
}