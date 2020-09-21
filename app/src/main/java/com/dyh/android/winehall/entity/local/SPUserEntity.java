package com.dyh.android.winehall.entity.local;

import com.dyh.android.winehall.entity.response.UserInfoResponseBean;
import com.dyh.common.lib.easy.PreferenceRename;
import com.dyh.common.lib.easy.PreferenceSupport;

/**
 * 作者：Allan
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：用户信息
 */
@PreferenceRename("inscloudtech_user")
public class SPUserEntity extends PreferenceSupport {
    public boolean isHaveAlreadyShowWelcomeInfo;//是否已经展示过欢迎页面，true说明展示过了
    public UserInfoResponseBean userInfo;//个人信息
    public boolean isHaveNewVersion;//是否有新版本
}
