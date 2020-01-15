package com.dyh.commonlib.ui.view;

import com.dyh.commonlib.entity.response.StatisticsResponseBean;
import com.dyh.common.lib.mvp.MVPView;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/18/018 16:54
 * 描述：统计
 */
public interface IStatisticsView extends MVPView {
    /**
     * 展示数据
     *
     * @param responseBean
     */
    void showData(StatisticsResponseBean responseBean);
}
