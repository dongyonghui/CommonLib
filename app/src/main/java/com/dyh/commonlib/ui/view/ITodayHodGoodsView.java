package com.dyh.commonlib.ui.view;

import com.dyh.commonlib.entity.response.DeliveryAreaItemBean;
import com.dyh.commonlib.entity.response.StatisticsResponseBean;
import com.dyh.common.lib.mvp.MVPView;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/18/018 16:54
 * 描述：统计
 */
public interface ITodayHodGoodsView extends MVPView {
    /**
     * 展示数据
     *
     * @param list
     */
    void showConditionData(List<DeliveryAreaItemBean> list);

    void showData(StatisticsResponseBean responseBean);
}
