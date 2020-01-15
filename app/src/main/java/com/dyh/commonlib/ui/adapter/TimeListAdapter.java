package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.TimeItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：时间列表
 */
public class TimeListAdapter extends BaseQuickRecyclerViewAdapter<TimeItemBean> {
    private boolean isAdmin = false;

    public TimeListAdapter(int layoutResId, boolean isAdmin) {
        super(layoutResId);
        this.isAdmin = isAdmin;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TimeItemBean item) {
        helper.setText(R.id.mNameTextView, item.getName())//
                .setGone(R.id.mNameRow, !TextUtils.isEmpty(item.getName()))
                .setGone(R.id.mEditTextView, !isAdmin)//管理员不可见编辑按钮
                .setText(R.id.mStartTimeTextView, item.getEndTimeStr())//
                .setText(R.id.mEndTimeTextView, item.getSendTimeStr())//
                .setText(R.id.mCityTextView, item.getCity())//
                .addOnClickListener(R.id.mEditTextView)
                .addOnClickListener(R.id.mDeleteTextView);
    }
}
