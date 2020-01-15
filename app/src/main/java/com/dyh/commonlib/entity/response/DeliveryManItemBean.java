package com.dyh.commonlib.entity.response;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/11/011 17:41
 * 描述：骑手
 */
public class DeliveryManItemBean {

    /**
     * id : 73
     * userName : qs1234
     * avatar : null
     * name : qs1234
     * phone : 18895322912
     * status : 1
     * deliveryTotal : 66
     * groupOrderNum : null
     * deliveryOrderNum : null
     * deliveryAddressList : [{"id":105,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":"北京市海淀区紫竹院街道中国青年政治学院电视节目制作实验室新闻传播实验教学中心中国青年政治学院","title":null,"lat":null,"lon":null,"location":null,"shops":[{"shopId":110010000000006,"name":"永慧超市","lat":null,"lon":null},{"shopId":1100118,"name":"脆脆的店","lat":null,"lon":null}],"deliveryManId":null,"distance":null,"remark":"测试地址C","deliveryAddressId":105,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":2,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null},{"id":134,"provinceId":null,"province":null,"cityId":null,"city":null,"districtId":null,"district":null,"address":"北京市海淀区学院路街道林业大学北路9号院中关村768创意产业园","title":null,"lat":null,"lon":null,"location":null,"shops":[],"deliveryManId":null,"distance":null,"remark":"768创意产业园B座","deliveryAddressId":134,"deliveryMealAddressId":null,"mealAddresses":null,"failMealAddresses":null,"deliveryMen":null,"deliveryStatus":null,"useType":2,"beginFloor":null,"endFloor":null,"companies":null,"type":1,"deliveryFee":null,"firstDeliveryFee":null,"freeMoney":null,"createdCanal":0,"openTime":null,"useStatus":null,"personPayMoney":null,"openid":null,"offDutyTime":null,"explain":null}]
     * idCardForward : null
     * idCardBack : null
     * healthCard : null
     * idCardNum : null
     */

    private String id;
    private String userName;
    private String name;
    private String phone;
    private String status;
    private String deliveryTotal;
    private List<DeliveryAddressManageItemBean> deliveryAddressList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


    public List<DeliveryAddressManageItemBean> getDeliveryAddressList() {
        return deliveryAddressList;
    }

    public void setDeliveryAddressList(List<DeliveryAddressManageItemBean> deliveryAddressList) {
        this.deliveryAddressList = deliveryAddressList;
    }

}
