package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.StatisticsResponseBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/19/019 11:12
 * 描述：爆款菜统计
 */
public class StatisticsHotGoodsAdapter extends BaseQuickRecyclerViewAdapter<StatisticsResponseBean.GoodsItemBean> {

    public StatisticsHotGoodsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, StatisticsResponseBean.GoodsItemBean item) {
        helper.setText(R.id.mNameTextView, item.skuName)
                .setText(R.id.mCountTextView, item.count)
                .setText(R.id.mPriceTextView, item.price)
                .setText(R.id.mShopTextView, item.shopName);
    }
}
