package com.dyh.commonlib.ui.view;

import com.dyh.commonlib.entity.response.CityDeliverPriceBean;
import com.dyh.common.lib.mvp.MVPView;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/26/026 18:30
 * 描述：编辑城市配送费
 */
public interface ICityDeliveryPriceManageView extends MVPView {

    /**
     * 获取城市配送费成功
     *
     * @param deliverPriceBean
     */
    void onLoadDeliveryPriceSuccess(CityDeliverPriceBean deliverPriceBean);

    /**
     * 提交成功
     */
    void onSubmitSuccess();
}
