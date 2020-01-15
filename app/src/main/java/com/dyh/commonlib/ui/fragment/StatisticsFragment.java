package com.dyh.commonlib.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dyh.commonlib.R;
import com.dyh.commonlib.entity.response.StatisticsResponseBean;
import com.dyh.commonlib.presenter.StatisticsPresenter;
import com.dyh.commonlib.ui.adapter.StatisticsHotGoodsAdapter;
import com.dyh.commonlib.ui.view.IStatisticsView;
import com.dyh.common.lib.dw.util.MathUtil;
import com.dyh.common.lib.mvp.impl.BaseMVPFragment;
import com.dyh.common.lib.weigit.titlebar.widget.CommonTitleBar;
import com.dyh.widget.BothProgressBar;
import com.dyh.widget.ProgressBuildConfig;

import java.util.Calendar;

import butterknife.BindView;

/**
 * 作者：DongYonghui
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：统计页面
 */
public class StatisticsFragment extends BaseMVPFragment implements IStatisticsView {

    @BindView(R.id.mCommonTitleBar)
    CommonTitleBar mCommonTitleBar;
    @BindView(R.id.mDateTextView)
    TextView mDateTextView;
    @BindView(R.id.mCurrentCountTextView)
    TextView mCurrentCountTextView;
    @BindView(R.id.mTargetCountTextView)
    TextView mTargetCountTextView;
    @BindView(R.id.mOrderCountBothProgressBar)
    BothProgressBar mOrderCountBothProgressBar;
    @BindView(R.id.mCurrentMoneyTextView)
    TextView mCurrentMoneyTextView;
    @BindView(R.id.mTargetMoneyTextView)
    TextView mTargetMoneyTextView;
    @BindView(R.id.mOrderMoneyBothProgressBar)
    BothProgressBar mOrderMoneyBothProgressBar;
    @BindView(R.id.mTodayOrderCountTextView)
    TextView mTodayOrderCountTextView;
    @BindView(R.id.mPayUserCountTextView)
    TextView mPayUserCountTextView;
    @BindView(R.id.mNewUserCountTextView)
    TextView mNewUserCountTextView;
    @BindView(R.id.mPayNewUserCountTextView)
    TextView mPayNewUserCountTextView;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private StatisticsPresenter mPresenter = new StatisticsPresenter(this);


    @Override
    public CommonTitleBar getCommonTitleBar() {
        return mCommonTitleBar;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_statistics;
    }

    @Override
    public void initPage( Bundle savedInstanceState) {

        mRecyclerView.setNestedScrollingEnabled(false);

        /*HomeActionAdapter homeActionAdapter = new HomeActionAdapter(R.layout.item_home_action);
        homeActionAdapter.addListBottom(new HomeActionBean(getString(R.string.todayHotGoods), R.mipmap.menu_hot, TodayHotGoodsActivity.class));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(homeActionAdapter);
        homeActionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                readyGo(Objects.requireNonNull(homeActionAdapter.getItem(position)).targetClass);
            }
        });*/

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                mPresenter.loadData();
            }
        });

        mPresenter.loadData();
    }

    @Override
    public void showData(StatisticsResponseBean responseBean) {
        if (responseBean == null) {
            return;
        }
        //设置商品列表
        StatisticsHotGoodsAdapter hotGoodsAdapter = new StatisticsHotGoodsAdapter(R.layout.item_statistics_hot_goods);
        hotGoodsAdapter.addListBottom(responseBean.getSkuSaleList());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(hotGoodsAdapter);
        hotGoodsAdapter.setEmptyView(R.layout.layout_empty_data, mRecyclerView);

        mTodayOrderCountTextView.setText(responseBean.getOrderCount());
        mNewUserCountTextView.setText(responseBean.getNewUserCount());
        mPayUserCountTextView.setText(responseBean.getPayUserCount());
        mPayNewUserCountTextView.setText(responseBean.getNewPayUserCount());
        StatisticsResponseBean.OrderTaskBean orderTaskBean = responseBean.getOrderTask();
        if (null != orderTaskBean) {
            mDateTextView.setText(orderTaskBean.getMonthDate());
            mCurrentCountTextView.setText(getString(R.string.formatString_orderCount, orderTaskBean.getCurrentOrderCount()));
            mCurrentMoneyTextView.setText(getString(R.string.formatString_dealMoney, orderTaskBean.getCurrentTradePrice()));
            mTargetCountTextView.setText(getString(R.string.formatString_target, orderTaskBean.getTargetOrderCount()));
            mTargetMoneyTextView.setText(getString(R.string.formatString_target, orderTaskBean.getTargetTradePrice()));


            Calendar nowCalendar = Calendar.getInstance();
            float indicatorValue = (float) (nowCalendar.get(Calendar.DAY_OF_MONTH) * 1.0 / nowCalendar.getMaximum(Calendar.DAY_OF_MONTH) * 100);

            float progressValue = (float) MathUtil.div(orderTaskBean.getCurrentOrderCount(), orderTaskBean.getTargetOrderCount()) * 100;//当前订单数 进度条长度
            //如果超额完成，则当前进度应大于应完成进度，
            // 所以应完成进度条显示80%，当前进度条显示100%
            if (MathUtil.getDoubleNumber(orderTaskBean.getCurrentOrderCount()) > MathUtil.getDoubleNumber(orderTaskBean.getTargetOrderCount())) {
                ProgressBuildConfig buildConfig = mOrderCountBothProgressBar.getBuildConfig()
                        .setDyhIndicatorValue(80)
                        .setDyhProgressValue(100);
                mOrderCountBothProgressBar.setConfigValue(buildConfig);
            } else {
                ProgressBuildConfig buildConfig = mOrderCountBothProgressBar.getBuildConfig()
                        .setDyhIndicatorValue(indicatorValue)
                        .setDyhProgressValue(progressValue);
                mOrderCountBothProgressBar.setConfigValue(buildConfig);
            }
            progressValue = (float) MathUtil.div(orderTaskBean.getCurrentTradePrice(), orderTaskBean.getTargetTradePrice()) * 100;//当前订单数 进度条长度
            if (MathUtil.getDoubleNumber(orderTaskBean.getCurrentTradePrice()) > MathUtil.getDoubleNumber(orderTaskBean.getTargetTradePrice())) {
                ProgressBuildConfig buildConfig = mOrderMoneyBothProgressBar.getBuildConfig()
                        .setDyhIndicatorValue(80)
                        .setDyhProgressValue(100);
                mOrderMoneyBothProgressBar.setConfigValue(buildConfig);
            } else {
                ProgressBuildConfig buildConfig = mOrderMoneyBothProgressBar.getBuildConfig()
                        .setDyhIndicatorValue(indicatorValue)
                        .setDyhProgressValue(progressValue);
                mOrderMoneyBothProgressBar.setConfigValue(buildConfig);
            }
        }
    }
}
