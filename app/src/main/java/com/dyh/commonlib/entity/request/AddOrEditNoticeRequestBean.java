package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/26/026 10:44
 * 描述：新建或编辑消息公告
 */
public class AddOrEditNoticeRequestBean extends BaseHttpRequestBean {
    public String noticeId;
    public String startTime;
    public String endTime;
    public String cityId;
    public String city;
    public String second = "0";
    public List<String> deliveryAddressIds;
    public List<String> noticeNameList;
}
