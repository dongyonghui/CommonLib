package com.dyh.commonlib.presenter;

import android.content.res.Resources;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.request.DeleteCityTaskRequestBean;
import com.dyh.commonlib.entity.request.GetCityTaskRequestBean;
import com.dyh.commonlib.entity.response.CityTaskItemBean;
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
 * 城市任务管理
 */
public class CityTaskManagePresenter extends MVPPresenter<IDefaultManageView<CityTaskItemBean>> {


    public CityTaskManagePresenter( IDefaultManageView<CityTaskItemBean> view) {
        super(view);
    }

    public void refreshList(GetCityTaskRequestBean requestBean) {
        //刷新前先清空list
        getView().showList(null);
        requestBean.page = 0;
        loadListData(requestBean);
    }

    public void loadList(GetCityTaskRequestBean requestBean) {
        requestBean.page++;
        loadListData(requestBean);
    }

    /**
     * 删除
     *
     * @param itemBean
     */
    public void deleteCityTask(CityTaskItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        Resources resources = getActivity().getResources();
        new MyAppDialog(getActivity())
                .setTitle(getActivity().getString(R.string.delete))
                .setMessage(getActivity().getString(R.string.formatString_deleteCityTask, itemBean.getCity()))
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(resources.getString(R.string.delete), new OnDialogClickListener() {
                    @Override
                    public void onDialogButtonClicked(MyAppDialog dialog) {
                        DeleteCityTaskRequestBean requestBean = new DeleteCityTaskRequestBean();
                        requestBean.orderTaskId = itemBean.getOrderTaskId();
                        EasyHttp.post(ApiPathConstants.DELETE_CITY_TASK)
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


    private void loadListData(GetCityTaskRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_CITY_TASK_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<CityTaskItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<CityTaskItemBean>> listOptional) {
                        if (requestBean.page == 0) {
                            getView().showList(listOptional.getIncludeNull());
                        } else {
                            getView().appendList(listOptional.getIncludeNull());
                        }
                    }
                });
    }
}
