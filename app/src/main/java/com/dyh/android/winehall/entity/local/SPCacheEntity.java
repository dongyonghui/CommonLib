package com.dyh.android.winehall.entity.local;

import com.dyh.common.lib.easy.PreferenceRename;
import com.dyh.common.lib.easy.PreferenceSupport;

import org.jetbrains.annotations.NotNull;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/5 0005 20:25
 * description: 本地缓存信息
 */
@PreferenceRename("inscloudtech_cache")
public class SPCacheEntity extends PreferenceSupport {
    //首页缓存信息
    public CacheHomeDataBean cacheHomeDataBean;

    @NotNull
    public CacheHomeDataBean getCacheHomeDataBean() {
        if (null == cacheHomeDataBean)
            cacheHomeDataBean = new CacheHomeDataBean();

        return cacheHomeDataBean;
    }
}
