package com.dyh.commonlib.presenter;

import android.text.TextUtils;

import com.dyh.commonlib.entity.request.AddOrEditSupplyPoolRequestBean;
import com.dyh.commonlib.entity.request.GetFilterListRequestBean;
import com.dyh.commonlib.entity.response.SupplyPoolItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IAddOrEditSupplyPoolView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.List;

/**
 * 新建或编辑供给池
 */
public class AddOrEditSupplyPoolPresenter extends MVPPresenter<IAddOrEditSupplyPoolView> {


    public AddOrEditSupplyPoolPresenter( IAddOrEditSupplyPoolView view) {
        super(view);
    }

    /**
     * 加载配送地址列表
     *
     * @param requestBean
     */
    public void loadSupplyPoolList(GetFilterListRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.GET_SUPPLY_POOL_LIST)
                .upObject(requestBean)
                .execute(new MyHttpCallBack<List<SupplyPoolItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<SupplyPoolItemBean>> listOptional) {
                        getView().onLoadSupplyPoolSuccess(listOptional.getIncludeNull());
                    }
                });
    }

    /**
     * 提交请求
     *
     * @param requestBean
     */
    public void addOrEdit(AddOrEditSupplyPoolRequestBean requestBean) {
        String url = ApiPathConstants.ADD_SUPPLY_POOL;
        if (!TextUtils.isEmpty(requestBean.supplyPoolId)) {
            url = ApiPathConstants.EDIT_SUPPLY_POOL;
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
