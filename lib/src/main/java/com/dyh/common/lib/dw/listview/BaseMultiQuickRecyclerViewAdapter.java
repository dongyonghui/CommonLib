package com.dyh.common.lib.dw.listview;

import android.content.Context;

import androidx.annotation.Nullable;

import com.dyh.common.lib.recyclerview_helper.BaseMultiItemQuickAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;
import com.dyh.common.lib.recyclerview_helper.entity.MultiItemEntity;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/6 0006 13:30
 * description:
 */
public abstract class BaseMultiQuickRecyclerViewAdapter<T extends MultiItemEntity> extends BaseMultiItemQuickAdapter<T, BaseViewHolder> {
    public static final int PAGE_SIZE = 20;

    public BaseMultiQuickRecyclerViewAdapter(Context context) {
        super(null);
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
        int startPosition = getItemCount();
        addData(list);
        refreshLoadMoreStatus(list.size());
        if (startPosition >= 0) {
            notifyItemRangeChanged(startPosition, list.size());
        } else {
            super.notifyDataSetChanged();
        }
    }

    public void addListBottom(T item) {
        if (item == null) {
            return;
        }
        int startPosition = getItemCount();
        addData(item);
        refreshLoadMoreStatus(1);
        if (startPosition >= 0) {
            notifyItemRangeChanged(startPosition, 1);
        } else {
            super.notifyDataSetChanged();
        }
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
