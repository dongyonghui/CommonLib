package com.dyh.android.winehall.ui.adapter;

import androidx.annotation.NonNull;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.common.GenreBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/7 0007 17:44
 * description: 课程详情标签列表
 */
public class CourseDetailFlagListAdapter extends BaseQuickRecyclerViewAdapter<GenreBean> {

    public CourseDetailFlagListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GenreBean item) {
        helper.setText(R.id.mTitleTextView, item.getTitle());
    }

}