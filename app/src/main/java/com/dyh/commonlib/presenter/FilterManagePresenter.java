package com.dyh.commonlib.presenter;

import com.dyh.commonlib.entity.request.GetFilterListRequestBean;
import com.dyh.commonlib.entity.response.CityItemBean;
import com.dyh.commonlib.entity.response.DeliveryAddressItemBean;
import com.dyh.commonlib.entity.response.SupplyPoolItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IFilterManageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.List;

/**
 * 筛选条件管理
 */
public class FilterManagePresenter extends MVPPresenter<IFilterManageView> {


    public FilterManagePresenter( IFilterManageView view) {
        super(view);
    }

    /**
     * 加载配城市列表
     */
    public void loadCityList() {
        EasyHttp.post(ApiPathConstants.GET_CITY_LIST)
                .execute(new MyHttpCallBack<List<CityItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<CityItemBean>> listOptional) {
                        getView().onLoadCitySuccess(listOptional.getIncludeNull());
                    }
                });
    }

    /**
     * 加载配送地址列表
     *
     * @param requestBean
     */
    public void loadDeliveryAddressList(GetFilterListRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_DELIVERY_ADDRESS_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<DeliveryAddressItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<DeliveryAddressItemBean>> listOptional) {
                        getView().onLoadDeliveryAddressSuccess(listOptional.getIncludeNull());
                    }
                });
    }

    /**
     * 加载配送地址列表
     *
     * @param requestBean
     */
    public void loadSupplyPoolList(GetFilterListRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_SUPPLY_POOL_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<SupplyPoolItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<SupplyPoolItemBean>> listOptional) {
                        getView().onLoadSupplyPoolSuccess(listOptional.getIncludeNull());
                    }
                });
    }
}
