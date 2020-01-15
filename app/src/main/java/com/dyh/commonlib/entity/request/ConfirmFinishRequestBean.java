package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 时间：2019/9/19/019
 * 邮箱：648731994@qq.com
 * 描述：确认完成
 */
public class ConfirmFinishRequestBean extends BaseHttpRequestBean {
    public List<String> groupIdList;
    public List<String> takeMealPointList;
    public List<String> openidList;
    public String deliveryAddressId;
    public String deliveryDetailInfo;//地址详细信息，本地展示使用
}
