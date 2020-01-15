package com.dyh.commonlib.entity.response;

import com.dyh.commonlib.constants.ServerConstants;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/2/002 16:25
 * 描述：配送地址管理列表项信息
 */
public class DeliveryAddressManageItemBean {


    /**
     * id : 136
     * provinceId : 620123
     * province : 甘肃省
     * cityId : 620100
     * city : 兰州市
     * districtId : 620123
     * district : 榆中县
     * address : 甘肃省兰州市榆中县城关镇栖云南路12号
     * title : 榆中县兴隆商业街名爵酒吧
     * lat : 35.846529
     * lon : 104.117671
     * location : {"lat":35.846529,"lon":104.117671}
     * shops : []
     * deliveryManId : null
     * distance : null
     * remark : 榆中县兴隆商业街名爵酒吧
     * deliveryAddressId : 136
     * deliveryMealAddressId : null
     * mealAddresses : null
     * failMealAddresses : null
     * deliveryMen : [{"id":78,"username":null,"name":"金木歌","phone":"13585908235","userName":"JD1066","createAt":null,"status":null,"deliveryTotal":null,"groupOrderNum":null,"deliveryOrderNum":null,"deliveryAddressList":null,"idCardForward":null,"idCardBack":null,"healthCard":null,"idCardNum":null}]
     * deliveryStatus : 1
     * useType : 3
     * beginFloor : null
     * endFloor : null
     * companies : null
     * type : 1
     * deliveryFee : 1.0
     * firstDeliveryFee : 1.0
     * freeMoney : 1.0
     * createdCanal : 1
     * openTime : null
     * useStatus : 1
     * personPayMoney : 0.0
     * openid : null
     * offDutyTime : 21:00
     * explain : null
     */

    private String id;
    private String provinceId;
    private String province;
    private String cityId;
    private String city;
    private String districtId;
    private String district;
    private String address;
    private String title;
    private String lat;
    private String lon;
    private LocationBean location;
    private String deliveryManId;
    private String distance;
    private String remark;
    private String deliveryAddressId;
    private String deliveryMealAddressId;
    private List<MealAddressBean> mealAddresses;
    private List<MealAddressBean> failMealAddresses;
    private String deliveryStatus;
    private String useType;
    private String beginFloor;
    private String endFloor;
    private String companies;
    private String type;
    private String deliveryFee;
    private String firstDeliveryFee;
    private String freeMoney;
    private String createdCanal;
    private String openTime;
    private String useStatus;
    private String personPayMoney;
    private String openid;
    private String offDutyTime;
    private String explain;
    private List<DeliveryMenBean> deliveryMen;
    private List<ShopItemBean> shops;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryAddressManageItemBean that = (DeliveryAddressManageItemBean) o;

