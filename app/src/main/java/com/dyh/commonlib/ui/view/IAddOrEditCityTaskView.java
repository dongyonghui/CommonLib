package com.dyh.commonlib.ui.view;

import com.dyh.commonlib.entity.response.CityItemBean;
import com.dyh.commonlib.entity.response.OperatorItemBean;
import com.dyh.common.lib.mvp.MVPView;

import java.util.List;

/**
 * 新建或编辑城市任务
 */
public interface IAddOrEditCityTaskView extends MVPView {

    void onLoadCitySuccess(List<CityItemBean> list);

    void onLoadOperatorSuccess(List<OperatorItemBean> list);

    void onOptionOk();
}
