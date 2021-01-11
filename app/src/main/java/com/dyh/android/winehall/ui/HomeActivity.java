package com.dyh.android.winehall.ui;

import android.os.Bundle;

import com.dyh.android.winehall.R;
import com.dyh.android.winehall.presenter.AppUpdatePresenter;
import com.dyh.android.winehall.ui.adapter.MyViewPagerAdapter;
import com.dyh.android.winehall.ui.fragment.HomeLearnFragment;
import com.dyh.android.winehall.ui.fragment.MineFragment;
import com.dyh.android.winehall.view.IAppUpdateView;
import com.dyh.android.winehall.weigit.CustomViewPagerAddCache;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.mvp.impl.BaseMVPFragment;
import com.dyh.common.lib.weigit.tablayout.CommonTabLayout;
import com.dyh.common.lib.weigit.tablayout.SimpleTabEntity;
import com.dyh.common.lib.weigit.tablayout.listener.CustomTabEntity;
import com.dyh.common.lib.weigit.tablayout.listener.OnTabSelectListener;
import com.dyh.common.lib.weigit.titlebar.statusbar.StatusBarUtils;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 首页
 */
public class HomeActivity extends BaseMVPActivity implements IAppUpdateView {

    @BindView(R.id.mBottomCommonTabLayout)
    CommonTabLayout mBottomCommonTabLayout;
    @BindView(R.id.mViewPager)
    CustomViewPagerAddCache mViewPager;

    private AppUpdatePresenter mAppUpdatePresenter = new AppUpdatePresenter(this);

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initPage(@Nullable Bundle savedInstanceState) {
        boolean isStatusBarTrans = StatusBarUtils.transparentStatusBar(getWindow());
        //检查版本更新
//        mAppUpdatePresenter.checkUpdate();
        //初始化底部Tab栏
        ArrayList<CustomTabEntity> bottomMenuList = new ArrayList<CustomTabEntity>();
        bottomMenuList.add(new SimpleTabEntity(getString(R.string.main_tab1Title), R.mipmap.main_tab_read_select, R.mipmap.main_tab_read_normal));
        bottomMenuList.add(new SimpleTabEntity(getString(R.string.main_my), R.mipmap.main_tab_my_select, R.mipmap.main_tab_my_normal));
        mBottomCommonTabLayout.setTabData(bottomMenuList);

        MyViewPagerAdapter mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),
                new BaseMVPFragment[]{
                        HomeLearnFragment.buildIntentData(isStatusBarTrans),
                        MineFragment.buildIntentData(isStatusBarTrans)});
        mViewPager.setAdapter(mAdapter);

        //初始化监听事件
        mBottomCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                if (position == mAdapter.getCount() - 1
//                        || 0 == position) {
//                    StatusBarUtils.setLightMode(getWindow());
//                } else {
//                    StatusBarUtils.setDarkMode(getWindow());
//                }
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    @Nullable
    @Override
    public CommonTitleBar getCommonTitleBar() {
        return null;
    }
}
