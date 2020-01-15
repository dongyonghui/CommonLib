package com.dyh.commonlib.presenter;

import android.text.TextUtils;

import com.dyh.commonlib.entity.request.AddOrEditGroupPrizeRequestBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.IDefaultOptionView;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;


/**
 * 团体奖励编辑
 */
public class AddOrEditGropPrizePresenter extends MVPPresenter<IDefaultOptionView> {


    public AddOrEditGropPrizePresenter( IDefaultOptionView view) {
        super(view);
    }


    /**
     * 新建或修改
     *
     * @param requestBean
     */
    public void addOrEdit(AddOrEditGroupPrizeRequestBean requestBean) {
        String url = ApiPathConstants.ADD_GROUP_PRIZE;
        if (!TextUtils.isEmpty(requestBean.getUserTeamAwardId())) {
            url = ApiPathConstants.EDIT_GROUP_PRIZE;
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
