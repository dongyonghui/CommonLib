package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/6/006 12:29
 * 描述：添加或编辑骑手
 */
public class AddOrEditDeliveryManRequestBean extends BaseHttpRequestBean {
    public List<String> deliveryAddressIds;
    public String name;
    public String userName;
    public String phone;
    public String authType = "1";
    public String deliveryManId;
}
