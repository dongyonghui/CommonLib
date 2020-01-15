package com.dyh.commonlib.entity.printer;

import com.dyh.commonlib.entity.response.PrintDeliveryListItemResultBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 日期：2019/10/30/030
 * BD订单小票模板头部Bean
 */
public class DeliveryAddressPrinterHeaderBean {

    private String deliveryAddressName;// <string>,    // 楼宇
    private String floor;// <int>, // 楼层
    private String qrcode;// <int>, // 二维码
    private List<PersonInfoBean> personList;//用户列表
    private List<PrintDeliveryListItemResultBean.SkuItemBean> skuList;//用户列表
    public List<DeliveryAddressPrinterItemBean> printerItemBeanList;//小票个人分组项列表

    public List<PrintDeliveryListItemResultBean.SkuItemBean> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<PrintDeliveryListItemResultBean.SkuItemBean> skuList) {
        this.skuList = skuList;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public List<PersonInfoBean> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonInfoBean> personList) {
        this.personList = personList;
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

    public static class PersonInfoBean {
        private String userName;// <string>,       // 用户名
        private String userPhone;// <string>,      // 用户手机

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
    }
}
