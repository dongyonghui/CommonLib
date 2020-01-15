package com.dyh.commonlib.presenter;

import com.dyh.commonlib.entity.request.EditTpcGoodsRequestBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.ITpcGoodsEditView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;


/**
 * 头牌菜商品编辑
 */
public class TpcGoodsEditPresenter extends MVPPresenter<ITpcGoodsEditView> {


    public TpcGoodsEditPresenter( ITpcGoodsEditView view) {
        super(view);
    }

    /**
     * 修改商品
     *
     * @param requestBean
     */
    public void update(EditTpcGoodsRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.UPDATE_TPC_GOODS).upObject(requestBean).execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
            @Override
            public void onSuccess(Optional<Object> result) {
                getView().onSubmitSuccess();
            }
        });
    }
}
