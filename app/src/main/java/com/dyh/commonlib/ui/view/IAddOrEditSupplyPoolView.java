package com.dyh.commonlib.ui.view;

import com.dyh.commonlib.entity.response.SupplyPoolItemBean;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/6/006 15:21
 * 描述：编辑或新建供给池
 */
public interface IAddOrEditSupplyPoolView extends IDefaultOptionView {

    /**
     * 获取所有供给池成功，绘制所有供给池的图形围栏
     *
     * @param supplyPoolItemBeanList
     */
    void onLoadSupplyPoolSuccess(List<SupplyPoolItemBean> supplyPoolItemBeanList);
}
