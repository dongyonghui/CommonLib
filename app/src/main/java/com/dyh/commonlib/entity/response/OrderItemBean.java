package com.dyh.commonlib.entity.response;

import android.content.Context;
import android.text.TextUtils;

import com.dyh.commonlib.R;
import com.dyh.common.lib.dw.util.JsonUtils;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.dw.util.TimeUtil;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/13/013 14:06
 * 描述：订单信息
 */
public class OrderItemBean {

    /**
     * openid : oK5rH5WSzIb_C5a7YCFdOpMhlFyw
     * shopId : 1100136
     * shopIdStr : 1100136
     * shopName : 觅姐麻辣烫
     * shopLogo : null
     * shopPhone : 15010880060
     * shopAddress : 服仓文创园
     * shopLat : 39.898363
     * shopLon : 116.563687
     * userPhone : 13109833329
     * userAddress : 北京市朝阳区高碑店镇泰丰新港(北京第二办公区)服仓文创园(2层201室)
     * userName : 树森℡
     * deliveryManPhone : 15010880060
     * distribution : 2019-12-10 19:00
     * distributionFormat : 12-10 19:00
     * orderType : 0
     * orderId : 519121015283239007
     * orderIdStr : 519121015283239007
     * mergeOrderIdStr : 519121015283239006
     * mergeOrderId : 519121015283239006
     * prepayOrderId : 0
     * number : 84482
     * count : 1
     * payReduce : 4.9
     * payFreight : 2.0
     * payService : null
     * payTotal : 5.0
     * payActual : 0.1
     * shopMoney : 0.1
     * orderDiscount : 0.0
     * createTime : 1575962912000
     * payTime : 1575962918000
     * status : 9
     * remark : null
     * countDown : 0
     * groupId : 86054
     * oldGroupId : 86052
     * groupUseType : 4
     * groupStatus : 2
     * peopleCount : 0
     * tableNo : -1
     * sequence : 2
     * checkStatus : 1
     * companyName : null
     * companyNumber : 201
     * barcodeImageUrl : https://image.goukugogo.com/gkbar201912100328381985519782c?x-oss-process=style/OrderBarcode
     * qrcodeImageUrl : https://image.goukugogo.com/gkqr201912100330004501e3230c2?x-oss-process=style/OrderBarcode
     * deliveryAddressId : 764
     * takeMealPoint : 0
     * takeMealPointName : null
     * deliveryMealAddress : null
     * deliveryManIds : 550
     * refundStatus : 5
     * refundTime : 1575968585000
     * refundReasons : 拿错
     * refundWay : 2
     * refundRecord : [{"time":"2019-12-10 17:02:48","describe":"[\"全部商品\"]","refundReasons":"拿错","refundPrice":2.10}]
     * skuCount : 0
     * usedCount : 0
     * items : [{"orderId":519121015283239007,"skuId":494450202,"skuUnitId":null,"unitName":null,"count":1,"name":"测试","price":5,"pricePreferential":0.1,"priceGouku":5,"priceSell":0.1,"priceUnit":0.1,"pictures":"https://image.goukugogo.com/2019120402354265010ebfe98","createAt":null,"repeatCount":0}]
     * groupOrder : {"id":null,"shopId":1100136,"shopIdStr":"1100136","groupId":86052,"orderId":519121015233938999,"orderIdStr":"519121015233938999","openid":"oK5rH5a4VcG2SSc8W7-y4AS_q9aA","userId":null,"userName":"金木歌.头牌菜","status":2,"countRequire":1,"countComplete":2,"createAt":1575962564000,"duration":259200,"flows":[{"time":"2019-12-10 15:23:47","userName":"金木歌.头牌菜","openid":"oK5rH5a4VcG2SSc8W7-y4AS_q9aA","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Ug3vL4QjoP9X6JkMFcSMNc9IXlicib8umiaYTsV24UvCQWShKZrEICfu9icTjq5BNS6CnqjJVUEEicmtXEfDz1R3B8Q/132","hourTime":"15:23"},{"time":"2019-12-10 15:28:38","userName":"树森℡","openid":"oK5rH5WSzIb_C5a7YCFdOpMhlFyw","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI5W2o7ITf9Tsuvqt4yXLowSvREFj45FV7PlOnTRHW3UELNtOTrMePX8l64vqHyuSdxSIXOSlCqxw/132","hourTime":"15:28"}],"deliveryNumber":0,"deliveryAddress":null,"deliveryAddressName":null,"addressOffDutyTime":null,"deliveryAddressId":764,"addressLocation":null,"cityId":null,"districtId":null,"groupType":6,"deliveryStatus":0,"deliveryInfo":"20191210","deliveryManPhone":null,"deliveryManName":null,"deliveryManId":null,"pickupTime":"20191210 17:30-18:30","items":[{"skuId":494450202,"orderId":null,"mergeOrderId":null,"count":1,"name":"测试","price":5,"pricePreferential":0.1,"priceSell":null,"refundStatus":0,"pictures":"https://image.goukugogo.com/2019120402354265010ebfe98","deliveryAddressId":null,"takeMealPoint":null,"deliveryAddressName":null,"takeMealPointName":null,"groupUseType":null,"address":null,"groupId":null,"addressLon":null,"addressLat":null,"userAddressId":null,"userName":null,"openid":null,"userPhone":null,"companyName":null,"houseNum":null,"companyNumber":null,"deliveryGroupId":null,"groupAddresses":null}],"itemsRefund":null,"shopAddress":null,"shopName":null,"shopPhone":null,"shopLocation":null,"dineType":0,"deliveryDate":"20191210","deliveryTime":"18:00","deliveryArriveTime":"19:00","dateStr":null,"dateStatus":0,"deliveryMen":null,"pressureType":null,"groupIdList":null,"deliveryManStatus":null,"takeMealPoint":2,"takeMealPointName":null,"companyNumber":"201","supplyPoolId":35,"supplyPoolShopNames":null,"shops":null,"deliveryGroupId":0,"useType":null,"orderCountTotal":0,"skuCountTotal":0,"deliveryGroupNumber":0,"wareNum":0,"mergeGroupId":null,"deliveryFee":null,"currentDeliveryFee":null,"userAddressId":26639,"mergeList":null}
     * location : {"lat":39.899587,"lon":116.55854}
     * deliveryAddressName : 服装商务空间
     * deliveryMen : [{"deliveryManId":550,"deliveryManPhone":"15010880060","deliveryManName":"张展"}]
     * userAddressInfo : null
     * couponId : 0
     * teamId : 0
     * supplyPoolId : 35
     * last : false
     */

