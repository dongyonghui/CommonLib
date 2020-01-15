package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;

/**
 * 作者：DongYonghui
 * 日期：2019/10/30/030
 * 获取打印配送订单小票（用户）
 */
public class GetPrintDeliveryListRequest extends BaseHttpRequestBean {
    public String useType;
    public String cityId = "610100";
}
