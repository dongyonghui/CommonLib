package com.dyh.commonlib.entity.response;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/11/011 14:12
 * 描述：城市配送费
 */
public class CityDeliverPriceBean {

    /**
     * firstDeliveryFee : 5
     * deliveryFee : 2
     * freeFee : 8
     */

    private String firstDeliveryFee;
    private String deliveryFee;
    private String freeFee;

    public String getFirstDeliveryFee() {
        return firstDeliveryFee;
    }

    public void setFirstDeliveryFee(String firstDeliveryFee) {
        this.firstDeliveryFee = firstDeliveryFee;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getFreeFee() {
        return freeFee;
    }

    public void setFreeFee(String freeFee) {
        this.freeFee = freeFee;
    }
}