    private String openid;
    private String shopId;
    private String shopIdStr;
    private String shopName;
    private String shopLogo;
    private String shopPhone;
    private String shopAddress;
    private String shopLat;
    private String shopLon;
    private String userPhone;
    private String userAddress;
    private String userName;
    private String deliveryManName = "";
    private String deliveryManPhone;
    private String distribution;
    private String distributionFormat;
    private String orderType;
    private String orderId;
    private String orderIdStr;
    private String mergeOrderIdStr;
    private String mergeOrderId;
    private String prepayOrderId;
    private String number;
    private String count;
    private String payReduce;
    private String payFreight;
    private String payTotal;
    private String payActual;
    private String shopMoney;
    private String orderDiscount;
    private String createTime;
    private String payTime;
    private String status;
    private String remark;
    private String countDown;
    private String groupId;
    private String oldGroupId;
    private String groupUseType;
    private String groupStatus;
    private String peopleCount;
    private String tableNo;
    private String sequence;
    private String checkStatus;
    private String companyName;
    private String companyNumber;
    private String barcodeImageUrl;
    private String qrcodeImageUrl;
    private String deliveryAddressId;
    private String takeMealPoint;
    private String takeMealPointName;
    private String deliveryManIds;
    private String refundStatus;
    private String refundTime;
    private String refundReasons;
    private String refundWay;
    private String refundInfo;
    private String refundRecord;
    public String refundRecordLastTime;
    private String skuCount;
    private String usedCount;
    private GroupOrderBean groupOrder;
    private LocationBean location;
    private DeliveryMealAddressBean deliveryMealAddress;
    private String deliveryAddressName;
    private String userAddressInfo;
    private String couponId;
    private String teamId;
    private String supplyPoolId;
    private boolean last;
    private List<GoodsItemsBean> items;
    private List<DeliveryMenBean> deliveryMen;

    /**
     * 获取UI显示的用户信息
     *
     * @param context
     * @return
     */
    public String getCustomerFormatInfo(Context context) {
        return context.getString(R.string.formatString_customerInfo, getUserName(), getUserPhone(), getUserAddress());
    }

