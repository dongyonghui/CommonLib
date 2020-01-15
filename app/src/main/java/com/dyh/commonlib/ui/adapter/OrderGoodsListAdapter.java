package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.OrderItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/13/013 15:58
 * 描述：订单商品列表
 */
public class OrderGoodsListAdapter extends BaseQuickRecyclerViewAdapter<OrderItemBean.GoodsItemsBean> {
    public OrderGoodsListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderItemBean.GoodsItemsBean item) {
        helper.setText(R.id.mGoodsNameTextView, item.getName())
                .setText(R.id.mGoodsPriceInfoTextView, mContext.getString(R.string.formatString_goodsPriceInfo,
                        item.getPriceSell(), item.getCount()));
    }
}
