package com.dyh.android.winehall.entity.local;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/17 0017
 * description: 发送的消息
 */
public class EventMessageBean {
    public static final int CODE_LOGIN_SUCCESS = 100;                   //登录成功
    public static final int CODE_COLLECT_STATUS_CHANGED = 110;          //收藏状态改变
    public int code;

    public EventMessageBean(int code) {
        this.code = code;
    }
}
