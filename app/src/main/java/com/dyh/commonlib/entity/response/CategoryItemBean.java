package com.dyh.commonlib.entity.response;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/28/028 14:54
 * 描述：分类数据
 */
public class CategoryItemBean {

    /**
     * id : 1115
     * createAt : 1565250907000
     * updateAt : 1565250907000
     * name : 热门
     * pid : 0
     * shopId : 20190625
     * elemeCategoryId : null
     * mtCategoryId : null
     * ebCategoryId : 0
     * ebParentId : 0
     * storeCount : 0
     * onlineCount : 0
     * picture : https://image.goukugogo.com/201911220447459722f70c8f7
     * description : null
     * activityId : 0
     * activityName : null
     * sort : 1069
     * cityId : 110100
     * cityName : 北京市
     * specialtyBeString : 0
     * moveType : null
     * childList : [{"id":1118,"createAt":1565315331000,"updateAt":1565315331000,"deleteAt":null,"isDeleted":false,"name":"美味佳肴","pid":1115,"shopId":20190625,"elemeCategoryId":null,"mtCategoryId":null,"ebCategoryId":0,"ebParentId":0,"storeCount":0,"onlineCount":0,"picture":null,"description":"","activityId":0,"activityName":null,"sort":1117,"cityId":110100,"cityName":null,"specialtyBelong":0,"moveType":null,"childList":null},{"id":1119,"createAt":1565315378000,"updateAt":1565315378000,"deleteAt":null,"isDeleted":false,"name":"超值特惠","pid":1115,"shopId":20190625,"elemeCategoryId":null,"mtCategoryId":null,"ebCategoryId":0,"ebParentId":0,"storeCount":0,"onlineCount":0,"picture":null,"description":"","activityId":0,"activityName":null,"sort":1118,"cityId":110100,"cityName":null,"specialtyBelong":0,"moveType":null,"childList":null},{"id":1117,"createAt":1565315331000,"updateAt":1565315331000,"deleteAt":null,"isDeleted":false,"name":"特惠菜品","pid":1115,"shopId":20190625,"elemeCategoryId":null,"mtCategoryId":null,"ebCategoryId":0,"ebParentId":0,"storeCount":0,"onlineCount":0,"picture":null,"description":"好好吃","activityId":0,"activityName":null,"sort":1119,"cityId":110100,"cityName":null,"specialtyBelong":0,"moveType":null,"childList":null},{"id":1120,"createAt":1565315378000,"updateAt":1565315378000,"deleteAt":null,"isDeleted":false,"name":"琼浆玉露","pid":1115,"shopId":20190625,"elemeCategoryId":null,"mtCategoryId":null,"ebCategoryId":0,"ebParentId":0,"storeCount":0,"onlineCount":0,"picture":null,"description":"","activityId":0,"activityName":null,"sort":1120,"cityId":110100,"cityName":null,"specialtyBelong":0,"moveType":null,"childList":null},{"id":1121,"createAt":1565315480000,"updateAt":1565315480000,"deleteAt":null,"isDeleted":false,"name":"非你不可","pid":1115,"shopId":20190625,"elemeCategoryId":null,"mtCategoryId":null,"ebCategoryId":0,"ebParentId":0,"storeCount":0,"onlineCount":0,"picture":null,"description":"","activityId":0,"activityName":null,"sort":1121,"cityId":110100,"cityName":null,"specialtyBelong":0,"moveType":null,"childList":null},{"id":1149,"createAt":1566182646000,"updateAt":1566182646000,"deleteAt":null,"isDeleted":false,"name":"福利团","pid":1115,"shopId":20190625,"elemeCategoryId":null,"mtCategoryId":null,"ebCategoryId":0,"ebParentId":0,"storeCount":0,"onlineCount":0,"picture":null,"description":"拼着吃","activityId":0,"activityName":null,"sort":1122,"cityId":110100,"cityName":null,"specialtyBelong":0,"moveType":null,"childList":null},{"id":1122,"createAt":1565315480000,"updateAt":1565315480000,"deleteAt":null,"isDeleted":false,"name":"千军万马","pid":1115,"shopId":20190625,"elemeCategoryId":null,"mtCategoryId":null,"ebCategoryId":0,"ebParentId":0,"storeCount":0,"onlineCount":0,"picture":null,"description":"","activityId":0,"activityName":null,"sort":1149,"cityId":110100,"cityName":null,"specialtyBelong":0,"moveType":null,"childList":null}]
     */

    private String id;
    private String createAt;
    private String updateAt;
    private String name;
    private String pid;
    private String shopId;
    private String ebCategoryId;
    private String ebParentId;
    private String storeCount;
    private String onlineCount;
    private String picture;
    private String activityId;
    private String sort;
    private String cityId;
    private String cityName;
    private String specialtyBelong;
    private List<CategoryItemBean> childList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryItemBean that = (CategoryItemBean) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getEbCategoryId() {
        return ebCategoryId;
    }

    public void setEbCategoryId(String ebCategoryId) {
        this.ebCategoryId = ebCategoryId;
    }

    public String getEbParentId() {
        return ebParentId;
    }

    public void setEbParentId(String ebParentId) {
        this.ebParentId = ebParentId;
    }

    public String getStoreCount() {
        return storeCount;
    }

    public void setStoreCount(String storeCount) {
        this.storeCount = storeCount;
    }

    public String getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(String onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSpecialtyBelong() {
        return specialtyBelong;
    }

    public void setSpecialtyBelong(String specialtyBelong) {
        this.specialtyBelong = specialtyBelong;
    }

    public List<CategoryItemBean> getChildList() {
        return childList;
    }

    public void setChildList(List<CategoryItemBean> childList) {
        this.childList = childList;
    }

}
