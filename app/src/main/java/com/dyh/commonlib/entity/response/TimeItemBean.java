package com.dyh.commonlib.entity.response;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/11/011 14:40
 * 描述：时间
 */
public class TimeItemBean {

    /**
     * id : 58
     * cityId : 110100
     * city : 北京市
     * districtId : 110100
     * district : 西城区
     * endTime : -28800000
     * sendTime : 56700000
     * endTimeStr : 00:00
     * sendTimeStr : 23:45
     * deliveryMode : 1
     * remark : 0
     * operatorId : null
     * name : 小碗菜专区
     * specialtyBelong : null
     * groupUserAddressList : null
     */

    private String id;
    private String cityId;
    private String city;
    private String districtId;
    private String district;
    private String endTime;
    private String sendTime;
    private String endTimeStr;
    private String sendTimeStr;
    private String deliveryMode;
    private String remark;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getSendTimeStr() {
        return sendTimeStr;
    }

    public void setSendTimeStr(String sendTimeStr) {
        this.sendTimeStr = sendTimeStr;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
