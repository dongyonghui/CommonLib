package com.dyh.commonlib.ui.view;

import com.dyh.commonlib.entity.response.CityItemBean;
import com.dyh.commonlib.entity.response.DeliveryAddressItemBean;
import com.dyh.commonlib.entity.response.SupplyPoolItemBean;
import com.dyh.common.lib.mvp.MVPView;

import java.util.List;

public interface IFilterManageView extends MVPView {

    void onLoadCitySuccess(List<CityItemBean> list);

    void onLoadDeliveryAddressSuccess(List<DeliveryAddressItemBean> list);

    void onLoadSupplyPoolSuccess(List<SupplyPoolItemBean> list);
}
