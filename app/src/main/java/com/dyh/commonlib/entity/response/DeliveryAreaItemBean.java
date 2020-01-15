package com.dyh.commonlib.entity.response;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/18/018 18:25
 * 描述：配送区域列表项
 */
public class DeliveryAreaItemBean {

    /**
     * id : 2
     * name : 区域01
     * provinceId : 110101
     * province : 北京市
     * cityId : 110100
     * city : 北京市
     * districtId : 110101
     * district : 东城区
     * remark : 0
     * status : 1
     * operatorId : 27
     * deliverySiteIds : [1,7,8]
     */

    private String id;
    private String name;
    private String provinceId;
    private String province;
    private String cityId;
    private String city;
    private String districtId;
    private String district;
    private String remark;
    private String status;
    private String operatorId;
    private String deliverySiteIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getDeliverySiteIds() {
        return deliverySiteIds;
    }

    public void setDeliverySiteIds(String deliverySiteIds) {
        this.deliverySiteIds = deliverySiteIds;
    }
}
