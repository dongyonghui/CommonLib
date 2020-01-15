package com.dyh.commonlib.presenter;

import android.content.res.Resources;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.request.DeleteBannerRequestBean;
import com.dyh.commonlib.entity.request.GetCityTaskRequestBean;
import com.dyh.commonlib.entity.response.BannerItemBean;
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
public class BannerManagePresenter extends MVPPresenter<IDefaultManageView<BannerItemBean>> {


    public BannerManagePresenter( IDefaultManageView<BannerItemBean> view) {
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
    public void deleteCityTask(BannerItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        Resources resources = getActivity().getResources();
        new MyAppDialog(getActivity())
                .setTitle(getActivity().getString(R.string.delete))
                .setMessage(getActivity().getString(R.string.deleteBannerConfirmInfo))
                .setNegative(resources.getString(R.string.cancel))
                .setPositive(resources.getString(R.string.delete), new OnDialogClickListener() {
                    @Override
                    public void onDialogButtonClicked(MyAppDialog dialog) {
                        DeleteBannerRequestBean requestBean = new DeleteBannerRequestBean();
                        requestBean.bannerId = itemBean.getBannerId();
                        EasyHttp.post(ApiPathConstants.DELETE_BANNER)
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
        EasyHttp.post(ApiPathConstants.GET_BANNER_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<BannerItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<BannerItemBean>> listOptional) {
                        if (requestBean.page == 0) {
                            getView().showList(listOptional.getIncludeNull());
                        } else {
                            getView().appendList(listOptional.getIncludeNull());
                        }
                    }
                });
    }
}
