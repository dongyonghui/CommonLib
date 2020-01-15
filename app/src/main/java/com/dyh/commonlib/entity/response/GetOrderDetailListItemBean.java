package com.dyh.commonlib.entity.response;

import android.text.TextUtils;

import com.dyh.commonlib.entity.BaseHttpResponseBean;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.dw.util.TimeUtil;

import java.util.Date;
import java.util.List;

/**
 * 作者：DongYonghui
 * 时间：2019/9/20/020
 * 邮箱：648731994@qq.com
 * 描述：获取订单详情列表数据
 */
public class GetOrderDetailListItemBean extends BaseHttpResponseBean {

    private String address;
    private String count;
    private String createAt;
    private String deliveryAddressId;
    private String deliveryAddressName;
    private String floor;
    private String groupUseType;
    private String name;
    private String orderId;
    private String orderIdStr;
    private String orderType;
    private String phone;
    private String price;
    private String pricePreferential;
    private String priceSell;
    private String remark;
    private String shopId;
    private String shopIdStr;
    private String shopLogo;
    private String shopName;
    private String shopOrderNumber;
    private String shopPhone;
    private String skuId;
    private String status;
    private String takeMealPoint;
    private String takeMealPointName;
    private String userName;

    private List<FloorMealListBean.SkuItemsBean> items;

    public List<FloorMealListBean.SkuItemsBean> getItems() {
        return items;
    }

    public void setItems(List<FloorMealListBean.SkuItemsBean> items) {
        this.items = items;
    }

    /**
     * 获取楼层或取餐点信息
     * groupUseType==4 送餐上楼 显示层  groupUseType==3 取餐点自取显示取餐点
     *
     * @return
     */
    public String getTakeMealPointOrFloorInfo() {
        if ("3".equals(groupUseType)) {
            return takeMealPointName;
        }
        if ("4".equals(groupUseType)) {
            return floor + "层";
        }

        return null;
    }

    /**
     * @return true表示需要展示公司门牌号，否则不需要
     */
    public boolean isNeedShowCompanyDoorName() {
        if ("4".equals(groupUseType) && !TextUtils.isEmpty(remark)) {
            return true;
        }
        return false;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCreateAt() {
        return TimeUtil.date2String(new Date(MathUtil.getLongNumber(createAt)), "yyyy/MM/dd HH:mm:ss");
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
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

    public String getGroupUseType() {
        return groupUseType;
    }

    public void setGroupUseType(String groupUseType) {
        this.groupUseType = groupUseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderIdStr() {
        return orderIdStr;
    }

    public void setOrderIdStr(String orderIdStr) {
        this.orderIdStr = orderIdStr;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPricePreferential() {
        return pricePreferential;
    }

    public void setPricePreferential(String pricePreferential) {
        this.pricePreferential = pricePreferential;
    }

    public String getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(String priceSell) {
        this.priceSell = priceSell;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopIdStr() {
        return shopIdStr;
    }

    public void setShopIdStr(String shopIdStr) {
        this.shopIdStr = shopIdStr;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopOrderNumber() {
        return shopOrderNumber;
    }

    public void setShopOrderNumber(String shopOrderNumber) {
        this.shopOrderNumber = shopOrderNumber;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTakeMealPoint() {
        return takeMealPoint;
    }

    public void setTakeMealPoint(String takeMealPoint) {
        this.takeMealPoint = takeMealPoint;
    }

    public String getTakeMealPointName() {
        return takeMealPointName;
    }

    public void setTakeMealPointName(String takeMealPointName) {
        this.takeMealPointName = takeMealPointName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