    /**
     * 获取UI显示的店铺信息
     *
     * @param context
     * @return
     */
    public String getShopFormatInfo(Context context) {
        return context.getString(R.string.formatString_orderListShopInfo, getShopName(), getShopPhone(), getShopAddress());
    }

    /**
     * 获取送达时间UI显示用
     *
     * @return
     */
    public String getArrivedTimeInfo() {
        if (groupOrder == null) {
            return "";
        }
        return groupOrder.deliveryArriveTime + "\n" + groupOrder.deliveryDate;
    }

    /**
     * @return 获取所有骑手的信息UI显示用
     */
    public String getDeliverMansInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (null != deliveryMen && deliveryMen.size() > 0) {
            for (DeliveryMenBean deliveryMAN : deliveryMen) {
                if (!TextUtils.isEmpty(stringBuilder)) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append(deliveryMAN.getDeliveryManName()).append("：").append(deliveryMAN.getDeliveryManPhone());
            }
        } else {
            stringBuilder.append(deliveryManName).append("：").append(deliveryManPhone);
        }
        return stringBuilder.toString();
    }

    public DeliveryMealAddressBean getDeliveryMealAddress() {
        return deliveryMealAddress;
    }

    public void setDeliveryMealAddress(DeliveryMealAddressBean deliveryMealAddress) {
        this.deliveryMealAddress = deliveryMealAddress;
    }

    public String getFormatRefundTime() {
        Date date;
        if (TextUtils.isEmpty(refundTime) && !TextUtils.isEmpty(refundRecord)) {
            List<RefundRecordBean> list = JsonUtils.object(refundRecord, new TypeToken<List<RefundRecordBean>>() {
            }.getType());
            if (null != list && list.size() > 0) {
                RefundRecordBean refundRecordBean = list.get(list.size() - 1);
                refundRecordLastTime = refundRecordBean.time;
            }
            date = TimeUtil.millis2Date(MathUtil.getLongNumber(refundRecordLastTime));
        } else {
            date = TimeUtil.millis2Date(MathUtil.getLongNumber(refundTime));
        }
        return TimeUtil.date2String(date, "yyyy-MM-dd HH:mm:ss");
    }

    public String getRefundInfo() {
        if (!TextUtils.isEmpty(refundRecord)) {
            List<RefundRecordBean> list = JsonUtils.object(refundRecord, new TypeToken<List<RefundRecordBean>>() {
            }.getType());
            if (null != list && list.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                RefundRecordBean refundRecordBean = list.get(list.size() - 1);
                refundRecordLastTime = refundRecordBean.time;
                List<String> desList = JsonUtils.object(refundRecordBean.describe, new TypeToken<List<String>>() {
                }.getType());
                for (String s : desList) {
                    stringBuilder.append(s).append(";");
                }

                return stringBuilder.toString();
            }
        }
        return refundInfo;
    }

    public void setRefundInfo(String refundInfo) {
        this.refundInfo = refundInfo;
    }

    public String getDeliveryManName() {
        return deliveryManName;
    }

    public void setDeliveryManName(String deliveryManName) {
        this.deliveryManName = deliveryManName;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopLat() {
        return shopLat;
    }

    public void setShopLat(String shopLat) {
        this.shopLat = shopLat;
    }

    public String getShopLon() {
        return shopLon;
    }

    public void setShopLon(String shopLon) {
        this.shopLon = shopLon;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public String getMergeOrderIdStr() {
        return mergeOrderIdStr;
    }

    public void setMergeOrderIdStr(String mergeOrderIdStr) {
        this.mergeOrderIdStr = mergeOrderIdStr;
    }

    public String getMergeOrderId() {
        return mergeOrderId;
    }

    public void setMergeOrderId(String mergeOrderId) {
        this.mergeOrderId = mergeOrderId;
    }

    public String getPrepayOrderId() {
        return prepayOrderId;
    }

    public void setPrepayOrderId(String prepayOrderId) {
        this.prepayOrderId = prepayOrderId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPayReduce() {
        return payReduce;
    }

    public void setPayReduce(String payReduce) {
        this.payReduce = payReduce;
    }

    public String getPayFreight() {
        return payFreight;
    }

    public void setPayFreight(String payFreight) {
        this.payFreight = payFreight;
    }

    public String getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(String payTotal) {
        this.payTotal = payTotal;
    }

    public String getPayActual() {
        return payActual;
    }

    public void setPayActual(String payActual) {
        this.payActual = payActual;
    }

    public String getShopMoney() {
        return shopMoney;
    }

    public void setShopMoney(String shopMoney) {
        this.shopMoney = shopMoney;
    }

    public String getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(String orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCountDown() {
        return countDown;
    }

    public void setCountDown(String countDown) {
        this.countDown = countDown;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getOldGroupId() {
        return oldGroupId;
    }

    public void setOldGroupId(String oldGroupId) {
        this.oldGroupId = oldGroupId;
    }

    public String getGroupUseType() {
        return groupUseType;
    }

    public void setGroupUseType(String groupUseType) {
        this.groupUseType = groupUseType;
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }

    public String getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(String peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getBarcodeImageUrl() {
        return barcodeImageUrl;
    }

    public void setBarcodeImageUrl(String barcodeImageUrl) {
        this.barcodeImageUrl = barcodeImageUrl;
    }

    public String getQrcodeImageUrl() {
        return qrcodeImageUrl;
    }

    public void setQrcodeImageUrl(String qrcodeImageUrl) {
        this.qrcodeImageUrl = qrcodeImageUrl;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
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

    public String getDeliveryManIds() {
        return deliveryManIds;
    }

    public void setDeliveryManIds(String deliveryManIds) {
        this.deliveryManIds = deliveryManIds;
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

    public String getRefundReasons() {
        return refundReasons;
    }

    public void setRefundReasons(String refundReasons) {
        this.refundReasons = refundReasons;
    }

    public String getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(String refundWay) {
        this.refundWay = refundWay;
    }

    public String getRefundRecord() {
        return refundRecord;
    }

    public void setRefundRecord(String refundRecord) {
        this.refundRecord = refundRecord;
    }

    public String getSkuCount() {
        return skuCount;
    }

    public void setSkuCount(String skuCount) {
        this.skuCount = skuCount;
    }

    public String getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(String usedCount) {
        this.usedCount = usedCount;
    }

    public GroupOrderBean getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(GroupOrderBean groupOrder) {
        this.groupOrder = groupOrder;
    }

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public String getDeliveryAddressName() {
        return deliveryAddressName;
    }

    public void setDeliveryAddressName(String deliveryAddressName) {
        this.deliveryAddressName = deliveryAddressName;
    }

    public String getUserAddressInfo() {
        return userAddressInfo;
    }

    public void setUserAddressInfo(String userAddressInfo) {
        this.userAddressInfo = userAddressInfo;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getSupplyPoolId() {
        return supplyPoolId;
    }

    public void setSupplyPoolId(String supplyPoolId) {
        this.supplyPoolId = supplyPoolId;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List<GoodsItemsBean> getItems() {
        return items;
    }

    public void setItems(List<GoodsItemsBean> items) {
        this.items = items;
    }

    public List<DeliveryMenBean> getDeliveryMen() {
        return deliveryMen;
    }

    public void setDeliveryMen(List<DeliveryMenBean> deliveryMen) {
        this.deliveryMen = deliveryMen;
    }

    public static class RefundRecordBean {
        public String time;
        public String describe;
    }

    public static class GroupOrderBean {
        /**
         * id : null
         * shopId : 1100136
         * shopIdStr : 1100136
         * groupId : 86052
         * orderId : 519121015233938999
         * orderIdStr : 519121015233938999
         * openid : oK5rH5a4VcG2SSc8W7-y4AS_q9aA
         * userId : null
         * userName : 金木歌.头牌菜
         * status : 2
         * countRequire : 1
         * countComplete : 2
         * createAt : 1575962564000
         * duration : 259200
         * flows : [{"time":"2019-12-10 15:23:47","userName":"金木歌.头牌菜","openid":"oK5rH5a4VcG2SSc8W7-y4AS_q9aA","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Ug3vL4QjoP9X6JkMFcSMNc9IXlicib8umiaYTsV24UvCQWShKZrEICfu9icTjq5BNS6CnqjJVUEEicmtXEfDz1R3B8Q/132","hourTime":"15:23"},{"time":"2019-12-10 15:28:38","userName":"树森℡","openid":"oK5rH5WSzIb_C5a7YCFdOpMhlFyw","avatar":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI5W2o7ITf9Tsuvqt4yXLowSvREFj45FV7PlOnTRHW3UELNtOTrMePX8l64vqHyuSdxSIXOSlCqxw/132","hourTime":"15:28"}]
         * deliveryNumber : 0
         * deliveryAddress : null
         * deliveryAddressName : null
         * addressOffDutyTime : null
         * deliveryAddressId : 764
         * addressLocation : null
         * cityId : null
         * districtId : null
         * groupType : 6
         * deliveryStatus : 0
         * deliveryInfo : 20191210
         * deliveryManPhone : null
         * deliveryManName : null
         * deliveryManId : null
         * pickupTime : 20191210 17:30-18:30
         * items : [{"skuId":494450202,"orderId":null,"mergeOrderId":null,"count":1,"name":"测试","price":5,"pricePreferential":0.1,"priceSell":null,"refundStatus":0,"pictures":"https://image.goukugogo.com/2019120402354265010ebfe98","deliveryAddressId":null,"takeMealPoint":null,"deliveryAddressName":null,"takeMealPointName":null,"groupUseType":null,"address":null,"groupId":null,"addressLon":null,"addressLat":null,"userAddressId":null,"userName":null,"openid":null,"userPhone":null,"companyName":null,"houseNum":null,"companyNumber":null,"deliveryGroupId":null,"groupAddresses":null}]
         * itemsRefund : null
         * shopAddress : null
         * shopName : null
         * shopPhone : null
         * shopLocation : null
         * dineType : 0
         * deliveryDate : 20191210
         * deliveryTime : 18:00
         * deliveryArriveTime : 19:00
         * dateStr : null
         * dateStatus : 0
         * deliveryMen : null
         * pressureType : null
         * groupIdList : null
         * deliveryManStatus : null
         * takeMealPoint : 2
         * takeMealPointName : null
         * companyNumber : 201
         * supplyPoolId : 35
         * supplyPoolShopNames : null
         * shops : null
         * deliveryGroupId : 0
         * useType : null
         * orderCountTotal : 0
         * skuCountTotal : 0
         * deliveryGroupNumber : 0
         * wareNum : 0
         * mergeGroupId : null
         * deliveryFee : null
         * currentDeliveryFee : null
         * userAddressId : 26639
         * mergeList : null
         */

        private String id;
        private String shopId;
        private String shopIdStr;
        private String groupId;
        private String orderId;
        private String orderIdStr;
        private String openid;
        private String userId;
        private String userName;
        private String status;
        private String countRequire;
        private String countComplete;
        private String createAt;
        private String duration;
        private String deliveryNumber;
        private String deliveryAddress;
        private String deliveryAddressName;
        private String addressOffDutyTime;
        private String deliveryAddressId;
        private String addressLocation;
        private String cityId;
        private String districtId;
        private String groupType;
        private String deliveryStatus;
        private String deliveryInfo;
        private String deliveryManPhone;
        private String deliveryManName;
        private String deliveryManId;
        private String pickupTime;
        private String itemsRefund;
        private String shopAddress;
        private String shopName;
        private String shopPhone;
        private String shopLocation;
        private String dineType;
        private String deliveryDate;
        private String deliveryTime;
        private String deliveryArriveTime;
        private String dateStr;
        private String dateStatus;
        private String deliveryMen;
        private String pressureType;
        private String groupIdList;
        private String deliveryManStatus;
        private String takeMealPoint;
        private String takeMealPointName;
        private String companyNumber;
        private String supplyPoolId;
        private String supplyPoolShopNames;
        private String shops;
        private String deliveryGroupId;
        private String useType;
        private String orderCountTotal;
        private String skuCountTotal;
        private String deliveryGroupNumber;
        private String wareNum;
        private String mergeGroupId;
        private String deliveryFee;
        private String currentDeliveryFee;
        private String userAddressId;
        private String mergeList;
        private List<FlowsBean> flows;
        private List<ItemsBean> items;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
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

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCountRequire() {
            return countRequire;
        }

        public void setCountRequire(String countRequire) {
            this.countRequire = countRequire;
        }

        public String getCountComplete() {
            return countComplete;
        }

        public void setCountComplete(String countComplete) {
            this.countComplete = countComplete;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDeliveryNumber() {
            return deliveryNumber;
        }

        public void setDeliveryNumber(String deliveryNumber) {
            this.deliveryNumber = deliveryNumber;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public String getDeliveryAddressName() {
            return deliveryAddressName;
        }

        public void setDeliveryAddressName(String deliveryAddressName) {
            this.deliveryAddressName = deliveryAddressName;
        }

        public String getAddressOffDutyTime() {
            return addressOffDutyTime;
        }

        public void setAddressOffDutyTime(String addressOffDutyTime) {
            this.addressOffDutyTime = addressOffDutyTime;
        }

        public String getDeliveryAddressId() {
            return deliveryAddressId;
        }

        public void setDeliveryAddressId(String deliveryAddressId) {
            this.deliveryAddressId = deliveryAddressId;
        }

        public String getAddressLocation() {
            return addressLocation;
        }

        public void setAddressLocation(String addressLocation) {
            this.addressLocation = addressLocation;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getGroupType() {
            return groupType;
        }

        public void setGroupType(String groupType) {
            this.groupType = groupType;
        }

        public String getDeliveryStatus() {
            return deliveryStatus;
        }

        public void setDeliveryStatus(String deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        public String getDeliveryInfo() {
            return deliveryInfo;
        }

        public void setDeliveryInfo(String deliveryInfo) {
            this.deliveryInfo = deliveryInfo;
        }

        public String getDeliveryManPhone() {
            return deliveryManPhone;
        }

        public void setDeliveryManPhone(String deliveryManPhone) {
            this.deliveryManPhone = deliveryManPhone;
        }

        public String getDeliveryManName() {
            return deliveryManName;
        }

        public void setDeliveryManName(String deliveryManName) {
            this.deliveryManName = deliveryManName;
        }

        public String getDeliveryManId() {
            return deliveryManId;
        }

        public void setDeliveryManId(String deliveryManId) {
            this.deliveryManId = deliveryManId;
        }

        public String getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(String pickupTime) {
            this.pickupTime = pickupTime;
        }

        public String getItemsRefund() {
            return itemsRefund;
        }

        public void setItemsRefund(String itemsRefund) {
            this.itemsRefund = itemsRefund;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
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

        public String getShopLocation() {
            return shopLocation;
        }

        public void setShopLocation(String shopLocation) {
            this.shopLocation = shopLocation;
        }

        public String getDineType() {
            return dineType;
        }

        public void setDineType(String dineType) {
            this.dineType = dineType;
        }

        public String getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getDeliveryArriveTime() {
            return deliveryArriveTime;
        }

        public void setDeliveryArriveTime(String deliveryArriveTime) {
            this.deliveryArriveTime = deliveryArriveTime;
        }

        public String getDateStr() {
            return dateStr;
        }

        public void setDateStr(String dateStr) {
            this.dateStr = dateStr;
        }

        public String getDateStatus() {
            return dateStatus;
        }

        public void setDateStatus(String dateStatus) {
            this.dateStatus = dateStatus;
        }

        public String getDeliveryMen() {
            return deliveryMen;
        }

        public void setDeliveryMen(String deliveryMen) {
            this.deliveryMen = deliveryMen;
        }

        public String getPressureType() {
            return pressureType;
        }

        public void setPressureType(String pressureType) {
            this.pressureType = pressureType;
        }

        public String getGroupIdList() {
            return groupIdList;
        }

        public void setGroupIdList(String groupIdList) {
            this.groupIdList = groupIdList;
        }

        public String getDeliveryManStatus() {
            return deliveryManStatus;
        }

        public void setDeliveryManStatus(String deliveryManStatus) {
            this.deliveryManStatus = deliveryManStatus;
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

        public String getCompanyNumber() {
            return companyNumber;
        }

        public void setCompanyNumber(String companyNumber) {
            this.companyNumber = companyNumber;
        }

        public String getSupplyPoolId() {
            return supplyPoolId;
        }

        public void setSupplyPoolId(String supplyPoolId) {
            this.supplyPoolId = supplyPoolId;
        }

        public String getSupplyPoolShopNames() {
            return supplyPoolShopNames;
        }

        public void setSupplyPoolShopNames(String supplyPoolShopNames) {
            this.supplyPoolShopNames = supplyPoolShopNames;
        }

        public String getShops() {
            return shops;
        }

        public void setShops(String shops) {
            this.shops = shops;
        }

        public String getDeliveryGroupId() {
            return deliveryGroupId;
        }

        public void setDeliveryGroupId(String deliveryGroupId) {
            this.deliveryGroupId = deliveryGroupId;
        }

        public String getUseType() {
            return useType;
        }

        public void setUseType(String useType) {
            this.useType = useType;
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

        public String getDeliveryGroupNumber() {
            return deliveryGroupNumber;
        }

        public void setDeliveryGroupNumber(String deliveryGroupNumber) {
            this.deliveryGroupNumber = deliveryGroupNumber;
        }

        public String getWareNum() {
            return wareNum;
        }

        public void setWareNum(String wareNum) {
            this.wareNum = wareNum;
        }

        public String getMergeGroupId() {
            return mergeGroupId;
        }

        public void setMergeGroupId(String mergeGroupId) {
            this.mergeGroupId = mergeGroupId;
        }

        public String getDeliveryFee() {
            return deliveryFee;
        }

        public void setDeliveryFee(String deliveryFee) {
            this.deliveryFee = deliveryFee;
        }

        public String getCurrentDeliveryFee() {
            return currentDeliveryFee;
        }

        public void setCurrentDeliveryFee(String currentDeliveryFee) {
            this.currentDeliveryFee = currentDeliveryFee;
        }

        public String getUserAddressId() {
            return userAddressId;
        }

        public void setUserAddressId(String userAddressId) {
            this.userAddressId = userAddressId;
        }

        public String getMergeList() {
            return mergeList;
        }

        public void setMergeList(String mergeList) {
            this.mergeList = mergeList;
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
             * time : 2019-12-10 15:23:47
             * userName : 金木歌.头牌菜
             * openid : oK5rH5a4VcG2SSc8W7-y4AS_q9aA
             * avatar : https://wx.qlogo.cn/mmopen/vi_32/Ug3vL4QjoP9X6JkMFcSMNc9IXlicib8umiaYTsV24UvCQWShKZrEICfu9icTjq5BNS6CnqjJVUEEicmtXEfDz1R3B8Q/132
             * hourTime : 15:23
             */

            private String time;
            private String userName;
            private String openid;
            private String avatar;
            private String hourTime;

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

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

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
        }

        public static class ItemsBean {
            /**
             * skuId : 494450202
             * orderId : null
             * mergeOrderId : null
             * count : 1
             * name : 测试
             * price : 5.0
             * pricePreferential : 0.1
             * priceSell : null
             * refundStatus : 0
             * pictures : https://image.goukugogo.com/2019120402354265010ebfe98
             * deliveryAddressId : null
             * takeMealPoint : null
             * deliveryAddressName : null
             * takeMealPointName : null
             * groupUseType : null
             * address : null
             * groupId : null
             * addressLon : null
             * addressLat : null
             * userAddressId : null
             * userName : null
             * openid : null
             * userPhone : null
             * companyName : null
             * houseNum : null
             * companyNumber : null
             * deliveryGroupId : null
             * groupAddresses : null
             */

            private String skuId;
            private String orderId;
            private String mergeOrderId;
            private String count;
            private String name;
            private String price;
            private String pricePreferential;
            private String priceSell;
            private String refundStatus;
            private String pictures;
            private String deliveryAddressId;
            private String takeMealPoint;
            private String deliveryAddressName;
            private String takeMealPointName;
            private String groupUseType;
            private String address;
            private String groupId;
            private String addressLon;
            private String addressLat;
            private String userAddressId;
            private String userName;
            private String openid;
            private String userPhone;
            private String companyName;
            private String houseNum;
            private String companyNumber;
            private String deliveryGroupId;
            private String groupAddresses;

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getMergeOrderId() {
                return mergeOrderId;
            }

            public void setMergeOrderId(String mergeOrderId) {
                this.mergeOrderId = mergeOrderId;
            }

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

            public String getPriceSell() {
                return priceSell;
            }

            public void setPriceSell(String priceSell) {
                this.priceSell = priceSell;
            }

            public String getRefundStatus() {
                return refundStatus;
            }

            public void setRefundStatus(String refundStatus) {
                this.refundStatus = refundStatus;
            }

            public String getPictures() {
                return pictures;
            }

            public void setPictures(String pictures) {
                this.pictures = pictures;
            }

            public String getDeliveryAddressId() {
                return deliveryAddressId;
            }

            public void setDeliveryAddressId(String deliveryAddressId) {
                this.deliveryAddressId = deliveryAddressId;
            }

            public String getTakeMealPoint() {
                return takeMealPoint;
            }

            public void setTakeMealPoint(String takeMealPoint) {
                this.takeMealPoint = takeMealPoint;
            }

            public String getDeliveryAddressName() {
                return deliveryAddressName;
            }

            public void setDeliveryAddressName(String deliveryAddressName) {
                this.deliveryAddressName = deliveryAddressName;
            }

            public String getTakeMealPointName() {
                return takeMealPointName;
            }

            public void setTakeMealPointName(String takeMealPointName) {
                this.takeMealPointName = takeMealPointName;
            }

            public String getGroupUseType() {
                return groupUseType;
            }

            public void setGroupUseType(String groupUseType) {
                this.groupUseType = groupUseType;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getAddressLon() {
                return addressLon;
            }

            public void setAddressLon(String addressLon) {
                this.addressLon = addressLon;
            }

            public String getAddressLat() {
                return addressLat;
            }

            public void setAddressLat(String addressLat) {
                this.addressLat = addressLat;
            }

            public String getUserAddressId() {
                return userAddressId;
            }

            public void setUserAddressId(String userAddressId) {
                this.userAddressId = userAddressId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getHouseNum() {
                return houseNum;
            }

            public void setHouseNum(String houseNum) {
                this.houseNum = houseNum;
            }

            public String getCompanyNumber() {
                return companyNumber;
            }

            public void setCompanyNumber(String companyNumber) {
                this.companyNumber = companyNumber;
            }

            public String getDeliveryGroupId() {
                return deliveryGroupId;
            }

            public void setDeliveryGroupId(String deliveryGroupId) {
                this.deliveryGroupId = deliveryGroupId;
            }

            public String getGroupAddresses() {
                return groupAddresses;
            }

            public void setGroupAddresses(String groupAddresses) {
                this.groupAddresses = groupAddresses;
            }
        }
    }

    public static class DeliveryMealAddressBean {

        /**
         * id : 96
         * mealAddress : 正门门口
         * lat : 31.64273
         * lon : 121.259278
         */

        private int id;
        private String mealAddress;
        private double lat;
        private double lon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMealAddress() {
            return mealAddress;
        }

        public void setMealAddress(String mealAddress) {
            this.mealAddress = mealAddress;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }

    public static class LocationBean {
        /**
         * lat : 39.899587
         * lon : 116.55854
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

    public static class GoodsItemsBean {
        /**
         * orderId : 519121015283239007
         * skuId : 494450202
         * skuUnitId : null
         * unitName : null
         * count : 1
         * name : 测试
         * price : 5.0
         * pricePreferential : 0.1
         * priceGouku : 5.0
         * priceSell : 0.1
         * priceUnit : 0.1
         * pictures : https://image.goukugogo.com/2019120402354265010ebfe98
         * createAt : null
         * repeatCount : 0
         */

        private String orderId;
        private String skuId;
        private String skuUnitId;
        private String unitName;
        private String count;
        private String name;
        private String price;
        private String pricePreferential;
        private String priceGouku;
        private String priceSell;
        private String priceUnit;
        private String pictures;
        private String createAt;
        private String repeatCount;

        public int refundCount;//本地使用，退款的个数

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSkuUnitId() {
            return skuUnitId;
        }

        public void setSkuUnitId(String skuUnitId) {
            this.skuUnitId = skuUnitId;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

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

        public String getPriceGouku() {
            return priceGouku;
        }

        public void setPriceGouku(String priceGouku) {
            this.priceGouku = priceGouku;
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

        public String getPictures() {
            return pictures;
        }

        public void setPictures(String pictures) {
            this.pictures = pictures;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getRepeatCount() {
            return repeatCount;
        }

        public void setRepeatCount(String repeatCount) {
            this.repeatCount = repeatCount;
        }
    }

    public static class DeliveryMenBean {
        /**
         * deliveryManId : 550
         * deliveryManPhone : 15010880060
         * deliveryManName : 张展
         */

        private String deliveryManId;
        private String deliveryManPhone;
        private String deliveryManName;

        public String getDeliveryManId() {
            return deliveryManId;
        }

        public void setDeliveryManId(String deliveryManId) {
            this.deliveryManId = deliveryManId;
        }

        public String getDeliveryManPhone() {
            return deliveryManPhone;
        }

        public void setDeliveryManPhone(String deliveryManPhone) {
            this.deliveryManPhone = deliveryManPhone;
        }

        public String getDeliveryManName() {
            return deliveryManName;
        }

        public void setDeliveryManName(String deliveryManName) {
            this.deliveryManName = deliveryManName;
        }
    }
}
