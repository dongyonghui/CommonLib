package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：配送地址和周边商家列表
 */
public class DeliveryAddressAndShopsListAdapter extends BaseQuickRecyclerViewAdapter<DeliveryAddressManageItemBean> {
    public DeliveryAddressAndShopsListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DeliveryAddressManageItemBean item) {

        helper.setText(R.id.mDeliverAddressTextView, item.getRemark())//地址名称
                .setText(R.id.mShopInfoTextView, mContext.getString(R.string.formatString_aroundShops, item.getShopsInfo()))//周边店铺
        ;
    }
}
