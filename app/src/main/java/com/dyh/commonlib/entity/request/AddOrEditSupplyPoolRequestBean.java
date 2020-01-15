package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;
import com.dyh.commonlib.entity.response.ShopItemBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/6/006 12:29
 * 描述：添加或编辑供给池
 */
public class AddOrEditSupplyPoolRequestBean extends BaseHttpRequestBean {
    public String supplyPoolId;
    public String name;
    public String lon;
    public String lat;
    public String address;
    public String points;
    public List<ShopItemBean> shopList;
}
