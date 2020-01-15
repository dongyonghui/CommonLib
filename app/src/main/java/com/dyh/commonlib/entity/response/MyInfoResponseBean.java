package com.dyh.commonlib.entity.response;

import android.text.TextUtils;

import com.dyh.commonlib.entity.BaseHttpResponseBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 时间：2019/9/23/023
 * 邮箱：648731994@qq.com
 * 描述：基本信息数据
 */
public class MyInfoResponseBean extends BaseHttpResponseBean {

    private String idCardNum;
    private String idCardForward;
    private String idCardBack;
    private String healthCard;
    private String name;
    private String phone;
    private List<DeliveryAddressListBean> deliveryAddressList;

    public String getIdCardForward() {
        return idCardForward;
    }

    public void setIdCardForward(String idCardForward) {
        this.idCardForward = idCardForward;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }

    public String getHealthCard() {
        return healthCard;
    }

    public void setHealthCard(String healthCard) {
        this.healthCard = healthCard;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<DeliveryAddressListBean> getDeliveryAddressList() {
        return deliveryAddressList;
    }

    /**
     * 获取地址集合信息
     *
     * @return
     */
    public String getAllAddressInfo() {
        if (deliveryAddressList == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (DeliveryAddressListBean deliveryAddressListBean : deliveryAddressList) {
            if (!TextUtils.isEmpty(stringBuilder)) {
                stringBuilder.append(";");
            }
            stringBuilder.append(deliveryAddressListBean.getTitle());
        }
        return stringBuilder.toString();
    }

    public void setDeliveryAddressList(List<DeliveryAddressListBean> deliveryAddressList) {
        this.deliveryAddressList = deliveryAddressList;
    }

    public static class DeliveryAddressListBean {
        private String address;
        private String city;
        private String cityId;
        private String createdCanal;
        private String deliveryAddressId;
        private String deliveryFee;
        private String deliveryStatus;
        private String district;
        private String districtId;
        private String firstDeliveryFee;
        private String freeMoney;
        private String id;
        private String lat;
        private String lon;
        private String province;
        private String provinceId;
        private String remark;
        private String title;
        private String type;
        private String useStatus;
        private String useType;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCreatedCanal() {
            return createdCanal;
        }

        public void setCreatedCanal(String createdCanal) {
            this.createdCanal = createdCanal;
        }

        public String getDeliveryAddressId() {
            return deliveryAddressId;
        }

        public void setDeliveryAddressId(String deliveryAddressId) {
            this.deliveryAddressId = deliveryAddressId;
        }

        public String getDeliveryFee() {
            return deliveryFee;
        }

        public void setDeliveryFee(String deliveryFee) {
            this.deliveryFee = deliveryFee;
        }

        public String getDeliveryStatus() {
            return deliveryStatus;
        }

        public void setDeliveryStatus(String deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getFirstDeliveryFee() {
            return firstDeliveryFee;
        }

        public void setFirstDeliveryFee(String firstDeliveryFee) {
            this.firstDeliveryFee = firstDeliveryFee;
        }

        public String getFreeMoney() {
            return freeMoney;
        }

        public void setFreeMoney(String freeMoney) {
            this.freeMoney = freeMoney;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUseStatus() {
            return useStatus;
        }

        public void setUseStatus(String useStatus) {
            this.useStatus = useStatus;
        }

        public String getUseType() {
            return useType;
        }

        public void setUseType(String useType) {
            this.useType = useType;
        }
    }
}
