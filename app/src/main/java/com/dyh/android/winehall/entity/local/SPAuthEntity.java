package com.dyh.android.winehall.entity.local;

import com.dyh.common.lib.easy.PreferenceRename;
import com.dyh.common.lib.easy.PreferenceSupport;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/4 0004 11:19
 * description:
 */
@PreferenceRename("inscloudtech_auth")
public class SPAuthEntity extends PreferenceSupport {
    public String topsession;//登录返回的token
}
