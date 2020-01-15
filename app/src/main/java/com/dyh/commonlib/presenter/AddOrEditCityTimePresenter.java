package com.dyh.commonlib.presenter;

import android.text.TextUtils;

import com.dyh.commonlib.entity.response.TimeItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;


/**
 * 新建或编辑城市时间
 */
public class AddOrEditCityTimePresenter extends MVPPresenter<IDefaultOptionView> {


    public AddOrEditCityTimePresenter( IDefaultOptionView view) {
        super(view);
    }

    /**
     * 提交请求
     *
     * @param requestBean
     */
    public void addOrEdit(TimeItemBean requestBean) {
        String url = ApiPathConstants.ADD_CITY_TIME;
        if (!TextUtils.isEmpty(requestBean.getId())) {
            url = ApiPathConstants.EDIT_CITY_TIME;
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
