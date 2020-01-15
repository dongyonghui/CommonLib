package com.dyh.commonlib.ui.view;

import com.dyh.common.lib.mvp.MVPView;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/3/003 9:49
 * 描述：管理列表页面
 */
public interface IDefaultManageView<T> extends MVPView {
    void onOptionSuccess();

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
