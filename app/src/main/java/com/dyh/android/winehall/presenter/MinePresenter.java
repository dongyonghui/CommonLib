package com.dyh.android.winehall.presenter;

import com.dyh.android.winehall.MyApplication;
import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.local.SPUserEntity;
import com.dyh.android.winehall.entity.response.UserInfoResponseBean;
import com.dyh.android.winehall.http.ApiPathConstants;
import com.dyh.android.winehall.view.IHomeMineView;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.http.EasyHttp;
import com.dyh.common.lib.http.callback.MyHttpNoViewCallBack;
import com.dyh.common.lib.http.exception.ApiException;
import com.dyh.common.lib.http.model.ResponseOptional;
import com.dyh.common.lib.mvp.MVPPresenter;

import org.jetbrains.annotations.Nullable;

/**
 * 作者：Allan
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：登录认证逻辑层
 */
public class MinePresenter extends MVPPresenter<IHomeMineView> {
    public MinePresenter(@Nullable IHomeMineView view) {
        super(view);
    }

    /**
     * 刷新用户信息
     */
    public void refreshUserInfo() {
        //未登录状态无需刷新
        if (!MyApplication.getInstance().isLogin()) {
            getView().showUserInfo2View(null);
            return;
        }

        EasyHttp.get(ApiPathConstants.USER_INFO)
                .execute(new MyHttpNoViewCallBack<UserInfoResponseBean>() {
                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getView().hideLoadingView();
                    }

                    @Override
                    public void onSuccess(ResponseOptional<UserInfoResponseBean> userInfoResponseBeanResponseOptional) {
                        getView().hideLoadingView();
                        UserInfoResponseBean userInfoResponseBean = userInfoResponseBeanResponseOptional.getIncludeNull();
                        //跟新缓存信息
                        SPUserEntity userEntity = EasySharedPreferences.load(SPUserEntity.class);
                        userEntity.userInfo = userInfoResponseBean;
                        userEntity.commit();

                        getView().showUserInfo2View(userInfoResponseBean);
                    }
                }, getLifecycleProvider());

    }

    /**
     * 退出登录
     */
    public void logout() {
        //清空缓存
        MyApplication.getInstance().clearLoginCacheInfo();

        //刷新UI
        getView().showUserInfo2View(null);
        EasyToast.getDEFAULT().show(R.string.info_logoutOk);
    }
}
