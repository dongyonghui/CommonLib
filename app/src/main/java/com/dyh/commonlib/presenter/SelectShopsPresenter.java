package com.dyh.commonlib.presenter;

import com.dyh.commonlib.entity.BaseHttpPageRequestBean;
import com.dyh.commonlib.entity.request.GetShopListRequestBean;
import com.dyh.commonlib.entity.response.ShopItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.List;

/**
 * 选择店铺
 */
public class SelectShopsPresenter extends MVPPresenter<IPageView<ShopItemBean>> {

    private final GetShopListRequestBean requestBean = new GetShopListRequestBean();

    public SelectShopsPresenter( IPageView<ShopItemBean> view) {
        super(view);
    }

    public void refreshList(String searchInfo) {
        requestBean.page = 0;
        requestBean.name = searchInfo;
        loadListData(requestBean);
    }

    public void loadList() {
        requestBean.page++;
        loadListData(requestBean);
    }


    private void loadListData(BaseHttpPageRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_SHOP_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<ShopItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<ShopItemBean>> listOptional) {
                        if (requestBean.page == 0) {
                            getView().showList(listOptional.getIncludeNull());
                        } else {
                            getView().appendList(listOptional.getIncludeNull());
                        }
                    }
                });
    }
}
