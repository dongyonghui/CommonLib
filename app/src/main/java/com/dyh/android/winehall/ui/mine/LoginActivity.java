package com.dyh.android.winehall.ui.mine;

import android.os.Bundle;
import android.view.View;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.local.EventMessageBean;
import com.dyh.android.winehall.entity.request.LoginRequestBean;
import com.dyh.android.winehall.entity.response.LoginResponseBean;
import com.dyh.android.winehall.presenter.LoginPresenter;
import com.dyh.android.winehall.view.ILoginView;
import com.dyh.android.winehall.weigit.ContainsEmojiEditText;
import com.dyh.common.lib.easy.EasyToast;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseMVPActivity implements ILoginView {
    @BindView(R.id.mLoginNameEditText)
    ContainsEmojiEditText mLoginNameEditText;
    @BindView(R.id.mLoginCodeEditText)
    ContainsEmojiEditText mLoginCodeEditText;

    private final LoginPresenter mLoginPresenter = new LoginPresenter(this);

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPage(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onLoginSuccess(LoginResponseBean responseBean) {
        EasyToast.getDEFAULT().show(R.string.info_loginSuccess);
        EventBus.getDefault().post(new EventMessageBean(EventMessageBean.CODE_LOGIN_SUCCESS));
        finish();
    }

    @OnClick(R.id.mLoginTextView)
    public void login(View view) {
        LoginRequestBean requestBean = new LoginRequestBean();
        requestBean.mobile = mLoginNameEditText.getText().toString();
        requestBean.code = mLoginCodeEditText.getText().toString();
        requestBean.store_key = getWxStoreKey();
        mLoginPresenter.login(requestBean);
    }

    /**
     * @return 如果是微信登录，需要返回数据
     */
    protected String getWxStoreKey() {
        return null;
    }

    @OnClick(R.id.mLoginGetCodeTextView)
    public void getValidateCode() {
        LoginRequestBean requestBean = new LoginRequestBean();
        requestBean.mobile = mLoginNameEditText.getText().toString();
        mLoginPresenter.sendMessageCode(requestBean);
        mLoginCodeEditText.setText("666666");
    }
}

