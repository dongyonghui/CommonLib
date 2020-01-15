package com.dyh.commonlib.entity.response;

import com.dyh.commonlib.entity.BaseHttpResponseBean;
import com.dyh.commonlib.entity.local.LocalFloorMealCompanyListItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：DongYonghui
 * 时间：2019/9/19/019
 * 邮箱：648731994@qq.com
 * 描述：订单列表配送中的
 */
public class HomeOrderSendingListItemBean extends BaseHttpResponseBean {

    public String addressLat;
    public String addressLon;
    private String address;
    private String deliveryAddressId;
    private String deliveryAddressName;
    private String groupUseType;
    private String deliveryArriveTime;
    private List<FloorMealListBean> floorList;
    private List<String> groupIds;
    private List<FloorMealListBean> mealList;

    public String getDeliveryArriveTime() {
        return deliveryArriveTime;
    }

    public void setDeliveryArriveTime(String deliveryArriveTime) {
        this.deliveryArriveTime = deliveryArriveTime;
    }

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
//        <!-- 送餐上楼拼团成功的显示楼层
//        送餐上楼拼团失败的显示自取点
//        自取显示自取点      -->
        for (FloorMealListBean floorMealListBean : floorList) {
            if ("3".equals(groupUseType)) {
                floorMealListBean.setFloor(null);
            } else {
                floorMealListBean.setTakeMealPoint(null);
                floorMealListBean.setTakeMealPointName(null);
            }

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

    /**
     * @return 返货楼层-公司 列表信息
     */
    public List<LocalFloorMealCompanyListItemBean> getMealCompanyList() {
        List<LocalFloorMealCompanyListItemBean> list = new ArrayList<>();
        mealList = getMealList();
        if (mealList != null) {
            for (FloorMealListBean floorMealListBean : mealList) {
                if (floorMealListBean.getGroupCompanys() != null) {
                    for (FloorMealListBean.GroupCompany groupCompany : floorMealListBean.getGroupCompanys()) {
                        LocalFloorMealCompanyListItemBean itemBean = new LocalFloorMealCompanyListItemBean();
                        itemBean.deliveryAddressName = getDeliveryAddressName();
                        itemBean.deliveryArriveTime = getDeliveryArriveTime();
                        itemBean.floorMealListBean = floorMealListBean;
                        itemBean.groupCompany = groupCompany;
                        list.add(itemBean);
                    }
                }
            }
        }
        floorList = getFloorList();
        if (floorList != null) {
            for (FloorMealListBean floorMealListBean : floorList) {
                if (floorMealListBean.getGroupCompanys() != null) {
                    for (FloorMealListBean.GroupCompany groupCompany : floorMealListBean.getGroupCompanys()) {
                        LocalFloorMealCompanyListItemBean itemBean = new LocalFloorMealCompanyListItemBean();
                        itemBean.deliveryAddressName = getDeliveryAddressName();
                        itemBean.deliveryArriveTime = getDeliveryArriveTime();
                        itemBean.floorMealListBean = floorMealListBean;
                        itemBean.groupCompany = groupCompany;
                        list.add(itemBean);
                    }
                }
            }
        }

        return list;
    }
}
