package com.dyh.commonlib.entity.response;

import android.text.TextUtils;

import java.util.List;

/**
 * 楼层/取餐点信息
 */
public class FloorMealListBean {
    private String countTotal;
    private String deliveryAddressId;
    private String floor;
    private String groupUseType;
    private String takeMealPoint;
    private String takeMealPointName;
    private List<String> groupIds;
    private List<SkuItemsBean> skuItems;
    private List<GroupCompany> groupCompanys;

    public List<GroupCompany> getGroupCompanys() {
        return groupCompanys;
    }

    public void setGroupCompanys(List<GroupCompany> groupCompanys) {
        this.groupCompanys = groupCompanys;
    }

    public String getMealOrFloor() {
        return TextUtils.isEmpty(takeMealPointName) ? floor + "层" : takeMealPointName;
    }

    public String getMealPointOrFloor() {
        return TextUtils.isEmpty(takeMealPoint) ? floor : takeMealPoint;
    }

    public String getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(String countTotal) {
        this.countTotal = countTotal;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getGroupUseType() {
        return groupUseType;
    }

    public void setGroupUseType(String groupUseType) {
        this.groupUseType = groupUseType;
    }

    public String getTakeMealPoint() {
        return takeMealPoint;
    }

    public void setTakeMealPoint(String takeMealPoint) {
        this.takeMealPoint = takeMealPoint;
    }

    public String getTakeMealPointName() {
        return takeMealPointName;
    }

    public void setTakeMealPointName(String takeMealPointName) {
        this.takeMealPointName = takeMealPointName;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    public List<SkuItemsBean> getSkuItems() {
        return skuItems;
    }

    public void setSkuItems(List<SkuItemsBean> skuItems) {
        this.skuItems = skuItems;
    }

    public static class GroupCompany {

        /**
         * companyNumber : 101
         * groupUsers : [{"openid":"oK5rH5fMrZEi4wArnPWE9kcWiWjs","skuItems":[{"count":1,"deliveryAddressId":105,"groupUseType":4,"name":"欢乐颂39","price":668,"pricePreferential":0.01,"refundStatus":0,"skuId":180990202,"takeMealPoint":11},{"count":1,"deliveryAddressId":105,"groupUseType":4,"name":"晚餐饮[P2]","price":0.03,"pricePreferential":0.01,"refundStatus":0,"skuId":179920202,"takeMealPoint":11}],"userAddressId":999,"userName":"T.","userPhone":"18832893890"},{"openid":"oK5rH5SAmQwPa3owVc6UBmjIC3GY","skuItems":[{"count":1,"deliveryAddressId":105,"groupUseType":4,"name":"欢乐颂的菜1","price":0.02,"pricePreferential":0.01,"refundStatus":0,"skuId":180580202,"takeMealPoint":11}],"userAddressId":1161,"userName":"夜寒","userPhone":"17719015857"}]
         * houseNum :
         * openidList : ["oK5rH5fMrZEi4wArnPWE9kcWiWjs","oK5rH5SAmQwPa3owVc6UBmjIC3GY"]
         */

        private String companyNumber;
        private String houseNum;
        private List<GroupUsersBean> groupUsers;
        private List<String> openidList;

        public String getCompanyNumber() {
            return companyNumber;
        }

        public void setCompanyNumber(String companyNumber) {
            this.companyNumber = companyNumber;
        }

        public String getHouseNum() {
            return houseNum;
        }

        public void setHouseNum(String houseNum) {
            this.houseNum = houseNum;
        }

        public List<GroupUsersBean> getGroupUsers() {
            return groupUsers;
        }

        public void setGroupUsers(List<GroupUsersBean> groupUsers) {
            this.groupUsers = groupUsers;
        }

        public List<String> getOpenidList() {
            return openidList;
        }

        public void setOpenidList(List<String> openidList) {
            this.openidList = openidList;
        }

        public static class GroupUsersBean {
            /**
             * openid : oK5rH5fMrZEi4wArnPWE9kcWiWjs
             * skuItems : [{"count":1,"deliveryAddressId":105,"groupUseType":4,"name":"欢乐颂39","price":668,"pricePreferential":0.01,"refundStatus":0,"skuId":180990202,"takeMealPoint":11},{"count":1,"deliveryAddressId":105,"groupUseType":4,"name":"晚餐饮[P2]","price":0.03,"pricePreferential":0.01,"refundStatus":0,"skuId":179920202,"takeMealPoint":11}]
             * userAddressId : 999
             * userName : T.
             * userPhone : 18832893890
             */

            private String openid;
            private int userAddressId;
            private String userName;
            private String userPhone;
            private List<SkuItemsBean> skuItems;

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public int getUserAddressId() {
                return userAddressId;
            }

            public void setUserAddressId(int userAddressId) {
                this.userAddressId = userAddressId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public List<SkuItemsBean> getSkuItems() {
                return skuItems;
            }

            public void setSkuItems(List<SkuItemsBean> skuItems) {
                this.skuItems = skuItems;
            }
        }
    }


    public static class SkuItemsBean {
        /**
         * count : 3
         * deliveryAddressId : 128
         * groupUseType : 4
         * name : 回锅肉
         * price : 0.06
         * pricePreferential : 0.03
         * refundStatus : 0
         * skuId : 180880202
         * takeMealPoint : 16
         */

        private String count;
        private String deliveryAddressId;
        private String groupUseType;
        private String name;
        private String price;
        private String pricePreferential;
        private String priceSell;
        private String refundStatus;
        private String skuId;
        private String takeMealPoint;

        public String getPriceSell() {
            return priceSell;
        }

        public void setPriceSell(String priceSell) {
            this.priceSell = priceSell;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getDeliveryAddressId() {
            return deliveryAddressId;
        }

        public void setDeliveryAddressId(String deliveryAddressId) {
            this.deliveryAddressId = deliveryAddressId;
        }

        public String getGroupUseType() {
            return groupUseType;
        }

        public void setGroupUseType(String groupUseType) {
            this.groupUseType = groupUseType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPricePreferential() {
            return pricePreferential;
        }

        public void setPricePreferential(String pricePreferential) {
            this.pricePreferential = pricePreferential;
        }

        public String getRefundStatus() {
            return refundStatus;
        }

        public void setRefundStatus(String refundStatus) {
            this.refundStatus = refundStatus;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getTakeMealPoint() {
            return takeMealPoint;
        }

        public void setTakeMealPoint(String takeMealPoint) {
            this.takeMealPoint = takeMealPoint;
        }
    }
}