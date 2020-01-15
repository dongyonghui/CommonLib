package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpPageRequestBean;

public class GetTpcGoodsRequestBean extends BaseHttpPageRequestBean {
    public String auditingType = "0";
    public String auditingStatus = "0";
    public String cityId = "0";
    public String deliveryAddressId = "0";
    public String supplyPoolId = "0";
    public String name;
}
