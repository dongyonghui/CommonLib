package com.dyh.commonlib.entity.printer;

import com.dyh.commonlib.entity.response.PrintDeliveryListItemResultBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 日期：2019/10/30/030
 * BD订单小票模板头部Bean
 */
public class DeliveryAddressPrinterItemBean {
    private String orderNumber;// <string>,       // 取餐码
    private String userName;// <string>,       // 用户名
    private String userPhone;// <string>,      // 用户手机
    private String userAddress;// <string>,    // 楼宇
    private String floorAndRemark;// <string>,//楼层门牌号
    private List<PrintDeliveryListItemResultBean.SkuItemBean> skuList;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getFloorAndRemark() {
        return floorAndRemark;
    }

    public void setFloorAndRemark(String floorAndRemark) {
        this.floorAndRemark = floorAndRemark;
    }

    public List<PrintDeliveryListItemResultBean.SkuItemBean> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<PrintDeliveryListItemResultBean.SkuItemBean> skuList) {
        this.skuList = skuList;
    }
}
