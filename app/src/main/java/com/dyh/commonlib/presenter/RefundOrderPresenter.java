package com.dyh.commonlib.presenter;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.IntentConstants;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.request.RefundOrderRequestBean;
import com.dyh.commonlib.entity.response.OrderItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单退款
 */
public class RefundOrderPresenter extends MVPPresenter<IDefaultOptionView> {


    public RefundOrderPresenter( IDefaultOptionView view) {
        super(view);
    }

    /**
     * 提交请求
     *
     * @param flag
     * @param reasonInfo
     * @param checkedRedioId
     */
    public void refund(OrderItemBean orderItemBean, int flag, String reasonInfo, int checkedRedioId) {
        if (orderItemBean == null) {
            return;
        }
        RefundOrderRequestBean requestBean = new RefundOrderRequestBean();
        //原因
        requestBean.refundReasons = reasonInfo;
        //责任方
        switch (checkedRedioId) {
            case R.id.mTpcRadioButton:
                requestBean.refundWay = ServerConstants.RefundWay.TPC;
                break;
            case R.id.mShopRadioButton:
                requestBean.refundWay = ServerConstants.RefundWay.SHOP;
                break;
            case R.id.mDeliveryManRadioButton:
                requestBean.refundWay = ServerConstants.RefundWay.DELIVERY_MAN;
                break;
        }
        String url = ApiPathConstants.REFUND_ORDER;
        switch (flag) {
            case IntentConstants.Flag.REFUND_ORDER_LIST://全部退款
                requestBean.mergeOrderId = orderItemBean.getMergeOrderId();
                break;
            case IntentConstants.Flag.REFUND_ORDER_PART://部分退款
                //如果是部分退款，则添加退款的商品
                List<RefundOrderRequestBean.ItemBean> itemList = new ArrayList<>();
                List<OrderItemBean.GoodsItemsBean> goodsItemsBeanList = orderItemBean.getItems();
                if (null != goodsItemsBeanList) {
                    for (OrderItemBean.GoodsItemsBean goodsItemsBean : goodsItemsBeanList) {
                        if(goodsItemsBean.refundCount > 0){
                            RefundOrderRequestBean.ItemBean item = new RefundOrderRequestBean.ItemBean();
                            item.amount = goodsItemsBean.refundCount;
                            item.name = goodsItemsBean.getName();
                            item.skuId = goodsItemsBean.getSkuId();
                            itemList.add(item);
                        }
                    }
                    requestBean.items = itemList;
                }
            case IntentConstants.Flag.REFUND_ORDER://整单退款
                requestBean.orderId = orderItemBean.getOrderIdStr();
                url = ApiPathConstants.REFUND_ORDER_PART;
                break;
        }


        EasyHttp.post(url)
                .upObject(requestBean)
                .execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(Optional<Object> listOptional) {
                        getView().onOptionOk();
                    }
                });
    }
}
