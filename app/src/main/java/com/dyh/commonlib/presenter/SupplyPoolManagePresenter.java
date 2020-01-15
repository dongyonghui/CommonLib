package com.dyh.commonlib.presenter;

import android.content.res.Resources;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.request.DeleteSupplyPoolRequestBean;
import com.dyh.commonlib.entity.request.GetSupplyPoolRequestBean;
import com.dyh.commonlib.entity.response.SupplyPoolManageItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IDefaultManageView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;
import com.dyh.common.lib.weigit.dialog_default.MyAppDialog;
import com.dyh.common.lib.weigit.dialog_default.interfaces.OnDialogClickListener;

import java.util.List;

/**
 * 供给池管理
 */
public class SupplyPoolManagePresenter extends MVPPresenter<IDefaultManageView<SupplyPoolManageItemBean>> {


    public SupplyPoolManagePresenter( IDefaultManageView<SupplyPoolManageItemBean> view) {
        super(view);
    }

    public void refreshList(GetSupplyPoolRequestBean requestBean) {
        //刷新前先清空list
        getView().showList(null);
        requestBean.page = 0;
        loadListData(requestBean);
    }

    public void loadList(GetSupplyPoolRequestBean requestBean) {
        requestBean.page++;
        loadListData(requestBean);
    }

    /**
     * 删除配送地址
     *
     * @param itemBean
     */
    public void deleteDeliverAddress(SupplyPoolManageItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        Resources resources = getActivity().getResources();
        new MyAppDialog(getActivity())
                .setTitle(getActivity().getString(R.string.delete))
                .setMessage(getActivity().getString(R.string.formatString_deleteSupplyPool, itemBean.getName()))
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(resources.getString(R.string.delete), new OnDialogClickListener() {
                    @Override
                    public void onDialogButtonClicked(MyAppDialog dialog) {
                        DeleteSupplyPoolRequestBean requestBean = new DeleteSupplyPoolRequestBean();
                        requestBean.supplyPoolId = itemBean.getSupplyPoolId();
                        EasyHttp.post(ApiPathConstants.DELETE_SUPPLY_POOL)
                                .upObject(requestBean)
                                .execute(new ProgressDialogCallBack<Object>(new ProgressDialogDefault(getActivity())) {
                                    @Override
                                    public void onSuccess(Optional<Object> listOptional) {
                                        getView().onOptionSuccess();
                                        dialog.dismiss();
                                    }
                                });
                    }
                })
                .isCancelable(true)
                .show();
    }

    private void loadListData(GetSupplyPoolRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_SUPPLY_POOL_MANAGE_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<SupplyPoolManageItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<SupplyPoolManageItemBean>> listOptional) {
                        if (requestBean.page == 0) {
                            getView().showList(listOptional.getIncludeNull());
                        } else {
                            getView().appendList(listOptional.getIncludeNull());
                        }
                    }
                });
    }
}
