package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 时间：2019/9/19/019
 * 邮箱：648731994@qq.com
 * 描述：确认送达
 */
public class ConfirmArrivedRequestBean extends BaseHttpRequestBean {
    public List<String> openidList;
    public List<String> groupIdList;
    public List<String> takeMealPointList;
    public String deliveryAddressId;
    public String takeMealAddress;
    public String deliveryArriveTime;
    public boolean isNeedSelectTakePlace;//是否需要选择上楼后的取餐地点，本地UI使用
}
