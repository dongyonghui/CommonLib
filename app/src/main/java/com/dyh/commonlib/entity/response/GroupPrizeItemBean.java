package com.dyh.commonlib.entity.response;

import android.content.Context;
import android.text.TextUtils;

import com.dyh.commonlib.R;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/10/010 18:23
 * 描述：团体奖励列表项
 */
public class GroupPrizeItemBean {

    /**
     * userTeamAwardId : 2
     * cityId : 110100
     * cityName : null
     * shopId : 1101001001
     * shopName : 北京市团体奖励店
     * phone : 17719015857
     * address : 北京市
     * wareList : [{"shopId":1101001001,"name":"哇哈哈4支","picture":"https://image.goukugogo.com/201910111055082707201e3dc","personNum":1,"onlinePrice":0,"onlineGroupPrice":0,"skuId":11010010011,"description":"1"},{"shopId":1101001001,"name":"果盘1份","picture":"https://image.goukugogo.com/20191011105620285b8fe20fd","personNum":2,"onlinePrice":0,"onlineGroupPrice":0,"skuId":11010010012,"description":"2"},{"shopId":1101001001,"name":"炸鸡拼盘1盘","picture":"https://image.goukugogo.com/20191011105611836100bbb36","personNum":3,"onlinePrice":0,"onlineGroupPrice":0,"skuId":11010010013,"description":"31"}]
     * defaultAward : 1
     * deliveryAddressList : [{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"测试地址G","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":132,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"中关村768创意产业园管理委员会","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":125,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"新世界百货啊","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":95,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"中国农业大学东校区","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":94,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"测试地址b","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":108,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"测试地址N","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":106,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"测试地址C","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":105,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"测试地址B","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":104,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"测试地址A","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":103,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"北京测试地址10","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":102,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"中国农业大学","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":98,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"林州","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":97,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"怡馨家园","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":93,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"中国农业大学足球场(学苑五路)","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":92,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"中国农业大学东校区第三食堂","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":90,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"黄海大厦111","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":73,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"测试地址，不要改","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":48,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"银座商务大厦","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":47,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"学清嘉创大厦","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":43,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":null,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":null,"title":"768创意产业园B座","lat":null,"lon":null,"location":null,"shops":null,"deliveryManId":null,"distance":null,"remark":null,"deliveryAddressId":134,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":null,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null}]
     */

    private String userTeamAwardId;
    private String cityId;
    private String cityName;
    private String shopId;
    private String shopName;
    private String phone;
    private String address;
    private String defaultAward;
    private List<WareListBean> wareList;
    private List<DeliveryAddressManageItemBean> deliveryAddressList;

    public String getWareTextInfo(Context context){
        StringBuilder waresInfo = new StringBuilder();
        if (wareList != null) {
            for (int i = 0; i < wareList.size(); i++) {
                if (!TextUtils.isEmpty(waresInfo)) {
                    waresInfo.append("\n");
                }
                waresInfo.append(context.getString(R.string.formatString_groupPrizeManageList, String.valueOf(i + 1)
                        , wareList.get(i).getPersonNum(), wareList.get(i).getName()));
            }
        }
        return waresInfo.toString();
    }

    public String getUserTeamAwardId() {
        return userTeamAwardId;
    }

    public void setUserTeamAwardId(String userTeamAwardId) {
        this.userTeamAwardId = userTeamAwardId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDefaultAward() {
        return defaultAward;
    }

    public void setDefaultAward(String defaultAward) {
        this.defaultAward = defaultAward;
    }

    public List<WareListBean> getWareList() {
        return wareList;
    }

    public void setWareList(List<WareListBean> wareList) {
        this.wareList = wareList;
    }

    public List<DeliveryAddressManageItemBean> getDeliveryAddressList() {
        return deliveryAddressList;
    }

    public void setDeliveryAddressList(List<DeliveryAddressManageItemBean> deliveryAddressList) {
        this.deliveryAddressList = deliveryAddressList;
    }

    public static class WareListBean {
        /**
         * shopId : 1101001001
         * name : 哇哈哈4支
         * picture : https://image.goukugogo.com/201910111055082707201e3dc
         * personNum : 1
         * onlinePrice : 0
         * onlineGroupPrice : 0
         * skuId : 11010010011
         * description : 1
         */

        private String shopId;
        private String name;
        private String picture;
        private String personNum;
        private String onlinePrice;
        private String onlineGroupPrice;
        private String skuId;
        private String description;

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
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

        public String getPersonNum() {
            return personNum;
        }

        public void setPersonNum(String personNum) {
            this.personNum = personNum;
        }

        public String getOnlinePrice() {
            return onlinePrice;
        }

        public void setOnlinePrice(String onlinePrice) {
            this.onlinePrice = onlinePrice;
        }

        public String getOnlineGroupPrice() {
            return onlineGroupPrice;
        }

        public void setOnlineGroupPrice(String onlineGroupPrice) {
            this.onlineGroupPrice = onlineGroupPrice;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
