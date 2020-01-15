package com.dyh.commonlib.presenter;

import com.dyh.commonlib.entity.BaseHttpPageRequestBean;
import com.dyh.commonlib.entity.response.LabelItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IPageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.List;

/**
 * 设置标签
 */
public class SetTpcGoodsLabelPresenter extends MVPPresenter<IPageView<LabelItemBean>> {

    private final BaseHttpPageRequestBean requestBean = new BaseHttpPageRequestBean();

    public SetTpcGoodsLabelPresenter( IPageView<LabelItemBean> view) {
        super(view);
    }

    public void refreshList() {
        requestBean.page = 0;
        loadListData(requestBean);
    }

    public void loadList() {
        requestBean.page++;
        loadListData(requestBean);
    }


    private void loadListData(BaseHttpPageRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_TPC_GOODS_LABELS)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<LabelItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<LabelItemBean>> listOptional) {
                        if (requestBean.page == 0) {
                            getView().showList(listOptional.getIncludeNull());
                        } else {
                            getView().appendList(listOptional.getIncludeNull());
                        }
                    }
                });
    }
}
