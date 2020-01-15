package com.dyh.commonlib.entity.local;

import android.text.TextUtils;

import com.dyh.commonlib.entity.response.FloorMealListBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 时间：2019/11/1/001
 * 邮箱：648731994@qq.com
 * 描述：订单列表公司层级信息
 */
public class LocalFloorMealCompanyListItemBean {
    public String deliveryArriveTime;
    public String deliveryAddressName;
    public FloorMealListBean floorMealListBean;//楼层信息
    public FloorMealListBean.GroupCompany groupCompany;//公司信息

    /**
     * @return 返回取餐点或者楼层-公司信息
     */
    public String getMealOrFloorContainCompany() {
        if (floorMealListBean == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(floorMealListBean.getMealOrFloor());
        if (!TextUtils.isEmpty(floorMealListBean.getFloor()) && groupCompany != null
                && !TextUtils.isEmpty(groupCompany.getCompanyNumber())) {
            stringBuilder.append(groupCompany.getCompanyNumber());
        }
        return stringBuilder.toString();
    }

    public List<String> getGroupIds() {
        if (floorMealListBean == null) {
            return null;
        }
        return floorMealListBean.getGroupIds();
    }

    public String getDeliveryAddressId() {
        if (floorMealListBean == null) {
            return null;
        }
        return floorMealListBean.getDeliveryAddressId();
    }

    public List<String> getOpenIds() {
        return null;
    }

    public String getMealPointOrFloor() {
        if (floorMealListBean == null) {
            return null;
        }
        return floorMealListBean.getMealPointOrFloor();
    }

    public String getDeliveryArriveTime() {
        return deliveryArriveTime;
    }

    /**
     * @return true表示需要选择上楼后的取餐地点
     */
    public boolean isNeedSelectTakePlace() {
        //如果是送餐上楼，则需要提示选择取餐点
        if (floorMealListBean != null && !TextUtils.isEmpty(floorMealListBean.getFloor())) {
            return true;
        }
        return false;
    }

    public String getTakeMealAddress() {
        return deliveryAddressName + getMealOrFloorContainCompany();
    }
}
