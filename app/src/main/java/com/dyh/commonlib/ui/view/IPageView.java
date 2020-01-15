package com.dyh.commonlib.ui.view;

import com.dyh.common.lib.mvp.MVPView;

import java.util.List;

/**
 * 分页
 */
public interface IPageView<T> extends MVPView {
    /**
     * 展示列表
     *
     * @param list
     */
    void showList(List<T> list);

    /**
     * 追加列表
     *
     * @param list
     */
    void appendList(List<T> list);
}
