package com.dyh.commonlib.ui.statistics;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.DeliveryAreaItemBean;
import com.dyh.commonlib.entity.response.StatisticsResponseBean;
import com.dyh.commonlib.presenter.TodayHotGoodsPresenter;
import com.dyh.commonlib.ui.view.ITodayHodGoodsView;
import com.dyh.common.lib.mvp.impl.BaseMVPActivity;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;

import java.util.List;

import butterknife.BindView;

public class TodayHotGoodsActivity extends BaseMVPActivity implements ITodayHodGoodsView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mSpinner)
    Spinner mSpinner;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private TodayHotGoodsPresenter mPresenter = new TodayHotGoodsPresenter(this);


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_today_hot_goods;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {
        mPresenter.loadConditions();
    }

    @Override
    public void showConditionData(List<DeliveryAreaItemBean> list) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (DeliveryAreaItemBean deliveryAreaItemBean : list) {
            arrayAdapter.add(deliveryAreaItemBean.getName());
        }
        mSpinner.setAdapter(arrayAdapter);
    }

    @Override
    public void showData(StatisticsResponseBean responseBean) {

    }
}
