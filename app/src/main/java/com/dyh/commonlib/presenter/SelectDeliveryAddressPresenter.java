package com.dyh.commonlib.presenter;

import com.dyh.commonlib.entity.request.GetDeliveryAddressRequestBean;
import com.dyh.commonlib.entity.response.DeliveryAddressManageItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.List;

/**
 * 选择配送地址
 */
public class SelectDeliveryAddressPresenter extends MVPPresenter<IPageView<DeliveryAddressManageItemBean>> {


    private GetDeliveryAddressRequestBean requestBean;
    public SelectDeliveryAddressPresenter( IPageView<DeliveryAddressManageItemBean> view) {
        super(view);
    }

    public void refreshList(GetDeliveryAddressRequestBean requestBean) {
        //刷新前先清空list
        getView().showList(null);
        this.requestBean = requestBean;
        requestBean.page = 0;
        loadListData(requestBean);
    }

    public void loadList() {
        requestBean.page++;
        loadListData(requestBean);
    }


    private void loadListData(GetDeliveryAddressRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_DELIVERY_ADDRESS_MANAGE_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<DeliveryAddressManageItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<DeliveryAddressManageItemBean>> listOptional) {
                        if (requestBean.page == 0) {
                            getView().showList(listOptional.getIncludeNull());
                        } else {
                            getView().appendList(listOptional.getIncludeNull());
                        }
                    }
                });
    }
}
