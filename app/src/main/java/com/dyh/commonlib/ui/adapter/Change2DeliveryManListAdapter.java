package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.DeliveryManItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：改派骑手列表
 */
public class Change2DeliveryManListAdapter extends BaseQuickRecyclerViewAdapter<DeliveryManItemBean> {
    public Change2DeliveryManListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeliveryManItemBean item) {

        helper.setText(R.id.mNameTextView, item.getName())//名称
                .setText(R.id.mPhoneTextView, item.getPhone());
    }
}
