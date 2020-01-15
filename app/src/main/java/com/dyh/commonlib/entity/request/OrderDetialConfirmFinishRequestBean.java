package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;

/**
 * 作者：DongYonghui
 * 时间：2019/9/19/019
 * 邮箱：648731994@qq.com
 * 描述：确认完成(订单详情)
 */
public class OrderDetialConfirmFinishRequestBean extends BaseHttpRequestBean {
    public String orderId;
    public String mergeOrderId;
}
