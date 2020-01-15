package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 时间：2019/9/20/020
 * 邮箱：648731994@qq.com
 * 描述：获取订单详情列表
 */
public class GetOrderDetailListRequestBean extends BaseHttpRequestBean {
    public String page;//<int>
    public String mealOrFloorContainCompany;//包含公司信息的楼层或者取餐点
    public String tabId;// <int>   // 必传: 2待送达详情；3已送达列表
    public String takeMealPoint;//<Long> //取餐点id或楼层
    public String deliveryAddressId;//<long> //楼宇id
    public String deliveryAddressName;//<long> //楼宇id
    public List<String> groupIdList;
    public List<String> openidList;
}
