package com.dyh.commonlib.entity.response;

import android.text.TextUtils;

import com.dyh.commonlib.entity.BaseHttpResponseBean;
import com.dyh.commonlib.entity.request.SubmitAuditingRequestBean;

import java.util.List;

public class TpcGoodsItemBean extends BaseHttpResponseBean {

    public static final String AUDITING_STATUS_WAITING2AUDIT = "1";//待审核
    public static final String AUDITING_STATUS_PASSED = "2";//审核通过
    public static final String AUDITING_STATUS_REJECT = "3";//被驳回

    private String shopId;
    private String shopName;
    private String address;
    private String personName;
    private String shopPhone;
    private String spu;
    private String name;
    private String picture;
    private String limitNum;
    private String description;
    private String reason;
    private String auditingStatus;
    private String specialtyStatus;
    private String sort;
    private String auditingType;
    private String categoryIds;
    private String personLimitNum;
    private List<StandardsBean> standards;
    private List<String> categoryNames;
    private List<AddressListBean> addressList;
    private List<String> pictureList;
    private List<String> preferenceTime;
    private List<String> preferenceIds;
    private List<LabelItemBean> tagsList;
    private List<SubmitAuditingRequestBean.CateListBean> cateList;
    private List<CategoryItemBean> categoryResponseList;

