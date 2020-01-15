package com.dyh.commonlib.ui.view;

import com.dyh.commonlib.entity.response.LoginResponseBean;
import com.dyh.common.lib.mvp.MVPView;

/**
 * 作者：DongYonghui
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：登录认证页面
 */
public interface ILoginView extends MVPView {
    /**
     * 登录认证返回结果
     *
     * @param responseBean
     */
    void onLoginSuccess(LoginResponseBean responseBean);
}
