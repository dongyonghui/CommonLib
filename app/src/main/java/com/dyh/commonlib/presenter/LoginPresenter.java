package com.dyh.commonlib.presenter;

import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.commonlib.entity.request.LoginRequestBean;
import com.dyh.commonlib.entity.response.LoginResponseBean;
import com.dyh.commonlib.http.ApiPathConstants;
import com.dyh.commonlib.ui.view.ILoginView;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.Optional;
import com.dyh.common.lib.mvp.MVPPresenter;


/**
 * 作者：DongYonghui
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：登录认证逻辑层
 */
public class LoginPresenter extends MVPPresenter<ILoginView> {
    public LoginPresenter( ILoginView view) {
        super(view);
    }

    /**
     * 登录
     *
     * @param requestBean
     */
    public void login(LoginRequestBean requestBean) {
        EasyHttp.post(ApiPathConstants.LOGIN_PATH).upObject(requestBean).execute(new ProgressDialogCallBack<LoginResponseBean>(new ProgressDialogDefault(getActivity())) {
            @Override
            public void onSuccess(Optional<LoginResponseBean> loginResponseBeanOptional) {
                SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
                spEntity.loginResponseBean = loginResponseBeanOptional.getIncludeNull();
                spEntity.commit();
                getView().onLoginSuccess(loginResponseBeanOptional.getIncludeNull());
            }
        });
    }
}