        return deliveryAddressId != null ? deliveryAddressId.equals(that.deliveryAddressId) : that.deliveryAddressId == null;
    }

    @Override
    public int hashCode() {
        return deliveryAddressId != null ? deliveryAddressId.hashCode() : 0;
    }

    /**
     * @return 返回取绑定骑手集合信息
     */
    public String getDeliverysInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (null != deliveryMen) {
            for (DeliveryMenBean item : deliveryMen) {
                stringBuilder.append(item.getName()).append(":").append(item.getPhone()).append("; ");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * @return 返回取周边店铺信息
     */
    public String getShopsInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (null != shops) {
            for (ShopItemBean item : shops) {
                stringBuilder.append(item.getName()).append("; ");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * @return 返回取餐点地址名称集合信息
     */
    public String getTakePointsNameInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (ServerConstants.DeliveryAddressUseType.TAKE_SELF.equals(useType) && null != mealAddresses) {
            for (MealAddressBean mealAddress : mealAddresses) {
                stringBuilder.append(mealAddress.mealAddress).append("; ");
            }
        } else if (ServerConstants.DeliveryAddressUseType.SEND2HOME.equals(useType) && null != failMealAddresses) {
            for (MealAddressBean failMealAddress : failMealAddresses) {
                stringBuilder.append(failMealAddress.mealAddress).append("; ");
            }
        }
        return stringBuilder.toString();
    }

    public List<ShopItemBean> getShops() {
        return shops;
    }

    public void setShops(List<ShopItemBean> shops) {
        this.shops = shops;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocationBean getLocation() {
        return location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public String getDeliveryManId() {
        return deliveryManId;
    }

    public void setDeliveryManId(String deliveryManId) {
        this.deliveryManId = deliveryManId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryMealAddressId() {
        return deliveryMealAddressId;
    }

    public void setDeliveryMealAddressId(String deliveryMealAddressId) {
        this.deliveryMealAddressId = deliveryMealAddressId;
    }

    public List<MealAddressBean> getMealAddresses() {
        return mealAddresses;
    }

    public void setMealAddresses(List<MealAddressBean> mealAddresses) {
        this.mealAddresses = mealAddresses;
    }

    public List<MealAddressBean> getFailMealAddresses() {
        return failMealAddresses;
    }

    public void setFailMealAddresses(List<MealAddressBean> failMealAddresses) {
        this.failMealAddresses = failMealAddresses;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getBeginFloor() {
        return beginFloor;
    }

    public void setBeginFloor(String beginFloor) {
        this.beginFloor = beginFloor;
    }

    public String getEndFloor() {
        return endFloor;
    }

    public void setEndFloor(String endFloor) {
        this.endFloor = endFloor;
    }

    public String getCompanies() {
        return companies;
    }

    public void setCompanies(String companies) {
        this.companies = companies;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getFirstDeliveryFee() {
        return firstDeliveryFee;
    }

    public void setFirstDeliveryFee(String firstDeliveryFee) {
        this.firstDeliveryFee = firstDeliveryFee;
    }

    public String getFreeMoney() {
        return freeMoney;
    }

    public void setFreeMoney(String freeMoney) {
        this.freeMoney = freeMoney;
    }

    public String getCreatedCanal() {
        return createdCanal;
    }

    public void setCreatedCanal(String createdCanal) {
        this.createdCanal = createdCanal;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getPersonPayMoney() {
        return personPayMoney;
    }

    public void setPersonPayMoney(String personPayMoney) {
        this.personPayMoney = personPayMoney;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOffDutyTime() {
        return offDutyTime;
    }

    public void setOffDutyTime(String offDutyTime) {
        this.offDutyTime = offDutyTime;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public List<DeliveryMenBean> getDeliveryMen() {
        return deliveryMen;
    }

    public void setDeliveryMen(List<DeliveryMenBean> deliveryMen) {
        this.deliveryMen = deliveryMen;
    }

    public static class LocationBean {
        /**
         * lat : 35.846529
         * lon : 104.117671
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

    public static class MealAddressBean {
        public String id;
        public String lat;
        public String lon;
        public String mealAddress;
    }

    public static class DeliveryMenBean {
        /**
         * id : 78
         * username : null
         * name : 金木歌
         * phone : 13585908235
         * userName : JD1066
         * createAt : null
         * status : null
         * deliveryTotal : null
         * groupOrderNum : null
         * deliveryOrderNum : null
         * deliveryAddressList : null
         * idCardForward : null
         * idCardBack : null
         * healthCard : null
         * idCardNum : null
         */

        private String id;
        private String username;
        private String name;
        private String phone;
        private String userName;
        private String createAt;
        private String status;
        private String deliveryTotal;
        private String groupOrderNum;
        private String deliveryOrderNum;
        private String deliveryAddressList;
        private String idCardForward;
        private String idCardBack;
        private String healthCard;
        private String idCardNum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDeliveryTotal() {
            return deliveryTotal;
        }

        public void setDeliveryTotal(String deliveryTotal) {
            this.deliveryTotal = deliveryTotal;
        }

        public String getGroupOrderNum() {
            return groupOrderNum;
        }

        public void setGroupOrderNum(String groupOrderNum) {
            this.groupOrderNum = groupOrderNum;
        }

        public String getDeliveryOrderNum() {
            return deliveryOrderNum;
        }

        public void setDeliveryOrderNum(String deliveryOrderNum) {
            this.deliveryOrderNum = deliveryOrderNum;
        }

        public String getDeliveryAddressList() {
            return deliveryAddressList;
        }

        public void setDeliveryAddressList(String deliveryAddressList) {
            this.deliveryAddressList = deliveryAddressList;
        }

        public String getIdCardForward() {
            return idCardForward;
        }

        public void setIdCardForward(String idCardForward) {
            this.idCardForward = idCardForward;
        }

        public String getIdCardBack() {
            return idCardBack;
        }

        public void setIdCardBack(String idCardBack) {
            this.idCardBack = idCardBack;
        }

        public String getHealthCard() {
            return healthCard;
        }

        public void setHealthCard(String healthCard) {
            this.healthCard = healthCard;
        }

        public String getIdCardNum() {
            return idCardNum;
        }

        public void setIdCardNum(String idCardNum) {
            this.idCardNum = idCardNum;
        }
    }
}
