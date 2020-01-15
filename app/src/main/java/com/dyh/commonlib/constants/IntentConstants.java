package com.dyh.commonlib.constants;

/**
 * intent 相关常量
 */
public class IntentConstants {
    public static class Flag {
        public static final int EDIT_MAP_ONLY = 1;//只编辑地图
        public static final int EDIT_SHOPS_ONLY = 2;//只编辑店铺
        public static final int REFUND_ORDER = 11;//订单退款
        public static final int REFUND_ORDER_PART = 12;//订单部分退款
        public static final int REFUND_ORDER_LIST = 13;//订单列表退款
    }

    public static final String KEY_SELECTED_DELIVER_ADDRESS_LIST_RESULT_JSON = "key_selected_deliver_address_list_result_json";//选择的配送地址列表json
    public static final String KEY_SELECTED_SHOP_LIST_RESULT_JSON = "key_selected_shop_list_result_json";//选择的店铺列表json
    public static final String KEY_ADD_TAKE_POINT_RESULT_JSON = "key_add_take_point_result_json";//添加取餐点结果数据json
    public static final String KEY_TPC_GOODS_ITEM_JSON = "key_tpc_goods_item_json";//头牌菜商品JSON数据
    public static final String KEY_DELIVERY_ADDRESS_ITEM_JSON = "key_delivery_address_item_json";//配送地址JSON数据
    public static final String KEY_CITY_TASK_ITEM_JSON = "key_city_task_item_json";//城市任务JSON数据
    public static final String KEY_DELIVERY_MAN_ITEM_JSON = "key_delivery_man_item_json";//骑手JSON数据
    public static final String KEY_BETTER_GOODS_TIME_ITEM_JSON = "key_better_goods_time_item_json";//优选菜展示时间JSON数据
    public static final String KEY_BANNER_ITEM_JSON = "key_banner_item_json";//BannerJSON数据
    public static final String KEY_ORDER_ITEM_JSON = "key_order_item_json";//订单列表项JSON数据
    public static final String KEY_REFUND_INFO = "key_refund_info";//退款信息
    public static final String KEY_NOTICE_ITEM_JSON = "key_notice_item_json";//消息公告JSON数据
    public static final String KEY_GROUP_PRIZE_ITEM_JSON = "key_group_prize_item_json";//团体奖励JSON数据
    public static final String KEY_SUPPLY_POOL_MANAGE_ITEM_JSON = "key_supply_pool_manage_item_json";//供给池JSON数据
    public static final String KEY_BD_DELIVERY_ADDRESS_LOCALBEAN_JSON = "key_bd_delivery_address_localbean_json";//配送地址管理列表数据
    public static final String KEY_MAP_LATLAN = "key_map_latlan";//地图位置信息
    public static final String KEY_FLAG_INT = "key_flag_int";//标签
    public static final String KEY_NEED_DELETE_BOOLEAN = "key_need_delete_boolean";//是否需要删除
}
