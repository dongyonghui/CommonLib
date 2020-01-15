package com.dyh.commonlib.presenter;

import com.dyh.commonlib.entity.request.GetTpcGoodsRequestBean;
import com.dyh.commonlib.entity.response.TpcGoodsItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.List;

/**
 * 头牌管理
 */
public class TpcGoodsManagePresenter extends MVPPresenter<IPageView<TpcGoodsItemBean>> {


    public TpcGoodsManagePresenter( IPageView<TpcGoodsItemBean> view) {
        super(view);
    }

    public void refreshList(GetTpcGoodsRequestBean requestBean) {
        //刷新前先清空list
        getView().showList(null);
        requestBean.page = 0;
        loadListData(requestBean);
    }

    public void loadList(GetTpcGoodsRequestBean requestBean) {
        requestBean.page++;
        loadListData(requestBean);
    }


    private void loadListData(GetTpcGoodsRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_TPC_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<TpcGoodsItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<TpcGoodsItemBean>> listOptional) {
                        if (requestBean.page == 0) {
                            getView().showList(listOptional.getIncludeNull());
                        } else {
                            getView().appendList(listOptional.getIncludeNull());
                        }
                    }
                });
    }
}
