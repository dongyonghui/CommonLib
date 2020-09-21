package com.dyh.android.winehall.view;

import com.dyh.common.lib.mvp.MVPView;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/4/23 0023 12:02
 * description: 专题详情--课程附件
 */
public interface IBasePageView<T> extends MVPView {

    /**
     * 刷新列表成功
     *
     * @param list
     * @param isLastPage true标识是最后一页
     */
    void onRefreshPageSuccess(List<T> list, boolean isLastPage);

    /**
     * 加载更多成功
     *
     * @param list
     * @param isLastPage true标识是最后一页
     */
    void onLoadMorePageSuccess(List<T> list, boolean isLastPage);

}
