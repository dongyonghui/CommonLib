package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/26/026 10:44
 * 描述：新建或编辑配送地址
 */
public class AddOrEditCityTaskRequestBean extends BaseHttpRequestBean {
    public String orderTaskId;
    public String cityId;
    public String city;
    public String monthDate;
    public String targetOrderCount;
    public String targetTradePrice;
    public String operatorId;
    public String operatorName;
}
