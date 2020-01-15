package com.dyh.commonlib.presenter;

import com.dyh.commonlib.entity.request.GetOrderListRequestBean;
import com.dyh.commonlib.entity.response.OrderItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IDefaultManageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.List;

/**
 * 订单管理
 */
public class OrderManagePresenter extends MVPPresenter<IDefaultManageView<OrderItemBean>> {


    public OrderManagePresenter( IDefaultManageView<OrderItemBean> view) {
        super(view);
    }

    public void refreshList(GetOrderListRequestBean requestBean) {
        //刷新前先清空list
        getView().showList(null);
        requestBean.page = 0;
        loadListData(requestBean);
    }

    public void loadList(GetOrderListRequestBean requestBean) {
        requestBean.page++;
        loadListData(requestBean);
    }

    private void loadListData(GetOrderListRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_ORDER_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<OrderItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<OrderItemBean>> listOptional) {
                        if (requestBean.page == 0) {
                            getView().showList(listOptional.getIncludeNull());
                        } else {
                            getView().appendList(listOptional.getIncludeNull());
                        }
                    }
                });
    }
}
