package com.dyh.common.lib.dw.listview;

import androidx.annotation.Nullable;

import com.dyh.common.lib.recyclerview_helper.BaseSectionQuickAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;
import com.dyh.common.lib.recyclerview_helper.entity.SectionEntity;

import java.util.List;

/**
 * RecyclerView适配器基类
 *
 * @param <T>
 */
public abstract class BaseSectionQuickRecyclerViewAdapter<T extends SectionEntity> extends BaseSectionQuickAdapter<T, BaseViewHolder> {

    public static final int PAGE_SIZE = 20;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public BaseSectionQuickRecyclerViewAdapter(int layoutResId, int sectionHeadResId, List<T> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    public void setNewData(@Nullable List<T> data) {
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