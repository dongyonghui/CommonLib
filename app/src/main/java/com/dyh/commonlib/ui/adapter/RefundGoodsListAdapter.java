package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.OrderItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;
import com.dyh.common.lib.weigit.number.SnappingStepper;
import com.dyh.common.lib.weigit.number.listener.SnappingStepperValueChangeListener;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/17/017 10:02
 * 描述：退款商品列表
 */
public class RefundGoodsListAdapter extends BaseQuickRecyclerViewAdapter<OrderItemBean.GoodsItemsBean> {
    public RefundGoodsListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderItemBean.GoodsItemsBean item) {
        helper.setText(R.id.mGoodsNameTextView, item.getName());
        SnappingStepper snappingStepper = helper.getView(R.id.mSnappingStepper);
        snappingStepper.setMinValue(0);
        snappingStepper.setValue(item.refundCount);
        snappingStepper.setMaxValue(MathUtil.getIntegerNumber(item.getCount()));
        snappingStepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                item.refundCount = value;
            }
        });
    }
}