    /**
     * @return 获取配送地址信息
     */
    public String getAddressTextInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (null != addressList && addressList.size() > 0) {
            for (AddressListBean addressListBean : addressList) {
                if (!TextUtils.isEmpty(stringBuilder)) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append(addressListBean.getDeliveryAddressName());
            }
        }
        return stringBuilder.toString();
    }

    /**
     * @return 获取展示时间信息
     */
    public String getShowTimeTextInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (null != preferenceTime && preferenceTime.size() > 0) {
            for (String item : preferenceTime) {
                stringBuilder.append(item).append("；");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * @return 获取加价信息
     */
    public String getAddedPriceTextInfo() {
        if (AUDITING_STATUS_PASSED.equals(auditingStatus)) {
            StandardsBean standardsBean = standards.get(0);
            return "加价：" + standardsBean.addPrice + "元";
        }
        return null;
    }

    /**
     * @return 获取商品价格信息
     */
    public String getPriceTextInfo() {
        if (null != standards && standards.size() > 0) {
            StandardsBean standardsBean = standards.get(0);
            return "原价：" + standardsBean.onlineStorePrice + "元\n供应价：" + standardsBean.onlineStoreGroupPrice
                    + "元";
        }
        return null;
    }

    /**
     * @return 获取商品的审核状态文本消息
     */
    public String getGoodsAuditingStatusText() {
        if (AUDITING_STATUS_WAITING2AUDIT.equals(auditingStatus)) {
            return "待审核";
        } else if (AUDITING_STATUS_PASSED.equals(auditingStatus)) {
            return "审核通过";
        } else if (AUDITING_STATUS_REJECT.equals(auditingStatus)) {
            return "被驳回";
        }
        return null;
    }

    /**
     * @return true表示是首单特惠商品
     */
    public boolean isFirstDiscountGoods() {
        return "5".equalsIgnoreCase(auditingType);
    }

    /**
     * @return true表示是限时秒杀商品
     */
    public boolean isLimitedTimeDiscountGoods() {
        return "7".equalsIgnoreCase(auditingType);
    }

    public List<SubmitAuditingRequestBean.CateListBean> getCateList() {
        return cateList;
    }

    public List<CategoryItemBean> getCategoryResponseList() {
        return categoryResponseList;
    }

    public void setCategoryResponseList(List<CategoryItemBean> categoryResponseList) {
        this.categoryResponseList = categoryResponseList;
    }

    public void setCateList(List<SubmitAuditingRequestBean.CateListBean> cateList) {
        this.cateList = cateList;
    }

    public List<LabelItemBean> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<LabelItemBean> tagsList) {
        this.tagsList = tagsList;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(String limitNum) {
        this.limitNum = limitNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAuditingStatus() {
        return auditingStatus;
    }

    public void setAuditingStatus(String auditingStatus) {
        this.auditingStatus = auditingStatus;
    }

    public String getSpecialtyStatus() {
        return specialtyStatus;
    }

    public void setSpecialtyStatus(String specialtyStatus) {
        this.specialtyStatus = specialtyStatus;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getAuditingType() {
        return auditingType;
    }

    public void setAuditingType(String auditingType) {
        this.auditingType = auditingType;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getPersonLimitNum() {
        return personLimitNum;
    }

    public void setPersonLimitNum(String personLimitNum) {
        this.personLimitNum = personLimitNum;
    }

    public List<StandardsBean> getStandards() {
        return standards;
    }

    public void setStandards(List<StandardsBean> standards) {
        this.standards = standards;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public List<AddressListBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressListBean> addressList) {
        this.addressList = addressList;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public List<String> getPreferenceTime() {
        return preferenceTime;
    }

    public void setPreferenceTime(List<String> preferenceTime) {
        this.preferenceTime = preferenceTime;
    }

    public List<String> getPreferenceIds() {
        return preferenceIds;
    }

    public void setPreferenceIds(List<String> preferenceIds) {
        this.preferenceIds = preferenceIds;
    }

    public static class StandardsBean {
        /**
         * sku : 4783302
         * name : 正常
         * pictures : null
         * xprice : 0.0
         * onlineStorePrice : 15.0
         * oldXprice : 0.0
         * oldOnlineStorePrice : 15.0
         * storePrice : 0.0
         * storeGroupPrice : 0.0
         * onlineStoreGroupPrice : 7.9
         * addPrice : 0.0
         * pictureList : null
         * onlineStorePriceUnit : 15.0
         * onlineStoreGroupPriceUnit : 7.9
         */
        private List<String> pictureList;
        private String sku;
        private String name;
        private String xprice;
        private String onlineStorePrice;
        private String oldXprice;
        private String oldOnlineStorePrice;
        private String storePrice;
        private String storeGroupPrice;
        private String onlineStoreGroupPrice;
        private String addPrice;
        private String onlineStorePriceUnit;
        private String onlineStoreGroupPriceUnit;

        public List<String> getPictureList() {
            return pictureList;
        }

        public void setPictureList(List<String> pictureList) {
            this.pictureList = pictureList;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getXprice() {
            return xprice;
        }

        public void setXprice(String xprice) {
            this.xprice = xprice;
        }

        public String getOnlineStorePrice() {
            return onlineStorePrice;
        }

        public void setOnlineStorePrice(String onlineStorePrice) {
            this.onlineStorePrice = onlineStorePrice;
        }

        public String getOldXprice() {
            return oldXprice;
        }

        public void setOldXprice(String oldXprice) {
            this.oldXprice = oldXprice;
        }

        public String getOldOnlineStorePrice() {
            return oldOnlineStorePrice;
        }

        public void setOldOnlineStorePrice(String oldOnlineStorePrice) {
            this.oldOnlineStorePrice = oldOnlineStorePrice;
        }

        public String getStorePrice() {
            return storePrice;
        }

        public void setStorePrice(String storePrice) {
            this.storePrice = storePrice;
        }

        public String getStoreGroupPrice() {
            return storeGroupPrice;
        }

        public void setStoreGroupPrice(String storeGroupPrice) {
            this.storeGroupPrice = storeGroupPrice;
        }

        public String getOnlineStoreGroupPrice() {
            return onlineStoreGroupPrice;
        }

        public void setOnlineStoreGroupPrice(String onlineStoreGroupPrice) {
            this.onlineStoreGroupPrice = onlineStoreGroupPrice;
        }

        public String getAddPrice() {
            return addPrice;
        }

        public void setAddPrice(String addPrice) {
            this.addPrice = addPrice;
        }

        public String getOnlineStorePriceUnit() {
            return onlineStorePriceUnit;
        }

        public void setOnlineStorePriceUnit(String onlineStorePriceUnit) {
            this.onlineStorePriceUnit = onlineStorePriceUnit;
        }

        public String getOnlineStoreGroupPriceUnit() {
            return onlineStoreGroupPriceUnit;
        }

        public void setOnlineStoreGroupPriceUnit(String onlineStoreGroupPriceUnit) {
            this.onlineStoreGroupPriceUnit = onlineStoreGroupPriceUnit;
        }
    }

    public static class AddressListBean {
        /**
         * deliveryAddressId : 86
         * deliveryAddressName : 巨安大厦、 融城云谷  取餐点
         * shopId : 11324170
         * shopName : 乾州驴蹄子面
         * shopAddress : null
         */

        private String deliveryAddressId;
        private String deliveryAddressName;
        private String shopId;
        private String shopName;
        private String shopAddress;

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

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }
    }
}
