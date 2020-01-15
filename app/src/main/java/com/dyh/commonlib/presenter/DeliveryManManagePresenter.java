package com.dyh.commonlib.presenter;

import android.content.res.Resources;

import com.dyh.commonlib.R;
import com.dyh.commonlib.constants.ServerConstants;
import com.dyh.commonlib.entity.request.ChangeDeliveryManRequestBean;
import com.dyh.commonlib.entity.request.GetDeliveryManRequestBean;
import com.dyh.commonlib.entity.response.DeliveryManItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IDeliveryManManageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.exception.ApiException;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;
import com.dyh.common.lib.weigit.dialog_default.MyAppDialog;
import com.dyh.common.lib.weigit.dialog_default.interfaces.OnDialogClickListener;

import java.util.List;

/**
 * 骑手管理
 */
public class DeliveryManManagePresenter extends MVPPresenter<IDeliveryManManageView<DeliveryManItemBean>> {


    public DeliveryManManagePresenter( IDeliveryManManageView<DeliveryManItemBean> view) {
        super(view);
    }

    public void refreshList(GetDeliveryManRequestBean requestBean) {
        //刷新前先清空list
        getView().showList(null);
        requestBean.page = 0;
        loadListData(requestBean);
    }

    public void loadList(GetDeliveryManRequestBean requestBean) {
        requestBean.page++;
        loadListData(requestBean);
    }

    /**
     * 删除
     *
     * @param itemBean
     */
    public void delete(DeliveryManItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        Resources resources = getActivity().getResources();
        new MyAppDialog(getActivity())
                .setTitle(getActivity().getString(R.string.delete))
                .setMessage(getActivity().getString(R.string.formatString_deleteDeliveryMan, itemBean.getName()))
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(resources.getString(R.string.delete), new OnDialogClickListener() {
                    @Override
                    public void onDialogButtonClicked(MyAppDialog dialog) {
                        ChangeDeliveryManRequestBean requestBean = new ChangeDeliveryManRequestBean();
                        requestBean.deliveryManId = itemBean.getId();
                        EasyHttp.post(ApiPathConstants.DELETE_DELIVERY_MAN)
                                .upObject(requestBean)
                                .execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
                                    @Override
                                    public void onSuccess(Optional<Object> listOptional) {
                                        getView().onOptionSuccess();
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onError(ApiException e) {
                                        super.onError(e);
                                        if (ServerConstants.ErrorCode.NEED_CHANGE_DELIVERY_MAN_FIRST == e.getCode()) {
                                            getView().toChangeDeliveryMan4Delete(itemBean);
                                        }
                                    }
                                });
                    }
                })
                .isCancelable(true)
                .show();
    }

    /**
     * 改派骑手
     *
     * @param fromDeliveryMan 被改派的骑手
     * @param toDeliveryMan   目标骑手
     * @param isDelete2Change true表示改派后删除，否则只改派
     */
    public void changeDeliveryMan(DeliveryManItemBean fromDeliveryMan, DeliveryManItemBean toDeliveryMan, boolean isDelete2Change) {
        if (fromDeliveryMan == null || toDeliveryMan == null) {
            return;
        }
        ChangeDeliveryManRequestBean requestBean = new ChangeDeliveryManRequestBean();
        requestBean.deliveryManId = fromDeliveryMan.getId();
        requestBean.anotherDeliveryManId = toDeliveryMan.getId();
        EasyHttp.post(isDelete2Change ? ApiPathConstants.DELETE_DELIVERY_MAN : ApiPathConstants.CHANGE_DELIVERY_MAN)
                .upObject(requestBean)
                .execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(Optional<Object> listOptional) {
                        getView().onOptionSuccess();
                    }
                });
    }


    private void loadListData(GetDeliveryManRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_DELIVERY_MAN_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<DeliveryManItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<DeliveryManItemBean>> listOptional) {
                        if (requestBean.page == 0) {
                            getView().showList(listOptional.getIncludeNull());
                        } else {
                            getView().appendList(listOptional.getIncludeNull());
                        }
                    }
                });
    }
}
