package com.dyh.commonlib.entity.local;

/**
 * 消息类型常量类
 */
public final class MessageCodeConstants {
    public static final int NOTIFY_HOME_ORDERLIST = 0x100;//更新首页订单列表

    public static final int HTTP_PICKUP = NOTIFY_HOME_ORDERLIST + 1;//网络请求——已取走
    public static final int HTTP_CONFIRM_ARRIVED = HTTP_PICKUP + 1;//网络请求——确认送达并通知用户
    public static final int HTTP_CONFIRM_ALL_ARRIVED = HTTP_CONFIRM_ARRIVED + 1;//网络请求——全部确认送达并通知用户
    public static final int HTTP_GET_ORDER_DETAIL_LIST_ARRIVED = HTTP_CONFIRM_ALL_ARRIVED + 1;//网络请求——展示订单详情列表
    public static final int HTTP_ORDER2B_FINISH = HTTP_GET_ORDER_DETAIL_LIST_ARRIVED + 1;//网络请求——确认完成

}