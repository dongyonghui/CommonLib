package com.dyh.android.winehall.entity.local;

import com.dyh.android.winehall.entity.response.BannerItemResponseBean;
import com.dyh.android.winehall.entity.response.CategoryItemResponseBean;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/5 0005 20:27
 * description: 首页缓存信息
 */
public class CacheHomeDataBean {
    //分类信息
    public List<CategoryItemResponseBean> categoryItemResponseBeanList;
    //Banner信息
    public List<BannerItemResponseBean> bannerItemResponseBeanList;
}
