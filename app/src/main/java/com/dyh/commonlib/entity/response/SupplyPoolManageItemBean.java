package com.dyh.commonlib.entity.response;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/5/005 14:58
 * 描述：供给池管理列表项
 */
public class SupplyPoolManageItemBean {


    /**
     * supplyPoolId : 8
     * name : 圣熙8号供给池
     * personName : 管理员
     * phone : 18895322989
     * lat : 40.008648
     * lon : 116.353909
     * city : 北京市
     * cityId : 0
     * province : 北京
     * provinceId : 0
     * deliveryAddressList : [{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":null,"lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":"圣熙8号购物中心","deliveryAddressId":762,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null}]
     * shopList : [{"shopId":110010000000004,"name":"永慧超市","lat":0,"lon":0}]
     * boundary : 2000
     * fenceId : 166a7204-8a10-47a9-bf99-279c4714b611
     * address : 圣熙8号购物中心
     * distance : null
     * points : null
     * isUsualPool : false
     */

    private String supplyPoolId;
    private String name;
    private String personName;
    private String phone;
    private String lat;
    private String lon;
    private String city;
    private String cityId;
    private String province;
    private String provinceId;
    private String boundary;
    private String fenceId;
    private String address;
    private String distance;
    private String points;
    private boolean isUsualPool;
    private List<DeliveryAddressManageItemBean> deliveryAddressList;
    private List<ShopItemBean> shopList;

    public String getSupplyPoolId() {
        return supplyPoolId;
    }

    public void setSupplyPoolId(String supplyPoolId) {
        this.supplyPoolId = supplyPoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public String getFenceId() {
        return fenceId;
    }

    public void setFenceId(String fenceId) {
        this.fenceId = fenceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public boolean isIsUsualPool() {
        return isUsualPool;
    }

    public void setIsUsualPool(boolean isUsualPool) {
        this.isUsualPool = isUsualPool;
    }

    public List<DeliveryAddressManageItemBean> getDeliveryAddressList() {
        return deliveryAddressList;
    }

    public void setDeliveryAddressList(List<DeliveryAddressManageItemBean> deliveryAddressList) {
        this.deliveryAddressList = deliveryAddressList;
    }

    public List<ShopItemBean> getShopList() {
        return shopList;
    }

    public void setShopList(List<ShopItemBean> shopList) {
        this.shopList = shopList;
    }
}
