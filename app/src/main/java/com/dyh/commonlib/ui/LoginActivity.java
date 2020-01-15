package com.dyh.commonlib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.commonlib.entity.request.LoginRequestBean;
import com.dyh.commonlib.entity.response.LoginResponseBean;
import com.dyh.commonlib.presenter.LoginPresenter;
import com.dyh.commonlib.ui.view.ILoginView;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseMVPActivity implements ILoginView {

    @BindView(R.id.mLoginNoEditText)
    EditText mLoginNoEditText;
    @BindView(R.id.mPwdEditText)
    EditText mPwdEditText;

    private final LoginPresenter mLoginPresenter = new LoginPresenter(this);

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
        mLoginNoEditText.setText(spEntity.loginName);

        mLoginNoEditText.setText("13141292840");
        mPwdEditText.setText("gouku2018");
    }

    @Override
    public void onLoginSuccess(LoginResponseBean responseBean) {
        SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
        spEntity.loginName = mLoginNoEditText.getText().toString();
        spEntity.commit();
        readyGoThenKill(HomeActivity.class);
    }

    @OnClick(R.id.mLoginTextView)
    public void login(View view) {
        LoginRequestBean requestBean = new LoginRequestBean();
        requestBean.account = mLoginNoEditText.getText().toString();
        requestBean.password = mPwdEditText.getText().toString();
        mLoginPresenter.login(requestBean);
    }

    @Override
    public CommonTitleBar getCommonTitleBar() {
        return null;
    }
}

