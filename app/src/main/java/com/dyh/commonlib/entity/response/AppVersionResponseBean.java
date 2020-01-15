package com.dyh.commonlib.entity.response;

import com.dyh.commonlib.entity.BaseHttpResponseBean;

/**
 * 作者：DongYonghui
 * 时间：2019/11/8/008
 * 邮箱：648731994@qq.com
 * 描述：版本更新信息
 */
public class AppVersionResponseBean extends BaseHttpResponseBean {
    public String version;
    public String downloadUrl;
    public String forced;
    public String hint;
    public String versionMinimum;
    public String versionName;
    public String appSize;
    public String content;
}
