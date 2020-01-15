package com.dyh.commonlib.entity.response;

import android.text.TextUtils;

import com.dyh.commonlib.entity.BaseHttpResponseBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：首页订单列表项
 */
public class HomeOrderListItemBean extends BaseHttpResponseBean {

    private String dateStatus;
    private String deliveryArriveTime;
    private String deliveryDate;
    private String deliveryNumber;
    private String deliveryStatus;
    private String deliveryTime;
    private String id;
    private String orderCountTotal;
    private String skuCountTotal;
    private String status;
    private List<GroupOrderListBean> groupOrderList;

    public String getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(String dateStatus) {
        this.dateStatus = dateStatus;
    }

    public String getDeliveryArriveTime() {
        return deliveryArriveTime;
    }

    public void setDeliveryArriveTime(String deliveryArriveTime) {
        this.deliveryArriveTime = deliveryArriveTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderCountTotal() {
        return orderCountTotal;
    }

    public void setOrderCountTotal(String orderCountTotal) {
        this.orderCountTotal = orderCountTotal;
    }

    public String getSkuCountTotal() {
        return skuCountTotal;
    }

    public void setSkuCountTotal(String skuCountTotal) {
        this.skuCountTotal = skuCountTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GroupOrderListBean> getGroupOrderList() {
        return groupOrderList;
    }

    public void setGroupOrderList(List<GroupOrderListBean> groupOrderList) {
        this.groupOrderList = groupOrderList;
    }

    public static class GroupOrderListBean {
        private String countComplete;
        private String countRequire;
        private String createAt;
        private String dateStatus;
        private String deliveryAddress;
        private String deliveryAddressId;
        private String deliveryArriveTime;
        private String deliveryDate;
        private String deliveryGroupId;
        private String deliveryGroupNumber;
        private String deliveryInfo;
        private String deliveryManName;
        private String deliveryManPhone;
        private String deliveryNumber;
        private String deliveryStatus;
        private String deliveryTime;
        private String dineType;
        private String duration;
        private String groupId;
        private String orderCountTotal;
        private String orderId;
        private String orderIdStr;
        private String pickupTime;
        private String shopAddress;
        private String shopId;
        private String shopIdStr;
        private String shopName;
        private String shopPhone;
        private String skuCountTotal;
        private String supplyPoolId;
        private String takeMealPoint;
        private String wareNum;
        private List<String> groupIdList;
        private List<ItemsBean> items;
        public Location shopLocation;

        public static class Location {
            public String lat;
            public String lon;
        }

        public String getCountComplete() {
            return countComplete;
        }

        public void setCountComplete(String countComplete) {
            this.countComplete = countComplete;
        }

        public String getCountRequire() {
            return countRequire;
        }

        public void setCountRequire(String countRequire) {
            this.countRequire = countRequire;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getDateStatus() {
            return dateStatus;
        }

        public void setDateStatus(String dateStatus) {
            this.dateStatus = dateStatus;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public String getDeliveryAddressId() {
            return deliveryAddressId;
        }

        public void setDeliveryAddressId(String deliveryAddressId) {
            this.deliveryAddressId = deliveryAddressId;
        }

        public String getDeliveryArriveTime() {
            return deliveryArriveTime;
        }

        public void setDeliveryArriveTime(String deliveryArriveTime) {
            this.deliveryArriveTime = deliveryArriveTime;
        }

        public String getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public String getDeliveryGroupId() {
            return deliveryGroupId;
        }

        public void setDeliveryGroupId(String deliveryGroupId) {
            this.deliveryGroupId = deliveryGroupId;
        }

        public String getDeliveryGroupNumber() {
            return deliveryGroupNumber;
        }

        public void setDeliveryGroupNumber(String deliveryGroupNumber) {
            this.deliveryGroupNumber = deliveryGroupNumber;
        }

        public String getDeliveryInfo() {
            return deliveryInfo;
        }

        public void setDeliveryInfo(String deliveryInfo) {
            this.deliveryInfo = deliveryInfo;
        }

        public String getDeliveryManName() {
            return deliveryManName;
        }

        public void setDeliveryManName(String deliveryManName) {
            this.deliveryManName = deliveryManName;
        }

        public String getDeliveryManPhone() {
            return deliveryManPhone;
        }

        public void setDeliveryManPhone(String deliveryManPhone) {
            this.deliveryManPhone = deliveryManPhone;
        }

        public String getDeliveryNumber() {
            return deliveryNumber;
        }

        public void setDeliveryNumber(String deliveryNumber) {
            this.deliveryNumber = deliveryNumber;
        }

        public String getDeliveryStatus() {
            return deliveryStatus;
        }

        public void setDeliveryStatus(String deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getDineType() {
            return dineType;
        }

        public void setDineType(String dineType) {
            this.dineType = dineType;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getOrderCountTotal() {
            return orderCountTotal;
        }

        public void setOrderCountTotal(String orderCountTotal) {
            this.orderCountTotal = orderCountTotal;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderIdStr() {
            return orderIdStr;
        }

        public void setOrderIdStr(String orderIdStr) {
            this.orderIdStr = orderIdStr;
        }

        public String getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(String pickupTime) {
            this.pickupTime = pickupTime;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getShopIdStr() {
            return shopIdStr;
        }

        public void setShopIdStr(String shopIdStr) {
            this.shopIdStr = shopIdStr;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopPhone() {
            return shopPhone;
        }

        public void setShopPhone(String shopPhone) {
            this.shopPhone = shopPhone;
        }

        public String getSkuCountTotal() {
            return skuCountTotal;
        }

        public void setSkuCountTotal(String skuCountTotal) {
            this.skuCountTotal = skuCountTotal;
        }

        public String getSupplyPoolId() {
            return supplyPoolId;
        }

        public void setSupplyPoolId(String supplyPoolId) {
            this.supplyPoolId = supplyPoolId;
        }

        public String getTakeMealPoint() {
            return takeMealPoint;
        }

        public void setTakeMealPoint(String takeMealPoint) {
            this.takeMealPoint = takeMealPoint;
        }

        public String getWareNum() {
            return wareNum;
        }

        public void setWareNum(String wareNum) {
            this.wareNum = wareNum;
        }

        public List<String> getGroupIdList() {
            return groupIdList;
        }

        public void setGroupIdList(List<String> groupIdList) {
            this.groupIdList = groupIdList;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            private String count;
            private String deliveryAddressId;
            private String groupUseType;
            private String name;
            private String price;
            private String pricePreferential;
            private String refundStatus;
            private String skuId;
            private String takeMealPoint;
            private List<GroupAddressesBean> groupAddresses;

//            public boolean isOpenChildList;//本地使用，标识是否展开子列表

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

            public List<GroupAddressesBean> getGroupAddresses() {
                return groupAddresses;
            }

            public void setGroupAddresses(List<GroupAddressesBean> groupAddresses) {
                this.groupAddresses = groupAddresses;
            }

            public static class GroupAddressesBean {
                private String countTotal;
                private String deliveryAddressId;
                private String deliveryAddressName;
                private String groupUseType;
                private List<String> groupIds;
                private List<FloorMealListBean> mealList;
                private List<FloorMealListBean> floorList;

                public List<FloorMealListBean> getFloorList() {
                    if (null == floorList) {
                        return null;
                    }
                    for (FloorMealListBean floorMealListBean : floorList) {
                        floorMealListBean.setTakeMealPoint(null);
                        floorMealListBean.setTakeMealPointName(null);
                    }
                    return floorList;
                }

                public void setFloorList(List<FloorMealListBean> floorList) {
                    this.floorList = floorList;
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

                public String getDeliveryAddressName() {
                    return deliveryAddressName;
                }

                public void setDeliveryAddressName(String deliveryAddressName) {
                    this.deliveryAddressName = deliveryAddressName;
                }

                public String getGroupUseType() {
                    return groupUseType;
                }

                public void setGroupUseType(String groupUseType) {
                    this.groupUseType = groupUseType;
                }

                public List<String> getGroupIds() {
                    return groupIds;
                }

                public void setGroupIds(List<String> groupIds) {
                    this.groupIds = groupIds;
                }

                public List<FloorMealListBean> getMealList() {
                    if (null == mealList) {
                        return null;
                    }
                    for (FloorMealListBean floorMealListBean : mealList) {
                        floorMealListBean.setFloor(null);
                    }
                    return mealList;
                }

                public void setMealList(List<FloorMealListBean> mealList) {
                    this.mealList = mealList;
                }

                public static class FloorMealListBean {
                    private String countTotal;
                    private String deliveryAddressId;
                    private String floor;
                    private String groupUseType;
                    private String takeMealPoint;
                    private String takeMealPointName;
                    private List<String> groupIds;

                    public String getMealOrFloor() {
                        return TextUtils.isEmpty(takeMealPointName) ? floor + "层" : takeMealPointName;
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
                }
            }
        }
    }
}
