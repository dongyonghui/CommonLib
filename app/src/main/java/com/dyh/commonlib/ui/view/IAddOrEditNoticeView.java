package com.dyh.commonlib.ui.view;

import com.dyh.commonlib.entity.response.CityItemBean;
import com.dyh.common.lib.mvp.MVPView;

import java.util.List;

/**
 * 新建或编辑城市任务
 */
public interface IAddOrEditNoticeView extends MVPView {

    void onLoadCitySuccess(List<CityItemBean> list);

    void onOptionOk();
}
