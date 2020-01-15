package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.CategoryItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 15:33
 * 描述：选择分类列表适配器
 */
public class SelectedCategoryListAdapter extends BaseQuickRecyclerViewAdapter<CategoryItemBean> {

    public SelectedCategoryListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CategoryItemBean item) {
        helper.setText(R.id.mInfoTextView, item.getName());
    }
}
