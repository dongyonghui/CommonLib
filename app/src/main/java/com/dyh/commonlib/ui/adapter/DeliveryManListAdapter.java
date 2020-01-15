package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.entity.response.DeliveryManItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：骑手列表
 */
public class DeliveryManListAdapter extends BaseQuickRecyclerViewAdapter<DeliveryManItemBean> {
    public DeliveryManListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeliveryManItemBean item) {

        StringBuilder deliveryAddressInfo = new StringBuilder();
        List<DeliveryAddressManageItemBean> addressManageItemBeanList = item.getDeliveryAddressList();
        if (null != addressManageItemBeanList) {
            for (int i = 0; i < addressManageItemBeanList.size(); i++) {
                if (i > 5) {
                    break;
                }
                deliveryAddressInfo.append(addressManageItemBeanList.get(i).getRemark()).append(";");
            }
        }


        helper.setText(R.id.mNameTextView, item.getName())//名称
                .setText(R.id.mPhoneTextView, item.getPhone())//
                .setText(R.id.mDeliveryAddressTextView, deliveryAddressInfo.toString())//配送地址
                .addOnClickListener(R.id.mChangeDeliveryManTextView)
                .addOnClickListener(R.id.mDeleteTextView);
    }
}
