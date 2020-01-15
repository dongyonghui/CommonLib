package com.dyh.commonlib.presenter;

import android.text.TextUtils;

import com.dyh.commonlib.entity.request.AddOrEditCityTaskRequestBean;
import com.dyh.commonlib.entity.response.CityItemBean;
import com.dyh.commonlib.entity.response.OperatorItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IAddOrEditCityTaskView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.List;

/**
 * 新建或编辑城市任务
 */
public class AddOrEditCityTaskPresenter extends MVPPresenter<IAddOrEditCityTaskView> {


    public AddOrEditCityTaskPresenter( IAddOrEditCityTaskView view) {
        super(view);
    }

    /**
     * 加载配城市列表
     */
    public void loadCityList() {
        EasyHttp.post(ApiPathConstants.GET_CITY_LIST)
                .execute(new MyHttpCallBack<List<CityItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<CityItemBean>> listOptional) {
                        getView().onLoadCitySuccess(listOptional.getIncludeNull());
                    }
                });
    }

    /**
     * 加载配城市列表
     */
    public void loadOperatorList() {
        EasyHttp.post(ApiPathConstants.GET_ALL_OPERATOR)
                .execute(new MyHttpCallBack<List<OperatorItemBean>>(getView()) {
                    @Override
                    public void onSuccess(Optional<List<OperatorItemBean>> listOptional) {
                        getView().onLoadOperatorSuccess(listOptional.getIncludeNull());
                    }
                });
    }

    /**
     * 提交请求
     *
     * @param requestBean
     */
    public void addOrEdit(AddOrEditCityTaskRequestBean requestBean) {
        String url = ApiPathConstants.ADD_CITY_TASK;
        if (!TextUtils.isEmpty(requestBean.orderTaskId)) {
            url = ApiPathConstants.EDIT_CITY_TASK;
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
