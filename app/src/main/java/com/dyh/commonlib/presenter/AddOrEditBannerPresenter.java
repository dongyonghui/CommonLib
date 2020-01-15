package com.dyh.commonlib.presenter;

import android.text.TextUtils;

import com.dyh.commonlib.entity.request.AddOrEditBannerRequestBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IAddOrEditBannerView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;


/**
 * 头牌菜商品编辑
 */
public class AddOrEditBannerPresenter extends MVPPresenter<IAddOrEditBannerView> {


    public AddOrEditBannerPresenter( IAddOrEditBannerView view) {
        super(view);
    }

    /**
     * 新建或修改
     *
     * @param requestBean
     */
    public void addOrEditBanner(AddOrEditBannerRequestBean requestBean) {
        String url = ApiPathConstants.ADD_BANNER;
        if (!TextUtils.isEmpty(requestBean.bannerId)) {
            url = ApiPathConstants.EDIT_BANNER;
        }
        EasyHttp.post(url)
                .upObject(requestBean)
                .execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(Optional<Object> listOptional) {
                        getView().onSubmitSuccess();
                    }
                });
    }
}
