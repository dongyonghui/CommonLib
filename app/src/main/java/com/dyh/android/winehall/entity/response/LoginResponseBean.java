package com.dyh.android.winehall.entity.response;


import android.text.TextUtils;

/**
 * 作者：Allan
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：登录响应结果
 */
public class LoginResponseBean extends UserInfoResponseBean {

    private String token;
    private String store_key;

    /**
     * 微信登录使用，微信登录接口成功后需要调用此方法检查是否登录成功
     *
     * @return true表示登录成功，直接进入登录状态；否则登录失败，需要绑定新手机号
     */
    public boolean isLoginSuccess() {
        String userId = getUser_id();
        return !TextUtils.isEmpty(userId) && !"0".equals(userId);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStore_key() {
        return store_key;
    }

    public void setStore_key(String store_key) {
        this.store_key = store_key;
    }
}
