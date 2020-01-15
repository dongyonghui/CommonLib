package com.dyh.commonlib.constants;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/27/027 10:52
 * 描述：服务器常量
 */
public class ServerConstants {

    public static final String DELIVERY_ADDRESS_USE_STATUS_USED = "1";//配送地址状态——启用
    public static final String DEFAULT_INT_TRUE = "1";//是
    public static final String DEFAULT_INT_FALSE = "0";//否
    public static final String TRUE_STRING = "true";//布尔值true

    public static final class ErrorCode {
        public static final int NEED_CHANGE_DELIVERY_MAN_FIRST = 5001;//需要先改派骑手再注销
    }

    public static class MenuWebPath {
        public static final String TPC_MANAGE = "page/goods/goodsManage.html";//头牌菜管理
        public static final String ADDRESS_MANAGE = "page/address/addressManage.html";//配送地址管理
        public static final String SUPPLY_POOL_MANAGE = "page/supplyPool/supplyPoolManage.html";//供给池管理
        public static final String DELIVERY_MAN_MANAGE = "page/delivery/deliveryMenManage.html";//骑手管理
        public static final String CITY_MANAGE = "page/cityManage";//城市管理
        public static final String CITY_TASK = "page/cityManage/cityTask/cityTask.html";//城市管理——任务管理
        public static final String CITY_BANNER = "page/cityManage/cityBanner/cityBanner.html";//城市管理——banner管理
        public static final String CITY_DELIVERY = "page/cityManage/cityDelivery/cityDelivery.html";//城市管理——配送费用管理
        public static final String CITY_GOOD_TIME = "page/cityManage/cityGoodTime/cityGoodTime.html";//城市管理——优选菜时间管理
        public static final String CITY_NOTICE = "page/cityManage/cityNotice/cityNotice.html";//城市管理——消息公告管理
        public static final String CITY_PRIZE = "page/cityManage/cityPrize/cityPrize.html";//城市管理——团体奖励管理
        public static final String CITY_TIME = "page/cityManage/cityTime/cityTime.html";//城市管理——城市时间管理
        public static final String LIMITED_TIME_DISCOUNT = "page/cityManage/limitedTimeDiscount/limitedTimeDiscount.html";//城市管理——秒杀时间管理
        public static final String ORDER_MANAGE = "page/orderManage/orderManage.html";//订单管理
    }

    /**
     * 退款状态
     */
    public static class RefundStatus {
        /**
         * 是否已退款
         *
         * @param refundStatus
         * @return true表示已退款，false表示还未退款
         */
        public static boolean isRefundedAlready(int refundStatus) {
            if (refundStatus != 0 && refundStatus < 8) {
                return true;
            }
            return false;
        }
    }

    /**
     * 退款责任方
     */
    public static class RefundWay {
        public static final String TPC = "1";//头牌菜
        public static final String SHOP = "2";//商家
        public static final String DELIVERY_MAN = "3";//骑手

    }

    /**
     * 消息公告状态
     */
    public static class NoticeStatus {
        public static final String UNKNOWN = "0";//未知
        public static final String NOT_START = "1";//未开始
        public static final String DOING = "2";//进行中
        public static final String PASSED = "3";//已过期

    }

    /**
     * 配送地址配送类型
     */
    public static class DeliveryAddressUseType {
        public static final String TAKE_SELF = "1";//用户自取
        public static final String SEND2HOME = "2";//送餐上楼
    }

    public static class WareType {
        public static final String FIRST_DISCOUNT = "2";//首单特惠
        public static final String LIMITED_TIME_DISCOUNT = "3";//限时秒杀
    }

    /**
     * 菜品类型
     */
    public static class AUDITING_TYPE {
        public static final String FIRST_DISCOUNT = "5";//首单特惠
        public static final String LIMITED_TIME_DISCOUNT = "7";//限时秒杀
    }

    /**
     * banner点击跳转类型
     */
    public static class BANNER_TYPE {
        public static final String IN_APP_PAGES = "1";//页面内跳转
        public static final String WEB_URL = "2";//外链
    }

}
