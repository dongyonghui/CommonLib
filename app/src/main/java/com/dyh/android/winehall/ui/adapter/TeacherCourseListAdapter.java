package com.dyh.android.winehall.ui.adapter;

import androidx.annotation.NonNull;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.response.HomeLearnCourseItemResponseBean;
import com.dyh.android.winehall.util.MyTimeFormatUtil;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;
import com.dyh.common.lib.weigit.powertext.LabelTextView;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/15 0015
 * description: 课节列表
 */
public class TeacherCourseListAdapter extends BaseQuickRecyclerViewAdapter<HomeLearnCourseItemResponseBean> {
    public TeacherCourseListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeLearnCourseItemResponseBean lessonsBean) {
        LabelTextView mTitleLabelTextView = helper.getView(R.id.mTitleLabelTextView);
        mTitleLabelTextView.setOriginalText(lessonsBean.getTitle());
        mTitleLabelTextView.setLabelText(mContext.getString(R.string.tryAndSee));
        helper
                .setText(R.id.mCourseDurationTextView,
                        MyTimeFormatUtil.getDurationTimeString(MathUtil.getIntegerNumber(lessonsBean.getDuration())))
                .setGone(R.id.mLineBottom, helper.getLayoutPosition() < (getItemCount() - 1));
    }
}
