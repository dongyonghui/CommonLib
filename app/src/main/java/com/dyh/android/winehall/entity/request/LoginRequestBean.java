package com.dyh.android.winehall.entity.request;


import com.dyh.android.winehall.entity.BaseHttpRequestBean;

/**
 * 作者：Allan
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：登录认证
 */
public class LoginRequestBean extends BaseHttpRequestBean {
    public String mobile;
    public String code;
    public String store_key;//用于移动端注册，来源见/user/wxsns-login接口
}
