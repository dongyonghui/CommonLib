package com.dyh.commonlib.http;

/**
 * API路径常量类
 */
public class ApiPathConstants {

    public final static String APP_VERSION_PATH = HttpHostUtil.getShopRootUrl() + "/common/app-version";//检查更新
    public final static String LOGIN_PATH = HttpHostUtil.getPassportRootUrl() + "/login";//登录
    public final static String GET_ALL_OPERATOR = HttpHostUtil.getPassportRootUrl() + "/operator/message/get";//获取成员
    public final static String ORDER_LIST_PATH = HttpHostUtil.getTradeRootUrl() + "/order2d/takeout/groupOrder/user/list";//首页订单列表
    //    public final static String ORDER_LIST_PATH = HttpHostUtil.getTradeRootUrl() + "/order2d/takeout/groupOrder/list";//首页订单列表
    public final static String PICKUP_PATH = HttpHostUtil.getTradeRootUrl() + "/order2d/takeout/groupOrder/pickuped";//订单已取走
    public final static String CONFIRM_ARRIVED_PATH = HttpHostUtil.getTradeRootUrl() + "/order2d/takeout/order/arrived/notify";//订单确认送达
    public final static String ORDER2B_FINISH_PATH = HttpHostUtil.getTradeRootUrl() + "/order2d/takeout/order/finish";//确认完成
    public final static String ORDER_DETAIL2B_FINISH_PATH = HttpHostUtil.getTradeRootUrl() + "/order2d/finish";//确认完成(订单详情)
    //    public final static String ORDER_DETAIL_LIST_PATH = HttpHostUtil.getTradeRootUrl() + "/order2d/takeout/groupOrder/detailList";//订单详情列表
    public final static String ORDER_DETAIL_LIST_PATH = HttpHostUtil.getTradeRootUrl() + "/order2d/takeout/groupOrder/user/detailList";//订单详情列表
    public final static String ORDER2B_LIST_PATH = HttpHostUtil.getTradeRootUrl() + "/order2d/list";//搜索订单列表
    public final static String ORDER2B_MERGE_LIST_PATH = HttpHostUtil.getTradeRootUrl() + "/order2d/mergeOrder/data/list";//搜索订单列表(合并后的订单)
    public final static String MY_INFO_PATH = HttpHostUtil.getTradeRootUrl() + "/delivery/man/message";//我的消息
    public final static String UPDATE_MY_INFO_PATH = HttpHostUtil.getTradeRootUrl() + "/delivery/man/message/update";//更新我的消息
    public final static String UPLOAD_FILE_PATH = HttpHostUtil.getShopRootUrl() + "/common/upload";//上传文件


