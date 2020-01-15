package com.dyh.commonlib.ui.city;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.local.HomeActionBean;
import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.commonlib.entity.response.LoginResponseBean;
import com.dyh.commonlib.ui.adapter.HomeActionAdapter;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import butterknife.BindView;

/**
 * 城市管理
 */
public class CityManageActivity extends BaseMVPActivity {
    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_city_manage;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        HomeActionAdapter homeActionAdapter = new HomeActionAdapter(R.layout.item_home_action);

        LoginResponseBean loginResponseBean = EasySharedPreferences.load(SPEntity.class).loginResponseBean;
        if (loginResponseBean != null) {
            homeActionAdapter.addListBottom(loginResponseBean.getCityManageMenuActionBean(this));
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(homeActionAdapter);

        homeActionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeActionBean actionBean = homeActionAdapter.getItem(position);
                if (null != actionBean) {
                    readyGo(actionBean.targetClass);
                }
            }
        });
    }
}
