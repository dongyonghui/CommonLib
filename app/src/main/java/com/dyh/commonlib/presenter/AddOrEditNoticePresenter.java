package com.dyh.commonlib.presenter;

import android.text.TextUtils;

import com.dyh.commonlib.entity.request.AddOrEditNoticeRequestBean;
import com.dyh.commonlib.entity.response.CityItemBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IAddOrEditNoticeView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;

import java.util.List;

/**
 * 消息公告编辑
 */
public class AddOrEditNoticePresenter extends MVPPresenter<IAddOrEditNoticeView> {


    public AddOrEditNoticePresenter( IAddOrEditNoticeView view) {
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
     * 新建或修改
     *
     * @param requestBean
     */
    public void addOrEdit(AddOrEditNoticeRequestBean requestBean) {
        String url = ApiPathConstants.ADD_NOTICE;
        if (!TextUtils.isEmpty(requestBean.noticeId)) {
            url = ApiPathConstants.EDIT_NOTICE;
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
