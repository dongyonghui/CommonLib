package com.dyh.android.winehall.ui.adapter;

import android.graphics.Typeface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.response.CategoryItemResponseBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/4 0004 17:49
 * description: 分类tab适配器
 */
public class CategoryTabListAdapter extends BaseQuickRecyclerViewAdapter<CategoryItemResponseBean> {
    //当前选中的项
    private int mCurrentSelectedIndex = 0;

    public CategoryTabListAdapter(int layoutResId) {
        super(layoutResId);
    }

    public void setSelectedIndex(int position) {
        mCurrentSelectedIndex = position;
        notifyDataSetChanged();
    }

    public CategoryItemResponseBean getSelectedItem() {
        return getItem(mCurrentSelectedIndex);
    }

    @Override
    public void setNewData(@Nullable List<CategoryItemResponseBean> data) {
        mCurrentSelectedIndex = 0;
        super.setNewData(data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CategoryItemResponseBean item) {
        boolean isSelect = helper.getLayoutPosition() == mCurrentSelectedIndex;
        helper.setText(R.id.mItemTitleTextView, item.title)
                .setVisible(R.id.mItemDivideView, isSelect)
                .setTextResColor(R.id.mItemTitleTextView, isSelect ?
                        R.color.text_000 :
                        R.color.text_333)
                .setTypeface(R.id.mItemTitleTextView, isSelect ?
                        Typeface.DEFAULT_BOLD :
                        Typeface.DEFAULT);
    }

}
