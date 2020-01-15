package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/16/016 14:05
 * 描述：订单退款
 */
public class RefundOrderRequestBean extends BaseHttpRequestBean {
    public String refundReasons;
    public String refundWay;
    public String mergeOrderId;
    public String orderId;
    public List<ItemBean> items;

    public static class ItemBean {
        public String skuId;
        public int amount;
        public String name;
    }
}
