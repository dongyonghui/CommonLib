package com.dyh.commonlib.entity.local;

import com.dyh.commonlib.entity.response.LoginResponseBean;
import com.dyh.common.lib.easy.PreferenceRename;
import com.dyh.common.lib.easy.PreferenceSupport;

/**
 * 作者：DongYonghui
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：SharedPreferences 保存的实体转换
 */
@PreferenceRename("gouku_toolkit")
public class SPEntity extends PreferenceSupport {
    public LoginResponseBean loginResponseBean;
    public String loginName;
}
