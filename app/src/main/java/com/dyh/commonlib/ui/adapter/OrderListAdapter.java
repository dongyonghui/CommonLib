package com.dyh.commonlib.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.response.OrderItemBean;
import com.dyh.common.lib.dw.listview.BaseQuickRecyclerViewAdapter;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.dw.util.TimeUtil;
import com.dyh.common.lib.recyclerview_helper.BaseViewHolder;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:33
 * 描述：订单列表
 */
public class OrderListAdapter extends BaseQuickRecyclerViewAdapter<OrderItemBean> {
    public OrderListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderItemBean item) {
        List<OrderItemBean.GoodsItemsBean> itemList = item.getItems();
        int sumCount = 0;
        double sumPrice = 0;
        if (null != itemList) {
            for (OrderItemBean.GoodsItemsBean goodsItemsBean : itemList) {
                sumCount = MathUtil.getIntegerNumber(goodsItemsBean.getCount()) + sumCount;
                sumPrice = MathUtil.sum(sumPrice, MathUtil.mul(goodsItemsBean.getPriceSell(), goodsItemsBean.getCount()));
            }

        }
        String refundWayInfo = null;
        String[] reufndWayArray = mContext.getResources().getStringArray(R.array.refundWay);
        int reundWay = MathUtil.getIntegerNumber(item.getRefundWay());
        if (reufndWayArray.length > reundWay) {
            refundWayInfo = reufndWayArray[reundWay];
        }

        //取餐码
        helper.setText(R.id.mTakeNumberTextView, mContext.getString(R.string.formatString_takeNumber, item.getNumber()))
                //已退标记
                .setGone(R.id.mRefundedFlagTextView, ServerConstants.RefundStatus.isRefundedAlready(MathUtil.getIntegerNumber(item.getRefundStatus())))
                //退款信息
                .setGone(R.id.mRefundInfoRootView, ServerConstants.RefundStatus.isRefundedAlready(MathUtil.getIntegerNumber(item.getRefundStatus())))
                //退款操作按钮
                .setGone(R.id.mOptionButtonRootView, !ServerConstants.RefundStatus.isRefundedAlready(MathUtil.getIntegerNumber(item.getRefundStatus())))
                //订单号
                .setText(R.id.mOrderNoTextView, item.getOrderIdStr())
                .setText(R.id.mMergedNoTextView, item.getMergeOrderIdStr())
                //下单用户
                .setText(R.id.mCustomerInfoTextView, item.getCustomerFormatInfo(mContext))
                //送达时间
                .setText(R.id.mArrivedTimeTextView, item.getArrivedTimeInfo())
                //总计
                .setText(R.id.mTotalTextView, mContext.getString(R.string.formatString_goodsTotalInfo, String.valueOf(sumCount), MathUtil.getMoneyNumber(sumPrice)))
                //店铺信息
                .setText(R.id.mShopInfoTextView, item.getShopFormatInfo(mContext))
                //骑手信息
                .setText(R.id.mDeliveryManInfoTextView, item.getDeliverMansInfo())
                .setText(R.id.mOrderCreateTimeTextView, TimeUtil.date2String(TimeUtil.millis2Date(MathUtil.getLongNumber(item.getCreateTime())), "yyyy-MM-dd HH:mm:ss"))
                .setText(R.id.mReasonTextView, item.getRefundReasons())
                .setText(R.id.mResponsiblePartyTextView, refundWayInfo)
                .setText(R.id.mRefundGoodsPartyTextView, item.getRefundInfo())
                .setText(R.id.mRefundTimeTextView, item.getFormatRefundTime())
                .addOnClickListener(R.id.mRefundTextView)
                .addOnClickListener(R.id.mRefundPartTextView);

        //设置商品列表
        RecyclerView goodsList = helper.getView(R.id.mGoodsRecyclerView);
        goodsList.setNestedScrollingEnabled(false);
        goodsList.setLayoutManager(new LinearLayoutManager(mContext));
        OrderGoodsListAdapter goodsListAdapter = new OrderGoodsListAdapter(R.layout.item_order_goods_list);
        goodsListAdapter.setNewData(item.getItems());
        goodsList.setAdapter(goodsListAdapter);
    }
}
