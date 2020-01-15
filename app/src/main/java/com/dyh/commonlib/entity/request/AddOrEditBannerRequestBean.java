package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/26/026 10:44
 * 描述：新建或编辑Banner
 */
public class AddOrEditBannerRequestBean extends BaseHttpRequestBean {
    public String bannerId;
    public String imageUrl;
    public String toUrl;
    public List<DeliveryAddressManageItemBean> deliveryAddress;
    public String type;
}
