package com.dyh.commonlib.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dyh.commonlib.R;
import com.dyh.commonlib.presenter.AppUpdatePresenter;
import com.dyh.commonlib.ui.adapter.MyViewPagerAdapter;
import com.dyh.commonlib.ui.fragment.HomeFragment;
import com.dyh.commonlib.ui.fragment.MineFragment;
import com.dyh.commonlib.ui.fragment.StatisticsFragment;
import com.dyh.commonlib.ui.view.IAppUpdateView;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.tablayout.CommonTabLayout;
import com.dyh.common.lib.weigit.tablayout.SimpleTabEntity;
import com.dyh.common.lib.weigit.tablayout.listener.CustomTabEntity;
import com.dyh.common.lib.weigit.tablayout.listener.OnTabSelectListener;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 首页
 */
public class HomeActivity extends BaseMVPActivity implements IAppUpdateView {

    @BindView(R.id.mBottomCommonTabLayout)
    CommonTabLayout mBottomCommonTabLayout;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    private AppUpdatePresenter mAppUpdatePresenter = new AppUpdatePresenter(this);

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        //检查版本更新
        mAppUpdatePresenter.checkUpdate();

        //初始化底部Tab栏
        ArrayList<CustomTabEntity> bottomMenuList = new ArrayList<CustomTabEntity>();
        bottomMenuList.add(new SimpleTabEntity(getString(R.string.home), R.mipmap.home_blue, R.mipmap.home_gray));
        bottomMenuList.add(new SimpleTabEntity(getString(R.string.statistics), R.mipmap.statistics_blue, R.mipmap.statistics_gray));
        bottomMenuList.add(new SimpleTabEntity(getString(R.string.mine), R.mipmap.mine_blue, R.mipmap.mine_gray));
        mBottomCommonTabLayout.setTabData(bottomMenuList);

        //初始化tab栏对应的界面
        MyViewPagerAdapter mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),
                new Fragment[]{new HomeFragment(), new StatisticsFragment(), new MineFragment()});
        mViewPager.setAdapter(mAdapter);


        //初始化监听事件
        mBottomCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mBottomCommonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return null;
    }
}
