package com.dyh.commonlib.entity.response;

import com.dyh.commonlib.entity.BaseHttpResponseBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/18/018 16:51
 * 描述：统计返回数据
 */
public class StatisticsResponseBean extends BaseHttpResponseBean {

    /**
     * orderCount : 0
     * newUserCount : 0
     * payUserCount : 0
     * newPayUserCount : 0
     * skuSaleList : []
     * orderTask : {"id":null,"orderTaskId":null,"provinceId":null,"province":null,"cityId":110100,"city":null,"districtId":null,"district":null,"targetOrderCount":100,"targetTradePrice":100,"status":null,"currentOrderCount":26,"currentTradePrice":18.29,"monthDate":"2019-12","operatorId":null,"operatorName":null,"orderCountRate":"26%","orderPriceRate":"18%"}
     */

    private String orderCount;
    private String newUserCount;
    private String payUserCount;
    private String newPayUserCount;
    private OrderTaskBean orderTask;
    private List<GoodsItemBean> skuSaleList;

    public List<GoodsItemBean> getSkuSaleList() {
        return skuSaleList;
    }

    public void setSkuSaleList(List<GoodsItemBean> skuSaleList) {
        this.skuSaleList = skuSaleList;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getNewUserCount() {
        return newUserCount;
    }

    public void setNewUserCount(String newUserCount) {
        this.newUserCount = newUserCount;
    }

    public String getPayUserCount() {
        return payUserCount;
    }

    public void setPayUserCount(String payUserCount) {
        this.payUserCount = payUserCount;
    }

    public String getNewPayUserCount() {
        return newPayUserCount;
    }

    public void setNewPayUserCount(String newPayUserCount) {
        this.newPayUserCount = newPayUserCount;
    }

    public OrderTaskBean getOrderTask() {
        return orderTask;
    }

    public void setOrderTask(OrderTaskBean orderTask) {
        this.orderTask = orderTask;
    }

    public static class GoodsItemBean{
        public String count;
        public String skuName;
        public String price;
        public String shopName;
    }

    public static class OrderTaskBean {
        /**
         * id : null
         * orderTaskId : null
         * provinceId : null
         * province : null
         * cityId : 110100
         * city : null
         * districtId : null
         * district : null
         * targetOrderCount : 100
         * targetTradePrice : 100.0
         * status : null
         * currentOrderCount : 26
         * currentTradePrice : 18.29
         * monthDate : 2019-12
         * operatorId : null
         * operatorName : null
         * orderCountRate : 26%
         * orderPriceRate : 18%
         */

        private String cityId;
        private String targetOrderCount;
        private String targetTradePrice;
        private String currentOrderCount;
        private String currentTradePrice;
        private String monthDate;
        private String orderCountRate;
        private String orderPriceRate;

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getTargetOrderCount() {
            return targetOrderCount;
        }

        public void setTargetOrderCount(String targetOrderCount) {
            this.targetOrderCount = targetOrderCount;
        }

        public String getTargetTradePrice() {
            return targetTradePrice;
        }

        public void setTargetTradePrice(String targetTradePrice) {
            this.targetTradePrice = targetTradePrice;
        }

        public String getCurrentOrderCount() {
            return currentOrderCount;
        }

        public void setCurrentOrderCount(String currentOrderCount) {
            this.currentOrderCount = currentOrderCount;
        }

        public String getCurrentTradePrice() {
            return currentTradePrice;
        }

        public void setCurrentTradePrice(String currentTradePrice) {
            this.currentTradePrice = currentTradePrice;
        }

        public String getMonthDate() {
            return monthDate;
        }

        public void setMonthDate(String monthDate) {
            this.monthDate = monthDate;
        }

        public String getOrderCountRate() {
            return orderCountRate;
        }

        public void setOrderCountRate(String orderCountRate) {
            this.orderCountRate = orderCountRate;
        }

        public String getOrderPriceRate() {
            return orderPriceRate;
        }

        public void setOrderPriceRate(String orderPriceRate) {
            this.orderPriceRate = orderPriceRate;
        }
    }
}
