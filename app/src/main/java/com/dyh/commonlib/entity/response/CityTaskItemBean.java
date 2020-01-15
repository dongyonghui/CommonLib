package com.dyh.commonlib.entity.response;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/9/009 11:06
 * 描述：城市任务
 */
public class CityTaskItemBean {


    /**
     * id : 2
     * orderTaskId : 2
     * provinceId : 120000
     * province : 天津市
     * cityId : 120100
     * city : 天津市
     * districtId : 120101
     * district : 和平区
     * targetOrderCount : 2520
     * targetTradePrice : 32000.0
     * status : 1
     * currentOrderCount : 129
     * currentTradePrice : 1351.08
     * monthDate : 2019-11
     * operatorId : 40
     * operatorName : 李方民
     * orderCountRate : null
     * orderPriceRate : null
     */

    private String id;
    private String orderTaskId;
    private String provinceId;
    private String province;
    private String cityId;
    private String city;
    private String districtId;
    private String district;
    private String targetOrderCount;
    private String targetTradePrice;
    private String status;
    private String currentOrderCount;
    private String currentTradePrice;
    private String monthDate;
    private String operatorId;
    private String operatorName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderTaskId() {
        return orderTaskId;
    }

    public void setOrderTaskId(String orderTaskId) {
        this.orderTaskId = orderTaskId;
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

    public String getTargetOrderCount() {
        return targetOrderCount;
    }

    public void setTargetOrderCount(String targetOrderCount) {
        this.targetOrderCount = targetOrderCount;
    }

    public String getTargetTradePrice() {
        return targetTradePrice;
    }

    public void setTargetTradePrice(String targetTradePrice) {
        this.targetTradePrice = targetTradePrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentOrderCount() {
        return currentOrderCount;
    }

    public void setCurrentOrderCount(String currentOrderCount) {
        this.currentOrderCount = currentOrderCount;
    }

    public String getCurrentTradePrice() {
        return currentTradePrice;
    }

    public void setCurrentTradePrice(String currentTradePrice) {
        this.currentTradePrice = currentTradePrice;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
