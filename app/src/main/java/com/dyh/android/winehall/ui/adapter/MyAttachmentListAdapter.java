package com.dyh.android.winehall.ui.adapter;

import androidx.annotation.NonNull;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.response.AttachmentItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/18 0018
 * description:课程详情--全部评价列表
 */
public class MyAttachmentListAdapter extends BaseQuickRecyclerViewAdapter<AttachmentItemBean> {
    public MyAttachmentListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AttachmentItemBean item) {
        helper.setText(R.id.mNameTextView, item.getName())
                .setText(R.id.mSizeTextView, item.getSize())
                .setGone(R.id.mLineBottom, helper.getLayoutPosition() < (getItemCount() - 1));
    }
}
