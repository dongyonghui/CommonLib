package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;
import com.dyh.commonlib.entity.response.ShopItemBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/26/026 10:44
 * 描述：新建或编辑配送地址
 */
public class AddOrEditDeliveryAddressRequestBean extends BaseHttpRequestBean {

    /**
     * offDutyTime : 21:00
     * useType : 1
     * deliveryAddressId : null
     * address : 222
     * remark : 22
     * lat : 39.990152
     * lon : 116.805201
     * firstDeliveryFee : 1
     * deliveryFee : 1
     * freeMoney : 0
     * personPayMoney : 0
     * mealAddresses : [{"mealAddress":"东北门","lat":39.990559,"lon":116.805547}]
     * provinceId : 130000
     * province : 河北省
     * cityId : 131000
     * city : 廊坊市
     * districtId : 131082
     * district : 三河市
     * shops : [{"shopId":110010000000001,"name":"建斌超市","address":"五道口(地铁站)","status":1,"phone":"18895322989","date":"2018-05-14 15:31:45","lat":39.992894,"lon":116.337742}]
     */

    private String offDutyTime;
    private String useType;
    private String deliveryAddressId;
    private String address;
    private String remark;
    private String lat;
    private String lon;
    private String firstDeliveryFee;
    private String deliveryFee;
    private String freeMoney;
    private String personPayMoney;
    private String provinceId;
    private String province;
    private String cityId;
    private String city;
    private String districtId;
    private String district;
    private List<MealAddressesBean> mealAddresses;
    private List<ShopItemBean> shops;

    public List<ShopItemBean> getShops() {
        return shops;
    }

    public void setShops(List<ShopItemBean> shops) {
        this.shops = shops;
    }

    public String getOffDutyTime() {
        return offDutyTime;
    }

    public void setOffDutyTime(String offDutyTime) {
        this.offDutyTime = offDutyTime;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getFirstDeliveryFee() {
        return firstDeliveryFee;
    }

    public void setFirstDeliveryFee(String firstDeliveryFee) {
        this.firstDeliveryFee = firstDeliveryFee;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getFreeMoney() {
        return freeMoney;
    }

    public void setFreeMoney(String freeMoney) {
        this.freeMoney = freeMoney;
    }

    public String getPersonPayMoney() {
        return personPayMoney;
    }

    public void setPersonPayMoney(String personPayMoney) {
        this.personPayMoney = personPayMoney;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<MealAddressesBean> getMealAddresses() {
        return mealAddresses;
    }

    public void setMealAddresses(List<MealAddressesBean> mealAddresses) {
        this.mealAddresses = mealAddresses;
    }


    public static class MealAddressesBean {
        /**
         * mealAddress : 东北门
         * lat : 39.990559
         * lon : 116.805547
         */

        private String mealAddress;
        private String lat;
        private String lon;

        public String getMealAddress() {
            return mealAddress;
        }

        public void setMealAddress(String mealAddress) {
            this.mealAddress = mealAddress;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }
    }
}
