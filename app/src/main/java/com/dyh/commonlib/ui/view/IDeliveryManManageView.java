package com.dyh.commonlib.ui.view;

import com.dyh.commonlib.entity.response.DeliveryManItemBean;
import com.dyh.common.lib.mvp.MVPView;

import java.util.List;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/12/012 16:53
 * 描述：骑手管理
 */
public interface IDeliveryManManageView<T> extends MVPView {
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

    /**
     * 改派骑手然后删除
     *
     * @param deliveryManItemBean
     */
    void toChangeDeliveryMan4Delete(DeliveryManItemBean deliveryManItemBean);
}
