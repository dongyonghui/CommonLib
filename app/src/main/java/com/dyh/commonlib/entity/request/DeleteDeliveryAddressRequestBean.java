package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/3/003 9:47
 * 描述：删除配送地址请求
 */
public class DeleteDeliveryAddressRequestBean extends BaseHttpRequestBean {
    public List<String> deliveryAddressIds;
}
