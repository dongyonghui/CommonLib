package com.dyh.commonlib.entity.response;

import java.util.List;

/**
 * 作者：DongYonghui
 * 日期：2019/10/30/030
 * BD订单小票数据
 */
public class PrintDeliveryListItemResultBean {

    private String qrcode;// <string>,       // 二维码
    private String userName;// <string>,       // 用户名
    private String userPhone;// <string>,      // 用户手机
    private String deliveryAddressName;// <string>,    // 楼宇
    private String floor;// <int>, // 楼层
    private String remark;// <string>,//门牌号
    private String orderNumber;// <string>,//取餐码
    private String userAddress;// <string>,//用户地址
    private List<SkuItemBean> skuList;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
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

    public String getDeliveryAddressName() {
        return deliveryAddressName;
    }

    public void setDeliveryAddressName(String deliveryAddressName) {
        this.deliveryAddressName = deliveryAddressName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SkuItemBean> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SkuItemBean> skuList) {
        this.skuList = skuList;
    }

    public static class SkuItemBean {
        private String skuName;// <string>, // 菜名
        private String skuCount;// <int>     // 数量

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getSkuCount() {
            return skuCount;
        }

        public void setSkuCount(String skuCount) {
            this.skuCount = skuCount;
        }
    }
}
