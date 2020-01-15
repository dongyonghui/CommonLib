package com.dyh.commonlib.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.local.SPEntity;
import com.dyh.commonlib.entity.response.LoginResponseBean;
import com.dyh.commonlib.ui.adapter.HomeActionAdapter;
import com.dyh.commonlib.ui.view.IHomeView;
import com.dyh.common.lib.easy.EasySharedPreferences;
import com.dyh.common.lib.mvp.impl.BaseMVPFragment;
import com.dyh.common.lib.recyclerview_helper.BaseQuickAdapter;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import butterknife.BindView;

/**
 * 作者：DongYonghui
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：首页订单页面
 */
public class HomeFragment extends BaseMVPFragment implements IHomeView {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {

        final HomeActionAdapter homeActionAdapter = new HomeActionAdapter(R.layout.item_home_action);
        LoginResponseBean loginResponseBean = EasySharedPreferences.load(SPEntity.class).loginResponseBean;
        if (loginResponseBean != null) {
            homeActionAdapter.addListBottom(loginResponseBean.getHomeMenuActionBean(getContext()));
        }


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(homeActionAdapter);

        homeActionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                readyGo(homeActionAdapter.getItem(position).targetClass);
            }
        });
    }
}