    public final static String GET_SHOP_LIST = HttpHostUtil.getShopRootUrl() + "/operating/shop/list";//获取店铺列表
    public final static String GET_DETAIL_CATEGORY = HttpHostUtil.getShopRootUrl() + "/operator/show/ware/detail/category";//获取详情分类
    public final static String GET_HOME_CATEGORY = HttpHostUtil.getShopRootUrl() + "/operator/shopWare/category/get";//获取主页分类
    public final static String SET_TAGS = HttpHostUtil.getShopRootUrl() + "/tags/ware/set";//设置标签
    public final static String GET_TPC_GOODS_LABELS = HttpHostUtil.getShopRootUrl() + "/tags/ware/show";//标签列表
    public final static String SET_CATEGORY_TPC_GOODS = HttpHostUtil.getShopRootUrl() + "/operator/shopWare/specialty/category/set";//设置头牌菜优选商品
    public final static String SET_FINER_TPC_GOODS = HttpHostUtil.getShopRootUrl() + "/operator/set/preference/category";//设置头牌菜优选商品
    public final static String UPDATE_TPC_GOODS = HttpHostUtil.getShopRootUrl() + "/operator/shopWare/specialty/update";//修改头牌菜商品
    public final static String UPDATE_SORT = HttpHostUtil.getShopRootUrl() + "/operator/auditing/specialty/updateSort";//修改权重
    public final static String ADD_PRICE = HttpHostUtil.getShopRootUrl() + "/operator/auditing/specialty/addPrice";//修改加价
    public final static String DELETE_TPC_GOODS = HttpHostUtil.getShopRootUrl() + "/operator/auditing/specialty/delete";//删除头牌菜商品
    public final static String SUBMIT_AUDITING = HttpHostUtil.getShopRootUrl() + "/operator/submit/auditing";//操作商品状态
    public final static String GET_TPC_LIST = HttpHostUtil.getShopRootUrl() + "/operator/get/auditing";//获取头牌菜列表
    public final static String GET_CITY_LIST = HttpHostUtil.getShopRootUrl() + "/operator/used/city";//获取城市列表
    public final static String GET_SUPPLY_POOL_LIST = HttpHostUtil.getTradeRootUrl() + "/delivery/supply/pool/get";//获取供给池列表
    public final static String GET_DELIVERY_ADDRESS_LIST = HttpHostUtil.getTradeRootUrl() + "/delivery/address/show/all";//获取配送地址列表
    public final static String GET_DELIVERY_ADDRESS_MANAGE_LIST = HttpHostUtil.getTradeRootUrl() + "/delivery/address/show";//获取配送地址列表
    public final static String GET_FINER_TIME_LIST = HttpHostUtil.getTradeRootUrl() + "/sendTime/preference/show";//获取优选菜时间列表
    public static final String GET_DELIVER_PRINTINFO_LIST = HttpHostUtil.getTradeRootUrl() + "/order2m/receipt/list";//订单小票（BD用户）
    public static final String DELETE_DELIVERY_ADDRESS = HttpHostUtil.getTradeRootUrl() + "/delivery/address/delete";//删除配送地址
    public static final String UPDATE_DELIVERY_ADDRESS = HttpHostUtil.getTradeRootUrl() + "/delivery/address/status/update";//开启或关闭配送地址
    public static final String ADD_DELIVERY_ADDRESS = HttpHostUtil.getTradeRootUrl() + "/delivery/address/set";//新建配送地址
    public static final String EDIT_DELIVERY_ADDRESS = HttpHostUtil.getTradeRootUrl() + "/delivery/address/update";//修改配送地址
    public static final String ADD_CITY_TASK = HttpHostUtil.getTradeRootUrl() + "/operator/shop/orderTask/add";//新建城市任务
    public static final String EDIT_CITY_TASK = HttpHostUtil.getTradeRootUrl() + "/operator/shop/orderTask/update";//修改城市任务
    public static final String GET_SUPPLY_POOL_MANAGE_LIST = HttpHostUtil.getTradeRootUrl() + "/delivery/supply/pool/show";//获取供给池列表
    public static final String DELETE_SUPPLY_POOL = HttpHostUtil.getTradeRootUrl() + "/delivery/supply/pool/delete";//删除供给池
    public static final String EDIT_SUPPLY_POOL = HttpHostUtil.getTradeRootUrl() + "/delivery/supply/pool/update";//编辑供给池
    public static final String ADD_SUPPLY_POOL = HttpHostUtil.getTradeRootUrl() + "/delivery/supply/pool/set";//添加供给池
    public static final String GET_CITY_TASK_LIST = HttpHostUtil.getTradeRootUrl() + "/operator/shop/orderTask/show";//获取城市任务列表
    public static final String DELETE_CITY_TASK = HttpHostUtil.getTradeRootUrl() + "/operator/shop/orderTask/delete";//删除城市任务
    public static final String GET_BANNER_LIST = HttpHostUtil.getTradeRootUrl() + "/city/banner/get";//获取banner
    public static final String DELETE_BANNER = HttpHostUtil.getTradeRootUrl() + "/city/banner/delete";//删除banner
    public static final String ADD_BANNER = HttpHostUtil.getTradeRootUrl() + "/city/banner/add";//新建banner
    public static final String EDIT_BANNER = HttpHostUtil.getTradeRootUrl() + "/city/banner/update";//编辑banner
    public static final String GET_NOTICE_LIST = HttpHostUtil.getTradeRootUrl() + "/operator/deliveryNotice/show";//获取消息公告列表
    public static final String DELETE_NOTICE = HttpHostUtil.getTradeRootUrl() + "/operator/deliveryNotice/delete";//删除消息公告
    public static final String ADD_NOTICE = HttpHostUtil.getTradeRootUrl() + "/operator/deliveryNotice/add";//新建消息公告
    public static final String EDIT_NOTICE = HttpHostUtil.getTradeRootUrl() + "/operator/deliveryNotice/update";//编辑消息公告
    public static final String GET_GROUP_PRIZE_LIST = HttpHostUtil.getTradeRootUrl() + "/award/team/show";//获取团体奖励列表
    public static final String DELETE_GROUP_PRIZE = HttpHostUtil.getTradeRootUrl() + "/award/team/delete";//删除团体奖励
    public static final String ADD_GROUP_PRIZE = HttpHostUtil.getTradeRootUrl() + "/award/team/create";//新建团体奖励
    public static final String EDIT_GROUP_PRIZE = HttpHostUtil.getTradeRootUrl() + "/award/team/update";//编辑团体奖励
    public static final String GET_CITY_DELIVERY_PRICE = HttpHostUtil.getTradeRootUrl() + "/city/deliveryFee/get";//获取城市配送费
    public static final String SUBMIT_CITY_DELIVERY_PRICE = HttpHostUtil.getTradeRootUrl() + "/city/deliveryFee/create";//提交城市配送费
    public static final String GET_BETTER_GOODS_TIME_LIST = HttpHostUtil.getTradeRootUrl() + "/sendTime/preference/show";//获取优选菜时间列表
    public static final String DELETE_BETTER_GOODS_TIME = HttpHostUtil.getTradeRootUrl() + "/sendTime/preference/delete";//删除优选菜时间
    public static final String ADD_BETTER_GOODS_TIME = HttpHostUtil.getTradeRootUrl() + "/sendTime/preference/add";//新增优选菜时间
    public static final String EDIT_BETTER_GOODS_TIME = HttpHostUtil.getTradeRootUrl() + "/sendTime/preference/update";//编辑优选菜时间
    public static final String GET_SECKILL_TIME_LIST = HttpHostUtil.getTradeRootUrl() + "/city/seckill/time/show";//获取秒杀时间列表
    public static final String DELETE_SECKILL_TIME = HttpHostUtil.getTradeRootUrl() + "/city/seckill/time/delete";//秒杀时间列表
    public static final String ADD_SECKILL_TIME = HttpHostUtil.getTradeRootUrl() + "/city/seckill/time/add";//秒杀时间列表
    public static final String EDIT_SECKILL_TIME = HttpHostUtil.getTradeRootUrl() + "/city/seckill/time/update";//秒杀时间列表
    public static final String GET_CITY_TIME_LIST = HttpHostUtil.getTradeRootUrl() + "/sendTime/time/show";//获取城市时间列表
    public static final String DELETE_CITY_TIME = HttpHostUtil.getTradeRootUrl() + "/sendTime/time/delete";//城市时间列表
    public static final String ADD_CITY_TIME = HttpHostUtil.getTradeRootUrl() + "/sendTime/time/add";//城市时间列表
    public static final String EDIT_CITY_TIME = HttpHostUtil.getTradeRootUrl() + "/sendTime/time/update";//城市时间列表
    public static final String GET_DELIVERY_MAN_LIST = HttpHostUtil.getTradeRootUrl() + "/delivery/man/show";//获取骑手列表
    public static final String DELETE_DELIVERY_MAN = HttpHostUtil.getTradeRootUrl() + "/delivery/man/delete";//骑手列表
    public static final String CHANGE_DELIVERY_MAN = HttpHostUtil.getTradeRootUrl() + "/delivery/man/change";//改派骑手
    public static final String ADD_DELIVERY_MAN = HttpHostUtil.getTradeRootUrl() + "/delivery/man/add";//骑手列表
    public static final String EDIT_DELIVERY_MAN = HttpHostUtil.getTradeRootUrl() + "/delivery/man/update";//骑手列表
    public static final String GET_ORDER_LIST = HttpHostUtil.getTradeRootUrl() + "/order2m/list";//订单列表
    public static final String REFUND_ORDER = HttpHostUtil.getTradeRootUrl() + "/order2m/refund/apply";//订单退款
    public static final String REFUND_ORDER_PART = HttpHostUtil.getTradeRootUrl() + "/order2m/refund/alone/apply";//订单部分退款
    public static final String GET_STATISTICS = HttpHostUtil.getTradeRootUrl() + "/stat/all";//获取统计数据
    public static final String GET_DELIVERYAREA = HttpHostUtil.getTradeRootUrl() + "/deliveryArea/list";//获取配送范围

}
