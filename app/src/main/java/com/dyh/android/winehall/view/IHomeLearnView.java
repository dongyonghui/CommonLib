package com.dyh.android.winehall.view;

import com.dyh.android.winehall.entity.response.BannerItemResponseBean;
import com.dyh.android.winehall.entity.response.CategoryItemResponseBean;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/4 0004 17:11
 * description:
 */
public interface IHomeLearnView extends IBasePageView {
    /**
     * 展示分类UI列表
     *
     * @param list
     */
    void showCategoryList(List<CategoryItemResponseBean> list);

    /**
     * 展示Banner列表
     *
     * @param list
     */
    void showBannerList(List<BannerItemResponseBean> list);
}
