package com.dyh.common.lib.mvp

import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/4/21 0021 15:18
 * description:页面多状态管理视图
 */
interface IPageMultipleStatusView {
    /**
     * 获取通用标题栏
     */
    fun getCommonTitleBar(): CommonTitleBar?

    /**
     * 如果需要在页面内展示多状态，可复写此方法，
     * 返回需要在loading中隐藏并替换未加载状态组件的id即可
     * 注意：返回的id必须是此Activity中的组件
     */
    fun getMultipleStatusContentViewId(): Int

    /**
     * 如果不需要展示多状态可以设置为false，默认true
     */
    fun isNeedShowMultipleStatusView(): Boolean

    /**
     * 重试按钮点击
     */
    fun onRetryClick()
}