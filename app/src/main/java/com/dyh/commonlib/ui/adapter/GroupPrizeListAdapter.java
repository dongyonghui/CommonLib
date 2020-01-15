package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.entity.response.GroupPrizeItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：团体奖励列表
 */
public class GroupPrizeListAdapter extends BaseQuickRecyclerViewAdapter<GroupPrizeItemBean> {
    public GroupPrizeListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GroupPrizeItemBean item) {
        StringBuilder deliveryAddressInfo = new StringBuilder();
        List<DeliveryAddressManageItemBean> addressManageItemBeanList = item.getDeliveryAddressList();
        if (null != addressManageItemBeanList) {
            for (int i = 0; i < addressManageItemBeanList.size(); i++) {
                if (i > 5) {
                    break;
                }
                deliveryAddressInfo.append(addressManageItemBeanList.get(i).getTitle()).append(";");
            }
        }


        helper.setText(R.id.mPrizeInfoTextView, item.getWareTextInfo(mContext))//
                .setText(R.id.mDeliverAddressTextView, deliveryAddressInfo.toString())//
                .setGone(R.id.mNotInputDeliverAddressTextView, ServerConstants.DEFAULT_INT_TRUE.equals(item.getDefaultAward()))
                .addOnClickListener(R.id.mEditTextView)
                .addOnClickListener(R.id.mDeleteTextView);
    }
}
