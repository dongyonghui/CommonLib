package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;
import com.dyh.commonlib.entity.response.GroupPrizeItemBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/26/026 10:44
 * 描述：新建或编辑团体奖励
 */
public class AddOrEditGroupPrizeRequestBean extends BaseHttpRequestBean {

    /**
     * wareList : [{"name":"哇哈哈4支","personNum":1,"description":"1","picture":"https://image.goukugogo.com/201910111055082707201e3dc"},{"name":"果盘1份","personNum":2,"description":"2","picture":"https://image.goukugogo.com/20191011105620285b8fe20fd"},{"name":"炸鸡拼盘1盘","personNum":3,"description":"31","picture":"https://image.goukugogo.com/20191011105611836100bbb36"}]
     * defaultAward : 1
     * deliveryAddressList : [{"deliveryAddressId":135,"deliveryAddressName":"圣熙8号购物中心售票处"},{"deliveryAddressId":134,"deliveryAddressName":"768创意产业园B座"}]
     * userTeamAwardId : 2
     */

    private String defaultAward;
    private String userTeamAwardId;
    private List<GroupPrizeItemBean.WareListBean> wareList;
    private List<DeliveryAddressListBean> deliveryAddressList;

    public String getDefaultAward() {
        return defaultAward;
    }

    public void setDefaultAward(String defaultAward) {
        this.defaultAward = defaultAward;
    }

    public String getUserTeamAwardId() {
        return userTeamAwardId;
    }

    public void setUserTeamAwardId(String userTeamAwardId) {
        this.userTeamAwardId = userTeamAwardId;
    }

    public List<GroupPrizeItemBean.WareListBean> getWareList() {
        return wareList;
    }

    public void setWareList(List<GroupPrizeItemBean.WareListBean> wareList) {
        this.wareList = wareList;
    }

    public List<DeliveryAddressListBean> getDeliveryAddressList() {
        return deliveryAddressList;
    }

    public void setDeliveryAddressList(List<DeliveryAddressListBean> deliveryAddressList) {
        this.deliveryAddressList = deliveryAddressList;
    }

    public static class DeliveryAddressListBean {
        /**
         * deliveryAddressId : 135
         * deliveryAddressName : 圣熙8号购物中心售票处
         */

        private String deliveryAddressId;
        private String deliveryAddressName;

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
    }
}
