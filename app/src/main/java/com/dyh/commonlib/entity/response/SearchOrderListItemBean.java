package com.dyh.commonlib.entity.response;

import com.dyh.commonlib.entity.BaseHttpResponseBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 时间：2019/9/23/023
 * 邮箱：648731994@qq.com
 * 描述：搜索订单列表项信息
 */
public class SearchOrderListItemBean extends BaseHttpResponseBean {

    /**
     * mergeOrderId : 0
     * mergeOrderIdStr : 519110414193405295
     * mergeOrderList : [{"barcodeImageUrl":"https://image.goukugogo.com/gkbar201911040219416122ff56893?x-oss-process=style/OrderBarcode","checkStatus":1,"count":1,"countDown":0,"couponId":0,"createTime":1572848374000,"deliveryAddressId":105,"deliveryAddressName":"测试地址C","deliveryManIds":"54","deliveryManPhone":"17777777777","deliveryMen":[{"deliveryManId":54,"deliveryManName":"骑手E","deliveryManPhone":"17777777777"}],"distribution":"2019-11-04 18:00","distributionFormat":"(今天)11-04 18:00","groupId":3020,"groupOrder":{"countComplete":2,"countRequire":1,"createAt":1572848374000,"dateStatus":0,"deliveryAddressId":105,"deliveryArriveTime":"18:00","deliveryDate":"20191104","deliveryGroupId":0,"deliveryGroupNumber":0,"deliveryInfo":"20191104","deliveryNumber":0,"deliveryStatus":0,"deliveryTime":"17:30","dineType":0,"duration":86400,"flows":[{"avatar":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoLSsEoe3RMhTxfic8reqibyjJhibX3ZQAu2iccg8YopbiceVyknPJJ6BfNiaOaI9xMR4VO5Ix4fOIQjNOw/132","hourTime":"14:19","time":"2019-11-04 14:19:41","userName":"T."},{"avatar":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoLSsEoe3RMhTxfic8reqibyjJhibX3ZQAu2iccg8YopbiceVyknPJJ6BfNiaOaI9xMR4VO5Ix4fOIQjNOw/132","hourTime":"14:19","time":"2019-11-04 14:19:41","userName":"T."}],"groupId":3019,"items":[{"count":1,"name":"测试头牌菜1","pictures":"https://image.goukugogo.com/201910310433341162e3d1021","price":5,"pricePreferential":0.01,"refundStatus":0,"skuId":181210202}],"openid":"oK5rH5fMrZEi4wArnPWE9kcWiWjs","orderCountTotal":0,"orderId":519110414193405296,"orderIdStr":"519110414193405296","pickupTime":"20191104 16:30-17:30","shopAddress":"富力运河","shopId":110010000000006,"shopIdStr":"110010000000006","shopName":"永慧超市","shopPhone":"13141292840","skuCountTotal":0,"supplyPoolId":70,"takeMealPoint":11,"wareNum":0},"groupStatus":2,"groupUseType":4,"items":[{"count":1,"name":"限购商品3","orderId":519110414193405297,"pictures":"https://image.goukugogo.com/201910120241186314945260c","price":5,"priceGouku":5,"pricePreferential":0.01,"priceSell":0.01,"priceUnit":0.01,"repeatCount":0,"skuId":181000202},{"count":1,"name":"测试头牌菜1","orderId":519110414193405296,"pictures":"https://image.goukugogo.com/201910310433341162e3d1021","price":5,"priceGouku":5,"pricePreferential":0.01,"priceSell":0.01,"priceUnit":0.01,"repeatCount":0,"skuId":181210202}],"last":false,"location":{"lat":40.008952,"lon":116.353914},"mergeOrderId":519110414193405295,"mergeOrderIdStr":"519110414193405295","number":7042,"oldGroupId":3019,"openid":"oK5rH5fMrZEi4wArnPWE9kcWiWjs","orderDiscount":0,"orderId":519110414193405297,"orderIdStr":"519110414193405297","orderType":0,"payActual":0.02,"payFreight":0,"payReduce":9.98,"payTime":1572848382000,"payTotal":10,"peopleCount":0,"prepayOrderId":0,"qrcodeImageUrl":"https://image.goukugogo.com/gkqr20191104034319021741f88ef?x-oss-process=style/OrderBarcode","refundStatus":0,"refundTime":0,"refundWay":0,"remark":"101","sequence":2,"shopAddress":"东城区","shopId":1100117,"shopIdStr":"1100117","shopLat":39.928353,"shopLogo":"https://image.goukugogo.com/20191016041112820b2356e28","shopLon":116.416357,"shopMoney":0.01,"shopName":"欢乐颂","shopPhone":"13102587410","skuCount":0,"status":4,"supplyPoolId":70,"tableNo":"-1","takeMealPoint":11,"teamId":0,"usedCount":0,"userAddress":"北京市海淀区紫竹院街道中国青年政治学院电视节目制作实验室新闻传播实验教学中心中国青年政治学院(-2)","userName":"T.","userPhone":"18832893890"}]
     */

