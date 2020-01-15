package com.dyh.commonlib.presenter;

import android.text.TextUtils;

import com.dyh.commonlib.entity.request.AddOrEditDeliveryManRequestBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;


/**
 * 新建或编辑骑手
 */
public class AddOrEditDeliveryManPresenter extends MVPPresenter<IDefaultOptionView> {


    public AddOrEditDeliveryManPresenter( IDefaultOptionView view) {
        super(view);
    }


    /**
     * 提交请求
     *
     * @param requestBean
     */
    public void addOrEdit(AddOrEditDeliveryManRequestBean requestBean) {
        String url = ApiPathConstants.ADD_DELIVERY_MAN;
        if (!TextUtils.isEmpty(requestBean.deliveryManId)) {
            url = ApiPathConstants.EDIT_DELIVERY_MAN;
        }
        EasyHttp.post(url)
                .upObject(requestBean)
                .execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(Optional<Object> listOptional) {
                        getView().onOptionOk();
                    }
                });
    }
}
