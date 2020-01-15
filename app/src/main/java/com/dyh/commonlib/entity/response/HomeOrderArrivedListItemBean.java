package com.dyh.commonlib.entity.response;

import com.dyh.commonlib.entity.BaseHttpResponseBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 时间：2019/9/19/019
 * 邮箱：648731994@qq.com
 * 描述：订单列表已送达的
 */
public class HomeOrderArrivedListItemBean extends BaseHttpResponseBean {

    private String address;
    private String deliveryAddressId;
    private String deliveryAddressName;
    private String groupUseType;
    private List<FloorMealListBean> floorList;
    private List<String> groupIds;
    private List<FloorMealListBean> mealList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getGroupUseType() {
        return groupUseType;
    }

    public void setGroupUseType(String groupUseType) {
        this.groupUseType = groupUseType;
    }

    public List<FloorMealListBean> getFloorList() {
        if (null == floorList) {
            return null;
        }
        for (FloorMealListBean floorMealListBean : floorList) {
            floorMealListBean.setTakeMealPoint(null);
            floorMealListBean.setTakeMealPointName(null);
        }
        return floorList;
    }

    public void setFloorList(List<FloorMealListBean> floorList) {
        this.floorList = floorList;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    public List<FloorMealListBean> getMealList() {
        if (null == mealList) {
            return null;
        }
        for (FloorMealListBean floorMealListBean : mealList) {
            floorMealListBean.setFloor(null);
        }
        return mealList;
    }

    public void setMealList(List<FloorMealListBean> mealList) {
        this.mealList = mealList;
    }
}