    private String mergeOrderId;
    private String mergeOrderIdStr;
    private List<MergeOrderListBean> mergeOrderList;

    public String getMergeOrderId() {
        return mergeOrderId;
    }

    public void setMergeOrderId(String mergeOrderId) {
        this.mergeOrderId = mergeOrderId;
    }

    public String getMergeOrderIdStr() {
        return mergeOrderIdStr;
    }

    public void setMergeOrderIdStr(String mergeOrderIdStr) {
        this.mergeOrderIdStr = mergeOrderIdStr;
    }

    public List<MergeOrderListBean> getMergeOrderList() {
        return mergeOrderList;
    }

    public void setMergeOrderList(List<MergeOrderListBean> mergeOrderList) {
        this.mergeOrderList = mergeOrderList;
    }

    public static class MergeOrderListBean {

        private String barcodeImageUrl;
        private String checkStatus;
        private String count;
        private String countDown;
        private String couponId;
        private String createTime;
        private String deliveryAddressId;
        private String deliveryAddressName;
        private String deliveryManIds;
        private String deliveryManPhone;
        private String distribution;
        private String distributionFormat;
        private String groupId;
        private GroupOrderBean groupOrder;
        private String groupStatus;
        private String groupUseType;
        private String last;
        private LocationBean location;
        private String mergeOrderId;
        private String mergeOrderIdStr;
        private String number;
        private String oldGroupId;
        private String openid;
        private String orderDiscount;
        private String orderId;
        private String orderIdStr;
        private String orderType;
        private String payActual;
        private String payFreight;
        private String payReduce;
        private String payTime;
        private String payTotal;
        private String peopleCount;
        private String prepayOrderId;
        private String qrcodeImageUrl;
        private String refundStatus;
        private String refundTime;
        private String refundWay;
        private String remark;
        private String sequence;
        private String shopAddress;
        private String shopId;
        private String shopIdStr;
        private String shopLat;
        private String shopLogo;
        private String shopLon;
        private String shopMoney;
        private String shopName;
        private String shopPhone;
        private String skuCount;
        private String status;
        private String supplyPoolId;
        private String tableNo;
        private String takeMealPoint;
        private String teamId;
        private String usedCount;
        private String userAddress;
        private String userName;
        private String userPhone;
        private List<DeliveryMenBean> deliveryMen;
        private List<ItemsBeanX> items;

        public String getBarcodeImageUrl() {
            return barcodeImageUrl;
        }

        public void setBarcodeImageUrl(String barcodeImageUrl) {
            this.barcodeImageUrl = barcodeImageUrl;
        }

