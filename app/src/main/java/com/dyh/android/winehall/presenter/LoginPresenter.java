package com.dyh.android.winehall.presenter;

import android.text.TextUtils;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.local.SPAuthEntity;
import com.dyh.android.winehall.entity.local.SPUserEntity;
import com.dyh.android.winehall.entity.request.LoginRequestBean;
import com.dyh.android.winehall.entity.response.LoginResponseBean;
import com.dyh.android.winehall.http.ApiPathConstants;
import com.dyh.android.winehall.view.ILoginView;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.ProgressDialogCallBack;
import com.dyh.common.lib.http.callback.ProgressDialogDefault;
import com.dyh.common.lib.http.model.ResponseOptional;
import com.dyh.common.lib.mvp.MVPPresenter;

import org.jetbrains.annotations.Nullable;

/**
 * 作者：Allan
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：登录认证逻辑层
 */
public class LoginPresenter extends MVPPresenter<ILoginView> {
    public LoginPresenter(@Nullable ILoginView view) {
        super(view);
    }

    private void saveLoginInfo(LoginResponseBean loginResponseBean) {
        if (null == loginResponseBean) return;

        //保存用户信息
        SPUserEntity spUserEntity = EasySharedPreferences.load(SPUserEntity.class);
        spUserEntity.userInfo = loginResponseBean;
        spUserEntity.commit();

        //保存token认证信息
        SPAuthEntity spAuthEntity = EasySharedPreferences.load(SPAuthEntity.class);
        spAuthEntity.topsession = loginResponseBean.getToken();
        spAuthEntity.commit();
    }

    /**
     * 登录
     *
     * @param requestBean
     */
    public void login(LoginRequestBean requestBean) {
        if (TextUtils.isEmpty(requestBean.mobile)) {
            EasyToast.getDEFAULT().show(R.string.inputPhoneNumber);
            return;
        }

        if (TextUtils.isEmpty(requestBean.code)) {
            EasyToast.getDEFAULT().show(R.string.inputValidateCode);
            return;
        }

        EasyHttp.post(ApiPathConstants.LOGIN_PATH)
                .upObject(requestBean)
                .execute(new ProgressDialogCallBack<LoginResponseBean>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(ResponseOptional<LoginResponseBean> loginResponseBeanResponseOptional) {
                        LoginResponseBean loginResponseBean = loginResponseBeanResponseOptional.getIncludeNull();
                        saveLoginInfo(loginResponseBean);
                        getView().onLoginSuccess(loginResponseBean);
                    }
                }, getLifecycleProvider());

    }

    /**
     * 登录
     *
     * @param requestBean
     */
    public void sendMessageCode(LoginRequestBean requestBean) {
        if (TextUtils.isEmpty(requestBean.mobile)) {
            EasyToast.getDEFAULT().show(R.string.inputPhoneNumber);
            return;
        }

        EasyHttp.post(ApiPathConstants.SEND_SMS_CODE)
                .upObject(requestBean)
                .execute(new ProgressDialogCallBack<String>(new ProgressDialogDefault(getActivity())) {
                    @Override
                    public void onSuccess(ResponseOptional<String> loginResponseBeanResponseOptional) {
                        EasyToast.getDEFAULT().show(R.string.info_sendSmsOk);
                    }
                }, getLifecycleProvider());

    }
}
