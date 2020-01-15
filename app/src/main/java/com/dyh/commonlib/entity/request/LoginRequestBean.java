package com.dyh.commonlib.entity.request;

import com.dyh.commonlib.entity.BaseHttpRequestBean;

/**
 * 作者：DongYonghui
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：登录认证
 */
public class LoginRequestBean extends BaseHttpRequestBean {
    public String account;
    public String password;
    public int authType = 1;
}
