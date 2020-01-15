package com.dyh.commonlib.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.dyh.commonlib.MyApplication;
import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.commonlib.presenter.AppUpdatePresenter;
import com.dyh.commonlib.ui.view.IAppUpdateView;
import com.dyh.common.lib.dw.util.AppTools;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.mvp.impl.BaseMVPFragment;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.vip.dw.bluetoothprinterlib.ui.PrinterManagerActivity;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/11/19/019 17:27
 * 描述：todo
 */
public class MineFragment extends BaseMVPFragment implements IAppUpdateView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mCheckVersionTextView)
    TextView mCheckVersionTextView;
    @BindView(R.id.mNameTextView)
    TextView mNameTextView;
    @BindView(R.id.mRolesNameTextView)
    TextView mRolesNameTextView;
    @BindView(R.id.mCityNameTextView)
    TextView mCityNameTextView;

    private final AppUpdatePresenter updatePresenter = new AppUpdatePresenter(this);



    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_mine;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        //设置登录用户信息
        SPEntity spEntity = EasySharedPreferences.load(SPEntity.class);
        if (spEntity.loginResponseBean != null && spEntity.loginResponseBean.getOper() != null) {
            mNameTextView.setText(getString(R.string.formatString_userName, spEntity.loginResponseBean.getOper().getName(), spEntity.loginResponseBean.getOper().getUsername()));
            mRolesNameTextView.setText(getString(R.string.formatString_roleName, spEntity.loginResponseBean.getOper().getRolesName()));
            mCityNameTextView.setText(getString(R.string.formatString_city, spEntity.loginResponseBean.getOper().getCity()));
        }
        //设置当前版本信息
        mCheckVersionTextView.setText(getString(R.string.formatString_checkNewVersion, AppTools.getVersionName(getContext())));
    }

    @OnClick(R.id.mSetPrinterTextView)
    public void setPrinter() {
        readyGo(PrinterManagerActivity.class);
    }

    @OnClick(R.id.mCheckVersionTextView)
    public void checkAppVersion() {
        updatePresenter.checkUpdate();
    }

    @OnClick(R.id.mLogoutTextView)
    public void logout() {
        MyApplication.getInstance().reLogin();
    }
}
