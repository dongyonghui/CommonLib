package com.dyh.commonlib.entity.response;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 17:53
 * 描述：标签信息
 */
public class LabelItemBean {
    private String shopWareTagsId;
    private String tagsName;
    private String description;
    private String cityName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabelItemBean itemBean = (LabelItemBean) o;

        return shopWareTagsId != null ? shopWareTagsId.equals(itemBean.shopWareTagsId) : itemBean.shopWareTagsId == null;
    }

    @Override
    public int hashCode() {
        return shopWareTagsId != null ? shopWareTagsId.hashCode() : 0;
    }

    public String getShopWareTagsId() {
        return shopWareTagsId;
    }

    public void setShopWareTagsId(String shopWareTagsId) {
        this.shopWareTagsId = shopWareTagsId;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