        public String getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(String checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getCountDown() {
            return countDown;
        }

        public void setCountDown(String countDown) {
            this.countDown = countDown;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getDeliveryManIds() {
            return deliveryManIds;
        }

        public void setDeliveryManIds(String deliveryManIds) {
            this.deliveryManIds = deliveryManIds;
        }

        public String getDeliveryManPhone() {
            return deliveryManPhone;
        }

        public void setDeliveryManPhone(String deliveryManPhone) {
            this.deliveryManPhone = deliveryManPhone;
        }

        public String getDistribution() {
            return distribution;
        }

        public void setDistribution(String distribution) {
            this.distribution = distribution;
        }

        public String getDistributionFormat() {
            return distributionFormat;
        }

        public void setDistributionFormat(String distributionFormat) {
            this.distributionFormat = distributionFormat;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public GroupOrderBean getGroupOrder() {
            return groupOrder;
        }

        public void setGroupOrder(GroupOrderBean groupOrder) {
            this.groupOrder = groupOrder;
        }

        public String getGroupStatus() {
            return groupStatus;
        }

        public void setGroupStatus(String groupStatus) {
            this.groupStatus = groupStatus;
        }

        public String getGroupUseType() {
            return groupUseType;
        }

        public void setGroupUseType(String groupUseType) {
            this.groupUseType = groupUseType;
        }

        public String isLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getMergeOrderId() {
            return mergeOrderId;
        }

        public void setMergeOrderId(String mergeOrderId) {
            this.mergeOrderId = mergeOrderId;
        }

        public String getMergeOrderIdStr() {
            return mergeOrderIdStr;
        }

        public void setMergeOrderIdStr(String mergeOrderIdStr) {
            this.mergeOrderIdStr = mergeOrderIdStr;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getOldGroupId() {
            return oldGroupId;
        }

        public void setOldGroupId(String oldGroupId) {
            this.oldGroupId = oldGroupId;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getOrderDiscount() {
            return orderDiscount;
        }

        public void setOrderDiscount(String orderDiscount) {
            this.orderDiscount = orderDiscount;
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

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getPayActual() {
            return payActual;
        }

        public void setPayActual(String payActual) {
            this.payActual = payActual;
        }

        public String getPayFreight() {
            return payFreight;
        }

        public void setPayFreight(String payFreight) {
            this.payFreight = payFreight;
        }

        public String getPayReduce() {
            return payReduce;
        }

        public void setPayReduce(String payReduce) {
            this.payReduce = payReduce;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPayTotal() {
            return payTotal;
        }

        public void setPayTotal(String payTotal) {
            this.payTotal = payTotal;
        }

        public String getPeopleCount() {
            return peopleCount;
        }

        public void setPeopleCount(String peopleCount) {
            this.peopleCount = peopleCount;
        }

        public String getPrepayOrderId() {
            return prepayOrderId;
        }

        public void setPrepayOrderId(String prepayOrderId) {
            this.prepayOrderId = prepayOrderId;
        }

        public String getQrcodeImageUrl() {
            return qrcodeImageUrl;
        }

        public void setQrcodeImageUrl(String qrcodeImageUrl) {
            this.qrcodeImageUrl = qrcodeImageUrl;
        }

        public String getRefundStatus() {
            return refundStatus;
        }

        public void setRefundStatus(String refundStatus) {
            this.refundStatus = refundStatus;
        }

        public String getRefundTime() {
            return refundTime;
        }

        public void setRefundTime(String refundTime) {
            this.refundTime = refundTime;
        }

        public String getRefundWay() {
            return refundWay;
        }

        public void setRefundWay(String refundWay) {
            this.refundWay = refundWay;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSequence() {
            return sequence;
        }

        public void setSequence(String sequence) {
            this.sequence = sequence;
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

        public String getShopLat() {
            return shopLat;
        }

        public void setShopLat(String shopLat) {
            this.shopLat = shopLat;
        }

        public String getShopLogo() {
            return shopLogo;
        }

        public void setShopLogo(String shopLogo) {
            this.shopLogo = shopLogo;
        }

        public String getShopLon() {
            return shopLon;
        }

        public void setShopLon(String shopLon) {
            this.shopLon = shopLon;
        }

        public String getShopMoney() {
            return shopMoney;
        }

        public void setShopMoney(String shopMoney) {
            this.shopMoney = shopMoney;
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

        public String getSkuCount() {
            return skuCount;
        }

        public void setSkuCount(String skuCount) {
            this.skuCount = skuCount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSupplyPoolId() {
            return supplyPoolId;
        }

        public void setSupplyPoolId(String supplyPoolId) {
            this.supplyPoolId = supplyPoolId;
        }

        public String getTableNo() {
            return tableNo;
        }

        public void setTableNo(String tableNo) {
            this.tableNo = tableNo;
        }

        public String getTakeMealPoint() {
            return takeMealPoint;
        }

        public void setTakeMealPoint(String takeMealPoint) {
            this.takeMealPoint = takeMealPoint;
        }

        public String getTeamId() {
            return teamId;
        }

        public void setTeamId(String teamId) {
            this.teamId = teamId;
        }

        public String getUsedCount() {
            return usedCount;
        }

        public void setUsedCount(String usedCount) {
            this.usedCount = usedCount;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
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

        public List<DeliveryMenBean> getDeliveryMen() {
            return deliveryMen;
        }

        public void setDeliveryMen(List<DeliveryMenBean> deliveryMen) {
            this.deliveryMen = deliveryMen;
        }

        public List<ItemsBeanX> getItems() {
            return items;
        }

        public void setItems(List<ItemsBeanX> items) {
            this.items = items;
        }

        public static class GroupOrderBean {
            /**
             * countComplete : 2
             * countRequire : 1
             * createAt : 1572848374000
             * dateStatus : 0
             * deliveryAddressId : 105
             * deliveryArriveTime : 18:00
             * deliveryDate : 20191104
             * deliveryGroupId : 0
             * deliveryGroupNumber : 0
             * deliveryInfo : 20191104
             * deliveryNumber : 0
             * deliveryStatus : 0
             * deliveryTime : 17:30
             * dineType : 0
             * duration : 86400
             * flows : [{"avatar":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoLSsEoe3RMhTxfic8reqibyjJhibX3ZQAu2iccg8YopbiceVyknPJJ6BfNiaOaI9xMR4VO5Ix4fOIQjNOw/132","hourTime":"14:19","time":"2019-11-04 14:19:41","userName":"T."},{"avatar":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoLSsEoe3RMhTxfic8reqibyjJhibX3ZQAu2iccg8YopbiceVyknPJJ6BfNiaOaI9xMR4VO5Ix4fOIQjNOw/132","hourTime":"14:19","time":"2019-11-04 14:19:41","userName":"T."}]
             * groupId : 3019
             * items : [{"count":1,"name":"测试头牌菜1","pictures":"https://image.goukugogo.com/201910310433341162e3d1021","price":5,"pricePreferential":0.01,"refundStatus":0,"skuId":181210202}]
             * openid : oK5rH5fMrZEi4wArnPWE9kcWiWjs
             * orderCountTotal : 0
             * orderId : 519110414193405296
             * orderIdStr : 519110414193405296
             * pickupTime : 20191104 16:30-17:30
             * shopAddress : 富力运河
             * shopId : 110010000000006
             * shopIdStr : 110010000000006
             * shopName : 永慧超市
             * shopPhone : 13141292840
             * skuCountTotal : 0
             * supplyPoolId : 70
             * takeMealPoint : 11
             * wareNum : 0
             */

            private String countComplete;
            private String countRequire;
            private String createAt;
            private String dateStatus;
            private String deliveryAddressId;
            private String deliveryArriveTime;
            private String deliveryDate;
            private String deliveryGroupId;
            private String deliveryGroupNumber;
            private String deliveryInfo;
            private String deliveryNumber;
            private String deliveryStatus;
            private String deliveryTime;
            private String dineType;
            private String duration;
            private String groupId;
            private String openid;
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
            private List<FlowsBean> flows;
            private List<ItemsBean> items;

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

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
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

            public List<FlowsBean> getFlows() {
                return flows;
            }

            public void setFlows(List<FlowsBean> flows) {
                this.flows = flows;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class FlowsBean {
                /**
                 * avatar : https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoLSsEoe3RMhTxfic8reqibyjJhibX3ZQAu2iccg8YopbiceVyknPJJ6BfNiaOaI9xMR4VO5Ix4fOIQjNOw/132
                 * hourTime : 14:19
                 * time : 2019-11-04 14:19:41
                 * userName : T.
                 */

                private String avatar;
                private String hourTime;
                private String time;
                private String userName;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getHourTime() {
                    return hourTime;
                }

                public void setHourTime(String hourTime) {
                    this.hourTime = hourTime;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }
            }

            public static class ItemsBean {
                /**
                 * count : 1
                 * name : 测试头牌菜1
                 * pictures : https://image.goukugogo.com/201910310433341162e3d1021
                 * price : 5.0
                 * pricePreferential : 0.01
                 * refundStatus : 0
                 * skuId : 181210202
                 */

                private String count;
                private String name;
                private String pictures;
                private String price;
                private String pricePreferential;
                private String refundStatus;
                private String skuId;

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPictures() {
                    return pictures;
                }

                public void setPictures(String pictures) {
                    this.pictures = pictures;
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
            }
        }

        public static class LocationBean {
            /**
             * lat : 40.008952
             * lon : 116.353914
             */

            private String lat;
            private String lon;

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
        }

        public static class DeliveryMenBean {
            /**
             * deliveryManId : 54
             * deliveryManName : 骑手E
             * deliveryManPhone : 17777777777
             */

            private String deliveryManId;
            private String deliveryManName;
            private String deliveryManPhone;

            public String getDeliveryManId() {
                return deliveryManId;
            }

            public void setDeliveryManId(String deliveryManId) {
                this.deliveryManId = deliveryManId;
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
        }

        public static class ItemsBeanX {
            /**
             * count : 1
             * name : 限购商品3
             * orderId : 519110414193405297
             * pictures : https://image.goukugogo.com/201910120241186314945260c
             * price : 5.0
             * priceGouku : 5.0
             * pricePreferential : 0.01
             * priceSell : 0.01
             * priceUnit : 0.01
             * repeatCount : 0
             * skuId : 181000202
             */

            private String count;
            private String name;
            private String orderId;
            private String pictures;
            private String price;
            private String priceGouku;
            private String pricePreferential;
            private String priceSell;
            private String priceUnit;
            private String repeatCount;
            private String skuId;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getPictures() {
                return pictures;
            }

            public void setPictures(String pictures) {
                this.pictures = pictures;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPriceGouku() {
                return priceGouku;
            }

            public void setPriceGouku(String priceGouku) {
                this.priceGouku = priceGouku;
            }

            public String getPricePreferential() {
                return pricePreferential;
            }

            public void setPricePreferential(String pricePreferential) {
                this.pricePreferential = pricePreferential;
            }

            public String getPriceSell() {
                return priceSell;
            }

            public void setPriceSell(String priceSell) {
                this.priceSell = priceSell;
            }

            public String getPriceUnit() {
                return priceUnit;
            }

            public void setPriceUnit(String priceUnit) {
                this.priceUnit = priceUnit;
            }

            public String getRepeatCount() {
                return repeatCount;
            }

            public void setRepeatCount(String repeatCount) {
                this.repeatCount = repeatCount;
            }

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }
        }
    }
}
